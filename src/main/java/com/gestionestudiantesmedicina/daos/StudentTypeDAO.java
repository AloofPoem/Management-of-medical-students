package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.StudentType;

public class StudentTypeDAO extends AbstractGenericDAO<StudentType, Long> {

    protected StudentTypeDAO() {
        super(StudentType.class);
    }

}
