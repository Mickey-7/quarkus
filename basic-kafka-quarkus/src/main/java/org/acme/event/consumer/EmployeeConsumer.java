package org.acme.event.consumer;

import io.smallrye.reactive.messaging.kafka.Record;
import org.acme.model.Employee;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.enterprise.context.ApplicationScoped;

@ApplicationScoped
public class EmployeeConsumer {
    private final Logger logger = LoggerFactory.getLogger(EmployeeConsumer.class);

    @Incoming("employee-in")
    public void addEmployee(Record<String, Employee> employeeRecord){
        logger.info("New employee {} {} joined with employee code {}",
                employeeRecord.value().getFirstName(),
                employeeRecord.value().getLastName(),
                employeeRecord.value().getEmpCode()
        );
    }
}
