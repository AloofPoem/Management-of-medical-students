package com.gestionestudiantesmedicina.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "relative")
public class Relative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idRelative;

    private String name;
    private String lastName;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    @Enumerated(EnumType.STRING)
    private RelationShip relationship;

    public Relative() {
    }

    public Relative(Long idRelative, String name, String lastName, Student student, RelationShip relationship) {
        this.idRelative = idRelative;
        this.name = name;
        this.lastName = lastName;
        this.student = student;
        this.relationship = relationship;
    }

    public Long getIdRelative() {
        return idRelative;
    }

    public void setIdRelative(Long idRelative) {
        this.idRelative = idRelative;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }

    public RelationShip getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationShip relationship) {
        this.relationship = relationship;
    }
}