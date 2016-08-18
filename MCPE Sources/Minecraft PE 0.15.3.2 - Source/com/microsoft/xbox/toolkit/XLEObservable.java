package com.microsoft.xbox.toolkit;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;

public abstract class XLEObservable<T>
{
  private HashSet<XLEObserver<T>> data = new HashSet();
  
  /* Error */
  public void addObserver(XLEObserver<T> paramXLEObserver)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 26	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   5: getstatic 32	com/microsoft/xbox/toolkit/ThreadManager:UIThread	Ljava/lang/Thread;
    //   8: if_acmpne +21 -> 29
    //   11: iconst_1
    //   12: istore_2
    //   13: iload_2
    //   14: invokestatic 38	com/microsoft/xbox/toolkit/XLEAssert:assertTrue	(Z)V
    //   17: aload_0
    //   18: getfield 17	com/microsoft/xbox/toolkit/XLEObservable:data	Ljava/util/HashSet;
    //   21: aload_1
    //   22: invokevirtual 42	java/util/HashSet:add	(Ljava/lang/Object;)Z
    //   25: pop
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: iconst_0
    //   30: istore_2
    //   31: goto -18 -> 13
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	XLEObservable
    //   0	39	1	paramXLEObserver	XLEObserver<T>
    //   12	19	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	11	34	finally
    //   13	26	34	finally
  }
  
  public void addUniqueObserver(XLEObserver<T> paramXLEObserver)
  {
    try
    {
      if (!this.data.contains(paramXLEObserver)) {
        addObserver(paramXLEObserver);
      }
      return;
    }
    finally
    {
      paramXLEObserver = finally;
      throw paramXLEObserver;
    }
  }
  
  /* Error */
  protected void clearObserver()
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 26	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   5: getstatic 32	com/microsoft/xbox/toolkit/ThreadManager:UIThread	Ljava/lang/Thread;
    //   8: if_acmpne +19 -> 27
    //   11: iconst_1
    //   12: istore_1
    //   13: iload_1
    //   14: invokestatic 38	com/microsoft/xbox/toolkit/XLEAssert:assertTrue	(Z)V
    //   17: aload_0
    //   18: getfield 17	com/microsoft/xbox/toolkit/XLEObservable:data	Ljava/util/HashSet;
    //   21: invokevirtual 54	java/util/HashSet:clear	()V
    //   24: aload_0
    //   25: monitorexit
    //   26: return
    //   27: iconst_0
    //   28: istore_1
    //   29: goto -16 -> 13
    //   32: astore_2
    //   33: aload_0
    //   34: monitorexit
    //   35: aload_2
    //   36: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	37	0	this	XLEObservable
    //   12	17	1	bool	boolean
    //   32	4	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   2	11	32	finally
    //   13	24	32	finally
  }
  
  protected ArrayList<XLEObserver<T>> getObservers()
  {
    try
    {
      ArrayList localArrayList = new ArrayList(this.data);
      return localArrayList;
    }
    finally
    {
      localObject = finally;
      throw ((Throwable)localObject);
    }
  }
  
  public void notifyObservers(AsyncResult<T> paramAsyncResult)
  {
    for (;;)
    {
      try
      {
        if (Thread.currentThread() == ThreadManager.UIThread)
        {
          bool = true;
          XLEAssert.assertTrue(bool);
          Iterator localIterator = new ArrayList(this.data).iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          ((XLEObserver)localIterator.next()).update(paramAsyncResult);
          continue;
        }
        boolean bool = false;
      }
      finally {}
    }
  }
  
  /* Error */
  public void removeObserver(XLEObserver<T> paramXLEObserver)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: invokestatic 26	java/lang/Thread:currentThread	()Ljava/lang/Thread;
    //   5: getstatic 32	com/microsoft/xbox/toolkit/ThreadManager:UIThread	Ljava/lang/Thread;
    //   8: if_acmpne +21 -> 29
    //   11: iconst_1
    //   12: istore_2
    //   13: iload_2
    //   14: invokestatic 38	com/microsoft/xbox/toolkit/XLEAssert:assertTrue	(Z)V
    //   17: aload_0
    //   18: getfield 17	com/microsoft/xbox/toolkit/XLEObservable:data	Ljava/util/HashSet;
    //   21: aload_1
    //   22: invokevirtual 90	java/util/HashSet:remove	(Ljava/lang/Object;)Z
    //   25: pop
    //   26: aload_0
    //   27: monitorexit
    //   28: return
    //   29: iconst_0
    //   30: istore_2
    //   31: goto -18 -> 13
    //   34: astore_1
    //   35: aload_0
    //   36: monitorexit
    //   37: aload_1
    //   38: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	39	0	this	XLEObservable
    //   0	39	1	paramXLEObserver	XLEObserver<T>
    //   12	19	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	11	34	finally
    //   13	26	34	finally
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\XLEObservable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */