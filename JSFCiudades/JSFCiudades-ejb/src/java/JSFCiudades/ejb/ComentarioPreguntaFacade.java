/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.ejb;

import JSFCiudades.entity.ComentarioPregunta;
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

    public List<ComentarioPregunta> getComentariosByQuestion(Integer idQuestion) {

        Query q = em.createQuery("select p from ComentarioPregunta p where p.idPregunta.idPregunta=:idPregunta");
        q.setParameter("idPregunta", idQuestion);
        return q.getResultList();

    }
}
