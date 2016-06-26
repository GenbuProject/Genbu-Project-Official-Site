package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

public abstract interface DecisionResult
  extends IInterface
{
  public abstract void expire(DecisionExpirationContext paramDecisionExpirationContext)
    throws RemoteException;
  
  public abstract String getAuthToken()
    throws RemoteException;
  
  public abstract long getDecisionDurationInSeconds()
    throws RemoteException;
  
  public abstract String getDescription()
    throws RemoteException;
  
  public abstract String getDisplayableName()
    throws RemoteException;
  
  public abstract Map getExtensionData()
    throws RemoteException;
  
  public abstract Choice getNegativeChoice()
    throws RemoteException;
  
  public abstract Choice getNeutralChoice()
    throws RemoteException;
  
  public abstract Choice getPositiveChoice()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements DecisionResult
  {
    private static final String DESCRIPTOR = "com.amazon.venezia.command.DecisionResult";
    static final int TRANSACTION_expire = 5;
    static final int TRANSACTION_getAuthToken = 1;
    static final int TRANSACTION_getDecisionDurationInSeconds = 4;
    static final int TRANSACTION_getDescription = 3;
    static final int TRANSACTION_getDisplayableName = 2;
    static final int TRANSACTION_getExtensionData = 9;
    static final int TRANSACTION_getNegativeChoice = 8;
    static final int TRANSACTION_getNeutralChoice = 7;
    static final int TRANSACTION_getPositiveChoice = 6;
    
    public Stub()
    {
      attachInterface(this, "com.amazon.venezia.command.DecisionResult");
    }
    
    public static DecisionResult asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.command.DecisionResult");
      if ((localIInterface != null) && ((localIInterface instanceof DecisionResult))) {
        return (DecisionResult)localIInterface;
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
        paramParcel2.writeString("com.amazon.venezia.command.DecisionResult");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        paramParcel1 = getAuthToken();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        paramParcel1 = getDisplayableName();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        paramParcel1 = getDescription();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        long l = getDecisionDurationInSeconds();
        paramParcel2.writeNoException();
        paramParcel2.writeLong(l);
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        expire(DecisionExpirationContext.Stub.asInterface(paramParcel1.readStrongBinder()));
        paramParcel2.writeNoException();
        return true;
      case 6: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        paramParcel1 = getPositiveChoice();
        paramParcel2.writeNoException();
        if (paramParcel1 != null) {}
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 7: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        paramParcel1 = getNeutralChoice();
        paramParcel2.writeNoException();
        if (paramParcel1 != null) {}
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      case 8: 
        paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
        paramParcel1 = getNegativeChoice();
        paramParcel2.writeNoException();
        if (paramParcel1 != null) {}
        for (paramParcel1 = paramParcel1.asBinder();; paramParcel1 = null)
        {
          paramParcel2.writeStrongBinder(paramParcel1);
          return true;
        }
      }
      paramParcel1.enforceInterface("com.amazon.venezia.command.DecisionResult");
      paramParcel1 = getExtensionData();
      paramParcel2.writeNoException();
      paramParcel2.writeMap(paramParcel1);
      return true;
    }
    
    private static class Proxy
      implements DecisionResult
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
      
      /* Error */
      public void expire(DecisionExpirationContext paramDecisionExpirationContext)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 34
        //   11: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +42 -> 57
        //   18: aload_1
        //   19: invokeinterface 42 1 0
        //   24: astore_1
        //   25: aload_2
        //   26: aload_1
        //   27: invokevirtual 45	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   30: aload_0
        //   31: getfield 19	com/amazon/venezia/command/DecisionResult$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   34: iconst_5
        //   35: aload_2
        //   36: aload_3
        //   37: iconst_0
        //   38: invokeinterface 51 5 0
        //   43: pop
        //   44: aload_3
        //   45: invokevirtual 54	android/os/Parcel:readException	()V
        //   48: aload_3
        //   49: invokevirtual 57	android/os/Parcel:recycle	()V
        //   52: aload_2
        //   53: invokevirtual 57	android/os/Parcel:recycle	()V
        //   56: return
        //   57: aconst_null
        //   58: astore_1
        //   59: goto -34 -> 25
        //   62: astore_1
        //   63: aload_3
        //   64: invokevirtual 57	android/os/Parcel:recycle	()V
        //   67: aload_2
        //   68: invokevirtual 57	android/os/Parcel:recycle	()V
        //   71: aload_1
        //   72: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	73	0	this	Proxy
        //   0	73	1	paramDecisionExpirationContext	DecisionExpirationContext
        //   3	65	2	localParcel1	Parcel
        //   7	57	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	62	finally
        //   18	25	62	finally
        //   25	48	62	finally
      }
      
      public String getAuthToken()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
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
      
      public long getDecisionDurationInSeconds()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
          this.mRemote.transact(4, localParcel1, localParcel2, 0);
          localParcel2.readException();
          long l = localParcel2.readLong();
          return l;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public String getDescription()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
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
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
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
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
          this.mRemote.transact(9, localParcel1, localParcel2, 0);
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
        return "com.amazon.venezia.command.DecisionResult";
      }
      
      public Choice getNegativeChoice()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
          this.mRemote.transact(8, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Choice localChoice = Choice.Stub.asInterface(localParcel2.readStrongBinder());
          return localChoice;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public Choice getNeutralChoice()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
          this.mRemote.transact(7, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Choice localChoice = Choice.Stub.asInterface(localParcel2.readStrongBinder());
          return localChoice;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public Choice getPositiveChoice()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.command.DecisionResult");
          this.mRemote.transact(6, localParcel1, localParcel2, 0);
          localParcel2.readException();
          Choice localChoice = Choice.Stub.asInterface(localParcel2.readStrongBinder());
          return localChoice;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\DecisionResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */