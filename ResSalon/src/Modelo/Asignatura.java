/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Modelo;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
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
 * @author ander
 */
@Entity
@Table(name = "asignatura")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Asignatura.findAll", query = "SELECT a FROM Asignatura a")
    , @NamedQuery(name = "Asignatura.findByCodasignatura", query = "SELECT a FROM Asignatura a WHERE a.codasignatura = :codasignatura")
    , @NamedQuery(name = "Asignatura.findByNombreprograma", query = "SELECT a FROM Asignatura a WHERE a.nombreprograma = :nombreprograma")})
public class Asignatura implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codasignatura")
    private Integer codasignatura;
    @Basic(optional = false)
    @Column(name = "nombreprograma")
    private String nombreprograma;
    @JoinColumn(name = "codprograma", referencedColumnName = "codprograma")
    @ManyToOne(optional = false)
    private Programa codprograma;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codasignatura")
    private Collection<Reserva> reservaCollection;

    public Asignatura() {
    }

    public Asignatura(Integer codasignatura) {
        this.codasignatura = codasignatura;
    }

    public Asignatura(Integer codasignatura, String nombreprograma) {
        this.codasignatura = codasignatura;
        this.nombreprograma = nombreprograma;
    }

    public Integer getCodasignatura() {
        return codasignatura;
    }

    public void setCodasignatura(Integer codasignatura) {
        this.codasignatura = codasignatura;
    }

    public String getNombreprograma() {
        return nombreprograma;
    }

    public void setNombreprograma(String nombreprograma) {
        this.nombreprograma = nombreprograma;
    }

    public Programa getCodprograma() {
        return codprograma;
    }

    public void setCodprograma(Programa codprograma) {
        this.codprograma = codprograma;
    }

    @XmlTransient
    public Collection<Reserva> getReservaCollection() {
        return reservaCollection;
    }

    public void setReservaCollection(Collection<Reserva> reservaCollection) {
        this.reservaCollection = reservaCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (codasignatura != null ? codasignatura.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Asignatura)) {
            return false;
        }
        Asignatura other = (Asignatura) object;
        if ((this.codasignatura == null && other.codasignatura != null) || (this.codasignatura != null && !this.codasignatura.equals(other.codasignatura))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Asignatura[ codasignatura=" + codasignatura + " ]";
    }
    
}
