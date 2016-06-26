package com.google.android.gms.wearable;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import java.util.List;

public abstract interface NodeApi
{
  @Deprecated
  public abstract PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, NodeListener paramNodeListener);
  
  public abstract PendingResult<GetConnectedNodesResult> getConnectedNodes(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<GetLocalNodeResult> getLocalNode(GoogleApiClient paramGoogleApiClient);
  
  @Deprecated
  public abstract PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, NodeListener paramNodeListener);
  
  public static abstract interface GetConnectedNodesResult
    extends Result
  {
    public abstract List<Node> getNodes();
  }
  
  public static abstract interface GetLocalNodeResult
    extends Result
  {
    public abstract Node getNode();
  }
  
  @Deprecated
  public static abstract interface NodeListener
  {
    @Deprecated
    public abstract void onPeerConnected(Node paramNode);
    
    @Deprecated
    public abstract void onPeerDisconnected(Node paramNode);
  }
  
  @Deprecated
  public static abstract interface zza
  {
    @Deprecated
    public abstract void onConnectedNodes(List<Node> paramList);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\NodeApi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */