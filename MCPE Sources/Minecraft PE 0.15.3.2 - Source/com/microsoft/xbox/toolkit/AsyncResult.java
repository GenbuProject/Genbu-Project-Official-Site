package com.microsoft.xbox.toolkit;

public class AsyncResult<T>
{
  private final XLEException exception;
  private final T result;
  private final Object sender;
  private AsyncActionStatus status;
  
  public AsyncResult(T paramT, Object paramObject, XLEException paramXLEException) {}
  
  public AsyncResult(T paramT, Object paramObject, XLEException paramXLEException, AsyncActionStatus paramAsyncActionStatus)
  {
    this.sender = paramObject;
    this.exception = paramXLEException;
    this.result = paramT;
    this.status = paramAsyncActionStatus;
  }
  
  public XLEException getException()
  {
    return this.exception;
  }
  
  public T getResult()
  {
    return (T)this.result;
  }
  
  public Object getSender()
  {
    return this.sender;
  }
  
  public AsyncActionStatus getStatus()
  {
    return this.status;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\toolkit\AsyncResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */