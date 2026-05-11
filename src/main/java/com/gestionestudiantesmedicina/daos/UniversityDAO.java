package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.University;

public class UniversityDAO extends AbstractGenericDAO<University, Long> {

    public UniversityDAO() {
        super(University.class);
    }

}
