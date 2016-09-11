package com.google.android.gms.internal;

import android.os.Bundle;
import android.os.SystemClock;
import com.google.android.gms.ads.internal.client.AdRequestParcel;
import com.google.android.gms.ads.internal.zzr;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.LinkedList;

@zzhb
public class zzig
{
  private boolean zzJu = false;
  private final LinkedList<zza> zzLf;
  private final String zzLg;
  private final String zzLh;
  private long zzLi = -1L;
  private long zzLj = -1L;
  private long zzLk = -1L;
  private long zzLl = 0L;
  private long zzLm = -1L;
  private long zzLn = -1L;
  private final Object zzpV = new Object();
  private final zzih zzqV;
  
  public zzig(zzih paramzzih, String paramString1, String paramString2)
  {
    this.zzqV = paramzzih;
    this.zzLg = paramString1;
    this.zzLh = paramString2;
    this.zzLf = new LinkedList();
  }
  
  public zzig(String paramString1, String paramString2)
  {
    this(zzr.zzbF(), paramString1, paramString2);
  }
  
  public Bundle toBundle()
  {
    ArrayList localArrayList;
    synchronized (this.zzpV)
    {
      Bundle localBundle1 = new Bundle();
      localBundle1.putString("seq_num", this.zzLg);
      localBundle1.putString("slotid", this.zzLh);
      localBundle1.putBoolean("ismediation", this.zzJu);
      localBundle1.putLong("treq", this.zzLm);
      localBundle1.putLong("tresponse", this.zzLn);
      localBundle1.putLong("timp", this.zzLj);
      localBundle1.putLong("tload", this.zzLk);
      localBundle1.putLong("pcc", this.zzLl);
      localBundle1.putLong("tfetch", this.zzLi);
      localArrayList = new ArrayList();
      Iterator localIterator = this.zzLf.iterator();
      if (localIterator.hasNext()) {
        localArrayList.add(((zza)localIterator.next()).toBundle());
      }
    }
    localBundle2.putParcelableArrayList("tclick", localArrayList);
    return localBundle2;
  }
  
  public void zzA(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      if (this.zzLn != -1L)
      {
        this.zzJu = paramBoolean;
        this.zzqV.zza(this);
      }
      return;
    }
  }
  
  public void zzgS()
  {
    synchronized (this.zzpV)
    {
      if ((this.zzLn != -1L) && (this.zzLj == -1L))
      {
        this.zzLj = SystemClock.elapsedRealtime();
        this.zzqV.zza(this);
      }
      this.zzqV.zzha().zzgS();
      return;
    }
  }
  
  public void zzgT()
  {
    synchronized (this.zzpV)
    {
      if (this.zzLn != -1L)
      {
        zza localzza = new zza();
        localzza.zzgX();
        this.zzLf.add(localzza);
        this.zzLl += 1L;
        this.zzqV.zzha().zzgT();
        this.zzqV.zza(this);
      }
      return;
    }
  }
  
  public void zzgU()
  {
    synchronized (this.zzpV)
    {
      if ((this.zzLn != -1L) && (!this.zzLf.isEmpty()))
      {
        zza localzza = (zza)this.zzLf.getLast();
        if (localzza.zzgV() == -1L)
        {
          localzza.zzgW();
          this.zzqV.zza(this);
        }
      }
      return;
    }
  }
  
  public void zzk(AdRequestParcel paramAdRequestParcel)
  {
    synchronized (this.zzpV)
    {
      this.zzLm = SystemClock.elapsedRealtime();
      this.zzqV.zzha().zzb(paramAdRequestParcel, this.zzLm);
      return;
    }
  }
  
  public void zzl(long paramLong)
  {
    synchronized (this.zzpV)
    {
      this.zzLn = paramLong;
      if (this.zzLn != -1L) {
        this.zzqV.zza(this);
      }
      return;
    }
  }
  
  public void zzm(long paramLong)
  {
    synchronized (this.zzpV)
    {
      if (this.zzLn != -1L)
      {
        this.zzLi = paramLong;
        this.zzqV.zza(this);
      }
      return;
    }
  }
  
  public void zzz(boolean paramBoolean)
  {
    synchronized (this.zzpV)
    {
      if (this.zzLn != -1L)
      {
        this.zzLk = SystemClock.elapsedRealtime();
        if (!paramBoolean)
        {
          this.zzLj = this.zzLk;
          this.zzqV.zza(this);
        }
      }
      return;
    }
  }
  
  @zzhb
  private static final class zza
  {
    private long zzLo = -1L;
    private long zzLp = -1L;
    
    public Bundle toBundle()
    {
      Bundle localBundle = new Bundle();
      localBundle.putLong("topen", this.zzLo);
      localBundle.putLong("tclose", this.zzLp);
      return localBundle;
    }
    
    public long zzgV()
    {
      return this.zzLp;
    }
    
    public void zzgW()
    {
      this.zzLp = SystemClock.elapsedRealtime();
    }
    
    public void zzgX()
    {
      this.zzLo = SystemClock.elapsedRealtime();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzig.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */