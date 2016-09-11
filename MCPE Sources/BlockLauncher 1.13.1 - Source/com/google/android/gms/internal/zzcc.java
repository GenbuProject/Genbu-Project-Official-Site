package com.google.android.gms.internal;

import android.view.View;
import com.google.android.gms.ads.internal.zzg;
import com.google.android.gms.dynamic.zzd;
import com.google.android.gms.dynamic.zze;

@zzhb
public final class zzcc
  extends zzce.zza
{
  private final zzg zzxE;
  private final String zzxF;
  private final String zzxG;
  
  public zzcc(zzg paramzzg, String paramString1, String paramString2)
  {
    this.zzxE = paramzzg;
    this.zzxF = paramString1;
    this.zzxG = paramString2;
  }
  
  public String getContent()
  {
    return this.zzxG;
  }
  
  public void recordClick()
  {
    this.zzxE.zzbd();
  }
  
  public void recordImpression()
  {
    this.zzxE.zzbe();
  }
  
  public void zzb(zzd paramzzd)
  {
    if (paramzzd == null) {
      return;
    }
    this.zzxE.zzc((View)zze.zzp(paramzzd));
  }
  
  public String zzdF()
  {
    return this.zzxF;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzcc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */