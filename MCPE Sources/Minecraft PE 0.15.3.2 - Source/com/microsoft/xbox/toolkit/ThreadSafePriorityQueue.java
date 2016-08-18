package com.microsoft.xbox.toolkit;

import java.util.HashSet;
import java.util.PriorityQueue;

public class ThreadSafePriorityQueue<T>
{
  private HashSet<T> hashSet = new HashSet();
  private PriorityQueue<T> queue = new PriorityQueue();
  private Object syncObject = new Object();
  
  public T pop()
  {
    Object localObject5 = null;
    Object localObject2 = null;
    Object localObject1 = localObject5;
    Object localObject6;
    try
    {
      localObject6 = this.syncObject;
      localObject1 = localObject5;
      for (;;)
      {
        localObject1 = localObject2;
        try
        {
          if (this.queue.isEmpty())
          {
            localObject1 = localObject2;
            this.syncObject.wait();
          }
        }
        finally {}
      }
      localObject1 = localInterruptedException;
    }
    catch (InterruptedException localInterruptedException)
    {
      return (T)localObject1;
    }
    Object localObject4 = this.queue.remove();
    localObject1 = localObject4;
    this.hashSet.remove(localObject4);
    localObject1 = localObject4;
    return (T)localObject4;
  }
  
  public void push(T paramT)
  {
    synchronized (this.syncObject)
    {
      if (!this.hashSet.contains(paramT))
      {
        this.queue.add(paramT);
        this.hashSet.add(paramT);
        this.syncObject.notifyAll();
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\ThreadSafePriorityQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */