package com.mojang.minecraftpe.store;

import java.io.PrintStream;

public class Store
{
  private StoreListener listener;
  
  public Store(StoreListener paramStoreListener)
  {
    this.listener = paramStoreListener;
  }
  
  public void acknowledgePurchase(String paramString1, String paramString2)
  {
    System.out.println("Acknowledge purchase: " + paramString1 + ":" + paramString2);
  }
  
  public void destructor()
  {
    System.out.println("Store: Destructor");
  }
  
  public String getStoreId()
  {
    System.out.println("Store: Get store ID");
    return "Placeholder store ID";
  }
  
  public void purchase(String paramString)
  {
    System.out.println("Store: Purchase " + paramString);
  }
  
  public void purchase(String paramString1, boolean paramBoolean, String paramString2)
  {
    System.out.println("Store: purchase " + paramString1 + ": " + paramBoolean + ": " + paramString2);
  }
  
  public void queryProducts(String[] paramArrayOfString)
  {
    System.out.println("Store: Query products");
  }
  
  public void queryPurchases()
  {
    System.out.println("Store: Query purchases");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\mojang\minecraftpe\store\Store.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */