package com.microsoft.onlineid.internal;

import android.app.PendingIntent;
import com.microsoft.onlineid.internal.exception.UserCancelledException;
import com.microsoft.onlineid.internal.sso.client.SsoResponse;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public abstract class BlockingApiRequestResultReceiver<ResultType>
  extends ApiRequestResultReceiver
{
  private final BlockingQueue<BlockingApiRequestResultReceiver<ResultType>.Result> _queue = new LinkedBlockingQueue();
  
  public BlockingApiRequestResultReceiver()
  {
    super(null);
  }
  
  public SsoResponse<ResultType> blockForResult()
    throws Exception
  {
    Result localResult = (Result)this._queue.take();
    if (localResult == null) {
      throw new IllegalStateException("Expect a result to be available.");
    }
    if (localResult.getException() != null) {
      throw localResult.getException();
    }
    return localResult.getSsoResponse();
  }
  
  protected void onFailure(Exception paramException)
  {
    this._queue.add(new Result(paramException, null));
  }
  
  protected void onUINeeded(PendingIntent paramPendingIntent)
  {
    this._queue.add(new Result(paramPendingIntent, null));
  }
  
  protected void onUserCancel()
  {
    this._queue.add(new Result(new UserCancelledException(), null));
  }
  
  protected void setResult(ResultType paramResultType)
  {
    this._queue.add(new Result(paramResultType, null));
  }
  
  public class Result
  {
    private final Exception _exception;
    private final SsoResponse<ResultType> _result;
    
    private Result(PendingIntent paramPendingIntent)
    {
      this._result = new SsoResponse().setPendingIntent(paramPendingIntent);
      this._exception = null;
    }
    
    private Result(Exception paramException)
    {
      this._result = null;
      this._exception = paramException;
    }
    
    private Result()
    {
      Object localObject;
      this._result = new SsoResponse().setData(localObject);
      this._exception = null;
    }
    
    private Exception getException()
    {
      return this._exception;
    }
    
    public SsoResponse<ResultType> getSsoResponse()
    {
      return this._result;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\microsoft\onlineid\internal\BlockingApiRequestResultReceiver.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */