package com.microsoft.xbox.xle.app.activity.FriendFinder;

import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.model.friendfinder.LinkedAccountHelpers.LinkedAccountType;
import com.microsoft.xbox.service.model.friendfinder.OptInStatus;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ErrorReturn;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.MsgType;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileRequest;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileResponse;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySetting;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySettingId;
import com.microsoft.xbox.service.model.privacy.PrivacySettings.PrivacySettingValue;
import com.microsoft.xbox.service.model.privacy.PrivacySettingsResult;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
import com.microsoft.xbox.service.network.managers.friendfinder.PhoneContactInfo;
import com.microsoft.xbox.service.network.managers.friendfinder.UploadContactsAsyncTask;
import com.microsoft.xbox.service.network.managers.friendfinder.UploadContactsAsyncTask.UploadContactsCompleted;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.app.adapter.FriendFinderVerifyCodeScreenAdapter;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.R.string;
import java.util.ArrayList;

public class FriendFinderVerifyCodeScreenViewModel
  extends ViewModelBase
{
  private static final String TAG = "FriendFinder";
  private AddShortCircuitProfileAsyncTask addShortCircuitProfileAsyncTask;
  private boolean isSendingCode;
  private boolean isUpdatingProfile;
  private UpdateShortCircuitProfileAsyncTask updateShortCircuitProfileAsyncTask;
  private UploadContactsAsyncTask uploadContactsAsyncTask;
  
  public FriendFinderVerifyCodeScreenViewModel(ScreenLayout paramScreenLayout)
  {
    super(paramScreenLayout);
    this.adapter = new FriendFinderVerifyCodeScreenAdapter(this);
  }
  
  private void addProfile(boolean paramBoolean)
  {
    if (this.addShortCircuitProfileAsyncTask != null) {
      this.addShortCircuitProfileAsyncTask.cancel();
    }
    this.addShortCircuitProfileAsyncTask = new AddShortCircuitProfileAsyncTask(paramBoolean);
    this.addShortCircuitProfileAsyncTask.load(true);
    this.isSendingCode = true;
    updateAdapter();
  }
  
  private void cancelActiveTasks()
  {
    if (this.addShortCircuitProfileAsyncTask != null)
    {
      this.addShortCircuitProfileAsyncTask.cancel();
      this.addShortCircuitProfileAsyncTask = null;
    }
    if (this.updateShortCircuitProfileAsyncTask != null)
    {
      this.updateShortCircuitProfileAsyncTask.cancel();
      this.updateShortCircuitProfileAsyncTask = null;
    }
    if (this.uploadContactsAsyncTask != null)
    {
      this.uploadContactsAsyncTask.cancel();
      this.uploadContactsAsyncTask = null;
    }
  }
  
  private void onAddShortCircuitProfileCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isSendingCode = false;
    switch (paramAsyncActionStatus)
    {
    }
    for (;;)
    {
      updateAdapter();
      return;
      showError(R.string.Service_ErrorText);
    }
  }
  
  private void onUpdateShortCircuitProfileCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      if (this.uploadContactsAsyncTask != null)
      {
        this.uploadContactsAsyncTask.cancel();
        this.uploadContactsAsyncTask = null;
      }
      this.uploadContactsAsyncTask = new UploadContactsAsyncTask(new UploadContactsAsyncTask.UploadContactsCompleted()
      {
        public void onResult(AsyncActionStatus paramAnonymousAsyncActionStatus)
        {
          FriendFinderVerifyCodeScreenViewModel.this.onUploadContactsCompleted(paramAnonymousAsyncActionStatus);
        }
      });
      this.uploadContactsAsyncTask.load(true);
      return;
    }
    this.isUpdatingProfile = false;
    showError(R.string.Service_ErrorText);
    updateAdapter();
  }
  
  private void onUploadContactsCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isUpdatingProfile = false;
    updateAdapter();
    paramAsyncActionStatus = new ActivityParameters();
    paramAsyncActionStatus.putFriendFinderType(FriendFinderType.PHONE);
    try
    {
      NavigationManager.getInstance().PushScreen(FriendFinderSuggestionsScreen.class, paramAsyncActionStatus);
      return;
    }
    catch (XLEException paramAsyncActionStatus) {}
  }
  
  public void callMe()
  {
    addProfile(true);
  }
  
  public boolean isBusy()
  {
    return this.isUpdatingProfile;
  }
  
  public boolean isSendingCode()
  {
    return this.isSendingCode;
  }
  
  public void load(boolean paramBoolean) {}
  
  public boolean onBackButtonPressed()
  {
    UTCFriendFinder.trackBackButtonPressed(getScreen().getName(), FriendFinderType.PHONE);
    return super.onBackButtonPressed();
  }
  
  public void onRehydrate()
  {
    this.adapter = new FriendFinderVerifyCodeScreenAdapter(this);
  }
  
  protected void onStartOverride() {}
  
  protected void onStopOverride()
  {
    cancelActiveTasks();
  }
  
  public void resendCode()
  {
    addProfile(false);
  }
  
  public void verifyCode(String paramString)
  {
    if (this.updateShortCircuitProfileAsyncTask != null) {
      this.updateShortCircuitProfileAsyncTask.cancel();
    }
    this.updateShortCircuitProfileAsyncTask = new UpdateShortCircuitProfileAsyncTask(paramString);
    this.updateShortCircuitProfileAsyncTask.load(true);
    updateAdapter();
  }
  
  private class AddShortCircuitProfileAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private boolean useVoice;
    
    public AddShortCircuitProfileAsyncTask(boolean paramBoolean)
    {
      this.useVoice = paramBoolean;
    }
    
    protected boolean checkShouldExecute()
    {
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      int j = 0;
      for (;;)
      {
        int i;
        int k;
        try
        {
          String str = PhoneContactInfo.getInstance().getRegionWithCode();
          Object localObject1 = PhoneContactInfo.getInstance().getUserEnteredNumber();
          if (JavaUtil.isNullOrEmpty(str))
          {
            if (((String)localObject1).startsWith("+")) {
              break label336;
            }
            localObject1 = "+" + (String)localObject1;
            localObject2 = new ShortCircuitProfileMessage.ShortCircuitProfileRequest(ShortCircuitProfileMessage.MsgType.Delete, (String)localObject1, str);
            localObject2 = ServiceManagerFactory.getInstance().getSLSServiceManager().sendShortCircuitProfile((ShortCircuitProfileMessage.ShortCircuitProfileRequest)localObject2);
            if ((localObject2 != null) && (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error != null) && (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error.message != null) && (!((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error.message.contains("Cannot edit or delete a phone that does not exist"))) {
              return AsyncActionStatus.FAIL;
            }
          }
          else
          {
            localObject1 = ((String)localObject1).replace("+", "");
            continue;
          }
          Object localObject2 = new ShortCircuitProfileMessage.ShortCircuitProfileRequest(ShortCircuitProfileMessage.MsgType.Add, (String)localObject1, str, this.useVoice);
          localObject2 = ServiceManagerFactory.getInstance().getSLSServiceManager().sendShortCircuitProfile((ShortCircuitProfileMessage.ShortCircuitProfileRequest)localObject2);
          if ((localObject2 != null) && (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error != null))
          {
            if (JavaUtil.isNullOrEmpty(str))
            {
              i = 0;
              if (i < ((String)localObject1).length())
              {
                k = j;
                if (!Character.isDigit(((String)localObject1).charAt(i))) {
                  break label339;
                }
                k = j + 1;
                break label339;
              }
              if (j == 10)
              {
                localObject1 = new ShortCircuitProfileMessage.ShortCircuitProfileRequest(ShortCircuitProfileMessage.MsgType.Add, "+1" + ((String)localObject1).replace("+", ""), str);
                localObject1 = ServiceManagerFactory.getInstance().getSLSServiceManager().sendShortCircuitProfile((ShortCircuitProfileMessage.ShortCircuitProfileRequest)localObject1);
                if ((localObject1 != null) && (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject1).error != null)) {
                  return AsyncActionStatus.FAIL;
                }
                return AsyncActionStatus.SUCCESS;
              }
            }
            localObject1 = AsyncActionStatus.FAIL;
            return (AsyncActionStatus)localObject1;
          }
        }
        catch (XLEException localXLEException)
        {
          return AsyncActionStatus.FAIL;
        }
        return AsyncActionStatus.SUCCESS;
        label336:
        continue;
        label339:
        i += 1;
        j = k;
      }
    }
    
    protected AsyncActionStatus onError()
    {
      return null;
    }
    
    protected void onNoAction() {}
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      FriendFinderVerifyCodeScreenViewModel.this.onAddShortCircuitProfileCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute() {}
  }
  
  private class UpdateShortCircuitProfileAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private String verificationToken;
    
    public UpdateShortCircuitProfileAsyncTask(String paramString)
    {
      this.verificationToken = paramString;
    }
    
    private boolean updateOptInSucceeded()
      throws XLEException
    {
      Object localObject = ServiceManagerFactory.getInstance().getSLSServiceManager().getPrivacySetting(PrivacySettings.PrivacySettingId.ShareIdentity);
      if (localObject != null) {}
      for (localObject = ((PrivacySettings.PrivacySetting)localObject).getPrivacySettingValue(); (localObject == PrivacySettings.PrivacySettingValue.NotSet) || (localObject == PrivacySettings.PrivacySettingValue.Blocked); localObject = PrivacySettings.PrivacySettingValue.NotSet)
      {
        localObject = new ArrayList();
        ((ArrayList)localObject).add(new PrivacySettings.PrivacySetting(PrivacySettings.PrivacySettingId.ShareIdentity, PrivacySettings.PrivacySettingValue.FriendCategoryShareIdentity));
        if (ServiceManagerFactory.getInstance().getSLSServiceManager().setPrivacySettings(new PrivacySettingsResult((ArrayList)localObject))) {
          break;
        }
        return false;
      }
      return ServiceManagerFactory.getInstance().getSLSServiceManager().setFriendFinderOptInStatus(LinkedAccountHelpers.LinkedAccountType.Phone, OptInStatus.OptedIn);
    }
    
    private boolean verifyPhoneNumberSucceeded()
      throws XLEException
    {
      boolean bool2 = false;
      String str = PhoneContactInfo.getInstance().getRegionWithCode();
      Object localObject2 = PhoneContactInfo.getInstance().getUserEnteredNumber();
      Object localObject1;
      int i;
      if (JavaUtil.isNullOrEmpty(str))
      {
        localObject1 = localObject2;
        if (!((String)localObject2).startsWith("+")) {
          localObject1 = "+" + (String)localObject2;
        }
        localObject2 = new ShortCircuitProfileMessage.ShortCircuitProfileRequest(ShortCircuitProfileMessage.MsgType.PhoneVerification, (String)localObject1, str, this.verificationToken);
        localObject2 = ServiceManagerFactory.getInstance().getSLSServiceManager().sendShortCircuitProfile((ShortCircuitProfileMessage.ShortCircuitProfileRequest)localObject2);
        if ((((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error != null) && ((((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error.code == null) || (!((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error.code.equalsIgnoreCase("PhoneAlreadyVerified:PhoneToVerifyTokenAlreadyVerified")))) {
          break label204;
        }
        i = 1;
      }
      for (;;)
      {
        label135:
        if ((i == 0) && (JavaUtil.isNullOrEmpty(str)))
        {
          int j = 0;
          int k = 0;
          for (;;)
          {
            if (j < ((String)localObject1).length())
            {
              int m = k;
              if (Character.isDigit(((String)localObject1).charAt(j))) {
                m = k + 1;
              }
              j += 1;
              k = m;
              continue;
              localObject1 = ((String)localObject2).replace("+", "");
              break;
              label204:
              i = 0;
              break label135;
            }
          }
          if (k == 10)
          {
            localObject1 = new ShortCircuitProfileMessage.ShortCircuitProfileRequest(ShortCircuitProfileMessage.MsgType.PhoneVerification, "+1" + ((String)localObject1).replace("+", ""), str, this.verificationToken);
            localObject1 = ServiceManagerFactory.getInstance().getSLSServiceManager().sendShortCircuitProfile((ShortCircuitProfileMessage.ShortCircuitProfileRequest)localObject1);
            if ((((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject1).error == null) || ((((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject1).error.code != null) && (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject1).error.code.equalsIgnoreCase("PhoneAlreadyVerified:PhoneToVerifyTokenAlreadyVerified")))) {
              i = 1;
            }
          }
        }
      }
      for (;;)
      {
        boolean bool1 = bool2;
        if (localObject1 != null)
        {
          bool1 = bool2;
          if (i != 0) {
            bool1 = true;
          }
        }
        return bool1;
        i = 0;
        continue;
        localObject1 = localObject2;
      }
    }
    
    protected boolean checkShouldExecute()
    {
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      try
      {
        if ((verifyPhoneNumberSucceeded()) && (updateOptInSucceeded()))
        {
          UTCFriendFinder.trackPhoneContactsLinkSuccess(FriendFinderVerifyCodeScreenViewModel.this.getScreen().getName());
          AsyncActionStatus localAsyncActionStatus = AsyncActionStatus.SUCCESS;
          return localAsyncActionStatus;
        }
      }
      catch (XLEException localXLEException) {}
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction() {}
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      FriendFinderVerifyCodeScreenViewModel.this.onUpdateShortCircuitProfileCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      FriendFinderVerifyCodeScreenViewModel.access$202(FriendFinderVerifyCodeScreenViewModel.this, true);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderVerifyCodeScreenViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */