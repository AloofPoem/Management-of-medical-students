package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Subject;

public class SubjectDAO extends AbstractGenericDAO<Subject, Long> {

    public SubjectDAO() {
        super(Subject.class);
    }

}
