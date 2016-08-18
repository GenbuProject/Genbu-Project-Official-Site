package org.simpleframework.xml.filter;

import java.util.Map;

public class MapFilter
  implements Filter
{
  private Filter filter;
  private Map map;
  
  public MapFilter(Map paramMap)
  {
    this(paramMap, null);
  }
  
  public MapFilter(Map paramMap, Filter paramFilter)
  {
    this.filter = paramFilter;
    this.map = paramMap;
  }
  
  public String replace(String paramString)
  {
    Object localObject = null;
    if (this.map != null) {
      localObject = this.map.get(paramString);
    }
    if (localObject != null) {
      return localObject.toString();
    }
    if (this.filter != null) {
      return this.filter.replace(paramString);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\filter\MapFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */