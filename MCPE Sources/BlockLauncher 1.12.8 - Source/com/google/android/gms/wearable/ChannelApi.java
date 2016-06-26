package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.lang.annotation.Annotation;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

public abstract interface ChannelApi
{
  public static final String ACTION_CHANNEL_EVENT = "com.google.android.gms.wearable.CHANNEL_EVENT";
  
  public abstract PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, ChannelListener paramChannelListener);
  
  public abstract PendingResult<OpenChannelResult> openChannel(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2);
  
  public abstract PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, ChannelListener paramChannelListener);
  
  public static abstract interface ChannelListener
  {
    public static final int CLOSE_REASON_DISCONNECTED = 1;
    public static final int CLOSE_REASON_LOCAL_CLOSE = 3;
    public static final int CLOSE_REASON_NORMAL = 0;
    public static final int CLOSE_REASON_REMOTE_CLOSE = 2;
    
    public abstract void onChannelClosed(Channel paramChannel, int paramInt1, int paramInt2);
    
    public abstract void onChannelOpened(Channel paramChannel);
    
    public abstract void onInputClosed(Channel paramChannel, int paramInt1, int paramInt2);
    
    public abstract void onOutputClosed(Channel paramChannel, int paramInt1, int paramInt2);
  }
  
  @Retention(RetentionPolicy.SOURCE)
  public static @interface CloseReason {}
  
  public static abstract interface OpenChannelResult
    extends Result
  {
    public abstract Channel getChannel();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\ChannelApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */