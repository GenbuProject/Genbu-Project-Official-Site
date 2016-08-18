package com.microsoft.xbox.xle.viewmodel;

import com.microsoft.xbox.service.model.FollowersData;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.model.UserProfileData;

public final class FriendSelectorItem
  extends FollowersData
{
  private static final long serialVersionUID = 5799344980951867134L;
  private boolean selected;
  
  public FriendSelectorItem(FollowersData paramFollowersData)
  {
    super(paramFollowersData);
    this.selected = false;
  }
  
  public FriendSelectorItem(ProfileModel paramProfileModel)
  {
    this.xuid = paramProfileModel.getXuid();
    this.userProfileData = new UserProfileData();
    this.userProfileData.gamerTag = paramProfileModel.getGamerTag();
    this.userProfileData.xuid = paramProfileModel.getXuid();
    this.userProfileData.profileImageUrl = paramProfileModel.getGamerPicImageUrl();
    this.userProfileData.gamerScore = paramProfileModel.getGamerScore();
    this.userProfileData.appDisplayName = paramProfileModel.getAppDisplayName();
    this.userProfileData.accountTier = paramProfileModel.getAccountTier();
    this.userProfileData.gamerRealName = paramProfileModel.getRealName();
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {}
    do
    {
      do
      {
        return true;
        if (paramObject == null) {
          return false;
        }
        if (getClass() != paramObject.getClass()) {
          return false;
        }
        paramObject = (FriendSelectorItem)paramObject;
        if ((this.userProfileData != null) && (this.userProfileData.gamerTag != null)) {
          break;
        }
      } while ((((FriendSelectorItem)paramObject).userProfileData == null) && (((FriendSelectorItem)paramObject).userProfileData.gamerTag == null));
      return false;
    } while (this.userProfileData.gamerTag.equals(((FriendSelectorItem)paramObject).userProfileData.gamerTag));
    return false;
  }
  
  public boolean getIsSelected()
  {
    return this.selected;
  }
  
  public int hashCode()
  {
    if ((this.userProfileData == null) || (this.userProfileData.gamerTag == null)) {}
    for (int i = 0;; i = this.userProfileData.gamerTag.hashCode()) {
      return i + 31;
    }
  }
  
  public void setSelected(boolean paramBoolean)
  {
    this.selected = paramBoolean;
  }
  
  public void toggleSelection()
  {
    if (!this.selected) {}
    for (boolean bool = true;; bool = false)
    {
      this.selected = bool;
      return;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\viewmodel\FriendSelectorItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */