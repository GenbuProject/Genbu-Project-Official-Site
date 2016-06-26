package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.zzd;

public class AncsNotificationParcelable
  implements SafeParcelable, zzd
{
  public static final Parcelable.Creator<AncsNotificationParcelable> CREATOR = new zzh();
  private int mId;
  final int mVersionCode;
  private String zzTJ;
  private String zzWQ;
  private final String zzaDH;
  private final String zzaUa;
  private final String zzaaH;
  private final String zzapg;
  private final String zzbrH;
  private byte zzbrI;
  private byte zzbrJ;
  private byte zzbrK;
  private byte zzbrL;
  
  AncsNotificationParcelable(int paramInt1, int paramInt2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, byte paramByte1, byte paramByte2, byte paramByte3, byte paramByte4, String paramString7)
  {
    this.mId = paramInt2;
    this.mVersionCode = paramInt1;
    this.zzaUa = paramString1;
    this.zzbrH = paramString2;
    this.zzaaH = paramString3;
    this.zzapg = paramString4;
    this.zzaDH = paramString5;
    this.zzWQ = paramString6;
    this.zzbrI = paramByte1;
    this.zzbrJ = paramByte2;
    this.zzbrK = paramByte3;
    this.zzbrL = paramByte4;
    this.zzTJ = paramString7;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (getClass() != paramObject.getClass())) {
        return false;
      }
      paramObject = (AncsNotificationParcelable)paramObject;
      if (this.zzbrL != ((AncsNotificationParcelable)paramObject).zzbrL) {
        return false;
      }
      if (this.zzbrK != ((AncsNotificationParcelable)paramObject).zzbrK) {
        return false;
      }
      if (this.zzbrJ != ((AncsNotificationParcelable)paramObject).zzbrJ) {
        return false;
      }
      if (this.zzbrI != ((AncsNotificationParcelable)paramObject).zzbrI) {
        return false;
      }
      if (this.mId != ((AncsNotificationParcelable)paramObject).mId) {
        return false;
      }
      if (this.mVersionCode != ((AncsNotificationParcelable)paramObject).mVersionCode) {
        return false;
      }
      if (!this.zzaUa.equals(((AncsNotificationParcelable)paramObject).zzaUa)) {
        return false;
      }
      if (this.zzbrH != null)
      {
        if (this.zzbrH.equals(((AncsNotificationParcelable)paramObject).zzbrH)) {}
      }
      else {
        while (((AncsNotificationParcelable)paramObject).zzbrH != null) {
          return false;
        }
      }
      if (!this.zzWQ.equals(((AncsNotificationParcelable)paramObject).zzWQ)) {
        return false;
      }
      if (!this.zzaaH.equals(((AncsNotificationParcelable)paramObject).zzaaH)) {
        return false;
      }
      if (!this.zzaDH.equals(((AncsNotificationParcelable)paramObject).zzaDH)) {
        return false;
      }
    } while (this.zzapg.equals(((AncsNotificationParcelable)paramObject).zzapg));
    return false;
  }
  
  public String getDisplayName()
  {
    if (this.zzWQ == null) {
      return this.zzaUa;
    }
    return this.zzWQ;
  }
  
  public int getId()
  {
    return this.mId;
  }
  
  public String getPackageName()
  {
    return this.zzTJ;
  }
  
  public String getTitle()
  {
    return this.zzapg;
  }
  
  public int hashCode()
  {
    int j = this.mVersionCode;
    int k = this.mId;
    int m = this.zzaUa.hashCode();
    if (this.zzbrH != null) {}
    for (int i = this.zzbrH.hashCode();; i = 0) {
      return ((((((((i + ((j * 31 + k) * 31 + m) * 31) * 31 + this.zzaaH.hashCode()) * 31 + this.zzapg.hashCode()) * 31 + this.zzaDH.hashCode()) * 31 + this.zzWQ.hashCode()) * 31 + this.zzbrI) * 31 + this.zzbrJ) * 31 + this.zzbrK) * 31 + this.zzbrL;
    }
  }
  
  public String toString()
  {
    return "AncsNotificationParcelable{mVersionCode=" + this.mVersionCode + ", mId=" + this.mId + ", mAppId='" + this.zzaUa + '\'' + ", mDateTime='" + this.zzbrH + '\'' + ", mNotificationText='" + this.zzaaH + '\'' + ", mTitle='" + this.zzapg + '\'' + ", mSubtitle='" + this.zzaDH + '\'' + ", mDisplayName='" + this.zzWQ + '\'' + ", mEventId=" + this.zzbrI + ", mEventFlags=" + this.zzbrJ + ", mCategoryId=" + this.zzbrK + ", mCategoryCount=" + this.zzbrL + '}';
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public String zzIB()
  {
    return this.zzbrH;
  }
  
  public String zzIC()
  {
    return this.zzaaH;
  }
  
  public byte zzID()
  {
    return this.zzbrI;
  }
  
  public byte zzIE()
  {
    return this.zzbrJ;
  }
  
  public byte zzIF()
  {
    return this.zzbrK;
  }
  
  public byte zzIG()
  {
    return this.zzbrL;
  }
  
  public String zzwK()
  {
    return this.zzaUa;
  }
  
  public String zzwc()
  {
    return this.zzaDH;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\AncsNotificationParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */