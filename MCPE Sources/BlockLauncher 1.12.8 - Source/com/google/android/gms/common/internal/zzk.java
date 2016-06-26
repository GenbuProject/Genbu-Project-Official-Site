package com.google.android.gms.common.internal;

import android.os.Bundle;
import android.os.Handler;
import android.os.Handler.Callback;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient.ConnectionCallbacks;
import com.google.android.gms.common.api.GoogleApiClient.OnConnectionFailedListener;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.concurrent.atomic.AtomicInteger;

public final class zzk
  implements Handler.Callback
{
  private final Handler mHandler;
  private final zza zzalQ;
  private final ArrayList<GoogleApiClient.ConnectionCallbacks> zzalR = new ArrayList();
  final ArrayList<GoogleApiClient.ConnectionCallbacks> zzalS = new ArrayList();
  private final ArrayList<GoogleApiClient.OnConnectionFailedListener> zzalT = new ArrayList();
  private volatile boolean zzalU = false;
  private final AtomicInteger zzalV = new AtomicInteger(0);
  private boolean zzalW = false;
  private final Object zzpV = new Object();
  
  public zzk(Looper paramLooper, zza paramzza)
  {
    this.zzalQ = paramzza;
    this.mHandler = new Handler(paramLooper, this);
  }
  
  public boolean handleMessage(Message arg1)
  {
    if (???.what == 1)
    {
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)???.obj;
      synchronized (this.zzpV)
      {
        if ((this.zzalU) && (this.zzalQ.isConnected()) && (this.zzalR.contains(localConnectionCallbacks))) {
          localConnectionCallbacks.onConnected(this.zzalQ.zzoi());
        }
        return true;
      }
    }
    Log.wtf("GmsClientEvents", "Don't know how to handle message: " + ???.what, new Exception());
    return false;
  }
  
  public boolean isConnectionCallbacksRegistered(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzx.zzz(paramConnectionCallbacks);
    synchronized (this.zzpV)
    {
      boolean bool = this.zzalR.contains(paramConnectionCallbacks);
      return bool;
    }
  }
  
  public boolean isConnectionFailedListenerRegistered(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzz(paramOnConnectionFailedListener);
    synchronized (this.zzpV)
    {
      boolean bool = this.zzalT.contains(paramOnConnectionFailedListener);
      return bool;
    }
  }
  
  public void registerConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzx.zzz(paramConnectionCallbacks);
    synchronized (this.zzpV)
    {
      if (this.zzalR.contains(paramConnectionCallbacks))
      {
        Log.w("GmsClientEvents", "registerConnectionCallbacks(): listener " + paramConnectionCallbacks + " is already registered");
        if (this.zzalQ.isConnected()) {
          this.mHandler.sendMessage(this.mHandler.obtainMessage(1, paramConnectionCallbacks));
        }
        return;
      }
      this.zzalR.add(paramConnectionCallbacks);
    }
  }
  
  public void registerConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzz(paramOnConnectionFailedListener);
    synchronized (this.zzpV)
    {
      if (this.zzalT.contains(paramOnConnectionFailedListener))
      {
        Log.w("GmsClientEvents", "registerConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " is already registered");
        return;
      }
      this.zzalT.add(paramOnConnectionFailedListener);
    }
  }
  
  public void unregisterConnectionCallbacks(GoogleApiClient.ConnectionCallbacks paramConnectionCallbacks)
  {
    zzx.zzz(paramConnectionCallbacks);
    synchronized (this.zzpV)
    {
      if (!this.zzalR.remove(paramConnectionCallbacks)) {
        Log.w("GmsClientEvents", "unregisterConnectionCallbacks(): listener " + paramConnectionCallbacks + " not found");
      }
      while (!this.zzalW) {
        return;
      }
      this.zzalS.add(paramConnectionCallbacks);
    }
  }
  
  public void unregisterConnectionFailedListener(GoogleApiClient.OnConnectionFailedListener paramOnConnectionFailedListener)
  {
    zzx.zzz(paramOnConnectionFailedListener);
    synchronized (this.zzpV)
    {
      if (!this.zzalT.remove(paramOnConnectionFailedListener)) {
        Log.w("GmsClientEvents", "unregisterConnectionFailedListener(): listener " + paramOnConnectionFailedListener + " not found");
      }
      return;
    }
  }
  
  public void zzbT(int paramInt)
  {
    boolean bool = false;
    if (Looper.myLooper() == this.mHandler.getLooper()) {
      bool = true;
    }
    zzx.zza(bool, "onUnintentionalDisconnection must only be called on the Handler thread");
    this.mHandler.removeMessages(1);
    synchronized (this.zzpV)
    {
      this.zzalW = true;
      Object localObject2 = new ArrayList(this.zzalR);
      int i = this.zzalV.get();
      localObject2 = ((ArrayList)localObject2).iterator();
      GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
      do
      {
        if (((Iterator)localObject2).hasNext())
        {
          localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)((Iterator)localObject2).next();
          if ((this.zzalU) && (this.zzalV.get() == i)) {}
        }
        else
        {
          this.zzalS.clear();
          this.zzalW = false;
          return;
        }
      } while (!this.zzalR.contains(localConnectionCallbacks));
      localConnectionCallbacks.onConnectionSuspended(paramInt);
    }
  }
  
  public void zzk(Bundle paramBundle)
  {
    boolean bool2 = true;
    boolean bool1;
    if (Looper.myLooper() == this.mHandler.getLooper())
    {
      bool1 = true;
      zzx.zza(bool1, "onConnectionSuccess must only be called on the Handler thread");
    }
    for (;;)
    {
      synchronized (this.zzpV)
      {
        if (this.zzalW) {
          break label206;
        }
        bool1 = true;
        zzx.zzab(bool1);
        this.mHandler.removeMessages(1);
        this.zzalW = true;
        if (this.zzalS.size() != 0) {
          break label211;
        }
        bool1 = bool2;
        zzx.zzab(bool1);
        Object localObject2 = new ArrayList(this.zzalR);
        int i = this.zzalV.get();
        localObject2 = ((ArrayList)localObject2).iterator();
        GoogleApiClient.ConnectionCallbacks localConnectionCallbacks;
        if (((Iterator)localObject2).hasNext())
        {
          localConnectionCallbacks = (GoogleApiClient.ConnectionCallbacks)((Iterator)localObject2).next();
          if ((this.zzalU) && (this.zzalQ.isConnected()) && (this.zzalV.get() == i)) {}
        }
        else
        {
          this.zzalS.clear();
          this.zzalW = false;
          return;
        }
        if (this.zzalS.contains(localConnectionCallbacks)) {
          continue;
        }
        localConnectionCallbacks.onConnected(paramBundle);
      }
      bool1 = false;
      break;
      label206:
      bool1 = false;
      continue;
      label211:
      bool1 = false;
    }
  }
  
  public void zzk(ConnectionResult paramConnectionResult)
  {
    if (Looper.myLooper() == this.mHandler.getLooper()) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "onConnectionFailure must only be called on the Handler thread");
      this.mHandler.removeMessages(1);
      synchronized (this.zzpV)
      {
        Object localObject2 = new ArrayList(this.zzalT);
        int i = this.zzalV.get();
        localObject2 = ((ArrayList)localObject2).iterator();
        while (((Iterator)localObject2).hasNext())
        {
          GoogleApiClient.OnConnectionFailedListener localOnConnectionFailedListener = (GoogleApiClient.OnConnectionFailedListener)((Iterator)localObject2).next();
          if ((!this.zzalU) || (this.zzalV.get() != i)) {
            return;
          }
          if (this.zzalT.contains(localOnConnectionFailedListener)) {
            localOnConnectionFailedListener.onConnectionFailed(paramConnectionResult);
          }
        }
      }
      return;
    }
  }
  
  public void zzqQ()
  {
    this.zzalU = false;
    this.zzalV.incrementAndGet();
  }
  
  public void zzqR()
  {
    this.zzalU = true;
  }
  
  public static abstract interface zza
  {
    public abstract boolean isConnected();
    
    public abstract Bundle zzoi();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\common\internal\zzk.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */