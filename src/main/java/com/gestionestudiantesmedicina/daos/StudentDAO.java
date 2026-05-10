package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Student;

public class StudentDAO extends AbstractGenericDAO<Student, Long> {

    protected StudentDAO() {
        super(Student.class);
    }

}
