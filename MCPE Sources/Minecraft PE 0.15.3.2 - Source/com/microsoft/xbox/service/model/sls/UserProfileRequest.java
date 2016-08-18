package com.microsoft.xbox.service.model.sls;

import com.microsoft.xbox.toolkit.GsonUtil;
import java.util.ArrayList;

public class UserProfileRequest
{
  public ArrayList<String> settings;
  public ArrayList<String> userIds;
  
  public UserProfileRequest()
  {
    this.settings = new ArrayList();
    this.userIds = new ArrayList();
    setDefaultProfileSettingsRequest(false);
  }
  
  public UserProfileRequest(ArrayList<String> paramArrayList)
  {
    this(paramArrayList, false);
  }
  
  public UserProfileRequest(ArrayList<String> paramArrayList1, ArrayList<String> paramArrayList2)
  {
    this.userIds = paramArrayList1;
    this.settings = paramArrayList2;
  }
  
  public UserProfileRequest(ArrayList<String> paramArrayList, boolean paramBoolean)
  {
    this.userIds = paramArrayList;
    this.settings = new ArrayList();
    setDefaultProfileSettingsRequest(paramBoolean);
  }
  
  public static String getUserProfileRequestBody(UserProfileRequest paramUserProfileRequest)
  {
    return GsonUtil.toJsonString(paramUserProfileRequest);
  }
  
  private void setDefaultProfileSettingsRequest(boolean paramBoolean)
  {
    if (this.settings != null)
    {
      this.settings.add(UserProfileSetting.GameDisplayName.toString());
      this.settings.add(UserProfileSetting.AppDisplayName.toString());
      this.settings.add(UserProfileSetting.AppDisplayPicRaw.toString());
      this.settings.add(UserProfileSetting.Gamerscore.toString());
      this.settings.add(UserProfileSetting.Gamertag.toString());
      this.settings.add(UserProfileSetting.GameDisplayPicRaw.toString());
      this.settings.add(UserProfileSetting.AccountTier.toString());
      this.settings.add(UserProfileSetting.TenureLevel.toString());
      this.settings.add(UserProfileSetting.XboxOneRep.toString());
      this.settings.add(UserProfileSetting.PreferredColor.toString());
      this.settings.add(UserProfileSetting.Location.toString());
      this.settings.add(UserProfileSetting.Bio.toString());
      this.settings.add(UserProfileSetting.Watermarks.toString());
      if (!paramBoolean) {
        this.settings.add(UserProfileSetting.RealName.toString());
      }
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\sls\UserProfileRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */