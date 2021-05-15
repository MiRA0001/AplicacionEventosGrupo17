/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mira
 */
@Entity
@Table(name = "CONVERSACION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Conversacion.findAll", query = "SELECT c FROM Conversacion c")
    , @NamedQuery(name = "Conversacion.findByConversacionId", query = "SELECT c FROM Conversacion c WHERE c.conversacionId = :conversacionId")})
public class Conversacion implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "CONVERSACION_ID")
    private Integer conversacionId;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuarioId;
    @JoinColumn(name = "TELEOPERADOR_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario teleoperadorId;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "conversacionId")
    private List<Mensaje> mensajeList;

    public Conversacion() {
    }

    public Conversacion(Integer conversacionId) {
        this.conversacionId = conversacionId;
    }

    public Integer getConversacionId() {
        return conversacionId;
    }

    public void setConversacionId(Integer conversacionId) {
        this.conversacionId = conversacionId;
    }

    public Usuario getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Usuario usuarioId) {
        this.usuarioId = usuarioId;
    }

    public Usuario getTeleoperadorId() {
        return teleoperadorId;
    }

    public void setTeleoperadorId(Usuario teleoperadorId) {
        this.teleoperadorId = teleoperadorId;
    }

    @XmlTransient
    public List<Mensaje> getMensajeList() {
        return mensajeList;
    }

    public void setMensajeList(List<Mensaje> mensajeList) {
        this.mensajeList = mensajeList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (conversacionId != null ? conversacionId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Conversacion)) {
            return false;
        }
        Conversacion other = (Conversacion) object;
        if ((this.conversacionId == null && other.conversacionId != null) || (this.conversacionId != null && !this.conversacionId.equals(other.conversacionId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacioneventostaw.entity.Conversacion[ conversacionId=" + conversacionId + " ]";
    }
    
}
