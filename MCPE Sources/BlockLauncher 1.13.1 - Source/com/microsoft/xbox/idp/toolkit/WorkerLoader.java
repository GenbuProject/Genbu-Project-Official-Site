package com.microsoft.xbox.idp.toolkit;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Loader;
import android.os.Build.VERSION;
import android.os.Handler;

public abstract class WorkerLoader<D>
  extends Loader<D>
{
  private final Handler dispatcher = new Handler();
  private final Object lock = new Object();
  private D result;
  private ResultListener<D> resultListener;
  private final Worker<D> worker;
  
  public WorkerLoader(Context paramContext, Worker<D> paramWorker)
  {
    super(paramContext);
    this.worker = paramWorker;
  }
  
  @SuppressLint({"NewApi"})
  private boolean cancelLoadCompat()
  {
    if (Build.VERSION.SDK_INT < 16) {
      return onCancelLoad();
    }
    return cancelLoad();
  }
  
  public void deliverResult(D paramD)
  {
    if (isReset()) {
      if (paramD != null) {
        releaseData(paramD);
      }
    }
    Object localObject;
    do
    {
      return;
      localObject = this.result;
      this.result = paramD;
      if (isStarted()) {
        super.deliverResult(paramD);
      }
    } while ((localObject == null) || (localObject == paramD) || (isDataReleased(localObject)));
    releaseData(localObject);
  }
  
  protected abstract boolean isDataReleased(D paramD);
  
  protected boolean onCancelLoad()
  {
    synchronized (this.lock)
    {
      if (this.resultListener != null)
      {
        this.worker.cancel();
        this.resultListener = null;
        return true;
      }
      return false;
    }
  }
  
  public void onCanceled(D paramD)
  {
    if ((paramD != null) && (!isDataReleased(paramD))) {
      releaseData(paramD);
    }
  }
  
  protected void onForceLoad()
  {
    super.onForceLoad();
    cancelLoadCompat();
    synchronized (this.lock)
    {
      this.resultListener = new ResultListenerImpl(null);
      this.worker.start(this.resultListener);
      return;
    }
  }
  
  protected void onReset()
  {
    cancelLoadCompat();
    if ((this.result != null) && (!isDataReleased(this.result))) {
      releaseData(this.result);
    }
    this.result = null;
  }
  
  protected void onStartLoading()
  {
    if (this.result != null) {
      deliverResult(this.result);
    }
    if ((takeContentChanged()) || (this.result == null)) {
      forceLoad();
    }
  }
  
  protected void onStopLoading()
  {
    cancelLoadCompat();
  }
  
  protected abstract void releaseData(D paramD);
  
  public static abstract interface ResultListener<D>
  {
    public abstract void onResult(D paramD);
  }
  
  private class ResultListenerImpl
    implements WorkerLoader.ResultListener<D>
  {
    private ResultListenerImpl() {}
    
    public void onResult(final D paramD)
    {
      for (;;)
      {
        synchronized (WorkerLoader.this.lock)
        {
          if (this != WorkerLoader.this.resultListener)
          {
            bool = true;
            WorkerLoader.access$202(WorkerLoader.this, null);
            WorkerLoader.this.dispatcher.post(new Runnable()
            {
              public void run()
              {
                if (bool)
                {
                  WorkerLoader.this.onCanceled(paramD);
                  return;
                }
                WorkerLoader.this.deliverResult(paramD);
              }
            });
            return;
          }
        }
        final boolean bool = false;
      }
    }
  }
  
  public static abstract interface Worker<D>
  {
    public abstract void cancel();
    
    public abstract void start(WorkerLoader.ResultListener<D> paramResultListener);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\toolkit\WorkerLoader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */