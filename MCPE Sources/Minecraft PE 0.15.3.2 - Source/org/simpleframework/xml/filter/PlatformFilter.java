package org.simpleframework.xml.filter;

import java.util.Map;

public class PlatformFilter
  extends StackFilter
{
  public PlatformFilter()
  {
    this(null);
  }
  
  public PlatformFilter(Map paramMap)
  {
    push(new EnvironmentFilter());
    push(new SystemFilter());
    push(new MapFilter(paramMap));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\filter\PlatformFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */