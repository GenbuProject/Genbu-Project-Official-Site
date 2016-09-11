package com.google.android.gms.internal;

import android.content.Intent;
import android.os.Binder;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import com.google.android.gms.auth.api.consent.GetConsentIntentRequest;

public abstract interface zzkx
  extends IInterface
{
  public abstract Intent zza(GetConsentIntentRequest paramGetConsentIntentRequest)
    throws RemoteException;
  
  public static abstract class zza
    extends Binder
    implements zzkx
  {
    public static zzkx zzau(IBinder paramIBinder)
    {
      if (paramIBinder == null) {
        return null;
      }
      IInterface localIInterface = paramIBinder.queryLocalInterface("com.google.android.gms.auth.api.consent.internal.IConsentService");
      if ((localIInterface != null) && ((localIInterface instanceof zzkx))) {
        return (zzkx)localIInterface;
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
        paramParcel2.writeString("com.google.android.gms.auth.api.consent.internal.IConsentService");
        return true;
      }
      paramParcel1.enforceInterface("com.google.android.gms.auth.api.consent.internal.IConsentService");
      if (paramParcel1.readInt() != 0)
      {
        paramParcel1 = (GetConsentIntentRequest)GetConsentIntentRequest.CREATOR.createFromParcel(paramParcel1);
        paramParcel1 = zza(paramParcel1);
        paramParcel2.writeNoException();
        if (paramParcel1 == null) {
          break label104;
        }
        paramParcel2.writeInt(1);
        paramParcel1.writeToParcel(paramParcel2, 1);
      }
      for (;;)
      {
        return true;
        paramParcel1 = null;
        break;
        label104:
        paramParcel2.writeInt(0);
      }
    }
    
    private static class zza
      implements zzkx
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
      
      public Intent zza(GetConsentIntentRequest paramGetConsentIntentRequest)
        throws RemoteException
      {
        Parcel localParcel1 = Parcel.obtain();
        Parcel localParcel2 = Parcel.obtain();
        for (;;)
        {
          try
          {
            localParcel1.writeInterfaceToken("com.google.android.gms.auth.api.consent.internal.IConsentService");
            if (paramGetConsentIntentRequest != null)
            {
              localParcel1.writeInt(1);
              paramGetConsentIntentRequest.writeToParcel(localParcel1, 0);
              this.zzoz.transact(1, localParcel1, localParcel2, 0);
              localParcel2.readException();
              if (localParcel2.readInt() != 0)
              {
                paramGetConsentIntentRequest = (Intent)Intent.CREATOR.createFromParcel(localParcel2);
                return paramGetConsentIntentRequest;
              }
            }
            else
            {
              localParcel1.writeInt(0);
              continue;
            }
            paramGetConsentIntentRequest = null;
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
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzkx.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */