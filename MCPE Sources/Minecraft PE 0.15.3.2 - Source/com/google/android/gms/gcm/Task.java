package com.google.android.gms.gcm;

import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;
import com.google.android.gms.common.internal.zzx;
import java.util.Iterator;
import java.util.Set;

public abstract class Task
  implements Parcelable
{
  public static final int EXTRAS_LIMIT_BYTES = 10240;
  public static final int NETWORK_STATE_ANY = 2;
  public static final int NETWORK_STATE_CONNECTED = 0;
  public static final int NETWORK_STATE_UNMETERED = 1;
  protected static final long UNINITIALIZED = -1L;
  private final Bundle mExtras;
  private final String mTag;
  private final String zzaMh;
  private final boolean zzaMi;
  private final boolean zzaMj;
  private final int zzaMk;
  private final boolean zzaMl;
  private final zzd zzaMm;
  
  @Deprecated
  Task(Parcel paramParcel)
  {
    Log.e("Task", "Constructing a Task object using a parcel.");
    this.zzaMh = paramParcel.readString();
    this.mTag = paramParcel.readString();
    if (paramParcel.readInt() == 1)
    {
      bool1 = true;
      this.zzaMi = bool1;
      if (paramParcel.readInt() != 1) {
        break label88;
      }
    }
    label88:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      this.zzaMj = bool1;
      this.zzaMk = 2;
      this.zzaMl = false;
      this.zzaMm = zzd.zzaMc;
      this.mExtras = null;
      return;
      bool1 = false;
      break;
    }
  }
  
  Task(Builder paramBuilder)
  {
    this.zzaMh = paramBuilder.gcmTaskService;
    this.mTag = paramBuilder.tag;
    this.zzaMi = paramBuilder.updateCurrent;
    this.zzaMj = paramBuilder.isPersisted;
    this.zzaMk = paramBuilder.requiredNetworkState;
    this.zzaMl = paramBuilder.requiresCharging;
    this.zzaMm = paramBuilder.zzaMn;
    this.mExtras = paramBuilder.extras;
  }
  
  private static boolean zzD(Object paramObject)
  {
    return ((paramObject instanceof Integer)) || ((paramObject instanceof Long)) || ((paramObject instanceof Double)) || ((paramObject instanceof String)) || ((paramObject instanceof Boolean));
  }
  
  public static void zzG(Bundle paramBundle)
  {
    if (paramBundle != null)
    {
      Object localObject = Parcel.obtain();
      paramBundle.writeToParcel((Parcel)localObject, 0);
      int i = ((Parcel)localObject).dataSize();
      if (i > 10240)
      {
        ((Parcel)localObject).recycle();
        throw new IllegalArgumentException("Extras exceeding maximum size(10240 bytes): " + i);
      }
      ((Parcel)localObject).recycle();
      localObject = paramBundle.keySet().iterator();
      while (((Iterator)localObject).hasNext()) {
        if (!zzD(paramBundle.get((String)((Iterator)localObject).next()))) {
          throw new IllegalArgumentException("Only the following extra parameter types are supported: Integer, Long, Double, String, and Boolean. ");
        }
      }
    }
  }
  
  public static void zza(zzd paramzzd)
  {
    if (paramzzd != null)
    {
      int i = paramzzd.zzym();
      if ((i != 1) && (i != 0)) {
        throw new IllegalArgumentException("Must provide a valid RetryPolicy: " + i);
      }
      int j = paramzzd.zzyn();
      int k = paramzzd.zzyo();
      if ((i == 0) && (j < 0)) {
        throw new IllegalArgumentException("InitialBackoffSeconds can't be negative: " + j);
      }
      if ((i == 1) && (j < 10)) {
        throw new IllegalArgumentException("RETRY_POLICY_LINEAR must have an initial backoff at least 10 seconds.");
      }
      if (k < j) {
        throw new IllegalArgumentException("MaximumBackoffSeconds must be greater than InitialBackoffSeconds: " + paramzzd.zzyo());
      }
    }
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public Bundle getExtras()
  {
    return this.mExtras;
  }
  
  public int getRequiredNetwork()
  {
    return this.zzaMk;
  }
  
  public boolean getRequiresCharging()
  {
    return this.zzaMl;
  }
  
  public String getServiceName()
  {
    return this.zzaMh;
  }
  
  public String getTag()
  {
    return this.mTag;
  }
  
  public boolean isPersisted()
  {
    return this.zzaMj;
  }
  
  public boolean isUpdateCurrent()
  {
    return this.zzaMi;
  }
  
  public void toBundle(Bundle paramBundle)
  {
    paramBundle.putString("tag", this.mTag);
    paramBundle.putBoolean("update_current", this.zzaMi);
    paramBundle.putBoolean("persisted", this.zzaMj);
    paramBundle.putString("service", this.zzaMh);
    paramBundle.putInt("requiredNetwork", this.zzaMk);
    paramBundle.putBoolean("requiresCharging", this.zzaMl);
    paramBundle.putBundle("retryStrategy", this.zzaMm.zzF(new Bundle()));
    paramBundle.putBundle("extras", this.mExtras);
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    int i = 1;
    paramParcel.writeString(this.zzaMh);
    paramParcel.writeString(this.mTag);
    if (this.zzaMi)
    {
      paramInt = 1;
      paramParcel.writeInt(paramInt);
      if (!this.zzaMj) {
        break label52;
      }
    }
    label52:
    for (paramInt = i;; paramInt = 0)
    {
      paramParcel.writeInt(paramInt);
      return;
      paramInt = 0;
      break;
    }
  }
  
  public static abstract class Builder
  {
    protected Bundle extras;
    protected String gcmTaskService;
    protected boolean isPersisted;
    protected int requiredNetworkState;
    protected boolean requiresCharging;
    protected String tag;
    protected boolean updateCurrent;
    protected zzd zzaMn = zzd.zzaMc;
    
    public abstract Task build();
    
    protected void checkConditions()
    {
      if (this.gcmTaskService != null) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Must provide an endpoint for this task by calling setService(ComponentName).");
        GcmNetworkManager.zzdT(this.tag);
        Task.zza(this.zzaMn);
        if (this.isPersisted) {
          Task.zzG(this.extras);
        }
        return;
      }
    }
    
    public abstract Builder setExtras(Bundle paramBundle);
    
    public abstract Builder setPersisted(boolean paramBoolean);
    
    public abstract Builder setRequiredNetwork(int paramInt);
    
    public abstract Builder setRequiresCharging(boolean paramBoolean);
    
    public abstract Builder setService(Class<? extends GcmTaskService> paramClass);
    
    public abstract Builder setTag(String paramString);
    
    public abstract Builder setUpdateCurrent(boolean paramBoolean);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\com\google\android\gms\gcm\Task.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */