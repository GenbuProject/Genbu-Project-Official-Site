package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareModel;
import com.facebook.share.model.ShareModelBuilder;

public class LikeContent
  implements ShareModel
{
  public static final Parcelable.Creator<LikeContent> CREATOR = new Parcelable.Creator()
  {
    public LikeContent createFromParcel(Parcel paramAnonymousParcel)
    {
      return new LikeContent(paramAnonymousParcel);
    }
    
    public LikeContent[] newArray(int paramAnonymousInt)
    {
      return new LikeContent[paramAnonymousInt];
    }
  };
  private final String objectId;
  private final String objectType;
  
  LikeContent(Parcel paramParcel)
  {
    this.objectId = paramParcel.readString();
    this.objectType = paramParcel.readString();
  }
  
  private LikeContent(Builder paramBuilder)
  {
    this.objectId = paramBuilder.objectId;
    this.objectType = paramBuilder.objectType;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getObjectId()
  {
    return this.objectId;
  }
  
  public String getObjectType()
  {
    return this.objectType;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.objectId);
    paramParcel.writeString(this.objectType);
  }
  
  public static class Builder
    implements ShareModelBuilder<LikeContent, Builder>
  {
    private String objectId;
    private String objectType;
    
    public LikeContent build()
    {
      return new LikeContent(this, null);
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((LikeContent)paramParcel.readParcelable(LikeContent.class.getClassLoader()));
    }
    
    public Builder readFrom(LikeContent paramLikeContent)
    {
      if (paramLikeContent == null) {
        return this;
      }
      return setObjectId(paramLikeContent.getObjectId()).setObjectType(paramLikeContent.getObjectType());
    }
    
    public Builder setObjectId(String paramString)
    {
      this.objectId = paramString;
      return this;
    }
    
    public Builder setObjectType(String paramString)
    {
      this.objectType = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\internal\LikeContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */