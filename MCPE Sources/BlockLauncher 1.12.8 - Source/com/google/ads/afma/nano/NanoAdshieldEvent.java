package com.google.ads.afma.nano;

import com.google.android.gms.internal.zzsm;
import com.google.android.gms.internal.zzsn;
import com.google.android.gms.internal.zzss;
import com.google.android.gms.internal.zzst;
import com.google.android.gms.internal.zzsu;
import com.google.android.gms.internal.zzsx;
import java.io.IOException;

public abstract interface NanoAdshieldEvent
{
  public static final class AdShieldEvent
    extends zzsu
  {
    private static volatile AdShieldEvent[] zzaK;
    public String appId;
    
    public AdShieldEvent()
    {
      clear();
    }
    
    public static AdShieldEvent[] emptyArray()
    {
      if (zzaK == null) {}
      synchronized (zzss.zzbut)
      {
        if (zzaK == null) {
          zzaK = new AdShieldEvent[0];
        }
        return zzaK;
      }
    }
    
    public static AdShieldEvent parseFrom(zzsm paramzzsm)
      throws IOException
    {
      return new AdShieldEvent().mergeFrom(paramzzsm);
    }
    
    public static AdShieldEvent parseFrom(byte[] paramArrayOfByte)
      throws zzst
    {
      return (AdShieldEvent)zzsu.mergeFrom(new AdShieldEvent(), paramArrayOfByte);
    }
    
    public AdShieldEvent clear()
    {
      this.appId = "";
      this.zzbuu = -1;
      return this;
    }
    
    public AdShieldEvent mergeFrom(zzsm paramzzsm)
      throws IOException
    {
      for (;;)
      {
        int i = paramzzsm.zzIX();
        switch (i)
        {
        default: 
          if (zzsx.zzb(paramzzsm, i)) {}
          break;
        case 0: 
          return this;
        case 10: 
          this.appId = paramzzsm.readString();
        }
      }
    }
    
    public void writeTo(zzsn paramzzsn)
      throws IOException
    {
      if (!this.appId.equals("")) {
        paramzzsn.zzn(1, this.appId);
      }
      super.writeTo(paramzzsn);
    }
    
    protected int zzz()
    {
      int j = super.zzz();
      int i = j;
      if (!this.appId.equals("")) {
        i = j + zzsn.zzo(1, this.appId);
      }
      return i;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\ads\afma\nano\NanoAdshieldEvent.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */