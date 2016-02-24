/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package managedBean;

import JSFCiudades.ejb.ComentarioEventoFacade;
import JSFCiudades.ejb.ComentarioPreguntaFacade;
import JSFCiudades.ejb.EventoFacade;
import JSFCiudades.ejb.PreguntaFacade;
import JSFCiudades.ejb.UsuarioEventoFacade;
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
    private UsuarioEventoFacade usuarioEventoFacade;
    @EJB
    private ComentarioPreguntaFacade comentarioPreguntaFacade;
    @EJB
    private ComentarioEventoFacade comentarioEventoFacade;
    @EJB
    private PreguntaFacade preguntaFacade;
    @EJB
    private EventoFacade eventoFacade;
    
    

    @EJB
    private UsuarioFacade usuarioFacade;
    

    private List<Usuario> listaUsuarios;

    /**
     * Creates a new instance of ListarUsuariosBean
     */
    public ListarUsuariosBean() {
    }

    @PostConstruct
    public void init() {
        listaUsuarios = usuarioFacade.findAll();
    }

    public List<Usuario> getListaUsuarios() {
        return listaUsuarios;
    }

    public void setListaUsuarios(List<Usuario> listaUsuarios) {
        this.listaUsuarios = listaUsuarios;
    }

    public int hilosCreados(Usuario usuario) {
        
        return eventoFacade.getNumEventosCreadosByUsuario(usuario) + preguntaFacade.getNumPreguntasCreadasByUsuario(usuario);
    }

    public int mensajesEscritos(Usuario usuario) {
        return comentarioEventoFacade.getNumComentariosEventoByUsuario(usuario) + comentarioPreguntaFacade.getNumComentariosPreguntaByUsuario(usuario);
    }

    public int eventosAsistidos(Usuario usuario){
        return usuarioEventoFacade.obtenerNumEventosAsist(usuario);
    }
    
    public String doDelete(Usuario usuario) {
        usuarioFacade.bloquearUsuario(usuario);
        listaUsuarios = usuarioFacade.findAll();
        return "ConfiguracionInsertarCiudad";
    }
    
    public boolean isBloqueado(Usuario usuario){
        return usuarioFacade.isBloqueado(usuario);
    }

}
