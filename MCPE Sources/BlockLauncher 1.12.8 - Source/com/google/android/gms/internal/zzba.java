package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONException;
import org.json.JSONObject;

@zzhb
public class zzba
  extends zzau
{
  private zzeg.zzd zzsF;
  private boolean zzsG;
  
  public zzba(final Context paramContext, AdSizeParcel paramAdSizeParcel, zzif paramzzif, VersionInfoParcel paramVersionInfoParcel, zzbb paramzzbb, zzeg paramzzeg)
  {
    super(paramContext, paramAdSizeParcel, paramzzif, paramVersionInfoParcel, paramzzbb);
    this.zzsF = paramzzeg.zzer();
    try
    {
      paramContext = zzd(paramzzbb.zzcq().zzco());
      this.zzsF.zza(new zzji.zzc()new zzji.zza
      {
        public void zzd(zzeh paramAnonymouszzeh)
        {
          zzba.this.zza(paramContext);
        }
      }, new zzji.zza()
      {
        public void run() {}
      });
      this.zzsF.zza(new zzji.zzc()new zzji.zza
      {
        public void zzd(zzeh paramAnonymouszzeh)
        {
          zzba.zza(zzba.this, true);
          zzba.this.zzb(paramAnonymouszzeh);
          zzba.this.zzcd();
          zzba.this.zzh(false);
        }
      }, new zzji.zza()
      {
        public void run()
        {
          zzba.this.destroy();
        }
      });
      zzin.zzaI("Tracking ad unit: " + this.zzrZ.zzcu());
      return;
    }
    catch (RuntimeException paramContext)
    {
      for (;;)
      {
        zzin.zzb("Failure while processing active view data.", paramContext);
      }
    }
    catch (JSONException paramContext)
    {
      for (;;) {}
    }
  }
  
  protected void destroy()
  {
    synchronized (this.zzpV)
    {
      super.destroy();
      this.zzsF.zza(new zzji.zzc()new zzji.zzb
      {
        public void zzd(zzeh paramAnonymouszzeh)
        {
          zzba.this.zzc(paramAnonymouszzeh);
        }
      }, new zzji.zzb());
      this.zzsF.release();
      return;
    }
  }
  
  protected void zzb(final JSONObject paramJSONObject)
  {
    this.zzsF.zza(new zzji.zzc()new zzji.zzb
    {
      public void zzd(zzeh paramAnonymouszzeh)
      {
        paramAnonymouszzeh.zza("AFMA_updateActiveView", paramJSONObject);
      }
    }, new zzji.zzb());
  }
  
  protected boolean zzcl()
  {
    return this.zzsG;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzba.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */