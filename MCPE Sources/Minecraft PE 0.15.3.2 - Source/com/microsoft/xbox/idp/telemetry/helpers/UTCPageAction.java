package com.microsoft.xbox.idp.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.utc.PageAction;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCCommonDataModel;

public class UTCPageAction
{
  private static final int PAGEACTIONVERSION = 1;
  
  public static void track(String paramString, CharSequence paramCharSequence)
  {
    track(paramString, UTCPageView.getCurrentPage(), paramCharSequence, new UTCAdditionalInfoModel());
  }
  
  public static void track(String paramString, CharSequence paramCharSequence, UTCAdditionalInfoModel paramUTCAdditionalInfoModel)
  {
    track(paramString, UTCPageView.getCurrentPage(), paramCharSequence, paramUTCAdditionalInfoModel);
  }
  
  public static void track(String paramString1, String paramString2, CharSequence paramCharSequence, UTCAdditionalInfoModel paramUTCAdditionalInfoModel)
  {
    if (paramCharSequence != null) {}
    try
    {
      paramUTCAdditionalInfoModel.addValue("activityTitle", paramCharSequence);
      paramCharSequence = new PageAction();
      paramCharSequence.setActionName(paramString1);
      paramCharSequence.setPageName(paramString2);
      paramCharSequence.setBaseData(UTCCommonDataModel.getCommonData(1, paramUTCAdditionalInfoModel));
      UTCLog.log("pageActions:%s, onPage:%s, additionalInfo:%s", new Object[] { paramString1, paramString2, paramUTCAdditionalInfoModel });
      UTCTelemetry.LogEvent(paramCharSequence);
      return;
    }
    catch (Exception paramString1)
    {
      UTCLog.log(paramString1.getMessage(), new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCPageAction.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */