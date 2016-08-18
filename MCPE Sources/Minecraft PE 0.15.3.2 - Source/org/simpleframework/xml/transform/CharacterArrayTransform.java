package org.simpleframework.xml.transform;

import java.lang.reflect.Array;

class CharacterArrayTransform
  implements Transform
{
  private final Class entry;
  
  public CharacterArrayTransform(Class paramClass)
  {
    this.entry = paramClass;
  }
  
  private Object read(char[] paramArrayOfChar, int paramInt)
    throws Exception
  {
    Object localObject = Array.newInstance(this.entry, paramInt);
    int i = 0;
    while (i < paramInt)
    {
      Array.set(localObject, i, Character.valueOf(paramArrayOfChar[i]));
      i += 1;
    }
    return localObject;
  }
  
  private String write(Object paramObject, int paramInt)
    throws Exception
  {
    StringBuilder localStringBuilder = new StringBuilder(paramInt);
    int i = 0;
    while (i < paramInt)
    {
      Object localObject = Array.get(paramObject, i);
      if (localObject != null) {
        localStringBuilder.append(localObject);
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  public Object read(String paramString)
    throws Exception
  {
    paramString = paramString.toCharArray();
    int i = paramString.length;
    if (this.entry == Character.TYPE) {
      return paramString;
    }
    return read(paramString, i);
  }
  
  public String write(Object paramObject)
    throws Exception
  {
    int i = Array.getLength(paramObject);
    if (this.entry == Character.TYPE) {
      return new String((char[])paramObject);
    }
    return write(paramObject, i);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\CharacterArrayTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */