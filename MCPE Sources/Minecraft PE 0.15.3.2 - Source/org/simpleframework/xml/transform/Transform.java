package org.simpleframework.xml.transform;

public abstract interface Transform<T>
{
  public abstract T read(String paramString)
    throws Exception;
  
  public abstract String write(T paramT)
    throws Exception;
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\Transform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */