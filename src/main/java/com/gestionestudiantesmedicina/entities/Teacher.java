package com.gestionestudiantesmedicina.entities;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idTeacher;

    @Column(name = "specialty", nullable = false)
    private String specialty;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private Record record;

    public Teacher() {
    }

    public Teacher(Long idTeacher, String specialty, List<Student> students) {
        this.idTeacher = idTeacher;
        this.specialty = specialty;
        this.students = students;
    }

    public Long getIdTeacher() {
        return idTeacher;
    }

    public void setIdTeacher(Long idTeacher) {
        this.idTeacher = idTeacher;
    }

    public String getSpecialty() {
        return specialty;
    }

    public void setSpecialty(String specialty) {
        this.specialty = specialty;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}