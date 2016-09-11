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
    Object localObject3 = null;
    for (;;)
    {
      try
      {
        localObject7 = this.syncObject;
        localObject1 = localObject3;
      }
      catch (InterruptedException localInterruptedException2)
      {
        Object localObject7;
        Object localObject1;
        Object localObject2;
        return null;
      }
      try
      {
        if (!this.queue.isEmpty()) {
          continue;
        }
        localObject1 = localObject3;
        this.syncObject.wait();
        continue;
      }
      finally
      {
        localObject6 = localInterruptedException2;
        continue;
      }
      localObject1 = localObject6;
      try
      {
        throw ((Throwable)localObject3);
      }
      catch (InterruptedException localInterruptedException1)
      {
        return (T)localObject6;
      }
      localObject2 = localObject3;
      localObject6 = this.queue.remove();
      try
      {
        this.hashSet.remove(localObject6);
        return (T)localObject6;
      }
      finally
      {
        localObject4 = finally;
      }
    }
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ThreadSafePriorityQueue.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */