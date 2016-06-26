package com.amazon.android.framework.task;

import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.concurrent.atomic.AtomicBoolean;

public final class a
  implements com.amazon.android.framework.resource.b, TaskManager
{
  private static final KiwiLogger a = new KiwiLogger("TaskManagerImpl");
  @Resource
  private com.amazon.android.framework.resource.a b;
  @Resource
  private com.amazon.android.o.g c;
  private final AtomicBoolean d = new AtomicBoolean(false);
  private final Map e = new HashMap();
  
  public a()
  {
    com.amazon.android.framework.task.pipeline.a locala1 = com.amazon.android.framework.task.pipeline.a.a(TaskPipelineId.COMMAND.name());
    com.amazon.android.framework.task.pipeline.a locala2 = com.amazon.android.framework.task.pipeline.a.a(TaskPipelineId.BACKGROUND.name());
    com.amazon.android.framework.task.pipeline.e locale = new com.amazon.android.framework.task.pipeline.e(locala2);
    this.e.put(TaskPipelineId.COMMAND, locala1);
    this.e.put(TaskPipelineId.BACKGROUND, locala2);
    this.e.put(TaskPipelineId.FOREGROUND, locale);
  }
  
  private void a(TaskPipelineId paramTaskPipelineId, Task paramTask, b paramb)
  {
    if (this.d.get())
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Task enqueued after TaskManager has been finished! Task: " + paramTask);
      }
      return;
    }
    if (KiwiLogger.TRACE_ON) {
      a.trace("Populating Task: " + paramTask);
    }
    this.b.b(paramTask);
    com.amazon.android.framework.task.pipeline.f localf = (com.amazon.android.framework.task.pipeline.f)this.e.get(paramTaskPipelineId);
    if (localf == null) {
      throw new IllegalStateException("No pipeline registered with id: " + paramTaskPipelineId);
    }
    paramb.a(paramTask, localf);
  }
  
  public final void a()
  {
    if (!this.d.compareAndSet(false, true)) {}
    for (;;)
    {
      return;
      if (KiwiLogger.TRACE_ON) {
        a.trace("TaskManager finishing....");
      }
      Iterator localIterator = this.e.values().iterator();
      while (localIterator.hasNext()) {
        ((com.amazon.android.framework.task.pipeline.f)localIterator.next()).a();
      }
    }
  }
  
  public final void enqueue(TaskPipelineId paramTaskPipelineId, Task paramTask)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Enqueue task on pipeline id: " + paramTaskPipelineId);
    }
    a(paramTaskPipelineId, paramTask, new c(this));
  }
  
  public final void enqueueAfterDelay(TaskPipelineId paramTaskPipelineId, Task paramTask, long paramLong)
  {
    a(paramTaskPipelineId, paramTask, new f(this, paramLong));
  }
  
  public final void enqueueAtFront(TaskPipelineId paramTaskPipelineId, Task paramTask)
  {
    a(paramTaskPipelineId, paramTask, new d(this));
  }
  
  public final void enqueueAtTime(TaskPipelineId paramTaskPipelineId, Task paramTask, Date paramDate)
  {
    a(paramTaskPipelineId, paramTask, new e(this, paramDate));
  }
  
  public final void onResourcesPopulated()
  {
    Object localObject = this.e.values().iterator();
    while (((Iterator)localObject).hasNext())
    {
      com.amazon.android.framework.task.pipeline.f localf = (com.amazon.android.framework.task.pipeline.f)((Iterator)localObject).next();
      this.b.b(localf);
    }
    localObject = new g(this);
    this.c.a((com.amazon.android.o.c)localObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */