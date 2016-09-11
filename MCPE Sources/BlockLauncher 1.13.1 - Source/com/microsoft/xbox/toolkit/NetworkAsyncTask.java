package com.microsoft.xbox.toolkit;

import com.microsoft.xbox.toolkit.network.XLEThreadPool;

public abstract class NetworkAsyncTask<T>
  extends XLEAsyncTask<T>
{
  protected boolean forceLoad = true;
  private boolean shouldExecute = true;
  
  public NetworkAsyncTask()
  {
    super(XLEThreadPool.networkOperationsThreadPool);
  }
  
  public NetworkAsyncTask(XLEThreadPool paramXLEThreadPool)
  {
    super(XLEThreadPool.networkOperationsThreadPool);
  }
  
  protected abstract boolean checkShouldExecute();
  
  protected final T doInBackground()
  {
    try
    {
      Object localObject = loadDataInBackground();
      return (T)localObject;
    }
    catch (Exception localException) {}
    return (T)onError();
  }
  
  public void execute()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      if (this.cancelled) {}
      this.shouldExecute = checkShouldExecute();
      if ((!this.shouldExecute) && (!this.forceLoad)) {
        break;
      }
      this.isBusy = true;
      onPreExecute();
      super.executeBackground();
      return;
    }
    onNoAction();
    this.isBusy = false;
  }
  
  public void load(boolean paramBoolean)
  {
    this.forceLoad = paramBoolean;
    execute();
  }
  
  protected abstract T loadDataInBackground();
  
  protected abstract T onError();
  
  protected abstract void onNoAction();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\NetworkAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */