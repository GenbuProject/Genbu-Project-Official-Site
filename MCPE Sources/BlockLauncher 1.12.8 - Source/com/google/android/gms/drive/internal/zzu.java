package com.google.android.gms.drive.internal;

import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.DeadObjectException;
import android.os.IBinder;
import android.os.Looper;
import android.os.Process;
import android.os.RemoteException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.zzf;
import com.google.android.gms.common.internal.zzj;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.ChangeListener;
import com.google.android.gms.drive.events.zzc;
import com.google.android.gms.drive.events.zzg;
import com.google.android.gms.drive.events.zzi;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class zzu
  extends zzj<zzam>
{
  private final String zzUW;
  final GoogleApiClient.ConnectionCallbacks zzalF;
  private final Bundle zzaqK;
  private final boolean zzaqL;
  private volatile DriveId zzaqM;
  private volatile DriveId zzaqN;
  private volatile boolean zzaqO = false;
  final Map<DriveId, Map<ChangeListener, zzae>> zzaqP = new HashMap();
  final Map<zzc, zzae> zzaqQ = new HashMap();
  final Map<DriveId, Map<zzi, zzae>> zzaqR = new HashMap();
  final Map<DriveId, Map<zzi, zzae>> zzaqS = new HashMap();
  
  public zzu(Context paramContext, Looper paramLooper, zzf paramzzf, GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks, GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener, Bundle paramBundle)
  {
    super(paramContext, paramLooper, 11, paramzzf, paramConnectionCallbacks, paramOnConnectionFailedListener);
    this.zzUW = paramzzf.zzqv();
    this.zzalF = paramConnectionCallbacks;
    this.zzaqK = paramBundle;
    paramLooper = new Intent("com.google.android.gms.drive.events.HANDLE_EVENT");
    paramLooper.setPackage(paramContext.getPackageName());
    paramContext = paramContext.getPackageManager().queryIntentServices(paramLooper, 0);
    switch (paramContext.size())
    {
    default: 
      throw new IllegalStateException("AndroidManifest.xml can only define one service that handles the " + paramLooper.getAction() + " action");
    case 0: 
      this.zzaqL = false;
      return;
    }
    paramContext = ((ResolveInfo)paramContext.get(0)).serviceInfo;
    if (!paramContext.exported) {
      throw new IllegalStateException("Drive event service " + paramContext.name + " must be exported in AndroidManifest.xml");
    }
    this.zzaqL = true;
  }
  
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final int paramInt, final DriveId paramDriveId)
  {
    zzx.zzac(zzg.zza(paramInt, paramDriveId));
    zzx.zza(isConnected(), "Client must be connected");
    paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(new RemoveEventListenerRequest(paramDriveId, paramInt), null, null, new zzbu(this));
      }
    });
  }
  
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final AddEventListenerRequest paramAddEventListenerRequest)
  {
    zzx.zzac(zzg.zza(paramAddEventListenerRequest.getEventType(), paramAddEventListenerRequest.getDriveId()));
    zzx.zza(isConnected(), "Client must be connected");
    if (!this.zzaqL) {
      throw new IllegalStateException("Application must define an exported DriveEventService subclass in AndroidManifest.xml to add event subscriptions");
    }
    paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(paramAddEventListenerRequest, null, null, new zzbu(this));
      }
    });
  }
  
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final AddEventListenerRequest paramAddEventListenerRequest, final zzae paramzzae)
  {
    paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(paramAddEventListenerRequest, paramzzae, null, new zzbu(this));
      }
    });
  }
  
  private PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, final RemoveEventListenerRequest paramRemoveEventListenerRequest, final zzae paramzzae)
  {
    paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
    {
      protected void zza(zzu paramAnonymouszzu)
        throws RemoteException
      {
        paramAnonymouszzu.zzte().zza(paramRemoveEventListenerRequest, paramzzae, null, new zzbu(this));
      }
    });
  }
  
  PendingResult<Status> cancelPendingActions(GoogleApiClient paramGoogleApiClient, final List<String> paramList)
  {
    boolean bool2 = true;
    if (paramList != null)
    {
      bool1 = true;
      zzx.zzac(bool1);
      if (paramList.isEmpty()) {
        break label58;
      }
    }
    label58:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zzac(bool1);
      zzx.zza(isConnected(), "Client must be connected");
      paramGoogleApiClient.zzb(new zzt.zza(paramGoogleApiClient)
      {
        protected void zza(zzu paramAnonymouszzu)
          throws RemoteException
        {
          paramAnonymouszzu.zzte().zza(new CancelPendingActionsRequest(paramList), new zzbu(this));
        }
      });
      bool1 = false;
      break;
    }
  }
  
  public void disconnect()
  {
    if (isConnected()) {}
    try
    {
      ((zzam)zzqJ()).zza(new DisconnectRequest());
      super.disconnect();
      synchronized (this.zzaqP)
      {
        this.zzaqP.clear();
        synchronized (this.zzaqQ)
        {
          this.zzaqQ.clear();
          synchronized (this.zzaqR)
          {
            this.zzaqR.clear();
          }
        }
      }
      synchronized (this.zzaqS)
      {
        this.zzaqS.clear();
        return;
        localObject1 = finally;
        throw ((Throwable)localObject1);
        localObject2 = finally;
        throw ((Throwable)localObject2);
        localObject3 = finally;
        throw ((Throwable)localObject3);
      }
    }
    catch (RemoteException localRemoteException)
    {
      for (;;) {}
    }
  }
  
  PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    return zza(paramGoogleApiClient, new AddEventListenerRequest(1, paramDriveId));
  }
  
  PendingResult<Status> zza(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId, ChangeListener paramChangeListener)
  {
    zzx.zzac(zzg.zza(1, paramDriveId));
    zzx.zzb(paramChangeListener, "listener");
    zzx.zza(isConnected(), "Client must be connected");
    for (;;)
    {
      synchronized (this.zzaqP)
      {
        Object localObject = (Map)this.zzaqP.get(paramDriveId);
        if (localObject == null)
        {
          localObject = new HashMap();
          this.zzaqP.put(paramDriveId, localObject);
          zzae localzzae = (zzae)((Map)localObject).get(paramChangeListener);
          if (localzzae == null)
          {
            localzzae = new zzae(getLooper(), getContext(), 1, paramChangeListener);
            ((Map)localObject).put(paramChangeListener, localzzae);
            paramChangeListener = localzzae;
            paramChangeListener.zzdg(1);
            paramGoogleApiClient = zza(paramGoogleApiClient, new AddEventListenerRequest(1, paramDriveId), paramChangeListener);
            return paramGoogleApiClient;
          }
          paramChangeListener = localzzae;
          if (localzzae.zzdh(1))
          {
            paramGoogleApiClient = new zzs.zzj(paramGoogleApiClient, Status.zzagC);
            return paramGoogleApiClient;
          }
        }
      }
    }
  }
  
  protected void zza(int paramInt1, IBinder paramIBinder, Bundle paramBundle, int paramInt2)
  {
    if (paramBundle != null)
    {
      paramBundle.setClassLoader(getClass().getClassLoader());
      this.zzaqM = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.root_id"));
      this.zzaqN = ((DriveId)paramBundle.getParcelable("com.google.android.gms.drive.appdata_id"));
      this.zzaqO = true;
    }
    super.zza(paramInt1, paramIBinder, paramBundle, paramInt2);
  }
  
  protected zzam zzaZ(IBinder paramIBinder)
  {
    return zzam.zza.zzba(paramIBinder);
  }
  
  PendingResult<Status> zzb(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId)
  {
    return zza(paramGoogleApiClient, 1, paramDriveId);
  }
  
  PendingResult<Status> zzb(GoogleApiClient paramGoogleApiClient, DriveId paramDriveId, ChangeListener paramChangeListener)
  {
    zzx.zzac(zzg.zza(1, paramDriveId));
    zzx.zza(isConnected(), "Client must be connected");
    zzx.zzb(paramChangeListener, "listener");
    Map localMap2;
    synchronized (this.zzaqP)
    {
      localMap2 = (Map)this.zzaqP.get(paramDriveId);
      if (localMap2 == null)
      {
        paramGoogleApiClient = new zzs.zzj(paramGoogleApiClient, Status.zzagC);
        return paramGoogleApiClient;
      }
      paramChangeListener = (zzae)localMap2.remove(paramChangeListener);
      if (paramChangeListener == null)
      {
        paramGoogleApiClient = new zzs.zzj(paramGoogleApiClient, Status.zzagC);
        return paramGoogleApiClient;
      }
    }
    if (localMap2.isEmpty()) {
      this.zzaqP.remove(paramDriveId);
    }
    paramGoogleApiClient = zza(paramGoogleApiClient, new RemoveEventListenerRequest(paramDriveId, 1), paramChangeListener);
    return paramGoogleApiClient;
  }
  
  protected String zzgu()
  {
    return "com.google.android.gms.drive.ApiService.START";
  }
  
  protected String zzgv()
  {
    return "com.google.android.gms.drive.internal.IDriveService";
  }
  
  public boolean zzmE()
  {
    return (!getContext().getPackageName().equals(this.zzUW)) || (!zztd());
  }
  
  protected Bundle zzml()
  {
    String str = getContext().getPackageName();
    zzx.zzz(str);
    if (!zzqH().zzqt().isEmpty()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzab(bool);
      Bundle localBundle = new Bundle();
      if (!str.equals(this.zzUW)) {
        localBundle.putString("proxy_package_name", this.zzUW);
      }
      localBundle.putAll(this.zzaqK);
      return localBundle;
    }
  }
  
  public boolean zzqK()
  {
    return true;
  }
  
  boolean zztd()
  {
    return GooglePlayServicesUtil.zzf(getContext(), Process.myUid());
  }
  
  public zzam zzte()
    throws DeadObjectException
  {
    return (zzam)zzqJ();
  }
  
  public DriveId zztf()
  {
    return this.zzaqM;
  }
  
  public DriveId zztg()
  {
    return this.zzaqN;
  }
  
  public boolean zzth()
  {
    return this.zzaqO;
  }
  
  public boolean zzti()
  {
    return this.zzaqL;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */