package org.acme.model;

import javax.validation.constraints.NotBlank;

public class Employee {

    @NotBlank(message = "First name cannot be null")
    private String firstName;
    private String lastName;
    @NotBlank(message = "Employee code cannot be null")
    private String empCode;

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmpCode() {
        return empCode;
    }

    public void setEmpCode(String empCode) {
        this.empCode = empCode;
    }
}
