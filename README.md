# sse-openid-model

An implementation of the Shared Signals and Events (SSE) data model for the Continuous 
Access Evaluation Protocol (CAEP) standard.

This library provides serialize-able POJOs and classes to implement SSE and CAEP under
a Java environment. It includes the following dependences:

 - SLF4J for logging and exception printing and integrating with surrounding applications.
 - Jackson Databind for JSON serialization.
 
## Examples

Producing a Security Event Token using this library involves constructing the objects
representing the `Subject`, the `SSEvent` (Shared Signals Event), and the `SEToken` (Security 
Event Token) that will carry the event.  At its most basic level an `SEToken` can carry 
one `SSEvent`.  

```java
	Subject subj = new Subject();
	subj.setSubjectType(Subject.SubjectTypes.id_token_claims);
	subj.setCategory(SubjectCategories.device);
	subj.setSpagId("https://example.com/v2/Groups/e9e30dba-f08f-4109-8486-d5c6a331660a");
	subj.setPhoneNumber("+1 (408) 555-1212");

	SSEvent evt = new SSEvent();
	evt.setEventType(SSEventTypes.IPADDR_CHAGNED);
	evt.setSubject(subj);

	SEToken set = new SEToken();
	set.setIssuer("https://sp.example2.com/");
	set.setJti("756E69717565206964656E746966696572");
	set.setIssuedAt(System.currentTimeMillis());
	set.setAudience("636C69656E745F6964");
	set.addEvent(evt);
```

See more code examples in `JsonRoundTrip.java`.

## Compiling
 
 This library is implemented as a Gradle based java library.  Running:
 
	./gradlew assemble

Produces a versions .jar file in the build/libs directory:

	ls -latr build/libs
	...  13308 Jul 29 12:33 sse-openid-model-0.1.0.jar
	
## Testing 

The library has TestNG tests implemented in `/src/test/java/` and are run with Gradle:

	./gradlew test

