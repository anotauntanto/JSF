/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.entity.Ciudad;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
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

    @ManagedProperty(value = "#{navegacionCiudadesBean}")
    protected NavegacionCiudadesBean navegacionCiudadesBean;

    @ManagedProperty(value = "#{ciudadBean}")
    protected CiudadBean ciudadBean;

    protected String busqueda;
    protected List<String> listaCiudades;

    /**
     * Creates a new instance of BuscarCiudadBean
     */
    public BuscarCiudadBean() {

    }

    @PostConstruct
    public void init() {
        busqueda = "";
        listaCiudades = ciudadFacade.getListaCiudades();

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

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }

    public List<String> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<String> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public String doBuscarNombre() {

        if (busqueda != null) {
            busqueda = busqueda.replaceAll("Ã±", "ñ");

            List<Ciudad> listTemp = ciudadFacade.getCiudad(busqueda);
            if (listTemp.size() != 0) {
                ciudadBean.ciudad = listTemp.get(0);
            }

            busqueda = "";
            return "PrincipalCiudad";

        }

        return "";
    }

    public List<String> completeCiudad(String query) {
        List<String> results = new ArrayList<String>();

        for (String nombreCiudad : this.listaCiudades) {
            if (nombreCiudad.contains(query)) {
                results.add(nombreCiudad);
            }
        }

        return results;
    }

    
    public String doBuscarDescripcion() {

        navegacionCiudadesBean.listaCiudades = ciudadFacade.getListaCiudadesSearch(busqueda);
        navegacionCiudadesBean.afterBusqueda = true;


        return "ListarCiudades";
    }
}
