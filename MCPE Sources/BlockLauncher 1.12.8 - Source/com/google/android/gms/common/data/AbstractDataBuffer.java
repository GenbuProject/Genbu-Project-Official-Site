package com.google.android.gms.common.data;

import android.os.Bundle;
import java.util.Iterator;

public abstract class AbstractDataBuffer<T>
  implements DataBuffer<T>
{
  protected final DataHolder zzahi;
  
  protected AbstractDataBuffer(DataHolder paramDataHolder)
  {
    this.zzahi = paramDataHolder;
    if (this.zzahi != null) {
      this.zzahi.zzu(this);
    }
  }
  
  @Deprecated
  public final void close()
  {
    release();
  }
  
  public abstract T get(int paramInt);
  
  public int getCount()
  {
    if (this.zzahi == null) {
      return 0;
    }
    return this.zzahi.getCount();
  }
  
  @Deprecated
  public boolean isClosed()
  {
    return (this.zzahi == null) || (this.zzahi.isClosed());
  }
  
  public Iterator<T> iterator()
  {
    return new zzb(this);
  }
  
  public void release()
  {
    if (this.zzahi != null) {
      this.zzahi.close();
    }
  }
  
  public Iterator<T> singleRefIterator()
  {
    return new zzg(this);
  }
  
  public Bundle zzpZ()
  {
    return this.zzahi.zzpZ();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\data\AbstractDataBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */