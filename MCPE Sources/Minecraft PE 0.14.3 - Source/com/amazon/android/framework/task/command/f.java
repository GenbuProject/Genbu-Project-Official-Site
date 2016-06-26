package com.amazon.android.framework.task.command;

import android.os.RemoteException;
import com.amazon.venezia.command.n;
import com.amazon.venezia.command.r;

final class f
{
  final String a;
  final String b;
  final long c;
  final m d;
  final m e;
  final m f;
  
  public f(r paramr)
    throws RemoteException
  {
    this.a = paramr.b();
    this.b = paramr.c();
    this.c = paramr.d();
    this.d = a(paramr.e());
    this.e = a(paramr.f());
    this.f = a(paramr.g());
  }
  
  private static m a(n paramn)
    throws RemoteException
  {
    if (paramn == null) {
      return null;
    }
    return new m(paramn);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\android\framework\task\command\f.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */