/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.message;

/**
 * messages.
 * @author Norito AGETSUMA
 */
public class Messages {

    public static Message thanksForSignUp(String email) {
        Message m = new Message();
        m.setTitle("Confirmation");
        m.addDescription("Your account has been created. Thank you for your registration.");
        m.addDescription("Please SignIn " + email + " using right Sign In form.");
        return m;
    }
    
    public static Message signinSuccess() {
        Message m = new Message();
        m.setTitle("Sign In Success");
        m.addDescription("welcome shiro sample application.");
        return m;
    }
    
    public static Message signinFailed() {
        Message m = new Message();
        m.setTitle("Sign In Failed");
        m.addDescription("username or password is incorrect");
        return m;
    }

}
