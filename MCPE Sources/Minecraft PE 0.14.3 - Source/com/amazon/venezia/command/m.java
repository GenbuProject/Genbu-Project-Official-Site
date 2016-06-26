package com.amazon.venezia.command;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class m
  extends Binder
  implements n
{
  public m()
  {
    attachInterface(this, "com.amazon.venezia.command.Choice");
  }
  
  public static n a(IBinder paramIBinder)
  {
    if (paramIBinder == null) {
      return null;
    }
    IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.command.Choice");
    if ((localIInterface != null) && ((localIInterface instanceof n))) {
      return (n)localIInterface;
    }
    return new a(paramIBinder);
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.amazon.venezia.command.Choice");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.Choice");
      paramParcel1 = a();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    case 2: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.Choice");
      paramParcel1 = b();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        return true;
        paramParcel2.writeInt(0);
      }
    case 3: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.Choice");
      paramParcel1 = paramParcel1.readStrongBinder();
      if (paramParcel1 == null) {
        paramParcel1 = null;
      }
      for (;;)
      {
        a(paramParcel1);
        paramParcel2.writeNoException();
        return true;
        IInterface localIInterface = paramParcel1.queryLocalInterface("com.amazon.venezia.command.ChoiceContext");
        if ((localIInterface != null) && ((localIInterface instanceof y))) {
          paramParcel1 = (y)localIInterface;
        } else {
          paramParcel1 = new k(paramParcel1);
        }
      }
    }
    paramParcel1.enforceInterface("com.amazon.venezia.command.Choice");
    paramParcel1 = c();
    paramParcel2.writeNoException();
    paramParcel2.writeMap(paramParcel1);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\m.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */