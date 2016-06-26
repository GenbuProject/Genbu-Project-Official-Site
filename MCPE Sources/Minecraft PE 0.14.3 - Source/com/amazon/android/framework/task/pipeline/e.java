package com.amazon.android.framework.task.pipeline;

import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.o.g;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public final class e
  implements com.amazon.android.framework.resource.b, f
{
  private static final KiwiLogger a = new KiwiLogger("ForegroundTaskPipeline");
  @Resource
  private ContextManager b;
  @Resource
  private g c;
  private f d = a.b("KIWI_UI");
  private f e;
  private List f = new ArrayList();
  
  public e(f paramf)
  {
    this.e = paramf;
  }
  
  private void a(Task paramTask, boolean paramBoolean)
  {
    if (this.b.isVisible())
    {
      if (paramBoolean)
      {
        this.d.b(paramTask);
        return;
      }
      this.d.a(paramTask);
      return;
    }
    if (KiwiLogger.TRACE_ON) {
      a.trace("No UI visible to execute task: " + paramTask + ", placing into pending queue until task " + "is visible");
    }
    this.f.add(paramTask);
  }
  
  private Task c(Task paramTask)
  {
    return new c(this, paramTask);
  }
  
  public final void a()
  {
    this.d.a();
    this.e.a();
    this.f.clear();
  }
  
  public final void a(Task paramTask)
  {
    a(paramTask, false);
  }
  
  public final void a(Task paramTask, long paramLong)
  {
    this.e.a(c(paramTask), paramLong);
  }
  
  public final void a(Task paramTask, Date paramDate)
  {
    this.e.a(c(paramTask), paramDate);
  }
  
  public final void b(Task paramTask)
  {
    a(paramTask, true);
  }
  
  public final void onResourcesPopulated()
  {
    this.c.a(new b(this));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\pipeline\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */