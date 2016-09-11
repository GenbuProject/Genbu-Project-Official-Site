package com.microsoft.cll.android;

import com.microsoft.telemetry.Base;
import java.util.ArrayList;
import java.util.EnumSet;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.atomic.AtomicBoolean;

public class SingletonCll
  implements ISingletonCll
{
  private static SingletonCll Instance;
  private static Object InstanceLock = new Object();
  protected final String TAG = "AndroidCll-SingletonCll";
  protected final ClientTelemetry clientTelemetry;
  protected final List<ICllEvents> cllEvents;
  public CorrelationVector correlationVector;
  protected EventHandler eventHandler;
  private ScheduledExecutorService executor;
  private final AtomicBoolean isChanging;
  private final AtomicBoolean isPaused;
  private final AtomicBoolean isStarted;
  protected ILogger logger;
  protected PartA partA;
  protected SettingsSync settingsSync;
  protected SnapshotScheduler snapshotScheduler;
  private ITicketCallback ticketCallback;
  
  private SingletonCll(String paramString1, ILogger paramILogger, String paramString2, PartA paramPartA, CorrelationVector paramCorrelationVector)
  {
    if ((paramString1 == null) || (paramString1 == "")) {
      throw new IllegalArgumentException("iKey cannot be null or \"\"");
    }
    paramILogger.setVerbosity(Verbosity.NONE);
    this.correlationVector = paramCorrelationVector;
    this.logger = paramILogger;
    this.partA = paramPartA;
    this.clientTelemetry = new ClientTelemetry();
    this.cllEvents = new ArrayList();
    this.eventHandler = new EventHandler(this.clientTelemetry, this.cllEvents, paramILogger, paramString2);
    this.isChanging = new AtomicBoolean(false);
    this.isStarted = new AtomicBoolean(false);
    this.isPaused = new AtomicBoolean(false);
    this.settingsSync = new SettingsSync(this.clientTelemetry, paramILogger, paramString1, paramPartA);
    this.snapshotScheduler = new SnapshotScheduler(this.clientTelemetry, paramILogger, this);
    setEndpointUrl(SettingsStore.getCllSettingsAsString(SettingsStore.Settings.VORTEXPRODURL));
  }
  
  public static ISingletonCll getInstance(String paramString1, ILogger paramILogger, String paramString2, PartA paramPartA, CorrelationVector paramCorrelationVector)
  {
    if (Instance == null) {}
    synchronized (InstanceLock)
    {
      if (Instance == null) {
        Instance = new SingletonCll(paramString1, paramILogger, paramString2, paramPartA, paramCorrelationVector);
      }
      return Instance;
    }
  }
  
  public void SubscribeCllEvents(ICllEvents paramICllEvents) {}
  
  public String getAppUserId()
  {
    return this.partA.getAppUserId();
  }
  
  public void log(Base paramBase, EventEnums.Latency paramLatency, EventEnums.Persistence paramPersistence, EnumSet<EventEnums.Sensitivity> paramEnumSet, double paramDouble, List<String> paramList)
  {
    if (!this.isStarted.get())
    {
      this.logger.error("AndroidCll-SingletonCll", "Cll must be started before logging events");
      return;
    }
    if ((paramList != null) && (this.ticketCallback == null))
    {
      this.logger.error("AndroidCll-SingletonCll", "You must set the ticket callback if you want to log ids with your events");
      return;
    }
    paramBase = this.partA.populate(paramBase, paramLatency, paramPersistence, paramEnumSet, paramDouble, paramList);
    this.eventHandler.log(paramBase, paramList);
  }
  
  public void pause()
  {
    if (this.isChanging.compareAndSet(false, true))
    {
      if ((this.isStarted.get()) && (!this.isPaused.get()))
      {
        this.eventHandler.pause();
        this.settingsSync.pause();
        this.snapshotScheduler.pause();
        this.executor.shutdown();
        this.isPaused.set(true);
      }
      this.isChanging.set(false);
    }
  }
  
  public void resume()
  {
    if (this.isChanging.compareAndSet(false, true))
    {
      if ((this.isStarted.get()) && (this.isPaused.get()))
      {
        this.executor = Executors.newScheduledThreadPool(SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.THREADSTOUSEWITHEXECUTOR));
        this.snapshotScheduler.resume(this.executor);
        this.eventHandler.resume(this.executor);
        this.settingsSync.resume(this.executor);
        this.isPaused.set(false);
      }
      this.isChanging.set(false);
    }
  }
  
  public void send()
  {
    if (this.isStarted.get())
    {
      this.eventHandler.send();
      return;
    }
    this.logger.info("AndroidCll-SingletonCll", "Cannot send while the CLL is stopped.");
  }
  
  public void setAppUserId(String paramString)
  {
    this.partA.setAppUserId(paramString);
  }
  
  public void setDebugVerbosity(Verbosity paramVerbosity)
  {
    this.logger.setVerbosity(paramVerbosity);
  }
  
  public void setEndpointUrl(String paramString)
  {
    this.eventHandler.setEndpointUrl(paramString);
  }
  
  protected void setEventSender(EventSender paramEventSender)
  {
    this.eventHandler.setSender(paramEventSender);
  }
  
  public void setExperimentId(String paramString)
  {
    this.partA.setExpId(paramString);
  }
  
  public void setXuidCallback(ITicketCallback paramITicketCallback)
  {
    this.ticketCallback = paramITicketCallback;
    if ((this.isStarted.get()) || (this.isPaused.get()))
    {
      this.logger.warn("AndroidCll-SingletonCll", "Xuid callback must be set before start.");
      return;
    }
    this.eventHandler.setXuidCallback(paramITicketCallback);
  }
  
  public void start()
  {
    if (this.isChanging.compareAndSet(false, true))
    {
      if (!this.isStarted.get())
      {
        this.executor = Executors.newScheduledThreadPool(3);
        this.snapshotScheduler.start(this.executor);
        this.eventHandler.start(this.executor);
        this.settingsSync.start(this.executor);
        this.isStarted.set(true);
      }
      this.isChanging.set(false);
    }
  }
  
  public void stop()
  {
    if (this.isChanging.compareAndSet(false, true))
    {
      if (this.isStarted.get())
      {
        this.eventHandler.stop();
        this.settingsSync.stop();
        this.snapshotScheduler.stop();
        this.executor.shutdown();
        this.isStarted.set(false);
      }
      Iterator localIterator = this.cllEvents.iterator();
      while (localIterator.hasNext()) {
        ((ICllEvents)localIterator.next()).stopped();
      }
      this.isChanging.set(false);
    }
  }
  
  public void synchronize()
  {
    this.eventHandler.synchronize();
  }
  
  public void useLegacyCS(boolean paramBoolean)
  {
    this.partA.useLegacyCS(paramBoolean);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\SingletonCll.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */