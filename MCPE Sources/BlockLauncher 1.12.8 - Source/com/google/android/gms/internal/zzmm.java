package com.google.android.gms.internal;

import android.support.v4.util.ArrayMap;
import java.util.AbstractSet;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class zzmm<E>
  extends AbstractSet<E>
{
  private final ArrayMap<E, E> zzanZ;
  
  public zzmm()
  {
    this.zzanZ = new ArrayMap();
  }
  
  public zzmm(int paramInt)
  {
    this.zzanZ = new ArrayMap(paramInt);
  }
  
  public zzmm(Collection<E> paramCollection)
  {
    this(paramCollection.size());
    addAll(paramCollection);
  }
  
  public boolean add(E paramE)
  {
    if (this.zzanZ.containsKey(paramE)) {
      return false;
    }
    this.zzanZ.put(paramE, paramE);
    return true;
  }
  
  public boolean addAll(Collection<? extends E> paramCollection)
  {
    if ((paramCollection instanceof zzmm)) {
      return zza((zzmm)paramCollection);
    }
    return super.addAll(paramCollection);
  }
  
  public void clear()
  {
    this.zzanZ.clear();
  }
  
  public boolean contains(Object paramObject)
  {
    return this.zzanZ.containsKey(paramObject);
  }
  
  public Iterator<E> iterator()
  {
    return this.zzanZ.keySet().iterator();
  }
  
  public boolean remove(Object paramObject)
  {
    if (!this.zzanZ.containsKey(paramObject)) {
      return false;
    }
    this.zzanZ.remove(paramObject);
    return true;
  }
  
  public int size()
  {
    return this.zzanZ.size();
  }
  
  public boolean zza(zzmm<? extends E> paramzzmm)
  {
    int i = size();
    this.zzanZ.putAll(paramzzmm.zzanZ);
    return size() > i;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzmm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */