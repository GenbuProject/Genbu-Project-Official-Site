package org.simpleframework.xml.filter;

public class EnvironmentFilter
  implements Filter
{
  private Filter filter;
  
  public EnvironmentFilter()
  {
    this(null);
  }
  
  public EnvironmentFilter(Filter paramFilter)
  {
    this.filter = paramFilter;
  }
  
  public String replace(String paramString)
  {
    String str = System.getenv(paramString);
    if (str != null) {
      return str;
    }
    if (this.filter != null) {
      return this.filter.replace(paramString);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\filter\EnvironmentFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */