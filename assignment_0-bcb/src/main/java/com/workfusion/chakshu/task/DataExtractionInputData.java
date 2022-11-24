package com.workfusion.chakshu.task;

import javax.inject.Inject;

import org.slf4j.Logger;

import com.workfusion.odf2.compiler.BotTask;
import com.workfusion.odf2.core.task.TaskInput;
import com.workfusion.odf2.core.task.generic.GenericTask;
import com.workfusion.odf2.core.task.output.SingleResult;
import com.workfusion.odf2.core.task.output.TaskRunnerOutput;

@BotTask
public class DataExtractionInputData implements GenericTask{

	private  Logger logger;
    private  TaskInput taskInput;
    private  TaskRunnerOutput taskOutput;
    
    @Inject
	public DataExtractionInputData(Logger logger, TaskInput taskInput) {
		super();
		this.logger = logger;
		this.taskInput = taskInput;
	}
	@Override
	public TaskRunnerOutput run() {
		// TODO Auto-generated method stub
		taskOutput=new SingleResult();
		String document = taskInput.getRequiredVariable("message");
        String modelId = "message_eb891a9e-1f4e-4001-aa58-52b4868e56f3";
        taskOutput.setColumn("model_id", modelId);
        taskOutput.setColumn("document", document);
		return taskOutput;
	}

}
