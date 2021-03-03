/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package net.openid.sse.model;

public class ValidationException extends Exception {
    public ValidationException(String message) {
        super(message);
    }
}
