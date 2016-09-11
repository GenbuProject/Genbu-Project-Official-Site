package org.mozilla.javascript;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;

public class UintMap
  implements Serializable
{
  private static final int A = -1640531527;
  private static final int DELETED = -2;
  private static final int EMPTY = -1;
  private static final boolean check = false;
  static final long serialVersionUID = 4242698212885848444L;
  private transient int ivaluesShift;
  private int keyCount;
  private transient int[] keys;
  private transient int occupiedCount;
  private int power;
  private transient Object[] values;
  
  public UintMap()
  {
    this(4);
  }
  
  public UintMap(int paramInt)
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
  
  private int ensureIndex(int paramInt, boolean paramBoolean)
  {
    int[] arrayOfInt = this.keys;
    int i;
    int j;
    if (arrayOfInt != null)
    {
      int k = paramInt * -1640531527;
      i = k >>> 32 - this.power;
      j = arrayOfInt[i];
      if (j == paramInt)
      {
        j = i;
        return j;
      }
      if (j != -1) {
        if (j == -2)
        {
          j = i;
          int n = (1 << this.power) - 1;
          int i1 = tableLookupStep(k, n, this.power);
          int m = i;
          k = j;
          int i2;
          do
          {
            i = m + i1 & n;
            i2 = arrayOfInt[i];
            j = i;
            if (i2 == paramInt) {
              break;
            }
            j = k;
            if (i2 == -2)
            {
              j = k;
              if (k < 0) {
                j = i;
              }
            }
            k = j;
            m = i;
          } while (i2 != -1);
        }
      }
    }
    for (;;)
    {
      if (j >= 0) {}
      for (;;)
      {
        arrayOfInt[j] = paramInt;
        this.keyCount += 1;
        return j;
        if ((arrayOfInt == null) || (this.occupiedCount * 4 >= (1 << this.power) * 3))
        {
          rehashTable(paramBoolean);
          return insertNewKey(paramInt);
        }
        this.occupiedCount += 1;
        j = i;
      }
      j = -1;
      break;
      j = -1;
      continue;
      j = -1;
      i = -1;
    }
  }
  
  private int findIndex(int paramInt)
  {
    int[] arrayOfInt = this.keys;
    if (arrayOfInt != null)
    {
      int j = paramInt * -1640531527;
      int i = j >>> 32 - this.power;
      int k = arrayOfInt[i];
      if (k == paramInt) {
        return i;
      }
      if (k != -1)
      {
        k = (1 << this.power) - 1;
        int m = tableLookupStep(j, k, this.power);
        int n;
        do
        {
          j = i + m & k;
          n = arrayOfInt[j];
          i = j;
          if (n == paramInt) {
            break;
          }
          i = j;
        } while (n != -1);
      }
    }
    return -1;
  }
  
  private int insertNewKey(int paramInt)
  {
    int[] arrayOfInt = this.keys;
    int m = paramInt * -1640531527;
    int i = m >>> 32 - this.power;
    int j = i;
    if (arrayOfInt[i] != -1)
    {
      int k = (1 << this.power) - 1;
      m = tableLookupStep(m, k, this.power);
      do
      {
        j = i + m & k;
        i = j;
      } while (arrayOfInt[j] != -1);
    }
    arrayOfInt[j] = paramInt;
    this.occupiedCount += 1;
    this.keyCount += 1;
    return j;
  }
  
  private void readObject(ObjectInputStream paramObjectInputStream)
    throws IOException, ClassNotFoundException
  {
    int j = 0;
    paramObjectInputStream.defaultReadObject();
    int k = this.keyCount;
    if (k != 0)
    {
      this.keyCount = 0;
      boolean bool1 = paramObjectInputStream.readBoolean();
      boolean bool2 = paramObjectInputStream.readBoolean();
      int m = 1 << this.power;
      if (bool1)
      {
        this.keys = new int[m * 2];
        this.ivaluesShift = m;
      }
      for (;;)
      {
        i = 0;
        while (i != m)
        {
          this.keys[i] = -1;
          i += 1;
        }
        this.keys = new int[m];
      }
      int i = j;
      if (bool2)
      {
        this.values = new Object[m];
        i = j;
      }
      while (i != k)
      {
        j = insertNewKey(paramObjectInputStream.readInt());
        if (bool1)
        {
          m = paramObjectInputStream.readInt();
          this.keys[(this.ivaluesShift + j)] = m;
        }
        if (bool2) {
          this.values[j] = paramObjectInputStream.readObject();
        }
        i += 1;
      }
    }
  }
  
  private void rehashTable(boolean paramBoolean)
  {
    int j = 0;
    if ((this.keys != null) && (this.keyCount * 2 >= this.occupiedCount)) {
      this.power += 1;
    }
    int k = 1 << this.power;
    int[] arrayOfInt = this.keys;
    int m = this.ivaluesShift;
    if ((m == 0) && (!paramBoolean)) {}
    int i;
    for (this.keys = new int[k];; this.keys = new int[k * 2])
    {
      i = 0;
      while (i != k)
      {
        this.keys[i] = -1;
        i += 1;
      }
      this.ivaluesShift = k;
    }
    Object[] arrayOfObject = this.values;
    if (arrayOfObject != null) {
      this.values = new Object[k];
    }
    k = this.keyCount;
    this.occupiedCount = 0;
    if (k != 0)
    {
      this.keyCount = 0;
      i = j;
      for (j = k; j != 0; j = k)
      {
        int n = arrayOfInt[i];
        k = j;
        if (n != -1)
        {
          k = j;
          if (n != -2)
          {
            k = insertNewKey(n);
            if (arrayOfObject != null) {
              this.values[k] = arrayOfObject[i];
            }
            if (m != 0) {
              this.keys[(k + this.ivaluesShift)] = arrayOfInt[(m + i)];
            }
            k = j - 1;
          }
        }
        i += 1;
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
    boolean bool2 = true;
    paramObjectOutputStream.defaultWriteObject();
    int k = this.keyCount;
    if (k != 0)
    {
      boolean bool1;
      if (this.ivaluesShift != 0)
      {
        bool1 = true;
        if (this.values == null) {
          break label150;
        }
      }
      for (;;)
      {
        paramObjectOutputStream.writeBoolean(bool1);
        paramObjectOutputStream.writeBoolean(bool2);
        int i = 0;
        while (k != 0)
        {
          int m = this.keys[i];
          int j = k;
          if (m != -1)
          {
            j = k;
            if (m != -2)
            {
              k -= 1;
              paramObjectOutputStream.writeInt(m);
              if (bool1) {
                paramObjectOutputStream.writeInt(this.keys[(this.ivaluesShift + i)]);
              }
              j = k;
              if (bool2)
              {
                paramObjectOutputStream.writeObject(this.values[i]);
                j = k;
              }
            }
          }
          i += 1;
          k = j;
        }
        bool1 = false;
        break;
        label150:
        bool2 = false;
      }
    }
  }
  
  public void clear()
  {
    int j = 1 << this.power;
    if (this.keys != null)
    {
      int i = 0;
      while (i != j)
      {
        this.keys[i] = -1;
        i += 1;
      }
      if (this.values != null)
      {
        i = 0;
        while (i != j)
        {
          this.values[i] = null;
          i += 1;
        }
      }
    }
    this.ivaluesShift = 0;
    this.keyCount = 0;
    this.occupiedCount = 0;
  }
  
  public int getExistingInt(int paramInt)
  {
    int i = 0;
    if (paramInt < 0) {
      Kit.codeBug();
    }
    int j = findIndex(paramInt);
    if (j >= 0)
    {
      paramInt = i;
      if (this.ivaluesShift != 0) {
        paramInt = this.keys[(j + this.ivaluesShift)];
      }
      return paramInt;
    }
    Kit.codeBug();
    return 0;
  }
  
  public int getInt(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      Kit.codeBug();
    }
    paramInt1 = findIndex(paramInt1);
    if (paramInt1 >= 0)
    {
      if (this.ivaluesShift != 0) {
        paramInt2 = this.keys[(paramInt1 + this.ivaluesShift)];
      }
    }
    else {
      return paramInt2;
    }
    return 0;
  }
  
  public int[] getKeys()
  {
    int[] arrayOfInt1 = this.keys;
    int j = this.keyCount;
    int[] arrayOfInt2 = new int[j];
    int i = 0;
    while (j != 0)
    {
      int m = arrayOfInt1[i];
      int k = j;
      if (m != -1)
      {
        k = j;
        if (m != -2)
        {
          k = j - 1;
          arrayOfInt2[k] = m;
        }
      }
      i += 1;
      j = k;
    }
    return arrayOfInt2;
  }
  
  public Object getObject(int paramInt)
  {
    if (paramInt < 0) {
      Kit.codeBug();
    }
    if (this.values != null)
    {
      paramInt = findIndex(paramInt);
      if (paramInt >= 0) {
        return this.values[paramInt];
      }
    }
    return null;
  }
  
  public boolean has(int paramInt)
  {
    if (paramInt < 0) {
      Kit.codeBug();
    }
    return findIndex(paramInt) >= 0;
  }
  
  public boolean isEmpty()
  {
    return this.keyCount == 0;
  }
  
  public void put(int paramInt1, int paramInt2)
  {
    if (paramInt1 < 0) {
      Kit.codeBug();
    }
    paramInt1 = ensureIndex(paramInt1, true);
    if (this.ivaluesShift == 0)
    {
      int i = 1 << this.power;
      if (this.keys.length != i * 2)
      {
        int[] arrayOfInt = new int[i * 2];
        System.arraycopy(this.keys, 0, arrayOfInt, 0, i);
        this.keys = arrayOfInt;
      }
      this.ivaluesShift = i;
    }
    this.keys[(paramInt1 + this.ivaluesShift)] = paramInt2;
  }
  
  public void put(int paramInt, Object paramObject)
  {
    if (paramInt < 0) {
      Kit.codeBug();
    }
    paramInt = ensureIndex(paramInt, false);
    if (this.values == null) {
      this.values = new Object[1 << this.power];
    }
    this.values[paramInt] = paramObject;
  }
  
  public void remove(int paramInt)
  {
    if (paramInt < 0) {
      Kit.codeBug();
    }
    paramInt = findIndex(paramInt);
    if (paramInt >= 0)
    {
      this.keys[paramInt] = -2;
      this.keyCount -= 1;
      if (this.values != null) {
        this.values[paramInt] = null;
      }
      if (this.ivaluesShift != 0) {
        this.keys[(paramInt + this.ivaluesShift)] = 0;
      }
    }
  }
  
  public int size()
  {
    return this.keyCount;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\org\mozilla\javascript\UintMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */