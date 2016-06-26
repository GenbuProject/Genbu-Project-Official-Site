package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.Set;

public class GetAllAccountsOperation
  extends ServiceOperation
{
  public GetAllAccountsOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager)
  {
    super(paramContext, paramBundle, paramAuthenticatorAccountManager, paramTicketManager);
  }
  
  public Bundle call()
  {
    ArrayList localArrayList = new ArrayList();
    Object localObject = getAccountManager().getAccounts().iterator();
    while (((Iterator)localObject).hasNext()) {
      localArrayList.add(BundleMarshaller.limitedUserAccountToBundle((AuthenticatorUserAccount)((Iterator)localObject).next()));
    }
    localObject = new Bundle();
    ((Bundle)localObject).putParcelableArrayList("com.microsoft.onlineid.all_users", localArrayList);
    return (Bundle)localObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\sso\service\operation\GetAllAccountsOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */