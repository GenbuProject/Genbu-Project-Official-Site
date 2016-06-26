package com.google.android.gms.drive;

public class zzh
  extends ExecutionOptions
{
  private String zzaoY;
  private String zzaoZ;
  
  private zzh(String paramString1, boolean paramBoolean, String paramString2, String paramString3, int paramInt)
  {
    super(paramString1, paramBoolean, paramInt);
    this.zzaoY = paramString2;
    this.zzaoZ = paramString3;
  }
  
  public static zzh zza(ExecutionOptions paramExecutionOptions)
  {
    zza localzza = new zza();
    if (paramExecutionOptions != null)
    {
      if (paramExecutionOptions.zzsD() != 0) {
        throw new IllegalStateException("May not set a conflict strategy for new file creation.");
      }
      String str = paramExecutionOptions.zzsB();
      if (str != null) {
        localzza.zzcY(str);
      }
      localzza.zzad(paramExecutionOptions.zzsC());
    }
    return localzza.zzsH();
  }
  
  public String zzsF()
  {
    return this.zzaoY;
  }
  
  public String zzsG()
  {
    return this.zzaoZ;
  }
  
  public static class zza
    extends ExecutionOptions.Builder
  {
    private String zzaoY;
    private String zzaoZ;
    
    public zza zzad(boolean paramBoolean)
    {
      super.setNotifyOnCompletion(paramBoolean);
      return this;
    }
    
    public zza zzcY(String paramString)
    {
      super.setTrackingTag(paramString);
      return this;
    }
    
    public zza zzcx(int paramInt)
    {
      throw new UnsupportedOperationException();
    }
    
    public zzh zzsH()
    {
      zzsE();
      return new zzh(this.zzaoV, this.zzaoW, this.zzaoY, this.zzaoZ, this.zzaoX, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\zzh.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */