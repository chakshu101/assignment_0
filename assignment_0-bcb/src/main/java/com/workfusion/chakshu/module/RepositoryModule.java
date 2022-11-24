package com.workfusion.chakshu.module;

import java.sql.SQLException;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import com.j256.ormlite.support.ConnectionSource;
import com.workfusion.chakshu.repository.ExtractionResultRepository;
import com.workfusion.odf2.core.cdi.OdfModule;

public class RepositoryModule implements OdfModule{

	
	  @Provides
	  @Singleton
	  public ExtractionResultRepository service(ConnectionSource connectionSource) throws SQLException {
	      return new ExtractionResultRepository(connectionSource);
	  }
}
