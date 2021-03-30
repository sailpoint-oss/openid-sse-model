/*
 * Copyright (c) 2021 SailPoint Technologies, Inc.
 *
 * SPDX-License-Identifier: Apache-2.0
 */

package com.sailpoint.sse.model;

public class ValidationException extends Exception {
    public ValidationException(final String message) {
        super(message);
    }
}
