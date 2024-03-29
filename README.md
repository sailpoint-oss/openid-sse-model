# openid-sse-model

An implementation of the [OpenID Foundation](https://openid.net/wg/sse/)'s Shared Signals and Events (SSE) data model for the Continuous Access Evaluation Protocol (CAEP) and Risk Incident Sharing and Coordination (RISC) event profiles.

This library provides classes implementing SSE (both CAEP and RISC profiles) under a Java environment. It includes the
following dependences:

- com.nimbusds nimbus-jose-jwt for JSONObject and JWTClaimsSet

## Examples

Producing a Security Event Token using this library involves constructing the objects representing the Subject
Identifier, the Shared Signals Event, and the Security Event Token that will carry the event. There are specific event
classes for each defined RISC and CAEP event. Construction follows the builder pattern. Events each have a validate()
method to verify mandatory fields.

        OpaqueSubjectIdentifier session = new OpaqueSubjectIdentifier.Builder()
                .id("dMTlD|1600802906337.16|16008.16")
                .build();

        SubjectIdentifier user = new SubjectIdentifier.Builder()
                .format(IdentifierFormats.ISSUER_SUBJECT)
                .issuer("https://idp.example.com/123456789/")
                .subject("dMTlD|1600802906337.16|16008.16")
                .build();

        OpaqueSubjectIdentifier tenant = new OpaqueSubjectIdentifier.Builder()
                .id("123456789")
                .build();

        SubjectIdentifier subj = new SubjectIdentifier.Builder()
                .session(session)
                .user(user)
                .tenant(tenant)
                .build();

        final long now = System.currentTimeMillis() /1000;
        CAEPSessionRevoked evt = new CAEPSessionRevoked.Builder()
                .initiatingEntity(CAEPInitiatingEntity.POLICY)
                .reasonAdmin("Landspeed Policy Violation: C076E82F")
                .reasonUser("Access attempt from multiple regions.")
                .eventTimestamp(now)
                .subject(subj)
                .build();
        evt.validate();

        JWTClaimsSet set = new JWTClaimsSet.Builder()
                .issuer("https://idp.example.com/123456789/")
                .jwtID(UUID.randomUUID().toString())
                .issueTime(DateUtils.fromSecondsSinceEpoch(now))
                .audience("https://sp.example.com/caep")
                .claim(SEToken.EVENTS_CLAIM, evt)
                .build();

See more usage examples in `src/test`.

## Compiling

This library is implemented as a Gradle based java library. Java 9+ is required. Running:

	./gradlew build

produces a versioned .jar file in the build/libs directory:

    ls -ltr build/libs
    total 700
    -rwxrwxrwx 1 mdomsch mdomsch  64318 Mar 10 22:33 openid-sse-model-0.1.0-SNAPSHOT.jar
    -rwxrwxrwx 1 mdomsch mdomsch 616249 Mar 10 22:33 openid-sse-model-0.1.0-SNAPSHOT-javadoc.jar
    -rwxrwxrwx 1 mdomsch mdomsch  28791 Mar 10 22:33 openid-sse-model-0.1.0-SNAPSHOT-sources.jar

## Testing

The library has tests implemented in `/src/test/java/` and are run with Gradle:

	./gradlew test

## Incorporation into your own projects

The library is published to Maven Central, and may be incorporated into your own projects as a dependency.
Because the library is under active and frequent development, you may wish to pull from the OSSRH
snapshot repository as added here:

`build.gradle`

    repositories {
        mavenCentral()
            maven {
                url "https://s01.oss.sonatype.org/content/repositories/releases/"
            }
            maven {
                url "https://s01.oss.sonatype.org/content/repositories/snapshots/"
            }
    }
    
    dependencies {
        implementation group: 'com.sailpoint', name: 'openid-sse-model', version: '0.2.0'
    }

