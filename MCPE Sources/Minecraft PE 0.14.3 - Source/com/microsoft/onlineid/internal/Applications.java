package com.microsoft.onlineid.internal;

import android.content.Context;
import android.content.pm.Signature;
import android.util.Base64;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.configuration.Settings;
import com.microsoft.onlineid.sts.Cryptography;
import java.security.MessageDigest;
import java.util.LinkedHashMap;
import java.util.Map;

public class Applications
{
  public static String buildClientAppUri(Context paramContext, String paramString)
  {
    paramContext = Cryptography.encodeBase32(getFirstCertHash(paramContext, paramString));
    return "android-app://" + paramString + "." + paramContext;
  }
  
  private static byte[] getFirstCertHash(Context paramContext, String paramString)
  {
    MessageDigest localMessageDigest = Cryptography.getShaDigester();
    paramContext = PackageInfoHelper.getAppSignatures(paramContext, paramString);
    if (paramContext.length > 0) {}
    LinkedHashMap localLinkedHashMap;
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      paramString = localMessageDigest.digest(paramContext[0].toByteArray());
      if (!Settings.isSettingEnabled("isCertificateTelemetryNeeded")) {
        return paramString;
      }
      localLinkedHashMap = new LinkedHashMap();
      int j = paramContext.length;
      int i = 0;
      while (i < j)
      {
        byte[] arrayOfByte = localMessageDigest.digest(paramContext[i].toByteArray());
        localLinkedHashMap.put(Base64.encodeToString(arrayOfByte, 2), arrayOfByte);
        i += 1;
      }
    }
    ClientAnalytics.get().logCertificates(localLinkedHashMap);
    Settings.setSetting("isCertificateTelemetryNeeded", String.valueOf(false));
    return paramString;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\Applications.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */