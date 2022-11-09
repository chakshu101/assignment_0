package com.workfusion.chakshu.task;

import java.util.Iterator;

import javax.inject.Inject;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.slf4j.Logger;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.workfusion.chakshu.model.ExtractionModelResult;
import com.workfusion.chakshu.module.RepositoryModule;
import com.workfusion.chakshu.repository.ExtractionModelResultRepository;
import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.cdi.Requires;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;

@BotTask
@Requires({ RepositoryModule.class })
public class DataExtractionOutputTask implements GenericTask{

	private  Logger log;
	private TaskInput taskInput;
	private TaskRunnerOutput taskOutput;
	private ExtractionModelResultRepository extractionModelResultRepository;
	
	
	@Inject
	public DataExtractionOutputTask(Logger log, TaskInput taskInput, TaskRunnerOutput taskOutput,
			ExtractionModelResultRepository extractionModelResultRepository) {
		super();
		this.log = log;
		this.taskInput = taskInput;
		this.taskOutput = taskOutput;
		this.extractionModelResultRepository = extractionModelResultRepository;
	}
	@Override
	public TaskRunnerOutput run() {
		// TODO Auto-generated method stub
		String modelResultJson= taskInput.getRequiredVariable("model_result");
		String extractedValue= org.apache.commons.lang.StringUtils.EMPTY;
		JsonObject jsonObject=new JsonParser().parse(modelResultJson).getAsJsonObject();  
        Iterator<JsonElement> iterator = jsonObject.get("tags").getAsJsonArray().iterator();
        while(iterator.hasNext()) {

            JsonObject jsonObj = iterator.next().getAsJsonObject();
            if(jsonObj.get("tag").getAsString().equalsIgnoreCase("invoice_amount")) {
                           extractedValue = jsonObj.get("text").getAsString();
                           break;
            }
        }
        ExtractionModelResult extractionModelResult = new ExtractionModelResult();
        
        extractionModelResult.setExtractedValue(extractedValue);

        String sel=taskInput.getRequiredVariable("document");
        Document js= Jsoup.parse(sel);
        String goldValue=js.select("invoice_amount").attr("data_value");
        
        extractionModelResult.setGoldValue(goldValue);
        
        extractionModelResultRepository.create(extractionModelResult);
        
        taskOutput.setColumn("extractionResult", extractionModelResultRepository.toString());
		return taskOutput;
	}
	
}
