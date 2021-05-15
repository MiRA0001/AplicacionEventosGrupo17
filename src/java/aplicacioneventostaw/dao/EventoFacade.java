/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.dao;

import aplicacioneventostaw.entity.Evento;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author mira
 */
@Stateless
public class EventoFacade extends AbstractFacade<Evento> {

    @PersistenceContext(unitName = "AplicacionEventosTAWPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public EventoFacade() {
        super(Evento.class);
    }
    
    public List<Evento> getEventosByUser(int id){
        Query q;
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid");
        q.setParameter("creadorid", id);
        return q.getResultList();
    }
    
    public Evento getEventoById(int id){
        Query q;
        q = this.em.createNamedQuery("Evento.findByEventoId");
        q.setParameter("eventoId", id);
        return (Evento)q.getResultList().get(0);
    }
    
    
    public List<Evento> getEventosByUserAndTag(int id, String tag){
        Query q;
        q = this.em.createQuery("SELECT e from Evento e JOIN e.etiquetaList a WHERE a.nombre=:tag AND e.creadorId.usuarioId=:id");
        q.setParameter("tag", tag);
        q.setParameter("id", id);
        return q.getResultList();
    }
    
    public List<Evento> getEventosByUserAndCaducados(int id){
    Query q;
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND e.fechaEvento<:hoy");
        q.setParameter("creadorid", id);
        q.setParameter("hoy",new Date());
        return q.getResultList();
    }
    
    public List<Evento> getEventosByUserAndFecha(int id, String fechaInicio, String fechaFin){
        Query q;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date f1 = null;
        Date f2 = null;
        try {
            f1 = formato.parse(fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            f2 = formato.parse(fechaFin);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND (e.fechaEvento BETWEEN :fechainicio AND :fechafin)");
        q.setParameter("creadorid", id);
        q.setParameter("fechainicio", f1);
        q.setParameter("fechafin", f2);
        return q.getResultList();
    }
    
    public List<Evento> getEventosByUserAndCadena(int id, String cadena){
        Query q;
        q = em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND (UPPER(e.titulo) LIKE :cadena OR UPPER(e.descripcion) LIKE :cadena)");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        return q.getResultList();
    }
    
    public List<Evento> getEventosByUserAndTitulo(int id, String cadena){
        Query q;
        q = em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND UPPER(e.titulo) LIKE :cadena");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        return q.getResultList();
    }
 
    public List<Evento> getEventosByUserAndTodoTitulo(int id, String cadena, String fechaInicio, String fechaFin){
        Query q;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date f1 = null;
        Date f2 = null;
        try {
            f1 = formato.parse(fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            f2 = formato.parse(fechaFin);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND UPPER(e.titulo) LIKE :cadena AND (e.fechaEvento BETWEEN :fechainicio AND :fechafin) AND e.fechaEvento<:hoy");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        q.setParameter("fechainicio", f1);
        q.setParameter("fechafin", f2);
        q.setParameter("hoy",new Date());
        return q.getResultList();
    }

    public List<Evento> getEventosByUserAndTodoFiltro(int id, String cadena, String fechaInicio, String fechaFin){
        Query q;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date f1 = null;
        Date f2 = null;
        try {
            f1 = formato.parse(fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            f2 = formato.parse(fechaFin);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND (UPPER(e.titulo) LIKE :cadena OR UPPER(e.descripcion) LIKE :cadena) AND (e.fechaEvento BETWEEN :fechainicio AND :fechafin) AND e.fechaEvento<:hoy");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        q.setParameter("fechainicio", f1);
        q.setParameter("fechafin", f2);
        q.setParameter("hoy",new Date());
        return q.getResultList();
    }
    
    public List<Evento> getEventosByUserAndTituloAndFecha(int id, String cadena, String fechaInicio, String fechaFin){
        Query q;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date f1 = null;
        Date f2 = null;
        try {
            f1 = formato.parse(fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            f2 = formato.parse(fechaFin);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND UPPER(e.titulo) LIKE :cadena AND (e.fechaEvento BETWEEN :fechainicio AND :fechafin)");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        q.setParameter("fechainicio", f1);
        q.setParameter("fechafin", f2);
        return q.getResultList();
    }
    
    public List<Evento> getEventosByUserAndTituloAndCaducados(int id, String cadena){
        Query q;
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND (UPPER(e.titulo) LIKE :cadena) AND (e.fechaEvento<:hoy)");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        q.setParameter("hoy",new Date());
        return q.getResultList();
    }

    public List<Evento> getEventosByUserAndFiltroAndFecha(int id, String cadena, String fechaInicio, String fechaFin){
        Query q;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date f1 = null;
        Date f2 = null;
        try {
            f1 = formato.parse(fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            f2 = formato.parse(fechaFin);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND (UPPER(e.titulo) LIKE :cadena OR UPPER(e.descripcion) LIKE :cadena) AND (e.fechaEvento BETWEEN :fechainicio AND :fechafin)");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        q.setParameter("fechainicio", f1);
        q.setParameter("fechafin", f2);
        return q.getResultList();
    }
 
    public List<Evento> getEventosByUserAndFechaAndCaducados(int id, String fechaInicio, String fechaFin){
        Query q;
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        Date f1 = null;
        Date f2 = null;
        try {
            f1 = formato.parse(fechaInicio);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            f2 = formato.parse(fechaFin);
        } catch (ParseException ex) {
            Logger.getLogger(EventoFacade.class.getName()).log(Level.SEVERE, null, ex);
        }
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND (e.fechaEvento BETWEEN :fechainicio AND :fechafin) AND (e.fechaEvento<:hoy)");
        q.setParameter("creadorid", id);
        q.setParameter("fechainicio", f1);
        q.setParameter("fechafin", f2);
        q.setParameter("hoy",new Date());
        return q.getResultList();
    }    
    
    public List<Evento> getEventosByUserAndFiltroAndCaducados(int id, String cadena){
    Query q;
        q = this.em.createQuery("SELECT e FROM Evento e WHERE e.creadorId.usuarioId = :creadorid AND (UPPER(e.titulo) LIKE :cadena OR UPPER(e.descripcion) LIKE :cadena) AND e.fechaEvento<:hoy");
        q.setParameter("creadorid", id);
        q.setParameter("cadena", "%" + cadena.toUpperCase() + "%");
        q.setParameter("hoy",new Date());
        return q.getResultList();
    }
    
}
