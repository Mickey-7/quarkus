package org.acme.error;

public class ErrorCodes {
    //error code for runtime exception
    public static final int ERR_RUNTIME = 1000;
    //error code for handler not found
    public static final int ERR_HANDLER_NOT_FOUND = 1010;
    //error code for resource not found
    public static final int ERR_RESOURCE_NOT_FOUND = 1020;
    //error code for validation failed exceptions
    public static final int ERR_REQUEST_PARAMS_BODY_VALIDATION_FAILED = 1030;
    //error code for constraint check exceptions
    public static final int ERR_CONSTRAINT_CHECK_FAILED = 1040;
}
