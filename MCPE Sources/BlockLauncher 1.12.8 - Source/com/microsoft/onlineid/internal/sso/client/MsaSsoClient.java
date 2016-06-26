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
    int k = this._config.getInt(ServerConfig.Int.MaxTriesForSsoRequestWithFallback);
    Object localObject1;
    if (k < 1)
    {
      localObject1 = "Invalid MaxTriesForSsoRequestWithFallback: " + k;
      Logger.error((String)localObject1);
      ClientAnalytics.get().logEvent("SDK", "SSO fallback", (String)localObject1);
      k = 1;
    }
    for (;;)
    {
      Object localObject3 = this._serviceFinder.getOrderedSsoServices().iterator();
      int j;
      int i;
      if (((Iterator)localObject3).hasNext())
      {
        localObject1 = (SsoService)((Iterator)localObject3).next();
        j = 0;
        i = 0;
      }
      int m;
      int n;
      label302:
      Object localObject5;
      for (;;)
      {
        if ((i >= k) || (localObject1 == null)) {
          break label494;
        }
        try
        {
          localObject4 = paramSingleSsoRequest.performRequest((SsoService)localObject1);
          return (T)localObject4;
          localObject1 = null;
          j = 0;
          i = 0;
        }
        catch (MasterRedirectException localMasterRedirectException)
        {
          localObject1 = localMasterRedirectException.getRedirectRequestTo();
          localObject4 = "Redirect to: " + (String)localObject1;
          Logger.info((String)localObject4, localMasterRedirectException);
          ClientAnalytics.get().logEvent("SDK", "SSO fallback", (String)localObject4);
          SsoService localSsoService2 = this._serviceFinder.getSsoService((String)localObject1);
          localObject1 = localSsoService2;
          localObject4 = localObject3;
          m = j;
          n = i;
          if (localSsoService2 == null)
          {
            Logger.error("Cannot find redirected master", localMasterRedirectException);
            ClientAnalytics.get().logEvent("SDK", "SSO fallback", "Cannot find redirected master");
            if (!((Iterator)localObject3).hasNext()) {
              break label302;
            }
            localObject1 = (SsoService)((Iterator)localObject3).next();
            n = i;
            m = j;
            localObject4 = localObject3;
          }
          for (;;)
          {
            i = n + 1;
            localObject3 = localObject4;
            j = m;
            break;
            localObject1 = null;
            localObject4 = localObject3;
            m = j;
            n = i;
          }
        }
        catch (ServiceBindingException localServiceBindingException)
        {
          for (;;)
          {
            Object localObject4;
            SsoService localSsoService1;
            if (((Iterator)localObject3).hasNext())
            {
              localSsoService1 = (SsoService)((Iterator)localObject3).next();
              localObject4 = localObject3;
              m = j;
              n = i;
            }
            else
            {
              localSsoService1 = null;
              localObject4 = localObject3;
              m = j;
              n = i;
            }
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
            localObject5 = localObject3;
            m = j;
            n = i;
          } while (!this._configManager.update());
          localObject5 = this._serviceFinder.getOrderedSsoServices().iterator();
          if (!((Iterator)localObject5).hasNext()) {
            break label488;
          }
        }
      }
      Object localObject2 = (SsoService)((Iterator)localObject5).next();
      label471:
      if (j == 0) {
        i -= 1;
      }
      for (;;)
      {
        m = 1;
        n = i;
        break;
        label488:
        localObject2 = null;
        break label471;
        label494:
        localObject2 = String.format(Locale.US, "SSO request failed after %d tries", new Object[] { Integer.valueOf(i) });
        Logger.error((String)localObject2);
        ClientAnalytics.get().logEvent("SDK", "SSO fallback", (String)localObject2);
        return (T)performRequestWithSelf(paramSingleSsoRequest);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\sso\client\MsaSsoClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */