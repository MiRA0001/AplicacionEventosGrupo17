/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.entity;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mira
 */
@Entity
@Table(name = "ETIQUETA")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Etiqueta.findAll", query = "SELECT e FROM Etiqueta e")
    , @NamedQuery(name = "Etiqueta.findByEtiquetaId", query = "SELECT e FROM Etiqueta e WHERE e.etiquetaId = :etiquetaId")
    , @NamedQuery(name = "Etiqueta.findByNombre", query = "SELECT e FROM Etiqueta e WHERE e.nombre = :nombre")})
public class Etiqueta implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ETIQUETA_ID")
    private Integer etiquetaId;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @ManyToMany(mappedBy = "etiquetaList")
    private List<Evento> eventoList;

    public Etiqueta() {
    }

    public Etiqueta(Integer etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    public Etiqueta(Integer etiquetaId, String nombre) {
        this.etiquetaId = etiquetaId;
        this.nombre = nombre;
    }

    public Integer getEtiquetaId() {
        return etiquetaId;
    }

    public void setEtiquetaId(Integer etiquetaId) {
        this.etiquetaId = etiquetaId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @XmlTransient
    public List<Evento> getEventoList() {
        return eventoList;
    }

    public void setEventoList(List<Evento> eventoList) {
        this.eventoList = eventoList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (etiquetaId != null ? etiquetaId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Etiqueta)) {
            return false;
        }
        Etiqueta other = (Etiqueta) object;
        if ((this.etiquetaId == null && other.etiquetaId != null) || (this.etiquetaId != null && !this.etiquetaId.equals(other.etiquetaId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacioneventostaw.entity.Etiqueta[ etiquetaId=" + etiquetaId + " ]";
    }
    
}
