package com.microsoft.onlineid.internal.sso.client.request;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;

public class GetAccountByIdRequest
  extends SingleSsoRequest<AuthenticatorUserAccount>
{
  private final String _cid;
  
  public GetAccountByIdRequest(Context paramContext, Bundle paramBundle, String paramString)
  {
    super(paramContext, paramBundle);
    this._cid = paramString;
  }
  
  public AuthenticatorUserAccount performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    localBundle.putString("com.microsoft.onlineid.user_cid", this._cid);
    localBundle = this._msaSsoService.getAccountById(localBundle);
    SingleSsoRequest.checkForErrors(localBundle);
    return BundleMarshaller.limitedUserAccountFromBundle(localBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\sso\client\request\GetAccountByIdRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */