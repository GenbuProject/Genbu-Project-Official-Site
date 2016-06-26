package com.google.android.gms.wearable.internal;

import android.content.IntentFilter;
import android.os.RemoteException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.common.api.internal.zza.zzb;
import com.google.android.gms.common.api.internal.zzq;
import com.google.android.gms.wearable.Node;
import com.google.android.gms.wearable.NodeApi;
import com.google.android.gms.wearable.NodeApi.GetConnectedNodesResult;
import com.google.android.gms.wearable.NodeApi.GetLocalNodeResult;
import com.google.android.gms.wearable.NodeApi.NodeListener;
import java.util.ArrayList;
import java.util.List;

public final class zzbb
  implements NodeApi
{
  private static zzb.zza<NodeApi.NodeListener> zza(IntentFilter[] paramArrayOfIntentFilter)
  {
    new zzb.zza()
    {
      public void zza(zzbp paramAnonymouszzbp, zza.zzb<Status> paramAnonymouszzb, NodeApi.NodeListener paramAnonymousNodeListener, zzq<NodeApi.NodeListener> paramAnonymouszzq)
        throws RemoteException
      {
        paramAnonymouszzbp.zza(paramAnonymouszzb, paramAnonymousNodeListener, paramAnonymouszzq, this.zzbrP);
      }
    };
  }
  
  public PendingResult<Status> addListener(GoogleApiClient paramGoogleApiClient, NodeApi.NodeListener paramNodeListener)
  {
    return zzb.zza(paramGoogleApiClient, zza(new IntentFilter[] { zzbn.zzgM("com.google.android.gms.wearable.NODE_CHANGED") }), paramNodeListener);
  }
  
  public PendingResult<NodeApi.GetConnectedNodesResult> getConnectedNodes(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zzt(this);
      }
      
      protected NodeApi.GetConnectedNodesResult zzbB(Status paramAnonymousStatus)
      {
        return new zzbb.zza(paramAnonymousStatus, new ArrayList());
      }
    });
  }
  
  public PendingResult<NodeApi.GetLocalNodeResult> getLocalNode(GoogleApiClient paramGoogleApiClient)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zzs(this);
      }
      
      protected NodeApi.GetLocalNodeResult zzbA(Status paramAnonymousStatus)
      {
        return new zzbb.zzb(paramAnonymousStatus, null);
      }
    });
  }
  
  public PendingResult<Status> removeListener(GoogleApiClient paramGoogleApiClient, final NodeApi.NodeListener paramNodeListener)
  {
    paramGoogleApiClient.zza(new zzi(paramGoogleApiClient)
    {
      protected void zza(zzbp paramAnonymouszzbp)
        throws RemoteException
      {
        paramAnonymouszzbp.zza(this, paramNodeListener);
      }
      
      public Status zzb(Status paramAnonymousStatus)
      {
        return paramAnonymousStatus;
      }
    });
  }
  
  public static class zza
    implements NodeApi.GetConnectedNodesResult
  {
    private final Status zzUX;
    private final List<Node> zzbsW;
    
    public zza(Status paramStatus, List<Node> paramList)
    {
      this.zzUX = paramStatus;
      this.zzbsW = paramList;
    }
    
    public List<Node> getNodes()
    {
      return this.zzbsW;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
  
  public static class zzb
    implements NodeApi.GetLocalNodeResult
  {
    private final Status zzUX;
    private final Node zzbsX;
    
    public zzb(Status paramStatus, Node paramNode)
    {
      this.zzUX = paramStatus;
      this.zzbsX = paramNode;
    }
    
    public Node getNode()
    {
      return this.zzbsX;
    }
    
    public Status getStatus()
    {
      return this.zzUX;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\wearable\internal\zzbb.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */