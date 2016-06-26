package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

public abstract interface FailureResult
  extends IInterface
{
  public abstract String getAuthToken()
    throws RemoteException;
  
  public abstract String getButtonLabel()
    throws RemoteException;
  
  public abstract String getDisplayableMessage()
    throws RemoteException;
  
  public abstract String getDisplayableName()
    throws RemoteException;
  
  public abstract Map getExtensionData()
    throws RemoteException;
  
  public abstract boolean show()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements FailureResult
  {
    private static final String DESCRIPTOR = "com.amazon.venezia.command.FailureResult";
    static final int TRANSACTION_getAuthToken = 1;
    static final int TRANSACTION_getButtonLabel = 4;
    static final int TRANSACTION_getDisplayableMessage = 3;
    static final int TRANSACTION_getDisplayableName = 2;
    static final int TRANSACTION_getExtensionData = 6;
    static final int TRANSACTION_show = 5;
    
    public Stub()
    {
      attachInterface(this, "com.amazon.venezia.command.FailureResult");
    }
    
    public static FailureResult asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.command.FailureResult");
      if ((localIInterface != null) && ((localIInterface instanceof FailureResult))) {
        return (FailureResult)localIInterface;
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
        paramParcel2.writeString("com.amazon.venezia.command.FailureResult");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.FailureResult");
        paramParcel1 = getAuthToken();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.FailureResult");
        paramParcel1 = getDisplayableName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.FailureResult");
        paramParcel1 = getDisplayableMessage();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.FailureResult");
        paramParcel1 = getButtonLabel();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.FailureResult");
        boolean bool = show();
        paramParcel2.writeNoException();
        if (bool) {}
        for (paramInt1 = 1;; paramInt1 = 0)
        {
          paramParcel2.writeInt(paramInt1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.amazon.venezia.command.FailureResult");
      paramParcel1 = getExtensionData();
      paramParcel2.writeNoException();
      paramParcel2.writeMap(paramParcel1);
      return true;
    }
    
    private static class Proxy
      implements FailureResult
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
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
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
      
      public String getButtonLabel()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
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
      
      public String getDisplayableMessage()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
          this.mRemote.transact(3, localParcel1, localParcel2, 0);
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
      
      public String getDisplayableName()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
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
      
      public Map getExtensionData()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
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
        return "com.amazon.venezia.command.FailureResult";
      }
      
      /* Error */
      public boolean show()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_3
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore 4
        //   9: aload_3
        //   10: ldc 34
        //   12: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_0
        //   16: getfield 19	com/amazon/venezia/command/FailureResult$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   19: iconst_5
        //   20: aload_3
        //   21: aload 4
        //   23: iconst_0
        //   24: invokeinterface 44 5 0
        //   29: pop
        //   30: aload 4
        //   32: invokevirtual 47	android/os/Parcel:readException	()V
        //   35: aload 4
        //   37: invokevirtual 80	android/os/Parcel:readInt	()I
        //   40: istore_1
        //   41: iload_1
        //   42: ifeq +16 -> 58
        //   45: iconst_1
        //   46: istore_2
        //   47: aload 4
        //   49: invokevirtual 53	android/os/Parcel:recycle	()V
        //   52: aload_3
        //   53: invokevirtual 53	android/os/Parcel:recycle	()V
        //   56: iload_2
        //   57: ireturn
        //   58: iconst_0
        //   59: istore_2
        //   60: goto -13 -> 47
        //   63: astore 5
        //   65: aload 4
        //   67: invokevirtual 53	android/os/Parcel:recycle	()V
        //   70: aload_3
        //   71: invokevirtual 53	android/os/Parcel:recycle	()V
        //   74: aload 5
        //   76: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	77	0	this	Proxy
        //   40	2	1	i	int
        //   46	14	2	bool	boolean
        //   3	68	3	localParcel1	Parcel
        //   7	59	4	localParcel2	Parcel
        //   63	12	5	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   9	41	63	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\FailureResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */