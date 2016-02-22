/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.ejb.PreguntaFacade;
import JSFCiudades.entity.Ciudad;
import JSFCiudades.entity.Evento;
import JSFCiudades.entity.Pregunta;
import JSFCiudades.entity.Usuario;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;
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
    protected String fecha;

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
        //pregunta = preguntaFacade.find(ciudadBean.idPregunta);  
        pregunta = new Pregunta();
        evento = new Evento();
        System.out.println("yujuuuu");
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

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
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


        String[] trozosFecha = this.fecha.split("/");
        String miFecha = trozosFecha[1] + "/" + trozosFecha[0] + "/" + trozosFecha[2];

        SimpleDateFormat formato = new SimpleDateFormat("dd/MM/yyyy");
        try {
            evento.setFecha(formato.parse(miFecha));
        } catch (ParseException ex) {
            Logger.getLogger(InsertarHilosBean.class.getName()).log(Level.SEVERE, null, ex);
        }

        eventoFacade.create(evento);
        fecha="";
        evento = new Evento();
        return null;
    }
}
