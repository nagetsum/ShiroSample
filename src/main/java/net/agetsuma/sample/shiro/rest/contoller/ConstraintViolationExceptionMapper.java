/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.contoller;

import javax.validation.ConstraintViolationException;
import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;
import net.agetsuma.sample.shiro.rest.message.Message;

/**
 * Custom Exception mapper for BeanValidation error.
 * @author Norito AGETSUMA
 */
@Provider
public class ConstraintViolationExceptionMapper 
    implements ExceptionMapper<ConstraintViolationException> {

    @Override
    public Response toResponse(ConstraintViolationException exception) {
        Message validationError = new Message();
        validationError.setTitle("Form input is invalid");
        exception.getConstraintViolations().stream().forEach(v -> {
            validationError.addDescription(v.getMessage());
        });
        
        return Response
                .status(Response.Status.BAD_REQUEST)
                .entity(validationError)
                .build();            
    }
}
