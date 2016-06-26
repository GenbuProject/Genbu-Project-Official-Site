package com.microsoft.onlineid.sts.exception;

import com.microsoft.onlineid.exception.InternalException;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.sts.StsError;
import com.microsoft.onlineid.sts.StsErrorCode;

public class StsException
  extends InternalException
{
  private static final long serialVersionUID = 1L;
  private final StsError _stsError;
  
  public StsException(String paramString, StsError paramStsError)
  {
    super(paramString + ": " + paramStsError.getMessage());
    Objects.verifyArgumentNotNull(paramStsError, "error");
    this._stsError = paramStsError;
  }
  
  public StsErrorCode getCode()
  {
    return this._stsError.getCode();
  }
  
  public StsError getError()
  {
    return this._stsError;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\sts\exception\StsException.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */