package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.location.Location;
import android.os.Bundle;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.mediation.MediationAdapter;
import com.google.android.gms.ads.mediation.NetworkExtras;
import com.google.android.gms.ads.mediation.admob.AdMobExtras;
import com.google.android.gms.ads.mediation.customevent.CustomEvent;
import com.google.android.gms.ads.search.SearchAdRequest;
import com.google.android.gms.internal.zzhb;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

@zzhb
public final class zzaa
{
  public static final String DEVICE_ID_EMULATOR = zzn.zzcS().zzaH("emulator");
  private final Date zzbf;
  private final Set<String> zzbh;
  private final Location zzbj;
  private final boolean zzpE;
  private final int zztT;
  private final int zztW;
  private final String zztX;
  private final String zztZ;
  private final Bundle zzuA;
  private final Map<Class<? extends NetworkExtras>, NetworkExtras> zzuB;
  private final SearchAdRequest zzuC;
  private final Set<String> zzuD;
  private final Set<String> zzuE;
  private final Bundle zzub;
  private final String zzud;
  private final boolean zzuf;
  
  public zzaa(zza paramzza)
  {
    this(paramzza, null);
  }
  
  public zzaa(zza paramzza, SearchAdRequest paramSearchAdRequest)
  {
    this.zzbf = zza.zza(paramzza);
    this.zztZ = zza.zzb(paramzza);
    this.zztT = zza.zzc(paramzza);
    this.zzbh = Collections.unmodifiableSet(zza.zzd(paramzza));
    this.zzbj = zza.zze(paramzza);
    this.zzpE = zza.zzf(paramzza);
    this.zzuA = zza.zzg(paramzza);
    this.zzuB = Collections.unmodifiableMap(zza.zzh(paramzza));
    this.zztX = zza.zzi(paramzza);
    this.zzud = zza.zzj(paramzza);
    this.zzuC = paramSearchAdRequest;
    this.zztW = zza.zzk(paramzza);
    this.zzuD = Collections.unmodifiableSet(zza.zzl(paramzza));
    this.zzub = zza.zzm(paramzza);
    this.zzuE = Collections.unmodifiableSet(zza.zzn(paramzza));
    this.zzuf = zza.zzo(paramzza);
  }
  
  public Date getBirthday()
  {
    return this.zzbf;
  }
  
  public String getContentUrl()
  {
    return this.zztZ;
  }
  
  public Bundle getCustomEventExtrasBundle(Class<? extends CustomEvent> paramClass)
  {
    Bundle localBundle = this.zzuA.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter");
    if (localBundle != null) {
      return localBundle.getBundle(paramClass.getClass().getName());
    }
    return null;
  }
  
  public Bundle getCustomTargeting()
  {
    return this.zzub;
  }
  
  public int getGender()
  {
    return this.zztT;
  }
  
  public Set<String> getKeywords()
  {
    return this.zzbh;
  }
  
  public Location getLocation()
  {
    return this.zzbj;
  }
  
  public boolean getManualImpressionsEnabled()
  {
    return this.zzpE;
  }
  
  @Deprecated
  public <T extends NetworkExtras> T getNetworkExtras(Class<T> paramClass)
  {
    return (NetworkExtras)this.zzuB.get(paramClass);
  }
  
  public Bundle getNetworkExtrasBundle(Class<? extends MediationAdapter> paramClass)
  {
    return this.zzuA.getBundle(paramClass.getName());
  }
  
  public String getPublisherProvidedId()
  {
    return this.zztX;
  }
  
  public boolean isDesignedForFamilies()
  {
    return this.zzuf;
  }
  
  public boolean isTestDevice(Context paramContext)
  {
    return this.zzuD.contains(zzn.zzcS().zzT(paramContext));
  }
  
  public String zzcZ()
  {
    return this.zzud;
  }
  
  public SearchAdRequest zzda()
  {
    return this.zzuC;
  }
  
  public Map<Class<? extends NetworkExtras>, NetworkExtras> zzdb()
  {
    return this.zzuB;
  }
  
  public Bundle zzdc()
  {
    return this.zzuA;
  }
  
  public int zzdd()
  {
    return this.zztW;
  }
  
  public Set<String> zzde()
  {
    return this.zzuE;
  }
  
  public static final class zza
  {
    private Date zzbf;
    private Location zzbj;
    private boolean zzpE = false;
    private int zztT = -1;
    private int zztW = -1;
    private String zztX;
    private String zztZ;
    private final Bundle zzuA = new Bundle();
    private final HashSet<String> zzuF = new HashSet();
    private final HashMap<Class<? extends NetworkExtras>, NetworkExtras> zzuG = new HashMap();
    private final HashSet<String> zzuH = new HashSet();
    private final HashSet<String> zzuI = new HashSet();
    private final Bundle zzub = new Bundle();
    private String zzud;
    private boolean zzuf;
    
    public void setManualImpressionsEnabled(boolean paramBoolean)
    {
      this.zzpE = paramBoolean;
    }
    
    public void zzA(String paramString)
    {
      this.zzuF.add(paramString);
    }
    
    public void zzB(String paramString)
    {
      this.zzuH.add(paramString);
    }
    
    public void zzC(String paramString)
    {
      this.zzuH.remove(paramString);
    }
    
    public void zzD(String paramString)
    {
      this.zztZ = paramString;
    }
    
    public void zzE(String paramString)
    {
      this.zztX = paramString;
    }
    
    public void zzF(String paramString)
    {
      this.zzud = paramString;
    }
    
    public void zzG(String paramString)
    {
      this.zzuI.add(paramString);
    }
    
    @Deprecated
    public void zza(NetworkExtras paramNetworkExtras)
    {
      if ((paramNetworkExtras instanceof AdMobExtras))
      {
        zza(AdMobAdapter.class, ((AdMobExtras)paramNetworkExtras).getExtras());
        return;
      }
      this.zzuG.put(paramNetworkExtras.getClass(), paramNetworkExtras);
    }
    
    public void zza(Class<? extends MediationAdapter> paramClass, Bundle paramBundle)
    {
      this.zzuA.putBundle(paramClass.getName(), paramBundle);
    }
    
    public void zza(String paramString1, String paramString2)
    {
      this.zzub.putString(paramString1, paramString2);
    }
    
    public void zza(Date paramDate)
    {
      this.zzbf = paramDate;
    }
    
    public void zzb(Location paramLocation)
    {
      this.zzbj = paramLocation;
    }
    
    public void zzb(Class<? extends CustomEvent> paramClass, Bundle paramBundle)
    {
      if (this.zzuA.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter") == null) {
        this.zzuA.putBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter", new Bundle());
      }
      this.zzuA.getBundle("com.google.android.gms.ads.mediation.customevent.CustomEventAdapter").putBundle(paramClass.getName(), paramBundle);
    }
    
    public void zzk(boolean paramBoolean)
    {
      if (paramBoolean) {}
      for (int i = 1;; i = 0)
      {
        this.zztW = i;
        return;
      }
    }
    
    public void zzl(boolean paramBoolean)
    {
      this.zzuf = paramBoolean;
    }
    
    public void zzn(int paramInt)
    {
      this.zztT = paramInt;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\client\zzaa.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */