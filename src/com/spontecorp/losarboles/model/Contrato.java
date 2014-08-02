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
import java.util.Date;
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
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Casper
 */
@Entity
@Table(name = "contrato")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Contrato.findAll", query = "SELECT c FROM Contrato c"),
    @NamedQuery(name = "Contrato.findById", query = "SELECT c FROM Contrato c WHERE c.id = :id"),
    @NamedQuery(name = "Contrato.findByFechaInicio", query = "SELECT c FROM Contrato c WHERE c.fechaInicio = :fechaInicio"),
    @NamedQuery(name = "Contrato.findByHoraInicio", query = "SELECT c FROM Contrato c WHERE c.horaInicio = :horaInicio"),
    @NamedQuery(name = "Contrato.findByFechaFin", query = "SELECT c FROM Contrato c WHERE c.fechaFin = :fechaFin"),
    @NamedQuery(name = "Contrato.findByHoraFin", query = "SELECT c FROM Contrato c WHERE c.horaFin = :horaFin"),
    @NamedQuery(name = "Contrato.findByReferencia", query = "SELECT c FROM Contrato c WHERE c.referencia = :referencia"),
    @NamedQuery(name = "Contrato.findByRenovable", query = "SELECT c FROM Contrato c WHERE c.renovable = :renovable"),
    @NamedQuery(name = "Contrato.findByCanon", query = "SELECT c FROM Contrato c WHERE c.canon = :canon")})
public class Contrato implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Basic(optional = false)
    @Column(name = "fecha_inicio")
    @Temporal(TemporalType.DATE)
    private Date fechaInicio;
    @Column(name = "hora_inicio")
    @Temporal(TemporalType.TIME)
    private Date horaInicio;
    @Basic(optional = false)
    @Column(name = "fecha_fin")
    @Temporal(TemporalType.DATE)
    private Date fechaFin;
    @Column(name = "hora_fin")
    @Temporal(TemporalType.TIME)
    private Date horaFin;
    @Column(name = "referencia")
    private String referencia;
    @Basic(optional = false)
    @Column(name = "renovable")
    private int renovable;
    @Basic(optional = false)
    @Column(name = "canon")
    private double canon;
    @JoinColumn(name = "local_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Local localId;
    @JoinColumn(name = "arrendatario_id", referencedColumnName = "id")
    @ManyToOne(optional = false)
    private Arrendatario arrendatarioId;

    public Contrato() {
    }

    public Contrato(Integer id) {
        this.id = id;
    }

    public Contrato(Integer id, Date fechaInicio, Date fechaFin, int renovable, double canon) {
        this.id = id;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.renovable = renovable;
        this.canon = canon;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(Date fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public Date getHoraInicio() {
        return horaInicio;
    }

    public void setHoraInicio(Date horaInicio) {
        this.horaInicio = horaInicio;
    }

    public Date getFechaFin() {
        return fechaFin;
    }

    public void setFechaFin(Date fechaFin) {
        this.fechaFin = fechaFin;
    }

    public Date getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Date horaFin) {
        this.horaFin = horaFin;
    }

    public String getReferencia() {
        return referencia;
    }

    public void setReferencia(String referencia) {
        this.referencia = referencia;
    }

    public int getRenovable() {
        return renovable;
    }

    public void setRenovable(int renovable) {
        this.renovable = renovable;
    }

    public double getCanon() {
        return canon;
    }

    public void setCanon(double canon) {
        this.canon = canon;
    }

    public Local getLocalId() {
        return localId;
    }

    public void setLocalId(Local localId) {
        this.localId = localId;
    }

    public Arrendatario getArrendatarioId() {
        return arrendatarioId;
    }

    public void setArrendatarioId(Arrendatario arrendatarioId) {
        this.arrendatarioId = arrendatarioId;
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
        if (!(object instanceof Contrato)) {
            return false;
        }
        Contrato other = (Contrato) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "org.losarboles.entity.Contrato[ id=" + id + " ]";
    }

}
