package com.microsoft.xbox.idp.toolkit;

public abstract class LoaderResult<T>
{
  private final T data;
  private final HttpError error;
  
  protected LoaderResult(T paramT, HttpError paramHttpError)
  {
    this.data = paramT;
    this.error = paramHttpError;
  }
  
  public T getData()
  {
    return (T)this.data;
  }
  
  public HttpError getError()
  {
    return this.error;
  }
  
  public boolean hasData()
  {
    return this.data != null;
  }
  
  public boolean hasError()
  {
    return this.error != null;
  }
  
  public abstract boolean isReleased();
  
  public abstract void release();
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\microsoft\xbox\idp\toolkit\LoaderResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */