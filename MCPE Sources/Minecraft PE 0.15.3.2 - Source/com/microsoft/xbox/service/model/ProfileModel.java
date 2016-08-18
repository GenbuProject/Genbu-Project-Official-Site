package com.microsoft.xbox.service.model;

import android.content.res.Resources;
import android.util.Log;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.FriendsFinderStateResult;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.LinkedAccountOptInStatus;
import com.microsoft.xbox.service.model.privacy.PrivacySettingsResult;
import com.microsoft.xbox.service.model.sls.AddShareIdentityRequest;
import com.microsoft.xbox.service.model.sls.FavoriteListRequest;
import com.microsoft.xbox.service.model.sls.FeedbackType;
import com.microsoft.xbox.service.model.sls.MutedListRequest;
import com.microsoft.xbox.service.model.sls.NeverListRequest;
import com.microsoft.xbox.service.model.sls.SubmitFeedbackRequest;
import com.microsoft.xbox.service.model.sls.UserProfileRequest;
import com.microsoft.xbox.service.model.sls.UserProfileSetting;
import com.microsoft.xbox.service.network.managers.AddFollowingUserResponseContainer.AddFollowingUserResponse;
import com.microsoft.xbox.service.network.managers.FamilySettings;
import com.microsoft.xbox.service.network.managers.FamilyUser;
import com.microsoft.xbox.service.network.managers.FollowingSummaryResult.People;
import com.microsoft.xbox.service.network.managers.IFollowerPresenceResult.UserPresence;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPeopleSummary;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPersonSummary;
import com.microsoft.xbox.service.network.managers.IUserProfileResult.ProfileUser;
import com.microsoft.xbox.service.network.managers.IUserProfileResult.Settings;
import com.microsoft.xbox.service.network.managers.IUserProfileResult.UserProfileResult;
import com.microsoft.xbox.service.network.managers.MutedListResultContainer.MutedListResult;
import com.microsoft.xbox.service.network.managers.NeverListResultContainer.NeverListResult;
import com.microsoft.xbox.service.network.managers.ProfilePreferredColor;
import com.microsoft.xbox.service.network.managers.ProfileSummaryResultContainer.ProfileSummaryResult;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.DataLoadUtil;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.ProjectSpecificDataProvider;
import com.microsoft.xbox.toolkit.SingleEntryLoadingStatus;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.ThreadSafeFixedSizeHashtable;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.XLERValueHelper;
import com.microsoft.xbox.toolkit.network.IDataLoaderRunnable;
import com.microsoft.xbox.toolkit.network.XLEThreadPool;
import com.microsoft.xbox.toolkit.network.XboxLiveEnvironment;
import com.microsoft.xbox.xle.app.XLEUtil;
import com.microsoft.xbox.xle.viewmodel.ShareRealNameSettingFilter;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.Enumeration;
import java.util.Iterator;

public class ProfileModel
  extends ModelBase<ProfileData>
{
  private static final int MAX_PROFILE_MODELS = 20;
  private static final long friendsDataLifetime = 180000L;
  private static ProfileModel meProfileInstance;
  private static ThreadSafeFixedSizeHashtable<String, ProfileModel> profileModelCache = new ThreadSafeFixedSizeHashtable(20);
  private static final long profilePresenceDataLifetime = 180000L;
  private AddFollowingUserResponseContainer.AddFollowingUserResponse addUserToFollowingResponse;
  private SingleEntryLoadingStatus addingUserToFavoriteListLoadingStatus;
  private SingleEntryLoadingStatus addingUserToFollowingListLoadingStatus;
  private SingleEntryLoadingStatus addingUserToMutedListLoadingStatus;
  private SingleEntryLoadingStatus addingUserToNeverListLoadingStatus;
  private SingleEntryLoadingStatus addingUserToShareIdentityListLoadingStatus;
  private ArrayList<FollowersData> favorites;
  private String firstName;
  private ArrayList<FollowersData> following;
  private ArrayList<FollowingSummaryResult.People> followingSummaries;
  private String lastName;
  private Date lastRefreshMutedList;
  private Date lastRefreshNeverList;
  private Date lastRefreshPeopleHubRecommendations;
  private Date lastRefreshPresenceData;
  private Date lastRefreshProfileSummary;
  private MutedListResultContainer.MutedListResult mutedList;
  private SingleEntryLoadingStatus mutedListLoadingStatus;
  private NeverListResultContainer.NeverListResult neverList;
  private SingleEntryLoadingStatus neverListLoadingStatus;
  private IPeopleHubResult.PeopleHubPersonSummary peopleHubPersonSummary;
  private ArrayList<FollowersData> peopleHubRecommendations;
  private IPeopleHubResult.PeopleHubPeopleSummary peopleHubRecommendationsRaw;
  private IFollowerPresenceResult.UserPresence presenceData;
  private SingleEntryLoadingStatus presenceDataLoadingStatus;
  private String profileImageUrl;
  private ProfileSummaryResultContainer.ProfileSummaryResult profileSummary;
  private SingleEntryLoadingStatus profileSummaryLoadingStatus;
  private IUserProfileResult.ProfileUser profileUser;
  private SingleEntryLoadingStatus removingUserFromFavoriteListLoadingStatus;
  private SingleEntryLoadingStatus removingUserFromFollowingListLoadingStatus;
  private SingleEntryLoadingStatus removingUserFromMutedListLoadingStatus;
  private SingleEntryLoadingStatus removingUserFromNeverListLoadingStatus;
  private SingleEntryLoadingStatus removingUserFromShareIdentityListLoadingStatus;
  private boolean shareRealName;
  private String shareRealNameStatus;
  private boolean sharingRealNameTransitively;
  private SingleEntryLoadingStatus submitFeedbackForUserLoadingStatus;
  private String xuid;
  
  private ProfileModel(String paramString)
  {
    this.xuid = paramString;
    this.mutedListLoadingStatus = new SingleEntryLoadingStatus();
    this.neverListLoadingStatus = new SingleEntryLoadingStatus();
    this.addingUserToNeverListLoadingStatus = new SingleEntryLoadingStatus();
    this.removingUserFromNeverListLoadingStatus = new SingleEntryLoadingStatus();
    this.addingUserToFavoriteListLoadingStatus = new SingleEntryLoadingStatus();
    this.addingUserToShareIdentityListLoadingStatus = new SingleEntryLoadingStatus();
    this.removingUserFromShareIdentityListLoadingStatus = new SingleEntryLoadingStatus();
    this.removingUserFromFavoriteListLoadingStatus = new SingleEntryLoadingStatus();
    this.addingUserToFollowingListLoadingStatus = new SingleEntryLoadingStatus();
    this.removingUserFromFollowingListLoadingStatus = new SingleEntryLoadingStatus();
    this.addingUserToMutedListLoadingStatus = new SingleEntryLoadingStatus();
    this.removingUserFromMutedListLoadingStatus = new SingleEntryLoadingStatus();
    this.submitFeedbackForUserLoadingStatus = new SingleEntryLoadingStatus();
  }
  
  private void buildRecommendationsList(boolean paramBoolean)
  {
    this.peopleHubRecommendations = new ArrayList();
    Object localObject;
    if (paramBoolean)
    {
      localObject = new RecommendationsPeopleData(true, FollowersData.DummyType.DUMMY_LINK_TO_FACEBOOK);
      this.peopleHubRecommendations.add(0, localObject);
    }
    if ((this.peopleHubRecommendationsRaw != null) && (!XLEUtil.isNullOrEmpty(this.peopleHubRecommendationsRaw.people)))
    {
      localObject = this.peopleHubRecommendationsRaw.people.iterator();
      while (((Iterator)localObject).hasNext())
      {
        RecommendationsPeopleData localRecommendationsPeopleData = new RecommendationsPeopleData((IPeopleHubResult.PeopleHubPersonSummary)((Iterator)localObject).next());
        this.peopleHubRecommendations.add(localRecommendationsPeopleData);
      }
    }
  }
  
  public static int getDefaultColor()
  {
    return XboxTcuiSdk.getResources().getColor(XLERValueHelper.getColorRValue("XboxOneGreen"));
  }
  
  public static ProfileModel getMeProfileModel()
  {
    if (ProjectSpecificDataProvider.getInstance().getXuidString() == null) {
      return null;
    }
    if (meProfileInstance == null) {
      meProfileInstance = new ProfileModel(ProjectSpecificDataProvider.getInstance().getXuidString());
    }
    return meProfileInstance;
  }
  
  private String getProfileImageUrl()
  {
    if (this.profileImageUrl != null) {
      return this.profileImageUrl;
    }
    this.profileImageUrl = getProfileSettingValue(UserProfileSetting.GameDisplayPicRaw);
    return this.profileImageUrl;
  }
  
  public static ProfileModel getProfileModel(String paramString)
  {
    if (JavaUtil.isNullOrEmpty(paramString)) {
      throw new IllegalArgumentException();
    }
    if (JavaUtil.stringsEqualCaseInsensitive(paramString, ProjectSpecificDataProvider.getInstance().getXuidString()))
    {
      if (meProfileInstance == null) {
        meProfileInstance = new ProfileModel(paramString);
      }
      localObject = meProfileInstance;
    }
    ProfileModel localProfileModel;
    do
    {
      return (ProfileModel)localObject;
      localProfileModel = (ProfileModel)profileModelCache.get(paramString);
      localObject = localProfileModel;
    } while (localProfileModel != null);
    Object localObject = new ProfileModel(paramString);
    profileModelCache.put(paramString, localObject);
    return (ProfileModel)localObject;
  }
  
  private String getProfileSettingValue(UserProfileSetting paramUserProfileSetting)
  {
    if ((this.profileUser != null) && (this.profileUser.settings != null))
    {
      Iterator localIterator = this.profileUser.settings.iterator();
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
  
  private static boolean hasPrivilege(String paramString)
  {
    String str = ProjectSpecificDataProvider.getInstance().getPrivileges();
    return (!JavaUtil.isNullOrEmpty(str)) && (str.contains(paramString));
  }
  
  public static boolean hasPrivilegeToAddFriend()
  {
    return hasPrivilege("255");
  }
  
  public static boolean hasPrivilegeToSendMessage()
  {
    return hasPrivilege("252");
  }
  
  public static boolean isMeXuid(String paramString)
  {
    String str = ProjectSpecificDataProvider.getInstance().getXuidString();
    return (str != null) && (paramString != null) && (paramString.compareToIgnoreCase(str) == 0);
  }
  
  private void onAddUserToFavoriteListCompleted(AsyncResult<Boolean> paramAsyncResult, String paramString)
  {
    if ((paramAsyncResult.getStatus() != AsyncActionStatus.SUCCESS) || (!((Boolean)paramAsyncResult.getResult()).booleanValue()) || (this.following == null)) {
      return;
    }
    paramAsyncResult = new ArrayList();
    Iterator localIterator = this.following.iterator();
    while (localIterator.hasNext())
    {
      FollowersData localFollowersData = (FollowersData)localIterator.next();
      if (localFollowersData.xuid.equals(paramString)) {
        localFollowersData.isFavorite = true;
      }
      if (localFollowersData.isFavorite) {
        paramAsyncResult.add(localFollowersData);
      }
    }
    Collections.sort(paramAsyncResult, new FollowingAndFavoritesComparator(null));
    this.favorites = paramAsyncResult;
    notifyObservers(new AsyncResult(new UpdateData(UpdateType.UpdateFriend, true), this, null));
  }
  
  private void onAddUserToFollowingListCompleted(AsyncResult<AddFollowingUserResponseContainer.AddFollowingUserResponse> paramAsyncResult, String paramString)
  {
    ProfileModel localProfileModel = getProfileModel(paramString);
    XLEAssert.assertNotNull(localProfileModel);
    this.addUserToFollowingResponse = ((AddFollowingUserResponseContainer.AddFollowingUserResponse)paramAsyncResult.getResult());
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (this.addUserToFollowingResponse != null) && (this.addUserToFollowingResponse.getAddFollowingRequestStatus()))
    {
      j = 0;
      i = 0;
      paramAsyncResult = new ArrayList();
      if (this.following != null)
      {
        localObject = this.following.iterator();
        for (;;)
        {
          j = i;
          if (!((Iterator)localObject).hasNext()) {
            break;
          }
          localFollowersData = (FollowersData)((Iterator)localObject).next();
          paramAsyncResult.add(localFollowersData);
          if (localFollowersData.xuid.equals(paramString)) {
            i = 1;
          }
        }
      }
      if (j == 0)
      {
        localObject = new FollowersData();
        ((FollowersData)localObject).xuid = paramString;
        ((FollowersData)localObject).isFavorite = false;
        ((FollowersData)localObject).status = UserStatus.Offline;
        ((FollowersData)localObject).userProfileData = new UserProfileData();
        ((FollowersData)localObject).userProfileData.accountTier = localProfileModel.getAccountTier();
        ((FollowersData)localObject).userProfileData.appDisplayName = localProfileModel.getAppDisplayName();
        ((FollowersData)localObject).userProfileData.gamerScore = localProfileModel.getGamerScore();
        ((FollowersData)localObject).userProfileData.gamerTag = localProfileModel.getGamerTag();
        ((FollowersData)localObject).userProfileData.profileImageUrl = localProfileModel.getProfileImageUrl();
        paramAsyncResult.add(localObject);
        Collections.sort(paramAsyncResult, new FollowingAndFavoritesComparator(null));
      }
      this.following = paramAsyncResult;
      notifyObservers(new AsyncResult(new UpdateData(UpdateType.UpdateFriend, true), this, null));
    }
    while ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && ((this.addUserToFollowingResponse.code == 1028) || (this.addUserToFollowingResponse.getAddFollowingRequestStatus())))
    {
      int j;
      int i;
      Object localObject;
      FollowersData localFollowersData;
      return;
    }
    this.addUserToFollowingResponse = null;
  }
  
  private void onAddUserToShareIdentityCompleted(AsyncResult<Boolean> paramAsyncResult, ArrayList<String> paramArrayList)
  {
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (((Boolean)paramAsyncResult.getResult()).booleanValue()))
    {
      paramAsyncResult = paramArrayList.iterator();
      while (paramAsyncResult.hasNext())
      {
        localObject = getProfileModel((String)paramAsyncResult.next()).getProfileSummaryData();
        if (localObject != null) {
          ((ProfileSummaryResultContainer.ProfileSummaryResult)localObject).hasCallerMarkedTargetAsIdentityShared = true;
        }
      }
      paramAsyncResult = getMeProfileModel();
      Object localObject = paramAsyncResult.getProfileFollowingSummaryData();
      if (!XLEUtil.isNullOrEmpty((Iterable)localObject))
      {
        paramArrayList = paramArrayList.iterator();
        for (;;)
        {
          if (!paramArrayList.hasNext()) {
            break label156;
          }
          String str = (String)paramArrayList.next();
          Iterator localIterator = ((ArrayList)localObject).iterator();
          if (localIterator.hasNext())
          {
            FollowingSummaryResult.People localPeople = (FollowingSummaryResult.People)localIterator.next();
            if (!localPeople.xuid.equalsIgnoreCase(str)) {
              break;
            }
            localPeople.isIdentityShared = true;
          }
        }
        label156:
        paramAsyncResult.setProfileFollowingSummaryData((ArrayList)localObject);
      }
    }
  }
  
  private void onGetMutedListCompleted(AsyncResult<MutedListResultContainer.MutedListResult> paramAsyncResult)
  {
    if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
    {
      paramAsyncResult = (MutedListResultContainer.MutedListResult)paramAsyncResult.getResult();
      this.lastRefreshMutedList = new Date();
      if (paramAsyncResult != null) {
        this.mutedList = paramAsyncResult;
      }
    }
    else
    {
      return;
    }
    this.mutedList = new MutedListResultContainer.MutedListResult();
  }
  
  private void onGetNeverListCompleted(AsyncResult<NeverListResultContainer.NeverListResult> paramAsyncResult)
  {
    if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
    {
      paramAsyncResult = (NeverListResultContainer.NeverListResult)paramAsyncResult.getResult();
      this.lastRefreshNeverList = new Date();
      if (paramAsyncResult != null) {
        this.neverList = paramAsyncResult;
      }
    }
    else
    {
      return;
    }
    this.neverList = new NeverListResultContainer.NeverListResult();
  }
  
  private void onGetPeopleHubPersonDataCompleted(AsyncResult<IPeopleHubResult.PeopleHubPersonSummary> paramAsyncResult)
  {
    if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) {
      this.peopleHubPersonSummary = ((IPeopleHubResult.PeopleHubPersonSummary)paramAsyncResult.getResult());
    }
  }
  
  private void onGetPeopleHubRecommendationsCompleted(AsyncResult<IPeopleHubResult.PeopleHubPeopleSummary> paramAsyncResult)
  {
    if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
    {
      paramAsyncResult = (IPeopleHubResult.PeopleHubPeopleSummary)paramAsyncResult.getResult();
      if (paramAsyncResult == null)
      {
        this.peopleHubRecommendationsRaw = null;
        this.peopleHubRecommendations = null;
      }
    }
    else
    {
      return;
    }
    this.peopleHubRecommendationsRaw = paramAsyncResult;
    paramAsyncResult = FacebookManager.getInstance().getFacebookFriendFinderState();
    if ((paramAsyncResult != null) && (paramAsyncResult.getLinkedAccountOptInStatus() == FriendFinderState.LinkedAccountOptInStatus.ShowPrompt)) {}
    for (boolean bool = true;; bool = false)
    {
      buildRecommendationsList(bool);
      this.lastRefreshPeopleHubRecommendations = new Date();
      return;
    }
  }
  
  private void onGetPresenceDataCompleted(AsyncResult<IFollowerPresenceResult.UserPresence> paramAsyncResult)
  {
    if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
    {
      this.lastRefreshPresenceData = new Date();
      this.presenceData = ((IFollowerPresenceResult.UserPresence)paramAsyncResult.getResult());
    }
  }
  
  private void onGetProfileSummaryCompleted(AsyncResult<ProfileSummaryResultContainer.ProfileSummaryResult> paramAsyncResult)
  {
    if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
    {
      paramAsyncResult = (ProfileSummaryResultContainer.ProfileSummaryResult)paramAsyncResult.getResult();
      this.lastRefreshProfileSummary = new Date();
      this.profileSummary = paramAsyncResult;
      notifyObservers(new AsyncResult(new UpdateData(UpdateType.ActivityAlertsSummary, true), this, null));
    }
  }
  
  private void onPutUserToMutedListCompleted(AsyncResult<Boolean> paramAsyncResult, String paramString)
  {
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (((Boolean)paramAsyncResult.getResult()).booleanValue()))
    {
      if (this.mutedList == null) {
        this.mutedList = new MutedListResultContainer.MutedListResult();
      }
      if (!this.mutedList.contains(paramString)) {
        this.mutedList.add(paramString);
      }
    }
  }
  
  private void onPutUserToNeverListCompleted(AsyncResult<Boolean> paramAsyncResult, String paramString)
  {
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (((Boolean)paramAsyncResult.getResult()).booleanValue()))
    {
      if (this.neverList == null) {
        this.neverList = new NeverListResultContainer.NeverListResult();
      }
      if (!this.neverList.contains(paramString)) {
        this.neverList.add(paramString);
      }
    }
  }
  
  private void onRemoveUserFromFavoriteListCompleted(AsyncResult<Boolean> paramAsyncResult, String paramString)
  {
    if ((paramAsyncResult.getStatus() != AsyncActionStatus.SUCCESS) || (!((Boolean)paramAsyncResult.getResult()).booleanValue()) || (this.following == null)) {
      return;
    }
    paramAsyncResult = new ArrayList();
    Iterator localIterator = this.following.iterator();
    while (localIterator.hasNext())
    {
      FollowersData localFollowersData = (FollowersData)localIterator.next();
      if (localFollowersData.xuid.equals(paramString)) {
        localFollowersData.isFavorite = false;
      }
      if (localFollowersData.isFavorite) {
        paramAsyncResult.add(localFollowersData);
      }
    }
    this.favorites = paramAsyncResult;
    notifyObservers(new AsyncResult(new UpdateData(UpdateType.UpdateFriend, true), this, null));
  }
  
  private void onRemoveUserFromFollowingListCompleted(AsyncResult<Boolean> paramAsyncResult, String paramString)
  {
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (((Boolean)paramAsyncResult.getResult()).booleanValue()) && (this.following != null))
    {
      paramAsyncResult = new ArrayList();
      ArrayList localArrayList = new ArrayList();
      Iterator localIterator = this.following.iterator();
      while (localIterator.hasNext())
      {
        FollowersData localFollowersData = (FollowersData)localIterator.next();
        if (!localFollowersData.xuid.equals(paramString))
        {
          paramAsyncResult.add(localFollowersData);
          if (localFollowersData.isFavorite) {
            localArrayList.add(localFollowersData);
          }
        }
      }
      this.following = paramAsyncResult;
      this.favorites = localArrayList;
      notifyObservers(new AsyncResult(new UpdateData(UpdateType.UpdateFriend, true), this, null));
    }
  }
  
  private void onRemoveUserFromMutedListCompleted(AsyncResult<Boolean> paramAsyncResult, String paramString)
  {
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (((Boolean)paramAsyncResult.getResult()).booleanValue()) && (this.mutedList != null) && (this.mutedList.contains(paramString))) {
      this.mutedList.remove(paramString);
    }
  }
  
  private void onRemoveUserFromNeverListCompleted(AsyncResult<Boolean> paramAsyncResult, String paramString)
  {
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (((Boolean)paramAsyncResult.getResult()).booleanValue()) && (this.neverList != null) && (this.neverList.contains(paramString))) {
      this.neverList.remove(paramString);
    }
  }
  
  private void onRemoveUserFromShareIdentityCompleted(AsyncResult<Boolean> paramAsyncResult, ArrayList<String> paramArrayList)
  {
    if ((paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS) && (((Boolean)paramAsyncResult.getResult()).booleanValue()))
    {
      paramAsyncResult = paramArrayList.iterator();
      while (paramAsyncResult.hasNext())
      {
        localObject = getProfileModel((String)paramAsyncResult.next()).getProfileSummaryData();
        if (localObject != null) {
          ((ProfileSummaryResultContainer.ProfileSummaryResult)localObject).hasCallerMarkedTargetAsIdentityShared = false;
        }
      }
      paramAsyncResult = getMeProfileModel();
      Object localObject = paramAsyncResult.getProfileFollowingSummaryData();
      if (!XLEUtil.isNullOrEmpty((Iterable)localObject))
      {
        paramArrayList = paramArrayList.iterator();
        for (;;)
        {
          if (!paramArrayList.hasNext()) {
            break label156;
          }
          String str = (String)paramArrayList.next();
          Iterator localIterator = ((ArrayList)localObject).iterator();
          if (localIterator.hasNext())
          {
            FollowingSummaryResult.People localPeople = (FollowingSummaryResult.People)localIterator.next();
            if (!localPeople.xuid.equalsIgnoreCase(str)) {
              break;
            }
            localPeople.isIdentityShared = false;
          }
        }
        label156:
        paramAsyncResult.setProfileFollowingSummaryData((ArrayList)localObject);
      }
    }
  }
  
  private void onSubmitFeedbackForUserCompleted(AsyncResult<Boolean> paramAsyncResult) {}
  
  public static void reset()
  {
    if (Thread.currentThread() == ThreadManager.UIThread) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      Enumeration localEnumeration = profileModelCache.elements();
      while (localEnumeration.hasMoreElements()) {
        ((ProfileModel)localEnumeration.nextElement()).clearObserver();
      }
    }
    if (meProfileInstance != null)
    {
      meProfileInstance.clearObserver();
      meProfileInstance = null;
    }
    profileModelCache = new ThreadSafeFixedSizeHashtable(20);
  }
  
  private void updateWithProfileData(AsyncResult<ProfileData> paramAsyncResult, boolean paramBoolean)
  {
    updateWithNewData(paramAsyncResult);
    if (paramBoolean) {
      invalidateData();
    }
  }
  
  public AsyncResult<Boolean> addUserToFavoriteList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.addingUserToFavoriteListLoadingStatus, new AddUserToFavoriteListRunner(this, paramString));
  }
  
  public AsyncResult<AddFollowingUserResponseContainer.AddFollowingUserResponse> addUserToFollowingList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.addingUserToFollowingListLoadingStatus, new AddUserToFollowingListRunner(this, paramString));
  }
  
  public AsyncResult<Boolean> addUserToMutedList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.addingUserToMutedListLoadingStatus, new PutUserToMutedListRunner(this, this.xuid, paramString));
  }
  
  public AsyncResult<Boolean> addUserToNeverList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.addingUserToNeverListLoadingStatus, new PutUserToNeverListRunner(this, this.xuid, paramString));
  }
  
  public AsyncResult<Boolean> addUserToShareIdentity(boolean paramBoolean, ArrayList<String> paramArrayList)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.addingUserToShareIdentityListLoadingStatus, new AddUsersToShareIdentityListRunner(this, paramArrayList));
  }
  
  public String getAccountTier()
  {
    return getProfileSettingValue(UserProfileSetting.AccountTier);
  }
  
  public AddFollowingUserResponseContainer.AddFollowingUserResponse getAddUserToFollowingResult()
  {
    return this.addUserToFollowingResponse;
  }
  
  public String getAppDisplayName()
  {
    return getProfileSettingValue(UserProfileSetting.AppDisplayName);
  }
  
  public String getBio()
  {
    return getProfileSettingValue(UserProfileSetting.Bio);
  }
  
  public ArrayList<FollowersData> getFavorites()
  {
    return this.favorites;
  }
  
  public ArrayList<FollowersData> getFollowingData()
  {
    return this.following;
  }
  
  public String getGamerPicImageUrl()
  {
    return getProfileImageUrl();
  }
  
  public String getGamerScore()
  {
    return getProfileSettingValue(UserProfileSetting.Gamerscore);
  }
  
  public String getGamerTag()
  {
    return getProfileSettingValue(UserProfileSetting.Gamertag);
  }
  
  public String getLocation()
  {
    return getProfileSettingValue(UserProfileSetting.Location);
  }
  
  public int getMaturityLevel()
  {
    if (this.profileUser != null) {
      return this.profileUser.getMaturityLevel();
    }
    return 0;
  }
  
  public MutedListResultContainer.MutedListResult getMutedList()
  {
    return this.mutedList;
  }
  
  public NeverListResultContainer.NeverListResult getNeverListData()
  {
    return this.neverList;
  }
  
  public int getNumberOfFollowers()
  {
    if (this.profileSummary != null) {
      return this.profileSummary.targetFollowerCount;
    }
    return 0;
  }
  
  public int getNumberOfFollowing()
  {
    if (this.profileSummary != null) {
      return this.profileSummary.targetFollowingCount;
    }
    return 0;
  }
  
  public IPeopleHubResult.PeopleHubPersonSummary getPeopleHubPersonSummary()
  {
    return this.peopleHubPersonSummary;
  }
  
  public IPeopleHubResult.PeopleHubPeopleSummary getPeopleHubRecommendationsRawData()
  {
    return this.peopleHubRecommendationsRaw;
  }
  
  public int getPreferedColor()
  {
    if ((this.profileUser != null) && (this.profileUser.colors != null)) {
      return this.profileUser.colors.getPrimaryColor();
    }
    return getDefaultColor();
  }
  
  public IFollowerPresenceResult.UserPresence getPresenceData()
  {
    return this.presenceData;
  }
  
  public ArrayList<FollowingSummaryResult.People> getProfileFollowingSummaryData()
  {
    return this.followingSummaries;
  }
  
  public ProfileSummaryResultContainer.ProfileSummaryResult getProfileSummaryData()
  {
    return this.profileSummary;
  }
  
  public String getRealName()
  {
    if (this.shareRealName) {
      return getProfileSettingValue(UserProfileSetting.RealName);
    }
    return null;
  }
  
  public String getShareRealNameStatus()
  {
    return this.shareRealNameStatus;
  }
  
  public ArrayList<URI> getWatermarkUris()
  {
    ArrayList localArrayList = new ArrayList();
    String str1 = getProfileSettingValue(UserProfileSetting.TenureLevel);
    if ((!JavaUtil.isNullOrEmpty(str1)) && (!str1.equalsIgnoreCase("0"))) {}
    try
    {
      String str2 = XboxLiveEnvironment.Instance().getTenureWatermarkUrlFormat();
      localObject = str1;
      if (str1.length() == 1) {
        localObject = "0" + str1;
      }
      localArrayList.add(new URI(String.format(str2, new Object[] { localObject })));
    }
    catch (URISyntaxException localURISyntaxException1)
    {
      try
      {
        for (;;)
        {
          Object localObject;
          int j;
          int i;
          localArrayList.add(new URI(XboxLiveEnvironment.Instance().getWatermarkUrl(str1)));
          i += 1;
        }
        localURISyntaxException1 = localURISyntaxException1;
        XLEAssert.fail("Failed to create URI for tenure watermark: " + localURISyntaxException1.toString());
      }
      catch (URISyntaxException localURISyntaxException2)
      {
        for (;;)
        {
          XLEAssert.fail("Failed to create URI for watermark " + str1 + " : " + localURISyntaxException2.toString());
        }
      }
    }
    localObject = getProfileSettingValue(UserProfileSetting.Watermarks);
    if (!JavaUtil.isNullOrEmpty((String)localObject))
    {
      localObject = ((String)localObject).split("\\|");
      j = localObject.length;
      i = 0;
      if (i < j) {
        str1 = localObject[i];
      }
    }
    return localArrayList;
  }
  
  public String getXuid()
  {
    return this.xuid;
  }
  
  public boolean hasCallerMarkedTargetAsFavorite()
  {
    return (this.profileSummary != null) && (this.profileSummary.hasCallerMarkedTargetAsFavorite);
  }
  
  public boolean hasCallerMarkedTargetAsIdentityShared()
  {
    return (this.profileSummary != null) && (this.profileSummary.hasCallerMarkedTargetAsIdentityShared);
  }
  
  public boolean isCallerFollowingTarget()
  {
    return (this.profileSummary != null) && (this.profileSummary.isCallerFollowingTarget);
  }
  
  public boolean isMeProfile()
  {
    return isMeXuid(this.xuid);
  }
  
  public boolean isTargetFollowingCaller()
  {
    return (this.profileSummary != null) && (this.profileSummary.isTargetFollowingCaller);
  }
  
  public void loadAsync(boolean paramBoolean)
  {
    loadInternal(paramBoolean, UpdateType.MeProfileData, new GetProfileRunner(this, this.xuid, false));
  }
  
  public AsyncResult<IPeopleHubResult.PeopleHubPeopleSummary> loadPeopleHubRecommendations(boolean paramBoolean)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    return DataLoadUtil.Load(paramBoolean, 180000L, this.lastRefreshPeopleHubRecommendations, new SingleEntryLoadingStatus(), new GetPeopleHubRecommendationRunner(this, this.xuid));
  }
  
  public AsyncResult<IFollowerPresenceResult.UserPresence> loadPresenceData(boolean paramBoolean)
  {
    if (this.presenceDataLoadingStatus == null) {
      this.presenceDataLoadingStatus = new SingleEntryLoadingStatus();
    }
    return DataLoadUtil.Load(paramBoolean, 180000L, this.lastRefreshPresenceData, this.presenceDataLoadingStatus, new GetPresenceDataRunner(this, this.xuid));
  }
  
  public AsyncResult<ProfileSummaryResultContainer.ProfileSummaryResult> loadProfileSummary(boolean paramBoolean)
  {
    if (this.profileSummaryLoadingStatus == null) {
      this.profileSummaryLoadingStatus = new SingleEntryLoadingStatus();
    }
    return DataLoadUtil.Load(paramBoolean, this.lifetime, this.lastRefreshProfileSummary, this.profileSummaryLoadingStatus, new GetProfileSummaryRunner(this, this.xuid));
  }
  
  public AsyncResult<ProfileData> loadSync(boolean paramBoolean)
  {
    return loadSync(paramBoolean, false);
  }
  
  public AsyncResult<ProfileData> loadSync(boolean paramBoolean1, boolean paramBoolean2)
  {
    return super.loadData(paramBoolean1, new GetProfileRunner(this, this.xuid, paramBoolean2));
  }
  
  public AsyncResult<MutedListResultContainer.MutedListResult> loadUserMutedList(boolean paramBoolean)
  {
    return DataLoadUtil.Load(paramBoolean, this.lifetime, this.lastRefreshMutedList, this.mutedListLoadingStatus, new GetMutedListRunner(this, this.xuid));
  }
  
  public AsyncResult<NeverListResultContainer.NeverListResult> loadUserNeverList(boolean paramBoolean)
  {
    return DataLoadUtil.Load(paramBoolean, this.lifetime, this.lastRefreshNeverList, this.neverListLoadingStatus, new GetNeverListRunner(this, this.xuid));
  }
  
  public AsyncResult<Boolean> removeUserFromFavoriteList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.removingUserFromFavoriteListLoadingStatus, new RemoveUserFromFavoriteListRunner(this, paramString));
  }
  
  public AsyncResult<Boolean> removeUserFromFollowingList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.removingUserFromFollowingListLoadingStatus, new RemoveUserFromFollowingListRunner(this, paramString));
  }
  
  public AsyncResult<Boolean> removeUserFromMutedList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.removingUserFromMutedListLoadingStatus, new RemoveUserFromMutedListRunner(this, this.xuid, paramString));
  }
  
  public AsyncResult<Boolean> removeUserFromNeverList(boolean paramBoolean, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    XLEAssert.assertNotNull(paramString);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.removingUserFromNeverListLoadingStatus, new RemoveUserFromNeverListRunner(this, this.xuid, paramString));
  }
  
  public AsyncResult<Boolean> removeUserFromShareIdentity(boolean paramBoolean, ArrayList<String> paramArrayList)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.removingUserFromShareIdentityListLoadingStatus, new RemoveUsersFromShareIdentityListRunner(this, paramArrayList));
  }
  
  public void setFirstName(String paramString)
  {
    this.firstName = paramString;
  }
  
  public void setLastName(String paramString)
  {
    this.lastName = paramString;
  }
  
  public void setProfileFollowingSummaryData(ArrayList<FollowingSummaryResult.People> paramArrayList)
  {
    this.followingSummaries = paramArrayList;
  }
  
  public boolean shouldRefreshPresenceData()
  {
    return XLEUtil.shouldRefresh(this.lastRefreshPresenceData, this.lifetime);
  }
  
  public boolean shouldRefreshProfileSummary()
  {
    return XLEUtil.shouldRefresh(this.lastRefreshProfileSummary, this.lifetime);
  }
  
  public AsyncResult<Boolean> submitFeedbackForUser(boolean paramBoolean, FeedbackType paramFeedbackType, String paramString)
  {
    XLEAssert.assertIsNotUIThread();
    XLEAssert.assertNotNull(this.xuid);
    return DataLoadUtil.Load(paramBoolean, this.lifetime, null, this.submitFeedbackForUserLoadingStatus, new SubmitFeedbackForUserRunner(this, this.xuid, paramFeedbackType, paramString));
  }
  
  public void updateWithNewData(AsyncResult<ProfileData> paramAsyncResult)
  {
    Object localObject;
    if (Thread.currentThread() == ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      super.updateWithNewData(paramAsyncResult);
      if (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)
      {
        localObject = (ProfileData)paramAsyncResult.getResult();
        if (localObject != null) {
          if (!isMeProfile()) {
            break label174;
          }
        }
      }
    }
    label174:
    for (boolean bool = ((ProfileData)localObject).getShareRealName();; bool = true)
    {
      this.shareRealName = bool;
      this.shareRealNameStatus = ((ProfileData)localObject).getShareRealNameStatus();
      Log.i("ProfileModel", "shareRealNameStatus: " + this.shareRealNameStatus);
      this.sharingRealNameTransitively = ((ProfileData)localObject).getSharingRealNameTransitively();
      localObject = ((ProfileData)localObject).getProfileResult();
      if ((localObject != null) && (((IUserProfileResult.UserProfileResult)localObject).profileUsers != null))
      {
        this.profileUser = ((IUserProfileResult.ProfileUser)((IUserProfileResult.UserProfileResult)localObject).profileUsers.get(0));
        this.profileImageUrl = null;
      }
      notifyObservers(new AsyncResult(new UpdateData(UpdateType.ProfileData, true), this, paramAsyncResult.getException()));
      return;
      bool = false;
      break;
    }
  }
  
  private class AddUserToFavoriteListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private String favoriteUserXuid;
    
    public AddUserToFavoriteListRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.favoriteUserXuid = paramString;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(this.favoriteUserXuid);
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().addUserToFavoriteList(FavoriteListRequest.getFavoriteListRequestBody(new FavoriteListRequest(localArrayList))));
    }
    
    public long getDefaultErrorCode()
    {
      return 3994L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onAddUserToFavoriteListCompleted(paramAsyncResult, this.favoriteUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class AddUserToFollowingListRunner
    extends IDataLoaderRunnable<AddFollowingUserResponseContainer.AddFollowingUserResponse>
  {
    private ProfileModel caller;
    private String followingUserXuid;
    
    public AddUserToFollowingListRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.followingUserXuid = paramString;
    }
    
    public AddFollowingUserResponseContainer.AddFollowingUserResponse buildData()
      throws XLEException
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(this.followingUserXuid);
      return ServiceManagerFactory.getInstance().getSLSServiceManager().addUserToFollowingList(FavoriteListRequest.getFavoriteListRequestBody(new FavoriteListRequest(localArrayList)));
    }
    
    public long getDefaultErrorCode()
    {
      return 3011L;
    }
    
    public void onPostExcute(AsyncResult<AddFollowingUserResponseContainer.AddFollowingUserResponse> paramAsyncResult)
    {
      this.caller.onAddUserToFollowingListCompleted(paramAsyncResult, this.followingUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class AddUsersToShareIdentityListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private ArrayList<String> userIds;
    
    public AddUsersToShareIdentityListRunner(ArrayList<String> paramArrayList)
    {
      this.caller = paramArrayList;
      ArrayList localArrayList;
      this.userIds = localArrayList;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      String str = AddShareIdentityRequest.getAddShareIdentityRequestBody(new AddShareIdentityRequest(this.userIds));
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().addFriendToShareIdentitySetting(this.caller.xuid, str));
    }
    
    public long getDefaultErrorCode()
    {
      return 40043L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onAddUserToShareIdentityCompleted(paramAsyncResult, this.userIds);
    }
    
    public void onPreExecute() {}
  }
  
  private class FollowingAndFavoritesComparator
    implements Comparator<FollowersData>
  {
    private FollowingAndFavoritesComparator() {}
    
    public int compare(FollowersData paramFollowersData1, FollowersData paramFollowersData2)
    {
      return paramFollowersData1.userProfileData.appDisplayName.compareToIgnoreCase(paramFollowersData2.userProfileData.appDisplayName);
    }
  }
  
  private class GetMutedListRunner
    extends IDataLoaderRunnable<MutedListResultContainer.MutedListResult>
  {
    private ProfileModel caller;
    private String xuid;
    
    public GetMutedListRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString;
    }
    
    public MutedListResultContainer.MutedListResult buildData()
      throws XLEException
    {
      return ServiceManagerFactory.getInstance().getSLSServiceManager().getMutedListInfo(this.xuid);
    }
    
    public long getDefaultErrorCode()
    {
      return 40040L;
    }
    
    public void onPostExcute(AsyncResult<MutedListResultContainer.MutedListResult> paramAsyncResult)
    {
      this.caller.onGetMutedListCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
  
  private class GetNeverListRunner
    extends IDataLoaderRunnable<NeverListResultContainer.NeverListResult>
  {
    private ProfileModel caller;
    private String xuid;
    
    public GetNeverListRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString;
    }
    
    public NeverListResultContainer.NeverListResult buildData()
      throws XLEException
    {
      return ServiceManagerFactory.getInstance().getSLSServiceManager().getNeverListInfo(this.xuid);
    }
    
    public long getDefaultErrorCode()
    {
      return 3203L;
    }
    
    public void onPostExcute(AsyncResult<NeverListResultContainer.NeverListResult> paramAsyncResult)
    {
      this.caller.onGetNeverListCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
  
  private class GetPeopleHubRecommendationRunner
    extends IDataLoaderRunnable<IPeopleHubResult.PeopleHubPeopleSummary>
  {
    private ProfileModel caller;
    private String xuid;
    
    public GetPeopleHubRecommendationRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString;
    }
    
    public IPeopleHubResult.PeopleHubPeopleSummary buildData()
      throws XLEException
    {
      IPeopleHubResult.PeopleHubPeopleSummary localPeopleHubPeopleSummary2 = new IPeopleHubResult.PeopleHubPeopleSummary();
      IPeopleHubResult.PeopleHubPeopleSummary localPeopleHubPeopleSummary1 = localPeopleHubPeopleSummary2;
      if (!JavaUtil.isNullOrEmpty(this.xuid))
      {
        localPeopleHubPeopleSummary1 = localPeopleHubPeopleSummary2;
        if (this.xuid.equalsIgnoreCase(ProjectSpecificDataProvider.getInstance().getXuidString())) {
          localPeopleHubPeopleSummary1 = ServiceManagerFactory.getInstance().getSLSServiceManager().getPeopleHubRecommendations();
        }
      }
      return localPeopleHubPeopleSummary1;
    }
    
    public long getDefaultErrorCode()
    {
      return 11L;
    }
    
    public void onPostExcute(AsyncResult<IPeopleHubResult.PeopleHubPeopleSummary> paramAsyncResult)
    {
      this.caller.onGetPeopleHubRecommendationsCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
  
  private class GetPresenceDataRunner
    extends IDataLoaderRunnable<IFollowerPresenceResult.UserPresence>
  {
    private ProfileModel caller;
    private String xuid;
    
    public GetPresenceDataRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString;
    }
    
    public IFollowerPresenceResult.UserPresence buildData()
      throws XLEException
    {
      return null;
    }
    
    public long getDefaultErrorCode()
    {
      return 3013L;
    }
    
    public void onPostExcute(AsyncResult<IFollowerPresenceResult.UserPresence> paramAsyncResult)
    {
      this.caller.onGetPresenceDataCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
  
  private class GetProfileRunner
    extends IDataLoaderRunnable<ProfileData>
  {
    private ProfileModel caller;
    private boolean loadEssentialsOnly;
    private String xuid;
    
    public GetProfileRunner(ProfileModel paramProfileModel, String paramString, boolean paramBoolean)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString;
      this.loadEssentialsOnly = paramBoolean;
    }
    
    public ProfileData buildData()
      throws XLEException
    {
      final ISLSServiceManager localISLSServiceManager = ServiceManagerFactory.getInstance().getSLSServiceManager();
      final Object localObject = new ArrayList();
      ((ArrayList)localObject).add(this.xuid);
      IUserProfileResult.UserProfileResult localUserProfileResult = localISLSServiceManager.getUserProfileInfo(UserProfileRequest.getUserProfileRequestBody(new UserProfileRequest((ArrayList)localObject, this.loadEssentialsOnly)));
      if (ProjectSpecificDataProvider.getInstance().getXuidString().equalsIgnoreCase(this.xuid)) {
        if ((localUserProfileResult != null) && (localUserProfileResult.profileUsers != null) && (localUserProfileResult.profileUsers.size() > 0))
        {
          localObject = (IUserProfileResult.ProfileUser)localUserProfileResult.profileUsers.get(0);
          ((IUserProfileResult.ProfileUser)localObject).setPrivilieges(localISLSServiceManager.getXTokenPrivileges());
        }
      }
      try
      {
        str = ((IUserProfileResult.ProfileUser)localObject).getSettingValue(UserProfileSetting.PreferredColor);
        if ((str != null) && (str.length() > 0)) {
          ((IUserProfileResult.ProfileUser)localObject).colors = localISLSServiceManager.getProfilePreferredColor(str);
        }
      }
      catch (Throwable localThrowable2)
      {
        String str;
        boolean bool4;
        boolean bool5;
        PrivacySettingsResult localPrivacySettingsResult;
        boolean bool3;
        boolean bool1;
        boolean bool2;
        for (;;) {}
      }
      XLEThreadPool.networkOperationsThreadPool.run(new Runnable()
      {
        public void run()
        {
          try
          {
            FamilySettings localFamilySettings = localISLSServiceManager.getFamilySettings(ProfileModel.GetProfileRunner.this.xuid);
            int i;
            if ((localFamilySettings != null) && (localFamilySettings.familyUsers != null)) {
              i = 0;
            }
            for (;;)
            {
              if (i < localFamilySettings.familyUsers.size())
              {
                if (((FamilyUser)localFamilySettings.familyUsers.get(i)).xuid.equalsIgnoreCase(ProfileModel.GetProfileRunner.this.xuid))
                {
                  localObject.canViewTVAdultContent = ((FamilyUser)localFamilySettings.familyUsers.get(i)).canViewTVAdultContent;
                  localObject.setmaturityLevel(((FamilyUser)localFamilySettings.familyUsers.get(i)).maturityLevel);
                }
              }
              else {
                return;
              }
              i += 1;
            }
            return;
          }
          catch (Throwable localThrowable) {}
        }
      });
      bool4 = false;
      bool5 = false;
      str = null;
      localPrivacySettingsResult = null;
      bool3 = false;
      bool1 = bool5;
      localObject = localPrivacySettingsResult;
      bool2 = bool3;
      if (this.xuid != null)
      {
        bool1 = bool5;
        localObject = localPrivacySettingsResult;
        bool2 = bool3;
        if (this.xuid.compareToIgnoreCase(ProjectSpecificDataProvider.getInstance().getXuidString()) == 0)
        {
          bool2 = bool4;
          localObject = str;
        }
      }
      for (;;)
      {
        try
        {
          localPrivacySettingsResult = localISLSServiceManager.getUserProfilePrivacySettings();
          bool2 = bool4;
          localObject = str;
          str = localPrivacySettingsResult.getShareRealNameStatus();
          bool2 = bool4;
          localObject = str;
          if (ShareRealNameSettingFilter.Blocked.toString().compareTo(str) == 0) {
            continue;
          }
          bool1 = true;
          bool2 = bool1;
          localObject = str;
          bool4 = localPrivacySettingsResult.getSharingRealNameTransitively();
          bool2 = bool4;
          localObject = str;
        }
        catch (Exception localException)
        {
          bool1 = bool2;
          bool2 = bool3;
          continue;
        }
        return new ProfileData(localUserProfileResult, bool1, (String)localObject, bool2);
        if ((localUserProfileResult == null) || (localUserProfileResult.profileUsers == null) || (localUserProfileResult.profileUsers.size() <= 0)) {
          break;
        }
        localObject = (IUserProfileResult.ProfileUser)localUserProfileResult.profileUsers.get(0);
        try
        {
          str = ((IUserProfileResult.ProfileUser)localObject).getSettingValue(UserProfileSetting.PreferredColor);
          if ((str == null) || (str.length() <= 0)) {
            break;
          }
          ((IUserProfileResult.ProfileUser)localObject).colors = localISLSServiceManager.getProfilePreferredColor(str);
        }
        catch (Throwable localThrowable1) {}
        break;
        bool1 = false;
      }
    }
    
    public long getDefaultErrorCode()
    {
      return 3002L;
    }
    
    public void onPostExcute(AsyncResult<ProfileData> paramAsyncResult)
    {
      this.caller.updateWithProfileData(paramAsyncResult, this.loadEssentialsOnly);
    }
    
    public void onPreExecute() {}
  }
  
  private class GetProfileSummaryRunner
    extends IDataLoaderRunnable<ProfileSummaryResultContainer.ProfileSummaryResult>
  {
    private ProfileModel caller;
    private String xuid;
    
    public GetProfileSummaryRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString;
    }
    
    public ProfileSummaryResultContainer.ProfileSummaryResult buildData()
      throws XLEException
    {
      return ServiceManagerFactory.getInstance().getSLSServiceManager().getProfileSummaryInfo(this.xuid);
    }
    
    public long getDefaultErrorCode()
    {
      return 3002L;
    }
    
    public void onPostExcute(AsyncResult<ProfileSummaryResultContainer.ProfileSummaryResult> paramAsyncResult)
    {
      this.caller.onGetProfileSummaryCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
  
  private class PutUserToMutedListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private String mutedUserXuid;
    private String xuid;
    
    public PutUserToMutedListRunner(ProfileModel paramProfileModel, String paramString1, String paramString2)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString1;
      this.mutedUserXuid = paramString2;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      MutedListRequest localMutedListRequest = new MutedListRequest(Long.parseLong(this.mutedUserXuid));
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().addUserToMutedList(this.xuid, MutedListRequest.getNeverListRequestBody(localMutedListRequest)));
    }
    
    public long getDefaultErrorCode()
    {
      return 4038L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onPutUserToMutedListCompleted(paramAsyncResult, this.mutedUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class PutUserToNeverListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private String blockUserXuid;
    private ProfileModel caller;
    private String xuid;
    
    public PutUserToNeverListRunner(ProfileModel paramProfileModel, String paramString1, String paramString2)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString1;
      this.blockUserXuid = paramString2;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      NeverListRequest localNeverListRequest = new NeverListRequest(Long.parseLong(this.blockUserXuid));
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().addUserToNeverList(this.xuid, NeverListRequest.getNeverListRequestBody(localNeverListRequest)));
    }
    
    public long getDefaultErrorCode()
    {
      return 3996L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onPutUserToNeverListCompleted(paramAsyncResult, this.blockUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class RemoveUserFromFavoriteListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private String favoriteUserXuid;
    
    public RemoveUserFromFavoriteListRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.favoriteUserXuid = paramString;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(this.favoriteUserXuid);
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().removeUserFromFavoriteList(FavoriteListRequest.getFavoriteListRequestBody(new FavoriteListRequest(localArrayList))));
    }
    
    public long getDefaultErrorCode()
    {
      return 3995L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onRemoveUserFromFavoriteListCompleted(paramAsyncResult, this.favoriteUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class RemoveUserFromFollowingListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private String followingUserXuid;
    
    public RemoveUserFromFollowingListRunner(ProfileModel paramProfileModel, String paramString)
    {
      this.caller = paramProfileModel;
      this.followingUserXuid = paramString;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      ArrayList localArrayList = new ArrayList();
      localArrayList.add(this.followingUserXuid);
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().removeUserFromFollowingList(FavoriteListRequest.getFavoriteListRequestBody(new FavoriteListRequest(localArrayList))));
    }
    
    public long getDefaultErrorCode()
    {
      return 3012L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onRemoveUserFromFollowingListCompleted(paramAsyncResult, this.followingUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class RemoveUserFromMutedListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private String unmutedUserXuid;
    private String xuid;
    
    public RemoveUserFromMutedListRunner(ProfileModel paramProfileModel, String paramString1, String paramString2)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString1;
      this.unmutedUserXuid = paramString2;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      MutedListRequest localMutedListRequest = new MutedListRequest(Long.parseLong(this.unmutedUserXuid));
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().removeUserFromMutedList(this.xuid, MutedListRequest.getNeverListRequestBody(localMutedListRequest)));
    }
    
    public long getDefaultErrorCode()
    {
      return 40039L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onRemoveUserFromMutedListCompleted(paramAsyncResult, this.unmutedUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class RemoveUserFromNeverListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private String unblockUserXuid;
    private String xuid;
    
    public RemoveUserFromNeverListRunner(ProfileModel paramProfileModel, String paramString1, String paramString2)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString1;
      this.unblockUserXuid = paramString2;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      NeverListRequest localNeverListRequest = new NeverListRequest(Long.parseLong(this.unblockUserXuid));
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().removeUserFromNeverList(this.xuid, NeverListRequest.getNeverListRequestBody(localNeverListRequest)));
    }
    
    public long getDefaultErrorCode()
    {
      return 3997L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onRemoveUserFromNeverListCompleted(paramAsyncResult, this.unblockUserXuid);
    }
    
    public void onPreExecute() {}
  }
  
  private class RemoveUsersFromShareIdentityListRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private ArrayList<String> userIds;
    
    public RemoveUsersFromShareIdentityListRunner(ArrayList<String> paramArrayList)
    {
      this.caller = paramArrayList;
      ArrayList localArrayList;
      this.userIds = localArrayList;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      String str = AddShareIdentityRequest.getAddShareIdentityRequestBody(new AddShareIdentityRequest(this.userIds));
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().removeFriendFromShareIdentitySetting(this.caller.xuid, str));
    }
    
    public long getDefaultErrorCode()
    {
      return 40042L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onRemoveUserFromShareIdentityCompleted(paramAsyncResult, this.userIds);
    }
    
    public void onPreExecute() {}
  }
  
  private class SubmitFeedbackForUserRunner
    extends IDataLoaderRunnable<Boolean>
  {
    private ProfileModel caller;
    private FeedbackType feedbackType;
    private String textReason;
    private String xuid;
    
    public SubmitFeedbackForUserRunner(ProfileModel paramProfileModel, String paramString1, FeedbackType paramFeedbackType, String paramString2)
    {
      this.caller = paramProfileModel;
      this.xuid = paramString1;
      this.feedbackType = paramFeedbackType;
      this.textReason = paramString2;
    }
    
    public Boolean buildData()
      throws XLEException
    {
      SubmitFeedbackRequest localSubmitFeedbackRequest = new SubmitFeedbackRequest(Long.parseLong(this.xuid), null, this.feedbackType, this.textReason, null, null);
      return Boolean.valueOf(ServiceManagerFactory.getInstance().getSLSServiceManager().submitFeedback(this.xuid, SubmitFeedbackRequest.getSubmitFeedbackRequestBody(localSubmitFeedbackRequest)));
    }
    
    public long getDefaultErrorCode()
    {
      return 40041L;
    }
    
    public void onPostExcute(AsyncResult<Boolean> paramAsyncResult)
    {
      this.caller.onSubmitFeedbackForUserCompleted(paramAsyncResult);
    }
    
    public void onPreExecute() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\ProfileModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */