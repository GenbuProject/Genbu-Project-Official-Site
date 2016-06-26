package com.microsoft.onlineid.internal.sso.client.request;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.OnlineIdConfiguration;
import com.microsoft.onlineid.SignInOptions;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;

public class GetSignInIntentRequest
  extends SingleSsoRequest<PendingIntent>
{
  private final OnlineIdConfiguration _onlineIdConfiguration;
  private final SignInOptions _signInOptions;
  
  public GetSignInIntentRequest(Context paramContext, Bundle paramBundle, SignInOptions paramSignInOptions, OnlineIdConfiguration paramOnlineIdConfiguration)
  {
    super(paramContext, paramBundle);
    this._signInOptions = paramSignInOptions;
    this._onlineIdConfiguration = paramOnlineIdConfiguration;
  }
  
  public PendingIntent performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    if (this._signInOptions != null) {}
    for (Object localObject = this._signInOptions.getPrefillUsername();; localObject = null)
    {
      if (localObject != null) {
        localBundle.putString("com.microsoft.onlineid.prefill_username", (String)localObject);
      }
      if (this._onlineIdConfiguration != null) {
        localBundle.putAll(BundleMarshaller.onlineIdConfigurationToBundle(this._onlineIdConfiguration));
      }
      localObject = this._msaSsoService.getSignInIntent(localBundle);
      SingleSsoRequest.checkForErrors((Bundle)localObject);
      return BundleMarshaller.pendingIntentFromBundle((Bundle)localObject);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\request\GetSignInIntentRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */