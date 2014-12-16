/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.contoller;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.json.Json;
import javax.json.JsonObject;
import javax.validation.Valid;
import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import static javax.ws.rs.core.Response.Status.UNAUTHORIZED;
import javax.ws.rs.core.UriInfo;
import net.agetsuma.sample.shiro.entity.Role;
import net.agetsuma.sample.shiro.entity.UserAccount;
import net.agetsuma.sample.shiro.entity.UserAccountRepository;
import net.agetsuma.sample.shiro.rest.message.Message;
import net.agetsuma.sample.shiro.rest.message.Messages;
import net.agetsuma.sample.shiro.service.auth.SigninService;
import org.apache.shiro.authc.credential.DefaultPasswordService;
import org.apache.shiro.authc.credential.PasswordService;

/**
 * JAX-RS Resource of UserAccount.
 * @author Norito AGETSUMA
 */
@RequestScoped
@Path("/users")
@Produces(MediaType.APPLICATION_JSON)
public class UserAccountResource {

    @Context
    UriInfo uriInfo;
    
    @Inject
    UserAccountRepository userAccountRepository;

    @Inject
    SigninService signinService;

    @Path("sign_up")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Message signUp(@Valid @BeanParam SignUpForm form) {
        PasswordService ps = new DefaultPasswordService();
        String encryptedPassword = ps.encryptPassword(form.getPassword());

        UserAccount newAccount = new UserAccount(form.getEmail(), encryptedPassword,
                form.getFirstName(), form.getLastName(), Role.valueOf(form.getRole()));

        userAccountRepository.create(newAccount);
        return Messages.thanksForSignUp(newAccount.getEmail());
    }

    @Path("sign_in")
    @POST
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    public Response signin(@Valid @BeanParam SignInForm form) {

        if (signinService.isAuthenticated() ||
                signinService.signin(form.getEmail(), form.getPassword(), form.isKeepSigned())) {
            // alrealdy sign in or success
            return Response.ok().entity(Messages.signinSuccess()).build();
        }

        // authentication failed, return 401
        return Response.status(UNAUTHORIZED)
                .entity(Messages.signinFailed()).build();
    }
    
    @Path("logout")
    @POST
    public Response signout() {
        signinService.signout();
        return Response.seeOther(uriInfo.getBaseUri()).build();
    }

    @Path("valid")
    @GET
    public JsonObject validEmail(@QueryParam("email") String email) {
        if (userAccountRepository.isAlreadyRegistered(email)) {
            return Json.createObjectBuilder().add("valid", false).build();
        }
        return Json.createObjectBuilder().add("valid", true).build();
    }

}
