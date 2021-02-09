package net.openid.sse.model;

/**
 * Commonly used SSE delivery methods for SSE/RISC/CAEP events.
 *
 */
public enum DeliveryMethods {
	
	PUSH ("push", true),
	POLL ("poll", true);
	
	// Name space prefix string for Shared Signals and Event types.
	public static final String SSE_PREFIX = "https://schemas.openid.net/secevent/risc/delivery-method/";

	private final String name;

	DeliveryMethods(String s, boolean addPrefix) {
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
