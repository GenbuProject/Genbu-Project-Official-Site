package com.google.android.gms.fitness.data;

import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import android.text.TextUtils;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.FitnessActivities;
import java.util.concurrent.TimeUnit;

public class Session
  implements SafeParcelable
{
  public static final Parcelable.Creator<Session> CREATOR = new zzq();
  public static final String EXTRA_SESSION = "vnd.google.fitness.session";
  public static final String MIME_TYPE_PREFIX = "vnd.google.fitness.session/";
  private final String mName;
  private final int mVersionCode;
  private final long zzRD;
  private final long zzavV;
  private final int zzawf;
  private final Application zzaws;
  private final String zzaxk;
  private final String zzaxl;
  private final Long zzaxm;
  
  Session(int paramInt1, long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, int paramInt2, Application paramApplication, Long paramLong)
  {
    this.mVersionCode = paramInt1;
    this.zzRD = paramLong1;
    this.zzavV = paramLong2;
    this.mName = paramString1;
    this.zzaxk = paramString2;
    this.zzaxl = paramString3;
    this.zzawf = paramInt2;
    this.zzaws = paramApplication;
    this.zzaxm = paramLong;
  }
  
  public Session(long paramLong1, long paramLong2, String paramString1, String paramString2, String paramString3, int paramInt, Application paramApplication, Long paramLong)
  {
    this(3, paramLong1, paramLong2, paramString1, paramString2, paramString3, paramInt, paramApplication, paramLong);
  }
  
  private Session(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), Builder.zzc(paramBuilder), Builder.zzd(paramBuilder), Builder.zze(paramBuilder), Builder.zzf(paramBuilder), Builder.zzg(paramBuilder), Builder.zzh(paramBuilder));
  }
  
  public static Session extract(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return (Session)zzc.zza(paramIntent, "vnd.google.fitness.session", CREATOR);
  }
  
  public static String getMimeType(String paramString)
  {
    return "vnd.google.fitness.session/" + paramString;
  }
  
  private boolean zza(Session paramSession)
  {
    return (this.zzRD == paramSession.zzRD) && (this.zzavV == paramSession.zzavV) && (zzw.equal(this.mName, paramSession.mName)) && (zzw.equal(this.zzaxk, paramSession.zzaxk)) && (zzw.equal(this.zzaxl, paramSession.zzaxl)) && (zzw.equal(this.zzaws, paramSession.zzaws)) && (this.zzawf == paramSession.zzawf);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof Session)) && (zza((Session)paramObject)));
  }
  
  public long getActiveTime(TimeUnit paramTimeUnit)
  {
    if (this.zzaxm != null) {}
    for (boolean bool = true;; bool = false)
    {
      zzx.zza(bool, "Active time is not set");
      return paramTimeUnit.convert(this.zzaxm.longValue(), TimeUnit.MILLISECONDS);
    }
  }
  
  public String getActivity()
  {
    return FitnessActivities.getName(this.zzawf);
  }
  
  public String getAppPackageName()
  {
    if (this.zzaws == null) {
      return null;
    }
    return this.zzaws.getPackageName();
  }
  
  public String getDescription()
  {
    return this.zzaxl;
  }
  
  public long getEndTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzavV, TimeUnit.MILLISECONDS);
  }
  
  public String getIdentifier()
  {
    return this.zzaxk;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public long getStartTime(TimeUnit paramTimeUnit)
  {
    return paramTimeUnit.convert(this.zzRD, TimeUnit.MILLISECONDS);
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public boolean hasActiveTime()
  {
    return this.zzaxm != null;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { Long.valueOf(this.zzRD), Long.valueOf(this.zzavV), this.zzaxk });
  }
  
  public boolean isOngoing()
  {
    return this.zzavV == 0L;
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("startTime", Long.valueOf(this.zzRD)).zzg("endTime", Long.valueOf(this.zzavV)).zzg("name", this.mName).zzg("identifier", this.zzaxk).zzg("description", this.zzaxl).zzg("activity", Integer.valueOf(this.zzawf)).zzg("application", this.zzaws).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzq.zza(this, paramParcel, paramInt);
  }
  
  public long zzlO()
  {
    return this.zzRD;
  }
  
  public int zzub()
  {
    return this.zzawf;
  }
  
  public long zzud()
  {
    return this.zzavV;
  }
  
  public Application zzum()
  {
    return this.zzaws;
  }
  
  public Long zzuw()
  {
    return this.zzaxm;
  }
  
  public static class Builder
  {
    private String mName = null;
    private long zzRD = 0L;
    private long zzavV = 0L;
    private int zzawf = 4;
    private Application zzaws;
    private String zzaxk;
    private String zzaxl;
    private Long zzaxm;
    
    public Session build()
    {
      boolean bool2 = false;
      boolean bool1;
      StringBuilder localStringBuilder;
      if (this.zzRD > 0L)
      {
        bool1 = true;
        zzx.zza(bool1, "Start time should be specified.");
        if (this.zzavV != 0L)
        {
          bool1 = bool2;
          if (this.zzavV <= this.zzRD) {}
        }
        else
        {
          bool1 = true;
        }
        zzx.zza(bool1, "End time should be later than start time.");
        if (this.zzaxk == null)
        {
          localStringBuilder = new StringBuilder();
          if (this.mName != null) {
            break label111;
          }
        }
      }
      label111:
      for (String str = "";; str = this.mName)
      {
        this.zzaxk = (str + this.zzRD);
        return new Session(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setActiveTime(long paramLong, TimeUnit paramTimeUnit)
    {
      this.zzaxm = Long.valueOf(paramTimeUnit.toMillis(paramLong));
      return this;
    }
    
    public Builder setActivity(String paramString)
    {
      return zzfa(FitnessActivities.zzdm(paramString));
    }
    
    public Builder setDescription(String paramString)
    {
      if (paramString.length() <= 1000) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Session description cannot exceed %d characters", new Object[] { Integer.valueOf(1000) });
        this.zzaxl = paramString;
        return this;
      }
    }
    
    public Builder setEndTime(long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong >= 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "End time should be positive.");
        this.zzavV = paramTimeUnit.toMillis(paramLong);
        return this;
      }
    }
    
    public Builder setIdentifier(String paramString)
    {
      if ((paramString != null) && (TextUtils.getTrimmedLength(paramString) > 0)) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzac(bool);
        this.zzaxk = paramString;
        return this;
      }
    }
    
    public Builder setName(String paramString)
    {
      if (paramString.length() <= 100) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Session name cannot exceed %d characters", new Object[] { Integer.valueOf(100) });
        this.mName = paramString;
        return this;
      }
    }
    
    public Builder setStartTime(long paramLong, TimeUnit paramTimeUnit)
    {
      if (paramLong > 0L) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zza(bool, "Start time should be positive.");
        this.zzRD = paramTimeUnit.toMillis(paramLong);
        return this;
      }
    }
    
    public Builder zzfa(int paramInt)
    {
      this.zzawf = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\Session.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */