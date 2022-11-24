package com.workfusion.chakshu.task;

import java.util.Iterator;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;

import com.google.gson.*;
import com.workfusion.chakshu.model.ExtractionResult;
import com.workfusion.chakshu.module.RepositoryModule;
import com.workfusion.chakshu.repository.ExtractionResultRepository;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Requires;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;

@BotTask
@Requires({ RepositoryModule.class })
public class DataExtractionOutputTask implements GenericTask{

	private  Logger log;
	private TaskInput taskInput;
	private TaskRunnerOutput taskOutput;
	private ExtractionResultRepository extractionModelResultRepository;
	
	
	@Inject
	public DataExtractionOutputTask(Logger log, TaskInput taskInput,
			ExtractionResultRepository extractionModelResultRepository) {
		super();
		this.log = log;
		this.taskInput = taskInput;
		this.extractionModelResultRepository = extractionModelResultRepository;
	}
	@Override
	public TaskRunnerOutput run() {
		// TODO Auto-generated method stub
		taskOutput=new SingleResult();
		String modelResultJson= taskInput.getRequiredVariable("model_result");
		String extractedValue= org.apache.commons.lang.StringUtils.EMPTY;
        JsonObject jsonObject = new JsonParser().parse(modelResultJson).getAsJsonObject();   
        Iterator<JsonElement> iterator = jsonObject.get("tags").getAsJsonArray().iterator();
        while(iterator.hasNext()) {

            JsonObject jsonObj = iterator.next().getAsJsonObject();
            if(jsonObj.get("tag").getAsString().equalsIgnoreCase("invoice_amount")) {
                           extractedValue = jsonObj.get("text").getAsString();
                           break;
            }
        }
        ExtractionResult extractionModelResult = new ExtractionResult();
        Document js= Jsoup.parse(taskInput.getRequiredVariable("document"));
       String goldValue=js.select("invoice_amount").attr("data-value").replaceAll("\"\"", "\"");
       log.info("Gold Value:"+goldValue+"  Extracted Value: "+extractedValue);
        extractionModelResult.setExtracted_value(extractedValue);
        extractionModelResult.setGold_value(goldValue);
        taskOutput.setColumn("gold_value", extractionModelResult.getGold_value());
        taskOutput.setColumn("extracted_value", extractionModelResult.getExtracted_value());
		return taskOutput;
	}
	
}
