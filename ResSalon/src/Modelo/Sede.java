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
@Table(name = "sede")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Sede.findAll", query = "SELECT s FROM Sede s")
    , @NamedQuery(name = "Sede.findByNumsede", query = "SELECT s FROM Sede s WHERE s.numsede = :numsede")
    , @NamedQuery(name = "Sede.findByNombresede", query = "SELECT s FROM Sede s WHERE s.nombresede = :nombresede")
    , @NamedQuery(name = "Sede.findByDirsede", query = "SELECT s FROM Sede s WHERE s.dirsede = :dirsede")})
public class Sede implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @Basic(optional = false)
    @Column(name = "numsede")
    private Integer numsede;
    @Basic(optional = false)
    @Column(name = "nombresede")
    private String nombresede;
    @Basic(optional = false)
    @Column(name = "dirsede")
    private String dirsede;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "numsede")
    private Collection<Ambiente> ambienteCollection;

    public Sede() {
    }

    public Sede(Integer numsede) {
        this.numsede = numsede;
    }

    public Sede(Integer numsede, String nombresede, String dirsede) {
        this.numsede = numsede;
        this.nombresede = nombresede;
        this.dirsede = dirsede;
    }

    public Integer getNumsede() {
        return numsede;
    }

    public void setNumsede(Integer numsede) {
        this.numsede = numsede;
    }

    public String getNombresede() {
        return nombresede;
    }

    public void setNombresede(String nombresede) {
        this.nombresede = nombresede;
    }

    public String getDirsede() {
        return dirsede;
    }

    public void setDirsede(String dirsede) {
        this.dirsede = dirsede;
    }

    @XmlTransient
    public Collection<Ambiente> getAmbienteCollection() {
        return ambienteCollection;
    }

    public void setAmbienteCollection(Collection<Ambiente> ambienteCollection) {
        this.ambienteCollection = ambienteCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (numsede != null ? numsede.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Sede)) {
            return false;
        }
        Sede other = (Sede) object;
        if ((this.numsede == null && other.numsede != null) || (this.numsede != null && !this.numsede.equals(other.numsede))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return   numsede.toString() ;
    }
    
}
