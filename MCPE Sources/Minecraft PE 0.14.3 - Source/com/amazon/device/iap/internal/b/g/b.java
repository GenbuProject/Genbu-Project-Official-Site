package com.amazon.device.iap.internal.b.g;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.RequestId;
import java.util.HashSet;
import java.util.Set;

public final class b
  extends e
{
  private final String a;
  private final FulfillmentResult b;
  
  public b(RequestId paramRequestId, String paramString, FulfillmentResult paramFulfillmentResult)
  {
    super(paramRequestId);
    paramRequestId = new HashSet();
    paramRequestId.add(paramString);
    this.a = paramString;
    this.b = paramFulfillmentResult;
    a(new a(this, paramRequestId, paramFulfillmentResult.toString()));
  }
  
  public void a() {}
  
  public void b()
  {
    if ((FulfillmentResult.FULFILLED == this.b) || (FulfillmentResult.UNAVAILABLE == this.b))
    {
      String str = com.amazon.device.iap.internal.c.a.a().c(this.a);
      if (str != null)
      {
        new com.amazon.device.iap.internal.b.f.b(this, str).a_();
        com.amazon.device.iap.internal.c.a.a().a(this.a);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\g\b.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */