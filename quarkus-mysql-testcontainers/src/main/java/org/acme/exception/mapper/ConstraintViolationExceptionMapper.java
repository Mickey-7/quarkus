package org.acme.exception.mapper;

import org.acme.error.Error;
import org.acme.error.ErrorCodes;
import org.acme.error.ErrorResponse;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ConstraintViolationExceptionMapper implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException e) {
        ErrorResponse errorResponse = new ErrorResponse();
        for (ConstraintViolation violation : e.getConstraintViolations()){
            errorResponse.getErrors().add(new Error(
                    ErrorCodes.ERR_CONSTRAINT_CHECK_FAILED,
                    violation.getPropertyPath().toString(),
                    violation.getMessage()
            ));
        }
        System.out.println("--> ConstraintViolation");
        return Response.status(Response.Status.BAD_REQUEST).entity(errorResponse).build();
    }
}
