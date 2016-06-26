package com.google.android.gms.drive.metadata.internal;

import android.content.Context;
import android.os.Bundle;
import android.os.Parcel;
import android.os.Parcelable.Creator;
import com.google.android.gms.common.data.BitmapTeleporter;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import com.google.android.gms.common.internal.zzw;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.internal.zzz;
import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.internal.zznm;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public final class MetadataBundle
  implements SafeParcelable
{
  public static final Parcelable.Creator<MetadataBundle> CREATOR = new zzh();
  final int mVersionCode;
  final Bundle zzasQ;
  
  MetadataBundle(int paramInt, Bundle paramBundle)
  {
    this.mVersionCode = paramInt;
    this.zzasQ = ((Bundle)zzx.zzz(paramBundle));
    this.zzasQ.setClassLoader(getClass().getClassLoader());
    paramBundle = new ArrayList();
    Object localObject = this.zzasQ.keySet().iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (zze.zzdc(str) == null)
      {
        paramBundle.add(str);
        zzz.zzz("MetadataBundle", "Ignored unknown metadata field in bundle: " + str);
      }
    }
    paramBundle = paramBundle.iterator();
    while (paramBundle.hasNext())
    {
      localObject = (String)paramBundle.next();
      this.zzasQ.remove((String)localObject);
    }
  }
  
  private MetadataBundle(Bundle paramBundle)
  {
    this(1, paramBundle);
  }
  
  public static <T> MetadataBundle zzb(MetadataField<T> paramMetadataField, T paramT)
  {
    MetadataBundle localMetadataBundle = zztE();
    localMetadataBundle.zzc(paramMetadataField, paramT);
    return localMetadataBundle;
  }
  
  public static MetadataBundle zztE()
  {
    return new MetadataBundle(new Bundle());
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
    if (!(paramObject instanceof MetadataBundle)) {
      return false;
    }
    paramObject = (MetadataBundle)paramObject;
    Object localObject = this.zzasQ.keySet();
    if (!((Set)localObject).equals(((MetadataBundle)paramObject).zzasQ.keySet())) {
      return false;
    }
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      String str = (String)((Iterator)localObject).next();
      if (!zzw.equal(this.zzasQ.get(str), ((MetadataBundle)paramObject).zzasQ.get(str))) {
        return false;
      }
    }
    return true;
  }
  
  public int hashCode()
  {
    Iterator localIterator = this.zzasQ.keySet().iterator();
    String str;
    for (int i = 1; localIterator.hasNext(); i = this.zzasQ.get(str).hashCode() + i * 31) {
      str = (String)localIterator.next();
    }
    return i;
  }
  
  public void setContext(Context paramContext)
  {
    BitmapTeleporter localBitmapTeleporter = (BitmapTeleporter)zza(zznm.zzatz);
    if (localBitmapTeleporter != null) {
      localBitmapTeleporter.zzc(paramContext.getCacheDir());
    }
  }
  
  public String toString()
  {
    return "MetadataBundle [values=" + this.zzasQ + "]";
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    zzh.zza(this, paramParcel, paramInt);
  }
  
  public <T> T zza(MetadataField<T> paramMetadataField)
  {
    return (T)paramMetadataField.zzm(this.zzasQ);
  }
  
  public <T> void zzc(MetadataField<T> paramMetadataField, T paramT)
  {
    if (zze.zzdc(paramMetadataField.getName()) == null) {
      throw new IllegalArgumentException("Unregistered field: " + paramMetadataField.getName());
    }
    paramMetadataField.zza(paramT, this.zzasQ);
  }
  
  public boolean zzc(MetadataField<?> paramMetadataField)
  {
    return this.zzasQ.containsKey(paramMetadataField.getName());
  }
  
  public MetadataBundle zztF()
  {
    return new MetadataBundle(new Bundle(this.zzasQ));
  }
  
  public Set<MetadataField<?>> zztG()
  {
    HashSet localHashSet = new HashSet();
    Iterator localIterator = this.zzasQ.keySet().iterator();
    while (localIterator.hasNext()) {
      localHashSet.add(zze.zzdc((String)localIterator.next()));
    }
    return localHashSet;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\internal\MetadataBundle.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */