package com.amazon.device.iap.internal.b.c;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.i;
import java.util.Set;

abstract class c
  extends i
{
  protected final Set<String> a;
  
  c(e parame, String paramString, Set<String> paramSet)
  {
    super(parame, "getItem_data", paramString);
    this.a = paramSet;
    a("skus", paramSet);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\c\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */