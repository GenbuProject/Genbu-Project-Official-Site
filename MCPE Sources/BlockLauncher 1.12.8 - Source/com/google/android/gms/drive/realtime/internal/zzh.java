package com.google.android.gms.drive.realtime.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzh
  extends IInterface
{
  public abstract void zzc(boolean paramBoolean1, boolean paramBoolean2)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzh
  {
    public static zzh zzbj(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
      if ((localIInterface != null) && ((localIInterface instanceof zzh))) {
        return (zzh)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      boolean bool2 = false;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.drive.realtime.internal.IDocumentSaveStateEventCallback");
      if (paramParcel1.readInt() != 0) {}
      for (boolean bool1 = true;; bool1 = false)
      {
        if (paramParcel1.readInt() != 0) {
          bool2 = true;
        }
        zzc(bool1, bool2);
        paramParcel2.writeNoException();
        return true;
      }
    }
    
    private static class zza
      implements zzh
    {
      private IBinder zzoz;
      
      zza(IBinder paramIBinder)
      {
        this.zzoz = paramIBinder;
      }
      
      public IBinder asBinder()
      {
        return this.zzoz;
      }
      
      /* Error */
      public void zzc(boolean paramBoolean1, boolean paramBoolean2)
        throws RemoteException
      {
        // Byte code:
        //   0: iconst_1
        //   1: istore 4
        //   3: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 5
        //   8: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   11: astore 6
        //   13: aload 5
        //   15: ldc 33
        //   17: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   20: iload_1
        //   21: ifeq +56 -> 77
        //   24: iconst_1
        //   25: istore_3
        //   26: aload 5
        //   28: iload_3
        //   29: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   32: iload_2
        //   33: ifeq +49 -> 82
        //   36: iload 4
        //   38: istore_3
        //   39: aload 5
        //   41: iload_3
        //   42: invokevirtual 41	android/os/Parcel:writeInt	(I)V
        //   45: aload_0
        //   46: getfield 18	com/google/android/gms/drive/realtime/internal/zzh$zza$zza:zzoz	Landroid/os/IBinder;
        //   49: iconst_1
        //   50: aload 5
        //   52: aload 6
        //   54: iconst_0
        //   55: invokeinterface 47 5 0
        //   60: pop
        //   61: aload 6
        //   63: invokevirtual 50	android/os/Parcel:readException	()V
        //   66: aload 6
        //   68: invokevirtual 53	android/os/Parcel:recycle	()V
        //   71: aload 5
        //   73: invokevirtual 53	android/os/Parcel:recycle	()V
        //   76: return
        //   77: iconst_0
        //   78: istore_3
        //   79: goto -53 -> 26
        //   82: iconst_0
        //   83: istore_3
        //   84: goto -45 -> 39
        //   87: astore 7
        //   89: aload 6
        //   91: invokevirtual 53	android/os/Parcel:recycle	()V
        //   94: aload 5
        //   96: invokevirtual 53	android/os/Parcel:recycle	()V
        //   99: aload 7
        //   101: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	102	0	this	zza
        //   0	102	1	paramBoolean1	boolean
        //   0	102	2	paramBoolean2	boolean
        //   25	59	3	i	int
        //   1	36	4	j	int
        //   6	89	5	localParcel1	Parcel
        //   11	79	6	localParcel2	Parcel
        //   87	13	7	localObject	Object
        // Exception table:
        //   from	to	target	type
        //   13	20	87	finally
        //   26	32	87	finally
        //   39	66	87	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\realtime\internal\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */