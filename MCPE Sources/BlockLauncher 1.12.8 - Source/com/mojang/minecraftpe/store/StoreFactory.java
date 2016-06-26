package com.mojang.minecraftpe.store;

public class StoreFactory
{
  public static Store createAmazonAppStore(StoreListener paramStoreListener)
  {
    return new Store(paramStoreListener);
  }
  
  public static Store createGooglePlayStore(String paramString, StoreListener paramStoreListener)
  {
    return new Store(paramStoreListener);
  }
  
  public static Store createSamsungAppStore(StoreListener paramStoreListener)
  {
    return new Store(paramStoreListener);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\mojang\minecraftpe\store\StoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */