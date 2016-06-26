package com.google.android.gms.maps.internal;

import android.content.Context;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesUtil;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.maps.model.RuntimeRemoteException;

public class zzad
{
  private static Context zzaSU;
  private static zzc zzaSV;
  
  private static Context getRemoteContext(Context paramContext)
  {
    if (zzaSU == null) {
      if (!zzAg()) {
        break label23;
      }
    }
    label23:
    for (zzaSU = paramContext.getApplicationContext();; zzaSU = GooglePlayServicesUtil.getRemoteContext(paramContext)) {
      return zzaSU;
    }
  }
  
  public static boolean zzAg()
  {
    return false;
  }
  
  private static Class<?> zzAh()
  {
    try
    {
      Class localClass = Class.forName("com.google.android.gms.maps.internal.CreatorImpl");
      return localClass;
    }
    catch (ClassNotFoundException localClassNotFoundException)
    {
      throw new RuntimeException(localClassNotFoundException);
    }
  }
  
  private static <T> T zza(ClassLoader paramClassLoader, String paramString)
  {
    try
    {
      paramClassLoader = zzd(((ClassLoader)zzx.zzz(paramClassLoader)).loadClass(paramString));
      return paramClassLoader;
    }
    catch (ClassNotFoundException paramClassLoader)
    {
      throw new IllegalStateException("Unable to find dynamic class " + paramString);
    }
  }
  
  public static zzc zzaO(Context paramContext)
    throws GooglePlayServicesNotAvailableException
  {
    zzx.zzz(paramContext);
    if (zzaSV != null) {
      return zzaSV;
    }
    zzaP(paramContext);
    zzaSV = zzaQ(paramContext);
    try
    {
      zzaSV.zzd(zze.zzC(getRemoteContext(paramContext).getResources()), GooglePlayServicesUtil.GOOGLE_PLAY_SERVICES_VERSION_CODE);
      return zzaSV;
    }
    catch (RemoteException paramContext)
    {
      throw new RuntimeRemoteException(paramContext);
    }
  }
  
  private static void zzaP(Context paramContext)
    throws GooglePlayServicesNotAvailableException
  {
    int i = GooglePlayServicesUtil.isGooglePlayServicesAvailable(paramContext);
    switch (i)
    {
    default: 
      throw new GooglePlayServicesNotAvailableException(i);
    }
  }
  
  private static zzc zzaQ(Context paramContext)
  {
    if (zzAg())
    {
      Log.i(zzad.class.getSimpleName(), "Making Creator statically");
      return (zzc)zzd(zzAh());
    }
    Log.i(zzad.class.getSimpleName(), "Making Creator dynamically");
    return zzc.zza.zzcu((IBinder)zza(getRemoteContext(paramContext).getClassLoader(), "com.google.android.gms.maps.internal.CreatorImpl"));
  }
  
  private static <T> T zzd(Class<?> paramClass)
  {
    try
    {
      Object localObject = paramClass.newInstance();
      return (T)localObject;
    }
    catch (InstantiationException localInstantiationException)
    {
      throw new IllegalStateException("Unable to instantiate the dynamic class " + paramClass.getName());
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new IllegalStateException("Unable to call the default constructor of " + paramClass.getName());
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\maps\internal\zzad.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */