package com.workfusion.chakshu.module;

import javax.inject.Singleton;

import org.codejargon.feather.Provides;

import com.j256.ormlite.support.ConnectionSource;
import com.workfusion.chakshu.repository.ExtractionModelResultRepository;
import com.workfusion.odf2.core.cdi.OdfModule;

public class RepositoryModule implements OdfModule{

	
	  @Provides
	  @Singleton
	  public ExtractionModelResultRepository service(ConnectionSource connectionSource) {
	      return new ExtractionModelResultRepository();
	  }
}
