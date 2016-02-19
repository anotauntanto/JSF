/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.entity;

import JSFCiudades.ejb.Ciudad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author inftel08
 */
@Stateless
public class CiudadFacade extends AbstractFacade<Ciudad> {
    @PersistenceContext(unitName = "JSFCiudades-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CiudadFacade() {
        super(Ciudad.class);
    }
    
}
