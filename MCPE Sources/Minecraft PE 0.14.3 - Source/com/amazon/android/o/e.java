package com.amazon.android.o;

import com.amazon.android.framework.util.KiwiLogger;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;

public final class e
  implements Comparator
{
  private static final KiwiLogger a = new KiwiLogger("EventListenerNotificationQueue");
  private List b = new ArrayList();
  
  public final void a(c paramc)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Adding listener: " + paramc);
    }
    this.b.add(paramc);
    Collections.sort(this.b, this);
  }
  
  public final void a(d paramd)
  {
    Iterator localIterator = this.b.iterator();
    while (localIterator.hasNext())
    {
      c localc = (c)localIterator.next();
      if (KiwiLogger.TRACE_ON) {
        a.trace("Notifying listener: " + localc);
      }
      localc.a(paramd);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\o\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */