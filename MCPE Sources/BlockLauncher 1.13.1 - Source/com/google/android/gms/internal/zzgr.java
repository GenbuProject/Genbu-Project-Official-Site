package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zza;
import com.google.android.gms.ads.internal.zzp;

@zzhb
public class zzgr
{
  public zzit zza(Context paramContext, zza paramzza, zzif.zza paramzza1, zzan paramzzan, zzjp paramzzjp, zzex paramzzex, zza paramzza2, zzcb paramzzcb)
  {
    AdResponseParcel localAdResponseParcel = paramzza1.zzLe;
    if (localAdResponseParcel.zzHT) {
      paramContext = new zzgu(paramContext, paramzza1, paramzzex, paramzza2, paramzzcb, paramzzjp);
    }
    for (;;)
    {
      zzin.zzaI("AdRenderer: " + paramContext.getClass().getName());
      paramContext.zzgd();
      return paramContext;
      if (localAdResponseParcel.zzuk)
      {
        if ((paramzza instanceof zzp))
        {
          paramContext = new zzgv(paramContext, (zzp)paramzza, new zzee(), paramzza1, paramzzan, paramzza2);
        }
        else
        {
          paramzza1 = new StringBuilder().append("Invalid NativeAdManager type. Found: ");
          if (paramzza != null) {}
          for (paramContext = paramzza.getClass().getName();; paramContext = "null") {
            throw new IllegalArgumentException(paramContext + "; Required: NativeAdManager.");
          }
        }
      }
      else if (localAdResponseParcel.zzHZ) {
        paramContext = new zzgp(paramContext, paramzza1, paramzzjp, paramzza2);
      } else if ((((Boolean)zzbt.zzwu.get()).booleanValue()) && (zzne.zzsk()) && (!zzne.isAtLeastL()) && (paramzzjp.zzaN().zzui)) {
        paramContext = new zzgt(paramContext, paramzza1, paramzzjp, paramzza2);
      } else {
        paramContext = new zzgs(paramContext, paramzza1, paramzzjp, paramzza2);
      }
    }
  }
  
  public zzit zza(Context paramContext, String paramString, zzif.zza paramzza, zzht paramzzht)
  {
    paramContext = new zzhz(paramContext, paramString, paramzza, paramzzht);
    zzin.zzaI("AdRenderer: " + paramContext.getClass().getName());
    paramContext.zzgd();
    return paramContext;
  }
  
  public static abstract interface zza
  {
    public abstract void zzb(zzif paramzzif);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */