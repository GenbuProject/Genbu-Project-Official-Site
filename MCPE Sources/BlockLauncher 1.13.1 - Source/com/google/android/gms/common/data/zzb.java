package com.google.android.gms.common.data;

import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.NoSuchElementException;

public class zzb<T>
  implements Iterator<T>
{
  protected final DataBuffer<T> zzajb;
  protected int zzajc;
  
  public zzb(DataBuffer<T> paramDataBuffer)
  {
    this.zzajb = ((DataBuffer)zzx.zzz(paramDataBuffer));
    this.zzajc = -1;
  }
  
  public boolean hasNext()
  {
    return this.zzajc < this.zzajb.getCount() - 1;
  }
  
  public T next()
  {
    if (!hasNext()) {
      throw new NoSuchElementException("Cannot advance the iterator beyond " + this.zzajc);
    }
    DataBuffer localDataBuffer = this.zzajb;
    int i = this.zzajc + 1;
    this.zzajc = i;
    return (T)localDataBuffer.get(i);
  }
  
  public void remove()
  {
    throw new UnsupportedOperationException("Cannot remove elements from a DataBufferIterator");
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\data\zzb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */