package com.microsoft.onlineid.internal.sso.service;

import android.app.Service;
import android.content.Intent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.SignatureVerifier;
import com.microsoft.onlineid.internal.sso.SsoServiceError;
import com.microsoft.onlineid.internal.sso.client.MigrationManager;
import com.microsoft.onlineid.internal.sso.service.operation.GetAccountByIdOperation;
import com.microsoft.onlineid.internal.sso.service.operation.GetAccountOperation;
import com.microsoft.onlineid.internal.sso.service.operation.GetAccountPickerOperation;
import com.microsoft.onlineid.internal.sso.service.operation.GetAllAccountsOperation;
import com.microsoft.onlineid.internal.sso.service.operation.GetSignInIntentOperation;
import com.microsoft.onlineid.internal.sso.service.operation.GetSignOutIntentOperation;
import com.microsoft.onlineid.internal.sso.service.operation.GetSignUpIntentOperation;
import com.microsoft.onlineid.internal.sso.service.operation.GetTicketOperation;
import com.microsoft.onlineid.internal.sso.service.operation.RetrieveBackupOperation;
import com.microsoft.onlineid.internal.sso.service.operation.ServiceOperation;
import com.microsoft.onlineid.internal.sso.service.operation.StoreBackupOperation;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.ConfigManager;

public class MsaSsoService
  extends Service
{
  private AuthenticatorAccountManager _accountManager;
  private final IMsaSsoService.Stub _binder = new IMsaSsoService.Stub()
  {
    public Bundle getAccount(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetAccountOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle getAccountById(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetAccountByIdOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle getAccountPickerIntent(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetAccountPickerOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle getAllAccounts(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetAllAccountsOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle getSignInIntent(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetSignInIntentOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle getSignOutIntent(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetSignOutIntentOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle getSignUpIntent(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetSignUpIntentOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle getTicket(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new GetTicketOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager));
    }
    
    public Bundle retrieveBackup(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new RetrieveBackupOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager, MsaSsoService.this._typedStorage));
    }
    
    public Bundle storeBackup(Bundle paramAnonymousBundle)
      throws RemoteException
    {
      return MsaSsoService.this.handleIncomingRequest(new StoreBackupOperation(MsaSsoService.this.getApplicationContext(), paramAnonymousBundle, MsaSsoService.this._accountManager, MsaSsoService.this._ticketManager, MsaSsoService.this._typedStorage));
    }
  };
  private ConfigManager _configManager;
  private MigrationManager _migrationManager;
  private SignatureVerifier _signatureVerifier;
  private TicketManager _ticketManager;
  private TypedStorage _typedStorage;
  
  protected Bundle handleIncomingRequest(ServiceOperation paramServiceOperation)
  {
    try
    {
      this._configManager.updateIfFirstDownloadNeeded();
      this._migrationManager.migrateAndUpgradeStorageIfNeeded();
      paramServiceOperation.verifyStandardArguments();
      if (paramServiceOperation.getCallerSsoVersion() <= 1) {
        return BundleMarshaller.errorToBundle(SsoServiceError.UnsupportedClientVersion, "Invalid SSO version.");
      }
      String str1 = paramServiceOperation.getCallingPackage();
      if (!this._signatureVerifier.isPackageInUid(Binder.getCallingUid(), str1)) {
        return BundleMarshaller.errorToBundle(SsoServiceError.ClientNotAuthorized, "Invalid caller package name.");
      }
      String str2 = paramServiceOperation.getCallerConfigVersion();
      if ((!this._configManager.hasConfigBeenUpdatedRecently(paramServiceOperation.getCallerConfigLastDownloadedTime())) && (this._configManager.isClientConfigVersionOlder(str2))) {
        return BundleMarshaller.errorToBundle(SsoServiceError.ClientConfigUpdateNeededException, "The caller must update config to version: " + this._configManager.getCurrentConfigVersion());
      }
      this._configManager.updateIfNeeded(str2);
      if (!this._signatureVerifier.isTrusted(str1)) {
        return BundleMarshaller.errorToBundle(SsoServiceError.ClientNotAuthorized, "The caller is not authorized to invoke this service.");
      }
      paramServiceOperation = paramServiceOperation.call();
      return paramServiceOperation;
    }
    catch (Exception paramServiceOperation)
    {
      Logger.warning("SSO Service caught exception", paramServiceOperation);
    }
    return BundleMarshaller.exceptionToBundle(paramServiceOperation);
  }
  
  public IBinder onBind(Intent paramIntent)
  {
    return this._binder;
  }
  
  public void onCreate()
  {
    this._signatureVerifier = new SignatureVerifier(getApplicationContext());
    this._accountManager = new AuthenticatorAccountManager(getApplicationContext());
    this._ticketManager = new TicketManager(getApplicationContext());
    this._typedStorage = new TypedStorage(getApplicationContext());
    this._configManager = new ConfigManager(getApplicationContext());
    this._migrationManager = new MigrationManager(getApplicationContext());
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\service\MsaSsoService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */