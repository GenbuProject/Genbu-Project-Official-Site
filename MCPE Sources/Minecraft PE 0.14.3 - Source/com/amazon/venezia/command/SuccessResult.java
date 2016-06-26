package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

public abstract interface SuccessResult
  extends IInterface
{
  public abstract String getAuthToken()
    throws RemoteException;
  
  public abstract Map getData()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements SuccessResult
  {
    private static final String DESCRIPTOR = "com.amazon.venezia.command.SuccessResult";
    static final int TRANSACTION_getAuthToken = 1;
    static final int TRANSACTION_getData = 2;
    
    public Stub()
    {
      attachInterface(this, "com.amazon.venezia.command.SuccessResult");
    }
    
    public static SuccessResult asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.command.SuccessResult");
      if ((localIInterface != null) && ((localIInterface instanceof SuccessResult))) {
        return (SuccessResult)localIInterface;
      }
      return new Proxy(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
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
    
    private static class Proxy
      implements SuccessResult
    {
      private IBinder mRemote;
      
      Proxy(IBinder paramIBinder)
      {
        this.mRemote = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.mRemote;
      }
      
      public String getAuthToken()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.SuccessResult");
          this.mRemote.transact(1, localParcel1, localParcel2, 0);
          localParcel2.readException();
          String str = localParcel2.readString();
          return str;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public Map getData()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.SuccessResult");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          HashMap localHashMap = localParcel2.readHashMap(getClass().getClassLoader());
          return localHashMap;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.amazon.venezia.command.SuccessResult";
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\SuccessResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */