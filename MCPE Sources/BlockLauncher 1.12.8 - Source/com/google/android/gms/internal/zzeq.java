package com.google.android.gms.internal;

@zzhb
public final class zzeq
  extends zzez.zza
{
  private zzes.zza zzCb;
  private zzep zzCc;
  private final Object zzpV = new Object();
  
  public void onAdClicked()
  {
    synchronized (this.zzpV)
    {
      if (this.zzCc != null) {
        this.zzCc.zzaY();
      }
      return;
    }
  }
  
  public void onAdClosed()
  {
    synchronized (this.zzpV)
    {
      if (this.zzCc != null) {
        this.zzCc.zzaZ();
      }
      return;
    }
  }
  
  public void onAdFailedToLoad(int paramInt)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzCb != null)
        {
          if (paramInt == 3)
          {
            paramInt = 1;
            this.zzCb.zzr(paramInt);
            this.zzCb = null;
          }
        }
        else {
          return;
        }
      }
      paramInt = 2;
    }
  }
  
  public void onAdLeftApplication()
  {
    synchronized (this.zzpV)
    {
      if (this.zzCc != null) {
        this.zzCc.zzba();
      }
      return;
    }
  }
  
  public void onAdLoaded()
  {
    synchronized (this.zzpV)
    {
      if (this.zzCb != null)
      {
        this.zzCb.zzr(0);
        this.zzCb = null;
        return;
      }
      if (this.zzCc != null) {
        this.zzCc.zzbc();
      }
      return;
    }
  }
  
  public void onAdOpened()
  {
    synchronized (this.zzpV)
    {
      if (this.zzCc != null) {
        this.zzCc.zzbb();
      }
      return;
    }
  }
  
  public void zza(zzep paramzzep)
  {
    synchronized (this.zzpV)
    {
      this.zzCc = paramzzep;
      return;
    }
  }
  
  public void zza(zzes.zza paramzza)
  {
    synchronized (this.zzpV)
    {
      this.zzCb = paramzza;
      return;
    }
  }
  
  public void zza(zzfa paramzzfa)
  {
    synchronized (this.zzpV)
    {
      if (this.zzCb != null)
      {
        this.zzCb.zza(0, paramzzfa);
        this.zzCb = null;
        return;
      }
      if (this.zzCc != null) {
        this.zzCc.zzbc();
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzeq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */