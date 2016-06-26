package com.amazon.android.framework.task.command;

import android.content.ComponentName;
import android.content.ServiceConnection;
import android.os.IBinder;
import com.amazon.android.framework.util.KiwiLogger;
import com.amazon.venezia.command.q;
import java.util.concurrent.BlockingQueue;

final class i
  implements ServiceConnection
{
  i(b paramb) {}
  
  public final void onServiceConnected(ComponentName paramComponentName, IBinder paramIBinder)
  {
    b.c().trace("onServiceConnected");
    b.a(this.a).add(q.a(paramIBinder));
  }
  
  public final void onServiceDisconnected(ComponentName paramComponentName)
  {
    b.c().trace("onServiceDisconnected!!!");
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\i.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */