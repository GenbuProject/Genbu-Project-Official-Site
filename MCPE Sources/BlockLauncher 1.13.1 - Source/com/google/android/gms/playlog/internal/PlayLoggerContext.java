package com.google.android.gms.playlog.internal;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;

public class PlayLoggerContext
  implements SafeParcelable
{
  public static final zze CREATOR = new zze();
  public final String packageName;
  public final int versionCode;
  public final int zzbdL;
  public final int zzbdM;
  public final String zzbdN;
  public final String zzbdO;
  public final boolean zzbdP;
  public final String zzbdQ;
  public final boolean zzbdR;
  public final int zzbdS;
  
  public PlayLoggerContext(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2, String paramString3, boolean paramBoolean1, String paramString4, boolean paramBoolean2, int paramInt4)
  {
    this.versionCode = paramInt1;
    this.packageName = paramString1;
    this.zzbdL = paramInt2;
    this.zzbdM = paramInt3;
    this.zzbdN = paramString2;
    this.zzbdO = paramString3;
    this.zzbdP = paramBoolean1;
    this.zzbdQ = paramString4;
    this.zzbdR = paramBoolean2;
    this.zzbdS = paramInt4;
  }
  
  public PlayLoggerContext(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, String paramString4, boolean paramBoolean, int paramInt3)
  {
    this.versionCode = 1;
    this.packageName = ((String)zzx.zzz(paramString1));
    this.zzbdL = paramInt1;
    this.zzbdM = paramInt2;
    this.zzbdQ = paramString2;
    this.zzbdN = paramString3;
    this.zzbdO = paramString4;
    if (!paramBoolean) {}
    for (boolean bool = true;; bool = false)
    {
      this.zzbdP = bool;
      this.zzbdR = paramBoolean;
      this.zzbdS = paramInt3;
      return;
    }
  }
  
  @Deprecated
  public PlayLoggerContext(String paramString1, int paramInt1, int paramInt2, String paramString2, String paramString3, boolean paramBoolean)
  {
    this.versionCode = 1;
    this.packageName = ((String)zzx.zzz(paramString1));
    this.zzbdL = paramInt1;
    this.zzbdM = paramInt2;
    this.zzbdQ = null;
    this.zzbdN = paramString2;
    this.zzbdO = paramString3;
    this.zzbdP = paramBoolean;
    this.zzbdR = false;
    this.zzbdS = 0;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if (!(paramObject instanceof PlayLoggerContext)) {
        break;
      }
      paramObject = (PlayLoggerContext)paramObject;
    } while ((this.versionCode == ((PlayLoggerContext)paramObject).versionCode) && (this.packageName.equals(((PlayLoggerContext)paramObject).packageName)) && (this.zzbdL == ((PlayLoggerContext)paramObject).zzbdL) && (this.zzbdM == ((PlayLoggerContext)paramObject).zzbdM) && (zzw.equal(this.zzbdQ, ((PlayLoggerContext)paramObject).zzbdQ)) && (zzw.equal(this.zzbdN, ((PlayLoggerContext)paramObject).zzbdN)) && (zzw.equal(this.zzbdO, ((PlayLoggerContext)paramObject).zzbdO)) && (this.zzbdP == ((PlayLoggerContext)paramObject).zzbdP) && (this.zzbdR == ((PlayLoggerContext)paramObject).zzbdR) && (this.zzbdS == ((PlayLoggerContext)paramObject).zzbdS));
    return false;
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.versionCode), this.packageName, Integer.valueOf(this.zzbdL), Integer.valueOf(this.zzbdM), this.zzbdQ, this.zzbdN, this.zzbdO, Boolean.valueOf(this.zzbdP), Boolean.valueOf(this.zzbdR), Integer.valueOf(this.zzbdS) });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("PlayLoggerContext[");
    localStringBuilder.append("versionCode=").append(this.versionCode).append(',');
    localStringBuilder.append("package=").append(this.packageName).append(',');
    localStringBuilder.append("packageVersionCode=").append(this.zzbdL).append(',');
    localStringBuilder.append("logSource=").append(this.zzbdM).append(',');
    localStringBuilder.append("logSourceName=").append(this.zzbdQ).append(',');
    localStringBuilder.append("uploadAccount=").append(this.zzbdN).append(',');
    localStringBuilder.append("loggingId=").append(this.zzbdO).append(',');
    localStringBuilder.append("logAndroidId=").append(this.zzbdP).append(',');
    localStringBuilder.append("isAnonymous=").append(this.zzbdR).append(',');
    localStringBuilder.append("qosTier=").append(this.zzbdS);
    localStringBuilder.append("]");
    return localStringBuilder.toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\playlog\internal\PlayLoggerContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */