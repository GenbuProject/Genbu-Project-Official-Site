package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzpu
  extends zze<zzpu>
{
  private String mCategory;
  private String zzSU;
  private long zzaDV;
  private String zzaUO;
  
  public String getAction()
  {
    return this.zzSU;
  }
  
  public String getLabel()
  {
    return this.zzaUO;
  }
  
  public long getValue()
  {
    return this.zzaDV;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("category", this.mCategory);
    localHashMap.put("action", this.zzSU);
    localHashMap.put("label", this.zzaUO);
    localHashMap.put("value", Long.valueOf(this.zzaDV));
    return zzF(localHashMap);
  }
  
  public String zzAZ()
  {
    return this.mCategory;
  }
  
  public void zzN(long paramLong)
  {
    this.zzaDV = paramLong;
  }
  
  public void zza(zzpu paramzzpu)
  {
    if (!TextUtils.isEmpty(this.mCategory)) {
      paramzzpu.zzeE(this.mCategory);
    }
    if (!TextUtils.isEmpty(this.zzSU)) {
      paramzzpu.zzeF(this.zzSU);
    }
    if (!TextUtils.isEmpty(this.zzaUO)) {
      paramzzpu.zzeG(this.zzaUO);
    }
    if (this.zzaDV != 0L) {
      paramzzpu.zzN(this.zzaDV);
    }
  }
  
  public void zzeE(String paramString)
  {
    this.mCategory = paramString;
  }
  
  public void zzeF(String paramString)
  {
    this.zzSU = paramString;
  }
  
  public void zzeG(String paramString)
  {
    this.zzaUO = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */