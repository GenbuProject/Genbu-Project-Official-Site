package com.microsoft.onlineid.internal;

import android.content.Context;
import android.net.Uri;
import android.net.Uri.Builder;
import android.text.TextUtils;
import com.microsoft.onlineid.internal.log.Logger;
import com.microsoft.onlineid.userdata.AccountManagerReader;
import com.microsoft.onlineid.userdata.TelephonyManagerReader;
import java.util.Set;

public class Uris
{
  static final String EmailDelimiter = ",";
  static final String EmailParam = "email";
  static final String MktParam = "mkt";
  static final String PhoneParam = "phone";
  
  public static Uri appendEmails(AccountManagerReader paramAccountManagerReader, Uri paramUri)
  {
    if (!TextUtils.isEmpty(paramUri.getQueryParameter("email")))
    {
      Logger.warning("Given URL already has email parameter set.");
      return paramUri;
    }
    paramAccountManagerReader = paramAccountManagerReader.getEmails();
    paramUri = paramUri.buildUpon();
    if (paramAccountManagerReader.isEmpty()) {}
    for (paramAccountManagerReader = "";; paramAccountManagerReader = TextUtils.join(",", paramAccountManagerReader)) {
      return paramUri.appendQueryParameter("email", paramAccountManagerReader).build();
    }
  }
  
  public static Uri appendMarketQueryString(Context paramContext, Uri paramUri)
  {
    if (!TextUtils.isEmpty(paramUri.getQueryParameter("mkt")))
    {
      Logger.warning("Given URL already has mkt parameter set.");
      return paramUri;
    }
    String str = Resources.getString(paramContext, "app_market");
    paramUri = paramUri.buildUpon();
    paramContext = str;
    if (TextUtils.isEmpty(str)) {
      paramContext = "en";
    }
    return paramUri.appendQueryParameter("mkt", paramContext).build();
  }
  
  public static Uri appendPhoneDigits(TelephonyManagerReader paramTelephonyManagerReader, Uri paramUri)
  {
    if (!TextUtils.isEmpty(paramUri.getQueryParameter("phone")))
    {
      Logger.warning("Given URL already has phone parameter set.");
      return paramUri;
    }
    paramTelephonyManagerReader = paramTelephonyManagerReader.getPhoneNumber();
    paramUri = paramUri.buildUpon();
    if (TextUtils.isEmpty(paramTelephonyManagerReader)) {}
    for (paramTelephonyManagerReader = "";; paramTelephonyManagerReader = paramTelephonyManagerReader.replaceAll("[^\\d]+", "")) {
      return paramUri.appendQueryParameter("phone", paramTelephonyManagerReader).build();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\MCPE.jar!\com\microsoft\onlineid\internal\Uris.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */