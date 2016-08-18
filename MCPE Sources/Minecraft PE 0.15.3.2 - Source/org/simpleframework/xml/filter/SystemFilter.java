package org.simpleframework.xml.filter;

public class SystemFilter
  implements Filter
{
  private Filter filter;
  
  public SystemFilter()
  {
    this(null);
  }
  
  public SystemFilter(Filter paramFilter)
  {
    this.filter = paramFilter;
  }
  
  public String replace(String paramString)
  {
    String str = System.getProperty(paramString);
    if (str != null) {
      return str;
    }
    if (this.filter != null) {
      return this.filter.replace(paramString);
    }
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\filter\SystemFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */