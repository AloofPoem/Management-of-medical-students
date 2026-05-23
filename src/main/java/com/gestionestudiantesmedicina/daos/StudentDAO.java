package com.gestionestudiantesmedicina.daos;

import javax.persistence.EntityManager;

import com.gestionestudiantesmedicina.entities.Student;

public class StudentDAO extends AbstractGenericDAO<Student, Long> {

    public StudentDAO() {
        super(Student.class);
    }

    public Student findByIdWithSchedules(Long id) {
        EntityManager em = emf.createEntityManager();
        try {
            return em.createQuery(
                    "SELECT s FROM Student s LEFT JOIN FETCH s.schedules WHERE s.id = :id", Student.class)
                    .setParameter("id", id)
                    .getSingleResult();
        } catch (Exception e) {
            return null;
        } finally {
            em.close();
        }
    }
}
