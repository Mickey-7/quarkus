package org.acme.exception.mapper;

import org.acme.error.Error;
import org.acme.error.ErrorCodes;
import org.acme.error.ErrorResponse;
import org.acme.exception.ResourceNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceNotFoundExceptionMapper  implements ExceptionMapper<ResourceNotFoundException> {
    private Logger logger = LoggerFactory.getLogger(ResourceNotFoundExceptionMapper.class);

    @Override
    public Response toResponse(ResourceNotFoundException e) {
        logger.error(String.format("no resource found exception occurred : %s",e.getMessage()));

        ErrorResponse errorResponse = new ErrorResponse();
        errorResponse.getErrors().add(
                new Error(
                        ErrorCodes.ERR_RESOURCE_NOT_FOUND,
                        "Resource not found",
                        e.getMessage()
                )
        );
        return Response.status(Response.Status.NOT_FOUND).entity(errorResponse).build();
    }
}
