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
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
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
@Table(name = "USUARIO_EVENTOS")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "UsuarioEventos.findAll", query = "SELECT u FROM UsuarioEventos u")
    , @NamedQuery(name = "UsuarioEventos.findByUsuarioId", query = "SELECT u FROM UsuarioEventos u WHERE u.usuarioId = :usuarioId")
    , @NamedQuery(name = "UsuarioEventos.findByNombre", query = "SELECT u FROM UsuarioEventos u WHERE u.nombre = :nombre")
    , @NamedQuery(name = "UsuarioEventos.findByApellidos", query = "SELECT u FROM UsuarioEventos u WHERE u.apellidos = :apellidos")
    , @NamedQuery(name = "UsuarioEventos.findByDomicilio", query = "SELECT u FROM UsuarioEventos u WHERE u.domicilio = :domicilio")
    , @NamedQuery(name = "UsuarioEventos.findByCiudad", query = "SELECT u FROM UsuarioEventos u WHERE u.ciudad = :ciudad")
    , @NamedQuery(name = "UsuarioEventos.findByEdad", query = "SELECT u FROM UsuarioEventos u WHERE u.edad = :edad")
    , @NamedQuery(name = "UsuarioEventos.findBySexo", query = "SELECT u FROM UsuarioEventos u WHERE u.sexo = :sexo")})
public class UsuarioEventos implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "USUARIO_ID")
    private Integer usuarioId;
    @Basic(optional = false)
    @Column(name = "NOMBRE")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "APELLIDOS")
    private String apellidos;
    @Basic(optional = false)
    @Column(name = "DOMICILIO")
    private String domicilio;
    @Basic(optional = false)
    @Column(name = "CIUDAD")
    private String ciudad;
    @Column(name = "EDAD")
    private Integer edad;
    @Column(name = "SEXO")
    private Character sexo;
    @JoinColumn(name = "USUARIO_ID", referencedColumnName = "USUARIO_ID", insertable = false, updatable = false)
    @OneToOne(optional = false)
    private Usuario usuario;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "usuarioId")
    private List<Reserva> reservaList;

    public UsuarioEventos() {
    }

    public UsuarioEventos(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public UsuarioEventos(Integer usuarioId, String nombre, String apellidos, String domicilio, String ciudad) {
        this.usuarioId = usuarioId;
        this.nombre = nombre;
        this.apellidos = apellidos;
        this.domicilio = domicilio;
        this.ciudad = ciudad;
    }

    public Integer getUsuarioId() {
        return usuarioId;
    }

    public void setUsuarioId(Integer usuarioId) {
        this.usuarioId = usuarioId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public Integer getEdad() {
        return edad;
    }

    public void setEdad(Integer edad) {
        this.edad = edad;
    }

    public Character getSexo() {
        return sexo;
    }

    public void setSexo(Character sexo) {
        this.sexo = sexo;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (usuarioId != null ? usuarioId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof UsuarioEventos)) {
            return false;
        }
        UsuarioEventos other = (UsuarioEventos) object;
        if ((this.usuarioId == null && other.usuarioId != null) || (this.usuarioId != null && !this.usuarioId.equals(other.usuarioId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacioneventostaw.entity.UsuarioEventos[ usuarioId=" + usuarioId + " ]";
    }
    
}
