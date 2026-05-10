package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Schedule;

public class ScheduleDAO extends AbstractGenericDAO<Schedule, Long> {

    public ScheduleDAO() {
        super(Schedule.class);
    }

}
