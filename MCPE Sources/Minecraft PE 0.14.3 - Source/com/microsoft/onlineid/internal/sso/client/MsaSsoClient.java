package com.microsoft.onlineid.internal.sso.client;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.OnlineIdConfiguration;
import com.microsoft.onlineid.SignInOptions;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.MasterRedirectException;
import com.microsoft.onlineid.internal.sso.SsoService;
import com.microsoft.onlineid.internal.sso.client.request.GetAccountByIdRequest;
import com.microsoft.onlineid.internal.sso.client.request.GetAccountPickerIntentRequest;
import com.microsoft.onlineid.internal.sso.client.request.GetAccountRequest;
import com.microsoft.onlineid.internal.sso.client.request.GetAllAccountsRequest;
import com.microsoft.onlineid.internal.sso.client.request.GetSignInIntentRequest;
import com.microsoft.onlineid.internal.sso.client.request.GetSignOutIntentRequest;
import com.microsoft.onlineid.internal.sso.client.request.GetSignUpIntentRequest;
import com.microsoft.onlineid.internal.sso.client.request.GetTicketRequest;
import com.microsoft.onlineid.internal.sso.client.request.SingleSsoRequest;
import com.microsoft.onlineid.internal.sso.exception.ClientNotAuthorizedException;
import com.microsoft.onlineid.internal.sso.exception.UnsupportedClientVersionException;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import com.microsoft.onlineid.sts.ConfigManager;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.Int;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Set;

public class MsaSsoClient
{
  private final Context _applicationContext;
  private final ServerConfig _config;
  private final ConfigManager _configManager;
  private final MigrationManager _migrationManager;
  private final ServiceFinder _serviceFinder;
  
  public MsaSsoClient(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._config = new ServerConfig(paramContext);
    this._serviceFinder = new ServiceFinder(paramContext);
    this._configManager = new ConfigManager(paramContext);
    this._migrationManager = new MigrationManager(paramContext);
  }
  
  private <T> T performRequestWithSelf(SingleSsoRequest<T> paramSingleSsoRequest)
    throws AuthenticationException
  {
    Logger.info("Attempting to self-service request.");
    return (T)paramSingleSsoRequest.performRequest(this._serviceFinder.getSelfSsoService());
  }
  
  public SsoResponse<AuthenticatorUserAccount> getAccount(OnlineIdConfiguration paramOnlineIdConfiguration, Bundle paramBundle)
    throws AuthenticationException
  {
    return (SsoResponse)performRequestWithFallback(new GetAccountRequest(this._applicationContext, paramBundle, paramOnlineIdConfiguration));
  }
  
  public AuthenticatorUserAccount getAccountById(String paramString, Bundle paramBundle)
    throws AuthenticationException
  {
    return (AuthenticatorUserAccount)performRequestWithFallback(new GetAccountByIdRequest(this._applicationContext, paramBundle, paramString));
  }
  
  public PendingIntent getAccountPickerIntent(ArrayList<String> paramArrayList, OnlineIdConfiguration paramOnlineIdConfiguration, Bundle paramBundle)
    throws AuthenticationException
  {
    return (PendingIntent)performRequestWithFallback(new GetAccountPickerIntentRequest(this._applicationContext, paramBundle, paramArrayList, paramOnlineIdConfiguration));
  }
  
  public Set<AuthenticatorUserAccount> getAllAccounts(Bundle paramBundle)
    throws AuthenticationException
  {
    return (Set)performRequestWithFallback(new GetAllAccountsRequest(this._applicationContext, paramBundle));
  }
  
  public PendingIntent getSignInIntent(SignInOptions paramSignInOptions, OnlineIdConfiguration paramOnlineIdConfiguration, Bundle paramBundle)
    throws AuthenticationException
  {
    return (PendingIntent)performRequestWithFallback(new GetSignInIntentRequest(this._applicationContext, paramBundle, paramSignInOptions, paramOnlineIdConfiguration));
  }
  
  public PendingIntent getSignOutIntent(String paramString, Bundle paramBundle)
    throws AuthenticationException
  {
    return (PendingIntent)performRequestWithFallback(new GetSignOutIntentRequest(this._applicationContext, paramBundle, paramString));
  }
  
  public PendingIntent getSignUpIntent(OnlineIdConfiguration paramOnlineIdConfiguration, Bundle paramBundle)
    throws AuthenticationException
  {
    return (PendingIntent)performRequestWithFallback(new GetSignUpIntentRequest(this._applicationContext, paramBundle, paramOnlineIdConfiguration));
  }
  
  public SsoResponse<Ticket> getTicket(String paramString, ISecurityScope paramISecurityScope, OnlineIdConfiguration paramOnlineIdConfiguration, Bundle paramBundle)
    throws AuthenticationException
  {
    return (SsoResponse)performRequestWithFallback(new GetTicketRequest(this._applicationContext, paramBundle, paramString, paramISecurityScope, paramOnlineIdConfiguration));
  }
  
  protected <T> T performRequestWithFallback(SingleSsoRequest<T> paramSingleSsoRequest)
    throws AuthenticationException
  {
    this._configManager.updateIfFirstDownloadNeeded();
    this._migrationManager.migrateAndUpgradeStorageIfNeeded();
    int i = this._config.getInt(ServerConfig.Int.MaxTriesForSsoRequestWithFallback);
    int k = i;
    Object localObject1;
    if (i < 1)
    {
      localObject1 = "Invalid MaxTriesForSsoRequestWithFallback: " + i;
      Logger.error((String)localObject1);
      ClientAnalytics.get().logEvent("SDK", "SSO fallback", (String)localObject1);
      k = 1;
    }
    i = 0;
    int m = 0;
    Object localObject3 = this._serviceFinder.getOrderedSsoServices().iterator();
    if (((Iterator)localObject3).hasNext()) {
      localObject1 = (SsoService)((Iterator)localObject3).next();
    }
    int n;
    int j;
    label299:
    Object localObject5;
    for (;;)
    {
      if ((i >= k) || (localObject1 == null)) {
        break label481;
      }
      try
      {
        localObject4 = paramSingleSsoRequest.performRequest((SsoService)localObject1);
        return (T)localObject4;
        localObject1 = null;
      }
      catch (MasterRedirectException localMasterRedirectException)
      {
        localObject1 = localMasterRedirectException.getRedirectRequestTo();
        localObject4 = "Redirect to: " + (String)localObject1;
        Logger.info((String)localObject4, localMasterRedirectException);
        ClientAnalytics.get().logEvent("SDK", "SSO fallback", (String)localObject4);
        SsoService localSsoService2 = this._serviceFinder.getSsoService((String)localObject1);
        n = m;
        localObject4 = localObject3;
        localObject1 = localSsoService2;
        j = i;
        if (localSsoService2 == null)
        {
          Logger.error("Cannot find redirected master", localMasterRedirectException);
          ClientAnalytics.get().logEvent("SDK", "SSO fallback", "Cannot find redirected master");
          if (!((Iterator)localObject3).hasNext()) {
            break label299;
          }
          localObject1 = (SsoService)((Iterator)localObject3).next();
          j = i;
          localObject4 = localObject3;
          n = m;
        }
        for (;;)
        {
          i = j + 1;
          m = n;
          localObject3 = localObject4;
          break;
          localObject1 = null;
          n = m;
          localObject4 = localObject3;
          j = i;
        }
      }
      catch (ServiceBindingException localServiceBindingException)
      {
        Object localObject4;
        if (((Iterator)localObject3).hasNext()) {}
        for (SsoService localSsoService1 = (SsoService)((Iterator)localObject3).next();; localSsoService1 = null)
        {
          n = m;
          localObject4 = localObject3;
          j = i;
          break;
        }
      }
      catch (ClientNotAuthorizedException localClientNotAuthorizedException)
      {
        return (T)performRequestWithSelf(paramSingleSsoRequest);
      }
      catch (UnsupportedClientVersionException localUnsupportedClientVersionException)
      {
        return (T)performRequestWithSelf(paramSingleSsoRequest);
      }
      catch (ClientConfigUpdateNeededException localClientConfigUpdateNeededException)
      {
        do
        {
          Logger.info("Client needs config update: " + localClientConfigUpdateNeededException.getMessage());
          n = m;
          localObject5 = localObject3;
          j = i;
        } while (!this._configManager.update());
        localObject5 = this._serviceFinder.getOrderedSsoServices().iterator();
        if (!((Iterator)localObject5).hasNext()) {}
      }
    }
    for (Object localObject2 = (SsoService)((Iterator)localObject5).next();; localObject2 = null)
    {
      j = i;
      if (m == 0) {
        j = i - 1;
      }
      n = 1;
      break;
    }
    label481:
    localObject2 = String.format(Locale.US, "SSO request failed after %d tries", new Object[] { Integer.valueOf(i) });
    Logger.error((String)localObject2);
    ClientAnalytics.get().logEvent("SDK", "SSO fallback", (String)localObject2);
    return (T)performRequestWithSelf(paramSingleSsoRequest);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\MsaSsoClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */