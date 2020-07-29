package sse.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

@JsonPropertyOrder({ 
	"issuer", 
	"jwks_uri", 
	"configuration_endpoint", 
	"delivery_methods_supported", 
	"status_endpoint", 
	"verification_endpoint",
	"add_subject_endpoint",
	"remove_subject_endpoint"
})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TransmitterConfig {
	
	/**
	 * REQUIRED.  URL using the https scheme with no query or
	 * fragment component that the Transmitter asserts as its Issuer
	 * Identifier.  This MUST be identical to the iss claim value in
	 * Security Event Tokens issued from this Transmitter.
	 */
	@JsonProperty("issuer")
	String issuer;
	
	/**
	 * REQUIRED.  URL of the Transmitter's JSON Web Key Set
	 * [RFC7517] document.  This contains the signing key(s) the Receiver
	 * uses to validate signatures from the Transmitter.
	 */
	@JsonProperty("jwks_uri")
	@JsonAlias("jwksUri")
	String jwksUri;
	
	/**
	 * OPTIONAL.  List of string representations of the
	 * numerical profile versions defined in Section 2 supported by this 
	 * transmitter.  If this element is missing in the Transmitter 
	 * Configuration Metadata, then a single-valued list containing the
	 * value "1.0" MUST be assumed.
	 */
	@JsonProperty("supported_versions")
	@JsonAlias("supportedVersions")
	String supportedVersions = "1.0";
	
	/**
	 * RECOMMENDED.  List of supported delivery method URIs.
	 */
	@JsonProperty("delivery_methods_supported")
	@JsonAlias("deliveryMethodsSupported")
	List<String> deliveryMethodsSupported; 
	
	/**
	 * OPTIONAL.  The URL of the Configuration Endpoint.
	 */
	@JsonProperty("configuration_endpoint")
	@JsonAlias("configurationEndpoint")
	String configurationEndpoint;
	
	/**
	 * OPTIONAL.  The URL of the Status Endpoint.
	 */
	@JsonProperty("status_endpoint")
	@JsonAlias("statusEndpoint")
	String statusEndpoint;
	
	/**
	 * OPTIONAL.  The URL of the Add Subject Endpoint.
	 */
	@JsonProperty("add_subject_endpoint")
	@JsonAlias("addSubjectEndpoint")
	String addSubjectEndpoint;
	
	/**
	 * OPTIONAL.  The URL of the Remove Subject Endpoint.
	 */
	@JsonProperty("remove_subject_endpoint")
	@JsonAlias("removeSubjectEndpoint")
	String removeSubjectEndpoint;
	
	/**
	 * OPTIONAL.  The URL of the Verification Endpoint.
	 */
	@JsonProperty("verification_endpoint")
	@JsonAlias("verificationEndpoint")
	String verificationEndpoint;

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getJwksUri() {
		return jwksUri;
	}

	public void setJwksUri(String jwksUri) {
		this.jwksUri = jwksUri;
	}

	public String getSupportedVersions() {
		return supportedVersions;
	}

	public void setSupportedVersions(String supportedVersions) {
		this.supportedVersions = supportedVersions;
	}

	public List<String> getDeliveryMethodsSupported() {
		return deliveryMethodsSupported;
	}

	public void setDeliveryMethodsSupported(List<String> deliveryMethodsSupported) {
		this.deliveryMethodsSupported = deliveryMethodsSupported;
	}

	public String getConfigurationEndpoint() {
		return configurationEndpoint;
	}

	public void setConfigurationEndpoint(String configurationEndpoint) {
		this.configurationEndpoint = configurationEndpoint;
	}

	public String getStatusEndpoint() {
		return statusEndpoint;
	}

	public void setStatusEndpoint(String statusEndpoint) {
		this.statusEndpoint = statusEndpoint;
	}

	public String getAddSubjectEndpoint() {
		return addSubjectEndpoint;
	}

	public void setAddSubjectEndpoint(String addSubjectEndpoint) {
		this.addSubjectEndpoint = addSubjectEndpoint;
	}

	public String getRemoveSubjectEndpoint() {
		return removeSubjectEndpoint;
	}

	public void setRemoveSubjectEndpoint(String removeSubjectEndpoint) {
		this.removeSubjectEndpoint = removeSubjectEndpoint;
	}

	public String getVerificationEndpoint() {
		return verificationEndpoint;
	}

	public void setVerificationEndpoint(String verificationEndpoint) {
		this.verificationEndpoint = verificationEndpoint;
	}
	
}
