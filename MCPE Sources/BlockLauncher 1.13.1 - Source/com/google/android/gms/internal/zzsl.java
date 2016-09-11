package com.google.android.gms.internal;

public class zzsl
{
  private final byte[] zzbtW = new byte['?'];
  private int zzbtX;
  private int zzbtY;
  
  public zzsl(byte[] paramArrayOfByte)
  {
    int j = 0;
    while (j < 256)
    {
      this.zzbtW[j] = ((byte)j);
      j += 1;
    }
    int k = 0;
    j = 0;
    while (j < 256)
    {
      k = k + this.zzbtW[j] + paramArrayOfByte[(j % paramArrayOfByte.length)] & 0xFF;
      int i = this.zzbtW[j];
      this.zzbtW[j] = this.zzbtW[k];
      this.zzbtW[k] = i;
      j += 1;
    }
    this.zzbtX = 0;
    this.zzbtY = 0;
  }
  
  public void zzC(byte[] paramArrayOfByte)
  {
    int m = this.zzbtX;
    int k = this.zzbtY;
    int j = 0;
    while (j < paramArrayOfByte.length)
    {
      m = m + 1 & 0xFF;
      k = k + this.zzbtW[m] & 0xFF;
      int i = this.zzbtW[m];
      this.zzbtW[m] = this.zzbtW[k];
      this.zzbtW[k] = i;
      paramArrayOfByte[j] = ((byte)(paramArrayOfByte[j] ^ this.zzbtW[(this.zzbtW[m] + this.zzbtW[k] & 0xFF)]));
      j += 1;
    }
    this.zzbtX = m;
    this.zzbtY = k;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzsl.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */