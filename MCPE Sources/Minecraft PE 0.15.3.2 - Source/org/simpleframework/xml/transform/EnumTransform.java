package org.simpleframework.xml.transform;

class EnumTransform
  implements Transform<Enum>
{
  private final Class type;
  
  public EnumTransform(Class paramClass)
  {
    this.type = paramClass;
  }
  
  public Enum read(String paramString)
    throws Exception
  {
    return Enum.valueOf(this.type, paramString);
  }
  
  public String write(Enum paramEnum)
    throws Exception
  {
    return paramEnum.name();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\EnumTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */