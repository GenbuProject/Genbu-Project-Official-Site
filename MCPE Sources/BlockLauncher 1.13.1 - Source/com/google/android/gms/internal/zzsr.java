package com.google.android.gms.internal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;

class zzsr
  implements Cloneable
{
  private zzsp<?, ?> zzbuq;
  private Object zzbur;
  private List<zzsw> zzbus = new ArrayList();
  
  private byte[] toByteArray()
    throws IOException
  {
    byte[] arrayOfByte = new byte[zzz()];
    writeTo(zzsn.zzE(arrayOfByte));
    return arrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1;
    if (paramObject == this) {
      bool1 = true;
    }
    do
    {
      do
      {
        return bool1;
        bool1 = bool2;
      } while (!(paramObject instanceof zzsr));
      paramObject = (zzsr)paramObject;
      if ((this.zzbur == null) || (((zzsr)paramObject).zzbur == null)) {
        break;
      }
      bool1 = bool2;
    } while (this.zzbuq != ((zzsr)paramObject).zzbuq);
    if (!this.zzbuq.zzbuk.isArray()) {
      return this.zzbur.equals(((zzsr)paramObject).zzbur);
    }
    if ((this.zzbur instanceof byte[])) {
      return Arrays.equals((byte[])this.zzbur, (byte[])((zzsr)paramObject).zzbur);
    }
    if ((this.zzbur instanceof int[])) {
      return Arrays.equals((int[])this.zzbur, (int[])((zzsr)paramObject).zzbur);
    }
    if ((this.zzbur instanceof long[])) {
      return Arrays.equals((long[])this.zzbur, (long[])((zzsr)paramObject).zzbur);
    }
    if ((this.zzbur instanceof float[])) {
      return Arrays.equals((float[])this.zzbur, (float[])((zzsr)paramObject).zzbur);
    }
    if ((this.zzbur instanceof double[])) {
      return Arrays.equals((double[])this.zzbur, (double[])((zzsr)paramObject).zzbur);
    }
    if ((this.zzbur instanceof boolean[])) {
      return Arrays.equals((boolean[])this.zzbur, (boolean[])((zzsr)paramObject).zzbur);
    }
    return Arrays.deepEquals((Object[])this.zzbur, (Object[])((zzsr)paramObject).zzbur);
    if ((this.zzbus != null) && (((zzsr)paramObject).zzbus != null)) {
      return this.zzbus.equals(((zzsr)paramObject).zzbus);
    }
    try
    {
      bool1 = Arrays.equals(toByteArray(), ((zzsr)paramObject).toByteArray());
      return bool1;
    }
    catch (IOException paramObject)
    {
      throw new IllegalStateException((Throwable)paramObject);
    }
  }
  
  public int hashCode()
  {
    try
    {
      int i = Arrays.hashCode(toByteArray());
      return i + 527;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException(localIOException);
    }
  }
  
  void writeTo(zzsn paramzzsn)
    throws IOException
  {
    if (this.zzbur != null) {
      this.zzbuq.zza(this.zzbur, paramzzsn);
    }
    for (;;)
    {
      return;
      Iterator localIterator = this.zzbus.iterator();
      while (localIterator.hasNext()) {
        ((zzsw)localIterator.next()).writeTo(paramzzsn);
      }
    }
  }
  
  public final zzsr zzJr()
  {
    int i = 0;
    zzsr localzzsr = new zzsr();
    try
    {
      localzzsr.zzbuq = this.zzbuq;
      if (this.zzbus == null) {
        localzzsr.zzbus = null;
      }
      while (this.zzbur == null)
      {
        return localzzsr;
        localzzsr.zzbus.addAll(this.zzbus);
      }
      if (!(this.zzbur instanceof zzsu)) {
        break label92;
      }
    }
    catch (CloneNotSupportedException localCloneNotSupportedException)
    {
      throw new AssertionError(localCloneNotSupportedException);
    }
    localCloneNotSupportedException.zzbur = ((zzsu)this.zzbur).clone();
    return localCloneNotSupportedException;
    label92:
    if ((this.zzbur instanceof byte[]))
    {
      localCloneNotSupportedException.zzbur = ((byte[])this.zzbur).clone();
      return localCloneNotSupportedException;
    }
    Object localObject1;
    Object localObject2;
    if ((this.zzbur instanceof byte[][]))
    {
      localObject1 = (byte[][])this.zzbur;
      localObject2 = new byte[localObject1.length][];
      localCloneNotSupportedException.zzbur = localObject2;
      i = 0;
      while (i < localObject1.length)
      {
        localObject2[i] = ((byte[])localObject1[i].clone());
        i += 1;
      }
    }
    if ((this.zzbur instanceof boolean[]))
    {
      localCloneNotSupportedException.zzbur = ((boolean[])this.zzbur).clone();
      return localCloneNotSupportedException;
    }
    if ((this.zzbur instanceof int[]))
    {
      localCloneNotSupportedException.zzbur = ((int[])this.zzbur).clone();
      return localCloneNotSupportedException;
    }
    if ((this.zzbur instanceof long[]))
    {
      localCloneNotSupportedException.zzbur = ((long[])this.zzbur).clone();
      return localCloneNotSupportedException;
    }
    if ((this.zzbur instanceof float[]))
    {
      localCloneNotSupportedException.zzbur = ((float[])this.zzbur).clone();
      return localCloneNotSupportedException;
    }
    if ((this.zzbur instanceof double[]))
    {
      localCloneNotSupportedException.zzbur = ((double[])this.zzbur).clone();
      return localCloneNotSupportedException;
    }
    if ((this.zzbur instanceof zzsu[]))
    {
      localObject1 = (zzsu[])this.zzbur;
      localObject2 = new zzsu[localObject1.length];
      localCloneNotSupportedException.zzbur = localObject2;
      while (i < localObject1.length)
      {
        localObject2[i] = localObject1[i].clone();
        i += 1;
      }
    }
    return localCloneNotSupportedException;
  }
  
  void zza(zzsw paramzzsw)
  {
    this.zzbus.add(paramzzsw);
  }
  
  <T> T zzb(zzsp<?, T> paramzzsp)
  {
    if (this.zzbur != null)
    {
      if (this.zzbuq != paramzzsp) {
        throw new IllegalStateException("Tried to getExtension with a differernt Extension.");
      }
    }
    else
    {
      this.zzbuq = paramzzsp;
      this.zzbur = paramzzsp.zzJ(this.zzbus);
      this.zzbus = null;
    }
    return (T)this.zzbur;
  }
  
  int zzz()
  {
    int j;
    if (this.zzbur != null)
    {
      j = this.zzbuq.zzY(this.zzbur);
      return j;
    }
    Iterator localIterator = this.zzbus.iterator();
    for (int i = 0;; i = ((zzsw)localIterator.next()).zzz() + i)
    {
      j = i;
      if (!localIterator.hasNext()) {
        break;
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzsr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */