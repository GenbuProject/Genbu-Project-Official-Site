package com.google.android.gms.games.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface IGamesClient
  extends IInterface
{
  public abstract PopupLocationInfoParcelable zzws()
    throws RemoteException;
  
  public static abstract class Stub
    extends Binder
    implements IGamesClient
  {
    public Stub()
    {
      attachInterface(this, "com.google.android.gms.games.internal.IGamesClient");
    }
    
    public static IGamesClient zzbX(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.games.internal.IGamesClient");
      if ((localIInterface != null) && ((localIInterface instanceof IGamesClient))) {
        return (IGamesClient)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.games.internal.IGamesClient");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.games.internal.IGamesClient");
      paramParcel1 = zzws();
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
      implements IGamesClient
    {
      private IBinder zzoz;
      
      Proxy(IBinder paramIBinder)
      {
        this.zzoz = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zzoz;
      }
      
      /* Error */
      public PopupLocationInfoParcelable zzws()
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
        //   14: aload_0
        //   15: getfield 19	com/google/android/gms/games/internal/IGamesClient$Stub$Proxy:zzoz	Landroid/os/IBinder;
        //   18: sipush 1001
        //   21: aload_2
        //   22: aload_3
        //   23: iconst_0
        //   24: invokeinterface 44 5 0
        //   29: pop
        //   30: aload_3
        //   31: invokevirtual 47	android/os/Parcel:readException	()V
        //   34: aload_3
        //   35: invokevirtual 51	android/os/Parcel:readInt	()I
        //   38: ifeq +21 -> 59
        //   41: getstatic 57	com/google/android/gms/games/internal/PopupLocationInfoParcelable:CREATOR	Lcom/google/android/gms/games/internal/PopupLocationInfoParcelableCreator;
        //   44: aload_3
        //   45: invokevirtual 63	com/google/android/gms/games/internal/PopupLocationInfoParcelableCreator:zzem	(Landroid/os/Parcel;)Lcom/google/android/gms/games/internal/PopupLocationInfoParcelable;
        //   48: astore_1
        //   49: aload_3
        //   50: invokevirtual 66	android/os/Parcel:recycle	()V
        //   53: aload_2
        //   54: invokevirtual 66	android/os/Parcel:recycle	()V
        //   57: aload_1
        //   58: areturn
        //   59: aconst_null
        //   60: astore_1
        //   61: goto -12 -> 49
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 66	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 66	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	Proxy
        //   48	13	1	localPopupLocationInfoParcelable	PopupLocationInfoParcelable
        //   64	10	1	localObject	Object
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	49	64	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\games\internal\IGamesClient.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */