/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package aplicacioneventostaw.entity;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author mira
 */
@Entity
@Table(name = "EVENTO")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Evento.findAll", query = "SELECT e FROM Evento e")
    , @NamedQuery(name = "Evento.findByEventoId", query = "SELECT e FROM Evento e WHERE e.eventoId = :eventoId")
    , @NamedQuery(name = "Evento.findByTitulo", query = "SELECT e FROM Evento e WHERE e.titulo = :titulo")
    , @NamedQuery(name = "Evento.findByDescripcion", query = "SELECT e FROM Evento e WHERE e.descripcion = :descripcion")
    , @NamedQuery(name = "Evento.findByFechaEvento", query = "SELECT e FROM Evento e WHERE e.fechaEvento = :fechaEvento")
    , @NamedQuery(name = "Evento.findByHora", query = "SELECT e FROM Evento e WHERE e.hora = :hora")
    , @NamedQuery(name = "Evento.findByFechaMaximaReserva", query = "SELECT e FROM Evento e WHERE e.fechaMaximaReserva = :fechaMaximaReserva")
    , @NamedQuery(name = "Evento.findByPrecio", query = "SELECT e FROM Evento e WHERE e.precio = :precio")
    , @NamedQuery(name = "Evento.findByAforo", query = "SELECT e FROM Evento e WHERE e.aforo = :aforo")
    , @NamedQuery(name = "Evento.findByMaximoEntradasPorUsuario", query = "SELECT e FROM Evento e WHERE e.maximoEntradasPorUsuario = :maximoEntradasPorUsuario")
    , @NamedQuery(name = "Evento.findByEntradasNumeradas", query = "SELECT e FROM Evento e WHERE e.entradasNumeradas = :entradasNumeradas")
    , @NamedQuery(name = "Evento.findByFilas", query = "SELECT e FROM Evento e WHERE e.filas = :filas")
    , @NamedQuery(name = "Evento.findByAsientosPorFila", query = "SELECT e FROM Evento e WHERE e.asientosPorFila = :asientosPorFila")})
public class Evento implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "EVENTO_ID")
    private Integer eventoId;
    @Basic(optional = false)
    @Column(name = "TITULO")
    private String titulo;
    @Column(name = "DESCRIPCION")
    private String descripcion;
    @Basic(optional = false)
    @Column(name = "FECHA_EVENTO")
    @Temporal(TemporalType.DATE)
    private Date fechaEvento;
    @Basic(optional = false)
    @Column(name = "HORA")
    @Temporal(TemporalType.TIME)
    private Date hora;
    @Basic(optional = false)
    @Column(name = "FECHA_MAXIMA_RESERVA")
    @Temporal(TemporalType.DATE)
    private Date fechaMaximaReserva;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Basic(optional = false)
    @Column(name = "PRECIO")
    private BigDecimal precio;
    @Basic(optional = false)
    @Column(name = "AFORO")
    private int aforo;
    @Basic(optional = false)
    @Column(name = "MAXIMO_ENTRADAS_POR_USUARIO")
    private int maximoEntradasPorUsuario;
    @Basic(optional = false)
    @Column(name = "ENTRADAS_NUMERADAS")
    private Boolean entradasNumeradas;
    @Column(name = "FILAS")
    private Integer filas;
    @Column(name = "ASIENTOS_POR_FILA")
    private Integer asientosPorFila;
    @JoinTable(name = "EVENTO_ETIQUETA", joinColumns = {
        @JoinColumn(name = "EVENTO_ID", referencedColumnName = "EVENTO_ID")}, inverseJoinColumns = {
        @JoinColumn(name = "ETIQUETA_ID", referencedColumnName = "ETIQUETA_ID")})
    @ManyToMany
    private List<Etiqueta> etiquetaList;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "evento")
    private List<Reserva> reservaList;
    @JoinColumn(name = "CREADOR_ID", referencedColumnName = "USUARIO_ID")
    @ManyToOne(optional = false)
    private Usuario usuario;

    public Evento() {
    }

    public Evento(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public Evento(Integer eventoId, String titulo, Date fechaEvento, Date hora, Date fechaMaximaReserva, BigDecimal precio, int aforo, int maximoEntradasPorUsuario, Boolean entradasNumeradas) {
        this.eventoId = eventoId;
        this.titulo = titulo;
        this.fechaEvento = fechaEvento;
        this.hora = hora;
        this.fechaMaximaReserva = fechaMaximaReserva;
        this.precio = precio;
        this.aforo = aforo;
        this.maximoEntradasPorUsuario = maximoEntradasPorUsuario;
        this.entradasNumeradas = entradasNumeradas;
    }

    public Integer getEventoId() {
        return eventoId;
    }

    public void setEventoId(Integer eventoId) {
        this.eventoId = eventoId;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Date getFechaEvento() {
        return fechaEvento;
    }

    public void setFechaEvento(Date fechaEvento) {
        this.fechaEvento = fechaEvento;
    }

    public Date getHora() {
        return hora;
    }

    public void setHora(Date hora) {
        this.hora = hora;
    }

    public Date getFechaMaximaReserva() {
        return fechaMaximaReserva;
    }

    public void setFechaMaximaReserva(Date fechaMaximaReserva) {
        this.fechaMaximaReserva = fechaMaximaReserva;
    }

    public BigDecimal getPrecio() {
        return precio;
    }

    public void setPrecio(BigDecimal precio) {
        this.precio = precio;
    }

    public int getAforo() {
        return aforo;
    }

    public void setAforo(int aforo) {
        this.aforo = aforo;
    }

    public int getMaximoEntradasPorUsuario() {
        return maximoEntradasPorUsuario;
    }

    public void setMaximoEntradasPorUsuario(int maximoEntradasPorUsuario) {
        this.maximoEntradasPorUsuario = maximoEntradasPorUsuario;
    }

    public Boolean getEntradasNumeradas() {
        return entradasNumeradas;
    }

    public void setEntradasNumeradas(Boolean entradasNumeradas) {
        this.entradasNumeradas = entradasNumeradas;
    }

    public Integer getFilas() {
        return filas;
    }

    public void setFilas(Integer filas) {
        this.filas = filas;
    }

    public Integer getAsientosPorFila() {
        return asientosPorFila;
    }

    public void setAsientosPorFila(Integer asientosPorFila) {
        this.asientosPorFila = asientosPorFila;
    }

    @XmlTransient
    public List<Etiqueta> getEtiquetaList() {
        return etiquetaList;
    }

    public void setEtiquetaList(List<Etiqueta> etiquetaList) {
        this.etiquetaList = etiquetaList;
    }

    @XmlTransient
    public List<Reserva> getReservaList() {
        return reservaList;
    }

    public void setReservaList(List<Reserva> reservaList) {
        this.reservaList = reservaList;
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
        hash += (eventoId != null ? eventoId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Evento)) {
            return false;
        }
        Evento other = (Evento) object;
        if ((this.eventoId == null && other.eventoId != null) || (this.eventoId != null && !this.eventoId.equals(other.eventoId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "aplicacioneventostaw.entity.Evento[ eventoId=" + eventoId + " ]";
    }
    
}
