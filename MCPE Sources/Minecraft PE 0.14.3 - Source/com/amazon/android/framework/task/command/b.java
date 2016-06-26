package com.amazon.android.framework.task.command;

import android.app.Application;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.ServiceConnection;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.RemoteException;
import com.amazon.android.b.g;
import com.amazon.android.framework.resource.Resource;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.venezia.command.n;
import com.amazon.venezia.command.r;
import com.amazon.venezia.command.w;
import java.util.List;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public final class b
{
  private static final KiwiLogger a = new KiwiLogger("CommandServiceClient");
  private com.amazon.venezia.command.h b;
  private String c;
  private final BlockingQueue d = new LinkedBlockingQueue();
  private final BlockingQueue e = new LinkedBlockingQueue();
  @Resource
  private Application f;
  @Resource
  private e g;
  @Resource
  private com.amazon.android.n.a h;
  private final ServiceConnection i = new i(this);
  private final com.amazon.venezia.command.f j = new j(this);
  
  private static Intent a(String paramString)
  {
    Intent localIntent = new Intent();
    localIntent.setAction("com.amazon.venezia.CommandService");
    localIntent.setClassName(paramString, "com.amazon.venezia.service.command.CommandServiceImpl");
    return localIntent;
  }
  
  private Intent a(List paramList)
  {
    int n = -1;
    int k = 0;
    String str = null;
    for (;;)
    {
      int m = n;
      try
      {
        if (k < paramList.size())
        {
          ResolveInfo localResolveInfo = (ResolveInfo)paramList.get(k);
          str = localResolveInfo.serviceInfo.applicationInfo.packageName;
          a.trace("Examining package " + str);
          a.trace("Priority is " + localResolveInfo.filter.getPriority());
          a.trace("Checking signature of package " + str);
          a.trace("isPackageSignatureTrusted " + str);
          if (this.g.a(str, k.a))
          {
            a.trace("Signature of package " + str + " is okay");
            m = k;
          }
        }
        else
        {
          if (m < 0) {
            break label278;
          }
          return a(str);
        }
        a.trace("Signature of package " + str + " is bad");
        k += 1;
      }
      catch (Exception paramList)
      {
        a.trace("Caught exception " + paramList);
      }
    }
    label278:
    return null;
  }
  
  private l d()
    throws RemoteException
  {
    try
    {
      a.trace("Blocking for result from service");
      l locall = (l)this.d.take();
      a.trace("Received result from service");
      return locall;
    }
    catch (InterruptedException localInterruptedException)
    {
      a.trace("TaskThread interrupted, returning null result");
    }
    return null;
  }
  
  public final l a(n paramn)
    throws RemoteException
  {
    paramn.a(null);
    return d();
  }
  
  public final l a(r paramr, a parama)
    throws RemoteException
  {
    paramr.a(new h(this, parama));
    return d();
  }
  
  public final l a(w paramw)
    throws g, com.amazon.android.b.f, RemoteException
  {
    long l1;
    Object localObject1;
    if (this.b != null)
    {
      k = 1;
      if (k != 0) {
        break label351;
      }
      l1 = System.currentTimeMillis();
      a.trace("Binding Service!!!");
      localObject1 = new Intent();
      ((Intent)localObject1).setAction("com.amazon.venezia.CommandService");
      a.trace("Created intent with  action  com.amazon.venezia.CommandService");
      if (this.f.getPackageManager().resolveService((Intent)localObject1, 64) == null) {
        break label86;
      }
    }
    label86:
    for (int k = 1;; k = 0)
    {
      if (k != 0) {
        break label91;
      }
      throw new g();
      k = 0;
      break;
    }
    label91:
    a.trace("Found service on one or more packages");
    if (this.c != null)
    {
      a.trace("Using previously determined package " + this.c);
      localObject1 = a(this.c);
    }
    Object localObject2;
    do
    {
      localObject2 = ((Intent)localObject1).getComponent().getPackageName();
      a.trace("Attempting to bind to service on " + (String)localObject2);
      if (this.f.bindService((Intent)localObject1, this.i, 1)) {
        break;
      }
      throw new com.amazon.android.b.f();
      a.trace("No previously determined package found, checking for suitable package.");
      localObject2 = a(this.f.getPackageManager().queryIntentServices((Intent)localObject1, 64));
      localObject1 = localObject2;
    } while (localObject2 != null);
    a.trace("No app with valid signature was providing our service.");
    throw new g();
    try
    {
      a.trace("Blocking while service is being bound!!");
      this.b = ((com.amazon.venezia.command.h)this.e.take());
      a.trace("service bound, returning!!");
      if (KiwiLogger.TRACE_ON)
      {
        long l2 = System.currentTimeMillis();
        a.trace("Kiwi.BindService Time: " + (l2 - l1));
      }
      this.h.a("PACKAGE", localObject2);
      this.c = ((String)localObject2);
      label351:
      this.h.a("COMMAND", paramw.a());
      this.b.a(paramw, this.j);
      return d();
    }
    catch (InterruptedException paramw)
    {
      Thread.currentThread().interrupt();
      throw new com.amazon.android.b.f();
    }
  }
  
  public final String a()
  {
    return this.c;
  }
  
  public final void b()
  {
    KiwiLogger localKiwiLogger = a;
    StringBuilder localStringBuilder = new StringBuilder().append("Finishing CommandServiceClient, unbinding service: ");
    if (this.b != null) {}
    for (boolean bool = true;; bool = false)
    {
      localKiwiLogger.trace(bool);
      if (this.b != null)
      {
        this.f.unbindService(this.i);
        this.b = null;
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */