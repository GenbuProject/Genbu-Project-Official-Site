package com.google.android.gms.vision;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.ImageFormat;
import android.graphics.SurfaceTexture;
import android.hardware.Camera;
import android.hardware.Camera.CameraInfo;
import android.hardware.Camera.Parameters;
import android.hardware.Camera.PictureCallback;
import android.hardware.Camera.PreviewCallback;
import android.hardware.Camera.ShutterCallback;
import android.hardware.Camera.Size;
import android.os.Build.VERSION;
import android.os.SystemClock;
import android.support.annotation.RequiresPermission;
import android.util.Log;
import android.view.Display;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;
import com.google.android.gms.common.images.Size;
import java.io.IOException;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

public class CameraSource
{
  @SuppressLint({"InlinedApi"})
  public static final int CAMERA_FACING_BACK = 0;
  @SuppressLint({"InlinedApi"})
  public static final int CAMERA_FACING_FRONT = 1;
  private Context mContext;
  private int zzDE;
  private Camera zzbmA;
  private int zzbmB = 0;
  private Size zzbmC;
  private float zzbmD = 30.0F;
  private int zzbmE = 1024;
  private int zzbmF = 768;
  private boolean zzbmG = false;
  private SurfaceView zzbmH;
  private SurfaceTexture zzbmI;
  private boolean zzbmJ;
  private Thread zzbmK;
  private zzb zzbmL;
  private Map<byte[], ByteBuffer> zzbmM = new HashMap();
  private final Object zzbmz = new Object();
  
  @SuppressLint({"InlinedApi"})
  private Camera zzIb()
  {
    int i = zzkp(this.zzbmB);
    if (i == -1) {
      throw new RuntimeException("Could not find requested camera.");
    }
    Camera localCamera = Camera.open(i);
    Object localObject = zza(localCamera, this.zzbmE, this.zzbmF);
    if (localObject == null) {
      throw new RuntimeException("Could not find suitable preview size.");
    }
    Size localSize = ((zze)localObject).zzId();
    this.zzbmC = ((zze)localObject).zzIc();
    localObject = zza(localCamera, this.zzbmD);
    if (localObject == null) {
      throw new RuntimeException("Could not find suitable preview frames per second range.");
    }
    Camera.Parameters localParameters = localCamera.getParameters();
    localParameters.setPictureSize(localSize.getWidth(), localSize.getHeight());
    localParameters.setPreviewSize(this.zzbmC.getWidth(), this.zzbmC.getHeight());
    localParameters.setPreviewFpsRange(localObject[0], localObject[1]);
    localParameters.setPreviewFormat(17);
    zza(localCamera, localParameters, i);
    if (this.zzbmG)
    {
      if (!localParameters.getSupportedFocusModes().contains("continuous-video")) {
        break label262;
      }
      localParameters.setFocusMode("continuous-video");
    }
    for (;;)
    {
      localCamera.setParameters(localParameters);
      localCamera.setPreviewCallbackWithBuffer(new zza(null));
      localCamera.addCallbackBuffer(zza(this.zzbmC));
      localCamera.addCallbackBuffer(zza(this.zzbmC));
      localCamera.addCallbackBuffer(zza(this.zzbmC));
      localCamera.addCallbackBuffer(zza(this.zzbmC));
      return localCamera;
      label262:
      Log.i("CameraSource", "Camera auto focus is not supported on this device.");
    }
  }
  
  private static zze zza(Camera paramCamera, int paramInt1, int paramInt2)
  {
    Object localObject = zza(paramCamera);
    paramCamera = null;
    int i = Integer.MAX_VALUE;
    Iterator localIterator = ((List)localObject).iterator();
    if (localIterator.hasNext())
    {
      localObject = (zze)localIterator.next();
      Size localSize = ((zze)localObject).zzIc();
      int j = Math.abs(localSize.getWidth() - paramInt1);
      j = Math.abs(localSize.getHeight() - paramInt2) + j;
      if (j >= i) {
        break label93;
      }
      paramCamera = (Camera)localObject;
      i = j;
    }
    label93:
    for (;;)
    {
      break;
      return paramCamera;
    }
  }
  
  private static List<zze> zza(Camera paramCamera)
  {
    Object localObject = paramCamera.getParameters();
    paramCamera = ((Camera.Parameters)localObject).getSupportedPreviewSizes();
    List localList = ((Camera.Parameters)localObject).getSupportedPictureSizes();
    localObject = new ArrayList();
    Iterator localIterator1 = paramCamera.iterator();
    for (;;)
    {
      if (!localIterator1.hasNext()) {
        break label143;
      }
      Camera.Size localSize1 = (Camera.Size)localIterator1.next();
      float f = localSize1.width / localSize1.height;
      Iterator localIterator2 = localList.iterator();
      if (localIterator2.hasNext())
      {
        Camera.Size localSize2 = (Camera.Size)localIterator2.next();
        if (Math.abs(f - localSize2.width / localSize2.height) >= 0.01F) {
          break;
        }
        ((List)localObject).add(new zze(localSize1, localSize2));
      }
    }
    label143:
    if (((List)localObject).size() == 0)
    {
      Log.w("CameraSource", "No preview sizes have a corresponding same-aspect-ratio picture size");
      paramCamera = paramCamera.iterator();
      while (paramCamera.hasNext()) {
        ((List)localObject).add(new zze((Camera.Size)paramCamera.next(), null));
      }
    }
    return (List<zze>)localObject;
  }
  
  private void zza(Camera paramCamera, Camera.Parameters paramParameters, int paramInt)
  {
    int i = ((WindowManager)this.mContext.getSystemService("window")).getDefaultDisplay().getRotation();
    Camera.CameraInfo localCameraInfo;
    switch (i)
    {
    default: 
      Log.e("CameraSource", "Bad rotation value: " + i);
      i = 0;
      localCameraInfo = new Camera.CameraInfo();
      Camera.getCameraInfo(paramInt, localCameraInfo);
      if (localCameraInfo.facing == 1)
      {
        i = (i + localCameraInfo.orientation) % 360;
        paramInt = (360 - i) % 360;
      }
      break;
    }
    for (;;)
    {
      this.zzDE = (i / 90);
      paramCamera.setDisplayOrientation(paramInt);
      paramParameters.setRotation(i);
      return;
      i = 0;
      break;
      i = 90;
      break;
      i = 180;
      break;
      i = 270;
      break;
      paramInt = (localCameraInfo.orientation - i + 360) % 360;
      i = paramInt;
    }
  }
  
  @SuppressLint({"InlinedApi"})
  private byte[] zza(Size paramSize)
  {
    paramSize = new byte[(int)Math.ceil(ImageFormat.getBitsPerPixel(17) * (paramSize.getHeight() * paramSize.getWidth()) / 8.0D) + 1];
    ByteBuffer localByteBuffer = ByteBuffer.wrap(paramSize);
    if ((!localByteBuffer.hasArray()) || (localByteBuffer.array() != paramSize)) {
      throw new IllegalStateException("Failed to create valid buffer for camera source.");
    }
    this.zzbmM.put(paramSize, localByteBuffer);
    return paramSize;
  }
  
  @SuppressLint({"InlinedApi"})
  private int[] zza(Camera paramCamera, float paramFloat)
  {
    int k = (int)(1000.0F * paramFloat);
    int[] arrayOfInt = null;
    int i = Integer.MAX_VALUE;
    Iterator localIterator = paramCamera.getParameters().getSupportedPreviewFpsRange().iterator();
    paramCamera = arrayOfInt;
    if (localIterator.hasNext())
    {
      arrayOfInt = (int[])localIterator.next();
      int j = arrayOfInt[0];
      int m = arrayOfInt[1];
      j = Math.abs(k - j) + Math.abs(k - m);
      if (j >= i) {
        break label101;
      }
      paramCamera = arrayOfInt;
      i = j;
    }
    label101:
    for (;;)
    {
      break;
      return paramCamera;
    }
  }
  
  private static int zzkp(int paramInt)
  {
    Camera.CameraInfo localCameraInfo = new Camera.CameraInfo();
    int i = 0;
    while (i < Camera.getNumberOfCameras())
    {
      Camera.getCameraInfo(i, localCameraInfo);
      if (localCameraInfo.facing == paramInt) {
        return i;
      }
      i += 1;
    }
    return -1;
  }
  
  public int getCameraFacing()
  {
    return this.zzbmB;
  }
  
  public Size getPreviewSize()
  {
    return this.zzbmC;
  }
  
  public void release()
  {
    synchronized (this.zzbmz)
    {
      stop();
      this.zzbmL.release();
      return;
    }
  }
  
  @RequiresPermission("android.permission.CAMERA")
  public CameraSource start()
    throws IOException
  {
    for (;;)
    {
      synchronized (this.zzbmz)
      {
        if (this.zzbmA != null) {
          return this;
        }
        this.zzbmA = zzIb();
        if (Build.VERSION.SDK_INT >= 11)
        {
          this.zzbmI = new SurfaceTexture(100);
          this.zzbmA.setPreviewTexture(this.zzbmI);
          this.zzbmJ = true;
          this.zzbmA.startPreview();
          this.zzbmK = new Thread(this.zzbmL);
          this.zzbmL.setActive(true);
          this.zzbmK.start();
          return this;
        }
      }
      this.zzbmH = new SurfaceView(this.mContext);
      this.zzbmA.setPreviewDisplay(this.zzbmH.getHolder());
      this.zzbmJ = false;
    }
  }
  
  @RequiresPermission("android.permission.CAMERA")
  public CameraSource start(SurfaceHolder paramSurfaceHolder)
    throws IOException
  {
    synchronized (this.zzbmz)
    {
      if (this.zzbmA != null) {
        return this;
      }
      this.zzbmA = zzIb();
      this.zzbmA.setPreviewDisplay(paramSurfaceHolder);
      this.zzbmA.startPreview();
      this.zzbmK = new Thread(this.zzbmL);
      this.zzbmL.setActive(true);
      this.zzbmK.start();
      this.zzbmJ = false;
      return this;
    }
  }
  
  /* Error */
  public void stop()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 72	com/google/android/gms/vision/CameraSource:zzbmz	Ljava/lang/Object;
    //   4: astore_1
    //   5: aload_1
    //   6: monitorenter
    //   7: aload_0
    //   8: getfield 213	com/google/android/gms/vision/CameraSource:zzbmL	Lcom/google/android/gms/vision/CameraSource$zzb;
    //   11: iconst_0
    //   12: invokevirtual 435	com/google/android/gms/vision/CameraSource$zzb:setActive	(Z)V
    //   15: aload_0
    //   16: getfield 382	com/google/android/gms/vision/CameraSource:zzbmK	Ljava/lang/Thread;
    //   19: astore_2
    //   20: aload_2
    //   21: ifnull +15 -> 36
    //   24: aload_0
    //   25: getfield 382	com/google/android/gms/vision/CameraSource:zzbmK	Ljava/lang/Thread;
    //   28: invokevirtual 461	java/lang/Thread:join	()V
    //   31: aload_0
    //   32: aconst_null
    //   33: putfield 382	com/google/android/gms/vision/CameraSource:zzbmK	Ljava/lang/Thread;
    //   36: aload_0
    //   37: getfield 378	com/google/android/gms/vision/CameraSource:zzbmA	Landroid/hardware/Camera;
    //   40: ifnull +45 -> 85
    //   43: aload_0
    //   44: getfield 378	com/google/android/gms/vision/CameraSource:zzbmA	Landroid/hardware/Camera;
    //   47: invokevirtual 464	android/hardware/Camera:stopPreview	()V
    //   50: aload_0
    //   51: getfield 378	com/google/android/gms/vision/CameraSource:zzbmA	Landroid/hardware/Camera;
    //   54: aconst_null
    //   55: invokevirtual 187	android/hardware/Camera:setPreviewCallbackWithBuffer	(Landroid/hardware/Camera$PreviewCallback;)V
    //   58: aload_0
    //   59: getfield 423	com/google/android/gms/vision/CameraSource:zzbmJ	Z
    //   62: ifeq +53 -> 115
    //   65: aload_0
    //   66: getfield 378	com/google/android/gms/vision/CameraSource:zzbmA	Landroid/hardware/Camera;
    //   69: aconst_null
    //   70: invokevirtual 421	android/hardware/Camera:setPreviewTexture	(Landroid/graphics/SurfaceTexture;)V
    //   73: aload_0
    //   74: getfield 378	com/google/android/gms/vision/CameraSource:zzbmA	Landroid/hardware/Camera;
    //   77: invokevirtual 465	android/hardware/Camera:release	()V
    //   80: aload_0
    //   81: aconst_null
    //   82: putfield 378	com/google/android/gms/vision/CameraSource:zzbmA	Landroid/hardware/Camera;
    //   85: aload_0
    //   86: getfield 88	com/google/android/gms/vision/CameraSource:zzbmM	Ljava/util/Map;
    //   89: invokeinterface 468 1 0
    //   94: aload_1
    //   95: monitorexit
    //   96: return
    //   97: astore_2
    //   98: ldc -60
    //   100: ldc_w 470
    //   103: invokestatic 473	android/util/Log:d	(Ljava/lang/String;Ljava/lang/String;)I
    //   106: pop
    //   107: goto -76 -> 31
    //   110: astore_2
    //   111: aload_1
    //   112: monitorexit
    //   113: aload_2
    //   114: athrow
    //   115: aload_0
    //   116: getfield 378	com/google/android/gms/vision/CameraSource:zzbmA	Landroid/hardware/Camera;
    //   119: aconst_null
    //   120: invokevirtual 452	android/hardware/Camera:setPreviewDisplay	(Landroid/view/SurfaceHolder;)V
    //   123: goto -50 -> 73
    //   126: astore_2
    //   127: ldc -60
    //   129: new 295	java/lang/StringBuilder
    //   132: dup
    //   133: invokespecial 296	java/lang/StringBuilder:<init>	()V
    //   136: ldc_w 475
    //   139: invokevirtual 302	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   142: aload_2
    //   143: invokevirtual 478	java/lang/StringBuilder:append	(Ljava/lang/Object;)Ljava/lang/StringBuilder;
    //   146: invokevirtual 309	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   149: invokestatic 312	android/util/Log:e	(Ljava/lang/String;Ljava/lang/String;)I
    //   152: pop
    //   153: goto -80 -> 73
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	156	0	this	CameraSource
    //   4	108	1	localObject1	Object
    //   19	2	2	localThread	Thread
    //   97	1	2	localInterruptedException	InterruptedException
    //   110	4	2	localObject2	Object
    //   126	17	2	localException	Exception
    // Exception table:
    //   from	to	target	type
    //   24	31	97	java/lang/InterruptedException
    //   7	20	110	finally
    //   24	31	110	finally
    //   31	36	110	finally
    //   36	58	110	finally
    //   58	73	110	finally
    //   73	85	110	finally
    //   85	96	110	finally
    //   98	107	110	finally
    //   111	113	110	finally
    //   115	123	110	finally
    //   127	153	110	finally
    //   58	73	126	java/lang/Exception
    //   115	123	126	java/lang/Exception
  }
  
  public void takePicture(ShutterCallback paramShutterCallback, PictureCallback paramPictureCallback)
  {
    synchronized (this.zzbmz)
    {
      if (this.zzbmA != null)
      {
        zzd localzzd = new zzd(null);
        zzd.zza(localzzd, paramShutterCallback);
        paramShutterCallback = new zzc(null);
        zzc.zza(paramShutterCallback, paramPictureCallback);
        this.zzbmA.takePicture(localzzd, null, null, paramShutterCallback);
      }
      return;
    }
  }
  
  public static class Builder
  {
    private final Detector<?> zzbmN;
    private CameraSource zzbmO = new CameraSource(null);
    
    public Builder(Context paramContext, Detector<?> paramDetector)
    {
      if (paramContext == null) {
        throw new IllegalArgumentException("No context supplied.");
      }
      if (paramDetector == null) {
        throw new IllegalArgumentException("No detector supplied.");
      }
      this.zzbmN = paramDetector;
      CameraSource.zza(this.zzbmO, paramContext);
    }
    
    public CameraSource build()
    {
      CameraSource localCameraSource1 = this.zzbmO;
      CameraSource localCameraSource2 = this.zzbmO;
      localCameraSource2.getClass();
      CameraSource.zza(localCameraSource1, new CameraSource.zzb(localCameraSource2, this.zzbmN));
      return this.zzbmO;
    }
    
    public Builder setAutoFocusEnabled(boolean paramBoolean)
    {
      CameraSource.zza(this.zzbmO, paramBoolean);
      return this;
    }
    
    public Builder setFacing(int paramInt)
    {
      if ((paramInt != 0) && (paramInt != 1)) {
        throw new IllegalArgumentException("Invalid camera: " + paramInt);
      }
      CameraSource.zzc(this.zzbmO, paramInt);
      return this;
    }
    
    public Builder setRequestedFps(float paramFloat)
    {
      if (paramFloat <= 0.0F) {
        throw new IllegalArgumentException("Invalid fps: " + paramFloat);
      }
      CameraSource.zza(this.zzbmO, paramFloat);
      return this;
    }
    
    public Builder setRequestedPreviewSize(int paramInt1, int paramInt2)
    {
      if ((paramInt1 <= 0) || (paramInt1 > 1000000) || (paramInt2 <= 0) || (paramInt2 > 1000000)) {
        throw new IllegalArgumentException("Invalid preview size: " + paramInt1 + "x" + paramInt2);
      }
      CameraSource.zza(this.zzbmO, paramInt1);
      CameraSource.zzb(this.zzbmO, paramInt2);
      return this;
    }
  }
  
  public static abstract interface PictureCallback
  {
    public abstract void onPictureTaken(byte[] paramArrayOfByte);
  }
  
  public static abstract interface ShutterCallback
  {
    public abstract void onShutter();
  }
  
  private class zza
    implements Camera.PreviewCallback
  {
    private zza() {}
    
    public void onPreviewFrame(byte[] paramArrayOfByte, Camera paramCamera)
    {
      CameraSource.zzc(CameraSource.this).zza(paramArrayOfByte, paramCamera);
    }
  }
  
  private class zzb
    implements Runnable
  {
    private long zzRD = SystemClock.elapsedRealtime();
    private Detector<?> zzbmN;
    private boolean zzbmQ = true;
    private long zzbmR;
    private int zzbmS = 0;
    private ByteBuffer zzbmT;
    private final Object zzpV = new Object();
    
    static
    {
      if (!CameraSource.class.desiredAssertionStatus()) {}
      for (boolean bool = true;; bool = false)
      {
        $assertionsDisabled = bool;
        return;
      }
    }
    
    zzb()
    {
      Detector localDetector;
      this.zzbmN = localDetector;
    }
    
    @SuppressLint({"Assert"})
    void release()
    {
      assert (CameraSource.zzd(CameraSource.this).getState() == Thread.State.TERMINATED);
      this.zzbmN.release();
      this.zzbmN = null;
    }
    
    @SuppressLint({"InlinedApi"})
    public void run()
    {
      for (;;)
      {
        synchronized (this.zzpV)
        {
          if (this.zzbmQ)
          {
            ByteBuffer localByteBuffer1 = this.zzbmT;
            if (localByteBuffer1 != null) {}
          }
          try
          {
            this.zzpV.wait();
            if (!this.zzbmQ) {
              return;
            }
          }
          catch (InterruptedException localInterruptedException)
          {
            Log.d("CameraSource", "Frame processing loop terminated.", localInterruptedException);
            return;
          }
        }
        Frame localFrame = new Frame.Builder().setImageData(this.zzbmT, CameraSource.zzg(CameraSource.this).getWidth(), CameraSource.zzg(CameraSource.this).getHeight(), 17).setId(this.zzbmS).setTimestampMillis(this.zzbmR).setRotation(CameraSource.zzf(CameraSource.this)).build();
        ByteBuffer localByteBuffer2 = this.zzbmT;
        this.zzbmT = null;
        try
        {
          this.zzbmN.receiveFrame(localFrame);
          CameraSource.zzb(CameraSource.this).addCallbackBuffer(localByteBuffer2.array());
        }
        catch (Throwable localThrowable)
        {
          Log.e("CameraSource", "Exception thrown from receiver.", localThrowable);
          CameraSource.zzb(CameraSource.this).addCallbackBuffer(localByteBuffer2.array());
        }
        finally
        {
          CameraSource.zzb(CameraSource.this).addCallbackBuffer(localByteBuffer2.array());
        }
      }
    }
    
    void setActive(boolean paramBoolean)
    {
      synchronized (this.zzpV)
      {
        this.zzbmQ = paramBoolean;
        this.zzpV.notifyAll();
        return;
      }
    }
    
    void zza(byte[] paramArrayOfByte, Camera paramCamera)
    {
      synchronized (this.zzpV)
      {
        if (this.zzbmT != null)
        {
          paramCamera.addCallbackBuffer(this.zzbmT.array());
          this.zzbmT = null;
        }
        this.zzbmR = (SystemClock.elapsedRealtime() - this.zzRD);
        this.zzbmS += 1;
        this.zzbmT = ((ByteBuffer)CameraSource.zze(CameraSource.this).get(paramArrayOfByte));
        this.zzpV.notifyAll();
        return;
      }
    }
  }
  
  private class zzc
    implements Camera.PictureCallback
  {
    private CameraSource.PictureCallback zzbmU;
    
    private zzc() {}
    
    public void onPictureTaken(byte[] arg1, Camera paramCamera)
    {
      if (this.zzbmU != null) {
        this.zzbmU.onPictureTaken(???);
      }
      synchronized (CameraSource.zza(CameraSource.this))
      {
        if (CameraSource.zzb(CameraSource.this) != null) {
          CameraSource.zzb(CameraSource.this).startPreview();
        }
        return;
      }
    }
  }
  
  private class zzd
    implements Camera.ShutterCallback
  {
    private CameraSource.ShutterCallback zzbmV;
    
    private zzd() {}
    
    public void onShutter()
    {
      if (this.zzbmV != null) {
        this.zzbmV.onShutter();
      }
    }
  }
  
  private static class zze
  {
    private Size zzbmW;
    private Size zzbmX;
    
    public zze(Camera.Size paramSize1, Camera.Size paramSize2)
    {
      this.zzbmW = new Size(paramSize1.width, paramSize1.height);
      this.zzbmX = new Size(paramSize2.width, paramSize2.height);
    }
    
    public Size zzIc()
    {
      return this.zzbmW;
    }
    
    public Size zzId()
    {
      return this.zzbmX;
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\vision\CameraSource.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */