package org.simpleframework.xml.util;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.WeakHashMap;

public class WeakCache<T>
  implements Cache<T>
{
  private WeakCache<T>.SegmentList list;
  
  public WeakCache()
  {
    this(10);
  }
  
  public WeakCache(int paramInt)
  {
    this.list = new SegmentList(paramInt);
  }
  
  private WeakCache<T>.Segment map(Object paramObject)
  {
    return this.list.get(paramObject);
  }
  
  public void cache(Object paramObject, T paramT)
  {
    map(paramObject).cache(paramObject, paramT);
  }
  
  public boolean contains(Object paramObject)
  {
    return map(paramObject).contains(paramObject);
  }
  
  public T fetch(Object paramObject)
  {
    return (T)map(paramObject).fetch(paramObject);
  }
  
  public boolean isEmpty()
  {
    Iterator localIterator = this.list.iterator();
    while (localIterator.hasNext()) {
      if (!((Segment)localIterator.next()).isEmpty()) {
        return false;
      }
    }
    return true;
  }
  
  public T take(Object paramObject)
  {
    return (T)map(paramObject).take(paramObject);
  }
  
  private class Segment
    extends WeakHashMap<Object, T>
  {
    private Segment() {}
    
    public void cache(Object paramObject, T paramT)
    {
      try
      {
        put(paramObject, paramT);
        return;
      }
      finally
      {
        paramObject = finally;
        throw ((Throwable)paramObject);
      }
    }
    
    public boolean contains(Object paramObject)
    {
      try
      {
        boolean bool = containsKey(paramObject);
        return bool;
      }
      finally
      {
        paramObject = finally;
        throw ((Throwable)paramObject);
      }
    }
    
    public T fetch(Object paramObject)
    {
      try
      {
        paramObject = get(paramObject);
        return (T)paramObject;
      }
      finally
      {
        paramObject = finally;
        throw ((Throwable)paramObject);
      }
    }
    
    public T take(Object paramObject)
    {
      try
      {
        paramObject = remove(paramObject);
        return (T)paramObject;
      }
      finally
      {
        paramObject = finally;
        throw ((Throwable)paramObject);
      }
    }
  }
  
  private class SegmentList
    implements Iterable<WeakCache<T>.Segment>
  {
    private List<WeakCache<T>.Segment> list = new ArrayList();
    private int size;
    
    public SegmentList(int paramInt)
    {
      this.size = paramInt;
      create(paramInt);
    }
    
    private void create(int paramInt)
    {
      while (paramInt > 0)
      {
        this.list.add(new WeakCache.Segment(WeakCache.this, null));
        paramInt -= 1;
      }
    }
    
    private int segment(Object paramObject)
    {
      return Math.abs(paramObject.hashCode() % this.size);
    }
    
    public WeakCache<T>.Segment get(Object paramObject)
    {
      int i = segment(paramObject);
      if (i < this.size) {
        return (WeakCache.Segment)this.list.get(i);
      }
      return null;
    }
    
    public Iterator<WeakCache<T>.Segment> iterator()
    {
      return this.list.iterator();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\util\WeakCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */