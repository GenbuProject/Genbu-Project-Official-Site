package org.simpleframework.xml.transform;

class FloatTransform
  implements Transform<Float>
{
  public Float read(String paramString)
  {
    return Float.valueOf(paramString);
  }
  
  public String write(Float paramFloat)
  {
    return paramFloat.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\FloatTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */