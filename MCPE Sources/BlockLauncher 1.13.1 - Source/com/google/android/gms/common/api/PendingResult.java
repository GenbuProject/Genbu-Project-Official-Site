package com.google.android.gms.common.api;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import java.util.concurrent.TimeUnit;

public abstract class PendingResult<R extends Result>
{
  @NonNull
  public abstract R await();
  
  @NonNull
  public abstract R await(long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  public abstract void cancel();
  
  public abstract boolean isCanceled();
  
  public abstract void setResultCallback(@NonNull ResultCallback<? super R> paramResultCallback);
  
  public abstract void setResultCallback(@NonNull ResultCallback<? super R> paramResultCallback, long paramLong, @NonNull TimeUnit paramTimeUnit);
  
  @NonNull
  public <S extends Result> TransformedResult<S> then(@NonNull ResultTransform<? super R, ? extends S> paramResultTransform)
  {
    throw new UnsupportedOperationException();
  }
  
  public void zza(@NonNull zza paramzza)
  {
    throw new UnsupportedOperationException();
  }
  
  @Nullable
  public Integer zzpa()
  {
    throw new UnsupportedOperationException();
  }
  
  public static abstract interface zza
  {
    public abstract void zzu(Status paramStatus);
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\api\PendingResult.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */