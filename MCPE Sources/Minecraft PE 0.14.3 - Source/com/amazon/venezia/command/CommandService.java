package com.amazon.venezia.command;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface CommandService
  extends IInterface
{
  public abstract void execute(Command paramCommand, ResultCallback paramResultCallback)
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements CommandService
  {
    private static final String DESCRIPTOR = "com.amazon.venezia.command.CommandService";
    static final int TRANSACTION_execute = 1;
    
    public Stub()
    {
      attachInterface(this, "com.amazon.venezia.command.CommandService");
    }
    
    public static CommandService asInterface(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.amazon.venezia.command.CommandService");
      if ((localIInterface != null) && ((localIInterface instanceof CommandService))) {
        return (CommandService)localIInterface;
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
        paramParcel2.writeString("com.amazon.venezia.command.CommandService");
        return true;
      }
      paramParcel1.enforceInterface("com.amazon.venezia.command.CommandService");
      execute(Command.Stub.asInterface(paramParcel1.readStrongBinder()), ResultCallback.Stub.asInterface(paramParcel1.readStrongBinder()));
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class Proxy
      implements CommandService
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
      public void execute(Command paramCommand, ResultCallback paramResultCallback)
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
        //   15: aload_1
        //   16: ifnull +61 -> 77
        //   19: aload_1
        //   20: invokeinterface 42 1 0
        //   25: astore_1
        //   26: aload_3
        //   27: aload_1
        //   28: invokevirtual 45	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   31: aload_2
        //   32: ifnull +50 -> 82
        //   35: aload_2
        //   36: invokeinterface 48 1 0
        //   41: astore_1
        //   42: aload_3
        //   43: aload_1
        //   44: invokevirtual 45	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   47: aload_0
        //   48: getfield 19	com/amazon/venezia/command/CommandService$Stub$Proxy:mRemote	Landroid/os/IBinder;
        //   51: iconst_1
        //   52: aload_3
        //   53: aload 4
        //   55: iconst_0
        //   56: invokeinterface 54 5 0
        //   61: pop
        //   62: aload 4
        //   64: invokevirtual 57	android/os/Parcel:readException	()V
        //   67: aload 4
        //   69: invokevirtual 60	android/os/Parcel:recycle	()V
        //   72: aload_3
        //   73: invokevirtual 60	android/os/Parcel:recycle	()V
        //   76: return
        //   77: aconst_null
        //   78: astore_1
        //   79: goto -53 -> 26
        //   82: aconst_null
        //   83: astore_1
        //   84: goto -42 -> 42
        //   87: astore_1
        //   88: aload 4
        //   90: invokevirtual 60	android/os/Parcel:recycle	()V
        //   93: aload_3
        //   94: invokevirtual 60	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	Proxy
        //   0	99	1	paramCommand	Command
        //   0	99	2	paramResultCallback	ResultCallback
        //   3	91	3	localParcel1	Parcel
        //   7	82	4	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   9	15	87	finally
        //   19	26	87	finally
        //   26	31	87	finally
        //   35	42	87	finally
        //   42	67	87	finally
      }
      
      public String getInterfaceDescriptor()
      {
        return "com.amazon.venezia.command.CommandService";
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\CommandService.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */