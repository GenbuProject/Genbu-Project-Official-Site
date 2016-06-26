package com.microsoft.onlineid.internal.sso;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.Signature;
import android.util.Base64;
import com.microsoft.onlineid.internal.Assertion;
import com.microsoft.onlineid.internal.configuration.Settings;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.sts.Cryptography;
import com.microsoft.onlineid.sts.ServerConfig;
import java.security.MessageDigest;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Set;

public class SignatureVerifier
{
  private final Context _applicationContext;
  private final ServerConfig _config;
  private final PackageManager _packageManager;
  
  @Deprecated
  public SignatureVerifier()
  {
    this._applicationContext = null;
    this._packageManager = null;
    this._config = null;
  }
  
  public SignatureVerifier(Context paramContext)
  {
    this._applicationContext = paramContext;
    this._packageManager = paramContext.getPackageManager();
    this._config = new ServerConfig(paramContext);
    Settings.initialize(paramContext);
  }
  
  public boolean isPackageInUid(int paramInt, String paramString)
  {
    String[] arrayOfString = this._packageManager.getPackagesForUid(paramInt);
    if ((arrayOfString == null) || (arrayOfString.length == 0)) {
      return false;
    }
    return Arrays.asList(arrayOfString).contains(paramString);
  }
  
  public boolean isTrusted(String paramString)
  {
    if (this._applicationContext.getPackageName().equalsIgnoreCase(paramString)) {
      return true;
    }
    if ((Settings.isDebugBuild()) && (!Settings.isSettingEnabled("shouldCheckSsoCertificatesInDebug"))) {
      return true;
    }
    ArrayList localArrayList;
    for (;;)
    {
      int i;
      String str;
      try
      {
        Object localObject = this._packageManager.getPackageInfo(paramString, 64);
        Set localSet = this._config.getStringSet(ServerConfig.AndroidSsoCertificates);
        localArrayList = new ArrayList();
        MessageDigest localMessageDigest = Cryptography.getSha256Digester();
        localObject = ((PackageInfo)localObject).signatures;
        int j = localObject.length;
        i = 0;
        if (i >= j) {
          break;
        }
        str = Base64.encodeToString(localMessageDigest.digest(localObject[i].toByteArray()), 2);
        if (localSet.contains(str)) {
          return true;
        }
      }
      catch (PackageManager.NameNotFoundException localNameNotFoundException)
      {
        paramString = "Cannot check trust state of missing package: " + paramString;
        Logger.error(paramString, localNameNotFoundException);
        Assertion.check(false, paramString);
        return false;
      }
      localArrayList.add(str);
      i += 1;
    }
    Logger.warning("Not trusting " + paramString + " because no matching hash was found in the whitelist.");
    Logger.warning("Hashes for " + paramString + " are: " + Arrays.toString(localArrayList.toArray()));
    Logger.warning("Whitelist is: " + Arrays.toString(localNameNotFoundException.toArray()));
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\sso\SignatureVerifier.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */