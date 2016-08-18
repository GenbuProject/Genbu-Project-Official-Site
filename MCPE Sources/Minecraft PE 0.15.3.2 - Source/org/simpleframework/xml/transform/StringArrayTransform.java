package org.simpleframework.xml.transform;

import java.util.regex.Pattern;

class StringArrayTransform
  implements Transform<String[]>
{
  private final Pattern pattern;
  private final String token;
  
  public StringArrayTransform()
  {
    this(",");
  }
  
  public StringArrayTransform(String paramString)
  {
    this.pattern = Pattern.compile(paramString);
    this.token = paramString;
  }
  
  private String[] read(String paramString1, String paramString2)
  {
    paramString1 = this.pattern.split(paramString1);
    int i = 0;
    while (i < paramString1.length)
    {
      paramString2 = paramString1[i];
      if (paramString2 != null) {
        paramString1[i] = paramString2.trim();
      }
      i += 1;
    }
    return paramString1;
  }
  
  private String write(String[] paramArrayOfString, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      String str = paramArrayOfString[i];
      if (str != null)
      {
        if (localStringBuilder.length() > 0)
        {
          localStringBuilder.append(paramString);
          localStringBuilder.append(' ');
        }
        localStringBuilder.append(str);
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public String[] read(String paramString)
  {
    return read(paramString, this.token);
  }
  
  public String write(String[] paramArrayOfString)
  {
    return write(paramArrayOfString, this.token);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\StringArrayTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */