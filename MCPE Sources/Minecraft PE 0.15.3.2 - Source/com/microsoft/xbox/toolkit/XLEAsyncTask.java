package com.microsoft.xbox.toolkit;

import com.microsoft.xbox.toolkit.network.XLEThreadPool;

public abstract class XLEAsyncTask<Result>
{
  protected boolean cancelled = false;
  private XLEAsyncTask chainedTask = null;
  private Runnable doBackgroundAndPostExecuteRunnable = null;
  protected boolean isBusy = false;
  private XLEThreadPool threadPool = null;
  
  public XLEAsyncTask(XLEThreadPool paramXLEThreadPool)
  {
    this.threadPool = paramXLEThreadPool;
    this.doBackgroundAndPostExecuteRunnable = new Runnable()
    {
      public void run()
      {
        if (!XLEAsyncTask.this.cancelled) {}
        for (final Object localObject = XLEAsyncTask.this.doInBackground();; localObject = null)
        {
          ThreadManager.UIThreadPost(new Runnable()
          {
            public void run()
            {
              XLEAsyncTask.this.isBusy = false;
              if (!XLEAsyncTask.this.cancelled)
              {
                XLEAsyncTask.this.onPostExecute(localObject);
                if (XLEAsyncTask.this.chainedTask != null) {
                  XLEAsyncTask.this.chainedTask.execute();
                }
              }
            }
          });
          return;
        }
      }
    };
  }
  
  public static void executeAll(XLEAsyncTask... paramVarArgs)
  {
    if (paramVarArgs.length > 0)
    {
      int i = 0;
      while (i < paramVarArgs.length - 1)
      {
        paramVarArgs[i].chainedTask = paramVarArgs[(i + 1)];
        i += 1;
      }
      paramVarArgs[0].execute();
    }
  }
  
  public void cancel()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      this.cancelled = true;
      return;
    }
  }
  
  protected abstract Result doInBackground();
  
  public void execute()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      this.cancelled = false;
      this.isBusy = true;
      onPreExecute();
      executeBackground();
      return;
    }
  }
  
  protected void executeBackground()
  {
    this.cancelled = false;
    this.threadPool.run(this.doBackgroundAndPostExecuteRunnable);
  }
  
  public boolean getIsBusy()
  {
    return (this.isBusy) && (!this.cancelled);
  }
  
  protected abstract void onPostExecute(Result paramResult);
  
  protected abstract void onPreExecute();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\XLEAsyncTask.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */