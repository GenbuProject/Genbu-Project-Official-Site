package com.microsoft.onlineid.sts;

import android.text.TextUtils;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.Set;

public class AuthenticatorUserAccount
  implements Serializable
{
  private static final long serialVersionUID = 1L;
  private final String _cid;
  private String _displayName;
  private Set<Integer> _flights;
  private String _gcmRegistrationID;
  private boolean _isSessionApprover;
  private boolean _isSessionApproverRegistrationNeeded;
  private final String _puid;
  private String _serverKeyIdentifier;
  private long _timeOfLastProfileUpdate;
  private DAToken _token;
  private byte[] _totpKey;
  private String _username;
  
  public AuthenticatorUserAccount(String paramString1, String paramString2, String paramString3, DAToken paramDAToken)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString3, "username");
    this._puid = paramString1;
    this._cid = paramString2;
    this._isSessionApprover = false;
    this._isSessionApproverRegistrationNeeded = true;
    this._username = paramString3;
    this._token = paramDAToken;
    this._totpKey = null;
    this._serverKeyIdentifier = null;
    this._flights = new HashSet();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof AuthenticatorUserAccount))) {
        break;
      }
      paramObject = (AuthenticatorUserAccount)paramObject;
    } while ((Objects.equals(this._puid, ((AuthenticatorUserAccount)paramObject)._puid)) && (this._isSessionApprover == ((AuthenticatorUserAccount)paramObject)._isSessionApprover) && (Objects.equals(this._username, ((AuthenticatorUserAccount)paramObject)._username)) && (Objects.equals(this._token, ((AuthenticatorUserAccount)paramObject)._token)) && (Arrays.equals(this._totpKey, ((AuthenticatorUserAccount)paramObject)._totpKey)));
    return false;
    return false;
  }
  
  public String getCid()
  {
    return this._cid;
  }
  
  public DAToken getDAToken()
  {
    return this._token;
  }
  
  public String getDisplayName()
  {
    return this._displayName;
  }
  
  public Set<Integer> getFlights()
  {
    if (this._flights != null) {
      return this._flights;
    }
    return Collections.emptySet();
  }
  
  public String getGcmRegistrationID()
  {
    return this._gcmRegistrationID;
  }
  
  public String getPuid()
  {
    return this._puid;
  }
  
  public String getServerKeyIdentifier()
  {
    return this._serverKeyIdentifier;
  }
  
  public long getTimeOfLastProfileUpdate()
  {
    return this._timeOfLastProfileUpdate;
  }
  
  public byte[] getTotpKey()
  {
    return this._totpKey;
  }
  
  public String getUsername()
  {
    return this._username;
  }
  
  public boolean hasNgcRegistrationSucceeded()
  {
    return this._serverKeyIdentifier != null;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this._puid);
  }
  
  public boolean isNewAndInOutOfBandInterrupt()
  {
    return TextUtils.isEmpty(this._puid);
  }
  
  public boolean isSessionApprover()
  {
    return this._isSessionApprover;
  }
  
  public boolean isSessionApproverRegistrationNeeded()
  {
    return this._isSessionApproverRegistrationNeeded;
  }
  
  public void setDAToken(DAToken paramDAToken)
  {
    Objects.verifyArgumentNotNull(paramDAToken, "token");
    this._token = paramDAToken;
  }
  
  public void setDisplayName(String paramString)
  {
    this._displayName = paramString;
  }
  
  public void setFlights(Set<Integer> paramSet)
  {
    this._flights = paramSet;
  }
  
  public void setGcmRegistrationID(String paramString)
  {
    this._gcmRegistrationID = paramString;
  }
  
  public void setIsSessionApprover(boolean paramBoolean)
  {
    this._isSessionApprover = paramBoolean;
  }
  
  public void setIsSessionApproverRegistrationNeeded(boolean paramBoolean)
  {
    this._isSessionApproverRegistrationNeeded = paramBoolean;
  }
  
  public void setServerKeyIdentifier(String paramString)
  {
    this._serverKeyIdentifier = paramString;
  }
  
  public void setTimeOfLastProfileUpdate(long paramLong)
  {
    this._timeOfLastProfileUpdate = paramLong;
  }
  
  public void setTotpKey(byte[] paramArrayOfByte)
  {
    this._totpKey = paramArrayOfByte;
  }
  
  public void setUsername(String paramString)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString, "username");
    this._username = paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\AuthenticatorUserAccount.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */