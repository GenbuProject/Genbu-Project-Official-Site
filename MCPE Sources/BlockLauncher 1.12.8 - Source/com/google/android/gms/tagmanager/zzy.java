package com.google.android.gms.tagmanager;

import android.util.Log;

public class zzy
  implements zzbh
{
  private int zzRB = 5;
  
  public void e(String paramString)
  {
    if (this.zzRB <= 6) {
      Log.e("GoogleTagManager", paramString);
    }
  }
  
  public void setLogLevel(int paramInt)
  {
    this.zzRB = paramInt;
  }
  
  public void v(String paramString)
  {
    if (this.zzRB <= 2) {
      Log.v("GoogleTagManager", paramString);
    }
  }
  
  public void zzaI(String paramString)
  {
    if (this.zzRB <= 3) {
      Log.d("GoogleTagManager", paramString);
    }
  }
  
  public void zzaJ(String paramString)
  {
    if (this.zzRB <= 4) {
      Log.i("GoogleTagManager", paramString);
    }
  }
  
  public void zzaK(String paramString)
  {
    if (this.zzRB <= 5) {
      Log.w("GoogleTagManager", paramString);
    }
  }
  
  public void zzb(String paramString, Throwable paramThrowable)
  {
    if (this.zzRB <= 6) {
      Log.e("GoogleTagManager", paramString, paramThrowable);
    }
  }
  
  public void zzd(String paramString, Throwable paramThrowable)
  {
    if (this.zzRB <= 5) {
      Log.w("GoogleTagManager", paramString, paramThrowable);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */