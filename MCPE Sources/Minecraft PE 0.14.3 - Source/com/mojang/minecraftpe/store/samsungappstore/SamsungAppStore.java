package com.mojang.minecraftpe.store.samsungappstore;

import android.content.Intent;
import com.mojang.minecraftpe.ActivityListener;
import com.mojang.minecraftpe.MainActivity;
import com.mojang.minecraftpe.store.Store;
import com.mojang.minecraftpe.store.StoreListener;

public class SamsungAppStore
  implements ActivityListener, Store
{
  MainActivity mActivity;
  StoreListener mListener;
  
  public SamsungAppStore(MainActivity paramMainActivity, StoreListener paramStoreListener)
  {
    this.mListener = paramStoreListener;
    this.mActivity = paramMainActivity;
    this.mActivity.addListener(this);
    this.mListener.onStoreInitialized(true);
  }
  
  public void destructor()
  {
    this.mActivity.removeListener(this);
    onDestroy();
  }
  
  public String getStoreId()
  {
    return "android.samsungappstore";
  }
  
  public void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent) {}
  
  public void onDestroy() {}
  
  public void purchase(String paramString)
  {
    this.mListener.onPurchaseFailed(paramString);
  }
  
  public void queryProducts(String[] paramArrayOfString)
  {
    this.mListener.onQueryProductsFail();
  }
  
  public void queryPurchases()
  {
    this.mListener.onQueryPurchasesFail();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\minecraftpe\store\samsungappstore\SamsungAppStore.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */