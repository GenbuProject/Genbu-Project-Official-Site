package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.PendingIntentBuilder;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.exception.InvalidResponseException;
import com.microsoft.onlineid.sts.exception.StsException;

public abstract class ServiceOperation
{
  private final AuthenticatorAccountManager _accountManager;
  private final Context _applicationContext;
  private final Bundle _parameters;
  private final TicketManager _ticketManager;
  
  public ServiceOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager)
  {
    this._applicationContext = paramContext;
    this._parameters = paramBundle;
    this._accountManager = paramAuthenticatorAccountManager;
    this._ticketManager = paramTicketManager;
  }
  
  public abstract Bundle call()
    throws AccountNotFoundException, InvalidResponseException, NetworkException, StsException, InternalException;
  
  protected AuthenticatorAccountManager getAccountManager()
  {
    return this._accountManager;
  }
  
  public long getCallerConfigLastDownloadedTime()
  {
    return this._parameters.getLong("com.microsoft.onlineid.client_config_last_downloaded_time");
  }
  
  public String getCallerConfigVersion()
  {
    return this._parameters.getString("com.microsoft.onlineid.client_config_version");
  }
  
  public String getCallerSdkVersion()
  {
    return this._parameters.getString("com.microsoft.onlineid.client_sdk_version");
  }
  
  public int getCallerSsoVersion()
  {
    return this._parameters.getInt("com.microsoft.onlineid.client_sso_version");
  }
  
  public Bundle getCallerStateBundle()
  {
    return this._parameters.getBundle("com.microsoft.onlineid.client_state");
  }
  
  public String getCallingPackage()
  {
    return this._parameters.getString("com.microsoft.onlineid.client_package_name");
  }
  
  protected Context getContext()
  {
    return this._applicationContext;
  }
  
  public Bundle getParameters()
  {
    return this._parameters;
  }
  
  protected PendingIntentBuilder getPendingIntentBuilder(Intent paramIntent)
  {
    return new PendingIntentBuilder(paramIntent);
  }
  
  protected TicketManager getTicketManager()
  {
    return this._ticketManager;
  }
  
  public void verifyStandardArguments()
  {
    Strings.verifyArgumentNotNullOrEmpty(getCallingPackage(), "Package name");
    Strings.verifyArgumentNotNullOrEmpty(getCallerSdkVersion(), "SDK version");
    Strings.verifyArgumentNotNullOrEmpty(getCallerConfigVersion(), "Config version");
    if (getCallerSsoVersion() == 0) {
      throw new IllegalArgumentException("SSO version must not be empty.");
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\service\operation\ServiceOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */