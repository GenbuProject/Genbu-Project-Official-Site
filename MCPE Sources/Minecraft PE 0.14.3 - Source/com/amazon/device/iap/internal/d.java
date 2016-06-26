package com.amazon.device.iap.internal;

import android.content.Context;
import android.content.Intent;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.RequestId;
import java.util.Iterator;
import java.util.LinkedHashSet;
import java.util.Set;

public class d
{
  private static String a = d.class.getSimpleName();
  private static String b = "sku";
  private static d c = new d();
  private final c d = e.b();
  private Context e;
  private PurchasingListener f;
  
  public static d d()
  {
    return c;
  }
  
  private void e()
  {
    if (this.f == null) {
      throw new IllegalStateException("You must register a PurchasingListener before invoking this operation");
    }
  }
  
  public PurchasingListener a()
  {
    return this.f;
  }
  
  public RequestId a(String paramString)
  {
    com.amazon.device.iap.internal.util.d.a(paramString, b);
    e();
    RequestId localRequestId = new RequestId();
    this.d.a(localRequestId, paramString);
    return localRequestId;
  }
  
  public RequestId a(Set<String> paramSet)
  {
    com.amazon.device.iap.internal.util.d.a(paramSet, "skus");
    com.amazon.device.iap.internal.util.d.a(paramSet, "skus");
    Object localObject = paramSet.iterator();
    while (((Iterator)localObject).hasNext()) {
      if (((String)((Iterator)localObject).next()).trim().length() == 0) {
        throw new IllegalArgumentException("Empty SKU values are not allowed");
      }
    }
    if (paramSet.size() > 100) {
      throw new IllegalArgumentException(paramSet.size() + " SKUs were provided, but no more than " + 100 + " SKUs are allowed");
    }
    e();
    localObject = new RequestId();
    paramSet = new LinkedHashSet(paramSet);
    this.d.a((RequestId)localObject, paramSet);
    return (RequestId)localObject;
  }
  
  public RequestId a(boolean paramBoolean)
  {
    e();
    RequestId localRequestId = new RequestId();
    this.d.a(localRequestId, paramBoolean);
    return localRequestId;
  }
  
  public void a(Context paramContext, Intent paramIntent)
  {
    try
    {
      this.d.a(paramContext, paramIntent);
      return;
    }
    catch (Exception paramContext)
    {
      com.amazon.device.iap.internal.util.e.b(a, "Error in onReceive: " + paramContext);
    }
  }
  
  public void a(Context paramContext, PurchasingListener paramPurchasingListener)
  {
    com.amazon.device.iap.internal.util.e.a(a, "PurchasingListener registered: " + paramPurchasingListener);
    com.amazon.device.iap.internal.util.e.a(a, "PurchasingListener Context: " + paramContext);
    if ((paramPurchasingListener == null) || (paramContext == null)) {
      throw new IllegalArgumentException("Neither PurchasingListener or its Context can be null");
    }
    this.e = paramContext.getApplicationContext();
    this.f = paramPurchasingListener;
  }
  
  public void a(String paramString, FulfillmentResult paramFulfillmentResult)
  {
    if (com.amazon.device.iap.internal.util.d.a(paramString)) {
      throw new IllegalArgumentException("Empty receiptId is not allowed");
    }
    com.amazon.device.iap.internal.util.d.a(paramFulfillmentResult, "fulfillmentResult");
    e();
    RequestId localRequestId = new RequestId();
    this.d.a(localRequestId, paramString, paramFulfillmentResult);
  }
  
  public Context b()
  {
    return this.e;
  }
  
  public RequestId c()
  {
    e();
    RequestId localRequestId = new RequestId();
    this.d.a(localRequestId);
    return localRequestId;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\device\iap\internal\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */