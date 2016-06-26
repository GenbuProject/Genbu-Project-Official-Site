package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.List;

public final class AppContentCardEntity
  implements SafeParcelable, AppContentCard
{
  public static final AppContentCardEntityCreator CREATOR = new AppContentCardEntityCreator();
  private final ArrayList<AppContentActionEntity> mActions;
  private final Bundle mExtras;
  private final int mVersionCode;
  private final String zzJN;
  private final ArrayList<AppContentAnnotationEntity> zzaDF;
  private final int zzaDG;
  private final String zzaDH;
  private final int zzaDI;
  private final ArrayList<AppContentConditionEntity> zzaDv;
  private final String zzaDw;
  private final String zzapg;
  private final String zzaxl;
  private final String zzyv;
  
  AppContentCardEntity(int paramInt1, ArrayList<AppContentActionEntity> paramArrayList, ArrayList<AppContentAnnotationEntity> paramArrayList1, ArrayList<AppContentConditionEntity> paramArrayList2, String paramString1, int paramInt2, String paramString2, Bundle paramBundle, String paramString3, String paramString4, int paramInt3, String paramString5, String paramString6)
  {
    this.mVersionCode = paramInt1;
    this.mActions = paramArrayList;
    this.zzaDF = paramArrayList1;
    this.zzaDv = paramArrayList2;
    this.zzaDw = paramString1;
    this.zzaDG = paramInt2;
    this.zzaxl = paramString2;
    this.mExtras = paramBundle;
    this.zzyv = paramString6;
    this.zzaDH = paramString3;
    this.zzapg = paramString4;
    this.zzaDI = paramInt3;
    this.zzJN = paramString5;
  }
  
  public AppContentCardEntity(AppContentCard paramAppContentCard)
  {
    this.mVersionCode = 4;
    this.zzaDw = paramAppContentCard.zzvQ();
    this.zzaDG = paramAppContentCard.zzwb();
    this.zzaxl = paramAppContentCard.getDescription();
    this.mExtras = paramAppContentCard.getExtras();
    this.zzyv = paramAppContentCard.getId();
    this.zzapg = paramAppContentCard.getTitle();
    this.zzaDH = paramAppContentCard.zzwc();
    this.zzaDI = paramAppContentCard.zzwd();
    this.zzJN = paramAppContentCard.getType();
    List localList = paramAppContentCard.getActions();
    int k = localList.size();
    this.mActions = new ArrayList(k);
    int i = 0;
    while (i < k)
    {
      this.mActions.add((AppContentActionEntity)((AppContentAction)localList.get(i)).freeze());
      i += 1;
    }
    localList = paramAppContentCard.zzwa();
    k = localList.size();
    this.zzaDF = new ArrayList(k);
    i = 0;
    while (i < k)
    {
      this.zzaDF.add((AppContentAnnotationEntity)((AppContentAnnotation)localList.get(i)).freeze());
      i += 1;
    }
    paramAppContentCard = paramAppContentCard.zzvP();
    k = paramAppContentCard.size();
    this.zzaDv = new ArrayList(k);
    i = j;
    while (i < k)
    {
      this.zzaDv.add((AppContentConditionEntity)((AppContentCondition)paramAppContentCard.get(i)).freeze());
      i += 1;
    }
  }
  
  static int zza(AppContentCard paramAppContentCard)
  {
    return zzw.hashCode(new Object[] { paramAppContentCard.getActions(), paramAppContentCard.zzwa(), paramAppContentCard.zzvP(), paramAppContentCard.zzvQ(), Integer.valueOf(paramAppContentCard.zzwb()), paramAppContentCard.getDescription(), paramAppContentCard.getExtras(), paramAppContentCard.getId(), paramAppContentCard.zzwc(), paramAppContentCard.getTitle(), Integer.valueOf(paramAppContentCard.zzwd()), paramAppContentCard.getType() });
  }
  
  static boolean zza(AppContentCard paramAppContentCard, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppContentCard)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppContentCard == paramObject);
      paramObject = (AppContentCard)paramObject;
      if ((!zzw.equal(((AppContentCard)paramObject).getActions(), paramAppContentCard.getActions())) || (!zzw.equal(((AppContentCard)paramObject).zzwa(), paramAppContentCard.zzwa())) || (!zzw.equal(((AppContentCard)paramObject).zzvP(), paramAppContentCard.zzvP())) || (!zzw.equal(((AppContentCard)paramObject).zzvQ(), paramAppContentCard.zzvQ())) || (!zzw.equal(Integer.valueOf(((AppContentCard)paramObject).zzwb()), Integer.valueOf(paramAppContentCard.zzwb()))) || (!zzw.equal(((AppContentCard)paramObject).getDescription(), paramAppContentCard.getDescription())) || (!zzw.equal(((AppContentCard)paramObject).getExtras(), paramAppContentCard.getExtras())) || (!zzw.equal(((AppContentCard)paramObject).getId(), paramAppContentCard.getId())) || (!zzw.equal(((AppContentCard)paramObject).zzwc(), paramAppContentCard.zzwc())) || (!zzw.equal(((AppContentCard)paramObject).getTitle(), paramAppContentCard.getTitle())) || (!zzw.equal(Integer.valueOf(((AppContentCard)paramObject).zzwd()), Integer.valueOf(paramAppContentCard.zzwd())))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((AppContentCard)paramObject).getType(), paramAppContentCard.getType()));
    return false;
  }
  
  static String zzb(AppContentCard paramAppContentCard)
  {
    return zzw.zzy(paramAppContentCard).zzg("Actions", paramAppContentCard.getActions()).zzg("Annotations", paramAppContentCard.zzwa()).zzg("Conditions", paramAppContentCard.zzvP()).zzg("ContentDescription", paramAppContentCard.zzvQ()).zzg("CurrentSteps", Integer.valueOf(paramAppContentCard.zzwb())).zzg("Description", paramAppContentCard.getDescription()).zzg("Extras", paramAppContentCard.getExtras()).zzg("Id", paramAppContentCard.getId()).zzg("Subtitle", paramAppContentCard.zzwc()).zzg("Title", paramAppContentCard.getTitle()).zzg("TotalSteps", Integer.valueOf(paramAppContentCard.zzwd())).zzg("Type", paramAppContentCard.getType()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public List<AppContentAction> getActions()
  {
    return new ArrayList(this.mActions);
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public String getTitle()
  {
    return this.zzapg;
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
    AppContentCardEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public List<AppContentCondition> zzvP()
  {
    return new ArrayList(this.zzaDv);
  }
  
  public String zzvQ()
  {
    return this.zzaDw;
  }
  
  public List<AppContentAnnotation> zzwa()
  {
    return new ArrayList(this.zzaDF);
  }
  
  public int zzwb()
  {
    return this.zzaDG;
  }
  
  public String zzwc()
  {
    return this.zzaDH;
  }
  
  public int zzwd()
  {
    return this.zzaDI;
  }
  
  public AppContentCard zzwe()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentCardEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */