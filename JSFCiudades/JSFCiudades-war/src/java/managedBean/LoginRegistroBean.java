/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.UsuarioFacade;
import JSFCiudades.entity.Usuario;
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
    private int idUsuario;

    private String passRegister1;
    private String passRegister2;

    private String error;

    protected Usuario usuario;

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

    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int id) {
        this.idUsuario = id;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public String doLogin() {

        //verificar si existe una sesión iniciada
        if (this.sesion == false) { //si no existe una sesión previa

            idUsuario = usuarioFacade.isLoginOK(username, passLogin);
            if (idUsuario > 0) { //verificar si el login es correcto
                this.sesion = true;
                usuario = new Usuario(idUsuario, username, passLogin);

                return "PrincipalCiudad";
            } else {

                error = "Nombre de usuario / contraseña incorrectos";
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

                if (passRegister1.equals(passRegister2)) {

                    this.sesion = true;

                    usuario = new Usuario();
                    usuario.setNombreUsuario(username);
                    String passHuella = MD5Signature.generateMD5Signature(passRegister1);
                    usuario.setContrasena(passHuella);
                    usuarioFacade.create(usuario);
                    return "PrincipalCiudad";

                } else {
                    
                    error = "Las contraseñas no coinciden";
                    this.sesion = false;
                    return "LoginRegistro";
                    
                }

                

            }

        } else {

            error = "Sesión ya iniciada";
            return "PrincipalCiudad";

        }
    }

    public String doLogout() {

        sesion = false;
        username = "";
        passLogin = "";
        passRegister1 = "";
        passRegister2 = "";
        idUsuario = 0;
        usuario = new Usuario();

        return "PrincipalCiudad";

    }

}
