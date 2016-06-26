package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.util.Base64;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.internal.zzab;
import com.google.android.gms.drive.internal.zzat;
import com.google.android.gms.drive.internal.zzau;
import com.google.android.gms.drive.internal.zzw;
import com.google.android.gms.drive.internal.zzy;
import com.google.android.gms.drive.internal.zzz;
import com.google.android.gms.internal.zzst;
import com.google.android.gms.internal.zzsu;

public class DriveId
  implements SafeParcelable
{
  public static final Parcelable.Creator<DriveId> CREATOR = new zze();
  public static final int RESOURCE_TYPE_FILE = 0;
  public static final int RESOURCE_TYPE_FOLDER = 1;
  public static final int RESOURCE_TYPE_UNKNOWN = -1;
  final int mVersionCode;
  final String zzaoL;
  final long zzaoM;
  final int zzaoN;
  private volatile String zzaoO = null;
  final long zzaou;
  private volatile String zzaow = null;
  
  DriveId(int paramInt1, String paramString, long paramLong1, long paramLong2, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzaoL = paramString;
    if (!"".equals(paramString)) {}
    for (boolean bool1 = true;; bool1 = false)
    {
      zzx.zzac(bool1);
      if (paramString == null)
      {
        bool1 = bool2;
        if (paramLong1 == -1L) {}
      }
      else
      {
        bool1 = true;
      }
      zzx.zzac(bool1);
      this.zzaoM = paramLong1;
      this.zzaou = paramLong2;
      this.zzaoN = paramInt2;
      return;
    }
  }
  
  public DriveId(String paramString, long paramLong1, long paramLong2, int paramInt)
  {
    this(1, paramString, paramLong1, paramLong2, paramInt);
  }
  
  public static DriveId decodeFromString(String paramString)
  {
    zzx.zzb(paramString.startsWith("DriveId:"), "Invalid DriveId: " + paramString);
    return zzl(Base64.decode(paramString.substring("DriveId:".length()), 10));
  }
  
  public static DriveId zzcW(String paramString)
  {
    zzx.zzz(paramString);
    return new DriveId(paramString, -1L, -1L, -1);
  }
  
  static DriveId zzl(byte[] paramArrayOfByte)
  {
    for (;;)
    {
      zzat localzzat;
      try
      {
        localzzat = zzat.zzm(paramArrayOfByte);
        if ("".equals(localzzat.zzarY))
        {
          paramArrayOfByte = null;
          return new DriveId(localzzat.versionCode, paramArrayOfByte, localzzat.zzarZ, localzzat.zzarW, localzzat.zzasa);
        }
      }
      catch (zzst paramArrayOfByte)
      {
        throw new IllegalArgumentException();
      }
      paramArrayOfByte = localzzat.zzarY;
    }
  }
  
  private byte[] zzsA()
  {
    zzau localzzau = new zzau();
    localzzau.zzarZ = this.zzaoM;
    localzzau.zzarW = this.zzaou;
    return zzsu.toByteArray(localzzau);
  }
  
  public DriveFile asDriveFile()
  {
    if (this.zzaoN == 1) {
      throw new IllegalStateException("This DriveId corresponds to a folder. Call asDriveFolder instead.");
    }
    return new zzw(this);
  }
  
  public DriveFolder asDriveFolder()
  {
    if (this.zzaoN == 0) {
      throw new IllegalStateException("This DriveId corresponds to a file. Call asDriveFile instead.");
    }
    return new zzy(this);
  }
  
  public DriveResource asDriveResource()
  {
    if (this.zzaoN == 1) {
      return asDriveFolder();
    }
    if (this.zzaoN == 0) {
      return asDriveFile();
    }
    return new zzab(this);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public final String encodeToString()
  {
    if (this.zzaow == null)
    {
      String str = Base64.encodeToString(zzsu(), 10);
      this.zzaow = ("DriveId:" + str);
    }
    return this.zzaow;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool = true;
    if (!(paramObject instanceof DriveId)) {}
    do
    {
      do
      {
        return false;
        paramObject = (DriveId)paramObject;
      } while (((DriveId)paramObject).zzaou != this.zzaou);
      if ((((DriveId)paramObject).zzaoM == -1L) && (this.zzaoM == -1L)) {
        return ((DriveId)paramObject).zzaoL.equals(this.zzaoL);
      }
      if ((this.zzaoL == null) || (((DriveId)paramObject).zzaoL == null))
      {
        if (((DriveId)paramObject).zzaoM == this.zzaoM) {}
        for (;;)
        {
          return bool;
          bool = false;
        }
      }
    } while (((DriveId)paramObject).zzaoM != this.zzaoM);
    if (((DriveId)paramObject).zzaoL.equals(this.zzaoL)) {
      return true;
    }
    zzz.zzz("DriveId", "Unexpected unequal resourceId for same DriveId object.");
    return false;
  }
  
  public String getResourceId()
  {
    return this.zzaoL;
  }
  
  public int getResourceType()
  {
    return this.zzaoN;
  }
  
  public int hashCode()
  {
    if (this.zzaoM == -1L) {
      return this.zzaoL.hashCode();
    }
    return (String.valueOf(this.zzaou) + String.valueOf(this.zzaoM)).hashCode();
  }
  
  public final String toInvariantString()
  {
    if (this.zzaoO == null) {
      this.zzaoO = Base64.encodeToString(zzsA(), 10);
    }
    return this.zzaoO;
  }
  
  public String toString()
  {
    return encodeToString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zze.zza(this, paramParcel, paramInt);
  }
  
  final byte[] zzsu()
  {
    zzat localzzat = new zzat();
    localzzat.versionCode = this.mVersionCode;
    if (this.zzaoL == null) {}
    for (String str = "";; str = this.zzaoL)
    {
      localzzat.zzarY = str;
      localzzat.zzarZ = this.zzaoM;
      localzzat.zzarW = this.zzaou;
      localzzat.zzasa = this.zzaoN;
      return zzsu.toByteArray(localzzat);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\DriveId.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */