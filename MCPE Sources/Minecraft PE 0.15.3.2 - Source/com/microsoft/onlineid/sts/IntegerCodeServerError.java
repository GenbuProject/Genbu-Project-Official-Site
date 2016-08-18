package com.microsoft.onlineid.sts;

import java.util.Locale;

public class IntegerCodeServerError
{
  private final int _error;
  private final String _message;
  private final int _subError;
  
  public IntegerCodeServerError(int paramInt)
  {
    this(paramInt, 0, null);
  }
  
  public IntegerCodeServerError(int paramInt1, int paramInt2)
  {
    this(paramInt1, paramInt2, null);
  }
  
  public IntegerCodeServerError(int paramInt1, int paramInt2, String paramString)
  {
    this._error = paramInt1;
    this._subError = paramInt2;
    this._message = paramString;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      return true;
      if ((paramObject == null) || (!(paramObject instanceof IntegerCodeServerError))) {
        break;
      }
      paramObject = (IntegerCodeServerError)paramObject;
    } while ((this._error == ((IntegerCodeServerError)paramObject)._error) && (this._subError == ((IntegerCodeServerError)paramObject)._subError));
    return false;
    return false;
  }
  
  public int getError()
  {
    return this._error;
  }
  
  public int getSubError()
  {
    return this._subError;
  }
  
  public int hashCode()
  {
    return this._error + this._subError;
  }
  
  public String toString()
  {
    return String.format(Locale.US, "Server Error: %s SubError: %s Message: %s", new Object[] { StsErrorCode.getFriendlyHRDescription(this._error), StsErrorCode.getFriendlyHRDescription(this._subError), this._message });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\sts\IntegerCodeServerError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */