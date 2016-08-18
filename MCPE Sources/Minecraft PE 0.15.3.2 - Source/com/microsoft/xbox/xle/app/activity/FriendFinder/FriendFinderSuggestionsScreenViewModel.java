package com.microsoft.xbox.xle.app.activity.FriendFinder;

import android.content.res.Resources;
import com.microsoft.xbox.service.model.ProfileModel;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderSuggestionModel;
import com.microsoft.xbox.service.model.friendfinder.FriendFinderType;
import com.microsoft.xbox.service.model.sls.FavoriteListRequest;
import com.microsoft.xbox.service.network.managers.AddFollowingUserResponseContainer.AddFollowingUserResponse;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPeopleSummary;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubPersonSummary;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.PeopleHubRecommendation;
import com.microsoft.xbox.service.network.managers.IPeopleHubResult.RecommendationType;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
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
import com.microsoft.xbox.xle.app.adapter.FriendFinderSuggestionsScreenAdapter;
import com.microsoft.xbox.xle.telemetry.helpers.UTCFriendFinder;
import com.microsoft.xbox.xle.viewmodel.ViewModelBase;
import com.microsoft.xboxtcui.R.string;
import com.microsoft.xboxtcui.XboxTcuiSdk;
import java.util.ArrayList;
import java.util.Iterator;

public class FriendFinderSuggestionsScreenViewModel
  extends ViewModelBase
{
  private AddSuggestionsAsyncTask addSuggestionsAsyncTask;
  private ArrayList<IPeopleHubResult.PeopleHubPersonSummary> foundPeople = new ArrayList(0);
  private FriendFinderType friendFinderType;
  private GetPeopleHubRecommendationsAsyncTask getPeopleHubRecommendationsAsyncTask;
  private boolean isAddingSuggestions;
  private boolean isLoadingRecommendations;
  private ProfileModel meProfileModel;
  
  public FriendFinderSuggestionsScreenViewModel(ScreenLayout paramScreenLayout)
  {
    super(paramScreenLayout);
    this.adapter = new FriendFinderSuggestionsScreenAdapter(this);
  }
  
  private void cancelActiveTasks()
  {
    if (this.getPeopleHubRecommendationsAsyncTask != null) {
      this.getPeopleHubRecommendationsAsyncTask.cancel();
    }
    if (this.addSuggestionsAsyncTask != null) {
      this.addSuggestionsAsyncTask.cancel();
    }
  }
  
  private String getNameOrGamertagAtIndex(int paramInt)
  {
    String str2 = "";
    Object localObject = str2;
    if (paramInt < this.foundPeople.size())
    {
      IPeopleHubResult.PeopleHubPersonSummary localPeopleHubPersonSummary = (IPeopleHubResult.PeopleHubPersonSummary)this.foundPeople.get(paramInt);
      String str1 = str2;
      if (localPeopleHubPersonSummary.recommendation != null)
      {
        str1 = str2;
        if (localPeopleHubPersonSummary.recommendation.Reasons != null)
        {
          str1 = str2;
          if (localPeopleHubPersonSummary.recommendation.Reasons.size() > 0) {
            str1 = (String)localPeopleHubPersonSummary.recommendation.Reasons.get(0);
          }
        }
      }
      localObject = str1;
      if (JavaUtil.isNullOrEmpty(str1)) {
        localObject = localPeopleHubPersonSummary.gamertag;
      }
    }
    return (String)localObject;
  }
  
  private void navigateToFacebookInvite()
  {
    ActivityParameters localActivityParameters = new ActivityParameters();
    localActivityParameters.putFriendFinderType(FriendFinderType.FACEBOOK);
    try
    {
      NavigationManager.getInstance().PushScreen(FriendFinderInviteScreen.class, localActivityParameters);
      return;
    }
    catch (XLEException localXLEException) {}
  }
  
  private void navigateToInvite()
  {
    if (this.friendFinderType == FriendFinderType.FACEBOOK)
    {
      navigateToFacebookInvite();
      return;
    }
    navigateToPhoneInvite();
  }
  
  private void navigateToPhoneInvite()
  {
    ActivityParameters localActivityParameters = new ActivityParameters();
    localActivityParameters.putFriendFinderDone(true);
    try
    {
      NavigationManager.getInstance().PushScreen(FriendFinderHomeScreen.class, localActivityParameters);
      return;
    }
    catch (XLEException localXLEException) {}
  }
  
  private void onAddSuggestionsCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    switch (paramAsyncActionStatus)
    {
    default: 
      return;
    case ???: 
    case ???: 
    case ???: 
      navigateToInvite();
      return;
    }
    showError(R.string.Service_ErrorText);
  }
  
  private void onGetPeopleHubRecommendationsAsyncTaskCompleted(AsyncActionStatus paramAsyncActionStatus)
  {
    this.isLoadingRecommendations = false;
    switch (paramAsyncActionStatus)
    {
    }
    for (;;)
    {
      updateAdapter();
      return;
      updateFoundPeople();
      continue;
      showError(R.string.Service_ErrorText);
    }
  }
  
  private void updateFoundPeople()
  {
    if (this.friendFinderType == FriendFinderType.FACEBOOK) {}
    for (IPeopleHubResult.RecommendationType localRecommendationType = IPeopleHubResult.RecommendationType.FacebookFriend;; localRecommendationType = IPeopleHubResult.RecommendationType.PhoneContact)
    {
      Object localObject = this.meProfileModel.getPeopleHubRecommendationsRawData();
      this.foundPeople = new ArrayList();
      if (localObject == null) {
        break;
      }
      localObject = ((IPeopleHubResult.PeopleHubPeopleSummary)localObject).people.iterator();
      while (((Iterator)localObject).hasNext())
      {
        IPeopleHubResult.PeopleHubPersonSummary localPeopleHubPersonSummary = (IPeopleHubResult.PeopleHubPersonSummary)((Iterator)localObject).next();
        if (localPeopleHubPersonSummary.recommendation.getRecommendationType() == localRecommendationType) {
          this.foundPeople.add(localPeopleHubPersonSummary);
        }
      }
    }
  }
  
  public void addSuggestions(ArrayList<Integer> paramArrayList)
  {
    cancelActiveTasks();
    ArrayList localArrayList = new ArrayList(paramArrayList.size());
    paramArrayList = paramArrayList.iterator();
    if (paramArrayList.hasNext())
    {
      Integer localInteger = (Integer)paramArrayList.next();
      if (localInteger.intValue() < this.foundPeople.size()) {}
      for (boolean bool = true;; bool = false)
      {
        XLEAssert.assertTrue(bool);
        if (localInteger.intValue() >= this.foundPeople.size()) {
          break;
        }
        localArrayList.add(((IPeopleHubResult.PeopleHubPersonSummary)this.foundPeople.get(localInteger.intValue())).xuid);
        break;
      }
    }
    switch (1.$SwitchMap$com$microsoft$xbox$service$model$friendfinder$FriendFinderType[this.friendFinderType.ordinal()])
    {
    }
    for (;;)
    {
      this.addSuggestionsAsyncTask = new AddSuggestionsAsyncTask(localArrayList);
      this.addSuggestionsAsyncTask.load(true);
      return;
      UTCFriendFinder.trackPhoneContactsAddFriends(getScreen().getName(), (String[])localArrayList.toArray(new String[localArrayList.size()]));
      continue;
      UTCFriendFinder.trackAddFacebookFriend(getScreen().getName(), (String[])localArrayList.toArray(new String[localArrayList.size()]));
    }
  }
  
  public String getSubtitle()
  {
    if (this.foundPeople.size() == 0)
    {
      String str2 = XboxTcuiSdk.getResources().getString(R.string.FriendFinder_Facebook_Upsell_Description_NoFriends_LineOne);
      String str1 = str2;
      if (this.friendFinderType == FriendFinderType.FACEBOOK) {
        str1 = str2 + "\n\n" + XboxTcuiSdk.getResources().getString(R.string.FriendFinder_Facebook_Upsell_Description_Default_LineTwo);
      }
      return str1;
    }
    return XboxTcuiSdk.getResources().getString(R.string.FriendFinder_Found_Subtitle);
  }
  
  public ArrayList<FriendFinderSuggestionModel> getSuggestions()
  {
    ArrayList localArrayList = new ArrayList(this.foundPeople.size());
    Iterator localIterator = this.foundPeople.iterator();
    while (localIterator.hasNext()) {
      localArrayList.add(FriendFinderSuggestionModel.fromPeopleHubSummary((IPeopleHubResult.PeopleHubPersonSummary)localIterator.next()));
    }
    return localArrayList;
  }
  
  public String getTitle()
  {
    switch (this.foundPeople.size())
    {
    default: 
      return String.format(XboxTcuiSdk.getResources().getText(R.string.FriendFinder_Facebook_Upsell_Title_ManyFriends_Android).toString(), new Object[] { getNameOrGamertagAtIndex(0), getNameOrGamertagAtIndex(1), Integer.valueOf(this.foundPeople.size() - 2) });
    case 0: 
      return XboxTcuiSdk.getResources().getText(R.string.FriendFinder_Facebook_Upsell_Title_NoFriends).toString();
    case 1: 
      return String.format(XboxTcuiSdk.getResources().getText(R.string.FriendFinder_Facebook_Upsell_Title_OneFriend_Android).toString(), new Object[] { getNameOrGamertagAtIndex(0) });
    case 2: 
      return String.format(XboxTcuiSdk.getResources().getText(R.string.FriendFinder_Facebook_Upsell_Title_TwoFriends_Android).toString(), new Object[] { getNameOrGamertagAtIndex(0), getNameOrGamertagAtIndex(1) });
    }
    return String.format(XboxTcuiSdk.getResources().getText(R.string.FriendFinder_Facebook_Upsell_Title_ThreeFriends_Android).toString(), new Object[] { getNameOrGamertagAtIndex(0), getNameOrGamertagAtIndex(1), getNameOrGamertagAtIndex(2) });
  }
  
  public boolean isBusy()
  {
    return (this.isLoadingRecommendations) || (this.isAddingSuggestions);
  }
  
  public void load(boolean paramBoolean)
  {
    cancelActiveTasks();
    this.getPeopleHubRecommendationsAsyncTask = new GetPeopleHubRecommendationsAsyncTask(null);
    this.getPeopleHubRecommendationsAsyncTask.load(true);
  }
  
  public void navigateToSkip()
  {
    switch (1.$SwitchMap$com$microsoft$xbox$service$model$friendfinder$FriendFinderType[this.friendFinderType.ordinal()])
    {
    }
    for (;;)
    {
      navigateToInvite();
      return;
      UTCFriendFinder.trackPhoneContactsSkipAddFriends(getScreen().getName());
      continue;
      UTCFriendFinder.trackAddFacebookFriendCancel(getScreen().getName());
    }
  }
  
  public boolean onBackButtonPressed()
  {
    UTCFriendFinder.trackBackButtonPressed(getScreen().getName(), this.friendFinderType);
    return super.onBackButtonPressed();
  }
  
  public void onRehydrate()
  {
    this.adapter = new FriendFinderSuggestionsScreenAdapter(this);
  }
  
  protected void onStartOverride()
  {
    this.friendFinderType = NavigationManager.getInstance().getActivityParameters().getFriendFinderType();
    if (this.friendFinderType != FriendFinderType.UNKNOWN) {}
    for (boolean bool = true;; bool = false)
    {
      XLEAssert.assertTrue(bool);
      this.meProfileModel = ProfileModel.getMeProfileModel();
      XLEAssert.assertNotNull(this.meProfileModel);
      return;
    }
  }
  
  protected void onStopOverride()
  {
    cancelActiveTasks();
  }
  
  private class AddSuggestionsAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private ArrayList<String> xuids;
    
    public AddSuggestionsAsyncTask()
    {
      ArrayList localArrayList;
      this.xuids = localArrayList;
    }
    
    protected boolean checkShouldExecute()
    {
      XLEAssert.assertIsUIThread();
      return this.xuids.size() > 0;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      try
      {
        if (ServiceManagerFactory.getInstance().getSLSServiceManager().addUserToFollowingList(FavoriteListRequest.getFavoriteListRequestBody(new FavoriteListRequest(this.xuids))).getAddFollowingRequestStatus()) {
          return AsyncActionStatus.SUCCESS;
        }
        AsyncActionStatus localAsyncActionStatus = AsyncActionStatus.FAIL;
        return localAsyncActionStatus;
      }
      catch (XLEException localXLEException) {}
      return AsyncActionStatus.FAIL;
    }
    
    protected AsyncActionStatus onError()
    {
      return AsyncActionStatus.FAIL;
    }
    
    protected void onNoAction()
    {
      XLEAssert.assertIsUIThread();
      FriendFinderSuggestionsScreenViewModel.this.onAddSuggestionsCompleted(AsyncActionStatus.NO_CHANGE);
    }
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      FriendFinderSuggestionsScreenViewModel.access$502(FriendFinderSuggestionsScreenViewModel.this, false);
      FriendFinderSuggestionsScreenViewModel.this.onAddSuggestionsCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      XLEAssert.assertIsUIThread();
      FriendFinderSuggestionsScreenViewModel.access$502(FriendFinderSuggestionsScreenViewModel.this, true);
      FriendFinderSuggestionsScreenViewModel.this.updateAdapter();
    }
  }
  
  private class GetPeopleHubRecommendationsAsyncTask
    extends NetworkAsyncTask<AsyncActionStatus>
  {
    private GetPeopleHubRecommendationsAsyncTask() {}
    
    protected boolean checkShouldExecute()
    {
      return false;
    }
    
    protected AsyncActionStatus loadDataInBackground()
    {
      FriendFinderSuggestionsScreenViewModel.this.meProfileModel.loadSync(true);
      return FriendFinderSuggestionsScreenViewModel.this.meProfileModel.loadPeopleHubRecommendations(true).getStatus();
    }
    
    protected AsyncActionStatus onError()
    {
      return null;
    }
    
    protected void onNoAction() {}
    
    protected void onPostExecute(AsyncActionStatus paramAsyncActionStatus)
    {
      FriendFinderSuggestionsScreenViewModel.this.onGetPeopleHubRecommendationsAsyncTaskCompleted(paramAsyncActionStatus);
    }
    
    protected void onPreExecute()
    {
      FriendFinderSuggestionsScreenViewModel.access$202(FriendFinderSuggestionsScreenViewModel.this, true);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\xle\app\activity\FriendFinder\FriendFinderSuggestionsScreenViewModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */