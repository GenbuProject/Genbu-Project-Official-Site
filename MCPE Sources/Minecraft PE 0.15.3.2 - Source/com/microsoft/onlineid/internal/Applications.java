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
    paramString = PackageInfoHelper.getAppSignatures(paramContext, paramString);
    if (paramString.length > 0) {}
    byte[] arrayOfByte1;
    LinkedHashMap localLinkedHashMap;
    for (boolean bool = true;; bool = false)
    {
      Assertion.check(bool);
      arrayOfByte1 = localMessageDigest.digest(paramString[0].toByteArray());
      paramContext = Settings.getInstance(paramContext);
      if (!paramContext.isSettingEnabled("isCertificateTelemetryNeeded")) {
        break label133;
      }
      localLinkedHashMap = new LinkedHashMap();
      int j = paramString.length;
      int i = 0;
      while (i < j)
      {
        byte[] arrayOfByte2 = localMessageDigest.digest(paramString[i].toByteArray());
        localLinkedHashMap.put(Base64.encodeToString(arrayOfByte2, 2), arrayOfByte2);
        i += 1;
      }
    }
    ClientAnalytics.get().logCertificates(localLinkedHashMap);
    paramContext.setSetting("isCertificateTelemetryNeeded", String.valueOf(false));
    label133:
    return arrayOfByte1;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\onlineid\internal\Applications.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */