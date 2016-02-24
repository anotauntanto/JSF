/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.ejb;

import JSFCiudades.entity.Ciudad;
import java.util.List;
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

    public List<Ciudad> getListaCiudadesSearch(String cadena) {

        String descripcion = "%" + cadena + "%";

        Query q = em.createQuery("SELECT c FROM Ciudad c WHERE c.descripcion LIKE :descripcion");
        q.setParameter("descripcion", descripcion);

        List<Ciudad> listaCiudadSearch = q.getResultList();

        return listaCiudadSearch;
    }

    
    public List<Ciudad> getCiudad(String nombreCiudad) {

        nombreCiudad = "%" + nombreCiudad + "%";
        Query q = em.createQuery("select c from Ciudad c WHERE c.nombreCiudad LIKE :nombre");
        q.setParameter("nombre", nombreCiudad);

        List<Ciudad> listaCiudad = q.getResultList();

        return listaCiudad;
    }

    /*public List<Ciudad> getListaCiudadesNameSearch(String cadena) {

        String name = "%" + cadena + "%";

        Query q = em.createQuery("SELECT c FROM Ciudad c WHERE c.nombreCiudad LIKE :name");
        q.setParameter("name", name);

        List<Ciudad> listaCiudadSearch = q.getResultList();
        return listaCiudadSearch;
    }*/

    public List<String> getListaCiudades() {

        Query q = em.createQuery("SELECT distinct c.nombreCiudad FROM Ciudad c");

        List<String> listaCiudadSearch = q.getResultList();

        return listaCiudadSearch;
    }

    @Override
    public List<Ciudad> findRange(int[] range) {

        Query q = em.createQuery("SELECT c FROM Ciudad c ORDER BY c.idCiudad DESC");
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
}
