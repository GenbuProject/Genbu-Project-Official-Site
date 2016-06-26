package com.google.android.gms.nearby.connection;

import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.PendingResult;
import com.google.android.gms.common.api.Result;
import com.google.android.gms.common.api.Status;
import com.google.android.gms.internal.zzmr;
import java.util.List;

public abstract interface Connections
{
  public static final long DURATION_INDEFINITE = 0L;
  public static final int MAX_RELIABLE_MESSAGE_LEN = 4096;
  public static final int MAX_UNRELIABLE_MESSAGE_LEN = 1168;
  public static final List<Integer> zzbba = zzmr.zzc(Integer.valueOf(1), Integer.valueOf(2));
  
  public abstract PendingResult<Status> acceptConnectionRequest(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte, MessageListener paramMessageListener);
  
  public abstract void disconnectFromEndpoint(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract String getLocalDeviceId(GoogleApiClient paramGoogleApiClient);
  
  public abstract String getLocalEndpointId(GoogleApiClient paramGoogleApiClient);
  
  public abstract PendingResult<Status> rejectConnectionRequest(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public abstract PendingResult<Status> sendConnectionRequest(GoogleApiClient paramGoogleApiClient, String paramString1, String paramString2, byte[] paramArrayOfByte, ConnectionResponseCallback paramConnectionResponseCallback, MessageListener paramMessageListener);
  
  public abstract void sendReliableMessage(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte);
  
  public abstract void sendReliableMessage(GoogleApiClient paramGoogleApiClient, List<String> paramList, byte[] paramArrayOfByte);
  
  public abstract void sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, String paramString, byte[] paramArrayOfByte);
  
  public abstract void sendUnreliableMessage(GoogleApiClient paramGoogleApiClient, List<String> paramList, byte[] paramArrayOfByte);
  
  public abstract PendingResult<StartAdvertisingResult> startAdvertising(GoogleApiClient paramGoogleApiClient, String paramString, AppMetadata paramAppMetadata, long paramLong, ConnectionRequestListener paramConnectionRequestListener);
  
  public abstract PendingResult<Status> startDiscovery(GoogleApiClient paramGoogleApiClient, String paramString, long paramLong, EndpointDiscoveryListener paramEndpointDiscoveryListener);
  
  public abstract void stopAdvertising(GoogleApiClient paramGoogleApiClient);
  
  public abstract void stopAllEndpoints(GoogleApiClient paramGoogleApiClient);
  
  public abstract void stopDiscovery(GoogleApiClient paramGoogleApiClient, String paramString);
  
  public static abstract interface ConnectionRequestListener
  {
    public abstract void onConnectionRequest(String paramString1, String paramString2, String paramString3, byte[] paramArrayOfByte);
  }
  
  public static abstract interface ConnectionResponseCallback
  {
    public abstract void onConnectionResponse(String paramString, Status paramStatus, byte[] paramArrayOfByte);
  }
  
  public static abstract interface EndpointDiscoveryListener
  {
    public abstract void onEndpointFound(String paramString1, String paramString2, String paramString3, String paramString4);
    
    public abstract void onEndpointLost(String paramString);
  }
  
  public static abstract interface MessageListener
  {
    public abstract void onDisconnected(String paramString);
    
    public abstract void onMessageReceived(String paramString, byte[] paramArrayOfByte, boolean paramBoolean);
  }
  
  public static abstract interface StartAdvertisingResult
    extends Result
  {
    public abstract String getLocalEndpointName();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\nearby\connection\Connections.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */