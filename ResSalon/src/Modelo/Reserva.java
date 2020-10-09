/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author ander
 */
@Entity
@Table(name = "reserva")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Reserva.findAll", query = "SELECT r FROM Reserva r")
    , @NamedQuery(name = "Reserva.findByCodreserva", query = "SELECT r FROM Reserva r WHERE r.codreserva = :codreserva")
    , @NamedQuery(name = "Reserva.findByFechainicio", query = "SELECT r FROM Reserva r WHERE r.fechainicio = :fechainicio")
    , @NamedQuery(name = "Reserva.findByFechafin", query = "SELECT r FROM Reserva r WHERE r.fechafin = :fechafin")
    , @NamedQuery(name = "Reserva.findByHorainicio", query = "SELECT r FROM Reserva r WHERE r.horainicio = :horainicio")
    , @NamedQuery(name = "Reserva.findByHorafin", query = "SELECT r FROM Reserva r WHERE r.horafin = :horafin")
    , @NamedQuery(name = "Reserva.findByObservaciones", query = "SELECT r FROM Reserva r WHERE r.observaciones = :observaciones")})
public class Reserva implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codreserva")
    private Integer codreserva;
    @Basic(optional = false)
    @Column(name = "fechainicio")
    @Temporal(TemporalType.DATE)
    private Date fechainicio;
    @Basic(optional = false)
    @Column(name = "fechafin")
    @Temporal(TemporalType.DATE)
    private Date fechafin;
    @Basic(optional = false)
    @Column(name = "horainicio")
    private String horainicio;
    @Basic(optional = false)
    @Column(name = "horafin")
    private String horafin;
    @Column(name = "observaciones")
    private String observaciones;
    @JoinColumn(name = "codambiente", referencedColumnName = "codambiente")
    @ManyToOne(optional = false)
    private Ambiente codambiente;
    @JoinColumn(name = "codasignatura", referencedColumnName = "codasignatura")
    @ManyToOne(optional = false)
    private Asignatura codasignatura;
    @JoinColumn(name = "codprograma", referencedColumnName = "codprograma")
    @ManyToOne(optional = false)
    private Programa codprograma;
    @JoinColumn(name = "codpersonal", referencedColumnName = "codpersonal")
    @ManyToOne(optional = false)
    private Usuario codpersonal;

    public Reserva() {
    }

    public Reserva(Integer codreserva) {
        this.codreserva = codreserva;
    }

    public Reserva(Integer codreserva, Date fechainicio, Date fechafin, String horainicio, String horafin) {
        this.codreserva = codreserva;
        this.fechainicio = fechainicio;
        this.fechafin = fechafin;
        this.horainicio = horainicio;
        this.horafin = horafin;
    }

    public Integer getCodreserva() {
        return codreserva;
    }

    public void setCodreserva(Integer codreserva) {
        this.codreserva = codreserva;
    }

    public Date getFechainicio() {
        return fechainicio;
    }

    public void setFechainicio(Date fechainicio) {
        this.fechainicio = fechainicio;
    }

    public Date getFechafin() {
        return fechafin;
    }

    public void setFechafin(Date fechafin) {
        this.fechafin = fechafin;
    }

    public String getHorainicio() {
        return horainicio;
    }

    public void setHorainicio(String horainicio) {
        this.horainicio = horainicio;
    }

    public String getHorafin() {
        return horafin;
    }

    public void setHorafin(String horafin) {
        this.horafin = horafin;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    public Ambiente getCodambiente() {
        return codambiente;
    }

    public void setCodambiente(Ambiente codambiente) {
        this.codambiente = codambiente;
    }

    public Asignatura getCodasignatura() {
        return codasignatura;
    }

    public void setCodasignatura(Asignatura codasignatura) {
        this.codasignatura = codasignatura;
    }

    public Programa getCodprograma() {
        return codprograma;
    }

    public void setCodprograma(Programa codprograma) {
        this.codprograma = codprograma;
    }

    public Usuario getCodpersonal() {
        return codpersonal;
    }

    public void setCodpersonal(Usuario codpersonal) {
        this.codpersonal = codpersonal;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codreserva != null ? codreserva.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Reserva)) {
            return false;
        }
        Reserva other = (Reserva) object;
        if ((this.codreserva == null && other.codreserva != null) || (this.codreserva != null && !this.codreserva.equals(other.codreserva))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Reserva[ codreserva=" + codreserva + " ]";
    }
    
}
