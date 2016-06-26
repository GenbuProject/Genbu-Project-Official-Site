package com.microsoft.onlineid.internal.sso.client;

import android.os.Bundle;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.internal.IFailureCallback;
import com.microsoft.onlineid.internal.log.Logger;

public abstract class SsoRunnable
  implements Runnable
{
  private final IFailureCallback _failureCallback;
  private final Bundle _state;
  
  public SsoRunnable(IFailureCallback paramIFailureCallback, Bundle paramBundle)
  {
    this._failureCallback = paramIFailureCallback;
    this._state = paramBundle;
  }
  
  public abstract void performRequest()
    throws AuthenticationException;
  
  public void run()
  {
    try
    {
      performRequest();
      return;
    }
    catch (AuthenticationException localAuthenticationException)
    {
      Logger.error(localAuthenticationException.toString());
      this._failureCallback.onFailure(localAuthenticationException, this._state);
      return;
    }
    catch (Exception localException)
    {
      InternalException localInternalException = new InternalException(localException);
      Logger.error(localInternalException.toString());
      this._failureCallback.onFailure(localInternalException, this._state);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\SsoRunnable.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */