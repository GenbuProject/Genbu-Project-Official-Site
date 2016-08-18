package org.simpleframework.xml.util;

import java.util.AbstractSet;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

public class Resolver<M extends Match>
  extends AbstractSet<M>
{
  protected final Resolver<M>.Cache cache = new Cache();
  protected final Resolver<M>.Stack stack = new Stack(null);
  
  private boolean match(char[] paramArrayOfChar1, int paramInt1, char[] paramArrayOfChar2, int paramInt2)
  {
    for (;;)
    {
      int j;
      int i;
      if ((paramInt2 < paramArrayOfChar2.length) && (paramInt1 < paramArrayOfChar1.length))
      {
        j = paramInt1;
        i = paramInt2;
        if (paramArrayOfChar2[paramInt2] == '*')
        {
          j = paramInt2;
          do
          {
            if (paramArrayOfChar2[j] != '*') {
              break;
            }
            paramInt2 = j + 1;
            j = paramInt2;
          } while (paramInt2 < paramArrayOfChar2.length);
        }
      }
      label153:
      do
      {
        do
        {
          return true;
          paramInt2 = paramInt1;
          i = j;
          if (paramArrayOfChar2[j] != '?') {
            break;
          }
          i = j + 1;
        } while (i >= paramArrayOfChar2.length);
        paramInt2 = paramInt1;
        for (;;)
        {
          if (paramInt2 >= paramArrayOfChar1.length) {
            break label153;
          }
          if ((paramArrayOfChar1[paramInt2] == paramArrayOfChar2[i]) || (paramArrayOfChar2[i] == '?'))
          {
            if (paramArrayOfChar2[(i - 1)] == '?') {
              break label153;
            }
            if (match(paramArrayOfChar1, paramInt2, paramArrayOfChar2, i)) {
              break;
            }
          }
          paramInt2 += 1;
        }
        j = paramInt2;
        if (paramArrayOfChar1.length == paramInt2) {
          return false;
        }
        paramInt1 = paramArrayOfChar1[j];
        paramInt2 = i + 1;
        if ((paramInt1 == paramArrayOfChar2[i]) || (paramArrayOfChar2[(paramInt2 - 1)] == '?')) {
          break label244;
        }
        return false;
        i = paramInt2;
        if (paramArrayOfChar2.length != paramInt2) {
          break;
        }
      } while (paramArrayOfChar1.length == paramInt1);
      return false;
      while (paramArrayOfChar2[i] == '*')
      {
        paramInt1 = i + 1;
        i = paramInt1;
        if (paramInt1 >= paramArrayOfChar2.length) {
          return true;
        }
      }
      return false;
      label244:
      paramInt1 = j + 1;
    }
  }
  
  private boolean match(char[] paramArrayOfChar1, char[] paramArrayOfChar2)
  {
    return match(paramArrayOfChar1, 0, paramArrayOfChar2, 0);
  }
  
  private List<M> resolveAll(String paramString, char[] paramArrayOfChar)
  {
    ArrayList localArrayList = new ArrayList();
    Iterator localIterator = this.stack.iterator();
    while (localIterator.hasNext())
    {
      Match localMatch = (Match)localIterator.next();
      if (match(paramArrayOfChar, localMatch.getPattern().toCharArray()))
      {
        this.cache.put(paramString, localArrayList);
        localArrayList.add(localMatch);
      }
    }
    return localArrayList;
  }
  
  public boolean add(M paramM)
  {
    this.stack.push(paramM);
    return true;
  }
  
  public void clear()
  {
    this.cache.clear();
    this.stack.clear();
  }
  
  public Iterator<M> iterator()
  {
    return this.stack.sequence();
  }
  
  public boolean remove(M paramM)
  {
    this.cache.clear();
    return this.stack.remove(paramM);
  }
  
  public M resolve(String paramString)
  {
    List localList2 = (List)this.cache.get(paramString);
    List localList1 = localList2;
    if (localList2 == null) {
      localList1 = resolveAll(paramString);
    }
    if (localList1.isEmpty()) {
      return null;
    }
    return (Match)localList1.get(0);
  }
  
  public List<M> resolveAll(String paramString)
  {
    Object localObject = (List)this.cache.get(paramString);
    if (localObject != null) {
      return (List<M>)localObject;
    }
    localObject = paramString.toCharArray();
    if (localObject == null) {
      return null;
    }
    return resolveAll(paramString, (char[])localObject);
  }
  
  public int size()
  {
    return this.stack.size();
  }
  
  private class Cache
    extends LimitedCache<List<M>>
  {
    public Cache()
    {
      super();
    }
  }
  
  private class Stack
    extends LinkedList<M>
  {
    private Stack() {}
    
    public void purge(int paramInt)
    {
      Resolver.this.cache.clear();
      remove(paramInt);
    }
    
    public void push(M paramM)
    {
      Resolver.this.cache.clear();
      addFirst(paramM);
    }
    
    public Iterator<M> sequence()
    {
      return new Sequence();
    }
    
    private class Sequence
      implements Iterator<M>
    {
      private int cursor = Resolver.Stack.this.size();
      
      public Sequence() {}
      
      public boolean hasNext()
      {
        return this.cursor > 0;
      }
      
      public M next()
      {
        if (hasNext())
        {
          Resolver.Stack localStack = Resolver.Stack.this;
          int i = this.cursor - 1;
          this.cursor = i;
          return (Match)localStack.get(i);
        }
        return null;
      }
      
      public void remove()
      {
        Resolver.Stack.this.purge(this.cursor);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\org\simpleframework\xml\util\Resolver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */