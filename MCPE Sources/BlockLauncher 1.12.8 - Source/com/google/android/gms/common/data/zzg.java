package com.google.android.gms.common.data;

import java.util.NoSuchElementException;

public class zzg<T>
  extends zzb<T>
{
  private T zzajy;
  
  public zzg(DataBuffer<T> paramDataBuffer)
  {
    super(paramDataBuffer);
  }
  
  public T next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzajc);
    }
    this.zzajc += 1;
    if (this.zzajc == 0)
    {
      this.zzajy = this.zzajb.get(0);
      if (!(this.zzajy instanceof zzc)) {
        throw new IllegalStateException("DataBuffer reference of type " + this.zzajy.getClass() + " is not movable");
      }
    }
    else
    {
      ((zzc)this.zzajy).zzbF(this.zzajc);
    }
    return (T)this.zzajy;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\data\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */