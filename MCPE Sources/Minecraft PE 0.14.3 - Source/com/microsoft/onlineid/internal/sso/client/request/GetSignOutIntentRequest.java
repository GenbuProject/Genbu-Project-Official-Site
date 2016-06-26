package com.microsoft.onlineid.internal.sso.client.request;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;

public class GetSignOutIntentRequest
  extends SingleSsoRequest<PendingIntent>
{
  private final String _cid;
  
  public GetSignOutIntentRequest(Context paramContext, Bundle paramBundle, String paramString)
  {
    super(paramContext, paramBundle);
    this._cid = paramString;
  }
  
  public PendingIntent performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    localBundle.putString("com.microsoft.onlineid.user_cid", this._cid);
    localBundle = this._msaSsoService.getSignOutIntent(localBundle);
    SingleSsoRequest.checkForErrors(localBundle);
    return BundleMarshaller.pendingIntentFromBundle(localBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\request\GetSignOutIntentRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */