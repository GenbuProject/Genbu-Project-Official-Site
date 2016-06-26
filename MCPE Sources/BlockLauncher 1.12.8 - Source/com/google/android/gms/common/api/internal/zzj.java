package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IBinder.DeathRecipient;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.NonNull;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.ResultCallback;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.zza;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzk;
import com.google.android.gms.common.internal.zzk.zza;
import com.google.android.gms.internal.zzmf;
import com.google.android.gms.internal.zzmg;
import com.google.android.gms.internal.zzrn;
import com.google.android.gms.internal.zzro;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.Lock;

public final class zzj
  extends GoogleApiClient
  implements zzp.zza
{
  private final Context mContext;
  private final Lock zzXG;
  private final int zzagp;
  private final Looper zzagr;
  private final com.google.android.gms.common.zzc zzags;
  final Api.zza<? extends zzrn, zzro> zzagt;
  final Map<Api<?>, Integer> zzahA;
  private final zzk zzahL;
  private zzp zzahM = null;
  final Queue<zza.zza<?, ?>> zzahN = new LinkedList();
  private volatile boolean zzahO;
  private long zzahP = 120000L;
  private long zzahQ = 5000L;
  private final zza zzahR;
  zzc zzahS;
  final Map<Api.zzc<?>, Api.zzb> zzahT;
  Set<Scope> zzahU = new HashSet();
  private final Set<zzq<?>> zzahV = Collections.newSetFromMap(new WeakHashMap());
  final Set<zze<?>> zzahW = Collections.newSetFromMap(new ConcurrentHashMap(16, 0.75F, 2));
  private zza zzahX;
  private final ArrayList<zzc> zzahY;
  private Integer zzahZ = null;
  final zzf zzahz;
  Set<zzx> zzaia = null;
  private final zzd zzaib = new zzd()
  {
    public void zzc(zzj.zze<?> paramAnonymouszze)
    {
      zzj.this.zzahW.remove(paramAnonymouszze);
      if ((paramAnonymouszze.zzpa() != null) && (zzj.zza(zzj.this) != null)) {
        zzj.zza(zzj.this).remove(paramAnonymouszze.zzpa().intValue());
      }
    }
  };
  private final zzk.zza zzaic = new zzk.zza()
  {
    public boolean isConnected()
    {
      return zzj.this.isConnected();
    }
    
    public Bundle zzoi()
    {
      return null;
    }
  };
  
  public zzj(Context paramContext, Lock paramLock, Looper paramLooper, zzf paramzzf, com.google.android.gms.common.zzc paramzzc, Api.zza<? extends zzrn, zzro> paramzza, Map<Api<?>, Integer> paramMap, List<GoogleApiClient.ConnectionCallbacks> paramList, List<GoogleApiClient.OnConnectionFailedListener> paramList1, Map<Api.zzc<?>, Api.zzb> paramMap1, int paramInt1, int paramInt2, ArrayList<zzc> paramArrayList)
  {
    this.mContext = paramContext;
    this.zzXG = paramLock;
    this.zzahL = new zzk(paramLooper, this.zzaic);
    this.zzagr = paramLooper;
    this.zzahR = new zza(paramLooper);
    this.zzags = paramzzc;
    this.zzagp = paramInt1;
    if (this.zzagp >= 0) {
      this.zzahZ = Integer.valueOf(paramInt2);
    }
    this.zzahA = paramMap;
    this.zzahT = paramMap1;
    this.zzahY = paramArrayList;
    paramContext = paramList.iterator();
    while (paramContext.hasNext())
    {
      paramLock = (GoogleApiClient.ConnectionCallbacks)paramContext.next();
      this.zzahL.registerConnectionCallbacks(paramLock);
    }
    paramContext = paramList1.iterator();
    while (paramContext.hasNext())
    {
      paramLock = (GoogleApiClient.OnConnectionFailedListener)paramContext.next();
      this.zzahL.registerConnectionFailedListener(paramLock);
    }
    this.zzahz = paramzzf;
    this.zzagt = paramzza;
  }
  
  private void resume()
  {
    this.zzXG.lock();
    try
    {
      if (zzpB()) {
        zzpC();
      }
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public static int zza(Iterable<Api.zzb> paramIterable, boolean paramBoolean)
  {
    int k = 1;
    paramIterable = paramIterable.iterator();
    int i = 0;
    int j = 0;
    if (paramIterable.hasNext())
    {
      Api.zzb localzzb = (Api.zzb)paramIterable.next();
      if (localzzb.zzmE()) {
        j = 1;
      }
      if (!localzzb.zznb()) {
        break label85;
      }
      i = 1;
    }
    label85:
    for (;;)
    {
      break;
      if (j != 0)
      {
        j = k;
        if (i != 0)
        {
          j = k;
          if (paramBoolean) {
            j = 2;
          }
        }
        return j;
      }
      return 3;
    }
  }
  
  private void zza(final GoogleApiClient paramGoogleApiClient, final zzv paramzzv, final boolean paramBoolean)
  {
    zzmf.zzamA.zzf(paramGoogleApiClient).setResultCallback(new ResultCallback()
    {
      public void zzp(@NonNull Status paramAnonymousStatus)
      {
        com.google.android.gms.auth.api.signin.internal.zzq.zzaf(zzj.zzd(zzj.this)).zznr();
        if ((paramAnonymousStatus.isSuccess()) && (zzj.this.isConnected())) {
          zzj.this.reconnect();
        }
        paramzzv.zza(paramAnonymousStatus);
        if (paramBoolean) {
          paramGoogleApiClient.disconnect();
        }
      }
    });
  }
  
  private static void zza(zze<?> paramzze, zza paramzza, IBinder paramIBinder)
  {
    if (paramzze.isReady())
    {
      paramzze.zza(new zzb(paramzze, paramzza, paramIBinder, null));
      return;
    }
    if ((paramIBinder != null) && (paramIBinder.isBinderAlive()))
    {
      zzb localzzb = new zzb(paramzze, paramzza, paramIBinder, null);
      paramzze.zza(localzzb);
      try
      {
        paramIBinder.linkToDeath(localzzb, 0);
        return;
      }
      catch (RemoteException paramIBinder)
      {
        paramzze.cancel();
        paramzza.remove(paramzze.zzpa().intValue());
        return;
      }
    }
    paramzze.zza(null);
    paramzze.cancel();
    paramzza.remove(paramzze.zzpa().intValue());
  }
  
  private void zzbB(int paramInt)
  {
    if (this.zzahZ == null) {
      this.zzahZ = Integer.valueOf(paramInt);
    }
    while (this.zzahM != null)
    {
      return;
      if (this.zzahZ.intValue() != paramInt) {
        throw new IllegalStateException("Cannot use sign-in mode: " + zzbC(paramInt) + ". Mode was already set to " + zzbC(this.zzahZ.intValue()));
      }
    }
    Iterator localIterator = this.zzahT.values().iterator();
    paramInt = 0;
    int i = 0;
    if (localIterator.hasNext())
    {
      Api.zzb localzzb = (Api.zzb)localIterator.next();
      if (localzzb.zzmE()) {
        i = 1;
      }
      if (!localzzb.zznb()) {
        break label317;
      }
      paramInt = 1;
    }
    label317:
    for (;;)
    {
      break;
      switch (this.zzahZ.intValue())
      {
      }
      do
      {
        do
        {
          this.zzahM = new zzl(this.mContext, this, this.zzXG, this.zzagr, this.zzags, this.zzahT, this.zzahz, this.zzahA, this.zzagt, this.zzahY, this);
          return;
          if (i == 0) {
            throw new IllegalStateException("SIGN_IN_MODE_REQUIRED cannot be used on a GoogleApiClient that does not contain any authenticated APIs. Use connect() instead.");
          }
        } while (paramInt == 0);
        throw new IllegalStateException("Cannot use SIGN_IN_MODE_REQUIRED with GOOGLE_SIGN_IN_API. Use connect(SIGN_IN_MODE_OPTIONAL) instead.");
      } while (i == 0);
      this.zzahM = new zzd(this.mContext, this, this.zzXG, this.zzagr, this.zzags, this.zzahT, this.zzahz, this.zzahA, this.zzagt, this.zzahY);
      return;
    }
  }
  
  static String zzbC(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 3: 
      return "SIGN_IN_MODE_NONE";
    case 1: 
      return "SIGN_IN_MODE_REQUIRED";
    }
    return "SIGN_IN_MODE_OPTIONAL";
  }
  
  private void zzpC()
  {
    this.zzahL.zzqR();
    this.zzahM.connect();
  }
  
  private void zzpD()
  {
    this.zzXG.lock();
    try
    {
      if (zzpF()) {
        zzpC();
      }
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public ConnectionResult blockingConnect()
  {
    boolean bool2 = true;
    boolean bool1;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool1 = true;
    }
    for (;;)
    {
      com.google.android.gms.common.internal.zzx.zza(bool1, "blockingConnect must not be called on the UI thread");
      this.zzXG.lock();
      try
      {
        if (this.zzagp >= 0) {
          if (this.zzahZ != null)
          {
            bool1 = bool2;
            label45:
            com.google.android.gms.common.internal.zzx.zza(bool1, "Sign-in mode should have been set explicitly by auto-manage.");
          }
        }
        do
        {
          for (;;)
          {
            zzbB(this.zzahZ.intValue());
            this.zzahL.zzqR();
            ConnectionResult localConnectionResult = this.zzahM.blockingConnect();
            return localConnectionResult;
            bool1 = false;
            break;
            bool1 = false;
            break label45;
            if (this.zzahZ != null) {
              break label143;
            }
            this.zzahZ = Integer.valueOf(zza(this.zzahT.values(), false));
          }
        } while (this.zzahZ.intValue() != 2);
      }
      finally
      {
        this.zzXG.unlock();
      }
    }
    label143:
    throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
  }
  
  public ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit)
  {
    boolean bool = false;
    if (Looper.myLooper() != Looper.getMainLooper()) {
      bool = true;
    }
    com.google.android.gms.common.internal.zzx.zza(bool, "blockingConnect must not be called on the UI thread");
    com.google.android.gms.common.internal.zzx.zzb(paramTimeUnit, "TimeUnit must not be null");
    this.zzXG.lock();
    try
    {
      if (this.zzahZ == null) {
        this.zzahZ = Integer.valueOf(zza(this.zzahT.values(), false));
      }
      while (this.zzahZ.intValue() != 2)
      {
        zzbB(this.zzahZ.intValue());
        this.zzahL.zzqR();
        paramTimeUnit = this.zzahM.blockingConnect(paramLong, paramTimeUnit);
        return paramTimeUnit;
      }
      throw new IllegalStateException("Cannot call blockingConnect() when sign-in mode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public PendingResult<Status> clearDefaultAccountAndReconnect()
  {
    com.google.android.gms.common.internal.zzx.zza(isConnected(), "GoogleApiClient is not connected yet.");
    if (this.zzahZ.intValue() != 2) {}
    final zzv localzzv;
    for (boolean bool = true;; bool = false)
    {
      com.google.android.gms.common.internal.zzx.zza(bool, "Cannot use clearDefaultAccountAndReconnect with GOOGLE_SIGN_IN_API");
      localzzv = new zzv(this);
      if (!this.zzahT.containsKey(zzmf.zzUI)) {
        break;
      }
      zza(this, localzzv, false);
      return localzzv;
    }
    final AtomicReference localAtomicReference = new AtomicReference();
    Object localObject = new GoogleApiClient.ConnectionCallbacks()
    {
      public void onConnected(Bundle paramAnonymousBundle)
      {
        zzj.zza(zzj.this, (GoogleApiClient)localAtomicReference.get(), localzzv, true);
      }
      
      public void onConnectionSuspended(int paramAnonymousInt) {}
    };
    GoogleApiClient.OnConnectionFailedListener local4 = new GoogleApiClient.OnConnectionFailedListener()
    {
      public void onConnectionFailed(@NonNull ConnectionResult paramAnonymousConnectionResult)
      {
        localzzv.zza(new Status(8));
      }
    };
    localObject = new GoogleApiClient.Builder(this.mContext).addApi(zzmf.API).addConnectionCallbacks((GoogleApiClient.ConnectionCallbacks)localObject).addOnConnectionFailedListener(local4).setHandler(this.zzahR).build();
    localAtomicReference.set(localObject);
    ((GoogleApiClient)localObject).connect();
    return localzzv;
  }
  
  public void connect()
  {
    boolean bool = false;
    this.zzXG.lock();
    try
    {
      if (this.zzagp >= 0)
      {
        if (this.zzahZ != null) {
          bool = true;
        }
        com.google.android.gms.common.internal.zzx.zza(bool, "Sign-in mode should have been set explicitly by auto-manage.");
      }
      do
      {
        for (;;)
        {
          connect(this.zzahZ.intValue());
          return;
          if (this.zzahZ != null) {
            break;
          }
          this.zzahZ = Integer.valueOf(zza(this.zzahT.values(), false));
        }
      } while (this.zzahZ.intValue() != 2);
    }
    finally
    {
      this.zzXG.unlock();
    }
    throw new IllegalStateException("Cannot call connect() when SignInMode is set to SIGN_IN_MODE_OPTIONAL. Call connect(SIGN_IN_MODE_OPTIONAL) instead.");
  }
  
  /* Error */
  public void connect(int paramInt)
  {
    // Byte code:
    //   0: iconst_1
    //   1: istore_3
    //   2: aload_0
    //   3: getfield 149	com/google/android/gms/common/api/internal/zzj:zzXG	Ljava/util/concurrent/locks/Lock;
    //   6: invokeinterface 220 1 0
    //   11: iload_3
    //   12: istore_2
    //   13: iload_1
    //   14: iconst_3
    //   15: if_icmpeq +17 -> 32
    //   18: iload_3
    //   19: istore_2
    //   20: iload_1
    //   21: iconst_1
    //   22: if_icmpeq +10 -> 32
    //   25: iload_1
    //   26: iconst_2
    //   27: if_icmpne +48 -> 75
    //   30: iload_3
    //   31: istore_2
    //   32: iload_2
    //   33: new 316	java/lang/StringBuilder
    //   36: dup
    //   37: invokespecial 317	java/lang/StringBuilder:<init>	()V
    //   40: ldc_w 493
    //   43: invokevirtual 323	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   46: iload_1
    //   47: invokevirtual 496	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   50: invokevirtual 333	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   53: invokestatic 498	com/google/android/gms/common/internal/zzx:zzb	(ZLjava/lang/Object;)V
    //   56: aload_0
    //   57: iload_1
    //   58: invokespecial 405	com/google/android/gms/common/api/internal/zzj:zzbB	(I)V
    //   61: aload_0
    //   62: invokespecial 226	com/google/android/gms/common/api/internal/zzj:zzpC	()V
    //   65: aload_0
    //   66: getfield 149	com/google/android/gms/common/api/internal/zzj:zzXG	Ljava/util/concurrent/locks/Lock;
    //   69: invokeinterface 229 1 0
    //   74: return
    //   75: iconst_0
    //   76: istore_2
    //   77: goto -45 -> 32
    //   80: astore 4
    //   82: aload_0
    //   83: getfield 149	com/google/android/gms/common/api/internal/zzj:zzXG	Ljava/util/concurrent/locks/Lock;
    //   86: invokeinterface 229 1 0
    //   91: aload 4
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	this	zzj
    //   0	94	1	paramInt	int
    //   12	65	2	bool1	boolean
    //   1	30	3	bool2	boolean
    //   80	12	4	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   32	65	80	finally
  }
  
  public void disconnect()
  {
    this.zzXG.lock();
    for (;;)
    {
      try
      {
        if ((this.zzahM != null) && (!this.zzahM.disconnect()))
        {
          bool = true;
          zzaa(bool);
          Iterator localIterator = this.zzahV.iterator();
          if (!localIterator.hasNext()) {
            break;
          }
          ((zzq)localIterator.next()).clear();
          continue;
        }
        boolean bool = false;
      }
      finally
      {
        this.zzXG.unlock();
      }
    }
    this.zzahV.clear();
    Object localObject2 = this.zzahN.iterator();
    while (((Iterator)localObject2).hasNext())
    {
      zze localzze = (zze)((Iterator)localObject2).next();
      localzze.zza(null);
      localzze.cancel();
    }
    this.zzahN.clear();
    localObject2 = this.zzahM;
    if (localObject2 == null)
    {
      this.zzXG.unlock();
      return;
    }
    zzpF();
    this.zzahL.zzqQ();
    this.zzXG.unlock();
  }
  
  public void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    paramPrintWriter.append(paramString).append("mContext=").println(this.mContext);
    paramPrintWriter.append(paramString).append("mResuming=").print(this.zzahO);
    paramPrintWriter.append(" mWorkQueue.size()=").print(this.zzahN.size());
    paramPrintWriter.append(" mUnconsumedRunners.size()=").println(this.zzahW.size());
    if (this.zzahM != null) {
      this.zzahM.dump(paramString, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
    }
  }
  
  @NonNull
  public ConnectionResult getConnectionResult(@NonNull Api<?> paramApi)
  {
    this.zzXG.lock();
    try
    {
      if ((!isConnected()) && (!zzpB())) {
        throw new IllegalStateException("Cannot invoke getConnectionResult unless GoogleApiClient is connected");
      }
    }
    finally
    {
      this.zzXG.unlock();
    }
    if (this.zzahT.containsKey(paramApi.zzoR()))
    {
      ConnectionResult localConnectionResult = this.zzahM.getConnectionResult(paramApi);
      if (localConnectionResult == null)
      {
        if (zzpB())
        {
          paramApi = ConnectionResult.zzafB;
          this.zzXG.unlock();
          return paramApi;
        }
        Log.i("GoogleApiClientImpl", zzpH());
        Log.wtf("GoogleApiClientImpl", paramApi.getName() + " requested in getConnectionResult" + " is not connected but is not present in the failed " + " connections map", new Exception());
        paramApi = new ConnectionResult(8, null);
        this.zzXG.unlock();
        return paramApi;
      }
      this.zzXG.unlock();
      return localConnectionResult;
    }
    throw new IllegalArgumentException(paramApi.getName() + " was never registered with GoogleApiClient");
  }
  
  public Context getContext()
  {
    return this.mContext;
  }
  
  public Looper getLooper()
  {
    return this.zzagr;
  }
  
  public int getSessionId()
  {
    return System.identityHashCode(this);
  }
  
  public boolean hasConnectedApi(@NonNull Api<?> paramApi)
  {
    paramApi = (Api.zzb)this.zzahT.get(paramApi.zzoR());
    return (paramApi != null) && (paramApi.isConnected());
  }
  
  public boolean isConnected()
  {
    return (this.zzahM != null) && (this.zzahM.isConnected());
  }
  
  public boolean isConnecting()
  {
    return (this.zzahM != null) && (this.zzahM.isConnecting());
  }
  
  public boolean isConnectionCallbacksRegistered(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    return this.zzahL.isConnectionCallbacksRegistered(paramConnectionCallbacks);
  }
  
  public boolean isConnectionFailedListenerRegistered(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    return this.zzahL.isConnectionFailedListenerRegistered(paramOnConnectionFailedListener);
  }
  
  public void reconnect()
  {
    disconnect();
    connect();
  }
  
  public void registerConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zzahL.registerConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void registerConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zzahL.registerConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  public void stopAutoManage(@NonNull final FragmentActivity paramFragmentActivity)
  {
    if (this.zzagp >= 0)
    {
      zzw localzzw = zzw.zza(paramFragmentActivity);
      if (localzzw == null)
      {
        new Handler(this.mContext.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            if ((paramFragmentActivity.isFinishing()) || (paramFragmentActivity.getSupportFragmentManager().isDestroyed())) {
              return;
            }
            zzw.zzb(paramFragmentActivity).zzbD(zzj.zze(zzj.this));
          }
        });
        return;
      }
      localzzw.zzbD(this.zzagp);
      return;
    }
    throw new IllegalStateException("Called stopAutoManage but automatic lifecycle management is not enabled.");
  }
  
  public void unregisterConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    this.zzahL.unregisterConnectionCallbacks(paramConnectionCallbacks);
  }
  
  public void unregisterConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.zzahL.unregisterConnectionFailedListener(paramOnConnectionFailedListener);
  }
  
  @NonNull
  public <C extends Api.zzb> C zza(@NonNull Api.zzc<C> paramzzc)
  {
    paramzzc = (Api.zzb)this.zzahT.get(paramzzc);
    com.google.android.gms.common.internal.zzx.zzb(paramzzc, "Appropriate Api was not requested.");
    return paramzzc;
  }
  
  public <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(@NonNull T paramT)
  {
    boolean bool;
    if (paramT.zzoR() != null) {
      bool = true;
    }
    for (;;)
    {
      com.google.android.gms.common.internal.zzx.zzb(bool, "This task can not be enqueued (it's probably a Batch or malformed)");
      com.google.android.gms.common.internal.zzx.zzb(this.zzahT.containsKey(paramT.zzoR()), "GoogleApiClient is not configured to use the API required for this call.");
      this.zzXG.lock();
      try
      {
        if (this.zzahM == null)
        {
          this.zzahN.add(paramT);
          return paramT;
          bool = false;
          continue;
        }
        paramT = this.zzahM.zza(paramT);
        return paramT;
      }
      finally
      {
        this.zzXG.unlock();
      }
    }
  }
  
  public void zza(zzx paramzzx)
  {
    this.zzXG.lock();
    try
    {
      if (this.zzaia == null) {
        this.zzaia = new HashSet();
      }
      this.zzaia.add(paramzzx);
      return;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  public boolean zza(@NonNull Api<?> paramApi)
  {
    return this.zzahT.containsKey(paramApi.zzoR());
  }
  
  public boolean zza(zzu paramzzu)
  {
    return (this.zzahM != null) && (this.zzahM.zza(paramzzu));
  }
  
  void zzaa(boolean paramBoolean)
  {
    Iterator localIterator = this.zzahW.iterator();
    while (localIterator.hasNext())
    {
      zze localzze = (zze)localIterator.next();
      if (localzze.zzpa() == null)
      {
        if (paramBoolean)
        {
          localzze.zzpg();
        }
        else
        {
          localzze.cancel();
          this.zzahW.remove(localzze);
        }
      }
      else
      {
        localzze.zzpe();
        IBinder localIBinder = zza(localzze.zzoR()).zzoT();
        zza(localzze, this.zzahX, localIBinder);
        this.zzahW.remove(localzze);
      }
    }
  }
  
  public <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    if (paramT.zzoR() != null) {}
    for (boolean bool = true;; bool = false)
    {
      com.google.android.gms.common.internal.zzx.zzb(bool, "This task can not be executed (it's probably a Batch or malformed)");
      this.zzXG.lock();
      try
      {
        if (this.zzahM != null) {
          break;
        }
        throw new IllegalStateException("GoogleApiClient is not connected yet.");
      }
      finally
      {
        this.zzXG.unlock();
      }
    }
    if (zzpB())
    {
      this.zzahN.add(paramT);
      while (!this.zzahN.isEmpty())
      {
        zze localzze = (zze)this.zzahN.remove();
        zzb(localzze);
        localzze.zzw(Status.zzagE);
      }
      this.zzXG.unlock();
      return paramT;
    }
    paramT = this.zzahM.zzb(paramT);
    this.zzXG.unlock();
    return paramT;
  }
  
  <A extends Api.zzb> void zzb(zze<A> paramzze)
  {
    this.zzahW.add(paramzze);
    paramzze.zza(this.zzaib);
  }
  
  public void zzb(zzx paramzzx)
  {
    this.zzXG.lock();
    for (;;)
    {
      try
      {
        if (this.zzaia == null)
        {
          Log.wtf("GoogleApiClientImpl", "Attempted to remove pending transform when no transforms are registered.", new Exception());
          return;
        }
        if (!this.zzaia.remove(paramzzx))
        {
          Log.wtf("GoogleApiClientImpl", "Failed to remove pending transform - this may lead to memory leaks!", new Exception());
          continue;
        }
        if (zzpG()) {
          continue;
        }
      }
      finally
      {
        this.zzXG.unlock();
      }
      this.zzahM.zzpj();
    }
  }
  
  public void zzc(int paramInt, boolean paramBoolean)
  {
    if ((paramInt == 1) && (!paramBoolean)) {
      zzpE();
    }
    Iterator localIterator = this.zzahW.iterator();
    while (localIterator.hasNext())
    {
      zze localzze = (zze)localIterator.next();
      if (paramBoolean) {
        localzze.zzpe();
      }
      localzze.zzx(new Status(8, "The connection to Google Play services was lost"));
    }
    this.zzahW.clear();
    this.zzahL.zzbT(paramInt);
    this.zzahL.zzqQ();
    if (paramInt == 2) {
      zzpC();
    }
  }
  
  public void zzd(ConnectionResult paramConnectionResult)
  {
    if (!this.zzags.zzd(this.mContext, paramConnectionResult.getErrorCode())) {
      zzpF();
    }
    if (!zzpB())
    {
      this.zzahL.zzk(paramConnectionResult);
      this.zzahL.zzqQ();
    }
  }
  
  public void zzi(Bundle paramBundle)
  {
    while (!this.zzahN.isEmpty()) {
      zzb((zza.zza)this.zzahN.remove());
    }
    this.zzahL.zzk(paramBundle);
  }
  
  public void zzoW()
  {
    if (this.zzahM != null) {
      this.zzahM.zzoW();
    }
  }
  
  boolean zzpB()
  {
    return this.zzahO;
  }
  
  void zzpE()
  {
    if (zzpB()) {
      return;
    }
    this.zzahO = true;
    if (this.zzahS == null) {
      this.zzahS = ((zzc)zzn.zza(this.mContext.getApplicationContext(), new zzc(this), this.zzags));
    }
    this.zzahR.sendMessageDelayed(this.zzahR.obtainMessage(1), this.zzahP);
    this.zzahR.sendMessageDelayed(this.zzahR.obtainMessage(2), this.zzahQ);
  }
  
  boolean zzpF()
  {
    if (!zzpB()) {
      return false;
    }
    this.zzahO = false;
    this.zzahR.removeMessages(2);
    this.zzahR.removeMessages(1);
    if (this.zzahS != null)
    {
      this.zzahS.unregister();
      this.zzahS = null;
    }
    return true;
  }
  
  boolean zzpG()
  {
    boolean bool1 = false;
    this.zzXG.lock();
    try
    {
      Set localSet = this.zzaia;
      if (localSet == null) {
        return false;
      }
      boolean bool2 = this.zzaia.isEmpty();
      if (!bool2) {
        bool1 = true;
      }
      return bool1;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  String zzpH()
  {
    StringWriter localStringWriter = new StringWriter();
    dump("", null, new PrintWriter(localStringWriter), null);
    return localStringWriter.toString();
  }
  
  public <L> zzq<L> zzr(@NonNull L paramL)
  {
    com.google.android.gms.common.internal.zzx.zzb(paramL, "Listener must not be null");
    this.zzXG.lock();
    try
    {
      paramL = new zzq(this.zzagr, paramL);
      this.zzahV.add(paramL);
      return paramL;
    }
    finally
    {
      this.zzXG.unlock();
    }
  }
  
  final class zza
    extends Handler
  {
    zza(Looper paramLooper)
    {
      super();
    }
    
    public void handleMessage(Message paramMessage)
    {
      switch (paramMessage.what)
      {
      default: 
        Log.w("GoogleApiClientImpl", "Unknown message id: " + paramMessage.what);
        return;
      case 1: 
        zzj.zzc(zzj.this);
        return;
      }
      zzj.zzb(zzj.this);
    }
  }
  
  private static class zzb
    implements IBinder.DeathRecipient, zzj.zzd
  {
    private final WeakReference<zzj.zze<?>> zzaii;
    private final WeakReference<zza> zzaij;
    private final WeakReference<IBinder> zzaik;
    
    private zzb(zzj.zze paramzze, zza paramzza, IBinder paramIBinder)
    {
      this.zzaij = new WeakReference(paramzza);
      this.zzaii = new WeakReference(paramzze);
      this.zzaik = new WeakReference(paramIBinder);
    }
    
    private void zzpI()
    {
      Object localObject = (zzj.zze)this.zzaii.get();
      zza localzza = (zza)this.zzaij.get();
      if ((localzza != null) && (localObject != null)) {
        localzza.remove(((zzj.zze)localObject).zzpa().intValue());
      }
      localObject = (IBinder)this.zzaik.get();
      if (this.zzaik != null) {
        ((IBinder)localObject).unlinkToDeath(this, 0);
      }
    }
    
    public void binderDied()
    {
      zzpI();
    }
    
    public void zzc(zzj.zze<?> paramzze)
    {
      zzpI();
    }
  }
  
  static class zzc
    extends zzn
  {
    private WeakReference<zzj> zzail;
    
    zzc(zzj paramzzj)
    {
      this.zzail = new WeakReference(paramzzj);
    }
    
    public void zzpJ()
    {
      zzj localzzj = (zzj)this.zzail.get();
      if (localzzj == null) {
        return;
      }
      zzj.zzb(localzzj);
    }
  }
  
  static abstract interface zzd
  {
    public abstract void zzc(zzj.zze<?> paramzze);
  }
  
  static abstract interface zze<A extends Api.zzb>
  {
    public abstract void cancel();
    
    public abstract boolean isReady();
    
    public abstract void zza(zzj.zzd paramzzd);
    
    public abstract void zzb(A paramA)
      throws DeadObjectException;
    
    public abstract Api.zzc<A> zzoR();
    
    public abstract Integer zzpa();
    
    public abstract void zzpe();
    
    public abstract void zzpg();
    
    public abstract void zzw(Status paramStatus);
    
    public abstract void zzx(Status paramStatus);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */