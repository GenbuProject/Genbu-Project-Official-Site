package com.google.android.gms.plus.internal.model.moments;

import android.os.Parcel;
import com.google.android.gms.common.server.response.FastJsonResponse.Field;
import com.google.android.gms.common.server.response.FastSafeParcelableJsonResponse;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public final class MomentEntity
  extends FastSafeParcelableJsonResponse
  implements Moment
{
  public static final zzb CREATOR = new zzb();
  private static final HashMap<String, FastJsonResponse.Field<?, ?>> zzbeM = new HashMap();
  final int mVersionCode;
  String zzJN;
  final Set<Integer> zzbeN;
  String zzbfA;
  ItemScopeEntity zzbfI;
  ItemScopeEntity zzbfJ;
  String zzyv;
  
  static
  {
    zzbeM.put("id", FastJsonResponse.Field.zzl("id", 2));
    zzbeM.put("result", FastJsonResponse.Field.zza("result", 4, ItemScopeEntity.class));
    zzbeM.put("startDate", FastJsonResponse.Field.zzl("startDate", 5));
    zzbeM.put("target", FastJsonResponse.Field.zza("target", 6, ItemScopeEntity.class));
    zzbeM.put("type", FastJsonResponse.Field.zzl("type", 7));
  }
  
  public MomentEntity()
  {
    this.mVersionCode = 1;
    this.zzbeN = new HashSet();
  }
  
  MomentEntity(Set<Integer> paramSet, int paramInt, String paramString1, ItemScopeEntity paramItemScopeEntity1, String paramString2, ItemScopeEntity paramItemScopeEntity2, String paramString3)
  {
    this.zzbeN = paramSet;
    this.mVersionCode = paramInt;
    this.zzyv = paramString1;
    this.zzbfI = paramItemScopeEntity1;
    this.zzbfA = paramString2;
    this.zzbfJ = paramItemScopeEntity2;
    this.zzJN = paramString3;
  }
  
  public MomentEntity(Set<Integer> paramSet, String paramString1, ItemScopeEntity paramItemScopeEntity1, String paramString2, ItemScopeEntity paramItemScopeEntity2, String paramString3)
  {
    this.zzbeN = paramSet;
    this.mVersionCode = 1;
    this.zzyv = paramString1;
    this.zzbfI = paramItemScopeEntity1;
    this.zzbfA = paramString2;
    this.zzbfJ = paramItemScopeEntity2;
    this.zzJN = paramString3;
  }
  
  public int describeContents()
  {
    zzb localzzb = CREATOR;
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (!(paramObject instanceof MomentEntity)) {
      return false;
    }
    if (this == paramObject) {
      return true;
    }
    paramObject = (MomentEntity)paramObject;
    Iterator localIterator = zzbeM.values().iterator();
    while (localIterator.hasNext())
    {
      FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
      if (zza(localField))
      {
        if (((MomentEntity)paramObject).zza(localField))
        {
          if (!zzb(localField).equals(((MomentEntity)paramObject).zzb(localField))) {
            return false;
          }
        }
        else {
          return false;
        }
      }
      else if (((MomentEntity)paramObject).zza(localField)) {
        return false;
      }
    }
    return true;
  }
  
  public String getId()
  {
    return this.zzyv;
  }
  
  public ItemScope getResult()
  {
    return this.zzbfI;
  }
  
  public String getStartDate()
  {
    return this.zzbfA;
  }
  
  public ItemScope getTarget()
  {
    return this.zzbfJ;
  }
  
  public String getType()
  {
    return this.zzJN;
  }
  
  public boolean hasId()
  {
    return this.zzbeN.contains(Integer.valueOf(2));
  }
  
  public boolean hasResult()
  {
    return this.zzbeN.contains(Integer.valueOf(4));
  }
  
  public boolean hasStartDate()
  {
    return this.zzbeN.contains(Integer.valueOf(5));
  }
  
  public boolean hasTarget()
  {
    return this.zzbeN.contains(Integer.valueOf(6));
  }
  
  public boolean hasType()
  {
    return this.zzbeN.contains(Integer.valueOf(7));
  }
  
  public int hashCode()
  {
    Iterator localIterator = zzbeM.values().iterator();
    int i = 0;
    if (localIterator.hasNext())
    {
      FastJsonResponse.Field localField = (FastJsonResponse.Field)localIterator.next();
      if (!zza(localField)) {
        break label68;
      }
      int j = localField.zzrs();
      i = zzb(localField).hashCode() + (i + j);
    }
    label68:
    for (;;)
    {
      break;
      return i;
    }
  }
  
  public boolean isDataValid()
  {
    return true;
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzb localzzb = CREATOR;
    zzb.zza(this, paramParcel, paramInt);
  }
  
  public HashMap<String, FastJsonResponse.Field<?, ?>> zzFl()
  {
    return zzbeM;
  }
  
  public MomentEntity zzFn()
  {
    return this;
  }
  
  protected boolean zza(FastJsonResponse.Field paramField)
  {
    return this.zzbeN.contains(Integer.valueOf(paramField.zzrs()));
  }
  
  protected Object zzb(FastJsonResponse.Field paramField)
  {
    switch (paramField.zzrs())
    {
    case 3: 
    default: 
      throw new IllegalStateException("Unknown safe parcelable id=" + paramField.zzrs());
    case 2: 
      return this.zzyv;
    case 4: 
      return this.zzbfI;
    case 5: 
      return this.zzbfA;
    case 6: 
      return this.zzbfJ;
    }
    return this.zzJN;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\plus\internal\model\moments\MomentEntity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */