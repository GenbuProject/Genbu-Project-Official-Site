package com.amazon.android.framework.task.command;

import android.os.RemoteException;
import com.amazon.venezia.command.FailureResult;
import com.amazon.venezia.command.SuccessResult;
import com.amazon.venezia.command.o;
import com.amazon.venezia.command.r;
import java.util.concurrent.BlockingQueue;

final class j
  extends o
{
  j(b paramb) {}
  
  public final void a(FailureResult paramFailureResult)
    throws RemoteException
  {
    b.b(this.a).add(new l(paramFailureResult));
  }
  
  public final void a(SuccessResult paramSuccessResult)
    throws RemoteException
  {
    b.b(this.a).add(new l(paramSuccessResult));
  }
  
  public final void a(com.amazon.venezia.command.j paramj)
    throws RemoteException
  {
    b.b(this.a).add(new l(paramj));
  }
  
  public final void a(r paramr)
    throws RemoteException
  {
    b.b(this.a).add(new l(paramr));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\j.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */