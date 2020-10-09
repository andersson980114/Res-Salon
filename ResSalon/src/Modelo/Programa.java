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
@Table(name = "programa")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Programa.findAll", query = "SELECT p FROM Programa p")
    , @NamedQuery(name = "Programa.findByCodprograma", query = "SELECT p FROM Programa p WHERE p.codprograma = :codprograma")
    , @NamedQuery(name = "Programa.findByNombreprograma", query = "SELECT p FROM Programa p WHERE p.nombreprograma = :nombreprograma")
    , @NamedQuery(name = "Programa.findByJornada", query = "SELECT p FROM Programa p WHERE p.jornada = :jornada")})
public class Programa implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codprograma")
    private Integer codprograma;
    @Basic(optional = false)
    @Column(name = "nombreprograma")
    private String nombreprograma;
    @Basic(optional = false)
    @Column(name = "jornada")
    private int jornada;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprograma")
    private Collection<Asignatura> asignaturaCollection;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codprograma")
    private Collection<Reserva> reservaCollection;

    public Programa() {
    }

    public Programa(Integer codprograma) {
        this.codprograma = codprograma;
    }

    public Programa(Integer codprograma, String nombreprograma, int jornada) {
        this.codprograma = codprograma;
        this.nombreprograma = nombreprograma;
        this.jornada = jornada;
    }

    public Integer getCodprograma() {
        return codprograma;
    }

    public void setCodprograma(Integer codprograma) {
        this.codprograma = codprograma;
    }

    public String getNombreprograma() {
        return nombreprograma;
    }

    public void setNombreprograma(String nombreprograma) {
        this.nombreprograma = nombreprograma;
    }

    public int getJornada() {
        return jornada;
    }

    public void setJornada(int jornada) {
        this.jornada = jornada;
    }

    @XmlTransient
    public Collection<Asignatura> getAsignaturaCollection() {
        return asignaturaCollection;
    }

    public void setAsignaturaCollection(Collection<Asignatura> asignaturaCollection) {
        this.asignaturaCollection = asignaturaCollection;
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
        hash += (codprograma != null ? codprograma.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Programa)) {
            return false;
        }
        Programa other = (Programa) object;
        if ((this.codprograma == null && other.codprograma != null) || (this.codprograma != null && !this.codprograma.equals(other.codprograma))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return  codprograma.toString();
    }
    
}
