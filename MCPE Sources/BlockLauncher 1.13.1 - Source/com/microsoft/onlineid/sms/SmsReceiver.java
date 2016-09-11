package com.microsoft.onlineid.sms;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.telephony.SmsMessage;
import android.text.TextUtils;
import com.microsoft.onlineid.analytics.ClientAnalytics;
import com.microsoft.onlineid.analytics.IClientAnalytics;
import com.microsoft.onlineid.internal.Objects;
import com.microsoft.onlineid.internal.ui.JavaScriptBridge;
import com.microsoft.onlineid.internal.ui.PropertyBag.Key;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class SmsReceiver
  extends BroadcastReceiver
{
  public static final Pattern OneTimeCodePattern = Pattern.compile("\\s*(\\d+).+\\Qhttp://aka.ms/smscode\\E");
  public static final String SMS_RECEIVED_ACTION = "android.provider.Telephony.SMS_RECEIVED";
  private final JavaScriptBridge _javaScriptBridge;
  
  public SmsReceiver(JavaScriptBridge paramJavaScriptBridge)
  {
    Objects.verifyArgumentNotNull(paramJavaScriptBridge, "javaScriptBridge");
    this._javaScriptBridge = paramJavaScriptBridge;
  }
  
  static String parseOneTimeCodeFromBody(String paramString)
  {
    if (paramString == null) {}
    do
    {
      return null;
      paramString = OneTimeCodePattern.matcher(paramString);
    } while ((!paramString.matches()) || (paramString.groupCount() != 1));
    return paramString.group(1);
  }
  
  protected void abortBroadcastWrapper()
  {
    abortBroadcast();
  }
  
  public void onReceive(Context paramContext, Intent paramIntent)
  {
    paramContext = paramIntent.getExtras();
    if (paramContext == null) {}
    do
    {
      do
      {
        return;
        paramContext = (Object[])paramContext.get("pdus");
      } while (paramContext.length < 1);
      paramContext = parseOneTimeCodeFromBody(SmsMessage.createFromPdu((byte[])paramContext[0]).getMessageBody());
    } while (TextUtils.isEmpty(paramContext));
    ClientAnalytics.get().logEvent("SMS verification", "Successfully verified");
    this._javaScriptBridge.Property(PropertyBag.Key.SmsCode.name(), paramContext);
    abortBroadcastWrapper();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\onlineid\sms\SmsReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */