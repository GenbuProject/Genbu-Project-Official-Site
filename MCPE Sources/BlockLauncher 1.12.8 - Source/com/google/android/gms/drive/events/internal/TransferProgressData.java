package com.google.android.gms.drive.events.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.DriveId;

public class TransferProgressData
  implements SafeParcelable
{
  public static final Parcelable.Creator<TransferProgressData> CREATOR = new zzc();
  final int mVersionCode;
  final int zzBc;
  final DriveId zzaoz;
  final int zzapT;
  final long zzapW;
  final long zzapX;
  
  TransferProgressData(int paramInt1, int paramInt2, DriveId paramDriveId, int paramInt3, long paramLong1, long paramLong2)
  {
    this.mVersionCode = paramInt1;
    this.zzapT = paramInt2;
    this.zzaoz = paramDriveId;
    this.zzBc = paramInt3;
    this.zzapW = paramLong1;
    this.zzapX = paramLong2;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = true;
    boolean bool1;
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      bool1 = false;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (paramObject == this);
      paramObject = (TransferProgressData)paramObject;
      if ((this.zzapT != ((TransferProgressData)paramObject).zzapT) || (!zzw.equal(this.zzaoz, ((TransferProgressData)paramObject).zzaoz)) || (this.zzBc != ((TransferProgressData)paramObject).zzBc) || (this.zzapW != ((TransferProgressData)paramObject).zzapW)) {
        break;
      }
      bool1 = bool2;
    } while (this.zzapX == ((TransferProgressData)paramObject).zzapX);
    return false;
  }
  
  public long getBytesTransferred()
  {
    return this.zzapW;
  }
  
  public DriveId getDriveId()
  {
    return this.zzaoz;
  }
  
  public int getStatus()
  {
    return this.zzBc;
  }
  
  public long getTotalBytes()
  {
    return this.zzapX;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Integer.valueOf(this.zzapT), this.zzaoz, Integer.valueOf(this.zzBc), Long.valueOf(this.zzapW), Long.valueOf(this.zzapX) });
  }
  
  public String toString()
  {
    return String.format("TransferProgressData[TransferType: %d, DriveId: %s, status: %d, bytes transferred: %d, total bytes: %d]", new Object[] { Integer.valueOf(this.zzapT), this.zzaoz, Integer.valueOf(this.zzBc), Long.valueOf(this.zzapW), Long.valueOf(this.zzapX) });
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzc.zza(this, paramParcel, paramInt);
  }
  
  public int zztb()
  {
    return this.zzapT;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\internal\TransferProgressData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */