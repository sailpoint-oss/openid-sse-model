# sse-openid-model

An implementation of the Shared Signals and Events (SSE) data model for the Continuous 
Access Evaluation Protocol (CAEP) standard.

This library provides serialize-able POJOs and classes to implement SSE and CAEP under
a Java environment. It includes the following dependences:

- com.nimbusds nimbus-jose-jwt for JSONObject and JWTClaimsSet
- SLF4J for logging and exception printing and integrating with surrounding applications.
 
 
## Examples

Producing a Security Event Token using this library involves constructing the objects
representing the `SubjectIdentifier`, the `SSEvent` (Shared Signals Event), and the `SEToken` (Security 
Event Token) that will carry the event.  At its most basic level an `SEToken` can carry 
one `SSEvent`.  

```java
        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifier.EMAIL_SUBJECT_IDENTIFIER_TYPE)
                .email("foo@example.com")
                .build();

        SSEvent evt = new SSEvent.Builder()
                .eventType(SSEventTypes.CAEP_IPADDR_CHANGED)
                .subject(subj)
                .ipAddress("123.45.67.89")
                .build();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://sp.example2.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(1520364019))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();
```

See more code examples in `OpenIDSSEProfileTest.java`.

## Compiling
 
 This library is implemented as a Gradle based java library.  Running:
 
	./gradlew assemble

Produces a versions .jar file in the build/libs directory:

	ls -latr build/libs
	...  13308 Jul 29 12:33 sse-openid-model-0.1.0.jar
	
## Testing 

The library has tests implemented in `/src/test/java/` and are run with Gradle:

	./gradlew test

