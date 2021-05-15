/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.entity;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mira
 */
@Entity
@Table(name = "RESERVA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByReservaId", query = "SELECT r FROM Reserva r WHERE r.reservaId = :reservaId")
    , @NamedQuery(name = "Reserva.findByFila", query = "SELECT r FROM Reserva r WHERE r.fila = :fila")
    , @NamedQuery(name = "Reserva.findByAsiento", query = "SELECT r FROM Reserva r WHERE r.asiento = :asiento")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "RESERVA_ID")
    private Integer reservaId;
    @Column(name = "FILA")
    private Integer fila;
    @Column(name = "ASIENTO")
    private Integer asiento;
    @JoinColumn(name = "EVENTO_ID", referencedColumnName = "EVENTO_ID")
    @ManyToOne(optional = false)
    private Evento eventoId;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private UsuarioEventos usuarioId;

    public Reserva() {
    }

    public Reserva(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public Integer getReservaId() {
        return reservaId;
    }

    public void setReservaId(Integer reservaId) {
        this.reservaId = reservaId;
    }

    public Integer getFila() {
        return fila;
    }

    public void setFila(Integer fila) {
        this.fila = fila;
    }

    public Integer getAsiento() {
        return asiento;
    }

    public void setAsiento(Integer asiento) {
        this.asiento = asiento;
    }

    public Evento getEventoId() {
        return eventoId;
    }

    public void setEventoId(Evento eventoId) {
        this.eventoId = eventoId;
    }

    public UsuarioEventos getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(UsuarioEventos usuarioId) {
        this.usuarioId = usuarioId;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (reservaId != null ? reservaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.reservaId == null && other.reservaId != null) || (this.reservaId != null && !this.reservaId.equals(other.reservaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacioneventostaw.entity.Reserva[ reservaId=" + reservaId + " ]";
    }
    
}
