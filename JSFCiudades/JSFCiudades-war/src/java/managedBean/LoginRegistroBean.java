/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.entity.UsuarioFacade;
import JSFCiudades.ejb.Usuario;
import JSFCiudades.util.MD5Signature;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author inftel08
 */
@ManagedBean
@SessionScoped
public class LoginRegistroBean {

    @EJB
    private UsuarioFacade usuarioFacade;

    private Boolean sesion;

    private String username;
    private String passLogin;

    private String passRegister1;
    private String passRegister2;

    private String error;

    /**
     * Creates a new instance of LoginRegistroBean
     */
    public LoginRegistroBean() {
        sesion = false;
        error = "";
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassLogin() {
        return passLogin;
    }

    public void setPassLogin(String passLogin) {
        this.passLogin = passLogin;
    }

    public String getPassRegister1() {
        return passRegister1;
    }

    public void setPassRegister1(String passRegister1) {
        this.passRegister1 = passRegister1;
    }

    public String getPassRegister2() {
        return passRegister2;
    }

    public void setPassRegister2(String passRegister2) {
        this.passRegister2 = passRegister2;
    }

    public Boolean getSesion() {
        return sesion;
    }

    public void setSesion(Boolean sesion) {
        this.sesion = sesion;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public String doLogin() {

        //verificar si existe una sesión iniciada
        if (this.sesion == false) { //si no existe una sesión previa

            boolean exist = usuarioFacade.isLoginOK(username, passLogin);
            if (exist) { //verificar si el login es correcto
                this.sesion = true;
                return "PrincipalCiudad";
            } else {
                this.sesion = false;
                return "LoginRegistro";
            }

        } else {

            return "PrincipalCiudad";
        }

    }

    public String doRegister() {

        //verificar si existe una sesión iniciada
        if (this.sesion == false) { //si no existe una sesión previa

            boolean existUserName = usuarioFacade.existUserName(username);

            if (existUserName) {
                error = "Nombre de usuario en uso";
                return "LoginRegistro";
                //añadir mensaje de error

            } else {

                this.sesion = true;
                
                Usuario user = new Usuario();
                user.setNombreUsuario(username);
                String passHuella = MD5Signature.generateMD5Signature(passRegister1);
                user.setContrasena(passHuella);
                usuarioFacade.create(user);
                
                return "PrincipalCiudad";

            }

        } else {
            
            error = "Sesión ya iniciada";
            return "PrincipalCiudad";
            
        }
    }

}
