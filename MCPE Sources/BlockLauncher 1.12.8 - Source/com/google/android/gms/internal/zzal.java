package com.google.android.gms.internal;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.MotionEvent;
import com.google.ads.afma.nano.NanoAdshieldEvent.AdShieldEvent;
import com.google.android.gms.clearcut.zzb;
import com.google.android.gms.clearcut.zzb.zza;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.common.api.GoogleApiClient.Builder;
import com.google.android.gms.common.zzc;
import dalvik.system.DexClassLoader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.nio.ByteBuffer;
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.TimeUnit;

public abstract class zzal
  extends zzak
{
  private static long startTime = 0L;
  private static Method zznH;
  private static Method zznI;
  private static Method zznJ;
  private static Method zznK;
  private static Method zznL;
  private static Method zznM;
  private static Method zznN;
  private static Method zznO;
  private static Method zznP;
  private static Method zznQ;
  private static Method zznR;
  private static Method zznS;
  private static Method zznT;
  private static String zznU;
  private static String zznV;
  private static String zznW;
  private static zzaq zznX;
  static boolean zznY = false;
  protected static zzb zznZ = null;
  protected static NanoAdshieldEvent.AdShieldEvent zzoa;
  protected static int zzob;
  private static Random zzoc = new Random();
  private static zzc zzod = zzc.zzoK();
  private static boolean zzoe;
  protected static boolean zzof = false;
  protected static boolean zzog = false;
  protected static boolean zzoh = false;
  protected static boolean zzoi = false;
  private static boolean zzoj = false;
  
  protected zzal(Context paramContext, zzap paramzzap)
  {
    super(paramContext, paramzzap);
    zzoa = new NanoAdshieldEvent.AdShieldEvent();
    zzoa.appId = paramContext.getPackageName();
  }
  
  private void zzT()
  {
    if ((zzoi) && (zznZ != null))
    {
      zznZ.zza(zznG, 100L, TimeUnit.MILLISECONDS);
      zznG.disconnect();
    }
  }
  
  static String zzU()
    throws zzal.zza
  {
    if (zznU == null) {
      throw new zza();
    }
    return zznU;
  }
  
  static Long zzV()
    throws zzal.zza
  {
    if (zznH == null) {
      throw new zza();
    }
    try
    {
      Long localLong = (Long)zznH.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new zza(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new zza(localInvocationTargetException);
    }
  }
  
  static String zzW()
    throws zzal.zza
  {
    if (zznJ == null) {
      throw new zza();
    }
    try
    {
      String str = (String)zznJ.invoke(null, new Object[0]);
      return str;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new zza(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new zza(localInvocationTargetException);
    }
  }
  
  static Long zzX()
    throws zzal.zza
  {
    if (zznI == null) {
      throw new zza();
    }
    try
    {
      Long localLong = (Long)zznI.invoke(null, new Object[0]);
      return localLong;
    }
    catch (IllegalAccessException localIllegalAccessException)
    {
      throw new zza(localIllegalAccessException);
    }
    catch (InvocationTargetException localInvocationTargetException)
    {
      throw new zza(localInvocationTargetException);
    }
  }
  
  static String zza(Context paramContext, zzap paramzzap)
    throws zzal.zza
  {
    if (zznV != null) {
      return zznV;
    }
    if (zznK == null) {
      throw new zza();
    }
    try
    {
      paramContext = (ByteBuffer)zznK.invoke(null, new Object[] { paramContext });
      if (paramContext == null) {
        throw new zza();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
      zznV = paramzzap.zza(paramContext.array(), true);
      paramContext = zznV;
      return paramContext;
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  static ArrayList<Long> zza(MotionEvent paramMotionEvent, DisplayMetrics paramDisplayMetrics)
    throws zzal.zza
  {
    if ((zznL == null) || (paramMotionEvent == null)) {
      throw new zza();
    }
    try
    {
      paramMotionEvent = (ArrayList)zznL.invoke(null, new Object[] { paramMotionEvent, paramDisplayMetrics });
      return paramMotionEvent;
    }
    catch (IllegalAccessException paramMotionEvent)
    {
      throw new zza(paramMotionEvent);
    }
    catch (InvocationTargetException paramMotionEvent)
    {
      throw new zza(paramMotionEvent);
    }
  }
  
  protected static void zza(int paramInt1, int paramInt2)
    throws IOException
  {
    if ((zzoi) && (zzof) && (zznZ != null))
    {
      zzb.zza localzza = zznZ.zzi(zzsu.toByteArray(zzoa));
      localzza.zzbr(paramInt2);
      localzza.zzbq(paramInt1);
      localzza.zzd(zznG);
    }
  }
  
  protected static void zza(String paramString, Context paramContext, zzap paramzzap)
  {
    for (;;)
    {
      boolean bool;
      try
      {
        bool = zznY;
        if (bool) {}
      }
      finally {}
      try
      {
        zznX = new zzaq(paramzzap, null);
        zznU = paramString;
        zzbt.initialize(paramContext);
        zzm(paramContext);
        startTime = zzV().longValue();
        zzoc = new Random();
      }
      catch (UnsupportedOperationException paramString)
      {
        continue;
      }
      catch (zza paramString)
      {
        continue;
      }
      try
      {
        zznG = new GoogleApiClient.Builder(paramContext).addApi(zzb.API).build();
        zzod = zzc.zzoK();
        if (zzod.isGooglePlayServicesAvailable(paramContext) != 0) {
          continue;
        }
        bool = true;
        zzoe = bool;
        zzbt.initialize(paramContext);
        zzof = ((Boolean)zzbt.zzwZ.get()).booleanValue();
        zznZ = new zzb(paramContext, "ADSHIELD", null, null);
      }
      catch (NoClassDefFoundError paramString) {}
    }
    if (zzod.zzaj(paramContext) > 0) {}
    for (bool = true;; bool = false)
    {
      zzoj = bool;
      zznY = true;
      return;
      bool = false;
      break;
    }
  }
  
  static String zzb(Context paramContext, zzap paramzzap)
    throws zzal.zza
  {
    if (zznW != null) {
      return zznW;
    }
    if (zznN == null) {
      throw new zza();
    }
    try
    {
      paramContext = (ByteBuffer)zznN.invoke(null, new Object[] { paramContext });
      if (paramContext == null) {
        throw new zza();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
      zznW = paramzzap.zza(paramContext.array(), true);
      paramContext = zznW;
      return paramContext;
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  private static String zzb(byte[] paramArrayOfByte, String paramString)
    throws zzal.zza
  {
    try
    {
      paramArrayOfByte = new String(zznX.zzc(paramArrayOfByte, paramString), "UTF-8");
      return paramArrayOfByte;
    }
    catch (zzaq.zza paramArrayOfByte)
    {
      throw new zza(paramArrayOfByte);
    }
    catch (UnsupportedEncodingException paramArrayOfByte)
    {
      throw new zza(paramArrayOfByte);
    }
  }
  
  private void zze(Context paramContext)
  {
    if (zzoe)
    {
      zznG.connect();
      zzoi = true;
      return;
    }
    zzoi = false;
  }
  
  static String zzf(Context paramContext)
    throws zzal.zza
  {
    if (zznM == null) {
      throw new zza();
    }
    try
    {
      paramContext = (String)zznM.invoke(null, new Object[] { paramContext });
      if (paramContext == null) {
        throw new zza();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
    return paramContext;
  }
  
  static String zzg(Context paramContext)
    throws zzal.zza
  {
    if (zznQ == null) {
      throw new zza();
    }
    try
    {
      paramContext = (String)zznQ.invoke(null, new Object[] { paramContext });
      return paramContext;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  static Long zzh(Context paramContext)
    throws zzal.zza
  {
    if (zznR == null) {
      throw new zza();
    }
    try
    {
      paramContext = (Long)zznR.invoke(null, new Object[] { paramContext });
      return paramContext;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  static ArrayList<Long> zzi(Context paramContext)
    throws zzal.zza
  {
    if (zznO == null) {
      throw new zza();
    }
    try
    {
      paramContext = (ArrayList)zznO.invoke(null, new Object[] { paramContext });
      if ((paramContext == null) || (paramContext.size() != 2)) {
        throw new zza();
      }
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
    return paramContext;
  }
  
  static int[] zzj(Context paramContext)
    throws zzal.zza
  {
    if (zznP == null) {
      throw new zza();
    }
    try
    {
      paramContext = (int[])zznP.invoke(null, new Object[] { paramContext });
      return paramContext;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  static int zzk(Context paramContext)
    throws zzal.zza
  {
    if (zznS == null) {
      throw new zza();
    }
    try
    {
      int i = ((Integer)zznS.invoke(null, new Object[] { paramContext })).intValue();
      return i;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  static int zzl(Context paramContext)
    throws zzal.zza
  {
    if (zznT == null) {
      throw new zza();
    }
    try
    {
      int i = ((Integer)zznT.invoke(null, new Object[] { paramContext })).intValue();
      return i;
    }
    catch (IllegalAccessException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (InvocationTargetException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  private static void zzm(Context paramContext)
    throws zzal.zza
  {
    try
    {
      localObject1 = zznX.zzl(zzar.getKey());
      localObject2 = zznX.zzc((byte[])localObject1, zzar.zzac());
      localFile2 = paramContext.getCacheDir();
      localFile1 = localFile2;
      if (localFile2 == null)
      {
        localFile2 = paramContext.getDir("dex", 0);
        localFile1 = localFile2;
        if (localFile2 == null) {
          throw new zza();
        }
      }
    }
    catch (FileNotFoundException paramContext)
    {
      Object localObject1;
      Object localObject2;
      File localFile1;
      throw new zza(paramContext);
      File localFile2 = File.createTempFile("ads", ".jar", localFile1);
      Object localObject3 = new FileOutputStream(localFile2);
      ((FileOutputStream)localObject3).write((byte[])localObject2, 0, localObject2.length);
      ((FileOutputStream)localObject3).close();
      try
      {
        Object localObject4 = new DexClassLoader(localFile2.getAbsolutePath(), localFile1.getAbsolutePath(), null, paramContext.getClassLoader());
        paramContext = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzal()));
        localObject2 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzaz()));
        localObject3 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzat()));
        Class localClass1 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzap()));
        Class localClass2 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzaB()));
        Class localClass3 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzan()));
        Class localClass4 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzax()));
        Class localClass5 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzav()));
        Class localClass6 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzaj()));
        Class localClass7 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzah()));
        Class localClass8 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzaf()));
        Class localClass9 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzar()));
        localObject4 = ((DexClassLoader)localObject4).loadClass(zzb((byte[])localObject1, zzar.zzad()));
        zznH = paramContext.getMethod(zzb((byte[])localObject1, zzar.zzam()), new Class[0]);
        zznI = ((Class)localObject2).getMethod(zzb((byte[])localObject1, zzar.zzaA()), new Class[0]);
        zznJ = ((Class)localObject3).getMethod(zzb((byte[])localObject1, zzar.zzau()), new Class[0]);
        zznK = localClass1.getMethod(zzb((byte[])localObject1, zzar.zzaq()), new Class[] { Context.class });
        zznL = localClass2.getMethod(zzb((byte[])localObject1, zzar.zzaC()), new Class[] { MotionEvent.class, DisplayMetrics.class });
        zznM = localClass3.getMethod(zzb((byte[])localObject1, zzar.zzao()), new Class[] { Context.class });
        zznN = localClass4.getMethod(zzb((byte[])localObject1, zzar.zzay()), new Class[] { Context.class });
        zznO = localClass5.getMethod(zzb((byte[])localObject1, zzar.zzaw()), new Class[] { Context.class });
        zznP = localClass6.getMethod(zzb((byte[])localObject1, zzar.zzak()), new Class[] { Context.class });
        zznQ = localClass7.getMethod(zzb((byte[])localObject1, zzar.zzai()), new Class[] { Context.class });
        zznR = localClass8.getMethod(zzb((byte[])localObject1, zzar.zzag()), new Class[] { Context.class });
        zznS = localClass9.getMethod(zzb((byte[])localObject1, zzar.zzas()), new Class[] { Context.class });
        zznT = ((Class)localObject4).getMethod(zzb((byte[])localObject1, zzar.zzae()), new Class[] { Context.class });
        return;
      }
      finally
      {
        localObject1 = localFile2.getName();
        localFile2.delete();
        new File(localFile1, ((String)localObject1).replace(".jar", ".dex")).delete();
      }
    }
    catch (IOException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (ClassNotFoundException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (zzaq.zza paramContext)
    {
      throw new zza(paramContext);
    }
    catch (NoSuchMethodException paramContext)
    {
      throw new zza(paramContext);
    }
    catch (NullPointerException paramContext)
    {
      throw new zza(paramContext);
    }
  }
  
  protected boolean zzS()
  {
    return zzoj;
  }
  
  /* Error */
  protected com.google.ads.afma.nano.NanoAfmaSignals.AFMASignals zzc(Context paramContext)
  {
    // Byte code:
    //   0: new 511	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals
    //   3: dup
    //   4: invokespecial 512	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:<init>	()V
    //   7: astore 5
    //   9: aload_0
    //   10: aload_1
    //   11: invokespecial 514	com/google/android/gms/internal/zzal:zze	(Landroid/content/Context;)V
    //   14: getstatic 62	com/google/android/gms/internal/zzal:zzoc	Ljava/util/Random;
    //   17: invokevirtual 517	java/util/Random:nextInt	()I
    //   20: putstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   23: iconst_0
    //   24: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   27: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   30: aload 5
    //   32: invokestatic 523	com/google/android/gms/internal/zzal:zzW	()Ljava/lang/String;
    //   35: putfield 526	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:osVersion	Ljava/lang/String;
    //   38: iconst_1
    //   39: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   42: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   45: aload 5
    //   47: invokestatic 528	com/google/android/gms/internal/zzal:zzU	()Ljava/lang/String;
    //   50: putfield 531	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:afmaVersion	Ljava/lang/String;
    //   53: iconst_2
    //   54: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   57: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   60: invokestatic 227	com/google/android/gms/internal/zzal:zzV	()Ljava/lang/Long;
    //   63: invokevirtual 231	java/lang/Long:longValue	()J
    //   66: lstore_2
    //   67: aload 5
    //   69: lload_2
    //   70: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   73: putfield 539	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:evtTime	Ljava/lang/Long;
    //   76: getstatic 51	com/google/android/gms/internal/zzal:startTime	J
    //   79: lconst_0
    //   80: lcmp
    //   81: ifeq +27 -> 108
    //   84: aload 5
    //   86: lload_2
    //   87: getstatic 51	com/google/android/gms/internal/zzal:startTime	J
    //   90: lsub
    //   91: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   94: putfield 542	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:uptSignal	Ljava/lang/Long;
    //   97: aload 5
    //   99: getstatic 51	com/google/android/gms/internal/zzal:startTime	J
    //   102: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   105: putfield 545	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:usgSignal	Ljava/lang/Long;
    //   108: bipush 25
    //   110: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   113: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   116: aload_1
    //   117: invokestatic 547	com/google/android/gms/internal/zzal:zzi	(Landroid/content/Context;)Ljava/util/ArrayList;
    //   120: astore 6
    //   122: aload 5
    //   124: aload 6
    //   126: iconst_0
    //   127: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   130: checkcast 144	java/lang/Long
    //   133: invokevirtual 231	java/lang/Long:longValue	()J
    //   136: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   139: putfield 553	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:uwSignal	Ljava/lang/Long;
    //   142: aload 5
    //   144: aload 6
    //   146: iconst_1
    //   147: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   150: checkcast 144	java/lang/Long
    //   153: invokevirtual 231	java/lang/Long:longValue	()J
    //   156: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   159: putfield 556	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:uhSignal	Ljava/lang/Long;
    //   162: bipush 31
    //   164: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   167: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   170: aload 5
    //   172: invokestatic 558	com/google/android/gms/internal/zzal:zzX	()Ljava/lang/Long;
    //   175: putfield 561	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:utzSignal	Ljava/lang/Long;
    //   178: bipush 33
    //   180: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   183: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   186: getstatic 74	com/google/android/gms/internal/zzal:zzog	Z
    //   189: ifeq +155 -> 344
    //   192: getstatic 76	com/google/android/gms/internal/zzal:zzoh	Z
    //   195: istore 4
    //   197: iload 4
    //   199: ifeq +145 -> 344
    //   202: aload 5
    //   204: aload_1
    //   205: aload_0
    //   206: getfield 565	com/google/android/gms/internal/zzal:zznF	Lcom/google/android/gms/internal/zzap;
    //   209: invokestatic 567	com/google/android/gms/internal/zzal:zzb	(Landroid/content/Context;Lcom/google/android/gms/internal/zzap;)Ljava/lang/String;
    //   212: putfield 570	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:cerSignal	Ljava/lang/String;
    //   215: bipush 29
    //   217: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   220: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   223: aload_1
    //   224: invokestatic 572	com/google/android/gms/internal/zzal:zzj	(Landroid/content/Context;)[I
    //   227: astore 6
    //   229: aload 5
    //   231: aload 6
    //   233: iconst_0
    //   234: iaload
    //   235: i2l
    //   236: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   239: putfield 575	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:btsSignal	Ljava/lang/Long;
    //   242: aload 5
    //   244: aload 6
    //   246: iconst_1
    //   247: iaload
    //   248: i2l
    //   249: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   252: putfield 578	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:btlSignal	Ljava/lang/Long;
    //   255: iconst_5
    //   256: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   259: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   262: aload 5
    //   264: aload_1
    //   265: invokestatic 580	com/google/android/gms/internal/zzal:zzk	(Landroid/content/Context;)I
    //   268: i2l
    //   269: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   272: putfield 583	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:ornSignal	Ljava/lang/Long;
    //   275: bipush 12
    //   277: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   280: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   283: aload 5
    //   285: aload_1
    //   286: invokestatic 585	com/google/android/gms/internal/zzal:zzl	(Landroid/content/Context;)I
    //   289: i2l
    //   290: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   293: putfield 588	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:atvSignal	Ljava/lang/Long;
    //   296: iconst_3
    //   297: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   300: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   303: aload 5
    //   305: aload_1
    //   306: invokestatic 590	com/google/android/gms/internal/zzal:zzg	(Landroid/content/Context;)Ljava/lang/String;
    //   309: putfield 593	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:vnmSignal	Ljava/lang/String;
    //   312: bipush 34
    //   314: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   317: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   320: aload 5
    //   322: aload_1
    //   323: invokestatic 595	com/google/android/gms/internal/zzal:zzh	(Landroid/content/Context;)Ljava/lang/Long;
    //   326: putfield 598	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:vcdSignal	Ljava/lang/Long;
    //   329: bipush 35
    //   331: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   334: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   337: aload_0
    //   338: invokespecial 600	com/google/android/gms/internal/zzal:zzT	()V
    //   341: aload 5
    //   343: areturn
    //   344: aload 5
    //   346: aload_1
    //   347: aload_0
    //   348: getfield 565	com/google/android/gms/internal/zzal:zznF	Lcom/google/android/gms/internal/zzap;
    //   351: invokestatic 602	com/google/android/gms/internal/zzal:zza	(Landroid/content/Context;Lcom/google/android/gms/internal/zzap;)Ljava/lang/String;
    //   354: putfield 605	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:intSignal	Ljava/lang/String;
    //   357: bipush 27
    //   359: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   362: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   365: goto -163 -> 202
    //   368: astore 6
    //   370: goto -168 -> 202
    //   373: astore_1
    //   374: aload 5
    //   376: areturn
    //   377: astore_1
    //   378: goto -41 -> 337
    //   381: astore 6
    //   383: goto -63 -> 320
    //   386: astore 6
    //   388: goto -85 -> 303
    //   391: astore 6
    //   393: goto -110 -> 283
    //   396: astore 6
    //   398: goto -136 -> 262
    //   401: astore 6
    //   403: goto -180 -> 223
    //   406: astore 6
    //   408: goto -222 -> 186
    //   411: astore 6
    //   413: goto -243 -> 170
    //   416: astore 6
    //   418: goto -302 -> 116
    //   421: astore 6
    //   423: goto -363 -> 60
    //   426: astore 6
    //   428: goto -383 -> 45
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	431	0	this	zzal
    //   0	431	1	paramContext	Context
    //   66	21	2	l	long
    //   195	3	4	bool	boolean
    //   7	368	5	localAFMASignals	com.google.ads.afma.nano.NanoAfmaSignals.AFMASignals
    //   120	125	6	localObject	Object
    //   368	1	6	localzza1	zza
    //   381	1	6	localzza2	zza
    //   386	1	6	localzza3	zza
    //   391	1	6	localzza4	zza
    //   396	1	6	localzza5	zza
    //   401	1	6	localzza6	zza
    //   406	1	6	localzza7	zza
    //   411	1	6	localzza8	zza
    //   416	1	6	localzza9	zza
    //   421	1	6	localzza10	zza
    //   426	1	6	localzza11	zza
    // Exception table:
    //   from	to	target	type
    //   186	197	368	com/google/android/gms/internal/zzal$zza
    //   344	365	368	com/google/android/gms/internal/zzal$zza
    //   9	30	373	java/io/IOException
    //   30	45	373	java/io/IOException
    //   45	60	373	java/io/IOException
    //   60	108	373	java/io/IOException
    //   108	116	373	java/io/IOException
    //   116	170	373	java/io/IOException
    //   170	186	373	java/io/IOException
    //   186	197	373	java/io/IOException
    //   202	223	373	java/io/IOException
    //   223	262	373	java/io/IOException
    //   262	283	373	java/io/IOException
    //   283	303	373	java/io/IOException
    //   303	320	373	java/io/IOException
    //   320	337	373	java/io/IOException
    //   337	341	373	java/io/IOException
    //   344	365	373	java/io/IOException
    //   320	337	377	com/google/android/gms/internal/zzal$zza
    //   303	320	381	com/google/android/gms/internal/zzal$zza
    //   283	303	386	com/google/android/gms/internal/zzal$zza
    //   262	283	391	com/google/android/gms/internal/zzal$zza
    //   223	262	396	com/google/android/gms/internal/zzal$zza
    //   202	223	401	com/google/android/gms/internal/zzal$zza
    //   170	186	406	com/google/android/gms/internal/zzal$zza
    //   116	170	411	com/google/android/gms/internal/zzal$zza
    //   60	108	416	com/google/android/gms/internal/zzal$zza
    //   108	116	416	com/google/android/gms/internal/zzal$zza
    //   45	60	421	com/google/android/gms/internal/zzal$zza
    //   30	45	426	com/google/android/gms/internal/zzal$zza
  }
  
  /* Error */
  protected com.google.ads.afma.nano.NanoAfmaSignals.AFMASignals zzd(Context paramContext)
  {
    // Byte code:
    //   0: iconst_0
    //   1: istore_2
    //   2: new 511	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals
    //   5: dup
    //   6: invokespecial 512	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:<init>	()V
    //   9: astore 4
    //   11: aload_0
    //   12: aload_1
    //   13: invokespecial 514	com/google/android/gms/internal/zzal:zze	(Landroid/content/Context;)V
    //   16: getstatic 62	com/google/android/gms/internal/zzal:zzoc	Ljava/util/Random;
    //   19: invokevirtual 517	java/util/Random:nextInt	()I
    //   22: putstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   25: aload 4
    //   27: invokestatic 528	com/google/android/gms/internal/zzal:zzU	()Ljava/lang/String;
    //   30: putfield 531	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:afmaVersion	Ljava/lang/String;
    //   33: aload 4
    //   35: invokestatic 523	com/google/android/gms/internal/zzal:zzW	()Ljava/lang/String;
    //   38: putfield 526	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:osVersion	Ljava/lang/String;
    //   41: aload 4
    //   43: invokestatic 227	com/google/android/gms/internal/zzal:zzV	()Ljava/lang/Long;
    //   46: putfield 539	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:evtTime	Ljava/lang/Long;
    //   49: iconst_0
    //   50: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   53: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   56: aload_0
    //   57: getfield 609	com/google/android/gms/internal/zzal:zznx	Landroid/view/MotionEvent;
    //   60: aload_0
    //   61: getfield 613	com/google/android/gms/internal/zzal:zznE	Landroid/util/DisplayMetrics;
    //   64: invokestatic 615	com/google/android/gms/internal/zzal:zza	(Landroid/view/MotionEvent;Landroid/util/DisplayMetrics;)Ljava/util/ArrayList;
    //   67: astore 5
    //   69: aload 4
    //   71: aload 5
    //   73: iconst_0
    //   74: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   77: checkcast 144	java/lang/Long
    //   80: putfield 618	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tcxSignal	Ljava/lang/Long;
    //   83: aload 4
    //   85: aload 5
    //   87: iconst_1
    //   88: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   91: checkcast 144	java/lang/Long
    //   94: putfield 621	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tcySignal	Ljava/lang/Long;
    //   97: aload 5
    //   99: iconst_2
    //   100: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   103: checkcast 144	java/lang/Long
    //   106: invokevirtual 231	java/lang/Long:longValue	()J
    //   109: lconst_0
    //   110: lcmp
    //   111: iflt +17 -> 128
    //   114: aload 4
    //   116: aload 5
    //   118: iconst_2
    //   119: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   122: checkcast 144	java/lang/Long
    //   125: putfield 624	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tctSignal	Ljava/lang/Long;
    //   128: aload 4
    //   130: aload 5
    //   132: iconst_3
    //   133: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   136: checkcast 144	java/lang/Long
    //   139: putfield 627	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tcpSignal	Ljava/lang/Long;
    //   142: aload 4
    //   144: aload 5
    //   146: iconst_4
    //   147: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   150: checkcast 144	java/lang/Long
    //   153: putfield 630	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tcdSignal	Ljava/lang/Long;
    //   156: bipush 14
    //   158: getstatic 519	com/google/android/gms/internal/zzal:zzob	I
    //   161: invokestatic 521	com/google/android/gms/internal/zzal:zza	(II)V
    //   164: aload_0
    //   165: getfield 633	com/google/android/gms/internal/zzal:zznz	J
    //   168: lconst_0
    //   169: lcmp
    //   170: ifle +15 -> 185
    //   173: aload 4
    //   175: aload_0
    //   176: getfield 633	com/google/android/gms/internal/zzal:zznz	J
    //   179: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   182: putfield 636	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tcdnSignal	Ljava/lang/Long;
    //   185: aload_0
    //   186: getfield 639	com/google/android/gms/internal/zzal:zznA	J
    //   189: lconst_0
    //   190: lcmp
    //   191: ifle +15 -> 206
    //   194: aload 4
    //   196: aload_0
    //   197: getfield 639	com/google/android/gms/internal/zzal:zznA	J
    //   200: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   203: putfield 642	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tcmSignal	Ljava/lang/Long;
    //   206: aload_0
    //   207: getfield 645	com/google/android/gms/internal/zzal:zznB	J
    //   210: lconst_0
    //   211: lcmp
    //   212: ifle +15 -> 227
    //   215: aload 4
    //   217: aload_0
    //   218: getfield 645	com/google/android/gms/internal/zzal:zznB	J
    //   221: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   224: putfield 648	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tcuSignal	Ljava/lang/Long;
    //   227: aload_0
    //   228: getfield 651	com/google/android/gms/internal/zzal:zznC	J
    //   231: lconst_0
    //   232: lcmp
    //   233: ifle +15 -> 248
    //   236: aload 4
    //   238: aload_0
    //   239: getfield 651	com/google/android/gms/internal/zzal:zznC	J
    //   242: invokestatic 535	java/lang/Long:valueOf	(J)Ljava/lang/Long;
    //   245: putfield 654	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:tccSignal	Ljava/lang/Long;
    //   248: aload_0
    //   249: getfield 658	com/google/android/gms/internal/zzal:zzny	Ljava/util/LinkedList;
    //   252: invokevirtual 661	java/util/LinkedList:size	()I
    //   255: iconst_1
    //   256: isub
    //   257: istore_3
    //   258: iload_3
    //   259: ifle +98 -> 357
    //   262: aload 4
    //   264: iload_3
    //   265: anewarray 663	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo
    //   268: putfield 667	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:previousTouches	[Lcom/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo;
    //   271: iload_2
    //   272: iload_3
    //   273: if_icmpge +84 -> 357
    //   276: aload_0
    //   277: getfield 658	com/google/android/gms/internal/zzal:zzny	Ljava/util/LinkedList;
    //   280: iload_2
    //   281: invokevirtual 668	java/util/LinkedList:get	(I)Ljava/lang/Object;
    //   284: checkcast 466	android/view/MotionEvent
    //   287: aload_0
    //   288: getfield 613	com/google/android/gms/internal/zzal:zznE	Landroid/util/DisplayMetrics;
    //   291: invokestatic 615	com/google/android/gms/internal/zzal:zza	(Landroid/view/MotionEvent;Landroid/util/DisplayMetrics;)Ljava/util/ArrayList;
    //   294: astore 5
    //   296: new 663	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo
    //   299: dup
    //   300: invokespecial 669	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo:<init>	()V
    //   303: astore 6
    //   305: aload 6
    //   307: aload 5
    //   309: iconst_0
    //   310: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   313: checkcast 144	java/lang/Long
    //   316: putfield 670	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo:tcxSignal	Ljava/lang/Long;
    //   319: aload 6
    //   321: aload 5
    //   323: iconst_1
    //   324: invokevirtual 550	java/util/ArrayList:get	(I)Ljava/lang/Object;
    //   327: checkcast 144	java/lang/Long
    //   330: putfield 671	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo:tcySignal	Ljava/lang/Long;
    //   333: aload 4
    //   335: getfield 667	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:previousTouches	[Lcom/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo;
    //   338: iload_2
    //   339: aload 6
    //   341: aastore
    //   342: iload_2
    //   343: iconst_1
    //   344: iadd
    //   345: istore_2
    //   346: goto -75 -> 271
    //   349: astore 5
    //   351: aload 4
    //   353: aconst_null
    //   354: putfield 667	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:previousTouches	[Lcom/google/ads/afma/nano/NanoAfmaSignals$AFMASignals$TouchInfo;
    //   357: aload 4
    //   359: aload_1
    //   360: invokestatic 590	com/google/android/gms/internal/zzal:zzg	(Landroid/content/Context;)Ljava/lang/String;
    //   363: putfield 593	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:vnmSignal	Ljava/lang/String;
    //   366: aload 4
    //   368: aload_1
    //   369: invokestatic 595	com/google/android/gms/internal/zzal:zzh	(Landroid/content/Context;)Ljava/lang/Long;
    //   372: putfield 598	com/google/ads/afma/nano/NanoAfmaSignals$AFMASignals:vcdSignal	Ljava/lang/Long;
    //   375: aload_0
    //   376: invokespecial 600	com/google/android/gms/internal/zzal:zzT	()V
    //   379: aload 4
    //   381: areturn
    //   382: astore_1
    //   383: aload 4
    //   385: areturn
    //   386: astore_1
    //   387: goto -12 -> 375
    //   390: astore 5
    //   392: goto -26 -> 366
    //   395: astore 5
    //   397: goto -233 -> 164
    //   400: astore 5
    //   402: goto -353 -> 49
    //   405: astore 5
    //   407: goto -366 -> 41
    //   410: astore 5
    //   412: goto -379 -> 33
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	415	0	this	zzal
    //   0	415	1	paramContext	Context
    //   1	345	2	i	int
    //   257	17	3	j	int
    //   9	375	4	localAFMASignals	com.google.ads.afma.nano.NanoAfmaSignals.AFMASignals
    //   67	255	5	localArrayList	ArrayList
    //   349	1	5	localzza1	zza
    //   390	1	5	localzza2	zza
    //   395	1	5	localzza3	zza
    //   400	1	5	localzza4	zza
    //   405	1	5	localzza5	zza
    //   410	1	5	localzza6	zza
    //   303	37	6	localTouchInfo	com.google.ads.afma.nano.NanoAfmaSignals.AFMASignals.TouchInfo
    // Exception table:
    //   from	to	target	type
    //   248	258	349	com/google/android/gms/internal/zzal$zza
    //   262	271	349	com/google/android/gms/internal/zzal$zza
    //   276	342	349	com/google/android/gms/internal/zzal$zza
    //   11	25	382	java/io/IOException
    //   25	33	382	java/io/IOException
    //   33	41	382	java/io/IOException
    //   41	49	382	java/io/IOException
    //   49	56	382	java/io/IOException
    //   56	128	382	java/io/IOException
    //   128	164	382	java/io/IOException
    //   164	185	382	java/io/IOException
    //   185	206	382	java/io/IOException
    //   206	227	382	java/io/IOException
    //   227	248	382	java/io/IOException
    //   248	258	382	java/io/IOException
    //   262	271	382	java/io/IOException
    //   276	342	382	java/io/IOException
    //   351	357	382	java/io/IOException
    //   357	366	382	java/io/IOException
    //   366	375	382	java/io/IOException
    //   375	379	382	java/io/IOException
    //   366	375	386	com/google/android/gms/internal/zzal$zza
    //   357	366	390	com/google/android/gms/internal/zzal$zza
    //   56	128	395	com/google/android/gms/internal/zzal$zza
    //   128	164	395	com/google/android/gms/internal/zzal$zza
    //   41	49	400	com/google/android/gms/internal/zzal$zza
    //   33	41	405	com/google/android/gms/internal/zzal$zza
    //   25	33	410	com/google/android/gms/internal/zzal$zza
  }
  
  static class zza
    extends Exception
  {
    public zza() {}
    
    public zza(Throwable paramThrowable)
    {
      super();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzal.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */