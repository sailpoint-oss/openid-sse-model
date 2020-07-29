package sse.json;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.databind.util.StdDateFormat;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;

/**
 * ObjectMapper with a standardized configuration.
 */
public class ObjectMapperStandard {
	
	static final Logger log = LoggerFactory.getLogger(ObjectMapperStandard.class);
	
	private ObjectMapper om = null;

	private static ObjectMapperStandard singleton = null;
	
	private ObjectMapperStandard() {
		
		om = new ObjectMapper();
		
		// om.registerModule(new JavaTimeModule());
		om.disable(SerializationFeature.WRITE_DATES_AS_TIMESTAMPS);

		// CAEP/SSE JSON parsing requires this.
		om.enable(DeserializationFeature.ACCEPT_SINGLE_VALUE_AS_ARRAY);
		
		// StdDateFormat is ISO8601 since Jackson 2.9, hooray!
		om.setDateFormat(new StdDateFormat().withColonInTimeZone(true));
		
		// For inbound compatibility with other serializers.
		om.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		om.configure(JsonParser.Feature.ALLOW_UNQUOTED_FIELD_NAMES, true);
	}
	
	public static ObjectMapperStandard getInstance() {
		if (null != singleton) return singleton;
		synchronized (ObjectMapperStandard.class) {
			if (null == singleton) {
				singleton = new ObjectMapperStandard();
			}
		}
		return singleton;
	}
	
	public static ObjectMapper getObjectMapper() {
		return getInstance().om;
	}
	
	public static String getJson(Object o, boolean prettyPrinted) {
		ObjectMapper om = ObjectMapperStandard.getObjectMapper();
		try {
			if (prettyPrinted) {
				return om.writerWithDefaultPrettyPrinter().writeValueAsString(o);
			} else {
				return om.writeValueAsString(o);
			}
		} catch (JsonProcessingException e) {
			log.error("Failure serializing {} to JSON", o.getClass().getName(), e);
		}
		return null;
	}
	
}
