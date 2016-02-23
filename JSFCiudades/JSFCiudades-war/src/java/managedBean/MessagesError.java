/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import java.io.Serializable;
import javax.faces.bean.ManagedBean;

/**
 *
 * @author inftel08
 */

@ManagedBean
public class MessagesError implements Serializable{
    
    private String errorCadenaVacia = "Debe introducir algún texto...";
    private String errorUsuario = "Nombre de usuario / contraseña incorrectos\n";
    private String errorUsername = "Nombre de usuario en uso \n";

    public String getErrorCadenaVacia() {
        return errorCadenaVacia;
    }

    public void setErrorCadenaVacia(String error1) {
        this.errorCadenaVacia = error1;
    }

    public String getErrorUsuario() {
        return errorUsuario;
    }

    public void setErrorUsuario(String errorUsuario) {
        this.errorUsuario = errorUsuario;
    }

    public String getErrorUsername() {
        return errorUsername;
    }

    public void setErrorUsername(String errorUsername) {
        this.errorUsername = errorUsername;
    }
    
    
    
}
