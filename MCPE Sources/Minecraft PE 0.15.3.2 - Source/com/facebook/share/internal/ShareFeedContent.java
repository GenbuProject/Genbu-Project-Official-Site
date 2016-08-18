package com.facebook.share.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.facebook.share.model.ShareContent;
import com.facebook.share.model.ShareContent.Builder;

public class ShareFeedContent
  extends ShareContent<ShareFeedContent, Builder>
{
  public static final Parcelable.Creator<ShareFeedContent> CREATOR = new Parcelable.Creator()
  {
    public ShareFeedContent createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ShareFeedContent(paramAnonymousParcel);
    }
    
    public ShareFeedContent[] newArray(int paramAnonymousInt)
    {
      return new ShareFeedContent[paramAnonymousInt];
    }
  };
  private final String link;
  private final String linkCaption;
  private final String linkDescription;
  private final String linkName;
  private final String mediaSource;
  private final String picture;
  private final String toId;
  
  ShareFeedContent(Parcel paramParcel)
  {
    super(paramParcel);
    this.toId = paramParcel.readString();
    this.link = paramParcel.readString();
    this.linkName = paramParcel.readString();
    this.linkCaption = paramParcel.readString();
    this.linkDescription = paramParcel.readString();
    this.picture = paramParcel.readString();
    this.mediaSource = paramParcel.readString();
  }
  
  private ShareFeedContent(Builder paramBuilder)
  {
    super(paramBuilder);
    this.toId = paramBuilder.toId;
    this.link = paramBuilder.link;
    this.linkName = paramBuilder.linkName;
    this.linkCaption = paramBuilder.linkCaption;
    this.linkDescription = paramBuilder.linkDescription;
    this.picture = paramBuilder.picture;
    this.mediaSource = paramBuilder.mediaSource;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getLink()
  {
    return this.link;
  }
  
  public String getLinkCaption()
  {
    return this.linkCaption;
  }
  
  public String getLinkDescription()
  {
    return this.linkDescription;
  }
  
  public String getLinkName()
  {
    return this.linkName;
  }
  
  public String getMediaSource()
  {
    return this.mediaSource;
  }
  
  public String getPicture()
  {
    return this.picture;
  }
  
  public String getToId()
  {
    return this.toId;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.toId);
    paramParcel.writeString(this.link);
    paramParcel.writeString(this.linkName);
    paramParcel.writeString(this.linkCaption);
    paramParcel.writeString(this.linkDescription);
    paramParcel.writeString(this.picture);
    paramParcel.writeString(this.mediaSource);
  }
  
  public static final class Builder
    extends ShareContent.Builder<ShareFeedContent, Builder>
  {
    private String link;
    private String linkCaption;
    private String linkDescription;
    private String linkName;
    private String mediaSource;
    private String picture;
    private String toId;
    
    public ShareFeedContent build()
    {
      return new ShareFeedContent(this, null);
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((ShareFeedContent)paramParcel.readParcelable(ShareFeedContent.class.getClassLoader()));
    }
    
    public Builder readFrom(ShareFeedContent paramShareFeedContent)
    {
      if (paramShareFeedContent == null) {
        return this;
      }
      return ((Builder)super.readFrom(paramShareFeedContent)).setToId(paramShareFeedContent.getToId()).setLink(paramShareFeedContent.getLink()).setLinkName(paramShareFeedContent.getLinkName()).setLinkCaption(paramShareFeedContent.getLinkCaption()).setLinkDescription(paramShareFeedContent.getLinkDescription()).setPicture(paramShareFeedContent.getPicture()).setMediaSource(paramShareFeedContent.getMediaSource());
    }
    
    public Builder setLink(String paramString)
    {
      this.link = paramString;
      return this;
    }
    
    public Builder setLinkCaption(String paramString)
    {
      this.linkCaption = paramString;
      return this;
    }
    
    public Builder setLinkDescription(String paramString)
    {
      this.linkDescription = paramString;
      return this;
    }
    
    public Builder setLinkName(String paramString)
    {
      this.linkName = paramString;
      return this;
    }
    
    public Builder setMediaSource(String paramString)
    {
      this.mediaSource = paramString;
      return this;
    }
    
    public Builder setPicture(String paramString)
    {
      this.picture = paramString;
      return this;
    }
    
    public Builder setToId(String paramString)
    {
      this.toId = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\internal\ShareFeedContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */