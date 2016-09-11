package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;

public final class zzke
  extends zze<zzke>
{
  private String zzPN;
  private String zzPO;
  private String zzPP;
  private boolean zzPQ;
  private String zzPR;
  private boolean zzPS;
  private double zzPT;
  private String zzrG;
  
  public String getClientId()
  {
    return this.zzPO;
  }
  
  public String getUserId()
  {
    return this.zzrG;
  }
  
  public void setClientId(String paramString)
  {
    this.zzPO = paramString;
  }
  
  public void setSampleRate(double paramDouble)
  {
    if ((paramDouble >= 0.0D) && (paramDouble <= 100.0D)) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zzb(bool, "Sample rate must be between 0% and 100%");
      this.zzPT = paramDouble;
      return;
    }
  }
  
  public void setUserId(String paramString)
  {
    this.zzrG = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("hitType", this.zzPN);
    localHashMap.put("clientId", this.zzPO);
    localHashMap.put("userId", this.zzrG);
    localHashMap.put("androidAdId", this.zzPP);
    localHashMap.put("AdTargetingEnabled", Boolean.valueOf(this.zzPQ));
    localHashMap.put("sessionControl", this.zzPR);
    localHashMap.put("nonInteraction", Boolean.valueOf(this.zzPS));
    localHashMap.put("sampleRate", Double.valueOf(this.zzPT));
    return zzF(localHashMap);
  }
  
  public void zzH(boolean paramBoolean)
  {
    this.zzPQ = paramBoolean;
  }
  
  public void zzI(boolean paramBoolean)
  {
    this.zzPS = paramBoolean;
  }
  
  public void zza(zzke paramzzke)
  {
    if (!TextUtils.isEmpty(this.zzPN)) {
      paramzzke.zzaX(this.zzPN);
    }
    if (!TextUtils.isEmpty(this.zzPO)) {
      paramzzke.setClientId(this.zzPO);
    }
    if (!TextUtils.isEmpty(this.zzrG)) {
      paramzzke.setUserId(this.zzrG);
    }
    if (!TextUtils.isEmpty(this.zzPP)) {
      paramzzke.zzaY(this.zzPP);
    }
    if (this.zzPQ) {
      paramzzke.zzH(true);
    }
    if (!TextUtils.isEmpty(this.zzPR)) {
      paramzzke.zzaZ(this.zzPR);
    }
    if (this.zzPS) {
      paramzzke.zzI(this.zzPS);
    }
    if (this.zzPT != 0.0D) {
      paramzzke.setSampleRate(this.zzPT);
    }
  }
  
  public void zzaX(String paramString)
  {
    this.zzPN = paramString;
  }
  
  public void zzaY(String paramString)
  {
    this.zzPP = paramString;
  }
  
  public void zzaZ(String paramString)
  {
    this.zzPR = paramString;
  }
  
  public String zziS()
  {
    return this.zzPN;
  }
  
  public String zziT()
  {
    return this.zzPP;
  }
  
  public boolean zziU()
  {
    return this.zzPQ;
  }
  
  public String zziV()
  {
    return this.zzPR;
  }
  
  public boolean zziW()
  {
    return this.zzPS;
  }
  
  public double zziX()
  {
    return this.zzPT;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzke.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */