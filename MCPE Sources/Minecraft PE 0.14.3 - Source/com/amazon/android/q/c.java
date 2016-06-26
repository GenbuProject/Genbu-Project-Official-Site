package com.amazon.android.q;

import com.amazon.android.framework.util.KiwiLogger;
import java.util.List;

public final class c
  implements d
{
  private static final KiwiLogger a = new KiwiLogger("MetricsManagerImpl");
  private a b = new a();
  
  /* Error */
  public final a a()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 30	com/amazon/android/q/c:b	Lcom/amazon/android/q/a;
    //   6: invokevirtual 34	com/amazon/android/q/a:a	()Z
    //   9: ifeq +12 -> 21
    //   12: aload_0
    //   13: getfield 30	com/amazon/android/q/c:b	Lcom/amazon/android/q/a;
    //   16: astore_1
    //   17: aload_0
    //   18: monitorexit
    //   19: aload_1
    //   20: areturn
    //   21: aload_0
    //   22: getfield 30	com/amazon/android/q/c:b	Lcom/amazon/android/q/a;
    //   25: astore_1
    //   26: aload_0
    //   27: new 27	com/amazon/android/q/a
    //   30: dup
    //   31: invokespecial 28	com/amazon/android/q/a:<init>	()V
    //   34: putfield 30	com/amazon/android/q/c:b	Lcom/amazon/android/q/a;
    //   37: goto -20 -> 17
    //   40: astore_1
    //   41: aload_0
    //   42: monitorexit
    //   43: aload_1
    //   44: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	45	0	this	c
    //   16	10	1	locala	a
    //   40	4	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	17	40	finally
    //   21	37	40	finally
  }
  
  public final void a(b paramb)
  {
    try
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Recording Metric: " + paramb);
      }
      this.b.a.add(paramb);
      return;
    }
    finally {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\q\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */