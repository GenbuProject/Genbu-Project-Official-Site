package com.microsoft.xbox.idp.telemetry.helpers;

import com.microsoft.xbox.idp.model.Profile.User;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCCommonDataModel;

public class UTCWelcome
{
  public static void trackChangeUser(Profile.User paramUser, CharSequence paramCharSequence)
  {
    if (paramUser != null) {}
    try
    {
      UTCCommonDataModel.setUserId(paramUser.id);
      UTCPageAction.track("Welcome - Change user", paramCharSequence);
      return;
    }
    catch (Exception paramUser)
    {
      UTCLog.log(paramUser.getMessage(), new Object[0]);
    }
  }
  
  public static void trackDone(Profile.User paramUser, CharSequence paramCharSequence)
  {
    if (paramUser != null) {}
    try
    {
      UTCCommonDataModel.setUserId(paramUser.id);
      UTCPageAction.track("Welcome - Done", paramCharSequence);
      return;
    }
    catch (Exception paramUser)
    {
      UTCLog.log(paramUser.getMessage(), new Object[0]);
    }
  }
  
  public static void trackPageView(Profile.User paramUser, CharSequence paramCharSequence)
  {
    if (paramUser != null) {}
    try
    {
      UTCCommonDataModel.setUserId(paramUser.id);
      UTCPageView.track("Welcome view", paramCharSequence);
      return;
    }
    catch (Exception paramUser)
    {
      UTCLog.log(paramUser.getMessage(), new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCWelcome.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */