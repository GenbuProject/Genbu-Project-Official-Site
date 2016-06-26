package com.amazon.android.b;

import android.os.RemoteException;
import com.amazon.android.framework.exception.KiwiException;
import com.amazon.venezia.command.j;

public class e
  extends KiwiException
{
  private static final long serialVersionUID = 1L;
  
  public e(j paramj)
    throws RemoteException
  {
    super("EXCEPTION_RESULT_FAILURE", paramj.a());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\b\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */