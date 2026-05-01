package com.gestionestudiantesmedicina.entities;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "schedule")
public class Schedule {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long IdSchedule;

    @Column(name = "date", nullable = false)
    private LocalDate date;

    @Column(name = "start_time", nullable = false)
    private LocalTime startTime;

    @Column(name = "end_time", nullable = false)
    private LocalTime endTime;

    @OneToMany(mappedBy = "schedule")
    private List<Record> record;

    public Schedule() {}

    public Schedule(Long idSchedule, LocalDate date, LocalTime startTime, LocalTime endTime, List<Record> record) {
        IdSchedule = idSchedule;
        this.date = date;
        this.startTime = startTime;
        this.endTime = endTime;
        this.record = record;
    }

    public Long getIdSchedule() {
        return IdSchedule;
    }

    public void setIdSchedule(Long idSchedule) {
        IdSchedule = idSchedule;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public LocalTime getEndTime() {
        return endTime;
    }

    public void setEndTime(LocalTime endTime) {
        this.endTime = endTime;
    }

    public List<Record> getRecord() {
        return record;
    }

    public void setRecord(List<Record> record) {
        this.record = record;
    }
}