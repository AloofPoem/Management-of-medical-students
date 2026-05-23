package com.gestionestudiantesmedicina.daos;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.EntityManager;

import com.gestionestudiantesmedicina.entities.Schedule;

public class ScheduleDAO extends AbstractGenericDAO<Schedule, Long> {

    public ScheduleDAO() {
        super(Schedule.class);
    }

    public List<Schedule> findByStudentId(Long studentId) {
        EntityManager em = emf.createEntityManager();
        try {
            String jpql = "SELECT s FROM Schedule s JOIN s.students st WHERE st.id = :studentId";

            return em.createQuery(jpql, Schedule.class).setParameter("studentId", studentId).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            em.close();
        }
    }
}
