package com.google.android.gms.drive.query.internal;

import com.google.android.gms.drive.metadata.MetadataField;
import com.google.android.gms.drive.metadata.zzb;
import java.util.List;

public abstract interface zzf<F>
{
  public abstract F zzB(F paramF);
  
  public abstract <T> F zzb(zzb<T> paramzzb, T paramT);
  
  public abstract <T> F zzb(Operator paramOperator, MetadataField<T> paramMetadataField, T paramT);
  
  public abstract F zzb(Operator paramOperator, List<F> paramList);
  
  public abstract F zzdj(String paramString);
  
  public abstract F zze(MetadataField<?> paramMetadataField);
  
  public abstract <T> F zze(MetadataField<T> paramMetadataField, T paramT);
  
  public abstract F zztP();
  
  public abstract F zztQ();
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\query\internal\zzf.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */