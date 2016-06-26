package com.google.android.gms.fitness.data;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.safeparcel.zzc;
import com.google.android.gms.common.internal.zzx;

public class DataSource
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataSource> CREATOR = new zzf();
  public static final String EXTRA_DATA_SOURCE = "vnd.google.fitness.data_source";
  public static final int TYPE_DERIVED = 1;
  public static final int TYPE_RAW = 0;
  private final String mName;
  private final int mVersionCode;
  private final int zzabB;
  private final DataType zzavT;
  private final Device zzawr;
  private final Application zzaws;
  private final String zzawt;
  private final String zzawu;
  
  DataSource(int paramInt1, DataType paramDataType, String paramString1, int paramInt2, Device paramDevice, Application paramApplication, String paramString2)
  {
    this.mVersionCode = paramInt1;
    this.zzavT = paramDataType;
    this.zzabB = paramInt2;
    this.mName = paramString1;
    this.zzawr = paramDevice;
    this.zzaws = paramApplication;
    this.zzawt = paramString2;
    this.zzawu = zzun();
  }
  
  private DataSource(Builder paramBuilder)
  {
    this.mVersionCode = 3;
    this.zzavT = Builder.zza(paramBuilder);
    this.zzabB = Builder.zzb(paramBuilder);
    this.mName = Builder.zzc(paramBuilder);
    this.zzawr = Builder.zzd(paramBuilder);
    this.zzaws = Builder.zze(paramBuilder);
    this.zzawt = Builder.zzf(paramBuilder);
    this.zzawu = zzun();
  }
  
  public static DataSource extract(Intent paramIntent)
  {
    if (paramIntent == null) {
      return null;
    }
    return (DataSource)zzc.zza(paramIntent, "vnd.google.fitness.data_source", CREATOR);
  }
  
  private String getTypeString()
  {
    switch (this.zzabB)
    {
    default: 
      throw new IllegalArgumentException("invalid type value");
    case 0: 
      return "raw";
    }
    return "derived";
  }
  
  private boolean zza(DataSource paramDataSource)
  {
    return this.zzawu.equals(paramDataSource.zzawu);
  }
  
  private String zzun()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getTypeString());
    localStringBuilder.append(":").append(this.zzavT.getName());
    if (this.zzaws != null) {
      localStringBuilder.append(":").append(this.zzaws.getPackageName());
    }
    if (this.zzawr != null) {
      localStringBuilder.append(":").append(this.zzawr.getStreamIdentifier());
    }
    if (this.zzawt != null) {
      localStringBuilder.append(":").append(this.zzawt);
    }
    return localStringBuilder.toString();
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (this == paramObject) || (((paramObject instanceof DataSource)) && (zza((DataSource)paramObject)));
  }
  
  public String getAppPackageName()
  {
    if (this.zzaws == null) {
      return null;
    }
    return this.zzaws.getPackageName();
  }
  
  public DataType getDataType()
  {
    return this.zzavT;
  }
  
  public Device getDevice()
  {
    return this.zzawr;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  public String getStreamIdentifier()
  {
    return this.zzawu;
  }
  
  public String getStreamName()
  {
    return this.zzawt;
  }
  
  public int getType()
  {
    return this.zzabB;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return this.zzawu.hashCode();
  }
  
  public String toDebugString()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    if (this.zzabB == 0)
    {
      str = "r";
      localStringBuilder = localStringBuilder.append(str).append(":").append(this.zzavT.zzuo());
      if (this.zzaws != null) {
        break label154;
      }
      str = "";
      label49:
      localStringBuilder = localStringBuilder.append(str);
      if (this.zzawr == null) {
        break label202;
      }
      str = ":" + this.zzawr.getModel() + ":" + this.zzawr.getUid();
      label103:
      localStringBuilder = localStringBuilder.append(str);
      if (this.zzawt == null) {
        break label208;
      }
    }
    label154:
    label202:
    label208:
    for (String str = ":" + this.zzawt;; str = "")
    {
      return str;
      str = "d";
      break;
      if (this.zzaws.equals(Application.zzawa))
      {
        str = ":gms";
        break label49;
      }
      str = ":" + this.zzaws.getPackageName();
      break label49;
      str = "";
      break label103;
    }
  }
  
  public String toString()
  {
    StringBuilder localStringBuilder = new StringBuilder("DataSource{");
    localStringBuilder.append(getTypeString());
    if (this.mName != null) {
      localStringBuilder.append(":").append(this.mName);
    }
    if (this.zzaws != null) {
      localStringBuilder.append(":").append(this.zzaws);
    }
    if (this.zzawr != null) {
      localStringBuilder.append(":").append(this.zzawr);
    }
    if (this.zzawt != null) {
      localStringBuilder.append(":").append(this.zzawt);
    }
    localStringBuilder.append(":").append(this.zzavT);
    return "}";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzf.zza(this, paramParcel, paramInt);
  }
  
  public Application zzum()
  {
    return this.zzaws;
  }
  
  public static final class Builder
  {
    private String mName;
    private int zzabB = -1;
    private DataType zzavT;
    private Device zzawr;
    private Application zzaws;
    private String zzawt = "";
    
    public DataSource build()
    {
      boolean bool2 = true;
      if (this.zzavT != null)
      {
        bool1 = true;
        zzx.zza(bool1, "Must set data type");
        if (this.zzabB < 0) {
          break label47;
        }
      }
      label47:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zza(bool1, "Must set data source type");
        return new DataSource(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setAppPackageName(Context paramContext)
    {
      return setAppPackageName(paramContext.getPackageName());
    }
    
    public Builder setAppPackageName(String paramString)
    {
      this.zzaws = Application.zzdn(paramString);
      return this;
    }
    
    public Builder setDataType(DataType paramDataType)
    {
      this.zzavT = paramDataType;
      return this;
    }
    
    public Builder setDevice(Device paramDevice)
    {
      this.zzawr = paramDevice;
      return this;
    }
    
    public Builder setName(String paramString)
    {
      this.mName = paramString;
      return this;
    }
    
    public Builder setStreamName(String paramString)
    {
      if (paramString != null) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Must specify a valid stream name");
        this.zzawt = paramString;
        return this;
      }
    }
    
    public Builder setType(int paramInt)
    {
      this.zzabB = paramInt;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\fitness\data\DataSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */