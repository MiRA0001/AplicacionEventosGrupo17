/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.dao;

import aplicacioneventostaw.entity.Usuario;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mira
 */
@Stateless
public class UsuarioFacade extends AbstractFacade<Usuario> {

    @PersistenceContext(unitName = "AplicacionEventosTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public UsuarioFacade() {
        super(Usuario.class);
    }
    
      public Usuario findByEmail(String em) {
        Query q;
        Usuario usuario = null;
        List<Usuario> lista;
        
        q = this.getEntityManager().createNamedQuery("Usuario.findByEmail");
        q.setParameter("email", em); 

        lista = q.getResultList(); 
        // Si la lista está vacía, quiere decir que no hay ningún administrador con ese email
        if (lista != null && !lista.isEmpty()) { 
            usuario = lista.get(0); // Como sé que solo hay uno, devuelvo directament el primero
        }
        return usuario;
    }
      public List<Usuario> findListByRolOrEmail(String email, String rol) {
        Query q;
        String comparador = "=";
        if(rol==""){
            comparador = ">";
        }
        q = this.getEntityManager().createQuery("SELECT u FROM Usuario u WHERE u.email like :email and u.rol "+comparador+" :rol");
        q.setParameter("email", "%"+email+"%");
        q.setParameter("rol", rol=="" ? 0 : new Integer(rol));
        

        return q.getResultList();
    }
    
}
