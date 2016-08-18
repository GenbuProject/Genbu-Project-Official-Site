package org.simpleframework.xml.transform;

class BooleanTransform
  implements Transform<Boolean>
{
  public Boolean read(String paramString)
  {
    return Boolean.valueOf(paramString);
  }
  
  public String write(Boolean paramBoolean)
  {
    return paramBoolean.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\BooleanTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */