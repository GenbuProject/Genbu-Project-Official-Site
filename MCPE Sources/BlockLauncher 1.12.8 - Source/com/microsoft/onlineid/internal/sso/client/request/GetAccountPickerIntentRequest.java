package com.microsoft.onlineid.internal.sso.client.request;

import android.app.PendingIntent;
import android.content.Context;
import android.os.Bundle;
import android.os.RemoteException;
import com.microsoft.onlineid.OnlineIdConfiguration;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;
import java.util.ArrayList;

public class GetAccountPickerIntentRequest
  extends SingleSsoRequest<PendingIntent>
{
  private final ArrayList<String> _cidExclusionList;
  private final OnlineIdConfiguration _onlineIdConfiguration;
  
  public GetAccountPickerIntentRequest(Context paramContext, Bundle paramBundle, ArrayList<String> paramArrayList, OnlineIdConfiguration paramOnlineIdConfiguration)
  {
    super(paramContext, paramBundle);
    this._cidExclusionList = paramArrayList;
    this._onlineIdConfiguration = paramOnlineIdConfiguration;
  }
  
  public PendingIntent performRequestTask()
    throws RemoteException, AuthenticationException
  {
    Bundle localBundle = getDefaultCallingParams();
    localBundle.putStringArrayList("com.microsoft.onlineid.cid_exclusion_list", this._cidExclusionList);
    if (this._onlineIdConfiguration != null) {
      localBundle.putAll(BundleMarshaller.onlineIdConfigurationToBundle(this._onlineIdConfiguration));
    }
    localBundle = this._msaSsoService.getAccountPickerIntent(localBundle);
    SingleSsoRequest.checkForErrors(localBundle);
    return BundleMarshaller.pendingIntentFromBundle(localBundle);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\sso\client\request\GetAccountPickerIntentRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */