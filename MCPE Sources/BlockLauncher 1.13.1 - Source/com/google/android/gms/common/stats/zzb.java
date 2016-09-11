package com.google.android.gms.common.stats;

import android.annotation.SuppressLint;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Debug;
import android.os.Process;
import android.os.SystemClock;
import android.util.Log;
import com.google.android.gms.internal.zzlz;
import com.google.android.gms.internal.zzmp;
import com.google.android.gms.internal.zznf;
import java.util.Arrays;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public class zzb
{
  private static final Object zzalX = new Object();
  private static zzb zzanp;
  private static Integer zzanv;
  private final List<String> zzanq;
  private final List<String> zzanr;
  private final List<String> zzans;
  private final List<String> zzant;
  private zze zzanu;
  private zze zzanw;
  
  private zzb()
  {
    if (getLogLevel() == zzd.LOG_LEVEL_OFF)
    {
      this.zzanq = Collections.EMPTY_LIST;
      this.zzanr = Collections.EMPTY_LIST;
      this.zzans = Collections.EMPTY_LIST;
      this.zzant = Collections.EMPTY_LIST;
      return;
    }
    Object localObject = (String)zzc.zza.zzanA.get();
    if (localObject == null)
    {
      localObject = Collections.EMPTY_LIST;
      this.zzanq = ((List)localObject);
      localObject = (String)zzc.zza.zzanB.get();
      if (localObject != null) {
        break label200;
      }
      localObject = Collections.EMPTY_LIST;
      label83:
      this.zzanr = ((List)localObject);
      localObject = (String)zzc.zza.zzanC.get();
      if (localObject != null) {
        break label213;
      }
      localObject = Collections.EMPTY_LIST;
      label106:
      this.zzans = ((List)localObject);
      localObject = (String)zzc.zza.zzanD.get();
      if (localObject != null) {
        break label226;
      }
    }
    label200:
    label213:
    label226:
    for (localObject = Collections.EMPTY_LIST;; localObject = Arrays.asList(((String)localObject).split(",")))
    {
      this.zzant = ((List)localObject);
      this.zzanu = new zze(1024, ((Long)zzc.zza.zzanE.get()).longValue());
      this.zzanw = new zze(1024, ((Long)zzc.zza.zzanE.get()).longValue());
      return;
      localObject = Arrays.asList(((String)localObject).split(","));
      break;
      localObject = Arrays.asList(((String)localObject).split(","));
      break label83;
      localObject = Arrays.asList(((String)localObject).split(","));
      break label106;
    }
  }
  
  private static int getLogLevel()
  {
    if (zzanv == null) {}
    for (;;)
    {
      try
      {
        if (!zzmp.zzkr()) {
          continue;
        }
        i = ((Integer)zzc.zza.zzanz.get()).intValue();
        zzanv = Integer.valueOf(i);
      }
      catch (SecurityException localSecurityException)
      {
        int i;
        zzanv = Integer.valueOf(zzd.LOG_LEVEL_OFF);
        continue;
      }
      return zzanv.intValue();
      i = zzd.LOG_LEVEL_OFF;
    }
  }
  
  private void zza(Context paramContext, String paramString1, int paramInt, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    long l2 = System.currentTimeMillis();
    Object localObject2 = null;
    Object localObject1 = localObject2;
    if ((getLogLevel() & zzd.zzanJ) != 0)
    {
      localObject1 = localObject2;
      if (paramInt != 13) {
        localObject1 = zznf.zzn(3, 5);
      }
    }
    long l1 = 0L;
    if ((getLogLevel() & zzd.zzanL) != 0) {
      l1 = Debug.getNativeHeapAllocatedSize();
    }
    if ((paramInt == 1) || (paramInt == 4) || (paramInt == 14)) {}
    for (paramString1 = new ConnectionEvent(l2, paramInt, null, null, null, null, (String)localObject1, paramString1, SystemClock.elapsedRealtime(), l1);; paramString1 = new ConnectionEvent(l2, paramInt, paramString2, paramString3, paramString4, paramString5, (String)localObject1, paramString1, SystemClock.elapsedRealtime(), l1))
    {
      paramContext.startService(new Intent().setComponent(zzd.zzanF).putExtra("com.google.android.gms.common.stats.EXTRA_LOG_EVENT", paramString1));
      return;
    }
  }
  
  private void zza(Context paramContext, String paramString1, String paramString2, Intent paramIntent, int paramInt)
  {
    Object localObject2 = null;
    if ((!zzrQ()) || (this.zzanu == null)) {}
    do
    {
      return;
      if ((paramInt != 4) && (paramInt != 1)) {
        break;
      }
    } while (!this.zzanu.zzcT(paramString1));
    Object localObject1 = null;
    String str = null;
    paramIntent = (Intent)localObject2;
    for (;;)
    {
      zza(paramContext, paramString1, paramInt, paramIntent, paramString2, str, (String)localObject1);
      return;
      localObject1 = zzd(paramContext, paramIntent);
      if (localObject1 == null)
      {
        Log.w("ConnectionTracker", String.format("Client %s made an invalid request %s", new Object[] { paramString2, paramIntent.toUri(0) }));
        return;
      }
      str = ((ServiceInfo)localObject1).processName;
      localObject1 = ((ServiceInfo)localObject1).name;
      paramIntent = zznf.zzaz(paramContext);
      if (!zzb(paramIntent, paramString2, str, (String)localObject1)) {
        break;
      }
      this.zzanu.zzcS(paramString1);
    }
  }
  
  private String zzb(ServiceConnection paramServiceConnection)
  {
    return String.valueOf(Process.myPid() << 32 | System.identityHashCode(paramServiceConnection));
  }
  
  private boolean zzb(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    int i = getLogLevel();
    return (!this.zzanq.contains(paramString1)) && (!this.zzanr.contains(paramString2)) && (!this.zzans.contains(paramString3)) && (!this.zzant.contains(paramString4)) && ((!paramString3.equals(paramString1)) || ((i & zzd.zzanK) == 0));
  }
  
  private boolean zzc(Context paramContext, Intent paramIntent)
  {
    paramIntent = paramIntent.getComponent();
    if ((paramIntent == null) || ((com.google.android.gms.common.internal.zzd.zzakE) && ("com.google.android.gms".equals(paramIntent.getPackageName())))) {
      return false;
    }
    return zzmp.zzk(paramContext, paramIntent.getPackageName());
  }
  
  private static ServiceInfo zzd(Context paramContext, Intent paramIntent)
  {
    paramContext = paramContext.getPackageManager().queryIntentServices(paramIntent, 128);
    if ((paramContext == null) || (paramContext.size() == 0))
    {
      Log.w("ConnectionTracker", String.format("There are no handler of this intent: %s\n Stack trace: %s", new Object[] { paramIntent.toUri(0), zznf.zzn(3, 20) }));
      return null;
    }
    if (paramContext.size() > 1)
    {
      Log.w("ConnectionTracker", String.format("Multiple handlers found for this intent: %s\n Stack trace: %s", new Object[] { paramIntent.toUri(0), zznf.zzn(3, 20) }));
      paramIntent = paramContext.iterator();
      if (paramIntent.hasNext())
      {
        Log.w("ConnectionTracker", ((ResolveInfo)paramIntent.next()).serviceInfo.name);
        return null;
      }
    }
    return ((ResolveInfo)paramContext.get(0)).serviceInfo;
  }
  
  public static zzb zzrP()
  {
    synchronized (zzalX)
    {
      if (zzanp == null) {
        zzanp = new zzb();
      }
      return zzanp;
    }
  }
  
  private boolean zzrQ()
  {
    if (!com.google.android.gms.common.internal.zzd.zzakE) {}
    while (getLogLevel() == zzd.LOG_LEVEL_OFF) {
      return false;
    }
    return true;
  }
  
  @SuppressLint({"UntrackedBindService"})
  public void zza(Context paramContext, ServiceConnection paramServiceConnection)
  {
    paramContext.unbindService(paramServiceConnection);
    zza(paramContext, zzb(paramServiceConnection), null, null, 1);
  }
  
  public void zza(Context paramContext, ServiceConnection paramServiceConnection, String paramString, Intent paramIntent)
  {
    zza(paramContext, zzb(paramServiceConnection), paramString, paramIntent, 3);
  }
  
  public boolean zza(Context paramContext, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    return zza(paramContext, paramContext.getClass().getName(), paramIntent, paramServiceConnection, paramInt);
  }
  
  @SuppressLint({"UntrackedBindService"})
  public boolean zza(Context paramContext, String paramString, Intent paramIntent, ServiceConnection paramServiceConnection, int paramInt)
  {
    if (zzc(paramContext, paramIntent))
    {
      Log.w("ConnectionTracker", "Attempted to bind to a service in a STOPPED package.");
      return false;
    }
    boolean bool = paramContext.bindService(paramIntent, paramServiceConnection, paramInt);
    if (bool) {
      zza(paramContext, zzb(paramServiceConnection), paramString, paramIntent, 2);
    }
    return bool;
  }
  
  public void zzb(Context paramContext, ServiceConnection paramServiceConnection)
  {
    zza(paramContext, zzb(paramServiceConnection), null, null, 4);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\stats\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */