/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.contoller;

import java.util.Collections;
import java.util.List;
import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import net.agetsuma.sample.shiro.entity.UserAccount;
import net.agetsuma.sample.shiro.entity.UserAccountRepository;
import net.agetsuma.sample.shiro.service.auth.SigninService;
import org.glassfish.jersey.server.mvc.Template;

/**
 * Show top page.
 * @author Norito AGETSUMA
 */
@RequestScoped
@Path("/")
public class RootController {
    
    @Inject
    UserAccountRepository userAccountRepository;
    
    @Inject
    SigninService signinService;
    
    @GET
    @Template(name = "/index")
    public List<UserAccount> getUsers() {
        if(!signinService.hasManagerRole()) {
            return Collections.emptyList();
        }
        return userAccountRepository.referAll();
    }
    
}
