package com.amazon.android.i;

import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class b
  implements com.amazon.android.framework.resource.b
{
  private static final KiwiLogger LOGGER = new KiwiLogger("Expirable");
  private AtomicBoolean expired = new AtomicBoolean(false);
  private final List observers = new Vector();
  @Resource
  protected TaskManager taskManager;
  
  private void notifyObservers()
  {
    Iterator localIterator = this.observers.iterator();
    while (localIterator.hasNext()) {
      ((d)localIterator.next()).observe(this);
    }
  }
  
  private void scheduleExpiration()
  {
    a locala = new a(this);
    this.taskManager.enqueueAtTime(TaskPipelineId.BACKGROUND, locala, getExpiration());
  }
  
  public final void discard()
  {
    if (!this.expired.compareAndSet(false, true)) {
      return;
    }
    notifyObservers();
  }
  
  protected abstract void doExpiration();
  
  public void expire()
  {
    if (!this.expired.compareAndSet(false, true)) {
      return;
    }
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Expiring: " + this);
    }
    doExpiration();
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Notifying Observers of expiration: " + this);
    }
    notifyObservers();
  }
  
  protected abstract Date getExpiration();
  
  protected boolean isExpired()
  {
    return this.expired.get();
  }
  
  public final void onResourcesPopulated()
  {
    scheduleExpiration();
    onResourcesPopulatedImpl();
  }
  
  protected void onResourcesPopulatedImpl() {}
  
  public final void register(d paramd)
  {
    this.observers.add(paramd);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\i\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */