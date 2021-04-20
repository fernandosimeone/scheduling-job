package jobs.schedulingJob.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;

import jobs.schedulingJob.dto.ErrorResponseDto;
import jobs.schedulingJob.model.SchedulingException;

@ControllerAdvice  
@RestController
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)  
    public ResponseEntity<ErrorResponseDto> handleException(Exception ex) {
    	
    	HttpStatus status = null;
    	String message = null;
    	
    	if (ex instanceof SchedulingException) {
    		
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    		message = ex.getMessage();
    		
    	} else if (ex instanceof IllegalArgumentException) {
    		
    		status = HttpStatus.BAD_REQUEST;
    		message = ex.getMessage();
    		
    	} else {
    		
    		status = HttpStatus.INTERNAL_SERVER_ERROR;
    		message = "Erro ao executar operação.";
    	}
    	
        return new ResponseEntity<ErrorResponseDto>(new ErrorResponseDto(message), status);
    }

}
