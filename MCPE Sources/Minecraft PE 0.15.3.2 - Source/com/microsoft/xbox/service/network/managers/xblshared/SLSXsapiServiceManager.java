package com.microsoft.xbox.service.network.managers.xblshared;

import android.util.Log;
import android.util.Pair;
import com.microsoft.xbox.idp.util.HttpCall;
import com.microsoft.xbox.idp.util.HttpCall.Callback;
import com.microsoft.xbox.idp.util.HttpUtil;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.FriendsFinderStateResult;
import com.microsoft.xbox.service.model.friendfinder.LinkedAccountHelpers.LinkedAccountType;
import com.microsoft.xbox.service.model.friendfinder.OptInStatus;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileRequest;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileResponse;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.UploadPhoneContactsRequest;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.UploadPhoneContactsResponse;
import com.microsoft.xbox.service.model.friendfinder.UpdateThirdPartyTokenRequest;
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
import com.microsoft.xbox.toolkit.GsonUtil;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.ProjectSpecificDataProvider;
import com.microsoft.xbox.toolkit.TcuiHttpUtil;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.network.XboxLiveEnvironment;
import com.microsoft.xbox.xle.app.FriendFinderSettings;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.concurrent.atomic.AtomicReference;

public class SLSXsapiServiceManager
  implements ISLSServiceManager
{
  private static final String TAG = SLSXsapiServiceManager.class.getSimpleName();
  
  public IUserProfileResult.UserProfileResult SearchGamertag(String paramString)
    throws XLEException
  {
    boolean bool = true;
    Log.i(TAG, "SearchGamertag");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (;;)
    {
      XLEAssert.assertTrue(bool);
      try
      {
        paramString = (IUserProfileResult.UserProfileResult)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", String.format(XboxLiveEnvironment.Instance().getGamertagSearchUrlFormat(), new Object[] { URLEncoder.encode(paramString.toLowerCase(), "utf-8") }), ""), "2"), IUserProfileResult.UserProfileResult.class);
        TcuiHttpUtil.throwIfNullOrFalse(paramString);
        return paramString;
      }
      catch (UnsupportedEncodingException paramString)
      {
        throw new XLEException(15L, paramString);
      }
      bool = false;
    }
  }
  
  public boolean addFriendToShareIdentitySetting(String paramString1, String paramString2)
    throws XLEException
  {
    Log.i(TAG, "addFriendToShareIdentitySetting");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString1 = HttpUtil.appendCommonParameters(new HttpCall("POST", String.format(XboxLiveEnvironment.Instance().getAddFriendsToShareIdentityUrlFormat(), new Object[] { paramString1 }), ""), "4");
      paramString1.setRequestBody(paramString2);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramString1, Arrays.asList(new Integer[] { Integer.valueOf(204) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public boolean addUserToFavoriteList(String paramString)
    throws XLEException
  {
    Log.i(TAG, "addUserToFavoriteList");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      HttpCall localHttpCall = HttpUtil.appendCommonParameters(new HttpCall("POST", String.format(XboxLiveEnvironment.Instance().getProfileFavoriteListUrl(), new Object[] { "add" }), ""), "1");
      localHttpCall.setRequestBody(paramString);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(localHttpCall, Arrays.asList(new Integer[] { Integer.valueOf(204) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public AddFollowingUserResponseContainer.AddFollowingUserResponse addUserToFollowingList(final String paramString)
    throws XLEException
  {
    Log.i(TAG, "addUserToFollowingList");
    boolean bool;
    if (Thread.currentThread() != ThreadManager.UIThread) {
      bool = true;
    }
    for (;;)
    {
      XLEAssert.assertTrue(bool);
      HttpCall localHttpCall = HttpUtil.appendCommonParameters(new HttpCall("POST", String.format(XboxLiveEnvironment.Instance().updateProfileFollowingListUrl(), new Object[] { "add" }), ""), "1");
      localHttpCall.setRequestBody(paramString);
      final AddFollowingUserResponseContainer.AddFollowingUserResponse localAddFollowingUserResponse = new AddFollowingUserResponseContainer.AddFollowingUserResponse();
      paramString = new AtomicReference();
      paramString.set(new Pair(Boolean.valueOf(false), null));
      localHttpCall.getResponseAsync(new HttpCall.Callback()
      {
        public void processHttpError(int paramAnonymousInt1, int paramAnonymousInt2, String paramAnonymousString)
        {
          localAtomicReference = paramString;
          if (paramAnonymousInt2 == 204) {}
          for (;;)
          {
            try
            {
              localAddFollowingUserResponse.setAddFollowingRequestStatus(true);
              paramString.set(new Pair(Boolean.valueOf(true), localAddFollowingUserResponse));
              paramString.notify();
              return;
            }
            finally {}
            paramAnonymousString = (AddFollowingUserResponseContainer.AddFollowingUserResponse)GsonUtil.deserializeJson(paramAnonymousString, AddFollowingUserResponseContainer.AddFollowingUserResponse.class);
            paramString.set(new Pair(Boolean.valueOf(true), paramAnonymousString));
          }
        }
        
        public void processResponse(InputStream arg1)
          throws Exception
        {
          synchronized (paramString)
          {
            localAddFollowingUserResponse.setAddFollowingRequestStatus(true);
            paramString.set(new Pair(Boolean.valueOf(true), localAddFollowingUserResponse));
            paramString.notify();
            return;
          }
        }
      });
      try
      {
        while (!((Boolean)((Pair)paramString.get()).first).booleanValue()) {
          paramString.wait();
        }
      }
      catch (InterruptedException localInterruptedException)
      {
        TcuiHttpUtil.throwIfNullOrFalse(((Pair)paramString.get()).second);
        return (AddFollowingUserResponseContainer.AddFollowingUserResponse)((Pair)paramString.get()).second;
        bool = false;
      }
      finally {}
    }
  }
  
  public boolean addUserToMutedList(String paramString1, String paramString2)
    throws XLEException
  {
    Log.i(TAG, "addUserToMutedList");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString1 = HttpUtil.appendCommonParameters(new HttpCall("PUT", String.format(XboxLiveEnvironment.Instance().getMutedServiceUrlFormat(), new Object[] { paramString1 }), ""), "1");
      paramString1.setRequestBody(paramString2);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramString1, new ArrayList(0));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public boolean addUserToNeverList(String paramString1, String paramString2)
    throws XLEException
  {
    Log.i(TAG, "addUserToNeverList");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString1 = HttpUtil.appendCommonParameters(new HttpCall("PUT", String.format(XboxLiveEnvironment.Instance().getProfileNeverListUrlFormat(), new Object[] { paramString1 }), ""), "1");
      paramString1.setRequestBody(paramString2);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramString1, new ArrayList(0));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public FamilySettings getFamilySettings(String paramString)
    throws XLEException
  {
    return null;
  }
  
  public FriendFinderSettings getFriendFinderSettings()
    throws XLEException
  {
    Log.i(TAG, "getFriendFinderSettings");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      FriendFinderSettings localFriendFinderSettings = (FriendFinderSettings)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", XboxLiveEnvironment.Instance().getFriendFinderSettingsUrl(), "", false), "1"), FriendFinderSettings.class);
      TcuiHttpUtil.throwIfNullOrFalse(localFriendFinderSettings);
      return localFriendFinderSettings;
    }
  }
  
  public MutedListResultContainer.MutedListResult getMutedListInfo(String paramString)
    throws XLEException
  {
    Log.i(TAG, "getMutedListInfo");
    if (Thread.currentThread() != ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (JavaUtil.isNullOrEmpty(paramString)) {
        break label90;
      }
    }
    label90:
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString = (MutedListResultContainer.MutedListResult)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", String.format(XboxLiveEnvironment.Instance().getMutedServiceUrlFormat(), new Object[] { paramString }), ""), "1"), MutedListResultContainer.MutedListResult.class);
      TcuiHttpUtil.throwIfNullOrFalse(paramString);
      return paramString;
      bool = false;
      break;
    }
  }
  
  public ShortCircuitProfileMessage.ShortCircuitProfileResponse getMyShortCircuitProfile()
    throws XLEException
  {
    Log.i(TAG, "getMyShortCircuitProfile");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      str = ProjectSpecificDataProvider.getInstance().getSCDRpsTicket();
      XLEAssert.assertFalse("Expected to have acquired a ticket already", JavaUtil.isNullOrEmpty(str));
      if (!JavaUtil.isNullOrEmpty(str)) {
        break;
      }
      throw new XLEException(2L);
    }
    HttpCall localHttpCall = new HttpCall("GET", XboxLiveEnvironment.Instance().getShortCircuitProfileUrlFormat(), "");
    localHttpCall.setCustomHeader("PS-MSAAuthTicket", str);
    localHttpCall.setCustomHeader("PS-ApplicationId", "44445A65-4A71-4083-8C90-041A22856E69");
    localHttpCall.setCustomHeader("PS-Scenario", "Minecraft TCUI Friend Finder");
    localHttpCall.setCustomHeader("Content-Type", "application/x-www-form-urlencoded");
    String str = TcuiHttpUtil.getResponseBodySync(localHttpCall);
    if (JavaUtil.isNullOrEmpty(str)) {
      throw new XLEException(2L);
    }
    return ShortCircuitProfileMessage.ShortCircuitProfileResponse.parseJson(str);
  }
  
  public NeverListResultContainer.NeverListResult getNeverListInfo(String paramString)
    throws XLEException
  {
    Log.i(TAG, "getNeverListInfo");
    if (Thread.currentThread() != ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (JavaUtil.isNullOrEmpty(paramString)) {
        break label92;
      }
    }
    label92:
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString = (NeverListResultContainer.NeverListResult)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", String.format(XboxLiveEnvironment.Instance().getProfileNeverListUrlFormat(), new Object[] { paramString }), ""), "1"), NeverListResultContainer.NeverListResult.class);
      TcuiHttpUtil.throwIfNullOrFalse(paramString);
      return paramString;
      bool = false;
      break;
    }
  }
  
  public FriendFinderState.FriendsFinderStateResult getPeopleHubFriendFinderState()
    throws XLEException
  {
    Log.i(TAG, "getPeopleHubFriendFinderState");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      Object localObject = HttpUtil.appendCommonParameters(new HttpCall("GET", XboxLiveEnvironment.Instance().getPeopleHubFriendFinderStateUrlFormat(), ""), "1");
      ((HttpCall)localObject).setCustomHeader("Accept-Language", ProjectSpecificDataProvider.getInstance().getLegalLocale());
      ((HttpCall)localObject).setCustomHeader("X-XBL-Contract-Version", "1");
      ((HttpCall)localObject).setCustomHeader("X-XBL-Market", ProjectSpecificDataProvider.getInstance().getRegion());
      localObject = (FriendFinderState.FriendsFinderStateResult)TcuiHttpUtil.getResponseSync((HttpCall)localObject, FriendFinderState.FriendsFinderStateResult.class);
      TcuiHttpUtil.throwIfNullOrFalse(localObject);
      return (FriendFinderState.FriendsFinderStateResult)localObject;
    }
  }
  
  public IPeopleHubResult.PeopleHubPeopleSummary getPeopleHubRecommendations()
    throws XLEException
  {
    Log.i(TAG, "getPeopleHubRecommendations");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      Object localObject = HttpUtil.appendCommonParameters(new HttpCall("GET", XboxLiveEnvironment.Instance().getPeopleHubRecommendationsUrlFormat(), ""), "1");
      ((HttpCall)localObject).setCustomHeader("Accept-Language", ProjectSpecificDataProvider.getInstance().getLegalLocale());
      ((HttpCall)localObject).setCustomHeader("X-XBL-Contract-Version", "1");
      ((HttpCall)localObject).setCustomHeader("X-XBL-Market", ProjectSpecificDataProvider.getInstance().getRegion());
      localObject = (IPeopleHubResult.PeopleHubPeopleSummary)TcuiHttpUtil.getResponseSync((HttpCall)localObject, IPeopleHubResult.PeopleHubPeopleSummary.class);
      TcuiHttpUtil.throwIfNullOrFalse(localObject);
      return (IPeopleHubResult.PeopleHubPeopleSummary)localObject;
    }
  }
  
  public PrivacySettings.PrivacySetting getPrivacySetting(PrivacySettings.PrivacySettingId paramPrivacySettingId)
    throws XLEException
  {
    Log.i(TAG, "getPrivacySetting");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramPrivacySettingId = (PrivacySettings.PrivacySetting)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", String.format(XboxLiveEnvironment.Instance().getProfileSettingUrlFormat(), new Object[] { paramPrivacySettingId.name() }), ""), "4"), PrivacySettings.PrivacySetting.class);
      TcuiHttpUtil.throwIfNullOrFalse(paramPrivacySettingId);
      return paramPrivacySettingId;
    }
  }
  
  public ProfilePreferredColor getProfilePreferredColor(String paramString)
    throws XLEException
  {
    Log.i(TAG, "getProfilePreferredColor");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString = (ProfilePreferredColor)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", paramString, ""), ""), ProfilePreferredColor.class);
      TcuiHttpUtil.throwIfNullOrFalse(paramString);
      return paramString;
    }
  }
  
  public ProfileSummaryResultContainer.ProfileSummaryResult getProfileSummaryInfo(String paramString)
    throws XLEException
  {
    Log.i(TAG, "getProfileSummaryInfo");
    if (Thread.currentThread() != ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      if (JavaUtil.isNullOrEmpty(paramString)) {
        break label92;
      }
    }
    label92:
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString = (ProfileSummaryResultContainer.ProfileSummaryResult)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", String.format(XboxLiveEnvironment.Instance().getProfileSummaryUrlFormat(), new Object[] { paramString }), ""), "2"), ProfileSummaryResultContainer.ProfileSummaryResult.class);
      TcuiHttpUtil.throwIfNullOrFalse(paramString);
      return paramString;
      bool = false;
      break;
    }
  }
  
  public IUserProfileResult.UserProfileResult getUserProfileInfo(String paramString)
    throws XLEException
  {
    Log.i(TAG, "getUserProfileInfo");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      HttpCall localHttpCall = HttpUtil.appendCommonParameters(new HttpCall("POST", XboxLiveEnvironment.Instance().getUserProfileInfoUrl(), ""), "2");
      localHttpCall.setRequestBody(paramString);
      paramString = (IUserProfileResult.UserProfileResult)TcuiHttpUtil.getResponseSync(localHttpCall, IUserProfileResult.UserProfileResult.class);
      TcuiHttpUtil.throwIfNullOrFalse(paramString);
      return paramString;
    }
  }
  
  public PrivacySettingsResult getUserProfilePrivacySettings()
    throws XLEException
  {
    Log.i(TAG, "getUserProfilePrivacySettings");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      PrivacySettingsResult localPrivacySettingsResult = (PrivacySettingsResult)TcuiHttpUtil.getResponseSync(HttpUtil.appendCommonParameters(new HttpCall("GET", XboxLiveEnvironment.Instance().getUserProfileSettingUrlFormat(), ""), "4"), PrivacySettingsResult.class);
      TcuiHttpUtil.throwIfNullOrFalse(localPrivacySettingsResult);
      return localPrivacySettingsResult;
    }
  }
  
  public int[] getXTokenPrivileges()
    throws XLEException
  {
    return new int[0];
  }
  
  public boolean removeFriendFromShareIdentitySetting(String paramString1, String paramString2)
    throws XLEException
  {
    Log.i(TAG, "removeFriendFromShareIdentitySetting");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString1 = HttpUtil.appendCommonParameters(new HttpCall("POST", String.format(XboxLiveEnvironment.Instance().getRemoveUsersFromShareIdentityUrlFormat(), new Object[] { paramString1 }), ""), "4");
      paramString1.setRequestBody(paramString2);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramString1, Arrays.asList(new Integer[] { Integer.valueOf(204) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public boolean removeUserFromFavoriteList(String paramString)
    throws XLEException
  {
    Log.i(TAG, "removeUserFromFavoriteList");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      HttpCall localHttpCall = HttpUtil.appendCommonParameters(new HttpCall("POST", String.format(XboxLiveEnvironment.Instance().getProfileFavoriteListUrl(), new Object[] { "remove" }), ""), "1");
      localHttpCall.setRequestBody(paramString);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(localHttpCall, Arrays.asList(new Integer[] { Integer.valueOf(204) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public boolean removeUserFromFollowingList(String paramString)
    throws XLEException
  {
    Log.i(TAG, "removeUserFromFollowingList");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      HttpCall localHttpCall = HttpUtil.appendCommonParameters(new HttpCall("POST", String.format(XboxLiveEnvironment.Instance().updateProfileFollowingListUrl(), new Object[] { "remove" }), ""), "1");
      localHttpCall.setRequestBody(paramString);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(localHttpCall, Arrays.asList(new Integer[] { Integer.valueOf(204) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public boolean removeUserFromMutedList(String paramString1, String paramString2)
    throws XLEException
  {
    Log.i(TAG, "removeUserFromMutedList");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString1 = HttpUtil.appendCommonParameters(new HttpCall("DELETE", String.format(XboxLiveEnvironment.Instance().getMutedServiceUrlFormat(), new Object[] { paramString1 }), ""), "1");
      paramString1.setRequestBody(paramString2);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramString1, new ArrayList(0));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public boolean removeUserFromNeverList(String paramString1, String paramString2)
    throws XLEException
  {
    Log.i(TAG, "removeUserFromNeverList");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString1 = HttpUtil.appendCommonParameters(new HttpCall("DELETE", String.format(XboxLiveEnvironment.Instance().getProfileNeverListUrlFormat(), new Object[] { paramString1 }), ""), "1");
      paramString1.setRequestBody(paramString2);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramString1, new ArrayList(0));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public ShortCircuitProfileMessage.ShortCircuitProfileResponse sendShortCircuitProfile(ShortCircuitProfileMessage.ShortCircuitProfileRequest paramShortCircuitProfileRequest)
    throws XLEException
  {
    Log.i(TAG, "sendShortCircuitProfile");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    String str;
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      str = ProjectSpecificDataProvider.getInstance().getSCDRpsTicket();
      XLEAssert.assertFalse("Expected to have acquired a ticket already", JavaUtil.isNullOrEmpty(str));
      if (!JavaUtil.isNullOrEmpty(str)) {
        break;
      }
      throw new XLEException(2L);
    }
    HttpCall localHttpCall = new HttpCall("POST", XboxLiveEnvironment.Instance().getShortCircuitProfileUrlFormat(), "");
    localHttpCall.setCustomHeader("PS-MSAAuthTicket", str);
    localHttpCall.setCustomHeader("PS-ApplicationId", "44445A65-4A71-4083-8C90-041A22856E69");
    localHttpCall.setCustomHeader("PS-Scenario", "Minecraft TCUI Friend Finder");
    localHttpCall.setCustomHeader("Content-Type", "application/x-www-form-urlencoded");
    localHttpCall.setRequestBody(paramShortCircuitProfileRequest.toString());
    paramShortCircuitProfileRequest = TcuiHttpUtil.getResponseBodySync(localHttpCall);
    if (JavaUtil.isNullOrEmpty(paramShortCircuitProfileRequest)) {
      throw new XLEException(2L);
    }
    return ShortCircuitProfileMessage.ShortCircuitProfileResponse.parseJson(paramShortCircuitProfileRequest);
  }
  
  public boolean setFriendFinderOptInStatus(LinkedAccountHelpers.LinkedAccountType paramLinkedAccountType, OptInStatus paramOptInStatus)
    throws XLEException
  {
    Log.i(TAG, "setFriendFinderOptInStatus");
    boolean bool;
    String str;
    if (Thread.currentThread() != ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      str = String.format(XboxLiveEnvironment.Instance().getSetFriendFinderOptInStatusUrlFormat(), new Object[] { paramLinkedAccountType.name() });
      if (paramOptInStatus != OptInStatus.OptedIn) {
        break label121;
      }
    }
    label121:
    for (paramLinkedAccountType = "?status=OptedIn&waitForUpdate=true";; paramLinkedAccountType = "?status=OptedOut")
    {
      paramLinkedAccountType = HttpUtil.appendCommonParameters(new HttpCall("PUT", str, paramLinkedAccountType), "1");
      paramLinkedAccountType.setCustomHeader("Content-Length", "0");
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramLinkedAccountType, Arrays.asList(new Integer[] { Integer.valueOf(204) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
      bool = false;
      break;
    }
  }
  
  public boolean setPrivacySettings(PrivacySettingsResult paramPrivacySettingsResult)
    throws XLEException
  {
    Log.i(TAG, "setPrivacySettings");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      HttpCall localHttpCall = HttpUtil.appendCommonParameters(new HttpCall("PUT", XboxLiveEnvironment.Instance().getUserProfileSettingUrlFormat(), ""), "4");
      localHttpCall.setRequestBody(PrivacySettingsResult.getPrivacySettingRequestBody(paramPrivacySettingsResult));
      bool = TcuiHttpUtil.getResponseSyncSucceeded(localHttpCall, Arrays.asList(new Integer[] { Integer.valueOf(201) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public boolean submitFeedback(String paramString1, String paramString2)
    throws XLEException
  {
    Log.i(TAG, "submitFeedback");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramString1 = HttpUtil.appendCommonParameters(new HttpCall("POST", String.format(XboxLiveEnvironment.Instance().getSubmitFeedbackUrlFormat(), new Object[] { paramString1 }), ""), "101");
      paramString1.setRequestBody(paramString2);
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramString1, new ArrayList(202));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
  
  public ShortCircuitProfileMessage.UploadPhoneContactsResponse updatePhoneContacts(ShortCircuitProfileMessage.UploadPhoneContactsRequest paramUploadPhoneContactsRequest)
    throws XLEException
  {
    Log.i(TAG, "updatePhoneContacts");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    String str;
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      str = ProjectSpecificDataProvider.getInstance().getSCDRpsTicket();
      XLEAssert.assertFalse("Expected to have acquired a ticket already", JavaUtil.isNullOrEmpty(str));
      if (!JavaUtil.isNullOrEmpty(str)) {
        break;
      }
      throw new XLEException(2L);
    }
    HttpCall localHttpCall = new HttpCall("POST", XboxLiveEnvironment.Instance().getUploadingPhoneContactsUrlFormat(), "");
    localHttpCall.setCustomHeader("X-TicketToken", str);
    localHttpCall.setCustomHeader("X-AppId", "44445A65-4A71-4083-8C90-041A22856E69");
    localHttpCall.setCustomHeader("X-Scenario", "Minecraft TCUI Friend Finder");
    localHttpCall.setCustomHeader("Content-Type", "application/x-www-form-urlencoded");
    localHttpCall.setRequestBody(paramUploadPhoneContactsRequest.toString());
    paramUploadPhoneContactsRequest = TcuiHttpUtil.getResponseBodySync(localHttpCall);
    if (JavaUtil.isNullOrEmpty(paramUploadPhoneContactsRequest)) {
      throw new XLEException(2L);
    }
    return ShortCircuitProfileMessage.UploadPhoneContactsResponse.parseJson(paramUploadPhoneContactsRequest);
  }
  
  public boolean updateThirdPartyToken(LinkedAccountHelpers.LinkedAccountType paramLinkedAccountType, String paramString)
    throws XLEException
  {
    Log.i(TAG, "updateThirdPartyToken");
    if (Thread.currentThread() != ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      paramLinkedAccountType = HttpUtil.appendCommonParameters(new HttpCall("PUT", String.format(XboxLiveEnvironment.Instance().getUpdateThirdPartyTokenUrlFormat(), new Object[] { paramLinkedAccountType.name() }), ""), "1");
      paramLinkedAccountType.setRequestBody(UpdateThirdPartyTokenRequest.getUpdateThirdPartyTokenRequestBody(new UpdateThirdPartyTokenRequest(paramString)));
      bool = TcuiHttpUtil.getResponseSyncSucceeded(paramLinkedAccountType, Arrays.asList(new Integer[] { Integer.valueOf(204) }));
      TcuiHttpUtil.throwIfNullOrFalse(Boolean.valueOf(bool));
      return bool;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\network\managers\xblshared\SLSXsapiServiceManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */