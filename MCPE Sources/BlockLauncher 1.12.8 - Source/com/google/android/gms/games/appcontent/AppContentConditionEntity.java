package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class AppContentConditionEntity
  implements SafeParcelable, AppContentCondition
{
  public static final AppContentConditionEntityCreator CREATOR = new AppContentConditionEntityCreator();
  private final int mVersionCode;
  private final String zzaDJ;
  private final String zzaDK;
  private final String zzaDL;
  private final Bundle zzaDM;
  
  AppContentConditionEntity(int paramInt, String paramString1, String paramString2, String paramString3, Bundle paramBundle)
  {
    this.mVersionCode = paramInt;
    this.zzaDJ = paramString1;
    this.zzaDK = paramString2;
    this.zzaDL = paramString3;
    this.zzaDM = paramBundle;
  }
  
  public AppContentConditionEntity(AppContentCondition paramAppContentCondition)
  {
    this.mVersionCode = 1;
    this.zzaDJ = paramAppContentCondition.zzwf();
    this.zzaDK = paramAppContentCondition.zzwg();
    this.zzaDL = paramAppContentCondition.zzwh();
    this.zzaDM = paramAppContentCondition.zzwi();
  }
  
  static int zza(AppContentCondition paramAppContentCondition)
  {
    return zzw.hashCode(new Object[] { paramAppContentCondition.zzwf(), paramAppContentCondition.zzwg(), paramAppContentCondition.zzwh(), paramAppContentCondition.zzwi() });
  }
  
  static boolean zza(AppContentCondition paramAppContentCondition, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppContentCondition)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppContentCondition == paramObject);
      paramObject = (AppContentCondition)paramObject;
      if ((!zzw.equal(((AppContentCondition)paramObject).zzwf(), paramAppContentCondition.zzwf())) || (!zzw.equal(((AppContentCondition)paramObject).zzwg(), paramAppContentCondition.zzwg())) || (!zzw.equal(((AppContentCondition)paramObject).zzwh(), paramAppContentCondition.zzwh()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((AppContentCondition)paramObject).zzwi(), paramAppContentCondition.zzwi()));
    return false;
  }
  
  static String zzb(AppContentCondition paramAppContentCondition)
  {
    return zzw.zzy(paramAppContentCondition).zzg("DefaultValue", paramAppContentCondition.zzwf()).zzg("ExpectedValue", paramAppContentCondition.zzwg()).zzg("Predicate", paramAppContentCondition.zzwh()).zzg("PredicateParameters", paramAppContentCondition.zzwi()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
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
    AppContentConditionEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public String zzwf()
  {
    return this.zzaDJ;
  }
  
  public String zzwg()
  {
    return this.zzaDK;
  }
  
  public String zzwh()
  {
    return this.zzaDL;
  }
  
  public Bundle zzwi()
  {
    return this.zzaDM;
  }
  
  public AppContentCondition zzwj()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentConditionEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */