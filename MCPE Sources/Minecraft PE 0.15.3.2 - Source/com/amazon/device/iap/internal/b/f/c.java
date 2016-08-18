package com.amazon.device.iap.internal.b.f;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.h;

public final class c
  extends a
{
  public c(e parame, boolean paramBoolean)
  {
    super(parame, "2.0");
    a("receiptDelivered", Boolean.valueOf(paramBoolean));
  }
  
  public void a_()
  {
    Object localObject = b().d().a("notifyListenerResult");
    if ((localObject != null) && (Boolean.TRUE.equals(localObject))) {
      a("notifyListenerSucceeded", Boolean.valueOf(true));
    }
    for (;;)
    {
      super.a_();
      return;
      a("notifyListenerSucceeded", Boolean.valueOf(false));
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\f\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */