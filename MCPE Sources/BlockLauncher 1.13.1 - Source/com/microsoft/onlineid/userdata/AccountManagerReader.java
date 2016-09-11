package com.microsoft.onlineid.userdata;

import android.accounts.Account;
import android.content.Context;
import android.util.Patterns;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import org.json.JSONArray;

public class AccountManagerReader
{
  private final AccountManagerWrapper _accountManager;
  
  public AccountManagerReader(Context paramContext)
  {
    this(new AccountManagerWrapper(paramContext));
  }
  
  AccountManagerReader(AccountManagerWrapper paramAccountManagerWrapper)
  {
    this._accountManager = paramAccountManagerWrapper;
  }
  
  public String getDeviceEmail()
  {
    Account[] arrayOfAccount = this._accountManager.getAccountsByType("com.google");
    IClientAnalytics localIClientAnalytics = ClientAnalytics.get();
    if (arrayOfAccount.length == 0) {}
    for (String str = "Does not exist in Account Manager";; str = "Exists in Account Manager")
    {
      localIClientAnalytics.logEvent("User data", "Google email", str);
      if (arrayOfAccount.length != 0) {
        break;
      }
      return null;
    }
    ClientAnalytics.get().logEvent("User data", "Google email count", "Exists in Account Manager", Long.valueOf(arrayOfAccount.length));
    return arrayOfAccount[0].name;
  }
  
  public Set<String> getEmails()
  {
    HashSet localHashSet = new HashSet();
    Account[] arrayOfAccount = this._accountManager.getAccounts();
    int j = arrayOfAccount.length;
    int i = 0;
    while (i < j)
    {
      Account localAccount = arrayOfAccount[i];
      if (Patterns.EMAIL_ADDRESS.matcher(localAccount.name).matches()) {
        localHashSet.add(localAccount.name);
      }
      i += 1;
    }
    ClientAnalytics.get().logEvent("User data", "Unique email count", "Exists in Account Manager", Long.valueOf(localHashSet.size()));
    return localHashSet;
  }
  
  public String getEmailsAsJsonArray()
  {
    Object localObject = getEmails();
    JSONArray localJSONArray = new JSONArray();
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext()) {
      localJSONArray.put((String)((Iterator)localObject).next());
    }
    return localJSONArray.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\userdata\AccountManagerReader.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */