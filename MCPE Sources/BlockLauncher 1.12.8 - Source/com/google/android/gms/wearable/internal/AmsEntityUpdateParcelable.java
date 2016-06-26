package com.google.android.gms.wearable.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.wearable.zzb;

public class AmsEntityUpdateParcelable
  implements SafeParcelable, zzb
{
  public static final Parcelable.Creator<AmsEntityUpdateParcelable> CREATOR = new zzf();
  private final String mValue;
  final int mVersionCode;
  private byte zzbrF;
  private final byte zzbrG;
  
  AmsEntityUpdateParcelable(int paramInt, byte paramByte1, byte paramByte2, String paramString)
  {
    this.zzbrF = paramByte1;
    this.mVersionCode = paramInt;
    this.zzbrG = paramByte2;
    this.mValue = paramString;
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
      paramObject = (AmsEntityUpdateParcelable)paramObject;
      if (this.zzbrF != ((AmsEntityUpdateParcelable)paramObject).zzbrF) {
        return false;
      }
      if (this.mVersionCode != ((AmsEntityUpdateParcelable)paramObject).mVersionCode) {
        return false;
      }
      if (this.zzbrG != ((AmsEntityUpdateParcelable)paramObject).zzbrG) {
        return false;
      }
    } while (this.mValue.equals(((AmsEntityUpdateParcelable)paramObject).mValue));
    return false;
  }
  
  public String getValue()
  {
    return this.mValue;
  }
  
  public int hashCode()
  {
    return ((this.mVersionCode * 31 + this.zzbrF) * 31 + this.zzbrG) * 31 + this.mValue.hashCode();
  }
  
  public String toString()
  {
    return "AmsEntityUpdateParcelable{mVersionCode=" + this.mVersionCode + ", mEntityId=" + this.zzbrF + ", mAttributeId=" + this.zzbrG + ", mValue='" + this.mValue + '\'' + '}';
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  public byte zzIA()
  {
    return this.zzbrG;
  }
  
  public byte zzIz()
  {
    return this.zzbrF;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\AmsEntityUpdateParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */