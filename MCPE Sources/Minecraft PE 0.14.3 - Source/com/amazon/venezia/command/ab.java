package com.amazon.venezia.command;

import android.os.IBinder;
import android.os.Parcel;
import android.os.RemoteException;
import java.util.HashMap;
import java.util.Map;

final class ab
  implements FailureResult
{
  private IBinder a;
  
  ab(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }
  
  public final IBinder asBinder()
  {
    return this.a;
  }
  
  public final String getAuthToken()
    throws RemoteException
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
      this.a.transact(1, localParcel1, localParcel2, 0);
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
  
  public final String getButtonLabel()
    throws RemoteException
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
      this.a.transact(4, localParcel1, localParcel2, 0);
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
  
  public final String getDisplayableMessage()
    throws RemoteException
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
      this.a.transact(3, localParcel1, localParcel2, 0);
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
  
  public final String getDisplayableName()
    throws RemoteException
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
      this.a.transact(2, localParcel1, localParcel2, 0);
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
  
  public final Map getExtensionData()
    throws RemoteException
  {
    Parcel localParcel1 = Parcel.obtain();
    Parcel localParcel2 = Parcel.obtain();
    try
    {
      localParcel1.writeInterfaceToken("com.amazon.venezia.command.FailureResult");
      this.a.transact(6, localParcel1, localParcel2, 0);
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
  
  /* Error */
  public final boolean show()
    throws RemoteException
  {
    // Byte code:
    //   0: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_3
    //   4: invokestatic 28	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   7: astore 4
    //   9: aload_3
    //   10: ldc 30
    //   12: invokevirtual 34	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   15: aload_0
    //   16: getfield 15	com/amazon/venezia/command/ab:a	Landroid/os/IBinder;
    //   19: iconst_5
    //   20: aload_3
    //   21: aload 4
    //   23: iconst_0
    //   24: invokeinterface 40 5 0
    //   29: pop
    //   30: aload 4
    //   32: invokevirtual 43	android/os/Parcel:readException	()V
    //   35: aload 4
    //   37: invokevirtual 75	android/os/Parcel:readInt	()I
    //   40: istore_1
    //   41: iload_1
    //   42: ifeq +16 -> 58
    //   45: iconst_1
    //   46: istore_2
    //   47: aload 4
    //   49: invokevirtual 49	android/os/Parcel:recycle	()V
    //   52: aload_3
    //   53: invokevirtual 49	android/os/Parcel:recycle	()V
    //   56: iload_2
    //   57: ireturn
    //   58: iconst_0
    //   59: istore_2
    //   60: goto -13 -> 47
    //   63: astore 5
    //   65: aload 4
    //   67: invokevirtual 49	android/os/Parcel:recycle	()V
    //   70: aload_3
    //   71: invokevirtual 49	android/os/Parcel:recycle	()V
    //   74: aload 5
    //   76: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	77	0	this	ab
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\ab.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */