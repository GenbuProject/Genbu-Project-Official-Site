package com.microsoft.onlineid.internal.sso.client;

import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.Resources;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.SsoService;
import com.microsoft.onlineid.internal.sso.client.request.RetrieveBackupRequest;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import java.util.Iterator;
import java.util.List;

public class MigrationManager
{
  public static final String InitialSdkVersion = "0";
  private final String _appSdkVersion;
  private final Context _applicationContext;
  private final ServiceFinder _serviceFinder;
  private List<SsoService> _ssoServices;
  private final TypedStorage _typedStorage;
  
  public MigrationManager(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._typedStorage = new TypedStorage(paramContext);
    this._serviceFinder = new ServiceFinder(paramContext);
    this._appSdkVersion = Resources.getSdkVersion(paramContext);
  }
  
  protected RetrieveBackupRequest createRetrieveBackupRequest(Context paramContext)
  {
    return new RetrieveBackupRequest(paramContext);
  }
  
  public void migrateAndUpgradeStorageIfNeeded()
  {
    String str = this._typedStorage.readSdkVersion();
    if (str == null)
    {
      this._typedStorage.writeSdkVersion("0");
      this._ssoServices = this._serviceFinder.getOrderedSsoServices();
      if (!this._ssoServices.isEmpty()) {
        migrateStorage();
      }
    }
    if ((str == null) || (!str.equals(this._appSdkVersion)))
    {
      upgradeStorage(str, this._appSdkVersion);
      this._typedStorage.writeSdkVersion(this._appSdkVersion);
    }
  }
  
  protected void migrateStorage()
  {
    String str1 = this._applicationContext.getPackageName();
    int i = 0;
    Iterator localIterator = this._ssoServices.iterator();
    for (;;)
    {
      int j = i;
      Object localObject;
      String str2;
      if (localIterator.hasNext())
      {
        localObject = (SsoService)localIterator.next();
        str2 = ((SsoService)localObject).getPackageName();
        if (!str2.equals(str1)) {
          j = i + 1;
        }
      }
      else
      {
        try
        {
          localObject = (Bundle)createRetrieveBackupRequest(this._applicationContext).performRequest((SsoService)localObject);
          i = j;
          if (!((Bundle)localObject).isEmpty())
          {
            this._typedStorage.storeBackup((Bundle)localObject);
            Logger.info(str1 + " migrated backup data from " + str2);
            ClientAnalytics.get().logEvent("Migration and Upgrade", "Migration attempts", String.valueOf(j));
            return;
          }
        }
        catch (Exception localException)
        {
          Logger.error("Encountered an error attempting to migrate storage from " + str2, localException);
          ClientAnalytics.get().logException(localException);
          i = j;
        }
      }
    }
  }
  
  protected void upgradeStorage(String paramString1, String paramString2) {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\MigrationManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */