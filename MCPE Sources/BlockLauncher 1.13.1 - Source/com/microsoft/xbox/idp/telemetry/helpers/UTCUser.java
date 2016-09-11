package com.microsoft.xbox.idp.telemetry.helpers;

import com.microsoft.xbox.idp.telemetry.utc.model.UTCAdditionalInfoModel;

public class UTCUser
{
  private static final boolean DEFAULT = true;
  private static boolean isSilent = true;
  
  public static void setIsSilent(boolean paramBoolean)
  {
    isSilent = paramBoolean;
  }
  
  public static void trackCancel(CharSequence paramCharSequence)
  {
    new UTCAdditionalInfoModel();
    UTCPageAction.track("UserCancel - User canceled", paramCharSequence);
  }
  
  public static void trackSignout(CharSequence paramCharSequence)
  {
    UTCAdditionalInfoModel localUTCAdditionalInfoModel = new UTCAdditionalInfoModel();
    localUTCAdditionalInfoModel.addValue("isSilent", Boolean.valueOf(isSilent));
    UTCPageAction.track("Signout - User signed out", paramCharSequence, localUTCAdditionalInfoModel);
    isSilent = true;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\idp\telemetry\helpers\UTCUser.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */