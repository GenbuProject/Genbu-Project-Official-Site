package com.google.android.gms.drive;

import android.os.Bundle;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.drive.internal.zzp;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import com.google.android.gms.drive.metadata.internal.zze;
import com.google.android.gms.internal.zznm;
import java.util.Collection;
import java.util.Iterator;

public final class MetadataBuffer
  extends AbstractDataBuffer<Metadata>
{
  private zza zzapb;
  
  public MetadataBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    paramDataHolder.zzpZ().setClassLoader(MetadataBuffer.class.getClassLoader());
  }
  
  public Metadata get(int paramInt)
  {
    zza localzza2 = this.zzapb;
    zza localzza1;
    if (localzza2 != null)
    {
      localzza1 = localzza2;
      if (zza.zza(localzza2) == paramInt) {}
    }
    else
    {
      localzza1 = new zza(this.zzahi, paramInt);
      this.zzapb = localzza1;
    }
    return localzza1;
  }
  
  @Deprecated
  public String getNextPageToken()
  {
    return null;
  }
  
  public void release()
  {
    if (this.zzahi != null) {
      zze.zza(this.zzahi);
    }
    super.release();
  }
  
  private static class zza
    extends Metadata
  {
    private final DataHolder zzahi;
    private final int zzajf;
    private final int zzapc;
    
    public zza(DataHolder paramDataHolder, int paramInt)
    {
      this.zzahi = paramDataHolder;
      this.zzapc = paramInt;
      this.zzajf = paramDataHolder.zzbH(paramInt);
    }
    
    public boolean isDataValid()
    {
      return !this.zzahi.isClosed();
    }
    
    public <T> T zza(MetadataField<T> paramMetadataField)
    {
      return (T)paramMetadataField.zza(this.zzahi, this.zzapc, this.zzajf);
    }
    
    public Metadata zzsK()
    {
      MetadataBundle localMetadataBundle = MetadataBundle.zztE();
      Iterator localIterator = zze.zztC().iterator();
      while (localIterator.hasNext())
      {
        MetadataField localMetadataField = (MetadataField)localIterator.next();
        if (localMetadataField != zznm.zzatz) {
          localMetadataField.zza(this.zzahi, localMetadataBundle, this.zzapc, this.zzajf);
        }
      }
      return new zzp(localMetadataBundle);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\MetadataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */