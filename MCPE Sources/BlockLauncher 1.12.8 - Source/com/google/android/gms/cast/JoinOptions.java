package com.google.android.gms.cast;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;

public class JoinOptions
  implements SafeParcelable
{
  public static final Parcelable.Creator<JoinOptions> CREATOR = new zzc();
  private final int mVersionCode;
  private int zzaaJ;
  
  public JoinOptions()
  {
    this(1, 0);
  }
  
  JoinOptions(int paramInt1, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzaaJ = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof JoinOptions)) {
        return false;
      }
      paramObject = (JoinOptions)paramObject;
    } while (this.zzaaJ == ((JoinOptions)paramObject).zzaaJ);
    return false;
  }
  
  public int getConnectionType()
  {
    return this.zzaaJ;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzaaJ) });
  }
  
  public String toString()
  {
    String str;
    switch (this.zzaaJ)
    {
    case 1: 
    default: 
      str = "UNKNOWN";
    }
    for (;;)
    {
      return String.format("joinOptions(connectionType=%s)", new Object[] { str });
      str = "STRONG";
      continue;
      str = "INVISIBLE";
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\JoinOptions.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */