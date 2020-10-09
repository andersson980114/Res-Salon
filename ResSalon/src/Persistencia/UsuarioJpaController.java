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
import Modelo.Reserva;
import Modelo.Usuario;
import Persistencia.exceptions.IllegalOrphanException;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.NoResultException;
import javax.persistence.Persistence;

/**
 *
 * @author ander
 */
public class UsuarioJpaController implements Serializable {

    public UsuarioJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public UsuarioJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ResSalonPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Usuario usuario) throws PreexistingEntityException, Exception {
        if (usuario.getReservaCollection() == null) {
            usuario.setReservaCollection(new ArrayList<Reserva>());
        }
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Collection<Reserva> attachedReservaCollection = new ArrayList<Reserva>();
            for (Reserva reservaCollectionReservaToAttach : usuario.getReservaCollection()) {
                reservaCollectionReservaToAttach = em.getReference(reservaCollectionReservaToAttach.getClass(), reservaCollectionReservaToAttach.getCodreserva());
                attachedReservaCollection.add(reservaCollectionReservaToAttach);
            }
            usuario.setReservaCollection(attachedReservaCollection);
            em.persist(usuario);
            for (Reserva reservaCollectionReserva : usuario.getReservaCollection()) {
                Usuario oldCodpersonalOfReservaCollectionReserva = reservaCollectionReserva.getCodpersonal();
                reservaCollectionReserva.setCodpersonal(usuario);
                reservaCollectionReserva = em.merge(reservaCollectionReserva);
                if (oldCodpersonalOfReservaCollectionReserva != null) {
                    oldCodpersonalOfReservaCollectionReserva.getReservaCollection().remove(reservaCollectionReserva);
                    oldCodpersonalOfReservaCollectionReserva = em.merge(oldCodpersonalOfReservaCollectionReserva);
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findUsuario(usuario.getCodpersonal()) != null) {
                throw new PreexistingEntityException("Usuario " + usuario + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Usuario usuario) throws IllegalOrphanException, NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Usuario persistentUsuario = em.find(Usuario.class, usuario.getCodpersonal());
            Collection<Reserva> reservaCollectionOld = persistentUsuario.getReservaCollection();
            Collection<Reserva> reservaCollectionNew = usuario.getReservaCollection();
            List<String> illegalOrphanMessages = null;
            for (Reserva reservaCollectionOldReserva : reservaCollectionOld) {
                if (!reservaCollectionNew.contains(reservaCollectionOldReserva)) {
                    if (illegalOrphanMessages == null) {
                        illegalOrphanMessages = new ArrayList<String>();
                    }
                    illegalOrphanMessages.add("You must retain Reserva " + reservaCollectionOldReserva + " since its codpersonal field is not nullable.");
                }
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            Collection<Reserva> attachedReservaCollectionNew = new ArrayList<Reserva>();
            for (Reserva reservaCollectionNewReservaToAttach : reservaCollectionNew) {
                reservaCollectionNewReservaToAttach = em.getReference(reservaCollectionNewReservaToAttach.getClass(), reservaCollectionNewReservaToAttach.getCodreserva());
                attachedReservaCollectionNew.add(reservaCollectionNewReservaToAttach);
            }
            reservaCollectionNew = attachedReservaCollectionNew;
            usuario.setReservaCollection(reservaCollectionNew);
            usuario = em.merge(usuario);
            for (Reserva reservaCollectionNewReserva : reservaCollectionNew) {
                if (!reservaCollectionOld.contains(reservaCollectionNewReserva)) {
                    Usuario oldCodpersonalOfReservaCollectionNewReserva = reservaCollectionNewReserva.getCodpersonal();
                    reservaCollectionNewReserva.setCodpersonal(usuario);
                    reservaCollectionNewReserva = em.merge(reservaCollectionNewReserva);
                    if (oldCodpersonalOfReservaCollectionNewReserva != null && !oldCodpersonalOfReservaCollectionNewReserva.equals(usuario)) {
                        oldCodpersonalOfReservaCollectionNewReserva.getReservaCollection().remove(reservaCollectionNewReserva);
                        oldCodpersonalOfReservaCollectionNewReserva = em.merge(oldCodpersonalOfReservaCollectionNewReserva);
                    }
                }
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = usuario.getCodpersonal();
                if (findUsuario(id) == null) {
                    throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.");
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
            Usuario usuario;
            try {
                usuario = em.getReference(Usuario.class, id);
                usuario.getCodpersonal();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The usuario with id " + id + " no longer exists.", enfe);
            }
            List<String> illegalOrphanMessages = null;
            Collection<Reserva> reservaCollectionOrphanCheck = usuario.getReservaCollection();
            for (Reserva reservaCollectionOrphanCheckReserva : reservaCollectionOrphanCheck) {
                if (illegalOrphanMessages == null) {
                    illegalOrphanMessages = new ArrayList<String>();
                }
                illegalOrphanMessages.add("This Usuario (" + usuario + ") cannot be destroyed since the Reserva " + reservaCollectionOrphanCheckReserva + " in its reservaCollection field has a non-nullable codpersonal field.");
            }
            if (illegalOrphanMessages != null) {
                throw new IllegalOrphanException(illegalOrphanMessages);
            }
            em.remove(usuario);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Usuario> findUsuarioEntities() {
        return findUsuarioEntities(true, -1, -1);
    }

    public List<Usuario> findUsuarioEntities(int maxResults, int firstResult) {
        return findUsuarioEntities(false, maxResults, firstResult);
    }

    private List<Usuario> findUsuarioEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Usuario.class));
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

    public Usuario findUsuario(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Usuario.class, id);
        } finally {
            em.close();
        }
    }

    public int getUsuarioCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Usuario> rt = cq.from(Usuario.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
    //agregado
    public Usuario findUsuarioCod (Integer codPersonal){
        //SQL select * from usuario u where u.documento = 122333
        String consulta = "select u from Usuario u where u.codPersonal = "+codPersonal;
        try{
            Query query = getEntityManager().createQuery(consulta);
            //getResutList (retornar una lista de registros)
            //getSingleResult (retornar un solo registro)
            return (Usuario) query.getSingleResult();    
        }catch(NoResultException e){
            return null;
        }
    }
    
    
    public int genCod(){
        try{
            String consulta = "select Max(u.codpersonal) from Usuario u";
            Query query = getEntityManager().createNamedQuery(consulta);
            return ((Long) query.getSingleResult()).intValue();
        }catch(NoResultException e){
            return 0;
        }
    }
    
    public List<Usuario> findxTipoUsuario (String tipousuario){
        try{
            //select * from usuario where tipousuario ilike '%tipousuario%';
            String consulta = "select u from Usuario u where u.tipousuario ilike '%"+tipousuario+"%'";
            Query query = getEntityManager().createNamedQuery(consulta);
            return (List<Usuario>) query.getResultList();
        }catch(NoResultException e){
            return null;
        }
    }
    
}