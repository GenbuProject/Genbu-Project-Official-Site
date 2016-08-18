package org.simpleframework.xml.transform;

import java.net.URL;

class URLTransform
  implements Transform<URL>
{
  public URL read(String paramString)
    throws Exception
  {
    return new URL(paramString);
  }
  
  public String write(URL paramURL)
    throws Exception
  {
    return paramURL.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\URLTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */