/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

/**
 * UserAccount entity.
 * @author Norito AGETSUMA
 */
@Entity
@NamedQueries({
    @NamedQuery(name="UserAccount.referAll",query="SELECT u FROM UserAccount u"),
    @NamedQuery(name="UserAccount.findByEmail", query="SELECT u FROM UserAccount u WHERE u.email = :email")
})
public class UserAccount implements Serializable {
    
    public static String REFER_ALL = "UserAccount.referAll";
    public static String FIND_BY_EMAIL = "UserAccount.findByEmail";
    
    @Id @GeneratedValue
    private long id;   
    private String email;
    private String password;
    private String firstName;
    private String lastName;
    
    @Enumerated(EnumType.STRING)
    private Role userRole;
    
    public UserAccount() {
    }
    
    public UserAccount(String email, String password,
            String firstName, String lastName, Role userRole) {
        this.email = email;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
        this.userRole = userRole;
    }
    
    public long getId() {
        return id;
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

    public Role getUserRole() {
        return userRole;
    }

    public void setUserRole(Role userRole) {
        this.userRole = userRole;
    }    
}
