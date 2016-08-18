package com.microsoft.xbox.idp.util;

import com.microsoft.xbox.idp.toolkit.ObjectLoader.Cache;
import com.microsoft.xbox.idp.toolkit.ObjectLoader.Result;
import java.util.HashMap;

public class ObjectLoaderCache
  implements ObjectLoader.Cache
{
  private final HashMap<Object, ObjectLoader.Result<?>> map = new HashMap();
  
  public void clear()
  {
    this.map.clear();
  }
  
  public <T> ObjectLoader.Result<T> get(Object paramObject)
  {
    return (ObjectLoader.Result)this.map.get(paramObject);
  }
  
  public <T> ObjectLoader.Result<T> put(Object paramObject, ObjectLoader.Result<T> paramResult)
  {
    return (ObjectLoader.Result)this.map.put(paramObject, paramResult);
  }
  
  public <T> ObjectLoader.Result<T> remove(Object paramObject)
  {
    return (ObjectLoader.Result)this.map.remove(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\util\ObjectLoaderCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */