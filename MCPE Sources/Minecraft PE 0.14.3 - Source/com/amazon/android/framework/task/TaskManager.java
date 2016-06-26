package com.amazon.android.framework.task;

import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import java.util.Date;

public abstract interface TaskManager
{
  public abstract void enqueue(TaskPipelineId paramTaskPipelineId, Task paramTask);
  
  public abstract void enqueueAfterDelay(TaskPipelineId paramTaskPipelineId, Task paramTask, long paramLong);
  
  public abstract void enqueueAtFront(TaskPipelineId paramTaskPipelineId, Task paramTask);
  
  public abstract void enqueueAtTime(TaskPipelineId paramTaskPipelineId, Task paramTask, Date paramDate);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\TaskManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */