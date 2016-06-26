package com.mojang.minecraftpe.store.amazonappstore;

import android.content.Context;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserDataResponse;
import com.mojang.minecraftpe.store.Purchase;
import com.mojang.minecraftpe.store.Store;
import com.mojang.minecraftpe.store.StoreListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

public class AmazonAppStore
  implements Store
{
  private StoreListener mListener;
  private Map<RequestId, String> mProductIdRequestMapping = new HashMap();
  private PurchasingListener mPurchasingListener = new PurchasingListener()
  {
    public void onProductDataResponse(ProductDataResponse paramAnonymousProductDataResponse)
    {
      if (paramAnonymousProductDataResponse.getRequestStatus() == ProductDataResponse.RequestStatus.SUCCESSFUL)
      {
        ArrayList localArrayList = new ArrayList();
        paramAnonymousProductDataResponse = paramAnonymousProductDataResponse.getProductData().entrySet().iterator();
        while (paramAnonymousProductDataResponse.hasNext())
        {
          Map.Entry localEntry = (Map.Entry)paramAnonymousProductDataResponse.next();
          localArrayList.add(new com.mojang.minecraftpe.store.Product(((com.amazon.device.iap.model.Product)localEntry.getValue()).getSku(), ((com.amazon.device.iap.model.Product)localEntry.getValue()).getPrice()));
        }
        AmazonAppStore.this.mListener.onQueryProductsSuccess((com.mojang.minecraftpe.store.Product[])localArrayList.toArray(new com.mojang.minecraftpe.store.Product[0]));
        return;
      }
      AmazonAppStore.this.mListener.onQueryProductsFail();
    }
    
    public void onPurchaseResponse(PurchaseResponse paramAnonymousPurchaseResponse)
    {
      String str = (String)AmazonAppStore.this.mProductIdRequestMapping.remove(paramAnonymousPurchaseResponse.getRequestId());
      if (paramAnonymousPurchaseResponse.getRequestStatus() == PurchaseResponse.RequestStatus.SUCCESSFUL)
      {
        AmazonAppStore.this.mListener.onPurchaseSuccessful(str);
        return;
      }
      AmazonAppStore.this.mListener.onPurchaseFailed(str);
    }
    
    public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse paramAnonymousPurchaseUpdatesResponse)
    {
      if (paramAnonymousPurchaseUpdatesResponse.getRequestStatus() == PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL)
      {
        ArrayList localArrayList = new ArrayList();
        paramAnonymousPurchaseUpdatesResponse = paramAnonymousPurchaseUpdatesResponse.getReceipts().iterator();
        while (paramAnonymousPurchaseUpdatesResponse.hasNext()) {
          localArrayList.add(new Purchase(((Receipt)paramAnonymousPurchaseUpdatesResponse.next()).getSku()));
        }
        AmazonAppStore.this.mListener.onQueryPurchasesSuccess((Purchase[])localArrayList.toArray(new Purchase[0]));
        return;
      }
      AmazonAppStore.this.mListener.onQueryPurchasesFail();
    }
    
    public void onUserDataResponse(UserDataResponse paramAnonymousUserDataResponse) {}
  };
  
  public AmazonAppStore(Context paramContext, StoreListener paramStoreListener)
  {
    this.mListener = paramStoreListener;
    PurchasingService.registerListener(paramContext, this.mPurchasingListener);
    paramStoreListener.onStoreInitialized(true);
  }
  
  public void destructor() {}
  
  public String getStoreId()
  {
    return "android.amazonappstore";
  }
  
  public void purchase(String paramString)
  {
    RequestId localRequestId = PurchasingService.purchase(paramString);
    this.mProductIdRequestMapping.put(localRequestId, paramString);
  }
  
  public void queryProducts(String[] paramArrayOfString)
  {
    PurchasingService.getProductData(new HashSet(Arrays.asList(paramArrayOfString)));
  }
  
  public void queryPurchases()
  {
    PurchasingService.getPurchaseUpdates(true);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\minecraftpe\store\amazonappstore\AmazonAppStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */