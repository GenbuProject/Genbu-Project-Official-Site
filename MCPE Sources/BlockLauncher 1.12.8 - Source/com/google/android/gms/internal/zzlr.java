package com.google.android.gms.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.RemoteException;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.CastRemoteDisplay.CastRemoteDisplaySessionCallbacks;
import com.google.android.gms.cast.internal.zzl;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;

public class zzlr
  extends zzj<zzlt>
  implements IBinder.DeathRecipient
{
  private static final zzl zzaaf = new zzl("CastRemoteDisplayClientImpl");
  private CastDevice zzZO;
  private CastRemoteDisplay.CastRemoteDisplaySessionCallbacks zzaeL;
  
  public zzlr(Context paramContext, Looper paramLooper, zzf paramzzf, CastDevice paramCastDevice, CastRemoteDisplay.CastRemoteDisplaySessionCallbacks paramCastRemoteDisplaySessionCallbacks, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 83, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    zzaaf.zzb("instance created", new Object[0]);
    this.zzaeL = paramCastRemoteDisplaySessionCallbacks;
    this.zzZO = paramCastDevice;
  }
  
  public void binderDied() {}
  
  /* Error */
  public void disconnect()
  {
    // Byte code:
    //   0: getstatic 27	com/google/android/gms/internal/zzlr:zzaaf	Lcom/google/android/gms/cast/internal/zzl;
    //   3: ldc 54
    //   5: iconst_0
    //   6: anewarray 36	java/lang/Object
    //   9: invokevirtual 40	com/google/android/gms/cast/internal/zzl:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   12: aload_0
    //   13: aconst_null
    //   14: putfield 42	com/google/android/gms/internal/zzlr:zzaeL	Lcom/google/android/gms/cast/CastRemoteDisplay$CastRemoteDisplaySessionCallbacks;
    //   17: aload_0
    //   18: aconst_null
    //   19: putfield 44	com/google/android/gms/internal/zzlr:zzZO	Lcom/google/android/gms/cast/CastDevice;
    //   22: aload_0
    //   23: invokevirtual 58	com/google/android/gms/internal/zzlr:zzqJ	()Landroid/os/IInterface;
    //   26: checkcast 60	com/google/android/gms/internal/zzlt
    //   29: invokeinterface 62 1 0
    //   34: aload_0
    //   35: invokespecial 63	com/google/android/gms/common/internal/zzj:disconnect	()V
    //   38: return
    //   39: astore_1
    //   40: aload_0
    //   41: invokespecial 63	com/google/android/gms/common/internal/zzj:disconnect	()V
    //   44: return
    //   45: astore_1
    //   46: aload_0
    //   47: invokespecial 63	com/google/android/gms/common/internal/zzj:disconnect	()V
    //   50: aload_1
    //   51: athrow
    //   52: astore_1
    //   53: goto -13 -> 40
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	56	0	this	zzlr
    //   39	1	1	localIllegalStateException	IllegalStateException
    //   45	6	1	localObject	Object
    //   52	1	1	localRemoteException	RemoteException
    // Exception table:
    //   from	to	target	type
    //   22	34	39	java/lang/IllegalStateException
    //   22	34	45	finally
    //   22	34	52	android/os/RemoteException
  }
  
  public void zza(zzls paramzzls)
    throws RemoteException
  {
    zzaaf.zzb("stopRemoteDisplay", new Object[0]);
    ((zzlt)zzqJ()).zza(paramzzls);
  }
  
  public void zza(zzls paramzzls, int paramInt)
    throws RemoteException
  {
    ((zzlt)zzqJ()).zza(paramzzls, paramInt);
  }
  
  public void zza(zzls paramzzls, final zzlu paramzzlu, String paramString)
    throws RemoteException
  {
    zzaaf.zzb("startRemoteDisplay", new Object[0]);
    paramzzlu = new zzlu.zza()
    {
      public void zzbp(int paramAnonymousInt)
        throws RemoteException
      {
        zzlr.zzoC().zzb("onRemoteDisplayEnded", new Object[0]);
        if (paramzzlu != null) {
          paramzzlu.zzbp(paramAnonymousInt);
        }
        if (zzlr.zzb(zzlr.this) != null) {
          zzlr.zzb(zzlr.this).onRemoteDisplayEnded(new Status(paramAnonymousInt));
        }
      }
    };
    ((zzlt)zzqJ()).zza(paramzzls, paramzzlu, this.zzZO.getDeviceId(), paramString);
  }
  
  public zzlt zzaG(IBinder paramIBinder)
  {
    return zzlt.zza.zzaI(paramIBinder);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.cast.remote_display.service.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.cast.remote_display.ICastRemoteDisplayService";
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzlr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */