package com.facebook.share.model;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareLinkContent
  extends ShareContent<ShareLinkContent, Builder>
{
  public static final Parcelable.Creator<ShareLinkContent> CREATOR = new Parcelable.Creator()
  {
    public ShareLinkContent createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ShareLinkContent(paramAnonymousParcel);
    }
    
    public ShareLinkContent[] newArray(int paramAnonymousInt)
    {
      return new ShareLinkContent[paramAnonymousInt];
    }
  };
  private final String contentDescription;
  private final String contentTitle;
  private final Uri imageUrl;
  private final String quote;
  
  ShareLinkContent(Parcel paramParcel)
  {
    super(paramParcel);
    this.contentDescription = paramParcel.readString();
    this.contentTitle = paramParcel.readString();
    this.imageUrl = ((Uri)paramParcel.readParcelable(Uri.class.getClassLoader()));
    this.quote = paramParcel.readString();
  }
  
  private ShareLinkContent(Builder paramBuilder)
  {
    super(paramBuilder);
    this.contentDescription = paramBuilder.contentDescription;
    this.contentTitle = paramBuilder.contentTitle;
    this.imageUrl = paramBuilder.imageUrl;
    this.quote = paramBuilder.quote;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getContentDescription()
  {
    return this.contentDescription;
  }
  
  @Nullable
  public String getContentTitle()
  {
    return this.contentTitle;
  }
  
  @Nullable
  public Uri getImageUrl()
  {
    return this.imageUrl;
  }
  
  @Nullable
  public String getQuote()
  {
    return this.quote;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeString(this.contentDescription);
    paramParcel.writeString(this.contentTitle);
    paramParcel.writeParcelable(this.imageUrl, 0);
    paramParcel.writeString(this.quote);
  }
  
  public static final class Builder
    extends ShareContent.Builder<ShareLinkContent, Builder>
  {
    private String contentDescription;
    private String contentTitle;
    private Uri imageUrl;
    private String quote;
    
    public ShareLinkContent build()
    {
      return new ShareLinkContent(this, null);
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((ShareLinkContent)paramParcel.readParcelable(ShareLinkContent.class.getClassLoader()));
    }
    
    public Builder readFrom(ShareLinkContent paramShareLinkContent)
    {
      if (paramShareLinkContent == null) {
        return this;
      }
      return ((Builder)super.readFrom(paramShareLinkContent)).setContentDescription(paramShareLinkContent.getContentDescription()).setImageUrl(paramShareLinkContent.getImageUrl()).setContentTitle(paramShareLinkContent.getContentTitle()).setQuote(paramShareLinkContent.getQuote());
    }
    
    public Builder setContentDescription(@Nullable String paramString)
    {
      this.contentDescription = paramString;
      return this;
    }
    
    public Builder setContentTitle(@Nullable String paramString)
    {
      this.contentTitle = paramString;
      return this;
    }
    
    public Builder setImageUrl(@Nullable Uri paramUri)
    {
      this.imageUrl = paramUri;
      return this;
    }
    
    public Builder setQuote(@Nullable String paramString)
    {
      this.quote = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\ShareLinkContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */