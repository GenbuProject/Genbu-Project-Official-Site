package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzpy
  extends zze<zzpy>
{
  public String mCategory;
  public String zzaUO;
  public String zzaVa;
  public long zzaVb;
  
  public String getLabel()
  {
    return this.zzaUO;
  }
  
  public long getTimeInMillis()
  {
    return this.zzaVb;
  }
  
  public void setTimeInMillis(long paramLong)
  {
    this.zzaVb = paramLong;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("variableName", this.zzaVa);
    localHashMap.put("timeInMillis", Long.valueOf(this.zzaVb));
    localHashMap.put("category", this.mCategory);
    localHashMap.put("label", this.zzaUO);
    return zzF(localHashMap);
  }
  
  public String zzAZ()
  {
    return this.mCategory;
  }
  
  public String zzBh()
  {
    return this.zzaVa;
  }
  
  public void zza(zzpy paramzzpy)
  {
    if (!TextUtils.isEmpty(this.zzaVa)) {
      paramzzpy.zzeL(this.zzaVa);
    }
    if (this.zzaVb != 0L) {
      paramzzpy.setTimeInMillis(this.zzaVb);
    }
    if (!TextUtils.isEmpty(this.mCategory)) {
      paramzzpy.zzeE(this.mCategory);
    }
    if (!TextUtils.isEmpty(this.zzaUO)) {
      paramzzpy.zzeG(this.zzaUO);
    }
  }
  
  public void zzeE(String paramString)
  {
    this.mCategory = paramString;
  }
  
  public void zzeG(String paramString)
  {
    this.zzaUO = paramString;
  }
  
  public void zzeL(String paramString)
  {
    this.zzaVa = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpy.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */