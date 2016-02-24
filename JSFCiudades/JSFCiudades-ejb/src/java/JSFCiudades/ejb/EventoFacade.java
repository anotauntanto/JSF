/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.ejb;

import JSFCiudades.entity.Ciudad;
import JSFCiudades.entity.Evento;
import JSFCiudades.entity.Usuario;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
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
public class EventoFacade extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "JSFCiudades-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }

    public List<Evento> getListaProximosEventosByCity(Ciudad ciudad, int numeroEventos) {
        Query q = em.createQuery("SELECT e FROM Evento e WHERE e.idCiudad.idCiudad=:idCiudad ORDER BY e.fecha");
        q.setParameter("idCiudad", ciudad.getIdCiudad());
        List<Evento> listaEvento = q.getResultList();
        Date midate = new Date();
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(midate);
        calendar.add(Calendar.DAY_OF_YEAR, -1); //REsto un dia para ver los eventos de hoy
        midate = calendar.getTime();
        if (numeroEventos != 0) {
            int i = 0;
            List<Evento> tempEvento = new ArrayList<Evento>();

            for (Evento e : listaEvento) {
                if (e.getFecha().after(midate) && i < numeroEventos) {
                    tempEvento.add(e);

                    i++;
                }
            }
            return tempEvento;
        } else {
            return listaEvento;
        }

    }

    public int getNumEventosCreadosByUsuario(Usuario usuario) {

        Query q = em.createQuery("select e from Evento e where e.idUsuario.idUsuario=:idUsuario");
        q.setParameter("idUsuario", usuario.getIdUsuario());
        return q.getResultList().size();

    }

    
    @Override
   public List<Evento> findRange(int[] range) {

       Query q = em.createQuery("SELECT e FROM Evento e ORDER BY e.fecha DESC");
        q.setMaxResults(range[1] - range[0] + 1);
        q.setFirstResult(range[0]);
        return q.getResultList();
    }
}
