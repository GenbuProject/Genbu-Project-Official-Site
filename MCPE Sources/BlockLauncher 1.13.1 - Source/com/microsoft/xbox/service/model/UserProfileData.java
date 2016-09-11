package com.microsoft.xbox.service.model;

import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPersonSummary;

public class UserProfileData
{
  public String TenureLevel;
  public String accountTier;
  public String appDisplayName;
  public String gamerRealName;
  public String gamerScore;
  public String gamerTag;
  public String profileImageUrl;
  public String xuid;
  
  public UserProfileData() {}
  
  public UserProfileData(IPeopleHubResult.PeopleHubPersonSummary paramPeopleHubPersonSummary)
  {
    this.xuid = paramPeopleHubPersonSummary.xuid;
    this.profileImageUrl = paramPeopleHubPersonSummary.displayPicRaw;
    this.gamerTag = paramPeopleHubPersonSummary.gamertag;
    this.appDisplayName = paramPeopleHubPersonSummary.displayName;
    this.gamerRealName = paramPeopleHubPersonSummary.realName;
    this.gamerScore = paramPeopleHubPersonSummary.gamerScore;
    this.accountTier = paramPeopleHubPersonSummary.xboxOneRep;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\UserProfileData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */