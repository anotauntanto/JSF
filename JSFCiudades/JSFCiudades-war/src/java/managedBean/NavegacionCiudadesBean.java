/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.entity.Ciudad;
import static com.sun.faces.facelets.util.Path.context;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.PhaseId;
import model.ListaCiudadesStreamed;
import org.primefaces.model.DefaultStreamedContent;
import org.primefaces.model.StreamedContent;

/**
 *
 * @author inftel08
 */
@ManagedBean
@SessionScoped
public class NavegacionCiudadesBean {

    @EJB
    private CiudadFacade ciudadFacade;

    protected int total;
    protected int indice;
    final protected int tamPag = 2;
    
    protected boolean afterBusqueda;

    protected int id = 0;


    protected List<Ciudad> listaCiudades;


    @ManagedProperty(value = "#{ciudadBean}")
    protected CiudadBean ciudadBean;

    /**
     * Creates a new instance of NavegacionCiudadesBean
     */
    public NavegacionCiudadesBean() {

    }

    @PostConstruct
    public void init() {

        int[] num = {0, tamPag};
        listaCiudades = ciudadFacade.findRange(num);
        total = ciudadFacade.count();
        this.indice = 0;
        afterBusqueda = false;

    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }

    public boolean isAfterBusqueda() {
        return afterBusqueda;
    }

    public void setAfterBusqueda(boolean afterBusqueda) {
        this.afterBusqueda = afterBusqueda;
    }

    
    public StreamedContent getFotoCiudad() {
        
        FacesContext context = FacesContext.getCurrentInstance();
        if (context.getCurrentPhaseId() == PhaseId.RENDER_RESPONSE) {
            return new DefaultStreamedContent();
        } else {
            String strId = context.getExternalContext().getRequestParameterMap().get("id");
            Ciudad ciudad = this.listaCiudades.get(Integer.parseInt(strId));

            return new DefaultStreamedContent(new ByteArrayInputStream(ciudad.getFoto()));
        }
    }

    public String doAnterior() {

        if (indice != 0) {
            indice -= tamPag + 1;
            int[] num = {indice, indice + tamPag};
            listaCiudades = ciudadFacade.findRange(num);

        }

        return null;
    }

    public String doSiguiente() {

        if (indice < (this.total - 3)) {
            indice += tamPag + 1;
            int[] num = {indice, indice + tamPag};
            listaCiudades = ciudadFacade.findRange(num);

        }

        return null;
    }

    public String doMostrarCiudad(Ciudad ciudad) {
     
        ciudadBean.setCiudad(ciudad);
        return "PrincipalCiudad";
    }

     public String doListar() {
        /*int[] num = {0, tamPag};
        listaCiudades = ciudadFacade.findRange(num);
        total = ciudadFacade.count();
        this.indice = 0;
        afterBusqueda = false;*/
         
        init();
        return "ListarCiudades";
    }
}
