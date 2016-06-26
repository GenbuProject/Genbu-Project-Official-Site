package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

public abstract interface DecisionExpirationContext
  extends IInterface
{
  public abstract Map getExtensionData()
    throws RemoteException;
  
  public abstract String getReason()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements DecisionExpirationContext
  {
    private static final String DESCRIPTOR = "com.amazon.venezia.command.DecisionExpirationContext";
    static final int TRANSACTION_getExtensionData = 2;
    static final int TRANSACTION_getReason = 1;
    
    public Stub()
    {
      attachInterface(this, "com.amazon.venezia.command.DecisionExpirationContext");
    }
    
    public static DecisionExpirationContext asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.command.DecisionExpirationContext");
      if ((localIInterface != null) && ((localIInterface instanceof DecisionExpirationContext))) {
        return (DecisionExpirationContext)localIInterface;
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
        paramParcel2.writeString("com.amazon.venezia.command.DecisionExpirationContext");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionExpirationContext");
        paramParcel1 = getReason();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      }
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionExpirationContext");
      paramParcel1 = getExtensionData();
      paramParcel2.writeNoException();
      paramParcel2.writeMap(paramParcel1);
      return true;
    }
    
    private static class Proxy
      implements DecisionExpirationContext
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
      
      public Map getExtensionData()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionExpirationContext");
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
        return "com.amazon.venezia.command.DecisionExpirationContext";
      }
      
      public String getReason()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionExpirationContext");
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
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\DecisionExpirationContext.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */