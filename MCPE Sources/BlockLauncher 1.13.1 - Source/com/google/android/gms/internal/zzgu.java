package com.google.android.gms.internal;

import android.content.Context;
import android.os.Handler;
import com.google.ads.mediation.admob.AdMobAdapter;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.zzm;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

@zzhb
public class zzgu
  extends zzgq
{
  private zzeo zzCf;
  private boolean zzGA;
  private zzem zzGy;
  protected zzes zzGz;
  private final zzjp zzpD;
  private final zzcb zzpe;
  private zzex zzpn;
  
  zzgu(Context paramContext, zzif.zza paramzza, zzex paramzzex, zzgr.zza paramzza1, zzcb paramzzcb, zzjp paramzzjp)
  {
    super(paramContext, paramzza, paramzza1);
    this.zzpn = paramzzex;
    this.zzCf = paramzza.zzKV;
    this.zzpe = paramzzcb;
    this.zzpD = paramzzjp;
  }
  
  private void zzgk()
    throws zzgq.zza
  {
    ??? = new CountDownLatch(1);
    zzir.zzMc.post(new Runnable()
    {
      public void run()
      {
        synchronized (zzgu.this.zzGg)
        {
          zzgu.zza(zzgu.this, zzm.zza(zzgu.zza(zzgu.this), zzgu.this.zzGz, localObject1));
          return;
        }
      }
    });
    try
    {
      ((CountDownLatch)???).await(10L, TimeUnit.SECONDS);
      synchronized (this.zzGg)
      {
        if (!this.zzGA) {
          throw new zzgq.zza("View could not be prepared", 0);
        }
      }
      if (!this.zzpD.isDestroyed()) {
        break label118;
      }
    }
    catch (InterruptedException localInterruptedException)
    {
      throw new zzgq.zza("Interrupted while waiting for latch : " + localInterruptedException, 0);
    }
    throw new zzgq.zza("Assets not loaded, web view is destroyed", 0);
    label118:
  }
  
  public void onStop()
  {
    synchronized (this.zzGg)
    {
      super.onStop();
      if (this.zzGy != null) {
        this.zzGy.cancel();
      }
      return;
    }
  }
  
  protected zzif zzD(int paramInt)
  {
    Object localObject = this.zzGd.zzLd;
    AdRequestParcel localAdRequestParcel = ((AdRequestInfoParcel)localObject).zzHt;
    zzjp localzzjp = this.zzpD;
    List localList1 = this.zzGe.zzBQ;
    List localList2 = this.zzGe.zzBR;
    List localList3 = this.zzGe.zzHV;
    int i = this.zzGe.orientation;
    long l = this.zzGe.zzBU;
    String str2 = ((AdRequestInfoParcel)localObject).zzHw;
    boolean bool = this.zzGe.zzHT;
    zzey localzzey;
    label113:
    String str1;
    label129:
    zzeo localzzeo;
    if (this.zzGz != null)
    {
      localObject = this.zzGz.zzCp;
      if (this.zzGz == null) {
        break label270;
      }
      localzzey = this.zzGz.zzCq;
      if (this.zzGz == null) {
        break label276;
      }
      str1 = this.zzGz.zzCr;
      localzzeo = this.zzCf;
      if (this.zzGz == null) {
        break label286;
      }
    }
    label270:
    label276:
    label286:
    for (zzeq localzzeq = this.zzGz.zzCs;; localzzeq = null)
    {
      return new zzif(localAdRequestParcel, localzzjp, localList1, paramInt, localList2, localList3, i, l, str2, bool, (zzen)localObject, localzzey, str1, localzzeo, localzzeq, this.zzGe.zzHU, this.zzGd.zzrp, this.zzGe.zzHS, this.zzGd.zzKY, this.zzGe.zzHX, this.zzGe.zzHY, this.zzGd.zzKT, null, this.zzGe.zzIj, this.zzGe.zzIk, this.zzGe.zzIl, this.zzGe.zzIm);
      localObject = null;
      break;
      localzzey = null;
      break label113;
      str1 = AdMobAdapter.class.getName();
      break label129;
    }
  }
  
  protected void zzh(long paramLong)
    throws zzgq.zza
  {
    synchronized (this.zzGg)
    {
      this.zzGy = zzi(paramLong);
      this.zzGz = this.zzGy.zzc(this.zzCf.zzBO);
      switch (this.zzGz.zzCo)
      {
      default: 
        throw new zzgq.zza("Unexpected mediation result: " + this.zzGz.zzCo, 0);
      }
    }
    throw new zzgq.zza("No fill from any mediation ad networks.", 3);
    if ((this.zzGz.zzCp != null) && (this.zzGz.zzCp.zzBJ != null)) {
      zzgk();
    }
  }
  
  zzem zzi(long paramLong)
  {
    if (this.zzCf.zzBX != -1) {
      return new zzeu(this.mContext, this.zzGd.zzLd, this.zzpn, this.zzCf, this.zzGe.zzuk, this.zzGe.zzum, paramLong, ((Long)zzbt.zzwY.get()).longValue(), 2);
    }
    return new zzev(this.mContext, this.zzGd.zzLd, this.zzpn, this.zzCf, this.zzGe.zzuk, this.zzGe.zzum, paramLong, ((Long)zzbt.zzwY.get()).longValue(), this.zzpe);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzgu.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */