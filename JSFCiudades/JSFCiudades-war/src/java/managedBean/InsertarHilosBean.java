/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.ejb.PreguntaFacade;
import java.util.Date;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
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
    
    private String textoPregunta;
    private String nombreEvento;
    private Date fechaEvento;
    private String direccion;
    private String descripcion;
    /**
     * Creates a new instance of InsertarHilosBean
     */
    public InsertarHilosBean() {
        
    }

    public String getTextoPregunta() {
        return textoPregunta;
    }

    public void setTextoPregunta(String textoPregunta) {
        this.textoPregunta = textoPregunta;
    }

    public String getNombreEvento() {
        return nombreEvento;
    }

    public void setNombreEvento(String nombreEvento) {
        this.nombreEvento = nombreEvento;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    
    public String doGuardarPregunta(){
    
        return "";
    }
    
    public String doGuardarEvento(){
        return "";
    }
}
