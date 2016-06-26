package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.List;

public final class AppContentActionEntity
  implements SafeParcelable, AppContentAction
{
  public static final AppContentActionEntityCreator CREATOR = new AppContentActionEntityCreator();
  private final Bundle mExtras;
  private final int mVersionCode;
  private final String zzJN;
  private final ArrayList<AppContentConditionEntity> zzaDv;
  private final String zzaDw;
  private final AppContentAnnotationEntity zzaDx;
  private final String zzaDy;
  private final String zzyv;
  
  AppContentActionEntity(int paramInt, ArrayList<AppContentConditionEntity> paramArrayList, String paramString1, Bundle paramBundle, String paramString2, String paramString3, AppContentAnnotationEntity paramAppContentAnnotationEntity, String paramString4)
  {
    this.mVersionCode = paramInt;
    this.zzaDx = paramAppContentAnnotationEntity;
    this.zzaDv = paramArrayList;
    this.zzaDw = paramString1;
    this.mExtras = paramBundle;
    this.zzyv = paramString3;
    this.zzaDy = paramString4;
    this.zzJN = paramString2;
  }
  
  public AppContentActionEntity(AppContentAction paramAppContentAction)
  {
    this.mVersionCode = 5;
    this.zzaDx = ((AppContentAnnotationEntity)paramAppContentAction.zzvO().freeze());
    this.zzaDw = paramAppContentAction.zzvQ();
    this.mExtras = paramAppContentAction.getExtras();
    this.zzyv = paramAppContentAction.getId();
    this.zzaDy = paramAppContentAction.zzvR();
    this.zzJN = paramAppContentAction.getType();
    paramAppContentAction = paramAppContentAction.zzvP();
    int j = paramAppContentAction.size();
    this.zzaDv = new ArrayList(j);
    int i = 0;
    while (i < j)
    {
      this.zzaDv.add((AppContentConditionEntity)((AppContentCondition)paramAppContentAction.get(i)).freeze());
      i += 1;
    }
  }
  
  static int zza(AppContentAction paramAppContentAction)
  {
    return zzw.hashCode(new Object[] { paramAppContentAction.zzvO(), paramAppContentAction.zzvP(), paramAppContentAction.zzvQ(), paramAppContentAction.getExtras(), paramAppContentAction.getId(), paramAppContentAction.zzvR(), paramAppContentAction.getType() });
  }
  
  static boolean zza(AppContentAction paramAppContentAction, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppContentAction)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppContentAction == paramObject);
      paramObject = (AppContentAction)paramObject;
      if ((!zzw.equal(((AppContentAction)paramObject).zzvO(), paramAppContentAction.zzvO())) || (!zzw.equal(((AppContentAction)paramObject).zzvP(), paramAppContentAction.zzvP())) || (!zzw.equal(((AppContentAction)paramObject).zzvQ(), paramAppContentAction.zzvQ())) || (!zzw.equal(((AppContentAction)paramObject).getExtras(), paramAppContentAction.getExtras())) || (!zzw.equal(((AppContentAction)paramObject).getId(), paramAppContentAction.getId())) || (!zzw.equal(((AppContentAction)paramObject).zzvR(), paramAppContentAction.zzvR()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((AppContentAction)paramObject).getType(), paramAppContentAction.getType()));
    return false;
  }
  
  static String zzb(AppContentAction paramAppContentAction)
  {
    return zzw.zzy(paramAppContentAction).zzg("Annotation", paramAppContentAction.zzvO()).zzg("Conditions", paramAppContentAction.zzvP()).zzg("ContentDescription", paramAppContentAction.zzvQ()).zzg("Extras", paramAppContentAction.getExtras()).zzg("Id", paramAppContentAction.getId()).zzg("OverflowText", paramAppContentAction.zzvR()).zzg("Type", paramAppContentAction.getType()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public String getType()
  {
    return this.zzJN;
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
    AppContentActionEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public AppContentAnnotation zzvO()
  {
    return this.zzaDx;
  }
  
  public List<AppContentCondition> zzvP()
  {
    return new ArrayList(this.zzaDv);
  }
  
  public String zzvQ()
  {
    return this.zzaDw;
  }
  
  public String zzvR()
  {
    return this.zzaDy;
  }
  
  public AppContentAction zzvS()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentActionEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */