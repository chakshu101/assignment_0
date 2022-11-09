package com.workfusion.chakshu.repository;

import java.util.ArrayList;
import java.util.List;

import com.workfusion.chakshu.model.ExtractionModelResult;

public class ExtractionModelResultRepository {


	private List<ExtractionModelResult> extractionModelResultArray=new ArrayList<>();

	
	public void create(ExtractionModelResult value) {
		extractionModelResultArray.add(value);
	}


	public List<ExtractionModelResult> getExtractionModelResultArray() {
		return extractionModelResultArray;
	}


	public void setExtractionModelResultArray(List<ExtractionModelResult> extractionModelResultArray) {
		this.extractionModelResultArray = extractionModelResultArray;
	}
	
	
	
}
