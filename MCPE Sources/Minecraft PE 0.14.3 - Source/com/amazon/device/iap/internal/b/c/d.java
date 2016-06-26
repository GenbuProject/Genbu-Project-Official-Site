package com.amazon.device.iap.internal.b.c;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.internal.model.ProductDataResponseBuilder;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.RequestId;
import java.util.Set;

public final class d
  extends e
{
  public d(RequestId paramRequestId, Set<String> paramSet)
  {
    super(paramRequestId);
    paramRequestId = new a(this, paramSet);
    paramRequestId.b(new b(this, paramSet));
    a(paramRequestId);
  }
  
  public void a()
  {
    a((ProductDataResponse)d().a());
  }
  
  public void b()
  {
    ProductDataResponse localProductDataResponse2 = (ProductDataResponse)d().a();
    ProductDataResponse localProductDataResponse1 = localProductDataResponse2;
    if (localProductDataResponse2 == null) {
      localProductDataResponse1 = new ProductDataResponseBuilder().setRequestId(c()).setRequestStatus(ProductDataResponse.RequestStatus.FAILED).build();
    }
    a(localProductDataResponse1);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\c\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */