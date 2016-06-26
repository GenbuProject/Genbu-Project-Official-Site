package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class ObjToIntMap
  implements Serializable
{
  private static final int A = -1640531527;
  private static final Object DELETED = new Object();
  private static final boolean check = false;
  static final long serialVersionUID = -1542220580748809402L;
  private int keyCount;
  private transient Object[] keys;
  private transient int occupiedCount;
  private int power;
  private transient int[] values;
  
  public ObjToIntMap()
  {
    this(4);
  }
  
  public ObjToIntMap(int paramInt)
  {
    if (paramInt < 0) {
      Kit.codeBug();
    }
    int i = paramInt * 4 / 3;
    paramInt = 2;
    while (1 << paramInt < i) {
      paramInt += 1;
    }
    this.power = paramInt;
  }
  
  private int ensureIndex(Object paramObject)
  {
    int m = -1;
    int n = paramObject.hashCode();
    int i;
    Object localObject;
    int k;
    int j;
    int i1;
    if (this.keys != null)
    {
      int i2 = n * -1640531527;
      i = i2 >>> 32 - this.power;
      localObject = this.keys[i];
      k = m;
      j = i;
      if (localObject != null)
      {
        i1 = 1 << this.power;
        j = i;
        if (localObject != paramObject)
        {
          if ((this.values[(i1 + i)] == n) && (localObject.equals(paramObject))) {
            j = i;
          }
        }
        else {
          return j;
        }
        k = m;
        if (localObject == DELETED) {
          k = i;
        }
        m = i1 - 1;
        i2 = tableLookupStep(i2, m, this.power);
        j = i;
        label132:
        i = j + i2 & m;
        localObject = this.keys[i];
        if (localObject != null) {
          break label197;
        }
        j = i;
      }
    }
    for (;;)
    {
      if (k >= 0) {}
      for (;;)
      {
        this.keys[k] = paramObject;
        this.values[((1 << this.power) + k)] = n;
        this.keyCount += 1;
        return k;
        label197:
        j = i;
        if (localObject == paramObject) {
          break;
        }
        if (this.values[(i1 + i)] == n)
        {
          j = i;
          if (localObject.equals(paramObject)) {
            break;
          }
        }
        j = i;
        if (localObject != DELETED) {
          break label132;
        }
        j = i;
        if (k >= 0) {
          break label132;
        }
        k = i;
        j = i;
        break label132;
        if ((this.keys == null) || (this.occupiedCount * 4 >= (1 << this.power) * 3))
        {
          rehashTable();
          return insertNewKey(paramObject, n);
        }
        this.occupiedCount += 1;
        k = j;
      }
      j = -1;
      k = m;
    }
  }
  
  private int findIndex(Object paramObject)
  {
    int k;
    int i1;
    int j;
    Object localObject;
    int m;
    int i;
    int n;
    if (this.keys != null)
    {
      k = paramObject.hashCode();
      i1 = k * -1640531527;
      j = i1 >>> 32 - this.power;
      localObject = this.keys[j];
      if (localObject != null)
      {
        m = 1 << this.power;
        i = j;
        if (localObject != paramObject)
        {
          if ((this.values[(m + j)] == k) && (localObject.equals(paramObject))) {
            i = j;
          }
        }
        else {
          return i;
        }
        n = m - 1;
        i1 = tableLookupStep(i1, n, this.power);
        i = j;
      }
    }
    do
    {
      do
      {
        j = i + i1 & n;
        localObject = this.keys[j];
        if (localObject == null) {
          return -1;
        }
        i = j;
        if (localObject == paramObject) {
          break;
        }
        i = j;
      } while (this.values[(m + j)] != k);
      i = j;
    } while (!localObject.equals(paramObject));
    return j;
  }
  
  private int insertNewKey(Object paramObject, int paramInt)
  {
    int n = paramInt * -1640531527;
    int i = n >>> 32 - this.power;
    int k = 1 << this.power;
    int j = i;
    if (this.keys[i] != null)
    {
      int m = k - 1;
      n = tableLookupStep(n, m, this.power);
      do
      {
        j = i + n & m;
        i = j;
      } while (this.keys[j] != null);
    }
    this.keys[j] = paramObject;
    this.values[(k + j)] = paramInt;
    this.occupiedCount += 1;
    this.keyCount += 1;
    return j;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    int i = 0;
    paramObjectInputStream.defaultReadObject();
    int j = this.keyCount;
    if (j != 0)
    {
      this.keyCount = 0;
      int k = 1 << this.power;
      this.keys = new Object[k];
      this.values = new int[k * 2];
      while (i != j)
      {
        Object localObject = paramObjectInputStream.readObject();
        k = insertNewKey(localObject, localObject.hashCode());
        this.values[k] = paramObjectInputStream.readInt();
        i += 1;
      }
    }
  }
  
  private void rehashTable()
  {
    int i;
    if (this.keys == null)
    {
      i = 1 << this.power;
      this.keys = new Object[i];
      this.values = new int[i * 2];
    }
    for (;;)
    {
      return;
      if (this.keyCount * 2 >= this.occupiedCount) {
        this.power += 1;
      }
      i = 1 << this.power;
      Object[] arrayOfObject = this.keys;
      int[] arrayOfInt = this.values;
      int m = arrayOfObject.length;
      this.keys = new Object[i];
      this.values = new int[i * 2];
      int j = this.keyCount;
      this.keyCount = 0;
      this.occupiedCount = 0;
      i = 0;
      while (j != 0)
      {
        Object localObject = arrayOfObject[i];
        int k = j;
        if (localObject != null)
        {
          k = j;
          if (localObject != DELETED)
          {
            k = insertNewKey(localObject, arrayOfInt[(m + i)]);
            this.values[k] = arrayOfInt[i];
            k = j - 1;
          }
        }
        i += 1;
        j = k;
      }
    }
  }
  
  private static int tableLookupStep(int paramInt1, int paramInt2, int paramInt3)
  {
    paramInt3 = 32 - paramInt3 * 2;
    if (paramInt3 >= 0) {
      return paramInt1 >>> paramInt3 & paramInt2 | 0x1;
    }
    return paramInt2 >>> -paramInt3 & paramInt1 | 0x1;
  }
  
  private void writeObject(ObjectOutputStream paramObjectOutputStream)
    throws IOException
  {
    paramObjectOutputStream.defaultWriteObject();
    int j = this.keyCount;
    int i = 0;
    while (j != 0)
    {
      Object localObject = this.keys[i];
      int k = j;
      if (localObject != null)
      {
        k = j;
        if (localObject != DELETED)
        {
          k = j - 1;
          paramObjectOutputStream.writeObject(localObject);
          paramObjectOutputStream.writeInt(this.values[i]);
        }
      }
      i += 1;
      j = k;
    }
  }
  
  public void clear()
  {
    int i = this.keys.length;
    while (i != 0)
    {
      Object[] arrayOfObject = this.keys;
      i -= 1;
      arrayOfObject[i] = null;
    }
    this.keyCount = 0;
    this.occupiedCount = 0;
  }
  
  public int get(Object paramObject, int paramInt)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = UniqueTag.NULL_VALUE;
    }
    int i = findIndex(localObject);
    if (i >= 0) {
      paramInt = this.values[i];
    }
    return paramInt;
  }
  
  public int getExisting(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = UniqueTag.NULL_VALUE;
    }
    int i = findIndex(localObject);
    if (i >= 0) {
      return this.values[i];
    }
    Kit.codeBug();
    return 0;
  }
  
  public void getKeys(Object[] paramArrayOfObject, int paramInt)
  {
    int i = this.keyCount;
    int j = 0;
    int k;
    if (i != 0)
    {
      Object localObject2 = this.keys[j];
      if ((localObject2 == null) || (localObject2 == DELETED)) {
        break label88;
      }
      Object localObject1 = localObject2;
      if (localObject2 == UniqueTag.NULL_VALUE) {
        localObject1 = null;
      }
      paramArrayOfObject[paramInt] = localObject1;
      k = i - 1;
      i = paramInt + 1;
      paramInt = k;
    }
    for (;;)
    {
      k = j + 1;
      j = i;
      i = paramInt;
      paramInt = j;
      j = k;
      break;
      return;
      label88:
      k = paramInt;
      paramInt = i;
      i = k;
    }
  }
  
  public Object[] getKeys()
  {
    Object[] arrayOfObject = new Object[this.keyCount];
    getKeys(arrayOfObject, 0);
    return arrayOfObject;
  }
  
  public boolean has(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = UniqueTag.NULL_VALUE;
    }
    return findIndex(localObject) >= 0;
  }
  
  final void initIterator(Iterator paramIterator)
  {
    paramIterator.init(this.keys, this.values, this.keyCount);
  }
  
  public Object intern(Object paramObject)
  {
    int i;
    if (paramObject == null)
    {
      i = 1;
      paramObject = UniqueTag.NULL_VALUE;
    }
    for (;;)
    {
      int j = ensureIndex(paramObject);
      this.values[j] = 0;
      if (i != 0) {
        return null;
      }
      return this.keys[j];
      i = 0;
    }
  }
  
  public boolean isEmpty()
  {
    return this.keyCount == 0;
  }
  
  public Iterator newIterator()
  {
    return new Iterator(this);
  }
  
  public void put(Object paramObject, int paramInt)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = UniqueTag.NULL_VALUE;
    }
    int i = ensureIndex(localObject);
    this.values[i] = paramInt;
  }
  
  public void remove(Object paramObject)
  {
    Object localObject = paramObject;
    if (paramObject == null) {
      localObject = UniqueTag.NULL_VALUE;
    }
    int i = findIndex(localObject);
    if (i >= 0)
    {
      this.keys[i] = DELETED;
      this.keyCount -= 1;
    }
  }
  
  public int size()
  {
    return this.keyCount;
  }
  
  public static class Iterator
  {
    private int cursor;
    private Object[] keys;
    ObjToIntMap master;
    private int remaining;
    private int[] values;
    
    Iterator(ObjToIntMap paramObjToIntMap)
    {
      this.master = paramObjToIntMap;
    }
    
    public boolean done()
    {
      return this.remaining < 0;
    }
    
    public Object getKey()
    {
      Object localObject2 = this.keys[this.cursor];
      Object localObject1 = localObject2;
      if (localObject2 == UniqueTag.NULL_VALUE) {
        localObject1 = null;
      }
      return localObject1;
    }
    
    public int getValue()
    {
      return this.values[this.cursor];
    }
    
    final void init(Object[] paramArrayOfObject, int[] paramArrayOfInt, int paramInt)
    {
      this.keys = paramArrayOfObject;
      this.values = paramArrayOfInt;
      this.cursor = -1;
      this.remaining = paramInt;
    }
    
    public void next()
    {
      if (this.remaining == -1) {
        Kit.codeBug();
      }
      if (this.remaining == 0)
      {
        this.remaining = -1;
        this.cursor = -1;
        return;
      }
      for (this.cursor += 1;; this.cursor += 1)
      {
        Object localObject = this.keys[this.cursor];
        if ((localObject != null) && (localObject != ObjToIntMap.DELETED))
        {
          this.remaining -= 1;
          return;
        }
      }
    }
    
    public void setValue(int paramInt)
    {
      this.values[this.cursor] = paramInt;
    }
    
    public void start()
    {
      this.master.initIterator(this);
      next();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\org\mozilla\javascript\ObjToIntMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */