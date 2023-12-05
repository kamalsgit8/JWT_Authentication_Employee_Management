package com.project.www.mappers;

import com.project.www.dtos.EmployeeDto;
import com.project.www.dtos.SignUpDto;
import com.project.www.model.Employee;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2023-12-04T15:08:12+0530",
    comments = "version: 1.5.3.Final, compiler: Eclipse JDT (IDE) 3.35.0.v20230814-2020, environment: Java 17.0.8.1 (Eclipse Adoptium)"
)
@Component
public class EmployeeMapperImpl implements EmployeeMapper {

    @Override
    public EmployeeDto toEmployeeDto(Employee employee) {
        if ( employee == null ) {
            return null;
        }

        EmployeeDto.EmployeeDtoBuilder employeeDto = EmployeeDto.builder();

        employeeDto.email( employee.getEmail() );
        employeeDto.firstName( employee.getFirstName() );
        employeeDto.id( employee.getId() );
        employeeDto.lastName( employee.getLastName() );
        employeeDto.login( employee.getLogin() );

        return employeeDto.build();
    }

    @Override
    public Employee signUpToEmployee(SignUpDto signUpDto) {
        if ( signUpDto == null ) {
            return null;
        }

        Employee.EmployeeBuilder employee = Employee.builder();

        employee.email( signUpDto.getEmail() );
        employee.firstName( signUpDto.getFirstName() );
        employee.lastName( signUpDto.getLastName() );
        employee.login( signUpDto.getLogin() );

        return employee.build();
    }
}
