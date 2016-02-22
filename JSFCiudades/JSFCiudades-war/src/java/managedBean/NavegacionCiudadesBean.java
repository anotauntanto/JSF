/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.entity.Ciudad;
import java.io.ByteArrayInputStream;
import java.util.ArrayList;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
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

    //protected Ciudad ciudad;
    protected List<ListaCiudadesStreamed> listaCiudades;

    /**
     * Creates a new instance of NavegacionCiudadesBean
     */
    public NavegacionCiudadesBean() {
    }

    @PostConstruct
    public void init() {

        int[] num = {0, tamPag};
        List<Ciudad> findRange = ciudadFacade.findRange(num);
        listaCiudades = new ArrayList();

        DefaultStreamedContent imagen1 = new DefaultStreamedContent(new ByteArrayInputStream(findRange.get(0).getFoto()));
        ListaCiudadesStreamed ciudad_mostrar1 = new ListaCiudadesStreamed(findRange.get(0).getDescripcion(), findRange.get(0).getNombreCiudad(), imagen1);
        listaCiudades.add(ciudad_mostrar1);
        
        DefaultStreamedContent imagen2 = new DefaultStreamedContent(new ByteArrayInputStream(findRange.get(1).getFoto()));
        ListaCiudadesStreamed ciudad_mostrar2 = new ListaCiudadesStreamed(findRange.get(1).getDescripcion(), findRange.get(1).getNombreCiudad(), imagen2);
        listaCiudades.add(ciudad_mostrar2);
        
        DefaultStreamedContent imagen3 = new DefaultStreamedContent(new ByteArrayInputStream(findRange.get(2).getFoto()));
        ListaCiudadesStreamed ciudad_mostrar3 = new ListaCiudadesStreamed(findRange.get(2).getDescripcion(), findRange.get(2).getNombreCiudad(), imagen3);
        listaCiudades.add(ciudad_mostrar3);
        
        /*for (int i = 0; i <= tamPag; i++) {
         DefaultStreamedContent imagen = new DefaultStreamedContent (new ByteArrayInputStream(findRange.get(i).getFoto()));
         ListaCiudadesStreamed ciudad_mostrar = new ListaCiudadesStreamed(findRange.get(i).getDescripcion(), findRange.get(i).getNombreCiudad(),imagen);
            
         System.out.println("Llego");
         listaCiudades.add(ciudad_mostrar);

         }*/
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

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public List<ListaCiudadesStreamed> getListaCiudades() {
        return listaCiudades;
    }

    public void setListaCiudades(List<ListaCiudadesStreamed> listaCiudades) {
        this.listaCiudades = listaCiudades;
    }

    public String doAnterior() {

        if (indice != 0) {
            indice -= tamPag + 1;
            int[] num = {indice, indice + tamPag};
            List<Ciudad> findRange = ciudadFacade.findRange(num);
            for (int i = 0; i <= tamPag; i++) {
                ListaCiudadesStreamed ciudad_mostrar = new ListaCiudadesStreamed(findRange.get(i).getDescripcion(), findRange.get(i).getNombreCiudad(), new DefaultStreamedContent(new ByteArrayInputStream(findRange.get(i).getFoto())));
                listaCiudades.add(ciudad_mostrar);

            }
            System.out.println("IndiceAnt: " + indice);

        }

        return null;
    }

    public String doSiguiente() {

        if (indice < (this.total - 3)) {
            indice += tamPag + 1;
            int[] num = {indice, indice + tamPag};
            List<Ciudad> findRange = ciudadFacade.findRange(num);
            for (int i = 0; i <= tamPag; i++) {
                ListaCiudadesStreamed ciudad_mostrar = new ListaCiudadesStreamed(findRange.get(i).getDescripcion(), findRange.get(i).getNombreCiudad(), new DefaultStreamedContent(new ByteArrayInputStream(findRange.get(i).getFoto())));
                listaCiudades.add(ciudad_mostrar);

            }
            System.out.println("IndiceSig: " + indice);
        }

        return null;
    }

    public String doMostrarCiudad() {
        //this.ciudad = ciudad;
        return "PrincipalCiudad";
    }

}
