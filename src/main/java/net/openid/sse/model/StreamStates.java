package net.openid.sse.model;

/**
 * Commonly used SSE stream states for SSE/RISC/CAEP events.
 * 
 * A Transmitter MAY decide to enable, pause or disable updates about a
 * SPAG independently of an update request from a Receiver.  If a
 * Transmitter decides to start or stop events for a SPAG then the
 * Transmitter MUST do the following according to the status of the
 * stream. If the stream is:
 *  
 * Enabled  the Transmitter MUST send a SPAG stream updated
 *    (Section 8.1.5) event respectively to the Receiver within the Event Stream.
 * Paused  the Transmitter SHOULD send SPAG stream updated
 *    (Section 8.1.5) after the Event Stream is re-started.
 * Disabled  the Transmitter MAY send SPAG stream updated
 *    (Section 8.1.5) after the Event Stream is re-enabled.
 *
 */
public enum StreamStates {
	
	ENABLED ("enabled"),
	PAUSED ("paused"),
	DISABLED ("disabled");
	
	private final String name;

	StreamStates(String s) {
		name = s;
	}

	public boolean equalsName(String otherName) {
		return name.equals(otherName);
	}

	@Override
	public String toString() {
		return this.name;
	}

}
