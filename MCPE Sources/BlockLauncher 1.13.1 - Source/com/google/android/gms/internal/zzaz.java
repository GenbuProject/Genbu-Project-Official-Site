package com.google.android.gms.internal;

import android.content.Context;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import org.json.JSONObject;

@zzhb
public class zzaz
  extends zzau
{
  private final zzeh zzsE;
  
  public zzaz(Context paramContext, AdSizeParcel paramAdSizeParcel, zzif paramzzif, VersionInfoParcel paramVersionInfoParcel, zzbb paramzzbb, zzeh paramzzeh)
  {
    super(paramContext, paramAdSizeParcel, paramzzif, paramVersionInfoParcel, paramzzbb);
    this.zzsE = paramzzeh;
    zzb(this.zzsE);
    zzcd();
    zzh(false);
    zzin.zzaI("Tracking ad unit: " + this.zzrZ.zzcu());
  }
  
  protected void destroy()
  {
    synchronized (this.zzpV)
    {
      super.destroy();
      zzc(this.zzsE);
      return;
    }
  }
  
  protected void zzb(JSONObject paramJSONObject)
  {
    this.zzsE.zza("AFMA_updateActiveView", paramJSONObject);
  }
  
  public void zzcf()
  {
    destroy();
  }
  
  protected boolean zzcl()
  {
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzaz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */