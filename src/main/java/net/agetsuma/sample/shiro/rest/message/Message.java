/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.rest.message;

import java.util.LinkedList;
import java.util.List;

/**
 * Form error message.
 * @author Norito AGETSUMA
 */
public class Message {
    
    private String title;
    public List<String> descriptions = new LinkedList<>();
    
    public Message() {
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public List<String> getDescriptions() {
        return descriptions;
    }

    public void setDescriptions(List<String> descriptions) {
        this.descriptions = descriptions;
    }
    
    public void addDescription(String descriptions) {
        this.descriptions.add(descriptions);
    }
    
}
