package com.microsoft.onlineid.internal.storage;

import android.util.Base64;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class ObjectStreamSerializer<ObjectType>
  implements ISerializer<ObjectType>
{
  /* Error */
  public ObjectType deserialize(String paramString)
    throws IOException
  {
    // Byte code:
    //   0: aconst_null
    //   1: astore_2
    //   2: aload_1
    //   3: ifnull +36 -> 39
    //   6: aload_1
    //   7: iconst_2
    //   8: invokestatic 28	android/util/Base64:decode	(Ljava/lang/String;I)[B
    //   11: astore_1
    //   12: new 30	java/io/ObjectInputStream
    //   15: dup
    //   16: new 32	java/io/ByteArrayInputStream
    //   19: dup
    //   20: aload_1
    //   21: invokespecial 35	java/io/ByteArrayInputStream:<init>	([B)V
    //   24: invokespecial 38	java/io/ObjectInputStream:<init>	(Ljava/io/InputStream;)V
    //   27: astore_2
    //   28: aload_2
    //   29: invokevirtual 42	java/io/ObjectInputStream:readObject	()Ljava/lang/Object;
    //   32: astore_1
    //   33: aload_2
    //   34: invokevirtual 45	java/io/ObjectInputStream:close	()V
    //   37: aload_1
    //   38: astore_2
    //   39: aload_2
    //   40: areturn
    //   41: astore_1
    //   42: new 16	java/io/IOException
    //   45: dup
    //   46: aload_1
    //   47: invokespecial 48	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   50: athrow
    //   51: astore_1
    //   52: new 16	java/io/IOException
    //   55: dup
    //   56: aload_1
    //   57: invokespecial 48	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   60: athrow
    //   61: astore_1
    //   62: aload_2
    //   63: invokevirtual 45	java/io/ObjectInputStream:close	()V
    //   66: aload_1
    //   67: athrow
    //   68: astore_1
    //   69: new 16	java/io/IOException
    //   72: dup
    //   73: aload_1
    //   74: invokespecial 48	java/io/IOException:<init>	(Ljava/lang/Throwable;)V
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	ObjectStreamSerializer
    //   0	78	1	paramString	String
    //   1	62	2	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   6	12	41	java/lang/IllegalArgumentException
    //   28	33	51	java/lang/ClassNotFoundException
    //   28	33	61	finally
    //   52	61	61	finally
    //   69	78	61	finally
    //   28	33	68	java/lang/ClassCastException
  }
  
  public Set<ObjectType> deserializeAll(Map<String, String> paramMap)
    throws IOException
  {
    if (paramMap.isEmpty())
    {
      paramMap = Collections.emptySet();
      return paramMap;
    }
    HashSet localHashSet = new HashSet();
    Iterator localIterator = paramMap.values().iterator();
    for (;;)
    {
      paramMap = localHashSet;
      if (!localIterator.hasNext()) {
        break;
      }
      localHashSet.add(deserialize((String)localIterator.next()));
    }
  }
  
  public String serialize(ObjectType paramObjectType)
    throws IOException
  {
    Object localObject = null;
    ByteArrayOutputStream localByteArrayOutputStream;
    if (paramObjectType != null)
    {
      localByteArrayOutputStream = new ByteArrayOutputStream();
      localObject = new ObjectOutputStream(localByteArrayOutputStream);
    }
    try
    {
      ((ObjectOutputStream)localObject).writeObject(paramObjectType);
      ((ObjectOutputStream)localObject).close();
      localObject = Base64.encodeToString(localByteArrayOutputStream.toByteArray(), 2);
      return (String)localObject;
    }
    finally
    {
      ((ObjectOutputStream)localObject).close();
    }
  }
  
  public Map<String, String> serializeAll(Map<String, ObjectType> paramMap)
    throws IOException
  {
    if (paramMap.isEmpty())
    {
      paramMap = Collections.emptyMap();
      return paramMap;
    }
    HashMap localHashMap = new HashMap();
    Iterator localIterator = paramMap.entrySet().iterator();
    for (;;)
    {
      paramMap = localHashMap;
      if (!localIterator.hasNext()) {
        break;
      }
      paramMap = (Map.Entry)localIterator.next();
      localHashMap.put(paramMap.getKey(), serialize(paramMap.getValue()));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\storage\ObjectStreamSerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */