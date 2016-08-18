package org.simpleframework.xml.transform;

class ShortTransform
  implements Transform<Short>
{
  public Short read(String paramString)
  {
    return Short.valueOf(paramString);
  }
  
  public String write(Short paramShort)
  {
    return paramShort.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\ShortTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */