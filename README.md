# sse-openid-model

An implementation of the Shared Signals and Events (SSE) data model for the Continuous 
Access Evaluation Protocol (CAEP) and Risk Incident Sharing and Coordination (RISC) event profiles.

This library provides classes implementing SSE (both CAEP and RISC profiles) under
a Java environment. It includes the following dependences:

- com.nimbusds nimbus-jose-jwt for JSONObject and JWTClaimsSet

 
## Examples

Producing a Security Event Token using this library involves constructing the objects
representing the Subject Identifier, the Shared Signals Event, and the Security 
Event Token that will carry the event.  There are specific event classes for each defined RISC and CAEP event.
Construction follows the builder pattern. Events each have a validate() method to verify mandatory fields. 

        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("jane.smith@example.com")
                .build();

        SubjectIdentifier device = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/3957ea72-1b66-44d6-a044-d805712b9288/")
                .subject("e9297990-14d2-42ec-a4a9-4036db86509a")
                .build();

        SubjectIdentifier userDevice = new SubjectIdentifier.Builder()
                .subjectType(SubjectIdentifierTypes.USER_DEVICE_SESSION)
                .user(user)
                .device(device)
                .build();

        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
               .subject(userDevice)
               .build();
        evt.validate(); /* throws ValidationException */

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/")
                .jwtID("756E69717565206964656E746966696572")
                .issueTime(DateUtils.fromSecondsSinceEpoch(System.currentTimeMillis()/1000))
                .audience("636C69656E745F6964")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

See more usage examples in `src/test`.

## Compiling
 
 This library is implemented as a Gradle based java library.  Java 9+ is required.  Running:
 
	./gradlew build

produces a versioned .jar file in the build/libs directory:

	ls -latr build/libs
	...  13308 Jul 29 12:33 openid-sse-model-0.1.0-SNAPSHOT.jar
	
## Testing 

The library has tests implemented in `/src/test/java/` and are run with Gradle:

	./gradlew test
