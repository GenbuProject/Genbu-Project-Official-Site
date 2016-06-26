package com.amazon.device.iap.internal.b;

import android.content.Context;
import android.content.Intent;
import com.amazon.device.iap.internal.b.g.b;
import com.amazon.device.iap.internal.util.e;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.RequestId;
import java.util.Set;

public final class c
  implements com.amazon.device.iap.internal.c
{
  private static final String a = c.class.getSimpleName();
  
  public void a(Context paramContext, Intent paramIntent)
  {
    e.a(a, "handleResponse");
    paramContext = paramIntent.getStringExtra("response_type");
    if (paramContext == null) {
      e.a(a, "Invalid response type: null");
    }
    do
    {
      return;
      e.a(a, "Found response type: " + paramContext);
    } while (!"purchase_response".equals(paramContext));
    new com.amazon.device.iap.internal.b.a.d(RequestId.fromString(paramIntent.getStringExtra("requestId"))).e();
  }
  
  public void a(RequestId paramRequestId)
  {
    e.a(a, "sendGetUserData");
    new com.amazon.device.iap.internal.b.e.a(paramRequestId).e();
  }
  
  public void a(RequestId paramRequestId, String paramString)
  {
    e.a(a, "sendPurchaseRequest");
    new com.amazon.device.iap.internal.b.b.d(paramRequestId, paramString).e();
  }
  
  public void a(RequestId paramRequestId, String paramString, FulfillmentResult paramFulfillmentResult)
  {
    e.a(a, "sendNotifyFulfillment");
    new b(paramRequestId, paramString, paramFulfillmentResult).e();
  }
  
  public void a(RequestId paramRequestId, Set<String> paramSet)
  {
    e.a(a, "sendGetProductDataRequest");
    new com.amazon.device.iap.internal.b.c.d(paramRequestId, paramSet).e();
  }
  
  public void a(RequestId paramRequestId, boolean paramBoolean)
  {
    e.a(a, "sendGetPurchaseUpdates");
    new com.amazon.device.iap.internal.b.d.a(paramRequestId, paramBoolean).e();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\b\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */