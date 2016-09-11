package com.google.android.gms.plus.model.people;

import android.os.Bundle;
import com.google.android.gms.common.data.AbstractDataBuffer;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.common.data.zzd;
import com.google.android.gms.plus.internal.model.people.PersonEntity;
import com.google.android.gms.plus.internal.model.people.zzk;

public final class PersonBuffer
  extends AbstractDataBuffer<Person>
{
  private final zzd<PersonEntity> zzbgt;
  
  public PersonBuffer(DataHolder paramDataHolder)
  {
    super(paramDataHolder);
    if ((paramDataHolder.zzpZ() != null) && (paramDataHolder.zzpZ().getBoolean("com.google.android.gms.plus.IsSafeParcelable", false)))
    {
      this.zzbgt = new zzd(paramDataHolder, PersonEntity.CREATOR);
      return;
    }
    this.zzbgt = null;
  }
  
  public Person get(int paramInt)
  {
    if (this.zzbgt != null) {
      return (Person)this.zzbgt.zzbG(paramInt);
    }
    return new zzk(this.zzahi, paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\plus\model\people\PersonBuffer.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */