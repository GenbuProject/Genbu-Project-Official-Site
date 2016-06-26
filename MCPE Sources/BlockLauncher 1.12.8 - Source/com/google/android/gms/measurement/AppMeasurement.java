package com.google.android.gms.measurement;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.measurement.internal.zzab;
import com.google.android.gms.measurement.internal.zzw;

public class AppMeasurement
{
  private final zzw zzaTV;
  
  public AppMeasurement(zzw paramzzw)
  {
    zzx.zzz(paramzzw);
    this.zzaTV = paramzzw;
  }
  
  public static AppMeasurement getInstance(Context paramContext)
  {
    return zzw.zzaT(paramContext).zzCV();
  }
  
  public void setMeasurementEnabled(boolean paramBoolean)
  {
    this.zzaTV.zzCf().setMeasurementEnabled(paramBoolean);
  }
  
  public void zzd(String paramString1, String paramString2, Bundle paramBundle)
  {
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    this.zzaTV.zzCf().zze(paramString1, paramString2, localBundle);
  }
  
  public static abstract interface zza
  {
    @WorkerThread
    public abstract void zza(String paramString1, String paramString2, Bundle paramBundle, long paramLong);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\measurement\AppMeasurement.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */