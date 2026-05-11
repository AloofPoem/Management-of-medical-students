package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.Practice;

public class PracticeDAO extends AbstractGenericDAO<Practice, Long> {

    public PracticeDAO() {
        super(Practice.class);
    }

}
