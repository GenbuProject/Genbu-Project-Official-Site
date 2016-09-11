package com.microsoft.xbox.service.model.privacy;

import com.microsoft.xbox.toolkit.GsonUtil;
import java.util.ArrayList;
import java.util.Iterator;

public class PrivacySettingsResult
{
  public ArrayList<PrivacySettings.PrivacySetting> settings;
  
  public PrivacySettingsResult() {}
  
  public PrivacySettingsResult(ArrayList<PrivacySettings.PrivacySetting> paramArrayList)
  {
    this.settings = new ArrayList(paramArrayList);
  }
  
  public static PrivacySettingsResult deserialize(String paramString)
  {
    return (PrivacySettingsResult)GsonUtil.deserializeJson(paramString, PrivacySettingsResult.class);
  }
  
  public static String getPrivacySettingRequestBody(PrivacySettingsResult paramPrivacySettingsResult)
  {
    try
    {
      paramPrivacySettingsResult = GsonUtil.toJsonString(paramPrivacySettingsResult);
      return paramPrivacySettingsResult;
    }
    catch (Exception paramPrivacySettingsResult) {}
    return null;
  }
  
  public String getShareRealNameStatus()
  {
    Iterator localIterator = this.settings.iterator();
    while (localIterator.hasNext())
    {
      PrivacySettings.PrivacySetting localPrivacySetting = (PrivacySettings.PrivacySetting)localIterator.next();
      if (localPrivacySetting.getPrivacySettingId() == PrivacySettings.PrivacySettingId.ShareIdentity) {
        return localPrivacySetting.value;
      }
    }
    return PrivacySettings.PrivacySettingValue.PeopleOnMyList.name();
  }
  
  public boolean getSharingRealNameTransitively()
  {
    Iterator localIterator = this.settings.iterator();
    while (localIterator.hasNext())
    {
      PrivacySettings.PrivacySetting localPrivacySetting = (PrivacySettings.PrivacySetting)localIterator.next();
      if (localPrivacySetting.getPrivacySettingId() == PrivacySettings.PrivacySettingId.ShareIdentityTransitively) {
        return localPrivacySetting.value.equalsIgnoreCase(PrivacySettings.PrivacySettingValue.Everyone.name());
      }
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\privacy\PrivacySettingsResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */