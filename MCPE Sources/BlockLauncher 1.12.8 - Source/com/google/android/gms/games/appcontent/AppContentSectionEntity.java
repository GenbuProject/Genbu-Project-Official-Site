package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import java.util.ArrayList;
import java.util.List;

public final class AppContentSectionEntity
  implements SafeParcelable, AppContentSection
{
  public static final AppContentSectionEntityCreator CREATOR = new AppContentSectionEntityCreator();
  private final ArrayList<AppContentActionEntity> mActions;
  private final Bundle mExtras;
  private final int mVersionCode;
  private final String zzJN;
  private final ArrayList<AppContentAnnotationEntity> zzaDF;
  private final String zzaDH;
  private final ArrayList<AppContentCardEntity> zzaDO;
  private final String zzaDP;
  private final String zzaDw;
  private final String zzapg;
  private final String zzyv;
  
  AppContentSectionEntity(int paramInt, ArrayList<AppContentActionEntity> paramArrayList, ArrayList<AppContentCardEntity> paramArrayList1, String paramString1, Bundle paramBundle, String paramString2, String paramString3, String paramString4, String paramString5, String paramString6, ArrayList<AppContentAnnotationEntity> paramArrayList2)
  {
    this.mVersionCode = paramInt;
    this.mActions = paramArrayList;
    this.zzaDF = paramArrayList2;
    this.zzaDO = paramArrayList1;
    this.zzaDP = paramString6;
    this.zzaDw = paramString1;
    this.mExtras = paramBundle;
    this.zzyv = paramString5;
    this.zzaDH = paramString2;
    this.zzapg = paramString3;
    this.zzJN = paramString4;
  }
  
  public AppContentSectionEntity(AppContentSection paramAppContentSection)
  {
    this.mVersionCode = 5;
    this.zzaDP = paramAppContentSection.zzwl();
    this.zzaDw = paramAppContentSection.zzvQ();
    this.mExtras = paramAppContentSection.getExtras();
    this.zzyv = paramAppContentSection.getId();
    this.zzaDH = paramAppContentSection.zzwc();
    this.zzapg = paramAppContentSection.getTitle();
    this.zzJN = paramAppContentSection.getType();
    List localList = paramAppContentSection.getActions();
    int k = localList.size();
    this.mActions = new ArrayList(k);
    int i = 0;
    while (i < k)
    {
      this.mActions.add((AppContentActionEntity)((AppContentAction)localList.get(i)).freeze());
      i += 1;
    }
    localList = paramAppContentSection.zzwk();
    k = localList.size();
    this.zzaDO = new ArrayList(k);
    i = 0;
    while (i < k)
    {
      this.zzaDO.add((AppContentCardEntity)((AppContentCard)localList.get(i)).freeze());
      i += 1;
    }
    paramAppContentSection = paramAppContentSection.zzwa();
    k = paramAppContentSection.size();
    this.zzaDF = new ArrayList(k);
    i = j;
    while (i < k)
    {
      this.zzaDF.add((AppContentAnnotationEntity)((AppContentAnnotation)paramAppContentSection.get(i)).freeze());
      i += 1;
    }
  }
  
  static int zza(AppContentSection paramAppContentSection)
  {
    return zzw.hashCode(new Object[] { paramAppContentSection.getActions(), paramAppContentSection.zzwa(), paramAppContentSection.zzwk(), paramAppContentSection.zzwl(), paramAppContentSection.zzvQ(), paramAppContentSection.getExtras(), paramAppContentSection.getId(), paramAppContentSection.zzwc(), paramAppContentSection.getTitle(), paramAppContentSection.getType() });
  }
  
  static boolean zza(AppContentSection paramAppContentSection, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppContentSection)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppContentSection == paramObject);
      paramObject = (AppContentSection)paramObject;
      if ((!zzw.equal(((AppContentSection)paramObject).getActions(), paramAppContentSection.getActions())) || (!zzw.equal(((AppContentSection)paramObject).zzwa(), paramAppContentSection.zzwa())) || (!zzw.equal(((AppContentSection)paramObject).zzwk(), paramAppContentSection.zzwk())) || (!zzw.equal(((AppContentSection)paramObject).zzwl(), paramAppContentSection.zzwl())) || (!zzw.equal(((AppContentSection)paramObject).zzvQ(), paramAppContentSection.zzvQ())) || (!zzw.equal(((AppContentSection)paramObject).getExtras(), paramAppContentSection.getExtras())) || (!zzw.equal(((AppContentSection)paramObject).getId(), paramAppContentSection.getId())) || (!zzw.equal(((AppContentSection)paramObject).zzwc(), paramAppContentSection.zzwc())) || (!zzw.equal(((AppContentSection)paramObject).getTitle(), paramAppContentSection.getTitle()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((AppContentSection)paramObject).getType(), paramAppContentSection.getType()));
    return false;
  }
  
  static String zzb(AppContentSection paramAppContentSection)
  {
    return zzw.zzy(paramAppContentSection).zzg("Actions", paramAppContentSection.getActions()).zzg("Annotations", paramAppContentSection.zzwa()).zzg("Cards", paramAppContentSection.zzwk()).zzg("CardType", paramAppContentSection.zzwl()).zzg("ContentDescription", paramAppContentSection.zzvQ()).zzg("Extras", paramAppContentSection.getExtras()).zzg("Id", paramAppContentSection.getId()).zzg("Subtitle", paramAppContentSection.zzwc()).zzg("Title", paramAppContentSection.getTitle()).zzg("Type", paramAppContentSection.getType()).toString();
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
    AppContentSectionEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public String zzvQ()
  {
    return this.zzaDw;
  }
  
  public List<AppContentAnnotation> zzwa()
  {
    return new ArrayList(this.zzaDF);
  }
  
  public String zzwc()
  {
    return this.zzaDH;
  }
  
  public List<AppContentCard> zzwk()
  {
    return new ArrayList(this.zzaDO);
  }
  
  public String zzwl()
  {
    return this.zzaDP;
  }
  
  public AppContentSection zzwm()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentSectionEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */