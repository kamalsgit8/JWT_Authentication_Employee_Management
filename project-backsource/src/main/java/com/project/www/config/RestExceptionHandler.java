package com.project.www.config;


import com.project.www.dtos.*;
import com.project.www.exception.*;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;


/*The @ControllerAdvice annotation indicates that this class provides centralized,
 *  exception handling for the controllers in the Spring MVC application.
 */

@ControllerAdvice
public class RestExceptionHandler {

	/*defines to handle exceptions for the class->AppException,
	 * Builder() in ErrorDto so, object gets builder().build(),
	 * return ->status,body ->errordto caught from AppException file.
	 */
	
    @ExceptionHandler(value = { AppException.class })
    @ResponseBody
    public ResponseEntity<ErrorDto> handleException(AppException ex) {
        return ResponseEntity
                .status(ex.getStatus())
                .body(ErrorDto.builder().message(ex.getMessage()).build());
    }
}