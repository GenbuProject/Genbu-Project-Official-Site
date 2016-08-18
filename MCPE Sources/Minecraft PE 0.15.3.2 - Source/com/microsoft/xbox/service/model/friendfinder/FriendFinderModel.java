package com.microsoft.xbox.service.model.friendfinder;

import com.microsoft.xbox.service.model.ModelBase;
import com.microsoft.xbox.service.model.UpdateData;
import com.microsoft.xbox.service.model.UpdateType;
import com.microsoft.xbox.service.network.managers.ServiceManagerFactory;
import com.microsoft.xbox.service.network.managers.friendfinder.FacebookManager;
import com.microsoft.xbox.service.network.managers.xblshared.ISLSServiceManager;
import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.ThreadManager;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEException;
import com.microsoft.xbox.toolkit.network.IDataLoaderRunnable;

public class FriendFinderModel
  extends ModelBase<FriendFinderState.FriendsFinderStateResult>
{
  private static FriendFinderModel instance = new FriendFinderModel();
  private LoadFailedCallback callback;
  private FriendFinderState.FriendsFinderStateResult result;
  
  public static FriendFinderModel getInstance()
  {
    return instance;
  }
  
  public FriendFinderState.FriendsFinderStateResult getResult()
  {
    return this.result;
  }
  
  public boolean isLoading()
  {
    return this.isLoading;
  }
  
  public void loadAsync(boolean paramBoolean)
  {
    loadInternal(paramBoolean, UpdateType.FriendFinderFacebook, new GetPeopleHubFriendFinderStateResultRunner());
  }
  
  public void loadAsync(boolean paramBoolean, LoadFailedCallback paramLoadFailedCallback)
  {
    this.callback = paramLoadFailedCallback;
    loadAsync(paramBoolean);
  }
  
  public boolean shouldRefresh()
  {
    return shouldRefresh(this.lastRefreshTime);
  }
  
  public void updateWithNewData(AsyncResult<FriendFinderState.FriendsFinderStateResult> paramAsyncResult)
  {
    boolean bool;
    if (Thread.currentThread() == ThreadManager.UIThread)
    {
      bool = true;
      XLEAssert.assertTrue(bool);
      super.updateWithNewData(paramAsyncResult);
      if ((paramAsyncResult.getStatus() != AsyncActionStatus.SUCCESS) || (paramAsyncResult.getResult() == null)) {
        break label91;
      }
      this.result = ((FriendFinderState.FriendsFinderStateResult)paramAsyncResult.getResult());
      FacebookManager.getInstance().setFacebookFriendFinderState(this.result);
      notifyObservers(new AsyncResult(new UpdateData(UpdateType.FriendFinderFacebook, true), this, paramAsyncResult.getException()));
    }
    label91:
    while (this.callback == null)
    {
      return;
      bool = false;
      break;
    }
    this.callback.onFriendFinderLoadFailed();
    this.callback = null;
  }
  
  private class GetPeopleHubFriendFinderStateResultRunner
    extends IDataLoaderRunnable<FriendFinderState.FriendsFinderStateResult>
  {
    public GetPeopleHubFriendFinderStateResultRunner() {}
    
    public FriendFinderState.FriendsFinderStateResult buildData()
      throws XLEException
    {
      return ServiceManagerFactory.getInstance().getSLSServiceManager().getPeopleHubFriendFinderState();
    }
    
    public long getDefaultErrorCode()
    {
      return 11L;
    }
    
    public void onPostExcute(AsyncResult<FriendFinderState.FriendsFinderStateResult> paramAsyncResult)
    {
      FriendFinderModel.this.updateWithNewData(paramAsyncResult);
    }
    
    public void onPreExecute()
    {
      FriendFinderModel.access$002(FriendFinderModel.this, true);
    }
  }
  
  public static abstract interface LoadFailedCallback
  {
    public abstract void onFriendFinderLoadFailed();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\service\model\friendfinder\FriendFinderModel.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */