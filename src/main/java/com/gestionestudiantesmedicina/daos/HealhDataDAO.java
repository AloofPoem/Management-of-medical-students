package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.HealthData;

public class HealhDataDAO extends AbstractGenericDAO<HealthData, Long> {

    protected HealhDataDAO() {
        super(HealthData.class);
    }

}
