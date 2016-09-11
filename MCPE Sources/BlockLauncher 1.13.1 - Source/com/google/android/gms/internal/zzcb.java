package com.google.android.gms.internal;

import android.text.TextUtils;
import com.google.android.gms.ads.internal.zzr;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

@zzhb
public class zzcb
{
  private final Object zzpV = new Object();
  private final Map<String, String> zzxA = new LinkedHashMap();
  private String zzxB;
  private zzbz zzxC;
  private zzcb zzxD;
  boolean zzxi;
  private final List<zzbz> zzxz = new LinkedList();
  
  public zzcb(boolean paramBoolean, String paramString1, String paramString2)
  {
    this.zzxi = paramBoolean;
    this.zzxA.put("action", paramString1);
    this.zzxA.put("ad_format", paramString2);
  }
  
  public void zzN(String paramString)
  {
    if (!this.zzxi) {
      return;
    }
    synchronized (this.zzpV)
    {
      this.zzxB = paramString;
      return;
    }
  }
  
  public boolean zza(zzbz paramzzbz, long paramLong, String... paramVarArgs)
  {
    synchronized (this.zzpV)
    {
      int j = paramVarArgs.length;
      int i = 0;
      while (i < j)
      {
        zzbz localzzbz = new zzbz(paramLong, paramVarArgs[i], paramzzbz);
        this.zzxz.add(localzzbz);
        i += 1;
      }
      return true;
    }
  }
  
  public boolean zza(zzbz paramzzbz, String... paramVarArgs)
  {
    if ((!this.zzxi) || (paramzzbz == null)) {
      return false;
    }
    return zza(paramzzbz, zzr.zzbG().elapsedRealtime(), paramVarArgs);
  }
  
  public zzbz zzb(long paramLong)
  {
    if (!this.zzxi) {
      return null;
    }
    return new zzbz(paramLong, null, null);
  }
  
  public void zzc(zzcb paramzzcb)
  {
    synchronized (this.zzpV)
    {
      this.zzxD = paramzzcb;
      return;
    }
  }
  
  public void zzc(String paramString1, String paramString2)
  {
    if ((!this.zzxi) || (TextUtils.isEmpty(paramString2))) {}
    zzbv localzzbv;
    do
    {
      return;
      localzzbv = zzr.zzbF().zzhb();
    } while (localzzbv == null);
    synchronized (this.zzpV)
    {
      localzzbv.zzL(paramString1).zza(this.zzxA, paramString1, paramString2);
      return;
    }
  }
  
  public zzbz zzdB()
  {
    return zzb(zzr.zzbG().elapsedRealtime());
  }
  
  public void zzdC()
  {
    synchronized (this.zzpV)
    {
      this.zzxC = zzdB();
      return;
    }
  }
  
  public String zzdD()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    synchronized (this.zzpV)
    {
      Iterator localIterator = this.zzxz.iterator();
      while (localIterator.hasNext())
      {
        zzbz localzzbz = (zzbz)localIterator.next();
        long l1 = localzzbz.getTime();
        String str2 = localzzbz.zzdy();
        localzzbz = localzzbz.zzdz();
        if ((localzzbz != null) && (l1 > 0L))
        {
          long l2 = localzzbz.getTime();
          localStringBuilder.append(str2).append('.').append(l1 - l2).append(',');
        }
      }
    }
    this.zzxz.clear();
    if (!TextUtils.isEmpty(this.zzxB)) {
      ((StringBuilder)localObject2).append(this.zzxB);
    }
    for (;;)
    {
      String str1 = ((StringBuilder)localObject2).toString();
      return str1;
      if (str1.length() > 0) {
        str1.setLength(str1.length() - 1);
      }
    }
  }
  
  public zzbz zzdE()
  {
    synchronized (this.zzpV)
    {
      zzbz localzzbz = this.zzxC;
      return localzzbz;
    }
  }
  
  Map<String, String> zzn()
  {
    synchronized (this.zzpV)
    {
      Object localObject2 = zzr.zzbF().zzhb();
      if ((localObject2 == null) || (this.zzxD == null))
      {
        localObject2 = this.zzxA;
        return (Map<String, String>)localObject2;
      }
      localObject2 = ((zzbv)localObject2).zza(this.zzxA, this.zzxD.zzn());
      return (Map<String, String>)localObject2;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzcb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */