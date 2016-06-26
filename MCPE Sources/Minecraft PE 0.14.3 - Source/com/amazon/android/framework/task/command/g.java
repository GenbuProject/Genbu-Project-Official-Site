package com.amazon.android.framework.task.command;

import android.app.Application;
import android.os.RemoteException;
import com.amazon.venezia.command.z;
import java.util.Map;

final class g
  extends z
{
  g(AbstractCommandTask paramAbstractCommandTask) {}
  
  public final String a()
    throws RemoteException
  {
    return this.a.getCommandName();
  }
  
  public final String b()
    throws RemoteException
  {
    return this.a.getCommandVersion();
  }
  
  public final String c()
    throws RemoteException
  {
    return AbstractCommandTask.a(this.a).getPackageName();
  }
  
  public final Map d()
    throws RemoteException
  {
    return this.a.getCommandData();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\g.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */