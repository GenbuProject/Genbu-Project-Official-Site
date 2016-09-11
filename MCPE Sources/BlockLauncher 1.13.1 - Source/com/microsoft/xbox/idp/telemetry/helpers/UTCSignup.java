package com.microsoft.xbox.idp.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.utc.model.UTCCommonDataModel;
import com.microsoft.xbox.idp.ui.AccountProvisioningResult;

public class UTCSignup
{
  public static void trackClaimGamerTag(AccountProvisioningResult paramAccountProvisioningResult, CharSequence paramCharSequence)
  {
    if (paramAccountProvisioningResult != null) {}
    try
    {
      UTCCommonDataModel.setUserId(paramAccountProvisioningResult.getXuid());
      UTCPageAction.track("Signup - Claim gamertag", paramCharSequence);
      return;
    }
    catch (Exception paramAccountProvisioningResult)
    {
      UTCLog.log(paramAccountProvisioningResult.getMessage(), new Object[0]);
    }
  }
  
  public static void trackClearGamerTag(AccountProvisioningResult paramAccountProvisioningResult, CharSequence paramCharSequence)
  {
    if (paramAccountProvisioningResult != null) {}
    try
    {
      UTCCommonDataModel.setUserId(paramAccountProvisioningResult.getXuid());
      UTCPageAction.track("Signup - Clear gamertag text", paramCharSequence);
      return;
    }
    catch (Exception paramAccountProvisioningResult)
    {
      UTCLog.log(paramAccountProvisioningResult.getMessage(), new Object[0]);
    }
  }
  
  public static void trackPageView(CharSequence paramCharSequence)
  {
    try
    {
      UTCPageView.track("Sign up view", paramCharSequence);
      return;
    }
    catch (Exception paramCharSequence)
    {
      UTCLog.log(paramCharSequence.getMessage(), new Object[0]);
    }
  }
  
  public static void trackSearchGamerTag(AccountProvisioningResult paramAccountProvisioningResult, CharSequence paramCharSequence)
  {
    if (paramAccountProvisioningResult != null) {}
    try
    {
      UTCCommonDataModel.setUserId(paramAccountProvisioningResult.getXuid());
      UTCPageAction.track("Signup - Search for gamertag", paramCharSequence);
      return;
    }
    catch (Exception paramAccountProvisioningResult)
    {
      UTCLog.log(paramAccountProvisioningResult.getMessage(), new Object[0]);
    }
  }
  
  public static void trackSignInWithDifferentUser(AccountProvisioningResult paramAccountProvisioningResult, CharSequence paramCharSequence)
  {
    if (paramAccountProvisioningResult != null) {}
    try
    {
      UTCCommonDataModel.setUserId(paramAccountProvisioningResult.getXuid());
      UTCPageAction.track("Signup - Signin with different user", paramCharSequence);
      return;
    }
    catch (Exception paramAccountProvisioningResult)
    {
      UTCLog.log(paramAccountProvisioningResult.getMessage(), new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCSignup.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */