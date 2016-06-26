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
  private final String TAG = "EventHandler";
  private final ClientTelemetry clientTelemetry;
  private final List<ICllEvents> cllEvents;
  final AbstractHandler criticalHandler;
  private URL endpoint;
  private final ILogger logger;
  final AbstractHandler normalHandler;
  private int sampleId;
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
    this.sampleId = -1;
  }
  
  public EventHandler(ClientTelemetry paramClientTelemetry, List<ICllEvents> paramList, ILogger paramILogger, String paramString)
  {
    super(SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.QUEUEDRAININTERVAL));
    this.clientTelemetry = paramClientTelemetry;
    this.cllEvents = paramList;
    this.logger = paramILogger;
    this.criticalHandler = new CriticalEventHandler(paramILogger, paramString, paramClientTelemetry);
    this.normalHandler = new NormalEventHandler(paramILogger, paramString, paramClientTelemetry);
    this.sampleId = -1;
  }
  
  private boolean Filter(SerializedEvent paramSerializedEvent)
  {
    if (paramSerializedEvent.getSerializedData().length() > SettingsStore.getCllSettingsAsInt(SettingsStore.Settings.MAXEVENTSIZEINBYTES))
    {
      this.logger.info("EventHandler", "Event is too large");
      return true;
    }
    if ((!IsUploadEnabled()) || (!IsInSample(paramSerializedEvent)))
    {
      this.logger.info("EventHandler", "Filtered event");
      return true;
    }
    return false;
  }
  
  private boolean IsInSample(SerializedEvent paramSerializedEvent)
  {
    if (paramSerializedEvent.getDeviceId() == null) {}
    do
    {
      return false;
      if (this.sampleId < 0) {
        this.sampleId = (Integer.parseInt(paramSerializedEvent.getDeviceId().substring(paramSerializedEvent.getDeviceId().length() - 7), 16) % 100);
      }
    } while (this.sampleId >= paramSerializedEvent.getSampleRate());
    return true;
  }
  
  private boolean IsUploadEnabled()
  {
    return SettingsStore.getCllSettingsAsBoolean(SettingsStore.Settings.UPLOADENABLED);
  }
  
  private boolean startEventQueueWriter(Runnable paramRunnable)
  {
    if (this.endpoint == null)
    {
      this.logger.warn("EventHandler", "No endpoint set");
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
      this.logger.warn("EventHandler", "Could not start new thread for EventQueueWriter");
      return false;
    }
    catch (NullPointerException paramRunnable)
    {
      for (;;)
      {
        this.logger.error("EventHandler", "Executor is null. Is the cll paused or stopped?");
      }
    }
  }
  
  protected boolean addToStorage(SerializedEvent paramSerializedEvent, List<String> paramList)
  {
    switch (paramSerializedEvent.getPersistence())
    {
    default: 
      this.logger.error("EventHandler", "Unknown persistence");
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
        this.logger.error("EventHandler", "Could not add event to normal storage");
        return false;
      }
      catch (FileStorage.FileFullException paramSerializedEvent)
      {
        this.logger.warn("EventHandler", "No space on disk to store events");
        return false;
      }
      try
      {
        this.criticalHandler.add(paramSerializedEvent.getSerializedData(), paramList);
      }
      catch (IOException paramSerializedEvent)
      {
        this.logger.error("EventHandler", "Could not add event to normal storage");
        return false;
      }
      catch (FileStorage.FileFullException paramSerializedEvent)
      {
        this.logger.warn("EventHandler", "No space on disk to store events");
      }
    }
    return false;
  }
  
  protected boolean log(SerializedEvent paramSerializedEvent, List<String> paramList)
  {
    if (Filter(paramSerializedEvent)) {
      return false;
    }
    if ((paramSerializedEvent.getLatency() == Cll.EventLatency.REALTIME) && (!this.isPaused) && (startEventQueueWriter(new EventQueueWriter(this.endpoint, paramSerializedEvent, paramList, this.clientTelemetry, this.cllEvents, this.logger, this.executor, this, this.ticketCallback)) == true)) {
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
      this.logger.info("EventHandler", "Retry logic in progress, skipping normal send");
      return;
    }
    send();
  }
  
  protected boolean send()
  {
    return send(null);
  }
  
  protected boolean send(Cll.EventPersistence paramEventPersistence)
  {
    if (this.isPaused) {
      return false;
    }
    Object localObject = null;
    if (paramEventPersistence == null)
    {
      this.logger.info("EventHandler", "Draining All events");
      paramEventPersistence = this.normalHandler.getFilesForDraining();
      paramEventPersistence.addAll(this.criticalHandler.getFilesForDraining());
    }
    while ((paramEventPersistence != null) && (paramEventPersistence.size() != 0))
    {
      return startEventQueueWriter(new EventQueueWriter(this.endpoint, paramEventPersistence, this.clientTelemetry, this.cllEvents, this.logger, this.executor, this.ticketCallback));
      switch (paramEventPersistence)
      {
      default: 
        this.logger.error("EventHandler", "Unknown persistence");
        paramEventPersistence = (Cll.EventPersistence)localObject;
        if (!$assertionsDisabled) {
          throw new AssertionError();
        }
        break;
      case ???: 
        this.logger.info("EventHandler", "Draining normal events");
        paramEventPersistence = this.normalHandler.getFilesForDraining();
        break;
      case ???: 
        this.logger.info("EventHandler", "Draining Critical events");
        paramEventPersistence = this.criticalHandler.getFilesForDraining();
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
      this.logger.error("EventHandler", "Bad Endpoint URL Form");
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\cll\android\EventHandler.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */