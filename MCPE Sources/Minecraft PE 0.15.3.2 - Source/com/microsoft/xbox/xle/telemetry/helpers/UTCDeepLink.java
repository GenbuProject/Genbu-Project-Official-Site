package com.microsoft.xbox.xle.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.helpers.UTCPageAction;
import com.microsoft.xbox.idp.telemetry.utc.CommonData;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCCommonDataModel;
import java.util.HashMap;

public class UTCDeepLink
{
  public static final String CALLING_APP_KEY = "deepLinkCaller";
  public static final String DEEPLINK_KEY_NAME = "deepLinkId";
  public static final String INTENDED_ACTION_KEY = "intendedAction";
  public static final String TARGET_TITLE_KEY = "targetTitleId";
  public static final String TARGET_XUID_KEY = "targetXUID";
  
  private static String generateCorrelationId()
  {
    return UTCCommonDataModel.getCommonData(1).getAppSessionId();
  }
  
  private static UTCAdditionalInfoModel getBaseModelInfo(String paramString)
  {
    UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
    localUTCAdditionalInfoModel.addValue("deepLinkId", generateCorrelationId());
    localUTCAdditionalInfoModel.addValue("deepLinkCaller", paramString);
    return localUTCAdditionalInfoModel;
  }
  
  public static String trackFriendSuggestionsLink(final CharSequence paramCharSequence, String paramString)
  {
    UTCEventTracker.callStringTrackWrapper(new UTCEventTracker.UTCStringEventDelegate()
    {
      public String call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCDeepLink.getBaseModelInfo(this.val$packageName);
        UTCPageAction.track("DeepLink - Friend Suggestions", paramCharSequence, localUTCAdditionalInfoModel);
        return localUTCAdditionalInfoModel.getAdditionalInfo().get("deepLinkId").toString();
      }
    });
  }
  
  public static String trackGameHubAchievementsLink(final CharSequence paramCharSequence, String paramString1, final String paramString2)
  {
    UTCEventTracker.callStringTrackWrapper(new UTCEventTracker.UTCStringEventDelegate()
    {
      public String call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCDeepLink.getBaseModelInfo(this.val$packageName);
        localUTCAdditionalInfoModel.addValue("targetTitleId", paramString2);
        UTCPageAction.track("DeepLink - GameHub", paramCharSequence, localUTCAdditionalInfoModel);
        return localUTCAdditionalInfoModel.getAdditionalInfo().get("deepLinkId").toString();
      }
    });
  }
  
  public static String trackGameHubLink(final CharSequence paramCharSequence, String paramString1, final String paramString2)
  {
    UTCEventTracker.callStringTrackWrapper(new UTCEventTracker.UTCStringEventDelegate()
    {
      public String call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCDeepLink.getBaseModelInfo(this.val$packageName);
        localUTCAdditionalInfoModel.addValue("targetXUID", paramString2);
        UTCPageAction.track("DeepLink - GameHub", paramCharSequence, localUTCAdditionalInfoModel);
        return localUTCAdditionalInfoModel.getAdditionalInfo().get("deepLinkId").toString();
      }
    });
  }
  
  public static String trackUserProfileLink(final CharSequence paramCharSequence, String paramString1, final String paramString2)
  {
    UTCEventTracker.callStringTrackWrapper(new UTCEventTracker.UTCStringEventDelegate()
    {
      public String call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCDeepLink.getBaseModelInfo(this.val$packageName);
        localUTCAdditionalInfoModel.addValue("targetXUID", "x:" + paramString2);
        UTCPageAction.track("DeepLink - User Profile", paramCharSequence, localUTCAdditionalInfoModel);
        return localUTCAdditionalInfoModel.getAdditionalInfo().get("deepLinkId").toString();
      }
    });
  }
  
  public static void trackUserSendToStore(final CharSequence paramCharSequence, String paramString1, final String paramString2)
  {
    UTCEventTracker.callTrackWrapper(new UTCEventTracker.UTCEventDelegate()
    {
      public void call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCDeepLink.getBaseModelInfo(this.val$packageName);
        localUTCAdditionalInfoModel.addValue("intendedAction", paramString2);
        UTCPageAction.track("DeepLink - Store Redirect", paramCharSequence, localUTCAdditionalInfoModel);
      }
    });
  }
  
  public static String trackUserSettingsLink(final CharSequence paramCharSequence, String paramString)
  {
    UTCEventTracker.callStringTrackWrapper(new UTCEventTracker.UTCStringEventDelegate()
    {
      public String call()
      {
        UTCAdditionalInfoModel localUTCAdditionalInfoModel = UTCDeepLink.getBaseModelInfo(this.val$packageName);
        UTCPageAction.track("DeepLink - User Settings", paramCharSequence, localUTCAdditionalInfoModel);
        return localUTCAdditionalInfoModel.getAdditionalInfo().get("deepLinkId").toString();
      }
    });
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\telemetry\helpers\UTCDeepLink.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */