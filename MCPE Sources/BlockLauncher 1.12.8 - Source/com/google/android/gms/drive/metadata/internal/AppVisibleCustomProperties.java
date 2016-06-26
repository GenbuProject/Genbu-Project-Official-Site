package com.google.android.gms.drive.metadata.internal;

import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.metadata.CustomPropertyKey;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public final class AppVisibleCustomProperties
  implements SafeParcelable, Iterable<CustomProperty>
{
  public static final Parcelable.Creator<AppVisibleCustomProperties> CREATOR = new zza();
  public static final AppVisibleCustomProperties zzasK = new zza().zztA();
  final int mVersionCode;
  final List<CustomProperty> zzasL;
  
  AppVisibleCustomProperties(int paramInt, Collection<CustomProperty> paramCollection)
  {
    this.mVersionCode = paramInt;
    zzx.zzz(paramCollection);
    this.zzasL = new ArrayList(paramCollection);
  }
  
  private AppVisibleCustomProperties(Collection<CustomProperty> paramCollection)
  {
    this(1, paramCollection);
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public boolean equals(Object paramObject)
  {
    if (this == paramObject) {
      return true;
    }
    if ((paramObject == null) || (paramObject.getClass() != getClass())) {
      return false;
    }
    return zztz().equals(((AppVisibleCustomProperties)paramObject).zztz());
  }
  
  public int hashCode()
  {
    return zzw.hashCode(new Object[] { this.zzasL });
  }
  
  public Iterator<CustomProperty> iterator()
  {
    return this.zzasL.iterator();
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zza.zza(this, paramParcel, paramInt);
  }
  
  public Map<CustomPropertyKey, String> zztz()
  {
    HashMap localHashMap = new HashMap(this.zzasL.size());
    Iterator localIterator = this.zzasL.iterator();
    while (localIterator.hasNext())
    {
      CustomProperty localCustomProperty = (CustomProperty)localIterator.next();
      localHashMap.put(localCustomProperty.zztB(), localCustomProperty.getValue());
    }
    return Collections.unmodifiableMap(localHashMap);
  }
  
  public static class zza
  {
    private final Map<CustomPropertyKey, CustomProperty> zzasM = new HashMap();
    
    public zza zza(CustomPropertyKey paramCustomPropertyKey, String paramString)
    {
      zzx.zzb(paramCustomPropertyKey, "key");
      this.zzasM.put(paramCustomPropertyKey, new CustomProperty(paramCustomPropertyKey, paramString));
      return this;
    }
    
    public zza zza(CustomProperty paramCustomProperty)
    {
      zzx.zzb(paramCustomProperty, "property");
      this.zzasM.put(paramCustomProperty.zztB(), paramCustomProperty);
      return this;
    }
    
    public AppVisibleCustomProperties zztA()
    {
      return new AppVisibleCustomProperties(this.zzasM.values(), null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\internal\AppVisibleCustomProperties.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */