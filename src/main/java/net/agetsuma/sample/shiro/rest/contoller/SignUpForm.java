/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.contoller;

import javax.validation.constraints.AssertTrue;
import javax.ws.rs.FormParam;
import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

/**
 * Sign Up form parameters.
 * @author Norito AGETSUMA
 */

public class SignUpForm {
    
    @FormParam("email")
    @NotEmpty(message = "Email may not be empty")
    @Email
    private String email;
    
    @FormParam("password")
    @NotEmpty(message = "password may not be empty")
    private String password;
    
    @FormParam("confirmPassword")
    @NotEmpty(message = "ConfirmPassword may not be empty")
    private String confirmPassword;
    
    @FormParam("firstName")
    @NotEmpty(message = "First Name may not be empty")
    private String firstName;
    
    @FormParam("lastName")
    @NotEmpty(message = "Last Name may not be empty")
    private String lastName;
    
    @FormParam("role")
    @NotEmpty(message = "Role may not be empty")
    private String role;
    
    @AssertTrue(message="Password should be equal ConfirmPassword")
    private boolean isPasswordEquals() {
        return this.password.equals(confirmPassword);
    }

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

    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
