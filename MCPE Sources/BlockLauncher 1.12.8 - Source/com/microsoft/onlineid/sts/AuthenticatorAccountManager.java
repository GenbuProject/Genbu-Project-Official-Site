package com.microsoft.onlineid.sts;

import android.content.Context;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import java.io.File;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class AuthenticatorAccountManager
{
  private final Context _applicationContext;
  private final TypedStorage _typedStorage;
  
  public AuthenticatorAccountManager(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._typedStorage = new TypedStorage(paramContext);
  }
  
  protected AuthenticatorAccountManager(TypedStorage paramTypedStorage)
  {
    this._applicationContext = null;
    this._typedStorage = paramTypedStorage;
  }
  
  public AuthenticatorUserAccount getAccountByCid(String paramString)
  {
    Iterator localIterator = this._typedStorage.readAllAccounts().iterator();
    while (localIterator.hasNext())
    {
      AuthenticatorUserAccount localAuthenticatorUserAccount = (AuthenticatorUserAccount)localIterator.next();
      if (paramString.equalsIgnoreCase(localAuthenticatorUserAccount.getCid())) {
        return localAuthenticatorUserAccount;
      }
    }
    return null;
  }
  
  public AuthenticatorUserAccount getAccountByPuid(String paramString)
  {
    return this._typedStorage.readAccount(paramString);
  }
  
  public Set<AuthenticatorUserAccount> getAccounts()
  {
    return this._typedStorage.readAllAccounts();
  }
  
  public Set<AuthenticatorUserAccount> getFilteredAccounts(Set<String> paramSet)
  {
    Set localSet = this._typedStorage.readAllAccounts();
    if ((paramSet == null) || (paramSet.isEmpty())) {}
    for (;;)
    {
      return localSet;
      Iterator localIterator = localSet.iterator();
      while (localIterator.hasNext()) {
        if (paramSet.contains(((AuthenticatorUserAccount)localIterator.next()).getCid())) {
          localIterator.remove();
        }
      }
    }
  }
  
  public Set<AuthenticatorUserAccount> getSessionApprovalAccounts()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = getAccounts().iterator();
    while (localIterator.hasNext())
    {
      AuthenticatorUserAccount localAuthenticatorUserAccount = (AuthenticatorUserAccount)localIterator.next();
      if (localAuthenticatorUserAccount.isSessionApprover()) {
        localHashSet.add(localAuthenticatorUserAccount);
      }
    }
    return localHashSet;
  }
  
  public boolean hasAccounts()
  {
    return this._typedStorage.hasAccounts();
  }
  
  public boolean hasNgcSessionApprovalAccounts()
  {
    Iterator localIterator = getAccounts().iterator();
    while (localIterator.hasNext()) {
      if (((AuthenticatorUserAccount)localIterator.next()).hasNgcRegistrationSucceeded()) {
        return true;
      }
    }
    return false;
  }
  
  public boolean hasSessionApprovalAccounts()
  {
    Iterator localIterator = getAccounts().iterator();
    while (localIterator.hasNext()) {
      if (((AuthenticatorUserAccount)localIterator.next()).isSessionApprover()) {
        return true;
      }
    }
    return false;
  }
  
  void removeLastSavedUserTileImage(AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    paramAuthenticatorUserAccount = this._applicationContext.getFileStreamPath(paramAuthenticatorUserAccount.getPuid() + ".png");
    if (paramAuthenticatorUserAccount.exists()) {
      paramAuthenticatorUserAccount.delete();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\sts\AuthenticatorAccountManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */