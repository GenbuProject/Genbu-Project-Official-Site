package com.google.android.gms.ads.mediation;

import android.os.Bundle;

public abstract interface MediationAdapter
{
  public abstract void onDestroy();
  
  public abstract void onPause();
  
  public abstract void onResume();
  
  public static class zza
  {
    private int zzOn;
    
    public zza zzS(int paramInt)
    {
      this.zzOn = paramInt;
      return this;
    }
    
    public Bundle zziw()
    {
      Bundle localBundle = new Bundle();
      localBundle.putInt("capabilities", this.zzOn);
      return localBundle;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\mediation\MediationAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */