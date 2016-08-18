package com.microsoft.xbox.toolkit;

public class Ready
{
  private boolean ready = false;
  private Object syncObj = new Object();
  
  public boolean getIsReady()
  {
    synchronized (this.syncObj)
    {
      boolean bool = this.ready;
      return bool;
    }
  }
  
  public void reset()
  {
    synchronized (this.syncObj)
    {
      this.ready = false;
      return;
    }
  }
  
  public void setReady()
  {
    synchronized (this.syncObj)
    {
      this.ready = true;
      this.syncObj.notifyAll();
      return;
    }
  }
  
  public void waitForReady()
  {
    waitForReady(0);
  }
  
  public void waitForReady(int paramInt)
  {
    synchronized (this.syncObj)
    {
      boolean bool = this.ready;
      if ((bool) || (paramInt > 0)) {}
      try
      {
        this.syncObj.wait(paramInt);
        for (;;)
        {
          return;
          this.syncObj.wait();
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        for (;;)
        {
          Thread.currentThread().interrupt();
        }
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\Ready.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */