package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.internal.PendingIntentBuilder;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.internal.ui.AccountPickerActivity;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Set;

public class GetAccountPickerOperation
  extends ServiceOperation
{
  public GetAccountPickerOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager)
  {
    super(paramContext, paramBundle, paramAuthenticatorAccountManager, paramTicketManager);
  }
  
  public Bundle call()
  {
    ArrayList localArrayList = getParameters().getStringArrayList("com.microsoft.onlineid.cid_exclusion_list");
    String str1 = getParameters().getString("com.microsoft.onlineid.preferred_membername_type");
    String str2 = getParameters().getString("com.microsoft.onlineid.cobranding_id");
    HashSet localHashSet = new HashSet();
    if (localArrayList != null) {
      localHashSet.addAll(localArrayList);
    }
    if (!getAccountManager().getFilteredAccounts(localHashSet).isEmpty()) {
      return BundleMarshaller.pendingIntentToBundle(getPendingIntentBuilder(AccountPickerActivity.getAccountPickerIntent(getContext(), localArrayList, str1, str2, getCallingPackage(), getCallerStateBundle())).setContext(getContext()).buildActivity());
    }
    return new GetSignInIntentOperation(getContext(), getParameters(), getAccountManager(), getTicketManager()).call();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\service\operation\GetAccountPickerOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */