package sse.model;

import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

/**
 * Model of a Subject identifier for an SSE Event. 
 *
 */
@JsonPropertyOrder({ "subject_type", "iss", "subject", "spag_id"})
@JsonInclude(JsonInclude.Include.NON_NULL)
public class Subject {
	
	/**
	 * Defined SubjectTypes of SSE/CAEP event types.
	 * 
	 * Java note: If we need more complicated mappings or ones that do not 
	 * match lower case Java keyword allowed characters then we can employ 
	 * the feature shown here:
	 * 
	 * https://stackoverflow.com/questions/6667243/using-enum-values-as-string-literals
	 * 
	 */
	public enum SubjectTypes {
		// An e-mail address identifies the subject.
		email,
		// An E.164 formatted phone number identities the subject.
		phone,
		// This subject is a Subject Principal Administrative Group.
		spag,
		// This subject is a subject type defined by the Issuer of the SET event.
		iss_sub,
		// TODO: Add description for this SubjectType.
		id_token_claims
	}
	
	/**
	 * If the "category" claim is not present, the category is assumed to be
	 * "user", unless a different default interpretation is specified in the
	 * subject type description.
	 */
	public enum SubjectCategories {
		// Specifies that the subject category is a user.
		user, 
		// Specifies that the subject category is a device.
		device,
		// Specifies that the subject category is a session.
		session
	}
	
	/**
	 * The type of subject for the SSE event. See {@link SubjectTypes} for standard
	 * subject types. 
	 */
	@JsonProperty("subject_type")
	@JsonAlias("subjectType")
	String subjectType;
	
	@JsonProperty("subject")
	String subject;
	
	@JsonProperty("category")
	String category;
	
	@JsonProperty("spag_id")
	@JsonAlias("spagId")
	String spagId;
	
	/**
	 * The Subject structure has its own concept of issuer when the type of a 
	 * subject is set to 'iss_sub'.  See section:
	 *    '6.1.3.  Issuer and Subject Subject Identifier Type'
	 */
	@JsonProperty("iss")
	@JsonAlias("issuer")
	String issuer;
	
	/**
	 * Property identifying the phone number of the subject in E.164 format.
	 * This is expected to be present when the subject_type field is populated
	 * with 'phone'.
	 */
	@JsonProperty("phone_number")
	@JsonAlias("phoneNumber")
	String phoneNumber;
	
	/**
	 * SAML Subject identifier type.
	 */
	@JsonProperty("assertion_id")
	@JsonAlias("assertionId")
	String assertionId;

	public String getSubjectType() {
		return subjectType;
	}

	public void setSubjectType(String subject_type) {
		this.subjectType = subject_type;
	}
	
	public void setSubjectType(SubjectTypes sbjType) {
		this.subjectType = sbjType.name();
	} 

	public String getSubject() {
		return subject;
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public String getSpagId() {
		return spagId;
	}

	public void setSpagId(String spag_id) {
		this.spagId = spag_id;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}
	
	public void setCategory(SubjectCategories subCat) {
		this.category = subCat.name();
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getIssuer() {
		return issuer;
	}

	public void setIssuer(String issuer) {
		this.issuer = issuer;
	}

	public String getAssertionId() {
		return assertionId;
	}

	public void setAssertionId(String assertionId) {
		this.assertionId = assertionId;
	}
	
}
