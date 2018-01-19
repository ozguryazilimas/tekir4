/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ozguryazilim.tekir;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.PersistenceContextType;

/**
 * Entity Manager üreticisi
 *
 * @author Hakan Uygun
 */
@ApplicationScoped
public class EntityManagerProducer {

    @PersistenceContext(type = PersistenceContextType.EXTENDED, unitName = "tekir")
    private EntityManager em;

    
    @Produces
    public EntityManager getEntityManager() {
        return em;
    }

    /*
    public void close(@Disposes final EntityManager entityManager) {
        if( entityManager.isOpen() ){
            entityManager.close();
        }
    }*/
}
