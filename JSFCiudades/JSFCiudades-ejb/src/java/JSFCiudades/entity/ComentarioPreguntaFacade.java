/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.entity;

import JSFCiudades.ejb.ComentarioPregunta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author inftel08
 */
@Stateless
public class ComentarioPreguntaFacade extends AbstractFacade<ComentarioPregunta> {
    @PersistenceContext(unitName = "JSFCiudades-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioPreguntaFacade() {
        super(ComentarioPregunta.class);
    }
    
}
