package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.concurrent.Future;

@zzhb
public class zzee
{
  private zzed zza(Context paramContext, VersionInfoParcel paramVersionInfoParcel, final zza<zzed> paramzza, zzan paramzzan)
  {
    paramContext = new zzef(paramContext, paramVersionInfoParcel, paramzzan);
    paramzza.zzAR = paramContext;
    paramContext.zza(new zzed.zza()
    {
      public void zzeo()
      {
        paramzza.zzg(paramzza.zzAR);
      }
    });
    return paramContext;
  }
  
  public Future<zzed> zza(final Context paramContext, final VersionInfoParcel paramVersionInfoParcel, final String paramString, final zzan paramzzan)
  {
    final zza localzza = new zza(null);
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        zzee.zza(zzee.this, paramContext, paramVersionInfoParcel, localzza, paramzzan).zzaa(paramString);
      }
    });
    return localzza;
  }
  
  private static class zza<JavascriptEngine>
    extends zzjd<JavascriptEngine>
  {
    JavascriptEngine zzAR;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzee.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */