package com.google.android.gms.measurement.internal;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.os.Looper;
import android.os.RemoteException;
import android.support.annotation.MainThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.stats.zzb;
import com.google.android.gms.common.zzc;
import com.google.android.gms.measurement.AppMeasurementService;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class zzac
  extends zzz
{
  private final zza zzaYN;
  private zzm zzaYO;
  private Boolean zzaYP;
  private final zzf zzaYQ;
  private final zzaf zzaYR;
  private final List<Runnable> zzaYS = new ArrayList();
  private final zzf zzaYT;
  
  protected zzac(zzw paramzzw)
  {
    super(paramzzw);
    this.zzaYR = new zzaf(paramzzw.zzjl());
    this.zzaYN = new zza();
    this.zzaYQ = new zzf(paramzzw)
    {
      public void run()
      {
        zzac.zzb(zzac.this);
      }
    };
    this.zzaYT = new zzf(paramzzw)
    {
      public void run()
      {
        zzac.this.zzAo().zzCF().zzfg("Tasks have been queued for a long time");
      }
    };
  }
  
  @WorkerThread
  private void onServiceDisconnected(ComponentName paramComponentName)
  {
    zzjk();
    if (this.zzaYO != null)
    {
      this.zzaYO = null;
      zzAo().zzCK().zzj("Disconnected from device MeasurementService", paramComponentName);
      zzDr();
    }
  }
  
  private boolean zzDp()
  {
    List localList = getContext().getPackageManager().queryIntentServices(new Intent(getContext(), AppMeasurementService.class), 65536);
    return (localList != null) && (localList.size() > 0);
  }
  
  @WorkerThread
  private boolean zzDq()
  {
    zzjk();
    zzjv();
    if (zzCp().zzkr()) {
      return true;
    }
    zzAo().zzCK().zzfg("Checking service availability");
    switch (zzc.zzoK().isGooglePlayServicesAvailable(getContext()))
    {
    default: 
      return false;
    case 0: 
      zzAo().zzCK().zzfg("Service available");
      return true;
    case 1: 
      zzAo().zzCK().zzfg("Service missing");
      return false;
    case 18: 
      zzAo().zzCK().zzfg("Service updating");
      return true;
    case 2: 
      zzAo().zzCK().zzfg("Service version update required");
      return false;
    case 3: 
      zzAo().zzCK().zzfg("Service disabled");
      return false;
    }
    zzAo().zzCK().zzfg("Service invalid");
    return false;
  }
  
  @WorkerThread
  private void zzDr()
  {
    zzjk();
    zzjX();
  }
  
  @WorkerThread
  private void zzDs()
  {
    zzjk();
    zzAo().zzCK().zzj("Processing queued up service tasks", Integer.valueOf(this.zzaYS.size()));
    Iterator localIterator = this.zzaYS.iterator();
    while (localIterator.hasNext())
    {
      Runnable localRunnable = (Runnable)localIterator.next();
      zzCn().zzg(localRunnable);
    }
    this.zzaYS.clear();
    this.zzaYT.cancel();
  }
  
  @WorkerThread
  private void zza(zzm paramzzm)
  {
    zzjk();
    com.google.android.gms.common.internal.zzx.zzz(paramzzm);
    this.zzaYO = paramzzm;
    zzjI();
    zzDs();
  }
  
  @WorkerThread
  private void zzi(Runnable paramRunnable)
    throws IllegalStateException
  {
    zzjk();
    if (isConnected())
    {
      paramRunnable.run();
      return;
    }
    if (this.zzaYS.size() >= zzCp().zzBS())
    {
      zzAo().zzCE().zzfg("Discarding data. Max runnable queue size reached");
      return;
    }
    this.zzaYS.add(paramRunnable);
    if (!this.zzaTV.zzCZ()) {
      this.zzaYT.zzt(60000L);
    }
    zzjX();
  }
  
  @WorkerThread
  private void zzjI()
  {
    zzjk();
    this.zzaYR.start();
    if (!this.zzaTV.zzCZ()) {
      this.zzaYQ.zzt(zzCp().zzkM());
    }
  }
  
  @WorkerThread
  private void zzjJ()
  {
    zzjk();
    if (!isConnected()) {
      return;
    }
    zzAo().zzCK().zzfg("Inactivity, disconnecting from AppMeasurementService");
    disconnect();
  }
  
  @WorkerThread
  private void zzjX()
  {
    zzjk();
    zzjv();
    if (isConnected()) {
      return;
    }
    if (this.zzaYP == null)
    {
      this.zzaYP = zzCo().zzCP();
      if (this.zzaYP == null)
      {
        zzAo().zzCK().zzfg("State of service unknown");
        this.zzaYP = Boolean.valueOf(zzDq());
        zzCo().zzar(this.zzaYP.booleanValue());
      }
    }
    if (this.zzaYP.booleanValue())
    {
      zzAo().zzCK().zzfg("Using measurement service");
      this.zzaYN.zzDt();
      return;
    }
    if ((zzDp()) && (!this.zzaTV.zzCZ()))
    {
      zzAo().zzCK().zzfg("Using local app measurement service");
      Intent localIntent = new Intent("com.google.android.gms.measurement.START");
      localIntent.setComponent(new ComponentName(getContext(), AppMeasurementService.class));
      this.zzaYN.zzz(localIntent);
      return;
    }
    if (zzCp().zzks())
    {
      zzAo().zzCK().zzfg("Using direct local measurement implementation");
      zza(new zzx(this.zzaTV, true));
      return;
    }
    zzAo().zzCE().zzfg("Not in main process. Unable to use local measurement implementation. Please register the AppMeasurementService service in the app manifest");
  }
  
  @WorkerThread
  public void disconnect()
  {
    zzjk();
    zzjv();
    try
    {
      zzb.zzrP().zza(getContext(), this.zzaYN);
      this.zzaYO = null;
      return;
    }
    catch (IllegalStateException localIllegalStateException)
    {
      for (;;) {}
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      for (;;) {}
    }
  }
  
  @WorkerThread
  public boolean isConnected()
  {
    zzjk();
    zzjv();
    return this.zzaYO != null;
  }
  
  @WorkerThread
  protected void zzDl()
  {
    zzjk();
    zzjv();
    zzi(new Runnable()
    {
      public void run()
      {
        zzm localzzm = zzac.zzc(zzac.this);
        if (localzzm == null)
        {
          zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to send app launch");
          return;
        }
        try
        {
          localzzm.zza(zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
          zzac.zzd(zzac.this);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzac.this.zzAo().zzCE().zzj("Failed to send app launch to AppMeasurementService", localRemoteException);
        }
      }
    });
  }
  
  @WorkerThread
  protected void zzDo()
  {
    zzjk();
    zzjv();
    zzi(new Runnable()
    {
      public void run()
      {
        zzm localzzm = zzac.zzc(zzac.this);
        if (localzzm == null)
        {
          zzac.this.zzAo().zzCE().zzfg("Failed to send measurementEnabled to service");
          return;
        }
        try
        {
          localzzm.zzb(zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
          zzac.zzd(zzac.this);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzac.this.zzAo().zzCE().zzj("Failed to send measurementEnabled to AppMeasurementService", localRemoteException);
        }
      }
    });
  }
  
  @WorkerThread
  protected void zza(final UserAttributeParcel paramUserAttributeParcel)
  {
    zzjk();
    zzjv();
    zzi(new Runnable()
    {
      public void run()
      {
        zzm localzzm = zzac.zzc(zzac.this);
        if (localzzm == null)
        {
          zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to set user attribute");
          return;
        }
        try
        {
          localzzm.zza(paramUserAttributeParcel, zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
          zzac.zzd(zzac.this);
          return;
        }
        catch (RemoteException localRemoteException)
        {
          zzac.this.zzAo().zzCE().zzj("Failed to send attribute to AppMeasurementService", localRemoteException);
        }
      }
    });
  }
  
  @WorkerThread
  protected void zzb(final EventParcel paramEventParcel, final String paramString)
  {
    com.google.android.gms.common.internal.zzx.zzz(paramEventParcel);
    zzjk();
    zzjv();
    zzi(new Runnable()
    {
      public void run()
      {
        zzm localzzm = zzac.zzc(zzac.this);
        if (localzzm == null)
        {
          zzac.this.zzAo().zzCE().zzfg("Discarding data. Failed to send event to service");
          return;
        }
        for (;;)
        {
          try
          {
            if (TextUtils.isEmpty(paramString))
            {
              localzzm.zza(paramEventParcel, zzac.this.zzCg().zzfe(zzac.this.zzAo().zzCL()));
              zzac.zzd(zzac.this);
              return;
            }
          }
          catch (RemoteException localRemoteException)
          {
            zzac.this.zzAo().zzCE().zzj("Failed to send event to AppMeasurementService", localRemoteException);
            return;
          }
          localRemoteException.zza(paramEventParcel, paramString, zzac.this.zzAo().zzCL());
        }
      }
    });
  }
  
  protected void zziJ() {}
  
  protected class zza
    implements ServiceConnection, GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
  {
    private volatile boolean zzaYV;
    private volatile zzo zzaYW;
    
    protected zza() {}
    
    /* Error */
    @MainThread
    public void onConnected(@android.support.annotation.Nullable final android.os.Bundle paramBundle)
    {
      // Byte code:
      //   0: ldc 48
      //   2: invokestatic 54	com/google/android/gms/common/internal/zzx:zzcD	(Ljava/lang/String;)V
      //   5: aload_0
      //   6: monitorenter
      //   7: aload_0
      //   8: getfield 56	com/google/android/gms/measurement/internal/zzac$zza:zzaYW	Lcom/google/android/gms/measurement/internal/zzo;
      //   11: invokevirtual 62	com/google/android/gms/measurement/internal/zzo:zzqJ	()Landroid/os/IInterface;
      //   14: checkcast 64	com/google/android/gms/measurement/internal/zzm
      //   17: astore_1
      //   18: aload_0
      //   19: aconst_null
      //   20: putfield 56	com/google/android/gms/measurement/internal/zzac$zza:zzaYW	Lcom/google/android/gms/measurement/internal/zzo;
      //   23: aload_0
      //   24: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   27: invokevirtual 68	com/google/android/gms/measurement/internal/zzac:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   30: new 19	com/google/android/gms/measurement/internal/zzac$zza$3
      //   33: dup
      //   34: aload_0
      //   35: aload_1
      //   36: invokespecial 71	com/google/android/gms/measurement/internal/zzac$zza$3:<init>	(Lcom/google/android/gms/measurement/internal/zzac$zza;Lcom/google/android/gms/measurement/internal/zzm;)V
      //   39: invokevirtual 77	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   42: aload_0
      //   43: monitorexit
      //   44: return
      //   45: aload_0
      //   46: aconst_null
      //   47: putfield 56	com/google/android/gms/measurement/internal/zzac$zza:zzaYW	Lcom/google/android/gms/measurement/internal/zzo;
      //   50: aload_0
      //   51: iconst_0
      //   52: putfield 38	com/google/android/gms/measurement/internal/zzac$zza:zzaYV	Z
      //   55: goto -13 -> 42
      //   58: astore_1
      //   59: aload_0
      //   60: monitorexit
      //   61: aload_1
      //   62: athrow
      //   63: astore_1
      //   64: goto -19 -> 45
      //   67: astore_1
      //   68: goto -23 -> 45
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	71	0	this	zza
      //   0	71	1	paramBundle	android.os.Bundle
      // Exception table:
      //   from	to	target	type
      //   7	42	58	finally
      //   42	44	58	finally
      //   45	55	58	finally
      //   59	61	58	finally
      //   7	42	63	java/lang/IllegalStateException
      //   7	42	67	android/os/DeadObjectException
    }
    
    @MainThread
    public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
      com.google.android.gms.common.internal.zzx.zzcD("MeasurementServiceConnection.onConnectionFailed");
      zzp localzzp = zzac.this.zzaTV.zzCT();
      if (localzzp != null) {
        localzzp.zzCF().zzj("Service connection failed", paramConnectionResult);
      }
      try
      {
        this.zzaYV = false;
        this.zzaYW = null;
        return;
      }
      finally {}
    }
    
    @MainThread
    public void onConnectionSuspended(int paramInt)
    {
      com.google.android.gms.common.internal.zzx.zzcD("MeasurementServiceConnection.onConnectionSuspended");
      zzac.this.zzAo().zzCJ().zzfg("Service connection suspended");
      zzac.this.zzCn().zzg(new Runnable()
      {
        public void run()
        {
          zzac.zza(zzac.this, new ComponentName(zzac.this.getContext(), AppMeasurementService.class));
        }
      });
    }
    
    /* Error */
    @MainThread
    public void onServiceConnected(final ComponentName paramComponentName, android.os.IBinder paramIBinder)
    {
      // Byte code:
      //   0: ldc -122
      //   2: invokestatic 54	com/google/android/gms/common/internal/zzx:zzcD	(Ljava/lang/String;)V
      //   5: aload_0
      //   6: monitorenter
      //   7: aload_2
      //   8: ifnonnull +26 -> 34
      //   11: aload_0
      //   12: iconst_0
      //   13: putfield 38	com/google/android/gms/measurement/internal/zzac$zza:zzaYV	Z
      //   16: aload_0
      //   17: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   20: invokevirtual 115	com/google/android/gms/measurement/internal/zzac:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   23: invokevirtual 137	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   26: ldc -117
      //   28: invokevirtual 123	com/google/android/gms/measurement/internal/zzp$zza:zzfg	(Ljava/lang/String;)V
      //   31: aload_0
      //   32: monitorexit
      //   33: return
      //   34: aconst_null
      //   35: astore 4
      //   37: aconst_null
      //   38: astore_3
      //   39: aload 4
      //   41: astore_1
      //   42: aload_2
      //   43: invokeinterface 145 1 0
      //   48: astore 5
      //   50: aload 4
      //   52: astore_1
      //   53: ldc -109
      //   55: aload 5
      //   57: invokevirtual 153	java/lang/String:equals	(Ljava/lang/Object;)Z
      //   60: ifeq +67 -> 127
      //   63: aload 4
      //   65: astore_1
      //   66: aload_2
      //   67: invokestatic 159	com/google/android/gms/measurement/internal/zzm$zza:zzdn	(Landroid/os/IBinder;)Lcom/google/android/gms/measurement/internal/zzm;
      //   70: astore_2
      //   71: aload_2
      //   72: astore_1
      //   73: aload_0
      //   74: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   77: invokevirtual 115	com/google/android/gms/measurement/internal/zzac:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   80: invokevirtual 162	com/google/android/gms/measurement/internal/zzp:zzCK	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   83: ldc -92
      //   85: invokevirtual 123	com/google/android/gms/measurement/internal/zzp$zza:zzfg	(Ljava/lang/String;)V
      //   88: aload_2
      //   89: astore_1
      //   90: aload_1
      //   91: ifnonnull +80 -> 171
      //   94: aload_0
      //   95: iconst_0
      //   96: putfield 38	com/google/android/gms/measurement/internal/zzac$zza:zzaYV	Z
      //   99: invokestatic 170	com/google/android/gms/common/stats/zzb:zzrP	()Lcom/google/android/gms/common/stats/zzb;
      //   102: aload_0
      //   103: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   106: invokevirtual 174	com/google/android/gms/measurement/internal/zzac:getContext	()Landroid/content/Context;
      //   109: aload_0
      //   110: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   113: invokestatic 177	com/google/android/gms/measurement/internal/zzac:zza	(Lcom/google/android/gms/measurement/internal/zzac;)Lcom/google/android/gms/measurement/internal/zzac$zza;
      //   116: invokevirtual 180	com/google/android/gms/common/stats/zzb:zza	(Landroid/content/Context;Landroid/content/ServiceConnection;)V
      //   119: aload_0
      //   120: monitorexit
      //   121: return
      //   122: astore_1
      //   123: aload_0
      //   124: monitorexit
      //   125: aload_1
      //   126: athrow
      //   127: aload 4
      //   129: astore_1
      //   130: aload_0
      //   131: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   134: invokevirtual 115	com/google/android/gms/measurement/internal/zzac:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   137: invokevirtual 137	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   140: ldc -74
      //   142: aload 5
      //   144: invokevirtual 108	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
      //   147: aload_3
      //   148: astore_1
      //   149: goto -59 -> 90
      //   152: astore_2
      //   153: aload_0
      //   154: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   157: invokevirtual 115	com/google/android/gms/measurement/internal/zzac:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
      //   160: invokevirtual 137	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
      //   163: ldc -72
      //   165: invokevirtual 123	com/google/android/gms/measurement/internal/zzp$zza:zzfg	(Ljava/lang/String;)V
      //   168: goto -78 -> 90
      //   171: aload_0
      //   172: getfield 31	com/google/android/gms/measurement/internal/zzac$zza:zzaYU	Lcom/google/android/gms/measurement/internal/zzac;
      //   175: invokevirtual 68	com/google/android/gms/measurement/internal/zzac:zzCn	()Lcom/google/android/gms/measurement/internal/zzv;
      //   178: new 15	com/google/android/gms/measurement/internal/zzac$zza$1
      //   181: dup
      //   182: aload_0
      //   183: aload_1
      //   184: invokespecial 185	com/google/android/gms/measurement/internal/zzac$zza$1:<init>	(Lcom/google/android/gms/measurement/internal/zzac$zza;Lcom/google/android/gms/measurement/internal/zzm;)V
      //   187: invokevirtual 77	com/google/android/gms/measurement/internal/zzv:zzg	(Ljava/lang/Runnable;)V
      //   190: goto -71 -> 119
      //   193: astore_1
      //   194: goto -75 -> 119
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	197	0	this	zza
      //   0	197	1	paramComponentName	ComponentName
      //   0	197	2	paramIBinder	android.os.IBinder
      //   38	110	3	localObject1	Object
      //   35	93	4	localObject2	Object
      //   48	95	5	str	String
      // Exception table:
      //   from	to	target	type
      //   11	33	122	finally
      //   42	50	122	finally
      //   53	63	122	finally
      //   66	71	122	finally
      //   73	88	122	finally
      //   94	99	122	finally
      //   99	119	122	finally
      //   119	121	122	finally
      //   123	125	122	finally
      //   130	147	122	finally
      //   153	168	122	finally
      //   171	190	122	finally
      //   42	50	152	android/os/RemoteException
      //   53	63	152	android/os/RemoteException
      //   66	71	152	android/os/RemoteException
      //   73	88	152	android/os/RemoteException
      //   130	147	152	android/os/RemoteException
      //   99	119	193	java/lang/IllegalArgumentException
    }
    
    @MainThread
    public void onServiceDisconnected(final ComponentName paramComponentName)
    {
      com.google.android.gms.common.internal.zzx.zzcD("MeasurementServiceConnection.onServiceDisconnected");
      zzac.this.zzAo().zzCJ().zzfg("Service disconnected");
      zzac.this.zzCn().zzg(new Runnable()
      {
        public void run()
        {
          zzac.zza(zzac.this, paramComponentName);
        }
      });
    }
    
    @WorkerThread
    public void zzDt()
    {
      zzac.this.zzjk();
      Context localContext1 = zzac.this.getContext();
      try
      {
        if (this.zzaYV)
        {
          zzac.this.zzAo().zzCK().zzfg("Connection attempt already in progress");
          return;
        }
        if (this.zzaYW != null)
        {
          zzac.this.zzAo().zzCK().zzfg("Already awaiting connection attempt");
          return;
        }
      }
      finally {}
      this.zzaYW = new zzo(localContext2, Looper.getMainLooper(), com.google.android.gms.common.internal.zzf.zzat(localContext2), this, this);
      zzac.this.zzAo().zzCK().zzfg("Connecting to remote service");
      this.zzaYV = true;
      this.zzaYW.zzqG();
    }
    
    @WorkerThread
    public void zzz(Intent paramIntent)
    {
      zzac.this.zzjk();
      Context localContext = zzac.this.getContext();
      zzb localzzb = zzb.zzrP();
      try
      {
        if (this.zzaYV)
        {
          zzac.this.zzAo().zzCK().zzfg("Connection attempt already in progress");
          return;
        }
        this.zzaYV = true;
        localzzb.zza(localContext, paramIntent, zzac.zza(zzac.this), 129);
        return;
      }
      finally {}
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzac.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */