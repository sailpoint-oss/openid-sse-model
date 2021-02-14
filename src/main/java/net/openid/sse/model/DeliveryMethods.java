package net.openid.sse.model;

import java.util.HashMap;
import java.util.Map;

/**
 * Commonly used SSE delivery methods for SSE/RISC/CAEP events.
 *
 */
public enum DeliveryMethods {
	
	PUSH ("push"),
	POLL ("poll");
	
	// Name space prefix string for Shared Signals and Event types.
	public static final String SSE_PREFIX = "https://schemas.openid.net/secevent/risc/delivery-method/";

	private final String name;

	DeliveryMethods(final String s) {
		name = SSE_PREFIX + s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	@Override
	public String toString() {
		return this.name;
	}

	private static final Map<String, DeliveryMethods> BY_NAME = new HashMap<>();

	static {
		for (DeliveryMethods t: values()) {
			BY_NAME.put(t.name, t);
		}
	}

	public static DeliveryMethods valueOfLabel(String name) {
		return BY_NAME.get(name);
	}

	public static boolean contains(final String name) {
		return BY_NAME.containsKey(name);
	}


}
