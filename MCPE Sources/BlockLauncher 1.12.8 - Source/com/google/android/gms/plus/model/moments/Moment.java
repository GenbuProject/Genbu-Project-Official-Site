package com.google.android.gms.plus.model.moments;

import com.google.android.gms.common.data.Freezable;
import com.google.android.gms.plus.internal.model.moments.ItemScopeEntity;
import com.google.android.gms.plus.internal.model.moments.MomentEntity;
import java.util.HashSet;
import java.util.Set;

public abstract interface Moment
  extends Freezable<Moment>
{
  public abstract String getId();
  
  public abstract ItemScope getResult();
  
  public abstract String getStartDate();
  
  public abstract ItemScope getTarget();
  
  public abstract String getType();
  
  public abstract boolean hasId();
  
  public abstract boolean hasResult();
  
  public abstract boolean hasStartDate();
  
  public abstract boolean hasTarget();
  
  public abstract boolean hasType();
  
  public static class Builder
  {
    private String zzJN;
    private final Set<Integer> zzbeN = new HashSet();
    private String zzbfA;
    private ItemScopeEntity zzbfI;
    private ItemScopeEntity zzbfJ;
    private String zzyv;
    
    public Moment build()
    {
      return new MomentEntity(this.zzbeN, this.zzyv, this.zzbfI, this.zzbfA, this.zzbfJ, this.zzJN);
    }
    
    public Builder setId(String paramString)
    {
      this.zzyv = paramString;
      this.zzbeN.add(Integer.valueOf(2));
      return this;
    }
    
    public Builder setResult(ItemScope paramItemScope)
    {
      this.zzbfI = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(4));
      return this;
    }
    
    public Builder setStartDate(String paramString)
    {
      this.zzbfA = paramString;
      this.zzbeN.add(Integer.valueOf(5));
      return this;
    }
    
    public Builder setTarget(ItemScope paramItemScope)
    {
      this.zzbfJ = ((ItemScopeEntity)paramItemScope);
      this.zzbeN.add(Integer.valueOf(6));
      return this;
    }
    
    public Builder setType(String paramString)
    {
      this.zzJN = paramString;
      this.zzbeN.add(Integer.valueOf(7));
      return this;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\plus\model\moments\Moment.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */