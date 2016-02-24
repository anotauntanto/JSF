/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.entity.Ciudad;
import JSFCiudades.entity.Evento;
import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.ejb.ComentarioPreguntaFacade;
import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.ejb.PreguntaFacade;
import JSFCiudades.ejb.UsuarioFacade;
import JSFCiudades.entity.ComentarioPregunta;
import JSFCiudades.entity.Pregunta;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import net.aksingh.owmjapis.CurrentWeather;
import net.aksingh.owmjapis.OpenWeatherMap;
import org.json.JSONException;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author inftel06
 */
@ManagedBean
@SessionScoped
public class CiudadBean {
    @EJB
    private ComentarioPreguntaFacade comentarioPreguntaFacade;
    @EJB
    private PreguntaFacade preguntaFacade;
    @EJB
    private UsuarioFacade usuarioFacade;
    @EJB
    private EventoFacade eventoFacade;
    @EJB
    private CiudadFacade ciudadFacade;
    

    protected Ciudad ciudad;
    protected float temperatura;
    protected String fecha;
    protected StreamedContent imagen;
 
    protected Pregunta pregunta;
    protected Evento evento;
      
    protected List<Pregunta> listaPreguntas;
    protected List<Evento> listaEventos;
    protected List<ComentarioPregunta> listaComentarioPreguntas;
    protected List<Evento> listaProximosEventos;


    /**
     * Creates a new instance of CiudadBean
     */
    public CiudadBean() {

    }

    @PostConstruct
    public void init() {
        listaPreguntas = new ArrayList();
        listaEventos = new ArrayList();
        listaProximosEventos = new ArrayList();
        listaComentarioPreguntas = new ArrayList();
        this.ciudad = ciudadFacade.getCiudad(0);
  
    }

    public float getTemperatura() {

        OpenWeatherMap owm = new OpenWeatherMap("");
        owm.setUnits(OpenWeatherMap.Units.METRIC);
        owm.setApiKey("d05638724c60088ab81382441f4e8586");
        owm.setLang(OpenWeatherMap.Language.SPANISH);
        CurrentWeather cwd = null;
        try {
            cwd = owm.currentWeatherByCityName(ciudad.getNombreCiudad());
        } catch (IOException | JSONException ex) {
            System.out.println("Error weather");
        }
        
        this.temperatura = cwd.getMainInstance().getTemperature();
        
        return temperatura;
    }

    public void setTemperatura(float temperatura) {
        
        this.temperatura = temperatura;
    }

    public String getFecha() {

        //Recuperar la fecha actual
        Date midate = new Date();
        String[] verfecha = midate.toString().split(" ");
        this.fecha = verfecha[2] + "/" + verfecha[1] + "/" + verfecha[5];
        
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
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

    public StreamedContent getImagen() {
        return new DefaultStreamedContent(new ByteArrayInputStream(this.ciudad.getFoto()));
    }

    public void setImagen(StreamedContent imagen) {
        this.imagen = imagen;
    }

    public List<Pregunta> getListaPreguntas() {
     
        listaPreguntas = preguntaFacade.getPreguntasByCity(this.ciudad.getIdCiudad());
        return listaPreguntas;
    }

    public void setListaPreguntas(List<Pregunta> listaPreguntas) {
        this.listaPreguntas = listaPreguntas;
    }

    public List<Evento> getListaEventos() {
        //listaEventos = eventoFacade.
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<ComentarioPregunta> getListaComentarioPreguntas() {
        listaComentarioPreguntas=comentarioPreguntaFacade.getComentariosByQuestion(this.pregunta.getIdPregunta());
        return listaComentarioPreguntas;
    }

    public void setListaComentarioPreguntas(List<ComentarioPregunta> listaComentarioPreguntas) {
        this.listaComentarioPreguntas = listaComentarioPreguntas;
    }

    public List<Evento> getListaProximosEventos() {
        listaProximosEventos = eventoFacade.getListaProximosEventosByCity(ciudad, 4);
        return listaProximosEventos;
    }

    public void setListaProximosEventos(List<Evento> listaProximosEventos) {
        this.listaProximosEventos = listaProximosEventos;
    }
      

    public String doMostrarComentarios(Pregunta pregunta) {
        this.pregunta = pregunta;
        return "ListadoPreguntasCiudad";
    }

    public String doMostrarEventos(Evento evento) {
        this.evento = evento;
        return "ListadoEventoCiudad";
    }

    
}
