package com.microsoft.xbox.toolkit;

import java.util.ArrayList;
import java.util.HashSet;

public class MultiSelection<T>
{
  private HashSet<T> selection = new HashSet();
  
  public void add(T paramT)
  {
    this.selection.add(paramT);
  }
  
  public boolean contains(T paramT)
  {
    return this.selection.contains(paramT);
  }
  
  public boolean isEmpty()
  {
    return this.selection.isEmpty();
  }
  
  public void remove(T paramT)
  {
    this.selection.remove(paramT);
  }
  
  public void reset()
  {
    this.selection.clear();
  }
  
  public int size()
  {
    return this.selection.size();
  }
  
  public ArrayList<T> toArrayList()
  {
    return new ArrayList(this.selection);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\toolkit\MultiSelection.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */