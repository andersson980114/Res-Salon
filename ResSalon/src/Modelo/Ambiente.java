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
@Table(name = "ambiente")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Ambiente.findAll", query = "SELECT a FROM Ambiente a")
    , @NamedQuery(name = "Ambiente.findByCodambiente", query = "SELECT a FROM Ambiente a WHERE a.codambiente = :codambiente")
    , @NamedQuery(name = "Ambiente.findByNombreambiente", query = "SELECT a FROM Ambiente a WHERE a.nombreambiente = :nombreambiente")
    , @NamedQuery(name = "Ambiente.findByTipoambiente", query = "SELECT a FROM Ambiente a WHERE a.tipoambiente = :tipoambiente")
    , @NamedQuery(name = "Ambiente.findByCapacidadambiente", query = "SELECT a FROM Ambiente a WHERE a.capacidadambiente = :capacidadambiente")
    , @NamedQuery(name = "Ambiente.findByContenido", query = "SELECT a FROM Ambiente a WHERE a.contenido = :contenido")})
public class Ambiente implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "codambiente")
    private Integer codambiente;
    @Basic(optional = false)
    @Column(name = "nombreambiente")
    private String nombreambiente;
    @Basic(optional = false)
    @Column(name = "tipoambiente")
    private String tipoambiente;
    @Basic(optional = false)
    @Column(name = "capacidadambiente")
    private int capacidadambiente;
    @Basic(optional = false)
    @Column(name = "contenido")
    private String contenido;
    @JoinColumn(name = "numsede", referencedColumnName = "numsede")
    @ManyToOne(optional = false)
    private Sede numsede;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codambiente")
    private Collection<Reserva> reservaCollection;

    public Ambiente() {
    }

    public Ambiente(Integer codambiente) {
        this.codambiente = codambiente;
    }

    public Ambiente(Integer codambiente, String nombreambiente, String tipoambiente, int capacidadambiente, String contenido) {
        this.codambiente = codambiente;
        this.nombreambiente = nombreambiente;
        this.tipoambiente = tipoambiente;
        this.capacidadambiente = capacidadambiente;
        this.contenido = contenido;
    }

    public Integer getCodambiente() {
        return codambiente;
    }

    public void setCodambiente(Integer codambiente) {
        this.codambiente = codambiente;
    }

    public String getNombreambiente() {
        return nombreambiente;
    }

    public void setNombreambiente(String nombreambiente) {
        this.nombreambiente = nombreambiente;
    }

    public String getTipoambiente() {
        return tipoambiente;
    }

    public void setTipoambiente(String tipoambiente) {
        this.tipoambiente = tipoambiente;
    }

    public int getCapacidadambiente() {
        return capacidadambiente;
    }

    public void setCapacidadambiente(int capacidadambiente) {
        this.capacidadambiente = capacidadambiente;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public Sede getNumsede() {
        return numsede;
    }

    public void setNumsede(Sede numsede) {
        this.numsede = numsede;
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
        hash += (codambiente != null ? codambiente.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Ambiente)) {
            return false;
        }
        Ambiente other = (Ambiente) object;
        if ((this.codambiente == null && other.codambiente != null) || (this.codambiente != null && !this.codambiente.equals(other.codambiente))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "Modelo.Ambiente[ codambiente=" + codambiente + " ]";
    }
    
    
}
