package com.amazon.android.framework.task.command;

import android.os.Binder;
import android.os.RemoteException;
import com.amazon.venezia.command.FailureResult;
import com.amazon.venezia.command.SuccessResult;
import com.amazon.venezia.command.j;
import com.amazon.venezia.command.r;

public final class l
{
  String a;
  RemoteException b;
  SuccessResult c;
  FailureResult d;
  r e;
  j f;
  private int g;
  
  public l(FailureResult paramFailureResult)
  {
    this.d = paramFailureResult;
    this.g = Binder.getCallingUid();
    try
    {
      this.a = paramFailureResult.getAuthToken();
      return;
    }
    catch (RemoteException paramFailureResult)
    {
      this.b = paramFailureResult;
    }
  }
  
  public l(SuccessResult paramSuccessResult)
  {
    this.c = paramSuccessResult;
    this.g = Binder.getCallingUid();
    try
    {
      this.a = paramSuccessResult.getAuthToken();
      return;
    }
    catch (RemoteException paramSuccessResult)
    {
      this.b = paramSuccessResult;
    }
  }
  
  public l(j paramj)
  {
    this.f = paramj;
    this.g = Binder.getCallingUid();
  }
  
  public l(r paramr)
  {
    this.e = paramr;
    this.g = Binder.getCallingUid();
    try
    {
      this.a = paramr.a();
      return;
    }
    catch (RemoteException paramr)
    {
      this.b = paramr;
    }
  }
  
  public final String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("CommandResult: [");
    localStringBuilder.append("CallingUid: ").append(this.g).append(", SuccessResult: ").append(this.c).append(", FailureResult: ").append(this.d).append(", DecisionResult: ").append(this.e).append(", ExceptionResult: ").append(this.f).append("]");
    return localStringBuilder.toString();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */