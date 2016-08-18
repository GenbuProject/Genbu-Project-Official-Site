package org.simpleframework.xml.transform;

import java.util.Date;
import java.util.GregorianCalendar;

class GregorianCalendarTransform
  implements Transform<GregorianCalendar>
{
  private final DateTransform transform;
  
  public GregorianCalendarTransform()
    throws Exception
  {
    this(Date.class);
  }
  
  public GregorianCalendarTransform(Class paramClass)
    throws Exception
  {
    this.transform = new DateTransform(paramClass);
  }
  
  private GregorianCalendar read(Date paramDate)
    throws Exception
  {
    GregorianCalendar localGregorianCalendar = new GregorianCalendar();
    if (paramDate != null) {
      localGregorianCalendar.setTime(paramDate);
    }
    return localGregorianCalendar;
  }
  
  public GregorianCalendar read(String paramString)
    throws Exception
  {
    return read(this.transform.read(paramString));
  }
  
  public String write(GregorianCalendar paramGregorianCalendar)
    throws Exception
  {
    return this.transform.write(paramGregorianCalendar.getTime());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\GregorianCalendarTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */