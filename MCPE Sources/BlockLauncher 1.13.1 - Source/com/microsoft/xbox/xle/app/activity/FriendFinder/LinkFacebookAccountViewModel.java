package com.microsoft.xbox.xle.app.activity.FriendFinder;

import com.microsoft.xbox.service.model.friendfinder.FriendFinderModel;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.model.friendfinder.LinkedAccountHelpers.LinkedAccountType;
import com.microsoft.xbox.service.model.friendfinder.OptInStatus;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySetting;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySettingId;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySettingValue;
import com.microsoft.xbox.service.model.privacy.PrivacySettingsResult;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.Ready;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.R.string;
import java.util.ArrayList;

public class LinkFacebookAccountViewModel
  extends ViewModelBase
{
  private LinkFacebookAccountAsyncTask linkAccountAsyncTask;
  
  public LinkFacebookAccountViewModel(ScreenLayout paramScreenLayout)
  {
    super(paramScreenLayout);
  }
  
  private void cancelActiveTasks()
  {
    if (this.linkAccountAsyncTask != null) {
      this.linkAccountAsyncTask.cancel();
    }
  }
  
  private void onLinkAccountAsyncTaskCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      FriendFinderModel.getInstance().loadAsync(true);
      try
      {
        paramAsyncActionStatus = new ActivityParameters();
        paramAsyncActionStatus.putFriendFinderType(FriendFinderType.FACEBOOK);
        UTCFriendFinder.trackFacebookLoginSuccessful(((FriendFinderLinkScreen)getScreen()).getActivityName());
        NavigationManager.getInstance().PopScreensAndReplace(1, FriendFinderSuggestionsScreen.class, false, true, false, paramAsyncActionStatus);
        return;
      }
      catch (XLEException paramAsyncActionStatus)
      {
        return;
      }
    }
    FacebookManager.getInstance().resetFacebookToken(true);
    showError(R.string.Service_ErrorText);
    NavigationManager.getInstance().OnBackButtonPressed();
  }
  
  public boolean isBusy()
  {
    return true;
  }
  
  public void load(boolean paramBoolean)
  {
    cancelActiveTasks();
    this.linkAccountAsyncTask = new LinkFacebookAccountAsyncTask();
    this.linkAccountAsyncTask.load(true);
  }
  
  public boolean onBackButtonPressed()
  {
    UTCFriendFinder.trackBackButtonPressed(getScreen().getName(), FriendFinderType.FACEBOOK);
    return super.onBackButtonPressed();
  }
  
  public void onRehydrate() {}
  
  public void onStart()
  {
    super.onStart();
    XLEAssert.assertTrue(FacebookManager.getFacebookManagerReady().getIsReady());
  }
  
  protected void onStartOverride() {}
  
  protected void onStopOverride()
  {
    cancelActiveTasks();
  }
  
  protected class LinkFacebookAccountAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    protected LinkFacebookAccountAsyncTask() {}
    
    private boolean needUpdatePrivacy(PrivacySettings.PrivacySetting paramPrivacySetting)
    {
      paramPrivacySetting = paramPrivacySetting.getPrivacySettingValue();
      return (paramPrivacySetting == PrivacySettings.PrivacySettingValue.NotSet) || (paramPrivacySetting == PrivacySettings.PrivacySettingValue.Blocked);
    }
    
    protected boolean checkShouldExecute()
    {
      return false;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      try
      {
        Object localObject = ServiceManagerFactory.getInstance().getSLSServiceManager().getPrivacySetting(PrivacySettings.PrivacySettingId.ShareIdentity);
        if ((localObject != null) && (needUpdatePrivacy((PrivacySettings.PrivacySetting)localObject)))
        {
          localObject = new ArrayList();
          ((ArrayList)localObject).add(new PrivacySettings.PrivacySetting(PrivacySettings.PrivacySettingId.ShareIdentity, PrivacySettings.PrivacySettingValue.FriendCategoryShareIdentity));
          if (!ServiceManagerFactory.getInstance().getSLSServiceManager().setPrivacySettings(new PrivacySettingsResult((ArrayList)localObject))) {
            return AsyncActionStatus.FAIL;
          }
        }
        if (!ServiceManagerFactory.getInstance().getSLSServiceManager().updateThirdPartyToken(LinkedAccountHelpers.LinkedAccountType.Facebook, FacebookManager.getInstance().getTokenString())) {
          return AsyncActionStatus.FAIL;
        }
        if (!ServiceManagerFactory.getInstance().getSLSServiceManager().setFriendFinderOptInStatus(LinkedAccountHelpers.LinkedAccountType.Facebook, OptInStatus.OptedIn)) {
          return AsyncActionStatus.FAIL;
        }
        localObject = AsyncActionStatus.SUCCESS;
        return (AsyncActionStatus)localObject;
      }
      catch (XLEException localXLEException) {}
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return null;
    }
    
    protected void onNoAction() {}
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      LinkFacebookAccountViewModel.this.onLinkAccountAsyncTaskCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute() {}
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\LinkFacebookAccountViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */