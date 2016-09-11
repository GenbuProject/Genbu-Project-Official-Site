package com.microsoft.onlineid.internal.sso.client.request;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.OnlineIdConfiguration;
import com.microsoft.onlineid.SignUpOptions;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;

public class GetSignUpIntentRequest
  extends SingleSsoRequest<PendingIntent>
{
  private final OnlineIdConfiguration _onlineIdConfiguration;
  private final SignUpOptions _signUpOptions;
  
  public GetSignUpIntentRequest(Context paramContext, Bundle paramBundle, SignUpOptions paramSignUpOptions, OnlineIdConfiguration paramOnlineIdConfiguration)
  {
    super(paramContext, paramBundle);
    this._signUpOptions = paramSignUpOptions;
    this._onlineIdConfiguration = paramOnlineIdConfiguration;
  }
  
  public PendingIntent performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    if (this._signUpOptions != null) {
      localBundle.putAll(this._signUpOptions.asBundle());
    }
    if (this._onlineIdConfiguration != null) {
      localBundle.putAll(BundleMarshaller.onlineIdConfigurationToBundle(this._onlineIdConfiguration));
    }
    localBundle = this._msaSsoService.getSignUpIntent(localBundle);
    SingleSsoRequest.checkForErrors(localBundle);
    return BundleMarshaller.pendingIntentFromBundle(localBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\client\request\GetSignUpIntentRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */