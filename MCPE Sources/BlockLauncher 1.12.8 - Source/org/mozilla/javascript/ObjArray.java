package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjArray
  implements Serializable
{
  private static final int FIELDS_STORE_SIZE = 5;
  static final long serialVersionUID = 4174889037736658296L;
  private transient Object[] data;
  private transient Object f0;
  private transient Object f1;
  private transient Object f2;
  private transient Object f3;
  private transient Object f4;
  private boolean sealed;
  private int size;
  
  private void ensureCapacity(int paramInt)
  {
    int j = 10;
    int i = paramInt - 5;
    if (i <= 0) {
      throw new IllegalArgumentException();
    }
    if (this.data == null) {
      if (10 >= i) {
        break label114;
      }
    }
    for (;;)
    {
      this.data = new Object[i];
      do
      {
        return;
        paramInt = this.data.length;
      } while (paramInt >= i);
      if (paramInt <= 5)
      {
        paramInt = j;
        if (paramInt >= i) {
          break label109;
        }
      }
      for (;;)
      {
        Object[] arrayOfObject = new Object[i];
        if (this.size > 5) {
          System.arraycopy(this.data, 0, arrayOfObject, 0, this.size - 5);
        }
        this.data = arrayOfObject;
        return;
        paramInt *= 2;
        break;
        label109:
        i = paramInt;
      }
      label114:
      i = 10;
    }
  }
  
  private Object getImpl(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return this.data[(paramInt - 5)];
    case 0: 
      return this.f0;
    case 1: 
      return this.f1;
    case 2: 
      return this.f2;
    case 3: 
      return this.f3;
    }
    return this.f4;
  }
  
  private static RuntimeException onEmptyStackTopRead()
  {
    throw new RuntimeException("Empty stack");
  }
  
  private static RuntimeException onInvalidIndex(int paramInt1, int paramInt2)
  {
    throw new IndexOutOfBoundsException(paramInt1 + " ? [0, " + paramInt2 + ')');
  }
  
  private static RuntimeException onSeledMutation()
  {
    throw new IllegalStateException("Attempt to modify sealed array");
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    paramObjectInputStream.defaultReadObject();
    int j = this.size;
    if (j > 5) {
      this.data = new Object[j - 5];
    }
    int i = 0;
    while (i != j)
    {
      setImpl(i, paramObjectInputStream.readObject());
      i += 1;
    }
  }
  
  private void setImpl(int paramInt, Object paramObject)
  {
    switch (paramInt)
    {
    default: 
      this.data[(paramInt - 5)] = paramObject;
      return;
    case 0: 
      this.f0 = paramObject;
      return;
    case 1: 
      this.f1 = paramObject;
      return;
    case 2: 
      this.f2 = paramObject;
      return;
    case 3: 
      this.f3 = paramObject;
      return;
    }
    this.f4 = paramObject;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    int j = this.size;
    int i = 0;
    while (i != j)
    {
      paramObjectOutputStream.writeObject(getImpl(i));
      i += 1;
    }
  }
  
  public final void add(int paramInt, Object paramObject)
  {
    int i = this.size;
    if ((paramInt < 0) || (paramInt > i)) {
      throw onInvalidIndex(paramInt, i + 1);
    }
    if (this.sealed) {
      throw onSeledMutation();
    }
    label111:
    Object localObject;
    switch (paramInt)
    {
    default: 
      ensureCapacity(i + 1);
      if (paramInt != i) {
        System.arraycopy(this.data, paramInt - 5, this.data, paramInt - 5 + 1, i - paramInt);
      }
      this.data[(paramInt - 5)] = paramObject;
    case 0: 
      for (;;)
      {
        this.size = (i + 1);
        return;
        if (i != 0) {
          break;
        }
        this.f0 = paramObject;
      }
      localObject = this.f0;
      this.f0 = paramObject;
      paramObject = localObject;
    }
    for (;;)
    {
      if (i == 1)
      {
        this.f1 = paramObject;
        break label111;
      }
      localObject = this.f1;
      this.f1 = paramObject;
      paramObject = localObject;
      for (;;)
      {
        if (i == 2)
        {
          this.f2 = paramObject;
          break label111;
        }
        localObject = this.f2;
        this.f2 = paramObject;
        paramObject = localObject;
        for (;;)
        {
          if (i == 3)
          {
            this.f3 = paramObject;
            break label111;
          }
          localObject = this.f3;
          this.f3 = paramObject;
          paramObject = localObject;
          for (;;)
          {
            if (i == 4)
            {
              this.f4 = paramObject;
              break label111;
            }
            localObject = this.f4;
            this.f4 = paramObject;
            paramInt = 5;
            paramObject = localObject;
            break;
          }
        }
      }
    }
  }
  
  public final void add(Object paramObject)
  {
    if (this.sealed) {
      throw onSeledMutation();
    }
    int i = this.size;
    if (i >= 5) {
      ensureCapacity(i + 1);
    }
    this.size = (i + 1);
    setImpl(i, paramObject);
  }
  
  public final void clear()
  {
    if (this.sealed) {
      throw onSeledMutation();
    }
    int j = this.size;
    int i = 0;
    while (i != j)
    {
      setImpl(i, null);
      i += 1;
    }
    this.size = 0;
  }
  
  public final Object get(int paramInt)
  {
    if ((paramInt < 0) || (paramInt >= this.size)) {
      throw onInvalidIndex(paramInt, this.size);
    }
    return getImpl(paramInt);
  }
  
  public int indexOf(Object paramObject)
  {
    int j = this.size;
    int i = 0;
    while (i != j)
    {
      Object localObject = getImpl(i);
      if ((localObject == paramObject) || ((localObject != null) && (localObject.equals(paramObject)))) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public final boolean isEmpty()
  {
    return this.size == 0;
  }
  
  public final boolean isSealed()
  {
    return this.sealed;
  }
  
  public int lastIndexOf(Object paramObject)
  {
    int i = this.size;
    while (i != 0)
    {
      int j = i - 1;
      Object localObject = getImpl(j);
      if (localObject != paramObject)
      {
        i = j;
        if (localObject != null)
        {
          i = j;
          if (!localObject.equals(paramObject)) {
            break;
          }
        }
      }
      else
      {
        return j;
      }
    }
    return -1;
  }
  
  public final Object peek()
  {
    int i = this.size;
    if (i == 0) {
      throw onEmptyStackTopRead();
    }
    return getImpl(i - 1);
  }
  
  public final Object pop()
  {
    if (this.sealed) {
      throw onSeledMutation();
    }
    int i = this.size - 1;
    Object localObject;
    switch (i)
    {
    default: 
      localObject = this.data[(i - 5)];
      this.data[(i - 5)] = null;
    }
    for (;;)
    {
      this.size = i;
      return localObject;
      throw onEmptyStackTopRead();
      localObject = this.f0;
      this.f0 = null;
      continue;
      localObject = this.f1;
      this.f1 = null;
      continue;
      localObject = this.f2;
      this.f2 = null;
      continue;
      localObject = this.f3;
      this.f3 = null;
      continue;
      localObject = this.f4;
      this.f4 = null;
    }
  }
  
  public final void push(Object paramObject)
  {
    add(paramObject);
  }
  
  public final void remove(int paramInt)
  {
    int i = this.size;
    if ((paramInt < 0) || (paramInt >= i)) {
      throw onInvalidIndex(paramInt, i);
    }
    if (this.sealed) {
      throw onSeledMutation();
    }
    i -= 1;
    switch (paramInt)
    {
    }
    for (;;)
    {
      if (paramInt != i) {
        System.arraycopy(this.data, paramInt - 5 + 1, this.data, paramInt - 5, i - paramInt);
      }
      this.data[(i - 5)] = null;
      for (;;)
      {
        this.size = i;
        return;
        if (i == 0)
        {
          this.f0 = null;
        }
        else
        {
          this.f0 = this.f1;
          if (i == 1)
          {
            this.f1 = null;
          }
          else
          {
            this.f1 = this.f2;
            if (i == 2)
            {
              this.f2 = null;
            }
            else
            {
              this.f2 = this.f3;
              if (i == 3)
              {
                this.f3 = null;
              }
              else
              {
                this.f3 = this.f4;
                if (i != 4) {
                  break;
                }
                this.f4 = null;
              }
            }
          }
        }
      }
      this.f4 = this.data[0];
      paramInt = 5;
    }
  }
  
  public final void seal()
  {
    this.sealed = true;
  }
  
  public final void set(int paramInt, Object paramObject)
  {
    if ((paramInt < 0) || (paramInt >= this.size)) {
      throw onInvalidIndex(paramInt, this.size);
    }
    if (this.sealed) {
      throw onSeledMutation();
    }
    setImpl(paramInt, paramObject);
  }
  
  public final void setSize(int paramInt)
  {
    if (paramInt < 0) {
      throw new IllegalArgumentException();
    }
    if (this.sealed) {
      throw onSeledMutation();
    }
    int j = this.size;
    if (paramInt < j)
    {
      int i = paramInt;
      while (i != j)
      {
        setImpl(i, null);
        i += 1;
      }
    }
    if ((paramInt > j) && (paramInt > 5)) {
      ensureCapacity(paramInt);
    }
    this.size = paramInt;
  }
  
  public final int size()
  {
    return this.size;
  }
  
  public final void toArray(Object[] paramArrayOfObject)
  {
    toArray(paramArrayOfObject, 0);
  }
  
  public final void toArray(Object[] paramArrayOfObject, int paramInt)
  {
    int i = this.size;
    switch (i)
    {
    default: 
      System.arraycopy(this.data, 0, paramArrayOfObject, paramInt + 5, i - 5);
    case 5: 
      paramArrayOfObject[(paramInt + 4)] = this.f4;
    case 4: 
      paramArrayOfObject[(paramInt + 3)] = this.f3;
    case 3: 
      paramArrayOfObject[(paramInt + 2)] = this.f2;
    case 2: 
      paramArrayOfObject[(paramInt + 1)] = this.f1;
    case 1: 
      paramArrayOfObject[(paramInt + 0)] = this.f0;
    }
  }
  
  public final Object[] toArray()
  {
    Object[] arrayOfObject = new Object[this.size];
    toArray(arrayOfObject, 0);
    return arrayOfObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ObjArray.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */