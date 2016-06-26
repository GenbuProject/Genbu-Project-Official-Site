package com.amazon.android.framework.context;

import android.app.Activity;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;

final class a
  implements Task
{
  a(d paramd) {}
  
  public final void execute()
  {
    Activity localActivity = this.a.getRoot();
    d.a.trace("Finishing Root Task: " + localActivity);
    if (localActivity != null)
    {
      d.a.trace("Finishing Root");
      localActivity.finish();
    }
  }
  
  public final String toString()
  {
    return "ContextManager: kill root task";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\context\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */