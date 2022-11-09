package com.workfusion.chakshu.model;

public class ExtractionModelResult {
	
	private String extractedValue;
	private String goldValue;
	
	
	public ExtractionModelResult() {
		super();
	}
	public ExtractionModelResult(String extractedValue, String goldValue) {
		super();
		this.extractedValue = extractedValue;
		this.goldValue = goldValue;
	}
	public String getExtractedValue() {
		return extractedValue;
	}
	public void setExtractedValue(String extractedValue) {
		this.extractedValue = extractedValue;
	}
	public String getGoldValue() {
		return goldValue;
	}
	public void setGoldValue(String goldValue) {
		this.goldValue = goldValue;
	}
	@Override
	public String toString() {
		return "ExtractionModelResult [extractedValue=" + extractedValue + ", goldValue=" + goldValue + "]";
	}

}
