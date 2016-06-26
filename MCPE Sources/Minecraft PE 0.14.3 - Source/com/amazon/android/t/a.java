package com.amazon.android.t;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.android.framework.task.command.AbstractCommandTask;
import com.amazon.venezia.command.FailureResult;
import com.amazon.venezia.command.SuccessResult;
import java.util.Map;

public final class a
  extends AbstractCommandTask
{
  private Map a;
  
  public a(Map paramMap)
  {
    this.a = paramMap;
  }
  
  protected final Map getCommandData()
  {
    return this.a;
  }
  
  protected final String getCommandName()
  {
    return "lifeCycle_Events";
  }
  
  protected final String getCommandVersion()
  {
    return "1.0";
  }
  
  protected final boolean isExecutionNeeded()
  {
    return true;
  }
  
  protected final void onFailure(FailureResult paramFailureResult)
    throws RemoteException, KiwiException
  {}
  
  protected final void onSuccess(SuccessResult paramSuccessResult)
    throws RemoteException, KiwiException
  {}
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\t\a.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */