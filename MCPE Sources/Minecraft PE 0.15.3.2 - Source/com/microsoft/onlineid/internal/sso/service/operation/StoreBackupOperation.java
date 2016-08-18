package com.microsoft.onlineid.internal.sso.service.operation;

import android.content.Context;
import android.os.Bundle;
import com.microsoft.onlineid.internal.sso.BundleMarshallerException;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.internal.sts.TicketManager;
import com.microsoft.onlineid.sts.AuthenticatorAccountManager;

public class StoreBackupOperation
  extends ServiceOperation
{
  private final TypedStorage _storage;
  
  public StoreBackupOperation(Context paramContext, Bundle paramBundle, AuthenticatorAccountManager paramAuthenticatorAccountManager, TicketManager paramTicketManager, TypedStorage paramTypedStorage)
  {
    super(paramContext, paramBundle, paramAuthenticatorAccountManager, paramTicketManager);
    this._storage = paramTypedStorage;
  }
  
  public Bundle call()
    throws BundleMarshallerException
  {
    this._storage.storeBackup(getParameters());
    return new Bundle();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\sso\service\operation\StoreBackupOperation.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */