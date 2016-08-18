package com.microsoft.xbox.idp.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.utc.ClientError;
import com.microsoft.xbox.idp.telemetry.utc.ServiceError;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;
import com.microsoft.xbox.idp.telemetry.utc.model.UTCCommonDataModel;
import com.microsoft.xbox.idp.toolkit.HttpError;
import com.microsoft.xbox.idp.ui.ErrorActivity.ErrorScreen;

public class UTCError
{
  private static final int CLIENTERRORVERSION = 1;
  private static final String FAILURE = "Client Error Type - Failure";
  private static final int SERVICEERRORVERSION = 1;
  private static final String SIGNEDOUT = "Client Error Type - Signed out";
  private static final String UINEEDEDERROR = "Client Error Type - UI Needed";
  private static final String USERCANCEL = "Client Error Type - User canceled";
  
  public static void trackClose(ErrorActivity.ErrorScreen paramErrorScreen, CharSequence paramCharSequence)
  {
    try
    {
      UTCPageAction.track("Errors - Close error screen", paramCharSequence);
      return;
    }
    catch (Exception paramErrorScreen)
    {
      UTCLog.log(paramErrorScreen.getMessage(), new Object[0]);
    }
  }
  
  public static void trackFailure(String paramString, boolean paramBoolean, UTCTelemetry.CallBackSources paramCallBackSources, long paramLong)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("isSilent", Boolean.valueOf(paramBoolean));
      localUTCAdditionalInfoModel.addValue("job", paramString);
      localUTCAdditionalInfoModel.addValue("source", paramCallBackSources);
      paramString = new ClientError();
      paramString.setErrorName("Client Error Type - Failure");
      paramString.setErrorCode(String.format("%s", new Object[] { Long.valueOf(paramLong) }));
      paramString.setPageName(UTCPageView.getCurrentPage());
      paramString.setBaseData(UTCCommonDataModel.getCommonData(1, localUTCAdditionalInfoModel));
      UTCLog.log("Error:%s, errorCode:%s, additionalInfo:%s", new Object[] { "Client Error Type - Failure", Long.valueOf(paramLong), localUTCAdditionalInfoModel });
      UTCTelemetry.LogEvent(paramString);
      return;
    }
    catch (Exception paramString)
    {
      UTCLog.log(paramString.getMessage(), new Object[0]);
    }
  }
  
  public static void trackFailure(String paramString, boolean paramBoolean, UTCTelemetry.CallBackSources paramCallBackSources, Exception paramException)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("isSilent", Boolean.valueOf(paramBoolean));
      localUTCAdditionalInfoModel.addValue("job", paramString);
      localUTCAdditionalInfoModel.addValue("source", paramCallBackSources);
      paramCallBackSources = new ClientError();
      paramCallBackSources.setErrorName("Client Error Type - Failure");
      paramCallBackSources.setPageName(UTCPageView.getCurrentPage());
      paramString = "";
      if (paramException != null)
      {
        paramString = paramException.getClass().getSimpleName();
        paramException = paramException.getMessage();
        paramCallBackSources.setErrorName(paramString);
        paramCallBackSources.setErrorText(paramException);
      }
      paramCallBackSources.setBaseData(UTCCommonDataModel.getCommonData(1, localUTCAdditionalInfoModel));
      UTCLog.log("Error:%s, exception:%s, additionalInfo:%s", new Object[] { "Client Error Type - Failure", paramString, localUTCAdditionalInfoModel });
      UTCTelemetry.LogEvent(paramCallBackSources);
      return;
    }
    catch (Exception paramString)
    {
      UTCLog.log(paramString.getMessage(), new Object[0]);
    }
  }
  
  public static void trackGoToEnforcement(ErrorActivity.ErrorScreen paramErrorScreen, CharSequence paramCharSequence)
  {
    try
    {
      UTCPageAction.track("Errors - View enforcement site", paramCharSequence);
      return;
    }
    catch (Exception paramErrorScreen)
    {
      UTCLog.log(paramErrorScreen.getMessage(), new Object[0]);
    }
  }
  
  public static void trackPageView(ErrorActivity.ErrorScreen paramErrorScreen, CharSequence paramCharSequence)
  {
    try
    {
      UTCPageView.track(UTCTelemetry.getErrorScreen(paramErrorScreen), paramCharSequence);
      return;
    }
    catch (Exception paramErrorScreen)
    {
      UTCLog.log(paramErrorScreen.getMessage(), new Object[0]);
    }
  }
  
  public static void trackRightButton(ErrorActivity.ErrorScreen paramErrorScreen, CharSequence paramCharSequence)
  {
    try
    {
      UTCPageAction.track("Errors - Right button", paramCharSequence);
      return;
    }
    catch (Exception paramErrorScreen)
    {
      UTCLog.log(paramErrorScreen.getMessage(), new Object[0]);
    }
  }
  
  public static void trackServiceFailure(String paramString1, String paramString2, HttpError paramHttpError)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("pageName", paramString2);
      String str2 = "UNKNOWN";
      String str1 = "0";
      if (paramHttpError != null)
      {
        str2 = paramHttpError.getErrorMessage();
        str1 = String.format("%s", new Object[] { Integer.valueOf(paramHttpError.getErrorCode()) });
      }
      paramHttpError = new ServiceError();
      paramHttpError.setErrorName(paramString1);
      paramHttpError.setErrorText(str2);
      paramString1 = paramString2;
      if (paramString2 == null) {
        paramString1 = UTCPageView.getCurrentPage();
      }
      paramHttpError.setPageName(paramString1);
      paramHttpError.setErrorCode(String.format("%s", new Object[] { str1 }));
      paramHttpError.setBaseData(UTCCommonDataModel.getCommonData(1, localUTCAdditionalInfoModel));
      UTCLog.log("Service Error:%s, errorCode:%s, additionalInfo:%s", new Object[] { "Client Error Type - Failure", str1, localUTCAdditionalInfoModel });
      UTCTelemetry.LogEvent(paramHttpError);
      return;
    }
    catch (Exception paramString1)
    {
      UTCLog.log(paramString1.getMessage(), new Object[0]);
    }
  }
  
  public static void trackSignedOut(String paramString, boolean paramBoolean, UTCTelemetry.CallBackSources paramCallBackSources)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("isSilent", Boolean.valueOf(paramBoolean));
      localUTCAdditionalInfoModel.addValue("job", paramString);
      localUTCAdditionalInfoModel.addValue("source", paramCallBackSources);
      paramString = new ClientError();
      paramString.setPageName(UTCPageView.getCurrentPage());
      paramString.setErrorName("Client Error Type - Signed out");
      paramString.setBaseData(UTCCommonDataModel.getCommonData(1, localUTCAdditionalInfoModel));
      UTCLog.log("Error:%s, additionalInfo:%s", new Object[] { "Client Error Type - Signed out", localUTCAdditionalInfoModel });
      UTCTelemetry.LogEvent(paramString);
      return;
    }
    catch (Exception paramString)
    {
      UTCLog.log(paramString.getMessage(), new Object[0]);
    }
  }
  
  public static void trackTryAgain(ErrorActivity.ErrorScreen paramErrorScreen, CharSequence paramCharSequence)
  {
    try
    {
      UTCPageAction.track("Errors - Try again", paramCharSequence);
      return;
    }
    catch (Exception paramErrorScreen)
    {
      UTCLog.log(paramErrorScreen.getMessage(), new Object[0]);
    }
  }
  
  public static void trackUINeeded(String paramString, boolean paramBoolean, UTCTelemetry.CallBackSources paramCallBackSources)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("isSilent", Boolean.valueOf(paramBoolean));
      localUTCAdditionalInfoModel.addValue("job", paramString);
      localUTCAdditionalInfoModel.addValue("source", paramCallBackSources);
      paramString = new ClientError();
      paramString.setPageName(UTCPageView.getCurrentPage());
      paramString.setErrorName("Client Error Type - UI Needed");
      paramString.setBaseData(UTCCommonDataModel.getCommonData(1, localUTCAdditionalInfoModel));
      UTCLog.log("Error:%s, additionalInfo:%s", new Object[] { "Client Error Type - UI Needed", localUTCAdditionalInfoModel });
      UTCTelemetry.LogEvent(paramString);
      return;
    }
    catch (Exception paramString)
    {
      UTCLog.log(paramString.getMessage(), new Object[0]);
    }
  }
  
  public static void trackUserCancel(String paramString, boolean paramBoolean, UTCTelemetry.CallBackSources paramCallBackSources)
  {
    try
    {
      UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
      localUTCAdditionalInfoModel.addValue("isSilent", Boolean.valueOf(paramBoolean));
      localUTCAdditionalInfoModel.addValue("job", paramString);
      localUTCAdditionalInfoModel.addValue("source", paramCallBackSources);
      paramString = new ClientError();
      paramString.setPageName(UTCPageView.getCurrentPage());
      paramString.setErrorName("Client Error Type - User canceled");
      paramString.setBaseData(UTCCommonDataModel.getCommonData(1, localUTCAdditionalInfoModel));
      UTCLog.log("Error:%s, additionalInfo:%s", new Object[] { "Client Error Type - User canceled", localUTCAdditionalInfoModel });
      UTCTelemetry.LogEvent(paramString);
      return;
    }
    catch (Exception paramString)
    {
      UTCLog.log(paramString.getMessage(), new Object[0]);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCError.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */