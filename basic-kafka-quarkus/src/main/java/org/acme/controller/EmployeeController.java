package org.acme.controller;

import org.acme.event.producer.EmployeeProducer;
import org.acme.model.Employee;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/employee")
@Produces(MediaType.APPLICATION_JSON)
@Consumes(MediaType.APPLICATION_JSON)
public class EmployeeController {

    @Inject
    EmployeeProducer employeeProducer;

    @POST
    public Response createEmployee(@Valid Employee employee){
        employeeProducer.createEmployeeEmitter(employee);
        return Response.accepted().build();
    }
}
