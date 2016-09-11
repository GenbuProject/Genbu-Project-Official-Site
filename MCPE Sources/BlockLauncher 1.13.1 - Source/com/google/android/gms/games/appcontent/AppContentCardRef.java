package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import java.util.List;

public final class AppContentCardRef
  extends MultiDataBufferRef
  implements AppContentCard
{
  AppContentCardRef(ArrayList<DataHolder> paramArrayList, int paramInt)
  {
    super(paramArrayList, 0, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return AppContentCardEntity.zza(this, paramObject);
  }
  
  public List<AppContentAction> getActions()
  {
    return AppContentUtils.zza(this.zzahi, this.zzaDN, "card_actions", this.zzaje);
  }
  
  public String getDescription()
  {
    return getString("card_description");
  }
  
  public Bundle getExtras()
  {
    return AppContentUtils.zzd(this.zzahi, this.zzaDN, "card_data", this.zzaje);
  }
  
  public String getId()
  {
    return getString("card_id");
  }
  
  public String getTitle()
  {
    return getString("card_title");
  }
  
  public String getType()
  {
    return getString("card_type");
  }
  
  public int hashCode()
  {
    return AppContentCardEntity.zza(this);
  }
  
  public String toString()
  {
    return AppContentCardEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((AppContentCardEntity)zzwe()).writeToParcel(paramParcel, paramInt);
  }
  
  public List<AppContentCondition> zzvP()
  {
    return AppContentUtils.zzc(this.zzahi, this.zzaDN, "card_conditions", this.zzaje);
  }
  
  public String zzvQ()
  {
    return getString("card_content_description");
  }
  
  public List<AppContentAnnotation> zzwa()
  {
    return AppContentUtils.zzb(this.zzahi, this.zzaDN, "card_annotations", this.zzaje);
  }
  
  public int zzwb()
  {
    return getInteger("card_current_steps");
  }
  
  public String zzwc()
  {
    return getString("card_subtitle");
  }
  
  public int zzwd()
  {
    return getInteger("card_total_steps");
  }
  
  public AppContentCard zzwe()
  {
    return new AppContentCardEntity(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\appcontent\AppContentCardRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */