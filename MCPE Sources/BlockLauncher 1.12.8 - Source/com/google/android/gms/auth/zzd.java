package com.google.android.gms.auth;

import android.accounts.Account;
import android.accounts.AccountManager;
import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.IBinder;
import android.os.RemoteException;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.text.TextUtils;
import android.util.Log;
import com.google.android.gms.common.GooglePlayServicesNotAvailableException;
import com.google.android.gms.common.GooglePlayServicesRepairableException;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.common.zze;
import com.google.android.gms.internal.zzas;
import com.google.android.gms.internal.zzas.zza;
import java.io.IOException;
import java.net.URISyntaxException;
import java.util.List;

public class zzd
{
  public static final int CHANGE_TYPE_ACCOUNT_ADDED = 1;
  public static final int CHANGE_TYPE_ACCOUNT_REMOVED = 2;
  public static final int CHANGE_TYPE_ACCOUNT_RENAMED_FROM = 3;
  public static final int CHANGE_TYPE_ACCOUNT_RENAMED_TO = 4;
  public static final String GOOGLE_ACCOUNT_TYPE = "com.google";
  public static final String KEY_ANDROID_PACKAGE_NAME;
  public static final String KEY_CALLER_UID;
  public static final String KEY_REQUEST_ACTIONS = "request_visible_actions";
  @Deprecated
  public static final String KEY_REQUEST_VISIBLE_ACTIVITIES = "request_visible_actions";
  public static final String KEY_SUPPRESS_PROGRESS_SCREEN = "suppressProgressScreen";
  private static final ComponentName zzVe;
  private static final ComponentName zzVf;
  
  static
  {
    if (Build.VERSION.SDK_INT >= 11)
    {
      KEY_CALLER_UID = "callerUid";
      if (Build.VERSION.SDK_INT < 14) {
        break label58;
      }
    }
    label58:
    for (;;)
    {
      KEY_ANDROID_PACKAGE_NAME = "androidPackageName";
      zzVe = new ComponentName("com.google.android.gms", "com.google.android.gms.auth.GetToken");
      zzVf = new ComponentName("com.google.android.gms", "com.google.android.gms.recovery.RecoveryService");
      return;
      break;
    }
  }
  
  public static void clearToken(Context paramContext, String paramString)
    throws GooglePlayServicesAvailabilityException, GoogleAuthException, IOException
  {
    zzx.zzcE("Calling this from your main thread can lead to deadlock");
    zzad(paramContext);
    final Bundle localBundle = new Bundle();
    String str = paramContext.getApplicationInfo().packageName;
    localBundle.putString("clientPackageName", str);
    if (!localBundle.containsKey(KEY_ANDROID_PACKAGE_NAME)) {
      localBundle.putString(KEY_ANDROID_PACKAGE_NAME, str);
    }
    paramString = new zza()
    {
      public Void zzao(IBinder paramAnonymousIBinder)
        throws RemoteException, IOException, GoogleAuthException
      {
        paramAnonymousIBinder = (Bundle)zzd.zzn(zzas.zza.zza(paramAnonymousIBinder).zza(this.zzVj, localBundle));
        String str = paramAnonymousIBinder.getString("Error");
        if (!paramAnonymousIBinder.getBoolean("booleanResult")) {
          throw new GoogleAuthException(str);
        }
        return null;
      }
    };
    zza(paramContext, zzVe, paramString);
  }
  
  public static List<AccountChangeEvent> getAccountChangeEvents(Context paramContext, final int paramInt, String paramString)
    throws GoogleAuthException, IOException
  {
    zzx.zzh(paramString, "accountName must be provided");
    zzx.zzcE("Calling this from your main thread can lead to deadlock");
    zzad(paramContext);
    paramString = new zza()
    {
      public List<AccountChangeEvent> zzap(IBinder paramAnonymousIBinder)
        throws RemoteException, IOException, GoogleAuthException
      {
        return ((AccountChangeEventsResponse)zzd.zzn(zzas.zza.zza(paramAnonymousIBinder).zza(new AccountChangeEventsRequest().setAccountName(this.zzVl).setEventIndex(paramInt)))).getEvents();
      }
    };
    return (List)zza(paramContext, zzVe, paramString);
  }
  
  public static String getAccountId(Context paramContext, String paramString)
    throws GoogleAuthException, IOException
  {
    zzx.zzh(paramString, "accountName must be provided");
    zzx.zzcE("Calling this from your main thread can lead to deadlock");
    zzad(paramContext);
    return getToken(paramContext, paramString, "^^_account_id_^^", new Bundle());
  }
  
  public static String getToken(Context paramContext, Account paramAccount, String paramString)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, paramAccount, paramString, new Bundle());
  }
  
  public static String getToken(Context paramContext, Account paramAccount, String paramString, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return zzc(paramContext, paramAccount, paramString, paramBundle).getToken();
  }
  
  @Deprecated
  public static String getToken(Context paramContext, String paramString1, String paramString2)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, new Account(paramString1, "com.google"), paramString2);
  }
  
  @Deprecated
  public static String getToken(Context paramContext, String paramString1, String paramString2, Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    return getToken(paramContext, new Account(paramString1, "com.google"), paramString2, paramBundle);
  }
  
  @Deprecated
  @RequiresPermission("android.permission.MANAGE_ACCOUNTS")
  public static void invalidateToken(Context paramContext, String paramString)
  {
    AccountManager.get(paramContext).invalidateAuthToken("com.google", paramString);
  }
  
  /* Error */
  private static <T> T zza(Context paramContext, ComponentName paramComponentName, zza<T> paramzza)
    throws IOException, GoogleAuthException
  {
    // Byte code:
    //   0: new 188	com/google/android/gms/common/zza
    //   3: dup
    //   4: invokespecial 189	com/google/android/gms/common/zza:<init>	()V
    //   7: astore_3
    //   8: aload_0
    //   9: invokestatic 195	com/google/android/gms/common/internal/zzl:zzau	(Landroid/content/Context;)Lcom/google/android/gms/common/internal/zzl;
    //   12: astore 4
    //   14: aload 4
    //   16: aload_1
    //   17: aload_3
    //   18: ldc -59
    //   20: invokevirtual 200	com/google/android/gms/common/internal/zzl:zza	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)Z
    //   23: ifeq +58 -> 81
    //   26: aload_2
    //   27: aload_3
    //   28: invokevirtual 204	com/google/android/gms/common/zza:zzoJ	()Landroid/os/IBinder;
    //   31: invokeinterface 208 2 0
    //   36: astore_0
    //   37: aload 4
    //   39: aload_1
    //   40: aload_3
    //   41: ldc -59
    //   43: invokevirtual 212	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   46: aload_0
    //   47: areturn
    //   48: astore_0
    //   49: ldc -59
    //   51: ldc -42
    //   53: aload_0
    //   54: invokestatic 220	android/util/Log:i	(Ljava/lang/String;Ljava/lang/String;Ljava/lang/Throwable;)I
    //   57: pop
    //   58: new 81	java/io/IOException
    //   61: dup
    //   62: ldc -42
    //   64: aload_0
    //   65: invokespecial 223	java/io/IOException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   68: athrow
    //   69: astore_0
    //   70: aload 4
    //   72: aload_1
    //   73: aload_3
    //   74: ldc -59
    //   76: invokevirtual 212	com/google/android/gms/common/internal/zzl:zzb	(Landroid/content/ComponentName;Landroid/content/ServiceConnection;Ljava/lang/String;)V
    //   79: aload_0
    //   80: athrow
    //   81: new 81	java/io/IOException
    //   84: dup
    //   85: ldc -31
    //   87: invokespecial 227	java/io/IOException:<init>	(Ljava/lang/String;)V
    //   90: athrow
    //   91: astore_0
    //   92: goto -43 -> 49
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	95	0	paramContext	Context
    //   0	95	1	paramComponentName	ComponentName
    //   0	95	2	paramzza	zza<T>
    //   7	67	3	localzza	com.google.android.gms.common.zza
    //   12	59	4	localzzl	com.google.android.gms.common.internal.zzl
    // Exception table:
    //   from	to	target	type
    //   26	37	48	java/lang/InterruptedException
    //   26	37	69	finally
    //   49	69	69	finally
    //   26	37	91	android/os/RemoteException
  }
  
  private static void zzad(Context paramContext)
    throws GoogleAuthException
  {
    try
    {
      zze.zzad(paramContext.getApplicationContext());
      return;
    }
    catch (GooglePlayServicesRepairableException paramContext)
    {
      throw new GooglePlayServicesAvailabilityException(paramContext.getConnectionStatusCode(), paramContext.getMessage(), paramContext.getIntent());
    }
    catch (GooglePlayServicesNotAvailableException paramContext)
    {
      throw new GoogleAuthException(paramContext.getMessage());
    }
  }
  
  public static TokenData zzc(Context paramContext, Account paramAccount, final String paramString, final Bundle paramBundle)
    throws IOException, UserRecoverableAuthException, GoogleAuthException
  {
    zzx.zzcE("Calling this from your main thread can lead to deadlock");
    zzad(paramContext);
    if (paramBundle == null) {}
    for (paramBundle = new Bundle();; paramBundle = new Bundle(paramBundle))
    {
      String str = paramContext.getApplicationInfo().packageName;
      paramBundle.putString("clientPackageName", str);
      if (TextUtils.isEmpty(paramBundle.getString(KEY_ANDROID_PACKAGE_NAME))) {
        paramBundle.putString(KEY_ANDROID_PACKAGE_NAME, str);
      }
      paramBundle.putLong("service_connection_start_time_millis", SystemClock.elapsedRealtime());
      paramAccount = new zza()
      {
        public TokenData zzam(IBinder paramAnonymousIBinder)
          throws RemoteException, IOException, GoogleAuthException
        {
          Object localObject = (Bundle)zzd.zzn(zzas.zza.zza(paramAnonymousIBinder).zza(this.zzVg, paramString, paramBundle));
          paramAnonymousIBinder = TokenData.zzc((Bundle)localObject, "tokenDetails");
          if (paramAnonymousIBinder != null) {
            return paramAnonymousIBinder;
          }
          paramAnonymousIBinder = ((Bundle)localObject).getString("Error");
          localObject = (Intent)((Bundle)localObject).getParcelable("userRecoveryIntent");
          com.google.android.gms.auth.firstparty.shared.zzd localzzd = com.google.android.gms.auth.firstparty.shared.zzd.zzbY(paramAnonymousIBinder);
          if (com.google.android.gms.auth.firstparty.shared.zzd.zza(localzzd)) {
            throw new UserRecoverableAuthException(paramAnonymousIBinder, (Intent)localObject);
          }
          if (com.google.android.gms.auth.firstparty.shared.zzd.zzc(localzzd)) {
            throw new IOException(paramAnonymousIBinder);
          }
          throw new GoogleAuthException(paramAnonymousIBinder);
        }
      };
      return (TokenData)zza(paramContext, zzVe, paramAccount);
    }
  }
  
  static void zzi(Intent paramIntent)
  {
    if (paramIntent == null) {
      throw new IllegalArgumentException("Callback cannot be null.");
    }
    paramIntent = paramIntent.toUri(1);
    try
    {
      Intent.parseUri(paramIntent, 1);
      return;
    }
    catch (URISyntaxException paramIntent)
    {
      throw new IllegalArgumentException("Parameter callback contains invalid data. It must be serializable using toUri() and parseUri().");
    }
  }
  
  private static <T> T zzm(T paramT)
    throws IOException
  {
    if (paramT == null)
    {
      Log.w("GoogleAuthUtil", "Binder call returned null.");
      throw new IOException("Service unavailable.");
    }
    return paramT;
  }
  
  private static abstract interface zza<T>
  {
    public abstract T zzan(IBinder paramIBinder)
      throws RemoteException, IOException, GoogleAuthException;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\auth\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */