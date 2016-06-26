package com.amazon.android.d;

import android.os.Looper;

public final class a
{
  public static void a()
  {
    long l = Looper.getMainLooper().getThread().getId();
    if (Thread.currentThread().getId() != l) {
      a("Executing thread must be thread: " + l + ", was: " + Thread.currentThread().getId());
    }
  }
  
  public static void a(Object paramObject, String paramString)
  {
    if (paramObject == null) {
      a("Argument: " + paramString + " cannot be null");
    }
  }
  
  private static void a(String paramString)
  {
    throw new b(paramString);
  }
  
  public static void a(boolean paramBoolean, String paramString)
  {
    if (!paramBoolean) {
      a(paramString);
    }
  }
  
  public static void b(boolean paramBoolean, String paramString)
  {
    if (paramBoolean) {
      a(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\d\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */