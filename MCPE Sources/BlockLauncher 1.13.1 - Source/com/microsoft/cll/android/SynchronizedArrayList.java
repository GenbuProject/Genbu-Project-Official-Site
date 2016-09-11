package com.microsoft.cll.android;

import java.util.ArrayList;

public class SynchronizedArrayList<T>
  extends ArrayList<T>
{
  /* Error */
  public boolean add(T paramT)
  {
    // Byte code:
    //   0: aload_0
    //   1: monitorenter
    //   2: aload_0
    //   3: aload_1
    //   4: invokevirtual 15	com/microsoft/cll/android/SynchronizedArrayList:contains	(Ljava/lang/Object;)Z
    //   7: istore_2
    //   8: iload_2
    //   9: ifeq +9 -> 18
    //   12: iconst_0
    //   13: istore_2
    //   14: aload_0
    //   15: monitorexit
    //   16: iload_2
    //   17: ireturn
    //   18: aload_0
    //   19: aload_1
    //   20: invokespecial 17	java/util/ArrayList:add	(Ljava/lang/Object;)Z
    //   23: istore_2
    //   24: goto -10 -> 14
    //   27: astore_1
    //   28: aload_0
    //   29: monitorexit
    //   30: aload_1
    //   31: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	32	0	this	SynchronizedArrayList
    //   0	32	1	paramT	T
    //   7	17	2	bool	boolean
    // Exception table:
    //   from	to	target	type
    //   2	8	27	finally
    //   18	24	27	finally
  }
  
  public boolean remove(Object paramObject)
  {
    try
    {
      boolean bool = super.remove(paramObject);
      return bool;
    }
    finally
    {
      paramObject = finally;
      throw ((Throwable)paramObject);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\SynchronizedArrayList.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */