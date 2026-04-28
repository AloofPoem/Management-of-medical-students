package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;

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
@Table(name = "legal_representative")
public class Legalrepresentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLegalRe;

    private String nameLegalRe;
    private String phoneNu;
    private String address;
    private LocalDate birthDate;
    private String city;

    @Enumerated(EnumType.STRING)
    private RelationShip relationship;

    @ManyToOne
    @JoinColumn(name = "student_id")
    private Student student;

    public Legalrepresentative() {
    }

    public Legalrepresentative(Long idLegalRe, String nameLegalRe, String phoneNu, String address, LocalDate birthDate,
            String city, RelationShip relationship, Student student) {
        this.idLegalRe = idLegalRe;
        this.nameLegalRe = nameLegalRe;
        this.phoneNu = phoneNu;
        this.address = address;
        this.birthDate = birthDate;
        this.city = city;
        this.relationship = relationship;
        this.student = student;
    }

    public Long getIdLegalRe() {
        return idLegalRe;
    }

    public void setIdLegalRe(Long idLegalRe) {
        this.idLegalRe = idLegalRe;
    }

    public String getNameLegalRe() {
        return nameLegalRe;
    }

    public void setNameLegalRe(String nameLegalRe) {
        this.nameLegalRe = nameLegalRe;
    }

    public String getPhoneNu() {
        return phoneNu;
    }

    public void setPhoneNu(String phoneNu) {
        this.phoneNu = phoneNu;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public RelationShip getRelationship() {
        return relationship;
    }

    public void setRelationship(RelationShip relationship) {
        this.relationship = relationship;
    }

    public Student getStudent() {
        return student;
    }

    public void setStudent(Student student) {
        this.student = student;
    }
}