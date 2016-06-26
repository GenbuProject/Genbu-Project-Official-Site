package com.google.android.gms.drive.events.internal;

import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.events.zzh;
import com.google.android.gms.drive.events.zzj;

public class zza
  implements zzh
{
  private final zzj zzapV;
  private final long zzapW;
  private final long zzapX;
  
  public zza(TransferProgressData paramTransferProgressData)
  {
    this.zzapV = new zzb(paramTransferProgressData);
    this.zzapW = paramTransferProgressData.getBytesTransferred();
    this.zzapX = paramTransferProgressData.getTotalBytes();
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
      paramObject = (zza)paramObject;
      if ((!zzw.equal(this.zzapV, ((zza)paramObject).zzapV)) || (this.zzapW != ((zza)paramObject).zzapW)) {
        break;
      }
      bool1 = bool2;
    } while (this.zzapX == ((zza)paramObject).zzapX);
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzapX), Long.valueOf(this.zzapW), Long.valueOf(this.zzapX) });
  }
  
  public String toString()
  {
    return String.format("FileTransferProgress[FileTransferState: %s, BytesTransferred: %d, TotalBytes: %d]", new Object[] { this.zzapV.toString(), Long.valueOf(this.zzapW), Long.valueOf(this.zzapX) });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\internal\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */