package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.WindowManager;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.util.client.zza;
import com.google.android.gms.ads.internal.zzr;
import java.util.Map;

@zzhb
public class zzfr
  extends zzfs
  implements zzdf
{
  private final Context mContext;
  DisplayMetrics zzDA;
  private float zzDB;
  int zzDC = -1;
  int zzDD = -1;
  private int zzDE;
  int zzDF = -1;
  int zzDG = -1;
  int zzDH = -1;
  int zzDI = -1;
  private final zzbl zzDz;
  private final zzjp zzpD;
  private final WindowManager zzsb;
  
  public zzfr(zzjp paramzzjp, Context paramContext, zzbl paramzzbl)
  {
    super(paramzzjp);
    this.zzpD = paramzzjp;
    this.mContext = paramContext;
    this.zzDz = paramzzbl;
    this.zzsb = ((WindowManager)paramContext.getSystemService("window"));
  }
  
  private void zzeQ()
  {
    this.zzDA = new DisplayMetrics();
    Display localDisplay = this.zzsb.getDefaultDisplay();
    localDisplay.getMetrics(this.zzDA);
    this.zzDB = this.zzDA.density;
    this.zzDE = localDisplay.getRotation();
  }
  
  private void zzeV()
  {
    int[] arrayOfInt = new int[2];
    this.zzpD.getLocationOnScreen(arrayOfInt);
    zzf(zzn.zzcS().zzc(this.mContext, arrayOfInt[0]), zzn.zzcS().zzc(this.mContext, arrayOfInt[1]));
  }
  
  private zzfq zzeY()
  {
    return new zzfq.zza().zzr(this.zzDz.zzdj()).zzq(this.zzDz.zzdk()).zzs(this.zzDz.zzdo()).zzt(this.zzDz.zzdl()).zzu(this.zzDz.zzdm()).zzeP();
  }
  
  public void zza(zzjp paramzzjp, Map<String, String> paramMap)
  {
    zzeT();
  }
  
  void zzeR()
  {
    this.zzDC = zzn.zzcS().zzb(this.zzDA, this.zzDA.widthPixels);
    this.zzDD = zzn.zzcS().zzb(this.zzDA, this.zzDA.heightPixels);
    Object localObject = this.zzpD.zzhP();
    if ((localObject == null) || (((Activity)localObject).getWindow() == null))
    {
      this.zzDF = this.zzDC;
      this.zzDG = this.zzDD;
      return;
    }
    localObject = zzr.zzbC().zze((Activity)localObject);
    this.zzDF = zzn.zzcS().zzb(this.zzDA, localObject[0]);
    this.zzDG = zzn.zzcS().zzb(this.zzDA, localObject[1]);
  }
  
  void zzeS()
  {
    if (this.zzpD.zzaN().zzui)
    {
      this.zzDH = this.zzDC;
      this.zzDI = this.zzDD;
      return;
    }
    this.zzpD.measure(0, 0);
    this.zzDH = zzn.zzcS().zzc(this.mContext, this.zzpD.getMeasuredWidth());
    this.zzDI = zzn.zzcS().zzc(this.mContext, this.zzpD.getMeasuredHeight());
  }
  
  public void zzeT()
  {
    zzeQ();
    zzeR();
    zzeS();
    zzeW();
    zzeX();
    zzeV();
    zzeU();
  }
  
  void zzeU()
  {
    if (zzin.zzQ(2)) {
      zzin.zzaJ("Dispatching Ready Event.");
    }
    zzan(this.zzpD.zzhX().afmaVersion);
  }
  
  void zzeW()
  {
    zza(this.zzDC, this.zzDD, this.zzDF, this.zzDG, this.zzDB, this.zzDE);
  }
  
  void zzeX()
  {
    zzfq localzzfq = zzeY();
    this.zzpD.zzb("onDeviceFeaturesReceived", localzzfq.toJson());
  }
  
  public void zzf(int paramInt1, int paramInt2)
  {
    if ((this.mContext instanceof Activity)) {}
    for (int i = zzr.zzbC().zzh((Activity)this.mContext)[0];; i = 0)
    {
      zzc(paramInt1, paramInt2 - i, this.zzDH, this.zzDI);
      this.zzpD.zzhU().zze(paramInt1, paramInt2);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzfr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */