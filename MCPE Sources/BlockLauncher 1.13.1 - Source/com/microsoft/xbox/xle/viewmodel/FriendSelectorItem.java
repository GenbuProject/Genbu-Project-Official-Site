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
    boolean bool2 = false;
    if (this == paramObject) {}
    do
    {
      do
      {
        boolean bool1 = true;
        do
        {
          do
          {
            do
            {
              return bool1;
              bool1 = bool2;
            } while (paramObject == null);
            bool1 = bool2;
          } while (getClass() != paramObject.getClass());
          paramObject = (FriendSelectorItem)paramObject;
          if ((this.userProfileData != null) && (this.userProfileData.gamerTag != null)) {
            break;
          }
          bool1 = bool2;
        } while (((FriendSelectorItem)paramObject).userProfileData != null);
      } while (((FriendSelectorItem)paramObject).userProfileData.gamerTag == null);
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


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\viewmodel\FriendSelectorItem.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */