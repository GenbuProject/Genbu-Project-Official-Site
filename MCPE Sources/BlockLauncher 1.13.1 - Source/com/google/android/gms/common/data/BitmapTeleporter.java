package com.google.android.gms.common.data;

import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.os.Parcel;
import android.os.ParcelFileDescriptor;
import android.os.ParcelFileDescriptor.AutoCloseInputStream;
import android.os.Parcelable.Creator;
import android.util.Log;
import com.google.android.gms.common.internal.safeparcel.SafeParcelable;
import java.io.Closeable;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.nio.Buffer;
import java.nio.ByteBuffer;

public class BitmapTeleporter
  implements SafeParcelable
{
  public static final Parcelable.Creator<BitmapTeleporter> CREATOR = new zza();
  final int mVersionCode;
  ParcelFileDescriptor zzIq;
  final int zzabB;
  private Bitmap zzaiY;
  private boolean zzaiZ;
  private File zzaja;
  
  BitmapTeleporter(int paramInt1, ParcelFileDescriptor paramParcelFileDescriptor, int paramInt2)
  {
    this.mVersionCode = paramInt1;
    this.zzIq = paramParcelFileDescriptor;
    this.zzabB = paramInt2;
    this.zzaiY = null;
    this.zzaiZ = false;
  }
  
  public BitmapTeleporter(Bitmap paramBitmap)
  {
    this.mVersionCode = 1;
    this.zzIq = null;
    this.zzabB = 0;
    this.zzaiY = paramBitmap;
    this.zzaiZ = true;
  }
  
  private void zza(Closeable paramCloseable)
  {
    try
    {
      paramCloseable.close();
      return;
    }
    catch (IOException paramCloseable)
    {
      Log.w("BitmapTeleporter", "Could not close stream", paramCloseable);
    }
  }
  
  /* Error */
  private java.io.FileOutputStream zzqb()
  {
    // Byte code:
    //   0: aload_0
    //   1: getfield 68	com/google/android/gms/common/data/BitmapTeleporter:zzaja	Ljava/io/File;
    //   4: ifnonnull +13 -> 17
    //   7: new 70	java/lang/IllegalStateException
    //   10: dup
    //   11: ldc 72
    //   13: invokespecial 75	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   16: athrow
    //   17: ldc 77
    //   19: ldc 79
    //   21: aload_0
    //   22: getfield 68	com/google/android/gms/common/data/BitmapTeleporter:zzaja	Ljava/io/File;
    //   25: invokestatic 85	java/io/File:createTempFile	(Ljava/lang/String;Ljava/lang/String;Ljava/io/File;)Ljava/io/File;
    //   28: astore_1
    //   29: new 87	java/io/FileOutputStream
    //   32: dup
    //   33: aload_1
    //   34: invokespecial 90	java/io/FileOutputStream:<init>	(Ljava/io/File;)V
    //   37: astore_2
    //   38: aload_0
    //   39: aload_1
    //   40: ldc 91
    //   42: invokestatic 97	android/os/ParcelFileDescriptor:open	(Ljava/io/File;I)Landroid/os/ParcelFileDescriptor;
    //   45: putfield 36	com/google/android/gms/common/data/BitmapTeleporter:zzIq	Landroid/os/ParcelFileDescriptor;
    //   48: aload_1
    //   49: invokevirtual 101	java/io/File:delete	()Z
    //   52: pop
    //   53: aload_2
    //   54: areturn
    //   55: astore_1
    //   56: new 70	java/lang/IllegalStateException
    //   59: dup
    //   60: ldc 103
    //   62: aload_1
    //   63: invokespecial 106	java/lang/IllegalStateException:<init>	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   66: athrow
    //   67: astore_1
    //   68: new 70	java/lang/IllegalStateException
    //   71: dup
    //   72: ldc 108
    //   74: invokespecial 75	java/lang/IllegalStateException:<init>	(Ljava/lang/String;)V
    //   77: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	78	0	this	BitmapTeleporter
    //   28	21	1	localFile	File
    //   55	8	1	localIOException	IOException
    //   67	1	1	localFileNotFoundException	java.io.FileNotFoundException
    //   37	17	2	localFileOutputStream	java.io.FileOutputStream
    // Exception table:
    //   from	to	target	type
    //   17	29	55	java/io/IOException
    //   29	48	67	java/io/FileNotFoundException
  }
  
  public int describeContents()
  {
    return 0;
  }
  
  public void release()
  {
    if (!this.zzaiZ) {}
    try
    {
      this.zzIq.close();
      return;
    }
    catch (IOException localIOException)
    {
      Log.w("BitmapTeleporter", "Could not close PFD", localIOException);
    }
  }
  
  public void writeToParcel(Parcel paramParcel, int paramInt)
  {
    Bitmap localBitmap;
    Object localObject;
    byte[] arrayOfByte;
    if (this.zzIq == null)
    {
      localBitmap = this.zzaiY;
      localObject = ByteBuffer.allocate(localBitmap.getRowBytes() * localBitmap.getHeight());
      localBitmap.copyPixelsToBuffer((Buffer)localObject);
      arrayOfByte = ((ByteBuffer)localObject).array();
      localObject = new DataOutputStream(zzqb());
    }
    try
    {
      ((DataOutputStream)localObject).writeInt(arrayOfByte.length);
      ((DataOutputStream)localObject).writeInt(localBitmap.getWidth());
      ((DataOutputStream)localObject).writeInt(localBitmap.getHeight());
      ((DataOutputStream)localObject).writeUTF(localBitmap.getConfig().toString());
      ((DataOutputStream)localObject).write(arrayOfByte);
      zza((Closeable)localObject);
      zza.zza(this, paramParcel, paramInt | 0x1);
      this.zzIq = null;
      return;
    }
    catch (IOException paramParcel)
    {
      throw new IllegalStateException("Could not write into unlinked file", paramParcel);
    }
    finally
    {
      zza((Closeable)localObject);
    }
  }
  
  public void zzc(File paramFile)
  {
    if (paramFile == null) {
      throw new NullPointerException("Cannot set null temp directory");
    }
    this.zzaja = paramFile;
  }
  
  public Bitmap zzqa()
  {
    Object localObject1;
    if (!this.zzaiZ) {
      localObject1 = new DataInputStream(new ParcelFileDescriptor.AutoCloseInputStream(this.zzIq));
    }
    try
    {
      byte[] arrayOfByte = new byte[((DataInputStream)localObject1).readInt()];
      int i = ((DataInputStream)localObject1).readInt();
      int j = ((DataInputStream)localObject1).readInt();
      Object localObject2 = Bitmap.Config.valueOf(((DataInputStream)localObject1).readUTF());
      ((DataInputStream)localObject1).read(arrayOfByte);
      zza((Closeable)localObject1);
      localObject1 = ByteBuffer.wrap(arrayOfByte);
      localObject2 = Bitmap.createBitmap(i, j, (Bitmap.Config)localObject2);
      ((Bitmap)localObject2).copyPixelsFromBuffer((Buffer)localObject1);
      this.zzaiY = ((Bitmap)localObject2);
      this.zzaiZ = true;
      return this.zzaiY;
    }
    catch (IOException localIOException)
    {
      throw new IllegalStateException("Could not read from parcel file descriptor", localIOException);
    }
    finally
    {
      zza((Closeable)localObject1);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\common\data\BitmapTeleporter.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */