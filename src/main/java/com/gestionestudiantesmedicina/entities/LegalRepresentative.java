package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gestionestudiantesmedicina.enumeration.RelationShip;

@Entity
@Table(name = "legal_representative")
public class LegalRepresentative {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idLegalRe;

    @Column(name = "name_legal_representative", nullable = false)
    private String nameLegalRe;

    @Column(name = "legal_representative_phone", nullable = false)
    private String legalRepPhone;

    @Column(name = "legal_representative_address")
    private String legalRepAddress;

    @Column(name = "legal_representative_birth_date")
    private LocalDate legalRepBirthDate;

    @Column(name = "legal_representative_city")
    private String legalRepCity;

    @Enumerated(EnumType.STRING)
    @Column(name = "legal_representative_relationship")
    private RelationShip legalRepRelationship;

    public LegalRepresentative() {
    }

    public LegalRepresentative(String nameLegalRe, String legalRepPhone, String legalRepAddress, LocalDate legalRepBirthDate,
            String legalRepCity, RelationShip legalRepRelationship) {
        this.nameLegalRe = nameLegalRe;
        this.legalRepPhone = legalRepPhone;
        this.legalRepAddress = legalRepAddress;
        this.legalRepBirthDate = legalRepBirthDate;
        this.legalRepCity = legalRepCity;
        this.legalRepRelationship = legalRepRelationship;
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
        return legalRepPhone;
    }

    public void setPhoneNu(String legalRepPhone){
        this.legalRepPhone = legalRepPhone;
    }

    public String getAddress() {
        return legalRepAddress;
    }

    public void setAddress(String legalRepAddress) {
        this.legalRepAddress = legalRepAddress;
    }

    public LocalDate getBirthDate() {
        return legalRepBirthDate;
    }

    public void setBirthDate(LocalDate legalRepBirthDate) {
        this.legalRepBirthDate = legalRepBirthDate;
    }

    public String getCity() {
        return legalRepCity;
    }

    public void setCity(String legalRepCity) {
        this.legalRepCity = legalRepCity;
    }

    public RelationShip getRelationship() {
        return legalRepRelationship;
    }

    public void setRelationship(RelationShip legalRepRelationship) {
        this.legalRepRelationship = legalRepRelationship;
    }

}