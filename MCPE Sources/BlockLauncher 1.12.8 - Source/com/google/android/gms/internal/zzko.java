package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.common.api.Status;

public abstract interface zzko
  extends IInterface
{
  public abstract void zza(Status paramStatus, Intent paramIntent)
    throws RemoteException;
  
  public abstract void zzd(Status paramStatus)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzko
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
    }
    
    public static zzko zzak(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
      if ((localIInterface != null) && ((localIInterface instanceof zzko))) {
        return (zzko)localIInterface;
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
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
        return true;
      case 1: 
        paramParcel1.enforceInterface("com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (Status)Status.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zzd(paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      }
      paramParcel1.enforceInterface("com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
      Status localStatus;
      if (paramParcel1.readInt() != 0)
      {
        localStatus = (Status)Status.CREATOR.createFromParcel(paramParcel1);
        if (paramParcel1.readInt() == 0) {
          break label162;
        }
      }
      label162:
      for (paramParcel1 = (Intent)Intent.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        zza(localStatus, paramParcel1);
        paramParcel2.writeNoException();
        return true;
        localStatus = null;
        break;
      }
    }
    
    private static class zza
      implements zzko
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
      
      public void zza(Status paramStatus, Intent paramIntent)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.appinvite.internal.IAppInviteCallbacks");
            if (paramStatus != null)
            {
              localParcel1.writeInt(1);
              paramStatus.writeToParcel(localParcel1, 0);
              if (paramIntent != null)
              {
                localParcel1.writeInt(1);
                paramIntent.writeToParcel(localParcel1, 0);
                this.zzoz.transact(2, localParcel1, localParcel2, 0);
                localParcel2.readException();
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            localParcel1.writeInt(0);
          }
          finally
          {
            localParcel2.recycle();
            localParcel1.recycle();
          }
        }
      }
      
      /* Error */
      public void zzd(Status paramStatus)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore_2
        //   4: invokestatic 30	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   7: astore_3
        //   8: aload_2
        //   9: ldc 32
        //   11: invokevirtual 36	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   14: aload_1
        //   15: ifnull +41 -> 56
        //   18: aload_2
        //   19: iconst_1
        //   20: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   23: aload_1
        //   24: aload_2
        //   25: iconst_0
        //   26: invokevirtual 46	com/google/android/gms/common/api/Status:writeToParcel	(Landroid/os/Parcel;I)V
        //   29: aload_0
        //   30: getfield 18	com/google/android/gms/internal/zzko$zza$zza:zzoz	Landroid/os/IBinder;
        //   33: iconst_1
        //   34: aload_2
        //   35: aload_3
        //   36: iconst_0
        //   37: invokeinterface 55 5 0
        //   42: pop
        //   43: aload_3
        //   44: invokevirtual 58	android/os/Parcel:readException	()V
        //   47: aload_3
        //   48: invokevirtual 61	android/os/Parcel:recycle	()V
        //   51: aload_2
        //   52: invokevirtual 61	android/os/Parcel:recycle	()V
        //   55: return
        //   56: aload_2
        //   57: iconst_0
        //   58: invokevirtual 40	android/os/Parcel:writeInt	(I)V
        //   61: goto -32 -> 29
        //   64: astore_1
        //   65: aload_3
        //   66: invokevirtual 61	android/os/Parcel:recycle	()V
        //   69: aload_2
        //   70: invokevirtual 61	android/os/Parcel:recycle	()V
        //   73: aload_1
        //   74: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	75	0	this	zza
        //   0	75	1	paramStatus	Status
        //   3	67	2	localParcel1	Parcel
        //   7	59	3	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   8	14	64	finally
        //   18	29	64	finally
        //   29	47	64	finally
        //   56	61	64	finally
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzko.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */