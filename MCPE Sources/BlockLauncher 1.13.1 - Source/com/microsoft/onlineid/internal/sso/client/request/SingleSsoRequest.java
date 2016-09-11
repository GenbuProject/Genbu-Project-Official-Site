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
  
  protected boolean bind(SsoService paramSsoService)
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
    //   31: ifeq +387 -> 418
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
    //   121: ifeq +108 -> 229
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
    //   184: aload_0
    //   185: invokevirtual 330	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:unbind	()V
    //   188: aload 10
    //   190: areturn
    //   191: astore 11
    //   193: aload 10
    //   195: monitorexit
    //   196: iload 6
    //   198: istore_3
    //   199: iload 7
    //   201: istore_2
    //   202: iload 8
    //   204: istore 4
    //   206: iload 9
    //   208: istore 5
    //   210: aload 11
    //   212: athrow
    //   213: astore_1
    //   214: iload_3
    //   215: istore_2
    //   216: aload_1
    //   217: athrow
    //   218: astore_1
    //   219: iload_2
    //   220: ifeq +7 -> 227
    //   223: aload_0
    //   224: invokevirtual 330	com/microsoft/onlineid/internal/sso/client/request/SingleSsoRequest:unbind	()V
    //   227: aload_1
    //   228: athrow
    //   229: iload 6
    //   231: istore_3
    //   232: iload 7
    //   234: istore_2
    //   235: iload 8
    //   237: istore 4
    //   239: iload 9
    //   241: istore 5
    //   243: new 123	java/lang/StringBuilder
    //   246: dup
    //   247: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   250: ldc_w 332
    //   253: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   256: sipush 3000
    //   259: invokestatic 336	java/lang/String:valueOf	(I)Ljava/lang/String;
    //   262: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   265: ldc_w 338
    //   268: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   271: aload_1
    //   272: invokevirtual 156	com/microsoft/onlineid/internal/sso/SsoService:getPackageName	()Ljava/lang/String;
    //   275: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   278: ldc -89
    //   280: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   283: aload_0
    //   284: invokevirtual 171	java/lang/Object:getClass	()Ljava/lang/Class;
    //   287: invokevirtual 176	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   290: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   293: ldc -78
    //   295: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   298: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   301: astore 10
    //   303: iload 6
    //   305: istore_3
    //   306: iload 7
    //   308: istore_2
    //   309: iload 8
    //   311: istore 4
    //   313: iload 9
    //   315: istore 5
    //   317: aload 10
    //   319: invokestatic 341	com/microsoft/onlineid/internal/log/Logger:warning	(Ljava/lang/String;)V
    //   322: iload 6
    //   324: istore_3
    //   325: iload 7
    //   327: istore_2
    //   328: iload 8
    //   330: istore 4
    //   332: iload 9
    //   334: istore 5
    //   336: new 284	com/microsoft/onlineid/internal/sso/client/ServiceBindingException
    //   339: dup
    //   340: aload 10
    //   342: invokespecial 342	com/microsoft/onlineid/internal/sso/client/ServiceBindingException:<init>	(Ljava/lang/String;)V
    //   345: athrow
    //   346: astore 10
    //   348: iload 4
    //   350: istore_2
    //   351: new 123	java/lang/StringBuilder
    //   354: dup
    //   355: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   358: ldc_w 344
    //   361: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   364: aload_1
    //   365: invokevirtual 156	com/microsoft/onlineid/internal/sso/SsoService:getPackageName	()Ljava/lang/String;
    //   368: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   371: ldc_w 346
    //   374: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   377: ldc -89
    //   379: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   382: aload_0
    //   383: invokevirtual 171	java/lang/Object:getClass	()Ljava/lang/Class;
    //   386: invokevirtual 176	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   389: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   392: ldc -78
    //   394: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   397: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   400: aload 10
    //   402: invokestatic 263	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   405: iload 4
    //   407: istore_2
    //   408: new 284	com/microsoft/onlineid/internal/sso/client/ServiceBindingException
    //   411: dup
    //   412: aload 10
    //   414: invokespecial 349	com/microsoft/onlineid/internal/sso/client/ServiceBindingException:<init>	(Ljava/lang/Throwable;)V
    //   417: athrow
    //   418: iload 6
    //   420: istore_3
    //   421: iload 7
    //   423: istore_2
    //   424: iload 8
    //   426: istore 4
    //   428: iload 9
    //   430: istore 5
    //   432: new 123	java/lang/StringBuilder
    //   435: dup
    //   436: invokespecial 124	java/lang/StringBuilder:<init>	()V
    //   439: ldc_w 351
    //   442: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   445: aload_1
    //   446: invokevirtual 156	com/microsoft/onlineid/internal/sso/SsoService:getPackageName	()Ljava/lang/String;
    //   449: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   452: ldc -89
    //   454: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   457: aload_0
    //   458: invokevirtual 171	java/lang/Object:getClass	()Ljava/lang/Class;
    //   461: invokevirtual 176	java/lang/Class:getSimpleName	()Ljava/lang/String;
    //   464: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   467: ldc -78
    //   469: invokevirtual 128	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   472: invokevirtual 133	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   475: astore 10
    //   477: iload 6
    //   479: istore_3
    //   480: iload 7
    //   482: istore_2
    //   483: iload 8
    //   485: istore 4
    //   487: iload 9
    //   489: istore 5
    //   491: aload 10
    //   493: invokestatic 114	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;)V
    //   496: iload 6
    //   498: istore_3
    //   499: iload 7
    //   501: istore_2
    //   502: iload 8
    //   504: istore 4
    //   506: iload 9
    //   508: istore 5
    //   510: new 284	com/microsoft/onlineid/internal/sso/client/ServiceBindingException
    //   513: dup
    //   514: aload 10
    //   516: invokespecial 342	com/microsoft/onlineid/internal/sso/client/ServiceBindingException:<init>	(Ljava/lang/String;)V
    //   519: athrow
    //   520: astore_1
    //   521: iload 5
    //   523: istore_2
    //   524: ldc_w 353
    //   527: aload_1
    //   528: invokestatic 263	com/microsoft/onlineid/internal/log/Logger:error	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   531: iload 5
    //   533: istore_2
    //   534: new 355	com/microsoft/onlineid/exception/InternalException
    //   537: dup
    //   538: aload_1
    //   539: invokespecial 356	com/microsoft/onlineid/exception/InternalException:<init>	(Ljava/lang/Throwable;)V
    //   542: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	543	0	this	SingleSsoRequest
    //   0	543	1	paramSsoService	SsoService
    //   17	517	2	i	int
    //   14	485	3	j	int
    //   20	485	4	k	int
    //   24	508	5	m	int
    //   10	487	6	n	int
    //   1	499	7	i1	int
    //   4	499	8	i2	int
    //   7	500	9	i3	int
    //   64	277	10	localObject1	Object
    //   346	67	10	localSecurityException	SecurityException
    //   475	40	10	str	String
    //   191	20	11	localObject2	Object
    // Exception table:
    //   from	to	target	type
    //   83	100	191	finally
    //   100	103	191	finally
    //   193	196	191	finally
    //   26	34	213	com/microsoft/onlineid/exception/AuthenticationException
    //   60	66	213	com/microsoft/onlineid/exception/AuthenticationException
    //   80	83	213	com/microsoft/onlineid/exception/AuthenticationException
    //   117	124	213	com/microsoft/onlineid/exception/AuthenticationException
    //   138	164	213	com/microsoft/onlineid/exception/AuthenticationException
    //   178	184	213	com/microsoft/onlineid/exception/AuthenticationException
    //   210	213	213	com/microsoft/onlineid/exception/AuthenticationException
    //   243	303	213	com/microsoft/onlineid/exception/AuthenticationException
    //   317	322	213	com/microsoft/onlineid/exception/AuthenticationException
    //   336	346	213	com/microsoft/onlineid/exception/AuthenticationException
    //   432	477	213	com/microsoft/onlineid/exception/AuthenticationException
    //   491	496	213	com/microsoft/onlineid/exception/AuthenticationException
    //   510	520	213	com/microsoft/onlineid/exception/AuthenticationException
    //   26	34	218	finally
    //   60	66	218	finally
    //   80	83	218	finally
    //   117	124	218	finally
    //   138	164	218	finally
    //   178	184	218	finally
    //   210	213	218	finally
    //   216	218	218	finally
    //   243	303	218	finally
    //   317	322	218	finally
    //   336	346	218	finally
    //   351	405	218	finally
    //   408	418	218	finally
    //   432	477	218	finally
    //   491	496	218	finally
    //   510	520	218	finally
    //   524	531	218	finally
    //   534	543	218	finally
    //   26	34	346	java/lang/SecurityException
    //   60	66	346	java/lang/SecurityException
    //   80	83	346	java/lang/SecurityException
    //   117	124	346	java/lang/SecurityException
    //   138	164	346	java/lang/SecurityException
    //   178	184	346	java/lang/SecurityException
    //   210	213	346	java/lang/SecurityException
    //   243	303	346	java/lang/SecurityException
    //   317	322	346	java/lang/SecurityException
    //   336	346	346	java/lang/SecurityException
    //   432	477	346	java/lang/SecurityException
    //   491	496	346	java/lang/SecurityException
    //   510	520	346	java/lang/SecurityException
    //   26	34	520	java/lang/Exception
    //   60	66	520	java/lang/Exception
    //   80	83	520	java/lang/Exception
    //   117	124	520	java/lang/Exception
    //   138	164	520	java/lang/Exception
    //   178	184	520	java/lang/Exception
    //   210	213	520	java/lang/Exception
    //   243	303	520	java/lang/Exception
    //   317	322	520	java/lang/Exception
    //   336	346	520	java/lang/Exception
    //   432	477	520	java/lang/Exception
    //   491	496	520	java/lang/Exception
    //   510	520	520	java/lang/Exception
  }
  
  protected void unbind()
  {
    this._serviceConnected = false;
    this._msaSsoService = null;
    this._applicationContext.unbindService(this);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\internal\sso\client\request\SingleSsoRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */