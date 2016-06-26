package com.amazon.android.framework.task.pipeline;

import android.os.SystemClock;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.Date;
import java.util.Set;

final class d
  implements Runnable
{
  d(a parama, Task paramTask) {}
  
  public final void run()
  {
    a.a(this.b).remove(this);
    try
    {
      if (KiwiLogger.TRACE_ON) {
        a.b().trace(a.b(this.b) + ": Executing Task: " + this.a + ", current time: " + new Date() + ", uptime: " + SystemClock.uptimeMillis());
      }
      this.a.execute();
      return;
    }
    catch (Throwable localThrowable)
    {
      if (KiwiLogger.ERROR_ON) {
        a.b().error("Task Failed with unhandled exception: " + localThrowable, localThrowable);
      }
      return;
    }
    finally
    {
      if (KiwiLogger.TRACE_ON) {
        a.b().trace(a.b(this.b) + ": Task finished executing: " + this.a);
      }
    }
  }
  
  public final String toString()
  {
    return this.a.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\pipeline\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */