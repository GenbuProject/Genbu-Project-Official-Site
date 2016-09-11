package com.google.android.gms.internal;

import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.measurement.zze;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public final class zzpw
  extends zze<zzpw>
{
  private String zzaUQ;
  private int zzaUR;
  private int zzaUS;
  private String zzaUT;
  private String zzaUU;
  private boolean zzaUV;
  private boolean zzaUW;
  private boolean zzaUX;
  
  public zzpw()
  {
    this(false);
  }
  
  public zzpw(boolean paramBoolean)
  {
    this(paramBoolean, zzBb());
  }
  
  public zzpw(boolean paramBoolean, int paramInt)
  {
    zzx.zzbV(paramInt);
    this.zzaUR = paramInt;
    this.zzaUW = paramBoolean;
  }
  
  static int zzBb()
  {
    UUID localUUID = UUID.randomUUID();
    int i = (int)(localUUID.getLeastSignificantBits() & 0x7FFFFFFF);
    if (i != 0) {}
    int j;
    do
    {
      return i;
      j = (int)(localUUID.getMostSignificantBits() & 0x7FFFFFFF);
      i = j;
    } while (j != 0);
    Log.e("GAv4", "UUID.randomUUID() returned 0.");
    return Integer.MAX_VALUE;
  }
  
  private void zzBf()
  {
    if (this.zzaUX) {
      throw new IllegalStateException("ScreenViewInfo is immutable");
    }
  }
  
  public void setScreenName(String paramString)
  {
    zzBf();
    this.zzaUQ = paramString;
  }
  
  public String toString()
  {
    HashMap localHashMap = new HashMap();
    localHashMap.put("screenName", this.zzaUQ);
    localHashMap.put("interstitial", Boolean.valueOf(this.zzaUV));
    localHashMap.put("automatic", Boolean.valueOf(this.zzaUW));
    localHashMap.put("screenId", Integer.valueOf(this.zzaUR));
    localHashMap.put("referrerScreenId", Integer.valueOf(this.zzaUS));
    localHashMap.put("referrerScreenName", this.zzaUT);
    localHashMap.put("referrerUri", this.zzaUU);
    return zzF(localHashMap);
  }
  
  public String zzBc()
  {
    return this.zzaUQ;
  }
  
  public int zzBd()
  {
    return this.zzaUR;
  }
  
  public String zzBe()
  {
    return this.zzaUU;
  }
  
  public void zza(zzpw paramzzpw)
  {
    if (!TextUtils.isEmpty(this.zzaUQ)) {
      paramzzpw.setScreenName(this.zzaUQ);
    }
    if (this.zzaUR != 0) {
      paramzzpw.zziF(this.zzaUR);
    }
    if (this.zzaUS != 0) {
      paramzzpw.zziG(this.zzaUS);
    }
    if (!TextUtils.isEmpty(this.zzaUT)) {
      paramzzpw.zzeH(this.zzaUT);
    }
    if (!TextUtils.isEmpty(this.zzaUU)) {
      paramzzpw.zzeI(this.zzaUU);
    }
    if (this.zzaUV) {
      paramzzpw.zzaq(this.zzaUV);
    }
    if (this.zzaUW) {
      paramzzpw.zzap(this.zzaUW);
    }
  }
  
  public void zzap(boolean paramBoolean)
  {
    zzBf();
    this.zzaUW = paramBoolean;
  }
  
  public void zzaq(boolean paramBoolean)
  {
    zzBf();
    this.zzaUV = paramBoolean;
  }
  
  public void zzeH(String paramString)
  {
    zzBf();
    this.zzaUT = paramString;
  }
  
  public void zzeI(String paramString)
  {
    zzBf();
    if (TextUtils.isEmpty(paramString))
    {
      this.zzaUU = null;
      return;
    }
    this.zzaUU = paramString;
  }
  
  public void zziF(int paramInt)
  {
    zzBf();
    this.zzaUR = paramInt;
  }
  
  public void zziG(int paramInt)
  {
    zzBf();
    this.zzaUS = paramInt;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzpw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */