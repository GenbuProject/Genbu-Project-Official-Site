package com.google.android.gms.common.api;

public abstract class OptionalPendingResult<R extends Result>
  extends PendingResult<R>
{
  public abstract R get();
  
  public abstract boolean isDone();
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\api\OptionalPendingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */