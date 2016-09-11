package com.google.android.gms.games.appcontent;

import android.net.Uri;
import android.os.Bundle;
import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import java.util.ArrayList;

public final class AppContentAnnotationRef
  extends MultiDataBufferRef
  implements AppContentAnnotation
{
  AppContentAnnotationRef(ArrayList<DataHolder> paramArrayList, int paramInt)
  {
    super(paramArrayList, 2, paramInt);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return AppContentAnnotationEntity.zza(this, paramObject);
  }
  
  public String getDescription()
  {
    return getString("annotation_description");
  }
  
  public String getId()
  {
    return getString("annotation_id");
  }
  
  public String getTitle()
  {
    return getString("annotation_title");
  }
  
  public int hashCode()
  {
    return AppContentAnnotationEntity.zza(this);
  }
  
  public String toString()
  {
    return AppContentAnnotationEntity.zzb(this);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    ((AppContentAnnotationEntity)zzvZ()).writeToParcel(paramParcel, paramInt);
  }
  
  public String zzvT()
  {
    return getString("annotation_image_default_id");
  }
  
  public int zzvU()
  {
    return getInteger("annotation_image_height");
  }
  
  public Uri zzvV()
  {
    return zzcA("annotation_image_uri");
  }
  
  public Bundle zzvW()
  {
    return AppContentUtils.zzd(this.zzahi, this.zzaDN, "annotation_modifiers", this.zzaje);
  }
  
  public int zzvX()
  {
    return getInteger("annotation_image_width");
  }
  
  public String zzvY()
  {
    return getString("annotation_layout_slot");
  }
  
  public AppContentAnnotation zzvZ()
  {
    return new AppContentAnnotationEntity(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\games\appcontent\AppContentAnnotationRef.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */