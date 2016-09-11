package com.google.android.gms.internal;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class zzsp<M extends zzso<M>, T>
{
  public final int tag;
  protected final int type;
  protected final Class<T> zzbuk;
  protected final boolean zzbul;
  
  private zzsp(int paramInt1, Class<T> paramClass, int paramInt2, boolean paramBoolean)
  {
    this.type = paramInt1;
    this.zzbuk = paramClass;
    this.tag = paramInt2;
    this.zzbul = paramBoolean;
  }
  
  private T zzK(List<zzsw> paramList)
  {
    int j = 0;
    ArrayList localArrayList = new ArrayList();
    int i = 0;
    while (i < paramList.size())
    {
      localObject = (zzsw)paramList.get(i);
      if (((zzsw)localObject).zzbuv.length != 0) {
        zza((zzsw)localObject, localArrayList);
      }
      i += 1;
    }
    int k = localArrayList.size();
    if (k == 0)
    {
      paramList = null;
      return paramList;
    }
    Object localObject = this.zzbuk.cast(Array.newInstance(this.zzbuk.getComponentType(), k));
    i = j;
    for (;;)
    {
      paramList = (List<zzsw>)localObject;
      if (i >= k) {
        break;
      }
      Array.set(localObject, i, localArrayList.get(i));
      i += 1;
    }
  }
  
  private T zzL(List<zzsw> paramList)
  {
    if (paramList.isEmpty()) {
      return null;
    }
    paramList = (zzsw)paramList.get(paramList.size() - 1);
    return (T)this.zzbuk.cast(zzP(zzsm.zzD(paramList.zzbuv)));
  }
  
  public static <M extends zzso<M>, T extends zzsu> zzsp<M, T> zza(int paramInt, Class<T> paramClass, long paramLong)
  {
    return new zzsp(paramInt, paramClass, (int)paramLong, false);
  }
  
  final T zzJ(List<zzsw> paramList)
  {
    if (paramList == null) {
      return null;
    }
    if (this.zzbul) {
      return (T)zzK(paramList);
    }
    return (T)zzL(paramList);
  }
  
  protected Object zzP(zzsm paramzzsm)
  {
    Class localClass;
    if (this.zzbul) {
      localClass = this.zzbuk.getComponentType();
    }
    for (;;)
    {
      try
      {
        switch (this.type)
        {
        case 10: 
          throw new IllegalArgumentException("Unknown type " + this.type);
        }
      }
      catch (InstantiationException paramzzsm)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, paramzzsm);
        localClass = this.zzbuk;
        continue;
        zzsu localzzsu = (zzsu)localClass.newInstance();
        paramzzsm.zza(localzzsu, zzsx.zzmJ(this.tag));
        return localzzsu;
        localzzsu = (zzsu)localClass.newInstance();
        paramzzsm.zza(localzzsu);
        return localzzsu;
      }
      catch (IllegalAccessException paramzzsm)
      {
        throw new IllegalArgumentException("Error creating instance of class " + localClass, paramzzsm);
      }
      catch (IOException paramzzsm)
      {
        throw new IllegalArgumentException("Error reading extension field", paramzzsm);
      }
    }
  }
  
  int zzY(Object paramObject)
  {
    if (this.zzbul) {
      return zzZ(paramObject);
    }
    return zzaa(paramObject);
  }
  
  protected int zzZ(Object paramObject)
  {
    int j = 0;
    int m = Array.getLength(paramObject);
    int i = 0;
    while (i < m)
    {
      int k = j;
      if (Array.get(paramObject, i) != null) {
        k = j + zzaa(Array.get(paramObject, i));
      }
      i += 1;
      j = k;
    }
    return j;
  }
  
  protected void zza(zzsw paramzzsw, List<Object> paramList)
  {
    paramList.add(zzP(zzsm.zzD(paramzzsw.zzbuv)));
  }
  
  void zza(Object paramObject, zzsn paramzzsn)
    throws IOException
  {
    if (this.zzbul)
    {
      zzc(paramObject, paramzzsn);
      return;
    }
    zzb(paramObject, paramzzsn);
  }
  
  protected int zzaa(Object paramObject)
  {
    int i = zzsx.zzmJ(this.tag);
    switch (this.type)
    {
    default: 
      throw new IllegalArgumentException("Unknown type " + this.type);
    case 10: 
      return zzsn.zzb(i, (zzsu)paramObject);
    }
    return zzsn.zzc(i, (zzsu)paramObject);
  }
  
  protected void zzb(Object paramObject, zzsn paramzzsn)
  {
    for (;;)
    {
      try
      {
        paramzzsn.zzmB(this.tag);
        switch (this.type)
        {
        case 10: 
          throw new IllegalArgumentException("Unknown type " + this.type);
        }
      }
      catch (IOException paramObject)
      {
        throw new IllegalStateException((Throwable)paramObject);
      }
      paramObject = (zzsu)paramObject;
      int i = zzsx.zzmJ(this.tag);
      paramzzsn.zzb((zzsu)paramObject);
      paramzzsn.zzE(i, 4);
      return;
      paramzzsn.zzc((zzsu)paramObject);
      return;
    }
  }
  
  protected void zzc(Object paramObject, zzsn paramzzsn)
  {
    int j = Array.getLength(paramObject);
    int i = 0;
    while (i < j)
    {
      Object localObject = Array.get(paramObject, i);
      if (localObject != null) {
        zzb(localObject, paramzzsn);
      }
      i += 1;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzsp.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */