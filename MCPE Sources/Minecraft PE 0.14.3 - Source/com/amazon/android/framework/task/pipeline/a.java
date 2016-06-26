package com.amazon.android.framework.task.pipeline;

import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.os.SystemClock;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.Collections;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class a
  implements f
{
  private static final KiwiLogger a = new KiwiLogger("SimpleTaskPipeline");
  private final Handler b;
  private final Set c = Collections.synchronizedSet(new HashSet());
  private final String d;
  
  private a(HandlerThread paramHandlerThread)
  {
    this.d = paramHandlerThread.getName();
    paramHandlerThread.start();
    this.b = new Handler(paramHandlerThread.getLooper());
  }
  
  private a(String paramString)
  {
    this.d = paramString;
    this.b = new Handler();
  }
  
  public static a a(String paramString)
  {
    return new a(new HandlerThread("KIWI_" + paramString));
  }
  
  public static a b(String paramString)
  {
    return new a(paramString);
  }
  
  private Runnable c(Task paramTask)
  {
    paramTask = new d(this, paramTask);
    this.c.add(paramTask);
    return paramTask;
  }
  
  public final void a()
  {
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator.next();
      if (KiwiLogger.TRACE_ON) {
        a.trace(this.d + ": Removing callback: " + localRunnable);
      }
      this.b.removeCallbacks(localRunnable);
    }
    this.c.clear();
    if ((this.b.getLooper() != Looper.getMainLooper()) && (this.b.getLooper().getThread().isAlive()))
    {
      a.trace("Interrupting looper thread!");
      this.b.getLooper().getThread().interrupt();
      a.trace("Quitting looper: " + this.b.getLooper().getThread() + ", " + this.b.getLooper().getThread().isAlive());
      this.b.getLooper().quit();
    }
  }
  
  public final void a(Task paramTask)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Scheduling task: " + paramTask);
    }
    this.b.post(c(paramTask));
  }
  
  public final void a(Task paramTask, long paramLong)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace(this.d + ": Scheduling task: " + paramTask + ", with delay: " + paramLong);
    }
    this.b.postDelayed(c(paramTask), paramLong);
  }
  
  public final void a(Task paramTask, Date paramDate)
  {
    long l = SystemClock.uptimeMillis() + (paramDate.getTime() - System.currentTimeMillis());
    if (KiwiLogger.TRACE_ON) {
      a.trace(this.d + ": Scheduling task: " + paramTask + ", at time: " + paramDate + ", System uptimeMillis: " + System.currentTimeMillis() + ", uptimeMillis: " + l);
    }
    this.b.postAtTime(c(paramTask), l);
  }
  
  public final void b(Task paramTask)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace(this.d + ": Scheduling task immediately: " + paramTask);
    }
    this.b.postAtFrontOfQueue(c(paramTask));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\pipeline\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */