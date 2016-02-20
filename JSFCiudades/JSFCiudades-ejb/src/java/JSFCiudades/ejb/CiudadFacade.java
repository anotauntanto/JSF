/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.ejb;

import JSFCiudades.entity.Ciudad;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

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

    public Ciudad getCiudad(int idCiudad) {
        
        if (idCiudad == 0) {
            int newIdCiudad = 0;

            Query q = em.createQuery("select max(c.idCiudad) from Ciudad c", Ciudad.class);

            newIdCiudad = (int) q.getSingleResult();
            //System.out.println(newIdCiudad);

            return find(newIdCiudad);

        } else {
            return find(idCiudad);
        }
    }

}
