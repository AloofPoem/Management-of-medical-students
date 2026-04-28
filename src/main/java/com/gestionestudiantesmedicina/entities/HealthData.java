package com.gestionestudiantesmedicina.entities;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.gestionestudiantesmedicina.enumeration.BloodType;

@Entity
@Table(name = "health_data")
public class HealthData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String generalDiseases;
    private String mentalIllness;
    private String medications;
    private String allergies;
    private double weight;
    private double size;
    private double bmi;

    @Enumerated(EnumType.STRING)
    private BloodType bloodType;

    public HealthData() {
    }

    public HealthData(String generalDiseases, String mentalIllness, String medications, String allergies, double weight,
            double size, double bmi, BloodType bloodType) {
        this.generalDiseases = generalDiseases;
        this.mentalIllness = mentalIllness;
        this.medications = medications;
        this.allergies = allergies;
        this.weight = weight;
        this.size = size;
        this.bmi = bmi;
        this.bloodType = bloodType;
    }

    public Long getId() {
        return id;
    }

    public String getGeneralDiseases() {
        return generalDiseases;
    }

    public void setGeneralDiseases(String generalDiseases) {
        this.generalDiseases = generalDiseases;
    }

    public String getMentalIllness() {
        return mentalIllness;
    }

    public void setMentalIllness(String mentalIllness) {
        this.mentalIllness = mentalIllness;
    }

    public String getMedications() {
        return medications;
    }

    public void setMedications(String medications) {
        this.medications = medications;
    }

    public String getAllergies() {
        return allergies;
    }

    public void setAllergies(String allergies) {
        this.allergies = allergies;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getSize() {
        return size;
    }

    public void setSize(double size) {
        this.size = size;
    }

    public double getBmi() {
        return bmi;
    }

    public void setBmi(double bmi) {
        this.bmi = bmi;
    }

    public BloodType getBloodType() {
        return bloodType;
    }

    public void setBloodType(BloodType bloodType) {
        this.bloodType = bloodType;
    }
}