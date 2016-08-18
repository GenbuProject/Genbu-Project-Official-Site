package com.mojang.minecraftpe.store;

public abstract interface Store
{
  public abstract void acknowledgePurchase(String paramString1, String paramString2);
  
  public abstract void destructor();
  
  public abstract String getStoreId();
  
  public abstract void purchase(String paramString1, boolean paramBoolean, String paramString2);
  
  public abstract void queryProducts(String[] paramArrayOfString);
  
  public abstract void queryPurchases();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\mojang\minecraftpe\store\Store.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */