package com.project.www.service;

import java.nio.CharBuffer;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.project.www.dtos.CredentialsDto;
import com.project.www.dtos.EmployeeDto;
import com.project.www.dtos.SignUpDto;
import com.project.www.exception.AppException;
import com.project.www.mappers.EmployeeMapper;
import com.project.www.model.Employee;
import com.project.www.repo.EmployeeRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class EmpService {

    private final EmployeeRepository employeeRepository;

    private final PasswordEncoder passwordEncoder;

    private final EmployeeMapper employeeMapper;
    
/*checking login cred if its not there in the db->unknown employee,
 * if its is right credentials check for password .matches(),
 * if password matches return the login cred to toEmployeeDto() of Mapper class,
 * or throw -> wrong password.
 */
    
    public EmployeeDto login(CredentialsDto credentialsDto) {
        Employee emp = employeeRepository.findByLogin(credentialsDto.getLogin())
                .orElseThrow(() -> new AppException("Unknown employee", HttpStatus.NOT_FOUND));

        if (passwordEncoder.matches(CharBuffer.wrap(credentialsDto.getPassword()), emp.getPassword())) {
            return employeeMapper.toEmployeeDto(emp);
        }
        throw new AppException("Invalid password", HttpStatus.BAD_REQUEST);
    }

    /*checking the SignUpDto file which has the details of new=user,
     * if user already exists in the repo it will return login already there,
     *if its a new=user-> the details passed to sigupToemployee() of mapper class by emplyeeDto,
     *employeeDto represents the user object for sending back response to client ,
     *which have user details of specific context weather of new/old,
     *password will be get, after 1. saved to repo 2. saveduser returned to toEmployeeDto(),
     *of mapper class.
     */
    
    
    public EmployeeDto register(SignUpDto employeeDto) {
        Optional<Employee> optionalEmployee = employeeRepository.findByLogin(employeeDto.getLogin());

        if (optionalEmployee.isPresent()) {
            throw new AppException("Login already exists", HttpStatus.BAD_REQUEST);
        }

        Employee emp = employeeMapper.signUpToEmployee(employeeDto);
        emp.setPassword(passwordEncoder.encode(CharBuffer.wrap(employeeDto.getPassword())));

        Employee savedEmp = employeeRepository.save(emp);

        return employeeMapper.toEmployeeDto(savedEmp);
    }

    
    /*findbylogin used in above methods is not an inbuilt so,
     * it will take the login details as param and search for the specified,
     * not found returns->Unknown
     * found returns->mapper class.
     */
    public EmployeeDto findByLogin(String login) {
    	Employee emp = employeeRepository.findByLogin(login)
                .orElseThrow(() -> new AppException("Unknown user", HttpStatus.NOT_FOUND));
        return employeeMapper.toEmployeeDto(emp);
    }

}
