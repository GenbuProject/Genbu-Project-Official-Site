package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzpx
  extends zze<zzpx>
{
  public String zzSU;
  public String zzaUY;
  public String zzaUZ;
  
  public String getAction()
  {
    return this.zzSU;
  }
  
  public String getTarget()
  {
    return this.zzaUZ;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("network", this.zzaUY);
    localHashMap.put("action", this.zzSU);
    localHashMap.put("target", this.zzaUZ);
    return zzF(localHashMap);
  }
  
  public String zzBg()
  {
    return this.zzaUY;
  }
  
  public void zza(zzpx paramzzpx)
  {
    if (!TextUtils.isEmpty(this.zzaUY)) {
      paramzzpx.zzeJ(this.zzaUY);
    }
    if (!TextUtils.isEmpty(this.zzSU)) {
      paramzzpx.zzeF(this.zzSU);
    }
    if (!TextUtils.isEmpty(this.zzaUZ)) {
      paramzzpx.zzeK(this.zzaUZ);
    }
  }
  
  public void zzeF(String paramString)
  {
    this.zzSU = paramString;
  }
  
  public void zzeJ(String paramString)
  {
    this.zzaUY = paramString;
  }
  
  public void zzeK(String paramString)
  {
    this.zzaUZ = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */