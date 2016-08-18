package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;

public final class AppInviteContent
  implements ShareModel
{
  public static final Parcelable.Creator<AppInviteContent> CREATOR = new Parcelable.Creator()
  {
    public AppInviteContent createFromParcel(Parcel paramAnonymousParcel)
    {
      return new AppInviteContent(paramAnonymousParcel);
    }
    
    public AppInviteContent[] newArray(int paramAnonymousInt)
    {
      return new AppInviteContent[paramAnonymousInt];
    }
  };
  private final String applinkUrl;
  private final String previewImageUrl;
  private final String promoCode;
  private final String promoText;
  
  AppInviteContent(Parcel paramParcel)
  {
    this.applinkUrl = paramParcel.readString();
    this.previewImageUrl = paramParcel.readString();
    this.promoText = paramParcel.readString();
    this.promoCode = paramParcel.readString();
  }
  
  private AppInviteContent(Builder paramBuilder)
  {
    this.applinkUrl = paramBuilder.applinkUrl;
    this.previewImageUrl = paramBuilder.previewImageUrl;
    this.promoCode = paramBuilder.promoCode;
    this.promoText = paramBuilder.promoText;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public String getApplinkUrl()
  {
    return this.applinkUrl;
  }
  
  public String getPreviewImageUrl()
  {
    return this.previewImageUrl;
  }
  
  public String getPromotionCode()
  {
    return this.promoCode;
  }
  
  public String getPromotionText()
  {
    return this.promoText;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    paramParcel.writeString(this.applinkUrl);
    paramParcel.writeString(this.previewImageUrl);
    paramParcel.writeString(this.promoText);
    paramParcel.writeString(this.promoCode);
  }
  
  public static class Builder
    implements ShareModelBuilder<AppInviteContent, Builder>
  {
    private String applinkUrl;
    private String previewImageUrl;
    private String promoCode;
    private String promoText;
    
    private boolean isAlphanumericWithSpaces(String paramString)
    {
      int i = 0;
      while (i < paramString.length())
      {
        char c = paramString.charAt(i);
        if ((!Character.isDigit(c)) && (!Character.isLetter(c)) && (!Character.isSpaceChar(c))) {
          return false;
        }
        i += 1;
      }
      return true;
    }
    
    public AppInviteContent build()
    {
      return new AppInviteContent(this, null);
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((AppInviteContent)paramParcel.readParcelable(AppInviteContent.class.getClassLoader()));
    }
    
    public Builder readFrom(AppInviteContent paramAppInviteContent)
    {
      if (paramAppInviteContent == null) {
        return this;
      }
      return setApplinkUrl(paramAppInviteContent.getApplinkUrl()).setPreviewImageUrl(paramAppInviteContent.getPreviewImageUrl()).setPromotionDetails(paramAppInviteContent.getPromotionText(), paramAppInviteContent.getPromotionCode());
    }
    
    public Builder setApplinkUrl(String paramString)
    {
      this.applinkUrl = paramString;
      return this;
    }
    
    public Builder setPreviewImageUrl(String paramString)
    {
      this.previewImageUrl = paramString;
      return this;
    }
    
    public Builder setPromotionDetails(String paramString1, String paramString2)
    {
      if (!TextUtils.isEmpty(paramString1))
      {
        if (paramString1.length() > 80) {
          throw new IllegalArgumentException("Invalid promotion text, promotionText needs to be between1 and 80 characters long");
        }
        if (!isAlphanumericWithSpaces(paramString1)) {
          throw new IllegalArgumentException("Invalid promotion text, promotionText can only contain alphanumericcharacters and spaces.");
        }
        if (!TextUtils.isEmpty(paramString2))
        {
          if (paramString2.length() > 10) {
            throw new IllegalArgumentException("Invalid promotion code, promotionCode can be between1 and 10 characters long");
          }
          if (!isAlphanumericWithSpaces(paramString2)) {
            throw new IllegalArgumentException("Invalid promotion code, promotionCode can only contain alphanumeric characters and spaces.");
          }
        }
      }
      else if (!TextUtils.isEmpty(paramString2))
      {
        throw new IllegalArgumentException("promotionCode cannot be specified without a valid promotionText");
      }
      this.promoCode = paramString2;
      this.promoText = paramString1;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\AppInviteContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */