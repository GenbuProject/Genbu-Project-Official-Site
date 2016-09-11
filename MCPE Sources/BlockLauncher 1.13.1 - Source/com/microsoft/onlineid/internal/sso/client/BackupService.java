package com.microsoft.onlineid.internal.sso.client;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.SsoService;
import com.microsoft.onlineid.internal.sso.client.request.RetrieveBackupRequest;
import com.microsoft.onlineid.internal.sso.client.request.StoreBackupRequest;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.Int;
import java.util.List;

public class BackupService
  extends IntentService
{
  public static final String ActionPushBackup = "com.microsoft.onlineid.internal.sso.client.PUSH_BACKUP";
  public static final String ActionPushBackupIfNeeded = "com.microsoft.onlineid.internal.sso.client.PUSH_BACKUP_IF_NEEDED";
  private Context _applicationContext;
  private ServerConfig _config;
  private MsaSsoClient _msaSsoClient;
  private ServiceFinder _serviceFinder;
  private TypedStorage _storage;
  
  public BackupService()
  {
    super(BackupService.class.getName());
  }
  
  public static void pushBackup(Context paramContext)
  {
    paramContext.startService(new Intent(paramContext, BackupService.class).setAction("com.microsoft.onlineid.internal.sso.client.PUSH_BACKUP"));
  }
  
  public static void pushBackupIfNeeded(Context paramContext)
  {
    paramContext.startService(new Intent(paramContext, BackupService.class).setAction("com.microsoft.onlineid.internal.sso.client.PUSH_BACKUP_IF_NEEDED"));
  }
  
  public void onCreate()
  {
    super.onCreate();
    this._applicationContext = getApplicationContext();
    this._config = new ServerConfig(this._applicationContext);
    this._storage = new TypedStorage(this._applicationContext);
    this._msaSsoClient = new MsaSsoClient(this._applicationContext);
    this._serviceFinder = new ServiceFinder(this._applicationContext);
  }
  
  protected void onHandleIntent(Intent paramIntent)
  {
    paramIntent = paramIntent.getAction();
    if (paramIntent.equals("com.microsoft.onlineid.internal.sso.client.PUSH_BACKUP"))
    {
      retrieveAndPushBackup();
      return;
    }
    if (paramIntent.equals("com.microsoft.onlineid.internal.sso.client.PUSH_BACKUP_IF_NEEDED"))
    {
      retrieveAndPushBackupIfNeeded();
      return;
    }
    paramIntent = new InternalException("Unknown action: " + paramIntent);
    ClientAnalytics.get().logException(paramIntent);
    Logger.error("Backup failed.", paramIntent);
  }
  
  protected void retrieveAndPushBackup()
  {
    List localList = this._serviceFinder.getOrderedSsoServices();
    if ((localList.size() > 0) && (this._applicationContext.getPackageName().equals(((SsoService)localList.get(0)).getPackageName())))
    {
      Bundle localBundle = retrieveBackup((SsoService)localList.get(0));
      if ((localBundle != null) && (!localBundle.isEmpty()))
      {
        int j = Math.min(localList.size() - 1, this._config.getInt(ServerConfig.Int.BackupSlaveCount));
        int i = 1;
        while (i <= j)
        {
          storeBackup((SsoService)localList.get(i), localBundle);
          i += 1;
        }
        this._storage.writeLastBackupPushedTime();
      }
    }
  }
  
  protected void retrieveAndPushBackupIfNeeded()
  {
    if ((System.currentTimeMillis() - this._storage.readLastBackupPushedTime()) / 1000L >= this._config.getInt(ServerConfig.Int.MaxSecondsBetweenBackups)) {
      retrieveAndPushBackup();
    }
  }
  
  protected Bundle retrieveBackup(SsoService paramSsoService)
  {
    try
    {
      paramSsoService = (Bundle)this._msaSsoClient.performRequestWithFallback(new RetrieveBackupRequest(this._applicationContext));
      return paramSsoService;
    }
    catch (Exception paramSsoService)
    {
      Logger.error("Retrieve backup failed.", paramSsoService);
      ClientAnalytics.get().logException(paramSsoService);
    }
    return null;
  }
  
  protected void storeBackup(SsoService paramSsoService, Bundle paramBundle)
  {
    try
    {
      new StoreBackupRequest(this._applicationContext, paramBundle).performRequest(paramSsoService);
      return;
    }
    catch (Exception paramSsoService)
    {
      Logger.error("Store backup failed.", paramSsoService);
      ClientAnalytics.get().logException(paramSsoService);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\client\BackupService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */