/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.ComentarioPreguntaFacade;
import JSFCiudades.ejb.PreguntaFacade;
import JSFCiudades.entity.Pregunta;
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
public class ComentariosPreguntaBean {
    @EJB
    private ComentarioPreguntaFacade comentarioPreguntaFacade;
    @EJB
    private PreguntaFacade preguntaFacade;
    
    protected Pregunta pregunta;

    @ManagedProperty(value="#{ciudadBean}")
    protected CiudadBean ciudadBean;
    /**
     * Creates a new instance of ComentariosPreguntaBean
     */
    
    
    public ComentariosPreguntaBean() {

    }
    
    @PostConstruct
    public void init () {
        pregunta = preguntaFacade.find(ciudadBean.idPregunta);       
    }

    public Pregunta getPregunta() {
        return pregunta;
    }

    public void setPregunta(Pregunta pregunta) {
        this.pregunta = pregunta;
    }

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }
    
    

    
    

}
