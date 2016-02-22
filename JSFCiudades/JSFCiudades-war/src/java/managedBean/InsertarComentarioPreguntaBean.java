/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.ComentarioPreguntaFacade;
import JSFCiudades.entity.ComentarioPregunta;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel08
 */
@ManagedBean
@RequestScoped
public class InsertarComentarioPreguntaBean {
    @EJB
    private ComentarioPreguntaFacade comentarioPreguntaFacade;

  
    private ComentarioPregunta comPregunta;
    
    @ManagedProperty(value = "#{ciudadBean}")
    protected CiudadBean ciudadBean;
    @ManagedProperty(value = "#{loginRegistroBean}")
    protected LoginRegistroBean loginRegistroBean;

    /**
     * Creates a new instance of InsertarComentario
     */
    public InsertarComentarioPreguntaBean() {
    }

    @PostConstruct
    public void init () {
        comPregunta = new ComentarioPregunta();
        
    }
    public ComentarioPregunta getComPregunta() {
        return comPregunta;
    }

    public void setComPregunta(ComentarioPregunta comPregunta) {
        this.comPregunta = comPregunta;
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

    public String doGuardarComentarioPregunta() {
        comPregunta.setIdUsuario(loginRegistroBean.usuario);
        comPregunta.setIdPregunta(ciudadBean.getPregunta());
        comentarioPreguntaFacade.create(comPregunta);
        comPregunta = new ComentarioPregunta();
        return "";
    }
    
    

}
