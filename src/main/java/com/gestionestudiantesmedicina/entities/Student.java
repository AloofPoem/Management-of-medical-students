package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import com.gestionestudiantesmedicina.enumeration.MaritalStatus;

@Entity
@Table(name = "student")
public class Student extends Person {

    @Enumerated(EnumType.STRING)
    @Column(name = "marital_status", nullable = false)
    private MaritalStatus maritalStatus;

    @Column(name = "birth_place", nullable = false)
    private String birthPlace;

    @Column(name = "address_tunja", nullable = false)
    private String addressTunja;

    @Column(name = "permanent_address", nullable = false)
    private String permanentAddress;

    @Column(name = "phone_number", nullable = false)
    private String phoneNumber;

    @Column(name = "email", nullable = false)
    private String email;

    @Column(name = "second_language")
    private String secondLanguage;

    @Column(name = "roomies", nullable = false)
    private Integer roomies;

    @Column(name = "family_core_tunja", nullable = false)
    private Integer familyCoreTunja;

    @Column(name = "entry_date", nullable = false)
    private LocalDate entryDate;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "health_data_id")
    private HealthData healthData;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "academic_data_id")
    private AcademicData academicData;

    @ManyToMany
    @JoinTable(name = "student_practice", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "practice_id"))
    private List<Practice> practices;

    @ManyToOne
    @JoinColumn(name = "student_type_id")
    private StudentType studentType;

    @OneToMany(mappedBy = "student", cascade = { CascadeType.MERGE, CascadeType.REFRESH,
            CascadeType.REMOVE }, orphanRemoval = true)
    private List<Relative> relatives;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "legal_representative")
    private LegalRepresentative legalRepresentative;

    @ManyToMany
    @JoinTable(name = "student_schedule", joinColumns = @JoinColumn(name = "student_id"), inverseJoinColumns = @JoinColumn(name = "schedule_id"))
    private List<Schedule> schedules;

    public Student() {
    }

    public Student(Long id, String name, String lastName, LocalDate birthDate, String password,
            MaritalStatus maritalStatus, String birthPlace, String addressTunja, String permanentAddress,
            String phoneNumber, String email, String secondLanguage, Integer roomies, Integer familyCoreTunja,
            LocalDate entryDate, HealthData healthData, AcademicData academicData, StudentType studentType,
            LegalRepresentative legalRepresentative) {
        super(id, name, lastName, birthDate, password);
        this.maritalStatus = maritalStatus;
        this.birthPlace = birthPlace;
        this.addressTunja = addressTunja;
        this.permanentAddress = permanentAddress;
        this.phoneNumber = phoneNumber;
        this.email = email;
        this.secondLanguage = secondLanguage;
        this.roomies = roomies;
        this.familyCoreTunja = familyCoreTunja;
        this.entryDate = entryDate;
        this.healthData = healthData;
        this.academicData = academicData;
        this.studentType = studentType;
        this.legalRepresentative = legalRepresentative;
        this.relatives = new ArrayList<>();
        this.practices = new ArrayList<>();
        this.schedules = new ArrayList<>();
    }

    public MaritalStatus getMaritalStatus() {
        return maritalStatus;
    }

    public void setMaritalStatus(MaritalStatus maritalStatus) {
        this.maritalStatus = maritalStatus;
    }

    public String getBirthPlace() {
        return birthPlace;
    }

    public void setBirthPlace(String birthPlace) {
        this.birthPlace = birthPlace;
    }

    public String getAddressTunja() {
        return addressTunja;
    }

    public void setAddressTunja(String addressTunja) {
        this.addressTunja = addressTunja;
    }

    public String getPermanentAddress() {
        return permanentAddress;
    }

    public void setPermanentAddress(String permanentAddress) {
        this.permanentAddress = permanentAddress;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSecondLanguage() {
        return secondLanguage;
    }

    public void setSecondLanguage(String secondLanguage) {
        this.secondLanguage = secondLanguage;
    }

    public Integer getRoomies() {
        return roomies;
    }

    public void setRoomies(Integer roomies) {
        this.roomies = roomies;
    }

    public Integer getFamilyCoreTunja() {
        return familyCoreTunja;
    }

    public void setFamilyCoreTunja(Integer familyCoreTunja) {
        this.familyCoreTunja = familyCoreTunja;
    }

    public LocalDate getEntryDate() {
        return entryDate;
    }

    public void setEntryDate(LocalDate entryDate) {
        this.entryDate = entryDate;
    }

    public HealthData getHealthData() {
        return healthData;
    }

    public void setHealthData(HealthData healthData) {
        this.healthData = healthData;
    }

    public AcademicData getAcademicData() {
        return academicData;
    }

    public void setAcademicData(AcademicData academicData) {
        this.academicData = academicData;
    }

    public List<Practice> getPractices() {
        return practices;
    }

    public void setPractices(List<Practice> practices) {
        this.practices = practices;
    }

    public StudentType getStudentType() {
        return studentType;
    }

    public void setStudentType(StudentType studentType) {
        this.studentType = studentType;
    }

    public List<Relative> getRelatives() {
        return relatives;
    }

    public void setRelatives(List<Relative> relatives) {
        this.relatives = relatives;
    }

    public LegalRepresentative getLegalRepresentative() {
        return legalRepresentative;
    }

    public void setLegalRepresentative(LegalRepresentative legalRepresentative) {
        this.legalRepresentative = legalRepresentative;
    }

    public List<Schedule> getSchedules() {
        return schedules;
    }

    public void setSchedules(List<Schedule> schedules) {
        this.schedules = schedules;
    }

    public void addSchedule(Schedule schedule) {
        this.schedules.add(schedule);
        //schedule.getStudents().add(this);
    }

    public void addPractice(Practice practice) {
        this.practices.add(practice);
        //practice.getStudents().add(this);
    }

}