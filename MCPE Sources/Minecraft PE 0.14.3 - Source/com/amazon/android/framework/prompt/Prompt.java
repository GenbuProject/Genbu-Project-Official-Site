package com.amazon.android.framework.prompt;

import android.app.Activity;
import android.app.Dialog;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.i.c;
import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

public abstract class Prompt
  extends c
{
  private static final KiwiLogger LOGGER = new KiwiLogger("Prompt");
  private Activity context;
  @Resource
  private com.amazon.android.n.a dataStore;
  private Dialog dialog;
  private final AtomicBoolean dismissed = new AtomicBoolean(false);
  private final int identifier = createIdentifier();
  private d manualExpirationReason;
  
  public Prompt()
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Creating Prompt: " + this.identifier);
    }
  }
  
  private int createIdentifier()
  {
    int j = new Random().nextInt(2146249079) + 1234567;
    int i = j;
    if (j <= 1234567) {
      i = 1234567;
    }
    return i;
  }
  
  private void dismissDialog()
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.error("Dismissing dialog: " + this.identifier);
    }
    try
    {
      this.context.dismissDialog(this.identifier);
      this.context.removeDialog(this.identifier);
      this.context = null;
      this.dialog = null;
      return;
    }
    catch (Exception localException)
    {
      for (;;)
      {
        if (KiwiLogger.TRACE_ON) {
          LOGGER.error("Unable to remove dialog: " + this.identifier);
        }
      }
    }
  }
  
  private void expire(d paramd)
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Expiring prompt pre-maturely: id: " + getIdentifier() + ", prompt: " + this + "," + ", reason: " + paramd);
    }
    this.manualExpirationReason = paramd;
    expire();
  }
  
  private boolean isCompatible(Activity paramActivity)
  {
    if (this.dataStore.b("TEST_MODE")) {
      return false;
    }
    return doCompatibilityCheck(paramActivity);
  }
  
  private void showDialog(Activity paramActivity)
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Showing prompt, id: " + getIdentifier() + ", prompt: " + this + ", activity: " + paramActivity);
    }
    if (this.context != null) {
      dismissDialog();
    }
    paramActivity.showDialog(getIdentifier());
  }
  
  public final Dialog create(Activity paramActivity)
  {
    this.context = paramActivity;
    this.dialog = doCreate(paramActivity);
    this.dialog.setCancelable(false);
    this.dialog.setOnKeyListener(new b(this));
    return this.dialog;
  }
  
  protected final boolean dismiss()
  {
    
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Dismissing Prompt: " + this.identifier);
    }
    if (!this.dismissed.compareAndSet(false, true))
    {
      if (KiwiLogger.TRACE_ON) {
        LOGGER.error("Prompt has already been dismissed");
      }
      return false;
    }
    if (this.context != null) {
      dismissDialog();
    }
    discard();
    return true;
  }
  
  protected boolean doCompatibilityCheck(Activity paramActivity)
  {
    return true;
  }
  
  protected abstract Dialog doCreate(Activity paramActivity);
  
  protected final void doExpiration()
  {
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("Expiring prompt: " + this);
    }
    a locala = new a(this);
    this.taskManager.enqueue(TaskPipelineId.FOREGROUND, locala);
    doExpiration(getExpirationReason());
  }
  
  protected abstract void doExpiration(d paramd);
  
  protected d getExpirationReason()
  {
    if (!isExpired()) {
      return null;
    }
    if (this.manualExpirationReason == null) {
      return d.b;
    }
    return this.manualExpirationReason;
  }
  
  public int getIdentifier()
  {
    return this.identifier;
  }
  
  public void onFocusChanged(Activity paramActivity, boolean paramBoolean)
  {
    if (paramActivity != this.context) {
      if (KiwiLogger.TRACE_ON) {
        LOGGER.trace("Unrecognized context");
      }
    }
    while ((!paramBoolean) || (this.dialog.isShowing())) {
      return;
    }
    if (KiwiLogger.TRACE_ON) {
      LOGGER.trace("showing dialog because it was not showing");
    }
    this.dialog.show();
  }
  
  public final void show(Activity paramActivity)
  {
    com.amazon.android.d.a.a(paramActivity, "activity");
    com.amazon.android.d.a.a();
    if (isCompatible(paramActivity))
    {
      showDialog(paramActivity);
      return;
    }
    expire(d.a);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\prompt\Prompt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */