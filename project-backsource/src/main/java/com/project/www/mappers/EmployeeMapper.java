package com.project.www.mappers;

import com.project.www.dtos.*;
import com.project.www.model.*;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface EmployeeMapper {

    EmployeeDto toEmployeeDto(Employee employee);

    @Mapping(target = "password", ignore = true)
   Employee signUpToEmployee(SignUpDto signUpDto);

}
