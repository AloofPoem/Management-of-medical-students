package com.gestionestudiantesmedicina.entities;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdTeacher;

    private String specialty;

    public Teacher() {
    }

    public Teacher(Long idTeacher, String specialty) {
        this.IdTeacher = idTeacher;
        this.specialty = specialty;
    }

    public Long getIdTeacher() {
        return IdTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.IdTeacher = idTeacher;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }
}