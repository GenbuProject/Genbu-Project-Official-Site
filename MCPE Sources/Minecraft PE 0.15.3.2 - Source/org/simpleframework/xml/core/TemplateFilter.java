package org.simpleframework.xml.core;

import org.simpleframework.xml.filter.Filter;

class TemplateFilter
  implements Filter
{
  private Context context;
  private Filter filter;
  
  public TemplateFilter(Context paramContext, Filter paramFilter)
  {
    this.context = paramContext;
    this.filter = paramFilter;
  }
  
  public String replace(String paramString)
  {
    Object localObject = this.context.getAttribute(paramString);
    if (localObject != null) {
      return localObject.toString();
    }
    return this.filter.replace(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\core\TemplateFilter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */