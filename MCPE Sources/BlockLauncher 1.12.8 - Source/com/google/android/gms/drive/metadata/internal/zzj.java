package com.google.android.gms.drive.metadata.internal;

import android.os.Bundle;
import android.os.Parcelable;
import com.google.android.gms.drive.metadata.zzb;
import java.util.ArrayList;
import java.util.Collection;

public class zzj<T extends Parcelable>
  extends zzb<T>
{
  public zzj(String paramString, Collection<String> paramCollection1, Collection<String> paramCollection2, int paramInt)
  {
    super(paramString, paramCollection1, paramCollection2, paramInt);
  }
  
  protected void zza(Bundle paramBundle, Collection<T> paramCollection)
  {
    String str = getName();
    if ((paramCollection instanceof ArrayList)) {}
    for (paramCollection = (ArrayList)paramCollection;; paramCollection = new ArrayList(paramCollection))
    {
      paramBundle.putParcelableArrayList(str, paramCollection);
      return;
    }
  }
  
  protected Collection<T> zzs(Bundle paramBundle)
  {
    return paramBundle.getParcelableArrayList(getName());
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\metadata\internal\zzj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */