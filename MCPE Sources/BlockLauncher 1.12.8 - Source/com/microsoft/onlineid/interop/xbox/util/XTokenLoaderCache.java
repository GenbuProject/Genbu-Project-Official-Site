package com.microsoft.onlineid.interop.xbox.util;

import com.microsoft.onlineid.interop.xbox.toolkit.XTokenLoader.Cache;
import com.microsoft.onlineid.interop.xbox.toolkit.XTokenLoader.Result;
import java.util.HashMap;

public class XTokenLoaderCache
  implements XTokenLoader.Cache
{
  private static final XTokenLoader.Cache instance = new XTokenLoaderCache();
  private final HashMap<Object, XTokenLoader.Result> map = new HashMap();
  
  public static XTokenLoader.Cache getInstance()
  {
    return instance;
  }
  
  public void clear()
  {
    this.map.clear();
  }
  
  public XTokenLoader.Result get(Object paramObject)
  {
    return (XTokenLoader.Result)this.map.get(paramObject);
  }
  
  public XTokenLoader.Result put(Object paramObject, XTokenLoader.Result paramResult)
  {
    return (XTokenLoader.Result)this.map.put(paramObject, paramResult);
  }
  
  public XTokenLoader.Result remove(Object paramObject)
  {
    return (XTokenLoader.Result)this.map.remove(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\interop\xbox\util\XTokenLoaderCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */