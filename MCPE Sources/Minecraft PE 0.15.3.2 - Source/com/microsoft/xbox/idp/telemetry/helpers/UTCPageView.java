package com.microsoft.xbox.idp.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.utc.PageView;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCCommonDataModel;
import java.util.ArrayList;

public class UTCPageView
{
  private static final int PAGEVIEWVERSION = 1;
  private static ArrayList<String> pages = new ArrayList();
  
  public static void addPage(String paramString)
  {
    if ((!pages.contains(paramString)) && (paramString != null)) {
      pages.add(paramString);
    }
  }
  
  public static String getCurrentPage()
  {
    int i = pages.size();
    if (i == 0) {
      return "Unknown";
    }
    return (String)pages.get(i - 1);
  }
  
  public static String getPreviousPage()
  {
    int i = pages.size();
    if (i < 2) {
      return "Unknown";
    }
    return (String)pages.get(i - 2);
  }
  
  public static void removePage()
  {
    int i = pages.size();
    if (i > 0) {
      pages.remove(i - 1);
    }
  }
  
  public static void track(String paramString, CharSequence paramCharSequence)
  {
    track(paramString, paramCharSequence, new UTCAdditionalInfoModel());
  }
  
  public static void track(String paramString, CharSequence paramCharSequence, UTCAdditionalInfoModel paramUTCAdditionalInfoModel)
  {
    if (paramCharSequence != null) {}
    try
    {
      paramUTCAdditionalInfoModel.addValue("activityTitle", paramCharSequence);
      addPage(paramString);
      paramCharSequence = getPreviousPage();
      PageView localPageView = new PageView();
      localPageView.setPageName(paramString);
      localPageView.setFromPage(paramCharSequence);
      UTCLog.log("pageView:%s, fromPage:%s, additionalInfo:%s", new Object[] { paramString, paramCharSequence, paramUTCAdditionalInfoModel });
      localPageView.setBaseData(UTCCommonDataModel.getCommonData(1, paramUTCAdditionalInfoModel));
      UTCTelemetry.LogEvent(localPageView);
      return;
    }
    catch (Exception paramString)
    {
      UTCLog.log(paramString.getMessage(), new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCPageView.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */