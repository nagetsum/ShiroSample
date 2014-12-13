/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.service.auth;

import javax.enterprise.context.Dependent;
import net.agetsuma.sample.shiro.entity.Role;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;

/**
 * Authenticaton on Apache Shiro.
 * @author Norito AGETSUMA
 */
@Dependent
public class SigninService {
    
    public boolean isAuthenticated() {
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.isAuthenticated();
    }
    
    public boolean signin(String email, String password, boolean keepsignin) {
        if (isAuthenticated()) {
            return true;
        }
        
        Subject currentUser = SecurityUtils.getSubject();
        UsernamePasswordToken token = new UsernamePasswordToken(email, password, keepsignin);
        try {
            currentUser.login(token);
            return true;
        } catch (AuthenticationException ae) {
            return false;
        }
    }
    
    public void signout() {
        Subject currentUser = SecurityUtils.getSubject();
        currentUser.logout();
    }
    
    public boolean hasManagerRole() {
        if (!isAuthenticated()) {
            return false;
        }
        Subject currentUser = SecurityUtils.getSubject();
        return currentUser.hasRole(Role.MANAGER.toString());
    }
    
}
