package com.google.android.gms.vision.barcode.internal.client;

import android.content.Context;
import android.graphics.Bitmap;
import android.os.IBinder;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.dynamic.zzg;
import com.google.android.gms.dynamic.zzg.zza;
import com.google.android.gms.vision.barcode.Barcode;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;
import java.nio.ByteBuffer;

public class zzd
{
  private final Context mContext;
  private final BarcodeDetectorOptions zzbnv;
  private zzb zzbnx = null;
  private final Object zzpV = new Object();
  
  public zzd(Context paramContext, BarcodeDetectorOptions paramBarcodeDetectorOptions)
  {
    this.mContext = paramContext;
    this.zzbnv = paramBarcodeDetectorOptions;
    zzIg();
  }
  
  private zzb zzIg()
  {
    synchronized (this.zzpV)
    {
      if (this.zzbnx == null) {
        this.zzbnx = zza.zza(this.mContext, this.zzbnv);
      }
      zzb localzzb = this.zzbnx;
      return localzzb;
    }
  }
  
  public boolean isOperational()
  {
    return zzIg() != null;
  }
  
  public Barcode[] zza(Bitmap paramBitmap, FrameMetadataParcel paramFrameMetadataParcel)
  {
    zzb localzzb = zzIg();
    if (localzzb == null) {
      return new Barcode[0];
    }
    try
    {
      paramBitmap = localzzb.zzb(zze.zzC(paramBitmap), paramFrameMetadataParcel);
      return paramBitmap;
    }
    catch (RemoteException paramBitmap)
    {
      Log.e("NativeBarcodeDetectorHandle", "Error calling native barcode detector", paramBitmap);
    }
    return new Barcode[0];
  }
  
  public Barcode[] zza(ByteBuffer paramByteBuffer, FrameMetadataParcel paramFrameMetadataParcel)
  {
    zzb localzzb = zzIg();
    if (localzzb == null) {
      return new Barcode[0];
    }
    try
    {
      paramByteBuffer = localzzb.zza(zze.zzC(paramByteBuffer), paramFrameMetadataParcel);
      return paramByteBuffer;
    }
    catch (RemoteException paramByteBuffer)
    {
      Log.e("NativeBarcodeDetectorHandle", "Error calling native barcode detector", paramByteBuffer);
    }
    return new Barcode[0];
  }
  
  static class zza
    extends zzg<zzc>
  {
    private static zza zzbny;
    
    zza()
    {
      super();
    }
    
    static zzb zza(Context paramContext, BarcodeDetectorOptions paramBarcodeDetectorOptions)
    {
      if (zzbny == null) {
        zzbny = new zza();
      }
      return zzbny.zzb(paramContext, paramBarcodeDetectorOptions);
    }
    
    private zzb zzb(Context paramContext, BarcodeDetectorOptions paramBarcodeDetectorOptions)
    {
      try
      {
        com.google.android.gms.dynamic.zzd localzzd = zze.zzC(paramContext);
        paramContext = ((zzc)zzaB(paramContext)).zza(localzzd, paramBarcodeDetectorOptions);
        return paramContext;
      }
      catch (RemoteException paramContext)
      {
        Log.e("NativeBarcodeDetectorHandle", "Error creating native barcode detector", paramContext);
        return null;
      }
      catch (zzg.zza paramContext)
      {
        for (;;)
        {
          Log.e("NativeBarcodeDetectorHandle", "Error creating native barcode detector", paramContext);
        }
      }
    }
    
    protected zzc zzef(IBinder paramIBinder)
    {
      return zzc.zza.zzee(paramIBinder);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\vision\barcode\internal\client\zzd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */