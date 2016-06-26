package com.google.android.gms.drive;

public class zzi
  extends ExecutionOptions
{
  private boolean zzapa;
  
  private zzi(String paramString, boolean paramBoolean1, int paramInt, boolean paramBoolean2)
  {
    super(paramString, paramBoolean1, paramInt);
    this.zzapa = paramBoolean2;
  }
  
  public static zzi zzb(ExecutionOptions paramExecutionOptions)
  {
    zza localzza = new zza();
    if (paramExecutionOptions != null)
    {
      localzza.zzcy(paramExecutionOptions.zzsD());
      localzza.zzae(paramExecutionOptions.zzsC());
      paramExecutionOptions = paramExecutionOptions.zzsB();
      if (paramExecutionOptions != null) {
        localzza.zzcZ(paramExecutionOptions);
      }
    }
    return localzza.zzsJ();
  }
  
  public boolean zzsI()
  {
    return this.zzapa;
  }
  
  public static class zza
    extends ExecutionOptions.Builder
  {
    private boolean zzapa = true;
    
    public zza zzae(boolean paramBoolean)
    {
      super.setNotifyOnCompletion(paramBoolean);
      return this;
    }
    
    public zza zzcZ(String paramString)
    {
      super.setTrackingTag(paramString);
      return this;
    }
    
    public zza zzcy(int paramInt)
    {
      super.setConflictStrategy(paramInt);
      return this;
    }
    
    public zzi zzsJ()
    {
      zzsE();
      return new zzi(this.zzaoV, this.zzaoW, this.zzaoX, this.zzapa, null);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\drive\zzi.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */