package com.facebook.share.model;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.support.annotation.Nullable;

public final class ShareOpenGraphAction
  extends ShareOpenGraphValueContainer<ShareOpenGraphAction, Builder>
{
  public static final Parcelable.Creator<ShareOpenGraphAction> CREATOR = new Parcelable.Creator()
  {
    public ShareOpenGraphAction createFromParcel(Parcel paramAnonymousParcel)
    {
      return new ShareOpenGraphAction(paramAnonymousParcel);
    }
    
    public ShareOpenGraphAction[] newArray(int paramAnonymousInt)
    {
      return new ShareOpenGraphAction[paramAnonymousInt];
    }
  };
  
  ShareOpenGraphAction(Parcel paramParcel)
  {
    super(paramParcel);
  }
  
  private ShareOpenGraphAction(Builder paramBuilder)
  {
    super(paramBuilder);
  }
  
  @Nullable
  public String getActionType()
  {
    return getString("og:type");
  }
  
  public static final class Builder
    extends ShareOpenGraphValueContainer.Builder<ShareOpenGraphAction, Builder>
  {
    private static final String ACTION_TYPE_KEY = "og:type";
    
    public ShareOpenGraphAction build()
    {
      return new ShareOpenGraphAction(this, null);
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((ShareOpenGraphAction)paramParcel.readParcelable(ShareOpenGraphAction.class.getClassLoader()));
    }
    
    public Builder readFrom(ShareOpenGraphAction paramShareOpenGraphAction)
    {
      if (paramShareOpenGraphAction == null) {
        return this;
      }
      return ((Builder)super.readFrom(paramShareOpenGraphAction)).setActionType(paramShareOpenGraphAction.getActionType());
    }
    
    public Builder setActionType(String paramString)
    {
      putString("og:type", paramString);
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\ShareOpenGraphAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */