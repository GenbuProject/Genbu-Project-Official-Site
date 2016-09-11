package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzps
  extends zze<zzps>
{
  public int zzDC;
  public int zzDD;
  public int zzaUL;
  public int zzaUM;
  public int zzaUN;
  private String zzaaL;
  
  public String getLanguage()
  {
    return this.zzaaL;
  }
  
  public void setLanguage(String paramString)
  {
    this.zzaaL = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("language", this.zzaaL);
    localHashMap.put("screenColors", Integer.valueOf(this.zzaUL));
    localHashMap.put("screenWidth", Integer.valueOf(this.zzDC));
    localHashMap.put("screenHeight", Integer.valueOf(this.zzDD));
    localHashMap.put("viewportWidth", Integer.valueOf(this.zzaUM));
    localHashMap.put("viewportHeight", Integer.valueOf(this.zzaUN));
    return zzF(localHashMap);
  }
  
  public int zzAQ()
  {
    return this.zzaUL;
  }
  
  public int zzAR()
  {
    return this.zzDC;
  }
  
  public int zzAS()
  {
    return this.zzDD;
  }
  
  public int zzAT()
  {
    return this.zzaUM;
  }
  
  public int zzAU()
  {
    return this.zzaUN;
  }
  
  public void zza(zzps paramzzps)
  {
    if (this.zzaUL != 0) {
      paramzzps.zziA(this.zzaUL);
    }
    if (this.zzDC != 0) {
      paramzzps.zziB(this.zzDC);
    }
    if (this.zzDD != 0) {
      paramzzps.zziC(this.zzDD);
    }
    if (this.zzaUM != 0) {
      paramzzps.zziD(this.zzaUM);
    }
    if (this.zzaUN != 0) {
      paramzzps.zziE(this.zzaUN);
    }
    if (!TextUtils.isEmpty(this.zzaaL)) {
      paramzzps.setLanguage(this.zzaaL);
    }
  }
  
  public void zziA(int paramInt)
  {
    this.zzaUL = paramInt;
  }
  
  public void zziB(int paramInt)
  {
    this.zzDC = paramInt;
  }
  
  public void zziC(int paramInt)
  {
    this.zzDD = paramInt;
  }
  
  public void zziD(int paramInt)
  {
    this.zzaUM = paramInt;
  }
  
  public void zziE(int paramInt)
  {
    this.zzaUN = paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzps.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */