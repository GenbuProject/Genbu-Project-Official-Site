package com.amazon.device.iap;

import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.UserDataResponse;

public abstract interface PurchasingListener
{
  public abstract void onProductDataResponse(ProductDataResponse paramProductDataResponse);
  
  public abstract void onPurchaseResponse(PurchaseResponse paramPurchaseResponse);
  
  public abstract void onPurchaseUpdatesResponse(PurchaseUpdatesResponse paramPurchaseUpdatesResponse);
  
  public abstract void onUserDataResponse(UserDataResponse paramUserDataResponse);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\amazon\device\iap\PurchasingListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */