package com.mojang.minecraftpe.store;

import com.mojang.minecraftpe.MainActivity;
import com.mojang.minecraftpe.store.amazonappstore.AmazonAppStore;
import com.mojang.minecraftpe.store.googleplay.GooglePlayStore;
import com.mojang.minecraftpe.store.samsungappstore.SamsungAppStore;

public class StoreFactory
{
  static Store createAmazonAppStore(StoreListener paramStoreListener)
  {
    return new AmazonAppStore(MainActivity.mInstance, paramStoreListener);
  }
  
  static Store createGooglePlayStore(String paramString, StoreListener paramStoreListener)
  {
    return new GooglePlayStore(MainActivity.mInstance, paramString, paramStoreListener);
  }
  
  static Store createSamsungAppStore(StoreListener paramStoreListener)
  {
    return new SamsungAppStore(MainActivity.mInstance, paramStoreListener);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\mojang\minecraftpe\store\StoreFactory.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */