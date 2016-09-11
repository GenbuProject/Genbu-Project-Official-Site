package com.google.android.gms.wallet.wobs;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.internal.zzmn;
import java.util.ArrayList;

public final class LabelValueRow
  implements SafeParcelable
{
  public static final Parcelable.Creator<LabelValueRow> CREATOR = new zzc();
  private final int mVersionCode;
  String zzbqF;
  String zzbqG;
  ArrayList<LabelValue> zzbqH;
  
  LabelValueRow()
  {
    this.mVersionCode = 1;
    this.zzbqH = zzmn.zzsa();
  }
  
  LabelValueRow(int paramInt, String paramString1, String paramString2, ArrayList<LabelValue> paramArrayList)
  {
    this.mVersionCode = paramInt;
    this.zzbqF = paramString1;
    this.zzbqG = paramString2;
    this.zzbqH = paramArrayList;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\wallet\wobs\LabelValueRow.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */