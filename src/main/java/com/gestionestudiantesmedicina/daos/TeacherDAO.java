package com.gestionestudiantesmedicina.daos;

import javax.persistence.EntityManager;

import com.gestionestudiantesmedicina.entities.Teacher;

public class TeacherDAO extends AbstractGenericDAO<Teacher, Long> {

    public TeacherDAO() {
        super(Teacher.class);
    }

    public Teacher findByIdWithList(Long id, String collections) {
    EntityManager em = emf.createEntityManager(); // Asegúrate de usar tu Variable de EntityManagerFactory
    try {
        return em.createQuery("SELECT t FROM Teacher t LEFT JOIN FETCH t." + collections + " WHERE t.id = :id",
                Teacher.class)
                .setParameter("id", id)
                .getSingleResult();
    } catch (Exception e) {
        // En caso de que no encuentre el docente o falle la consulta
        System.err.println("Error al buscar Teacher con colección " + collections + ": " + e.getMessage());
        return null;
    } finally {
        em.close();
    }
}
}
