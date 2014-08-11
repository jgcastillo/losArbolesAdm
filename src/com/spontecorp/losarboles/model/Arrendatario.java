/*
 * Copyrigth (c) 2014, Spontecorp, C.A. Todos los derechos reservados
 *  
 * ESTE SOFTWARE ES SUMINISTRADO POR LOS PROPIETARIOS DE LOS DERECHOS DE AUTOR
 * "TAL COMO ESTA" Y CUALQUIER GARANTIA EXPRESA O IMPLICITA, INCLUYENDO PERO NO LIMITADO A,
 * LAS GARANTIAS DE COMERCIALIZACION Y/O PARA UN PROPOSITO PARTICULAR. EN NINGUN CASO
 * EL PROPIETARIOS DE LOS DERECHOS DE AUTOR (COPYRIGTH) O SUS COLABORADORES SERAN
 * RESPONSABLES  POR NINGUN DA�O DIRECTO, INDIRECTO, INCIDENTAL, ESPECIAL, EJEMPLARES O
 * DERIVADOS (INCLUYENDO, PERO NO LIMITADO A LA OBTENCION DE BIENES O SERVICIOS SUSTITUTOS, 
 * LA PERDIDA DE USO, DE DATOS O BENEFICIOS O LA INTERRUPCION DEL NEGOCIO) CAUSADOS COMO
 * EN CUALQUIER TEORIA DE RESPONSABILIDAD, YA SEA POR CONTRATO, RESPONSABILIDAD OBJETIVA 
 * O AGRAVIO (INCLUYENDO NEGLIGENCIA) QUE RESULTE DE CUALQUIER FORMA DEL USO DE ESTE SOFTWARE,
 * AUNQUE SE HAYA ADVERTIDO DE LA POSIBILIDAD DE TALES DA�OS.

 */

package com.spontecorp.losarboles.model;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author Casper
 */
@Entity
@Table(name = "arrendatario")
@NamedQueries({
    @NamedQuery(name = "Arrendatario.findAll", query = "SELECT a FROM Arrendatario a"),
    @NamedQuery(name = "Arrendatario.findById", query = "SELECT a FROM Arrendatario a WHERE a.id = :id"),
    @NamedQuery(name = "Arrendatario.findByNombre", query = "SELECT a FROM Arrendatario a WHERE a.nombre = :nombre"),
    @NamedQuery(name = "Arrendatario.findByApellido", query = "SELECT a FROM Arrendatario a WHERE a.apellido = :apellido"),
    @NamedQuery(name = "Arrendatario.findByCi", query = "SELECT a FROM Arrendatario a WHERE a.ci = :ci"),
    @NamedQuery(name = "Arrendatario.findByDireccion", query = "SELECT a FROM Arrendatario a WHERE a.direccion = :direccion"),
    @NamedQuery(name = "Arrendatario.findByTelefono", query = "SELECT a FROM Arrendatario a WHERE a.telefono = :telefono"),
    @NamedQuery(name = "Arrendatario.findByCelular", query = "SELECT a FROM Arrendatario a WHERE a.celular = :celular"),
    @NamedQuery(name = "Arrendatario.findByTlfTrabajo", query = "SELECT a FROM Arrendatario a WHERE a.tlfTrabajo = :tlfTrabajo"),
    @NamedQuery(name = "Arrendatario.findByEmail", query = "SELECT a FROM Arrendatario a WHERE a.email = :email")})
public class Arrendatario implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "nombre")
    private String nombre;
    @Basic(optional = false)
    @Column(name = "apellido")
    private String apellido;
    @Basic(optional = false)
    @Column(name = "ci")
    private int ci;
    @Basic(optional = false)
    @Column(name = "direccion")
    private String direccion;
    @Basic(optional = false)
    @Column(name = "telefono")
    private String telefono;
    @Basic(optional = false)
    @Column(name = "celular")
    private String celular;
    @Column(name = "tlf_trabajo")
    private String tlfTrabajo;
    @Column(name = "email")
    private String email;
    @Column(name = "status")
    private int status;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "arrendatarioId")
    private Collection<Contrato> contratoCollection;

    public Arrendatario() {
    }

    public Arrendatario(Integer id) {
        this.id = id;
    }

    public Arrendatario(Integer id, String nombre, String apellido, int ci, String direccion, String telefono, String celular, int status) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.ci = ci;
        this.direccion = direccion;
        this.telefono = telefono;
        this.celular = celular;
        this.status = status;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public int getCi() {
        return ci;
    }

    public void setCi(int ci) {
        this.ci = ci;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getTlfTrabajo() {
        return tlfTrabajo;
    }

    public void setTlfTrabajo(String tlfTrabajo) {
        this.tlfTrabajo = tlfTrabajo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public Collection<Contrato> getContratoCollection() {
        return contratoCollection;
    }

    public void setContratoCollection(Collection<Contrato> contratoCollection) {
        this.contratoCollection = contratoCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Arrendatario)) {
            return false;
        }
        Arrendatario other = (Arrendatario) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.losarboles.entity.Arrendatario[ id=" + id + " ]";
    }

}
