/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.dao;

import aplicacioneventostaw.entity.Etiqueta;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mira
 */
@Stateless
public class EtiquetaFacade extends AbstractFacade<Etiqueta> {

    @PersistenceContext(unitName = "AplicacionEventosTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EtiquetaFacade() {
        super(Etiqueta.class);
    }
    
        public Etiqueta getEtiquetaByNombre(String str){
        Query q;
        q = this.em.createQuery("SELECT e FROM Etiqueta e WHERE e.nombre = :nombre ORDER BY e.nombre");
        q.setParameter("nombre", str);
        if (q.getResultList().isEmpty()){
            return null;
        }else{
            return (Etiqueta)q.getResultList().get(0);
        }
        
    }
}
