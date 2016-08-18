package com.microsoft.cll.android;

import Microsoft.Android.LoggingLibrary.Snapshot;
import java.util.EnumSet;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class SnapshotScheduler
  extends ScheduledWorker
{
  private final String TAG = "AndroidCll-SnapshotScheduler";
  private final ClientTelemetry clientTelemetry;
  private final ISingletonCll cll;
  private final ILogger logger;
  
  public SnapshotScheduler(ClientTelemetry paramClientTelemetry, ILogger paramILogger, ISingletonCll paramISingletonCll)
  {
    super(SettingsStore.getCllSettingsAsLong(SettingsStore.Settings.SNAPSHOTSCHEDULEINTERVAL));
    this.cll = paramISingletonCll;
    this.clientTelemetry = paramClientTelemetry;
    this.logger = paramILogger;
  }
  
  private void recordStatistics()
  {
    Snapshot localSnapshot = this.clientTelemetry.GetEvent();
    this.cll.log(localSnapshot, EventEnums.Latency.LatencyUnspecified, EventEnums.Persistence.PersistenceUnspecified, EnumSet.of(EventEnums.Sensitivity.SensitivityUnspecified), -1.0D, null);
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
    this.logger.info("AndroidCll-SnapshotScheduler", "Uploading snapshot");
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\SnapshotScheduler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */