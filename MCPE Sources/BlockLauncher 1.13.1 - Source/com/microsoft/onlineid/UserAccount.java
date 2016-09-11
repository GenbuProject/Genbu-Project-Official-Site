package com.microsoft.onlineid;

import android.os.Bundle;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.sts.AuthenticatorUserAccount;

public class UserAccount
{
  private final AccountManager _accountManager;
  private final String _cid;
  private final String _puid;
  private final String _username;
  
  UserAccount(AccountManager paramAccountManager, AuthenticatorUserAccount paramAuthenticatorUserAccount)
  {
    this(paramAccountManager, paramAuthenticatorUserAccount.getCid(), paramAuthenticatorUserAccount.getPuid(), paramAuthenticatorUserAccount.getUsername());
  }
  
  UserAccount(AccountManager paramAccountManager, String paramString1, String paramString2, String paramString3)
  {
    this._accountManager = paramAccountManager;
    this._cid = paramString1;
    this._puid = paramString2;
    this._username = paramString3;
  }
  
  public boolean equals(Object paramObject)
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (paramObject != null)
    {
      bool1 = bool2;
      if ((paramObject instanceof UserAccount))
      {
        paramObject = (UserAccount)paramObject;
        bool1 = bool2;
        if (Objects.equals(this._puid, ((UserAccount)paramObject)._puid))
        {
          bool1 = bool2;
          if (Objects.equals(this._cid, ((UserAccount)paramObject)._cid)) {
            bool1 = true;
          }
        }
      }
    }
    return bool1;
  }
  
  public String getCid()
  {
    return this._cid;
  }
  
  String getPuid()
  {
    return this._puid;
  }
  
  public void getTicket(ISecurityScope paramISecurityScope, Bundle paramBundle)
  {
    this._accountManager.getTicket(this, paramISecurityScope, paramBundle);
  }
  
  public String getUsername()
  {
    return this._username;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this._puid) + Objects.hashCode(this._cid);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\UserAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */