package com.google.android.gms.drive;

import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.io.OutputStream;

public class Contents
  implements SafeParcelable
{
  public static final Parcelable.Creator<Contents> CREATOR = new zzb();
  final int mVersionCode;
  final ParcelFileDescriptor zzajL;
  final boolean zzaoA;
  final int zzaox;
  final int zzaoy;
  final DriveId zzaoz;
  final String zzsU;
  
  Contents(int paramInt1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt2, int paramInt3, DriveId paramDriveId, boolean paramBoolean, String paramString)
  {
    this.mVersionCode = paramInt1;
    this.zzajL = paramParcelFileDescriptor;
    this.zzaox = paramInt2;
    this.zzaoy = paramInt3;
    this.zzaoz = paramDriveId;
    this.zzaoA = paramBoolean;
    this.zzsU = paramString;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public DriveId getDriveId()
  {
    return this.zzaoz;
  }
  
  public InputStream getInputStream()
  {
    return new FileInputStream(this.zzajL.getFileDescriptor());
  }
  
  public int getMode()
  {
    return this.zzaoy;
  }
  
  public OutputStream getOutputStream()
  {
    return new FileOutputStream(this.zzajL.getFileDescriptor());
  }
  
  public ParcelFileDescriptor getParcelFileDescriptor()
  {
    return this.zzajL;
  }
  
  public int getRequestId()
  {
    return this.zzaox;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public boolean zzsv()
  {
    return this.zzaoA;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\Contents.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */