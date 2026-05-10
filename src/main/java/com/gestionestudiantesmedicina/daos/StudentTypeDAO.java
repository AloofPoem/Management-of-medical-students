package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.StudentType;

public class StudentTypeDAO extends AbstractGenericDAO<StudentType, Long> {

    public StudentTypeDAO() {
        super(StudentType.class);
    }

}
