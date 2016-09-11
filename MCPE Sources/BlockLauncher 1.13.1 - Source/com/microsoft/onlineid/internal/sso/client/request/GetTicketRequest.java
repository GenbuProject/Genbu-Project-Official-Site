package com.microsoft.onlineid.internal.sso.client.request;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.OnlineIdConfiguration;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.client.SsoResponse;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;

public class GetTicketRequest
  extends SingleSsoRequest<SsoResponse<Ticket>>
{
  private final String _cid;
  private final OnlineIdConfiguration _onlineIdConfiguration;
  private final ISecurityScope _securityScope;
  
  public GetTicketRequest(Context paramContext, Bundle paramBundle, String paramString, ISecurityScope paramISecurityScope, OnlineIdConfiguration paramOnlineIdConfiguration)
  {
    super(paramContext, paramBundle);
    this._cid = paramString;
    this._securityScope = paramISecurityScope;
    this._onlineIdConfiguration = paramOnlineIdConfiguration;
  }
  
  public SsoResponse<Ticket> performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    localBundle.putString("com.microsoft.onlineid.user_cid", this._cid);
    localBundle.putAll(BundleMarshaller.scopeToBundle(this._securityScope));
    if (this._onlineIdConfiguration != null) {
      localBundle.putAll(BundleMarshaller.onlineIdConfigurationToBundle(this._onlineIdConfiguration));
    }
    localBundle = this._msaSsoService.getTicket(localBundle);
    SingleSsoRequest.checkForErrors(localBundle);
    if (BundleMarshaller.hasPendingIntent(localBundle)) {
      return new SsoResponse().setPendingIntent(BundleMarshaller.pendingIntentFromBundle(localBundle));
    }
    return new SsoResponse().setData(BundleMarshaller.ticketFromBundle(localBundle));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\client\request\GetTicketRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */