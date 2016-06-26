package com.google.android.gms.internal;

import com.google.android.gms.ads.formats.NativeCustomTemplateAd.OnCustomClickListener;

@zzhb
public class zzcy
  extends zzct.zza
{
  private final NativeCustomTemplateAd.OnCustomClickListener zzyU;
  
  public zzcy(NativeCustomTemplateAd.OnCustomClickListener paramOnCustomClickListener)
  {
    this.zzyU = paramOnCustomClickListener;
  }
  
  public void zza(zzcp paramzzcp, String paramString)
  {
    this.zzyU.onCustomClick(new zzcq(paramzzcp), paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzcy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */