package com.facebook.internal;

import com.facebook.FacebookSdk;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.FutureTask;

public class LockOnGetVariable<T>
{
  private CountDownLatch initLatch;
  private T value;
  
  public LockOnGetVariable(T paramT)
  {
    this.value = paramT;
  }
  
  public LockOnGetVariable(final Callable<T> paramCallable)
  {
    this.initLatch = new CountDownLatch(1);
    FacebookSdk.getExecutor().execute(new FutureTask(new Callable()
    {
      public Void call()
        throws Exception
      {
        try
        {
          LockOnGetVariable.access$002(LockOnGetVariable.this, paramCallable.call());
          return null;
        }
        finally
        {
          LockOnGetVariable.this.initLatch.countDown();
        }
      }
    }));
  }
  
  private void waitOnInit()
  {
    if (this.initLatch == null) {
      return;
    }
    try
    {
      this.initLatch.await();
      return;
    }
    catch (InterruptedException localInterruptedException) {}
  }
  
  public T getValue()
  {
    waitOnInit();
    return (T)this.value;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\internal\LockOnGetVariable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */