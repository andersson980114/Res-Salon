/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Asignatura;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Programa;
import Modelo.Reserva;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ander
 */
public class AsignaturaJpaController implements Serializable {

    public AsignaturaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AsignaturaJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ResSalonPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Asignatura asignatura) throws PreexistingEntityException, Exception {
        if (asignatura.getReservaCollection() == null) {
            asignatura.setReservaCollection(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Programa codprograma = asignatura.getCodprograma();
            if (codprograma != null) {
                codprograma = em.getReference(codprograma.getClass(), codprograma.getCodprograma());
                asignatura.setCodprograma(codprograma);
            }
            Collection<Reserva> attachedReservaCollection = new ArrayList<Reserva>();
            for (Reserva reservaCollectionReservaToAttach : asignatura.getReservaCollection()) {
                reservaCollectionReservaToAttach = em.getReference(reservaCollectionReservaToAttach.getClass(), reservaCollectionReservaToAttach.getCodreserva());
                attachedReservaCollection.add(reservaCollectionReservaToAttach);
            }
            asignatura.setReservaCollection(attachedReservaCollection);
            em.persist(asignatura);
            if (codprograma != null) {
                codprograma.getAsignaturaCollection().add(asignatura);
                codprograma = em.merge(codprograma);
            }
            for (Reserva reservaCollectionReserva : asignatura.getReservaCollection()) {
                Asignatura oldCodasignaturaOfReservaCollectionReserva = reservaCollectionReserva.getCodasignatura();
                reservaCollectionReserva.setCodasignatura(asignatura);
                reservaCollectionReserva = em.merge(reservaCollectionReserva);
                if (oldCodasignaturaOfReservaCollectionReserva != null) {
                    oldCodasignaturaOfReservaCollectionReserva.getReservaCollection().remove(reservaCollectionReserva);
                    oldCodasignaturaOfReservaCollectionReserva = em.merge(oldCodasignaturaOfReservaCollectionReserva);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAsignatura(asignatura.getCodasignatura()) != null) {
                throw new PreexistingEntityException("Asignatura " + asignatura + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Asignatura asignatura) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Asignatura persistentAsignatura = em.find(Asignatura.class, asignatura.getCodasignatura());
            Programa codprogramaOld = persistentAsignatura.getCodprograma();
            Programa codprogramaNew = asignatura.getCodprograma();
            Collection<Reserva> reservaCollectionOld = persistentAsignatura.getReservaCollection();
            Collection<Reserva> reservaCollectionNew = asignatura.getReservaCollection();
            List<String> illegalOrphanMessages = null;
            for (Reserva reservaCollectionOldReserva : reservaCollectionOld) {
                if (!reservaCollectionNew.contains(reservaCollectionOldReserva)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reserva " + reservaCollectionOldReserva + " since its codasignatura field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (codprogramaNew != null) {
                codprogramaNew = em.getReference(codprogramaNew.getClass(), codprogramaNew.getCodprograma());
                asignatura.setCodprograma(codprogramaNew);
            }
            Collection<Reserva> attachedReservaCollectionNew = new ArrayList<Reserva>();
            for (Reserva reservaCollectionNewReservaToAttach : reservaCollectionNew) {
                reservaCollectionNewReservaToAttach = em.getReference(reservaCollectionNewReservaToAttach.getClass(), reservaCollectionNewReservaToAttach.getCodreserva());
                attachedReservaCollectionNew.add(reservaCollectionNewReservaToAttach);
            }
            reservaCollectionNew = attachedReservaCollectionNew;
            asignatura.setReservaCollection(reservaCollectionNew);
            asignatura = em.merge(asignatura);
            if (codprogramaOld != null && !codprogramaOld.equals(codprogramaNew)) {
                codprogramaOld.getAsignaturaCollection().remove(asignatura);
                codprogramaOld = em.merge(codprogramaOld);
            }
            if (codprogramaNew != null && !codprogramaNew.equals(codprogramaOld)) {
                codprogramaNew.getAsignaturaCollection().add(asignatura);
                codprogramaNew = em.merge(codprogramaNew);
            }
            for (Reserva reservaCollectionNewReserva : reservaCollectionNew) {
                if (!reservaCollectionOld.contains(reservaCollectionNewReserva)) {
                    Asignatura oldCodasignaturaOfReservaCollectionNewReserva = reservaCollectionNewReserva.getCodasignatura();
                    reservaCollectionNewReserva.setCodasignatura(asignatura);
                    reservaCollectionNewReserva = em.merge(reservaCollectionNewReserva);
                    if (oldCodasignaturaOfReservaCollectionNewReserva != null && !oldCodasignaturaOfReservaCollectionNewReserva.equals(asignatura)) {
                        oldCodasignaturaOfReservaCollectionNewReserva.getReservaCollection().remove(reservaCollectionNewReserva);
                        oldCodasignaturaOfReservaCollectionNewReserva = em.merge(oldCodasignaturaOfReservaCollectionNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = asignatura.getCodasignatura();
                if (findAsignatura(id) == null) {
                    throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.");
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
            Asignatura asignatura;
            try {
                asignatura = em.getReference(Asignatura.class, id);
                asignatura.getCodasignatura();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The asignatura with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reserva> reservaCollectionOrphanCheck = asignatura.getReservaCollection();
            for (Reserva reservaCollectionOrphanCheckReserva : reservaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Asignatura (" + asignatura + ") cannot be destroyed since the Reserva " + reservaCollectionOrphanCheckReserva + " in its reservaCollection field has a non-nullable codasignatura field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Programa codprograma = asignatura.getCodprograma();
            if (codprograma != null) {
                codprograma.getAsignaturaCollection().remove(asignatura);
                codprograma = em.merge(codprograma);
            }
            em.remove(asignatura);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Asignatura> findAsignaturaEntities() {
        return findAsignaturaEntities(true, -1, -1);
    }

    public List<Asignatura> findAsignaturaEntities(int maxResults, int firstResult) {
        return findAsignaturaEntities(false, maxResults, firstResult);
    }

    private List<Asignatura> findAsignaturaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Asignatura.class));
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

    public Asignatura findAsignatura(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Asignatura.class, id);
        } finally {
            em.close();
        }
    }

    public int getAsignaturaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Asignatura> rt = cq.from(Asignatura.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
