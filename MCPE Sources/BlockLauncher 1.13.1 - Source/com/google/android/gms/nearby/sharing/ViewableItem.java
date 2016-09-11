package com.google.android.gms.nearby.sharing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.Arrays;

public class ViewableItem
  implements SafeParcelable
{
  public static final Parcelable.Creator<ViewableItem> CREATOR = new zzf();
  private final int versionCode;
  private String[] zzbdj;
  
  private ViewableItem()
  {
    this.versionCode = 1;
  }
  
  ViewableItem(int paramInt, String[] paramArrayOfString)
  {
    this.versionCode = paramInt;
    this.zzbdj = paramArrayOfString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {
      return true;
    }
    if (!(paramObject instanceof ViewableItem)) {
      return false;
    }
    paramObject = (ViewableItem)paramObject;
    return zzw.equal(this.zzbdj, ((ViewableItem)paramObject).zzbdj);
  }
  
  int getVersionCode()
  {
    return this.versionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzbdj });
  }
  
  public String toString()
  {
    return "ViewableItem[uris=" + Arrays.toString(this.zzbdj) + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  public String[] zzEN()
  {
    return this.zzbdj;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\nearby\sharing\ViewableItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */