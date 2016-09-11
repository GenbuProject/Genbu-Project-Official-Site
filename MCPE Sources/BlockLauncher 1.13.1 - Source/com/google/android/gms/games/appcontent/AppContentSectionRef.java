package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

public final class AppContentSectionRef
  extends MultiDataBufferRef
  implements AppContentSection
{
  private final int zzaDQ;
  
  AppContentSectionRef(ArrayList<DataHolder> paramArrayList, int paramInt1, int paramInt2)
  {
    super(paramArrayList, 0, paramInt1);
    this.zzaDQ = paramInt2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return AppContentSectionEntity.zza(this, paramObject);
  }
  
  public Bundle getExtras()
  {
    return AppContentUtils.zzd(this.zzahi, this.zzaDN, "section_data", this.zzaje);
  }
  
  public String getId()
  {
    return getString("section_id");
  }
  
  public String getTitle()
  {
    return getString("section_title");
  }
  
  public String getType()
  {
    return getString("section_type");
  }
  
  public int hashCode()
  {
    return AppContentSectionEntity.zza(this);
  }
  
  public String toString()
  {
    return AppContentSectionEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((AppContentSectionEntity)zzwm()).writeToParcel(paramParcel, paramInt);
  }
  
  public String zzvQ()
  {
    return getString("section_content_description");
  }
  
  public String zzwc()
  {
    return getString("section_subtitle");
  }
  
  public String zzwl()
  {
    return getString("section_card_type");
  }
  
  public AppContentSection zzwm()
  {
    return new AppContentSectionEntity(this);
  }
  
  public ArrayList<AppContentAction> zzwn()
  {
    return AppContentUtils.zza(this.zzahi, this.zzaDN, "section_actions", this.zzaje);
  }
  
  public ArrayList<AppContentAnnotation> zzwo()
  {
    return AppContentUtils.zzb(this.zzahi, this.zzaDN, "section_annotations", this.zzaje);
  }
  
  public ArrayList<AppContentCard> zzwp()
  {
    ArrayList localArrayList = new ArrayList(this.zzaDQ);
    int i = 0;
    while (i < this.zzaDQ)
    {
      localArrayList.add(new AppContentCardRef(this.zzaDN, this.zzaje + i));
      i += 1;
    }
    return localArrayList;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\appcontent\AppContentSectionRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */