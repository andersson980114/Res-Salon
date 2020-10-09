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
import Modelo.Ambiente;
import Modelo.Sede;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.swing.JComboBox;

/**
 *
 * @author ander
 */
public class SedeJpaController implements Serializable {

    public SedeJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public SedeJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ResSalonPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Sede sede) throws PreexistingEntityException, Exception {
        if (sede.getAmbienteCollection() == null) {
            sede.setAmbienteCollection(new ArrayList<Ambiente>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Ambiente> attachedAmbienteCollection = new ArrayList<Ambiente>();
            for (Ambiente ambienteCollectionAmbienteToAttach : sede.getAmbienteCollection()) {
                ambienteCollectionAmbienteToAttach = em.getReference(ambienteCollectionAmbienteToAttach.getClass(), ambienteCollectionAmbienteToAttach.getCodambiente());
                attachedAmbienteCollection.add(ambienteCollectionAmbienteToAttach);
            }
            sede.setAmbienteCollection(attachedAmbienteCollection);
            em.persist(sede);
            for (Ambiente ambienteCollectionAmbiente : sede.getAmbienteCollection()) {
                Sede oldNumsedeOfAmbienteCollectionAmbiente = ambienteCollectionAmbiente.getNumsede();
                ambienteCollectionAmbiente.setNumsede(sede);
                ambienteCollectionAmbiente = em.merge(ambienteCollectionAmbiente);
                if (oldNumsedeOfAmbienteCollectionAmbiente != null) {
                    oldNumsedeOfAmbienteCollectionAmbiente.getAmbienteCollection().remove(ambienteCollectionAmbiente);
                    oldNumsedeOfAmbienteCollectionAmbiente = em.merge(oldNumsedeOfAmbienteCollectionAmbiente);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findSede(sede.getNumsede()) != null) {
                throw new PreexistingEntityException("Sede " + sede + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Sede sede) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Sede persistentSede = em.find(Sede.class, sede.getNumsede());
            Collection<Ambiente> ambienteCollectionOld = persistentSede.getAmbienteCollection();
            Collection<Ambiente> ambienteCollectionNew = sede.getAmbienteCollection();
            List<String> illegalOrphanMessages = null;
            for (Ambiente ambienteCollectionOldAmbiente : ambienteCollectionOld) {
                if (!ambienteCollectionNew.contains(ambienteCollectionOldAmbiente)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Ambiente " + ambienteCollectionOldAmbiente + " since its numsede field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Ambiente> attachedAmbienteCollectionNew = new ArrayList<Ambiente>();
            for (Ambiente ambienteCollectionNewAmbienteToAttach : ambienteCollectionNew) {
                ambienteCollectionNewAmbienteToAttach = em.getReference(ambienteCollectionNewAmbienteToAttach.getClass(), ambienteCollectionNewAmbienteToAttach.getCodambiente());
                attachedAmbienteCollectionNew.add(ambienteCollectionNewAmbienteToAttach);
            }
            ambienteCollectionNew = attachedAmbienteCollectionNew;
            sede.setAmbienteCollection(ambienteCollectionNew);
            sede = em.merge(sede);
            for (Ambiente ambienteCollectionNewAmbiente : ambienteCollectionNew) {
                if (!ambienteCollectionOld.contains(ambienteCollectionNewAmbiente)) {
                    Sede oldNumsedeOfAmbienteCollectionNewAmbiente = ambienteCollectionNewAmbiente.getNumsede();
                    ambienteCollectionNewAmbiente.setNumsede(sede);
                    ambienteCollectionNewAmbiente = em.merge(ambienteCollectionNewAmbiente);
                    if (oldNumsedeOfAmbienteCollectionNewAmbiente != null && !oldNumsedeOfAmbienteCollectionNewAmbiente.equals(sede)) {
                        oldNumsedeOfAmbienteCollectionNewAmbiente.getAmbienteCollection().remove(ambienteCollectionNewAmbiente);
                        oldNumsedeOfAmbienteCollectionNewAmbiente = em.merge(oldNumsedeOfAmbienteCollectionNewAmbiente);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = sede.getNumsede();
                if (findSede(id) == null) {
                    throw new NonexistentEntityException("The sede with id " + id + " no longer exists.");
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
            Sede sede;
            try {
                sede = em.getReference(Sede.class, id);
                sede.getNumsede();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The sede with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Ambiente> ambienteCollectionOrphanCheck = sede.getAmbienteCollection();
            for (Ambiente ambienteCollectionOrphanCheckAmbiente : ambienteCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Sede (" + sede + ") cannot be destroyed since the Ambiente " + ambienteCollectionOrphanCheckAmbiente + " in its ambienteCollection field has a non-nullable numsede field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(sede);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Sede> findSedeEntities() {
        return findSedeEntities(true, -1, -1);
    }

    public List<Sede> findSedeEntities(int maxResults, int firstResult) {
        return findSedeEntities(false, maxResults, firstResult);
    }

    private List<Sede> findSedeEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Sede.class));
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

    public Sede findSede(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Sede.class, id);
        } finally {
            em.close();
        }
    }

    public int getSedeCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Sede> rt = cq.from(Sede.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //agregado
    public void sedeCMB(JComboBox<Sede> cmbSede){
        EntityManager em = getEntityManager();
        Iterator it = em.createQuery("SELECT s FROM Sede s").getResultList().iterator();
        Sede s;
        try{
            while(it.hasNext()){
                s = (Sede)it.next();
                cmbSede.addItem(s);
            }
        }catch(Exception e){
            System.out.println("no se pudo listar las sedes en el combo box");
        }
    }
    
}
