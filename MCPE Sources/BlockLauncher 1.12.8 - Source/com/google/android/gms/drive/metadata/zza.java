package com.google.android.gms.drive.metadata;

import android.os.Bundle;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.drive.metadata.internal.MetadataBundle;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public abstract class zza<T>
  implements MetadataField<T>
{
  private final String zzasF;
  private final Set<String> zzasG;
  private final Set<String> zzasH;
  private final int zzasI;
  
  protected zza(String paramString, int paramInt)
  {
    this.zzasF = ((String)zzx.zzb(paramString, "fieldName"));
    this.zzasG = Collections.singleton(paramString);
    this.zzasH = Collections.emptySet();
    this.zzasI = paramInt;
  }
  
  protected zza(String paramString, Collection<String> paramCollection1, Collection<String> paramCollection2, int paramInt)
  {
    this.zzasF = ((String)zzx.zzb(paramString, "fieldName"));
    this.zzasG = Collections.unmodifiableSet(new HashSet(paramCollection1));
    this.zzasH = Collections.unmodifiableSet(new HashSet(paramCollection2));
    this.zzasI = paramInt;
  }
  
  public final String getName()
  {
    return this.zzasF;
  }
  
  public String toString()
  {
    return this.zzasF;
  }
  
  public final T zza(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    if (zzb(paramDataHolder, paramInt1, paramInt2)) {
      return (T)zzc(paramDataHolder, paramInt1, paramInt2);
    }
    return null;
  }
  
  protected abstract void zza(Bundle paramBundle, T paramT);
  
  public final void zza(DataHolder paramDataHolder, MetadataBundle paramMetadataBundle, int paramInt1, int paramInt2)
  {
    zzx.zzb(paramDataHolder, "dataHolder");
    zzx.zzb(paramMetadataBundle, "bundle");
    if (zzb(paramDataHolder, paramInt1, paramInt2)) {
      paramMetadataBundle.zzc(this, zzc(paramDataHolder, paramInt1, paramInt2));
    }
  }
  
  public final void zza(T paramT, Bundle paramBundle)
  {
    zzx.zzb(paramBundle, "bundle");
    if (paramT == null)
    {
      paramBundle.putString(getName(), null);
      return;
    }
    zza(paramBundle, paramT);
  }
  
  protected boolean zzb(DataHolder paramDataHolder, int paramInt1, int paramInt2)
  {
    Iterator localIterator = this.zzasG.iterator();
    while (localIterator.hasNext())
    {
      String str = (String)localIterator.next();
      if ((!paramDataHolder.zzcz(str)) || (paramDataHolder.zzi(str, paramInt1, paramInt2))) {
        return false;
      }
    }
    return true;
  }
  
  protected abstract T zzc(DataHolder paramDataHolder, int paramInt1, int paramInt2);
  
  public final T zzm(Bundle paramBundle)
  {
    zzx.zzb(paramBundle, "bundle");
    if (paramBundle.get(getName()) != null) {
      return (T)zzn(paramBundle);
    }
    return null;
  }
  
  protected abstract T zzn(Bundle paramBundle);
  
  public final Collection<String> zzty()
  {
    return this.zzasG;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\zza.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */