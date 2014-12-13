/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.application.config;

import net.agetsuma.sample.shiro.rest.contoller.RootController;
import org.glassfish.jersey.server.ResourceConfig;
import org.glassfish.jersey.server.ServerProperties;
import org.glassfish.jersey.server.mvc.MvcFeature;
import org.glassfish.jersey.server.mvc.beanvalidation.MvcBeanValidationFeature;
import org.glassfish.jersey.server.mvc.jsp.JspMvcFeature;

/**
 * JAX-RS Configration.
 * @author Norito AGETSUMA
 */
public class ApplicationConfig extends ResourceConfig {

    public ApplicationConfig() {
        packages(RootController.class.getPackage().getName());
        property(ServerProperties.BV_SEND_ERROR_IN_RESPONSE, true);
        register(JspMvcFeature.class);
        property(MvcFeature.TEMPLATE_BASE_PATH, "/templates/");
        register(MvcBeanValidationFeature.class);   
    }
    
}
