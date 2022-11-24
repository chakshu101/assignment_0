package com.workfusion.chakshu.model;

import com.j256.ormlite.field.DatabaseField;
import com.j256.ormlite.table.DatabaseTable;

@DatabaseTable(tableName = "calculation")
public class Calculation {

	@DatabaseField(columnName = "tp")
    private String tp;
	
	@DatabaseField(columnName = "tn")
    private String tn;
	
	@DatabaseField(columnName = "fp")
    private String fp;
	
	@DatabaseField(columnName = "fn")
    private String fn;

	public String getTp() {
		return tp;
	}

	public void setTp(String tp) {
		this.tp = tp;
	}

	public String getTn() {
		return tn;
	}

	public void setTn(String tn) {
		this.tn = tn;
	}

	public String getFp() {
		return fp;
	}

	public void setFp(String fp) {
		this.fp = fp;
	}

	public String getFn() {
		return fn;
	}

	public void setFn(String fn) {
		this.fn = fn;
	}
	
	
	
}
