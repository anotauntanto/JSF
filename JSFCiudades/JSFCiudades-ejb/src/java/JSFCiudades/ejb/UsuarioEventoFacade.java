/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.ejb;

import JSFCiudades.entity.Evento;
import JSFCiudades.entity.Usuario;
import JSFCiudades.entity.UsuarioEvento;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author inftel08
 */
@Stateless
public class UsuarioEventoFacade extends AbstractFacade<UsuarioEvento> {

    @PersistenceContext(unitName = "JSFCiudades-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioEventoFacade() {
        super(UsuarioEvento.class);
    }

    public int obtenerAsistentes(Evento evento) {
        Query q = em.createQuery("SELECT ue FROM UsuarioEvento ue WHERE ue.idEvento.idEvento = ?1").setParameter(1, evento.getIdEvento());

        //(int) q.getSingleResult();
        return q.getResultList().size();

    }

    public boolean exitsUsuarioEvento(Evento evento, int idUsuario) {

        Query q = em.createQuery("SELECT ue FROM UsuarioEvento ue WHERE ue.idEvento.idEvento = ?1 AND ue.idUsuario.idUsuario = ?2", UsuarioEvento.class);

        q.setParameter(1, evento.getIdEvento());
        q.setParameter(2, idUsuario);

        //(int) q.getSingleResult();
        if (q.getResultList().isEmpty()) {

            return false;
        }

        return true;
    }

    public void insertAsistir(Evento evento, int idUsuario) {

        UsuarioEvento ue = new UsuarioEvento();
        Evento e = new Evento();
        Usuario u = new Usuario();
        e.setIdEvento(evento.getIdEvento());
        ue.setIdEvento(e);
        u.setIdUsuario(idUsuario);
        ue.setIdUsuario(u);

        create(ue);

    }

}
