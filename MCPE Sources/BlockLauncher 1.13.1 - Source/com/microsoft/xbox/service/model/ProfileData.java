package com.microsoft.xbox.service.model;

import com.microsoft.xbox.service.network.managers.IUserProfileResult.UserProfileResult;

public class ProfileData
{
  private IUserProfileResult.UserProfileResult profileResult;
  private boolean shareRealName;
  private String shareRealNameStatus;
  private boolean sharingRealNameTransitively;
  
  public ProfileData(IUserProfileResult.UserProfileResult paramUserProfileResult, boolean paramBoolean)
  {
    this.profileResult = paramUserProfileResult;
    this.shareRealName = paramBoolean;
    this.shareRealNameStatus = null;
  }
  
  public ProfileData(IUserProfileResult.UserProfileResult paramUserProfileResult, boolean paramBoolean1, String paramString, boolean paramBoolean2)
  {
    this.profileResult = paramUserProfileResult;
    this.shareRealName = paramBoolean1;
    this.shareRealNameStatus = paramString;
    this.sharingRealNameTransitively = paramBoolean2;
  }
  
  public IUserProfileResult.UserProfileResult getProfileResult()
  {
    return this.profileResult;
  }
  
  public boolean getShareRealName()
  {
    return this.shareRealName;
  }
  
  public String getShareRealNameStatus()
  {
    return this.shareRealNameStatus;
  }
  
  public boolean getSharingRealNameTransitively()
  {
    return this.sharingRealNameTransitively;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\ProfileData.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */