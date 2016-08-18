package com.amazon.device.iap;

import android.content.Context;
import android.util.Log;
import com.amazon.device.iap.internal.d;
import com.amazon.device.iap.internal.e;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.RequestId;
import java.util.Set;

public final class PurchasingService
{
  public static final boolean IS_SANDBOX_MODE = e.a();
  public static final String SDK_VERSION = "2.0.61.0";
  private static final String TAG = PurchasingService.class.getSimpleName();
  
  private PurchasingService()
  {
    Log.i(TAG, "In-App Purchasing SDK initializing. SDK Version 2.0.61.0, IS_SANDBOX_MODE: " + IS_SANDBOX_MODE);
  }
  
  public static RequestId getProductData(Set<String> paramSet)
  {
    return d.d().a(paramSet);
  }
  
  public static RequestId getPurchaseUpdates(boolean paramBoolean)
  {
    return d.d().a(paramBoolean);
  }
  
  public static RequestId getUserData()
  {
    return d.d().c();
  }
  
  public static void notifyFulfillment(String paramString, FulfillmentResult paramFulfillmentResult)
  {
    d.d().a(paramString, paramFulfillmentResult);
  }
  
  public static RequestId purchase(String paramString)
  {
    return d.d().a(paramString);
  }
  
  public static void registerListener(Context paramContext, PurchasingListener paramPurchasingListener)
  {
    d.d().a(paramContext, paramPurchasingListener);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\PurchasingService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */