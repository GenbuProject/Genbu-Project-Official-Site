package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.Nullable;

public final class ShareVideo
  extends ShareMedia
{
  private final Uri localUrl;
  
  ShareVideo(Parcel paramParcel)
  {
    super(paramParcel);
    this.localUrl = ((Uri)paramParcel.readParcelable(Uri.class.getClassLoader()));
  }
  
  private ShareVideo(Builder paramBuilder)
  {
    super(paramBuilder);
    this.localUrl = paramBuilder.localUrl;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @Nullable
  public Uri getLocalUrl()
  {
    return this.localUrl;
  }
  
  public ShareMedia.Type getMediaType()
  {
    return ShareMedia.Type.VIDEO;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.localUrl, 0);
  }
  
  public static final class Builder
    extends ShareMedia.Builder<ShareVideo, Builder>
  {
    private Uri localUrl;
    
    public ShareVideo build()
    {
      return new ShareVideo(this, null);
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((ShareVideo)paramParcel.readParcelable(ShareVideo.class.getClassLoader()));
    }
    
    public Builder readFrom(ShareVideo paramShareVideo)
    {
      if (paramShareVideo == null) {
        return this;
      }
      return ((Builder)super.readFrom(paramShareVideo)).setLocalUrl(paramShareVideo.getLocalUrl());
    }
    
    public Builder setLocalUrl(@Nullable Uri paramUri)
    {
      this.localUrl = paramUri;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\ShareVideo.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */