package com.amazon.device.iap.internal.c;

import com.amazon.device.iap.internal.util.d;
import java.util.Set;
import java.util.concurrent.ConcurrentSkipListSet;

public class b
{
  private static final b b = new b();
  private final Set<String> a = new ConcurrentSkipListSet();
  
  public static b a()
  {
    return b;
  }
  
  public boolean a(String paramString)
  {
    if (!d.a(paramString)) {
      return this.a.remove(paramString);
    }
    return false;
  }
  
  public void b(String paramString)
  {
    if (!d.a(paramString)) {
      this.a.add(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\c\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */