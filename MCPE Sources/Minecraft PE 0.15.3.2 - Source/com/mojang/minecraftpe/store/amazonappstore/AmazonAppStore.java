package com.mojang.minecraftpe.store.amazonappstore;

import android.content.Context;
import com.amazon.device.iap.PurchasingListener;
import com.amazon.device.iap.PurchasingService;
import com.amazon.device.iap.model.FulfillmentResult;
import com.amazon.device.iap.model.ProductDataResponse;
import com.amazon.device.iap.model.ProductDataResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseResponse;
import com.amazon.device.iap.model.PurchaseResponse.RequestStatus;
import com.amazon.device.iap.model.PurchaseUpdatesResponse;
import com.amazon.device.iap.model.PurchaseUpdatesResponse.RequestStatus;
import com.amazon.device.iap.model.Receipt;
import com.amazon.device.iap.model.RequestId;
import com.amazon.device.iap.model.UserData;
import com.amazon.device.iap.model.UserDataResponse;
import com.mojang.minecraftpe.store.Purchase;
import com.mojang.minecraftpe.store.Store;
import com.mojang.minecraftpe.store.StoreListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.json.JSONException;
import org.json.JSONObject;

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
        Iterator localIterator = paramAnonymousProductDataResponse.getProductData().values().iterator();
        if (localIterator.hasNext())
        {
          Object localObject = (com.amazon.device.iap.model.Product)localIterator.next();
          if (((com.amazon.device.iap.model.Product)localObject).getSku() != null)
          {
            paramAnonymousProductDataResponse = ((com.amazon.device.iap.model.Product)localObject).getSku().replace(".child", "");
            label74:
            if (((com.amazon.device.iap.model.Product)localObject).getPrice() == null) {
              break label109;
            }
          }
          label109:
          for (localObject = ((com.amazon.device.iap.model.Product)localObject).getPrice();; localObject = "")
          {
            localArrayList.add(new com.mojang.minecraftpe.store.Product(paramAnonymousProductDataResponse, (String)localObject));
            break;
            paramAnonymousProductDataResponse = "";
            break label74;
          }
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
        AmazonAppStore.this.mListener.onPurchaseSuccessful(str, AmazonAppStore.this.createReceipt(paramAnonymousPurchaseResponse));
        return;
      }
      AmazonAppStore.this.mListener.onPurchaseFailed(str);
    }
    
    public void onPurchaseUpdatesResponse(PurchaseUpdatesResponse paramAnonymousPurchaseUpdatesResponse)
    {
      if (paramAnonymousPurchaseUpdatesResponse.getRequestStatus() == PurchaseUpdatesResponse.RequestStatus.SUCCESSFUL)
      {
        ArrayList localArrayList = new ArrayList();
        String str1 = paramAnonymousPurchaseUpdatesResponse.getUserData().getUserId();
        paramAnonymousPurchaseUpdatesResponse = paramAnonymousPurchaseUpdatesResponse.getReceipts().iterator();
        if (paramAnonymousPurchaseUpdatesResponse.hasNext())
        {
          Receipt localReceipt = (Receipt)paramAnonymousPurchaseUpdatesResponse.next();
          String str2 = localReceipt.getSku();
          String str3 = AmazonAppStore.this.createReceipt(str1, localReceipt.getReceiptId());
          if (!localReceipt.isCanceled()) {}
          for (boolean bool = true;; bool = false)
          {
            localArrayList.add(new Purchase(str2, str3, bool));
            break;
          }
        }
        AmazonAppStore.this.mListener.onQueryPurchasesSuccess((Purchase[])localArrayList.toArray(new Purchase[0]));
        return;
      }
      AmazonAppStore.this.mListener.onQueryPurchasesFail();
    }
    
    public void onUserDataResponse(UserDataResponse paramAnonymousUserDataResponse) {}
  };
  private final String subscriptionKey = ".subscription";
  
  public AmazonAppStore(Context paramContext, StoreListener paramStoreListener)
  {
    this.mListener = paramStoreListener;
    PurchasingService.registerListener(paramContext, this.mPurchasingListener);
    paramStoreListener.onStoreInitialized(true);
  }
  
  private String createReceipt(PurchaseResponse paramPurchaseResponse)
  {
    return createReceipt(paramPurchaseResponse.getUserData().getUserId(), paramPurchaseResponse.getReceipt().getReceiptId());
  }
  
  private String createReceipt(String paramString1, String paramString2)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("userId", paramString1);
      localJSONObject.put("receiptId", paramString2);
      return localJSONObject.toString();
    }
    catch (JSONException paramString1)
    {
      throw new RuntimeException(paramString1);
    }
  }
  
  public void acknowledgePurchase(String paramString1, String paramString2)
  {
    try
    {
      PurchasingService.notifyFulfillment(new JSONObject(paramString1).getString("receiptId"), FulfillmentResult.FULFILLED);
      return;
    }
    catch (JSONException paramString1) {}
  }
  
  public void destructor() {}
  
  public String getStoreId()
  {
    return "android.amazonappstore";
  }
  
  public void purchase(String paramString1, boolean paramBoolean, String paramString2)
  {
    paramString2 = PurchasingService.purchase(paramString1);
    this.mProductIdRequestMapping.put(paramString2, paramString1);
  }
  
  public void queryProducts(String[] paramArrayOfString)
  {
    String[] arrayOfString = new String[paramArrayOfString.length];
    int i = 0;
    if (i < paramArrayOfString.length)
    {
      if (paramArrayOfString[i].indexOf(".subscription") != -1) {
        arrayOfString[i] = (paramArrayOfString[i] + ".child");
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfString[i] = paramArrayOfString[i];
      }
    }
    PurchasingService.getProductData(new HashSet(Arrays.asList(arrayOfString)));
  }
  
  public void queryPurchases()
  {
    PurchasingService.getPurchaseUpdates(true);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\store\amazonappstore\AmazonAppStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */