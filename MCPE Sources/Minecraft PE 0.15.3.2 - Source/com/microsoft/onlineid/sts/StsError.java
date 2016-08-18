package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.internal.Objects;
import java.util.Locale;

public class StsError
{
  private final StsErrorCode _code;
  private final String _logMessage;
  private final String _originalErrorMessage;
  
  public StsError(IntegerCodeServerError paramIntegerCodeServerError)
  {
    Objects.verifyArgumentNotNull(paramIntegerCodeServerError, "error");
    this._code = StsErrorCode.convertServerError(paramIntegerCodeServerError);
    this._originalErrorMessage = paramIntegerCodeServerError.toString();
    this._logMessage = String.format(Locale.US, "%s error caused by server error:\n%s", new Object[] { this._code.name(), this._originalErrorMessage });
  }
  
  public StsError(StringCodeServerError paramStringCodeServerError)
  {
    Objects.verifyArgumentNotNull(paramStringCodeServerError, "error");
    this._code = StsErrorCode.convertServerError(paramStringCodeServerError);
    this._originalErrorMessage = paramStringCodeServerError.toString();
    this._logMessage = String.format(Locale.US, "%s error caused by server error:\n%s", new Object[] { this._code.name(), this._originalErrorMessage });
  }
  
  public StsError(StsErrorCode paramStsErrorCode)
  {
    Objects.verifyArgumentNotNull(paramStsErrorCode, "code");
    this._code = paramStsErrorCode;
    this._originalErrorMessage = paramStsErrorCode.name();
    this._logMessage = String.format(Locale.US, "%s error.", new Object[] { this._originalErrorMessage });
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if (((paramObject instanceof StsError)) && (paramObject != null))
    {
      paramObject = (StsError)paramObject;
      return Objects.equals(this._code, ((StsError)paramObject)._code);
    }
    if (((paramObject instanceof StsErrorCode)) && (paramObject != null))
    {
      paramObject = (StsErrorCode)paramObject;
      return Objects.equals(this._code, paramObject);
    }
    return false;
  }
  
  public StsErrorCode getCode()
  {
    return this._code;
  }
  
  public String getMessage()
  {
    return this._logMessage;
  }
  
  public String getOriginalErrorMessage()
  {
    return this._originalErrorMessage;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this._code);
  }
  
  public boolean isInvalidSessionError()
  {
    switch (this._code)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public boolean isNgcKeyNotFoundError()
  {
    return this._code == StsErrorCode.PP_E_NGC_LOGIN_KEY_NOT_FOUND;
  }
  
  public boolean isRetryableDeviceDAErrorForDeviceAuth()
  {
    switch (this._code)
    {
    default: 
      return false;
    }
    return true;
  }
  
  public boolean isRetryableDeviceDAErrorForUserAuth()
  {
    switch (this._code)
    {
    default: 
      return false;
    }
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\StsError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */