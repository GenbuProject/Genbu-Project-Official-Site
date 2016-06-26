package com.google.android.gms.drive.realtime.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class ParcelableCollaborator
  implements SafeParcelable
{
  public static final Parcelable.Creator<ParcelableCollaborator> CREATOR = new zzq();
  final int mVersionCode;
  final String zzLq;
  final String zzWQ;
  final boolean zzaeW;
  final boolean zzauI;
  final String zzauJ;
  final String zzauK;
  final String zzrG;
  
  ParcelableCollaborator(int paramInt, boolean paramBoolean1, boolean paramBoolean2, String paramString1, String paramString2, String paramString3, String paramString4, String paramString5)
  {
    this.mVersionCode = paramInt;
    this.zzauI = paramBoolean1;
    this.zzaeW = paramBoolean2;
    this.zzLq = paramString1;
    this.zzrG = paramString2;
    this.zzWQ = paramString3;
    this.zzauJ = paramString4;
    this.zzauK = paramString5;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (!(paramObject instanceof ParcelableCollaborator)) {
      return false;
    }
    paramObject = (ParcelableCollaborator)paramObject;
    return this.zzLq.equals(((ParcelableCollaborator)paramObject).zzLq);
  }
  
  public int hashCode()
  {
    return this.zzLq.hashCode();
  }
  
  public String toString()
  {
    return "Collaborator [isMe=" + this.zzauI + ", isAnonymous=" + this.zzaeW + ", sessionId=" + this.zzLq + ", userId=" + this.zzrG + ", displayName=" + this.zzWQ + ", color=" + this.zzauJ + ", photoUrl=" + this.zzauK + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzq.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\realtime\internal\ParcelableCollaborator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */