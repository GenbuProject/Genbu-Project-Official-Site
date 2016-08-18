package com.microsoft.onlineid.internal.sts;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.net.Uri.Builder;
import android.os.Bundle;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.exception.NetworkException;
import com.microsoft.onlineid.internal.ApiRequest;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.MsaService;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Resources;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.exception.PromptNeededException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.client.BackupService;
import com.microsoft.onlineid.internal.storage.TicketStorage;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.ui.InterruptResolutionActivity;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.ConfigManager;
import com.microsoft.onlineid.sts.DAToken;
import com.microsoft.onlineid.sts.DeviceIdentity;
import com.microsoft.onlineid.sts.DeviceIdentityManager;
import com.microsoft.onlineid.sts.FlightManager;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.exception.InvalidResponseException;
import com.microsoft.onlineid.sts.exception.StsException;
import com.microsoft.onlineid.sts.request.ServiceRequest;
import com.microsoft.onlineid.sts.request.StsRequestFactory;
import com.microsoft.onlineid.sts.response.ServiceResponse;

public class TicketManager
{
  private final Context _applicationContext;
  private final ConfigManager _configManager;
  private final DeviceIdentityManager _deviceManager;
  private final FlightManager _flightManager;
  private final StsRequestFactory _stsRequestFactory;
  private final TicketStorage _ticketStorage;
  private final TypedStorage _typedStorage;
  
  @Deprecated
  public TicketManager()
  {
    this._applicationContext = null;
    this._configManager = null;
    this._deviceManager = null;
    this._stsRequestFactory = null;
    this._typedStorage = null;
    this._ticketStorage = null;
    this._flightManager = null;
  }
  
  public TicketManager(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._configManager = new ConfigManager(paramContext);
    this._deviceManager = new DeviceIdentityManager(paramContext);
    this._stsRequestFactory = new StsRequestFactory(paramContext);
    this._typedStorage = new TypedStorage(paramContext);
    this._ticketStorage = new TicketStorage(paramContext);
    this._flightManager = new FlightManager(paramContext);
  }
  
  private void updateAccountDetails(String paramString, ServiceResponse paramServiceResponse, boolean paramBoolean)
  {
    Assertion.check(paramServiceResponse.succeeded());
    DAToken localDAToken = paramServiceResponse.getDAToken();
    if (localDAToken != null)
    {
      paramString = this._typedStorage.readAccount(paramString);
      if (paramString != null)
      {
        if (paramBoolean) {
          paramString.setFlights(paramServiceResponse.getFlights());
        }
        paramString.setDAToken(localDAToken);
        this._typedStorage.writeAccount(paramString);
      }
    }
  }
  
  public ApiRequest createTicketRequest(String paramString1, ISecurityScope paramISecurityScope, String paramString2, String paramString3, Bundle paramBundle)
  {
    return new ApiRequest(this._applicationContext, new Intent(this._applicationContext, MsaService.class).setAction("com.microsoft.onlineid.internal.GET_TICKET")).setAccountPuid(paramString1).setScope(paramISecurityScope).setClientPackageName(paramString2).setCobrandingId(paramString3).setClientStateBundle(paramBundle);
  }
  
  protected String getSdkVersion()
  {
    return Resources.getSdkVersion(this._applicationContext);
  }
  
  public Ticket getTicket(String paramString1, ISecurityScope paramISecurityScope, String paramString2)
    throws AccountNotFoundException, InvalidResponseException, StsException, NetworkException, PromptNeededException
  {
    return getTicket(paramString1, paramISecurityScope, this._applicationContext.getPackageName(), paramString2, false, null, false, null);
  }
  
  public Ticket getTicket(String paramString1, ISecurityScope paramISecurityScope, String paramString2, String paramString3, String paramString4)
    throws AccountNotFoundException, InvalidResponseException, StsException, NetworkException, PromptNeededException
  {
    return getTicket(paramString1, paramISecurityScope, paramString2, paramString3, false, paramString4, false, null);
  }
  
  public Ticket getTicket(String paramString1, ISecurityScope paramISecurityScope, String paramString2, String paramString3, boolean paramBoolean1, String paramString4, boolean paramBoolean2, Bundle paramBundle)
    throws AccountNotFoundException, InvalidResponseException, StsException, NetworkException, PromptNeededException
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString1, "accountPuid");
    Objects.verifyArgumentNotNull(paramISecurityScope, "scope");
    Strings.verifyArgumentNotNullOrEmpty(paramString2, "packageName");
    Object localObject = this._ticketStorage.getTicket(paramString1, paramString2, paramISecurityScope);
    if (localObject != null)
    {
      Logger.info("Ticket request serviced from cache: " + paramISecurityScope.toString());
      return (Ticket)localObject;
    }
    localObject = this._typedStorage.readAccount(paramString1);
    if (localObject == null) {
      throw new AccountNotFoundException("The account was deleted.");
    }
    Logger.info("Attempting to get ticket from server: " + paramISecurityScope.toString());
    paramISecurityScope = performServiceRequest((AuthenticatorUserAccount)localObject, paramISecurityScope, paramString2, paramString3, paramBoolean1, paramString4, paramBoolean2, paramBundle);
    Assertion.check(paramISecurityScope.succeeded(), "Service request failure not handled by performServiceRequest");
    updateAccountDetails(paramString1, paramISecurityScope, paramBoolean1);
    if (paramString3 != null)
    {
      BackupService.pushBackup(this._applicationContext);
      if (paramBoolean1) {
        this._flightManager.enrollInFlights();
      }
      paramISecurityScope = paramISecurityScope.getTicket();
      if (paramISecurityScope == null) {
        break label212;
      }
    }
    label212:
    for (paramBoolean1 = true;; paramBoolean1 = false)
    {
      Assertion.check(paramBoolean1);
      this._ticketStorage.storeTicket(paramString1, paramString2, paramISecurityScope);
      return paramISecurityScope;
      BackupService.pushBackupIfNeeded(this._applicationContext);
      break;
    }
  }
  
  public Ticket getTicket(String paramString1, ISecurityScope paramISecurityScope, String paramString2, boolean paramBoolean)
    throws AccountNotFoundException, InvalidResponseException, StsException, NetworkException, PromptNeededException
  {
    return getTicket(paramString1, paramISecurityScope, this._applicationContext.getPackageName(), paramString2, false, null, false, null);
  }
  
  public Ticket getTicketNoCache(AuthenticatorUserAccount paramAuthenticatorUserAccount, ISecurityScope paramISecurityScope, String paramString)
    throws NetworkException, PromptNeededException, InvalidResponseException, StsException
  {
    return getTicketNoCache(paramAuthenticatorUserAccount, paramISecurityScope, this._applicationContext.getPackageName(), paramString);
  }
  
  public Ticket getTicketNoCache(AuthenticatorUserAccount paramAuthenticatorUserAccount, ISecurityScope paramISecurityScope, String paramString1, String paramString2)
    throws NetworkException, PromptNeededException, InvalidResponseException, StsException
  {
    boolean bool = false;
    Objects.verifyArgumentNotNull(paramAuthenticatorUserAccount, "account");
    Objects.verifyArgumentNotNull(paramISecurityScope, "scope");
    Strings.verifyArgumentNotNullOrEmpty(paramString1, "packageName");
    Logger.info("Attempting to get ticket from server: " + paramISecurityScope.toString());
    paramAuthenticatorUserAccount = performServiceRequest(paramAuthenticatorUserAccount, paramISecurityScope, paramString1, paramString2, false, null, false, null);
    Assertion.check(paramAuthenticatorUserAccount.succeeded(), "Service request failure not handled by performServiceRequest");
    paramAuthenticatorUserAccount = paramAuthenticatorUserAccount.getTicket();
    if (paramAuthenticatorUserAccount != null) {
      bool = true;
    }
    Assertion.check(bool);
    return paramAuthenticatorUserAccount;
  }
  
  protected ServiceResponse performServiceRequest(AuthenticatorUserAccount paramAuthenticatorUserAccount, ISecurityScope paramISecurityScope, String paramString1, String paramString2, boolean paramBoolean1, String paramString3, boolean paramBoolean2, Bundle paramBundle)
    throws NetworkException, InvalidResponseException, StsException, PromptNeededException
  {
    Object localObject = this._deviceManager.getDeviceIdentity(false);
    ServiceResponse localServiceResponse = (ServiceResponse)this._stsRequestFactory.createServiceRequest(paramAuthenticatorUserAccount, (DeviceIdentity)localObject, paramISecurityScope, paramString1, paramString2, paramBoolean1).send();
    localObject = localServiceResponse;
    if (!localServiceResponse.succeeded())
    {
      localObject = localServiceResponse;
      if (localServiceResponse.getError().isRetryableDeviceDAErrorForUserAuth())
      {
        localObject = this._deviceManager.getDeviceIdentity(true);
        localObject = (ServiceResponse)this._stsRequestFactory.createServiceRequest(paramAuthenticatorUserAccount, (DeviceIdentity)localObject, paramISecurityScope, paramString1, paramString2, paramBoolean1).send();
      }
    }
    this._configManager.updateIfNeeded(((ServiceResponse)localObject).getConfigVersion());
    if (!((ServiceResponse)localObject).succeeded())
    {
      paramString2 = ((ServiceResponse)localObject).getError();
      Logger.error("ServiceRequest failed with error: " + paramString2.getMessage());
      localObject = ((ServiceResponse)localObject).getInlineAuthUrl();
      if (localObject != null)
      {
        paramString2 = "android" + getSdkVersion();
        localObject = Uri.parse((String)localObject).buildUpon();
        ((Uri.Builder)localObject).appendQueryParameter("platform", paramString2);
        if (paramString3 != null) {
          ((Uri.Builder)localObject).appendQueryParameter("cobrandid", paramString3);
        }
        paramAuthenticatorUserAccount = InterruptResolutionActivity.getResolutionIntent(this._applicationContext, ((Uri.Builder)localObject).build(), paramAuthenticatorUserAccount, paramISecurityScope, paramString3, paramBoolean2, paramString1, paramBundle);
        throw new PromptNeededException(new ApiRequest(this._applicationContext, paramAuthenticatorUserAccount));
      }
      throw new StsException("Could not acquire ticket.", paramString2);
    }
    return (ServiceResponse)localObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\sts\TicketManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */