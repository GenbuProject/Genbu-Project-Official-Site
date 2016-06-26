package com.amazon.device.iap.internal.b.a;

import com.amazon.device.iap.internal.b.e;
import com.amazon.device.iap.internal.b.h;
import com.amazon.device.iap.internal.b.i;
import com.amazon.device.iap.internal.model.PurchaseResponseBuilder;
import com.amazon.device.iap.internal.model.UserDataBuilder;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;

abstract class c
  extends i
{
  c(e parame, String paramString)
  {
    super(parame, "purchase_response", paramString);
  }
  
  protected void a(String paramString1, String paramString2, String paramString3, PurchaseResponse.RequestStatus paramRequestStatus)
  {
    paramString3 = b();
    paramString1 = new UserDataBuilder().setUserId(paramString1).setMarketplace(paramString2).build();
    paramString1 = new PurchaseResponseBuilder().setRequestId(paramString3.c()).setRequestStatus(paramRequestStatus).setUserData(paramString1).setReceipt(null).build();
    paramString3.d().a(paramString1);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\a\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */