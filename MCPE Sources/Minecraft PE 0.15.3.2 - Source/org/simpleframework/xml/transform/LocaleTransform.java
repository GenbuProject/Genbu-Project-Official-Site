package org.simpleframework.xml.transform;

import java.util.Locale;
import java.util.regex.Pattern;

class LocaleTransform
  implements Transform<Locale>
{
  private final Pattern pattern = Pattern.compile("_");
  
  private Locale read(String[] paramArrayOfString)
    throws Exception
  {
    String[] arrayOfString = new String[3];
    arrayOfString[0] = "";
    arrayOfString[1] = "";
    arrayOfString[2] = "";
    int i = 0;
    while (i < arrayOfString.length)
    {
      if (i < paramArrayOfString.length) {
        arrayOfString[i] = paramArrayOfString[i];
      }
      i += 1;
    }
    return new Locale(arrayOfString[0], arrayOfString[1], arrayOfString[2]);
  }
  
  public Locale read(String paramString)
    throws Exception
  {
    String[] arrayOfString = this.pattern.split(paramString);
    if (arrayOfString.length < 1) {
      throw new InvalidFormatException("Invalid locale %s", new Object[] { paramString });
    }
    return read(arrayOfString);
  }
  
  public String write(Locale paramLocale)
  {
    return paramLocale.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\LocaleTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */