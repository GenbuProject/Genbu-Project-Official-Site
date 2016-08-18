package org.simpleframework.xml.stream;

import org.simpleframework.xml.util.Cache;
import org.simpleframework.xml.util.ConcurrentCache;

class Builder
  implements Style
{
  private final Cache<String> attributes = new ConcurrentCache();
  private final Cache<String> elements = new ConcurrentCache();
  private final Style style;
  
  public Builder(Style paramStyle)
  {
    this.style = paramStyle;
  }
  
  public String getAttribute(String paramString)
  {
    String str = (String)this.attributes.fetch(paramString);
    if (str != null) {
      return str;
    }
    str = this.style.getAttribute(paramString);
    if (str != null) {
      this.attributes.cache(paramString, str);
    }
    return str;
  }
  
  public String getElement(String paramString)
  {
    String str = (String)this.elements.fetch(paramString);
    if (str != null) {
      return str;
    }
    str = this.style.getElement(paramString);
    if (str != null) {
      this.elements.cache(paramString, str);
    }
    return str;
  }
  
  public void setAttribute(String paramString1, String paramString2)
  {
    this.attributes.cache(paramString1, paramString2);
  }
  
  public void setElement(String paramString1, String paramString2)
  {
    this.elements.cache(paramString1, paramString2);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\Builder.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */