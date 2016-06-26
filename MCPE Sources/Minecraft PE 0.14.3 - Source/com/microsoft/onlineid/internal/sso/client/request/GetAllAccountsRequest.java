package com.microsoft.onlineid.internal.sso.client.request;

import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.BundleMarshallerException;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class GetAllAccountsRequest
  extends SingleSsoRequest<Set<AuthenticatorUserAccount>>
{
  public GetAllAccountsRequest(Context paramContext, Bundle paramBundle)
  {
    super(paramContext, paramBundle);
  }
  
  public Set<AuthenticatorUserAccount> performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Object localObject = this._msaSsoService.getAllAccounts(getDefaultCallingParams());
    SingleSsoRequest.checkForErrors((Bundle)localObject);
    HashSet localHashSet = new HashSet();
    localObject = ((Bundle)localObject).getParcelableArrayList("com.microsoft.onlineid.all_users").iterator();
    while (((Iterator)localObject).hasNext())
    {
      Bundle localBundle = (Bundle)((Iterator)localObject).next();
      try
      {
        localHashSet.add(BundleMarshaller.limitedUserAccountFromBundle(localBundle));
      }
      catch (BundleMarshallerException localBundleMarshallerException)
      {
        Logger.error("Encountered an error while trying to unbundle accounts.", localBundleMarshallerException);
        ClientAnalytics.get().logException(localBundleMarshallerException);
      }
    }
    return localHashSet;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\request\GetAllAccountsRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */