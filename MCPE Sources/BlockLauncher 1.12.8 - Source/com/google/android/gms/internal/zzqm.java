package com.google.android.gms.internal;

import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.nearby.connection.AppMetadata;

public abstract interface zzqm
  extends IInterface
{
  public abstract void onConnectionRequest(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public abstract void onDisconnected(String paramString)
    throws RemoteException;
  
  public abstract void onEndpointFound(String paramString1, String paramString2, String paramString3, String paramString4)
    throws RemoteException;
  
  public abstract void onEndpointLost(String paramString)
    throws RemoteException;
  
  public abstract void onMessageReceived(String paramString, byte[] paramArrayOfByte, boolean paramBoolean)
    throws RemoteException;
  
  public abstract void zza(String paramString, int paramInt, byte[] paramArrayOfByte)
    throws RemoteException;
  
  public abstract void zza(String paramString1, String paramString2, String paramString3, String paramString4, AppMetadata paramAppMetadata)
    throws RemoteException;
  
  public abstract void zzfy(String paramString)
    throws RemoteException;
  
  public abstract void zziW(int paramInt)
    throws RemoteException;
  
  public abstract void zziX(int paramInt)
    throws RemoteException;
  
  public abstract void zziY(int paramInt)
    throws RemoteException;
  
  public abstract void zziZ(int paramInt)
    throws RemoteException;
  
  public abstract void zzja(int paramInt)
    throws RemoteException;
  
  public abstract void zzm(int paramInt, String paramString)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzqm
  {
    public zza()
    {
      attachInterface(this, "com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
    }
    
    public static zzqm zzdw(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
      if ((localIInterface != null) && ((localIInterface instanceof zzqm))) {
        return (zzqm)localIInterface;
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
      String str1;
      Object localObject;
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        return true;
      case 1001: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        onConnectionRequest(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.createByteArray());
        paramParcel2.writeNoException();
        return true;
      case 1002: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        onEndpointFound(paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 1003: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        onEndpointLost(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 1004: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        str1 = paramParcel1.readString();
        localObject = paramParcel1.readString();
        String str2 = paramParcel1.readString();
        String str3 = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel1 = (AppMetadata)AppMetadata.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
        {
          zza(str1, (String)localObject, str2, str3, paramParcel1);
          paramParcel2.writeNoException();
          return true;
        }
      case 1005: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        zzfy(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 1006: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        zza(paramParcel1.readString(), paramParcel1.readInt(), paramParcel1.createByteArray());
        paramParcel2.writeNoException();
        return true;
      case 1007: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        str1 = paramParcel1.readString();
        localObject = paramParcel1.createByteArray();
        if (paramParcel1.readInt() != 0) {}
        for (boolean bool = true;; bool = false)
        {
          onMessageReceived(str1, (byte[])localObject, bool);
          paramParcel2.writeNoException();
          return true;
        }
      case 1008: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        onDisconnected(paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 1009: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        zzm(paramParcel1.readInt(), paramParcel1.readString());
        paramParcel2.writeNoException();
        return true;
      case 1010: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        zziW(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 1011: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        zziX(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 1012: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        zziY(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      case 1013: 
        paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
        zziZ(paramParcel1.readInt());
        paramParcel2.writeNoException();
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
      zzja(paramParcel1.readInt());
      paramParcel2.writeNoException();
      return true;
    }
    
    private static class zza
      implements zzqm
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
      
      public void onConnectionRequest(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeString(paramString3);
          localParcel1.writeByteArray(paramArrayOfByte);
          this.zzoz.transact(1001, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onDisconnected(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeString(paramString);
          this.zzoz.transact(1008, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onEndpointFound(String paramString1, String paramString2, String paramString3, String paramString4)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeString(paramString1);
          localParcel1.writeString(paramString2);
          localParcel1.writeString(paramString3);
          localParcel1.writeString(paramString4);
          this.zzoz.transact(1002, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onEndpointLost(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeString(paramString);
          this.zzoz.transact(1003, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void onMessageReceived(String paramString, byte[] paramArrayOfByte, boolean paramBoolean)
        throws RemoteException
      {
        int i = 0;
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeString(paramString);
          localParcel1.writeByteArray(paramArrayOfByte);
          if (paramBoolean) {
            i = 1;
          }
          localParcel1.writeInt(i);
          this.zzoz.transact(1007, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zza(String paramString, int paramInt, byte[] paramArrayOfByte)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeString(paramString);
          localParcel1.writeInt(paramInt);
          localParcel1.writeByteArray(paramArrayOfByte);
          this.zzoz.transact(1006, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      /* Error */
      public void zza(String paramString1, String paramString2, String paramString3, String paramString4, AppMetadata paramAppMetadata)
        throws RemoteException
      {
        // Byte code:
        //   0: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   3: astore 6
        //   5: invokestatic 31	android/os/Parcel:obtain	()Landroid/os/Parcel;
        //   8: astore 7
        //   10: aload 6
        //   12: ldc 33
        //   14: invokevirtual 37	android/os/Parcel:writeInterfaceToken	(Ljava/lang/String;)V
        //   17: aload 6
        //   19: aload_1
        //   20: invokevirtual 40	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   23: aload 6
        //   25: aload_2
        //   26: invokevirtual 40	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   29: aload 6
        //   31: aload_3
        //   32: invokevirtual 40	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   35: aload 6
        //   37: aload 4
        //   39: invokevirtual 40	android/os/Parcel:writeString	(Ljava/lang/String;)V
        //   42: aload 5
        //   44: ifnull +51 -> 95
        //   47: aload 6
        //   49: iconst_1
        //   50: invokevirtual 67	android/os/Parcel:writeInt	(I)V
        //   53: aload 5
        //   55: aload 6
        //   57: iconst_0
        //   58: invokevirtual 75	com/google/android/gms/nearby/connection/AppMetadata:writeToParcel	(Landroid/os/Parcel;I)V
        //   61: aload_0
        //   62: getfield 18	com/google/android/gms/internal/zzqm$zza$zza:zzoz	Landroid/os/IBinder;
        //   65: sipush 1004
        //   68: aload 6
        //   70: aload 7
        //   72: iconst_0
        //   73: invokeinterface 50 5 0
        //   78: pop
        //   79: aload 7
        //   81: invokevirtual 53	android/os/Parcel:readException	()V
        //   84: aload 7
        //   86: invokevirtual 56	android/os/Parcel:recycle	()V
        //   89: aload 6
        //   91: invokevirtual 56	android/os/Parcel:recycle	()V
        //   94: return
        //   95: aload 6
        //   97: iconst_0
        //   98: invokevirtual 67	android/os/Parcel:writeInt	(I)V
        //   101: goto -40 -> 61
        //   104: astore_1
        //   105: aload 7
        //   107: invokevirtual 56	android/os/Parcel:recycle	()V
        //   110: aload 6
        //   112: invokevirtual 56	android/os/Parcel:recycle	()V
        //   115: aload_1
        //   116: athrow
        // Local variable table:
        //   start	length	slot	name	signature
        //   0	117	0	this	zza
        //   0	117	1	paramString1	String
        //   0	117	2	paramString2	String
        //   0	117	3	paramString3	String
        //   0	117	4	paramString4	String
        //   0	117	5	paramAppMetadata	AppMetadata
        //   3	108	6	localParcel1	Parcel
        //   8	98	7	localParcel2	Parcel
        // Exception table:
        //   from	to	target	type
        //   10	42	104	finally
        //   47	61	104	finally
        //   61	84	104	finally
        //   95	101	104	finally
      }
      
      public void zzfy(String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeString(paramString);
          this.zzoz.transact(1005, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zziW(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeInt(paramInt);
          this.zzoz.transact(1010, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zziX(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeInt(paramInt);
          this.zzoz.transact(1011, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zziY(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeInt(paramInt);
          this.zzoz.transact(1012, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zziZ(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeInt(paramInt);
          this.zzoz.transact(1013, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zzja(int paramInt)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeInt(paramInt);
          this.zzoz.transact(1014, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
        }
        finally
        {
          localParcel2.recycle();
          localParcel1.recycle();
        }
      }
      
      public void zzm(int paramInt, String paramString)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        try
        {
          localParcel1.writeInterfaceToken("com.google.android.gms.nearby.internal.connection.INearbyConnectionCallbacks");
          localParcel1.writeInt(paramInt);
          localParcel1.writeString(paramString);
          this.zzoz.transact(1009, localParcel1, localParcel2, 0);
          localParcel2.readException();
          return;
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzqm.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */