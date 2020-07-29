# sse-openid-model

An implementation of the Shared Signals and Events (SSE) data model for the Continuous 
Access Evaluation Protocol (CAEP) standard.

This library provides serialize-able POJOs and classes to implement SSE and CAEP under
a Java environment. It includes the following dependences:

 - SLF4J for logging and exception printing and integrating with surrounding applications.
 - Jackson Databind for JSON serialization.
 
## Examples

TODO: Add code examples beyond those in `JsonRoundTrip.java`.

## Compiling
 
 This library is implemented as a Gradle based java library.  Running:
 
	./gradlew assemble

Produces a versions .jar file in the build/libs directory:

	ls -latr build/libs
	...  13308 Jul 29 12:33 sse-openid-model-0.1.0.jar
	
## Testing 

The library has TestNG tests implemented in `/src/test/java/` and are run with Gradle:

	./gradlew test

