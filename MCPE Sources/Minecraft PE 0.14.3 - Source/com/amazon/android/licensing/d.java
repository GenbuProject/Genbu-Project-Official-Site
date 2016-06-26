package com.amazon.android.licensing;

import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.n.b;
import java.util.Date;

final class d
  extends b
{
  d(l paraml, Object paramObject, Date paramDate)
  {
    super(paramObject, paramDate);
  }
  
  public final void doExpiration()
  {
    l locall = new l();
    this.taskManager.enqueue(TaskPipelineId.COMMAND, locall);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\licensing\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */