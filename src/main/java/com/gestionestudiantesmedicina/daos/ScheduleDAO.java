package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Schedule;

public class ScheduleDAO extends AbstractGenericDAO<Schedule, Long> {

    protected ScheduleDAO() {
        super(Schedule.class);
    }

}
