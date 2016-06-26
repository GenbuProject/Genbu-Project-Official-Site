package com.amazon.android.n;

import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public final class d
  implements com.amazon.android.i.d
{
  private static final KiwiLogger a = new KiwiLogger("ExpirableValueDataStore");
  @Resource
  private com.amazon.android.framework.resource.a b;
  private final Map c = new HashMap();
  
  private void a(b paramb)
  {
    try
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Observed expiration: " + paramb + " removing from store!");
      }
      Iterator localIterator = this.c.entrySet().iterator();
      while (localIterator.hasNext()) {
        if (((Map.Entry)localIterator.next()).getValue() == paramb)
        {
          if (KiwiLogger.TRACE_ON) {
            a.trace("Removing entry from store: " + paramb);
          }
          localIterator.remove();
        }
      }
    }
    finally {}
  }
  
  public final void a(String paramString, b paramb)
  {
    try
    {
      com.amazon.android.d.a.a(paramString, "key");
      com.amazon.android.d.a.a(paramb, "value");
      if (KiwiLogger.TRACE_ON) {
        a.trace("Placing value into store with key: " + paramString + ", expiration: " + paramb.getExpiration());
      }
      this.b.b(paramb);
      paramb.register(this);
      this.c.put(paramString, paramb);
      return;
    }
    finally {}
  }
  
  public final void a(String paramString, Object paramObject)
  {
    try
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Placing non-expiring value into store with key: " + paramString);
      }
      paramObject = new c(this, paramObject, new Date());
      this.c.put(paramString, paramObject);
      return;
    }
    finally {}
  }
  
  /* Error */
  public final boolean a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 142	com/amazon/android/n/d:b	(Ljava/lang/String;)Ljava/lang/Object;
    //   7: astore_1
    //   8: aload_1
    //   9: ifnull +9 -> 18
    //   12: iconst_1
    //   13: istore_2
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_2
    //   17: ireturn
    //   18: iconst_0
    //   19: istore_2
    //   20: goto -6 -> 14
    //   23: astore_1
    //   24: aload_0
    //   25: monitorexit
    //   26: aload_1
    //   27: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	28	0	this	d
    //   0	28	1	paramString	String
    //   13	7	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	8	23	finally
  }
  
  /* Error */
  public final Object b(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: getstatic 39	com/amazon/android/framework/util/KiwiLogger:TRACE_ON	Z
    //   5: ifeq +28 -> 33
    //   8: getstatic 25	com/amazon/android/n/d:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   11: new 41	java/lang/StringBuilder
    //   14: dup
    //   15: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   18: ldc -112
    //   20: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   23: aload_1
    //   24: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   30: invokevirtual 60	com/amazon/android/framework/util/KiwiLogger:trace	(Ljava/lang/String;)V
    //   33: aload_0
    //   34: getfield 33	com/amazon/android/n/d:c	Ljava/util/Map;
    //   37: aload_1
    //   38: invokeinterface 148 2 0
    //   43: checkcast 108	com/amazon/android/n/b
    //   46: astore_1
    //   47: aload_1
    //   48: ifnonnull +23 -> 71
    //   51: getstatic 39	com/amazon/android/framework/util/KiwiLogger:TRACE_ON	Z
    //   54: ifeq +11 -> 65
    //   57: getstatic 25	com/amazon/android/n/d:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   60: ldc -106
    //   62: invokevirtual 60	com/amazon/android/framework/util/KiwiLogger:trace	(Ljava/lang/String;)V
    //   65: aconst_null
    //   66: astore_1
    //   67: aload_0
    //   68: monitorexit
    //   69: aload_1
    //   70: areturn
    //   71: aload_1
    //   72: getfield 153	com/amazon/android/n/b:a	Ljava/lang/Object;
    //   75: astore_1
    //   76: goto -9 -> 67
    //   79: astore_1
    //   80: aload_0
    //   81: monitorexit
    //   82: aload_1
    //   83: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	84	0	this	d
    //   0	84	1	paramString	String
    // Exception table:
    //   from	to	target	type
    //   2	33	79	finally
    //   33	47	79	finally
    //   51	65	79	finally
    //   71	76	79	finally
  }
  
  /* Error */
  public final void c(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: getfield 33	com/amazon/android/n/d:c	Ljava/util/Map;
    //   6: aload_1
    //   7: invokeinterface 148 2 0
    //   12: checkcast 108	com/amazon/android/n/b
    //   15: astore_2
    //   16: aload_2
    //   17: ifnonnull +6 -> 23
    //   20: aload_0
    //   21: monitorexit
    //   22: return
    //   23: getstatic 39	com/amazon/android/framework/util/KiwiLogger:TRACE_ON	Z
    //   26: ifeq +37 -> 63
    //   29: getstatic 25	com/amazon/android/n/d:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   32: new 41	java/lang/StringBuilder
    //   35: dup
    //   36: invokespecial 42	java/lang/StringBuilder:<init>	()V
    //   39: ldc -101
    //   41: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   44: aload_1
    //   45: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   48: ldc -99
    //   50: invokevirtual 48	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   53: aload_2
    //   54: invokevirtual 51	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   57: invokevirtual 57	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   60: invokevirtual 60	com/amazon/android/framework/util/KiwiLogger:trace	(Ljava/lang/String;)V
    //   63: aload_0
    //   64: getfield 33	com/amazon/android/n/d:c	Ljava/util/Map;
    //   67: aload_1
    //   68: invokeinterface 159 2 0
    //   73: pop
    //   74: aload_2
    //   75: invokevirtual 162	com/amazon/android/n/b:discard	()V
    //   78: goto -58 -> 20
    //   81: astore_1
    //   82: aload_0
    //   83: monitorexit
    //   84: aload_1
    //   85: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	86	0	this	d
    //   0	86	1	paramString	String
    //   15	60	2	localb	b
    // Exception table:
    //   from	to	target	type
    //   2	16	81	finally
    //   23	63	81	finally
    //   63	78	81	finally
  }
  
  public final String toString()
  {
    return this.c.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\n\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */