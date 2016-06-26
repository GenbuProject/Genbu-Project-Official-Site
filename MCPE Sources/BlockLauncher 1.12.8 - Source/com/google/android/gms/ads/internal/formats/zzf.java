package com.google.android.gms.ads.internal.formats;

import android.support.v4.util.SimpleArrayMap;
import com.google.android.gms.internal.zzch;
import com.google.android.gms.internal.zzcp.zza;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import java.util.Arrays;
import java.util.List;

@zzhb
public class zzf
  extends zzcp.zza
  implements zzh.zza
{
  private final Object zzpV = new Object();
  private final zza zzye;
  private zzh zzyf;
  private final String zzyi;
  private final SimpleArrayMap<String, zzc> zzyj;
  private final SimpleArrayMap<String, String> zzyk;
  
  public zzf(String paramString, SimpleArrayMap<String, zzc> paramSimpleArrayMap, SimpleArrayMap<String, String> paramSimpleArrayMap1, zza paramzza)
  {
    this.zzyi = paramString;
    this.zzyj = paramSimpleArrayMap;
    this.zzyk = paramSimpleArrayMap1;
    this.zzye = paramzza;
  }
  
  public List<String> getAvailableAssetNames()
  {
    int n = 0;
    String[] arrayOfString = new String[this.zzyj.size() + this.zzyk.size()];
    int j = 0;
    int i = 0;
    int k;
    int m;
    for (;;)
    {
      k = n;
      m = i;
      if (j >= this.zzyj.size()) {
        break;
      }
      arrayOfString[i] = ((String)this.zzyj.keyAt(j));
      i += 1;
      j += 1;
    }
    while (k < this.zzyk.size())
    {
      arrayOfString[m] = ((String)this.zzyk.keyAt(k));
      k += 1;
      m += 1;
    }
    return Arrays.asList(arrayOfString);
  }
  
  public String getCustomTemplateId()
  {
    return this.zzyi;
  }
  
  public void performClick(String paramString)
  {
    synchronized (this.zzpV)
    {
      if (this.zzyf == null)
      {
        zzin.e("Attempt to call performClick before ad initialized.");
        return;
      }
      this.zzyf.zza(paramString, null, null, null);
      return;
    }
  }
  
  public void recordImpression()
  {
    synchronized (this.zzpV)
    {
      if (this.zzyf == null)
      {
        zzin.e("Attempt to perform recordImpression before ad initialized.");
        return;
      }
      this.zzyf.recordImpression();
      return;
    }
  }
  
  public String zzO(String paramString)
  {
    return (String)this.zzyk.get(paramString);
  }
  
  public zzch zzP(String paramString)
  {
    return (zzch)this.zzyj.get(paramString);
  }
  
  public void zzb(zzh paramzzh)
  {
    synchronized (this.zzpV)
    {
      this.zzyf = paramzzh;
      return;
    }
  }
  
  public String zzdM()
  {
    return "3";
  }
  
  public zza zzdN()
  {
    return this.zzye;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\ads\internal\formats\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */