package com.google.android.gms.internal;

import android.support.annotation.Nullable;
import com.google.android.gms.common.api.Api.ApiOptions.Optional;

public final class zzro
  implements Api.ApiOptions.Optional
{
  public static final zzro zzbgV = new zza().zzFJ();
  private final boolean zzXa;
  private final boolean zzXc;
  private final String zzXd;
  private final String zzXe;
  private final boolean zzbgW;
  private final boolean zzbgX;
  
  private zzro(boolean paramBoolean1, boolean paramBoolean2, String paramString1, boolean paramBoolean3, String paramString2, boolean paramBoolean4)
  {
    this.zzbgW = paramBoolean1;
    this.zzXa = paramBoolean2;
    this.zzXd = paramString1;
    this.zzXc = paramBoolean3;
    this.zzbgX = paramBoolean4;
    this.zzXe = paramString2;
  }
  
  public boolean zzFH()
  {
    return this.zzbgW;
  }
  
  public boolean zzFI()
  {
    return this.zzbgX;
  }
  
  public boolean zzmO()
  {
    return this.zzXa;
  }
  
  public boolean zzmQ()
  {
    return this.zzXc;
  }
  
  public String zzmR()
  {
    return this.zzXd;
  }
  
  @Nullable
  public String zzmS()
  {
    return this.zzXe;
  }
  
  public static final class zza
  {
    private String zzbdY;
    private boolean zzbgY;
    private boolean zzbgZ;
    private boolean zzbha;
    private String zzbhb;
    private boolean zzbhc;
    
    public zzro zzFJ()
    {
      return new zzro(this.zzbgY, this.zzbgZ, this.zzbdY, this.zzbha, this.zzbhb, this.zzbhc, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzro.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */