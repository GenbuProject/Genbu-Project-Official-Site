package com.amazon.device.iap.internal.b.b;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.internal.model.PurchaseResponseBuilder;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.RequestId;

public final class d
  extends e
{
  public d(RequestId paramRequestId, String paramString)
  {
    super(paramRequestId);
    paramRequestId = new c(this, paramString);
    paramRequestId.b(new b(this, paramString));
    a(paramRequestId);
  }
  
  public void a() {}
  
  public void b()
  {
    PurchaseResponse localPurchaseResponse2 = (PurchaseResponse)d().a();
    PurchaseResponse localPurchaseResponse1 = localPurchaseResponse2;
    if (localPurchaseResponse2 == null) {
      localPurchaseResponse1 = new PurchaseResponseBuilder().setRequestId(c()).setRequestStatus(PurchaseResponse.RequestStatus.FAILED).build();
    }
    a(localPurchaseResponse1);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\b\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */