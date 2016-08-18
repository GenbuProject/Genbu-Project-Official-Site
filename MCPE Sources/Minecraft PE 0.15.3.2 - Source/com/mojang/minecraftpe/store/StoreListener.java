package com.mojang.minecraftpe.store;

public abstract interface StoreListener
{
  public abstract void onPurchaseCanceled(String paramString);
  
  public abstract void onPurchaseFailed(String paramString);
  
  public abstract void onPurchaseSuccessful(String paramString1, String paramString2);
  
  public abstract void onQueryProductsFail();
  
  public abstract void onQueryProductsSuccess(Product[] paramArrayOfProduct);
  
  public abstract void onQueryPurchasesFail();
  
  public abstract void onQueryPurchasesSuccess(Purchase[] paramArrayOfPurchase);
  
  public abstract void onStoreInitialized(boolean paramBoolean);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\store\StoreListener.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */