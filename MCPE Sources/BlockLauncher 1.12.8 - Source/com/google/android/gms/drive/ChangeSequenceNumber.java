package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.internal.zzas;
import com.google.android.gms.internal.zzsu;

public class ChangeSequenceNumber
  implements SafeParcelable
{
  public static final Parcelable.Creator<ChangeSequenceNumber> CREATOR = new zza();
  final int mVersionCode;
  final long zzaot;
  final long zzaou;
  final long zzaov;
  private volatile String zzaow = null;
  
  ChangeSequenceNumber(int paramInt, long paramLong1, long paramLong2, long paramLong3)
  {
    if (paramLong1 != -1L)
    {
      bool1 = true;
      zzx.zzac(bool1);
      if (paramLong2 == -1L) {
        break label92;
      }
      bool1 = true;
      label40:
      zzx.zzac(bool1);
      if (paramLong3 == -1L) {
        break label98;
      }
    }
    label92:
    label98:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      zzx.zzac(bool1);
      this.mVersionCode = paramInt;
      this.zzaot = paramLong1;
      this.zzaou = paramLong2;
      this.zzaov = paramLong3;
      return;
      bool1 = false;
      break;
      bool1 = false;
      break label40;
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public final String encodeToString()
  {
    if (this.zzaow == null)
    {
      String str = Base64.encodeToString(zzsu(), 10);
      this.zzaow = ("ChangeSequenceNumber:" + str);
    }
    return this.zzaow;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ChangeSequenceNumber)) {}
    do
    {
      return false;
      paramObject = (ChangeSequenceNumber)paramObject;
    } while ((((ChangeSequenceNumber)paramObject).zzaou != this.zzaou) || (((ChangeSequenceNumber)paramObject).zzaov != this.zzaov) || (((ChangeSequenceNumber)paramObject).zzaot != this.zzaot));
    return true;
  }
  
  public int hashCode()
  {
    return (String.valueOf(this.zzaot) + String.valueOf(this.zzaou) + String.valueOf(this.zzaov)).hashCode();
  }
  
  public String toString()
  {
    return encodeToString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  final byte[] zzsu()
  {
    zzas localzzas = new zzas();
    localzzas.versionCode = this.mVersionCode;
    localzzas.zzarV = this.zzaot;
    localzzas.zzarW = this.zzaou;
    localzzas.zzarX = this.zzaov;
    return zzsu.toByteArray(localzzas);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\ChangeSequenceNumber.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */