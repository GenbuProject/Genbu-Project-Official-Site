package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.os.AsyncTask;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.model.friendfinder.LinkedAccountHelpers.LinkedAccountType;
import com.microsoft.xbox.service.model.friendfinder.OptInStatus;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ErrorReturn;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.MsgType;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.PhoneState;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileRequest;
import com.microsoft.xbox.service.model.friendfinder.ShortCircuitProfileMessage.ShortCircuitProfileResponse;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
import com.microsoft.xbox.service.network.managers.friendfinder.PhoneContactInfo;
import com.microsoft.xbox.service.network.managers.friendfinder.UploadContactsAsyncTask;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.JavaUtil;
import com.microsoft.xbox.toolkit.NetworkAsyncTask;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.ui.ActivityParameters;
import com.microsoft.xbox.toolkit.ui.NavigationManager;
import com.microsoft.xbox.toolkit.ui.ScreenLayout;
import com.microsoft.xbox.xle.app.adapter.FriendFinderAddPhoneScreenAdapter;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.R.string;

public class FriendFinderAddPhoneScreenViewModel
  extends ViewModelBase
{
  private AddShortCircuitProfileAsyncTask addShortCircuitProfileAsyncTask;
  private String currentCountryCode;
  private boolean isAddingProfile;
  private boolean isLoadingInfo;
  private boolean isLoadingMyProfileTask;
  private boolean isUploadingContactsAndOptingIn;
  private LoadInfoAsyncTask loadInfoAsyncTask;
  private LoadMyProfileAsyncTask loadMyProfileAsyncTask;
  private ShortCircuitProfileMessage.PhoneState myPhoneState;
  private ShortCircuitProfileMessage.ShortCircuitProfileResponse myProfile;
  private OptInAsyncTask optInAsyncTask;
  private String simPhoneNumber;
  private UploadContactsAsyncTask uploadContactsAsyncTask;
  
  public FriendFinderAddPhoneScreenViewModel(ScreenLayout paramScreenLayout)
  {
    super(paramScreenLayout);
    this.adapter = new FriendFinderAddPhoneScreenAdapter(this);
  }
  
  private void cancelActiveTasks()
  {
    if (this.addShortCircuitProfileAsyncTask != null)
    {
      this.addShortCircuitProfileAsyncTask.cancel();
      this.addShortCircuitProfileAsyncTask = null;
    }
    if (this.loadInfoAsyncTask != null)
    {
      this.loadInfoAsyncTask.cancel(true);
      this.loadInfoAsyncTask = null;
    }
    if (this.loadMyProfileAsyncTask != null)
    {
      this.loadMyProfileAsyncTask.cancel();
      this.loadMyProfileAsyncTask = null;
    }
    if (this.uploadContactsAsyncTask != null)
    {
      this.uploadContactsAsyncTask.cancel();
      this.uploadContactsAsyncTask = null;
    }
    if (this.optInAsyncTask != null)
    {
      this.optInAsyncTask.cancel();
      this.optInAsyncTask = null;
    }
  }
  
  private boolean needToAddPhoneNumber(String paramString)
  {
    if (this.myProfile != null) {
      this.myPhoneState = this.myProfile.isVerified(paramString);
    }
    return (this.myPhoneState == null) || (!this.myPhoneState.isVerified) || (!this.myPhoneState.hasXboxApplication);
  }
  
  private void onAddShortCircuitProfileCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isAddingProfile = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      try
      {
        NavigationManager.getInstance().PushScreen(FriendFinderVerifyCodeScreen.class);
        return;
      }
      catch (XLEException paramAsyncActionStatus)
      {
        return;
      }
    }
    showError(R.string.Service_ErrorText);
    updateAdapter();
  }
  
  private void onLoadMyProfileCompleted(AsyncActionStatus paramAsyncActionStatus, ShortCircuitProfileMessage.ShortCircuitProfileResponse paramShortCircuitProfileResponse)
  {
    this.isLoadingMyProfileTask = false;
    switch (paramAsyncActionStatus)
    {
    }
    for (;;)
    {
      updateAdapter();
      return;
      this.myProfile = paramShortCircuitProfileResponse;
    }
  }
  
  private void onOptInCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isUploadingContactsAndOptingIn = false;
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      try
      {
        paramAsyncActionStatus = new ActivityParameters();
        paramAsyncActionStatus.putFriendFinderType(FriendFinderType.PHONE);
        NavigationManager.getInstance().PushScreen(FriendFinderSuggestionsScreen.class, paramAsyncActionStatus);
        return;
      }
      catch (XLEException paramAsyncActionStatus)
      {
        return;
      }
    }
    showError(R.string.Service_ErrorText);
    updateAdapter();
  }
  
  public void addPhoneNumber(String paramString)
  {
    if (JavaUtil.isNullOrEmpty(paramString))
    {
      showError(R.string.FriendFinder_PhoneNumberHint);
      return;
    }
    PhoneContactInfo.getInstance().setUserEnteredNumber(paramString);
    if (needToAddPhoneNumber(paramString))
    {
      if (!JavaUtil.isNullOrEmpty(PhoneContactInfo.normalizePhoneNumber(paramString))) {}
      for (int i = 1; i == 0; i = 0)
      {
        showError(R.string.FriendFinder_PhoneVerifyEnterRegionAndPhoneNubmer);
        return;
      }
      cancelActiveTasks();
      this.addShortCircuitProfileAsyncTask = new AddShortCircuitProfileAsyncTask(null);
      this.addShortCircuitProfileAsyncTask.load(true);
    }
    for (;;)
    {
      updateAdapter();
      return;
      if (this.uploadContactsAsyncTask != null)
      {
        this.uploadContactsAsyncTask.cancel();
        this.uploadContactsAsyncTask = null;
      }
      this.uploadContactsAsyncTask = new UploadContactsAsyncTask(null);
      this.uploadContactsAsyncTask.load(true);
      if (this.optInAsyncTask != null)
      {
        this.optInAsyncTask.cancel();
        this.optInAsyncTask = null;
      }
      this.optInAsyncTask = new OptInAsyncTask(null);
      this.optInAsyncTask.load(true);
    }
  }
  
  public String getCurrentCountryCode()
  {
    return this.currentCountryCode;
  }
  
  public String getSimPhoneNumber()
  {
    return this.simPhoneNumber;
  }
  
  public boolean isBusy()
  {
    return (this.isAddingProfile) || (this.isLoadingInfo) || (this.isLoadingMyProfileTask) || (this.isUploadingContactsAndOptingIn);
  }
  
  public void load(boolean paramBoolean)
  {
    cancelActiveTasks();
    this.loadInfoAsyncTask = new LoadInfoAsyncTask(null);
    this.loadInfoAsyncTask.execute(new Void[0]);
    this.loadMyProfileAsyncTask = new LoadMyProfileAsyncTask(null);
    this.loadMyProfileAsyncTask.load(true);
  }
  
  public boolean onBackButtonPressed()
  {
    UTCFriendFinder.trackBackButtonPressed(getScreen().getName(), FriendFinderType.PHONE);
    return super.onBackButtonPressed();
  }
  
  public void onRehydrate()
  {
    this.adapter = new FriendFinderAddPhoneScreenAdapter(this);
  }
  
  protected void onStartOverride() {}
  
  protected void onStopOverride()
  {
    cancelActiveTasks();
  }
  
  private class AddShortCircuitProfileAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private AddShortCircuitProfileAsyncTask() {}
    
    private ShortCircuitProfileMessage.MsgType getAddType()
    {
      if (FriendFinderAddPhoneScreenViewModel.this.myPhoneState == null) {
        return ShortCircuitProfileMessage.MsgType.Add;
      }
      if ((FriendFinderAddPhoneScreenViewModel.this.myPhoneState.isVerified) && (FriendFinderAddPhoneScreenViewModel.this.myPhoneState.hasXboxApplication)) {}
      for (boolean bool = true;; bool = false)
      {
        XLEAssert.assertFalse("Check for these before invoking this task", bool);
        if (!FriendFinderAddPhoneScreenViewModel.this.myPhoneState.isVerified) {
          break;
        }
        return ShortCircuitProfileMessage.MsgType.AddXbox;
      }
      return ShortCircuitProfileMessage.MsgType.Edit;
    }
    
    protected boolean checkShouldExecute()
    {
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      for (;;)
      {
        int i;
        int k;
        try
        {
          String str = PhoneContactInfo.getInstance().getRegionWithCode();
          Object localObject2 = PhoneContactInfo.getInstance().getUserEnteredNumber();
          if (JavaUtil.isNullOrEmpty(str))
          {
            localObject1 = localObject2;
            if (!((String)localObject2).startsWith("+")) {
              localObject1 = "+" + (String)localObject2;
            }
            localObject2 = new ShortCircuitProfileMessage.ShortCircuitProfileRequest(getAddType(), (String)localObject1, str);
            localObject2 = ServiceManagerFactory.getInstance().getSLSServiceManager().sendShortCircuitProfile((ShortCircuitProfileMessage.ShortCircuitProfileRequest)localObject2);
            if ((localObject2 == null) || (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error == null)) {
              break label290;
            }
            if (!JavaUtil.isNullOrEmpty(str)) {
              continue;
            }
            j = 0;
            i = 0;
            if (i < ((String)localObject1).length())
            {
              k = j;
              if (!Character.isDigit(((String)localObject1).charAt(i))) {
                break label294;
              }
              k = j + 1;
              break label294;
            }
          }
          else
          {
            localObject1 = ((String)localObject2).replace("+", "");
            continue;
          }
          if (j == 10)
          {
            localObject1 = new ShortCircuitProfileMessage.ShortCircuitProfileRequest(getAddType(), "+1" + ((String)localObject1).replace("+", ""), str);
            localObject1 = ServiceManagerFactory.getInstance().getSLSServiceManager().sendShortCircuitProfile((ShortCircuitProfileMessage.ShortCircuitProfileRequest)localObject1);
            if ((localObject1 != null) && (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject1).error != null)) {
              return AsyncActionStatus.FAIL;
            }
            return AsyncActionStatus.SUCCESS;
          }
          if ((((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error.code != null) && (((ShortCircuitProfileMessage.ShortCircuitProfileResponse)localObject2).error.code.equalsIgnoreCase("PhoneAlreadyVerified"))) {
            return AsyncActionStatus.SUCCESS;
          }
          Object localObject1 = AsyncActionStatus.FAIL;
          return (AsyncActionStatus)localObject1;
        }
        catch (XLEException localXLEException)
        {
          return AsyncActionStatus.FAIL;
        }
        label290:
        return AsyncActionStatus.SUCCESS;
        label294:
        i += 1;
        int j = k;
      }
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction() {}
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      FriendFinderAddPhoneScreenViewModel.this.onAddShortCircuitProfileCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      FriendFinderAddPhoneScreenViewModel.access$1202(FriendFinderAddPhoneScreenViewModel.this, true);
    }
  }
  
  private class LoadInfoAsyncTask
    extends AsyncTask<Void, Void, Void>
  {
    private LoadInfoAsyncTask() {}
    
    protected Void doInBackground(Void... paramVarArgs)
    {
      paramVarArgs = PhoneContactInfo.getInstance().getRegion();
      PhoneContactInfo.getInstance().getCountryNameFromRegion(paramVarArgs);
      paramVarArgs = PhoneContactInfo.getInstance().getCountryCode();
      if (!JavaUtil.isNullOrEmpty(paramVarArgs)) {
        FriendFinderAddPhoneScreenViewModel.access$502(FriendFinderAddPhoneScreenViewModel.this, paramVarArgs);
      }
      FriendFinderAddPhoneScreenViewModel.access$602(FriendFinderAddPhoneScreenViewModel.this, PhoneContactInfo.getInstance().getPhoneNumberFromSim());
      return null;
    }
    
    protected void onPostExecute(Void paramVoid)
    {
      FriendFinderAddPhoneScreenViewModel.access$402(FriendFinderAddPhoneScreenViewModel.this, false);
      FriendFinderAddPhoneScreenViewModel.this.updateAdapter();
    }
    
    protected void onPreExecute()
    {
      FriendFinderAddPhoneScreenViewModel.access$402(FriendFinderAddPhoneScreenViewModel.this, true);
    }
  }
  
  private class LoadMyProfileAsyncTask
    extends NetworkAsyncTask<AsyncResult<ShortCircuitProfileMessage.ShortCircuitProfileResponse>>
  {
    private LoadMyProfileAsyncTask() {}
    
    protected boolean checkShouldExecute()
    {
      return FriendFinderAddPhoneScreenViewModel.this.myProfile == null;
    }
    
    protected AsyncResult<ShortCircuitProfileMessage.ShortCircuitProfileResponse> loadDataInBackground()
    {
      Object localObject = null;
      try
      {
        ShortCircuitProfileMessage.ShortCircuitProfileResponse localShortCircuitProfileResponse = ServiceManagerFactory.getInstance().getSLSServiceManager().getMyShortCircuitProfile();
        localObject = localShortCircuitProfileResponse;
      }
      catch (XLEException localXLEException)
      {
        for (;;) {}
      }
      return new AsyncResult(localObject, this, null);
    }
    
    protected AsyncResult<ShortCircuitProfileMessage.ShortCircuitProfileResponse> onError()
    {
      return null;
    }
    
    protected void onNoAction()
    {
      FriendFinderAddPhoneScreenViewModel.this.onLoadMyProfileCompleted(AsyncActionStatus.SUCCESS, FriendFinderAddPhoneScreenViewModel.this.myProfile);
    }
    
    protected void onPostExecute(AsyncResult<ShortCircuitProfileMessage.ShortCircuitProfileResponse> paramAsyncResult)
    {
      if (paramAsyncResult != null)
      {
        FriendFinderAddPhoneScreenViewModel.this.onLoadMyProfileCompleted(paramAsyncResult.getStatus(), (ShortCircuitProfileMessage.ShortCircuitProfileResponse)paramAsyncResult.getResult());
        return;
      }
      FriendFinderAddPhoneScreenViewModel.this.onLoadMyProfileCompleted(AsyncActionStatus.FAIL, null);
    }
    
    protected void onPreExecute()
    {
      FriendFinderAddPhoneScreenViewModel.access$1002(FriendFinderAddPhoneScreenViewModel.this, true);
    }
  }
  
  private class OptInAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private OptInAsyncTask() {}
    
    protected boolean checkShouldExecute()
    {
      return true;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      try
      {
        ServiceManagerFactory.getInstance().getSLSServiceManager().setFriendFinderOptInStatus(LinkedAccountHelpers.LinkedAccountType.Phone, OptInStatus.OptedIn);
        AsyncActionStatus localAsyncActionStatus = AsyncActionStatus.SUCCESS;
        return localAsyncActionStatus;
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
      FriendFinderAddPhoneScreenViewModel.this.onOptInCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      FriendFinderAddPhoneScreenViewModel.access$1402(FriendFinderAddPhoneScreenViewModel.this, true);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderAddPhoneScreenViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */