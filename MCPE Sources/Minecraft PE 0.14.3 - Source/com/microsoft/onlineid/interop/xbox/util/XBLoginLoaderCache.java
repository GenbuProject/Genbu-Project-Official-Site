package com.microsoft.onlineid.interop.xbox.util;

import com.microsoft.onlineid.interop.xbox.toolkit.XBLoginLoader.Cache;
import com.microsoft.onlineid.interop.xbox.toolkit.XBLoginLoader.Result;
import java.util.HashMap;

public class XBLoginLoaderCache
  implements XBLoginLoader.Cache
{
  private static final XBLoginLoader.Cache instance = new XBLoginLoaderCache();
  private final HashMap<Object, XBLoginLoader.Result> map = new HashMap();
  
  public static XBLoginLoader.Cache getInstance()
  {
    return instance;
  }
  
  public void clear()
  {
    this.map.clear();
  }
  
  public XBLoginLoader.Result get(Object paramObject)
  {
    return (XBLoginLoader.Result)this.map.get(paramObject);
  }
  
  public XBLoginLoader.Result put(Object paramObject, XBLoginLoader.Result paramResult)
  {
    return (XBLoginLoader.Result)this.map.put(paramObject, paramResult);
  }
  
  public XBLoginLoader.Result remove(Object paramObject)
  {
    return (XBLoginLoader.Result)this.map.remove(paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\interop\xbox\util\XBLoginLoaderCache.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */