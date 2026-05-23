package com.gestionestudiantesmedicina.daos;

import javax.persistence.EntityManager;

import com.gestionestudiantesmedicina.entities.Record;

public class RecordDAO extends AbstractGenericDAO<Record, Long> {

    public RecordDAO() {
        super(Record.class);
    }

    public Record findLastByPersonId(Long personId) {
    EntityManager em = getEntityManager();
    try {
        return em.createQuery(
            "SELECT r FROM Record r WHERE r.person.id = :personId ORDER BY r.id DESC", Record.class).setParameter("personId", personId).setMaxResults(1).getResultStream().findFirst().orElse(null);
    } catch (Exception e) {
        e.printStackTrace(); 
        return null;
    } finally {
        em.close(); 
    }
}

}
