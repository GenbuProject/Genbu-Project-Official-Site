package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class q
  extends Binder
  implements h
{
  public q()
  {
    attachInterface(this, "com.amazon.venezia.command.CommandService");
  }
  
  public static h a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.command.CommandService");
    if ((localIInterface != null) && ((localIInterface instanceof h))) {
      return (h)localIInterface;
    }
    return new l(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.amazon.venezia.command.CommandService");
      return true;
    }
    paramParcel1.enforceInterface("com.amazon.venezia.command.CommandService");
    Object localObject = paramParcel1.readStrongBinder();
    if (localObject == null)
    {
      localObject = null;
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 != null) {
        break label138;
      }
      paramParcel1 = null;
    }
    for (;;)
    {
      a((w)localObject, paramParcel1);
      paramParcel2.writeNoException();
      return true;
      IInterface localIInterface = ((IBinder)localObject).queryLocalInterface("com.amazon.venezia.command.Command");
      if ((localIInterface != null) && ((localIInterface instanceof w)))
      {
        localObject = (w)localIInterface;
        break;
      }
      localObject = new d((IBinder)localObject);
      break;
      label138:
      localIInterface = paramParcel1.queryLocalInterface("com.amazon.venezia.command.ResultCallback");
      if ((localIInterface != null) && ((localIInterface instanceof f))) {
        paramParcel1 = (f)localIInterface;
      } else {
        paramParcel1 = new t(paramParcel1);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\q.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */