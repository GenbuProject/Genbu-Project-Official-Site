package com.amazon.android.framework.context;

import android.app.Activity;
import android.app.Application;
import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.Intent;
import android.content.IntentFilter;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.task.TaskManager;
import com.amazon.android.framework.task.pipeline.TaskPipelineId;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.android.o.g;
import java.util.Iterator;
import java.util.WeakHashMap;
import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;

public final class d
  implements ContextManager, com.amazon.android.framework.resource.b
{
  public static final KiwiLogger a = new KiwiLogger("ContextManagerImpl");
  private final AtomicReference b = new AtomicReference();
  private final com.amazon.android.framework.util.c c = new com.amazon.android.framework.util.c();
  private final com.amazon.android.framework.util.c d = new com.amazon.android.framework.util.c();
  private final com.amazon.android.framework.util.c e = new com.amazon.android.framework.util.c();
  private final AtomicBoolean f = new AtomicBoolean(false);
  private final AtomicBoolean g = new AtomicBoolean(false);
  private final AtomicBoolean h = new AtomicBoolean(false);
  @Resource
  private Application i;
  @Resource
  private TaskManager j;
  @Resource
  private g k;
  private String l;
  private boolean m = false;
  
  private void a(com.amazon.android.j.c paramc, Activity paramActivity)
  {
    paramc = new com.amazon.android.j.b(paramc, paramActivity);
    this.k.a(paramc);
  }
  
  private void a(com.amazon.android.j.d paramd)
  {
    paramd = new com.amazon.android.j.a(paramd, this.i);
    this.k.a(paramd);
  }
  
  private static boolean a(Activity paramActivity)
  {
    if (paramActivity == null) {
      return false;
    }
    return paramActivity.isTaskRoot();
  }
  
  private static Activity b(Activity paramActivity)
  {
    while (paramActivity.isChild()) {
      paramActivity = paramActivity.getParent();
    }
    return paramActivity;
  }
  
  public final void finishActivities()
  {
    if (!this.f.compareAndSet(false, true)) {
      return;
    }
    if (KiwiLogger.TRACE_ON)
    {
      a.trace("---------- SCHEDULING FINISH ACTIVITIES -----------");
      a.trace(this.i.getPackageName());
      a.trace(Thread.currentThread().toString());
      a.trace("---------------------------------------------------");
    }
    c localc = new c(this);
    this.j.enqueue(TaskPipelineId.FOREGROUND, localc);
  }
  
  public final Activity getRoot()
  {
    com.amazon.android.d.a.a();
    Iterator localIterator = this.c.iterator();
    while (localIterator.hasNext())
    {
      Activity localActivity = (Activity)localIterator.next();
      if (a(localActivity)) {
        return localActivity;
      }
    }
    return null;
  }
  
  public final Activity getVisible()
  {
    com.amazon.android.d.a.a();
    return (Activity)this.b.get();
  }
  
  public final boolean hasAppShutdownBeenRequested()
  {
    return (this.g.get()) || (this.f.get());
  }
  
  public final boolean isVisible()
  {
    return this.b.get() != null;
  }
  
  public final void onCreate(Activity paramActivity)
  {
    com.amazon.android.d.a.a(paramActivity, "activity");
    com.amazon.android.d.a.a();
    this.c.a(paramActivity);
    if (KiwiLogger.TRACE_ON) {
      a.trace("Activity->onCreate.  Activity: " + paramActivity + ", Total Activities: " + this.c.a.size());
    }
    a(com.amazon.android.j.c.a, paramActivity);
    if ((!this.m) && (this.c.a.size() == 1)) {}
    for (int n = 1;; n = 0)
    {
      if (n != 0)
      {
        this.m = true;
        a(com.amazon.android.j.d.a);
      }
      return;
    }
  }
  
  public final void onCreate(Service paramService)
  {
    
    if (KiwiLogger.TRACE_ON) {
      a.trace("Service->onCreate: " + paramService);
    }
    this.d.a(paramService);
  }
  
  public final void onDestroy(Activity paramActivity)
  {
    com.amazon.android.d.a.a(paramActivity, "activity");
    com.amazon.android.d.a.a();
    this.c.b(paramActivity);
    if (KiwiLogger.TRACE_ON) {
      a.trace("Activity->onDestroy.  Activity: " + paramActivity + ", Total Activities: " + this.c.a.size());
    }
    a(com.amazon.android.j.c.b, paramActivity);
    if (KiwiLogger.TRACE_ON) {
      a.trace("Checking if application is destroyed");
    }
    if (this.c.a.isEmpty())
    {
      a.trace("App is destroyed: " + paramActivity.isTaskRoot() + ", " + paramActivity.isFinishing());
      if ((!paramActivity.isTaskRoot()) || (!paramActivity.isFinishing())) {}
    }
    for (int n = 1;; n = 0)
    {
      if (n != 0)
      {
        this.m = false;
        a(com.amazon.android.j.d.b);
      }
      return;
    }
  }
  
  public final void onDestroy(Service paramService)
  {
    com.amazon.android.d.a.a();
    this.d.b(paramService);
  }
  
  public final void onPause(Activity paramActivity)
  {
    
    if (KiwiLogger.TRACE_ON) {
      a.trace("Activity paused: " + paramActivity + ", visible activity: " + getVisible());
    }
    if (getVisible() == paramActivity)
    {
      if (KiwiLogger.TRACE_ON) {
        a.trace("Setting visible activity to null");
      }
      this.b.set(null);
      a(com.amazon.android.j.c.d, paramActivity);
    }
  }
  
  public final void onResourcesPopulated()
  {
    Object localObject = this.i.getPackageName();
    this.l = ("com.amazon." + (String)localObject + ".shutdown");
    localObject = new b(this);
    IntentFilter localIntentFilter = new IntentFilter(this.l);
    this.i.registerReceiver((BroadcastReceiver)localObject, localIntentFilter);
  }
  
  public final void onResume(Activity paramActivity)
  {
    com.amazon.android.d.a.a();
    a.trace("Activity resumed: " + paramActivity + ", is child: " + paramActivity.isChild());
    Activity localActivity = b(paramActivity);
    a.trace("Setting visible to: " + localActivity);
    this.b.set(localActivity);
    if (KiwiLogger.TRACE_ON) {
      a.trace("Activity now visible: " + paramActivity + ", " + "publishing resume event");
    }
    a(com.amazon.android.j.c.c, paramActivity);
  }
  
  public final void onStart(Activity paramActivity)
  {
    com.amazon.android.d.a.a(paramActivity, "activity");
    com.amazon.android.d.a.a();
    a.trace("Activity started: " + paramActivity);
    this.e.a(paramActivity);
    a(com.amazon.android.j.c.e, paramActivity);
    if (this.e.a.size() == 1) {
      a(com.amazon.android.j.d.c);
    }
  }
  
  public final void onStop(Activity paramActivity)
  {
    com.amazon.android.d.a.a(paramActivity, "activity");
    com.amazon.android.d.a.a();
    a.trace("Activity stopped: " + paramActivity);
    this.e.b(paramActivity);
    a(com.amazon.android.j.c.f, paramActivity);
    if (this.e.a.isEmpty()) {
      a(com.amazon.android.j.d.d);
    }
  }
  
  public final void stopServices()
  {
    if ((!this.g.compareAndSet(false, true)) && (KiwiLogger.TRACE_ON)) {
      a.trace("Ignoring duplicate stopServices request");
    }
    if (KiwiLogger.TRACE_ON)
    {
      a.trace("------------- STOPPING SERVICES -------------------");
      a.trace(this.i.getPackageName());
      a.trace("---------------------------------------------------");
    }
    Intent localIntent = new Intent(this.l);
    localIntent.setPackage(this.i.getPackageName());
    if (KiwiLogger.TRACE_ON) {
      a.trace("Sending Broadcast!!!!: " + localIntent + ", Thread: " + Thread.currentThread());
    }
    this.i.sendBroadcast(localIntent);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\context\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */