package com.google.android.gms.tagmanager;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

class zzcw<K, V>
  implements zzl<K, V>
{
  private final Map<K, V> zzbld = new HashMap();
  private final int zzble;
  private final zzm.zza<K, V> zzblf;
  private int zzblg;
  
  zzcw(int paramInt, zzm.zza<K, V> paramzza)
  {
    this.zzble = paramInt;
    this.zzblf = paramzza;
  }
  
  public V get(K paramK)
  {
    try
    {
      paramK = this.zzbld.get(paramK);
      return paramK;
    }
    finally
    {
      paramK = finally;
      throw paramK;
    }
  }
  
  public void zzh(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      try
      {
        throw new NullPointerException("key == null || value == null");
      }
      finally {}
    }
    this.zzblg += this.zzblf.sizeOf(paramK, paramV);
    if (this.zzblg > this.zzble)
    {
      Iterator localIterator = this.zzbld.entrySet().iterator();
      do
      {
        if (!localIterator.hasNext()) {
          break;
        }
        Map.Entry localEntry = (Map.Entry)localIterator.next();
        this.zzblg -= this.zzblf.sizeOf(localEntry.getKey(), localEntry.getValue());
        localIterator.remove();
      } while (this.zzblg > this.zzble);
    }
    this.zzbld.put(paramK, paramV);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\tagmanager\zzcw.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */