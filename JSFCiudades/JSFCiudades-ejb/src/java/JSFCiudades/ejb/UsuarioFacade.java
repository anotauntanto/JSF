/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package JSFCiudades.ejb;

import JSFCiudades.entity.Usuario;
import JSFCiudades.util.MD5Signature;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author inftel08
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {
    @PersistenceContext(unitName = "JSFCiudades-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
        public int isLoginOK (String username, String password) {

        Usuario u;
        int id = 0;
        
        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1", Usuario.class).setParameter(1, username);
            u = (Usuario) q.getSingleResult();

            String huella = MD5Signature.generateMD5Signature(password);
            if (u.getContrasena().equals(huella)) {
                id = u.getIdUsuario();
            }

        } catch (NoResultException e) {
            id = -1;
        }

        return id;

    }
    
    public boolean existUserName(String username) {

        boolean existe = false;
        Usuario u;

        try {
            Query q = em.createQuery("SELECT u FROM Usuario u WHERE u.nombreUsuario = ?1", Usuario.class).setParameter(1, username);
            u = (Usuario) q.getSingleResult();
            existe=true;

        } catch (NoResultException e) {
            existe = false;
        }

        return existe;

    }
    
}
