package com.google.android.gms.internal;

import java.util.Map;
import java.util.concurrent.Future;

@zzhb
public final class zzhf
{
  private String zzEY;
  private String zzJh;
  private zzjd<zzhi> zzJi = new zzjd();
  zzeg.zzd zzJj;
  public final zzdf zzJk = new zzdf()
  {
    public void zza(zzjp arg1, Map<String, String> paramAnonymousMap)
    {
      synchronized (zzhf.zza(zzhf.this))
      {
        if (zzhf.zzb(zzhf.this).isDone()) {
          return;
        }
        if (!zzhf.zzc(zzhf.this).equals(paramAnonymousMap.get("request_id"))) {
          return;
        }
      }
      paramAnonymousMap = new zzhi(1, paramAnonymousMap);
      zzin.zzaK("Invalid " + paramAnonymousMap.getType() + " request error: " + paramAnonymousMap.zzgE());
      zzhf.zzb(zzhf.this).zzg(paramAnonymousMap);
    }
  };
  public final zzdf zzJl = new zzdf()
  {
    public void zza(zzjp paramAnonymouszzjp, Map<String, String> paramAnonymousMap)
    {
      zzhi localzzhi;
      synchronized (zzhf.zza(zzhf.this))
      {
        if (zzhf.zzb(zzhf.this).isDone()) {
          return;
        }
        localzzhi = new zzhi(-2, paramAnonymousMap);
        if (!zzhf.zzc(zzhf.this).equals(localzzhi.getRequestId()))
        {
          zzin.zzaK(localzzhi.getRequestId() + " ==== " + zzhf.zzc(zzhf.this));
          return;
        }
      }
      String str = localzzhi.getUrl();
      if (str == null)
      {
        zzin.zzaK("URL missing in loadAdUrl GMSG.");
        return;
      }
      if (str.contains("%40mediation_adapters%40"))
      {
        paramAnonymouszzjp = str.replaceAll("%40mediation_adapters%40", zzil.zza(paramAnonymouszzjp.getContext(), (String)paramAnonymousMap.get("check_adapters"), zzhf.zzd(zzhf.this)));
        localzzhi.setUrl(paramAnonymouszzjp);
        zzin.v("Ad request URL modified to " + paramAnonymouszzjp);
      }
      zzhf.zzb(zzhf.this).zzg(localzzhi);
    }
  };
  zzjp zzpD;
  private final Object zzpV = new Object();
  
  public zzhf(String paramString1, String paramString2)
  {
    this.zzJh = paramString2;
    this.zzEY = paramString1;
  }
  
  public void zzb(zzeg.zzd paramzzd)
  {
    this.zzJj = paramzzd;
  }
  
  public zzeg.zzd zzgB()
  {
    return this.zzJj;
  }
  
  public Future<zzhi> zzgC()
  {
    return this.zzJi;
  }
  
  public void zzgD()
  {
    if (this.zzpD != null)
    {
      this.zzpD.destroy();
      this.zzpD = null;
    }
  }
  
  public void zzh(zzjp paramzzjp)
  {
    this.zzpD = paramzzjp;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzhf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */