package org.simpleframework.xml.transform;

import java.lang.reflect.Array;

class ArrayTransform
  implements Transform
{
  private final Transform delegate;
  private final Class entry;
  private final StringArrayTransform split = new StringArrayTransform();
  
  public ArrayTransform(Transform paramTransform, Class paramClass)
  {
    this.delegate = paramTransform;
    this.entry = paramClass;
  }
  
  private Object read(String[] paramArrayOfString, int paramInt)
    throws Exception
  {
    Object localObject1 = Array.newInstance(this.entry, paramInt);
    int i = 0;
    while (i < paramInt)
    {
      Object localObject2 = this.delegate.read(paramArrayOfString[i]);
      if (localObject2 != null) {
        Array.set(localObject1, i, localObject2);
      }
      i += 1;
    }
    return localObject1;
  }
  
  private String write(Object paramObject, int paramInt)
    throws Exception
  {
    String[] arrayOfString = new String[paramInt];
    int i = 0;
    while (i < paramInt)
    {
      Object localObject = Array.get(paramObject, i);
      if (localObject != null) {
        arrayOfString[i] = this.delegate.write(localObject);
      }
      i += 1;
    }
    return this.split.write(arrayOfString);
  }
  
  public Object read(String paramString)
    throws Exception
  {
    paramString = this.split.read(paramString);
    return read(paramString, paramString.length);
  }
  
  public String write(Object paramObject)
    throws Exception
  {
    return write(paramObject, Array.getLength(paramObject));
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\transform\ArrayTransform.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */