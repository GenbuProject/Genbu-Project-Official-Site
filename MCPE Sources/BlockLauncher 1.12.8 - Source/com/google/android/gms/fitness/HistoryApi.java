package com.google.android.gms.fitness;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.support.annotation.RequiresPermission;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.DataSet;
import com.google.android.gms.fitness.data.DataSource;
import com.google.android.gms.fitness.data.DataType;
import com.google.android.gms.fitness.request.DataDeleteRequest;
import com.google.android.gms.fitness.request.DataReadRequest;
import com.google.android.gms.fitness.request.DataUpdateRequest;
import com.google.android.gms.fitness.result.DailyTotalResult;
import com.google.android.gms.fitness.result.DataReadResult;
import java.util.concurrent.TimeUnit;

public abstract interface HistoryApi
{
  public abstract PendingResult<Status> deleteData(GoogleApiClient paramGoogleApiClient, DataDeleteRequest paramDataDeleteRequest);
  
  public abstract PendingResult<Status> insertData(GoogleApiClient paramGoogleApiClient, DataSet paramDataSet);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional=true)
  public abstract PendingResult<DailyTotalResult> readDailyTotal(GoogleApiClient paramGoogleApiClient, DataType paramDataType);
  
  @RequiresPermission(anyOf={"android.permission.ACCESS_FINE_LOCATION", "android.permission.BODY_SENSORS"}, conditional=true)
  public abstract PendingResult<DataReadResult> readData(GoogleApiClient paramGoogleApiClient, DataReadRequest paramDataReadRequest);
  
  public abstract PendingResult<Status> updateData(GoogleApiClient paramGoogleApiClient, DataUpdateRequest paramDataUpdateRequest);
  
  public static class ViewIntentBuilder
  {
    private final Context mContext;
    private long zzRD;
    private final DataType zzavT;
    private DataSource zzavU;
    private long zzavV;
    private String zzavW;
    
    public ViewIntentBuilder(Context paramContext, DataType paramDataType)
    {
      this.mContext = paramContext;
      this.zzavT = paramDataType;
    }
    
    private Intent zzl(Intent paramIntent)
    {
      if (this.zzavW == null) {}
      Intent localIntent;
      ResolveInfo localResolveInfo;
      do
      {
        return paramIntent;
        localIntent = new Intent(paramIntent).setPackage(this.zzavW);
        localResolveInfo = this.mContext.getPackageManager().resolveActivity(localIntent, 0);
      } while (localResolveInfo == null);
      paramIntent = localResolveInfo.activityInfo.name;
      localIntent.setComponent(new ComponentName(this.zzavW, paramIntent));
      return localIntent;
    }
    
    public Intent build()
    {
      boolean bool2 = true;
      if (this.zzRD > 0L)
      {
        bool1 = true;
        zzx.zza(bool1, "Start time must be set");
        if (this.zzavV <= this.zzRD) {
          break label107;
        }
      }
      label107:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zza(bool1, "End time must be set and after start time");
        Intent localIntent = new Intent("vnd.google.fitness.VIEW");
        localIntent.setType(DataType.getMimeType(this.zzavU.getDataType()));
        localIntent.putExtra("vnd.google.fitness.start_time", this.zzRD);
        localIntent.putExtra("vnd.google.fitness.end_time", this.zzavV);
        zzc.zza(this.zzavU, localIntent, "vnd.google.fitness.data_source");
        return zzl(localIntent);
        bool1 = false;
        break;
      }
    }
    
    public ViewIntentBuilder setDataSource(DataSource paramDataSource)
    {
      zzx.zzb(paramDataSource.getDataType().equals(this.zzavT), "Data source %s is not for the data type %s", new Object[] { paramDataSource, this.zzavT });
      this.zzavU = paramDataSource;
      return this;
    }
    
    public ViewIntentBuilder setPreferredApplication(String paramString)
    {
      this.zzavW = paramString;
      return this;
    }
    
    public ViewIntentBuilder setTimeInterval(long paramLong1, long paramLong2, TimeUnit paramTimeUnit)
    {
      this.zzRD = paramTimeUnit.toMillis(paramLong1);
      this.zzavV = paramTimeUnit.toMillis(paramLong2);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\HistoryApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */