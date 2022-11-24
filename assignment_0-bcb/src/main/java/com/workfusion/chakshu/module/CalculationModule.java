package com.workfusion.chakshu.module;

import java.sql.SQLException;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import com.j256.ormlite.support.ConnectionSource;
import com.workfusion.chakshu.repository.CalculationRepository;
import com.workfusion.odf2.core.cdi.OdfModule;

public class CalculationModule implements OdfModule{
	
	  @Provides
	  @Singleton
	    public CalculationRepository calculationRepository(ConnectionSource connectionSource) throws SQLException {
	        return new CalculationRepository(connectionSource);
	    }

}
