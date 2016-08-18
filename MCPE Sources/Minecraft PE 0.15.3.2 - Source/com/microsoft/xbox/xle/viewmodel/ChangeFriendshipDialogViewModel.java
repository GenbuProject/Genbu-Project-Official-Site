package com.microsoft.xbox.xle.viewmodel;

import android.content.res.Resources;
import com.microsoft.xbox.service.model.FollowersData;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.network.managers.AddFollowingUserResponseContainer.AddFollowingUserResponse;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.DialogManager;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.network.ListState;
import com.microsoft.xbox.xle.app.SGProjectSpecificDialogManager;
import com.microsoft.xbox.xle.app.activity.Profile.ProfileScreenViewModel.ChangeFriendshipFormOptions;
import com.microsoft.xbox.xle.telemetry.helpers.UTCChangeRelationship;
import com.microsoft.xbox.xle.telemetry.helpers.UTCChangeRelationship.FavoriteStatus;
import com.microsoft.xbox.xle.telemetry.helpers.UTCChangeRelationship.GamerType;
import com.microsoft.xbox.xle.telemetry.helpers.UTCChangeRelationship.RealNameStatus;
import com.microsoft.xbox.xle.telemetry.helpers.UTCChangeRelationship.Relationship;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;

public class ChangeFriendshipDialogViewModel
{
  private static final String TAG = ChangeFriendshipDialogViewModel.class.getSimpleName();
  private AddUserToFavoriteListAsyncTask addUserToFavoriteListAsyncTask;
  private AddUserToFollowingListAsyncTask addUserToFollowingListAsyncTask;
  private AddUserToShareIdentityListAsyncTask addUserToShareIdentityListAsyncTask;
  private HashSet<ProfileScreenViewModel.ChangeFriendshipFormOptions> changeFriendshipForm = new HashSet();
  private boolean isAddingUserToFavoriteList;
  private boolean isAddingUserToFollowingList;
  private boolean isAddingUserToShareIdentityList;
  private boolean isFavorite = false;
  private boolean isFollowing = false;
  private boolean isLoadingUserProfile;
  private boolean isRemovingUserFromFavoriteList;
  private boolean isRemovingUserFromFollowingList;
  private boolean isRemovingUserFromShareIdentityList;
  private boolean isSharingRealNameEnd;
  private boolean isSharingRealNameStart;
  private LoadPersonDataAsyncTask loadProfileAsyncTask;
  private ProfileModel model;
  private RemoveUserFromFavoriteListAsyncTask removeUserFromFavoriteListAsyncTask;
  private RemoveUserFromFollowingListAsyncTask removeUserFromFollowingListAsyncTask;
  private RemoveUserFromShareIdentityListAsyncTask removeUserFromShareIdentityListAsyncTask;
  private ListState viewModelState = ListState.LoadingState;
  
  public ChangeFriendshipDialogViewModel(ProfileModel paramProfileModel)
  {
    if (!ProfileModel.isMeXuid(paramProfileModel.getXuid())) {
      bool = true;
    }
    XLEAssert.assertTrue(bool);
    this.model = paramProfileModel;
  }
  
  private void notifyDialogAsyncTaskCompleted()
  {
    ((SGProjectSpecificDialogManager)DialogManager.getInstance().getManager()).notifyChangeFriendshipDialogAsyncTaskCompleted();
  }
  
  private void notifyDialogAsyncTaskFailed(String paramString)
  {
    ((SGProjectSpecificDialogManager)DialogManager.getInstance().getManager()).notifyChangeFriendshipDialogAsyncTaskFailed(paramString);
  }
  
  private void notifyDialogUpdateView()
  {
    ((SGProjectSpecificDialogManager)DialogManager.getInstance().getManager()).notifyChangeFriendshipDialogUpdateView();
  }
  
  private void onAddUseToShareIdentityListCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isAddingUserToShareIdentityList = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      notifyDialogAsyncTaskCompleted();
      return;
    }
    notifyDialogAsyncTaskFailed(XboxTcuiSdk.getResources().getString(R.string.RealNameSharing_ErrorChangeRemove));
  }
  
  private void onAddUserToFavoriteListCompleted(AsyncActionStatus paramAsyncActionStatus, boolean paramBoolean)
  {
    this.isAddingUserToFavoriteList = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      this.isFavorite = paramBoolean;
      notifyDialogAsyncTaskCompleted();
      return;
    }
    notifyDialogAsyncTaskFailed(XboxTcuiSdk.getResources().getString(R.string.RealNameSharing_ErrorChangeRemove));
  }
  
  private void onAddUserToFollowingListCompleted(AsyncActionStatus paramAsyncActionStatus, boolean paramBoolean)
  {
    this.isAddingUserToFollowingList = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      this.isFollowing = paramBoolean;
      notifyDialogAsyncTaskCompleted();
      return;
    }
    paramAsyncActionStatus = null;
    ProfileModel localProfileModel = ProfileModel.getMeProfileModel();
    if (localProfileModel != null) {
      paramAsyncActionStatus = localProfileModel.getAddUserToFollowingResult();
    }
    if ((paramAsyncActionStatus != null) && (!paramAsyncActionStatus.getAddFollowingRequestStatus()) && (paramAsyncActionStatus.code == 1028))
    {
      notifyDialogAsyncTaskFailed(paramAsyncActionStatus.description);
      return;
    }
    notifyDialogAsyncTaskFailed(XboxTcuiSdk.getResources().getString(R.string.RealNameSharing_ErrorAddingFriend));
  }
  
  private void onLoadPersonDataCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isLoadingUserProfile = false;
    switch (paramAsyncActionStatus)
    {
    }
    for (;;)
    {
      notifyDialogUpdateView();
      return;
      if (this.model.getProfileSummaryData() != null)
      {
        this.viewModelState = ListState.ValidContentState;
      }
      else
      {
        this.viewModelState = ListState.ErrorState;
        continue;
        this.viewModelState = ListState.ErrorState;
      }
    }
  }
  
  private void onRemoveUserFromFavoriteListCompleted(AsyncActionStatus paramAsyncActionStatus, boolean paramBoolean)
  {
    this.isRemovingUserFromFavoriteList = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      this.isFavorite = paramBoolean;
      notifyDialogAsyncTaskCompleted();
      return;
    }
    notifyDialogAsyncTaskFailed(XboxTcuiSdk.getResources().getString(R.string.RealNameSharing_ErrorChangeRemove));
  }
  
  private void onRemoveUserFromFollowingListCompleted(AsyncActionStatus paramAsyncActionStatus, boolean paramBoolean)
  {
    this.isRemovingUserFromFollowingList = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      this.isFollowing = paramBoolean;
      if ((this.isFavorite) && (!this.isFollowing)) {
        this.isFavorite = false;
      }
      notifyDialogAsyncTaskCompleted();
      return;
    }
    notifyDialogAsyncTaskFailed(XboxTcuiSdk.getResources().getString(R.string.RealNameSharing_ErrorChangeRemove));
  }
  
  private void onRemoveUserFromShareIdentityListCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isRemovingUserFromShareIdentityList = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      notifyDialogAsyncTaskCompleted();
      return;
    }
    notifyDialogAsyncTaskFailed(XboxTcuiSdk.getResources().getString(R.string.RealNameSharing_ErrorChangeRemove));
  }
  
  private void showError(int paramInt)
  {
    DialogManager.getInstance().showToast(paramInt);
  }
  
  public void addFavoriteUser()
  {
    if (this.addUserToFavoriteListAsyncTask != null) {
      this.addUserToFavoriteListAsyncTask.cancel();
    }
    this.addUserToFavoriteListAsyncTask = new AddUserToFavoriteListAsyncTask(this.model.getXuid());
    this.addUserToFavoriteListAsyncTask.load(true);
  }
  
  public void addFollowingUser()
  {
    if (ProfileModel.hasPrivilegeToAddFriend())
    {
      if (this.addUserToFollowingListAsyncTask != null) {
        this.addUserToFollowingListAsyncTask.cancel();
      }
      this.addUserToFollowingListAsyncTask = new AddUserToFollowingListAsyncTask(this.model.getXuid());
      this.addUserToFollowingListAsyncTask.load(true);
      return;
    }
    showError(R.string.Global_MissingPrivilegeError_DialogBody);
  }
  
  public void addUserToShareIdentityList()
  {
    if (this.addUserToShareIdentityListAsyncTask != null) {
      this.addUserToShareIdentityListAsyncTask.cancel();
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.model.getXuid());
    this.addUserToShareIdentityListAsyncTask = new AddUserToShareIdentityListAsyncTask(localArrayList);
    this.addUserToShareIdentityListAsyncTask.load(true);
  }
  
  public void clearChangeFriendshipForm()
  {
    this.changeFriendshipForm.clear();
  }
  
  public String getCallerGamerTag()
  {
    ProfileModel localProfileModel = ProfileModel.getMeProfileModel();
    if (localProfileModel != null) {
      return localProfileModel.getGamerTag();
    }
    return "";
  }
  
  public boolean getCallerMarkedTargetAsIdentityShared()
  {
    return this.model.hasCallerMarkedTargetAsIdentityShared();
  }
  
  public String getCallerShareRealNameStatus()
  {
    ProfileModel localProfileModel = ProfileModel.getMeProfileModel();
    if (localProfileModel != null) {
      return localProfileModel.getShareRealNameStatus();
    }
    return "";
  }
  
  public String getDialogButtonText()
  {
    if (this.isFollowing) {
      return XboxTcuiSdk.getResources().getString(R.string.TextInput_Confirm);
    }
    return XboxTcuiSdk.getResources().getString(R.string.OK_Text);
  }
  
  public String getGamerPicUrl()
  {
    return this.model.getGamerPicImageUrl();
  }
  
  public String getGamerScore()
  {
    return this.model.getGamerScore();
  }
  
  public String getGamerTag()
  {
    return this.model.getGamerTag();
  }
  
  public boolean getIsFavorite()
  {
    return this.model.hasCallerMarkedTargetAsFavorite();
  }
  
  public boolean getIsFollowing()
  {
    return this.model.isCallerFollowingTarget();
  }
  
  public boolean getIsSharingRealNameEnd()
  {
    return this.isSharingRealNameEnd;
  }
  
  public boolean getIsSharingRealNameStart()
  {
    return this.isSharingRealNameStart;
  }
  
  public int getPreferredColor()
  {
    return this.model.getPreferedColor();
  }
  
  public String getRealName()
  {
    return this.model.getRealName();
  }
  
  public ListState getViewModelState()
  {
    return this.viewModelState;
  }
  
  public String getXuid()
  {
    return this.model.getXuid();
  }
  
  public boolean isBusy()
  {
    return (this.isLoadingUserProfile) || (this.isAddingUserToFavoriteList) || (this.isRemovingUserFromFavoriteList) || (this.isAddingUserToFollowingList) || (this.isRemovingUserFromFollowingList) || (this.isAddingUserToShareIdentityList) || (this.isRemovingUserFromShareIdentityList);
  }
  
  public void load()
  {
    if (this.loadProfileAsyncTask != null) {
      this.loadProfileAsyncTask.cancel();
    }
    this.loadProfileAsyncTask = new LoadPersonDataAsyncTask(null);
    this.loadProfileAsyncTask.load(true);
  }
  
  public void onChangeRelationshipCompleted()
  {
    int i = 0;
    UTCChangeRelationship.Relationship localRelationship;
    UTCChangeRelationship.FavoriteStatus localFavoriteStatus;
    if (this.model.isCallerFollowingTarget())
    {
      localRelationship = UTCChangeRelationship.Relationship.EXISTINGFRIEND;
      if (!this.model.hasCallerMarkedTargetAsFavorite()) {
        break label207;
      }
      localFavoriteStatus = UTCChangeRelationship.FavoriteStatus.EXISTINGFAVORITE;
      label31:
      if (!this.model.hasCallerMarkedTargetAsIdentityShared()) {
        break label214;
      }
    }
    UTCChangeRelationship.GamerType localGamerType;
    label207:
    label214:
    for (UTCChangeRelationship.RealNameStatus localRealNameStatus = UTCChangeRelationship.RealNameStatus.EXISTINGSHARED;; localRealNameStatus = UTCChangeRelationship.RealNameStatus.EXISTINGNOTSHARED)
    {
      localGamerType = UTCChangeRelationship.GamerType.NORMAL;
      if (this.changeFriendshipForm.contains(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToFriendList))
      {
        localRelationship = UTCChangeRelationship.Relationship.ADDFRIEND;
        addFollowingUser();
        i = 1;
      }
      if (this.changeFriendshipForm.contains(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldRemoveUserFromFriendList))
      {
        localRelationship = UTCChangeRelationship.Relationship.REMOVEFRIEND;
        removeFollowingUser();
        i = 1;
      }
      if (this.changeFriendshipForm.contains(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToFavoriteList))
      {
        localFavoriteStatus = UTCChangeRelationship.FavoriteStatus.FAVORITED;
        addFavoriteUser();
        i = 1;
      }
      if (this.changeFriendshipForm.contains(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldRemoveUserFromFavoriteList))
      {
        localFavoriteStatus = UTCChangeRelationship.FavoriteStatus.UNFAVORITED;
        removeFavoriteUser();
        i = 1;
      }
      if (this.changeFriendshipForm.contains(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToShareIdentityList))
      {
        localRealNameStatus = UTCChangeRelationship.RealNameStatus.SHARINGON;
        addUserToShareIdentityList();
        i = 1;
      }
      if (this.changeFriendshipForm.contains(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldRemoveUserFromShareIdentityList))
      {
        localRealNameStatus = UTCChangeRelationship.RealNameStatus.SHARINGOFF;
        removeUserFromShareIdentityList();
        i = 1;
      }
      if (i != 0) {
        break label221;
      }
      notifyDialogAsyncTaskCompleted();
      return;
      localRelationship = UTCChangeRelationship.Relationship.NOTCHANGED;
      break;
      localFavoriteStatus = UTCChangeRelationship.FavoriteStatus.EXISTINGNOTFAVORITED;
      break label31;
    }
    label221:
    UTCChangeRelationship.trackChangeRelationshipDone(localRelationship, localRealNameStatus, localFavoriteStatus, localGamerType);
  }
  
  public void removeFavoriteUser()
  {
    if (this.removeUserFromFavoriteListAsyncTask != null) {
      this.removeUserFromFavoriteListAsyncTask.cancel();
    }
    this.removeUserFromFavoriteListAsyncTask = new RemoveUserFromFavoriteListAsyncTask(this.model.getXuid());
    this.removeUserFromFavoriteListAsyncTask.load(true);
  }
  
  public void removeFollowingUser()
  {
    if (this.removeUserFromFollowingListAsyncTask != null) {
      this.removeUserFromFollowingListAsyncTask.cancel();
    }
    this.removeUserFromFollowingListAsyncTask = new RemoveUserFromFollowingListAsyncTask(this.model.getXuid());
    this.removeUserFromFollowingListAsyncTask.load(true);
  }
  
  public void removeUserFromShareIdentityList()
  {
    if (this.removeUserFromFollowingListAsyncTask != null) {
      this.removeUserFromFavoriteListAsyncTask.cancel();
    }
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(this.model.getXuid());
    this.removeUserFromShareIdentityListAsyncTask = new RemoveUserFromShareIdentityListAsyncTask(localArrayList);
    this.removeUserFromShareIdentityListAsyncTask.load(true);
  }
  
  public void setInitialRealNameSharingState(boolean paramBoolean)
  {
    this.isSharingRealNameStart = paramBoolean;
    this.isSharingRealNameEnd = paramBoolean;
  }
  
  public void setIsSharingRealNameEnd(boolean paramBoolean)
  {
    this.isSharingRealNameEnd = paramBoolean;
  }
  
  public void setShouldAddUserToFavoriteList(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.changeFriendshipForm.add(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToFavoriteList);
      return;
    }
    this.changeFriendshipForm.remove(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToFavoriteList);
  }
  
  public void setShouldAddUserToFriendList(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.changeFriendshipForm.add(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToFriendList);
      return;
    }
    this.changeFriendshipForm.remove(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToFriendList);
  }
  
  public void setShouldAddUserToShareIdentityList(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.changeFriendshipForm.add(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToShareIdentityList);
      return;
    }
    this.changeFriendshipForm.remove(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldAddUserToShareIdentityList);
  }
  
  public void setShouldRemoveUserFroShareIdentityList(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.changeFriendshipForm.add(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldRemoveUserFromShareIdentityList);
      return;
    }
    this.changeFriendshipForm.remove(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldRemoveUserFromShareIdentityList);
  }
  
  public void setShouldRemoveUserFromFavoriteList(boolean paramBoolean)
  {
    if (paramBoolean)
    {
      this.changeFriendshipForm.add(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldRemoveUserFromFavoriteList);
      return;
    }
    this.changeFriendshipForm.remove(ProfileScreenViewModel.ChangeFriendshipFormOptions.ShouldRemoveUserFromFavoriteList);
  }
  
  private class AddUserToFavoriteListAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private boolean favoriteUser = false;
    private String favoriteUserXuid;
    
    public AddUserToFavoriteListAsyncTask(String paramString)
    {
      this.favoriteUserXuid = paramString;
    }
    
    protected boolean checkShouldExecute()
    {
      XLEAssert.assertIsUIThread();
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      Object localObject = ProfileModel.getMeProfileModel();
      if (localObject != null)
      {
        AsyncActionStatus localAsyncActionStatus = ((ProfileModel)localObject).addUserToFavoriteList(this.forceLoad, this.favoriteUserXuid).getStatus();
        if ((localAsyncActionStatus == AsyncActionStatus.SUCCESS) || (localAsyncActionStatus == AsyncActionStatus.NO_CHANGE) || (localAsyncActionStatus == AsyncActionStatus.NO_OP_SUCCESS))
        {
          localObject = ((ProfileModel)localObject).getFavorites();
          if (localObject != null)
          {
            localObject = ((ArrayList)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              FollowersData localFollowersData = (FollowersData)((Iterator)localObject).next();
              if (localFollowersData.xuid.equals(this.favoriteUserXuid)) {
                this.favoriteUser = localFollowersData.isFavorite;
              }
            }
          }
        }
        return localAsyncActionStatus;
      }
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.this.onAddUserToFavoriteListCompleted(AsyncActionStatus.NO_CHANGE, this.favoriteUser);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ChangeFriendshipDialogViewModel.this.onAddUserToFavoriteListCompleted(paramAsyncActionStatus, this.favoriteUser);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.access$902(ChangeFriendshipDialogViewModel.this, true);
    }
  }
  
  private class AddUserToFollowingListAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private String followingUserXuid;
    private boolean isFollowingUser = false;
    
    public AddUserToFollowingListAsyncTask(String paramString)
    {
      this.followingUserXuid = paramString;
    }
    
    protected boolean checkShouldExecute()
    {
      XLEAssert.assertIsUIThread();
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      Object localObject2 = ProfileModel.getMeProfileModel();
      if (localObject2 != null)
      {
        AsyncActionStatus localAsyncActionStatus = ((ProfileModel)localObject2).addUserToFollowingList(this.forceLoad, this.followingUserXuid).getStatus();
        Object localObject1 = localAsyncActionStatus;
        if (!AsyncActionStatus.getIsFail(localAsyncActionStatus))
        {
          localObject1 = ((ProfileModel)localObject2).getAddUserToFollowingResult();
          if ((localObject1 == null) || (((AddFollowingUserResponseContainer.AddFollowingUserResponse)localObject1).getAddFollowingRequestStatus()) || (((AddFollowingUserResponseContainer.AddFollowingUserResponse)localObject1).code != 1028)) {
            break label65;
          }
          localObject1 = AsyncActionStatus.FAIL;
        }
        label65:
        do
        {
          return (AsyncActionStatus)localObject1;
          ChangeFriendshipDialogViewModel.this.model.loadProfileSummary(true);
          ((ProfileModel)localObject2).loadProfileSummary(true);
          localObject2 = ((ProfileModel)localObject2).getFollowingData();
          localObject1 = localAsyncActionStatus;
        } while (localObject2 == null);
        localObject2 = ((ArrayList)localObject2).iterator();
        do
        {
          localObject1 = localAsyncActionStatus;
          if (!((Iterator)localObject2).hasNext()) {
            break;
          }
        } while (!((FollowersData)((Iterator)localObject2).next()).xuid.equals(this.followingUserXuid));
        this.isFollowingUser = true;
        return localAsyncActionStatus;
      }
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.this.onAddUserToFollowingListCompleted(AsyncActionStatus.NO_CHANGE, this.isFollowingUser);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ChangeFriendshipDialogViewModel.this.onAddUserToFollowingListCompleted(paramAsyncActionStatus, this.isFollowingUser);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.access$1302(ChangeFriendshipDialogViewModel.this, true);
    }
  }
  
  private class AddUserToShareIdentityListAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private ArrayList<String> usersToAdd;
    
    public AddUserToShareIdentityListAsyncTask()
    {
      ArrayList localArrayList;
      this.usersToAdd = localArrayList;
    }
    
    protected boolean checkShouldExecute()
    {
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      ProfileModel localProfileModel = ProfileModel.getMeProfileModel();
      if (localProfileModel != null) {
        return localProfileModel.addUserToShareIdentity(this.forceLoad, this.usersToAdd).getStatus();
      }
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction() {}
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ChangeFriendshipDialogViewModel.this.onAddUseToShareIdentityListCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.access$602(ChangeFriendshipDialogViewModel.this, true);
    }
  }
  
  private class LoadPersonDataAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private LoadPersonDataAsyncTask() {}
    
    protected boolean checkShouldExecute()
    {
      XLEAssert.assertIsUIThread();
      return false;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      XLEAssert.assertNotNull(ChangeFriendshipDialogViewModel.this.model);
      return ChangeFriendshipDialogViewModel.this.model.loadProfileSummary(this.forceLoad).getStatus();
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.this.onLoadPersonDataCompleted(AsyncActionStatus.NO_CHANGE);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ChangeFriendshipDialogViewModel.this.onLoadPersonDataCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.access$202(ChangeFriendshipDialogViewModel.this, true);
    }
  }
  
  private class RemoveUserFromFavoriteListAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private boolean favoriteUser = false;
    private String favoriteUserXuid;
    
    public RemoveUserFromFavoriteListAsyncTask(String paramString)
    {
      this.favoriteUserXuid = paramString;
    }
    
    protected boolean checkShouldExecute()
    {
      XLEAssert.assertIsUIThread();
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      Object localObject = ProfileModel.getMeProfileModel();
      if (localObject != null)
      {
        AsyncActionStatus localAsyncActionStatus = ((ProfileModel)localObject).removeUserFromFavoriteList(this.forceLoad, this.favoriteUserXuid).getStatus();
        if ((localAsyncActionStatus == AsyncActionStatus.SUCCESS) || (localAsyncActionStatus == AsyncActionStatus.NO_CHANGE) || (localAsyncActionStatus == AsyncActionStatus.NO_OP_SUCCESS))
        {
          localObject = ((ProfileModel)localObject).getFavorites();
          if (localObject != null)
          {
            localObject = ((ArrayList)localObject).iterator();
            while (((Iterator)localObject).hasNext())
            {
              FollowersData localFollowersData = (FollowersData)((Iterator)localObject).next();
              if (localFollowersData.xuid.equals(this.favoriteUserXuid)) {
                this.favoriteUser = localFollowersData.isFavorite;
              }
            }
          }
        }
        return localAsyncActionStatus;
      }
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.this.onRemoveUserFromFavoriteListCompleted(AsyncActionStatus.NO_CHANGE, this.favoriteUser);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ChangeFriendshipDialogViewModel.this.onRemoveUserFromFavoriteListCompleted(paramAsyncActionStatus, this.favoriteUser);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.access$1102(ChangeFriendshipDialogViewModel.this, true);
    }
  }
  
  private class RemoveUserFromFollowingListAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private String followingUserXuid;
    private boolean isFollowingUser = true;
    
    public RemoveUserFromFollowingListAsyncTask(String paramString)
    {
      this.followingUserXuid = paramString;
    }
    
    protected boolean checkShouldExecute()
    {
      XLEAssert.assertIsUIThread();
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      ProfileModel localProfileModel = ProfileModel.getMeProfileModel();
      if (localProfileModel != null)
      {
        AsyncActionStatus localAsyncActionStatus = localProfileModel.removeUserFromFollowingList(this.forceLoad, this.followingUserXuid).getStatus();
        if (!AsyncActionStatus.getIsFail(localAsyncActionStatus))
        {
          ChangeFriendshipDialogViewModel.this.model.loadProfileSummary(true);
          localProfileModel.loadProfileSummary(true);
          this.isFollowingUser = false;
        }
        return localAsyncActionStatus;
      }
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.this.onRemoveUserFromFollowingListCompleted(AsyncActionStatus.NO_CHANGE, this.isFollowingUser);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ChangeFriendshipDialogViewModel.this.onRemoveUserFromFollowingListCompleted(paramAsyncActionStatus, this.isFollowingUser);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.access$1502(ChangeFriendshipDialogViewModel.this, true);
    }
  }
  
  private class RemoveUserFromShareIdentityListAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private ArrayList<String> usersToAdd;
    
    public RemoveUserFromShareIdentityListAsyncTask()
    {
      ArrayList localArrayList;
      this.usersToAdd = localArrayList;
    }
    
    protected boolean checkShouldExecute()
    {
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      ProfileModel localProfileModel = ProfileModel.getMeProfileModel();
      if (localProfileModel != null) {
        return localProfileModel.removeUserFromShareIdentity(this.forceLoad, this.usersToAdd).getStatus();
      }
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction() {}
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      ChangeFriendshipDialogViewModel.this.onRemoveUserFromShareIdentityListCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      ChangeFriendshipDialogViewModel.access$402(ChangeFriendshipDialogViewModel.this, true);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\viewmodel\ChangeFriendshipDialogViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */