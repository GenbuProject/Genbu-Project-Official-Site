package com.microsoft.xbox.idp.util;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

public class HttpHeaders
{
  private final List<Header> headers = new ArrayList();
  
  public void add(String paramString1, String paramString2)
  {
    this.headers.add(new Header(paramString1, paramString2));
  }
  
  public Collection<Header> getAllHeaders()
  {
    return this.headers;
  }
  
  public Header getFirstHeader(String paramString)
  {
    if (paramString != null)
    {
      Iterator localIterator = this.headers.iterator();
      while (localIterator.hasNext())
      {
        Header localHeader = (Header)localIterator.next();
        if (paramString.equals(localHeader.key)) {
          return localHeader;
        }
      }
    }
    return null;
  }
  
  public Header getLastHeader(String paramString)
  {
    if (paramString != null)
    {
      int i = this.headers.size() - 1;
      while (i >= 0)
      {
        Header localHeader = (Header)this.headers.get(i);
        if (paramString.equals(localHeader.key)) {
          return localHeader;
        }
        i -= 1;
      }
    }
    return null;
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("[ ");
    Iterator localIterator = this.headers.iterator();
    while (localIterator.hasNext()) {
      localStringBuilder.append((Header)localIterator.next());
    }
    localStringBuilder.append(" ]");
    return localStringBuilder.toString();
  }
  
  public static class Header
  {
    private final String key;
    private final String value;
    
    public Header(String paramString1, String paramString2)
    {
      this.key = paramString1;
      this.value = paramString2;
    }
    
    public String getKey()
    {
      return this.key;
    }
    
    public String getValue()
    {
      return this.value;
    }
    
    public String toString()
    {
      StringBuilder localStringBuilder = new StringBuilder();
      localStringBuilder.append("{ ").append("\"").append(this.key).append("\": ").append("\"").append(this.value).append("\"").append(" }");
      return localStringBuilder.toString();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\util\HttpHeaders.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */