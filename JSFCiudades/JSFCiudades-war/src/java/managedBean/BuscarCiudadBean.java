/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.entity.Ciudad;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

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
    
    @ManagedProperty(value="#{ciudadBean}")
    protected CiudadBean ciudadBean;
    
    protected String busqueda;
    
    protected String txt4;
    
    protected Ciudad ciudad4;
    
    /**
     * Creates a new instance of BuscarCiudadBean
     */
    public BuscarCiudadBean() {
        
    }
    
    @PostConstruct
    public void init(){
        txt4="";        ;
        System.out.println("SAlir: "+ txt4);
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

    public String getTxt4() {
        return txt4;
    }

    public void setTxt4(String txt4) {
        this.txt4 = txt4;
    }

    public Ciudad getCiudad4() {
        return ciudad4;
    }

    public void setCiudad4(Ciudad ciudad4) {
        this.ciudad4 = ciudad4;
    }

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }
    
    
    
    public String doBuscar() {
        //int total = ciudadFacade.getListaCiudadesSearch(busqueda).size();
       System.out.println("total  PEROSDS");
        if (txt4 != null){
            //String mitxt = new String(txt4.getBytes("UTF-8"), "UTF-8");
            txt4 = txt4.replaceAll("Ã±", "ñ");
            //System.out.println("total CON1E "+ replaceAll);
            
            System.out.println("total CON2E "+ txt4);
            ciudadBean.ciudad = ciudadFacade.getCiudad(txt4);
        
        //ciudadFacade.getListaCiudadesSearch(busqueda);
        //navegacionCiudadesBean.afterBusqueda = true;
        System.out.println("total "+ ciudadBean.ciudad.getDescripcion());
        txt4="";
        return "PrincipalCiudad";
        }
        
        return "";
    }
    /*
     public List<Ciudad> completeCiudad(String query) {
        List<Ciudad> results = ciudadFacade.getListaCiudadesNameSearch(txt4);
        
         
        return results;
    }
     */
     public List<String> completeText(String query) {
        List<String> results = new ArrayList<String>();
        List<Ciudad> resultCiudad = ciudadFacade.getListaCiudadesNameSearch(query);
        for(int i = 0; i < resultCiudad.size(); i++) {
            results.add(resultCiudad.get(i).getNombreCiudad());
            System.out.println("LISTA NOMBRE "+results.get(i));
        }
         
        return results;
    }
     
     public String doBuscar2(Ciudad ciudad){
        //int total = ciudadFacade.getListaCiudadesSearch(busqueda).size();
    
        System.out.println("total "+ ciudad.getNombreCiudad());
        
        return "ListarCiudades";
    }
}
