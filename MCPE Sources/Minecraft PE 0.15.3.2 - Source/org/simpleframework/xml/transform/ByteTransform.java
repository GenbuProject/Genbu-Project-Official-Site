package org.simpleframework.xml.transform;

class ByteTransform
  implements Transform<Byte>
{
  public Byte read(String paramString)
  {
    return Byte.valueOf(paramString);
  }
  
  public String write(Byte paramByte)
  {
    return paramByte.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\ByteTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */