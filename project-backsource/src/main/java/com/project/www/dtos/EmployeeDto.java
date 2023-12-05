package com.project.www.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EmployeeDto {

    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String login;
    private String token;

}
