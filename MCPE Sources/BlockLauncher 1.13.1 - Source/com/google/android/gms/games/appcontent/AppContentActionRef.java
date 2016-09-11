package com.google.android.gms.games.appcontent;

import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;
import java.util.List;

public final class AppContentActionRef
  extends MultiDataBufferRef
  implements AppContentAction
{
  AppContentActionRef(ArrayList<DataHolder> paramArrayList, int paramInt)
  {
    super(paramArrayList, 1, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return AppContentActionEntity.zza(this, paramObject);
  }
  
  public Bundle getExtras()
  {
    return AppContentUtils.zzd(this.zzahi, this.zzaDN, "action_data", this.zzaje);
  }
  
  public String getId()
  {
    return getString("action_id");
  }
  
  public String getType()
  {
    return getString("action_type");
  }
  
  public int hashCode()
  {
    return AppContentActionEntity.zza(this);
  }
  
  public String toString()
  {
    return AppContentActionEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((AppContentActionEntity)zzvS()).writeToParcel(paramParcel, paramInt);
  }
  
  public AppContentAnnotation zzvO()
  {
    ArrayList localArrayList = AppContentUtils.zzb(this.zzahi, this.zzaDN, "action_annotation", this.zzaje);
    if (localArrayList.size() == 1) {
      return (AppContentAnnotation)localArrayList.get(0);
    }
    return null;
  }
  
  public List<AppContentCondition> zzvP()
  {
    return AppContentUtils.zzc(this.zzahi, this.zzaDN, "action_conditions", this.zzaje);
  }
  
  public String zzvQ()
  {
    return getString("action_content_description");
  }
  
  public String zzvR()
  {
    return getString("overflow_text");
  }
  
  public AppContentAction zzvS()
  {
    return new AppContentActionEntity(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\appcontent\AppContentActionRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */