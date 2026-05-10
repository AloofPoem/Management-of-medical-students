package com.gestionestudiantesmedicina.daos;

import com.gestionestudiantesmedicina.entities.AcademicData;

public class AcademicDataDAO extends AbstractGenericDAO<AcademicData, Long> {

    protected AcademicDataDAO() {
        super(AcademicData.class);
    }

}
