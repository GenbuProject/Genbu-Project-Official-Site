package com.amazon.device.iap.internal;

import android.util.Log;

public final class e
{
  private static final String a = e.class.getName();
  private static volatile boolean b;
  private static volatile boolean c;
  private static volatile c d;
  private static volatile a e;
  private static volatile b f;
  
  private static <T> T a(Class<T> paramClass)
  {
    try
    {
      Object localObject = d().a(paramClass).newInstance();
      return (T)localObject;
    }
    catch (Exception localException)
    {
      Log.e(a, "error getting instance for " + paramClass, localException);
    }
    return null;
  }
  
  public static boolean a()
  {
    if (c) {
      return b;
    }
    try
    {
      if (c)
      {
        boolean bool = b;
        return bool;
      }
    }
    finally {}
    try
    {
      e.class.getClassLoader().loadClass("com.amazon.android.Kiwi");
      b = false;
      c = true;
      return b;
    }
    catch (Throwable localThrowable)
    {
      for (;;)
      {
        b = true;
      }
    }
  }
  
  public static c b()
  {
    if (d == null) {}
    try
    {
      if (d == null) {
        d = (c)a(c.class);
      }
      return d;
    }
    finally {}
  }
  
  public static a c()
  {
    if (e == null) {}
    try
    {
      if (e == null) {
        e = (a)a(a.class);
      }
      return e;
    }
    finally {}
  }
  
  /* Error */
  private static b d()
  {
    // Byte code:
    //   0: getstatic 99	com/amazon/device/iap/internal/e:f	Lcom/amazon/device/iap/internal/b;
    //   3: ifnonnull +31 -> 34
    //   6: ldc 2
    //   8: monitorenter
    //   9: getstatic 99	com/amazon/device/iap/internal/e:f	Lcom/amazon/device/iap/internal/b;
    //   12: ifnonnull +19 -> 31
    //   15: invokestatic 101	com/amazon/device/iap/internal/e:a	()Z
    //   18: ifeq +20 -> 38
    //   21: new 103	com/amazon/device/iap/internal/a/d
    //   24: dup
    //   25: invokespecial 104	com/amazon/device/iap/internal/a/d:<init>	()V
    //   28: putstatic 99	com/amazon/device/iap/internal/e:f	Lcom/amazon/device/iap/internal/b;
    //   31: ldc 2
    //   33: monitorexit
    //   34: getstatic 99	com/amazon/device/iap/internal/e:f	Lcom/amazon/device/iap/internal/b;
    //   37: areturn
    //   38: new 106	com/amazon/device/iap/internal/b/g
    //   41: dup
    //   42: invokespecial 107	com/amazon/device/iap/internal/b/g:<init>	()V
    //   45: putstatic 99	com/amazon/device/iap/internal/e:f	Lcom/amazon/device/iap/internal/b;
    //   48: goto -17 -> 31
    //   51: astore_0
    //   52: ldc 2
    //   54: monitorexit
    //   55: aload_0
    //   56: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   51	5	0	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   9	31	51	finally
    //   31	34	51	finally
    //   38	48	51	finally
    //   52	55	51	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */