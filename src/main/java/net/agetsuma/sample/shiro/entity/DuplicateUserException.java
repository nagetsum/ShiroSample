package net.agetsuma.sample.shiro.entity;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 * Signup duplcate email .
 * @author Norito AGETSUMA
 */
public class DuplicateUserException extends RuntimeException {
    
    public DuplicateUserException(String message) {
        super(message);
    }
    
    public DuplicateUserException(String message, Throwable cause) {
        super(message, cause);
    }  
}
