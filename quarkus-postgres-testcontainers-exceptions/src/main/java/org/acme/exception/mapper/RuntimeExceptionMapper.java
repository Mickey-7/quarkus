package org.acme.exception.mapper;

import org.acme.error.Error;
import org.acme.error.ErrorCodes;
import org.acme.error.ErrorResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class RuntimeExceptionMapper implements ExceptionMapper<RuntimeException> {
    private Logger log = LoggerFactory.getLogger(RuntimeExceptionMapper.class);
    @Override
    public Response toResponse(RuntimeException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(
                new Error(
                        ErrorCodes.ERR_RUNTIME,
                        "internal server error",
                        "error occurred while processing your request. please try again !!"
                )
        );
        return Response.status(Response.Status.INTERNAL_SERVER_ERROR).entity(errorResponse).build();
    }

}
