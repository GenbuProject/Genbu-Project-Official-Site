package com.amazon.venezia.command;

import android.os.Binder;
import android.os.Parcel;
import android.os.RemoteException;

public abstract class e
  extends Binder
  implements SuccessResult
{
  public e()
  {
    attachInterface(this, "com.amazon.venezia.command.SuccessResult");
  }
  
  public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
    throws RemoteException
  {
    switch (paramInt1)
    {
    default: 
      return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
    case 1598968902: 
      paramParcel2.writeString("com.amazon.venezia.command.SuccessResult");
      return true;
    case 1: 
      paramParcel1.enforceInterface("com.amazon.venezia.command.SuccessResult");
      paramParcel1 = getAuthToken();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }
    paramParcel1.enforceInterface("com.amazon.venezia.command.SuccessResult");
    paramParcel1 = getData();
    paramParcel2.writeNoException();
    paramParcel2.writeMap(paramParcel1);
    return true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\e.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */