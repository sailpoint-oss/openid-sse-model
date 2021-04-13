/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

module com.sailpoint.sse.model {
    exports com.sailpoint.sse.model;
    exports com.sailpoint.sse.model.caep;
    exports com.sailpoint.sse.model.risc;
    exports com.sailpoint.sse.model.sse;
    exports com.sailpoint.sse.model.oauth;
    requires transitive com.nimbusds.jose.jwt;
}
