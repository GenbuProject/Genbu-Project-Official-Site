package org.simpleframework.xml.transform;

class DoubleTransform
  implements Transform<Double>
{
  public Double read(String paramString)
  {
    return Double.valueOf(paramString);
  }
  
  public String write(Double paramDouble)
  {
    return paramDouble.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\DoubleTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */