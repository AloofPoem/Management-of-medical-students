package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Teacher;

public class TeacherDAO extends AbstractGenericDAO<Teacher, Long> {

    protected TeacherDAO() {
        super(Teacher.class);
    }

}
