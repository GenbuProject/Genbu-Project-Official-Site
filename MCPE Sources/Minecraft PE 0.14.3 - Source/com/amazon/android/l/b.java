package com.amazon.android.l;

import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.Task;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class b
  extends c
  implements com.amazon.android.framework.resource.b
{
  private static final KiwiLogger a = new KiwiLogger("TaskWorkflow");
  @Resource
  private com.amazon.android.framework.resource.a b;
  private List c = new ArrayList();
  private AtomicBoolean d = new AtomicBoolean(false);
  
  protected void a() {}
  
  protected final void a(Task paramTask)
  {
    com.amazon.android.d.a.a(paramTask, "task");
    this.c.add(paramTask);
    if ((paramTask instanceof a)) {
      ((a)paramTask).setWorkflow(this);
    }
  }
  
  protected abstract String b();
  
  public final void c()
  {
    this.d.set(true);
  }
  
  /* Error */
  public final void execute()
  {
    // Byte code:
    //   0: getstatic 71	com/amazon/android/framework/util/KiwiLogger:TRACE_ON	Z
    //   3: ifeq +28 -> 31
    //   6: getstatic 27	com/amazon/android/l/b:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   9: new 73	java/lang/StringBuilder
    //   12: dup
    //   13: invokespecial 74	java/lang/StringBuilder:<init>	()V
    //   16: ldc 76
    //   18: invokevirtual 80	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   21: aload_0
    //   22: invokevirtual 83	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   25: invokevirtual 86	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   28: invokevirtual 89	com/amazon/android/framework/util/KiwiLogger:trace	(Ljava/lang/String;)V
    //   31: aload_0
    //   32: getfield 35	com/amazon/android/l/b:c	Ljava/util/List;
    //   35: invokeinterface 93 1 0
    //   40: astore_1
    //   41: aload_1
    //   42: invokeinterface 99 1 0
    //   47: ifeq +37 -> 84
    //   50: aload_1
    //   51: invokeinterface 103 1 0
    //   56: checkcast 105	com/amazon/android/framework/task/Task
    //   59: astore_2
    //   60: aload_0
    //   61: getfield 42	com/amazon/android/l/b:d	Ljava/util/concurrent/atomic/AtomicBoolean;
    //   64: invokevirtual 108	java/util/concurrent/atomic/AtomicBoolean:get	()Z
    //   67: ifeq +22 -> 89
    //   70: getstatic 71	com/amazon/android/framework/util/KiwiLogger:TRACE_ON	Z
    //   73: ifeq +11 -> 84
    //   76: getstatic 27	com/amazon/android/l/b:a	Lcom/amazon/android/framework/util/KiwiLogger;
    //   79: ldc 110
    //   81: invokevirtual 89	com/amazon/android/framework/util/KiwiLogger:trace	(Ljava/lang/String;)V
    //   84: aload_0
    //   85: invokevirtual 112	com/amazon/android/l/b:a	()V
    //   88: return
    //   89: aload_2
    //   90: invokeinterface 114 1 0
    //   95: goto -54 -> 41
    //   98: astore_1
    //   99: aload_0
    //   100: invokevirtual 112	com/amazon/android/l/b:a	()V
    //   103: aload_1
    //   104: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	105	0	this	b
    //   40	11	1	localIterator	Iterator
    //   98	6	1	localObject	Object
    //   59	31	2	localTask	Task
    // Exception table:
    //   from	to	target	type
    //   0	31	98	finally
    //   31	41	98	finally
    //   41	84	98	finally
    //   89	95	98	finally
  }
  
  public final void onResourcesPopulated()
  {
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext())
    {
      Task localTask = (Task)localIterator.next();
      this.b.b(localTask);
    }
  }
  
  public final String toString()
  {
    return b();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\l\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */