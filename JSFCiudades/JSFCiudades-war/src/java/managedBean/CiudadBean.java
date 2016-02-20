/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.Ciudad;
import JSFCiudades.ejb.Evento;
import JSFCiudades.ejb.Usuario;
import JSFCiudades.entity.CiudadFacade;
import JSFCiudades.entity.EventoFacade;
import JSFCiudades.entity.UsuarioFacade;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

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
    
    
    

    protected String nombreCiudad;
    protected int idCiudad;
    protected String temperatura;
    protected String fecha;
    protected List<Evento> listaEventos;
    protected List<Ciudad> listaCiudades;
    protected List<Usuario> listaUsuarios;

    /**
     * Creates a new instance of CiudadBean
     */
    public CiudadBean() {
       
    }
    
    @PostConstruct
    public void init(){
        this. nombreCiudad="Sevilla, España";
       this.idCiudad = 155;
        //this.listaEventos = this.eventoFacade.findAll();
        //this.listaCiudad = this.ciudadFacade.findAll();
       this.listaUsuarios = this.usuarioFacade.findAll();
       
       
    }

    public String getNombreCiudad() {
        return nombreCiudad;
    }

    public void setNombreCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }


    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public List<Evento> getListaEventos() {
        //this.listaEventos = eventoFacade.findAll();
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public String getTemperatura() {
        return temperatura;
    }

    public void setTemperatura(String temperatura) {
        this.temperatura = temperatura;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
    

}
