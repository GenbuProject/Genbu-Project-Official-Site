package com.google.android.gms.cast;

import android.net.Uri;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.cast.internal.zzf;
import com.google.android.gms.common.images.WebImage;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public final class ApplicationMetadata
  implements SafeParcelable
{
  public static final Parcelable.Creator<ApplicationMetadata> CREATOR = new zza();
  String mName;
  private final int mVersionCode;
  String zzZC;
  List<String> zzZD;
  String zzZE;
  Uri zzZF;
  List<WebImage> zzxX;
  
  private ApplicationMetadata()
  {
    this.mVersionCode = 1;
    this.zzxX = new ArrayList();
    this.zzZD = new ArrayList();
  }
  
  ApplicationMetadata(int paramInt, String paramString1, String paramString2, List<WebImage> paramList, List<String> paramList1, String paramString3, Uri paramUri)
  {
    this.mVersionCode = paramInt;
    this.zzZC = paramString1;
    this.mName = paramString2;
    this.zzxX = paramList;
    this.zzZD = paramList1;
    this.zzZE = paramString3;
    this.zzZF = paramUri;
  }
  
  public boolean areNamespacesSupported(List<String> paramList)
  {
    return (this.zzZD != null) && (this.zzZD.containsAll(paramList));
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
      if (!(paramObject instanceof ApplicationMetadata)) {
        return false;
      }
      paramObject = (ApplicationMetadata)paramObject;
    } while ((zzf.zza(this.zzZC, ((ApplicationMetadata)paramObject).zzZC)) && (zzf.zza(this.zzxX, ((ApplicationMetadata)paramObject).zzxX)) && (zzf.zza(this.mName, ((ApplicationMetadata)paramObject).mName)) && (zzf.zza(this.zzZD, ((ApplicationMetadata)paramObject).zzZD)) && (zzf.zza(this.zzZE, ((ApplicationMetadata)paramObject).zzZE)) && (zzf.zza(this.zzZF, ((ApplicationMetadata)paramObject).zzZF)));
    return false;
  }
  
  public String getApplicationId()
  {
    return this.zzZC;
  }
  
  public List<WebImage> getImages()
  {
    return this.zzxX;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getSenderAppIdentifier()
  {
    return this.zzZE;
  }
  
  public List<String> getSupportedNamespaces()
  {
    return Collections.unmodifiableList(this.zzZD);
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.mVersionCode), this.zzZC, this.mName, this.zzxX, this.zzZD, this.zzZE, this.zzZF });
  }
  
  public boolean isNamespaceSupported(String paramString)
  {
    return (this.zzZD != null) && (this.zzZD.contains(paramString));
  }
  
  public String toString()
  {
    int j = 0;
    StringBuilder localStringBuilder = new StringBuilder().append("applicationId: ").append(this.zzZC).append(", name: ").append(this.mName).append(", images.count: ");
    if (this.zzxX == null)
    {
      i = 0;
      localStringBuilder = localStringBuilder.append(i).append(", namespaces.count: ");
      if (this.zzZD != null) {
        break label114;
      }
    }
    label114:
    for (int i = j;; i = this.zzZD.size())
    {
      return i + ", senderAppIdentifier: " + this.zzZE + ", senderAppLaunchUrl: " + this.zzZF;
      i = this.zzxX.size();
      break;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public Uri zznx()
  {
    return this.zzZF;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\cast\ApplicationMetadata.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */