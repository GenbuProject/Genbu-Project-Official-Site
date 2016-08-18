package com.microsoft.xbox.toolkit;

import java.util.HashSet;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.Set;

public class MultiMap<K, V>
{
  private Hashtable<K, HashSet<V>> data = new Hashtable();
  private Hashtable<V, K> dataInverse = new Hashtable();
  
  private void removeKeyIfEmpty(K paramK)
  {
    HashSet localHashSet = get(paramK);
    if ((localHashSet != null) && (localHashSet.isEmpty())) {
      this.data.remove(paramK);
    }
  }
  
  public int TESTsizeDegenerate()
  {
    int i = 0;
    Iterator localIterator = this.data.keySet().iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      if (((HashSet)this.data.get(localObject)).size() == 0) {
        i += 1;
      }
    }
    return i;
  }
  
  public void clear()
  {
    this.data.clear();
    this.dataInverse.clear();
  }
  
  public boolean containsKey(K paramK)
  {
    return this.data.containsKey(paramK);
  }
  
  public boolean containsValue(V paramV)
  {
    return getKey(paramV) != null;
  }
  
  public HashSet<V> get(K paramK)
  {
    return (HashSet)this.data.get(paramK);
  }
  
  public K getKey(V paramV)
  {
    return (K)this.dataInverse.get(paramV);
  }
  
  public boolean keyValueMatches(K paramK, V paramV)
  {
    paramK = get(paramK);
    if (paramK == null) {
      return false;
    }
    return paramK.contains(paramV);
  }
  
  public void put(K paramK, V paramV)
  {
    if (this.data.get(paramK) == null) {
      this.data.put(paramK, new HashSet());
    }
    if (!this.dataInverse.containsKey(paramV)) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      ((HashSet)this.data.get(paramK)).add(paramV);
      this.dataInverse.put(paramV, paramK);
      return;
    }
  }
  
  public void removeKey(K paramK)
  {
    Iterator localIterator = ((HashSet)this.data.get(paramK)).iterator();
    while (localIterator.hasNext())
    {
      Object localObject = localIterator.next();
      XLEAssert.assertTrue(this.dataInverse.containsKey(localObject));
      this.dataInverse.remove(localObject);
    }
    this.data.remove(paramK);
  }
  
  public void removeValue(V paramV)
  {
    Object localObject = getKey(paramV);
    ((HashSet)this.data.get(localObject)).remove(paramV);
    this.dataInverse.remove(paramV);
    removeKeyIfEmpty(localObject);
  }
  
  public int size()
  {
    return this.data.size();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\MultiMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */