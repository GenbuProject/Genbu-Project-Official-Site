package com.microsoft.xbox.authenticate;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IDelegateKeyService
  extends IInterface
{
  public abstract DelegateRPSTicketResult requestDelegateRPSTicketSilently()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IDelegateKeyService
  {
    private static final String DESCRIPTOR = "com.microsoft.xbox.authenticate.IDelegateKeyService";
    static final int TRANSACTION_requestDelegateRPSTicketSilently = 1;
    
    public Stub()
    {
      attachInterface(this, "com.microsoft.xbox.authenticate.IDelegateKeyService");
    }
    
    public static IDelegateKeyService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.microsoft.xbox.authenticate.IDelegateKeyService");
      if ((localIInterface != null) && ((localIInterface instanceof IDelegateKeyService))) {
        return (IDelegateKeyService)localIInterface;
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
        paramParcel2.writeString("com.microsoft.xbox.authenticate.IDelegateKeyService");
        return true;
      }
      paramParcel1.enforceInterface("com.microsoft.xbox.authenticate.IDelegateKeyService");
      paramParcel1 = requestDelegateRPSTicketSilently();
      paramParcel2.writeNoException();
      if (paramParcel1 != null)
      {
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
        return true;
      }
      paramParcel2.writeInt(0);
      return true;
    }
    
    private static class Proxy
      implements IDelegateKeyService
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
      
      public String getInterfaceDescriptor()
      {
        return "com.microsoft.xbox.authenticate.IDelegateKeyService";
      }
      
      /* Error */
      public DelegateRPSTicketResult requestDelegateRPSTicketSilently()
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 36	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 26
        //   11: invokevirtual 40	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_0
        //   15: getfield 19	com/microsoft/xbox/authenticate/IDelegateKeyService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   18: iconst_1
        //   19: aload_2
        //   20: aload_3
        //   21: iconst_0
        //   22: invokeinterface 46 5 0
        //   27: pop
        //   28: aload_3
        //   29: invokevirtual 49	android/os/Parcel:readException	()V
        //   32: aload_3
        //   33: invokevirtual 53	android/os/Parcel:readInt	()I
        //   36: ifeq +26 -> 62
        //   39: getstatic 59	com/microsoft/xbox/authenticate/DelegateRPSTicketResult:CREATOR	Landroid/os/Parcelable$Creator;
        //   42: aload_3
        //   43: invokeinterface 65 2 0
        //   48: checkcast 55	com/microsoft/xbox/authenticate/DelegateRPSTicketResult
        //   51: astore_1
        //   52: aload_3
        //   53: invokevirtual 68	android/os/Parcel:recycle	()V
        //   56: aload_2
        //   57: invokevirtual 68	android/os/Parcel:recycle	()V
        //   60: aload_1
        //   61: areturn
        //   62: aconst_null
        //   63: astore_1
        //   64: goto -12 -> 52
        //   67: astore_1
        //   68: aload_3
        //   69: invokevirtual 68	android/os/Parcel:recycle	()V
        //   72: aload_2
        //   73: invokevirtual 68	android/os/Parcel:recycle	()V
        //   76: aload_1
        //   77: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	78	0	this	Proxy
        //   51	13	1	localDelegateRPSTicketResult	DelegateRPSTicketResult
        //   67	10	1	localObject	Object
        //   3	70	2	localParcel1	Parcel
        //   7	62	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	52	67	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\authenticate\IDelegateKeyService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */