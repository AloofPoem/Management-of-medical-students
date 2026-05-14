package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Record;

public class RecordDAO extends AbstractGenericDAO<Record, Long> {

    public RecordDAO() {
        super(Record.class);
    }

}
