package com.google.android.gms.plus.internal.model.moments;

import android.os.Parcel;
import com.google.android.gms.common.data.DataHolder;
import com.google.android.gms.plus.model.moments.ItemScope;
import com.google.android.gms.plus.model.moments.Moment;

public final class zzc
  extends com.google.android.gms.common.data.zzc
  implements Moment
{
  private MomentEntity zzbfK;
  
  public zzc(DataHolder paramDataHolder, int paramInt)
  {
    super(paramDataHolder, paramInt);
  }
  
  private MomentEntity zzFo()
  {
    try
    {
      if (this.zzbfK == null)
      {
        byte[] arrayOfByte = getByteArray("momentImpl");
        Parcel localParcel = Parcel.obtain();
        localParcel.unmarshall(arrayOfByte, 0, arrayOfByte.length);
        localParcel.setDataPosition(0);
        this.zzbfK = MomentEntity.CREATOR.zzgD(localParcel);
        localParcel.recycle();
      }
      return this.zzbfK;
    }
    finally {}
  }
  
  public String getId()
  {
    return zzFo().getId();
  }
  
  public ItemScope getResult()
  {
    return zzFo().getResult();
  }
  
  public String getStartDate()
  {
    return zzFo().getStartDate();
  }
  
  public ItemScope getTarget()
  {
    return zzFo().getTarget();
  }
  
  public String getType()
  {
    return zzFo().getType();
  }
  
  public boolean hasId()
  {
    return zzFo().hasId();
  }
  
  public boolean hasResult()
  {
    return zzFo().hasResult();
  }
  
  public boolean hasStartDate()
  {
    return zzFo().hasStartDate();
  }
  
  public boolean hasTarget()
  {
    return zzFo().hasTarget();
  }
  
  public boolean hasType()
  {
    return zzFo().hasType();
  }
  
  public MomentEntity zzFn()
  {
    return zzFo();
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\plus\internal\model\moments\zzc.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */