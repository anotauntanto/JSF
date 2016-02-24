/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.CiudadFacade;
import JSFCiudades.entity.Ciudad;
import java.awt.Image;
import java.io.IOException;
import java.io.InputStream;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.servlet.http.Part;
import org.apache.commons.io.IOUtils;

/**
 *
 * @author inftel08
 */
@ManagedBean
@RequestScoped
public class InsertarCiudadBean {

    @EJB
    private CiudadFacade ciudadFacade;

    /**
     * Creates a new instance of InsertarCiudadBean
     */
    protected Ciudad ciudad;
    protected Part imagePart;

    @ManagedProperty(value = "#{ciudadBean}")
    protected CiudadBean ciudadBean;

    public InsertarCiudadBean() {

        ciudad = new Ciudad();
    }

    public Ciudad getCiudad() {
        return ciudad;
    }

    public void setCiudad(Ciudad ciudad) {
        this.ciudad = ciudad;
    }

    public Part getImagePart() {
        return imagePart;
    }

    public void setImagePart(Part imagePart) {
        this.imagePart = imagePart;
    }

    public CiudadBean getCiudadBean() {
        return ciudadBean;
    }

    public void setCiudadBean(CiudadBean ciudadBean) {
        this.ciudadBean = ciudadBean;
    }

    
    public String doInsertarCiudad() {
        System.out.println("FALLO");
        try {
            InputStream inputStream = imagePart.getInputStream();
            ciudad.setFoto(IOUtils.toByteArray(inputStream));
        } catch (IOException ex) {
            Logger.getLogger(InsertarCiudadBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        ciudadFacade.create(ciudad);
        ciudadBean.ciudad = ciudad;
        ciudad = new Ciudad();
      

        return "";
    }

}
