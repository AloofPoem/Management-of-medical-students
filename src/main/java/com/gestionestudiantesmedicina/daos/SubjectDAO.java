package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Subject;

public class SubjectDAO extends AbstractGenericDAO<Subject, Long> {

    protected SubjectDAO() {
        super(Subject.class);
    }

}
