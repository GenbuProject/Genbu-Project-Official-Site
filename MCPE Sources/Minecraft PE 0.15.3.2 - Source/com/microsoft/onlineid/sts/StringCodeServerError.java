package com.microsoft.onlineid.sts;

import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.Strings;
import java.util.Locale;

public class StringCodeServerError
{
  private final String _error;
  private final int _subError;
  
  public StringCodeServerError(String paramString, int paramInt)
  {
    Strings.verifyArgumentNotNullOrEmpty(paramString, "error");
    this._error = paramString;
    this._subError = paramInt;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof StringCodeServerError))) {
        break;
      }
      paramObject = (StringCodeServerError)paramObject;
    } while ((Objects.equals(this._error, ((StringCodeServerError)paramObject)._error)) && (this._subError == ((StringCodeServerError)paramObject)._subError));
    return false;
    return false;
  }
  
  public String getError()
  {
    return this._error;
  }
  
  public int getSubError()
  {
    return this._subError;
  }
  
  public int hashCode()
  {
    return Objects.hashCode(this._error) + this._subError;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Server Error: %s SubError: %s", new Object[] { this._error, StsErrorCode.getFriendlyHRDescription(this._subError) });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\StringCodeServerError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */