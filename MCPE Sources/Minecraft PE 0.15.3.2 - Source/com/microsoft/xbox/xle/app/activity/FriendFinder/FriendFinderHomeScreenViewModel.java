package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.pm.ApplicationInfo;
import android.support.v4.content.ContextCompat;
import android.util.Log;
import com.microsoft.onlineid.Ticket;
import com.microsoft.onlineid.UserAccount;
import com.microsoft.xbox.idp.interop.LocalConfig;
import com.microsoft.xbox.idp.jobs.JobSilentSignIn;
import com.microsoft.xbox.idp.jobs.MSAJob;
import com.microsoft.xbox.idp.jobs.MSAJob.Callbacks;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.model.UpdateData;
import com.microsoft.xbox.service.model.UpdateType;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderModel;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderModel.LoadFailedCallback;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.FriendsFinderStateResult;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderState.LinkedAccountOptInStatus;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.RecommendationType;
import com.microsoft.xbox.service.network.managers.IUserProfileResult.ProfileUser;
import com.microsoft.xbox.service.network.managers.IUserProfileResult.UserProfileResult;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.service.network.managers.friendfinder.UploadContactsAsyncTask;
import com.microsoft.xbox.service.network.managers.friendfinder.UploadContactsAsyncTask.UploadContactsCompleted;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.DataLoadUtil;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.ProjectSpecificDataProvider;
import com.microsoft.xbox.toolkit.Ready;
import com.microsoft.xbox.toolkit.SingleEntryLoadingStatus;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.network.IDataLoaderRunnable;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.app.FriendFinderSettings;
import com.microsoft.xbox.xle.app.FriendFinderSettings.IconImageSize;
import com.microsoft.xbox.xle.app.activity.Profile.ProfileScreen;
import com.microsoft.xbox.xle.app.adapter.FriendFinderHomeScreenAdapter;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;
import java.util.Date;

public class FriendFinderHomeScreenViewModel
  extends ViewModelBase
  implements MSAJob.Callbacks, FriendFinderModel.LoadFailedCallback
{
  private static final int MAX_SEARCH_LENGTH = 256;
  private static final String MSA_TAG = "FriendFinder.MSA";
  private static final String POLICY = "mbi_ssl";
  private static final String SCOPE = "ssl.live.com";
  private FBSettingsAsyncTask fbSettingsAsyncTask;
  private FBManagerAndModelInitTask initFBandModelTask;
  private boolean isLoadingFriendFinderState;
  private boolean isSearchGamertagTaskRunning = false;
  private boolean isUploadingContacts;
  private SingleEntryLoadingStatus searchGamertagLoadingStatus = new SingleEntryLoadingStatus();
  private NetworkAsyncTask searchGamertagTask;
  private Boolean shouldShowDone;
  private UploadContactsAsyncTask uploadContactsAsyncTask;
  
  public FriendFinderHomeScreenViewModel(ScreenLayout paramScreenLayout)
  {
    super(paramScreenLayout);
    this.adapter = new FriendFinderHomeScreenAdapter(this);
  }
  
  private void cancelActiveTasks()
  {
    if (this.initFBandModelTask != null)
    {
      this.initFBandModelTask.cancel();
      this.initFBandModelTask = null;
    }
    if (this.fbSettingsAsyncTask != null)
    {
      this.fbSettingsAsyncTask.cancel();
      this.fbSettingsAsyncTask = null;
    }
    if (this.searchGamertagTask != null)
    {
      this.searchGamertagTask.cancel();
      this.searchGamertagTask = null;
    }
    if (this.uploadContactsAsyncTask != null)
    {
      this.uploadContactsAsyncTask.cancel();
      this.uploadContactsAsyncTask = null;
    }
  }
  
  private boolean hasReadContactsPermission()
  {
    return ContextCompat.checkSelfPermission(XboxTcuiSdk.getActivity(), "android.permission.READ_CONTACTS") == 0;
  }
  
  private void navigateToInfo(FriendFinderType paramFriendFinderType)
  {
    ActivityParameters localActivityParameters = new ActivityParameters();
    localActivityParameters.putFriendFinderType(paramFriendFinderType);
    try
    {
      NavigationManager.getInstance().PushScreen(FriendFinderInfoScreen.class, localActivityParameters);
      return;
    }
    catch (XLEException paramFriendFinderType) {}
  }
  
  private void navigateToSuggestions(FriendFinderType paramFriendFinderType)
  {
    try
    {
      ActivityParameters localActivityParameters = new ActivityParameters();
      localActivityParameters.putFriendFinderType(paramFriendFinderType);
      NavigationManager.getInstance().PushScreen(FriendFinderSuggestionsScreen.class, localActivityParameters);
      return;
    }
    catch (XLEException paramFriendFinderType) {}
  }
  
  private void onSearchGamertagCompleted(AsyncActionStatus paramAsyncActionStatus, String paramString)
  {
    this.isSearchGamertagTaskRunning = false;
    switch (paramAsyncActionStatus)
    {
    }
    for (;;)
    {
      updateAdapter();
      return;
      try
      {
        if (JavaUtil.isNullOrEmpty(paramString)) {
          continue;
        }
        paramAsyncActionStatus = new ActivityParameters();
        paramAsyncActionStatus.putSelectedProfile(paramString);
        UTCFriendFinder.trackGamertagSearchSuccess(getScreen().getName(), paramString);
        NavigationManager.getInstance().PushScreen(ProfileScreen.class, paramAsyncActionStatus);
      }
      catch (XLEException paramAsyncActionStatus) {}
      continue;
      showError(R.string.FriendsHub_CouldNotFindGamer);
    }
  }
  
  private void showContactsPermissionDialog()
  {
    Object localObject = XboxTcuiSdk.getActivity();
    String str = ((Context)localObject).getString(((Context)localObject).getApplicationInfo().labelRes);
    str = String.format(((Context)localObject).getString(R.string.Contacts_Permission_Denied_Android), new Object[] { str, str });
    localObject = new AlertDialog.Builder((Context)localObject);
    ((AlertDialog.Builder)localObject).setTitle(R.string.Contacts_Permission_Denied_Header);
    ((AlertDialog.Builder)localObject).setMessage(str);
    ((AlertDialog.Builder)localObject).create().show();
  }
  
  private boolean validSearchGamertag(String paramString)
  {
    return (!JavaUtil.isNullOrEmpty(paramString)) && (paramString.length() <= 256) && (JavaUtil.urlEncode(paramString) != null);
  }
  
  public boolean facebookLinked()
  {
    if (FacebookManager.getFacebookManagerReady().getIsReady()) {
      return FacebookManager.getInstance().isFacebookFriendFinderOptedIn();
    }
    return false;
  }
  
  public void finishFriendFinder()
  {
    try
    {
      NavigationManager.getInstance().PopAllScreens();
      return;
    }
    catch (XLEException localXLEException) {}
  }
  
  public String getFacebookIconUri()
  {
    return FriendFinderSettings.getIconBySize(IPeopleHubResult.RecommendationType.FacebookFriend.name(), FriendFinderSettings.IconImageSize.MEDIUM);
  }
  
  public boolean isBusy()
  {
    return (this.isLoadingFriendFinderState) || (this.isUploadingContacts);
  }
  
  public void load(boolean paramBoolean)
  {
    cancelActiveTasks();
    if (JavaUtil.isNullOrEmpty(ProjectSpecificDataProvider.getInstance().getSCDRpsTicket())) {
      new JobSilentSignIn(XboxTcuiSdk.getActivity(), "FriendFinderHome", this, "ssl.live.com", "mbi_ssl", new LocalConfig().getCid()).start();
    }
    if (FacebookManager.getFacebookManagerReady().getIsReady()) {
      FriendFinderModel.getInstance().loadAsync(true, this);
    }
    for (;;)
    {
      this.fbSettingsAsyncTask = new FBSettingsAsyncTask();
      this.fbSettingsAsyncTask.load(paramBoolean);
      return;
      this.initFBandModelTask = new FBManagerAndModelInitTask();
      this.initFBandModelTask.load(paramBoolean);
    }
  }
  
  public void navigateToFacebookSuggestions()
  {
    navigateToSuggestions(FriendFinderType.FACEBOOK);
  }
  
  public void navigateToLinkFacebook()
  {
    navigateToInfo(FriendFinderType.FACEBOOK);
  }
  
  public void navigateToLinkPhone()
  {
    if (hasReadContactsPermission())
    {
      navigateToInfo(FriendFinderType.PHONE);
      return;
    }
    showContactsPermissionDialog();
  }
  
  public void navigateToPhoneSuggestions()
  {
    if (hasReadContactsPermission())
    {
      if (this.uploadContactsAsyncTask != null)
      {
        this.uploadContactsAsyncTask.cancel();
        this.uploadContactsAsyncTask = null;
      }
      this.uploadContactsAsyncTask = new UploadContactsAsyncTask(new UploadContactsAsyncTask.UploadContactsCompleted()
      {
        public void onResult(AsyncActionStatus paramAnonymousAsyncActionStatus)
        {
          FriendFinderHomeScreenViewModel.access$002(FriendFinderHomeScreenViewModel.this, false);
          FriendFinderHomeScreenViewModel.this.navigateToSuggestions(FriendFinderType.PHONE);
        }
      });
      this.isUploadingContacts = true;
      updateAdapter();
      this.uploadContactsAsyncTask.load(true);
      return;
    }
    showContactsPermissionDialog();
  }
  
  public void onAccountAcquired(MSAJob paramMSAJob, UserAccount paramUserAccount) {}
  
  protected void onFacebookInitCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      FriendFinderModel.getInstance().loadAsync(true, this);
      return;
    }
    showError(R.string.Service_ErrorText);
  }
  
  protected void onFacebookSettingsCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    updateAdapter();
  }
  
  public void onFailure(MSAJob paramMSAJob, Exception paramException)
  {
    Log.i("FriendFinder.MSA", "onFailure - ignoring and will fail phone finder if invoked. " + Log.getStackTraceString(paramException));
  }
  
  public void onFriendFinderLoadFailed()
  {
    AlertDialog.Builder localBuilder = new AlertDialog.Builder(XboxTcuiSdk.getActivity());
    localBuilder.setMessage(R.string.Service_ErrorText);
    localBuilder.setCancelable(false);
    localBuilder.setPositiveButton(R.string.OK_Text, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          NavigationManager.getInstance().PopAllScreens();
          return;
        }
        catch (XLEException paramAnonymousDialogInterface) {}
      }
    });
    localBuilder.create().show();
  }
  
  public void onRehydrate()
  {
    this.adapter = new FriendFinderHomeScreenAdapter(this);
  }
  
  public void onSignedOut(MSAJob paramMSAJob) {}
  
  protected void onStartOverride()
  {
    FriendFinderModel.getInstance().addUniqueObserver(this);
  }
  
  protected void onStopOverride()
  {
    cancelActiveTasks();
    FriendFinderModel.getInstance().removeObserver(this);
  }
  
  public void onTicketAcquired(MSAJob paramMSAJob, Ticket paramTicket)
  {
    Log.i("FriendFinder.MSA", "onTicketAcquired - " + paramTicket.getValue());
    ProjectSpecificDataProvider.getInstance().setSCDRpsTicket(paramTicket.getValue());
  }
  
  public void onUiNeeded(MSAJob paramMSAJob)
  {
    Log.i("FriendFinder.MSA", "onUiNeeded - ignoring and will fail phone finder if invoked.");
  }
  
  public void onUserCancel(MSAJob paramMSAJob) {}
  
  public boolean phoneLinked()
  {
    FriendFinderState.FriendsFinderStateResult localFriendsFinderStateResult = FriendFinderModel.getInstance().getResult();
    return (localFriendsFinderStateResult != null) && (localFriendsFinderStateResult.getPhoneAccountOptInStatus() == FriendFinderState.LinkedAccountOptInStatus.OptedIn);
  }
  
  public void searchGamertag(String paramString)
  {
    if (!this.isSearchGamertagTaskRunning)
    {
      if (this.searchGamertagTask != null)
      {
        this.searchGamertagTask.cancel();
        this.searchGamertagTask = null;
      }
      if (validSearchGamertag(paramString))
      {
        this.isSearchGamertagTaskRunning = true;
        this.searchGamertagTask = DataLoadUtil.StartLoadFromUI(true, new Date().getTime(), null, this.searchGamertagLoadingStatus, new SearchGamertagRunner(paramString.trim()));
      }
    }
    else
    {
      return;
    }
    showError(R.string.FriendsHub_CouldNotFindGamer);
  }
  
  public boolean shouldShowDone()
  {
    if (this.shouldShowDone == null)
    {
      ActivityParameters localActivityParameters = NavigationManager.getInstance().getActivityParameters();
      if ((localActivityParameters == null) || (!localActivityParameters.getFriendFinderDone())) {
        break label43;
      }
    }
    label43:
    for (boolean bool = true;; bool = false)
    {
      this.shouldShowDone = Boolean.valueOf(bool);
      return this.shouldShowDone.booleanValue();
    }
  }
  
  protected void updateOverride(AsyncResult<UpdateData> paramAsyncResult)
  {
    if ((((UpdateData)paramAsyncResult.getResult()).getUpdateType() == UpdateType.FriendFinderFacebook) && (((UpdateData)paramAsyncResult.getResult()).getIsFinal()))
    {
      paramAsyncResult = FriendFinderModel.getInstance().getResult();
      this.isLoadingFriendFinderState = false;
      if ((FacebookManager.getInstance().getFacebookFriendFinderState() == null) || ((paramAsyncResult != null) && (FacebookManager.getInstance().getFacebookFriendFinderState().isFacebookStateChanged(paramAsyncResult)))) {
        FacebookManager.getInstance().setFacebookFriendFinderState(paramAsyncResult);
      }
      updateAdapter();
    }
  }
  
  protected class FBManagerAndModelInitTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    protected FBManagerAndModelInitTask() {}
    
    protected boolean checkShouldExecute()
    {
      return (!FacebookManager.getFacebookManagerReady().getIsReady()) || (FriendFinderModel.getInstance().shouldRefresh());
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      FacebookManager.getInstance();
      FacebookManager.getFacebookManagerReady().waitForReady();
      ProfileModel.getMeProfileModel().loadSync(true);
      if (FacebookManager.getFacebookManagerReady().getIsReady()) {
        return AsyncActionStatus.SUCCESS;
      }
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      if (FacebookManager.getFacebookManagerReady().getIsReady()) {
        FriendFinderHomeScreenViewModel.this.onFacebookInitCompleted(AsyncActionStatus.SUCCESS);
      }
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      FriendFinderHomeScreenViewModel.this.onFacebookInitCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      FriendFinderHomeScreenViewModel.access$202(FriendFinderHomeScreenViewModel.this, true);
    }
  }
  
  protected class FBSettingsAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    protected FBSettingsAsyncTask() {}
    
    protected boolean checkShouldExecute()
    {
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      Object localObject = null;
      try
      {
        FriendFinderSettings localFriendFinderSettings = ServiceManagerFactory.getInstance().getSLSServiceManager().getFriendFinderSettings();
        localObject = localFriendFinderSettings;
        localFriendFinderSettings.getIconsFromJson(localFriendFinderSettings.ICONS);
        localObject = localFriendFinderSettings;
      }
      catch (XLEException localXLEException)
      {
        for (;;) {}
      }
      if (localObject != null) {
        return AsyncActionStatus.SUCCESS;
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
      FriendFinderHomeScreenViewModel.this.onFacebookSettingsCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      FriendFinderHomeScreenViewModel.access$202(FriendFinderHomeScreenViewModel.this, true);
    }
  }
  
  private class SearchGamertagRunner
    extends IDataLoaderRunnable<IUserProfileResult.UserProfileResult>
  {
    private String gamerXuid;
    private String gamertag;
    
    public SearchGamertagRunner(String paramString)
    {
      this.gamertag = paramString;
    }
    
    public IUserProfileResult.UserProfileResult buildData()
      throws XLEException
    {
      IUserProfileResult.UserProfileResult localUserProfileResult = ServiceManagerFactory.getInstance().getSLSServiceManager().SearchGamertag(this.gamertag);
      this.gamerXuid = ((IUserProfileResult.ProfileUser)localUserProfileResult.profileUsers.get(0)).id;
      if (JavaUtil.isNullOrEmpty(this.gamerXuid)) {
        throw new XLEException(getDefaultErrorCode(), "Invalid gamertag returned from search service");
      }
      return localUserProfileResult;
    }
    
    public long getDefaultErrorCode()
    {
      return 3002L;
    }
    
    public void onPostExcute(AsyncResult<IUserProfileResult.UserProfileResult> paramAsyncResult)
    {
      FriendFinderHomeScreenViewModel.this.onSearchGamertagCompleted(paramAsyncResult.getStatus(), this.gamerXuid);
    }
    
    public void onPreExecute() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderHomeScreenViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */