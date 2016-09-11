package com.google.android.gms.common.internal;

import android.accounts.Account;
import android.app.PendingIntent;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.Handler;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Looper;
import android.os.Message;
import android.os.RemoteException;
import android.support.annotation.BinderThread;
import android.support.annotation.CallSuper;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.WorkerThread;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.Api.zzb;
import com.google.android.gms.common.api.CommonStatusCodes;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.GoogleApiClient.zza;
import com.google.android.gms.common.api.Scope;
import com.google.android.gms.common.zzc;
import java.io.FileDescriptor;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.Locale;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

public abstract class zzj<T extends IInterface>
  implements Api.zzb, zzk.zza
{
  public static final String[] zzalJ = { "service_esmobile", "service_googleme" };
  private final Context mContext;
  final Handler mHandler;
  private final Account zzTI;
  private final Set<Scope> zzXf;
  private final Looper zzagr;
  private final zzc zzags;
  private final zzf zzahz;
  private GoogleApiClient.zza zzalA = new zzf();
  private T zzalB;
  private final ArrayList<zzj<T>.zzc<?>> zzalC = new ArrayList();
  private zzj<T>.zze zzalD;
  private int zzalE = 1;
  private final GoogleApiClient.ConnectionCallbacks zzalF;
  private final GoogleApiClient.OnConnectionFailedListener zzalG;
  private final int zzalH;
  protected AtomicInteger zzalI = new AtomicInteger(0);
  private int zzals;
  private long zzalt;
  private long zzalu;
  private int zzalv;
  private long zzalw;
  private final zzl zzalx;
  private final Object zzaly = new Object();
  private zzs zzalz;
  private final Object zzpV = new Object();
  
  protected zzj(Context paramContext, Looper paramLooper, int paramInt, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this(paramContext, paramLooper, zzl.zzau(paramContext), zzc.zzoK(), paramInt, paramzzf, (GoogleApiClient.ConnectionCallbacks)zzx.zzz(paramConnectionCallbacks), (GoogleApiClient.OnConnectionFailedListener)zzx.zzz(paramOnConnectionFailedListener));
  }
  
  protected zzj(Context paramContext, Looper paramLooper, zzl paramzzl, zzc paramzzc, int paramInt, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    this.mContext = ((Context)zzx.zzb(paramContext, "Context must not be null"));
    this.zzagr = ((Looper)zzx.zzb(paramLooper, "Looper must not be null"));
    this.zzalx = ((zzl)zzx.zzb(paramzzl, "Supervisor must not be null"));
    this.zzags = ((zzc)zzx.zzb(paramzzc, "API availability must not be null"));
    this.mHandler = new zzb(paramLooper);
    this.zzalH = paramInt;
    this.zzahz = ((zzf)zzx.zzz(paramzzf));
    this.zzTI = paramzzf.getAccount();
    this.zzXf = zza(paramzzf.zzqt());
    this.zzalF = paramConnectionCallbacks;
    this.zzalG = paramOnConnectionFailedListener;
  }
  
  private Set<Scope> zza(Set<Scope> paramSet)
  {
    Set localSet = zzb(paramSet);
    if (localSet == null) {
      return localSet;
    }
    Iterator localIterator = localSet.iterator();
    while (localIterator.hasNext()) {
      if (!paramSet.contains((Scope)localIterator.next())) {
        throw new IllegalStateException("Expanding scopes is not permitted, use implied scopes instead");
      }
    }
    return localSet;
  }
  
  private boolean zza(int paramInt1, int paramInt2, T paramT)
  {
    synchronized (this.zzpV)
    {
      if (this.zzalE != paramInt1) {
        return false;
      }
      zzb(paramInt2, paramT);
      return true;
    }
  }
  
  private void zzb(int paramInt, T paramT)
  {
    boolean bool = true;
    int i;
    int j;
    if (paramInt == 3)
    {
      i = 1;
      if (paramT == null) {
        break label120;
      }
      j = 1;
      label17:
      if (i != j) {
        break label126;
      }
    }
    for (;;)
    {
      zzx.zzac(bool);
      for (;;)
      {
        synchronized (this.zzpV)
        {
          this.zzalE = paramInt;
          this.zzalB = paramT;
          zzc(paramInt, paramT);
          switch (paramInt)
          {
          case 2: 
            return;
            zzqE();
          }
        }
        zza(paramT);
        continue;
        zzqF();
      }
      i = 0;
      break;
      label120:
      j = 0;
      break label17;
      label126:
      bool = false;
    }
  }
  
  private void zzqE()
  {
    if (this.zzalD != null)
    {
      Log.e("GmsClient", "Calling connect() while still connected, missing disconnect() for " + zzgu());
      this.zzalx.zzb(zzgu(), this.zzalD, zzqD());
      this.zzalI.incrementAndGet();
    }
    this.zzalD = new zze(this.zzalI.get());
    if (!this.zzalx.zza(zzgu(), this.zzalD, zzqD()))
    {
      Log.e("GmsClient", "unable to connect to service: " + zzgu());
      zzm(8, this.zzalI.get());
    }
  }
  
  private void zzqF()
  {
    if (this.zzalD != null)
    {
      this.zzalx.zzb(zzgu(), this.zzalD, zzqD());
      this.zzalD = null;
    }
  }
  
  public void disconnect()
  {
    this.zzalI.incrementAndGet();
    synchronized (this.zzalC)
    {
      int j = this.zzalC.size();
      int i = 0;
      while (i < j)
      {
        ((zzc)this.zzalC.get(i)).zzqO();
        i += 1;
      }
      this.zzalC.clear();
    }
    synchronized (this.zzaly)
    {
      this.zzalz = null;
      zzb(1, null);
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
  }
  
  public void dump(String paramString, FileDescriptor arg2, PrintWriter paramPrintWriter, String[] paramArrayOfString)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        int i = this.zzalE;
        paramArrayOfString = this.zzalB;
        paramPrintWriter.append(paramString).append("mConnectState=");
        switch (i)
        {
        default: 
          paramPrintWriter.print("UNKNOWN");
          paramPrintWriter.append(" mService=");
          if (paramArrayOfString != null) {
            break label407;
          }
          paramPrintWriter.println("null");
          ??? = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS", Locale.US);
          if (this.zzalu > 0L) {
            paramPrintWriter.append(paramString).append("lastConnectedTime=").println(this.zzalu + " " + ???.format(new Date(this.zzalu)));
          }
          if (this.zzalt > 0L) {
            paramPrintWriter.append(paramString).append("lastSuspendedCause=");
          }
          switch (this.zzals)
          {
          default: 
            paramPrintWriter.append(String.valueOf(this.zzals));
            paramPrintWriter.append(" lastSuspendedTime=").println(this.zzalt + " " + ???.format(new Date(this.zzalt)));
            if (this.zzalw > 0L)
            {
              paramPrintWriter.append(paramString).append("lastFailedStatus=").append(CommonStatusCodes.getStatusCodeString(this.zzalv));
              paramPrintWriter.append(" lastFailedTime=").println(this.zzalw + " " + ???.format(new Date(this.zzalw)));
            }
            return;
          }
          break;
        }
      }
      paramPrintWriter.print("CONNECTING");
      continue;
      paramPrintWriter.print("CONNECTED");
      continue;
      paramPrintWriter.print("DISCONNECTING");
      continue;
      paramPrintWriter.print("DISCONNECTED");
      continue;
      label407:
      paramPrintWriter.append(zzgv()).append("@").println(Integer.toHexString(System.identityHashCode(paramArrayOfString.asBinder())));
      continue;
      paramPrintWriter.append("CAUSE_SERVICE_DISCONNECTED");
      continue;
      paramPrintWriter.append("CAUSE_NETWORK_LOST");
    }
  }
  
  public final Context getContext()
  {
    return this.mContext;
  }
  
  public final Looper getLooper()
  {
    return this.zzagr;
  }
  
  public boolean isConnected()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzalE == 3)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public boolean isConnecting()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzalE == 2)
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  @CallSuper
  protected void onConnectionFailed(ConnectionResult paramConnectionResult)
  {
    this.zzalv = paramConnectionResult.getErrorCode();
    this.zzalw = System.currentTimeMillis();
  }
  
  @CallSuper
  protected void onConnectionSuspended(int paramInt)
  {
    this.zzals = paramInt;
    this.zzalt = System.currentTimeMillis();
  }
  
  @Nullable
  protected abstract T zzW(IBinder paramIBinder);
  
  @BinderThread
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramInt2, -1, new zzg(paramInt1, paramIBinder, paramBundle)));
  }
  
  @CallSuper
  protected void zza(@NonNull T paramT)
  {
    this.zzalu = System.currentTimeMillis();
  }
  
  public void zza(@NonNull GoogleApiClient.zza paramzza)
  {
    this.zzalA = ((GoogleApiClient.zza)zzx.zzb(paramzza, "Connection progress callbacks cannot be null."));
    zzb(2, null);
  }
  
  @WorkerThread
  public void zza(zzp arg1, Set<Scope> paramSet)
  {
    try
    {
      Object localObject = zzml();
      localObject = new GetServiceRequest(this.zzalH).zzcG(this.mContext.getPackageName()).zzj((Bundle)localObject);
      if (paramSet != null) {
        ((GetServiceRequest)localObject).zzd(paramSet);
      }
      if (zzmE()) {
        ((GetServiceRequest)localObject).zzc(zzqq()).zzb(???);
      }
      return;
    }
    catch (DeadObjectException ???)
    {
      synchronized (this.zzaly)
      {
        while (this.zzalz != null)
        {
          this.zzalz.zza(new zzd(this, this.zzalI.get()), (GetServiceRequest)localObject);
          return;
          if (zzqK())
          {
            ((GetServiceRequest)localObject).zzc(this.zzTI);
            continue;
            ??? = ???;
            Log.w("GmsClient", "service died");
            zzbS(1);
            return;
          }
        }
        Log.w("GmsClient", "mServiceBroker is null, client disconnected");
      }
    }
    catch (RemoteException ???)
    {
      Log.w("GmsClient", "Remote exception occurred", ???);
    }
  }
  
  @NonNull
  protected Set<Scope> zzb(@NonNull Set<Scope> paramSet)
  {
    return paramSet;
  }
  
  public void zzbS(int paramInt)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(4, this.zzalI.get(), paramInt));
  }
  
  void zzc(int paramInt, T paramT) {}
  
  @NonNull
  protected abstract String zzgu();
  
  @NonNull
  protected abstract String zzgv();
  
  protected void zzm(int paramInt1, int paramInt2)
  {
    this.mHandler.sendMessage(this.mHandler.obtainMessage(5, paramInt2, -1, new zzh(paramInt1)));
  }
  
  public boolean zzmE()
  {
    return false;
  }
  
  protected Bundle zzml()
  {
    return new Bundle();
  }
  
  public boolean zznb()
  {
    return false;
  }
  
  public Intent zznc()
  {
    throw new UnsupportedOperationException("Not a sign in API");
  }
  
  @Nullable
  public IBinder zzoT()
  {
    synchronized (this.zzaly)
    {
      if (this.zzalz == null) {
        return null;
      }
      IBinder localIBinder = this.zzalz.asBinder();
      return localIBinder;
    }
  }
  
  public Bundle zzoi()
  {
    return null;
  }
  
  @Nullable
  protected final String zzqD()
  {
    return this.zzahz.zzqw();
  }
  
  public void zzqG()
  {
    int i = this.zzags.isGooglePlayServicesAvailable(this.mContext);
    if (i != 0)
    {
      zzb(1, null);
      this.zzalA = new zzf();
      this.mHandler.sendMessage(this.mHandler.obtainMessage(3, this.zzalI.get(), i));
      return;
    }
    zza(new zzf());
  }
  
  protected final zzf zzqH()
  {
    return this.zzahz;
  }
  
  protected final void zzqI()
  {
    if (!isConnected()) {
      throw new IllegalStateException("Not connected. Call connect() and wait for onConnected() to be called.");
    }
  }
  
  public final T zzqJ()
    throws DeadObjectException
  {
    synchronized (this.zzpV)
    {
      if (this.zzalE == 4) {
        throw new DeadObjectException();
      }
    }
    zzqI();
    if (this.zzalB != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Client is connected but service is null");
      IInterface localIInterface = this.zzalB;
      return localIInterface;
    }
  }
  
  public boolean zzqK()
  {
    return false;
  }
  
  public final Account zzqq()
  {
    if (this.zzTI != null) {
      return this.zzTI;
    }
    return new Account("<<default account>>", "com.google");
  }
  
  private abstract class zza
    extends zzj<T>.zzc<Boolean>
  {
    public final int statusCode;
    public final Bundle zzalK;
    
    @BinderThread
    protected zza(int paramInt, Bundle paramBundle)
    {
      super(Boolean.valueOf(true));
      this.statusCode = paramInt;
      this.zzalK = paramBundle;
    }
    
    protected void zzc(Boolean paramBoolean)
    {
      Object localObject = null;
      if (paramBoolean == null) {
        zzj.zza(zzj.this, 1, null);
      }
      do
      {
        return;
        switch (this.statusCode)
        {
        default: 
          zzj.zza(zzj.this, 1, null);
          paramBoolean = (Boolean)localObject;
          if (this.zzalK != null) {
            paramBoolean = (PendingIntent)this.zzalK.getParcelable("pendingIntent");
          }
          zzj(new ConnectionResult(this.statusCode, paramBoolean));
          return;
        }
      } while (zzqL());
      zzj.zza(zzj.this, 1, null);
      zzj(new ConnectionResult(8, null));
      return;
      zzj.zza(zzj.this, 1, null);
      throw new IllegalStateException("A fatal developer error has occurred. Check the logs for further information.");
    }
    
    protected abstract void zzj(ConnectionResult paramConnectionResult);
    
    protected abstract boolean zzqL();
    
    protected void zzqM() {}
  }
  
  final class zzb
    extends Handler
  {
    public zzb(Looper paramLooper)
    {
      super();
    }
    
    private void zza(Message paramMessage)
    {
      paramMessage = (zzj.zzc)paramMessage.obj;
      paramMessage.zzqM();
      paramMessage.unregister();
    }
    
    private boolean zzb(Message paramMessage)
    {
      return (paramMessage.what == 2) || (paramMessage.what == 1) || (paramMessage.what == 5);
    }
    
    public void handleMessage(Message paramMessage)
    {
      if (zzj.this.zzalI.get() != paramMessage.arg1)
      {
        if (zzb(paramMessage)) {
          zza(paramMessage);
        }
        return;
      }
      if (((paramMessage.what == 1) || (paramMessage.what == 5)) && (!zzj.this.isConnecting()))
      {
        zza(paramMessage);
        return;
      }
      if (paramMessage.what == 3)
      {
        paramMessage = new ConnectionResult(paramMessage.arg2, null);
        zzj.zzb(zzj.this).zza(paramMessage);
        zzj.this.onConnectionFailed(paramMessage);
        return;
      }
      if (paramMessage.what == 4)
      {
        zzj.zza(zzj.this, 4, null);
        if (zzj.zzc(zzj.this) != null) {
          zzj.zzc(zzj.this).onConnectionSuspended(paramMessage.arg2);
        }
        zzj.this.onConnectionSuspended(paramMessage.arg2);
        zzj.zza(zzj.this, 4, 1, null);
        return;
      }
      if ((paramMessage.what == 2) && (!zzj.this.isConnected()))
      {
        zza(paramMessage);
        return;
      }
      if (zzb(paramMessage))
      {
        ((zzj.zzc)paramMessage.obj).zzqN();
        return;
      }
      Log.wtf("GmsClient", "Don't know how to handle message: " + paramMessage.what, new Exception());
    }
  }
  
  protected abstract class zzc<TListener>
  {
    private TListener mListener;
    private boolean zzalM;
    
    public zzc()
    {
      Object localObject;
      this.mListener = localObject;
      this.zzalM = false;
    }
    
    public void unregister()
    {
      zzqO();
      synchronized (zzj.zzd(zzj.this))
      {
        zzj.zzd(zzj.this).remove(this);
        return;
      }
    }
    
    protected abstract void zzqM();
    
    /* Error */
    public void zzqN()
    {
      // Byte code:
      //   0: aload_0
      //   1: monitorenter
      //   2: aload_0
      //   3: getfield 24	com/google/android/gms/common/internal/zzj$zzc:mListener	Ljava/lang/Object;
      //   6: astore_1
      //   7: aload_0
      //   8: getfield 26	com/google/android/gms/common/internal/zzj$zzc:zzalM	Z
      //   11: ifeq +33 -> 44
      //   14: ldc 49
      //   16: new 51	java/lang/StringBuilder
      //   19: dup
      //   20: invokespecial 52	java/lang/StringBuilder:<init>	()V
      //   23: ldc 54
      //   25: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   28: aload_0
      //   29: invokevirtual 61	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
      //   32: ldc 63
      //   34: invokevirtual 58	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
      //   37: invokevirtual 67	java/lang/StringBuilder:toString	()Ljava/lang/String;
      //   40: invokestatic 73	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
      //   43: pop
      //   44: aload_0
      //   45: monitorexit
      //   46: aload_1
      //   47: ifnull +34 -> 81
      //   50: aload_0
      //   51: aload_1
      //   52: invokevirtual 77	com/google/android/gms/common/internal/zzj$zzc:zzw	(Ljava/lang/Object;)V
      //   55: aload_0
      //   56: monitorenter
      //   57: aload_0
      //   58: iconst_1
      //   59: putfield 26	com/google/android/gms/common/internal/zzj$zzc:zzalM	Z
      //   62: aload_0
      //   63: monitorexit
      //   64: aload_0
      //   65: invokevirtual 79	com/google/android/gms/common/internal/zzj$zzc:unregister	()V
      //   68: return
      //   69: astore_1
      //   70: aload_0
      //   71: monitorexit
      //   72: aload_1
      //   73: athrow
      //   74: astore_1
      //   75: aload_0
      //   76: invokevirtual 81	com/google/android/gms/common/internal/zzj$zzc:zzqM	()V
      //   79: aload_1
      //   80: athrow
      //   81: aload_0
      //   82: invokevirtual 81	com/google/android/gms/common/internal/zzj$zzc:zzqM	()V
      //   85: goto -30 -> 55
      //   88: astore_1
      //   89: aload_0
      //   90: monitorexit
      //   91: aload_1
      //   92: athrow
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	93	0	this	zzc
      //   6	46	1	localObject1	Object
      //   69	4	1	localObject2	Object
      //   74	6	1	localRuntimeException	RuntimeException
      //   88	4	1	localObject3	Object
      // Exception table:
      //   from	to	target	type
      //   2	44	69	finally
      //   44	46	69	finally
      //   70	72	69	finally
      //   50	55	74	java/lang/RuntimeException
      //   57	64	88	finally
      //   89	91	88	finally
    }
    
    public void zzqO()
    {
      try
      {
        this.mListener = null;
        return;
      }
      finally {}
    }
    
    protected abstract void zzw(TListener paramTListener);
  }
  
  public static final class zzd
    extends zzr.zza
  {
    private zzj zzalN;
    private final int zzalO;
    
    public zzd(@NonNull zzj paramzzj, int paramInt)
    {
      this.zzalN = paramzzj;
      this.zzalO = paramInt;
    }
    
    private void zzqP()
    {
      this.zzalN = null;
    }
    
    @BinderThread
    public void zza(int paramInt, @NonNull IBinder paramIBinder, @Nullable Bundle paramBundle)
    {
      zzx.zzb(this.zzalN, "onPostInitComplete can be called only once per call to getRemoteService");
      this.zzalN.zza(paramInt, paramIBinder, paramBundle, this.zzalO);
      zzqP();
    }
    
    @BinderThread
    public void zzb(int paramInt, @Nullable Bundle paramBundle)
    {
      Log.wtf("GmsClient", "received deprecated onAccountValidationComplete callback, ignoring", new Exception());
    }
  }
  
  public final class zze
    implements ServiceConnection
  {
    private final int zzalO;
    
    public zze(int paramInt)
    {
      this.zzalO = paramInt;
    }
    
    public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
    {
      zzx.zzb(paramIBinder, "Expecting a valid IBinder");
      synchronized (zzj.zza(zzj.this))
      {
        zzj.zza(zzj.this, zzs.zza.zzaS(paramIBinder));
        zzj.this.zzm(0, this.zzalO);
        return;
      }
    }
    
    public void onServiceDisconnected(ComponentName arg1)
    {
      synchronized (zzj.zza(zzj.this))
      {
        zzj.zza(zzj.this, null);
        zzj.this.mHandler.sendMessage(zzj.this.mHandler.obtainMessage(4, this.zzalO, 1));
        return;
      }
    }
  }
  
  protected class zzf
    implements GoogleApiClient.zza
  {
    public zzf() {}
    
    public void zza(@NonNull ConnectionResult paramConnectionResult)
    {
      if (paramConnectionResult.isSuccess()) {
        zzj.this.zza(null, zzj.zze(zzj.this));
      }
      while (zzj.zzf(zzj.this) == null) {
        return;
      }
      zzj.zzf(zzj.this).onConnectionFailed(paramConnectionResult);
    }
  }
  
  protected final class zzg
    extends zzj<T>.zza
  {
    public final IBinder zzalP;
    
    @BinderThread
    public zzg(int paramInt, IBinder paramIBinder, Bundle paramBundle)
    {
      super(paramInt, paramBundle);
      this.zzalP = paramIBinder;
    }
    
    protected void zzj(ConnectionResult paramConnectionResult)
    {
      if (zzj.zzf(zzj.this) != null) {
        zzj.zzf(zzj.this).onConnectionFailed(paramConnectionResult);
      }
      zzj.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzqL()
    {
      do
      {
        try
        {
          String str = this.zzalP.getInterfaceDescriptor();
          if (!zzj.this.zzgv().equals(str))
          {
            Log.e("GmsClient", "service descriptor mismatch: " + zzj.this.zzgv() + " vs. " + str);
            return false;
          }
        }
        catch (RemoteException localRemoteException)
        {
          Log.w("GmsClient", "service probably died");
          return false;
        }
        localObject = zzj.this.zzW(this.zzalP);
      } while ((localObject == null) || (!zzj.zza(zzj.this, 2, 3, (IInterface)localObject)));
      Object localObject = zzj.this.zzoi();
      if (zzj.zzc(zzj.this) != null) {
        zzj.zzc(zzj.this).onConnected((Bundle)localObject);
      }
      return true;
    }
  }
  
  protected final class zzh
    extends zzj<T>.zza
  {
    @BinderThread
    public zzh(int paramInt)
    {
      super(paramInt, null);
    }
    
    protected void zzj(ConnectionResult paramConnectionResult)
    {
      zzj.zzb(zzj.this).zza(paramConnectionResult);
      zzj.this.onConnectionFailed(paramConnectionResult);
    }
    
    protected boolean zzqL()
    {
      zzj.zzb(zzj.this).zza(ConnectionResult.zzafB);
      return true;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */