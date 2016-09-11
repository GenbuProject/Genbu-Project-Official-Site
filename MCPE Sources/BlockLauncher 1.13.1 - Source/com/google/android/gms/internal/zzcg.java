package com.google.android.gms.internal;

import com.google.android.gms.ads.doubleclick.OnCustomRenderedAdLoadedListener;

@zzhb
public final class zzcg
  extends zzcf.zza
{
  private final OnCustomRenderedAdLoadedListener zzuQ;
  
  public zzcg(OnCustomRenderedAdLoadedListener paramOnCustomRenderedAdLoadedListener)
  {
    this.zzuQ = paramOnCustomRenderedAdLoadedListener;
  }
  
  public void zza(zzce paramzzce)
  {
    this.zzuQ.onCustomRenderedAdLoaded(new zzcd(paramzzce));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzcg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */