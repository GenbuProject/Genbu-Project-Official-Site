package com.google.android.gms.vision.face.internal.client;

import android.content.Context;
import android.graphics.PointF;
import android.os.RemoteException;
import android.util.Log;
import com.google.android.gms.dynamic.zze;
import com.google.android.gms.vision.face.Face;
import com.google.android.gms.vision.face.Landmark;
import com.google.android.gms.vision.internal.client.FrameMetadataParcel;
import java.nio.ByteBuffer;

public class zzg
{
  private final Context mContext;
  private final FaceSettingsParcel zzbob;
  private zzc zzboc = null;
  private boolean zzbod = false;
  private final Object zzpV = new Object();
  
  public zzg(Context paramContext, FaceSettingsParcel paramFaceSettingsParcel)
  {
    this.mContext = paramContext;
    this.zzbob = paramFaceSettingsParcel;
    zzIi();
  }
  
  private zzc zzIi()
  {
    for (;;)
    {
      synchronized (this.zzpV)
      {
        zzc localzzc;
        if (this.zzboc != null)
        {
          localzzc = this.zzboc;
          return localzzc;
        }
        this.zzboc = zzf.zza(this.mContext, this.zzbob);
        if ((!this.zzbod) && (this.zzboc == null))
        {
          Log.w("FaceDetectorHandle", "Native face detector not yet available.  Reverting to no-op detection.");
          this.zzbod = true;
          localzzc = this.zzboc;
          return localzzc;
        }
      }
      if ((this.zzbod) && (this.zzboc != null)) {
        Log.w("FaceDetectorHandle", "Native face detector is now available.");
      }
    }
  }
  
  private Face zza(FaceParcel paramFaceParcel)
  {
    return new Face(paramFaceParcel.id, new PointF(paramFaceParcel.centerX, paramFaceParcel.centerY), paramFaceParcel.width, paramFaceParcel.height, paramFaceParcel.zzbnP, paramFaceParcel.zzbnQ, zzb(paramFaceParcel), paramFaceParcel.zzbnS, paramFaceParcel.zzbnT, paramFaceParcel.zzbnU);
  }
  
  private Landmark zza(LandmarkParcel paramLandmarkParcel)
  {
    return new Landmark(new PointF(paramLandmarkParcel.x, paramLandmarkParcel.y), paramLandmarkParcel.type);
  }
  
  private Landmark[] zzb(FaceParcel paramFaceParcel)
  {
    int i = 0;
    paramFaceParcel = paramFaceParcel.zzbnR;
    if (paramFaceParcel == null) {
      return new Landmark[0];
    }
    Landmark[] arrayOfLandmark = new Landmark[paramFaceParcel.length];
    while (i < paramFaceParcel.length)
    {
      arrayOfLandmark[i] = zza(paramFaceParcel[i]);
      i += 1;
    }
    return arrayOfLandmark;
  }
  
  public boolean isOperational()
  {
    return zzIi() != null;
  }
  
  public void zzIh()
  {
    synchronized (this.zzpV)
    {
      if (this.zzboc == null) {
        return;
      }
    }
    try
    {
      this.zzboc.zzIh();
      return;
      localObject2 = finally;
      throw ((Throwable)localObject2);
    }
    catch (RemoteException localRemoteException)
    {
      for (;;)
      {
        Log.e("FaceDetectorHandle", "Could not finalize native face detector", localRemoteException);
      }
    }
  }
  
  public Face[] zzb(ByteBuffer paramByteBuffer, FrameMetadataParcel paramFrameMetadataParcel)
  {
    int i = 0;
    zzc localzzc = zzIi();
    if (localzzc == null) {
      return new Face[0];
    }
    try
    {
      paramByteBuffer = localzzc.zzc(zze.zzC(paramByteBuffer), paramFrameMetadataParcel);
      paramFrameMetadataParcel = new Face[paramByteBuffer.length];
      while (i < paramByteBuffer.length)
      {
        paramFrameMetadataParcel[i] = zza(paramByteBuffer[i]);
        i += 1;
      }
      return paramFrameMetadataParcel;
    }
    catch (RemoteException paramByteBuffer)
    {
      Log.e("FaceDetectorHandle", "Could not call native face detector", paramByteBuffer);
      return new Face[0];
    }
  }
  
  public boolean zzkJ(int paramInt)
  {
    zzc localzzc = zzIi();
    if (localzzc == null) {
      return false;
    }
    try
    {
      boolean bool = localzzc.zzkJ(paramInt);
      return bool;
    }
    catch (RemoteException localRemoteException)
    {
      Log.e("FaceDetectorHandle", "Could not call native face detector", localRemoteException);
    }
    return false;
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\vision\face\internal\client\zzg.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */