package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.HealthData;

public class HealhDataDAO extends AbstractGenericDAO<HealthData, Long> {

    public HealhDataDAO() {
        super(HealthData.class);
    }

}
