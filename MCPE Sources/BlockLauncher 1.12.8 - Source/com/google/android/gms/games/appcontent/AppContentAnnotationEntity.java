package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;

public final class AppContentAnnotationEntity
  implements SafeParcelable, AppContentAnnotation
{
  public static final AppContentAnnotationEntityCreator CREATOR = new AppContentAnnotationEntityCreator();
  private final int mVersionCode;
  private final String zzaDA;
  private final String zzaDB;
  private final int zzaDC;
  private final int zzaDD;
  private final Bundle zzaDE;
  private final Uri zzaDz;
  private final String zzapg;
  private final String zzaxl;
  private final String zzyv;
  
  AppContentAnnotationEntity(int paramInt1, String paramString1, Uri paramUri, String paramString2, String paramString3, String paramString4, String paramString5, int paramInt2, int paramInt3, Bundle paramBundle)
  {
    this.mVersionCode = paramInt1;
    this.zzaxl = paramString1;
    this.zzyv = paramString3;
    this.zzaDB = paramString5;
    this.zzaDC = paramInt2;
    this.zzaDz = paramUri;
    this.zzaDD = paramInt3;
    this.zzaDA = paramString4;
    this.zzaDE = paramBundle;
    this.zzapg = paramString2;
  }
  
  public AppContentAnnotationEntity(AppContentAnnotation paramAppContentAnnotation)
  {
    this.mVersionCode = 4;
    this.zzaxl = paramAppContentAnnotation.getDescription();
    this.zzyv = paramAppContentAnnotation.getId();
    this.zzaDB = paramAppContentAnnotation.zzvT();
    this.zzaDC = paramAppContentAnnotation.zzvU();
    this.zzaDz = paramAppContentAnnotation.zzvV();
    this.zzaDD = paramAppContentAnnotation.zzvX();
    this.zzaDA = paramAppContentAnnotation.zzvY();
    this.zzaDE = paramAppContentAnnotation.zzvW();
    this.zzapg = paramAppContentAnnotation.getTitle();
  }
  
  static int zza(AppContentAnnotation paramAppContentAnnotation)
  {
    return zzw.hashCode(new Object[] { paramAppContentAnnotation.getDescription(), paramAppContentAnnotation.getId(), paramAppContentAnnotation.zzvT(), Integer.valueOf(paramAppContentAnnotation.zzvU()), paramAppContentAnnotation.zzvV(), Integer.valueOf(paramAppContentAnnotation.zzvX()), paramAppContentAnnotation.zzvY(), paramAppContentAnnotation.zzvW(), paramAppContentAnnotation.getTitle() });
  }
  
  static boolean zza(AppContentAnnotation paramAppContentAnnotation, Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if (!(paramObject instanceof AppContentAnnotation)) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramAppContentAnnotation == paramObject);
      paramObject = (AppContentAnnotation)paramObject;
      if ((!zzw.equal(((AppContentAnnotation)paramObject).getDescription(), paramAppContentAnnotation.getDescription())) || (!zzw.equal(((AppContentAnnotation)paramObject).getId(), paramAppContentAnnotation.getId())) || (!zzw.equal(((AppContentAnnotation)paramObject).zzvT(), paramAppContentAnnotation.zzvT())) || (!zzw.equal(Integer.valueOf(((AppContentAnnotation)paramObject).zzvU()), Integer.valueOf(paramAppContentAnnotation.zzvU()))) || (!zzw.equal(((AppContentAnnotation)paramObject).zzvV(), paramAppContentAnnotation.zzvV())) || (!zzw.equal(Integer.valueOf(((AppContentAnnotation)paramObject).zzvX()), Integer.valueOf(paramAppContentAnnotation.zzvX()))) || (!zzw.equal(((AppContentAnnotation)paramObject).zzvY(), paramAppContentAnnotation.zzvY())) || (!zzw.equal(((AppContentAnnotation)paramObject).zzvW(), paramAppContentAnnotation.zzvW()))) {
        break;
      }
      bool1 = bool2;
    } while (zzw.equal(((AppContentAnnotation)paramObject).getTitle(), paramAppContentAnnotation.getTitle()));
    return false;
  }
  
  static String zzb(AppContentAnnotation paramAppContentAnnotation)
  {
    return zzw.zzy(paramAppContentAnnotation).zzg("Description", paramAppContentAnnotation.getDescription()).zzg("Id", paramAppContentAnnotation.getId()).zzg("ImageDefaultId", paramAppContentAnnotation.zzvT()).zzg("ImageHeight", Integer.valueOf(paramAppContentAnnotation.zzvU())).zzg("ImageUri", paramAppContentAnnotation.zzvV()).zzg("ImageWidth", Integer.valueOf(paramAppContentAnnotation.zzvX())).zzg("LayoutSlot", paramAppContentAnnotation.zzvY()).zzg("Modifiers", paramAppContentAnnotation.zzvW()).zzg("Title", paramAppContentAnnotation.getTitle()).toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return zza(this, paramObject);
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public String getTitle()
  {
    return this.zzapg;
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
    AppContentAnnotationEntityCreator.zza(this, paramParcel, paramInt);
  }
  
  public String zzvT()
  {
    return this.zzaDB;
  }
  
  public int zzvU()
  {
    return this.zzaDC;
  }
  
  public Uri zzvV()
  {
    return this.zzaDz;
  }
  
  public Bundle zzvW()
  {
    return this.zzaDE;
  }
  
  public int zzvX()
  {
    return this.zzaDD;
  }
  
  public String zzvY()
  {
    return this.zzaDA;
  }
  
  public AppContentAnnotation zzvZ()
  {
    return this;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\appcontent\AppContentAnnotationEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */