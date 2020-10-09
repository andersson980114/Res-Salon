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
import Modelo.Asignatura;
import Modelo.Programa;
import Modelo.Reserva;
import Modelo.Usuario;
import Persistencia.exceptions.NonexistentEntityException;
import Persistencia.exceptions.PreexistingEntityException;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author ander
 */
public class ReservaJpaController implements Serializable {

    public ReservaJpaController(EntityManagerFactory emf) {
        this.emf = emf;
    }
    
    public ReservaJpaController(){
        this.emf = Persistence.createEntityManagerFactory("ResSalonPU");
    }
    private EntityManagerFactory emf = null;

    public EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

    public void create(Reserva reserva) throws PreexistingEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Ambiente codambiente = reserva.getCodambiente();
            if (codambiente != null) {
                codambiente = em.getReference(codambiente.getClass(), codambiente.getCodambiente());
                reserva.setCodambiente(codambiente);
            }
            Asignatura codasignatura = reserva.getCodasignatura();
            if (codasignatura != null) {
                codasignatura = em.getReference(codasignatura.getClass(), codasignatura.getCodasignatura());
                reserva.setCodasignatura(codasignatura);
            }
            Programa codprograma = reserva.getCodprograma();
            if (codprograma != null) {
                codprograma = em.getReference(codprograma.getClass(), codprograma.getCodprograma());
                reserva.setCodprograma(codprograma);
            }
            Usuario codpersonal = reserva.getCodpersonal();
            if (codpersonal != null) {
                codpersonal = em.getReference(codpersonal.getClass(), codpersonal.getCodpersonal());
                reserva.setCodpersonal(codpersonal);
            }
            em.persist(reserva);
            if (codambiente != null) {
                codambiente.getReservaCollection().add(reserva);
                codambiente = em.merge(codambiente);
            }
            if (codasignatura != null) {
                codasignatura.getReservaCollection().add(reserva);
                codasignatura = em.merge(codasignatura);
            }
            if (codprograma != null) {
                codprograma.getReservaCollection().add(reserva);
                codprograma = em.merge(codprograma);
            }
            if (codpersonal != null) {
                codpersonal.getReservaCollection().add(reserva);
                codpersonal = em.merge(codpersonal);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            if (findReserva(reserva.getCodreserva()) != null) {
                throw new PreexistingEntityException("Reserva " + reserva + " already exists.", ex);
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void edit(Reserva reserva) throws NonexistentEntityException, Exception {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva persistentReserva = em.find(Reserva.class, reserva.getCodreserva());
            Ambiente codambienteOld = persistentReserva.getCodambiente();
            Ambiente codambienteNew = reserva.getCodambiente();
            Asignatura codasignaturaOld = persistentReserva.getCodasignatura();
            Asignatura codasignaturaNew = reserva.getCodasignatura();
            Programa codprogramaOld = persistentReserva.getCodprograma();
            Programa codprogramaNew = reserva.getCodprograma();
            Usuario codpersonalOld = persistentReserva.getCodpersonal();
            Usuario codpersonalNew = reserva.getCodpersonal();
            if (codambienteNew != null) {
                codambienteNew = em.getReference(codambienteNew.getClass(), codambienteNew.getCodambiente());
                reserva.setCodambiente(codambienteNew);
            }
            if (codasignaturaNew != null) {
                codasignaturaNew = em.getReference(codasignaturaNew.getClass(), codasignaturaNew.getCodasignatura());
                reserva.setCodasignatura(codasignaturaNew);
            }
            if (codprogramaNew != null) {
                codprogramaNew = em.getReference(codprogramaNew.getClass(), codprogramaNew.getCodprograma());
                reserva.setCodprograma(codprogramaNew);
            }
            if (codpersonalNew != null) {
                codpersonalNew = em.getReference(codpersonalNew.getClass(), codpersonalNew.getCodpersonal());
                reserva.setCodpersonal(codpersonalNew);
            }
            reserva = em.merge(reserva);
            if (codambienteOld != null && !codambienteOld.equals(codambienteNew)) {
                codambienteOld.getReservaCollection().remove(reserva);
                codambienteOld = em.merge(codambienteOld);
            }
            if (codambienteNew != null && !codambienteNew.equals(codambienteOld)) {
                codambienteNew.getReservaCollection().add(reserva);
                codambienteNew = em.merge(codambienteNew);
            }
            if (codasignaturaOld != null && !codasignaturaOld.equals(codasignaturaNew)) {
                codasignaturaOld.getReservaCollection().remove(reserva);
                codasignaturaOld = em.merge(codasignaturaOld);
            }
            if (codasignaturaNew != null && !codasignaturaNew.equals(codasignaturaOld)) {
                codasignaturaNew.getReservaCollection().add(reserva);
                codasignaturaNew = em.merge(codasignaturaNew);
            }
            if (codprogramaOld != null && !codprogramaOld.equals(codprogramaNew)) {
                codprogramaOld.getReservaCollection().remove(reserva);
                codprogramaOld = em.merge(codprogramaOld);
            }
            if (codprogramaNew != null && !codprogramaNew.equals(codprogramaOld)) {
                codprogramaNew.getReservaCollection().add(reserva);
                codprogramaNew = em.merge(codprogramaNew);
            }
            if (codpersonalOld != null && !codpersonalOld.equals(codpersonalNew)) {
                codpersonalOld.getReservaCollection().remove(reserva);
                codpersonalOld = em.merge(codpersonalOld);
            }
            if (codpersonalNew != null && !codpersonalNew.equals(codpersonalOld)) {
                codpersonalNew.getReservaCollection().add(reserva);
                codpersonalNew = em.merge(codpersonalNew);
            }
            em.getTransaction().commit();
        } catch (Exception ex) {
            String msg = ex.getLocalizedMessage();
            if (msg == null || msg.length() == 0) {
                Integer id = reserva.getCodreserva();
                if (findReserva(id) == null) {
                    throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.");
                }
            }
            throw ex;
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public void destroy(Integer id) throws NonexistentEntityException {
        EntityManager em = null;
        try {
            em = getEntityManager();
            em.getTransaction().begin();
            Reserva reserva;
            try {
                reserva = em.getReference(Reserva.class, id);
                reserva.getCodreserva();
            } catch (EntityNotFoundException enfe) {
                throw new NonexistentEntityException("The reserva with id " + id + " no longer exists.", enfe);
            }
            Ambiente codambiente = reserva.getCodambiente();
            if (codambiente != null) {
                codambiente.getReservaCollection().remove(reserva);
                codambiente = em.merge(codambiente);
            }
            Asignatura codasignatura = reserva.getCodasignatura();
            if (codasignatura != null) {
                codasignatura.getReservaCollection().remove(reserva);
                codasignatura = em.merge(codasignatura);
            }
            Programa codprograma = reserva.getCodprograma();
            if (codprograma != null) {
                codprograma.getReservaCollection().remove(reserva);
                codprograma = em.merge(codprograma);
            }
            Usuario codpersonal = reserva.getCodpersonal();
            if (codpersonal != null) {
                codpersonal.getReservaCollection().remove(reserva);
                codpersonal = em.merge(codpersonal);
            }
            em.remove(reserva);
            em.getTransaction().commit();
        } finally {
            if (em != null) {
                em.close();
            }
        }
    }

    public List<Reserva> findReservaEntities() {
        return findReservaEntities(true, -1, -1);
    }

    public List<Reserva> findReservaEntities(int maxResults, int firstResult) {
        return findReservaEntities(false, maxResults, firstResult);
    }

    private List<Reserva> findReservaEntities(boolean all, int maxResults, int firstResult) {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            cq.select(cq.from(Reserva.class));
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

    public Reserva findReserva(Integer id) {
        EntityManager em = getEntityManager();
        try {
            return em.find(Reserva.class, id);
        } finally {
            em.close();
        }
    }

    public int getReservaCount() {
        EntityManager em = getEntityManager();
        try {
            CriteriaQuery cq = em.getCriteriaBuilder().createQuery();
            Root<Reserva> rt = cq.from(Reserva.class);
            cq.select(em.getCriteriaBuilder().count(rt));
            Query q = em.createQuery(cq);
            return ((Long) q.getSingleResult()).intValue();
        } finally {
            em.close();
        }
    }
    
}
