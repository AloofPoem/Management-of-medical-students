package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;
import java.time.LocalTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "record")
public class Record {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdRecord;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "time_in", nullable = false)
    private LocalTime timeIn;

    @Column(name = "time_out")
    private LocalTime timeOut;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "schedule_id")
    private Schedule schedule;

    public Record() {}

    public Record(Long idRecord, LocalDate date, LocalTime timeIn, LocalTime timeOut, Teacher teacher,
            Schedule schedule) {
        IdRecord = idRecord;
        this.date = date;
        this.timeIn = timeIn;
        this.timeOut = timeOut;
        this.teacher = teacher;
        this.schedule = schedule;
    }

    public Long getIdRecord() {
        return IdRecord;
    }

    public void setIdRecord(Long idRecord) {
        IdRecord = idRecord;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getTimeIn() {
        return timeIn;
    }

    public void setTimeIn(LocalTime timeIn) {
        this.timeIn = timeIn;
    }

    public LocalTime getTimeOut() {
        return timeOut;
    }

    public void setTimeOut(LocalTime timeOut) {
        this.timeOut = timeOut;
    }

    public Teacher getTeacher() {
        return teacher;
    }

    public void setTeacher(Teacher teacher) {
        this.teacher = teacher;
    }

    public Schedule getSchedule() {
        return schedule;
    }

    public void setSchedule(Schedule schedule) {
        this.schedule = schedule;
    }
}