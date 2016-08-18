package com.microsoft.xbox.idp.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;

public class UTCSignin
{
  private static CharSequence activityTitle = null;
  
  public static CharSequence getCurrentActivity()
  {
    return activityTitle;
  }
  
  public static void setCurrentActivity(CharSequence paramCharSequence)
  {
    activityTitle = paramCharSequence;
  }
  
  public static void trackAccountAcquired(String paramString1, String paramString2, boolean paramBoolean)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("cid", paramString2);
      localUTCAdditionalInfoModel.addValue("job", paramString1);
      localUTCAdditionalInfoModel.addValue("source", UTCTelemetry.CallBackSources.Account);
      UTCPageAction.track("Signin - Account acquired", getCurrentActivity(), localUTCAdditionalInfoModel);
      return;
    }
    catch (Exception paramString1)
    {
      UTCLog.log(paramString1.getMessage(), new Object[0]);
    }
  }
  
  public static void trackPageView(CharSequence paramCharSequence)
  {
    try
    {
      UTCPageView.track("Sign in view", paramCharSequence);
      return;
    }
    catch (Exception paramCharSequence)
    {
      UTCLog.log(paramCharSequence.getMessage(), new Object[0]);
    }
  }
  
  public static void trackSignin(String paramString, boolean paramBoolean, CharSequence paramCharSequence)
  {
    try
    {
      setCurrentActivity(paramCharSequence);
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("cid", paramString);
      localUTCAdditionalInfoModel.addValue("isSilent", Boolean.valueOf(paramBoolean));
      UTCPageAction.track("Signin - Sign in", paramCharSequence, localUTCAdditionalInfoModel);
      return;
    }
    catch (Exception paramString)
    {
      UTCLog.log(paramString.getMessage(), new Object[0]);
    }
  }
  
  public static void trackTicketAcquired(String paramString1, String paramString2, boolean paramBoolean)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("cid", paramString2);
      localUTCAdditionalInfoModel.addValue("job", paramString1);
      localUTCAdditionalInfoModel.addValue("source", UTCTelemetry.CallBackSources.Ticket);
      UTCPageAction.track("Signin - Ticket acquired", getCurrentActivity(), localUTCAdditionalInfoModel);
      return;
    }
    catch (Exception paramString1)
    {
      UTCLog.log(paramString1.getMessage(), new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCSignin.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */