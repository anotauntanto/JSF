/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.ComentarioEventoFacade;
import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.entity.Evento;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel06
 */
@ManagedBean
@RequestScoped
public class ComentariosEventoBean {
    @EJB
    private ComentarioEventoFacade comentarioEventoFacade;
    @EJB
    private EventoFacade eventoFacade;
    
    
    protected Evento evento;
    
    @ManagedProperty(value="#{ciudadBean}")
    protected CiudadBean ciudadBean;
    
    /**
     * Creates a new instance of ComentariosEventoBean
     */
    public ComentariosEventoBean() {
    }
    
        @PostConstruct
    public void init () {
        //pregunta = preguntaFacade.find(ciudadBean.idPregunta);  
        evento = ciudadBean.evento;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }
    
    public String doAsistir(){
       
        return null;
    }
    
    public int obtenerAsistentes(){
        return 0;
    }
    
}
