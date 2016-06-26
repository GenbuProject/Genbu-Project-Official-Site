package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Application;
import android.content.Context;
import android.content.res.Resources;
import android.os.Bundle;
import android.security.NetworkSecurityPolicy;
import com.google.android.gms.ads.internal.purchase.zzi;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.zze;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.Set;
import java.util.concurrent.Future;

@zzhb
public class zzih
  implements zzip.zzb
{
  private Context mContext;
  private boolean zzJA = true;
  private boolean zzJz = true;
  private final LinkedList<Thread> zzLA = new LinkedList();
  private Boolean zzLB = null;
  private boolean zzLC = false;
  private boolean zzLD = false;
  private final String zzLq;
  private final zzii zzLr;
  private BigInteger zzLs = BigInteger.ONE;
  private final HashSet<zzig> zzLt = new HashSet();
  private final HashMap<String, zzik> zzLu = new HashMap();
  private boolean zzLv = false;
  private int zzLw = 0;
  private zzbv zzLx = null;
  private zzbf zzLy = null;
  private String zzLz;
  private VersionInfoParcel zzpT;
  private final Object zzpV = new Object();
  private zzax zzpl;
  private boolean zzqA = false;
  private zzbe zzsZ = null;
  private zzbd zzta = null;
  private final zzha zztb = null;
  private String zzzN;
  
  public zzih(zzir paramzzir)
  {
    this.zzLq = paramzzir.zzhs();
    this.zzLr = new zzii(this.zzLq);
  }
  
  public String getSessionId()
  {
    return this.zzLq;
  }
  
  public void zzB(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      if (this.zzJA != paramBoolean) {
        zzip.zzb(this.mContext, paramBoolean);
      }
      this.zzJA = paramBoolean;
      zzbf localzzbf = zzG(this.mContext);
      if ((localzzbf != null) && (!localzzbf.isAlive()))
      {
        zzin.zzaJ("start fetching content...");
        localzzbf.zzcG();
      }
      return;
    }
  }
  
  public void zzC(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      this.zzLC = paramBoolean;
      return;
    }
  }
  
  public zzbf zzG(Context paramContext)
  {
    if ((!((Boolean)zzbt.zzwj.get()).booleanValue()) || (!zzne.zzsg()) || (zzgY())) {
      return null;
    }
    synchronized (this.zzpV)
    {
      if (this.zzsZ == null)
      {
        if (!(paramContext instanceof Activity)) {
          return null;
        }
        this.zzsZ = new zzbe((Application)paramContext.getApplicationContext(), (Activity)paramContext);
      }
      if (this.zzta == null) {
        this.zzta = new zzbd();
      }
      if (this.zzLy == null) {
        this.zzLy = new zzbf(this.zzsZ, this.zzta, new zzha(this.mContext, this.zzpT, null, null));
      }
      this.zzLy.zzcG();
      paramContext = this.zzLy;
      return paramContext;
    }
  }
  
  public Bundle zza(Context paramContext, zzij paramzzij, String paramString)
  {
    Bundle localBundle;
    synchronized (this.zzpV)
    {
      localBundle = new Bundle();
      localBundle.putBundle("app", this.zzLr.zzc(paramContext, paramString));
      paramContext = new Bundle();
      paramString = this.zzLu.keySet().iterator();
      if (paramString.hasNext())
      {
        String str = (String)paramString.next();
        paramContext.putBundle(str, ((zzik)this.zzLu.get(str)).toBundle());
      }
    }
    localBundle.putBundle("slots", paramContext);
    paramContext = new ArrayList();
    paramString = this.zzLt.iterator();
    while (paramString.hasNext()) {
      paramContext.add(((zzig)paramString.next()).toBundle());
    }
    localBundle.putParcelableArrayList("ads", paramContext);
    paramzzij.zza(this.zzLt);
    this.zzLt.clear();
    return localBundle;
  }
  
  public Future zza(Context paramContext, boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      if (paramBoolean != this.zzJz)
      {
        this.zzJz = paramBoolean;
        paramContext = zzip.zza(paramContext, paramBoolean);
        return paramContext;
      }
      return null;
    }
  }
  
  public void zza(zzig paramzzig)
  {
    synchronized (this.zzpV)
    {
      this.zzLt.add(paramzzig);
      return;
    }
  }
  
  public void zza(String paramString, zzik paramzzik)
  {
    synchronized (this.zzpV)
    {
      this.zzLu.put(paramString, paramzzik);
      return;
    }
  }
  
  public void zza(Thread paramThread)
  {
    zzha.zza(this.mContext, paramThread, this.zzpT);
  }
  
  public Future zzaA(String paramString)
  {
    Object localObject = this.zzpV;
    if (paramString != null) {}
    try
    {
      if (!paramString.equals(this.zzLz))
      {
        this.zzLz = paramString;
        paramString = zzip.zzd(this.mContext, paramString);
        return paramString;
      }
      return null;
    }
    finally {}
  }
  
  @TargetApi(23)
  public void zzb(Context paramContext, VersionInfoParcel paramVersionInfoParcel)
  {
    synchronized (this.zzpV)
    {
      if (!this.zzqA)
      {
        this.mContext = paramContext.getApplicationContext();
        this.zzpT = paramVersionInfoParcel;
        zzip.zza(paramContext, this);
        zzip.zzb(paramContext, this);
        zzip.zzc(paramContext, this);
        zzip.zzd(paramContext, this);
        zza(Thread.currentThread());
        this.zzzN = zzr.zzbC().zze(paramContext, paramVersionInfoParcel.afmaVersion);
        if ((zzne.zzsn()) && (!NetworkSecurityPolicy.getInstance().isCleartextTrafficPermitted())) {
          this.zzLD = true;
        }
        this.zzpl = new zzax(paramContext.getApplicationContext(), this.zzpT, new zzeg(paramContext.getApplicationContext(), this.zzpT, (String)zzbt.zzvB.get()));
        zzhk();
        zzr.zzbM().zzz(this.mContext);
        this.zzqA = true;
      }
      return;
    }
  }
  
  public void zzb(Boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      this.zzLB = paramBoolean;
      return;
    }
  }
  
  public void zzb(Throwable paramThrowable, boolean paramBoolean)
  {
    new zzha(this.mContext, this.zzpT, null, null).zza(paramThrowable, paramBoolean);
  }
  
  public void zzb(HashSet<zzig> paramHashSet)
  {
    synchronized (this.zzpV)
    {
      this.zzLt.addAll(paramHashSet);
      return;
    }
  }
  
  public String zzd(int paramInt, String paramString)
  {
    if (this.zzpT.zzNb) {}
    for (Resources localResources = this.mContext.getResources(); localResources == null; localResources = zze.getRemoteResource(this.mContext)) {
      return paramString;
    }
    return localResources.getString(paramInt);
  }
  
  public void zze(Bundle paramBundle)
  {
    synchronized (this.zzpV)
    {
      if (paramBundle.containsKey("use_https")) {}
      for (boolean bool = paramBundle.getBoolean("use_https");; bool = this.zzJz)
      {
        this.zzJz = bool;
        if (!paramBundle.containsKey("webview_cache_version")) {
          break;
        }
        i = paramBundle.getInt("webview_cache_version");
        this.zzLw = i;
        if (paramBundle.containsKey("content_url_opted_out")) {
          zzB(paramBundle.getBoolean("content_url_opted_out"));
        }
        if (paramBundle.containsKey("content_url_hashes")) {
          this.zzLz = paramBundle.getString("content_url_hashes");
        }
        return;
      }
      int i = this.zzLw;
    }
  }
  
  public boolean zzgY()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzJA;
      return bool;
    }
  }
  
  public String zzgZ()
  {
    synchronized (this.zzpV)
    {
      String str = this.zzLs.toString();
      this.zzLs = this.zzLs.add(BigInteger.ONE);
      return str;
    }
  }
  
  public zzii zzha()
  {
    synchronized (this.zzpV)
    {
      zzii localzzii = this.zzLr;
      return localzzii;
    }
  }
  
  public zzbv zzhb()
  {
    synchronized (this.zzpV)
    {
      zzbv localzzbv = this.zzLx;
      return localzzbv;
    }
  }
  
  public boolean zzhc()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzLv;
      this.zzLv = true;
      return bool;
    }
  }
  
  public boolean zzhd()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (!this.zzJz)
        {
          if (!this.zzLD) {
            break label38;
          }
          break label33;
          return bool;
        }
      }
      label33:
      boolean bool = true;
      continue;
      label38:
      bool = false;
    }
  }
  
  public String zzhe()
  {
    synchronized (this.zzpV)
    {
      String str = this.zzzN;
      return str;
    }
  }
  
  public String zzhf()
  {
    synchronized (this.zzpV)
    {
      String str = this.zzLz;
      return str;
    }
  }
  
  public Boolean zzhg()
  {
    synchronized (this.zzpV)
    {
      Boolean localBoolean = this.zzLB;
      return localBoolean;
    }
  }
  
  public zzax zzhh()
  {
    return this.zzpl;
  }
  
  public boolean zzhi()
  {
    synchronized (this.zzpV)
    {
      if (this.zzLw < ((Integer)zzbt.zzwA.get()).intValue())
      {
        this.zzLw = ((Integer)zzbt.zzwA.get()).intValue();
        zzip.zza(this.mContext, this.zzLw);
        return true;
      }
      return false;
    }
  }
  
  public boolean zzhj()
  {
    synchronized (this.zzpV)
    {
      boolean bool = this.zzLC;
      return bool;
    }
  }
  
  void zzhk()
  {
    zzbu localzzbu = new zzbu(this.mContext, this.zzpT.afmaVersion);
    try
    {
      this.zzLx = zzr.zzbH().zza(localzzbu);
      return;
    }
    catch (IllegalArgumentException localIllegalArgumentException)
    {
      zzin.zzd("Cannot initialize CSI reporter.", localIllegalArgumentException);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzih.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */