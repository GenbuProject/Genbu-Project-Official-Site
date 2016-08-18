package org.simpleframework.xml.transform;

import java.util.TimeZone;

class TimeZoneTransform
  implements Transform<TimeZone>
{
  public TimeZone read(String paramString)
  {
    return TimeZone.getTimeZone(paramString);
  }
  
  public String write(TimeZone paramTimeZone)
  {
    return paramTimeZone.getID();
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\TimeZoneTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */