package com.amazon.android.framework.task.command;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.RemoteException;
import com.amazon.android.framework.prompt.Prompt;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.venezia.command.n;
import com.amazon.venezia.command.r;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class c
  extends Prompt
{
  private static final KiwiLogger a = new KiwiLogger("DecisionDialog");
  private final Thread b = Thread.currentThread();
  private final f c;
  private final BlockingQueue d = new LinkedBlockingQueue();
  
  public c(r paramr)
    throws RemoteException
  {
    this.c = new f(paramr);
  }
  
  private static ActivityInfo a(Activity paramActivity)
  {
    try
    {
      ActivityInfo localActivityInfo = paramActivity.getPackageManager().getActivityInfo(paramActivity.getComponentName(), 128);
      return localActivityInfo;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      if (KiwiLogger.ERROR_ON) {
        a.error("Unable to get info for activity: " + paramActivity);
      }
    }
    return null;
  }
  
  private void a(AlertDialog paramAlertDialog, m paramm, int paramInt)
  {
    if (paramm == null) {
      return;
    }
    paramAlertDialog.setButton(paramInt, paramm.b, new d(this, paramm));
  }
  
  private static boolean a(m paramm)
  {
    return (paramm != null) && (paramm.c != null);
  }
  
  private n c()
    throws com.amazon.android.b.c
  {
    try
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Blocking while awaiting customer decision: " + Thread.currentThread());
      }
      n localn = ((m)this.d.take()).a;
      return localn;
    }
    catch (InterruptedException localInterruptedException)
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Interrupted while awaiting decision, throwing decision expired!");
      }
      if (getExpirationReason() != com.amazon.android.framework.prompt.d.a) {}
    }
    for (a locala = a.b;; locala = a.a) {
      throw new com.amazon.android.b.c(locala);
    }
  }
  
  public final n a()
    throws com.amazon.android.b.c
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("GetCustomerDecision: " + this.b);
    }
    return c();
  }
  
  public final boolean doCompatibilityCheck(Activity paramActivity)
  {
    if ((a(this.c.d)) || (a(this.c.e)) || (a(this.c.f))) {}
    for (int i = 1; i == 0; i = 0) {
      return true;
    }
    paramActivity = a(paramActivity);
    if (paramActivity == null) {
      return false;
    }
    boolean bool1;
    boolean bool2;
    if (paramActivity.launchMode == 3)
    {
      bool1 = true;
      a.trace("Single instance: " + bool1);
      if ((paramActivity.flags & 0x2) == 0) {
        break label197;
      }
      bool2 = true;
      label110:
      a.trace("Finish on task launch:" + bool2);
      if ((paramActivity.flags & 0x80) == 0) {
        break label203;
      }
    }
    label197:
    label203:
    for (boolean bool3 = true;; bool3 = false)
    {
      a.trace("No History: " + bool3);
      if ((bool1) || (bool2) || (bool3)) {
        break label209;
      }
      return true;
      bool1 = false;
      break;
      bool2 = false;
      break label110;
    }
    label209:
    return false;
  }
  
  public final Dialog doCreate(Activity paramActivity)
  {
    paramActivity = new AlertDialog.Builder(paramActivity);
    paramActivity.setTitle(this.c.a).setMessage(this.c.b).setCancelable(false);
    paramActivity = paramActivity.create();
    a(paramActivity, this.c.d, -1);
    a(paramActivity, this.c.e, -3);
    a(paramActivity, this.c.f, -2);
    return paramActivity;
  }
  
  protected final void doExpiration(com.amazon.android.framework.prompt.d paramd)
  {
    if (KiwiLogger.TRACE_ON) {
      a.trace("Expiring Decision Dialog: Thread: " + Thread.currentThread());
    }
    this.b.interrupt();
  }
  
  protected final long getExpirationDurationInSeconds()
  {
    return this.c.c;
  }
  
  public final String toString()
  {
    return "DecisionDialog: " + this.c.a;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */