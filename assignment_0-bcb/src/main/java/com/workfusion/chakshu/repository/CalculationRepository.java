package com.workfusion.chakshu.repository;

import java.sql.SQLException;

import com.j256.ormlite.support.ConnectionSource;
import com.workfusion.chakshu.model.Calculation;
import com.workfusion.odf2.core.orm.OrmLiteRepository;

public class CalculationRepository extends OrmLiteRepository<Calculation>{


	public CalculationRepository(ConnectionSource connectionSource) throws SQLException {
        super(connectionSource, Calculation.class);
    }

}
