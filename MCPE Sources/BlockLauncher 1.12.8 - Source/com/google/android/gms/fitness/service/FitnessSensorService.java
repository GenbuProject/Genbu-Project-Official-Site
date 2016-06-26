package com.google.android.gms.fitness.service;

import android.annotation.TargetApi;
import android.app.AppOpsManager;
import android.app.Service;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Binder;
import android.os.IBinder;
import android.os.RemoteException;
import android.support.annotation.CallSuper;
import android.util.Log;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.internal.service.FitnessDataSourcesRequest;
import com.google.android.gms.fitness.internal.service.FitnessUnregistrationRequest;
import com.google.android.gms.fitness.internal.service.zzc.zza;
import com.google.android.gms.fitness.result.DataSourcesResult;
import com.google.android.gms.internal.zzne;
import com.google.android.gms.internal.zzoi;
import com.google.android.gms.internal.zzow;
import java.util.List;

public abstract class FitnessSensorService
  extends Service
{
  public static final String SERVICE_INTERFACE = "com.google.android.gms.fitness.service.FitnessSensorService";
  private zza zzaBP;
  
  @CallSuper
  public IBinder onBind(Intent paramIntent)
  {
    if ("com.google.android.gms.fitness.service.FitnessSensorService".equals(paramIntent.getAction()))
    {
      if (Log.isLoggable("FitnessSensorService", 3)) {
        Log.d("FitnessSensorService", "Intent " + paramIntent + " received by " + getClass().getName());
      }
      return this.zzaBP.asBinder();
    }
    return null;
  }
  
  @CallSuper
  public void onCreate()
  {
    super.onCreate();
    this.zzaBP = new zza(this, null);
  }
  
  public abstract List<DataSource> onFindDataSources(List<DataType> paramList);
  
  public abstract boolean onRegister(FitnessSensorServiceRequest paramFitnessSensorServiceRequest);
  
  public abstract boolean onUnregister(DataSource paramDataSource);
  
  @TargetApi(19)
  protected void zzvr()
    throws SecurityException
  {
    int i = Binder.getCallingUid();
    if (zzne.zzsk())
    {
      ((AppOpsManager)getSystemService("appops")).checkPackage(i, "com.google.android.gms");
      return;
    }
    String[] arrayOfString = getPackageManager().getPackagesForUid(i);
    if (arrayOfString != null)
    {
      int j = arrayOfString.length;
      i = 0;
      for (;;)
      {
        if (i >= j) {
          break label67;
        }
        if (arrayOfString[i].equals("com.google.android.gms")) {
          break;
        }
        i += 1;
      }
    }
    label67:
    throw new SecurityException("Unauthorized caller");
  }
  
  private static class zza
    extends zzc.zza
  {
    private final FitnessSensorService zzaBQ;
    
    private zza(FitnessSensorService paramFitnessSensorService)
    {
      this.zzaBQ = paramFitnessSensorService;
    }
    
    public void zza(FitnessDataSourcesRequest paramFitnessDataSourcesRequest, zzoi paramzzoi)
      throws RemoteException
    {
      this.zzaBQ.zzvr();
      paramzzoi.zza(new DataSourcesResult(this.zzaBQ.onFindDataSources(paramFitnessDataSourcesRequest.getDataTypes()), Status.zzagC));
    }
    
    public void zza(FitnessUnregistrationRequest paramFitnessUnregistrationRequest, zzow paramzzow)
      throws RemoteException
    {
      this.zzaBQ.zzvr();
      if (this.zzaBQ.onUnregister(paramFitnessUnregistrationRequest.getDataSource()))
      {
        paramzzow.zzp(Status.zzagC);
        return;
      }
      paramzzow.zzp(new Status(13));
    }
    
    public void zza(FitnessSensorServiceRequest paramFitnessSensorServiceRequest, zzow paramzzow)
      throws RemoteException
    {
      this.zzaBQ.zzvr();
      if (this.zzaBQ.onRegister(paramFitnessSensorServiceRequest))
      {
        paramzzow.zzp(Status.zzagC);
        return;
      }
      paramzzow.zzp(new Status(13));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\service\FitnessSensorService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */