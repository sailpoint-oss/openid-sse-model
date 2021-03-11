/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

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
		private static final String ISSUER_MEMBER = "issuer";


		/**
		 * REQUIRED.  URL of the Transmitter's JSON Web Key Set
		 * [RFC7517] document.  This contains the signing key(s) the Receiver
		 * uses to validate signatures from the Transmitter.
		 */
		private static final String JWKS_URI_MEMBER = "jwks_uri";

		/**
		 * RECOMMENDED.  List of supported delivery method URIs.
		 */
		private static final String DELIVERY_METHODS_SUPPORTED_MEMBER = "delivery_methods_supported";

		/**
		 * OPTIONAL.  The URL of the Configuration Endpoint.
		 */
		private static final String CONFIGURATION_ENDPOINT_MEMBER = "configuration_endpoint";


		/**
		 * OPTIONAL.  The URL of the Status Endpoint.
		 */
		private static final String STATUS_ENDPOINT_MEMBER = "status_endpoint";

		/**
		 * OPTIONAL.  The URL of the Add Subject Endpoint.
		 */
		private static final String ADD_SUBJECT_ENDPOINT_MEMBER = "add_subject_endpoint";


		/**
		 * OPTIONAL.  The URL of the Remove Subject Endpoint.
		 */
		private static final String REMOVE_SUBJECT_ENDPOINT_MEMBER = "remove_subject_endpoint";

		/**
		 * OPTIONAL.  The URL of the Verification Endpoint.
		 */
		private static final String VERIFICATION_ENDPOINT_MEMBER = "verification_endpoint";

		// List of Strings
		private static final String CRITICAL_SUBJECT_MEMBERS_MEMBER = "critical_subject_members";

		/**
		 * The members.
		 */
		private final TransmitterConfig members = new TransmitterConfig();

		public Builder issuer(final String iss) {

			members.put(ISSUER_MEMBER, iss);
			return this;
		}

		public Builder jwksUri(final String jwks_uri) {

			members.put(JWKS_URI_MEMBER, jwks_uri);
			return this;
		}

		public Builder deliveryMethods(final List<String> methods) {
			members.put(DELIVERY_METHODS_SUPPORTED_MEMBER, methods);
			return this;
		}

		public Builder configurationEndpoint(final String endpoint) {
			members.put(CONFIGURATION_ENDPOINT_MEMBER, endpoint);
			return this;
		}

		public Builder statusEndpoint(final String endpoint) {
			members.put(STATUS_ENDPOINT_MEMBER, endpoint);
			return this;
		}

		public Builder addSubjectEndpoint(final String endpoint) {
			members.put(ADD_SUBJECT_ENDPOINT_MEMBER, endpoint);
			return this;
		}

		public Builder removeSubjectEndpoint(final String endpoint) {
			members.put(REMOVE_SUBJECT_ENDPOINT_MEMBER, endpoint);
			return this;
		}

		public Builder verificationEndpoint(final String endpoint) {
			members.put(VERIFICATION_ENDPOINT_MEMBER, endpoint);
			return this;
		}

		public Builder criticalSubjectMembers(final List<String> subjectMembers) {
			members.put(CRITICAL_SUBJECT_MEMBERS_MEMBER, subjectMembers);
			return this;
		}


		public TransmitterConfig build() {
			return members;
		}
	}
}