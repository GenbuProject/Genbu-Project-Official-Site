package com.microsoft.cll.android;

import Microsoft.Android.LoggingLibrary.Snapshot;
import Ms.Telemetry.CllHeartBeat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.TimeZone;

public class ClientTelemetry
{
  private ArrayList<Integer> settingsCallLatencies = new ArrayList();
  protected CllHeartBeat snapshot = new CllHeartBeat();
  private ArrayList<Integer> vortexCallLatencies = new ArrayList();
  
  public ClientTelemetry()
  {
    Reset();
  }
  
  protected Snapshot GetEvent()
  {
    Snapshot localSnapshot = new Snapshot();
    localSnapshot.setBaseData(this.snapshot);
    return localSnapshot;
  }
  
  protected void IncrementEventsDroppedDueToQuota()
  {
    int i = this.snapshot.getQuotaDropCount();
    this.snapshot.setQuotaDropCount(i + 1);
  }
  
  protected void IncrementEventsQueuedForUpload()
  {
    IncrementEventsQueuedForUpload(1);
  }
  
  protected void IncrementEventsQueuedForUpload(int paramInt)
  {
    int i = this.snapshot.getEventsQueued();
    this.snapshot.setEventsQueued(i + paramInt);
  }
  
  protected void IncrementLogFailures()
  {
    int i = this.snapshot.getLogFailures();
    this.snapshot.setLogFailures(i + 1);
  }
  
  protected void IncrementRejectDropCount(int paramInt)
  {
    int i = this.snapshot.getRejectDropCount();
    this.snapshot.setRejectDropCount(i + paramInt);
  }
  
  protected void IncrementSettingsHttpAttempts()
  {
    int i = this.snapshot.getSettingsHttpAttempts();
    this.snapshot.setSettingsHttpAttempts(i + 1);
  }
  
  protected void IncrementSettingsHttpFailures(int paramInt)
  {
    int i = this.snapshot.getSettingsHttpFailures();
    this.snapshot.setSettingsHttpFailures(i + 1);
    if ((paramInt >= 400) && (paramInt < 500))
    {
      i = this.snapshot.getSettingsFailures4xx();
      this.snapshot.setSettingsFailures4xx(i + 1);
    }
    if ((paramInt >= 500) && (paramInt < 600))
    {
      i = this.snapshot.getSettingsFailures5xx();
      this.snapshot.setSettingsFailures5xx(i + 1);
    }
    if (paramInt == -1)
    {
      paramInt = this.snapshot.getSettingsFailuresTimeout();
      this.snapshot.setSettingsFailuresTimeout(paramInt + 1);
    }
  }
  
  protected void IncrementVortexHttpAttempts()
  {
    int i = this.snapshot.getVortexHttpAttempts();
    this.snapshot.setVortexHttpAttempts(i + 1);
  }
  
  protected void IncrementVortexHttpFailures(int paramInt)
  {
    int i = this.snapshot.getVortexHttpFailures();
    this.snapshot.setVortexHttpFailures(i + 1);
    if ((paramInt >= 400) && (paramInt < 500))
    {
      i = this.snapshot.getVortexFailures4xx();
      this.snapshot.setVortexFailures4xx(i + 1);
    }
    if ((paramInt >= 500) && (paramInt < 600))
    {
      i = this.snapshot.getVortexFailures5xx();
      this.snapshot.setVortexFailures5xx(i + 1);
    }
    if (paramInt == -1)
    {
      paramInt = this.snapshot.getVortexFailuresTimeout();
      this.snapshot.setVortexFailuresTimeout(paramInt + 1);
    }
  }
  
  protected void Reset()
  {
    SimpleDateFormat localSimpleDateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSSSSSS'Z'", Locale.US);
    localSimpleDateFormat.setTimeZone(TimeZone.getTimeZone("UTC"));
    this.snapshot.setLastHeartBeat(localSimpleDateFormat.format(new Date()).toString());
    this.snapshot.setEventsQueued(0);
    this.snapshot.setLogFailures(0);
    this.snapshot.setQuotaDropCount(0);
    this.snapshot.setRejectDropCount(0);
    this.snapshot.setVortexHttpAttempts(0);
    this.snapshot.setVortexHttpFailures(0);
    this.snapshot.setCacheUsagePercent(0.0D);
    this.snapshot.setAvgVortexLatencyMs(0);
    this.snapshot.setMaxVortexLatencyMs(0);
    this.snapshot.setSettingsHttpAttempts(0);
    this.snapshot.setSettingsHttpFailures(0);
    this.snapshot.setAvgSettingsLatencyMs(0);
    this.snapshot.setMaxSettingsLatencyMs(0);
    this.snapshot.setVortexFailures4xx(0);
    this.snapshot.setVortexFailures5xx(0);
    this.snapshot.setVortexFailuresTimeout(0);
    this.snapshot.setSettingsFailures4xx(0);
    this.snapshot.setSettingsFailures5xx(0);
    this.snapshot.setSettingsFailuresTimeout(0);
    this.settingsCallLatencies.clear();
    this.vortexCallLatencies.clear();
  }
  
  protected void SetAvgSettingsLatencyMs(int paramInt)
  {
    this.settingsCallLatencies.add(Integer.valueOf(paramInt));
    paramInt = 0;
    Iterator localIterator = this.settingsCallLatencies.iterator();
    while (localIterator.hasNext()) {
      paramInt += ((Integer)localIterator.next()).intValue();
    }
    paramInt /= this.settingsCallLatencies.size();
    this.snapshot.setAvgSettingsLatencyMs(paramInt);
  }
  
  protected void SetAvgVortexLatencyMs(int paramInt)
  {
    this.vortexCallLatencies.add(Integer.valueOf(paramInt));
    paramInt = 0;
    Iterator localIterator = this.vortexCallLatencies.iterator();
    while (localIterator.hasNext()) {
      paramInt += ((Integer)localIterator.next()).intValue();
    }
    paramInt /= this.vortexCallLatencies.size();
    this.snapshot.setAvgVortexLatencyMs(paramInt);
  }
  
  protected void SetCacheUsagePercent(double paramDouble)
  {
    this.snapshot.setCacheUsagePercent(paramDouble);
  }
  
  protected void SetMaxSettingsLatencyMs(int paramInt)
  {
    if (this.snapshot.getMaxSettingsLatencyMs() < paramInt) {
      this.snapshot.setMaxSettingsLatencyMs(paramInt);
    }
  }
  
  protected void SetMaxVortexLatencyMs(int paramInt)
  {
    if (this.snapshot.getMaxVortexLatencyMs() < paramInt) {
      this.snapshot.setMaxVortexLatencyMs(paramInt);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\cll\android\ClientTelemetry.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */