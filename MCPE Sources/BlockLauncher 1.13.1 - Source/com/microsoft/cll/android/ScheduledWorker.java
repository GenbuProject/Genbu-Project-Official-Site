package com.microsoft.cll.android;

import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public abstract class ScheduledWorker
  implements Runnable
{
  protected ScheduledExecutorService executor;
  protected long interval;
  protected boolean isPaused;
  protected ScheduledFuture nextExecution;
  
  public ScheduledWorker(long paramLong)
  {
    this.interval = paramLong;
    this.isPaused = false;
  }
  
  private void setupExecutor(ScheduledExecutorService paramScheduledExecutorService)
  {
    this.executor = paramScheduledExecutorService;
    this.nextExecution = paramScheduledExecutorService.scheduleAtFixedRate(this, 0L, this.interval, TimeUnit.SECONDS);
  }
  
  protected void pause()
  {
    this.nextExecution.cancel(false);
    this.isPaused = true;
  }
  
  protected void resume(ScheduledExecutorService paramScheduledExecutorService)
  {
    setupExecutor(paramScheduledExecutorService);
    this.isPaused = false;
  }
  
  public abstract void run();
  
  protected void start(ScheduledExecutorService paramScheduledExecutorService)
  {
    setupExecutor(paramScheduledExecutorService);
  }
  
  protected void stop()
  {
    this.nextExecution.cancel(true);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\ScheduledWorker.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */