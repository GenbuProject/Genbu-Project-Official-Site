package com.google.android.gms.location.places.internal;

import android.annotation.SuppressLint;
import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.location.places.Place;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.TimeZone;

public final class PlaceImpl
  implements SafeParcelable, Place
{
  public static final zzl CREATOR = new zzl();
  private final String mName;
  final int mVersionCode;
  private final LatLng zzaPc;
  private final List<Integer> zzaPd;
  private final String zzaPe;
  private final Uri zzaPf;
  private final String zzaQA;
  private final boolean zzaQB;
  private final float zzaQC;
  private final int zzaQD;
  private final long zzaQE;
  private final List<Integer> zzaQF;
  private final String zzaQG;
  private final List<String> zzaQH;
  private final Map<Integer, String> zzaQI;
  private final TimeZone zzaQJ;
  private Locale zzaQr;
  private final Bundle zzaQw;
  @Deprecated
  private final PlaceLocalization zzaQx;
  private final float zzaQy;
  private final LatLngBounds zzaQz;
  private final String zzawc;
  private final String zzyv;
  
  PlaceImpl(int paramInt1, String paramString1, List<Integer> paramList1, List<Integer> paramList2, Bundle paramBundle, String paramString2, String paramString3, String paramString4, String paramString5, List<String> paramList, LatLng paramLatLng, float paramFloat1, LatLngBounds paramLatLngBounds, String paramString6, Uri paramUri, boolean paramBoolean, float paramFloat2, int paramInt2, long paramLong, PlaceLocalization paramPlaceLocalization)
  {
    this.mVersionCode = paramInt1;
    this.zzyv = paramString1;
    this.zzaPd = Collections.unmodifiableList(paramList1);
    this.zzaQF = paramList2;
    if (paramBundle != null)
    {
      this.zzaQw = paramBundle;
      this.mName = paramString2;
      this.zzawc = paramString3;
      this.zzaPe = paramString4;
      this.zzaQG = paramString5;
      if (paramList == null) {
        break label176;
      }
      label68:
      this.zzaQH = paramList;
      this.zzaPc = paramLatLng;
      this.zzaQy = paramFloat1;
      this.zzaQz = paramLatLngBounds;
      if (paramString6 == null) {
        break label184;
      }
    }
    for (;;)
    {
      this.zzaQA = paramString6;
      this.zzaPf = paramUri;
      this.zzaQB = paramBoolean;
      this.zzaQC = paramFloat2;
      this.zzaQD = paramInt2;
      this.zzaQE = paramLong;
      this.zzaQI = Collections.unmodifiableMap(new HashMap());
      this.zzaQJ = null;
      this.zzaQr = null;
      this.zzaQx = paramPlaceLocalization;
      return;
      paramBundle = new Bundle();
      break;
      label176:
      paramList = Collections.emptyList();
      break label68;
      label184:
      paramString6 = "UTC";
    }
  }
  
  public int describeContents()
  {
    zzl localzzl = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PlaceImpl)) {
        return false;
      }
      paramObject = (PlaceImpl)paramObject;
    } while ((this.zzyv.equals(((PlaceImpl)paramObject).zzyv)) && (zzw.equal(this.zzaQr, ((PlaceImpl)paramObject).zzaQr)) && (this.zzaQE == ((PlaceImpl)paramObject).zzaQE));
    return false;
  }
  
  public String getAddress()
  {
    return this.zzawc;
  }
  
  public CharSequence getAttributions()
  {
    return zzc.zzj(this.zzaQH);
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public LatLng getLatLng()
  {
    return this.zzaPc;
  }
  
  public Locale getLocale()
  {
    return this.zzaQr;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getPhoneNumber()
  {
    return this.zzaPe;
  }
  
  public List<Integer> getPlaceTypes()
  {
    return this.zzaPd;
  }
  
  public int getPriceLevel()
  {
    return this.zzaQD;
  }
  
  public float getRating()
  {
    return this.zzaQC;
  }
  
  public LatLngBounds getViewport()
  {
    return this.zzaQz;
  }
  
  public Uri getWebsiteUri()
  {
    return this.zzaPf;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzyv, this.zzaQr, Long.valueOf(this.zzaQE) });
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public void setLocale(Locale paramLocale)
  {
    this.zzaQr = paramLocale;
  }
  
  @SuppressLint({"DefaultLocale"})
  public String toString()
  {
    return zzw.zzy(this).zzg("id", this.zzyv).zzg("placeTypes", this.zzaPd).zzg("locale", this.zzaQr).zzg("name", this.mName).zzg("address", this.zzawc).zzg("phoneNumber", this.zzaPe).zzg("latlng", this.zzaPc).zzg("viewport", this.zzaQz).zzg("websiteUri", this.zzaPf).zzg("isPermanentlyClosed", Boolean.valueOf(this.zzaQB)).zzg("priceLevel", Integer.valueOf(this.zzaQD)).zzg("timestampSecs", Long.valueOf(this.zzaQE)).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzl localzzl = CREATOR;
    zzl.zza(this, paramParcel, paramInt);
  }
  
  public List<Integer> zzzn()
  {
    return this.zzaQF;
  }
  
  public float zzzo()
  {
    return this.zzaQy;
  }
  
  public String zzzp()
  {
    return this.zzaQG;
  }
  
  public List<String> zzzq()
  {
    return this.zzaQH;
  }
  
  public boolean zzzr()
  {
    return this.zzaQB;
  }
  
  public long zzzs()
  {
    return this.zzaQE;
  }
  
  public Bundle zzzt()
  {
    return this.zzaQw;
  }
  
  public String zzzu()
  {
    return this.zzaQA;
  }
  
  @Deprecated
  public PlaceLocalization zzzv()
  {
    return this.zzaQx;
  }
  
  public Place zzzw()
  {
    return this;
  }
  
  public static class zza
  {
    private String mName;
    private int mVersionCode = 0;
    private LatLng zzaPc;
    private String zzaPe;
    private Uri zzaPf;
    private String zzaQA;
    private boolean zzaQB;
    private float zzaQC;
    private int zzaQD;
    private long zzaQE;
    private String zzaQG;
    private List<String> zzaQH;
    private Bundle zzaQK;
    private List<Integer> zzaQL;
    private float zzaQy;
    private LatLngBounds zzaQz;
    private String zzawc;
    private String zzyv;
    
    public zza zza(LatLng paramLatLng)
    {
      this.zzaPc = paramLatLng;
      return this;
    }
    
    public zza zza(LatLngBounds paramLatLngBounds)
    {
      this.zzaQz = paramLatLngBounds;
      return this;
    }
    
    public zza zzan(boolean paramBoolean)
    {
      this.zzaQB = paramBoolean;
      return this;
    }
    
    public zza zzem(String paramString)
    {
      this.zzyv = paramString;
      return this;
    }
    
    public zza zzen(String paramString)
    {
      this.mName = paramString;
      return this;
    }
    
    public zza zzeo(String paramString)
    {
      this.zzawc = paramString;
      return this;
    }
    
    public zza zzep(String paramString)
    {
      this.zzaPe = paramString;
      return this;
    }
    
    public zza zzf(float paramFloat)
    {
      this.zzaQy = paramFloat;
      return this;
    }
    
    public zza zzg(float paramFloat)
    {
      this.zzaQC = paramFloat;
      return this;
    }
    
    public zza zzhX(int paramInt)
    {
      this.zzaQD = paramInt;
      return this;
    }
    
    public zza zzo(Uri paramUri)
    {
      this.zzaPf = paramUri;
      return this;
    }
    
    public zza zzx(List<Integer> paramList)
    {
      this.zzaQL = paramList;
      return this;
    }
    
    public zza zzy(List<String> paramList)
    {
      this.zzaQH = paramList;
      return this;
    }
    
    public PlaceImpl zzzx()
    {
      return new PlaceImpl(this.mVersionCode, this.zzyv, this.zzaQL, Collections.emptyList(), this.zzaQK, this.mName, this.zzawc, this.zzaPe, this.zzaQG, this.zzaQH, this.zzaPc, this.zzaQy, this.zzaQz, this.zzaQA, this.zzaPf, this.zzaQB, this.zzaQC, this.zzaQD, this.zzaQE, PlaceLocalization.zza(this.mName, this.zzawc, this.zzaPe, this.zzaQG, this.zzaQH));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\location\places\internal\PlaceImpl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */