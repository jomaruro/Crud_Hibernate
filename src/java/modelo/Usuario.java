package modelo;
// Generated 05-oct-2018 10:47:59 by Hibernate Tools 4.3.1

import java.util.Date;
import java.util.HashSet;
import java.util.Set;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * Usuario generated by hbm2java
 */
@Entity
@Table(name = "USUARIO",
         schema = "TPV"
)
public class Usuario implements java.io.Serializable {

    private int idUsuario;
    private String nombre;
    private String clave;
    private String nivel;
    private Date fechaCreacion;
    private Date fechaModificacion;
    private Set<Cabecera> cabeceras = new HashSet(0);

    public Usuario() {
    }

    public Usuario(int idUsuario, String nombre) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
    }

    public Usuario(int idUsuario, String nombre, String clave, String nivel, Date fechaCreacion, Date fechaModificacion, Set<Cabecera> cabeceras) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.clave = clave;
        this.nivel = nivel;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
        this.cabeceras = cabeceras;
    }

    @Id
    @Column(name = "ID_USUARIO", unique = true, nullable = false, precision = 10, scale = 0)
    public int getIdUsuario() {
        return this.idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    @Column(name = "NOMBRE", nullable = false, length = 15)
    public String getNombre() {
        return this.nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Column(name = "CLAVE", length = 10)
    public String getClave() {
        return this.clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    @Column(name = "NIVEL", length = 10)
    public String getNivel() {
        return this.nivel;
    }

    public void setNivel(String nivel) {
        this.nivel = nivel;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_CREACION", length = 7)
    public Date getFechaCreacion() {
        return this.fechaCreacion;
    }

    public void setFechaCreacion(Date fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "FECHA_MODIFICACION", length = 7)
    public Date getFechaModificacion() {
        return this.fechaModificacion;
    }

    public void setFechaModificacion(Date fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "usuario")
    public Set<Cabecera> getCabeceras() {
        return this.cabeceras;
    }

    public void setCabeceras(Set<Cabecera> cabeceras) {
        this.cabeceras = cabeceras;
    }
}
