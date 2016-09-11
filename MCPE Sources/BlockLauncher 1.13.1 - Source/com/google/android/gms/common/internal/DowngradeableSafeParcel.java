package com.google.android.gms.common.internal;

import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.lang.reflect.Field;

public abstract class DowngradeableSafeParcel
  implements SafeParcelable
{
  private static final Object zzalh = new Object();
  private static ClassLoader zzali = null;
  private static Integer zzalj = null;
  private boolean zzalk = false;
  
  private static boolean zza(Class<?> paramClass)
  {
    try
    {
      boolean bool = "SAFE_PARCELABLE_NULL_STRING".equals(paramClass.getField("NULL").get(null));
      return bool;
    }
    catch (IllegalAccessException paramClass)
    {
      return false;
    }
    catch (NoSuchFieldException paramClass) {}
    return false;
  }
  
  protected static boolean zzcF(String paramString)
  {
    ClassLoader localClassLoader = zzqA();
    if (localClassLoader == null) {
      return true;
    }
    try
    {
      boolean bool = zza(localClassLoader.loadClass(paramString));
      return bool;
    }
    catch (Exception paramString) {}
    return false;
  }
  
  protected static ClassLoader zzqA()
  {
    synchronized (zzalh)
    {
      ClassLoader localClassLoader = zzali;
      return localClassLoader;
    }
  }
  
  protected static Integer zzqB()
  {
    synchronized (zzalh)
    {
      Integer localInteger = zzalj;
      return localInteger;
    }
  }
  
  protected boolean zzqC()
  {
    return this.zzalk;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\internal\DowngradeableSafeParcel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */