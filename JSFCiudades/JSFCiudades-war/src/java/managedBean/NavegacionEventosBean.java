/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.entity.Ciudad;
import JSFCiudades.entity.Evento;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author inftel08
 */
@ManagedBean
@SessionScoped
public class NavegacionEventosBean {

    @EJB
    private EventoFacade eventoFacade;

    protected int total;
    protected List<Evento> listaEventos;
    protected int indice;

    final protected int tamPag = 4;

    protected Evento evento;
    protected Ciudad ciudad;
    
    @ManagedProperty(value="#{ciudadBean}")
    protected CiudadBean ciudadBean;

    /**
     * Creates a new instance of NavegacionCiudadesBean
     */
    public NavegacionEventosBean() {
    }

    @PostConstruct
    public void init() {

        int[] num = {0, tamPag};
        listaEventos = eventoFacade.findRange(num);
        total = eventoFacade.count();
        this.indice = 0;

        System.out.println("Total: " + total);
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public List<Evento> getListaEventos() {
        return listaEventos;
    }

    public void setListaEventos(List<Evento> listaEventos) {
        this.listaEventos = listaEventos;
    }

    public int getIndice() {
        return indice;
    }

    public void setIndice(int indice) {
        this.indice = indice;
    }

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }

    public String doAnterior() {

        if (indice != 0) {
            indice -= tamPag + 1;
            int[] num = {indice, indice + tamPag};
            listaEventos = eventoFacade.findRange(num);
  

        }
        return null;
    }

    public String doSiguiente() {

        if (indice < (this.total - 5)) {
            indice += tamPag + 1;
            int[] num = {indice, indice + tamPag};
            listaEventos = eventoFacade.findRange(num);
   
        }

        return null;
    }

    public String doMostrarCiudad(Ciudad ciudad) {
        ciudadBean.ciudad = ciudad;
        this.ciudad = ciudad;
        return "PrincipalCiudad";
    }

    public String doMostrarEvento(Evento evento) {
        ciudadBean.evento = evento;
        this.evento = evento;
        return "ListadoEventoCiudad";
    }

}
