package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzpv
  extends zze<zzpv>
{
  public boolean zzaUP;
  public String zzaxl;
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public void setDescription(String paramString)
  {
    this.zzaxl = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("description", this.zzaxl);
    localHashMap.put("fatal", Boolean.valueOf(this.zzaUP));
    return zzF(localHashMap);
  }
  
  public boolean zzBa()
  {
    return this.zzaUP;
  }
  
  public void zza(zzpv paramzzpv)
  {
    if (!TextUtils.isEmpty(this.zzaxl)) {
      paramzzpv.setDescription(this.zzaxl);
    }
    if (this.zzaUP) {
      paramzzpv.zzao(this.zzaUP);
    }
  }
  
  public void zzao(boolean paramBoolean)
  {
    this.zzaUP = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */