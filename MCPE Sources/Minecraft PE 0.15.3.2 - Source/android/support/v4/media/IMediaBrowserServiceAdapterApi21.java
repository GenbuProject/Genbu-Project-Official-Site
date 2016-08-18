package android.support.v4.media;

import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import android.os.IInterface;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.os.RemoteException;
import android.os.ResultReceiver;

class IMediaBrowserServiceAdapterApi21
{
  static abstract class Stub
    extends Binder
    implements IInterface
  {
    private static final String DESCRIPTOR = "android.service.media.IMediaBrowserService";
    private static final int TRANSACTION_addSubscription = 3;
    private static final int TRANSACTION_connect = 1;
    private static final int TRANSACTION_disconnect = 2;
    private static final int TRANSACTION_getMediaItem = 5;
    private static final int TRANSACTION_removeSubscription = 4;
    
    public Stub()
    {
      attachInterface(this, "android.service.media.IMediaBrowserService");
    }
    
    public abstract void addSubscription(String paramString, Object paramObject);
    
    public IBinder asBinder()
    {
      return this;
    }
    
    public abstract void connect(String paramString, Bundle paramBundle, Object paramObject);
    
    public abstract void disconnect(Object paramObject);
    
    public abstract void getMediaItem(String paramString, ResultReceiver paramResultReceiver);
    
    public boolean onTransact(int paramInt1, Parcel paramParcel1, Parcel paramParcel2, int paramInt2)
      throws RemoteException
    {
      switch (paramInt1)
      {
      default: 
        return super.onTransact(paramInt1, paramParcel1, paramParcel2, paramInt2);
      case 1598968902: 
        paramParcel2.writeString("android.service.media.IMediaBrowserService");
        return true;
      case 1: 
        paramParcel1.enforceInterface("android.service.media.IMediaBrowserService");
        String str = paramParcel1.readString();
        if (paramParcel1.readInt() != 0) {}
        for (paramParcel2 = (Bundle)Bundle.CREATOR.createFromParcel(paramParcel1);; paramParcel2 = null)
        {
          connect(str, paramParcel2, IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(paramParcel1.readStrongBinder()));
          return true;
        }
      case 2: 
        paramParcel1.enforceInterface("android.service.media.IMediaBrowserService");
        disconnect(IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(paramParcel1.readStrongBinder()));
        return true;
      case 3: 
        paramParcel1.enforceInterface("android.service.media.IMediaBrowserService");
        addSubscription(paramParcel1.readString(), IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(paramParcel1.readStrongBinder()));
        return true;
      case 4: 
        paramParcel1.enforceInterface("android.service.media.IMediaBrowserService");
        removeSubscription(paramParcel1.readString(), IMediaBrowserServiceCallbacksAdapterApi21.Stub.asInterface(paramParcel1.readStrongBinder()));
        return true;
      }
      paramParcel1.enforceInterface("android.service.media.IMediaBrowserService");
      paramParcel2 = paramParcel1.readString();
      if (paramParcel1.readInt() != 0) {}
      for (paramParcel1 = (ResultReceiver)ResultReceiver.CREATOR.createFromParcel(paramParcel1);; paramParcel1 = null)
      {
        getMediaItem(paramParcel2, paramParcel1);
        return true;
      }
    }
    
    public abstract void removeSubscription(String paramString, Object paramObject);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\media\IMediaBrowserServiceAdapterApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */