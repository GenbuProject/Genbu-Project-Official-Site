package com.google.android.gms.ads;

import android.content.Context;
import android.content.res.Resources;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.client.zzn;
import com.google.android.gms.ads.internal.util.client.zza;

public final class AdSize
{
  public static final int AUTO_HEIGHT = -2;
  public static final AdSize BANNER = new AdSize(320, 50, "320x50_mb");
  public static final AdSize FLUID = new AdSize(-3, -4, "fluid");
  public static final AdSize FULL_BANNER = new AdSize(468, 60, "468x60_as");
  public static final int FULL_WIDTH = -1;
  public static final AdSize LARGE_BANNER = new AdSize(320, 100, "320x100_as");
  public static final AdSize LEADERBOARD = new AdSize(728, 90, "728x90_as");
  public static final AdSize MEDIUM_RECTANGLE = new AdSize(300, 250, "300x250_as");
  public static final AdSize SMART_BANNER;
  public static final AdSize WIDE_SKYSCRAPER = new AdSize(160, 600, "160x600_as");
  private final int zzoG;
  private final int zzoH;
  private final String zzoI;
  
  static
  {
    SMART_BANNER = new AdSize(-1, -2, "smart_banner");
  }
  
  public AdSize(int paramInt1, int paramInt2) {}
  
  AdSize(int paramInt1, int paramInt2, String paramString)
  {
    if ((paramInt1 < 0) && (paramInt1 != -1) && (paramInt1 != -3)) {
      throw new IllegalArgumentException("Invalid width for AdSize: " + paramInt1);
    }
    if ((paramInt2 < 0) && (paramInt2 != -2) && (paramInt2 != -4)) {
      throw new IllegalArgumentException("Invalid height for AdSize: " + paramInt2);
    }
    this.zzoG = paramInt1;
    this.zzoH = paramInt2;
    this.zzoI = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof AdSize)) {
        return false;
      }
      paramObject = (AdSize)paramObject;
    } while ((this.zzoG == ((AdSize)paramObject).zzoG) && (this.zzoH == ((AdSize)paramObject).zzoH) && (this.zzoI.equals(((AdSize)paramObject).zzoI)));
    return false;
  }
  
  public int getHeight()
  {
    return this.zzoH;
  }
  
  public int getHeightInPixels(Context paramContext)
  {
    switch (this.zzoH)
    {
    default: 
      return zzn.zzcS().zzb(paramContext, this.zzoH);
    case -2: 
      return AdSizeParcel.zzb(paramContext.getResources().getDisplayMetrics());
    }
    return -1;
  }
  
  public int getWidth()
  {
    return this.zzoG;
  }
  
  public int getWidthInPixels(Context paramContext)
  {
    switch (this.zzoG)
    {
    case -2: 
    default: 
      return zzn.zzcS().zzb(paramContext, this.zzoG);
    case -1: 
      return AdSizeParcel.zza(paramContext.getResources().getDisplayMetrics());
    }
    return -1;
  }
  
  public int hashCode()
  {
    return this.zzoI.hashCode();
  }
  
  public boolean isAutoHeight()
  {
    return this.zzoH == -2;
  }
  
  public boolean isFluid()
  {
    return (this.zzoG == -3) && (this.zzoH == -4);
  }
  
  public boolean isFullWidth()
  {
    return this.zzoG == -1;
  }
  
  public String toString()
  {
    return this.zzoI;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\ads\AdSize.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */