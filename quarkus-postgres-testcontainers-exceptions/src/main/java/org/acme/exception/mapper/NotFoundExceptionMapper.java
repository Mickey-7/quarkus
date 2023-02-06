package org.acme.exception.mapper;

import org.acme.error.Error;
import org.acme.error.ErrorCodes;
import org.acme.error.ErrorResponse;

import javax.ws.rs.NotFoundException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class NotFoundExceptionMapper implements ExceptionMapper<NotFoundException> {
    @Override
    public Response toResponse(NotFoundException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(
                new Error(
                        ErrorCodes.ERR_HANDLER_NOT_FOUND,
                        "invalid path",
                        e.getMessage()
                )
        );
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }

}
