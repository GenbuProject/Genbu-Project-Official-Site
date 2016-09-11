package com.google.android.gms.internal;

import android.app.Activity;
import android.view.ViewTreeObserver.OnGlobalLayoutListener;
import android.view.ViewTreeObserver.OnScrollChangedListener;
import com.google.android.gms.ads.internal.zzr;

@zzhb
public final class zzjc
{
  private Activity zzMM;
  private boolean zzMN;
  private boolean zzMO;
  private boolean zzMP;
  private ViewTreeObserver.OnGlobalLayoutListener zzMQ;
  private ViewTreeObserver.OnScrollChangedListener zzMR;
  
  public zzjc(Activity paramActivity, ViewTreeObserver.OnGlobalLayoutListener paramOnGlobalLayoutListener, ViewTreeObserver.OnScrollChangedListener paramOnScrollChangedListener)
  {
    this.zzMM = paramActivity;
    this.zzMQ = paramOnGlobalLayoutListener;
    this.zzMR = paramOnScrollChangedListener;
  }
  
  private void zzhG()
  {
    if (this.zzMM == null) {}
    while (this.zzMN) {
      return;
    }
    if (this.zzMQ != null) {
      zzr.zzbC().zza(this.zzMM, this.zzMQ);
    }
    if (this.zzMR != null) {
      zzr.zzbC().zza(this.zzMM, this.zzMR);
    }
    this.zzMN = true;
  }
  
  private void zzhH()
  {
    if (this.zzMM == null) {}
    while (!this.zzMN) {
      return;
    }
    if (this.zzMQ != null) {
      zzr.zzbE().zzb(this.zzMM, this.zzMQ);
    }
    if (this.zzMR != null) {
      zzr.zzbC().zzb(this.zzMM, this.zzMR);
    }
    this.zzMN = false;
  }
  
  public void onAttachedToWindow()
  {
    this.zzMO = true;
    if (this.zzMP) {
      zzhG();
    }
  }
  
  public void onDetachedFromWindow()
  {
    this.zzMO = false;
    zzhH();
  }
  
  public void zzhE()
  {
    this.zzMP = true;
    if (this.zzMO) {
      zzhG();
    }
  }
  
  public void zzhF()
  {
    this.zzMP = false;
    zzhH();
  }
  
  public void zzi(Activity paramActivity)
  {
    this.zzMM = paramActivity;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzjc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */