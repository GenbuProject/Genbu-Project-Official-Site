package com.microsoft.onlineid.internal.sso.client.request;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.OnlineIdConfiguration;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.client.SsoResponse;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;

public class GetAccountRequest
  extends SingleSsoRequest<SsoResponse<AuthenticatorUserAccount>>
{
  private final OnlineIdConfiguration _onlineIdConfiguration;
  
  public GetAccountRequest(Context paramContext, Bundle paramBundle, OnlineIdConfiguration paramOnlineIdConfiguration)
  {
    super(paramContext, paramBundle);
    this._onlineIdConfiguration = paramOnlineIdConfiguration;
  }
  
  public SsoResponse<AuthenticatorUserAccount> performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    if (this._onlineIdConfiguration != null) {
      localBundle.putAll(BundleMarshaller.onlineIdConfigurationToBundle(this._onlineIdConfiguration));
    }
    localBundle = this._msaSsoService.getAccount(localBundle);
    SingleSsoRequest.checkForErrors(localBundle);
    if (BundleMarshaller.hasPendingIntent(localBundle)) {
      return new SsoResponse().setPendingIntent(BundleMarshaller.pendingIntentFromBundle(localBundle));
    }
    return new SsoResponse().setData(BundleMarshaller.limitedUserAccountFromBundle(localBundle));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\request\GetAccountRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */