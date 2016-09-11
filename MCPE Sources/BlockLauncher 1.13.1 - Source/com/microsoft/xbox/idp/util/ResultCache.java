package com.microsoft.xbox.idp.util;

import java.util.HashMap;

public class ResultCache<R>
{
  private final HashMap<Object, R> map = new HashMap();
  
  public void clear()
  {
    this.map.clear();
  }
  
  public R get(Object paramObject)
  {
    return (R)this.map.get(paramObject);
  }
  
  public R put(Object paramObject, R paramR)
  {
    return (R)this.map.put(paramObject, paramR);
  }
  
  public R remove(Object paramObject)
  {
    return (R)this.map.remove(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\util\ResultCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */