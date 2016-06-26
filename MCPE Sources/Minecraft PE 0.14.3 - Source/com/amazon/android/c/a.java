package com.amazon.android.c;

import android.app.Application;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.o.g;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class a
  implements d, com.amazon.android.framework.resource.b, Thread.UncaughtExceptionHandler
{
  private static final KiwiLogger a = new KiwiLogger("CrashManagerImpl");
  @Resource
  private g b;
  @Resource
  private com.amazon.android.g.c c;
  @Resource
  private Application d;
  private Thread.UncaughtExceptionHandler e;
  private Map f = new HashMap();
  
  /* Error */
  private void a(String paramString)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: new 48	java/util/Random
    //   5: dup
    //   6: invokespecial 49	java/util/Random:<init>	()V
    //   9: ldc 50
    //   11: invokevirtual 54	java/util/Random:nextInt	(I)I
    //   14: istore_2
    //   15: new 56	java/lang/StringBuilder
    //   18: dup
    //   19: invokespecial 57	java/lang/StringBuilder:<init>	()V
    //   22: ldc 59
    //   24: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   27: iload_2
    //   28: invokevirtual 66	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   31: ldc 68
    //   33: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   36: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   39: astore 4
    //   41: aconst_null
    //   42: astore_3
    //   43: aconst_null
    //   44: astore 5
    //   46: aload_0
    //   47: getfield 74	com/amazon/android/c/a:d	Landroid/app/Application;
    //   50: aload 4
    //   52: iconst_0
    //   53: invokevirtual 80	android/app/Application:openFileOutput	(Ljava/lang/String;I)Ljava/io/FileOutputStream;
    //   56: astore 4
    //   58: aload 4
    //   60: aload_1
    //   61: invokevirtual 86	java/lang/String:getBytes	()[B
    //   64: invokevirtual 92	java/io/FileOutputStream:write	([B)V
    //   67: aload 4
    //   69: invokestatic 97	com/amazon/android/framework/util/a:a	(Ljava/io/OutputStream;)V
    //   72: aload_0
    //   73: monitorexit
    //   74: return
    //   75: astore 4
    //   77: aload 5
    //   79: astore_1
    //   80: aload_1
    //   81: astore_3
    //   82: getstatic 101	com/amazon/android/framework/util/KiwiLogger:ERROR_ON	Z
    //   85: ifeq +15 -> 100
    //   88: aload_1
    //   89: astore_3
    //   90: getstatic 35	com/amazon/android/c/a:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   93: ldc 103
    //   95: aload 4
    //   97: invokevirtual 107	com/amazon/android/framework/util/KiwiLogger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   100: aload_1
    //   101: invokestatic 97	com/amazon/android/framework/util/a:a	(Ljava/io/OutputStream;)V
    //   104: goto -32 -> 72
    //   107: astore_1
    //   108: aload_0
    //   109: monitorexit
    //   110: aload_1
    //   111: athrow
    //   112: astore_1
    //   113: aload_3
    //   114: invokestatic 97	com/amazon/android/framework/util/a:a	(Ljava/io/OutputStream;)V
    //   117: aload_1
    //   118: athrow
    //   119: astore_1
    //   120: aload 4
    //   122: astore_3
    //   123: goto -10 -> 113
    //   126: astore_3
    //   127: aload 4
    //   129: astore_1
    //   130: aload_3
    //   131: astore 4
    //   133: goto -53 -> 80
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	136	0	this	a
    //   0	136	1	paramString	String
    //   14	14	2	i	int
    //   42	81	3	localObject1	Object
    //   126	5	3	localException1	Exception
    //   39	29	4	localObject2	Object
    //   75	53	4	localException2	Exception
    //   131	1	4	localObject3	Object
    //   44	34	5	localObject4	Object
    // Exception table:
    //   from	to	target	type
    //   46	58	75	java/lang/Exception
    //   2	41	107	finally
    //   67	72	107	finally
    //   100	104	107	finally
    //   113	119	107	finally
    //   46	58	112	finally
    //   82	88	112	finally
    //   90	100	112	finally
    //   58	67	119	finally
    //   58	67	126	java/lang/Exception
  }
  
  private b b(String paramString)
  {
    try
    {
      Object localObject = c(paramString);
      localObject = (b)com.amazon.android.u.a.a(this.c.b((String)localObject));
      return (b)localObject;
    }
    catch (Exception localException)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Failed to load crash report: " + paramString);
      }
    }
    return null;
  }
  
  private boolean b()
  {
    return this.f.size() >= 5;
  }
  
  /* Error */
  private static String c(String paramString)
    throws java.io.IOException
  {
    // Byte code:
    //   0: new 56	java/lang/StringBuilder
    //   3: dup
    //   4: invokespecial 57	java/lang/StringBuilder:<init>	()V
    //   7: astore_2
    //   8: aconst_null
    //   9: astore_1
    //   10: new 139	java/io/BufferedReader
    //   13: dup
    //   14: new 141	java/io/FileReader
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 142	java/io/FileReader:<init>	(Ljava/lang/String;)V
    //   22: invokespecial 145	java/io/BufferedReader:<init>	(Ljava/io/Reader;)V
    //   25: astore_0
    //   26: aload_0
    //   27: invokevirtual 148	java/io/BufferedReader:ready	()Z
    //   30: ifeq +26 -> 56
    //   33: aload_2
    //   34: aload_0
    //   35: invokevirtual 151	java/io/BufferedReader:readLine	()Ljava/lang/String;
    //   38: invokevirtual 63	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: pop
    //   42: goto -16 -> 26
    //   45: astore_2
    //   46: aload_0
    //   47: astore_1
    //   48: aload_2
    //   49: astore_0
    //   50: aload_1
    //   51: invokestatic 153	com/amazon/android/framework/util/a:a	(Ljava/io/Reader;)V
    //   54: aload_0
    //   55: athrow
    //   56: aload_0
    //   57: invokestatic 153	com/amazon/android/framework/util/a:a	(Ljava/io/Reader;)V
    //   60: aload_2
    //   61: invokevirtual 72	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   64: areturn
    //   65: astore_0
    //   66: goto -16 -> 50
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	69	0	paramString	String
    //   9	42	1	str	String
    //   7	27	2	localStringBuilder	StringBuilder
    //   45	16	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   26	42	45	finally
    //   10	26	65	finally
  }
  
  private static void d(String paramString)
  {
    try
    {
      new File(paramString).delete();
      return;
    }
    catch (Exception localException)
    {
      while (!KiwiLogger.ERROR_ON) {}
      a.error("Cannot delete file: " + paramString, localException);
    }
  }
  
  public final List a()
  {
    if (b()) {
      localObject = Collections.emptyList();
    }
    ArrayList localArrayList;
    String[] arrayOfString;
    int i;
    do
    {
      do
      {
        return (List)localObject;
        localArrayList = new ArrayList();
        arrayOfString = new File(this.d.getFilesDir().getAbsolutePath() + "/").list(new c(this));
        i = 0;
        localObject = localArrayList;
      } while (i >= arrayOfString.length);
      localObject = localArrayList;
    } while (b());
    Object localObject = arrayOfString[i];
    localObject = this.d.getFilesDir().getAbsolutePath() + "/" + (String)localObject;
    b localb = b((String)localObject);
    if (localb != null)
    {
      this.f.put(localb, localObject);
      localArrayList.add(localb);
    }
    for (;;)
    {
      i += 1;
      break;
      d((String)localObject);
    }
  }
  
  public final void a(List paramList)
  {
    paramList = paramList.iterator();
    while (paramList.hasNext())
    {
      b localb = (b)paramList.next();
      d((String)this.f.get(localb));
      this.f.remove(localb);
    }
  }
  
  public final void onResourcesPopulated()
  {
    
    if (!(Thread.getDefaultUncaughtExceptionHandler() instanceof d))
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Registering Crash Handler");
      }
      this.e = Thread.getDefaultUncaughtExceptionHandler();
      Thread.setDefaultUncaughtExceptionHandler(this);
    }
  }
  
  public final void uncaughtException(Thread paramThread, Throwable paramThrowable)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Crash detected", paramThrowable);
    }
    try
    {
      String str = com.amazon.android.u.a.a(new b(this.d, paramThrowable));
      a(this.c.a(str));
    }
    catch (Throwable localThrowable1)
    {
      try
      {
        for (;;)
        {
          this.b.a(new com.amazon.android.a.b());
          if (KiwiLogger.TRACE_ON) {
            a.trace("Calling previous handler");
          }
          if (this.e != null) {
            this.e.uncaughtException(paramThread, paramThrowable);
          }
          return;
          localThrowable1 = localThrowable1;
          if (KiwiLogger.ERROR_ON) {
            a.error("Could not handle uncaught exception", localThrowable1);
          }
        }
      }
      catch (Throwable localThrowable2)
      {
        for (;;)
        {
          if (KiwiLogger.TRACE_ON) {
            a.trace("Error occured while handling exception", localThrowable2);
          }
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\c\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */