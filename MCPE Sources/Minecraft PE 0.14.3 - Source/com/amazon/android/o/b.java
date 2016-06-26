package com.amazon.android.o;

import com.amazon.android.d.a;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.HashMap;
import java.util.Map;

public final class b
  implements g
{
  private static final KiwiLogger a = new KiwiLogger("EventManagerImpl");
  private final Map b = new HashMap();
  
  public final void a(c paramc)
  {
    a.a(paramc, "listener");
    a.a();
    f localf = paramc.a();
    a.trace("Registering listener for event: " + localf + ", " + paramc);
    e locale2 = (e)this.b.get(localf);
    e locale1 = locale2;
    if (locale2 == null)
    {
      locale1 = new e();
      this.b.put(localf, locale1);
    }
    locale1.a(paramc);
  }
  
  public final void a(d paramd)
  {
    f localf = paramd.a();
    if (KiwiLogger.TRACE_ON) {
      a.trace("Posting event: " + localf);
    }
    if (!this.b.containsKey(localf))
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("No registered listeners, returning");
      }
      return;
    }
    ((e)this.b.get(localf)).a(paramd);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\o\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */