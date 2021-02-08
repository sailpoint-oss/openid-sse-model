package sse.model;

import com.nimbusds.jose.shaded.json.JSONObject;

import java.util.List;

public class TransmitterConfig extends JSONObject {

	public static class Builder {

		/**
		 * REQUIRED.  URL using the https scheme with no query or
		 * fragment component that the Transmitter asserts as its Issuer
		 * Identifier.  This MUST be identical to the iss claim value in
		 * Security Event Tokens issued from this Transmitter.
		 */
		private static final String ISSUER_CLAIM = "issuer";


		/**
		 * REQUIRED.  URL of the Transmitter's JSON Web Key Set
		 * [RFC7517] document.  This contains the signing key(s) the Receiver
		 * uses to validate signatures from the Transmitter.
		 */
		private static final String JWKS_URI_CLAIM = "jwks_uri";

		/**
		 * RECOMMENDED.  List of supported delivery method URIs.
		 */
		private static final String DELIVERY_METHODS_SUPPORTED_CLAIM = "delivery_methods_supported";

		/**
		 * OPTIONAL.  The URL of the Configuration Endpoint.
		 */
		private static final String CONFIGURATION_ENDPOINT_CLAIM = "configuration_endpoint";


		/**
		 * OPTIONAL.  The URL of the Status Endpoint.
		 */
		private static final String STATUS_ENDPOINT_CLAIM = "status_endpoint";

		/**
		 * OPTIONAL.  The URL of the Add Subject Endpoint.
		 */
		private static final String ADD_SUBJECT_ENDPOINT_CLAIM = "add_subject_endpoint";


		/**
		 * OPTIONAL.  The URL of the Remove Subject Endpoint.
		 */
		private static final String REMOVE_SUBJECT_ENDPOINT_CLAIM = "remove_subject_endpoint";

		/**
		 * OPTIONAL.  The URL of the Verification Endpoint.
		 */
		private static final String VERIFICATION_ENDPOINT_CLAIM = "verification_endpoint";

		/**
		 * The claims.
		 */
		private final TransmitterConfig claims = new TransmitterConfig();

		public Builder issuer(final String iss) {

			claims.put(ISSUER_CLAIM, iss);
			return this;
		}

		public Builder jwks_uri(final String jwks_uri) {

			claims.put(JWKS_URI_CLAIM, jwks_uri);
			return this;
		}

		public Builder delivery_methods_supported(final List<String> methods) {
			claims.put(DELIVERY_METHODS_SUPPORTED_CLAIM, methods);
			return this;
		}

		public Builder configuration_endpoint(final String endpoint) {
			claims.put(CONFIGURATION_ENDPOINT_CLAIM, endpoint);
			return this;
		}

		public Builder status_endpoint(final String endpoint) {
			claims.put(STATUS_ENDPOINT_CLAIM, endpoint);
			return this;
		}

		public Builder add_subject_endpoint(final String endpoint) {
			claims.put(ADD_SUBJECT_ENDPOINT_CLAIM, endpoint);
			return this;
		}

		public Builder remove_subject_endpoint(final String endpoint) {
			claims.put(REMOVE_SUBJECT_ENDPOINT_CLAIM, endpoint);
			return this;
		}

		public Builder verification_endpoint(final String endpoint) {
			claims.put(VERIFICATION_ENDPOINT_CLAIM, endpoint);
			return this;
		}

		public TransmitterConfig build() {
			return claims;
		}
	}
}