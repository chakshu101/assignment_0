package com.workfusion.chakshu.model;

public class ExtractionResult {
	

	 private String extracted_value;

	  private String gold_value;


	public ExtractionResult() {
		super();
	}

	public ExtractionResult(String extracted_value, String gold_value) {
		super();
		this.extracted_value = extracted_value;
		this.gold_value = gold_value;
	}

	public String getExtracted_value() {
		return extracted_value;
	}

	public void setExtracted_value(String extracted_value) {
		this.extracted_value = extracted_value;
	}

	public String getGold_value() {
		return gold_value;
	}

	public void setGold_value(String gold_value) {
		this.gold_value = gold_value;
	}
	  
	  


}
