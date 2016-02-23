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
    private String error2;

    public String getErrorCadenaVacia() {
        return errorCadenaVacia;
    }

    public void setErrorCadenaVacia(String error1) {
        this.errorCadenaVacia = error1;
    }

    public String getError2() {
        return error2;
    }

    public void setError2(String error2) {
        this.error2 = error2;
    }
    
    
    
}
