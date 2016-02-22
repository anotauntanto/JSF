/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.entity.Ciudad;
import JSFCiudades.entity.Evento;
import JSFCiudades.entity.Usuario;
import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.ejb.UsuarioFacade;
import JSFCiudades.entity.Pregunta;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.imageio.ImageIO;
import javax.imageio.stream.ImageOutputStream;
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
    private UsuarioFacade usuarioFacade;
    @EJB
    private EventoFacade eventoFacade;
    @EJB
    private CiudadFacade ciudadFacade;

    protected Ciudad ciudad;
    protected float temperatura;
    protected String fecha;

    protected Pregunta pregunta;

    protected Evento evento;
    protected StreamedContent imagen;


    /**
     * Creates a new instance of CiudadBean
     */
    public CiudadBean() {

    }

    @PostConstruct
    public void init() {
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

    public String doMostrarComentarios(Pregunta pregunta) {
        this.pregunta = pregunta;
        return "ListadoPreguntasCiudad";
    }

    public String doMostrarEventos(Evento evento) {
        this.evento = evento;
        return "ListadoEventoCiudad";
    }

    
}
