package com.google.android.gms.ads.internal.overlay;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.TextureView;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import com.google.android.gms.internal.zzbp;
import com.google.android.gms.internal.zzbt;
import com.google.android.gms.internal.zzbx;
import com.google.android.gms.internal.zzbz;
import com.google.android.gms.internal.zzcb;
import com.google.android.gms.internal.zzhb;
import com.google.android.gms.internal.zzin;
import com.google.android.gms.internal.zzir;
import com.google.android.gms.internal.zziv;
import com.google.android.gms.internal.zziv.zza;
import com.google.android.gms.internal.zziv.zzb;
import com.google.android.gms.internal.zzmq;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

@zzhb
public class zzt
{
  private final Context mContext;
  private final String zzEY;
  private final VersionInfoParcel zzEZ;
  @Nullable
  private final zzbz zzFa;
  @Nullable
  private final zzcb zzFb;
  private final zziv zzFc = new zziv.zzb().zza("min_1", Double.MIN_VALUE, 1.0D).zza("1_5", 1.0D, 5.0D).zza("5_10", 5.0D, 10.0D).zza("10_20", 10.0D, 20.0D).zza("20_30", 20.0D, 30.0D).zza("30_max", 30.0D, Double.MAX_VALUE).zzhA();
  private final long[] zzFd;
  private final String[] zzFe;
  @Nullable
  private zzbz zzFf;
  @Nullable
  private zzbz zzFg;
  @Nullable
  private zzbz zzFh;
  @Nullable
  private zzbz zzFi;
  private boolean zzFj;
  private zzi zzFk;
  private boolean zzFl;
  private boolean zzFm;
  private long zzFn = -1L;
  
  public zzt(Context paramContext, VersionInfoParcel paramVersionInfoParcel, String paramString, @Nullable zzcb paramzzcb, @Nullable zzbz paramzzbz)
  {
    this.mContext = paramContext;
    this.zzEZ = paramVersionInfoParcel;
    this.zzEY = paramString;
    this.zzFb = paramzzcb;
    this.zzFa = paramzzbz;
    paramContext = (String)zzbt.zzvV.get();
    if (paramContext == null)
    {
      this.zzFe = new String[0];
      this.zzFd = new long[0];
      return;
    }
    paramContext = TextUtils.split(paramContext, ",");
    this.zzFe = new String[paramContext.length];
    this.zzFd = new long[paramContext.length];
    int i = 0;
    while (i < paramContext.length) {
      try
      {
        this.zzFd[i] = Long.parseLong(paramContext[i]);
        i += 1;
      }
      catch (NumberFormatException paramVersionInfoParcel)
      {
        for (;;)
        {
          zzin.zzd("Unable to parse frame hash target time number.", paramVersionInfoParcel);
          this.zzFd[i] = -1L;
        }
      }
    }
  }
  
  private void zzc(zzi paramzzi)
  {
    long l1 = ((Long)zzbt.zzvW.get()).longValue();
    long l2 = paramzzi.getCurrentPosition();
    int i = 0;
    if (i < this.zzFe.length)
    {
      if (this.zzFe[i] != null) {}
      while (l1 <= Math.abs(l2 - this.zzFd[i]))
      {
        i += 1;
        break;
      }
      this.zzFe[i] = zza(paramzzi);
    }
  }
  
  private void zzfN()
  {
    if ((this.zzFh != null) && (this.zzFi == null))
    {
      zzbx.zza(this.zzFb, this.zzFh, new String[] { "vff" });
      zzbx.zza(this.zzFb, this.zzFa, new String[] { "vtt" });
      this.zzFi = zzbx.zzb(this.zzFb);
    }
    long l = zzr.zzbG().nanoTime();
    if ((this.zzFj) && (this.zzFm) && (this.zzFn != -1L))
    {
      double d = TimeUnit.SECONDS.toNanos(1L) / (l - this.zzFn);
      this.zzFc.zza(d);
    }
    this.zzFm = this.zzFj;
    this.zzFn = l;
  }
  
  public void onStop()
  {
    if ((((Boolean)zzbt.zzvU.get()).booleanValue()) && (!this.zzFl))
    {
      Bundle localBundle = new Bundle();
      localBundle.putString("type", "native-player-metrics");
      localBundle.putString("request", this.zzEY);
      localBundle.putString("player", this.zzFk.zzeZ());
      Object localObject = this.zzFc.getBuckets().iterator();
      while (((Iterator)localObject).hasNext())
      {
        zziv.zza localzza = (zziv.zza)((Iterator)localObject).next();
        localBundle.putString("fps_c_" + localzza.name, Integer.toString(localzza.count));
        localBundle.putString("fps_p_" + localzza.name, Double.toString(localzza.zzMu));
      }
      int i = 0;
      if (i < this.zzFd.length)
      {
        localObject = this.zzFe[i];
        if (localObject == null) {}
        for (;;)
        {
          i += 1;
          break;
          localBundle.putString("fh_" + Long.valueOf(this.zzFd[i]), (String)localObject);
        }
      }
      zzr.zzbC().zza(this.mContext, this.zzEZ.afmaVersion, "gmob-apps", localBundle, true);
      this.zzFl = true;
    }
  }
  
  String zza(TextureView paramTextureView)
  {
    paramTextureView = paramTextureView.getBitmap(8, 8);
    long l2 = 0L;
    long l1 = 63L;
    int i = 0;
    while (i < 8)
    {
      long l3 = l1;
      int j = 0;
      l1 = l2;
      l2 = l3;
      if (j < 8)
      {
        int k = paramTextureView.getPixel(j, i);
        int m = Color.blue(k);
        int n = Color.red(k);
        if (Color.green(k) + (m + n) > 128) {}
        for (l3 = 1L;; l3 = 0L)
        {
          l1 |= l3 << (int)l2;
          l2 -= 1L;
          j += 1;
          break;
        }
      }
      i += 1;
      l3 = l1;
      l1 = l2;
      l2 = l3;
    }
    return String.format("%016X", new Object[] { Long.valueOf(l2) });
  }
  
  public void zza(zzi paramzzi)
  {
    zzbx.zza(this.zzFb, this.zzFa, new String[] { "vpc" });
    this.zzFf = zzbx.zzb(this.zzFb);
    this.zzFk = paramzzi;
  }
  
  public void zzb(zzi paramzzi)
  {
    zzfN();
    zzc(paramzzi);
  }
  
  public void zzfO()
  {
    this.zzFj = true;
    if ((this.zzFg != null) && (this.zzFh == null))
    {
      zzbx.zza(this.zzFb, this.zzFg, new String[] { "vfp" });
      this.zzFh = zzbx.zzb(this.zzFb);
    }
  }
  
  public void zzfP()
  {
    this.zzFj = false;
  }
  
  public void zzfz()
  {
    if ((this.zzFf == null) || (this.zzFg != null)) {
      return;
    }
    zzbx.zza(this.zzFb, this.zzFf, new String[] { "vfr" });
    this.zzFg = zzbx.zzb(this.zzFb);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\overlay\zzt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */