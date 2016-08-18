package com.microsoft.xbox.service.network.managers;

import com.microsoft.xbox.service.model.sls.UserProfileSetting;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.GsonUtil;
import java.util.ArrayList;
import java.util.Iterator;

public abstract interface IUserProfileResult
{
  public static class ProfileUser
  {
    private static final long FORCE_MATURITY_LEVEL_UPDATE_TIME = 10800000L;
    public boolean canViewTVAdultContent;
    public ProfilePreferredColor colors;
    public String id;
    private int maturityLevel;
    private int[] privileges;
    public ArrayList<IUserProfileResult.Settings> settings;
    private long updateMaturityLevelTimer = -1L;
    
    private void fetchMaturityLevel()
    {
      for (;;)
      {
        try
        {
          FamilySettings localFamilySettings = ServiceManagerFactory.getInstance().getSLSServiceManager().getFamilySettings(this.id);
          if ((localFamilySettings != null) && (localFamilySettings.familyUsers != null))
          {
            i = 0;
            if (i < localFamilySettings.familyUsers.size())
            {
              if (!((FamilyUser)localFamilySettings.familyUsers.get(i)).xuid.equalsIgnoreCase(this.id)) {
                continue;
              }
              this.canViewTVAdultContent = ((FamilyUser)localFamilySettings.familyUsers.get(i)).canViewTVAdultContent;
              this.maturityLevel = ((FamilyUser)localFamilySettings.familyUsers.get(i)).maturityLevel;
            }
          }
        }
        catch (Throwable localThrowable)
        {
          int i;
          continue;
        }
        this.updateMaturityLevelTimer = System.currentTimeMillis();
        return;
        i += 1;
      }
    }
    
    public int getMaturityLevel()
    {
      if ((this.updateMaturityLevelTimer < 0L) || (System.currentTimeMillis() - this.updateMaturityLevelTimer > 10800000L)) {
        fetchMaturityLevel();
      }
      return this.maturityLevel;
    }
    
    public int[] getPrivileges()
    {
      return this.privileges;
    }
    
    public String getSettingValue(UserProfileSetting paramUserProfileSetting)
    {
      if (this.settings != null)
      {
        Iterator localIterator = this.settings.iterator();
        while (localIterator.hasNext())
        {
          IUserProfileResult.Settings localSettings = (IUserProfileResult.Settings)localIterator.next();
          if ((localSettings.id != null) && (localSettings.id.equals(paramUserProfileSetting.toString()))) {
            return localSettings.value;
          }
        }
      }
      return null;
    }
    
    public void setPrivilieges(int[] paramArrayOfInt)
    {
      this.privileges = paramArrayOfInt;
    }
    
    public void setmaturityLevel(int paramInt)
    {
      this.maturityLevel = paramInt;
      this.updateMaturityLevelTimer = System.currentTimeMillis();
    }
  }
  
  public static class Settings
  {
    public String id;
    public String value;
  }
  
  public static class UserProfileResult
  {
    public ArrayList<IUserProfileResult.ProfileUser> profileUsers;
    
    public static UserProfileResult deserialize(String paramString)
    {
      return (UserProfileResult)GsonUtil.deserializeJson(paramString, UserProfileResult.class);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\IUserProfileResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */