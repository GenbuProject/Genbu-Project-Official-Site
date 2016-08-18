package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.ISecurityScope;
import com.microsoft.onlineid.SecurityScope;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import java.io.Serializable;
import java.util.Arrays;
import java.util.Date;

public class DAToken
  implements Serializable
{
  public static final ISecurityScope Scope = new SecurityScope("http://Passport.NET/tb", null);
  private static final long serialVersionUID = 1L;
  private final byte[] _sessionKey;
  private final String _token;
  
  public DAToken(String paramString, byte[] paramArrayOfByte)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString, "token");
    Objects.verifyArgumentNotNull(paramArrayOfByte, "sessionKey");
    this._token = paramString;
    this._sessionKey = paramArrayOfByte;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof DAToken))) {
        break;
      }
      paramObject = (DAToken)paramObject;
    } while ((Objects.equals(this._token, ((DAToken)paramObject)._token)) && (Arrays.equals(this._sessionKey, ((DAToken)paramObject)._sessionKey)));
    return false;
    return false;
  }
  
  public String getOneTimeSignedCredential(Date paramDate, String paramString)
  {
    return new OneTimeCredentialSigner(paramDate, this).generateOneTimeSignedCredential(paramString);
  }
  
  public byte[] getSessionKey()
  {
    return this._sessionKey;
  }
  
  public String getToken()
  {
    return this._token;
  }
  
  public int hashCode()
  {
    return this._token.hashCode() + Arrays.hashCode(this._sessionKey);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\DAToken.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */