package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "teacher")
public class Teacher extends Person {

    @Column(name = "specialty", nullable = false)
    private String specialty;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Student> students;

    @OneToMany(mappedBy = "teacher", cascade = {CascadeType.MERGE,CascadeType.REFRESH, CascadeType.REMOVE}, orphanRemoval = true)
    private List<Record> records;

    public Teacher() {
    }

    public Teacher(Long id, String name, String lastName, LocalDate birthDate, String password, String specialty,
            List<Student> students, List<Record> records) {
        super(id, name, lastName, birthDate, password);
        this.specialty = specialty;
        this.students = students;
        this.records = records;
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

    public List<Record> getRecords() {
        return records;
    }

    public void setRecords(List<Record> records) {
        this.records = records;
    }
    
}