package com.project.www.controller;

import java.net.URI;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.www.config.EmployeeAuthenticationProvider;
import com.project.www.dtos.CredentialsDto;
import com.project.www.dtos.EmployeeDto;
import com.project.www.dtos.SignUpDto;
import com.project.www.service.EmpService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
public class AuthController {

    private final EmpService empService;
    private final EmployeeAuthenticationProvider userAuthenticationProvider;
  

    @PostMapping("/login")
    public ResponseEntity<EmployeeDto> login(@RequestBody @Valid CredentialsDto credentialsDto) {
        EmployeeDto employeeDto = empService.login(credentialsDto);
        employeeDto.setToken(userAuthenticationProvider.createToken(employeeDto.getLogin()));
        return ResponseEntity.ok(employeeDto);
    }

    @PostMapping("/register")
    public ResponseEntity<EmployeeDto> register(@RequestBody @Valid SignUpDto emp) {
        EmployeeDto newEmp = empService.register(emp);
        newEmp.setToken(userAuthenticationProvider.createToken(emp.getLogin()));
        return ResponseEntity.created(URI.create("/newemp/" + newEmp.getId())).body(newEmp);
    }

}
