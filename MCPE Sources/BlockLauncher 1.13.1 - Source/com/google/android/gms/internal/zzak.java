package com.google.android.gms.internal;

import android.content.Context;
import android.content.res.Resources;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.ads.afma.nano.NanoAfmaSignals.AFMASignals;
import com.google.android.gms.common.api.GoogleApiClient;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.UUID;

public abstract class zzak
  implements zzaj
{
  protected static GoogleApiClient zznG;
  protected long zznA = 0L;
  protected long zznB = 0L;
  protected long zznC = 0L;
  private boolean zznD = false;
  protected DisplayMetrics zznE;
  protected zzap zznF;
  protected MotionEvent zznx;
  protected LinkedList<MotionEvent> zzny = new LinkedList();
  protected long zznz = 0L;
  
  protected zzak(Context paramContext, zzap paramzzap)
  {
    this.zznF = paramzzap;
    try
    {
      this.zznE = paramContext.getResources().getDisplayMetrics();
      return;
    }
    catch (UnsupportedOperationException paramContext)
    {
      this.zznE = new DisplayMetrics();
      this.zznE.density = 1.0F;
    }
  }
  
  private String zza(Context paramContext, String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {}
    try
    {
      paramContext = zzd(paramContext);
      this.zznD = true;
      while ((paramContext == null) || (paramContext.getSerializedSize() == 0))
      {
        return Integer.toString(5);
        paramContext = zzc(paramContext);
      }
      paramContext = zza(paramContext, paramString);
      return paramContext;
    }
    catch (NoSuchAlgorithmException paramContext)
    {
      return Integer.toString(7);
    }
    catch (UnsupportedEncodingException paramContext)
    {
      return Integer.toString(7);
    }
    catch (IOException paramContext) {}
    return Integer.toString(3);
  }
  
  protected String zza(NanoAfmaSignals.AFMASignals paramAFMASignals, String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException
  {
    return zza(zzsu.toByteArray(paramAFMASignals), paramString);
  }
  
  String zza(byte[] paramArrayOfByte, String paramString)
    throws NoSuchAlgorithmException, UnsupportedEncodingException, IOException
  {
    Object localObject = paramArrayOfByte;
    if (paramArrayOfByte.length > 239)
    {
      paramArrayOfByte = new NanoAfmaSignals.AFMASignals();
      paramArrayOfByte.psnSignal = Long.valueOf(1L);
      localObject = zzsu.toByteArray(paramArrayOfByte);
    }
    if (localObject.length < 239)
    {
      paramArrayOfByte = new byte[239 - localObject.length];
      new SecureRandom().nextBytes(paramArrayOfByte);
    }
    for (paramArrayOfByte = ByteBuffer.allocate(240).put((byte)localObject.length).put((byte[])localObject).put(paramArrayOfByte).array();; paramArrayOfByte = ByteBuffer.allocate(240).put((byte)localObject.length).put((byte[])localObject).array())
    {
      localObject = MessageDigest.getInstance("MD5");
      ((MessageDigest)localObject).update(paramArrayOfByte);
      localObject = ((MessageDigest)localObject).digest();
      paramArrayOfByte = ByteBuffer.allocate(256).put((byte[])localObject).put(paramArrayOfByte).array();
      localObject = new byte['?'];
      new zzai().zzb(paramArrayOfByte, (byte[])localObject);
      if ((paramString != null) && (paramString.length() > 0)) {
        zza(paramString, (byte[])localObject);
      }
      return this.zznF.zza((byte[])localObject, true);
    }
  }
  
  public void zza(int paramInt1, int paramInt2, int paramInt3)
  {
    if (this.zznx != null) {
      this.zznx.recycle();
    }
    this.zznx = MotionEvent.obtain(0L, paramInt3, 1, paramInt1 * this.zznE.density, paramInt2 * this.zznE.density, 0.0F, 0.0F, 0, 0.0F, 0.0F, 0, 0);
  }
  
  public void zza(MotionEvent paramMotionEvent)
  {
    if (this.zznD)
    {
      this.zznC = 0L;
      this.zznB = 0L;
      this.zznA = 0L;
      this.zznz = 0L;
      Iterator localIterator = this.zzny.iterator();
      while (localIterator.hasNext()) {
        ((MotionEvent)localIterator.next()).recycle();
      }
      this.zzny.clear();
      this.zznx = null;
      this.zznD = false;
    }
    switch (paramMotionEvent.getAction())
    {
    default: 
      return;
    case 1: 
      this.zznx = MotionEvent.obtain(paramMotionEvent);
      this.zzny.add(this.zznx);
      if (this.zzny.size() > 6) {
        ((MotionEvent)this.zzny.remove()).recycle();
      }
      this.zznB += 1L;
      return;
    case 0: 
      this.zznz += 1L;
      return;
    case 3: 
      this.zznC += 1L;
      return;
    }
    this.zznA += paramMotionEvent.getHistorySize() + 1;
  }
  
  void zza(String paramString, byte[] paramArrayOfByte)
    throws UnsupportedEncodingException
  {
    String str = paramString;
    if (paramString.length() > 32) {
      str = paramString.substring(0, 32);
    }
    new zzsl(str.getBytes("UTF-8")).zzC(paramArrayOfByte);
  }
  
  public String zzb(Context paramContext)
  {
    return zza(paramContext, null, false);
  }
  
  public String zzb(Context paramContext, String paramString)
  {
    return zza(paramContext, paramString, true);
  }
  
  protected abstract NanoAfmaSignals.AFMASignals zzc(Context paramContext);
  
  protected abstract NanoAfmaSignals.AFMASignals zzd(Context paramContext);
  
  protected String zzk(String paramString)
  {
    Object localObject = paramString;
    if (paramString != null)
    {
      localObject = paramString;
      if (paramString.matches("^[a-fA-F0-9]{8}-([a-fA-F0-9]{4}-){3}[a-fA-F0-9]{12}$"))
      {
        paramString = UUID.fromString(paramString);
        localObject = new byte[16];
        ByteBuffer localByteBuffer = ByteBuffer.wrap((byte[])localObject);
        localByteBuffer.putLong(paramString.getMostSignificantBits());
        localByteBuffer.putLong(paramString.getLeastSignificantBits());
        localObject = this.zznF.zza((byte[])localObject, true);
      }
    }
    return (String)localObject;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzak.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */