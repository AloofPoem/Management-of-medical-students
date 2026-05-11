package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Teacher;

public class TeacherDAO extends AbstractGenericDAO<Teacher, Long> {

    public TeacherDAO() {
        super(Teacher.class);
    }

}
