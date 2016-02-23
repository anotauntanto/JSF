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
    
    private String error1;
    private String error2;

    public String getError1() {
        return error1;
    }

    public void setError1(String error1) {
        this.error1 = error1;
    }

    public String getError2() {
        return error2;
    }

    public void setError2(String error2) {
        this.error2 = error2;
    }
    
    
    
}
