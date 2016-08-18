package com.microsoft.xbox.service.network.managers.xblshared;

import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.FriendsFinderStateResult;
import com.microsoft.xbox.service.model.friendfinder.LinkedAccountHelpers.LinkedAccountType;
import com.microsoft.xbox.service.model.friendfinder.OptInStatus;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileRequest;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileResponse;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.UploadPhoneContactsRequest;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.UploadPhoneContactsResponse;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySetting;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySettingId;
import com.microsoft.xbox.service.model.privacy.PrivacySettingsResult;
import com.microsoft.xbox.service.network.managers.AddFollowingUserResponseContainer.AddFollowingUserResponse;
import com.microsoft.xbox.service.network.managers.FamilySettings;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPeopleSummary;
import com.microsoft.xbox.service.network.managers.IUserProfileResult.UserProfileResult;
import com.microsoft.xbox.service.network.managers.MutedListResultContainer.MutedListResult;
import com.microsoft.xbox.service.network.managers.NeverListResultContainer.NeverListResult;
import com.microsoft.xbox.service.network.managers.ProfilePreferredColor;
import com.microsoft.xbox.service.network.managers.ProfileSummaryResultContainer.ProfileSummaryResult;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.xle.app.FriendFinderSettings;

public abstract interface ISLSServiceManager
{
  public abstract IUserProfileResult.UserProfileResult SearchGamertag(String paramString)
    throws XLEException;
  
  public abstract boolean addFriendToShareIdentitySetting(String paramString1, String paramString2)
    throws XLEException;
  
  public abstract boolean addUserToFavoriteList(String paramString)
    throws XLEException;
  
  public abstract AddFollowingUserResponseContainer.AddFollowingUserResponse addUserToFollowingList(String paramString)
    throws XLEException;
  
  public abstract boolean addUserToMutedList(String paramString1, String paramString2)
    throws XLEException;
  
  public abstract boolean addUserToNeverList(String paramString1, String paramString2)
    throws XLEException;
  
  public abstract FamilySettings getFamilySettings(String paramString)
    throws XLEException;
  
  public abstract FriendFinderSettings getFriendFinderSettings()
    throws XLEException;
  
  public abstract MutedListResultContainer.MutedListResult getMutedListInfo(String paramString)
    throws XLEException;
  
  public abstract ShortCircuitProfileMessage.ShortCircuitProfileResponse getMyShortCircuitProfile()
    throws XLEException;
  
  public abstract NeverListResultContainer.NeverListResult getNeverListInfo(String paramString)
    throws XLEException;
  
  public abstract FriendFinderState.FriendsFinderStateResult getPeopleHubFriendFinderState()
    throws XLEException;
  
  public abstract IPeopleHubResult.PeopleHubPeopleSummary getPeopleHubRecommendations()
    throws XLEException;
  
  public abstract PrivacySettings.PrivacySetting getPrivacySetting(PrivacySettings.PrivacySettingId paramPrivacySettingId)
    throws XLEException;
  
  public abstract ProfilePreferredColor getProfilePreferredColor(String paramString)
    throws XLEException;
  
  public abstract ProfileSummaryResultContainer.ProfileSummaryResult getProfileSummaryInfo(String paramString)
    throws XLEException;
  
  public abstract IUserProfileResult.UserProfileResult getUserProfileInfo(String paramString)
    throws XLEException;
  
  public abstract PrivacySettingsResult getUserProfilePrivacySettings()
    throws XLEException;
  
  public abstract int[] getXTokenPrivileges()
    throws XLEException;
  
  public abstract boolean removeFriendFromShareIdentitySetting(String paramString1, String paramString2)
    throws XLEException;
  
  public abstract boolean removeUserFromFavoriteList(String paramString)
    throws XLEException;
  
  public abstract boolean removeUserFromFollowingList(String paramString)
    throws XLEException;
  
  public abstract boolean removeUserFromMutedList(String paramString1, String paramString2)
    throws XLEException;
  
  public abstract boolean removeUserFromNeverList(String paramString1, String paramString2)
    throws XLEException;
  
  public abstract ShortCircuitProfileMessage.ShortCircuitProfileResponse sendShortCircuitProfile(ShortCircuitProfileMessage.ShortCircuitProfileRequest paramShortCircuitProfileRequest)
    throws XLEException;
  
  public abstract boolean setFriendFinderOptInStatus(LinkedAccountHelpers.LinkedAccountType paramLinkedAccountType, OptInStatus paramOptInStatus)
    throws XLEException;
  
  public abstract boolean setPrivacySettings(PrivacySettingsResult paramPrivacySettingsResult)
    throws XLEException;
  
  public abstract boolean submitFeedback(String paramString1, String paramString2)
    throws XLEException;
  
  public abstract ShortCircuitProfileMessage.UploadPhoneContactsResponse updatePhoneContacts(ShortCircuitProfileMessage.UploadPhoneContactsRequest paramUploadPhoneContactsRequest)
    throws XLEException;
  
  public abstract boolean updateThirdPartyToken(LinkedAccountHelpers.LinkedAccountType paramLinkedAccountType, String paramString)
    throws XLEException;
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\xblshared\ISLSServiceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */