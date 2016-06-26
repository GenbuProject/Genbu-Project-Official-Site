package com.microsoft.cll.android;

import Microsoft.Android.LoggingLibrary.Snapshot;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SnapshotScheduler
  extends ScheduledWorker
{
  private final String TAG = "SnapshotScheduler";
  private final ClientTelemetry clientTelemetry;
  private final ICll cll;
  private final ILogger logger;
  
  public SnapshotScheduler(ClientTelemetry paramClientTelemetry, ILogger paramILogger, ICll paramICll)
  {
    super(SettingsStore.getCllSettingsAsLong(SettingsStore.Settings.SNAPSHOTSCHEDULEINTERVAL));
    this.cll = paramICll;
    this.clientTelemetry = paramClientTelemetry;
    this.logger = paramILogger;
  }
  
  private void recordStatistics()
  {
    Snapshot localSnapshot = this.clientTelemetry.GetEvent();
    this.cll.log(localSnapshot, new EventSensitivity[0]);
    this.clientTelemetry.Reset();
  }
  
  public void resume(ScheduledExecutorService paramScheduledExecutorService)
  {
    this.executor = paramScheduledExecutorService;
    this.nextExecution = paramScheduledExecutorService.scheduleAtFixedRate(this, this.interval, this.interval, TimeUnit.SECONDS);
    this.isPaused = false;
  }
  
  public void run()
  {
    this.logger.info("SnapshotScheduler", "Uploading snapshot");
    if (this.interval != SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.SNAPSHOTSCHEDULEINTERVAL))
    {
      this.nextExecution.cancel(false);
      this.interval = SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.SNAPSHOTSCHEDULEINTERVAL);
      this.nextExecution = this.executor.scheduleAtFixedRate(this, this.interval, this.interval, TimeUnit.SECONDS);
    }
    recordStatistics();
  }
  
  public void start(ScheduledExecutorService paramScheduledExecutorService)
  {
    this.executor = paramScheduledExecutorService;
    this.nextExecution = paramScheduledExecutorService.scheduleAtFixedRate(this, this.interval, this.interval, TimeUnit.SECONDS);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\SnapshotScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */