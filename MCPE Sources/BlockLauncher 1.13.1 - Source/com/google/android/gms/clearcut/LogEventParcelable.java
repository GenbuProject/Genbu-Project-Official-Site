package com.google.android.gms.clearcut;

import android.os.Parcel;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzv;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.internal.zzsz.zzd;
import com.google.android.gms.playlog.internal.PlayLoggerContext;
import java.util.Arrays;

public class LogEventParcelable
  implements SafeParcelable
{
  public static final zzd CREATOR = new zzd();
  public final int versionCode;
  public PlayLoggerContext zzafh;
  public byte[] zzafi;
  public int[] zzafj;
  public final zzsz.zzd zzafk;
  public final zzb.zzb zzafl;
  public final zzb.zzb zzafm;
  
  LogEventParcelable(int paramInt, PlayLoggerContext paramPlayLoggerContext, byte[] paramArrayOfByte, int[] paramArrayOfInt)
  {
    this.versionCode = paramInt;
    this.zzafh = paramPlayLoggerContext;
    this.zzafi = paramArrayOfByte;
    this.zzafj = paramArrayOfInt;
    this.zzafk = null;
    this.zzafl = null;
    this.zzafm = null;
  }
  
  public LogEventParcelable(PlayLoggerContext paramPlayLoggerContext, zzsz.zzd paramzzd, zzb.zzb paramzzb1, zzb.zzb paramzzb2, int[] paramArrayOfInt)
  {
    this.versionCode = 1;
    this.zzafh = paramPlayLoggerContext;
    this.zzafk = paramzzd;
    this.zzafl = paramzzb1;
    this.zzafm = paramzzb2;
    this.zzafj = paramArrayOfInt;
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
      if (!(paramObject instanceof LogEventParcelable)) {
        break;
      }
      paramObject = (LogEventParcelable)paramObject;
    } while ((this.versionCode == ((LogEventParcelable)paramObject).versionCode) && (zzw.equal(this.zzafh, ((LogEventParcelable)paramObject).zzafh)) && (Arrays.equals(this.zzafi, ((LogEventParcelable)paramObject).zzafi)) && (Arrays.equals(this.zzafj, ((LogEventParcelable)paramObject).zzafj)) && (zzw.equal(this.zzafk, ((LogEventParcelable)paramObject).zzafk)) && (zzw.equal(this.zzafl, ((LogEventParcelable)paramObject).zzafl)) && (zzw.equal(this.zzafm, ((LogEventParcelable)paramObject).zzafm)));
    return false;
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.versionCode), this.zzafh, this.zzafi, this.zzafj, this.zzafk, this.zzafl, this.zzafm });
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("LogEventParcelable[");
    localStringBuilder.append(this.versionCode);
    localStringBuilder.append(", ");
    localStringBuilder.append(this.zzafh);
    localStringBuilder.append(", ");
    if (this.zzafi == null)
    {
      str = null;
      localStringBuilder.append(str);
      localStringBuilder.append(", ");
      if (this.zzafj != null) {
        break label157;
      }
    }
    label157:
    for (String str = (String)null;; str = zzv.zzcL(", ").zza(Arrays.asList(new int[][] { this.zzafj })))
    {
      localStringBuilder.append(str);
      localStringBuilder.append(", ");
      localStringBuilder.append(this.zzafk);
      localStringBuilder.append(", ");
      localStringBuilder.append(this.zzafl);
      localStringBuilder.append(", ");
      localStringBuilder.append(this.zzafm);
      localStringBuilder.append("]");
      return localStringBuilder.toString();
      str = new String(this.zzafi);
      break;
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzd.zza(this, paramParcel, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\clearcut\LogEventParcelable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */