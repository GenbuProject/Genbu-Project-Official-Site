package com.google.android.gms.drive.events;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.DriveSpace;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public final class ChangesAvailableOptions
  implements SafeParcelable
{
  public static final Parcelable.Creator<ChangesAvailableOptions> CREATOR = new zzd();
  final int mVersionCode;
  final boolean zzapA;
  final List<DriveSpace> zzapB;
  private final Set<DriveSpace> zzapC;
  final int zzapz;
  
  ChangesAvailableOptions(int paramInt1, int paramInt2, boolean paramBoolean, List<DriveSpace> paramList) {}
  
  private ChangesAvailableOptions(int paramInt1, int paramInt2, boolean paramBoolean, List<DriveSpace> paramList, Set<DriveSpace> paramSet)
  {
    this.mVersionCode = paramInt1;
    this.zzapz = paramInt2;
    this.zzapA = paramBoolean;
    this.zzapB = paramList;
    this.zzapC = paramSet;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == this);
      paramObject = (ChangesAvailableOptions)paramObject;
      if ((!zzw.equal(this.zzapC, ((ChangesAvailableOptions)paramObject).zzapC)) || (this.zzapz != ((ChangesAvailableOptions)paramObject).zzapz)) {
        break;
      }
      bool1 = bool2;
    } while (this.zzapA == ((ChangesAvailableOptions)paramObject).zzapA);
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzapC, Integer.valueOf(this.zzapz), Boolean.valueOf(this.zzapA) });
  }
  
  public String toString()
  {
    return String.format(Locale.US, "ChangesAvailableOptions[ChangesSizeLimit=%d, Repeats=%s, Spaces=%s]", new Object[] { Integer.valueOf(this.zzapz), Boolean.valueOf(this.zzapA), this.zzapB });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\ChangesAvailableOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */