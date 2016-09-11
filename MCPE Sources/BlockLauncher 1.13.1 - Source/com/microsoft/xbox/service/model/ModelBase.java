package com.microsoft.xbox.service.model;

import com.microsoft.xbox.toolkit.AsyncActionStatus;
import com.microsoft.xbox.toolkit.AsyncResult;
import com.microsoft.xbox.toolkit.DataLoadUtil;
import com.microsoft.xbox.toolkit.ModelData;
import com.microsoft.xbox.toolkit.SingleEntryLoadingStatus;
import com.microsoft.xbox.toolkit.XLEAssert;
import com.microsoft.xbox.toolkit.XLEObservable;
import com.microsoft.xbox.toolkit.network.IDataLoaderRunnable;
import com.microsoft.xbox.xle.app.XLEUtil;
import java.util.Date;

public abstract class ModelBase<T>
  extends XLEObservable<UpdateData>
  implements ModelData<T>
{
  protected static final long MilliSecondsInADay = 86400000L;
  protected static final long MilliSecondsInAnHour = 3600000L;
  protected static final long MilliSecondsInHalfHour = 1800000L;
  protected boolean isLoading = false;
  protected long lastInvalidatedTick = 0L;
  protected Date lastRefreshTime;
  protected long lifetime = 86400000L;
  protected IDataLoaderRunnable<T> loaderRunnable;
  private SingleEntryLoadingStatus loadingStatus = new SingleEntryLoadingStatus();
  
  public boolean getIsLoading()
  {
    return this.loadingStatus.getIsLoading();
  }
  
  public boolean hasValidData()
  {
    return this.lastRefreshTime != null;
  }
  
  public void invalidateData()
  {
    this.lastRefreshTime = null;
  }
  
  protected boolean isLoaded()
  {
    return this.lastRefreshTime != null;
  }
  
  protected AsyncResult<T> loadData(boolean paramBoolean, IDataLoaderRunnable<T> paramIDataLoaderRunnable)
  {
    XLEAssert.assertIsNotUIThread();
    return DataLoadUtil.Load(paramBoolean, this.lifetime, this.lastRefreshTime, this.loadingStatus, paramIDataLoaderRunnable);
  }
  
  protected void loadInternal(boolean paramBoolean, UpdateType paramUpdateType, IDataLoaderRunnable<T> paramIDataLoaderRunnable)
  {
    loadInternal(paramBoolean, paramUpdateType, paramIDataLoaderRunnable, this.lastRefreshTime);
  }
  
  protected void loadInternal(boolean paramBoolean, UpdateType paramUpdateType, IDataLoaderRunnable<T> paramIDataLoaderRunnable, Date paramDate)
  {
    boolean bool = false;
    XLEAssert.assertIsUIThread();
    if ((!getIsLoading()) && ((paramBoolean) || (shouldRefresh(paramDate))))
    {
      DataLoadUtil.StartLoadFromUI(paramBoolean, this.lifetime, this.lastRefreshTime, this.loadingStatus, paramIDataLoaderRunnable);
      notifyObservers(new AsyncResult(new UpdateData(paramUpdateType, false), this, null));
      return;
    }
    paramBoolean = bool;
    if (!getIsLoading()) {
      paramBoolean = true;
    }
    notifyObservers(new AsyncResult(new UpdateData(paramUpdateType, paramBoolean), this, null));
  }
  
  public boolean shouldRefresh()
  {
    return shouldRefresh(this.lastRefreshTime);
  }
  
  protected boolean shouldRefresh(Date paramDate)
  {
    return XLEUtil.shouldRefresh(paramDate, this.lifetime);
  }
  
  public void updateWithNewData(AsyncResult<T> paramAsyncResult)
  {
    this.isLoading = false;
    if ((paramAsyncResult.getException() == null) && (paramAsyncResult.getStatus() == AsyncActionStatus.SUCCESS)) {
      this.lastRefreshTime = new Date();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\microsoft\xbox\service\model\ModelBase.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */