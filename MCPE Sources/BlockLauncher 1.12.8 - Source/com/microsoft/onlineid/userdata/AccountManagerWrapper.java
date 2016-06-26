package com.microsoft.onlineid.userdata;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.Context;

public class AccountManagerWrapper
{
  private final AccountManager _accountManager;
  
  public AccountManagerWrapper(Context paramContext)
  {
    this._accountManager = AccountManager.get(paramContext);
  }
  
  public Account[] getAccounts()
  {
    return this._accountManager.getAccounts();
  }
  
  public Account[] getAccountsByType(String paramString)
  {
    return this._accountManager.getAccountsByType(paramString);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\userdata\AccountManagerWrapper.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */