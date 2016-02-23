/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.ejb;

import JSFCiudades.entity.ComentarioEvento;
import JSFCiudades.entity.Usuario;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author inftel08
 */
@Stateless
public class ComentarioEventoFacade extends AbstractFacade<ComentarioEvento> {

    @PersistenceContext(unitName = "JSFCiudades-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ComentarioEventoFacade() {
        super(ComentarioEvento.class);
    }

    public int getNumComentariosEventoByUsuario(Usuario usuario) {

        Query q = em.createQuery("select ce from ComentarioEvento ce where ce.idUsuario.idUsuario=:idUsuario");
        q.setParameter("idUsuario", usuario.getIdUsuario());
        return q.getResultList().size();

    }
}
