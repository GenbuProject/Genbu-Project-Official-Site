package com.google.android.gms.internal;

import android.os.Binder;

public abstract class zzlz<T>
{
  private static zza zzaiV = null;
  private static int zzaiW = 0;
  private static String zzaiX = "com.google.android.providers.gsf.permission.READ_GSERVICES";
  private static final Object zzqy = new Object();
  private T zzSC = null;
  protected final String zzvs;
  protected final T zzvt;
  
  protected zzlz(String paramString, T paramT)
  {
    this.zzvs = paramString;
    this.zzvt = paramT;
  }
  
  public static boolean isInitialized()
  {
    return zzaiV != null;
  }
  
  public static zzlz<Float> zza(String paramString, Float paramFloat)
  {
    new zzlz(paramString, paramFloat)
    {
      protected Float zzcx(String paramAnonymousString)
      {
        return zzlz.zzpY().zzb(this.zzvs, (Float)this.zzvt);
      }
    };
  }
  
  public static zzlz<Integer> zza(String paramString, Integer paramInteger)
  {
    new zzlz(paramString, paramInteger)
    {
      protected Integer zzcw(String paramAnonymousString)
      {
        return zzlz.zzpY().zzb(this.zzvs, (Integer)this.zzvt);
      }
    };
  }
  
  public static zzlz<Long> zza(String paramString, Long paramLong)
  {
    new zzlz(paramString, paramLong)
    {
      protected Long zzcv(String paramAnonymousString)
      {
        return zzlz.zzpY().getLong(this.zzvs, (Long)this.zzvt);
      }
    };
  }
  
  public static zzlz<Boolean> zzk(String paramString, boolean paramBoolean)
  {
    new zzlz(paramString, Boolean.valueOf(paramBoolean))
    {
      protected Boolean zzcu(String paramAnonymousString)
      {
        return zzlz.zzpY().zza(this.zzvs, (Boolean)this.zzvt);
      }
    };
  }
  
  public static int zzpW()
  {
    return zzaiW;
  }
  
  public static zzlz<String> zzv(String paramString1, String paramString2)
  {
    new zzlz(paramString1, paramString2)
    {
      protected String zzcy(String paramAnonymousString)
      {
        return zzlz.zzpY().getString(this.zzvs, (String)this.zzvt);
      }
    };
  }
  
  public final T get()
  {
    if (this.zzSC != null) {
      return (T)this.zzSC;
    }
    return (T)zzct(this.zzvs);
  }
  
  protected abstract T zzct(String paramString);
  
  public final T zzpX()
  {
    long l = Binder.clearCallingIdentity();
    try
    {
      Object localObject1 = get();
      return (T)localObject1;
    }
    finally
    {
      Binder.restoreCallingIdentity(l);
    }
  }
  
  private static abstract interface zza
  {
    public abstract Long getLong(String paramString, Long paramLong);
    
    public abstract String getString(String paramString1, String paramString2);
    
    public abstract Boolean zza(String paramString, Boolean paramBoolean);
    
    public abstract Float zzb(String paramString, Float paramFloat);
    
    public abstract Integer zzb(String paramString, Integer paramInteger);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzlz.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */