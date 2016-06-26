package com.google.android.gms.cast.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.IBinder;
import android.os.Looper;
import android.os.RemoteException;
import android.text.TextUtils;
import com.google.android.gms.cast.ApplicationMetadata;
import com.google.android.gms.cast.Cast.ApplicationConnectionResult;
import com.google.android.gms.cast.Cast.Listener;
import com.google.android.gms.cast.Cast.MessageReceivedCallback;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.cast.JoinOptions;
import com.google.android.gms.cast.LaunchOptions;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.internal.BinderWrapper;
import com.google.android.gms.common.internal.zzj;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.AtomicReference;

public final class zze
  extends zzj<zzi>
{
  private static final zzl zzaaf = new zzl("CastClientImpl");
  private static final Object zzadP = new Object();
  private static final Object zzadQ = new Object();
  private final Cast.Listener zzZP;
  private double zzabs;
  private boolean zzabt;
  private final long zzadA;
  private zzb zzadB;
  private String zzadC;
  private boolean zzadD;
  private boolean zzadE;
  private boolean zzadF;
  private int zzadG;
  private int zzadH;
  private final AtomicLong zzadI;
  private String zzadJ;
  private String zzadK;
  private Bundle zzadL;
  private final Map<Long, zza.zzb<Status>> zzadM;
  private zza.zzb<Cast.ApplicationConnectionResult> zzadN;
  private zza.zzb<Status> zzadO;
  private ApplicationMetadata zzadx;
  private final CastDevice zzady;
  private final Map<String, Cast.MessageReceivedCallback> zzadz;
  
  public zze(Context paramContext, Looper paramLooper, com.google.android.gms.common.internal.zzf paramzzf, CastDevice paramCastDevice, long paramLong, Cast.Listener paramListener, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    super(paramContext, paramLooper, 10, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzady = paramCastDevice;
    this.zzZP = paramListener;
    this.zzadA = paramLong;
    this.zzadz = new HashMap();
    this.zzadI = new AtomicLong(0L);
    this.zzadM = new HashMap();
    zzoh();
  }
  
  private void zza(ApplicationStatus paramApplicationStatus)
  {
    paramApplicationStatus = paramApplicationStatus.zzoe();
    if (!zzf.zza(paramApplicationStatus, this.zzadC)) {
      this.zzadC = paramApplicationStatus;
    }
    for (boolean bool = true;; bool = false)
    {
      zzaaf.zzb("hasChanged=%b, mFirstApplicationStatusUpdate=%b", new Object[] { Boolean.valueOf(bool), Boolean.valueOf(this.zzadD) });
      if ((this.zzZP != null) && ((bool) || (this.zzadD))) {
        this.zzZP.onApplicationStatusChanged();
      }
      this.zzadD = false;
      return;
    }
  }
  
  private void zza(DeviceStatus paramDeviceStatus)
  {
    ApplicationMetadata localApplicationMetadata = paramDeviceStatus.getApplicationMetadata();
    if (!zzf.zza(localApplicationMetadata, this.zzadx))
    {
      this.zzadx = localApplicationMetadata;
      this.zzZP.onApplicationMetadataChanged(this.zzadx);
    }
    double d = paramDeviceStatus.zzok();
    if ((d != NaN.0D) && (Math.abs(d - this.zzabs) > 1.0E-7D)) {
      this.zzabs = d;
    }
    for (boolean bool1 = true;; bool1 = false)
    {
      boolean bool2 = paramDeviceStatus.zzot();
      if (bool2 != this.zzabt)
      {
        this.zzabt = bool2;
        bool1 = true;
      }
      zzaaf.zzb("hasVolumeChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[] { Boolean.valueOf(bool1), Boolean.valueOf(this.zzadE) });
      if ((this.zzZP != null) && ((bool1) || (this.zzadE))) {
        this.zzZP.onVolumeChanged();
      }
      int i = paramDeviceStatus.zzol();
      if (i != this.zzadG) {
        this.zzadG = i;
      }
      for (bool1 = true;; bool1 = false)
      {
        zzaaf.zzb("hasActiveInputChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[] { Boolean.valueOf(bool1), Boolean.valueOf(this.zzadE) });
        if ((this.zzZP != null) && ((bool1) || (this.zzadE))) {
          this.zzZP.onActiveInputStateChanged(this.zzadG);
        }
        i = paramDeviceStatus.zzom();
        if (i != this.zzadH) {
          this.zzadH = i;
        }
        for (bool1 = true;; bool1 = false)
        {
          zzaaf.zzb("hasStandbyStateChanged=%b, mFirstDeviceStatusUpdate=%b", new Object[] { Boolean.valueOf(bool1), Boolean.valueOf(this.zzadE) });
          if ((this.zzZP != null) && ((bool1) || (this.zzadE))) {
            this.zzZP.onStandbyStateChanged(this.zzadH);
          }
          this.zzadE = false;
          return;
        }
      }
    }
  }
  
  private void zza(zza.zzb<Cast.ApplicationConnectionResult> paramzzb)
  {
    synchronized (zzadP)
    {
      if (this.zzadN != null) {
        this.zzadN.zzs(new zza(new Status(2002)));
      }
      this.zzadN = paramzzb;
      return;
    }
  }
  
  private void zzc(zza.zzb<Status> paramzzb)
  {
    synchronized (zzadQ)
    {
      if (this.zzadO != null)
      {
        paramzzb.zzs(new Status(2001));
        return;
      }
      this.zzadO = paramzzb;
      return;
    }
  }
  
  private void zzoh()
  {
    this.zzadF = false;
    this.zzadG = -1;
    this.zzadH = -1;
    this.zzadx = null;
    this.zzadC = null;
    this.zzabs = 0.0D;
    this.zzabt = false;
  }
  
  private void zzon()
  {
    zzaaf.zzb("removing all MessageReceivedCallbacks", new Object[0]);
    synchronized (this.zzadz)
    {
      this.zzadz.clear();
      return;
    }
  }
  
  private void zzoo()
    throws IllegalStateException
  {
    if ((!this.zzadF) || (this.zzadB == null) || (this.zzadB.isDisposed())) {
      throw new IllegalStateException("Not connected to a device");
    }
  }
  
  /* Error */
  public void disconnect()
  {
    // Byte code:
    //   0: getstatic 74	com/google/android/gms/cast/internal/zze:zzaaf	Lcom/google/android/gms/cast/internal/zzl;
    //   3: ldc_w 283
    //   6: iconst_2
    //   7: anewarray 76	java/lang/Object
    //   10: dup
    //   11: iconst_0
    //   12: aload_0
    //   13: getfield 266	com/google/android/gms/cast/internal/zze:zzadB	Lcom/google/android/gms/cast/internal/zze$zzb;
    //   16: aastore
    //   17: dup
    //   18: iconst_1
    //   19: aload_0
    //   20: invokevirtual 286	com/google/android/gms/cast/internal/zze:isConnected	()Z
    //   23: invokestatic 141	java/lang/Boolean:valueOf	(Z)Ljava/lang/Boolean;
    //   26: aastore
    //   27: invokevirtual 146	com/google/android/gms/cast/internal/zzl:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   30: aload_0
    //   31: getfield 266	com/google/android/gms/cast/internal/zze:zzadB	Lcom/google/android/gms/cast/internal/zze$zzb;
    //   34: astore_1
    //   35: aload_0
    //   36: aconst_null
    //   37: putfield 266	com/google/android/gms/cast/internal/zze:zzadB	Lcom/google/android/gms/cast/internal/zze$zzb;
    //   40: aload_1
    //   41: ifnull +10 -> 51
    //   44: aload_1
    //   45: invokevirtual 290	com/google/android/gms/cast/internal/zze$zzb:zzos	()Lcom/google/android/gms/cast/internal/zze;
    //   48: ifnonnull +17 -> 65
    //   51: getstatic 74	com/google/android/gms/cast/internal/zze:zzaaf	Lcom/google/android/gms/cast/internal/zzl;
    //   54: ldc_w 292
    //   57: iconst_0
    //   58: anewarray 76	java/lang/Object
    //   61: invokevirtual 146	com/google/android/gms/cast/internal/zzl:zzb	(Ljava/lang/String;[Ljava/lang/Object;)V
    //   64: return
    //   65: aload_0
    //   66: invokespecial 294	com/google/android/gms/cast/internal/zze:zzon	()V
    //   69: aload_0
    //   70: invokevirtual 298	com/google/android/gms/cast/internal/zze:zzqJ	()Landroid/os/IInterface;
    //   73: checkcast 300	com/google/android/gms/cast/internal/zzi
    //   76: invokeinterface 302 1 0
    //   81: aload_0
    //   82: invokespecial 303	com/google/android/gms/common/internal/zzj:disconnect	()V
    //   85: return
    //   86: astore_1
    //   87: getstatic 74	com/google/android/gms/cast/internal/zze:zzaaf	Lcom/google/android/gms/cast/internal/zzl;
    //   90: aload_1
    //   91: ldc_w 305
    //   94: iconst_1
    //   95: anewarray 76	java/lang/Object
    //   98: dup
    //   99: iconst_0
    //   100: aload_1
    //   101: invokevirtual 310	java/lang/Exception:getMessage	()Ljava/lang/String;
    //   104: aastore
    //   105: invokevirtual 313	com/google/android/gms/cast/internal/zzl:zzb	(Ljava/lang/Throwable;Ljava/lang/String;[Ljava/lang/Object;)V
    //   108: aload_0
    //   109: invokespecial 303	com/google/android/gms/common/internal/zzj:disconnect	()V
    //   112: return
    //   113: astore_1
    //   114: aload_0
    //   115: invokespecial 303	com/google/android/gms/common/internal/zzj:disconnect	()V
    //   118: aload_1
    //   119: athrow
    //   120: astore_1
    //   121: goto -34 -> 87
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	124	0	this	zze
    //   34	11	1	localzzb	zzb
    //   86	15	1	localIllegalStateException	IllegalStateException
    //   113	6	1	localObject	Object
    //   120	1	1	localRemoteException	RemoteException
    // Exception table:
    //   from	to	target	type
    //   69	81	86	java/lang/IllegalStateException
    //   69	81	113	finally
    //   87	108	113	finally
    //   69	81	120	android/os/RemoteException
  }
  
  public ApplicationMetadata getApplicationMetadata()
    throws IllegalStateException
  {
    zzoo();
    return this.zzadx;
  }
  
  public String getApplicationStatus()
    throws IllegalStateException
  {
    zzoo();
    return this.zzadC;
  }
  
  public boolean isMute()
    throws IllegalStateException
  {
    zzoo();
    return this.zzabt;
  }
  
  public void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    super.onConnectionFailed(paramConnectionResult);
    zzon();
  }
  
  public void zzX(boolean paramBoolean)
    throws IllegalStateException, RemoteException
  {
    ((zzi)zzqJ()).zza(paramBoolean, this.zzabs, this.zzabt);
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    zzaaf.zzb("in onPostInitHandler; statusCode=%d", new Object[] { Integer.valueOf(paramInt1) });
    if ((paramInt1 == 0) || (paramInt1 == 1001))
    {
      this.zzadF = true;
      this.zzadD = true;
      this.zzadE = true;
    }
    for (;;)
    {
      int i = paramInt1;
      if (paramInt1 == 1001)
      {
        this.zzadL = new Bundle();
        this.zzadL.putBoolean("com.google.android.gms.cast.EXTRA_APP_NO_LONGER_RUNNING", true);
        i = 0;
      }
      super.zza(i, paramIBinder, paramBundle, paramInt2);
      return;
      this.zzadF = false;
    }
  }
  
  public void zza(String paramString, Cast.MessageReceivedCallback paramMessageReceivedCallback)
    throws IllegalArgumentException, IllegalStateException, RemoteException
  {
    zzf.zzch(paramString);
    zzcg(paramString);
    if (paramMessageReceivedCallback != null) {}
    synchronized (this.zzadz)
    {
      this.zzadz.put(paramString, paramMessageReceivedCallback);
      ((zzi)zzqJ()).zzcl(paramString);
      return;
    }
  }
  
  public void zza(String paramString, LaunchOptions paramLaunchOptions, zza.zzb<Cast.ApplicationConnectionResult> paramzzb)
    throws IllegalStateException, RemoteException
  {
    zza(paramzzb);
    ((zzi)zzqJ()).zza(paramString, paramLaunchOptions);
  }
  
  public void zza(String paramString, zza.zzb<Status> paramzzb)
    throws IllegalStateException, RemoteException
  {
    zzc(paramzzb);
    ((zzi)zzqJ()).zzck(paramString);
  }
  
  public void zza(String paramString1, String paramString2, JoinOptions paramJoinOptions, zza.zzb<Cast.ApplicationConnectionResult> paramzzb)
    throws IllegalStateException, RemoteException
  {
    zza(paramzzb);
    paramzzb = paramJoinOptions;
    if (paramJoinOptions == null) {
      paramzzb = new JoinOptions();
    }
    ((zzi)zzqJ()).zza(paramString1, paramString2, paramzzb);
  }
  
  public void zza(String paramString1, String paramString2, zza.zzb<Status> paramzzb)
    throws IllegalArgumentException, IllegalStateException, RemoteException
  {
    if (TextUtils.isEmpty(paramString2)) {
      throw new IllegalArgumentException("The message payload cannot be null or empty");
    }
    if (paramString2.length() > 65536) {
      throw new IllegalArgumentException("Message exceeds maximum size");
    }
    zzf.zzch(paramString1);
    zzoo();
    long l = this.zzadI.incrementAndGet();
    try
    {
      this.zzadM.put(Long.valueOf(l), paramzzb);
      ((zzi)zzqJ()).zzb(paramString1, paramString2, l);
      return;
    }
    catch (Throwable paramString1)
    {
      this.zzadM.remove(Long.valueOf(l));
      throw paramString1;
    }
  }
  
  public void zza(String paramString, boolean paramBoolean, zza.zzb<Cast.ApplicationConnectionResult> paramzzb)
    throws IllegalStateException, RemoteException
  {
    LaunchOptions localLaunchOptions = new LaunchOptions();
    localLaunchOptions.setRelaunchIfRunning(paramBoolean);
    zza(paramString, localLaunchOptions, paramzzb);
  }
  
  protected zzi zzaE(IBinder paramIBinder)
  {
    return zzi.zza.zzaF(paramIBinder);
  }
  
  public void zzb(zza.zzb<Status> paramzzb)
    throws IllegalStateException, RemoteException
  {
    zzc(paramzzb);
    ((zzi)zzqJ()).zzou();
  }
  
  public void zzcg(String paramString)
    throws IllegalArgumentException, RemoteException
  {
    if (TextUtils.isEmpty(paramString)) {
      throw new IllegalArgumentException("Channel namespace cannot be null or empty");
    }
    synchronized (this.zzadz)
    {
      Cast.MessageReceivedCallback localMessageReceivedCallback = (Cast.MessageReceivedCallback)this.zzadz.remove(paramString);
      if (localMessageReceivedCallback == null) {}
    }
  }
  
  public void zzf(double paramDouble)
    throws IllegalArgumentException, IllegalStateException, RemoteException
  {
    if ((Double.isInfinite(paramDouble)) || (Double.isNaN(paramDouble))) {
      throw new IllegalArgumentException("Volume cannot be " + paramDouble);
    }
    ((zzi)zzqJ()).zza(paramDouble, this.zzabs, this.zzabt);
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.cast.service.BIND_CAST_DEVICE_CONTROLLER_SERVICE";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.cast.internal.ICastDeviceController";
  }
  
  protected Bundle zzml()
  {
    Bundle localBundle = new Bundle();
    zzaaf.zzb("getRemoteService(): mLastApplicationId=%s, mLastSessionId=%s", new Object[] { this.zzadJ, this.zzadK });
    this.zzady.putInBundle(localBundle);
    localBundle.putLong("com.google.android.gms.cast.EXTRA_CAST_FLAGS", this.zzadA);
    this.zzadB = new zzb(this);
    localBundle.putParcelable("listener", new BinderWrapper(this.zzadB.asBinder()));
    if (this.zzadJ != null)
    {
      localBundle.putString("last_application_id", this.zzadJ);
      if (this.zzadK != null) {
        localBundle.putString("last_session_id", this.zzadK);
      }
    }
    return localBundle;
  }
  
  public Bundle zzoi()
  {
    if (this.zzadL != null)
    {
      Bundle localBundle = this.zzadL;
      this.zzadL = null;
      return localBundle;
    }
    return super.zzoi();
  }
  
  public void zzoj()
    throws IllegalStateException, RemoteException
  {
    ((zzi)zzqJ()).zzoj();
  }
  
  public double zzok()
    throws IllegalStateException
  {
    zzoo();
    return this.zzabs;
  }
  
  public int zzol()
    throws IllegalStateException
  {
    zzoo();
    return this.zzadG;
  }
  
  public int zzom()
    throws IllegalStateException
  {
    zzoo();
    return this.zzadH;
  }
  
  private static final class zza
    implements Cast.ApplicationConnectionResult
  {
    private final String zzLq;
    private final Status zzUX;
    private final ApplicationMetadata zzadR;
    private final String zzadS;
    private final boolean zzadT;
    
    public zza(Status paramStatus)
    {
      this(paramStatus, null, null, null, false);
    }
    
    public zza(Status paramStatus, ApplicationMetadata paramApplicationMetadata, String paramString1, String paramString2, boolean paramBoolean)
    {
      this.zzUX = paramStatus;
      this.zzadR = paramApplicationMetadata;
      this.zzadS = paramString1;
      this.zzLq = paramString2;
      this.zzadT = paramBoolean;
    }
    
    public ApplicationMetadata getApplicationMetadata()
    {
      return this.zzadR;
    }
    
    public String getApplicationStatus()
    {
      return this.zzadS;
    }
    
    public String getSessionId()
    {
      return this.zzLq;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
    
    public boolean getWasLaunched()
    {
      return this.zzadT;
    }
  }
  
  private static class zzb
    extends zzj.zza
  {
    private final Handler mHandler;
    private final AtomicReference<zze> zzadU;
    
    public zzb(zze paramzze)
    {
      this.zzadU = new AtomicReference(paramzze);
      this.mHandler = new Handler(paramzze.getLooper());
    }
    
    private void zza(zze paramzze, long paramLong, int paramInt)
    {
      synchronized (zze.zzg(paramzze))
      {
        paramzze = (zza.zzb)zze.zzg(paramzze).remove(Long.valueOf(paramLong));
        if (paramzze != null) {
          paramzze.zzs(new Status(paramInt));
        }
        return;
      }
    }
    
    private boolean zza(zze paramzze, int paramInt)
    {
      synchronized ()
      {
        if (zze.zzh(paramzze) != null)
        {
          zze.zzh(paramzze).zzs(new Status(paramInt));
          zze.zzb(paramzze, null);
          return true;
        }
        return false;
      }
    }
    
    public boolean isDisposed()
    {
      return this.zzadU.get() == null;
    }
    
    public void onApplicationDisconnected(final int paramInt)
    {
      final zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {}
      do
      {
        return;
        zze.zza(localzze, null);
        zze.zzb(localzze, null);
        zza(localzze, paramInt);
      } while (zze.zzd(localzze) == null);
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          zze.zzd(localzze).onApplicationDisconnected(paramInt);
        }
      });
    }
    
    public void zza(ApplicationMetadata paramApplicationMetadata, String paramString1, String paramString2, boolean paramBoolean)
    {
      zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {
        return;
      }
      zze.zza(localzze, paramApplicationMetadata);
      zze.zza(localzze, paramApplicationMetadata.getApplicationId());
      zze.zzb(localzze, paramString2);
      synchronized (zze.zzoq())
      {
        if (zze.zzc(localzze) != null)
        {
          zze.zzc(localzze).zzs(new zze.zza(new Status(0), paramApplicationMetadata, paramString1, paramString2, paramBoolean));
          zze.zza(localzze, null);
        }
        return;
      }
    }
    
    public void zza(String paramString, double paramDouble, boolean paramBoolean)
    {
      zze.zzop().zzb("Deprecated callback: \"onStatusreceived\"", new Object[0]);
    }
    
    public void zza(String paramString, long paramLong, int paramInt)
    {
      paramString = (zze)this.zzadU.get();
      if (paramString == null) {
        return;
      }
      zza(paramString, paramLong, paramInt);
    }
    
    public void zzb(final ApplicationStatus paramApplicationStatus)
    {
      final zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {
        return;
      }
      zze.zzop().zzb("onApplicationStatusChanged", new Object[0]);
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          zze.zza(localzze, paramApplicationStatus);
        }
      });
    }
    
    public void zzb(final DeviceStatus paramDeviceStatus)
    {
      final zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {
        return;
      }
      zze.zzop().zzb("onDeviceStatusChanged", new Object[0]);
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          zze.zza(localzze, paramDeviceStatus);
        }
      });
    }
    
    public void zzb(String paramString, byte[] paramArrayOfByte)
    {
      if ((zze)this.zzadU.get() == null) {
        return;
      }
      zze.zzop().zzb("IGNORING: Receive (type=binary, ns=%s) <%d bytes>", new Object[] { paramString, Integer.valueOf(paramArrayOfByte.length) });
    }
    
    public void zzbk(int paramInt)
    {
      zze localzze = zzos();
      if (localzze == null) {}
      do
      {
        return;
        zze.zzop().zzb("ICastDeviceControllerListener.onDisconnected: %d", new Object[] { Integer.valueOf(paramInt) });
      } while (paramInt == 0);
      localzze.zzbS(2);
    }
    
    public void zzbl(int paramInt)
    {
      zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {
        return;
      }
      synchronized (zze.zzoq())
      {
        if (zze.zzc(localzze) != null)
        {
          zze.zzc(localzze).zzs(new zze.zza(new Status(paramInt)));
          zze.zza(localzze, null);
        }
        return;
      }
    }
    
    public void zzbm(int paramInt)
    {
      zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {
        return;
      }
      zza(localzze, paramInt);
    }
    
    public void zzbn(int paramInt)
    {
      zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {
        return;
      }
      zza(localzze, paramInt);
    }
    
    public void zzc(String paramString, long paramLong)
    {
      paramString = (zze)this.zzadU.get();
      if (paramString == null) {
        return;
      }
      zza(paramString, paramLong, 0);
    }
    
    public zze zzos()
    {
      zze localzze = (zze)this.zzadU.getAndSet(null);
      if (localzze == null) {
        return null;
      }
      zze.zzb(localzze);
      return localzze;
    }
    
    public void zzt(final String paramString1, final String paramString2)
    {
      final zze localzze = (zze)this.zzadU.get();
      if (localzze == null) {
        return;
      }
      zze.zzop().zzb("Receive (type=text, ns=%s) %s", new Object[] { paramString1, paramString2 });
      this.mHandler.post(new Runnable()
      {
        public void run()
        {
          synchronized (zze.zze(localzze))
          {
            Cast.MessageReceivedCallback localMessageReceivedCallback = (Cast.MessageReceivedCallback)zze.zze(localzze).get(paramString1);
            if (localMessageReceivedCallback != null)
            {
              localMessageReceivedCallback.onMessageReceived(zze.zzf(localzze), paramString1, paramString2);
              return;
            }
          }
          zze.zzop().zzb("Discarded message for unknown namespace '%s'", new Object[] { paramString1 });
        }
      });
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\internal\zze.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */