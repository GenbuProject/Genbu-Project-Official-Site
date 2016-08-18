package com.google.gson;

import java.lang.reflect.Type;

public abstract interface InstanceCreator<T>
{
  public abstract T createInstance(Type paramType);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\google\gson\InstanceCreator.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */