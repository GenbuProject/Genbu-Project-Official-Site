package org.simpleframework.xml.transform;

import java.text.SimpleDateFormat;
import java.util.Date;

 enum DateType
{
  FULL("yyyy-MM-dd HH:mm:ss.S z"),  LONG("yyyy-MM-dd HH:mm:ss z"),  NORMAL("yyyy-MM-dd z"),  SHORT("yyyy-MM-dd");
  
  private DateFormat format;
  
  private DateType(String paramString)
  {
    this.format = new DateFormat(paramString);
  }
  
  public static Date getDate(String paramString)
    throws Exception
  {
    return getType(paramString).getFormat().getDate(paramString);
  }
  
  private DateFormat getFormat()
  {
    return this.format;
  }
  
  public static String getText(Date paramDate)
    throws Exception
  {
    return FULL.getFormat().getText(paramDate);
  }
  
  public static DateType getType(String paramString)
  {
    int i = paramString.length();
    if (i > 23) {
      return FULL;
    }
    if (i > 20) {
      return LONG;
    }
    if (i > 11) {
      return NORMAL;
    }
    return SHORT;
  }
  
  private static class DateFormat
  {
    private SimpleDateFormat format;
    
    public DateFormat(String paramString)
    {
      this.format = new SimpleDateFormat(paramString);
    }
    
    public Date getDate(String paramString)
      throws Exception
    {
      try
      {
        paramString = this.format.parse(paramString);
        return paramString;
      }
      finally
      {
        paramString = finally;
        throw paramString;
      }
    }
    
    public String getText(Date paramDate)
      throws Exception
    {
      try
      {
        paramDate = this.format.format(paramDate);
        return paramDate;
      }
      finally
      {
        paramDate = finally;
        throw paramDate;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\DateType.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */