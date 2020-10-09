/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Asignatura;
import Modelo.Programa;
import java.util.ArrayList;
import java.util.Collection;
import Modelo.Reserva;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.Iterator;
import java.util.List;
import javafx.scene.control.ComboBox;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;

/**
 *
 * @author ander
 */
public class ProgramaJpaController implements Serializable {

    public ProgramaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ProgramaJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ResSalonPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Programa programa) throws PreexistingEntityException, Exception {
        if (programa.getAsignaturaCollection() == null) {
            programa.setAsignaturaCollection(new ArrayList<Asignatura>());
        }
        if (programa.getReservaCollection() == null) {
            programa.setReservaCollection(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Asignatura> attachedAsignaturaCollection = new ArrayList<Asignatura>();
            for (Asignatura asignaturaCollectionAsignaturaToAttach : programa.getAsignaturaCollection()) {
                asignaturaCollectionAsignaturaToAttach = em.getReference(asignaturaCollectionAsignaturaToAttach.getClass(), asignaturaCollectionAsignaturaToAttach.getCodasignatura());
                attachedAsignaturaCollection.add(asignaturaCollectionAsignaturaToAttach);
            }
            programa.setAsignaturaCollection(attachedAsignaturaCollection);
            Collection<Reserva> attachedReservaCollection = new ArrayList<Reserva>();
            for (Reserva reservaCollectionReservaToAttach : programa.getReservaCollection()) {
                reservaCollectionReservaToAttach = em.getReference(reservaCollectionReservaToAttach.getClass(), reservaCollectionReservaToAttach.getCodreserva());
                attachedReservaCollection.add(reservaCollectionReservaToAttach);
            }
            programa.setReservaCollection(attachedReservaCollection);
            em.persist(programa);
            for (Asignatura asignaturaCollectionAsignatura : programa.getAsignaturaCollection()) {
                Programa oldCodprogramaOfAsignaturaCollectionAsignatura = asignaturaCollectionAsignatura.getCodprograma();
                asignaturaCollectionAsignatura.setCodprograma(programa);
                asignaturaCollectionAsignatura = em.merge(asignaturaCollectionAsignatura);
                if (oldCodprogramaOfAsignaturaCollectionAsignatura != null) {
                    oldCodprogramaOfAsignaturaCollectionAsignatura.getAsignaturaCollection().remove(asignaturaCollectionAsignatura);
                    oldCodprogramaOfAsignaturaCollectionAsignatura = em.merge(oldCodprogramaOfAsignaturaCollectionAsignatura);
                }
            }
            for (Reserva reservaCollectionReserva : programa.getReservaCollection()) {
                Programa oldCodprogramaOfReservaCollectionReserva = reservaCollectionReserva.getCodprograma();
                reservaCollectionReserva.setCodprograma(programa);
                reservaCollectionReserva = em.merge(reservaCollectionReserva);
                if (oldCodprogramaOfReservaCollectionReserva != null) {
                    oldCodprogramaOfReservaCollectionReserva.getReservaCollection().remove(reservaCollectionReserva);
                    oldCodprogramaOfReservaCollectionReserva = em.merge(oldCodprogramaOfReservaCollectionReserva);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findPrograma(programa.getCodprograma()) != null) {
                throw new PreexistingEntityException("Programa " + programa + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Programa programa) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa persistentPrograma = em.find(Programa.class, programa.getCodprograma());
            Collection<Asignatura> asignaturaCollectionOld = persistentPrograma.getAsignaturaCollection();
            Collection<Asignatura> asignaturaCollectionNew = programa.getAsignaturaCollection();
            Collection<Reserva> reservaCollectionOld = persistentPrograma.getReservaCollection();
            Collection<Reserva> reservaCollectionNew = programa.getReservaCollection();
            List<String> illegalOrphanMessages = null;
            for (Asignatura asignaturaCollectionOldAsignatura : asignaturaCollectionOld) {
                if (!asignaturaCollectionNew.contains(asignaturaCollectionOldAsignatura)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Asignatura " + asignaturaCollectionOldAsignatura + " since its codprograma field is not nullable.");
                }
            }
            for (Reserva reservaCollectionOldReserva : reservaCollectionOld) {
                if (!reservaCollectionNew.contains(reservaCollectionOldReserva)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reserva " + reservaCollectionOldReserva + " since its codprograma field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Asignatura> attachedAsignaturaCollectionNew = new ArrayList<Asignatura>();
            for (Asignatura asignaturaCollectionNewAsignaturaToAttach : asignaturaCollectionNew) {
                asignaturaCollectionNewAsignaturaToAttach = em.getReference(asignaturaCollectionNewAsignaturaToAttach.getClass(), asignaturaCollectionNewAsignaturaToAttach.getCodasignatura());
                attachedAsignaturaCollectionNew.add(asignaturaCollectionNewAsignaturaToAttach);
            }
            asignaturaCollectionNew = attachedAsignaturaCollectionNew;
            programa.setAsignaturaCollection(asignaturaCollectionNew);
            Collection<Reserva> attachedReservaCollectionNew = new ArrayList<Reserva>();
            for (Reserva reservaCollectionNewReservaToAttach : reservaCollectionNew) {
                reservaCollectionNewReservaToAttach = em.getReference(reservaCollectionNewReservaToAttach.getClass(), reservaCollectionNewReservaToAttach.getCodreserva());
                attachedReservaCollectionNew.add(reservaCollectionNewReservaToAttach);
            }
            reservaCollectionNew = attachedReservaCollectionNew;
            programa.setReservaCollection(reservaCollectionNew);
            programa = em.merge(programa);
            for (Asignatura asignaturaCollectionNewAsignatura : asignaturaCollectionNew) {
                if (!asignaturaCollectionOld.contains(asignaturaCollectionNewAsignatura)) {
                    Programa oldCodprogramaOfAsignaturaCollectionNewAsignatura = asignaturaCollectionNewAsignatura.getCodprograma();
                    asignaturaCollectionNewAsignatura.setCodprograma(programa);
                    asignaturaCollectionNewAsignatura = em.merge(asignaturaCollectionNewAsignatura);
                    if (oldCodprogramaOfAsignaturaCollectionNewAsignatura != null && !oldCodprogramaOfAsignaturaCollectionNewAsignatura.equals(programa)) {
                        oldCodprogramaOfAsignaturaCollectionNewAsignatura.getAsignaturaCollection().remove(asignaturaCollectionNewAsignatura);
                        oldCodprogramaOfAsignaturaCollectionNewAsignatura = em.merge(oldCodprogramaOfAsignaturaCollectionNewAsignatura);
                    }
                }
            }
            for (Reserva reservaCollectionNewReserva : reservaCollectionNew) {
                if (!reservaCollectionOld.contains(reservaCollectionNewReserva)) {
                    Programa oldCodprogramaOfReservaCollectionNewReserva = reservaCollectionNewReserva.getCodprograma();
                    reservaCollectionNewReserva.setCodprograma(programa);
                    reservaCollectionNewReserva = em.merge(reservaCollectionNewReserva);
                    if (oldCodprogramaOfReservaCollectionNewReserva != null && !oldCodprogramaOfReservaCollectionNewReserva.equals(programa)) {
                        oldCodprogramaOfReservaCollectionNewReserva.getReservaCollection().remove(reservaCollectionNewReserva);
                        oldCodprogramaOfReservaCollectionNewReserva = em.merge(oldCodprogramaOfReservaCollectionNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = programa.getCodprograma();
                if (findPrograma(id) == null) {
                    throw new NonexistentEntityException("The programa with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws IllegalOrphanException, NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa programa;
            try {
                programa = em.getReference(Programa.class, id);
                programa.getCodprograma();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The programa with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Asignatura> asignaturaCollectionOrphanCheck = programa.getAsignaturaCollection();
            for (Asignatura asignaturaCollectionOrphanCheckAsignatura : asignaturaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Asignatura " + asignaturaCollectionOrphanCheckAsignatura + " in its asignaturaCollection field has a non-nullable codprograma field.");
            }
            Collection<Reserva> reservaCollectionOrphanCheck = programa.getReservaCollection();
            for (Reserva reservaCollectionOrphanCheckReserva : reservaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Programa (" + programa + ") cannot be destroyed since the Reserva " + reservaCollectionOrphanCheckReserva + " in its reservaCollection field has a non-nullable codprograma field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(programa);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Programa> findProgramaEntities() {
        return findProgramaEntities(true, -1, -1);
    }

    public List<Programa> findProgramaEntities(int maxResults, int firstResult) {
        return findProgramaEntities(false, maxResults, firstResult);
    }

    private List<Programa> findProgramaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Programa.class));
            Query q = em.createQuery(cq);
            if (!all) {
                q.setMaxResults(maxResults);
                q.setFirstResult(firstResult);
            }
            return q.getResultList();
        } finally {
            em.close();
        }
    }

    public Programa findPrograma(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Programa.class, id);
        } finally {
            em.close();
        }
    }

    public int getProgramaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Programa> rt = cq.from(Programa.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    public void getProgramaCmb(JComboBox<Programa> cmbPrograma){
         EntityManager em = getEntityManager();
         Iterator  it = em.createQuery("Select p from Programa p").getResultList().iterator();
         Programa P;
        try{
            while(it.hasNext()){
			P =(Programa) it.next();
                        cmbPrograma.addItem(P);
		}
        }catch(Exception e){
            System.out.println("error no se puede listar el comboBox Programa (ProgramaJpaController)"); 
        }

    }
    
}
