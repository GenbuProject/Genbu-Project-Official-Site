package org.simpleframework.xml.transform;

class LongTransform
  implements Transform<Long>
{
  public Long read(String paramString)
  {
    return Long.valueOf(paramString);
  }
  
  public String write(Long paramLong)
  {
    return paramLong.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\LongTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */