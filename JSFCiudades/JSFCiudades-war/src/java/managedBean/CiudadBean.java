/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.Evento;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel06
 */
@ManagedBean
@RequestScoped
public class CiudadBean {

    private String nombreCiudad;
    private int idCiudad;
    private List<Evento> listaEventos;

    /**
     * Creates a new instance of CiudadBean
     */
    public CiudadBean() {
    }

    public String getCiudad() {
        return nombreCiudad;
    }

    public void setCiudad(String nombreCiudad) {
        this.nombreCiudad = nombreCiudad;
    }

    public int getIdCiudad() {
        return idCiudad;
    }

    public void setIdCiudad(int idCiudad) {
        this.idCiudad = idCiudad;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

}
