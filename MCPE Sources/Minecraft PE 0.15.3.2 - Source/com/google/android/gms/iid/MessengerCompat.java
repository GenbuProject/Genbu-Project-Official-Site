package com.google.android.gms.iid;

import android.annotation.TargetApi;
import android.os.Binder;
import android.os.Build.VERSION;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.Parcel;
import android.os.Parcelable;
import android.os.Parcelable.Creator;
import android.os.RemoteException;

public class MessengerCompat
  implements Parcelable
{
  public static final Parcelable.Creator<MessengerCompat> CREATOR = new Parcelable.Creator()
  {
    public MessengerCompat zzeO(Parcel paramAnonymousParcel)
    {
      paramAnonymousParcel = paramAnonymousParcel.readStrongBinder();
      if (paramAnonymousParcel != null) {
        return new MessengerCompat(paramAnonymousParcel);
      }
      return null;
    }
    
    public MessengerCompat[] zzhm(int paramAnonymousInt)
    {
      return new MessengerCompat[paramAnonymousInt];
    }
  };
  Messenger zzaNd;
  zzb zzaNe;
  
  public MessengerCompat(Handler paramHandler)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.zzaNd = new Messenger(paramHandler);
      return;
    }
    this.zzaNe = new zza(paramHandler);
  }
  
  public MessengerCompat(IBinder paramIBinder)
  {
    if (Build.VERSION.SDK_INT >= 21)
    {
      this.zzaNd = new Messenger(paramIBinder);
      return;
    }
    this.zzaNe = zzb.zza.zzcd(paramIBinder);
  }
  
  public static int zzc(Message paramMessage)
  {
    if (Build.VERSION.SDK_INT >= 21) {
      return zzd(paramMessage);
    }
    return paramMessage.arg2;
  }
  
  @TargetApi(21)
  private static int zzd(Message paramMessage)
  {
    return paramMessage.sendingUid;
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (paramObject == null) {
      return false;
    }
    try
    {
      boolean bool = getBinder().equals(((MessengerCompat)paramObject).getBinder());
      return bool;
    }
    catch (ClassCastException paramObject) {}
    return false;
  }
  
  public IBinder getBinder()
  {
    if (this.zzaNd != null) {
      return this.zzaNd.getBinder();
    }
    return this.zzaNe.asBinder();
  }
  
  public int hashCode()
  {
    return getBinder().hashCode();
  }
  
  public void send(Message paramMessage)
    throws RemoteException
  {
    if (this.zzaNd != null)
    {
      this.zzaNd.send(paramMessage);
      return;
    }
    this.zzaNe.send(paramMessage);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    if (this.zzaNd != null)
    {
      paramParcel.writeStrongBinder(this.zzaNd.getBinder());
      return;
    }
    paramParcel.writeStrongBinder(this.zzaNe.asBinder());
  }
  
  private final class zza
    extends zzb.zza
  {
    Handler handler;
    
    zza(Handler paramHandler)
    {
      this.handler = paramHandler;
    }
    
    public void send(Message paramMessage)
      throws RemoteException
    {
      paramMessage.arg2 = Binder.getCallingUid();
      this.handler.dispatchMessage(paramMessage);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\google\android\gms\iid\MessengerCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */