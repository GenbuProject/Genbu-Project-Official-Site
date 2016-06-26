package com.google.android.gms.appindexing;

import android.app.Activity;
import android.content.Intent;
import android.net.Uri;
import android.view.View;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import java.util.List;

public abstract interface AppIndexApi
{
  @Deprecated
  public abstract ActionResult action(GoogleApiClient paramGoogleApiClient, Action paramAction);
  
  public abstract PendingResult<Status> end(GoogleApiClient paramGoogleApiClient, Action paramAction);
  
  public abstract PendingResult<Status> start(GoogleApiClient paramGoogleApiClient, Action paramAction);
  
  @Deprecated
  public abstract PendingResult<Status> view(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Intent paramIntent, String paramString, Uri paramUri, List<AppIndexingLink> paramList);
  
  @Deprecated
  public abstract PendingResult<Status> view(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Uri paramUri1, String paramString, Uri paramUri2, List<AppIndexingLink> paramList);
  
  @Deprecated
  public abstract PendingResult<Status> viewEnd(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Intent paramIntent);
  
  @Deprecated
  public abstract PendingResult<Status> viewEnd(GoogleApiClient paramGoogleApiClient, Activity paramActivity, Uri paramUri);
  
  @Deprecated
  public static abstract interface ActionResult
  {
    public abstract PendingResult<Status> end(GoogleApiClient paramGoogleApiClient);
    
    public abstract PendingResult<Status> getPendingResult();
  }
  
  @Deprecated
  public static final class AppIndexingLink
  {
    public final Uri appIndexingUrl;
    public final int viewId;
    public final Uri webUrl;
    
    public AppIndexingLink(Uri paramUri1, Uri paramUri2, View paramView)
    {
      this.appIndexingUrl = paramUri1;
      this.webUrl = paramUri2;
      this.viewId = paramView.getId();
    }
    
    public AppIndexingLink(Uri paramUri, View paramView)
    {
      this(paramUri, null, paramView);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\appindexing\AppIndexApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */