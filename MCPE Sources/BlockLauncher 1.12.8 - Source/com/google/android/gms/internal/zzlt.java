package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.RemoteException;

public abstract interface zzlt
  extends IInterface
{
  public abstract void destroy()
    throws RemoteException;
  
  public abstract void disconnect()
    throws RemoteException;
  
  public abstract void zza(zzls paramzzls)
    throws RemoteException;
  
  public abstract void zza(zzls paramzzls, int paramInt)
    throws RemoteException;
  
  public abstract void zza(zzls paramzzls, zzlu paramzzlu, String paramString1, String paramString2)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzlt
  {
    public static zzlt zzaI(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
      if ((localIInterface != null) && ((localIInterface instanceof zzlt))) {
        return (zzlt)localIInterface;
      }
      return new zza(paramIBinder);
    }
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        return true;
      case 2: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        destroy();
        return true;
      case 3: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        disconnect();
        return true;
      case 4: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        zza(zzls.zza.zzaH(paramParcel1.readStrongBinder()), zzlu.zza.zzaJ(paramParcel1.readStrongBinder()), paramParcel1.readString(), paramParcel1.readString());
        return true;
      case 5: 
        paramParcel1.enforceInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
        zza(zzls.zza.zzaH(paramParcel1.readStrongBinder()), paramParcel1.readInt());
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
      zza(zzls.zza.zzaH(paramParcel1.readStrongBinder()));
      return true;
    }
    
    private static class zza
      implements zzlt
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
      
      public void destroy()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
          this.zzoz.transact(2, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void disconnect()
        throws RemoteException
      {
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
          this.zzoz.transact(3, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void zza(zzls paramzzls)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
          if (paramzzls != null) {
            localIBinder = paramzzls.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          this.zzoz.transact(6, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      public void zza(zzls paramzzls, int paramInt)
        throws RemoteException
      {
        IBinder localIBinder = null;
        Parcel localParcel = Parcel.obtain();
        try
        {
          localParcel.writeInterfaceToken("com.google.android.gms.cast.remote_display.ICastRemoteDisplayService");
          if (paramzzls != null) {
            localIBinder = paramzzls.asBinder();
          }
          localParcel.writeStrongBinder(localIBinder);
          localParcel.writeInt(paramInt);
          this.zzoz.transact(5, localParcel, null, 1);
          return;
        }
        finally
        {
          localParcel.recycle();
        }
      }
      
      /* Error */
      public void zza(zzls paramzzls, zzlu paramzzlu, String paramString1, String paramString2)
        throws RemoteException
      {
        // Byte code:
        //   0: aconst_null
        //   1: astore 5
        //   3: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   6: astore 6
        //   8: aload 6
        //   10: ldc 32
        //   12: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   15: aload_1
        //   16: ifnull +70 -> 86
        //   19: aload_1
        //   20: invokeinterface 52 1 0
        //   25: astore_1
        //   26: aload 6
        //   28: aload_1
        //   29: invokevirtual 55	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   32: aload 5
        //   34: astore_1
        //   35: aload_2
        //   36: ifnull +10 -> 46
        //   39: aload_2
        //   40: invokeinterface 64 1 0
        //   45: astore_1
        //   46: aload 6
        //   48: aload_1
        //   49: invokevirtual 55	android/os/Parcel:writeStrongBinder	(Landroid/os/IBinder;)V
        //   52: aload 6
        //   54: aload_3
        //   55: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   58: aload 6
        //   60: aload 4
        //   62: invokevirtual 67	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   65: aload_0
        //   66: getfield 18	com/google/android/gms/internal/zzlt$zza$zza:zzoz	Landroid/os/IBinder;
        //   69: iconst_4
        //   70: aload 6
        //   72: aconst_null
        //   73: iconst_1
        //   74: invokeinterface 42 5 0
        //   79: pop
        //   80: aload 6
        //   82: invokevirtual 45	android/os/Parcel:recycle	()V
        //   85: return
        //   86: aconst_null
        //   87: astore_1
        //   88: goto -62 -> 26
        //   91: astore_1
        //   92: aload 6
        //   94: invokevirtual 45	android/os/Parcel:recycle	()V
        //   97: aload_1
        //   98: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	99	0	this	zza
        //   0	99	1	paramzzls	zzls
        //   0	99	2	paramzzlu	zzlu
        //   0	99	3	paramString1	String
        //   0	99	4	paramString2	String
        //   1	32	5	localObject	Object
        //   6	87	6	localParcel	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	15	91	finally
        //   19	26	91	finally
        //   26	32	91	finally
        //   39	46	91	finally
        //   46	80	91	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzlt.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */