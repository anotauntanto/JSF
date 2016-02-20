/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.entity.Ciudad;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;

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
    protected List<Ciudad> listaCiudades;
    protected int indice;

    final protected int tamPag = 2;

    protected Ciudad ciudad;

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
    

        System.out.println("Total: " + total);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Ciudad> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<Ciudad> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public String doAnterior() {

        if (indice != 0) {
            indice -= tamPag + 1;
            int[] num = {indice, indice + tamPag};
            listaCiudades = ciudadFacade.findRange(num);
            System.out.println("IndiceAnt: " + indice);

        }

        return null;
    }

    public String doSiguiente() {

        if (indice < (this.total - 3)) {
            indice += tamPag + 1;
            int[] num = {indice, indice + tamPag};
            listaCiudades = ciudadFacade.findRange(num);
            System.out.println("IndiceSig: " + indice);
        }

        return null;
    }

    public String doMostrarCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
        return "PrincipalCiudad";
    }

}
