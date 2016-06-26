package com.amazon.venezia.command;

import android.os.IBinder;

final class l
  implements h
{
  private IBinder a;
  
  l(IBinder paramIBinder)
  {
    this.a = paramIBinder;
  }
  
  /* Error */
  public final void a(w paramw, f paramf)
    throws android.os.RemoteException
  {
    // Byte code:
    //   0: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   3: astore_3
    //   4: invokestatic 25	android/os/Parcel:obtain	()Landroid/os/Parcel;
    //   7: astore 4
    //   9: aload_3
    //   10: ldc 27
    //   12: invokevirtual 31	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
    //   15: aload_1
    //   16: ifnull +61 -> 77
    //   19: aload_1
    //   20: invokeinterface 37 1 0
    //   25: astore_1
    //   26: aload_3
    //   27: aload_1
    //   28: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
    //   31: aload_2
    //   32: ifnull +50 -> 82
    //   35: aload_2
    //   36: invokeinterface 43 1 0
    //   41: astore_1
    //   42: aload_3
    //   43: aload_1
    //   44: invokevirtual 40	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
    //   47: aload_0
    //   48: getfield 15	com/amazon/venezia/command/l:a	Landroid/os/IBinder;
    //   51: iconst_1
    //   52: aload_3
    //   53: aload 4
    //   55: iconst_0
    //   56: invokeinterface 49 5 0
    //   61: pop
    //   62: aload 4
    //   64: invokevirtual 52	android/os/Parcel:readException	()V
    //   67: aload 4
    //   69: invokevirtual 55	android/os/Parcel:recycle	()V
    //   72: aload_3
    //   73: invokevirtual 55	android/os/Parcel:recycle	()V
    //   76: return
    //   77: aconst_null
    //   78: astore_1
    //   79: goto -53 -> 26
    //   82: aconst_null
    //   83: astore_1
    //   84: goto -42 -> 42
    //   87: astore_1
    //   88: aload 4
    //   90: invokevirtual 55	android/os/Parcel:recycle	()V
    //   93: aload_3
    //   94: invokevirtual 55	android/os/Parcel:recycle	()V
    //   97: aload_1
    //   98: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	99	0	this	l
    //   0	99	1	paramw	w
    //   0	99	2	paramf	f
    //   3	91	3	localParcel1	android.os.Parcel
    //   7	82	4	localParcel2	android.os.Parcel
    // Exception table:
    //   from	to	target	type
    //   9	15	87	finally
    //   19	26	87	finally
    //   26	31	87	finally
    //   35	42	87	finally
    //   42	67	87	finally
  }
  
  public final IBinder asBinder()
  {
    return this.a;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\amazon\venezia\command\l.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */