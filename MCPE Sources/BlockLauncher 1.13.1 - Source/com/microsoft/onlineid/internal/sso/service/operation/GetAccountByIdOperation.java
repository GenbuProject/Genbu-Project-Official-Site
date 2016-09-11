package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.internal.Strings;
import com.microsoft.onlineid.internal.exception.AccountNotFoundException;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;

public class GetAccountByIdOperation
  extends ServiceOperation
{
  public GetAccountByIdOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager)
  {
    super(paramContext, paramBundle, paramAuthenticatorAccountManager, paramTicketManager);
  }
  
  public Bundle call()
    throws AccountNotFoundException
  {
    Object localObject = getParameters().getString("com.microsoft.onlineid.user_cid");
    Strings.verifyArgumentNotNullOrEmpty((String)localObject, "com.microsoft.onlineid.user_cid");
    localObject = getAccountManager().getAccountByCid((String)localObject);
    if (localObject == null) {
      throw new AccountNotFoundException("No account was found with the specified ID.");
    }
    return BundleMarshaller.limitedUserAccountToBundle((AuthenticatorUserAccount)localObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\service\operation\GetAccountByIdOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */