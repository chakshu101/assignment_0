package com.workfusion.chakshu.task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.workfusion.chakshu.model.Calculation;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;


@BotTask
public class CalculatePandR implements GenericTask{

	private TaskInput taskInput;
	private TaskRunnerOutput taskOutput;
	
	
	private Logger log;



	@Inject
	public CalculatePandR(TaskInput taskInput, Logger log) {
		super();
		this.taskInput = taskInput;
		this.log=log;
	}






	@Override
	public TaskRunnerOutput run() {
		// TODO Auto-generated method stub
		taskOutput=new SingleResult();
		
		String gold_value= taskInput.getRequiredVariable("gold_value");
		String extracted_value=taskInput.getRequiredVariable("extracted_value");
		
		if(!System.getProperties().containsKey("TP")) {
			System.setProperty("TP", "0");
		}
		if(!System.getProperties().containsKey("TN")) {
			System.setProperty("TN", "0");
		}
		if(!System.getProperties().containsKey("FP")) {
			System.setProperty("FP", "0");
		}
		if(!System.getProperties().containsKey("FN")) {
			System.setProperty("FN", "0");
		}
		
		 int tp=Integer.parseInt(System.getProperty("TP")),fp=Integer.parseInt(System.getProperty("FP"));
		 int tn=Integer.parseInt(System.getProperty("TN")), fn=Integer.parseInt(System.getProperty("FN"));
		
		 
		 
		 
		if(extracted_value.equals(gold_value)&& !extracted_value.isEmpty()&&!gold_value.isEmpty()) {
			tp=tp+1;
			System.setProperty("TP", Integer.toString(tp));
		}
		else if(!gold_value.isEmpty() && extracted_value.isEmpty()) {
			fn=fn+1;
			System.setProperty("FN", Integer.toString(fn));
		}
		else if(!extracted_value.equals(gold_value)&&!extracted_value.isEmpty()&&!gold_value.isEmpty() ) {
			fp=fp+1;
			System.setProperty("FP", Integer.toString(fp));
			fn=fn+1;
			System.setProperty("FN", Integer.toString(fn));
		}
		else if(extracted_value.isEmpty()&&!gold_value.isEmpty()) {
			fp=fp+1;
			System.setProperty("FP", Integer.toString(fp));
		}
		else if(extracted_value.isEmpty()&&gold_value.isEmpty()) {
			tn=tn+1;
			System.setProperty("TN", Integer.toString(tn));
	
		}

		log.info("Tp "+tp+"  Tn"+tn+ "  Fp: "+fp+"  Fn: "+fn);
		taskOutput.setColumn("res", ""+tp);
		
		return taskOutput;
	}

}
