package com.google.android.gms.common.api.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Looper;
import android.support.annotation.BinderThread;
import android.support.annotation.NonNull;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api;
import com.google.android.gms.common.api.Api.zza;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.Api.zzc;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.zza;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.internal.ResolveAccountResponse;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zza;
import com.google.android.gms.common.internal.zzp;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.zzc;
import com.google.android.gms.internal.zzrn;
import com.google.android.gms.internal.zzro;
import com.google.android.gms.signin.internal.SignInResponse;
import com.google.android.gms.signin.internal.zzb;
import java.lang.ref.WeakReference;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Future;
import java.util.concurrent.locks.Lock;

public class zzh
  implements zzk
{
  private final Context mContext;
  private final Lock zzXG;
  private final zzc zzags;
  private final Api.zza<? extends zzrn, zzro> zzagt;
  private final Map<Api<?>, Integer> zzahA;
  private ArrayList<Future<?>> zzahB = new ArrayList();
  private final zzl zzahj;
  private ConnectionResult zzahm;
  private int zzahn;
  private int zzaho = 0;
  private int zzahp;
  private final Bundle zzahq = new Bundle();
  private final Set<Api.zzc> zzahr = new HashSet();
  private zzrn zzahs;
  private int zzaht;
  private boolean zzahu;
  private boolean zzahv;
  private zzp zzahw;
  private boolean zzahx;
  private boolean zzahy;
  private final zzf zzahz;
  
  public zzh(zzl paramzzl, zzf paramzzf, Map<Api<?>, Integer> paramMap, zzc paramzzc, Api.zza<? extends zzrn, zzro> paramzza, Lock paramLock, Context paramContext)
  {
    this.zzahj = paramzzl;
    this.zzahz = paramzzf;
    this.zzahA = paramMap;
    this.zzags = paramzzc;
    this.zzagt = paramzza;
    this.zzXG = paramLock;
    this.mContext = paramContext;
  }
  
  private void zzZ(boolean paramBoolean)
  {
    if (this.zzahs != null)
    {
      if ((this.zzahs.isConnected()) && (paramBoolean)) {
        this.zzahs.zzFG();
      }
      this.zzahs.disconnect();
      this.zzahw = null;
    }
  }
  
  private void zza(SignInResponse paramSignInResponse)
  {
    if (!zzbz(0)) {
      return;
    }
    ConnectionResult localConnectionResult = paramSignInResponse.zzqY();
    if (localConnectionResult.isSuccess())
    {
      paramSignInResponse = paramSignInResponse.zzFP();
      localConnectionResult = paramSignInResponse.zzqY();
      if (!localConnectionResult.isSuccess())
      {
        Log.wtf("GoogleApiClientConnecting", "Sign-in succeeded with resolve account failure: " + localConnectionResult, new Exception());
        zzg(localConnectionResult);
        return;
      }
      this.zzahv = true;
      this.zzahw = paramSignInResponse.zzqX();
      this.zzahx = paramSignInResponse.zzqZ();
      this.zzahy = paramSignInResponse.zzra();
      zzpv();
      return;
    }
    if (zzf(localConnectionResult))
    {
      zzpy();
      zzpv();
      return;
    }
    zzg(localConnectionResult);
  }
  
  private boolean zza(int paramInt1, int paramInt2, ConnectionResult paramConnectionResult)
  {
    if ((paramInt2 == 1) && (!zze(paramConnectionResult))) {}
    while ((this.zzahm != null) && (paramInt1 >= this.zzahn)) {
      return false;
    }
    return true;
  }
  
  private void zzb(ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt)
  {
    if (paramInt != 2)
    {
      int i = paramApi.zzoP().getPriority();
      if (zza(i, paramInt, paramConnectionResult))
      {
        this.zzahm = paramConnectionResult;
        this.zzahn = i;
      }
    }
    this.zzahj.zzaio.put(paramApi.zzoR(), paramConnectionResult);
  }
  
  private String zzbA(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return "UNKNOWN";
    case 0: 
      return "STEP_SERVICE_BINDINGS_AND_SIGN_IN";
    }
    return "STEP_GETTING_REMOTE_SERVICE";
  }
  
  private boolean zzbz(int paramInt)
  {
    if (this.zzaho != paramInt)
    {
      Log.i("GoogleApiClientConnecting", this.zzahj.zzagW.zzpH());
      Log.wtf("GoogleApiClientConnecting", "GoogleApiClient connecting is in step " + zzbA(this.zzaho) + " but received callback for step " + zzbA(paramInt), new Exception());
      zzg(new ConnectionResult(8, null));
      return false;
    }
    return true;
  }
  
  private boolean zze(ConnectionResult paramConnectionResult)
  {
    if (paramConnectionResult.hasResolution()) {}
    while (this.zzags.zzbu(paramConnectionResult.getErrorCode()) != null) {
      return true;
    }
    return false;
  }
  
  private boolean zzf(ConnectionResult paramConnectionResult)
  {
    return (this.zzaht == 2) || ((this.zzaht == 1) && (!paramConnectionResult.hasResolution()));
  }
  
  private void zzg(ConnectionResult paramConnectionResult)
  {
    zzpz();
    if (!paramConnectionResult.hasResolution()) {}
    for (boolean bool = true;; bool = false)
    {
      zzZ(bool);
      this.zzahj.zzh(paramConnectionResult);
      this.zzahj.zzais.zzd(paramConnectionResult);
      return;
    }
  }
  
  private Set<Scope> zzpA()
  {
    if (this.zzahz == null) {
      return Collections.emptySet();
    }
    HashSet localHashSet = new HashSet(this.zzahz.zzqs());
    Map localMap = this.zzahz.zzqu();
    Iterator localIterator = localMap.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api localApi = (Api)localIterator.next();
      if (!this.zzahj.zzaio.containsKey(localApi.zzoR())) {
        localHashSet.addAll(((zzf.zza)localMap.get(localApi)).zzXf);
      }
    }
    return localHashSet;
  }
  
  private boolean zzpu()
  {
    this.zzahp -= 1;
    if (this.zzahp > 0) {
      return false;
    }
    if (this.zzahp < 0)
    {
      Log.i("GoogleApiClientConnecting", this.zzahj.zzagW.zzpH());
      Log.wtf("GoogleApiClientConnecting", "GoogleApiClient received too many callbacks for the given step. Clients may be in an unexpected state; GoogleApiClient will now disconnect.", new Exception());
      zzg(new ConnectionResult(8, null));
      return false;
    }
    if (this.zzahm != null)
    {
      this.zzahj.zzair = this.zzahn;
      zzg(this.zzahm);
      return false;
    }
    return true;
  }
  
  private void zzpv()
  {
    if (this.zzahp != 0) {}
    while ((this.zzahu) && (!this.zzahv)) {
      return;
    }
    zzpw();
  }
  
  private void zzpw()
  {
    ArrayList localArrayList = new ArrayList();
    this.zzaho = 1;
    this.zzahp = this.zzahj.zzahT.size();
    Iterator localIterator = this.zzahj.zzahT.keySet().iterator();
    while (localIterator.hasNext())
    {
      Api.zzc localzzc = (Api.zzc)localIterator.next();
      if (this.zzahj.zzaio.containsKey(localzzc))
      {
        if (zzpu()) {
          zzpx();
        }
      }
      else {
        localArrayList.add(this.zzahj.zzahT.get(localzzc));
      }
    }
    if (!localArrayList.isEmpty()) {
      this.zzahB.add(zzm.zzpN().submit(new zzc(localArrayList)));
    }
  }
  
  private void zzpx()
  {
    this.zzahj.zzpL();
    zzm.zzpN().execute(new Runnable()
    {
      public void run()
      {
        zzh.zzb(zzh.this).zzal(zzh.zza(zzh.this));
      }
    });
    if (this.zzahs != null)
    {
      if (this.zzahx) {
        this.zzahs.zza(this.zzahw, this.zzahy);
      }
      zzZ(false);
    }
    Object localObject = this.zzahj.zzaio.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      Api.zzc localzzc = (Api.zzc)((Iterator)localObject).next();
      ((Api.zzb)this.zzahj.zzahT.get(localzzc)).disconnect();
    }
    if (this.zzahq.isEmpty()) {}
    for (localObject = null;; localObject = this.zzahq)
    {
      this.zzahj.zzais.zzi((Bundle)localObject);
      return;
    }
  }
  
  private void zzpy()
  {
    this.zzahu = false;
    this.zzahj.zzagW.zzahU = Collections.emptySet();
    Iterator localIterator = this.zzahr.iterator();
    while (localIterator.hasNext())
    {
      Api.zzc localzzc = (Api.zzc)localIterator.next();
      if (!this.zzahj.zzaio.containsKey(localzzc)) {
        this.zzahj.zzaio.put(localzzc, new ConnectionResult(17, null));
      }
    }
  }
  
  private void zzpz()
  {
    Iterator localIterator = this.zzahB.iterator();
    while (localIterator.hasNext()) {
      ((Future)localIterator.next()).cancel(true);
    }
    this.zzahB.clear();
  }
  
  public void begin()
  {
    this.zzahj.zzaio.clear();
    this.zzahu = false;
    this.zzahm = null;
    this.zzaho = 0;
    this.zzaht = 2;
    this.zzahv = false;
    this.zzahx = false;
    HashMap localHashMap = new HashMap();
    Object localObject = this.zzahA.keySet().iterator();
    int i = 0;
    if (((Iterator)localObject).hasNext())
    {
      Api localApi = (Api)((Iterator)localObject).next();
      Api.zzb localzzb = (Api.zzb)this.zzahj.zzahT.get(localApi.zzoR());
      int k = ((Integer)this.zzahA.get(localApi)).intValue();
      if (localApi.zzoP().getPriority() == 1) {}
      for (int j = 1;; j = 0)
      {
        if (localzzb.zzmE())
        {
          this.zzahu = true;
          if (k < this.zzaht) {
            this.zzaht = k;
          }
          if (k != 0) {
            this.zzahr.add(localApi.zzoR());
          }
        }
        localHashMap.put(localzzb, new zza(this, localApi, k));
        i = j | i;
        break;
      }
    }
    if (i != 0) {
      this.zzahu = false;
    }
    if (this.zzahu)
    {
      this.zzahz.zza(Integer.valueOf(this.zzahj.zzagW.getSessionId()));
      localObject = new zze(null);
      this.zzahs = ((zzrn)this.zzagt.zza(this.mContext, this.zzahj.zzagW.getLooper(), this.zzahz, this.zzahz.zzqy(), (GoogleApiClient.ConnectionCallbacks)localObject, (GoogleApiClient.OnConnectionFailedListener)localObject));
    }
    this.zzahp = this.zzahj.zzahT.size();
    this.zzahB.add(zzm.zzpN().submit(new zzb(localHashMap)));
  }
  
  public void connect() {}
  
  public boolean disconnect()
  {
    zzpz();
    zzZ(true);
    this.zzahj.zzh(null);
    return true;
  }
  
  public void onConnected(Bundle paramBundle)
  {
    if (!zzbz(1)) {}
    do
    {
      return;
      if (paramBundle != null) {
        this.zzahq.putAll(paramBundle);
      }
    } while (!zzpu());
    zzpx();
  }
  
  public void onConnectionSuspended(int paramInt)
  {
    zzg(new ConnectionResult(8, null));
  }
  
  public <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(T paramT)
  {
    this.zzahj.zzagW.zzahN.add(paramT);
    return paramT;
  }
  
  public void zza(ConnectionResult paramConnectionResult, Api<?> paramApi, int paramInt)
  {
    if (!zzbz(1)) {}
    do
    {
      return;
      zzb(paramConnectionResult, paramApi, paramInt);
    } while (!zzpu());
    zzpx();
  }
  
  public <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(T paramT)
  {
    throw new IllegalStateException("GoogleApiClient is not connected yet.");
  }
  
  private static class zza
    implements GoogleApiClient.zza
  {
    private final Api<?> zzagT;
    private final int zzagU;
    private final WeakReference<zzh> zzahD;
    
    public zza(zzh paramzzh, Api<?> paramApi, int paramInt)
    {
      this.zzahD = new WeakReference(paramzzh);
      this.zzagT = paramApi;
      this.zzagU = paramInt;
    }
    
    public void zza(@NonNull ConnectionResult paramConnectionResult)
    {
      boolean bool = false;
      zzh localzzh = (zzh)this.zzahD.get();
      if (localzzh == null) {
        return;
      }
      if (Looper.myLooper() == zzh.zzd(localzzh).zzagW.getLooper()) {
        bool = true;
      }
      zzx.zza(bool, "onReportServiceBinding must be called on the GoogleApiClient handler thread");
      zzh.zzc(localzzh).lock();
      try
      {
        bool = zzh.zza(localzzh, 0);
        if (!bool) {
          return;
        }
        if (!paramConnectionResult.isSuccess()) {
          zzh.zza(localzzh, paramConnectionResult, this.zzagT, this.zzagU);
        }
        if (zzh.zzk(localzzh)) {
          zzh.zzj(localzzh);
        }
        return;
      }
      finally
      {
        zzh.zzc(localzzh).unlock();
      }
    }
  }
  
  private class zzb
    extends zzh.zzf
  {
    private final Map<Api.zzb, GoogleApiClient.zza> zzahE;
    
    public zzb()
    {
      super(null);
      Map localMap;
      this.zzahE = localMap;
    }
    
    @WorkerThread
    public void zzpt()
    {
      int i = zzh.zzb(zzh.this).isGooglePlayServicesAvailable(zzh.zza(zzh.this));
      final Object localObject;
      if (i != 0)
      {
        localObject = new ConnectionResult(i, null);
        zzh.zzd(zzh.this).zza(new zzl.zza(zzh.this)
        {
          public void zzpt()
          {
            zzh.zza(zzh.this, localObject);
          }
        });
      }
      for (;;)
      {
        return;
        if (zzh.zze(zzh.this)) {
          zzh.zzf(zzh.this).connect();
        }
        localObject = this.zzahE.keySet().iterator();
        while (((Iterator)localObject).hasNext())
        {
          Api.zzb localzzb = (Api.zzb)((Iterator)localObject).next();
          localzzb.zza((GoogleApiClient.zza)this.zzahE.get(localzzb));
        }
      }
    }
  }
  
  private class zzc
    extends zzh.zzf
  {
    private final ArrayList<Api.zzb> zzahH;
    
    public zzc()
    {
      super(null);
      ArrayList localArrayList;
      this.zzahH = localArrayList;
    }
    
    @WorkerThread
    public void zzpt()
    {
      zzh.zzd(zzh.this).zzagW.zzahU = zzh.zzg(zzh.this);
      Iterator localIterator = this.zzahH.iterator();
      while (localIterator.hasNext()) {
        ((Api.zzb)localIterator.next()).zza(zzh.zzh(zzh.this), zzh.zzd(zzh.this).zzagW.zzahU);
      }
    }
  }
  
  private static class zzd
    extends zzb
  {
    private final WeakReference<zzh> zzahD;
    
    zzd(zzh paramzzh)
    {
      this.zzahD = new WeakReference(paramzzh);
    }
    
    @BinderThread
    public void zzb(final SignInResponse paramSignInResponse)
    {
      final zzh localzzh = (zzh)this.zzahD.get();
      if (localzzh == null) {
        return;
      }
      zzh.zzd(localzzh).zza(new zzl.zza(localzzh)
      {
        public void zzpt()
        {
          zzh.zza(localzzh, paramSignInResponse);
        }
      });
    }
  }
  
  private class zze
    implements GoogleApiClient.ConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener
  {
    private zze() {}
    
    public void onConnected(Bundle paramBundle)
    {
      zzh.zzf(zzh.this).zza(new zzh.zzd(zzh.this));
    }
    
    /* Error */
    public void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult)
    {
      // Byte code:
      //   0: aload_0
      //   1: getfield 17	com/google/android/gms/common/api/internal/zzh$zze:zzahC	Lcom/google/android/gms/common/api/internal/zzh;
      //   4: invokestatic 46	com/google/android/gms/common/api/internal/zzh:zzc	(Lcom/google/android/gms/common/api/internal/zzh;)Ljava/util/concurrent/locks/Lock;
      //   7: invokeinterface 51 1 0
      //   12: aload_0
      //   13: getfield 17	com/google/android/gms/common/api/internal/zzh$zze:zzahC	Lcom/google/android/gms/common/api/internal/zzh;
      //   16: aload_1
      //   17: invokestatic 55	com/google/android/gms/common/api/internal/zzh:zzb	(Lcom/google/android/gms/common/api/internal/zzh;Lcom/google/android/gms/common/ConnectionResult;)Z
      //   20: ifeq +30 -> 50
      //   23: aload_0
      //   24: getfield 17	com/google/android/gms/common/api/internal/zzh$zze:zzahC	Lcom/google/android/gms/common/api/internal/zzh;
      //   27: invokestatic 58	com/google/android/gms/common/api/internal/zzh:zzi	(Lcom/google/android/gms/common/api/internal/zzh;)V
      //   30: aload_0
      //   31: getfield 17	com/google/android/gms/common/api/internal/zzh$zze:zzahC	Lcom/google/android/gms/common/api/internal/zzh;
      //   34: invokestatic 61	com/google/android/gms/common/api/internal/zzh:zzj	(Lcom/google/android/gms/common/api/internal/zzh;)V
      //   37: aload_0
      //   38: getfield 17	com/google/android/gms/common/api/internal/zzh$zze:zzahC	Lcom/google/android/gms/common/api/internal/zzh;
      //   41: invokestatic 46	com/google/android/gms/common/api/internal/zzh:zzc	(Lcom/google/android/gms/common/api/internal/zzh;)Ljava/util/concurrent/locks/Lock;
      //   44: invokeinterface 64 1 0
      //   49: return
      //   50: aload_0
      //   51: getfield 17	com/google/android/gms/common/api/internal/zzh$zze:zzahC	Lcom/google/android/gms/common/api/internal/zzh;
      //   54: aload_1
      //   55: invokestatic 67	com/google/android/gms/common/api/internal/zzh:zza	(Lcom/google/android/gms/common/api/internal/zzh;Lcom/google/android/gms/common/ConnectionResult;)V
      //   58: goto -21 -> 37
      //   61: astore_1
      //   62: aload_0
      //   63: getfield 17	com/google/android/gms/common/api/internal/zzh$zze:zzahC	Lcom/google/android/gms/common/api/internal/zzh;
      //   66: invokestatic 46	com/google/android/gms/common/api/internal/zzh:zzc	(Lcom/google/android/gms/common/api/internal/zzh;)Ljava/util/concurrent/locks/Lock;
      //   69: invokeinterface 64 1 0
      //   74: aload_1
      //   75: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	76	0	this	zze
      //   0	76	1	paramConnectionResult	ConnectionResult
      // Exception table:
      //   from	to	target	type
      //   12	37	61	finally
      //   50	58	61	finally
    }
    
    public void onConnectionSuspended(int paramInt) {}
  }
  
  private abstract class zzf
    implements Runnable
  {
    private zzf() {}
    
    @WorkerThread
    public void run()
    {
      zzh.zzc(zzh.this).lock();
      try
      {
        boolean bool = Thread.interrupted();
        if (bool) {
          return;
        }
        zzpt();
        return;
      }
      catch (RuntimeException localRuntimeException)
      {
        zzh.zzd(zzh.this).zza(localRuntimeException);
        return;
      }
      finally
      {
        zzh.zzc(zzh.this).unlock();
      }
    }
    
    @WorkerThread
    protected abstract void zzpt();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */