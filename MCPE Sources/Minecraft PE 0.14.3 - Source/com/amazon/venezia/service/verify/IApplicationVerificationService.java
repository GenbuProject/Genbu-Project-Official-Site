package com.amazon.venezia.service.verify;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IApplicationVerificationService
  extends IInterface
{
  public abstract String getAmazonId()
    throws RemoteException;
  
  public abstract String getDeviceId()
    throws RemoteException;
  
  public abstract String getToken(String paramString)
    throws RemoteException;
  
  public abstract void reportVerificationResults(String paramString1, boolean paramBoolean, String paramString2)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IApplicationVerificationService
  {
    private static final String DESCRIPTOR = "com.amazon.venezia.service.verify.IApplicationVerificationService";
    static final int TRANSACTION_getAmazonId = 3;
    static final int TRANSACTION_getDeviceId = 4;
    static final int TRANSACTION_getToken = 2;
    static final int TRANSACTION_reportVerificationResults = 1;
    
    public Stub()
    {
      attachInterface(this, "com.amazon.venezia.service.verify.IApplicationVerificationService");
    }
    
    public static IApplicationVerificationService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.service.verify.IApplicationVerificationService");
      if ((localIInterface != null) && ((localIInterface instanceof IApplicationVerificationService))) {
        return (IApplicationVerificationService)localIInterface;
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
        paramParcel2.writeString("com.amazon.venezia.service.verify.IApplicationVerificationService");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.amazon.venezia.service.verify.IApplicationVerificationService");
        String str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          reportVerificationResults(str, bool, paramParcel1.readString());
          paramParcel2.writeNoException();
          return true;
        }
      case 2: 
        paramParcel1.enforceInterface("com.amazon.venezia.service.verify.IApplicationVerificationService");
        paramParcel1 = getToken(paramParcel1.readString());
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.amazon.venezia.service.verify.IApplicationVerificationService");
        paramParcel1 = getAmazonId();
        paramParcel2.writeNoException();
        paramParcel2.writeString(paramParcel1);
        return true;
      }
      paramParcel1.enforceInterface("com.amazon.venezia.service.verify.IApplicationVerificationService");
      paramParcel1 = getDeviceId();
      paramParcel2.writeNoException();
      paramParcel2.writeString(paramParcel1);
      return true;
    }
    
    private static class Proxy
      implements IApplicationVerificationService
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
      
      public String getAmazonId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.service.verify.IApplicationVerificationService");
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
      
      public String getDeviceId()
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.service.verify.IApplicationVerificationService");
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
      
      public String getInterfaceDescriptor()
      {
        return "com.amazon.venezia.service.verify.IApplicationVerificationService";
      }
      
      public String getToken(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.amazon.venezia.service.verify.IApplicationVerificationService");
          localParcel1.writeString(paramString);
          this.mRemote.transact(2, localParcel1, localParcel2, 0);
          localParcel2.readException();
          paramString = localParcel2.readString();
          return paramString;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void reportVerificationResults(String paramString1, boolean paramBoolean, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 5
        //   5: invokestatic 32	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 6
        //   10: aload 5
        //   12: ldc 34
        //   14: invokevirtual 38	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 5
        //   19: aload_1
        //   20: invokevirtual 61	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: iload_2
        //   24: ifeq +51 -> 75
        //   27: iconst_1
        //   28: istore 4
        //   30: aload 5
        //   32: iload 4
        //   34: invokevirtual 67	android/os/Parcel:writeInt	(I)V
        //   37: aload 5
        //   39: aload_3
        //   40: invokevirtual 61	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   43: aload_0
        //   44: getfield 19	com/amazon/venezia/service/verify/IApplicationVerificationService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   47: iconst_1
        //   48: aload 5
        //   50: aload 6
        //   52: iconst_0
        //   53: invokeinterface 44 5 0
        //   58: pop
        //   59: aload 6
        //   61: invokevirtual 47	android/os/Parcel:readException	()V
        //   64: aload 6
        //   66: invokevirtual 53	android/os/Parcel:recycle	()V
        //   69: aload 5
        //   71: invokevirtual 53	android/os/Parcel:recycle	()V
        //   74: return
        //   75: iconst_0
        //   76: istore 4
        //   78: goto -48 -> 30
        //   81: astore_1
        //   82: aload 6
        //   84: invokevirtual 53	android/os/Parcel:recycle	()V
        //   87: aload 5
        //   89: invokevirtual 53	android/os/Parcel:recycle	()V
        //   92: aload_1
        //   93: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	94	0	this	Proxy
        //   0	94	1	paramString1	String
        //   0	94	2	paramBoolean	boolean
        //   0	94	3	paramString2	String
        //   28	49	4	i	int
        //   3	85	5	localParcel1	Parcel
        //   8	75	6	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	23	81	finally
        //   30	64	81	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\service\verify\IApplicationVerificationService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */