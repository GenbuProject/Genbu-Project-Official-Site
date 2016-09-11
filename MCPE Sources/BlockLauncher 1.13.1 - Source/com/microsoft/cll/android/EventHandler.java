package com.microsoft.cll.android;

import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.List;
import java.util.concurrent.RejectedExecutionException;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledFuture;
import java.util.concurrent.TimeUnit;

public class EventHandler
  extends ScheduledWorker
{
  private final String TAG = "AndroidCll-EventHandler";
  private final ClientTelemetry clientTelemetry;
  private final List<ICllEvents> cllEvents;
  final AbstractHandler criticalHandler;
  private URL endpoint;
  private final ILogger logger;
  final AbstractHandler normalHandler;
  private double sampleId;
  private EventSender sender;
  private ITicketCallback ticketCallback;
  
  static
  {
    if (!EventHandler.class.desiredAssertionStatus()) {}
    for (boolean bool = true;; bool = false)
    {
      $assertionsDisabled = bool;
      return;
    }
  }
  
  protected EventHandler(ClientTelemetry paramClientTelemetry, List<ICllEvents> paramList, ILogger paramILogger, AbstractHandler paramAbstractHandler1, AbstractHandler paramAbstractHandler2)
  {
    super(SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.QUEUEDRAININTERVAL));
    this.clientTelemetry = paramClientTelemetry;
    this.cllEvents = paramList;
    this.logger = paramILogger;
    this.normalHandler = paramAbstractHandler1;
    this.criticalHandler = paramAbstractHandler2;
    this.sampleId = -1.0D;
  }
  
  public EventHandler(ClientTelemetry paramClientTelemetry, List<ICllEvents> paramList, ILogger paramILogger, String paramString)
  {
    super(SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.QUEUEDRAININTERVAL));
    this.clientTelemetry = paramClientTelemetry;
    this.cllEvents = paramList;
    this.logger = paramILogger;
    this.criticalHandler = new CriticalEventHandler(paramILogger, paramString, paramClientTelemetry);
    this.normalHandler = new NormalEventHandler(paramILogger, paramString, paramClientTelemetry);
    this.sampleId = -1.0D;
  }
  
  private boolean Filter(SerializedEvent paramSerializedEvent)
  {
    if (paramSerializedEvent.getSerializedData().length() > SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSIZEINBYTES))
    {
      this.logger.info("AndroidCll-EventHandler", "Event is too large");
      return true;
    }
    if ((!IsUploadEnabled()) || (!IsInSample(paramSerializedEvent)))
    {
      this.logger.info("AndroidCll-EventHandler", "Filtered event");
      return true;
    }
    return false;
  }
  
  private boolean IsInSample(SerializedEvent paramSerializedEvent)
  {
    String str;
    if (this.sampleId < -1.0E-5D)
    {
      this.sampleId = 0.0D;
      str = paramSerializedEvent.getDeviceId();
      if ((str == null) || (str.length() <= 7)) {}
    }
    try
    {
      this.sampleId = (Long.parseLong(str.substring(str.length() - 7), 16) % 10000L / 100.0D);
      this.logger.info("AndroidCll-EventHandler", "Sample Id is " + String.valueOf(this.sampleId) + " based on deviceId of " + str);
      return this.sampleId < 1.0E-5D + paramSerializedEvent.getSampleRate();
    }
    catch (NumberFormatException localNumberFormatException)
    {
      for (;;) {}
    }
  }
  
  private boolean IsUploadEnabled()
  {
    return SettingsStore.getCllSettingsAsBoolean(SettingsStore.Settings.UPLOADENABLED);
  }
  
  private boolean startEventQueueWriter(Runnable paramRunnable)
  {
    if (this.endpoint == null)
    {
      this.logger.warn("AndroidCll-EventHandler", "No endpoint set");
      return false;
    }
    EventQueueWriter localEventQueueWriter = (EventQueueWriter)paramRunnable;
    if (this.sender != null) {
      localEventQueueWriter.setSender(this.sender);
    }
    try
    {
      this.executor.execute(paramRunnable);
      return true;
    }
    catch (RejectedExecutionException paramRunnable)
    {
      this.logger.warn("AndroidCll-EventHandler", "Could not start new thread for EventQueueWriter");
      return false;
    }
    catch (NullPointerException paramRunnable)
    {
      for (;;)
      {
        this.logger.error("AndroidCll-EventHandler", "Executor is null. Is the cll paused or stopped?");
      }
    }
  }
  
  protected boolean addToStorage(SerializedEvent paramSerializedEvent, List<String> paramList)
  {
    switch (paramSerializedEvent.getPersistence())
    {
    default: 
      this.logger.error("AndroidCll-EventHandler", "Unknown persistence");
      if (!$assertionsDisabled) {
        throw new AssertionError();
      }
      break;
    }
    for (;;)
    {
      try
      {
        this.normalHandler.add(paramSerializedEvent.getSerializedData(), paramList);
        return true;
      }
      catch (IOException paramSerializedEvent)
      {
        this.logger.error("AndroidCll-EventHandler", "Could not add event to normal storage");
        return false;
      }
      catch (FileStorage.FileFullException paramSerializedEvent)
      {
        this.logger.warn("AndroidCll-EventHandler", "No space on disk to store events");
        return false;
      }
      try
      {
        this.criticalHandler.add(paramSerializedEvent.getSerializedData(), paramList);
      }
      catch (IOException paramSerializedEvent)
      {
        this.logger.error("AndroidCll-EventHandler", "Could not add event to normal storage");
        return false;
      }
      catch (FileStorage.FileFullException paramSerializedEvent)
      {
        this.logger.warn("AndroidCll-EventHandler", "No space on disk to store events");
      }
    }
    return false;
  }
  
  protected boolean log(SerializedEvent paramSerializedEvent, List<String> paramList)
  {
    int i = 0;
    if (Filter(paramSerializedEvent)) {
      return false;
    }
    if (EventQueueWriter.getRunningThreadCount() >= SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSIZEINBYTES)) {
      i = 1;
    }
    if ((paramSerializedEvent.getLatency() == EventEnums.Latency.LatencyRealtime) && (!this.isPaused) && (i == 0) && (startEventQueueWriter(new EventQueueWriter(this.endpoint, paramSerializedEvent, paramList, this.clientTelemetry, this.cllEvents, this.logger, this.executor, this, this.ticketCallback)))) {
      return true;
    }
    return addToStorage(paramSerializedEvent, paramList);
  }
  
  public void run()
  {
    if (this.interval != SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.QUEUEDRAININTERVAL))
    {
      this.nextExecution.cancel(false);
      this.interval = SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.QUEUEDRAININTERVAL);
      this.nextExecution = this.executor.scheduleAtFixedRate(this, this.interval, this.interval, TimeUnit.SECONDS);
    }
    if (EventQueueWriter.future != null)
    {
      this.logger.info("AndroidCll-EventHandler", "Retry logic in progress, skipping normal send");
      return;
    }
    send();
  }
  
  protected boolean send()
  {
    return send(null);
  }
  
  protected boolean send(EventEnums.Persistence paramPersistence)
  {
    if (this.isPaused) {
      return false;
    }
    Object localObject = null;
    if (paramPersistence == null)
    {
      this.logger.info("AndroidCll-EventHandler", "Draining All events");
      paramPersistence = this.normalHandler.getFilesForDraining();
      paramPersistence.addAll(this.criticalHandler.getFilesForDraining());
    }
    while ((paramPersistence != null) && (paramPersistence.size() != 0))
    {
      return startEventQueueWriter(new EventQueueWriter(this.endpoint, paramPersistence, this.clientTelemetry, this.cllEvents, this.logger, this.executor, this.ticketCallback));
      switch (paramPersistence)
      {
      default: 
        this.logger.error("AndroidCll-EventHandler", "Unknown persistence");
        paramPersistence = (EventEnums.Persistence)localObject;
        if (!$assertionsDisabled) {
          throw new AssertionError();
        }
        break;
      case ???: 
        this.logger.info("AndroidCll-EventHandler", "Draining normal events");
        paramPersistence = this.normalHandler.getFilesForDraining();
        break;
      case ???: 
        this.logger.info("AndroidCll-EventHandler", "Draining Critical events");
        paramPersistence = this.criticalHandler.getFilesForDraining();
      }
    }
    return true;
  }
  
  protected void setEndpointUrl(String paramString)
  {
    try
    {
      this.endpoint = new URL(paramString);
      return;
    }
    catch (MalformedURLException paramString)
    {
      this.logger.error("AndroidCll-EventHandler", "Bad Endpoint URL Form");
    }
  }
  
  void setSender(EventSender paramEventSender)
  {
    this.sender = paramEventSender;
  }
  
  void setXuidCallback(ITicketCallback paramITicketCallback)
  {
    this.ticketCallback = paramITicketCallback;
  }
  
  public void stop()
  {
    super.stop();
    this.normalHandler.close();
    this.criticalHandler.close();
  }
  
  void synchronize()
  {
    ((NormalEventHandler)this.normalHandler).writeQueueToDisk();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\cll\android\EventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */