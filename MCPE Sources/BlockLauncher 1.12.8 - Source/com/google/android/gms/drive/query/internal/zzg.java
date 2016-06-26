package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;
import com.google.android.gms.drive.query.Filter;
import java.util.List;

public class zzg
  implements zzf<Boolean>
{
  private Boolean zzaut = Boolean.valueOf(false);
  
  public static boolean zza(Filter paramFilter)
  {
    if (paramFilter == null) {
      return false;
    }
    return ((Boolean)paramFilter.zza(new zzg())).booleanValue();
  }
  
  public <T> Boolean zzc(zzb<T> paramzzb, T paramT)
  {
    return this.zzaut;
  }
  
  public <T> Boolean zzc(Operator paramOperator, MetadataField<T> paramMetadataField, T paramT)
  {
    return this.zzaut;
  }
  
  public Boolean zzc(Operator paramOperator, List<Boolean> paramList)
  {
    return this.zzaut;
  }
  
  public Boolean zzd(Boolean paramBoolean)
  {
    return this.zzaut;
  }
  
  public Boolean zzdk(String paramString)
  {
    if (!paramString.isEmpty()) {
      this.zzaut = Boolean.valueOf(true);
    }
    return this.zzaut;
  }
  
  public Boolean zzf(MetadataField<?> paramMetadataField)
  {
    return this.zzaut;
  }
  
  public <T> Boolean zzf(MetadataField<T> paramMetadataField, T paramT)
  {
    return this.zzaut;
  }
  
  public Boolean zztR()
  {
    return this.zzaut;
  }
  
  public Boolean zztS()
  {
    return this.zzaut;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\query\internal\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */