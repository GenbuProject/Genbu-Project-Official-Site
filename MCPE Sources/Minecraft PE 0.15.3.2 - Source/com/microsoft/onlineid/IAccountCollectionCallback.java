package com.microsoft.onlineid;

import android.os.Bundle;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.IFailureCallback;
import java.util.Set;

public abstract interface IAccountCollectionCallback
  extends IFailureCallback
{
  public abstract void onAccountCollectionAcquired(Set<UserAccount> paramSet, Bundle paramBundle);
  
  public abstract void onFailure(AuthenticationException paramAuthenticationException, Bundle paramBundle);
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\IAccountCollectionCallback.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */