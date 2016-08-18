package org.simpleframework.xml.stream;

import java.util.ArrayList;

class Stack<T>
  extends ArrayList<T>
{
  public Stack(int paramInt)
  {
    super(paramInt);
  }
  
  public T bottom()
  {
    if (size() <= 0) {
      return null;
    }
    return (T)get(0);
  }
  
  public T pop()
  {
    int i = size();
    if (i <= 0) {
      return null;
    }
    return (T)remove(i - 1);
  }
  
  public T push(T paramT)
  {
    add(paramT);
    return paramT;
  }
  
  public T top()
  {
    int i = size();
    if (i <= 0) {
      return null;
    }
    return (T)get(i - 1);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\stream\Stack.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */