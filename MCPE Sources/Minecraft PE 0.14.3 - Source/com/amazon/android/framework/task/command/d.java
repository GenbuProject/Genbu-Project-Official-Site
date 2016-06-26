package com.amazon.android.framework.task.command;

import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import com.amazon.android.framework.util.KiwiLogger;
import java.util.concurrent.BlockingQueue;

final class d
  implements DialogInterface.OnClickListener
{
  d(c paramc, m paramm) {}
  
  public final void onClick(DialogInterface paramDialogInterface, int paramInt)
  {
    if (KiwiLogger.TRACE_ON) {
      c.b().trace("Choice selected!");
    }
    if (c.a(this.b)) {
      c.b(this.b).add(this.a);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\d.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */