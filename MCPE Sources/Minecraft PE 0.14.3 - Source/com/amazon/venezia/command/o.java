package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class o
  extends Binder
  implements f
{
  public o()
  {
    attachInterface(this, "com.amazon.venezia.command.ResultCallback");
  }
  
  public IBinder asBinder()
  {
    return this;
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    IInterface localIInterface;
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.amazon.venezia.command.ResultCallback");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.ResultCallback");
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 == null) {
        paramParcel1 = null;
      }
      for (;;)
      {
        a(paramParcel1);
        paramParcel2.writeNoException();
        return true;
        localIInterface = paramParcel1.queryLocalInterface("com.amazon.venezia.command.SuccessResult");
        if ((localIInterface != null) && ((localIInterface instanceof SuccessResult))) {
          paramParcel1 = (SuccessResult)localIInterface;
        } else {
          paramParcel1 = new x(paramParcel1);
        }
      }
    case 2: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.ResultCallback");
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 == null) {
        paramParcel1 = null;
      }
      for (;;)
      {
        a(paramParcel1);
        paramParcel2.writeNoException();
        return true;
        localIInterface = paramParcel1.queryLocalInterface("com.amazon.venezia.command.FailureResult");
        if ((localIInterface != null) && ((localIInterface instanceof FailureResult))) {
          paramParcel1 = (FailureResult)localIInterface;
        } else {
          paramParcel1 = new ab(paramParcel1);
        }
      }
    case 3: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.ResultCallback");
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 == null) {
        paramParcel1 = null;
      }
      for (;;)
      {
        a(paramParcel1);
        paramParcel2.writeNoException();
        return true;
        localIInterface = paramParcel1.queryLocalInterface("com.amazon.venezia.command.DecisionResult");
        if ((localIInterface != null) && ((localIInterface instanceof r))) {
          paramParcel1 = (r)localIInterface;
        } else {
          paramParcel1 = new aa(paramParcel1);
        }
      }
    }
    paramParcel1.enforceInterface("com.amazon.venezia.command.ResultCallback");
    paramParcel1 = paramParcel1.readStrongBinder();
    if (paramParcel1 == null) {
      paramParcel1 = null;
    }
    for (;;)
    {
      a(paramParcel1);
      paramParcel2.writeNoException();
      return true;
      localIInterface = paramParcel1.queryLocalInterface("com.amazon.venezia.command.ExceptionResult");
      if ((localIInterface != null) && ((localIInterface instanceof j))) {
        paramParcel1 = (j)localIInterface;
      } else {
        paramParcel1 = new v(paramParcel1);
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\o.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */