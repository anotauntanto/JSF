/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel05
 */
@ManagedBean
@RequestScoped
public class BuscarCiudadBean {

    protected String busqueda;
    
    /**
     * Creates a new instance of BuscarCiudadBean
     */
    public BuscarCiudadBean() {
    }

    public String getBusqueda() {
        return busqueda;
    }

    public void setBusqueda(String busqueda) {
        this.busqueda = busqueda;
    }
    
    public void doBuscar(){
        
    }
}
