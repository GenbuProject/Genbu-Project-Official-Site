package com.microsoft.xbox.xle.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.helpers.UTCPageAction;
import com.microsoft.xbox.idp.telemetry.helpers.UTCPageView;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.toolkit.XLEAssert;

public class UTCPeopleHub
{
  private static CharSequence currentActivityTitle = "";
  private static String currentXUID = "";
  
  private static UTCAdditionalInfoModel getBaseInfoModel(String paramString)
  {
    UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
    localUTCAdditionalInfoModel.addValue("targetXUID", "x:" + paramString);
    return localUTCAdditionalInfoModel;
  }
  
  public static void trackBlock()
  {
    verifyTrackedDefaults();
    trackBlock(currentActivityTitle, currentXUID);
  }
  
  public static void trackBlock(CharSequence paramCharSequence, final String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCPageAction.track("People Hub - Block", this.val$activityTitle, UTCPeopleHub.getBaseInfoModel(paramString));
      }
    });
  }
  
  public static void trackBlockDialogComplete()
  {
    verifyTrackedDefaults();
    trackBlockDialogComplete(currentActivityTitle, currentXUID);
  }
  
  public static void trackBlockDialogComplete(CharSequence paramCharSequence, final String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCPageAction.track("People Hub - Block OK", this.val$activityTitle, UTCPeopleHub.getBaseInfoModel(paramString));
      }
    });
  }
  
  public static void trackMute(final CharSequence paramCharSequence, String paramString, final boolean paramBoolean)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCPeopleHub.getBaseInfoModel(this.val$targetXUID);
        localUTCAdditionalInfoModel.addValue("isMuted", Boolean.valueOf(paramBoolean));
        UTCPageAction.track("People Hub - Mute", paramCharSequence, localUTCAdditionalInfoModel);
      }
    });
  }
  
  public static void trackMute(boolean paramBoolean)
  {
    verifyTrackedDefaults();
    trackMute(currentActivityTitle, currentXUID, paramBoolean);
  }
  
  public static void trackPeopleHubView(final CharSequence paramCharSequence, String paramString, final boolean paramBoolean)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCPeopleHub.access$002(this.val$targetXUID);
        UTCPeopleHub.access$102(paramCharSequence);
        if (paramBoolean) {}
        for (String str = "People Hub - ME View";; str = "People Hub - You View")
        {
          UTCPageView.track(str, UTCPeopleHub.currentActivityTitle, UTCPeopleHub.getBaseInfoModel(this.val$targetXUID));
          return;
        }
      }
    });
  }
  
  public static void trackReport()
  {
    verifyTrackedDefaults();
    trackReport(currentActivityTitle, currentXUID);
  }
  
  public static void trackReport(CharSequence paramCharSequence, final String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCPageAction.track("People Hub - Report", this.val$activityTitle, UTCPeopleHub.getBaseInfoModel(paramString));
      }
    });
  }
  
  public static void trackUnblock()
  {
    verifyTrackedDefaults();
    trackUnblock(currentActivityTitle, currentXUID);
  }
  
  public static void trackUnblock(CharSequence paramCharSequence, final String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCPageAction.track("People Hub - Unblock", this.val$activityTitle, UTCPeopleHub.getBaseInfoModel(paramString));
      }
    });
  }
  
  public static void trackViewInXboxApp()
  {
    verifyTrackedDefaults();
    trackViewInXboxApp(currentActivityTitle, currentXUID);
  }
  
  public static void trackViewInXboxApp(CharSequence paramCharSequence, final String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCPageAction.track("People Hub - View in Xbox App", this.val$activityTitle, UTCPeopleHub.getBaseInfoModel(paramString));
      }
    });
  }
  
  public static void trackViewInXboxAppDialogComplete()
  {
    verifyTrackedDefaults();
    trackViewInXboxAppDialogComplete(currentActivityTitle, currentXUID);
  }
  
  public static void trackViewInXboxAppDialogComplete(CharSequence paramCharSequence, final String paramString)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCPageAction.track("People Hub - View in Xbox App OK", this.val$activityTitle, UTCPeopleHub.getBaseInfoModel(paramString));
      }
    });
  }
  
  private static void verifyTrackedDefaults()
  {
    XLEAssert.assertFalse("Called trackPeopleHubView without set currentXUID", currentXUID.equals(""));
    XLEAssert.assertFalse("Called trackPeopleHubView without set activityTitle", currentActivityTitle.toString().equals(""));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\telemetry\helpers\UTCPeopleHub.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */