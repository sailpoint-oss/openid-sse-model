package net.openid.sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class Utils {

    public static void validateMember(final SSEvent event, final String member, final Class<?> memberCls) throws ValidationException {
        final SSEventTypes eventType = event.getEventType();
        if (null == eventType) {
            /* Unknown event type, not instantiated via a normal constructor. */
            return;
        }
        JSONObject members = (JSONObject) event.get(eventType.toString());

        final Object o = members.get(member);
        if (null == o) {
            throw new ValidationException(event.getClass().getName() + " member " + member + " is missing or null.");
        }

        // Fun with reflection to invoke contains() if the enum.name has it (and throw an exception if it does not so we can go fix it
        Method methodToFind = null;
        try {
            methodToFind = memberCls.getMethod("contains", new Class[]{String.class});
        } catch (NoSuchMethodException | SecurityException e) {
            throw new ValidationException(memberCls.getName() + " does not have a contains() method.");
        }

        try {
            final Boolean present = (Boolean) methodToFind.invoke(memberCls, o.toString());
            if (!present) {
                throw new ValidationException(event.getClass().getName() + " member " + member + " has an invalid value.");
            }
        }
        catch(IllegalAccessException e) {
            throw new ValidationException(event.getClass().getName() + " member " + member + " IllegalAccessException: " + e);
        }
        catch(InvocationTargetException e) {
            throw new ValidationException(event.getClass().getName() + " member " + member + " InvocationTargetException: " + e);
        }
    }

}
