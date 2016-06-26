package com.microsoft.onlineid.internal.sso.client.request;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;

public class RetrieveBackupRequest
  extends SingleSsoRequest<Bundle>
{
  public RetrieveBackupRequest(Context paramContext)
  {
    super(paramContext, null);
  }
  
  public Bundle performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = this._msaSsoService.retrieveBackup(getDefaultCallingParams());
    SingleSsoRequest.checkForErrors(localBundle);
    return localBundle;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\request\RetrieveBackupRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */