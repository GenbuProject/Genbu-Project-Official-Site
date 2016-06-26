package com.microsoft.onlineid.internal.sso.client.request;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;

public class StoreBackupRequest
  extends SingleSsoRequest<Void>
{
  private final Bundle _backup;
  
  public StoreBackupRequest(Context paramContext, Bundle paramBundle)
  {
    super(paramContext, null);
    this._backup = paramBundle;
  }
  
  public Void performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    localBundle.putAll(this._backup);
    SingleSsoRequest.checkForErrors(this._msaSsoService.storeBackup(localBundle));
    return null;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\sso\client\request\StoreBackupRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */