package com.facebook.share.model;

import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Parcel;
import android.support.annotation.Nullable;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public final class SharePhoto
  extends ShareMedia
{
  private final Bitmap bitmap;
  private final String caption;
  private final Uri imageUrl;
  private final boolean userGenerated;
  
  SharePhoto(Parcel paramParcel)
  {
    super(paramParcel);
    this.bitmap = ((Bitmap)paramParcel.readParcelable(Bitmap.class.getClassLoader()));
    this.imageUrl = ((Uri)paramParcel.readParcelable(Uri.class.getClassLoader()));
    if (paramParcel.readByte() != 0) {}
    for (boolean bool = true;; bool = false)
    {
      this.userGenerated = bool;
      this.caption = paramParcel.readString();
      return;
    }
  }
  
  private SharePhoto(Builder paramBuilder)
  {
    super(paramBuilder);
    this.bitmap = paramBuilder.bitmap;
    this.imageUrl = paramBuilder.imageUrl;
    this.userGenerated = paramBuilder.userGenerated;
    this.caption = paramBuilder.caption;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  @Nullable
  public Bitmap getBitmap()
  {
    return this.bitmap;
  }
  
  public String getCaption()
  {
    return this.caption;
  }
  
  @Nullable
  public Uri getImageUrl()
  {
    return this.imageUrl;
  }
  
  public ShareMedia.Type getMediaType()
  {
    return ShareMedia.Type.PHOTO;
  }
  
  public boolean getUserGenerated()
  {
    return this.userGenerated;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 0;
    super.writeToParcel(paramParcel, paramInt);
    paramParcel.writeParcelable(this.bitmap, 0);
    paramParcel.writeParcelable(this.imageUrl, 0);
    paramInt = i;
    if (this.userGenerated) {
      paramInt = 1;
    }
    paramParcel.writeByte((byte)paramInt);
    paramParcel.writeString(this.caption);
  }
  
  public static final class Builder
    extends ShareMedia.Builder<SharePhoto, Builder>
  {
    private Bitmap bitmap;
    private String caption;
    private Uri imageUrl;
    private boolean userGenerated;
    
    public static List<SharePhoto> readPhotoListFrom(Parcel paramParcel)
    {
      Object localObject = readListFrom(paramParcel);
      paramParcel = new ArrayList();
      localObject = ((List)localObject).iterator();
      while (((Iterator)localObject).hasNext())
      {
        ShareMedia localShareMedia = (ShareMedia)((Iterator)localObject).next();
        if ((localShareMedia instanceof SharePhoto)) {
          paramParcel.add((SharePhoto)localShareMedia);
        }
      }
      return paramParcel;
    }
    
    public static void writePhotoListTo(Parcel paramParcel, List<SharePhoto> paramList)
    {
      ArrayList localArrayList = new ArrayList();
      paramList = paramList.iterator();
      while (paramList.hasNext()) {
        localArrayList.add((SharePhoto)paramList.next());
      }
      writeListTo(paramParcel, localArrayList);
    }
    
    public SharePhoto build()
    {
      return new SharePhoto(this, null);
    }
    
    Bitmap getBitmap()
    {
      return this.bitmap;
    }
    
    Uri getImageUrl()
    {
      return this.imageUrl;
    }
    
    public Builder readFrom(Parcel paramParcel)
    {
      return readFrom((SharePhoto)paramParcel.readParcelable(SharePhoto.class.getClassLoader()));
    }
    
    public Builder readFrom(SharePhoto paramSharePhoto)
    {
      if (paramSharePhoto == null) {
        return this;
      }
      return ((Builder)super.readFrom(paramSharePhoto)).setBitmap(paramSharePhoto.getBitmap()).setImageUrl(paramSharePhoto.getImageUrl()).setUserGenerated(paramSharePhoto.getUserGenerated()).setCaption(paramSharePhoto.getCaption());
    }
    
    public Builder setBitmap(@Nullable Bitmap paramBitmap)
    {
      this.bitmap = paramBitmap;
      return this;
    }
    
    public Builder setCaption(@Nullable String paramString)
    {
      this.caption = paramString;
      return this;
    }
    
    public Builder setImageUrl(@Nullable Uri paramUri)
    {
      this.imageUrl = paramUri;
      return this;
    }
    
    public Builder setUserGenerated(boolean paramBoolean)
    {
      this.userGenerated = paramBoolean;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\facebook\share\model\SharePhoto.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */