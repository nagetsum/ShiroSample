/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.contoller;

import javax.ws.rs.FormParam;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Sign In form parameters.
 * @author Norito AGETSUMA
 */
public class SignInForm {
    
    @FormParam("email")
    @NotEmpty(message = "Email may not be empty")
    @Email
    private String email;
    
    @FormParam("password")
    @NotEmpty(message = "password may not be empty")
    private String password;
    
    @FormParam("keepsigned")
    private boolean keepSigned;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isKeepSigned() {
        return keepSigned;
    }

    public void setKeepSigned(boolean keepSigned) {
        this.keepSigned = keepSigned;
    }
    
}
