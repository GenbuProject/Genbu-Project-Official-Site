package com.amazon.device.iap.internal.b.a;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.f.c;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.internal.model.PurchaseResponseBuilder;
import com.amazon.device.iap.model.ProductType;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;

public final class d
  extends e
{
  public d(RequestId paramRequestId)
  {
    super(paramRequestId);
    paramRequestId = new a(this);
    paramRequestId.b(new b(this));
    a(paramRequestId);
  }
  
  public void a()
  {
    PurchaseResponse localPurchaseResponse = (PurchaseResponse)d().a();
    if (localPurchaseResponse == null) {
      return;
    }
    Receipt localReceipt = localPurchaseResponse.getReceipt();
    if (localReceipt != null) {}
    for (boolean bool = true;; bool = false)
    {
      c localc = new c(this, bool);
      if ((bool) && ((ProductType.ENTITLED == localReceipt.getProductType()) || (ProductType.SUBSCRIPTION == localReceipt.getProductType()))) {
        localc.b(new com.amazon.device.iap.internal.b.f.b(this, c().toString()));
      }
      a(localPurchaseResponse, localc);
      return;
    }
  }
  
  public void b()
  {
    PurchaseResponse localPurchaseResponse2 = (PurchaseResponse)d().a();
    PurchaseResponse localPurchaseResponse1 = localPurchaseResponse2;
    if (localPurchaseResponse2 == null) {
      localPurchaseResponse1 = new PurchaseResponseBuilder().setRequestId(c()).setRequestStatus(PurchaseResponse.RequestStatus.FAILED).build();
    }
    a(localPurchaseResponse1, new c(this, false));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\internal\b\a\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */