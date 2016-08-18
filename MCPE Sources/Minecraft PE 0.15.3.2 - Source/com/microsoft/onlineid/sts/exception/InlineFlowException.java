package com.microsoft.onlineid.sts.exception;

import com.microsoft.onlineid.exception.AuthenticationException;
import java.util.Locale;

public class InlineFlowException
  extends AuthenticationException
{
  private static final long serialVersionUID = 1L;
  private final String _errorCode;
  private final String _errorUrl;
  private final String _extendedErrorString;
  
  public InlineFlowException(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    super(paramString1);
    this._errorUrl = paramString2;
    this._errorCode = paramString3;
    this._extendedErrorString = paramString4;
  }
  
  public String getErrorCode()
  {
    return this._errorCode;
  }
  
  public String getErrorUrl()
  {
    return this._errorUrl;
  }
  
  public String getExtendedErrorString()
  {
    return this._extendedErrorString;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Inline flow error to be resolved at '%s': %s (code = %s, extended = %s)", new Object[] { this._errorUrl, getMessage(), this._errorCode, this._extendedErrorString });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\exception\InlineFlowException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */