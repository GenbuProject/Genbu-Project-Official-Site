package com.microsoft.onlineid.internal.sso;

import android.util.SparseArray;

public enum SsoServiceError
{
  private static final SparseArray<SsoServiceError> _lookup;
  private int _code;
  
  static
  {
    int i = 0;
    Unknown = new SsoServiceError("Unknown", 0, 1);
    ClientNotAuthorized = new SsoServiceError("ClientNotAuthorized", 1, 2);
    UnsupportedClientVersion = new SsoServiceError("UnsupportedClientVersion", 2, 3);
    StorageException = new SsoServiceError("StorageException", 3, 4);
    IllegalArgumentException = new SsoServiceError("IllegalArgumentException", 4, 5);
    AccountNotFound = new SsoServiceError("AccountNotFound", 5, 6);
    NetworkException = new SsoServiceError("NetworkException", 6, 7);
    StsException = new SsoServiceError("StsException", 7, 8);
    InvalidResponseException = new SsoServiceError("InvalidResponseException", 8, 9);
    MasterRedirectException = new SsoServiceError("MasterRedirectException", 9, 10);
    ClientConfigUpdateNeededException = new SsoServiceError("ClientConfigUpdateNeededException", 10, 11);
    $VALUES = new SsoServiceError[] { Unknown, ClientNotAuthorized, UnsupportedClientVersion, StorageException, IllegalArgumentException, AccountNotFound, NetworkException, StsException, InvalidResponseException, MasterRedirectException, ClientConfigUpdateNeededException };
    _lookup = new SparseArray();
    SsoServiceError[] arrayOfSsoServiceError = values();
    int j = arrayOfSsoServiceError.length;
    while (i < j)
    {
      SsoServiceError localSsoServiceError = arrayOfSsoServiceError[i];
      _lookup.put(localSsoServiceError.getCode(), localSsoServiceError);
      i += 1;
    }
  }
  
  private SsoServiceError(int paramInt)
  {
    this._code = paramInt;
  }
  
  public static SsoServiceError get(int paramInt)
  {
    return (SsoServiceError)_lookup.get(paramInt);
  }
  
  public int getCode()
  {
    return this._code;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\sso\SsoServiceError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */