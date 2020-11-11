package sse.scaffolding;

import java.util.ArrayList;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;

import sse.json.ObjectMapperStandard;
import sse.model.SEToken;
import sse.model.SSEvent;
import sse.model.SSEventTypes;
import sse.model.Subject;
import sse.model.Subject.SubjectCategories;
import sse.model.TransmitterConfig;

public class MultiEventInSet {
	
	static final Logger log = LoggerFactory.getLogger(MultiEventInSet.class);

	public static void main(String[] args) {

		System.err.println("");
		System.out.println("Multi-Events per SET Event");

		{ 
			System.out.println("# Draft of Multi Events per SET, 20200818.");
			
			Subject subj01 = new Subject();
			subj01.setSubjectType(Subject.SubjectTypes.email);
			subj01.setSubject("adam.hampton@sailpoint.com");

			SSEvent evt01 = new SSEvent();
			evt01.setEventType(SSEventTypes.ACCOUNT_ENABLED);
			evt01.setSubject(subj01);

			Subject subj02 = new Subject();
			subj02.setSubjectType(Subject.SubjectTypes.email);
			subj02.setSubject("matt.domsch@sailpoint.com");

			SSEvent evt02 = new SSEvent();
			evt02.setEventType(SSEventTypes.ACCOUNT_ENABLED);
			evt02.setSubject(subj02);
			
			SEToken set = new SEToken();
			set.setIssuer("https://idp.example.com/");
			set.setJti("756E69717565206964656E7469666900001");
			set.setIssuedAt(System.currentTimeMillis());
			set.setAudience("636C69656E745F69FF");
			set.addEvent(evt01);
			set.addEvent(evt02);

			String json = ObjectMapperStandard.getJson(set, true);
			System.out.println(json);
			
			try {
				SEToken setNew = ObjectMapperStandard.getObjectMapper().readValue(json, SEToken.class);
			} catch (JsonMappingException e) {
				log.error("JsonMappingException failure de-serializing JSON {}", json, e);
			} catch (JsonProcessingException e) {
				log.error("JsonMappingException failure de-serializing JSON {}", json, e);
			}
		}

	}

}
