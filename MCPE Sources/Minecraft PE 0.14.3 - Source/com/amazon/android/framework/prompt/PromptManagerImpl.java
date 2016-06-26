package com.amazon.android.framework.prompt;

import android.app.Activity;
import android.app.Dialog;
import com.amazon.android.framework.context.ContextManager;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.resource.a;
import com.amazon.android.framework.resource.b;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.o.g;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;
import java.util.concurrent.atomic.AtomicBoolean;

public class PromptManagerImpl
  implements PromptManager, b
{
  public static final KiwiLogger LOGGER = new KiwiLogger("PromptManagerImpl");
  @Resource
  private ContextManager contextManager;
  @Resource
  private g eventManager;
  private final AtomicBoolean finished = new AtomicBoolean(false);
  private Set pending = new LinkedHashSet();
  @Resource
  private a resourceManager;
  private Prompt showing;
  @Resource
  private TaskManager taskManager;
  
  private void finish()
  {
    if (!this.finished.compareAndSet(false, true)) {}
    do
    {
      return;
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("PromptManager finishing....");
      }
      Iterator localIterator = this.pending.iterator();
      while (localIterator.hasNext())
      {
        Prompt localPrompt = (Prompt)localIterator.next();
        localIterator.remove();
        localPrompt.expire();
      }
    } while (this.showing == null);
    this.showing.dismiss();
  }
  
  private Prompt getNextPending()
  {
    if (this.pending.isEmpty()) {
      return null;
    }
    return (Prompt)this.pending.iterator().next();
  }
  
  private void onResume(Activity paramActivity)
  {
    if (this.showing != null)
    {
      show(this.showing, paramActivity);
      return;
    }
    presentNextPending(paramActivity);
  }
  
  private void presentImpl(Prompt paramPrompt)
  {
    if (this.finished.get())
    {
      if (KiwiLogger.ERROR_ON) {
        LOGGER.error("Prompt: " + paramPrompt + " presented after app" + " destruction expiring it now!");
      }
      paramPrompt.expire();
    }
    do
    {
      do
      {
        return;
        if (KiwiLogger.TRACE_ON) {
          LOGGER.trace("Presening Prompt: " + paramPrompt);
        }
        paramPrompt.register(this);
        this.pending.add(paramPrompt);
        if (this.showing == null) {
          break;
        }
      } while (!KiwiLogger.TRACE_ON);
      LOGGER.trace("Dialog currently showing, not presenting given dialog");
      return;
      paramPrompt = this.contextManager.getVisible();
    } while (paramPrompt == null);
    presentNextPending(paramPrompt);
  }
  
  private void presentNextPending(Activity paramActivity)
  {
    Prompt localPrompt = getNextPending();
    if (localPrompt == null) {
      return;
    }
    show(localPrompt, paramActivity);
  }
  
  private void registerActivityResumedListener()
  {
    this.eventManager.a(new f(this));
  }
  
  private void registerAppDestructionListener()
  {
    i locali = new i(this);
    this.eventManager.a(locali);
  }
  
  private void registerTestModeListener()
  {
    j localj = new j(this);
    this.eventManager.a(localj);
  }
  
  private void removeExpiredPrompt(Prompt paramPrompt)
  {
    this.pending.remove(paramPrompt);
    if (this.showing == paramPrompt)
    {
      this.showing = null;
      paramPrompt = this.contextManager.getVisible();
      if (paramPrompt != null) {
        presentNextPending(paramPrompt);
      }
    }
  }
  
  private void show(Prompt paramPrompt, Activity paramActivity)
  {
    this.showing = paramPrompt;
    paramPrompt.show(paramActivity);
  }
  
  public void observe(Prompt paramPrompt)
  {
    paramPrompt = new e(this, paramPrompt);
    this.taskManager.enqueue(TaskPipelineId.FOREGROUND, paramPrompt);
  }
  
  public Dialog onCreateDialog(Activity paramActivity, int paramInt)
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("onCreateDialog, id: " + paramInt + ", activity: " + paramActivity);
    }
    if (this.showing == null)
    {
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Showing dialog is null, returning");
      }
      return null;
    }
    if (this.showing.getIdentifier() != paramInt)
    {
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Showing dialog id does not match given id: " + paramInt + ", returning");
      }
      return null;
    }
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Creating dialog prompt: " + this.showing);
    }
    return this.showing.create(paramActivity);
  }
  
  public void onResourcesPopulated()
  {
    registerActivityResumedListener();
    registerAppDestructionListener();
    registerTestModeListener();
  }
  
  public void onWindowFocusChanged(Activity paramActivity, boolean paramBoolean)
  {
    if (this.showing != null) {
      this.showing.onFocusChanged(paramActivity, paramBoolean);
    }
  }
  
  public void present(Prompt paramPrompt)
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Scheduling presentation: " + paramPrompt);
    }
    this.resourceManager.b(paramPrompt);
    if (this.finished.get())
    {
      if (KiwiLogger.ERROR_ON) {
        LOGGER.error("Prompt: " + paramPrompt + " presented after app" + " destruction expiring it now!");
      }
      paramPrompt.expire();
      return;
    }
    paramPrompt = new h(this, paramPrompt);
    this.taskManager.enqueue(TaskPipelineId.FOREGROUND, paramPrompt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\PromptManagerImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */