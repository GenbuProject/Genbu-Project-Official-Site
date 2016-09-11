package com.google.android.gms.ads.internal.client;

import android.content.Context;
import android.content.res.Resources;
import android.os.Parcel;
import android.util.DisplayMetrics;
import com.google.android.gms.ads.AdSize;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzhb;

@zzhb
public class AdSizeParcel
  implements SafeParcelable
{
  public static final zzi CREATOR = new zzi();
  public final int height;
  public final int heightPixels;
  public final int versionCode;
  public final int width;
  public final int widthPixels;
  public final String zzuh;
  public final boolean zzui;
  public final AdSizeParcel[] zzuj;
  public final boolean zzuk;
  public final boolean zzul;
  public boolean zzum;
  
  public AdSizeParcel()
  {
    this(5, "interstitial_mb", 0, 0, true, 0, 0, null, false, false, false);
  }
  
  AdSizeParcel(int paramInt1, String paramString, int paramInt2, int paramInt3, boolean paramBoolean1, int paramInt4, int paramInt5, AdSizeParcel[] paramArrayOfAdSizeParcel, boolean paramBoolean2, boolean paramBoolean3, boolean paramBoolean4)
  {
    this.versionCode = paramInt1;
    this.zzuh = paramString;
    this.height = paramInt2;
    this.heightPixels = paramInt3;
    this.zzui = paramBoolean1;
    this.width = paramInt4;
    this.widthPixels = paramInt5;
    this.zzuj = paramArrayOfAdSizeParcel;
    this.zzuk = paramBoolean2;
    this.zzul = paramBoolean3;
    this.zzum = paramBoolean4;
  }
  
  public AdSizeParcel(Context paramContext, AdSize paramAdSize)
  {
    this(paramContext, new AdSize[] { paramAdSize });
  }
  
  public AdSizeParcel(Context paramContext, AdSize[] paramArrayOfAdSize)
  {
    AdSize localAdSize = paramArrayOfAdSize[0];
    this.versionCode = 5;
    this.zzui = false;
    this.zzul = localAdSize.isFluid();
    int j;
    label66:
    int k;
    label78:
    DisplayMetrics localDisplayMetrics;
    label129:
    int m;
    int i;
    if (this.zzul)
    {
      this.width = AdSize.BANNER.getWidth();
      this.height = AdSize.BANNER.getHeight();
      if (this.width != -1) {
        break label312;
      }
      j = 1;
      if (this.height != -2) {
        break label318;
      }
      k = 1;
      localDisplayMetrics = paramContext.getResources().getDisplayMetrics();
      if (j == 0) {
        break label336;
      }
      if ((!zzn.zzcS().zzV(paramContext)) || (!zzn.zzcS().zzW(paramContext))) {
        break label324;
      }
      this.widthPixels = (zza(localDisplayMetrics) - zzn.zzcS().zzX(paramContext));
      double d = this.widthPixels / localDisplayMetrics.density;
      m = (int)d;
      i = m;
      if (d - (int)d >= 0.01D) {
        i = m + 1;
      }
      label168:
      if (k == 0) {
        break label361;
      }
      m = zzc(localDisplayMetrics);
      label180:
      this.heightPixels = zzn.zzcS().zza(localDisplayMetrics, m);
      if ((j == 0) && (k == 0)) {
        break label370;
      }
      this.zzuh = (i + "x" + m + "_as");
    }
    for (;;)
    {
      if (paramArrayOfAdSize.length <= 1) {
        break label398;
      }
      this.zzuj = new AdSizeParcel[paramArrayOfAdSize.length];
      i = 0;
      while (i < paramArrayOfAdSize.length)
      {
        this.zzuj[i] = new AdSizeParcel(paramContext, paramArrayOfAdSize[i]);
        i += 1;
      }
      this.width = localAdSize.getWidth();
      this.height = localAdSize.getHeight();
      break;
      label312:
      j = 0;
      break label66;
      label318:
      k = 0;
      break label78;
      label324:
      this.widthPixels = zza(localDisplayMetrics);
      break label129;
      label336:
      i = this.width;
      this.widthPixels = zzn.zzcS().zza(localDisplayMetrics, this.width);
      break label168;
      label361:
      m = this.height;
      break label180;
      label370:
      if (this.zzul) {
        this.zzuh = "320x50_mb";
      } else {
        this.zzuh = localAdSize.toString();
      }
    }
    label398:
    this.zzuj = null;
    this.zzuk = false;
    this.zzum = false;
  }
  
  public AdSizeParcel(AdSizeParcel paramAdSizeParcel, AdSizeParcel[] paramArrayOfAdSizeParcel)
  {
    this(5, paramAdSizeParcel.zzuh, paramAdSizeParcel.height, paramAdSizeParcel.heightPixels, paramAdSizeParcel.zzui, paramAdSizeParcel.width, paramAdSizeParcel.widthPixels, paramArrayOfAdSizeParcel, paramAdSizeParcel.zzuk, paramAdSizeParcel.zzul, paramAdSizeParcel.zzum);
  }
  
  public static int zza(DisplayMetrics paramDisplayMetrics)
  {
    return paramDisplayMetrics.widthPixels;
  }
  
  public static int zzb(DisplayMetrics paramDisplayMetrics)
  {
    return (int)(zzc(paramDisplayMetrics) * paramDisplayMetrics.density);
  }
  
  private static int zzc(DisplayMetrics paramDisplayMetrics)
  {
    int i = (int)(paramDisplayMetrics.heightPixels / paramDisplayMetrics.density);
    if (i <= 400) {
      return 32;
    }
    if (i <= 720) {
      return 50;
    }
    return 90;
  }
  
  public static AdSizeParcel zzcP()
  {
    return new AdSizeParcel(5, "reward_mb", 0, 0, true, 0, 0, null, false, false, false);
  }
  
  public static AdSizeParcel zzt(Context paramContext)
  {
    return new AdSizeParcel(5, "320x50_mb", 0, 0, false, 0, 0, null, true, false, false);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public AdSize zzcQ()
  {
    return com.google.android.gms.ads.zza.zza(this.width, this.height, this.zzuh);
  }
  
  public void zzi(boolean paramBoolean)
  {
    this.zzum = paramBoolean;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\internal\client\AdSizeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */