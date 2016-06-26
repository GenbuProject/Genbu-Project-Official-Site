package com.amazon.android.f;

import android.content.Intent;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.o.g;
import java.util.Random;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.atomic.AtomicReference;

public final class c
  implements b, com.amazon.android.framework.resource.b
{
  private static final KiwiLogger a = new KiwiLogger("ActivityResultManagerImpl");
  private static final Random b = new Random();
  @Resource
  private TaskManager c;
  @Resource
  private ContextManager d;
  @Resource
  private g e;
  private AtomicReference f = new AtomicReference();
  private BlockingQueue g = new LinkedBlockingQueue();
  
  public final f a(Intent paramIntent)
  {
    a locala = new a(paramIntent, b.nextInt(65535) + 1);
    if (!this.f.compareAndSet(null, locala))
    {
      a.error("StartActivityForResult called while ActivityResultManager is already awaiting a result");
      return null;
    }
    a.trace("Starting activity for result: " + paramIntent + ", " + paramIntent.getFlags() + ", requestId: " + locala.a);
    paramIntent = new d(this, locala);
    this.c.enqueueAtFront(TaskPipelineId.FOREGROUND, paramIntent);
    try
    {
      a.trace("Blocking for request: " + locala.a);
      paramIntent = (f)this.g.take();
      return paramIntent;
    }
    catch (InterruptedException paramIntent)
    {
      a.trace("Interrupted while awaiting for request, returning null");
      return null;
    }
    finally
    {
      a.trace("Received Response: " + locala.a);
      this.f.set(null);
    }
  }
  
  public final boolean a(f paramf)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Recieved ActivityResult: " + paramf);
    }
    a locala = (a)this.f.get();
    if (locala == null)
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("We don't have a current open request, returning");
      }
      return false;
    }
    if (locala.a != paramf.a)
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("We don't have a request with code: " + paramf.a + ", returning");
      }
      return false;
    }
    if (KiwiLogger.TRACE_ON) {
      a.trace("Signaling thread waiting for request: " + paramf.a);
    }
    this.g.add(paramf);
    return true;
  }
  
  public final void onResourcesPopulated()
  {
    this.e.a(new e(this));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\f\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */