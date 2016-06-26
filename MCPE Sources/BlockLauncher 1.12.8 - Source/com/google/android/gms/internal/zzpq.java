package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzpq
  extends zze<zzpq>
{
  private String zzSE;
  private String zzSF;
  private String zzaUE;
  private String zzaUa;
  
  public void setAppId(String paramString)
  {
    this.zzaUa = paramString;
  }
  
  public void setAppInstallerId(String paramString)
  {
    this.zzaUE = paramString;
  }
  
  public void setAppName(String paramString)
  {
    this.zzSE = paramString;
  }
  
  public void setAppVersion(String paramString)
  {
    this.zzSF = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("appName", this.zzSE);
    localHashMap.put("appVersion", this.zzSF);
    localHashMap.put("appId", this.zzaUa);
    localHashMap.put("appInstallerId", this.zzaUE);
    return zzF(localHashMap);
  }
  
  public String zzAJ()
  {
    return this.zzaUE;
  }
  
  public void zza(zzpq paramzzpq)
  {
    if (!TextUtils.isEmpty(this.zzSE)) {
      paramzzpq.setAppName(this.zzSE);
    }
    if (!TextUtils.isEmpty(this.zzSF)) {
      paramzzpq.setAppVersion(this.zzSF);
    }
    if (!TextUtils.isEmpty(this.zzaUa)) {
      paramzzpq.setAppId(this.zzaUa);
    }
    if (!TextUtils.isEmpty(this.zzaUE)) {
      paramzzpq.setAppInstallerId(this.zzaUE);
    }
  }
  
  public String zzlg()
  {
    return this.zzSE;
  }
  
  public String zzli()
  {
    return this.zzSF;
  }
  
  public String zzwK()
  {
    return this.zzaUa;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzpq.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */