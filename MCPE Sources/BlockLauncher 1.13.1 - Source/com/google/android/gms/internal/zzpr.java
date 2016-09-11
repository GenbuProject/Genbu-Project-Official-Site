package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzpr
  extends zze<zzpr>
{
  private String mName;
  private String zzaPI;
  private String zzaUF;
  private String zzaUG;
  private String zzaUH;
  private String zzaUI;
  private String zzaUJ;
  private String zzaUK;
  private String zzxG;
  private String zzyv;
  
  public String getContent()
  {
    return this.zzxG;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getSource()
  {
    return this.zzaPI;
  }
  
  public void setName(String paramString)
  {
    this.mName = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("name", this.mName);
    localHashMap.put("source", this.zzaPI);
    localHashMap.put("medium", this.zzaUF);
    localHashMap.put("keyword", this.zzaUG);
    localHashMap.put("content", this.zzxG);
    localHashMap.put("id", this.zzyv);
    localHashMap.put("adNetworkId", this.zzaUH);
    localHashMap.put("gclid", this.zzaUI);
    localHashMap.put("dclid", this.zzaUJ);
    localHashMap.put("aclid", this.zzaUK);
    return zzF(localHashMap);
  }
  
  public String zzAK()
  {
    return this.zzaUF;
  }
  
  public String zzAL()
  {
    return this.zzaUG;
  }
  
  public String zzAM()
  {
    return this.zzaUH;
  }
  
  public String zzAN()
  {
    return this.zzaUI;
  }
  
  public String zzAO()
  {
    return this.zzaUJ;
  }
  
  public String zzAP()
  {
    return this.zzaUK;
  }
  
  public void zza(zzpr paramzzpr)
  {
    if (!TextUtils.isEmpty(this.mName)) {
      paramzzpr.setName(this.mName);
    }
    if (!TextUtils.isEmpty(this.zzaPI)) {
      paramzzpr.zzev(this.zzaPI);
    }
    if (!TextUtils.isEmpty(this.zzaUF)) {
      paramzzpr.zzew(this.zzaUF);
    }
    if (!TextUtils.isEmpty(this.zzaUG)) {
      paramzzpr.zzex(this.zzaUG);
    }
    if (!TextUtils.isEmpty(this.zzxG)) {
      paramzzpr.zzey(this.zzxG);
    }
    if (!TextUtils.isEmpty(this.zzyv)) {
      paramzzpr.zzez(this.zzyv);
    }
    if (!TextUtils.isEmpty(this.zzaUH)) {
      paramzzpr.zzeA(this.zzaUH);
    }
    if (!TextUtils.isEmpty(this.zzaUI)) {
      paramzzpr.zzeB(this.zzaUI);
    }
    if (!TextUtils.isEmpty(this.zzaUJ)) {
      paramzzpr.zzeC(this.zzaUJ);
    }
    if (!TextUtils.isEmpty(this.zzaUK)) {
      paramzzpr.zzeD(this.zzaUK);
    }
  }
  
  public void zzeA(String paramString)
  {
    this.zzaUH = paramString;
  }
  
  public void zzeB(String paramString)
  {
    this.zzaUI = paramString;
  }
  
  public void zzeC(String paramString)
  {
    this.zzaUJ = paramString;
  }
  
  public void zzeD(String paramString)
  {
    this.zzaUK = paramString;
  }
  
  public void zzev(String paramString)
  {
    this.zzaPI = paramString;
  }
  
  public void zzew(String paramString)
  {
    this.zzaUF = paramString;
  }
  
  public void zzex(String paramString)
  {
    this.zzaUG = paramString;
  }
  
  public void zzey(String paramString)
  {
    this.zzxG = paramString;
  }
  
  public void zzez(String paramString)
  {
    this.zzyv = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */