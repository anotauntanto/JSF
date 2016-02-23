/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.CiudadFacade;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel05
 */
@ManagedBean
@RequestScoped
public class BuscarCiudadBean {
    @EJB
    private CiudadFacade ciudadFacade;

    @ManagedProperty(value="#{navegacionCiudadesBean}")
    protected NavegacionCiudadesBean navegacionCiudadesBean;
    
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

    public NavegacionCiudadesBean getNavegacionCiudadesBean() {
        return navegacionCiudadesBean;
    }

    public void setNavegacionCiudadesBean(NavegacionCiudadesBean navegacionCiudadesBean) {
        this.navegacionCiudadesBean = navegacionCiudadesBean;
    }
    
    
    public String doBuscar(){
        //int total = ciudadFacade.getListaCiudadesSearch(busqueda).size();
        navegacionCiudadesBean.listaCiudades = ciudadFacade.getListaCiudadesSearch(busqueda);
        navegacionCiudadesBean.afterBusqueda = true;
        //System.out.println("total "+ total);
        
        return "ListarCiudades";
    }
}
