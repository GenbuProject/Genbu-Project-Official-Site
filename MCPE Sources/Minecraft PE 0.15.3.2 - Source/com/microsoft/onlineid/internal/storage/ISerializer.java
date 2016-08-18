package com.microsoft.onlineid.internal.storage;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

abstract interface ISerializer<ObjectType>
{
  public abstract ObjectType deserialize(String paramString)
    throws IOException;
  
  public abstract Set<ObjectType> deserializeAll(Map<String, String> paramMap)
    throws IOException;
  
  public abstract String serialize(ObjectType paramObjectType)
    throws IOException;
  
  public abstract Map<String, String> serializeAll(Map<String, ObjectType> paramMap)
    throws IOException;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\storage\ISerializer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */