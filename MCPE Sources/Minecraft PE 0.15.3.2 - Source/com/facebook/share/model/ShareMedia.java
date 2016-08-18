package com.facebook.share.model;

import android.os.Bundle;
import android.os.Parcel;
import android.os.ParcelFormatException;
import android.os.Parcelable.Creator;
import java.util.ArrayList;
import java.util.List;

public abstract class ShareMedia
  implements ShareModel
{
  public static final Parcelable.Creator<ShareMedia> CREATOR = new Parcelable.Creator()
  {
    public ShareMedia createFromParcel(Parcel paramAnonymousParcel)
    {
      switch (ShareMedia.2.$SwitchMap$com$facebook$share$model$ShareMedia$Type[ShareMedia.Type.valueOf(paramAnonymousParcel.readString()).ordinal()])
      {
      default: 
        throw new ParcelFormatException("ShareMedia has invalid type");
      case 1: 
        return new SharePhoto(paramAnonymousParcel);
      }
      return new ShareVideo(paramAnonymousParcel);
    }
    
    public ShareMedia[] newArray(int paramAnonymousInt)
    {
      return new ShareMedia[paramAnonymousInt];
    }
  };
  private final Bundle params;
  
  ShareMedia(Parcel paramParcel)
  {
    this.params = paramParcel.readBundle();
  }
  
  protected ShareMedia(Builder paramBuilder)
  {
    this.params = new Bundle(paramBuilder.params);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public abstract Type getMediaType();
  
  @Deprecated
  public Bundle getParameters()
  {
    return new Bundle(this.params);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(getMediaType().name());
    paramParcel.writeBundle(this.params);
  }
  
  public static abstract class Builder<M extends ShareMedia, B extends Builder>
    implements ShareModelBuilder<M, B>
  {
    private Bundle params = new Bundle();
    
    public static List<ShareMedia> readListFrom(Parcel paramParcel)
    {
      ArrayList localArrayList = new ArrayList();
      paramParcel.readTypedList(localArrayList, ShareMedia.CREATOR);
      return localArrayList;
    }
    
    public static void writeListTo(Parcel paramParcel, List<ShareMedia> paramList)
    {
      paramParcel.writeTypedList(paramList);
    }
    
    public B readFrom(M paramM)
    {
      if (paramM == null) {
        return this;
      }
      return setParameters(paramM.getParameters());
    }
    
    @Deprecated
    public B setParameter(String paramString1, String paramString2)
    {
      this.params.putString(paramString1, paramString2);
      return this;
    }
    
    @Deprecated
    public B setParameters(Bundle paramBundle)
    {
      this.params.putAll(paramBundle);
      return this;
    }
  }
  
  public static enum Type
  {
    PHOTO,  VIDEO;
    
    private Type() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\ShareMedia.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */