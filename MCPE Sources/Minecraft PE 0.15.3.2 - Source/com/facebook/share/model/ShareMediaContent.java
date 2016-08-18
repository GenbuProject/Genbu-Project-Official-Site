package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

public final class ShareMediaContent
  extends ShareContent<ShareMediaContent, Builder>
{
  public static final Parcelable.Creator<ShareMediaContent> CREATOR = new Parcelable.Creator()
  {
    public ShareMediaContent createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ShareMediaContent(paramAnonymousParcel);
    }
    
    public ShareMediaContent[] newArray(int paramAnonymousInt)
    {
      return new ShareMediaContent[paramAnonymousInt];
    }
  };
  private final List<ShareMedia> media;
  
  ShareMediaContent(Parcel paramParcel)
  {
    super(paramParcel);
    this.media = Collections.unmodifiableList(ShareMedia.Builder.readListFrom(paramParcel));
  }
  
  private ShareMediaContent(Builder paramBuilder)
  {
    super(paramBuilder);
    this.media = Collections.unmodifiableList(paramBuilder.media);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @Nullable
  public List<ShareMedia> getMedia()
  {
    return this.media;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    ShareMedia.Builder.writeListTo(paramParcel, this.media);
  }
  
  public static class Builder
    extends ShareContent.Builder<ShareMediaContent, Builder>
  {
    private final List<ShareMedia> media = new ArrayList();
    
    public Builder addMedia(@Nullable List<ShareMedia> paramList)
    {
      if (paramList != null)
      {
        paramList = paramList.iterator();
        while (paramList.hasNext()) {
          addMedium((ShareMedia)paramList.next());
        }
      }
      return this;
    }
    
    public Builder addMedium(@Nullable ShareMedia paramShareMedia)
    {
      if (paramShareMedia != null) {
        if (!(paramShareMedia instanceof SharePhoto)) {
          break label42;
        }
      }
      for (paramShareMedia = new SharePhoto.Builder().readFrom((SharePhoto)paramShareMedia).build();; paramShareMedia = new ShareVideo.Builder().readFrom((ShareVideo)paramShareMedia).build())
      {
        this.media.add(paramShareMedia);
        return this;
        label42:
        if (!(paramShareMedia instanceof ShareVideo)) {
          break;
        }
      }
      throw new IllegalArgumentException("medium must be either a SharePhoto or ShareVideo");
    }
    
    public ShareMediaContent build()
    {
      return new ShareMediaContent(this, null);
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((ShareMediaContent)paramParcel.readParcelable(ShareMediaContent.class.getClassLoader()));
    }
    
    public Builder readFrom(ShareMediaContent paramShareMediaContent)
    {
      if (paramShareMediaContent == null) {
        return this;
      }
      return ((Builder)super.readFrom(paramShareMediaContent)).addMedia(paramShareMediaContent.getMedia());
    }
    
    public Builder setMedia(@Nullable List<ShareMedia> paramList)
    {
      this.media.clear();
      addMedia(paramList);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\ShareMediaContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */