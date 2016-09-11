package com.google.android.gms.vision.face.internal.client;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;

public class FaceSettingsParcel
  implements SafeParcelable
{
  public static final zzb CREATOR = new zzb();
  public int mode;
  public final int versionCode;
  public int zzbnV;
  public int zzbnW;
  public boolean zzbnX;
  public boolean zzbnY;
  public float zzbnZ;
  
  public FaceSettingsParcel()
  {
    this.versionCode = 2;
  }
  
  public FaceSettingsParcel(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean1, boolean paramBoolean2, float paramFloat)
  {
    this.versionCode = paramInt1;
    this.mode = paramInt2;
    this.zzbnV = paramInt3;
    this.zzbnW = paramInt4;
    this.zzbnX = paramBoolean1;
    this.zzbnY = paramBoolean2;
    this.zzbnZ = paramFloat;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\vision\face\internal\client\FaceSettingsParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */