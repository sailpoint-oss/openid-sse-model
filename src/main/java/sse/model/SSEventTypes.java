package sse.model;

/**
 * Commonly used SSE event types for SSE/CAEP events.
 *
 */
public enum SSEventTypes {
	
	ACCOUNT_DISABLED ("account-disabled", true),
	ACCOUNT_ENABLED  ("account-enabled", true),
	VERIFICATION     ("verification", true), 
	IPADDR_CHAGNED   ("ip-address-changed", true),
	STREAM_UPDATED   ("stream-updated", true),
	TOKEN_REVOCATION ("token-revocation", true);
	
	// Name space prefix string for Shared Signals and Event types.
	public static final String SSE_PREFIX = "https://schemas.openid.net/secevent/risc/event-type/";

	private final String name;

	private SSEventTypes(String s, boolean addPrefix) {
		if (addPrefix) {
			name = SSE_PREFIX + s;
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
