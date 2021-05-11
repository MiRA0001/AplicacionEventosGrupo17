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
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author mira
 */
@Entity
@Table(name = "ESTUDIO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Estudio.findAll", query = "SELECT e FROM Estudio e")
    , @NamedQuery(name = "Estudio.findByEstudioId", query = "SELECT e FROM Estudio e WHERE e.estudioId = :estudioId")
    , @NamedQuery(name = "Estudio.findByTitulo", query = "SELECT e FROM Estudio e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "Estudio.findByEtiquetas", query = "SELECT e FROM Estudio e WHERE e.etiquetas = :etiquetas")
    , @NamedQuery(name = "Estudio.findByEventoCampos", query = "SELECT e FROM Estudio e WHERE e.eventoCampos = :eventoCampos")
    , @NamedQuery(name = "Estudio.findByUsuarioCampos", query = "SELECT e FROM Estudio e WHERE e.usuarioCampos = :usuarioCampos")
    , @NamedQuery(name = "Estudio.findByUsuarioEventosCampos", query = "SELECT e FROM Estudio e WHERE e.usuarioEventosCampos = :usuarioEventosCampos")
    , @NamedQuery(name = "Estudio.findByEventoCondiciones", query = "SELECT e FROM Estudio e WHERE e.eventoCondiciones = :eventoCondiciones")
    , @NamedQuery(name = "Estudio.findByUsuarioCondiciones", query = "SELECT e FROM Estudio e WHERE e.usuarioCondiciones = :usuarioCondiciones")
    , @NamedQuery(name = "Estudio.findByUsuarioEventosCondiciones", query = "SELECT e FROM Estudio e WHERE e.usuarioEventosCondiciones = :usuarioEventosCondiciones")
    , @NamedQuery(name = "Estudio.findByEventoValores", query = "SELECT e FROM Estudio e WHERE e.eventoValores = :eventoValores")
    , @NamedQuery(name = "Estudio.findByUsuarioValores", query = "SELECT e FROM Estudio e WHERE e.usuarioValores = :usuarioValores")
    , @NamedQuery(name = "Estudio.findByUsuarioEventosValores", query = "SELECT e FROM Estudio e WHERE e.usuarioEventosValores = :usuarioEventosValores")})
public class Estudio implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "ESTUDIO_ID")
    private Integer estudioId;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "ETIQUETAS")
    private String etiquetas;
    @Column(name = "EVENTO_CAMPOS")
    private String eventoCampos;
    @Column(name = "USUARIO_CAMPOS")
    private String usuarioCampos;
    @Column(name = "USUARIO_EVENTOS_CAMPOS")
    private String usuarioEventosCampos;
    @Column(name = "EVENTO_CONDICIONES")
    private String eventoCondiciones;
    @Column(name = "USUARIO_CONDICIONES")
    private String usuarioCondiciones;
    @Column(name = "USUARIO_EVENTOS_CONDICIONES")
    private String usuarioEventosCondiciones;
    @Column(name = "EVENTO_VALORES")
    private String eventoValores;
    @Column(name = "USUARIO_VALORES")
    private String usuarioValores;
    @Column(name = "USUARIO_EVENTOS_VALORES")
    private String usuarioEventosValores;
    @JoinColumn(name = "ANALISTA_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Estudio() {
    }

    public Estudio(Integer estudioId) {
        this.estudioId = estudioId;
    }

    public Estudio(Integer estudioId, String titulo) {
        this.estudioId = estudioId;
        this.titulo = titulo;
    }

    public Integer getEstudioId() {
        return estudioId;
    }

    public void setEstudioId(Integer estudioId) {
        this.estudioId = estudioId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getEtiquetas() {
        return etiquetas;
    }

    public void setEtiquetas(String etiquetas) {
        this.etiquetas = etiquetas;
    }

    public String getEventoCampos() {
        return eventoCampos;
    }

    public void setEventoCampos(String eventoCampos) {
        this.eventoCampos = eventoCampos;
    }

    public String getUsuarioCampos() {
        return usuarioCampos;
    }

    public void setUsuarioCampos(String usuarioCampos) {
        this.usuarioCampos = usuarioCampos;
    }

    public String getUsuarioEventosCampos() {
        return usuarioEventosCampos;
    }

    public void setUsuarioEventosCampos(String usuarioEventosCampos) {
        this.usuarioEventosCampos = usuarioEventosCampos;
    }

    public String getEventoCondiciones() {
        return eventoCondiciones;
    }

    public void setEventoCondiciones(String eventoCondiciones) {
        this.eventoCondiciones = eventoCondiciones;
    }

    public String getUsuarioCondiciones() {
        return usuarioCondiciones;
    }

    public void setUsuarioCondiciones(String usuarioCondiciones) {
        this.usuarioCondiciones = usuarioCondiciones;
    }

    public String getUsuarioEventosCondiciones() {
        return usuarioEventosCondiciones;
    }

    public void setUsuarioEventosCondiciones(String usuarioEventosCondiciones) {
        this.usuarioEventosCondiciones = usuarioEventosCondiciones;
    }

    public String getEventoValores() {
        return eventoValores;
    }

    public void setEventoValores(String eventoValores) {
        this.eventoValores = eventoValores;
    }

    public String getUsuarioValores() {
        return usuarioValores;
    }

    public void setUsuarioValores(String usuarioValores) {
        this.usuarioValores = usuarioValores;
    }

    public String getUsuarioEventosValores() {
        return usuarioEventosValores;
    }

    public void setUsuarioEventosValores(String usuarioEventosValores) {
        this.usuarioEventosValores = usuarioEventosValores;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (estudioId != null ? estudioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Estudio)) {
            return false;
        }
        Estudio other = (Estudio) object;
        if ((this.estudioId == null && other.estudioId != null) || (this.estudioId != null && !this.estudioId.equals(other.estudioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacioneventostaw.entity.Estudio[ estudioId=" + estudioId + " ]";
    }
    
}
