package com.microsoft.onlineid.internal.sso.client.request;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.exception.AuthenticationException;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.internal.sso.BundleMarshaller;
import com.microsoft.onlineid.internal.sso.SsoService;
import com.microsoft.onlineid.internal.sso.SsoServiceError;
import com.microsoft.onlineid.internal.sso.client.ServiceBindingException;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService;
import com.microsoft.onlineid.internal.sso.service.IMsaSsoService.Stub;
import com.microsoft.onlineid.internal.sso.service.MsaSsoService;
import com.microsoft.onlineid.internal.storage.TypedStorage;
import com.microsoft.onlineid.sts.ServerConfig;
import com.microsoft.onlineid.sts.ServerConfig.Int;
import java.util.Locale;

public abstract class SingleSsoRequest<T>
  implements ServiceConnection
{
  static final String MaxTriesErrorMessage = "Max SSO tries exceeded.";
  static final int MaxWaitTimeForServiceBindingInMillis = 3000;
  protected final Context _applicationContext;
  protected final Bundle _clientState;
  protected final ServerConfig _config;
  private final Object _lock = new Object();
  protected IMsaSsoService _msaSsoService;
  protected boolean _serviceConnected;
  protected final TypedStorage _storage;
  
  public SingleSsoRequest(Context paramContext, Bundle paramBundle)
  {
    this._applicationContext = paramContext;
    this._clientState = paramBundle;
    this._config = new ServerConfig(paramContext);
    this._storage = new TypedStorage(paramContext);
    this._msaSsoService = null;
  }
  
  protected static void checkForErrors(Bundle paramBundle)
    throws AuthenticationException
  {
    checkForErrors(paramBundle, true);
  }
  
  static void checkForErrors(Bundle paramBundle, boolean paramBoolean)
    throws AuthenticationException
  {
    if (BundleMarshaller.hasError(paramBundle))
    {
      if (paramBoolean)
      {
        SsoServiceError localSsoServiceError = SsoServiceError.get(paramBundle.getInt("com.microsoft.onlineid.error_code"));
        String str = paramBundle.getString("com.microsoft.onlineid.error_message");
        Logger.error(String.format(Locale.US, "%s: %s, %s", new Object[] { "SSO error", localSsoServiceError.name(), str }));
        ClientAnalytics.get().logEvent("SDK", "SSO error", localSsoServiceError.name() + ": " + str);
      }
      throw BundleMarshaller.exceptionFromBundle(paramBundle);
    }
  }
  
  boolean bind(SsoService paramSsoService)
  {
    Intent localIntent = new Intent("com.microsoft.msa.action.SSO_SERVICE").setPackage(paramSsoService.getPackageName());
    Logger.info(this._applicationContext.getPackageName() + " attempting to bind to: " + paramSsoService.getPackageName() + " [" + getClass().getSimpleName() + "]");
    return this._applicationContext.bindService(localIntent, this, 1);
  }
  
  public Bundle getDefaultCallingParams()
  {
    Bundle localBundle1 = new Bundle();
    try
    {
      Bundle localBundle2 = this._applicationContext.getPackageManager().getServiceInfo(new ComponentName(this._applicationContext, MsaSsoService.class.getName()), 128).metaData;
      localBundle1.putString("com.microsoft.onlineid.client_package_name", this._applicationContext.getPackageName());
      localBundle1.putInt("com.microsoft.onlineid.client_sso_version", localBundle2.getInt("com.microsoft.msa.service.sso_version"));
      localBundle1.putString("com.microsoft.onlineid.client_sdk_version", localBundle2.getString("com.microsoft.msa.service.sdk_version"));
      localBundle1.putString("com.microsoft.onlineid.client_config_version", this._config.getString(ServerConfig.Version));
      localBundle1.putLong("com.microsoft.onlineid.client_config_last_downloaded_time", this._storage.readConfigLastDownloadedTime());
      localBundle1.putBundle("com.microsoft.onlineid.client_state", this._clientState);
      return localBundle1;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Logger.error("Could not find calling SSO service meta-data.", localNameNotFoundException);
    }
    return localBundle1;
  }
  
  boolean getIsServiceConnected()
  {
    return this._serviceConnected;
  }
  
  public void onServiceConnected(ComponentName arg1, IBinder paramIBinder)
  {
    this._msaSsoService = IMsaSsoService.Stub.asInterface(paramIBinder);
    synchronized (this._lock)
    {
      this._serviceConnected = true;
      this._lock.notify();
      return;
    }
  }
  
  public void onServiceDisconnected(ComponentName paramComponentName)
  {
    this._msaSsoService = null;
    this._serviceConnected = false;
  }
  
  public T performRequest(SsoService paramSsoService)
    throws AuthenticationException
  {
    int j = this._config.getInt(ServerConfig.Int.MaxTriesForSsoRequestToSingleService);
    int i = j;
    if (j < 1)
    {
      localObject = "Invalid MaxTriesForSsoRequestToSingleService: " + j;
      Logger.error((String)localObject);
      ClientAnalytics.get().logEvent("SDK", "SSO fallback", (String)localObject);
      i = 1;
    }
    j = 1;
    Object localObject = null;
    while (j <= i) {
      try
      {
        localObject = tryPerformRequest(paramSsoService);
        return (T)localObject;
      }
      catch (ServiceBindingException localServiceBindingException)
      {
        j += 1;
      }
    }
    throw new ServiceBindingException("Max SSO tries exceeded.", localServiceBindingException);
  }
  
  protected abstract T performRequestTask()
    throws AuthenticationException, RemoteException;
  
  /* Error */
  public T tryPerformRequest(SsoService paramSsoService)
    throws AuthenticationException
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore 7
    //   3: iconst_0
    //   4: istore 8
    //   6: iconst_0
    //   7: istore 9
    //   9: iconst_0
    //   10: istore 6
    //   12: iload 6
    //   14: istore_3
    //   15: iload 7
    //   17: istore_2
    //   18: iload 8
    //   20: istore 4
    //   22: iload 9
    //   24: istore 5
    //   26: aload_0
    //   27: aload_1
    //   28: invokevirtual 317	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:bind	(Lcom/microsoft/onlineid/internal/sso/SsoService;)Z
    //   31: ifeq +391 -> 422
    //   34: iconst_1
    //   35: istore 7
    //   37: iconst_1
    //   38: istore 8
    //   40: iconst_1
    //   41: istore 9
    //   43: iconst_1
    //   44: istore 6
    //   46: iload 6
    //   48: istore_3
    //   49: iload 7
    //   51: istore_2
    //   52: iload 8
    //   54: istore 4
    //   56: iload 9
    //   58: istore 5
    //   60: aload_0
    //   61: getfield 35	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:_lock	Ljava/lang/Object;
    //   64: astore 10
    //   66: iload 6
    //   68: istore_3
    //   69: iload 7
    //   71: istore_2
    //   72: iload 8
    //   74: istore 4
    //   76: iload 9
    //   78: istore 5
    //   80: aload 10
    //   82: monitorenter
    //   83: aload_0
    //   84: getfield 267	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:_serviceConnected	Z
    //   87: ifne +13 -> 100
    //   90: aload_0
    //   91: getfield 35	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:_lock	Ljava/lang/Object;
    //   94: ldc2_w 318
    //   97: invokevirtual 323	java/lang/Object:wait	(J)V
    //   100: aload 10
    //   102: monitorexit
    //   103: iload 6
    //   105: istore_3
    //   106: iload 7
    //   108: istore_2
    //   109: iload 8
    //   111: istore 4
    //   113: iload 9
    //   115: istore 5
    //   117: aload_0
    //   118: getfield 267	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:_serviceConnected	Z
    //   121: ifeq +112 -> 233
    //   124: iload 6
    //   126: istore_3
    //   127: iload 7
    //   129: istore_2
    //   130: iload 8
    //   132: istore 4
    //   134: iload 9
    //   136: istore 5
    //   138: new 123	java/lang/StringBuilder
    //   141: dup
    //   142: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   145: ldc_w 325
    //   148: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: aload_1
    //   152: invokevirtual 156	com/microsoft/onlineid/internal/sso/SsoService:getPackageName	()Ljava/lang/String;
    //   155: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   158: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   161: invokestatic 181	com/microsoft/onlineid/internal/log/Logger:info	(Ljava/lang/String;)V
    //   164: iload 6
    //   166: istore_3
    //   167: iload 7
    //   169: istore_2
    //   170: iload 8
    //   172: istore 4
    //   174: iload 9
    //   176: istore 5
    //   178: aload_0
    //   179: invokevirtual 327	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:performRequestTask	()Ljava/lang/Object;
    //   182: astore 10
    //   184: iconst_1
    //   185: ifeq +7 -> 192
    //   188: aload_0
    //   189: invokevirtual 330	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:unbind	()V
    //   192: aload 10
    //   194: areturn
    //   195: astore 11
    //   197: aload 10
    //   199: monitorexit
    //   200: iload 6
    //   202: istore_3
    //   203: iload 7
    //   205: istore_2
    //   206: iload 8
    //   208: istore 4
    //   210: iload 9
    //   212: istore 5
    //   214: aload 11
    //   216: athrow
    //   217: astore_1
    //   218: iload_3
    //   219: istore_2
    //   220: aload_1
    //   221: athrow
    //   222: astore_1
    //   223: iload_2
    //   224: ifeq +7 -> 231
    //   227: aload_0
    //   228: invokevirtual 330	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:unbind	()V
    //   231: aload_1
    //   232: athrow
    //   233: iload 6
    //   235: istore_3
    //   236: iload 7
    //   238: istore_2
    //   239: iload 8
    //   241: istore 4
    //   243: iload 9
    //   245: istore 5
    //   247: new 123	java/lang/StringBuilder
    //   250: dup
    //   251: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   254: ldc_w 332
    //   257: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   260: sipush 3000
    //   263: invokestatic 336	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   266: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   269: ldc_w 338
    //   272: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   275: aload_1
    //   276: invokevirtual 156	com/microsoft/onlineid/internal/sso/SsoService:getPackageName	()Ljava/lang/String;
    //   279: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   282: ldc -89
    //   284: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   287: aload_0
    //   288: invokevirtual 171	java/lang/Object:getClass	()Ljava/lang/Class;
    //   291: invokevirtual 176	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   294: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   297: ldc -78
    //   299: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   302: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   305: astore 10
    //   307: iload 6
    //   309: istore_3
    //   310: iload 7
    //   312: istore_2
    //   313: iload 8
    //   315: istore 4
    //   317: iload 9
    //   319: istore 5
    //   321: aload 10
    //   323: invokestatic 341	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;)V
    //   326: iload 6
    //   328: istore_3
    //   329: iload 7
    //   331: istore_2
    //   332: iload 8
    //   334: istore 4
    //   336: iload 9
    //   338: istore 5
    //   340: new 284	com/microsoft/onlineid/internal/sso/client/ServiceBindingException
    //   343: dup
    //   344: aload 10
    //   346: invokespecial 342	com/microsoft/onlineid/internal/sso/client/ServiceBindingException:<init>	(Ljava/lang/String;)V
    //   349: athrow
    //   350: astore 10
    //   352: iload 4
    //   354: istore_2
    //   355: new 123	java/lang/StringBuilder
    //   358: dup
    //   359: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   362: ldc_w 344
    //   365: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   368: aload_1
    //   369: invokevirtual 156	com/microsoft/onlineid/internal/sso/SsoService:getPackageName	()Ljava/lang/String;
    //   372: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   375: ldc_w 346
    //   378: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   381: ldc -89
    //   383: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   386: aload_0
    //   387: invokevirtual 171	java/lang/Object:getClass	()Ljava/lang/Class;
    //   390: invokevirtual 176	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   393: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   396: ldc -78
    //   398: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   401: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   404: aload 10
    //   406: invokestatic 263	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   409: iload 4
    //   411: istore_2
    //   412: new 284	com/microsoft/onlineid/internal/sso/client/ServiceBindingException
    //   415: dup
    //   416: aload 10
    //   418: invokespecial 349	com/microsoft/onlineid/internal/sso/client/ServiceBindingException:<init>	(Ljava/lang/Throwable;)V
    //   421: athrow
    //   422: iload 6
    //   424: istore_3
    //   425: iload 7
    //   427: istore_2
    //   428: iload 8
    //   430: istore 4
    //   432: iload 9
    //   434: istore 5
    //   436: new 123	java/lang/StringBuilder
    //   439: dup
    //   440: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   443: ldc_w 351
    //   446: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   449: aload_1
    //   450: invokevirtual 156	com/microsoft/onlineid/internal/sso/SsoService:getPackageName	()Ljava/lang/String;
    //   453: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   456: ldc -89
    //   458: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   461: aload_0
    //   462: invokevirtual 171	java/lang/Object:getClass	()Ljava/lang/Class;
    //   465: invokevirtual 176	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   468: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   471: ldc -78
    //   473: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   476: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   479: astore 10
    //   481: iload 6
    //   483: istore_3
    //   484: iload 7
    //   486: istore_2
    //   487: iload 8
    //   489: istore 4
    //   491: iload 9
    //   493: istore 5
    //   495: aload 10
    //   497: invokestatic 114	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;)V
    //   500: iload 6
    //   502: istore_3
    //   503: iload 7
    //   505: istore_2
    //   506: iload 8
    //   508: istore 4
    //   510: iload 9
    //   512: istore 5
    //   514: new 284	com/microsoft/onlineid/internal/sso/client/ServiceBindingException
    //   517: dup
    //   518: aload 10
    //   520: invokespecial 342	com/microsoft/onlineid/internal/sso/client/ServiceBindingException:<init>	(Ljava/lang/String;)V
    //   523: athrow
    //   524: astore_1
    //   525: iload 5
    //   527: istore_2
    //   528: ldc_w 353
    //   531: aload_1
    //   532: invokestatic 263	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   535: iload 5
    //   537: istore_2
    //   538: new 355	com/microsoft/onlineid/exception/InternalException
    //   541: dup
    //   542: aload_1
    //   543: invokespecial 356	com/microsoft/onlineid/exception/InternalException:<init>	(Ljava/lang/Throwable;)V
    //   546: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	547	0	this	SingleSsoRequest
    //   0	547	1	paramSsoService	SsoService
    //   17	521	2	i	int
    //   14	489	3	j	int
    //   20	489	4	k	int
    //   24	512	5	m	int
    //   10	491	6	n	int
    //   1	503	7	i1	int
    //   4	503	8	i2	int
    //   7	504	9	i3	int
    //   64	281	10	localObject1	Object
    //   350	67	10	localSecurityException	SecurityException
    //   479	40	10	str	String
    //   195	20	11	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   83	100	195	finally
    //   100	103	195	finally
    //   197	200	195	finally
    //   26	34	217	com/microsoft/onlineid/exception/AuthenticationException
    //   60	66	217	com/microsoft/onlineid/exception/AuthenticationException
    //   80	83	217	com/microsoft/onlineid/exception/AuthenticationException
    //   117	124	217	com/microsoft/onlineid/exception/AuthenticationException
    //   138	164	217	com/microsoft/onlineid/exception/AuthenticationException
    //   178	184	217	com/microsoft/onlineid/exception/AuthenticationException
    //   214	217	217	com/microsoft/onlineid/exception/AuthenticationException
    //   247	307	217	com/microsoft/onlineid/exception/AuthenticationException
    //   321	326	217	com/microsoft/onlineid/exception/AuthenticationException
    //   340	350	217	com/microsoft/onlineid/exception/AuthenticationException
    //   436	481	217	com/microsoft/onlineid/exception/AuthenticationException
    //   495	500	217	com/microsoft/onlineid/exception/AuthenticationException
    //   514	524	217	com/microsoft/onlineid/exception/AuthenticationException
    //   26	34	222	finally
    //   60	66	222	finally
    //   80	83	222	finally
    //   117	124	222	finally
    //   138	164	222	finally
    //   178	184	222	finally
    //   214	217	222	finally
    //   220	222	222	finally
    //   247	307	222	finally
    //   321	326	222	finally
    //   340	350	222	finally
    //   355	409	222	finally
    //   412	422	222	finally
    //   436	481	222	finally
    //   495	500	222	finally
    //   514	524	222	finally
    //   528	535	222	finally
    //   538	547	222	finally
    //   26	34	350	java/lang/SecurityException
    //   60	66	350	java/lang/SecurityException
    //   80	83	350	java/lang/SecurityException
    //   117	124	350	java/lang/SecurityException
    //   138	164	350	java/lang/SecurityException
    //   178	184	350	java/lang/SecurityException
    //   214	217	350	java/lang/SecurityException
    //   247	307	350	java/lang/SecurityException
    //   321	326	350	java/lang/SecurityException
    //   340	350	350	java/lang/SecurityException
    //   436	481	350	java/lang/SecurityException
    //   495	500	350	java/lang/SecurityException
    //   514	524	350	java/lang/SecurityException
    //   26	34	524	java/lang/Exception
    //   60	66	524	java/lang/Exception
    //   80	83	524	java/lang/Exception
    //   117	124	524	java/lang/Exception
    //   138	164	524	java/lang/Exception
    //   178	184	524	java/lang/Exception
    //   214	217	524	java/lang/Exception
    //   247	307	524	java/lang/Exception
    //   321	326	524	java/lang/Exception
    //   340	350	524	java/lang/Exception
    //   436	481	524	java/lang/Exception
    //   495	500	524	java/lang/Exception
    //   514	524	524	java/lang/Exception
  }
  
  void unbind()
  {
    this._serviceConnected = false;
    this._msaSsoService = null;
    this._applicationContext.unbindService(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\client\request\SingleSsoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */