package com.google.android.gms.nearby.sharing;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.Arrays;

public class SharedContent
  implements SafeParcelable
{
  public static final Parcelable.Creator<SharedContent> CREATOR = new zzc();
  private final int versionCode;
  @Deprecated
  private String zzbdg;
  private ViewableItem[] zzbdh;
  private LocalContent[] zzbdi;
  
  private SharedContent()
  {
    this.versionCode = 2;
  }
  
  SharedContent(int paramInt, String paramString, ViewableItem[] paramArrayOfViewableItem, LocalContent[] paramArrayOfLocalContent)
  {
    this.versionCode = paramInt;
    this.zzbdg = paramString;
    this.zzbdh = paramArrayOfViewableItem;
    this.zzbdi = paramArrayOfLocalContent;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == this) {}
    do
    {
      return true;
      if (!(paramObject instanceof SharedContent)) {
        return false;
      }
      paramObject = (SharedContent)paramObject;
    } while ((zzw.equal(this.zzbdh, ((SharedContent)paramObject).zzbdh)) && (zzw.equal(this.zzbdi, ((SharedContent)paramObject).zzbdi)) && (zzw.equal(this.zzbdg, ((SharedContent)paramObject).zzbdg)));
    return false;
  }
  
  public String getUri()
  {
    return this.zzbdg;
  }
  
  int getVersionCode()
  {
    return this.versionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzbdh, this.zzbdi, this.zzbdg });
  }
  
  public String toString()
  {
    return "SharedContent[viewableItems=" + Arrays.toString(this.zzbdh) + ", localContents=" + Arrays.toString(this.zzbdi) + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public ViewableItem[] zzEL()
  {
    return this.zzbdh;
  }
  
  public LocalContent[] zzEM()
  {
    return this.zzbdi;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\sharing\SharedContent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */