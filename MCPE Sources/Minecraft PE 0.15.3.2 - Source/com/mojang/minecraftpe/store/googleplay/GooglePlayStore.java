package com.mojang.minecraftpe.store.googleplay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.util.Log;
import com.googleplay.util.IabHelper;
import com.googleplay.util.IabHelper.OnIabPurchaseFinishedListener;
import com.googleplay.util.IabHelper.OnIabSetupFinishedListener;
import com.googleplay.util.IabHelper.QueryInventoryFinishedListener;
import com.googleplay.util.IabResult;
import com.googleplay.util.Inventory;
import com.googleplay.util.SkuDetails;
import com.mojang.minecraftpe.ActivityListener;
import com.mojang.minecraftpe.MainActivity;
import com.mojang.minecraftpe.store.Product;
import com.mojang.minecraftpe.store.Store;
import com.mojang.minecraftpe.store.StoreListener;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.List;
import org.json.JSONException;
import org.json.JSONObject;

public class GooglePlayStore
  extends BroadcastReceiver
  implements Store, ActivityListener
{
  static final String IAB_BROADCAST_ACTION = "com.android.vending.billing.PURCHASES_UPDATED";
  MainActivity mActivity;
  IabHelper mIabHelper;
  StoreListener mListener;
  int mPurchaseRequestCode;
  
  public GooglePlayStore(MainActivity paramMainActivity, String paramString, StoreListener paramStoreListener)
  {
    this.mListener = paramStoreListener;
    this.mActivity = paramMainActivity;
    this.mActivity.addListener(this);
    this.mPurchaseRequestCode = MainActivity.RESULT_GOOGLEPLAY_PURCHASE;
    this.mIabHelper = new IabHelper(this.mActivity, paramString);
    this.mIabHelper.startSetup(new IabHelper.OnIabSetupFinishedListener()
    {
      public void onIabSetupFinished(IabResult paramAnonymousIabResult)
      {
        Log.i("GooglePlayStore", "onIabSetupFinished: " + paramAnonymousIabResult.getResponse() + ", " + paramAnonymousIabResult.getMessage());
        IntentFilter localIntentFilter = new IntentFilter("com.android.vending.billing.PURCHASES_UPDATED");
        GooglePlayStore.this.mActivity.registerReceiver(jdField_this, localIntentFilter);
        GooglePlayStore.this.mListener.onStoreInitialized(paramAnonymousIabResult.isSuccess());
      }
    });
  }
  
  private String createReceipt(com.googleplay.util.Purchase paramPurchase)
  {
    JSONObject localJSONObject = new JSONObject();
    try
    {
      localJSONObject.put("itemtype", paramPurchase.getItemType());
      localJSONObject.put("originaljson", paramPurchase.getOriginalJson());
      localJSONObject.put("signature", paramPurchase.getSignature());
      return localJSONObject.toString();
    }
    catch (JSONException paramPurchase)
    {
      throw new RuntimeException(paramPurchase);
    }
  }
  
  private com.googleplay.util.Purchase parseReceipt(String paramString)
  {
    try
    {
      paramString = new JSONObject(paramString);
      paramString = new com.googleplay.util.Purchase(paramString.getString("itemtype"), paramString.getString("originaljson"), paramString.getString("signature"));
      return paramString;
    }
    catch (JSONException paramString) {}
    return null;
  }
  
  public void acknowledgePurchase(final String paramString1, String paramString2)
  {
    if (paramString2.equals("Consumable")) {
      this.mActivity.runOnUiThread(new Runnable()
      {
        public void run()
        {
          com.googleplay.util.Purchase localPurchase = GooglePlayStore.this.parseReceipt(paramString1);
          if (localPurchase != null) {
            GooglePlayStore.this.mIabHelper.consumeAsync(localPurchase, null);
          }
        }
      });
    }
  }
  
  public void destructor()
  {
    onDestroy();
  }
  
  public String getStoreId()
  {
    return "android.googleplay";
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (this.mIabHelper != null) {
      this.mIabHelper.handleActivityResult(paramInt1, paramInt2, paramIntent);
    }
  }
  
  public void onDestroy()
  {
    this.mActivity.removeListener(this);
    if (this.mIabHelper != null) {
      this.mIabHelper.dispose();
    }
    this.mIabHelper = null;
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    Log.i("GooglePlayStore", "IabBroadcastReceiver received message PURCHASES_UPDATED");
    queryPurchases();
  }
  
  public void purchase(final String paramString1, final boolean paramBoolean, final String paramString2)
  {
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        if (paramBoolean) {
          localObject = GooglePlayStore.this.mIabHelper;
        }
        for (Object localObject = "subs";; localObject = "inapp")
        {
          GooglePlayStore.this.mIabHelper.launchPurchaseFlow(GooglePlayStore.this.mActivity, paramString1, (String)localObject, GooglePlayStore.this.mPurchaseRequestCode, new IabHelper.OnIabPurchaseFinishedListener()
          {
            public void onIabPurchaseFinished(IabResult paramAnonymous2IabResult, com.googleplay.util.Purchase paramAnonymous2Purchase)
            {
              if (paramAnonymous2IabResult.isSuccess())
              {
                GooglePlayStore.this.mListener.onPurchaseSuccessful(GooglePlayStore.3.this.val$productId, GooglePlayStore.this.createReceipt(paramAnonymous2Purchase));
                return;
              }
              if (paramAnonymous2IabResult.getResponse() == 64531)
              {
                GooglePlayStore.this.mListener.onPurchaseCanceled(GooglePlayStore.3.this.val$productId);
                return;
              }
              GooglePlayStore.this.mListener.onPurchaseFailed(GooglePlayStore.3.this.val$productId);
            }
          }, paramString2);
          return;
          localObject = GooglePlayStore.this.mIabHelper;
        }
      }
    });
  }
  
  public void queryProducts(final String[] paramArrayOfString)
  {
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        GooglePlayStore.this.mIabHelper.queryInventoryAsync(true, Arrays.asList(paramArrayOfString), new IabHelper.QueryInventoryFinishedListener()
        {
          public void onQueryInventoryFinished(IabResult paramAnonymous2IabResult, Inventory paramAnonymous2Inventory)
          {
            if (paramAnonymous2IabResult.isSuccess())
            {
              ArrayList localArrayList = new ArrayList();
              if (paramAnonymous2IabResult.isSuccess())
              {
                paramAnonymous2IabResult = GooglePlayStore.2.this.val$productIds;
                int j = paramAnonymous2IabResult.length;
                int i = 0;
                while (i < j)
                {
                  String str = paramAnonymous2IabResult[i];
                  SkuDetails localSkuDetails = paramAnonymous2Inventory.getSkuDetails(str);
                  if (localSkuDetails != null) {
                    localArrayList.add(new Product(str, localSkuDetails.getPrice()));
                  }
                  i += 1;
                }
              }
              GooglePlayStore.this.mListener.onQueryProductsSuccess((Product[])localArrayList.toArray(new Product[0]));
              return;
            }
            GooglePlayStore.this.mListener.onQueryProductsFail();
          }
        });
      }
    });
  }
  
  public void queryPurchases()
  {
    this.mActivity.runOnUiThread(new Runnable()
    {
      public void run()
      {
        GooglePlayStore.this.mIabHelper.queryInventoryAsync(false, new IabHelper.QueryInventoryFinishedListener()
        {
          public void onQueryInventoryFinished(IabResult paramAnonymous2IabResult, Inventory paramAnonymous2Inventory)
          {
            if (paramAnonymous2IabResult.isSuccess())
            {
              ArrayList localArrayList = new ArrayList();
              if (paramAnonymous2IabResult.isSuccess())
              {
                paramAnonymous2IabResult = paramAnonymous2Inventory.getAllPurchases().iterator();
                if (paramAnonymous2IabResult.hasNext())
                {
                  paramAnonymous2Inventory = (com.googleplay.util.Purchase)paramAnonymous2IabResult.next();
                  String str1 = paramAnonymous2Inventory.getSku();
                  String str2 = GooglePlayStore.this.createReceipt(paramAnonymous2Inventory);
                  if (paramAnonymous2Inventory.getPurchaseState() == 0) {}
                  for (boolean bool = true;; bool = false)
                  {
                    localArrayList.add(new com.mojang.minecraftpe.store.Purchase(str1, str2, bool));
                    break;
                  }
                }
              }
              GooglePlayStore.this.mListener.onQueryPurchasesSuccess((com.mojang.minecraftpe.store.Purchase[])localArrayList.toArray(new com.mojang.minecraftpe.store.Purchase[0]));
              return;
            }
            GooglePlayStore.this.mListener.onQueryPurchasesFail();
          }
        });
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\store\googleplay\GooglePlayStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */