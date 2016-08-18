package com.amazon.device.iap.internal.b.d;

import com.amazon.android.framework.exception.KiwiException;
import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.b.i;

abstract class b
  extends i
{
  protected final boolean a;
  
  b(e parame, String paramString, boolean paramBoolean)
  {
    super(parame, "purchase_updates", paramString);
    this.a = paramBoolean;
  }
  
  protected void preExecution()
    throws KiwiException
  {
    super.preExecution();
    String str = (String)b().d().a("userId");
    if (this.a) {}
    for (str = null;; str = com.amazon.device.iap.internal.util.b.a(str))
    {
      a("cursor", str);
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\d\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */