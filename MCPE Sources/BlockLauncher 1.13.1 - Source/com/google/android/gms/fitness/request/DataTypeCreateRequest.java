package com.google.android.gms.fitness.request;

import android.os.IBinder;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzw.zza;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.fitness.data.Field;
import com.google.android.gms.internal.zzoj;
import com.google.android.gms.internal.zzoj.zza;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class DataTypeCreateRequest
  implements SafeParcelable
{
  public static final Parcelable.Creator<DataTypeCreateRequest> CREATOR = new zzi();
  private final String mName;
  private final int mVersionCode;
  private final zzoj zzaBa;
  private final List<Field> zzawE;
  
  DataTypeCreateRequest(int paramInt, String paramString, List<Field> paramList, IBinder paramIBinder)
  {
    this.mVersionCode = paramInt;
    this.mName = paramString;
    this.zzawE = Collections.unmodifiableList(paramList);
    this.zzaBa = zzoj.zza.zzbE(paramIBinder);
  }
  
  private DataTypeCreateRequest(Builder paramBuilder)
  {
    this(Builder.zza(paramBuilder), Builder.zzb(paramBuilder), null);
  }
  
  public DataTypeCreateRequest(DataTypeCreateRequest paramDataTypeCreateRequest, zzoj paramzzoj)
  {
    this(paramDataTypeCreateRequest.mName, paramDataTypeCreateRequest.zzawE, paramzzoj);
  }
  
  public DataTypeCreateRequest(String paramString, List<Field> paramList, zzoj paramzzoj)
  {
    this.mVersionCode = 3;
    this.mName = paramString;
    this.zzawE = Collections.unmodifiableList(paramList);
    this.zzaBa = paramzzoj;
  }
  
  private boolean zzb(DataTypeCreateRequest paramDataTypeCreateRequest)
  {
    return (zzw.equal(this.mName, paramDataTypeCreateRequest.mName)) && (zzw.equal(this.zzawE, paramDataTypeCreateRequest.zzawE));
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    return (paramObject == this) || (((paramObject instanceof DataTypeCreateRequest)) && (zzb((DataTypeCreateRequest)paramObject)));
  }
  
  public IBinder getCallbackBinder()
  {
    if (this.zzaBa == null) {
      return null;
    }
    return this.zzaBa.asBinder();
  }
  
  public List<Field> getFields()
  {
    return this.zzawE;
  }
  
  public String getName()
  {
    return this.mName;
  }
  
  int getVersionCode()
  {
    return this.mVersionCode;
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.mName, this.zzawE });
  }
  
  public String toString()
  {
    return zzw.zzy(this).zzg("name", this.mName).zzg("fields", this.zzawE).toString();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzi.zza(this, paramParcel, paramInt);
  }
  
  public static class Builder
  {
    private String mName;
    private List<Field> zzawE = new ArrayList();
    
    public Builder addField(Field paramField)
    {
      if (!this.zzawE.contains(paramField)) {
        this.zzawE.add(paramField);
      }
      return this;
    }
    
    public Builder addField(String paramString, int paramInt)
    {
      if ((paramString != null) && (!paramString.isEmpty())) {}
      for (boolean bool = true;; bool = false)
      {
        zzx.zzb(bool, "Invalid name specified");
        return addField(Field.zzn(paramString, paramInt));
      }
    }
    
    public DataTypeCreateRequest build()
    {
      boolean bool2 = true;
      if (this.mName != null)
      {
        bool1 = true;
        zzx.zza(bool1, "Must set the name");
        if (this.zzawE.isEmpty()) {
          break label52;
        }
      }
      label52:
      for (boolean bool1 = bool2;; bool1 = false)
      {
        zzx.zza(bool1, "Must specify the data fields");
        return new DataTypeCreateRequest(this, null);
        bool1 = false;
        break;
      }
    }
    
    public Builder setName(String paramString)
    {
      this.mName = paramString;
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\fitness\request\DataTypeCreateRequest.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */