package org.simpleframework.xml.transform;

import java.util.Date;

class DateTransform<T extends Date>
  implements Transform<T>
{
  private final DateFactory<T> factory;
  
  public DateTransform(Class<T> paramClass)
    throws Exception
  {
    this.factory = new DateFactory(paramClass);
  }
  
  public T read(String paramString)
    throws Exception
  {
    try
    {
      long l = DateType.getDate(paramString).getTime();
      paramString = this.factory.getInstance(new Object[] { Long.valueOf(l) });
      return paramString;
    }
    finally
    {
      paramString = finally;
      throw paramString;
    }
  }
  
  public String write(T paramT)
    throws Exception
  {
    try
    {
      paramT = DateType.getText(paramT);
      return paramT;
    }
    finally
    {
      paramT = finally;
      throw paramT;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\DateTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */