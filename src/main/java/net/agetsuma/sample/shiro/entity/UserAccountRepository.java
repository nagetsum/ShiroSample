/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package net.agetsuma.sample.shiro.entity;

import java.util.List;
import javax.enterprise.context.Dependent;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;
import javax.transaction.Transactional;

/**
 * Repository for UserAcount entity.
 * @author Norito AGETSUMA
 */
@Dependent
public class UserAccountRepository {  
    
    @PersistenceContext
    private EntityManager em;
    
    @Transactional
    public void create(UserAccount newAccount) {
        if (isAlreadyRegistered(newAccount.getEmail())) {
            throw new DuplicateUserException(newAccount.getEmail() + " is already registered.");
        }
        
        em.persist(newAccount);
    }
    
    public List<UserAccount> referAll() {
        return em.createNamedQuery(UserAccount.REFER_ALL, UserAccount.class).getResultList();
    }
    
    public boolean isAlreadyRegistered(String email) {
        TypedQuery<UserAccount> findByEmail = em.createNamedQuery(UserAccount.FIND_BY_EMAIL, UserAccount.class);
        findByEmail.setParameter("email", email);
        return !findByEmail.getResultList().isEmpty();
    }
    
}
