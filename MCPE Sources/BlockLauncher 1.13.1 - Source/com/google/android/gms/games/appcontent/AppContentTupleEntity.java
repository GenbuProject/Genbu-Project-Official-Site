package com.google.android.gms.games.appcontent;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class AppContentTupleEntity
  implements SafeParcelable, AppContentTuple
{
  public static final AppContentTupleEntityCreator CREATOR = new AppContentTupleEntityCreator();
  private final String mName;
  private final String mValue;
  private final int mVersionCode;
  
  AppContentTupleEntity(int paramInt, String paramString1, String paramString2)
  {
    this.mVersionCode = paramInt;
    this.mName = paramString1;
    this.mValue = paramString2;
  }
  
  public AppContentTupleEntity(AppContentTuple paramAppContentTuple)
  {
    this.mVersionCode = 1;
    this.mName = paramAppContentTuple.getName();
    this.mValue = paramAppContentTuple.getValue();
  }
  
  static int zza(AppContentTuple paramAppContentTuple)
  {
    return zzw.hashCode(new Object[] { paramAppContentTuple.getName(), paramAppContentTuple.getValue() });
  }
  
  static boolean zza(AppContentTuple paramAppContentTuple, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppContentTuple)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppContentTuple == paramObject);
      paramObject = (AppContentTuple)paramObject;
      if (!zzw.equal(((AppContentTuple)paramObject).getName(), paramAppContentTuple.getName())) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((AppContentTuple)paramObject).getValue(), paramAppContentTuple.getValue()));
    return false;
  }
  
  static String zzb(AppContentTuple paramAppContentTuple)
  {
    return zzw.zzy(paramAppContentTuple).zzg("Name", paramAppContentTuple.getName()).zzg("Value", paramAppContentTuple.getValue()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getValue()
  {
    return this.mValue;
  }
  
  public int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zza(this);
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public String toString()
  {
    return zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    AppContentTupleEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public AppContentTuple zzwq()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\appcontent\AppContentTupleEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */