package com.workfusion.chakshu.task;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.workfusion.chakshu.model.ExtractionResult;
import com.workfusion.chakshu.module.RepositoryModule;
import com.workfusion.chakshu.repository.ExtractionResultRepository;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Requires;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;

@BotTask
@Requires(RepositoryModule.class)
public class CalculationFinal implements GenericTask{

	private Logger log;
	private final ExtractionResultRepository extractionResultRepository;
	
	@Inject
	public CalculationFinal(Logger log, ExtractionResultRepository extractionResultRepository) {
		super();
		this.log = log;
		this.extractionResultRepository=extractionResultRepository;
	}


	@Override
	public TaskRunnerOutput run() {
		TaskRunnerOutput out=new SingleResult();
		float tp=0;
		float fp=0;
		float tn=0;
		float fn=0;
		log.info("Tp "+tp+"  Tn"+tn+ "  Fp: "+fp+"  Fn: "+fn);
//		for(ExtractionResult result: results){
//			String extracted_value=result.getExtracted_value();
//			String gold_value=result.getGold_value();
//			if(extracted_value.equals(gold_value)&& !extracted_value.isEmpty()&&!gold_value.isEmpty()) {
//				tp=tp+1;
//			}
//			else if(!gold_value.isEmpty() && extracted_value.isEmpty()) {
//				fn=fn+1;
//			}
//			else if(!extracted_value.equals(gold_value)&&!extracted_value.isEmpty()&&!gold_value.isEmpty() ) {
//				fp=fp+1;
//				fn=fn+1;
//			}
//			else if(extracted_value.isEmpty()&&!gold_value.isEmpty()) {
//				fp=fp+1;
//			}
//			else if(extracted_value.isEmpty()&&gold_value.isEmpty()) {
//				tn=tn+1;
//		
//			}
//		}
//		if(System.getProperties().containsKey("TP")) {
//			System.clearProperty("TP");
//		}
//		if(System.getProperties().containsKey("TN")) {
//			System.clearProperty("TN");
//		}
//		if(System.getProperties().containsKey("FP")) {
//			System.clearProperty("FP");
//		}
//		if(System.getProperties().containsKey("FN")) {
//			System.clearProperty("FN");
//		}
		
		
		float precision=tp/(tp+fp);//x=28/(28+172)
		float recall=tp/(tp+fn);
		float accuracy=(tp+tn)/(tp+tn+fp+fn);
		log.info("precision "+precision+"  recall"+recall+ "  accuracy: "+accuracy);
		out.setColumn("Precision", ""+precision);
		out.setColumn("Recall", ""+recall);
		out.setColumn("Accuracy", ""+accuracy);
		return out;
	}

}
