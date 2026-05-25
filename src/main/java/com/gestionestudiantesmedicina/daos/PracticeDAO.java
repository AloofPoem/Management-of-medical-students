package com.gestionestudiantesmedicina.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.gestionestudiantesmedicina.entities.Practice;

public class PracticeDAO extends AbstractGenericDAO<Practice, Long> {

    public PracticeDAO() {
        super(Practice.class);
    }

    public List<Practice> findByStudentId(Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT DISTINCT p FROM Practice p JOIN p.students st WHERE st.id = :studentId";

            return em.createQuery(jpql, Practice.class).setParameter("studentId", studentId).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return new ArrayList<>();
        } finally {
            em.close();
        }
    }
}