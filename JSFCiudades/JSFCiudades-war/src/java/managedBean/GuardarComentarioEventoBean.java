/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.ComentarioEventoFacade;
import JSFCiudades.entity.ComentarioEvento;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel06
 */
@ManagedBean
@RequestScoped
public class GuardarComentarioEventoBean {
    @EJB
    private ComentarioEventoFacade comentarioEventoFacade;

    
    protected String texto;
    
    
    
    @ManagedProperty(value="#{ciudadBean}")
    protected CiudadBean ciudadBean;
    @ManagedProperty(value="#{loginRegistroBean}")
    protected LoginRegistroBean loginRegistroBean;
  
    
   
   
    /**
     * Creates a new instance of GuardarComentarioEventoBean
     */
    public GuardarComentarioEventoBean() {
    }

    public String getTexto() {
        return texto;
    }

    public void setTexto(String texto) {
        this.texto = texto;
    }

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }

    public LoginRegistroBean getLoginRegistroBean() {
        return loginRegistroBean;
    }

    public void setLoginRegistroBean(LoginRegistroBean loginRegistroBean) {
        this.loginRegistroBean = loginRegistroBean;
    }


    
    
    public String doGuardar(){
        
        ComentarioEvento ce = new ComentarioEvento();
        ce.setIdUsuario(loginRegistroBean.usuario);
        ce.setTexto(texto);
        texto="";
        ce.setIdEvento(ciudadBean.evento);
        
        comentarioEventoFacade.create(ce);
        
        return null;
    }
    

    
    
}
