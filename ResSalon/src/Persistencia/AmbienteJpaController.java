/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Persistencia;

import Modelo.Ambiente;
import java.io.Serializable;
import javax.persistence.Query;
import javax.persistence.EntityNotFoundException;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import Modelo.Sede;
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
public class AmbienteJpaController implements Serializable {

    public AmbienteJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public AmbienteJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ResSalonPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Ambiente ambiente) throws PreexistingEntityException, Exception {
        if (ambiente.getReservaCollection() == null) {
            ambiente.setReservaCollection(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede numsede = ambiente.getNumsede();
            if (numsede != null) {
                numsede = em.getReference(numsede.getClass(), numsede.getNumsede());
                ambiente.setNumsede(numsede);
            }
            Collection<Reserva> attachedReservaCollection = new ArrayList<Reserva>();
            for (Reserva reservaCollectionReservaToAttach : ambiente.getReservaCollection()) {
                reservaCollectionReservaToAttach = em.getReference(reservaCollectionReservaToAttach.getClass(), reservaCollectionReservaToAttach.getCodreserva());
                attachedReservaCollection.add(reservaCollectionReservaToAttach);
            }
            ambiente.setReservaCollection(attachedReservaCollection);
            em.persist(ambiente);
            if (numsede != null) {
                numsede.getAmbienteCollection().add(ambiente);
                numsede = em.merge(numsede);
            }
            for (Reserva reservaCollectionReserva : ambiente.getReservaCollection()) {
                Ambiente oldCodambienteOfReservaCollectionReserva = reservaCollectionReserva.getCodambiente();
                reservaCollectionReserva.setCodambiente(ambiente);
                reservaCollectionReserva = em.merge(reservaCollectionReserva);
                if (oldCodambienteOfReservaCollectionReserva != null) {
                    oldCodambienteOfReservaCollectionReserva.getReservaCollection().remove(reservaCollectionReserva);
                    oldCodambienteOfReservaCollectionReserva = em.merge(oldCodambienteOfReservaCollectionReserva);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findAmbiente(ambiente.getCodambiente()) != null) {
                throw new PreexistingEntityException("Ambiente " + ambiente + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Ambiente ambiente) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ambiente persistentAmbiente = em.find(Ambiente.class, ambiente.getCodambiente());
            Sede numsedeOld = persistentAmbiente.getNumsede();
            Sede numsedeNew = ambiente.getNumsede();
            Collection<Reserva> reservaCollectionOld = persistentAmbiente.getReservaCollection();
            Collection<Reserva> reservaCollectionNew = ambiente.getReservaCollection();
            List<String> illegalOrphanMessages = null;
            for (Reserva reservaCollectionOldReserva : reservaCollectionOld) {
                if (!reservaCollectionNew.contains(reservaCollectionOldReserva)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reserva " + reservaCollectionOldReserva + " since its codambiente field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            if (numsedeNew != null) {
                numsedeNew = em.getReference(numsedeNew.getClass(), numsedeNew.getNumsede());
                ambiente.setNumsede(numsedeNew);
            }
            Collection<Reserva> attachedReservaCollectionNew = new ArrayList<Reserva>();
            for (Reserva reservaCollectionNewReservaToAttach : reservaCollectionNew) {
                reservaCollectionNewReservaToAttach = em.getReference(reservaCollectionNewReservaToAttach.getClass(), reservaCollectionNewReservaToAttach.getCodreserva());
                attachedReservaCollectionNew.add(reservaCollectionNewReservaToAttach);
            }
            reservaCollectionNew = attachedReservaCollectionNew;
            ambiente.setReservaCollection(reservaCollectionNew);
            ambiente = em.merge(ambiente);
            if (numsedeOld != null && !numsedeOld.equals(numsedeNew)) {
                numsedeOld.getAmbienteCollection().remove(ambiente);
                numsedeOld = em.merge(numsedeOld);
            }
            if (numsedeNew != null && !numsedeNew.equals(numsedeOld)) {
                numsedeNew.getAmbienteCollection().add(ambiente);
                numsedeNew = em.merge(numsedeNew);
            }
            for (Reserva reservaCollectionNewReserva : reservaCollectionNew) {
                if (!reservaCollectionOld.contains(reservaCollectionNewReserva)) {
                    Ambiente oldCodambienteOfReservaCollectionNewReserva = reservaCollectionNewReserva.getCodambiente();
                    reservaCollectionNewReserva.setCodambiente(ambiente);
                    reservaCollectionNewReserva = em.merge(reservaCollectionNewReserva);
                    if (oldCodambienteOfReservaCollectionNewReserva != null && !oldCodambienteOfReservaCollectionNewReserva.equals(ambiente)) {
                        oldCodambienteOfReservaCollectionNewReserva.getReservaCollection().remove(reservaCollectionNewReserva);
                        oldCodambienteOfReservaCollectionNewReserva = em.merge(oldCodambienteOfReservaCollectionNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = ambiente.getCodambiente();
                if (findAmbiente(id) == null) {
                    throw new NonexistentEntityException("The ambiente with id " + id + " no longer exists.");
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
            Ambiente ambiente;
            try {
                ambiente = em.getReference(Ambiente.class, id);
                ambiente.getCodambiente();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The ambiente with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reserva> reservaCollectionOrphanCheck = ambiente.getReservaCollection();
            for (Reserva reservaCollectionOrphanCheckReserva : reservaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Ambiente (" + ambiente + ") cannot be destroyed since the Reserva " + reservaCollectionOrphanCheckReserva + " in its reservaCollection field has a non-nullable codambiente field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Sede numsede = ambiente.getNumsede();
            if (numsede != null) {
                numsede.getAmbienteCollection().remove(ambiente);
                numsede = em.merge(numsede);
            }
            em.remove(ambiente);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Ambiente> findAmbienteEntities() {
        return findAmbienteEntities(true, -1, -1);
    }

    public List<Ambiente> findAmbienteEntities(int maxResults, int firstResult) {
        return findAmbienteEntities(false, maxResults, firstResult);
    }

    private List<Ambiente> findAmbienteEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Ambiente.class));
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

    public Ambiente findAmbiente(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Ambiente.class, id);
        } finally {
            em.close();
        }
    }

    public int getAmbienteCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Ambiente> rt = cq.from(Ambiente.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
