package com.gestionestudiantesmedicina.entities;


import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "student_type")
public class StudentType {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idStuType;

    @Column(name = "name_stu_type", nullable = false)
    private String nameStuType;

    @OneToMany(mappedBy = "studentType")
    private List<Student> students;

    public StudentType() {
    }

    public StudentType(Long idStuType, String nameStuType, List<Student> students) {
        this.idStuType = idStuType;
        this.nameStuType = nameStuType;
        this.students = students;
    }

    public Long getIdStuType() {
        return idStuType;
    }

    public void setIdStuType(Long idStuType) {
        this.idStuType = idStuType;
    }

    public String getNameStuType() {
        return nameStuType;
    }

    public void setNameStuType(String nameStuType) {
        this.nameStuType = nameStuType;
    }

    public List<Student> getStudents() {
        return students;
    }

    public void setStudents(List<Student> students) {
        this.students = students;
    }
}