package com.microsoft.onlineid.ui;

import android.database.DataSetObserver;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListAdapter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public abstract class AbstractListAdapter<T>
  implements ListAdapter
{
  protected List<T> _items = new ArrayList();
  protected Set<DataSetObserver> _observers = new HashSet();
  
  private void fireChanged()
  {
    onChanged();
    Iterator localIterator = this._observers.iterator();
    while (localIterator.hasNext()) {
      ((DataSetObserver)localIterator.next()).onChanged();
    }
  }
  
  public boolean areAllItemsEnabled()
  {
    return true;
  }
  
  public int getCount()
  {
    return this._items.size();
  }
  
  public T getItem(int paramInt)
  {
    return (T)this._items.get(paramInt);
  }
  
  public abstract View getView(int paramInt, View paramView, ViewGroup paramViewGroup);
  
  public boolean hasStableIds()
  {
    return true;
  }
  
  public boolean isEmpty()
  {
    return this._items.isEmpty();
  }
  
  public boolean isEnabled(int paramInt)
  {
    return true;
  }
  
  protected void onChanged() {}
  
  public void registerDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this._observers.add(paramDataSetObserver);
  }
  
  public void setContent(Collection<? extends T> paramCollection)
  {
    this._items.clear();
    this._items.addAll(paramCollection);
    fireChanged();
  }
  
  public void setContent(T... paramVarArgs)
  {
    this._items.clear();
    Collections.addAll(this._items, paramVarArgs);
    fireChanged();
  }
  
  public void unregisterDataSetObserver(DataSetObserver paramDataSetObserver)
  {
    this._observers.remove(paramDataSetObserver);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\ui\AbstractListAdapter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */