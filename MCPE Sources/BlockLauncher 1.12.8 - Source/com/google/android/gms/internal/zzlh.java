package com.google.android.gms.internal;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import com.google.android.gms.auth.api.signin.internal.IdpTokenType;
import com.google.android.gms.auth.api.signin.zzd;
import com.google.android.gms.common.internal.zzx;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;

public class zzlh
  extends zzlg
{
  public static final List<String> zzYf = Collections.singletonList("email");
  private Class<?> zzYg;
  private Class<?> zzYh;
  private Object zzYi;
  private Object zzYj;
  
  public zzlh(Activity paramActivity, List<String> paramList)
  {
    super(paramActivity, zzYf, paramList);
  }
  
  /* Error */
  public static void zzag(Context paramContext)
    throws java.lang.IllegalStateException
  {
    // Byte code:
    //   0: aload_0
    //   1: invokestatic 64	com/google/android/gms/common/internal/zzx:zzz	(Ljava/lang/Object;)Ljava/lang/Object;
    //   4: pop
    //   5: ldc 66
    //   7: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   10: astore_1
    //   11: aload_1
    //   12: ldc 74
    //   14: iconst_2
    //   15: anewarray 68	java/lang/Class
    //   18: dup
    //   19: iconst_0
    //   20: ldc 76
    //   22: aastore
    //   23: dup
    //   24: iconst_1
    //   25: getstatic 81	java/lang/Integer:TYPE	Ljava/lang/Class;
    //   28: aastore
    //   29: invokevirtual 85	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   32: aconst_null
    //   33: iconst_2
    //   34: anewarray 87	java/lang/Object
    //   37: dup
    //   38: iconst_0
    //   39: aload_0
    //   40: invokevirtual 91	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   43: aastore
    //   44: dup
    //   45: iconst_1
    //   46: ldc 92
    //   48: invokestatic 96	java/lang/Integer:valueOf	(I)Ljava/lang/Integer;
    //   51: aastore
    //   52: invokevirtual 102	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   55: pop
    //   56: ldc 104
    //   58: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   61: astore_0
    //   62: aload_0
    //   63: ldc 106
    //   65: iconst_0
    //   66: anewarray 68	java/lang/Class
    //   69: invokevirtual 85	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   72: aconst_null
    //   73: iconst_0
    //   74: anewarray 87	java/lang/Object
    //   77: invokevirtual 102	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   80: astore_1
    //   81: aload_0
    //   82: ldc 108
    //   84: iconst_0
    //   85: anewarray 68	java/lang/Class
    //   88: invokevirtual 85	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   91: aload_1
    //   92: iconst_0
    //   93: anewarray 87	java/lang/Object
    //   96: invokevirtual 102	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   99: pop
    //   100: return
    //   101: astore_0
    //   102: ldc 110
    //   104: ldc 112
    //   106: aload_0
    //   107: invokestatic 118	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   110: pop
    //   111: new 50	java/lang/IllegalStateException
    //   114: dup
    //   115: ldc 120
    //   117: invokespecial 123	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   120: athrow
    //   121: astore_0
    //   122: ldc 125
    //   124: invokestatic 72	java/lang/Class:forName	(Ljava/lang/String;)Ljava/lang/Class;
    //   127: astore_0
    //   128: aload_0
    //   129: ldc 127
    //   131: iconst_0
    //   132: anewarray 68	java/lang/Class
    //   135: invokevirtual 85	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   138: aconst_null
    //   139: iconst_0
    //   140: anewarray 87	java/lang/Object
    //   143: invokevirtual 102	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   146: astore_1
    //   147: aload_1
    //   148: ifnull -48 -> 100
    //   151: aload_0
    //   152: ldc -127
    //   154: iconst_0
    //   155: anewarray 68	java/lang/Class
    //   158: invokevirtual 85	java/lang/Class:getDeclaredMethod	(Ljava/lang/String;[Ljava/lang/Class;)Ljava/lang/reflect/Method;
    //   161: aload_1
    //   162: iconst_0
    //   163: anewarray 87	java/lang/Object
    //   166: invokevirtual 102	java/lang/reflect/Method:invoke	(Ljava/lang/Object;[Ljava/lang/Object;)Ljava/lang/Object;
    //   169: pop
    //   170: return
    //   171: astore_0
    //   172: ldc 110
    //   174: ldc 112
    //   176: aload_0
    //   177: invokestatic 118	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   180: pop
    //   181: new 50	java/lang/IllegalStateException
    //   184: dup
    //   185: ldc 120
    //   187: invokespecial 123	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   190: athrow
    //   191: astore_0
    //   192: goto -20 -> 172
    //   195: astore_0
    //   196: goto -24 -> 172
    //   199: astore_0
    //   200: goto -28 -> 172
    //   203: astore_0
    //   204: goto -102 -> 102
    //   207: astore_0
    //   208: goto -106 -> 102
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	211	0	paramContext	Context
    //   10	152	1	localObject	Object
    // Exception table:
    //   from	to	target	type
    //   11	100	101	java/lang/NoSuchMethodException
    //   5	11	121	java/lang/ClassNotFoundException
    //   11	100	121	java/lang/ClassNotFoundException
    //   102	121	121	java/lang/ClassNotFoundException
    //   122	147	171	java/lang/ClassNotFoundException
    //   151	170	171	java/lang/ClassNotFoundException
    //   122	147	191	java/lang/NoSuchMethodException
    //   151	170	191	java/lang/NoSuchMethodException
    //   122	147	195	java/lang/IllegalAccessException
    //   151	170	195	java/lang/IllegalAccessException
    //   122	147	199	java/lang/reflect/InvocationTargetException
    //   151	170	199	java/lang/reflect/InvocationTargetException
    //   11	100	203	java/lang/IllegalAccessException
    //   11	100	207	java/lang/reflect/InvocationTargetException
  }
  
  private void zznv()
    throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException
  {
    if (this.zzYj != null)
    {
      Class.forName("com.facebook.login.LoginManager").getDeclaredMethod("logInWithReadPermissions", new Class[] { Activity.class, Collection.class }).invoke(this.zzYj, new Object[] { this.mActivity, new ArrayList(zzns()) });
      return;
    }
    Class localClass1 = Class.forName("com.facebook.Session$OpenRequest");
    Object localObject1 = localClass1.getConstructor(new Class[] { Activity.class }).newInstance(new Object[] { this.mActivity });
    localClass1.getDeclaredMethod("setPermissions", new Class[] { List.class }).invoke(localObject1, new Object[] { new ArrayList(zzns()) });
    localClass1.getDeclaredMethod("setRequestCode", new Class[] { Integer.TYPE }).invoke(localObject1, new Object[] { Integer.valueOf(64206) });
    Class localClass2 = Class.forName("com.facebook.Session");
    localClass1.getDeclaredMethod("setCallback", new Class[] { Class.forName("com.facebook.Session$StatusCallback") }).invoke(localObject1, new Object[] { zznw() });
    Object localObject2 = localClass2.getConstructor(new Class[] { Context.class }).newInstance(new Object[] { this.mActivity });
    localClass2.getDeclaredMethod("setActiveSession", new Class[] { localClass2 }).invoke(null, new Object[] { localObject2 });
    localClass2.getDeclaredMethod("openForRead", new Class[] { localClass1 }).invoke(localObject2, new Object[] { localObject1 });
  }
  
  private Object zznw()
    throws ClassNotFoundException
  {
    final Object localObject = Class.forName("com.facebook.Session");
    Class localClass = Class.forName("com.facebook.Session$StatusCallback");
    ClassLoader localClassLoader = localClass.getClassLoader();
    localObject = new InvocationHandler()
    {
      public Object invoke(Object paramAnonymousObject, Method paramAnonymousMethod, Object[] paramAnonymousArrayOfObject)
        throws Throwable
      {
        paramAnonymousObject = Class.forName("com.facebook.SessionState");
        Class[] arrayOfClass = paramAnonymousMethod.getParameterTypes();
        if ((!paramAnonymousMethod.getName().equals("call")) || (arrayOfClass.length != 3) || (arrayOfClass[0] != localObject) || (arrayOfClass[1] != paramAnonymousObject) || (arrayOfClass[2] != Exception.class)) {
          throw new ExceptionInInitializerError("Method not supported!");
        }
        if (((Boolean)localObject.getDeclaredMethod("isOpened", new Class[0]).invoke(paramAnonymousArrayOfObject[0], new Object[0])).booleanValue())
        {
          paramAnonymousObject = (String)localObject.getDeclaredMethod("getAccessToken", new Class[0]).invoke(paramAnonymousArrayOfObject[0], new Object[0]);
          zzlh.zzb(zzlh.this).zzk(zzlh.zza(zzlh.this, IdpTokenType.zzXA, (String)paramAnonymousObject, zzlh.zza(zzlh.this)));
        }
        return null;
      }
    };
    return Proxy.newProxyInstance(localClassLoader, new Class[] { localClass }, (InvocationHandler)localObject);
  }
  
  public void zza(zzlf.zza paramzza)
  {
    zzb(null, null, (zzlf.zza)zzx.zzz(paramzza));
    try
    {
      zznv();
      return;
    }
    catch (ClassNotFoundException paramzza)
    {
      throw new RuntimeException(paramzza);
    }
    catch (NoSuchMethodException paramzza)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramzza)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramzza)
    {
      for (;;) {}
    }
    catch (InstantiationException paramzza)
    {
      for (;;) {}
    }
  }
  
  public void zza(String paramString, zzlf.zza paramzza)
  {
    zzb((String)zzx.zzz(paramString), null, (zzlf.zza)zzx.zzz(paramzza));
    try
    {
      zznv();
      return;
    }
    catch (ClassNotFoundException paramString)
    {
      throw new RuntimeException(paramString);
    }
    catch (NoSuchMethodException paramString)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramString)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramString)
    {
      for (;;) {}
    }
    catch (InstantiationException paramString)
    {
      for (;;) {}
    }
  }
  
  public void zza(String paramString1, String paramString2, zzlf.zza paramzza)
  {
    zzb((String)zzx.zzz(paramString1), (String)zzx.zzz(paramString2), (zzlf.zza)zzx.zzz(paramzza));
    try
    {
      zznv();
      return;
    }
    catch (ClassNotFoundException paramString1)
    {
      throw new RuntimeException(paramString1);
    }
    catch (NoSuchMethodException paramString1)
    {
      for (;;) {}
    }
    catch (IllegalAccessException paramString1)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramString1)
    {
      for (;;) {}
    }
    catch (InstantiationException paramString1)
    {
      for (;;) {}
    }
  }
  
  public boolean zza(int paramInt1, int paramInt2, Intent paramIntent, zzlf.zza paramzza)
  {
    zzb(paramzza);
    if ((paramInt1 != 64206) && (this.zzYg == null)) {
      return false;
    }
    if ((this.zzYg != null) && (this.zzYh != null) && (this.zzYi != null)) {}
    try
    {
      if (!((Boolean)this.zzYg.getDeclaredMethod("isFacebookRequestCode", new Class[] { Integer.TYPE }).invoke(null, new Object[] { Integer.valueOf(paramInt1) })).booleanValue()) {
        return false;
      }
      bool = ((Boolean)this.zzYh.getDeclaredMethod("onActivityResult", new Class[] { Integer.TYPE, Integer.TYPE, Intent.class }).invoke(this.zzYi, new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramIntent })).booleanValue();
      return bool;
    }
    catch (NoSuchMethodException paramIntent)
    {
      boolean bool;
      throw new RuntimeException(paramIntent);
      try
      {
        Object localObject = Class.forName("com.facebook.Session");
        paramzza = ((Class)localObject).getDeclaredMethod("getActiveSession", new Class[0]).invoke(null, new Object[0]);
        localObject = ((Class)localObject).getDeclaredMethod("onActivityResult", new Class[] { Activity.class, Integer.TYPE, Integer.TYPE, Intent.class });
        if (paramzza == null) {
          return false;
        }
        bool = ((Boolean)((Method)localObject).invoke(paramzza, new Object[] { this.mActivity, Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), paramIntent })).booleanValue();
        return bool;
      }
      catch (NoSuchMethodException paramIntent)
      {
        throw new RuntimeException(paramIntent);
      }
      catch (ClassNotFoundException paramIntent)
      {
        for (;;) {}
      }
      catch (IllegalAccessException paramIntent)
      {
        for (;;) {}
      }
      catch (InvocationTargetException paramIntent)
      {
        for (;;) {}
      }
    }
    catch (IllegalAccessException paramIntent)
    {
      for (;;) {}
    }
    catch (InvocationTargetException paramIntent)
    {
      for (;;) {}
    }
  }
  
  public zzd zzmU()
  {
    return zzd.zzXi;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzlh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */