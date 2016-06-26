package com.google.android.gms.location.places;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmr;
import java.util.Set;

public final class UserDataType
  implements SafeParcelable
{
  public static final zzm CREATOR = new zzm();
  public static final UserDataType zzaPX = zzy("test_type", 1);
  public static final UserDataType zzaPY = zzy("labeled_place", 6);
  public static final UserDataType zzaPZ = zzy("here_content", 7);
  public static final Set<UserDataType> zzaQa = zzmr.zza(zzaPX, zzaPY, zzaPZ);
  final int mVersionCode;
  final String zzJN;
  final int zzaQb;
  
  UserDataType(int paramInt1, String paramString, int paramInt2)
  {
    zzx.zzcM(paramString);
    this.mVersionCode = paramInt1;
    this.zzJN = paramString;
    this.zzaQb = paramInt2;
  }
  
  private static UserDataType zzy(String paramString, int paramInt)
  {
    return new UserDataType(0, paramString, paramInt);
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
      if (!(paramObject instanceof UserDataType)) {
        return false;
      }
      paramObject = (UserDataType)paramObject;
    } while ((this.zzJN.equals(((UserDataType)paramObject).zzJN)) && (this.zzaQb == ((UserDataType)paramObject).zzaQb));
    return false;
  }
  
  public int hashCode()
  {
    return this.zzJN.hashCode();
  }
  
  public String toString()
  {
    return this.zzJN;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzm.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\location\places\UserDataType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */