package com.microsoft.xbox.xle.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.helpers.UTCPageAction;
import com.microsoft.xbox.idp.telemetry.helpers.UTCPageView;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.toolkit.XLEAssert;

public class UTCReportUser
{
  private static CharSequence currentActivityTitle = "";
  private static String currentXUID = "";
  
  private static UTCAdditionalInfoModel getBaseInfoModel(String paramString)
  {
    UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
    localUTCAdditionalInfoModel.addValue("targetXUID", "x:" + paramString);
    return localUTCAdditionalInfoModel;
  }
  
  public static void trackReportDialogOK(final CharSequence paramCharSequence, String paramString1, final String paramString2)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCReportUser.getBaseInfoModel(this.val$targetXUID);
        localUTCAdditionalInfoModel.addValue("reason", paramString2);
        UTCPageAction.track("People Hub - Report OK", paramCharSequence, localUTCAdditionalInfoModel);
      }
    });
  }
  
  public static void trackReportDialogOK(String paramString)
  {
    verifyTrackedDefaults();
    trackReportDialogOK(currentActivityTitle, currentXUID, paramString);
  }
  
  public static void trackReportView(CharSequence paramCharSequence, final String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCReportUser.access$002(this.val$activityTitle);
        UTCReportUser.access$102(paramString);
        UTCPageView.track("People Hub Report view", UTCReportUser.currentActivityTitle, UTCReportUser.getBaseInfoModel(paramString));
      }
    });
  }
  
  private static void verifyTrackedDefaults()
  {
    XLEAssert.assertFalse("Called trackPeopleHubView without set currentXUID", currentXUID.equals(""));
    XLEAssert.assertFalse("Called trackPeopleHubView without set activityTitle", currentActivityTitle.toString().equals(""));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\telemetry\helpers\UTCReportUser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */