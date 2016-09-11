package com.google.android.gms.internal;

import android.location.Location;
import android.support.annotation.Nullable;
import com.google.android.gms.ads.formats.NativeAdOptions;
import com.google.android.gms.ads.formats.NativeAdOptions.Builder;
import com.google.android.gms.ads.internal.formats.NativeAdOptionsParcel;
import com.google.android.gms.ads.mediation.NativeMediationAdRequest;
import java.util.Date;
import java.util.List;
import java.util.Set;

@zzhb
public final class zzfi
  implements NativeMediationAdRequest
{
  private final int zzCH;
  private final Date zzbf;
  private final Set<String> zzbh;
  private final boolean zzbi;
  private final Location zzbj;
  private final NativeAdOptionsParcel zzpP;
  private final List<String> zzpQ;
  private final int zztT;
  private final boolean zzuf;
  
  public zzfi(@Nullable Date paramDate, int paramInt1, @Nullable Set<String> paramSet, @Nullable Location paramLocation, boolean paramBoolean1, int paramInt2, NativeAdOptionsParcel paramNativeAdOptionsParcel, List<String> paramList, boolean paramBoolean2)
  {
    this.zzbf = paramDate;
    this.zztT = paramInt1;
    this.zzbh = paramSet;
    this.zzbj = paramLocation;
    this.zzbi = paramBoolean1;
    this.zzCH = paramInt2;
    this.zzpP = paramNativeAdOptionsParcel;
    this.zzpQ = paramList;
    this.zzuf = paramBoolean2;
  }
  
  public Date getBirthday()
  {
    return this.zzbf;
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
  
  public NativeAdOptions getNativeAdOptions()
  {
    if (this.zzpP == null) {
      return null;
    }
    return new NativeAdOptions.Builder().setReturnUrlsForImageAssets(this.zzpP.zzyA).setImageOrientation(this.zzpP.zzyB).setRequestMultipleImages(this.zzpP.zzyC).build();
  }
  
  public boolean isAppInstallAdRequested()
  {
    return (this.zzpQ != null) && (this.zzpQ.contains("2"));
  }
  
  public boolean isContentAdRequested()
  {
    return (this.zzpQ != null) && (this.zzpQ.contains("1"));
  }
  
  public boolean isDesignedForFamilies()
  {
    return this.zzuf;
  }
  
  public boolean isTesting()
  {
    return this.zzbi;
  }
  
  public int taggedForChildDirectedTreatment()
  {
    return this.zzCH;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzfi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */