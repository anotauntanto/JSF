/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.UsuarioFacade;
import JSFCiudades.entity.Usuario;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author inftel08
 */
@ManagedBean
@RequestScoped
public class ListarUsuariosBean {
    @EJB
    private UsuarioFacade usuarioFacade;
    
    private List<Usuario> listaUsuarios;

    /**
     * Creates a new instance of ListarUsuariosBean
     */
    
    
    public ListarUsuariosBean() {
    }
    
    @PostConstruct
    public void init () {       
        listaUsuarios = usuarioFacade.findAll();       
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }
    
    
}
