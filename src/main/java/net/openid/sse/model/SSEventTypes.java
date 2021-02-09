package net.openid.sse.model;

/**
 * Commonly used SSE event types for SSE/CAEP events.
 *
 */
class SSESPECS {
	public static final String RISC_PREFIX = "risc";
	public static final String CAEP_PREFIX = "caep";

}

public enum SSEventTypes {

	RISC_ACCOUNT_DISABLED ("account-disabled", SSESPECS.RISC_PREFIX, true),
	RISC_ACCOUNT_ENABLED  ("account-enabled", SSESPECS.RISC_PREFIX, true),
	RISC_VERIFICATION     ("verification", SSESPECS.RISC_PREFIX, true),
	CAEP_IPADDR_CHANGED   ("ip-address-changed", SSESPECS.CAEP_PREFIX, true),
	CAEP_STREAM_UPDATED   ("stream-updated", SSESPECS.CAEP_PREFIX, true),
	CAEP_TOKEN_REVOCATION ("token-revocation", SSESPECS.CAEP_PREFIX, true),
	CAEP_SESSION_REVOKED ("session-revoked", SSESPECS.CAEP_PREFIX, true),
	CAEP_TOKEN_CLAIMS_CHANGE ("token-claims-change", SSESPECS.CAEP_PREFIX, true),
	CAEP_CREDENTIAL_CHANGE ("credential-change", SSESPECS.CAEP_PREFIX, true),
	CAEP_ASSURANCE_LEVEL_CHANGE ("assurance-level-change", SSESPECS.CAEP_PREFIX, true),
	CAEP_DEVICE_COMPLIANCE_CHANGE ("device-compliance-change", SSESPECS.CAEP_PREFIX, true);

	// Name space prefix string for Shared Signals and Event types.
	private static final String SSE_PREFIX = "https://schemas.openid.net/secevent/";

	private final String name;

	SSEventTypes(final String s, final String spec, boolean addPrefix) {
		if (addPrefix) {
			name = SSE_PREFIX + spec + "/event-type/" + s;
		} else {
			name = s;
		}
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
