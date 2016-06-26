package com.google.android.gms.internal;

import android.support.v4.util.ArrayMap;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public final class zzmr
{
  public static <T> Set<T> zzA(T paramT)
  {
    return Collections.singleton(paramT);
  }
  
  public static <K, V> Map<K, V> zza(K paramK1, V paramV1, K paramK2, V paramV2, K paramK3, V paramV3, K paramK4, V paramV4, K paramK5, V paramV5, K paramK6, V paramV6)
  {
    ArrayMap localArrayMap = new ArrayMap(6);
    localArrayMap.put(paramK1, paramV1);
    localArrayMap.put(paramK2, paramV2);
    localArrayMap.put(paramK3, paramV3);
    localArrayMap.put(paramK4, paramV4);
    localArrayMap.put(paramK5, paramV5);
    localArrayMap.put(paramK6, paramV6);
    return Collections.unmodifiableMap(localArrayMap);
  }
  
  public static <T> Set<T> zza(T paramT1, T paramT2, T paramT3)
  {
    zzmm localzzmm = new zzmm(3);
    localzzmm.add(paramT1);
    localzzmm.add(paramT2);
    localzzmm.add(paramT3);
    return Collections.unmodifiableSet(localzzmm);
  }
  
  public static <T> Set<T> zza(T paramT1, T paramT2, T paramT3, T paramT4)
  {
    zzmm localzzmm = new zzmm(4);
    localzzmm.add(paramT1);
    localzzmm.add(paramT2);
    localzzmm.add(paramT3);
    localzzmm.add(paramT4);
    return Collections.unmodifiableSet(localzzmm);
  }
  
  public static <T> List<T> zzc(T paramT1, T paramT2)
  {
    ArrayList localArrayList = new ArrayList(2);
    localArrayList.add(paramT1);
    localArrayList.add(paramT2);
    return Collections.unmodifiableList(localArrayList);
  }
  
  public static <T> Set<T> zzc(T... paramVarArgs)
  {
    switch (paramVarArgs.length)
    {
    default: 
      if (paramVarArgs.length > 32) {
        break;
      }
    }
    for (paramVarArgs = new zzmm(Arrays.asList(paramVarArgs));; paramVarArgs = new HashSet(Arrays.asList(paramVarArgs)))
    {
      return Collections.unmodifiableSet(paramVarArgs);
      return zzsb();
      return zzA(paramVarArgs[0]);
      return zzd(paramVarArgs[0], paramVarArgs[1]);
      return zza(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2]);
      return zza(paramVarArgs[0], paramVarArgs[1], paramVarArgs[2], paramVarArgs[3]);
    }
  }
  
  public static <T> Set<T> zzd(T paramT1, T paramT2)
  {
    zzmm localzzmm = new zzmm(2);
    localzzmm.add(paramT1);
    localzzmm.add(paramT2);
    return Collections.unmodifiableSet(localzzmm);
  }
  
  public static <T> Set<T> zzsb()
  {
    return Collections.emptySet();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzmr.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */