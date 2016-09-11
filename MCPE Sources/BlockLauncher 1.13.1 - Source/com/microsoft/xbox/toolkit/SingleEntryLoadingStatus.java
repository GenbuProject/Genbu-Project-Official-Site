package com.microsoft.xbox.toolkit;

public class SingleEntryLoadingStatus
{
  private boolean isLoading = false;
  private XLEException lastError = null;
  private Object syncObj = new Object();
  
  private void setDone(XLEException paramXLEException)
  {
    synchronized (this.syncObj)
    {
      this.isLoading = false;
      this.lastError = paramXLEException;
      this.syncObj.notifyAll();
      return;
    }
  }
  
  public boolean getIsLoading()
  {
    return this.isLoading;
  }
  
  public XLEException getLastError()
  {
    return this.lastError;
  }
  
  public void reset()
  {
    synchronized (this.syncObj)
    {
      this.isLoading = false;
      this.lastError = null;
      this.syncObj.notifyAll();
      return;
    }
  }
  
  public void setFailed(XLEException paramXLEException)
  {
    setDone(paramXLEException);
  }
  
  public void setSuccess()
  {
    setDone(null);
  }
  
  public WaitResult waitForNotLoading()
  {
    synchronized (this.syncObj)
    {
      boolean bool = this.isLoading;
      if (bool) {
        try
        {
          this.syncObj.wait();
          WaitResult localWaitResult1 = new WaitResult(true, this.lastError);
          return localWaitResult1;
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
    this.isLoading = true;
    WaitResult localWaitResult2 = new WaitResult(false, null);
    return localWaitResult2;
  }
  
  public class WaitResult
  {
    public XLEException error;
    public boolean waited;
    
    public WaitResult(boolean paramBoolean, XLEException paramXLEException)
    {
      this.waited = paramBoolean;
      this.error = paramXLEException;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\SingleEntryLoadingStatus.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */