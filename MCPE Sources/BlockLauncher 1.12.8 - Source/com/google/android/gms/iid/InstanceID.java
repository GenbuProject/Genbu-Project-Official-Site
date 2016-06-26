package com.google.android.gms.iid;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Bundle;
import android.os.Looper;
import android.util.Base64;
import android.util.Log;
import java.io.IOException;
import java.security.KeyPair;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.PublicKey;
import java.util.HashMap;
import java.util.Map;

public class InstanceID
{
  public static final String ERROR_BACKOFF = "RETRY_LATER";
  public static final String ERROR_MAIN_THREAD = "MAIN_THREAD";
  public static final String ERROR_MISSING_INSTANCEID_SERVICE = "MISSING_INSTANCEID_SERVICE";
  public static final String ERROR_SERVICE_NOT_AVAILABLE = "SERVICE_NOT_AVAILABLE";
  public static final String ERROR_TIMEOUT = "TIMEOUT";
  static Map<String, InstanceID> zzaMP = new HashMap();
  private static zzd zzaMQ;
  private static zzc zzaMR;
  static String zzaMV;
  Context mContext;
  KeyPair zzaMS;
  String zzaMT = "";
  long zzaMU;
  
  protected InstanceID(Context paramContext, String paramString, Bundle paramBundle)
  {
    this.mContext = paramContext.getApplicationContext();
    this.zzaMT = paramString;
  }
  
  public static InstanceID getInstance(Context paramContext)
  {
    return zza(paramContext, null);
  }
  
  public static InstanceID zza(Context paramContext, Bundle paramBundle)
  {
    String str;
    if (paramBundle == null) {
      str = "";
    }
    for (;;)
    {
      try
      {
        Context localContext = paramContext.getApplicationContext();
        if (zzaMQ == null)
        {
          zzaMQ = new zzd(localContext);
          zzaMR = new zzc(localContext);
        }
        zzaMV = Integer.toString(zzaL(localContext));
        InstanceID localInstanceID = (InstanceID)zzaMP.get(str);
        paramContext = localInstanceID;
        if (localInstanceID == null)
        {
          paramContext = new InstanceID(localContext, str, paramBundle);
          zzaMP.put(str, paramContext);
        }
        return paramContext;
      }
      finally {}
      str = paramBundle.getString("subtype");
      while (str != null) {
        break;
      }
      str = "";
    }
  }
  
  static String zza(KeyPair paramKeyPair)
  {
    paramKeyPair = paramKeyPair.getPublic().getEncoded();
    try
    {
      paramKeyPair = MessageDigest.getInstance("SHA1").digest(paramKeyPair);
      paramKeyPair[0] = ((byte)((paramKeyPair[0] & 0xF) + 112 & 0xFF));
      paramKeyPair = Base64.encodeToString(paramKeyPair, 0, 8, 11);
      return paramKeyPair;
    }
    catch (NoSuchAlgorithmException paramKeyPair)
    {
      Log.w("InstanceID", "Unexpected error, device missing required alghorithms");
    }
    return null;
  }
  
  static int zzaL(Context paramContext)
  {
    try
    {
      int i = paramContext.getPackageManager().getPackageInfo(paramContext.getPackageName(), 0).versionCode;
      return i;
    }
    catch (PackageManager.NameNotFoundException paramContext)
    {
      Log.w("InstanceID", "Never happens: can't find own package " + paramContext);
    }
    return 0;
  }
  
  static String zzn(byte[] paramArrayOfByte)
  {
    return Base64.encodeToString(paramArrayOfByte, 11);
  }
  
  public void deleteInstanceID()
    throws IOException
  {
    zzb("*", "*", null);
    zzyA();
  }
  
  public void deleteToken(String paramString1, String paramString2)
    throws IOException
  {
    zzb(paramString1, paramString2, null);
  }
  
  public long getCreationTime()
  {
    if (this.zzaMU == 0L)
    {
      String str = zzaMQ.get(this.zzaMT, "cre");
      if (str != null) {
        this.zzaMU = Long.parseLong(str);
      }
    }
    return this.zzaMU;
  }
  
  public String getId()
  {
    return zza(zzyz());
  }
  
  public String getToken(String paramString1, String paramString2)
    throws IOException
  {
    return getToken(paramString1, paramString2, null);
  }
  
  public String getToken(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    int j = 0;
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    int i = 1;
    if (zzyD()) {}
    for (Object localObject = null; localObject != null; localObject = zzaMQ.zzi(this.zzaMT, paramString1, paramString2)) {
      return (String)localObject;
    }
    localObject = paramBundle;
    if (paramBundle == null) {
      localObject = new Bundle();
    }
    if (((Bundle)localObject).getString("ttl") != null) {
      i = 0;
    }
    if ("jwt".equals(((Bundle)localObject).getString("type"))) {
      i = j;
    }
    for (;;)
    {
      paramBundle = zzc(paramString1, paramString2, (Bundle)localObject);
      Log.w("InstanceID", "token: " + paramBundle);
      localObject = paramBundle;
      if (paramBundle == null) {
        break;
      }
      localObject = paramBundle;
      if (i == 0) {
        break;
      }
      zzaMQ.zza(this.zzaMT, paramString1, paramString2, paramBundle, zzaMV);
      return paramBundle;
    }
  }
  
  public void zzb(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (Looper.getMainLooper() == Looper.myLooper()) {
      throw new IOException("MAIN_THREAD");
    }
    zzaMQ.zzj(this.zzaMT, paramString1, paramString2);
    Bundle localBundle = paramBundle;
    if (paramBundle == null) {
      localBundle = new Bundle();
    }
    localBundle.putString("sender", paramString1);
    if (paramString2 != null) {
      localBundle.putString("scope", paramString2);
    }
    localBundle.putString("subscription", paramString1);
    localBundle.putString("delete", "1");
    localBundle.putString("X-delete", "1");
    if ("".equals(this.zzaMT))
    {
      paramString2 = paramString1;
      localBundle.putString("subtype", paramString2);
      if (!"".equals(this.zzaMT)) {
        break label173;
      }
    }
    for (;;)
    {
      localBundle.putString("X-subtype", paramString1);
      paramString1 = zzaMR.zza(localBundle, zzyz());
      zzaMR.zzu(paramString1);
      return;
      paramString2 = this.zzaMT;
      break;
      label173:
      paramString1 = this.zzaMT;
    }
  }
  
  public String zzc(String paramString1, String paramString2, Bundle paramBundle)
    throws IOException
  {
    if (paramString2 != null) {
      paramBundle.putString("scope", paramString2);
    }
    paramBundle.putString("sender", paramString1);
    if ("".equals(this.zzaMT)) {}
    for (paramString2 = paramString1;; paramString2 = this.zzaMT)
    {
      if (!paramBundle.containsKey("legacy.register"))
      {
        paramBundle.putString("subscription", paramString1);
        paramBundle.putString("subtype", paramString2);
        paramBundle.putString("X-subscription", paramString1);
        paramBundle.putString("X-subtype", paramString2);
      }
      paramString1 = zzaMR.zza(paramBundle, zzyz());
      return zzaMR.zzu(paramString1);
    }
  }
  
  void zzyA()
  {
    this.zzaMU = 0L;
    zzaMQ.zzee(this.zzaMT);
    this.zzaMS = null;
  }
  
  public zzd zzyB()
  {
    return zzaMQ;
  }
  
  zzc zzyC()
  {
    return zzaMR;
  }
  
  boolean zzyD()
  {
    String str = zzaMQ.get("appVersion");
    if ((str == null) || (!str.equals(zzaMV))) {}
    long l;
    do
    {
      do
      {
        return true;
        str = zzaMQ.get("lastToken");
      } while (str == null);
      l = Long.parseLong(str);
    } while (System.currentTimeMillis() / 1000L - Long.valueOf(l).longValue() > 604800L);
    return false;
  }
  
  KeyPair zzyz()
  {
    if (this.zzaMS == null) {
      this.zzaMS = zzaMQ.zzed(this.zzaMT);
    }
    if (this.zzaMS == null)
    {
      this.zzaMU = System.currentTimeMillis();
      this.zzaMS = zzaMQ.zzd(this.zzaMT, this.zzaMU);
    }
    return this.zzaMS;
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\iid\InstanceID.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */