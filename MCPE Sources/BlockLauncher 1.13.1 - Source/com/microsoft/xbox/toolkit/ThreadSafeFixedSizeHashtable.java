package com.microsoft.xbox.toolkit;

import java.util.Enumeration;
import java.util.Hashtable;
import java.util.Iterator;
import java.util.PriorityQueue;

public class ThreadSafeFixedSizeHashtable<K, V>
{
  private int count = 0;
  private PriorityQueue<ThreadSafeFixedSizeHashtable<K, V>.KeyTuple> fifo = new PriorityQueue();
  private Hashtable<K, V> hashtable = new Hashtable();
  private final int maxSize;
  private Object syncObject = new Object();
  
  public ThreadSafeFixedSizeHashtable(int paramInt)
  {
    this.maxSize = paramInt;
    if (paramInt <= 0) {
      throw new IllegalArgumentException();
    }
  }
  
  private void cleanupIfNecessary()
  {
    if (this.hashtable.size() == this.fifo.size())
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      label23:
      if (this.hashtable.size() <= this.maxSize) {
        return;
      }
      KeyTuple localKeyTuple = (KeyTuple)this.fifo.remove();
      this.hashtable.remove(localKeyTuple.getKey());
      if (this.hashtable.size() != this.fifo.size()) {
        break label91;
      }
    }
    label91:
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      break label23;
      bool = false;
      break;
    }
  }
  
  public Enumeration<V> elements()
  {
    return this.hashtable.elements();
  }
  
  public V get(K paramK)
  {
    if (paramK == null) {
      return null;
    }
    synchronized (this.syncObject)
    {
      paramK = this.hashtable.get(paramK);
      return paramK;
    }
  }
  
  public Enumeration<K> keys()
  {
    return this.hashtable.keys();
  }
  
  public void put(K paramK, V paramV)
  {
    if ((paramK == null) || (paramV == null)) {
      return;
    }
    synchronized (this.syncObject)
    {
      if (this.hashtable.containsKey(paramK)) {
        return;
      }
    }
    this.count += 1;
    this.fifo.add(new KeyTuple(paramK, this.count));
    this.hashtable.put(paramK, paramV);
    cleanupIfNecessary();
  }
  
  public void remove(K paramK)
  {
    if (paramK == null) {
      return;
    }
    synchronized (this.syncObject)
    {
      if (!this.hashtable.containsKey(paramK)) {
        return;
      }
    }
    this.hashtable.remove(paramK);
    Iterator localIterator = this.fifo.iterator();
    KeyTuple localKeyTuple;
    do
    {
      if (!localIterator.hasNext()) {
        break;
      }
      localKeyTuple = (KeyTuple)localIterator.next();
    } while (!localKeyTuple.key.equals(paramK));
    for (paramK = localKeyTuple;; paramK = null)
    {
      if (paramK != null) {
        this.fifo.remove(paramK);
      }
      return;
    }
  }
  
  private class KeyTuple
    implements Comparable<ThreadSafeFixedSizeHashtable<K, V>.KeyTuple>
  {
    private int index = 0;
    private K key;
    
    public KeyTuple(int paramInt)
    {
      this.key = paramInt;
      int i;
      this.index = i;
    }
    
    public int compareTo(ThreadSafeFixedSizeHashtable<K, V>.KeyTuple paramThreadSafeFixedSizeHashtable)
    {
      return this.index - paramThreadSafeFixedSizeHashtable.index;
    }
    
    public K getKey()
    {
      return (K)this.key;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\ThreadSafeFixedSizeHashtable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */