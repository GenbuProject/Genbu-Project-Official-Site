package com.google.android.gms.measurement.internal;

import android.app.Service;
import android.content.BroadcastReceiver;
import android.content.ComponentName;
import android.content.Context;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.pm.ServiceInfo;
import android.os.Bundle;
import android.support.annotation.WorkerThread;
import android.text.TextUtils;
import com.google.android.gms.common.internal.zzx;
import com.google.android.gms.internal.zzmq;
import com.google.android.gms.internal.zzqb.zza;
import com.google.android.gms.internal.zzqb.zzb;
import com.google.android.gms.internal.zzqb.zzc;
import com.google.android.gms.internal.zzqb.zzd;
import com.google.android.gms.internal.zzqb.zze;
import com.google.android.gms.internal.zzqb.zzf;
import com.google.android.gms.internal.zzqb.zzg;
import com.google.android.gms.internal.zzsn;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.BitSet;
import java.util.zip.GZIPOutputStream;

public class zzaj
  extends zzy
{
  zzaj(zzw paramzzw)
  {
    super(paramzzw);
  }
  
  public static boolean zzI(Bundle paramBundle)
  {
    return paramBundle.getLong("_c") == 1L;
  }
  
  public static boolean zzQ(String paramString1, String paramString2)
  {
    if ((paramString1 == null) && (paramString2 == null)) {
      return true;
    }
    if (paramString1 == null) {
      return false;
    }
    return paramString1.equals(paramString2);
  }
  
  private Object zza(int paramInt, Object paramObject, boolean paramBoolean)
  {
    Object localObject;
    if (paramObject == null) {
      localObject = null;
    }
    do
    {
      do
      {
        do
        {
          return localObject;
          localObject = paramObject;
        } while ((paramObject instanceof Long));
        localObject = paramObject;
      } while ((paramObject instanceof Float));
      if ((paramObject instanceof Integer)) {
        return Long.valueOf(((Integer)paramObject).intValue());
      }
      if ((paramObject instanceof Byte)) {
        return Long.valueOf(((Byte)paramObject).byteValue());
      }
      if ((paramObject instanceof Short)) {
        return Long.valueOf(((Short)paramObject).shortValue());
      }
      if ((paramObject instanceof Boolean))
      {
        if (((Boolean)paramObject).booleanValue()) {}
        for (long l = 1L;; l = 0L) {
          return Long.valueOf(l);
        }
      }
      if ((paramObject instanceof Double)) {
        return Float.valueOf((float)((Double)paramObject).doubleValue());
      }
      if ((!(paramObject instanceof String)) && (!(paramObject instanceof Character)) && (!(paramObject instanceof CharSequence))) {
        break;
      }
      paramObject = String.valueOf(paramObject);
      localObject = paramObject;
    } while (((String)paramObject).length() <= paramInt);
    if (paramBoolean) {
      return ((String)paramObject).substring(0, paramInt);
    }
    return null;
    return null;
  }
  
  private void zza(String paramString1, String paramString2, int paramInt, Object paramObject)
  {
    if (paramObject == null) {
      zzAo().zzCH().zzj(paramString1 + " value can't be null. Ignoring " + paramString1, paramString2);
    }
    do
    {
      do
      {
        return;
      } while (((paramObject instanceof Long)) || ((paramObject instanceof Float)) || ((paramObject instanceof Integer)) || ((paramObject instanceof Byte)) || ((paramObject instanceof Short)) || ((paramObject instanceof Boolean)) || ((paramObject instanceof Double)) || ((!(paramObject instanceof String)) && (!(paramObject instanceof Character)) && (!(paramObject instanceof CharSequence))));
      paramObject = String.valueOf(paramObject);
    } while (((String)paramObject).length() <= paramInt);
    zzAo().zzCH().zze("Ignoring " + paramString1 + ". Value is too long. name, value length", paramString2, Integer.valueOf(((String)paramObject).length()));
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt)
  {
    int i = 0;
    while (i < paramInt)
    {
      paramStringBuilder.append("  ");
      i += 1;
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzqb.zze paramzze)
  {
    if (paramzze == null) {
      return;
    }
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("bundle {\n");
    zza(paramStringBuilder, paramInt, "protocol_version", paramzze.zzbal);
    zza(paramStringBuilder, paramInt, "platform", paramzze.zzbat);
    zza(paramStringBuilder, paramInt, "gmp_version", paramzze.zzbax);
    zza(paramStringBuilder, paramInt, "uploading_gmp_version", paramzze.zzbay);
    zza(paramStringBuilder, paramInt, "gmp_app_id", paramzze.zzaVt);
    zza(paramStringBuilder, paramInt, "app_id", paramzze.appId);
    zza(paramStringBuilder, paramInt, "app_version", paramzze.zzaMV);
    zza(paramStringBuilder, paramInt, "dev_cert_hash", paramzze.zzbaC);
    zza(paramStringBuilder, paramInt, "app_store", paramzze.zzaVu);
    zza(paramStringBuilder, paramInt, "upload_timestamp_millis", paramzze.zzbao);
    zza(paramStringBuilder, paramInt, "start_timestamp_millis", paramzze.zzbap);
    zza(paramStringBuilder, paramInt, "end_timestamp_millis", paramzze.zzbaq);
    zza(paramStringBuilder, paramInt, "previous_bundle_start_timestamp_millis", paramzze.zzbar);
    zza(paramStringBuilder, paramInt, "previous_bundle_end_timestamp_millis", paramzze.zzbas);
    zza(paramStringBuilder, paramInt, "app_instance_id", paramzze.zzbaB);
    zza(paramStringBuilder, paramInt, "resettable_device_id", paramzze.zzbaz);
    zza(paramStringBuilder, paramInt, "limited_ad_tracking", paramzze.zzbaA);
    zza(paramStringBuilder, paramInt, "os_version", paramzze.osVersion);
    zza(paramStringBuilder, paramInt, "device_model", paramzze.zzbau);
    zza(paramStringBuilder, paramInt, "user_default_language", paramzze.zzbav);
    zza(paramStringBuilder, paramInt, "time_zone_offset_minutes", paramzze.zzbaw);
    zza(paramStringBuilder, paramInt, "bundle_sequential_index", paramzze.zzbaD);
    zza(paramStringBuilder, paramInt, "service_upload", paramzze.zzbaE);
    zza(paramStringBuilder, paramInt, "health_monitor", paramzze.zzaVx);
    zza(paramStringBuilder, paramInt, paramzze.zzban);
    zza(paramStringBuilder, paramInt, paramzze.zzbaF);
    zza(paramStringBuilder, paramInt, paramzze.zzbam);
    zza(paramStringBuilder, paramInt);
    paramStringBuilder.append("}\n");
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, zzqb.zzf paramzzf)
  {
    int j = 0;
    if (paramzzf == null) {
      return;
    }
    int k = paramInt + 1;
    zza(paramStringBuilder, k);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(" {\n");
    int m;
    int i;
    long l;
    if (paramzzf.zzbaH != null)
    {
      zza(paramStringBuilder, k + 1);
      paramStringBuilder.append("results: ");
      paramString = paramzzf.zzbaH;
      m = paramString.length;
      i = 0;
      paramInt = 0;
      while (i < m)
      {
        l = paramString[i];
        if (paramInt != 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append(Long.valueOf(l));
        i += 1;
        paramInt += 1;
      }
      paramStringBuilder.append('\n');
    }
    if (paramzzf.zzbaG != null)
    {
      zza(paramStringBuilder, k + 1);
      paramStringBuilder.append("status: ");
      paramString = paramzzf.zzbaG;
      m = paramString.length;
      paramInt = 0;
      i = j;
      while (i < m)
      {
        l = paramString[i];
        if (paramInt != 0) {
          paramStringBuilder.append(", ");
        }
        paramStringBuilder.append(Long.valueOf(l));
        i += 1;
        paramInt += 1;
      }
      paramStringBuilder.append('\n');
    }
    zza(paramStringBuilder, k);
    paramStringBuilder.append("}\n");
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, String paramString, Object paramObject)
  {
    if (paramObject == null) {
      return;
    }
    zza(paramStringBuilder, paramInt + 1);
    paramStringBuilder.append(paramString);
    paramStringBuilder.append(": ");
    paramStringBuilder.append(paramObject);
    paramStringBuilder.append('\n');
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzqb.zza[] paramArrayOfzza)
  {
    if (paramArrayOfzza == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzza.length;
    paramInt = 0;
    label15:
    zzqb.zza localzza;
    if (paramInt < j)
    {
      localzza = paramArrayOfzza[paramInt];
      if (localzza != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("audience_membership {\n");
      zza(paramStringBuilder, i, "audience_id", localzza.zzaZr);
      zza(paramStringBuilder, i, "new_audience", localzza.zzbac);
      zza(paramStringBuilder, i, "current_data", localzza.zzbaa);
      zza(paramStringBuilder, i, "previous_data", localzza.zzbab);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzqb.zzb[] paramArrayOfzzb)
  {
    if (paramArrayOfzzb == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzzb.length;
    paramInt = 0;
    label15:
    zzqb.zzb localzzb;
    if (paramInt < j)
    {
      localzzb = paramArrayOfzzb[paramInt];
      if (localzzb != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("event {\n");
      zza(paramStringBuilder, i, "name", localzzb.name);
      zza(paramStringBuilder, i, "timestamp_millis", localzzb.zzbaf);
      zza(paramStringBuilder, i, "previous_timestamp_millis", localzzb.zzbag);
      zza(paramStringBuilder, i, "count", localzzb.count);
      zza(paramStringBuilder, i, localzzb.zzbae);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzqb.zzc[] paramArrayOfzzc)
  {
    if (paramArrayOfzzc == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzzc.length;
    paramInt = 0;
    label15:
    zzqb.zzc localzzc;
    if (paramInt < j)
    {
      localzzc = paramArrayOfzzc[paramInt];
      if (localzzc != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("event {\n");
      zza(paramStringBuilder, i, "name", localzzc.name);
      zza(paramStringBuilder, i, "string_value", localzzc.zzamJ);
      zza(paramStringBuilder, i, "int_value", localzzc.zzbai);
      zza(paramStringBuilder, i, "float_value", localzzc.zzaZo);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  private static void zza(StringBuilder paramStringBuilder, int paramInt, zzqb.zzg[] paramArrayOfzzg)
  {
    if (paramArrayOfzzg == null) {
      return;
    }
    int i = paramInt + 1;
    int j = paramArrayOfzzg.length;
    paramInt = 0;
    label15:
    zzqb.zzg localzzg;
    if (paramInt < j)
    {
      localzzg = paramArrayOfzzg[paramInt];
      if (localzzg != null) {
        break label38;
      }
    }
    for (;;)
    {
      paramInt += 1;
      break label15;
      break;
      label38:
      zza(paramStringBuilder, i);
      paramStringBuilder.append("user_property {\n");
      zza(paramStringBuilder, i, "set_timestamp_millis", localzzg.zzbaJ);
      zza(paramStringBuilder, i, "name", localzzg.name);
      zza(paramStringBuilder, i, "string_value", localzzg.zzamJ);
      zza(paramStringBuilder, i, "int_value", localzzg.zzbai);
      zza(paramStringBuilder, i, "float_value", localzzg.zzaZo);
      zza(paramStringBuilder, i);
      paramStringBuilder.append("}\n");
    }
  }
  
  public static boolean zza(Context paramContext, Class<? extends Service> paramClass)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getServiceInfo(new ComponentName(paramContext, paramClass), 4);
      if (paramContext != null)
      {
        boolean bool = paramContext.enabled;
        if (bool) {
          return true;
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean zza(Context paramContext, Class<? extends BroadcastReceiver> paramClass, boolean paramBoolean)
  {
    try
    {
      paramContext = paramContext.getPackageManager().getReceiverInfo(new ComponentName(paramContext, paramClass), 2);
      if ((paramContext != null) && (paramContext.enabled)) {
        if (paramBoolean)
        {
          paramBoolean = paramContext.exported;
          if (!paramBoolean) {}
        }
        else
        {
          return true;
        }
      }
    }
    catch (PackageManager.NameNotFoundException paramContext) {}
    return false;
  }
  
  public static boolean zza(long[] paramArrayOfLong, int paramInt)
  {
    if (paramInt >= paramArrayOfLong.length * 64) {}
    while ((paramArrayOfLong[(paramInt / 64)] & 1L << paramInt % 64) == 0L) {
      return false;
    }
    return true;
  }
  
  public static long[] zza(BitSet paramBitSet)
  {
    int k = (paramBitSet.length() + 63) / 64;
    long[] arrayOfLong = new long[k];
    int i = 0;
    if (i < k)
    {
      arrayOfLong[i] = 0L;
      int j = 0;
      for (;;)
      {
        if ((j >= 64) || (i * 64 + j >= paramBitSet.length()))
        {
          i += 1;
          break;
        }
        if (paramBitSet.get(i * 64 + j)) {
          arrayOfLong[i] |= 1L << j;
        }
        j += 1;
      }
    }
    return arrayOfLong;
  }
  
  public static String zzb(zzqb.zzd paramzzd)
  {
    if (paramzzd == null) {
      return "";
    }
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append("\nbatch {\n");
    if (paramzzd.zzbaj != null)
    {
      paramzzd = paramzzd.zzbaj;
      int j = paramzzd.length;
      int i = 0;
      if (i < j)
      {
        zzqb.zze localzze = paramzzd[i];
        if (localzze == null) {}
        for (;;)
        {
          i += 1;
          break;
          zza(localStringBuilder, 1, localzze);
        }
      }
    }
    localStringBuilder.append("}\n");
    return localStringBuilder.toString();
  }
  
  static MessageDigest zzbv(String paramString)
  {
    int i = 0;
    while (i < 2) {
      try
      {
        MessageDigest localMessageDigest = MessageDigest.getInstance(paramString);
        if (localMessageDigest != null) {
          return localMessageDigest;
        }
      }
      catch (NoSuchAlgorithmException localNoSuchAlgorithmException)
      {
        i += 1;
      }
    }
    return null;
  }
  
  static boolean zzfq(String paramString)
  {
    boolean bool = false;
    zzx.zzcM(paramString);
    if (paramString.charAt(0) != '_') {
      bool = true;
    }
    return bool;
  }
  
  private int zzfu(String paramString)
  {
    if ("_ldl".equals(paramString)) {
      return zzCp().zzBG();
    }
    return zzCp().zzBF();
  }
  
  public static boolean zzfv(String paramString)
  {
    return (!TextUtils.isEmpty(paramString)) && (paramString.startsWith("_"));
  }
  
  static long zzq(byte[] paramArrayOfByte)
  {
    int j = 0;
    zzx.zzz(paramArrayOfByte);
    if (paramArrayOfByte.length > 0) {}
    long l;
    for (boolean bool = true;; bool = false)
    {
      zzx.zzab(bool);
      l = 0L;
      int i = paramArrayOfByte.length - 1;
      while ((i >= 0) && (i >= paramArrayOfByte.length - 8))
      {
        l += ((paramArrayOfByte[i] & 0xFF) << j);
        j += 8;
        i -= 1;
      }
    }
    return l;
  }
  
  public void zza(Bundle paramBundle, String paramString, Object paramObject)
  {
    if ((paramObject instanceof Long)) {
      paramBundle.putLong(paramString, ((Long)paramObject).longValue());
    }
    do
    {
      return;
      if ((paramObject instanceof Float))
      {
        paramBundle.putFloat(paramString, ((Float)paramObject).floatValue());
        return;
      }
      if ((paramObject instanceof String))
      {
        paramBundle.putString(paramString, String.valueOf(paramObject));
        return;
      }
    } while (paramString == null);
    zzAo().zzCH().zze("Not putting event parameter. Invalid value type. name, type", paramString, paramObject.getClass().getSimpleName());
  }
  
  public void zza(zzqb.zzc paramzzc, Object paramObject)
  {
    zzx.zzz(paramObject);
    paramzzc.zzamJ = null;
    paramzzc.zzbai = null;
    paramzzc.zzaZo = null;
    if ((paramObject instanceof String))
    {
      paramzzc.zzamJ = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Long))
    {
      paramzzc.zzbai = ((Long)paramObject);
      return;
    }
    if ((paramObject instanceof Float))
    {
      paramzzc.zzaZo = ((Float)paramObject);
      return;
    }
    zzAo().zzCE().zzj("Ignoring invalid (type) event param value", paramObject);
  }
  
  public void zza(zzqb.zzg paramzzg, Object paramObject)
  {
    zzx.zzz(paramObject);
    paramzzg.zzamJ = null;
    paramzzg.zzbai = null;
    paramzzg.zzaZo = null;
    if ((paramObject instanceof String))
    {
      paramzzg.zzamJ = ((String)paramObject);
      return;
    }
    if ((paramObject instanceof Long))
    {
      paramzzg.zzbai = ((Long)paramObject);
      return;
    }
    if ((paramObject instanceof Float))
    {
      paramzzg.zzaZo = ((Float)paramObject);
      return;
    }
    zzAo().zzCE().zzj("Ignoring invalid (type) user attribute value", paramObject);
  }
  
  public byte[] zza(zzqb.zzd paramzzd)
  {
    try
    {
      byte[] arrayOfByte = new byte[paramzzd.getSerializedSize()];
      zzsn localzzsn = zzsn.zzE(arrayOfByte);
      paramzzd.writeTo(localzzsn);
      localzzsn.zzJo();
      return arrayOfByte;
    }
    catch (IOException paramzzd)
    {
      zzAo().zzCE().zzj("Data loss. Failed to serialize batch", paramzzd);
    }
    return null;
  }
  
  @WorkerThread
  public boolean zzbk(String paramString)
  {
    zzjk();
    if (getContext().checkCallingOrSelfPermission(paramString) == 0) {
      return true;
    }
    zzAo().zzCJ().zzj("Permission not granted", paramString);
    return false;
  }
  
  void zzc(String paramString1, int paramInt, String paramString2)
  {
    if (paramString2 == null) {
      throw new IllegalArgumentException(paramString1 + " name is required and can't be null");
    }
    if (paramString2.length() == 0) {
      throw new IllegalArgumentException(paramString1 + " name is required and can't be empty");
    }
    char c = paramString2.charAt(0);
    if ((!Character.isLetter(c)) && (c != '_')) {
      throw new IllegalArgumentException(paramString1 + " name must start with a letter or _");
    }
    int i = 1;
    while (i < paramString2.length())
    {
      c = paramString2.charAt(i);
      if ((c != '_') && (!Character.isLetterOrDigit(c))) {
        throw new IllegalArgumentException(paramString1 + " name must consist of letters, digits or _ (underscores)");
      }
      i += 1;
    }
    if (paramString2.length() > paramInt) {
      throw new IllegalArgumentException(paramString1 + " name is too long. The maximum supported length is " + paramInt);
    }
  }
  
  public boolean zzc(long paramLong1, long paramLong2)
  {
    if ((paramLong1 == 0L) || (paramLong2 <= 0L)) {}
    while (Math.abs(zzjl().currentTimeMillis() - paramLong1) > paramLong2) {
      return true;
    }
    return false;
  }
  
  public void zzfr(String paramString)
  {
    zzc("event", zzCp().zzBB(), paramString);
  }
  
  public void zzfs(String paramString)
  {
    zzc("user attribute", zzCp().zzBC(), paramString);
  }
  
  public void zzft(String paramString)
  {
    zzc("event param", zzCp().zzBC(), paramString);
  }
  
  public byte[] zzg(byte[] paramArrayOfByte)
    throws IOException
  {
    try
    {
      ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
      GZIPOutputStream localGZIPOutputStream = new GZIPOutputStream(localByteArrayOutputStream);
      localGZIPOutputStream.write(paramArrayOfByte);
      localGZIPOutputStream.close();
      localByteArrayOutputStream.close();
      paramArrayOfByte = localByteArrayOutputStream.toByteArray();
      return paramArrayOfByte;
    }
    catch (IOException paramArrayOfByte)
    {
      zzAo().zzCE().zzj("Failed to gzip content", paramArrayOfByte);
      throw paramArrayOfByte;
    }
  }
  
  public Object zzk(String paramString, Object paramObject)
  {
    if (zzfv(paramString)) {}
    for (int i = zzCp().zzBE();; i = zzCp().zzBD()) {
      return zza(i, paramObject, false);
    }
  }
  
  public void zzl(String paramString, Object paramObject)
  {
    if ("_ldl".equals(paramString))
    {
      zza("user attribute referrer", paramString, zzfu(paramString), paramObject);
      return;
    }
    zza("user attribute", paramString, zzfu(paramString), paramObject);
  }
  
  public Object zzm(String paramString, Object paramObject)
  {
    if ("_ldl".equals(paramString)) {
      return zza(zzfu(paramString), paramObject, true);
    }
    return zza(zzfu(paramString), paramObject, false);
  }
  
  /* Error */
  public byte[] zzp(byte[] paramArrayOfByte)
    throws IOException
  {
    // Byte code:
    //   0: new 770	java/io/ByteArrayInputStream
    //   3: dup
    //   4: aload_1
    //   5: invokespecial 772	java/io/ByteArrayInputStream:<init>	([B)V
    //   8: astore_1
    //   9: new 774	java/util/zip/GZIPInputStream
    //   12: dup
    //   13: aload_1
    //   14: invokespecial 777	java/util/zip/GZIPInputStream:<init>	(Ljava/io/InputStream;)V
    //   17: astore_3
    //   18: new 721	java/io/ByteArrayOutputStream
    //   21: dup
    //   22: invokespecial 722	java/io/ByteArrayOutputStream:<init>	()V
    //   25: astore 4
    //   27: sipush 1024
    //   30: newarray <illegal type>
    //   32: astore 5
    //   34: aload_3
    //   35: aload 5
    //   37: invokevirtual 781	java/util/zip/GZIPInputStream:read	([B)I
    //   40: istore_2
    //   41: iload_2
    //   42: ifgt +17 -> 59
    //   45: aload_3
    //   46: invokevirtual 782	java/util/zip/GZIPInputStream:close	()V
    //   49: aload_1
    //   50: invokevirtual 783	java/io/ByteArrayInputStream:close	()V
    //   53: aload 4
    //   55: invokevirtual 739	java/io/ByteArrayOutputStream:toByteArray	()[B
    //   58: areturn
    //   59: aload 4
    //   61: aload 5
    //   63: iconst_0
    //   64: iload_2
    //   65: invokevirtual 786	java/io/ByteArrayOutputStream:write	([BII)V
    //   68: goto -34 -> 34
    //   71: astore_1
    //   72: aload_0
    //   73: invokevirtual 89	com/google/android/gms/measurement/internal/zzaj:zzAo	()Lcom/google/android/gms/measurement/internal/zzp;
    //   76: invokevirtual 616	com/google/android/gms/measurement/internal/zzp:zzCE	()Lcom/google/android/gms/measurement/internal/zzp$zza;
    //   79: ldc_w 788
    //   82: aload_1
    //   83: invokevirtual 116	com/google/android/gms/measurement/internal/zzp$zza:zzj	(Ljava/lang/String;Ljava/lang/Object;)V
    //   86: aload_1
    //   87: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	88	0	this	zzaj
    //   0	88	1	paramArrayOfByte	byte[]
    //   40	25	2	i	int
    //   17	29	3	localGZIPInputStream	java.util.zip.GZIPInputStream
    //   25	35	4	localByteArrayOutputStream	ByteArrayOutputStream
    //   32	30	5	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   0	34	71	java/io/IOException
    //   34	41	71	java/io/IOException
    //   45	59	71	java/io/IOException
    //   59	68	71	java/io/IOException
  }
  
  public long zzr(byte[] paramArrayOfByte)
  {
    zzx.zzz(paramArrayOfByte);
    MessageDigest localMessageDigest = zzbv("MD5");
    if (localMessageDigest == null)
    {
      zzAo().zzCE().zzfg("Failed to get MD5");
      return 0L;
    }
    return zzq(localMessageDigest.digest(paramArrayOfByte));
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\measurement\internal\zzaj.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */