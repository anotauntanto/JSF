/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.ComentarioEventoFacade;
import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.ejb.UsuarioEventoFacade;
import JSFCiudades.entity.Evento;
import java.awt.geom.Point2D;
import java.io.UnsupportedEncodingException;
import java.net.MalformedURLException;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.management.Query;
import maps.java.Geocoding;

/**
 *
 * @author inftel06
 */
@ManagedBean
@RequestScoped
public class ComentariosEventoBean {

    @EJB
    private UsuarioEventoFacade usuarioEventoFacade;
    @EJB
    private ComentarioEventoFacade comentarioEventoFacade;
    @EJB
    private EventoFacade eventoFacade;

    protected Evento evento;

    @ManagedProperty(value = "#{ciudadBean}")
    protected CiudadBean ciudadBean;
    @ManagedProperty(value = "#{loginRegistroBean}")
    protected LoginRegistroBean loginRegistroBean;


    protected boolean verMapa;

    protected Point2D.Double resultadoCD;

    /**
     * Creates a new instance of ComentariosEventoBean
     */
    public ComentariosEventoBean() {
    }

    @PostConstruct
    public void init() {
        //pregunta = preguntaFacade.find(ciudadBean.idPregunta);  
        evento = ciudadBean.evento;
        verMapa = false;
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

    public Point2D.Double getResultadoCD() {
        return resultadoCD;
    }

    public void setResultadoCD(Point2D.Double resultadoCD) {
        this.resultadoCD = resultadoCD;
    }

    public LoginRegistroBean getLoginRegistroBean() {
        return loginRegistroBean;
    }

    public void setLoginRegistroBean(LoginRegistroBean loginRegistroBean) {
        this.loginRegistroBean = loginRegistroBean;
    }

    public String doAsistir() {
        if (!usuarioEventoFacade.exitsUsuarioEvento(evento, loginRegistroBean.getIdUsuario())) {
            usuarioEventoFacade.insertAsistir(evento, loginRegistroBean.getIdUsuario());
        }
        return "ListadoEventoCiudad";
    }

    public int obtenerAsistentes(Evento evento) {

        return usuarioEventoFacade.obtenerAsistentes(evento);

    }

    public String doVerMapa(Evento evento) throws UnsupportedEncodingException, MalformedURLException {
        Geocoding ObjGeocod = new Geocoding();
        //resultadoCD = ObjGeocod.getCoordinates(evento.getIdCiudad().getNombreCiudad()+ ", " + evento.getDireccion());
        resultadoCD = ObjGeocod.getCoordinates("Sevilla, España, C/ Virgen de Lujan, 8");
        System.out.println("Coordenadas: " + resultadoCD.x + " " + resultadoCD.y);

        return "VerMapa";

    }

    public String doVolver(Evento evento) {
        this.evento = evento;
        return "ListadoEventoCiudad";
    }
}
