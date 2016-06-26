package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class c
  extends Binder
  implements r
{
  public c()
  {
    attachInterface(this, "com.amazon.venezia.command.DecisionResult");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.amazon.venezia.command.DecisionResult");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = a();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 2: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = b();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 3: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = c();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 4: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      long l = d();
      paramParcel2.writeNoException();
      paramParcel2.writeLong(l);
      return true;
    case 5: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 == null) {
        paramParcel1 = null;
      }
      for (;;)
      {
        a(paramParcel1);
        paramParcel2.writeNoException();
        return true;
        IInterface localIInterface = paramParcel1.queryLocalInterface("com.amazon.venezia.command.DecisionExpirationContext");
        if ((localIInterface != null) && ((localIInterface instanceof s))) {
          paramParcel1 = (s)localIInterface;
        } else {
          paramParcel1 = new u(paramParcel1);
        }
      }
    case 6: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = e();
      paramParcel2.writeNoException();
      if (paramParcel1 != null) {}
      for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
      {
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      }
    case 7: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = f();
      paramParcel2.writeNoException();
      if (paramParcel1 != null) {}
      for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
      {
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      }
    case 8: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = g();
      paramParcel2.writeNoException();
      if (paramParcel1 != null) {}
      for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
      {
        paramParcel2.writeStrongBinder(paramParcel1);
        return true;
      }
    }
    paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
    paramParcel1 = h();
    paramParcel2.writeNoException();
    paramParcel2.writeMap(paramParcel1);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\c.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */