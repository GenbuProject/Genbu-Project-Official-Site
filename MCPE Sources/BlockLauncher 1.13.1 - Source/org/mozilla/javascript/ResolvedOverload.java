package org.mozilla.javascript;

import java.util.Arrays;

class ResolvedOverload
{
  final int index;
  final Class<?>[] types;
  
  ResolvedOverload(Object[] paramArrayOfObject, int paramInt)
  {
    this.index = paramInt;
    this.types = new Class[paramArrayOfObject.length];
    int i = paramArrayOfObject.length;
    paramInt = 0;
    if (paramInt < i)
    {
      Object localObject2 = paramArrayOfObject[paramInt];
      Object localObject1 = localObject2;
      if ((localObject2 instanceof Wrapper)) {
        localObject1 = ((Wrapper)localObject2).unwrap();
      }
      localObject2 = this.types;
      if (localObject1 == null) {}
      for (localObject1 = null;; localObject1 = localObject1.getClass())
      {
        localObject2[paramInt] = localObject1;
        paramInt += 1;
        break;
      }
    }
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof ResolvedOverload)) {}
    do
    {
      return false;
      paramObject = (ResolvedOverload)paramObject;
    } while ((!Arrays.equals(this.types, ((ResolvedOverload)paramObject).types)) || (this.index != ((ResolvedOverload)paramObject).index));
    return true;
  }
  
  public int hashCode()
  {
    return Arrays.hashCode(this.types);
  }
  
  boolean matches(Object[] paramArrayOfObject)
  {
    if (paramArrayOfObject.length != this.types.length) {
      return false;
    }
    int j = paramArrayOfObject.length;
    int i = 0;
    while (i < j)
    {
      Object localObject2 = paramArrayOfObject[i];
      Object localObject1 = localObject2;
      if ((localObject2 instanceof Wrapper)) {
        localObject1 = ((Wrapper)localObject2).unwrap();
      }
      if (localObject1 == null)
      {
        if (this.types[i] != null) {
          return false;
        }
      }
      else if (localObject1.getClass() != this.types[i]) {
        return false;
      }
      i += 1;
    }
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\ResolvedOverload.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */