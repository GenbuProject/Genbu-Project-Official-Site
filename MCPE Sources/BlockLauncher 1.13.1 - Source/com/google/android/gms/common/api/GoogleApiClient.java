package com.google.android.gms.common.api;

import android.accounts.Account;
import android.content.Context;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.util.ArrayMap;
import android.view.View;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.internal.zza.zza;
import com.google.android.gms.common.api.internal.zzj;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.common.api.internal.zzu;
import com.google.android.gms.common.api.internal.zzw;
import com.google.android.gms.common.internal.zzad;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzf.zza;
import com.google.android.gms.internal.zzrl;
import com.google.android.gms.internal.zzrn;
import com.google.android.gms.internal.zzro;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.WeakHashMap;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

public abstract class GoogleApiClient
{
  public static final int SIGN_IN_MODE_OPTIONAL = 2;
  public static final int SIGN_IN_MODE_REQUIRED = 1;
  private static final Set<GoogleApiClient> zzagg = Collections.newSetFromMap(new WeakHashMap());
  
  public static void dumpAll(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    synchronized (zzagg)
    {
      String str = paramString + "  ";
      Iterator localIterator = zzagg.iterator();
      int i = 0;
      while (localIterator.hasNext())
      {
        GoogleApiClient localGoogleApiClient = (GoogleApiClient)localIterator.next();
        paramPrintWriter.append(paramString).append("GoogleApiClient#").println(i);
        localGoogleApiClient.dump(str, paramFileDescriptor, paramPrintWriter, paramArrayOfString);
        i += 1;
      }
      return;
    }
  }
  
  public static Set<GoogleApiClient> zzoV()
  {
    return zzagg;
  }
  
  public abstract ConnectionResult blockingConnect();
  
  public abstract ConnectionResult blockingConnect(long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  public abstract PendingResult<Status> clearDefaultAccountAndReconnect();
  
  public abstract void connect();
  
  public void connect(int paramInt)
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract void disconnect();
  
  public abstract void dump(String paramString, FileDescriptor paramFileDescriptor, PrintWriter paramPrintWriter, String[] paramArrayOfString);
  
  @NonNull
  public abstract ConnectionResult getConnectionResult(@NonNull Api<?> paramApi);
  
  public Context getContext()
  {
    throw new UnsupportedOperationException();
  }
  
  public Looper getLooper()
  {
    throw new UnsupportedOperationException();
  }
  
  public abstract boolean hasConnectedApi(@NonNull Api<?> paramApi);
  
  public abstract boolean isConnected();
  
  public abstract boolean isConnecting();
  
  public abstract boolean isConnectionCallbacksRegistered(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract boolean isConnectionFailedListenerRegistered(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void reconnect();
  
  public abstract void registerConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void registerConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  public abstract void stopAutoManage(@NonNull FragmentActivity paramFragmentActivity);
  
  public abstract void unregisterConnectionCallbacks(@NonNull ConnectionCallbacks paramConnectionCallbacks);
  
  public abstract void unregisterConnectionFailedListener(@NonNull OnConnectionFailedListener paramOnConnectionFailedListener);
  
  @NonNull
  public <C extends Api.zzb> C zza(@NonNull Api.zzc<C> paramzzc)
  {
    throw new UnsupportedOperationException();
  }
  
  public <A extends Api.zzb, R extends Result, T extends zza.zza<R, A>> T zza(@NonNull T paramT)
  {
    throw new UnsupportedOperationException();
  }
  
  public void zza(com.google.android.gms.common.api.internal.zzx paramzzx)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean zza(@NonNull Api<?> paramApi)
  {
    throw new UnsupportedOperationException();
  }
  
  public boolean zza(zzu paramzzu)
  {
    throw new UnsupportedOperationException();
  }
  
  public <A extends Api.zzb, T extends zza.zza<? extends Result, A>> T zzb(@NonNull T paramT)
  {
    throw new UnsupportedOperationException();
  }
  
  public void zzb(com.google.android.gms.common.api.internal.zzx paramzzx)
  {
    throw new UnsupportedOperationException();
  }
  
  public void zzoW()
  {
    throw new UnsupportedOperationException();
  }
  
  public <L> zzq<L> zzr(@NonNull L paramL)
  {
    throw new UnsupportedOperationException();
  }
  
  public static final class Builder
  {
    private final Context mContext;
    private Account zzTI;
    private String zzUW;
    private final Set<Scope> zzagh = new HashSet();
    private final Set<Scope> zzagi = new HashSet();
    private int zzagj;
    private View zzagk;
    private String zzagl;
    private final Map<Api<?>, zzf.zza> zzagm = new ArrayMap();
    private final Map<Api<?>, Api.ApiOptions> zzagn = new ArrayMap();
    private FragmentActivity zzago;
    private int zzagp = -1;
    private GoogleApiClient.OnConnectionFailedListener zzagq;
    private Looper zzagr;
    private com.google.android.gms.common.zzc zzags = com.google.android.gms.common.zzc.zzoK();
    private Api.zza<? extends zzrn, zzro> zzagt = zzrl.zzUJ;
    private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzagu = new ArrayList();
    private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzagv = new ArrayList();
    
    public Builder(@NonNull Context paramContext)
    {
      this.mContext = paramContext;
      this.zzagr = paramContext.getMainLooper();
      this.zzUW = paramContext.getPackageName();
      this.zzagl = paramContext.getClass().getName();
    }
    
    public Builder(@NonNull Context paramContext, @NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, @NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      this(paramContext);
      com.google.android.gms.common.internal.zzx.zzb(paramConnectionCallbacks, "Must provide a connected listener");
      this.zzagu.add(paramConnectionCallbacks);
      com.google.android.gms.common.internal.zzx.zzb(paramOnConnectionFailedListener, "Must provide a connection failed listener");
      this.zzagv.add(paramOnConnectionFailedListener);
    }
    
    private static <C extends Api.zzb, O> C zza(Api.zza<C, O> paramzza, Object paramObject, Context paramContext, Looper paramLooper, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return paramzza.zza(paramContext, paramLooper, paramzzf, paramObject, paramConnectionCallbacks, paramOnConnectionFailedListener);
    }
    
    private static <C extends Api.zzd, O> zzad zza(Api.zze<C, O> paramzze, Object paramObject, Context paramContext, Looper paramLooper, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return new zzad(paramContext, paramLooper, paramzze.zzoU(), paramConnectionCallbacks, paramOnConnectionFailedListener, paramzzf, paramzze.zzq(paramObject));
    }
    
    private <O extends Api.ApiOptions> void zza(Api<O> paramApi, O paramO, int paramInt, Scope... paramVarArgs)
    {
      boolean bool = true;
      int i = 0;
      if (paramInt == 1) {}
      for (;;)
      {
        paramO = new HashSet(paramApi.zzoP().zzo(paramO));
        int j = paramVarArgs.length;
        paramInt = i;
        while (paramInt < j)
        {
          paramO.add(paramVarArgs[paramInt]);
          paramInt += 1;
        }
        if (paramInt != 2) {
          break;
        }
        bool = false;
      }
      throw new IllegalArgumentException("Invalid resolution mode: '" + paramInt + "', use a constant from GoogleApiClient.ResolutionMode");
      this.zzagm.put(paramApi, new zzf.zza(paramO, bool));
    }
    
    private void zza(zzw paramzzw, GoogleApiClient paramGoogleApiClient)
    {
      paramzzw.zza(this.zzagp, paramGoogleApiClient, this.zzagq);
    }
    
    private void zze(final GoogleApiClient paramGoogleApiClient)
    {
      zzw localzzw = zzw.zza(this.zzago);
      if (localzzw == null)
      {
        new Handler(this.mContext.getMainLooper()).post(new Runnable()
        {
          public void run()
          {
            if ((GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this).isFinishing()) || (GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this).getSupportFragmentManager().isDestroyed())) {
              return;
            }
            GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this, zzw.zzb(GoogleApiClient.Builder.zza(GoogleApiClient.Builder.this)), paramGoogleApiClient);
          }
        });
        return;
      }
      zza(localzzw, paramGoogleApiClient);
    }
    
    private GoogleApiClient zzoZ()
    {
      zzf localzzf = zzoY();
      Object localObject2 = null;
      Map localMap = localzzf.zzqu();
      ArrayMap localArrayMap1 = new ArrayMap();
      ArrayMap localArrayMap2 = new ArrayMap();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = this.zzagn.keySet().iterator();
      Object localObject1 = null;
      Api localApi;
      Object localObject3;
      int i;
      label130:
      com.google.android.gms.common.api.internal.zzc localzzc;
      Object localObject4;
      if (localIterator.hasNext())
      {
        localApi = (Api)localIterator.next();
        localObject3 = this.zzagn.get(localApi);
        i = 0;
        if (localMap.get(localApi) != null)
        {
          if (((zzf.zza)localMap.get(localApi)).zzalf) {
            i = 1;
          }
        }
        else
        {
          localArrayMap1.put(localApi, Integer.valueOf(i));
          localzzc = new com.google.android.gms.common.api.internal.zzc(localApi, i);
          localArrayList.add(localzzc);
          if (!localApi.zzoS()) {
            break label295;
          }
          localObject4 = localApi.zzoQ();
          if (((Api.zze)localObject4).getPriority() != 1) {
            break label530;
          }
          localObject1 = localApi;
        }
      }
      label216:
      label295:
      label344:
      label522:
      label527:
      label530:
      for (;;)
      {
        localObject3 = zza((Api.zze)localObject4, localObject3, this.mContext, this.zzagr, localzzf, localzzc, localzzc);
        localArrayMap2.put(localApi.zzoR(), localObject3);
        if (((Api.zzb)localObject3).zznb())
        {
          localObject3 = localApi;
          if (localObject2 == null) {
            break label344;
          }
          throw new IllegalStateException(localApi.getName() + " cannot be used with " + ((Api)localObject2).getName());
          i = 2;
          break label130;
          localObject4 = localApi.zzoP();
          if (((Api.zza)localObject4).getPriority() != 1) {
            break label527;
          }
          localObject1 = localApi;
        }
        for (;;)
        {
          localObject3 = zza((Api.zza)localObject4, localObject3, this.mContext, this.zzagr, localzzf, localzzc, localzzc);
          break label216;
          localObject3 = localObject2;
          localObject2 = localObject3;
          break;
          if (localObject2 != null)
          {
            if (localObject1 != null) {
              throw new IllegalStateException(((Api)localObject2).getName() + " cannot be used with " + ((Api)localObject1).getName());
            }
            if (this.zzTI != null) {
              break label522;
            }
          }
          for (boolean bool = true;; bool = false)
          {
            com.google.android.gms.common.internal.zzx.zza(bool, "Must not set an account in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead", new Object[] { ((Api)localObject2).getName() });
            com.google.android.gms.common.internal.zzx.zza(this.zzagh.equals(this.zzagi), "Must not set scopes in GoogleApiClient.Builder when using %s. Set account in GoogleSignInOptions.Builder instead.", new Object[] { ((Api)localObject2).getName() });
            i = zzj.zza(localArrayMap2.values(), true);
            return new zzj(this.mContext, new ReentrantLock(), this.zzagr, localzzf, this.zzags, this.zzagt, localArrayMap1, this.zzagu, this.zzagv, localArrayMap2, this.zzagp, i, localArrayList);
          }
        }
      }
    }
    
    public Builder addApi(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramApi, "Api must not be null");
      this.zzagn.put(paramApi, null);
      paramApi = paramApi.zzoP().zzo(null);
      this.zzagi.addAll(paramApi);
      this.zzagh.addAll(paramApi);
      return this;
    }
    
    public <O extends Api.ApiOptions.HasOptions> Builder addApi(@NonNull Api<O> paramApi, @NonNull O paramO)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramApi, "Api must not be null");
      com.google.android.gms.common.internal.zzx.zzb(paramO, "Null options are not permitted for this Api");
      this.zzagn.put(paramApi, paramO);
      paramApi = paramApi.zzoP().zzo(paramO);
      this.zzagi.addAll(paramApi);
      this.zzagh.addAll(paramApi);
      return this;
    }
    
    public <O extends Api.ApiOptions.HasOptions> Builder addApiIfAvailable(@NonNull Api<O> paramApi, @NonNull O paramO, Scope... paramVarArgs)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramApi, "Api must not be null");
      com.google.android.gms.common.internal.zzx.zzb(paramO, "Null options are not permitted for this Api");
      this.zzagn.put(paramApi, paramO);
      zza(paramApi, paramO, 1, paramVarArgs);
      return this;
    }
    
    public Builder addApiIfAvailable(@NonNull Api<? extends Api.ApiOptions.NotRequiredOptions> paramApi, Scope... paramVarArgs)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramApi, "Api must not be null");
      this.zzagn.put(paramApi, null);
      zza(paramApi, null, 1, paramVarArgs);
      return this;
    }
    
    public Builder addConnectionCallbacks(@NonNull GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramConnectionCallbacks, "Listener must not be null");
      this.zzagu.add(paramConnectionCallbacks);
      return this;
    }
    
    public Builder addOnConnectionFailedListener(@NonNull GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramOnConnectionFailedListener, "Listener must not be null");
      this.zzagv.add(paramOnConnectionFailedListener);
      return this;
    }
    
    public Builder addScope(@NonNull Scope paramScope)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramScope, "Scope must not be null");
      this.zzagh.add(paramScope);
      return this;
    }
    
    public GoogleApiClient build()
    {
      boolean bool;
      if (!this.zzagn.isEmpty()) {
        bool = true;
      }
      for (;;)
      {
        com.google.android.gms.common.internal.zzx.zzb(bool, "must call addApi() to add at least one API");
        GoogleApiClient localGoogleApiClient = zzoZ();
        synchronized (GoogleApiClient.zzoX())
        {
          GoogleApiClient.zzoX().add(localGoogleApiClient);
          if (this.zzagp >= 0) {
            zze(localGoogleApiClient);
          }
          return localGoogleApiClient;
          bool = false;
        }
      }
    }
    
    public Builder enableAutoManage(@NonNull FragmentActivity paramFragmentActivity, int paramInt, @Nullable GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      if (paramInt >= 0) {}
      for (boolean bool = true;; bool = false)
      {
        com.google.android.gms.common.internal.zzx.zzb(bool, "clientId must be non-negative");
        this.zzagp = paramInt;
        this.zzago = ((FragmentActivity)com.google.android.gms.common.internal.zzx.zzb(paramFragmentActivity, "Null activity is not permitted."));
        this.zzagq = paramOnConnectionFailedListener;
        return this;
      }
    }
    
    public Builder enableAutoManage(@NonNull FragmentActivity paramFragmentActivity, @Nullable GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
    {
      return enableAutoManage(paramFragmentActivity, 0, paramOnConnectionFailedListener);
    }
    
    public Builder setAccountName(String paramString)
    {
      if (paramString == null) {}
      for (paramString = null;; paramString = new Account(paramString, "com.google"))
      {
        this.zzTI = paramString;
        return this;
      }
    }
    
    public Builder setGravityForPopups(int paramInt)
    {
      this.zzagj = paramInt;
      return this;
    }
    
    public Builder setHandler(@NonNull Handler paramHandler)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramHandler, "Handler must not be null");
      this.zzagr = paramHandler.getLooper();
      return this;
    }
    
    public Builder setViewForPopups(@NonNull View paramView)
    {
      com.google.android.gms.common.internal.zzx.zzb(paramView, "View must not be null");
      this.zzagk = paramView;
      return this;
    }
    
    public Builder useDefaultAccount()
    {
      return setAccountName("<<default account>>");
    }
    
    public zzf zzoY()
    {
      zzro localzzro = zzro.zzbgV;
      if (this.zzagn.containsKey(zzrl.API)) {
        localzzro = (zzro)this.zzagn.get(zzrl.API);
      }
      return new zzf(this.zzTI, this.zzagh, this.zzagm, this.zzagj, this.zzagk, this.zzUW, this.zzagl, localzzro);
    }
  }
  
  public static abstract interface ConnectionCallbacks
  {
    public static final int CAUSE_NETWORK_LOST = 2;
    public static final int CAUSE_SERVICE_DISCONNECTED = 1;
    
    public abstract void onConnected(@Nullable Bundle paramBundle);
    
    public abstract void onConnectionSuspended(int paramInt);
  }
  
  public static abstract interface OnConnectionFailedListener
  {
    public abstract void onConnectionFailed(@NonNull ConnectionResult paramConnectionResult);
  }
  
  public static abstract interface zza
  {
    public abstract void zza(@NonNull ConnectionResult paramConnectionResult);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\api\GoogleApiClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */