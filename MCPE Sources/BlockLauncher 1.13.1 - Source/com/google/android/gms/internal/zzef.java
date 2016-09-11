package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.overlay.zzg;
import com.google.android.gms.ads.internal.overlay.zzp;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zze;
import com.google.android.gms.ads.internal.zzr;
import org.json.JSONObject;

@zzhb
public class zzef
  implements zzed
{
  private final zzjp zzpD;
  
  public zzef(Context paramContext, VersionInfoParcel paramVersionInfoParcel, zzan paramzzan)
  {
    this.zzpD = zzr.zzbD().zza(paramContext, new AdSizeParcel(), false, false, paramzzan, paramVersionInfoParcel);
    this.zzpD.getWebView().setWillNotDraw(true);
  }
  
  private void runOnUiThread(Runnable paramRunnable)
  {
    if (zzn.zzcS().zzhJ())
    {
      paramRunnable.run();
      return;
    }
    zzir.zzMc.post(paramRunnable);
  }
  
  public void destroy()
  {
    this.zzpD.destroy();
  }
  
  public void zzZ(String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        zzef.zza(zzef.this).loadData(this.zzAW, "text/html", "UTF-8");
      }
    });
  }
  
  public void zza(com.google.android.gms.ads.internal.client.zza paramzza, zzg paramzzg, zzdb paramzzdb, zzp paramzzp, boolean paramBoolean, zzdh paramzzdh, zzdj paramzzdj, zze paramzze, zzft paramzzft)
  {
    this.zzpD.zzhU().zzb(paramzza, paramzzg, paramzzdb, paramzzp, paramBoolean, paramzzdh, paramzzdj, new zze(false), paramzzft);
  }
  
  public void zza(final zzed.zza paramzza)
  {
    this.zzpD.zzhU().zza(new zzjq.zza()
    {
      public void zza(zzjp paramAnonymouszzjp, boolean paramAnonymousBoolean)
      {
        paramzza.zzeo();
      }
    });
  }
  
  public void zza(String paramString, zzdf paramzzdf)
  {
    this.zzpD.zzhU().zza(paramString, paramzzdf);
  }
  
  public void zza(final String paramString, final JSONObject paramJSONObject)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        zzef.zza(zzef.this).zza(paramString, paramJSONObject);
      }
    });
  }
  
  public void zzaa(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        zzef.zza(zzef.this).loadUrl(paramString);
      }
    });
  }
  
  public void zzab(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        zzef.zza(zzef.this).loadData(paramString, "text/html", "UTF-8");
      }
    });
  }
  
  public void zzb(String paramString, zzdf paramzzdf)
  {
    this.zzpD.zzhU().zzb(paramString, paramzzdf);
  }
  
  public void zzb(String paramString, JSONObject paramJSONObject)
  {
    this.zzpD.zzb(paramString, paramJSONObject);
  }
  
  public void zze(final String paramString1, final String paramString2)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        zzef.zza(zzef.this).zze(paramString1, paramString2);
      }
    });
  }
  
  public zzei zzen()
  {
    return new zzej(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */