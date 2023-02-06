package org.acme.exception.mapper;

import com.fasterxml.jackson.databind.exc.InvalidFormatException;
import org.acme.error.Error;
import org.acme.error.ErrorCodes;
import org.acme.error.ErrorResponse;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class InvalidFormatExceptionMapper implements ExceptionMapper<InvalidFormatException> {
    @Override
    public Response toResponse(InvalidFormatException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(
                new Error(
                        ErrorCodes.ERR_REQUEST_PARAMS_BODY_VALIDATION_FAILED,
                        "invalid request format. please verify your request body and try again!!",
                        e.getMessage()
                )
        );
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
