package com.google.android.gms.internal;

import android.content.Context;
import android.view.View;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.formats.zzh;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Map.Entry;
import java.util.Set;
import java.util.WeakHashMap;

@zzhb
public class zzax
  implements zzay
{
  private final VersionInfoParcel zzpT;
  private final Object zzpV = new Object();
  private final WeakHashMap<zzif, zzau> zzsB = new WeakHashMap();
  private final ArrayList<zzau> zzsC = new ArrayList();
  private final zzeg zzsD;
  private final Context zzsa;
  
  public zzax(Context paramContext, VersionInfoParcel paramVersionInfoParcel, zzeg paramzzeg)
  {
    this.zzsa = paramContext.getApplicationContext();
    this.zzpT = paramVersionInfoParcel;
    this.zzsD = paramzzeg;
  }
  
  public zzau zza(AdSizeParcel paramAdSizeParcel, zzif paramzzif)
  {
    return zza(paramAdSizeParcel, paramzzif, paramzzif.zzED.getView());
  }
  
  public zzau zza(AdSizeParcel paramAdSizeParcel, zzif paramzzif, View paramView)
  {
    return zza(paramAdSizeParcel, paramzzif, new zzau.zzd(paramView, paramzzif), null);
  }
  
  public zzau zza(AdSizeParcel paramAdSizeParcel, zzif paramzzif, View paramView, zzeh paramzzeh)
  {
    return zza(paramAdSizeParcel, paramzzif, new zzau.zzd(paramView, paramzzif), paramzzeh);
  }
  
  public zzau zza(AdSizeParcel paramAdSizeParcel, zzif paramzzif, zzh paramzzh)
  {
    return zza(paramAdSizeParcel, paramzzif, new zzau.zza(paramzzh), null);
  }
  
  public zzau zza(AdSizeParcel paramAdSizeParcel, zzif paramzzif, zzbb paramzzbb, zzeh paramzzeh)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (zzh(paramzzif))
        {
          paramAdSizeParcel = (zzau)this.zzsB.get(paramzzif);
          return paramAdSizeParcel;
        }
        if (paramzzeh != null)
        {
          paramAdSizeParcel = new zzaz(this.zzsa, paramAdSizeParcel, paramzzif, this.zzpT, paramzzbb, paramzzeh);
          paramAdSizeParcel.zza(this);
          this.zzsB.put(paramzzif, paramAdSizeParcel);
          this.zzsC.add(paramAdSizeParcel);
          return paramAdSizeParcel;
        }
      }
      paramAdSizeParcel = new zzba(this.zzsa, paramAdSizeParcel, paramzzif, this.zzpT, paramzzbb, this.zzsD);
    }
  }
  
  public void zza(zzau paramzzau)
  {
    synchronized (this.zzpV)
    {
      if (!paramzzau.zzch())
      {
        this.zzsC.remove(paramzzau);
        Iterator localIterator = this.zzsB.entrySet().iterator();
        while (localIterator.hasNext()) {
          if (((Map.Entry)localIterator.next()).getValue() == paramzzau) {
            localIterator.remove();
          }
        }
      }
    }
  }
  
  public boolean zzh(zzif paramzzif)
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        paramzzif = (zzau)this.zzsB.get(paramzzif);
        if ((paramzzif != null) && (paramzzif.zzch()))
        {
          bool = true;
          return bool;
        }
      }
      boolean bool = false;
    }
  }
  
  public void zzi(zzif paramzzif)
  {
    synchronized (this.zzpV)
    {
      paramzzif = (zzau)this.zzsB.get(paramzzif);
      if (paramzzif != null) {
        paramzzif.zzcf();
      }
      return;
    }
  }
  
  public void zzj(zzif paramzzif)
  {
    synchronized (this.zzpV)
    {
      paramzzif = (zzau)this.zzsB.get(paramzzif);
      if (paramzzif != null) {
        paramzzif.stop();
      }
      return;
    }
  }
  
  public void zzk(zzif paramzzif)
  {
    synchronized (this.zzpV)
    {
      paramzzif = (zzau)this.zzsB.get(paramzzif);
      if (paramzzif != null) {
        paramzzif.pause();
      }
      return;
    }
  }
  
  public void zzl(zzif paramzzif)
  {
    synchronized (this.zzpV)
    {
      paramzzif = (zzau)this.zzsB.get(paramzzif);
      if (paramzzif != null) {
        paramzzif.resume();
      }
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzax.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */