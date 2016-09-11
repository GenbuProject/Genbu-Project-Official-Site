package com.google.android.gms.internal;

import android.content.MutableContextWrapper;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.zzk;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.LinkedList;

@zzhb
class zzea
{
  private final LinkedList<zza> zzAB;
  private final int zzAC;
  private final String zzpS;
  private AdRequestParcel zzqH;
  
  zzea(AdRequestParcel paramAdRequestParcel, String paramString, int paramInt)
  {
    zzx.zzz(paramAdRequestParcel);
    zzx.zzz(paramString);
    this.zzAB = new LinkedList();
    this.zzqH = paramAdRequestParcel;
    this.zzpS = paramString;
    this.zzAC = paramInt;
  }
  
  String getAdUnitId()
  {
    return this.zzpS;
  }
  
  int getNetworkType()
  {
    return this.zzAC;
  }
  
  int size()
  {
    return this.zzAB.size();
  }
  
  void zzb(zzdv paramzzdv)
  {
    paramzzdv = new zza(paramzzdv);
    this.zzAB.add(paramzzdv);
    paramzzdv.zzh(this.zzqH);
  }
  
  AdRequestParcel zzei()
  {
    return this.zzqH;
  }
  
  zza zzej()
  {
    return (zza)this.zzAB.remove();
  }
  
  class zza
  {
    zzk zzAD;
    zzdw zzAE;
    long zzAF;
    boolean zzAG;
    boolean zzAH;
    MutableContextWrapper zzAb;
    
    zza(zzdv paramzzdv)
    {
      zzdv localzzdv = paramzzdv.zzec();
      this.zzAb = paramzzdv.zzed();
      this.zzAD = localzzdv.zzX(zzea.zza(zzea.this));
      this.zzAE = new zzdw();
      this.zzAE.zzc(this.zzAD);
    }
    
    private void zzek()
    {
      if ((!this.zzAG) && (zzea.zzc(zzea.this) != null))
      {
        this.zzAH = this.zzAD.zzb(zzea.zzc(zzea.this));
        this.zzAG = true;
        this.zzAF = zzr.zzbG().currentTimeMillis();
      }
    }
    
    void zzc(zzdv paramzzdv)
    {
      paramzzdv = paramzzdv.zzed().getBaseContext();
      this.zzAb.setBaseContext(paramzzdv);
    }
    
    void zzh(AdRequestParcel paramAdRequestParcel)
    {
      if (paramAdRequestParcel != null) {
        zzea.zza(zzea.this, paramAdRequestParcel);
      }
      zzek();
      paramAdRequestParcel = zzea.zzb(zzea.this).iterator();
      while (paramAdRequestParcel.hasNext()) {
        ((zza)paramAdRequestParcel.next()).zzek();
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzea.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */