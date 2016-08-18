package android.support.v4.media;

import android.media.session.MediaSession.Token;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

class IMediaBrowserServiceCallbacksAdapterApi21
{
  private Method mAsBinderMethod;
  Object mCallbackObject;
  private Method mOnConnectFailedMethod;
  private Method mOnConnectMethod;
  private Method mOnLoadChildrenMethod;
  
  IMediaBrowserServiceCallbacksAdapterApi21(Object paramObject)
  {
    this.mCallbackObject = paramObject;
    try
    {
      paramObject = Class.forName("android.service.media.IMediaBrowserServiceCallbacks");
      Class localClass = Class.forName("android.content.pm.ParceledListSlice");
      this.mAsBinderMethod = ((Class)paramObject).getMethod("asBinder", new Class[0]);
      this.mOnConnectMethod = ((Class)paramObject).getMethod("onConnect", new Class[] { String.class, MediaSession.Token.class, Bundle.class });
      this.mOnConnectFailedMethod = ((Class)paramObject).getMethod("onConnectFailed", new Class[0]);
      this.mOnLoadChildrenMethod = ((Class)paramObject).getMethod("onLoadChildren", new Class[] { String.class, localClass });
      return;
    }
    catch (NoSuchMethodException paramObject)
    {
      ((ReflectiveOperationException)paramObject).printStackTrace();
      return;
    }
    catch (ClassNotFoundException paramObject)
    {
      for (;;) {}
    }
  }
  
  IBinder asBinder()
  {
    try
    {
      IBinder localIBinder = (IBinder)this.mAsBinderMethod.invoke(this.mCallbackObject, new Object[0]);
      return localIBinder;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return null;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
  }
  
  void onConnect(String paramString, Object paramObject, Bundle paramBundle)
    throws RemoteException
  {
    try
    {
      this.mOnConnectMethod.invoke(this.mCallbackObject, new Object[] { paramString, paramObject, paramBundle });
      return;
    }
    catch (IllegalAccessException paramString)
    {
      paramString.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramString)
    {
      for (;;) {}
    }
  }
  
  void onConnectFailed()
    throws RemoteException
  {
    try
    {
      this.mOnConnectFailedMethod.invoke(this.mCallbackObject, new Object[0]);
      return;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      localIllegalAccessException.printStackTrace();
      return;
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      for (;;) {}
    }
  }
  
  void onLoadChildren(String paramString, Object paramObject)
    throws RemoteException
  {
    try
    {
      this.mOnLoadChildrenMethod.invoke(this.mCallbackObject, new Object[] { paramString, paramObject });
      return;
    }
    catch (IllegalAccessException paramString)
    {
      paramString.printStackTrace();
      return;
    }
    catch (InvocationTargetException paramString)
    {
      for (;;) {}
    }
  }
  
  static class Stub
  {
    static Method sAsInterfaceMethod;
    
    static
    {
      try
      {
        sAsInterfaceMethod = Class.forName("android.service.media.IMediaBrowserServiceCallbacks$Stub").getMethod("asInterface", new Class[] { IBinder.class });
        return;
      }
      catch (NoSuchMethodException localNoSuchMethodException)
      {
        localNoSuchMethodException.printStackTrace();
        return;
      }
      catch (ClassNotFoundException localClassNotFoundException)
      {
        for (;;) {}
      }
    }
    
    static Object asInterface(IBinder paramIBinder)
    {
      try
      {
        paramIBinder = sAsInterfaceMethod.invoke(null, new Object[] { paramIBinder });
        return paramIBinder;
      }
      catch (IllegalAccessException paramIBinder)
      {
        paramIBinder.printStackTrace();
        return null;
      }
      catch (InvocationTargetException paramIBinder)
      {
        for (;;) {}
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\media\IMediaBrowserServiceCallbacksAdapterApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */