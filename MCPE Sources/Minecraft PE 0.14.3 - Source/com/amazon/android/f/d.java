package com.amazon.android.f;

import android.app.Activity;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;

final class d
  implements Task
{
  d(c paramc, a parama) {}
  
  public final void execute()
  {
    Activity localActivity = c.b(this.b).getVisible();
    if (localActivity == null)
    {
      c.a().trace("No activity to call startActivityForResult on. startActivityForResult when an activity becomes visible");
      return;
    }
    this.a.a(localActivity);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\f\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */