package com.gestionestudiantesmedicina.daos;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class AbstractGenericDAO<T, K> implements GenericDAO<T, K> {

    protected Class<T> entityClass;

    // cambiar base de datos
    protected static final EntityManagerFactory emf = Persistence.createEntityManagerFactory("estudiantes_medicina00");;

    protected AbstractGenericDAO(Class<T> entityClass) {
        this.entityClass = entityClass;
    }

    @Override
    public T save(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        em.persist(entity);
        em.getTransaction().commit();
        em.close();
        return entity;
    }

    @Override
    public T findById(K id) {
        EntityManager em = emf.createEntityManager();
        T entity = em.find(entityClass, id);
        em.close();
        return entity;
    }

    @SuppressWarnings("unchecked")
    @Override
    public List<T> findAll() {
        EntityManager em = emf.createEntityManager();
        List<T> list = em.createQuery("FROM " + entityClass.getSimpleName()).getResultList();
        em.close();
        return list;
    }

    @Override
    public T update(T entity) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        T merged = em.merge(entity);
        em.getTransaction().commit();
        em.close();
        return merged;
    }

    @Override
    public void delete(K id) {
        EntityManager em = emf.createEntityManager();
        em.getTransaction().begin();
        T entity = em.find(entityClass, id);
        if (entity != null) {
            em.remove(entity);
        }
        em.getTransaction().commit();
        em.close();
    }

    @Override
    public List<T> findByAttribute(String attributeName, Object value) {
        EntityManager em = emf.createEntityManager();
        try {
            // Construye un query dinámico: "FROM Relative WHERE student.id = :value"
            String hql = "FROM " + entityClass.getSimpleName() + " WHERE " + attributeName + " = :value";

            return em.createQuery(hql, entityClass)
                    .setParameter("value", value)
                    .getResultList();
        } finally {
            em.close();
        }
    }

    protected EntityManager getEntityManager() {
        return emf.createEntityManager();
    }

}
