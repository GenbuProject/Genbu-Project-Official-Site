package com.google.android.gms.drive.events.internal;

import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.drive.DriveId;
import com.google.android.gms.drive.events.zzj;

public class zzb
  implements zzj
{
  private final int zzBc;
  private final DriveId zzaoz;
  private final int zzapT;
  
  public zzb(TransferProgressData paramTransferProgressData)
  {
    this.zzaoz = paramTransferProgressData.getDriveId();
    this.zzapT = paramTransferProgressData.zztb();
    this.zzBc = paramTransferProgressData.getStatus();
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
      paramObject = (zzb)paramObject;
      if ((!zzw.equal(this.zzaoz, ((zzb)paramObject).zzaoz)) || (this.zzapT != ((zzb)paramObject).zzapT)) {
        break;
      }
      bool1 = bool2;
    } while (this.zzBc == ((zzb)paramObject).zzBc);
    return false;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzaoz, Integer.valueOf(this.zzapT), Integer.valueOf(this.zzBc) });
  }
  
  public String toString()
  {
    return String.format("FileTransferState[TransferType: %d, DriveId: %s, status: %d]", new Object[] { Integer.valueOf(this.zzapT), this.zzaoz, Integer.valueOf(this.zzBc) });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\events\internal\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */