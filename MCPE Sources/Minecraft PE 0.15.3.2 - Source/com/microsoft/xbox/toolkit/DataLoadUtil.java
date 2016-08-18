package com.microsoft.xbox.toolkit;

import com.microsoft.xbox.toolkit.network.IDataLoaderRunnable;
import com.microsoft.xbox.xle.app.XLEUtil;
import java.util.Date;

public class DataLoadUtil
{
  public static <T> AsyncResult<T> Load(boolean paramBoolean, long paramLong, Date paramDate, SingleEntryLoadingStatus paramSingleEntryLoadingStatus, IDataLoaderRunnable<T> paramIDataLoaderRunnable)
  {
    XLEAssert.assertNotNull(paramSingleEntryLoadingStatus);
    XLEAssert.assertNotNull(paramIDataLoaderRunnable);
    XLEAssert.assertIsNotUIThread();
    SingleEntryLoadingStatus.WaitResult localWaitResult = paramSingleEntryLoadingStatus.waitForNotLoading();
    if (!localWaitResult.waited)
    {
      if ((XLEUtil.shouldRefresh(paramDate, paramLong)) || (paramBoolean))
      {
        ThreadManager.UIThreadSend(new Runnable()
        {
          public void run()
          {
            this.val$runner.onPreExecute();
          }
        });
        paramDate = null;
        int j = paramIDataLoaderRunnable.getShouldRetryCountOnTokenError();
        int i = 0;
        for (;;)
        {
          if (i <= j) {}
          try
          {
            paramDate = paramIDataLoaderRunnable.buildData();
            postExecute(paramDate, paramIDataLoaderRunnable, null, AsyncActionStatus.SUCCESS);
            paramSingleEntryLoadingStatus.setSuccess();
            paramDate = new AsyncResult(paramDate, paramIDataLoaderRunnable, null, AsyncActionStatus.SUCCESS);
            return paramDate;
          }
          catch (XLEException localXLEException)
          {
            paramDate = localXLEException;
            if (localXLEException.getErrorCode() == 1020L)
            {
              i += 1;
            }
            else
            {
              if (localXLEException.getErrorCode() == 1005L) {}
              paramSingleEntryLoadingStatus.setFailed(paramDate);
              return safeReturnResult(null, paramIDataLoaderRunnable, paramDate, AsyncActionStatus.FAIL);
            }
          }
          catch (Exception paramDate)
          {
            for (;;)
            {
              paramDate = new XLEException(paramIDataLoaderRunnable.getDefaultErrorCode(), paramDate);
            }
          }
        }
      }
      paramSingleEntryLoadingStatus.setSuccess();
      return safeReturnResult(null, paramIDataLoaderRunnable, null, AsyncActionStatus.NO_CHANGE);
    }
    paramDate = localXLEException.error;
    if (paramDate == null) {
      return safeReturnResult(null, paramIDataLoaderRunnable, null, AsyncActionStatus.NO_OP_SUCCESS);
    }
    return safeReturnResult(null, paramIDataLoaderRunnable, paramDate, AsyncActionStatus.NO_OP_FAIL);
  }
  
  public static <T> NetworkAsyncTask StartLoadFromUI(boolean paramBoolean, long paramLong, Date paramDate, final SingleEntryLoadingStatus paramSingleEntryLoadingStatus, final IDataLoaderRunnable<T> paramIDataLoaderRunnable)
  {
    paramDate = new NetworkAsyncTask()
    {
      protected boolean checkShouldExecute()
      {
        return this.forceLoad;
      }
      
      protected T loadDataInBackground()
      {
        return (T)DataLoadUtil.Load(this.forceLoad, this.val$lifetime, paramSingleEntryLoadingStatus, paramIDataLoaderRunnable, this.val$runner).getResult();
      }
      
      protected T onError()
      {
        return null;
      }
      
      protected void onNoAction() {}
      
      protected void onPostExecute(T paramAnonymousT) {}
      
      protected void onPreExecute() {}
    };
    paramDate.execute();
    return paramDate;
  }
  
  private static <T> void postExecute(final T paramT, IDataLoaderRunnable<T> paramIDataLoaderRunnable, final XLEException paramXLEException, final AsyncActionStatus paramAsyncActionStatus)
  {
    ThreadManager.UIThreadSend(new Runnable()
    {
      public void run()
      {
        this.val$runner.onPostExcute(new AsyncResult(paramT, this.val$runner, paramXLEException, paramAsyncActionStatus));
      }
    });
  }
  
  private static <T> AsyncResult<T> safeReturnResult(T paramT, IDataLoaderRunnable<T> paramIDataLoaderRunnable, XLEException paramXLEException, AsyncActionStatus paramAsyncActionStatus)
  {
    postExecute(paramT, paramIDataLoaderRunnable, paramXLEException, paramAsyncActionStatus);
    return new AsyncResult(paramT, paramIDataLoaderRunnable, paramXLEException, paramAsyncActionStatus);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\DataLoadUtil.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */