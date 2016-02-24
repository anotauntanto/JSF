/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.ejb.PreguntaFacade;
import JSFCiudades.entity.Evento;
import JSFCiudades.entity.Pregunta;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel07
 */
@ManagedBean
@RequestScoped

public class InsertarHilosBean {

    @EJB
    private PreguntaFacade preguntaFacade;
    @EJB
    private EventoFacade eventoFacade;

    protected Pregunta pregunta;
    protected Evento evento;
    
    protected String error = "Debe escribir algo";

    @ManagedProperty(value = "#{ciudadBean}")
    protected CiudadBean ciudadBean;
    @ManagedProperty(value = "#{loginRegistroBean}")
    protected LoginRegistroBean loginRegistroBean;

    /**
     * Creates a new instance of InsertarHilosBean
     */
    public InsertarHilosBean() {

    }

    @PostConstruct
    public void init() {
        
        pregunta = new Pregunta();
        evento = new Evento();
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
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

    public LoginRegistroBean getLoginRegistroBean() {
        return loginRegistroBean;
    }

    public void setLoginRegistroBean(LoginRegistroBean loginRegistroBean) {
        this.loginRegistroBean = loginRegistroBean;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
    
    

    public String doGuardarPregunta() {

        pregunta.setIdCiudad(ciudadBean.ciudad);
        pregunta.setIdUsuario(loginRegistroBean.usuario);
        preguntaFacade.create(pregunta);
        pregunta = new Pregunta();
        return null;
    }

    public String doGuardarEvento() {

        evento.setIdCiudad(ciudadBean.ciudad);
        evento.setIdUsuario(loginRegistroBean.usuario);
        eventoFacade.create(evento);
        evento = new Evento();
        return null;
    }
}
