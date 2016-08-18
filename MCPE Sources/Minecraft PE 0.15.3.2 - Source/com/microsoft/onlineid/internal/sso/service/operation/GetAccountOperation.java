package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

public class GetAccountOperation
  extends ServiceOperation
{
  public GetAccountOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager)
  {
    super(paramContext, paramBundle, paramAuthenticatorAccountManager, paramTicketManager);
  }
  
  public Bundle call()
  {
    if (!getAccountManager().hasAccounts()) {
      return new GetSignInIntentOperation(getContext(), getParameters(), getAccountManager(), getTicketManager()).call();
    }
    Set localSet = getAccountManager().getAccounts();
    if (localSet.size() == 1) {
      return BundleMarshaller.limitedUserAccountToBundle((AuthenticatorUserAccount)localSet.iterator().next());
    }
    return new GetAccountPickerOperation(getContext(), getParameters(), getAccountManager(), getTicketManager()).call();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\sso\service\operation\GetAccountOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */