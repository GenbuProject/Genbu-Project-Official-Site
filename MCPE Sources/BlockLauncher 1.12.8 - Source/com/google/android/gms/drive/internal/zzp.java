package com.google.android.gms.drive.internal;

import com.google.android.gms.drive.Metadata;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;

public final class zzp
  extends Metadata
{
  private final MetadataBundle zzaqB;
  
  public zzp(MetadataBundle paramMetadataBundle)
  {
    this.zzaqB = paramMetadataBundle;
  }
  
  public boolean isDataValid()
  {
    return this.zzaqB != null;
  }
  
  public String toString()
  {
    return "Metadata [mImpl=" + this.zzaqB + "]";
  }
  
  public <T> T zza(MetadataField<T> paramMetadataField)
  {
    return (T)this.zzaqB.zza(paramMetadataField);
  }
  
  public Metadata zzsK()
  {
    return new zzp(this.zzaqB.zztF());
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\internal\zzp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */