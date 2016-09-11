package com.google.android.gms.internal;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.os.Bundle;
import android.os.RemoteException;
import android.webkit.WebView;
import com.google.android.gms.ads.internal.client.AdSizeParcel;
import com.google.android.gms.ads.internal.request.AdRequestInfoParcel;
import com.google.android.gms.ads.internal.request.AdResponseParcel;
import com.google.android.gms.ads.internal.request.zzj.zza;
import com.google.android.gms.ads.internal.request.zzk;
import com.google.android.gms.ads.internal.util.client.VersionInfoParcel;
import com.google.android.gms.ads.internal.zzr;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.Callable;

@zzhb
public final class zzhd
  extends zzj.zza
{
  private static zzhd zzIQ;
  private static final Object zzqy = new Object();
  private final Context mContext;
  private final zzhc zzIR;
  private final zzbm zzIS;
  private final zzeg zzIT;
  
  zzhd(Context paramContext, zzbm paramzzbm, zzhc paramzzhc)
  {
    this.mContext = paramContext;
    this.zzIR = paramzzhc;
    this.zzIS = paramzzbm;
    if (paramContext.getApplicationContext() != null) {
      paramContext = paramContext.getApplicationContext();
    }
    for (;;)
    {
      this.zzIT = new zzeg(paramContext, new VersionInfoParcel(8487000, 8487000, true), paramzzbm.zzdp(), new zzeg.zzb()new zzeg.zzc
      {
        public void zza(zzed paramAnonymouszzed)
        {
          paramAnonymouszzed.zza("/log", zzde.zzzf);
        }
      }, new zzeg.zzc());
      return;
    }
  }
  
  /* Error */
  private static AdResponseParcel zza(final Context paramContext, zzeg paramzzeg, final zzbm paramzzbm, zzhc paramzzhc, final AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    // Byte code:
    //   0: ldc 98
    //   2: invokestatic 104	com/google/android/gms/internal/zzin:zzaI	(Ljava/lang/String;)V
    //   5: aload_0
    //   6: invokestatic 110	com/google/android/gms/internal/zzbt:initialize	(Landroid/content/Context;)V
    //   9: new 112	com/google/android/gms/internal/zzcb
    //   12: dup
    //   13: getstatic 116	com/google/android/gms/internal/zzbt:zzwg	Lcom/google/android/gms/internal/zzbp;
    //   16: invokevirtual 122	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   19: checkcast 124	java/lang/Boolean
    //   22: invokevirtual 128	java/lang/Boolean:booleanValue	()Z
    //   25: ldc -126
    //   27: aload 4
    //   29: getfield 136	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzrp	Lcom/google/android/gms/ads/internal/client/AdSizeParcel;
    //   32: getfield 142	com/google/android/gms/ads/internal/client/AdSizeParcel:zzuh	Ljava/lang/String;
    //   35: invokespecial 145	com/google/android/gms/internal/zzcb:<init>	(ZLjava/lang/String;Ljava/lang/String;)V
    //   38: astore 9
    //   40: aload 4
    //   42: getfield 149	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:versionCode	I
    //   45: bipush 10
    //   47: if_icmple +40 -> 87
    //   50: aload 4
    //   52: getfield 153	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHL	J
    //   55: ldc2_w 154
    //   58: lcmp
    //   59: ifeq +28 -> 87
    //   62: aload 9
    //   64: aload 9
    //   66: aload 4
    //   68: getfield 153	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHL	J
    //   71: invokevirtual 159	com/google/android/gms/internal/zzcb:zzb	(J)Lcom/google/android/gms/internal/zzbz;
    //   74: iconst_1
    //   75: anewarray 161	java/lang/String
    //   78: dup
    //   79: iconst_0
    //   80: ldc -93
    //   82: aastore
    //   83: invokevirtual 166	com/google/android/gms/internal/zzcb:zza	(Lcom/google/android/gms/internal/zzbz;[Ljava/lang/String;)Z
    //   86: pop
    //   87: aload 9
    //   89: invokevirtual 170	com/google/android/gms/internal/zzcb:zzdB	()Lcom/google/android/gms/internal/zzbz;
    //   92: astore 10
    //   94: aload 4
    //   96: getfield 149	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:versionCode	I
    //   99: iconst_4
    //   100: if_icmplt +898 -> 998
    //   103: aload 4
    //   105: getfield 174	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHA	Landroid/os/Bundle;
    //   108: ifnull +890 -> 998
    //   111: aload 4
    //   113: getfield 174	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHA	Landroid/os/Bundle;
    //   116: astore 6
    //   118: getstatic 177	com/google/android/gms/internal/zzbt:zzwp	Lcom/google/android/gms/internal/zzbp;
    //   121: invokevirtual 122	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   124: checkcast 124	java/lang/Boolean
    //   127: invokevirtual 128	java/lang/Boolean:booleanValue	()Z
    //   130: ifeq +858 -> 988
    //   133: aload_3
    //   134: getfield 183	com/google/android/gms/internal/zzhc:zzIP	Lcom/google/android/gms/internal/zzhh;
    //   137: ifnull +851 -> 988
    //   140: aload 6
    //   142: astore 5
    //   144: aload 6
    //   146: ifnonnull +36 -> 182
    //   149: aload 6
    //   151: astore 5
    //   153: getstatic 186	com/google/android/gms/internal/zzbt:zzwq	Lcom/google/android/gms/internal/zzbp;
    //   156: invokevirtual 122	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   159: checkcast 124	java/lang/Boolean
    //   162: invokevirtual 128	java/lang/Boolean:booleanValue	()Z
    //   165: ifeq +17 -> 182
    //   168: ldc -68
    //   170: invokestatic 191	com/google/android/gms/internal/zzin:v	(Ljava/lang/String;)V
    //   173: new 193	android/os/Bundle
    //   176: dup
    //   177: invokespecial 194	android/os/Bundle:<init>	()V
    //   180: astore 5
    //   182: aload 5
    //   184: ifnull +798 -> 982
    //   187: new 6	com/google/android/gms/internal/zzhd$1
    //   190: dup
    //   191: aload_3
    //   192: aload_0
    //   193: aload 4
    //   195: aload 5
    //   197: invokespecial 197	com/google/android/gms/internal/zzhd$1:<init>	(Lcom/google/android/gms/internal/zzhc;Landroid/content/Context;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;Landroid/os/Bundle;)V
    //   200: invokestatic 202	com/google/android/gms/internal/zziq:zza	(Ljava/util/concurrent/Callable;)Lcom/google/android/gms/internal/zzjg;
    //   203: astore 6
    //   205: aload_3
    //   206: getfield 206	com/google/android/gms/internal/zzhc:zzIK	Lcom/google/android/gms/internal/zzek;
    //   209: invokeinterface 211 1 0
    //   214: invokestatic 217	com/google/android/gms/ads/internal/zzr:zzbI	()Lcom/google/android/gms/internal/zzhk;
    //   217: aload_0
    //   218: invokevirtual 223	com/google/android/gms/internal/zzhk:zzE	(Landroid/content/Context;)Lcom/google/android/gms/internal/zzhj;
    //   221: astore 12
    //   223: aload 12
    //   225: getfield 228	com/google/android/gms/internal/zzhj:zzKc	I
    //   228: iconst_m1
    //   229: if_icmpne +17 -> 246
    //   232: ldc -26
    //   234: invokestatic 104	com/google/android/gms/internal/zzin:zzaI	(Ljava/lang/String;)V
    //   237: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   240: dup
    //   241: iconst_2
    //   242: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   245: areturn
    //   246: aload 4
    //   248: getfield 149	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:versionCode	I
    //   251: bipush 7
    //   253: if_icmplt +70 -> 323
    //   256: aload 4
    //   258: getfield 238	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHI	Ljava/lang/String;
    //   261: astore 7
    //   263: new 240	com/google/android/gms/internal/zzhf
    //   266: dup
    //   267: aload 7
    //   269: aload 4
    //   271: getfield 244	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   274: getfield 249	android/content/pm/ApplicationInfo:packageName	Ljava/lang/String;
    //   277: invokespecial 252	com/google/android/gms/internal/zzhf:<init>	(Ljava/lang/String;Ljava/lang/String;)V
    //   280: astore 11
    //   282: aload 4
    //   284: getfield 256	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHt	Lcom/google/android/gms/ads/internal/client/AdRequestParcel;
    //   287: getfield 261	com/google/android/gms/ads/internal/client/AdRequestParcel:extras	Landroid/os/Bundle;
    //   290: ifnull +44 -> 334
    //   293: aload 4
    //   295: getfield 256	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHt	Lcom/google/android/gms/ads/internal/client/AdRequestParcel;
    //   298: getfield 261	com/google/android/gms/ads/internal/client/AdRequestParcel:extras	Landroid/os/Bundle;
    //   301: ldc_w 263
    //   304: invokevirtual 267	android/os/Bundle:getString	(Ljava/lang/String;)Ljava/lang/String;
    //   307: astore 8
    //   309: aload 8
    //   311: ifnull +23 -> 334
    //   314: aload_0
    //   315: aload 4
    //   317: aload 8
    //   319: invokestatic 272	com/google/android/gms/internal/zzhe:zza	(Landroid/content/Context;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;Ljava/lang/String;)Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
    //   322: areturn
    //   323: invokestatic 278	java/util/UUID:randomUUID	()Ljava/util/UUID;
    //   326: invokevirtual 281	java/util/UUID:toString	()Ljava/lang/String;
    //   329: astore 7
    //   331: goto -68 -> 263
    //   334: aload_3
    //   335: getfield 206	com/google/android/gms/internal/zzhc:zzIK	Lcom/google/android/gms/internal/zzek;
    //   338: ldc2_w 282
    //   341: invokeinterface 287 3 0
    //   346: astore 13
    //   348: aload_3
    //   349: getfield 291	com/google/android/gms/internal/zzhc:zzIL	Lcom/google/android/gms/internal/zzfy;
    //   352: aload_0
    //   353: aload 4
    //   355: getfield 294	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzrj	Ljava/lang/String;
    //   358: aload 4
    //   360: getfield 298	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHu	Landroid/content/pm/PackageInfo;
    //   363: getfield 301	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   366: invokeinterface 307 4 0
    //   371: astore 8
    //   373: aload_3
    //   374: getfield 311	com/google/android/gms/internal/zzhc:zzII	Lcom/google/android/gms/internal/zzbo;
    //   377: aload 4
    //   379: invokeinterface 316 2 0
    //   384: astore 14
    //   386: aload_3
    //   387: getfield 320	com/google/android/gms/internal/zzhc:zzIM	Lcom/google/android/gms/internal/zzie;
    //   390: aload 4
    //   392: invokeinterface 326 2 0
    //   397: astore 15
    //   399: aload_3
    //   400: getfield 330	com/google/android/gms/internal/zzhc:zzIN	Lcom/google/android/gms/internal/zzhn;
    //   403: aload_0
    //   404: invokeinterface 336 2 0
    //   409: astore 16
    //   411: aload 6
    //   413: ifnull +38 -> 451
    //   416: ldc_w 338
    //   419: invokestatic 191	com/google/android/gms/internal/zzin:v	(Ljava/lang/String;)V
    //   422: aload 6
    //   424: getstatic 341	com/google/android/gms/internal/zzbt:zzwr	Lcom/google/android/gms/internal/zzbp;
    //   427: invokevirtual 122	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   430: checkcast 343	java/lang/Long
    //   433: invokevirtual 347	java/lang/Long:longValue	()J
    //   436: getstatic 353	java/util/concurrent/TimeUnit:MILLISECONDS	Ljava/util/concurrent/TimeUnit;
    //   439: invokeinterface 358 4 0
    //   444: pop
    //   445: ldc_w 360
    //   448: invokestatic 191	com/google/android/gms/internal/zzin:v	(Ljava/lang/String;)V
    //   451: aload_0
    //   452: aload 4
    //   454: aload 12
    //   456: aload 16
    //   458: aload 13
    //   460: aload_2
    //   461: aload 8
    //   463: aload 15
    //   465: aload 14
    //   467: aload 5
    //   469: invokestatic 363	com/google/android/gms/internal/zzhe:zza	(Landroid/content/Context;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;Lcom/google/android/gms/internal/zzhj;Lcom/google/android/gms/internal/zzhn$zza;Landroid/location/Location;Lcom/google/android/gms/internal/zzbm;Ljava/lang/String;Ljava/lang/String;Ljava/util/List;Landroid/os/Bundle;)Lorg/json/JSONObject;
    //   472: astore 5
    //   474: aload 4
    //   476: getfield 149	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:versionCode	I
    //   479: bipush 7
    //   481: if_icmpge +14 -> 495
    //   484: aload 5
    //   486: ldc_w 365
    //   489: aload 7
    //   491: invokevirtual 371	org/json/JSONObject:put	(Ljava/lang/String;Ljava/lang/Object;)Lorg/json/JSONObject;
    //   494: pop
    //   495: aload 5
    //   497: ifnonnull +36 -> 533
    //   500: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   503: dup
    //   504: iconst_0
    //   505: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   508: areturn
    //   509: astore 6
    //   511: ldc_w 373
    //   514: aload 6
    //   516: invokestatic 376	com/google/android/gms/internal/zzin:zzd	(Ljava/lang/String;Ljava/lang/Throwable;)V
    //   519: goto -68 -> 451
    //   522: astore 6
    //   524: ldc_w 378
    //   527: invokestatic 104	com/google/android/gms/internal/zzin:zzaI	(Ljava/lang/String;)V
    //   530: goto -79 -> 451
    //   533: aload 5
    //   535: invokevirtual 379	org/json/JSONObject:toString	()Ljava/lang/String;
    //   538: astore 5
    //   540: aload 9
    //   542: aload 10
    //   544: iconst_1
    //   545: anewarray 161	java/lang/String
    //   548: dup
    //   549: iconst_0
    //   550: ldc_w 381
    //   553: aastore
    //   554: invokevirtual 166	com/google/android/gms/internal/zzcb:zza	(Lcom/google/android/gms/internal/zzbz;[Ljava/lang/String;)Z
    //   557: pop
    //   558: aload 9
    //   560: invokevirtual 170	com/google/android/gms/internal/zzcb:zzdB	()Lcom/google/android/gms/internal/zzbz;
    //   563: astore 6
    //   565: getstatic 384	com/google/android/gms/internal/zzbt:zzvC	Lcom/google/android/gms/internal/zzbp;
    //   568: invokevirtual 122	com/google/android/gms/internal/zzbp:get	()Ljava/lang/Object;
    //   571: checkcast 124	java/lang/Boolean
    //   574: invokevirtual 128	java/lang/Boolean:booleanValue	()Z
    //   577: ifeq +83 -> 660
    //   580: getstatic 390	com/google/android/gms/internal/zzir:zzMc	Landroid/os/Handler;
    //   583: new 8	com/google/android/gms/internal/zzhd$2
    //   586: dup
    //   587: aload_1
    //   588: aload 11
    //   590: aload 9
    //   592: aload 6
    //   594: aload 5
    //   596: invokespecial 393	com/google/android/gms/internal/zzhd$2:<init>	(Lcom/google/android/gms/internal/zzeg;Lcom/google/android/gms/internal/zzhf;Lcom/google/android/gms/internal/zzcb;Lcom/google/android/gms/internal/zzbz;Ljava/lang/String;)V
    //   599: invokevirtual 399	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   602: pop
    //   603: aload 11
    //   605: invokevirtual 403	com/google/android/gms/internal/zzhf:zzgC	()Ljava/util/concurrent/Future;
    //   608: ldc2_w 404
    //   611: getstatic 408	java/util/concurrent/TimeUnit:SECONDS	Ljava/util/concurrent/TimeUnit;
    //   614: invokeinterface 358 4 0
    //   619: checkcast 410	com/google/android/gms/internal/zzhi
    //   622: astore 5
    //   624: aload 5
    //   626: ifnonnull +95 -> 721
    //   629: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   632: dup
    //   633: iconst_0
    //   634: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   637: astore_1
    //   638: getstatic 390	com/google/android/gms/internal/zzir:zzMc	Landroid/os/Handler;
    //   641: new 16	com/google/android/gms/internal/zzhd$4
    //   644: dup
    //   645: aload_3
    //   646: aload_0
    //   647: aload 11
    //   649: aload 4
    //   651: invokespecial 413	com/google/android/gms/internal/zzhd$4:<init>	(Lcom/google/android/gms/internal/zzhc;Landroid/content/Context;Lcom/google/android/gms/internal/zzhf;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
    //   654: invokevirtual 399	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   657: pop
    //   658: aload_1
    //   659: areturn
    //   660: getstatic 390	com/google/android/gms/internal/zzir:zzMc	Landroid/os/Handler;
    //   663: new 14	com/google/android/gms/internal/zzhd$3
    //   666: dup
    //   667: aload_0
    //   668: aload 4
    //   670: aload 11
    //   672: aload 9
    //   674: aload 6
    //   676: aload 5
    //   678: aload_2
    //   679: invokespecial 416	com/google/android/gms/internal/zzhd$3:<init>	(Landroid/content/Context;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;Lcom/google/android/gms/internal/zzhf;Lcom/google/android/gms/internal/zzcb;Lcom/google/android/gms/internal/zzbz;Ljava/lang/String;Lcom/google/android/gms/internal/zzbm;)V
    //   682: invokevirtual 399	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   685: pop
    //   686: goto -83 -> 603
    //   689: astore_1
    //   690: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   693: dup
    //   694: iconst_0
    //   695: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   698: astore_1
    //   699: getstatic 390	com/google/android/gms/internal/zzir:zzMc	Landroid/os/Handler;
    //   702: new 16	com/google/android/gms/internal/zzhd$4
    //   705: dup
    //   706: aload_3
    //   707: aload_0
    //   708: aload 11
    //   710: aload 4
    //   712: invokespecial 413	com/google/android/gms/internal/zzhd$4:<init>	(Lcom/google/android/gms/internal/zzhc;Landroid/content/Context;Lcom/google/android/gms/internal/zzhf;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
    //   715: invokevirtual 399	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   718: pop
    //   719: aload_1
    //   720: areturn
    //   721: aload 5
    //   723: invokevirtual 420	com/google/android/gms/internal/zzhi:getErrorCode	()I
    //   726: bipush -2
    //   728: if_icmpeq +38 -> 766
    //   731: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   734: dup
    //   735: aload 5
    //   737: invokevirtual 420	com/google/android/gms/internal/zzhi:getErrorCode	()I
    //   740: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   743: astore_1
    //   744: getstatic 390	com/google/android/gms/internal/zzir:zzMc	Landroid/os/Handler;
    //   747: new 16	com/google/android/gms/internal/zzhd$4
    //   750: dup
    //   751: aload_3
    //   752: aload_0
    //   753: aload 11
    //   755: aload 4
    //   757: invokespecial 413	com/google/android/gms/internal/zzhd$4:<init>	(Lcom/google/android/gms/internal/zzhc;Landroid/content/Context;Lcom/google/android/gms/internal/zzhf;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
    //   760: invokevirtual 399	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   763: pop
    //   764: aload_1
    //   765: areturn
    //   766: aload 9
    //   768: invokevirtual 423	com/google/android/gms/internal/zzcb:zzdE	()Lcom/google/android/gms/internal/zzbz;
    //   771: ifnull +24 -> 795
    //   774: aload 9
    //   776: aload 9
    //   778: invokevirtual 423	com/google/android/gms/internal/zzcb:zzdE	()Lcom/google/android/gms/internal/zzbz;
    //   781: iconst_1
    //   782: anewarray 161	java/lang/String
    //   785: dup
    //   786: iconst_0
    //   787: ldc_w 425
    //   790: aastore
    //   791: invokevirtual 166	com/google/android/gms/internal/zzcb:zza	(Lcom/google/android/gms/internal/zzbz;[Ljava/lang/String;)Z
    //   794: pop
    //   795: aconst_null
    //   796: astore_1
    //   797: aload 5
    //   799: invokevirtual 428	com/google/android/gms/internal/zzhi:zzgG	()Z
    //   802: ifeq +21 -> 823
    //   805: aload_3
    //   806: getfield 432	com/google/android/gms/internal/zzhc:zzIH	Lcom/google/android/gms/internal/zzib;
    //   809: aload 4
    //   811: getfield 298	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHu	Landroid/content/pm/PackageInfo;
    //   814: getfield 301	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   817: invokeinterface 437 2 0
    //   822: astore_1
    //   823: aload 4
    //   825: getfield 441	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzrl	Lcom/google/android/gms/ads/internal/util/client/VersionInfoParcel;
    //   828: getfield 444	com/google/android/gms/ads/internal/util/client/VersionInfoParcel:afmaVersion	Ljava/lang/String;
    //   831: astore 6
    //   833: aload 5
    //   835: invokevirtual 447	com/google/android/gms/internal/zzhi:getUrl	()Ljava/lang/String;
    //   838: astore 7
    //   840: aload 5
    //   842: invokevirtual 450	com/google/android/gms/internal/zzhi:zzgH	()Z
    //   845: ifeq +99 -> 944
    //   848: aload 8
    //   850: astore_2
    //   851: aload 4
    //   853: aload_0
    //   854: aload 6
    //   856: aload 7
    //   858: aload_1
    //   859: aload_2
    //   860: aload 5
    //   862: aload 9
    //   864: aload_3
    //   865: invokestatic 453	com/google/android/gms/internal/zzhd:zza	(Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;Landroid/content/Context;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Ljava/lang/String;Lcom/google/android/gms/internal/zzhi;Lcom/google/android/gms/internal/zzcb;Lcom/google/android/gms/internal/zzhc;)Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
    //   868: astore_1
    //   869: aload_1
    //   870: getfield 456	com/google/android/gms/ads/internal/request/AdResponseParcel:zzIf	I
    //   873: iconst_1
    //   874: if_icmpne +21 -> 895
    //   877: aload_3
    //   878: getfield 291	com/google/android/gms/internal/zzhc:zzIL	Lcom/google/android/gms/internal/zzfy;
    //   881: aload_0
    //   882: aload 4
    //   884: getfield 298	com/google/android/gms/ads/internal/request/AdRequestInfoParcel:zzHu	Landroid/content/pm/PackageInfo;
    //   887: getfield 301	android/content/pm/PackageInfo:packageName	Ljava/lang/String;
    //   890: invokeinterface 460 3 0
    //   895: aload 9
    //   897: aload 10
    //   899: iconst_1
    //   900: anewarray 161	java/lang/String
    //   903: dup
    //   904: iconst_0
    //   905: ldc_w 462
    //   908: aastore
    //   909: invokevirtual 166	com/google/android/gms/internal/zzcb:zza	(Lcom/google/android/gms/internal/zzbz;[Ljava/lang/String;)Z
    //   912: pop
    //   913: aload_1
    //   914: aload 9
    //   916: invokevirtual 465	com/google/android/gms/internal/zzcb:zzdD	()Ljava/lang/String;
    //   919: putfield 468	com/google/android/gms/ads/internal/request/AdResponseParcel:zzIh	Ljava/lang/String;
    //   922: getstatic 390	com/google/android/gms/internal/zzir:zzMc	Landroid/os/Handler;
    //   925: new 16	com/google/android/gms/internal/zzhd$4
    //   928: dup
    //   929: aload_3
    //   930: aload_0
    //   931: aload 11
    //   933: aload 4
    //   935: invokespecial 413	com/google/android/gms/internal/zzhd$4:<init>	(Lcom/google/android/gms/internal/zzhc;Landroid/content/Context;Lcom/google/android/gms/internal/zzhf;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
    //   938: invokevirtual 399	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   941: pop
    //   942: aload_1
    //   943: areturn
    //   944: aconst_null
    //   945: astore_2
    //   946: goto -95 -> 851
    //   949: astore_1
    //   950: getstatic 390	com/google/android/gms/internal/zzir:zzMc	Landroid/os/Handler;
    //   953: new 16	com/google/android/gms/internal/zzhd$4
    //   956: dup
    //   957: aload_3
    //   958: aload_0
    //   959: aload 11
    //   961: aload 4
    //   963: invokespecial 413	com/google/android/gms/internal/zzhd$4:<init>	(Lcom/google/android/gms/internal/zzhc;Landroid/content/Context;Lcom/google/android/gms/internal/zzhf;Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
    //   966: invokevirtual 399	android/os/Handler:post	(Ljava/lang/Runnable;)Z
    //   969: pop
    //   970: aload_1
    //   971: athrow
    //   972: astore 6
    //   974: goto -479 -> 495
    //   977: astore 6
    //   979: goto -468 -> 511
    //   982: aconst_null
    //   983: astore 6
    //   985: goto -780 -> 205
    //   988: aload 6
    //   990: astore 5
    //   992: aconst_null
    //   993: astore 6
    //   995: goto -790 -> 205
    //   998: aconst_null
    //   999: astore 6
    //   1001: goto -883 -> 118
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1004	0	paramContext	Context
    //   0	1004	1	paramzzeg	zzeg
    //   0	1004	2	paramzzbm	zzbm
    //   0	1004	3	paramzzhc	zzhc
    //   0	1004	4	paramAdRequestInfoParcel	AdRequestInfoParcel
    //   142	849	5	localObject1	Object
    //   116	307	6	localObject2	Object
    //   509	6	6	localInterruptedException	InterruptedException
    //   522	1	6	localTimeoutException	java.util.concurrent.TimeoutException
    //   563	292	6	localObject3	Object
    //   972	1	6	localJSONException	org.json.JSONException
    //   977	1	6	localExecutionException	java.util.concurrent.ExecutionException
    //   983	17	6	localObject4	Object
    //   261	596	7	str1	String
    //   307	542	8	str2	String
    //   38	877	9	localzzcb	zzcb
    //   92	806	10	localzzbz	zzbz
    //   280	680	11	localzzhf	zzhf
    //   221	234	12	localzzhj	zzhj
    //   346	113	13	localLocation	android.location.Location
    //   384	82	14	localList	List
    //   397	67	15	str3	String
    //   409	48	16	localzza	zzhn.zza
    // Exception table:
    //   from	to	target	type
    //   416	451	509	java/lang/InterruptedException
    //   416	451	522	java/util/concurrent/TimeoutException
    //   603	624	689	java/lang/Exception
    //   603	624	949	finally
    //   629	638	949	finally
    //   690	699	949	finally
    //   721	744	949	finally
    //   766	795	949	finally
    //   797	823	949	finally
    //   823	848	949	finally
    //   851	895	949	finally
    //   895	922	949	finally
    //   484	495	972	org/json/JSONException
    //   416	451	977	java/util/concurrent/ExecutionException
  }
  
  /* Error */
  public static AdResponseParcel zza(AdRequestInfoParcel paramAdRequestInfoParcel, Context paramContext, String paramString1, String paramString2, String paramString3, String paramString4, zzhi paramzzhi, zzcb paramzzcb, zzhc paramzzhc)
  {
    // Byte code:
    //   0: aload 7
    //   2: ifnull +346 -> 348
    //   5: aload 7
    //   7: invokevirtual 170	com/google/android/gms/internal/zzcb:zzdB	()Lcom/google/android/gms/internal/zzbz;
    //   10: astore 13
    //   12: new 472	com/google/android/gms/internal/zzhg
    //   15: dup
    //   16: aload_0
    //   17: invokespecial 475	com/google/android/gms/internal/zzhg:<init>	(Lcom/google/android/gms/ads/internal/request/AdRequestInfoParcel;)V
    //   20: astore 15
    //   22: new 477	java/lang/StringBuilder
    //   25: dup
    //   26: invokespecial 478	java/lang/StringBuilder:<init>	()V
    //   29: ldc_w 480
    //   32: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   35: aload_3
    //   36: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   39: invokevirtual 485	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   42: invokestatic 104	com/google/android/gms/internal/zzin:zzaI	(Ljava/lang/String;)V
    //   45: new 487	java/net/URL
    //   48: dup
    //   49: aload_3
    //   50: invokespecial 489	java/net/URL:<init>	(Ljava/lang/String;)V
    //   53: astore_0
    //   54: invokestatic 493	com/google/android/gms/ads/internal/zzr:zzbG	()Lcom/google/android/gms/internal/zzmq;
    //   57: invokeinterface 498 1 0
    //   62: lstore 11
    //   64: iconst_0
    //   65: istore 9
    //   67: aload 8
    //   69: ifnull +13 -> 82
    //   72: aload 8
    //   74: getfield 502	com/google/android/gms/internal/zzhc:zzIO	Lcom/google/android/gms/internal/zzhm;
    //   77: invokeinterface 507 1 0
    //   82: aload_0
    //   83: invokevirtual 511	java/net/URL:openConnection	()Ljava/net/URLConnection;
    //   86: checkcast 513	java/net/HttpURLConnection
    //   89: astore 14
    //   91: invokestatic 517	com/google/android/gms/ads/internal/zzr:zzbC	()Lcom/google/android/gms/internal/zzir;
    //   94: aload_1
    //   95: aload_2
    //   96: iconst_0
    //   97: aload 14
    //   99: invokevirtual 520	com/google/android/gms/internal/zzir:zza	(Landroid/content/Context;Ljava/lang/String;ZLjava/net/HttpURLConnection;)V
    //   102: aload 4
    //   104: invokestatic 526	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   107: ifne +13 -> 120
    //   110: aload 14
    //   112: ldc_w 528
    //   115: aload 4
    //   117: invokevirtual 531	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   120: aload 5
    //   122: invokestatic 526	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   125: ifne +32 -> 157
    //   128: aload 14
    //   130: ldc_w 533
    //   133: new 477	java/lang/StringBuilder
    //   136: dup
    //   137: invokespecial 478	java/lang/StringBuilder:<init>	()V
    //   140: ldc_w 535
    //   143: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   146: aload 5
    //   148: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   151: invokevirtual 485	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   154: invokevirtual 531	java/net/HttpURLConnection:addRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
    //   157: aload 6
    //   159: ifnull +61 -> 220
    //   162: aload 6
    //   164: invokevirtual 538	com/google/android/gms/internal/zzhi:zzgF	()Ljava/lang/String;
    //   167: invokestatic 526	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   170: ifne +50 -> 220
    //   173: aload 14
    //   175: iconst_1
    //   176: invokevirtual 542	java/net/HttpURLConnection:setDoOutput	(Z)V
    //   179: aload 6
    //   181: invokevirtual 538	com/google/android/gms/internal/zzhi:zzgF	()Ljava/lang/String;
    //   184: invokevirtual 546	java/lang/String:getBytes	()[B
    //   187: astore 16
    //   189: aload 14
    //   191: aload 16
    //   193: arraylength
    //   194: invokevirtual 549	java/net/HttpURLConnection:setFixedLengthStreamingMode	(I)V
    //   197: new 551	java/io/BufferedOutputStream
    //   200: dup
    //   201: aload 14
    //   203: invokevirtual 555	java/net/HttpURLConnection:getOutputStream	()Ljava/io/OutputStream;
    //   206: invokespecial 558	java/io/BufferedOutputStream:<init>	(Ljava/io/OutputStream;)V
    //   209: astore_3
    //   210: aload_3
    //   211: aload 16
    //   213: invokevirtual 562	java/io/BufferedOutputStream:write	([B)V
    //   216: aload_3
    //   217: invokestatic 567	com/google/android/gms/internal/zzna:zzb	(Ljava/io/Closeable;)V
    //   220: aload 14
    //   222: invokevirtual 570	java/net/HttpURLConnection:getResponseCode	()I
    //   225: istore 10
    //   227: aload 14
    //   229: invokevirtual 574	java/net/HttpURLConnection:getHeaderFields	()Ljava/util/Map;
    //   232: astore_3
    //   233: iload 10
    //   235: sipush 200
    //   238: if_icmplt +193 -> 431
    //   241: iload 10
    //   243: sipush 300
    //   246: if_icmpge +185 -> 431
    //   249: aload_0
    //   250: invokevirtual 575	java/net/URL:toString	()Ljava/lang/String;
    //   253: astore_0
    //   254: new 577	java/io/InputStreamReader
    //   257: dup
    //   258: aload 14
    //   260: invokevirtual 581	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
    //   263: invokespecial 584	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   266: astore_1
    //   267: invokestatic 517	com/google/android/gms/ads/internal/zzr:zzbC	()Lcom/google/android/gms/internal/zzir;
    //   270: aload_1
    //   271: invokevirtual 587	com/google/android/gms/internal/zzir:zza	(Ljava/io/InputStreamReader;)Ljava/lang/String;
    //   274: astore_2
    //   275: aload_1
    //   276: invokestatic 567	com/google/android/gms/internal/zzna:zzb	(Ljava/io/Closeable;)V
    //   279: aload_0
    //   280: aload_3
    //   281: aload_2
    //   282: iload 10
    //   284: invokestatic 590	com/google/android/gms/internal/zzhd:zza	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   287: aload 15
    //   289: aload_0
    //   290: aload_3
    //   291: aload_2
    //   292: invokevirtual 593	com/google/android/gms/internal/zzhg:zzb	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;)V
    //   295: aload 7
    //   297: ifnull +21 -> 318
    //   300: aload 7
    //   302: aload 13
    //   304: iconst_1
    //   305: anewarray 161	java/lang/String
    //   308: dup
    //   309: iconst_0
    //   310: ldc_w 595
    //   313: aastore
    //   314: invokevirtual 166	com/google/android/gms/internal/zzcb:zza	(Lcom/google/android/gms/internal/zzbz;[Ljava/lang/String;)Z
    //   317: pop
    //   318: aload 15
    //   320: lload 11
    //   322: invokevirtual 599	com/google/android/gms/internal/zzhg:zzj	(J)Lcom/google/android/gms/ads/internal/request/AdResponseParcel;
    //   325: astore_0
    //   326: aload 14
    //   328: invokevirtual 602	java/net/HttpURLConnection:disconnect	()V
    //   331: aload 8
    //   333: ifnull +13 -> 346
    //   336: aload 8
    //   338: getfield 502	com/google/android/gms/internal/zzhc:zzIO	Lcom/google/android/gms/internal/zzhm;
    //   341: invokeinterface 605 1 0
    //   346: aload_0
    //   347: areturn
    //   348: aconst_null
    //   349: astore 13
    //   351: goto -339 -> 12
    //   354: astore_0
    //   355: aconst_null
    //   356: astore_1
    //   357: aload_1
    //   358: invokestatic 567	com/google/android/gms/internal/zzna:zzb	(Ljava/io/Closeable;)V
    //   361: aload_0
    //   362: athrow
    //   363: astore_0
    //   364: aload 14
    //   366: invokevirtual 602	java/net/HttpURLConnection:disconnect	()V
    //   369: aload 8
    //   371: ifnull +13 -> 384
    //   374: aload 8
    //   376: getfield 502	com/google/android/gms/internal/zzhc:zzIO	Lcom/google/android/gms/internal/zzhm;
    //   379: invokeinterface 605 1 0
    //   384: aload_0
    //   385: athrow
    //   386: astore_0
    //   387: new 477	java/lang/StringBuilder
    //   390: dup
    //   391: invokespecial 478	java/lang/StringBuilder:<init>	()V
    //   394: ldc_w 607
    //   397: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   400: aload_0
    //   401: invokevirtual 610	java/io/IOException:getMessage	()Ljava/lang/String;
    //   404: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   407: invokevirtual 485	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   410: invokestatic 613	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   413: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   416: dup
    //   417: iconst_2
    //   418: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   421: areturn
    //   422: astore_0
    //   423: aconst_null
    //   424: astore_1
    //   425: aload_1
    //   426: invokestatic 567	com/google/android/gms/internal/zzna:zzb	(Ljava/io/Closeable;)V
    //   429: aload_0
    //   430: athrow
    //   431: aload_0
    //   432: invokevirtual 575	java/net/URL:toString	()Ljava/lang/String;
    //   435: aload_3
    //   436: aconst_null
    //   437: iload 10
    //   439: invokestatic 590	com/google/android/gms/internal/zzhd:zza	(Ljava/lang/String;Ljava/util/Map;Ljava/lang/String;I)V
    //   442: iload 10
    //   444: sipush 300
    //   447: if_icmplt +122 -> 569
    //   450: iload 10
    //   452: sipush 400
    //   455: if_icmpge +114 -> 569
    //   458: aload 14
    //   460: ldc_w 615
    //   463: invokevirtual 618	java/net/HttpURLConnection:getHeaderField	(Ljava/lang/String;)Ljava/lang/String;
    //   466: astore_0
    //   467: aload_0
    //   468: invokestatic 526	android/text/TextUtils:isEmpty	(Ljava/lang/CharSequence;)Z
    //   471: ifeq +40 -> 511
    //   474: ldc_w 620
    //   477: invokestatic 613	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   480: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   483: dup
    //   484: iconst_0
    //   485: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   488: astore_0
    //   489: aload 14
    //   491: invokevirtual 602	java/net/HttpURLConnection:disconnect	()V
    //   494: aload 8
    //   496: ifnull +13 -> 509
    //   499: aload 8
    //   501: getfield 502	com/google/android/gms/internal/zzhc:zzIO	Lcom/google/android/gms/internal/zzhm;
    //   504: invokeinterface 605 1 0
    //   509: aload_0
    //   510: areturn
    //   511: new 487	java/net/URL
    //   514: dup
    //   515: aload_0
    //   516: invokespecial 489	java/net/URL:<init>	(Ljava/lang/String;)V
    //   519: astore_0
    //   520: iload 9
    //   522: iconst_1
    //   523: iadd
    //   524: istore 9
    //   526: iload 9
    //   528: iconst_5
    //   529: if_icmple +95 -> 624
    //   532: ldc_w 622
    //   535: invokestatic 613	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   538: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   541: dup
    //   542: iconst_0
    //   543: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   546: astore_0
    //   547: aload 14
    //   549: invokevirtual 602	java/net/HttpURLConnection:disconnect	()V
    //   552: aload 8
    //   554: ifnull +13 -> 567
    //   557: aload 8
    //   559: getfield 502	com/google/android/gms/internal/zzhc:zzIO	Lcom/google/android/gms/internal/zzhm;
    //   562: invokeinterface 605 1 0
    //   567: aload_0
    //   568: areturn
    //   569: new 477	java/lang/StringBuilder
    //   572: dup
    //   573: invokespecial 478	java/lang/StringBuilder:<init>	()V
    //   576: ldc_w 624
    //   579: invokevirtual 484	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   582: iload 10
    //   584: invokevirtual 627	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   587: invokevirtual 485	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   590: invokestatic 613	com/google/android/gms/internal/zzin:zzaK	(Ljava/lang/String;)V
    //   593: new 232	com/google/android/gms/ads/internal/request/AdResponseParcel
    //   596: dup
    //   597: iconst_0
    //   598: invokespecial 235	com/google/android/gms/ads/internal/request/AdResponseParcel:<init>	(I)V
    //   601: astore_0
    //   602: aload 14
    //   604: invokevirtual 602	java/net/HttpURLConnection:disconnect	()V
    //   607: aload 8
    //   609: ifnull +13 -> 622
    //   612: aload 8
    //   614: getfield 502	com/google/android/gms/internal/zzhc:zzIO	Lcom/google/android/gms/internal/zzhm;
    //   617: invokeinterface 605 1 0
    //   622: aload_0
    //   623: areturn
    //   624: aload 15
    //   626: aload_3
    //   627: invokevirtual 630	com/google/android/gms/internal/zzhg:zzj	(Ljava/util/Map;)V
    //   630: aload 14
    //   632: invokevirtual 602	java/net/HttpURLConnection:disconnect	()V
    //   635: aload 8
    //   637: ifnull +13 -> 650
    //   640: aload 8
    //   642: getfield 502	com/google/android/gms/internal/zzhc:zzIO	Lcom/google/android/gms/internal/zzhm;
    //   645: invokeinterface 605 1 0
    //   650: goto -583 -> 67
    //   653: astore_0
    //   654: goto -229 -> 425
    //   657: astore_0
    //   658: aload_3
    //   659: astore_1
    //   660: goto -303 -> 357
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	663	0	paramAdRequestInfoParcel	AdRequestInfoParcel
    //   0	663	1	paramContext	Context
    //   0	663	2	paramString1	String
    //   0	663	3	paramString2	String
    //   0	663	4	paramString3	String
    //   0	663	5	paramString4	String
    //   0	663	6	paramzzhi	zzhi
    //   0	663	7	paramzzcb	zzcb
    //   0	663	8	paramzzhc	zzhc
    //   65	465	9	i	int
    //   225	358	10	j	int
    //   62	259	11	l	long
    //   10	340	13	localzzbz	zzbz
    //   89	542	14	localHttpURLConnection	java.net.HttpURLConnection
    //   20	605	15	localzzhg	zzhg
    //   187	25	16	arrayOfByte	byte[]
    // Exception table:
    //   from	to	target	type
    //   197	210	354	finally
    //   91	120	363	finally
    //   120	157	363	finally
    //   162	197	363	finally
    //   216	220	363	finally
    //   220	233	363	finally
    //   249	254	363	finally
    //   275	295	363	finally
    //   300	318	363	finally
    //   318	326	363	finally
    //   357	363	363	finally
    //   425	431	363	finally
    //   431	442	363	finally
    //   458	489	363	finally
    //   511	520	363	finally
    //   532	547	363	finally
    //   569	602	363	finally
    //   624	630	363	finally
    //   12	64	386	java/io/IOException
    //   72	82	386	java/io/IOException
    //   82	91	386	java/io/IOException
    //   326	331	386	java/io/IOException
    //   336	346	386	java/io/IOException
    //   364	369	386	java/io/IOException
    //   374	384	386	java/io/IOException
    //   384	386	386	java/io/IOException
    //   489	494	386	java/io/IOException
    //   499	509	386	java/io/IOException
    //   547	552	386	java/io/IOException
    //   557	567	386	java/io/IOException
    //   602	607	386	java/io/IOException
    //   612	622	386	java/io/IOException
    //   630	635	386	java/io/IOException
    //   640	650	386	java/io/IOException
    //   254	267	422	finally
    //   267	275	653	finally
    //   210	216	657	finally
  }
  
  public static zzhd zza(Context paramContext, zzbm paramzzbm, zzhc paramzzhc)
  {
    synchronized (zzqy)
    {
      if (zzIQ == null)
      {
        Context localContext = paramContext;
        if (paramContext.getApplicationContext() != null) {
          localContext = paramContext.getApplicationContext();
        }
        zzIQ = new zzhd(localContext, paramzzbm, paramzzhc);
      }
      paramContext = zzIQ;
      return paramContext;
    }
  }
  
  private static zzjq.zza zza(final String paramString, zzcb paramzzcb, final zzbz paramzzbz)
  {
    new zzjq.zza()
    {
      public void zza(zzjp paramAnonymouszzjp, boolean paramAnonymousBoolean)
      {
        this.zzpt.zza(paramzzbz, new String[] { "jsf" });
        this.zzpt.zzdC();
        paramAnonymouszzjp.zze("AFMA_buildAdURL", paramString);
      }
    };
  }
  
  private static void zza(String paramString1, Map<String, List<String>> paramMap, String paramString2, int paramInt)
  {
    if (zzin.zzQ(2))
    {
      zzin.v("Http Response: {\n  URL:\n    " + paramString1 + "\n  Headers:");
      if (paramMap != null)
      {
        paramString1 = paramMap.keySet().iterator();
        while (paramString1.hasNext())
        {
          Object localObject = (String)paramString1.next();
          zzin.v("    " + (String)localObject + ":");
          localObject = ((List)paramMap.get(localObject)).iterator();
          while (((Iterator)localObject).hasNext())
          {
            String str = (String)((Iterator)localObject).next();
            zzin.v("      " + str);
          }
        }
      }
      zzin.v("  Body:");
      if (paramString2 != null)
      {
        int i = 0;
        while (i < Math.min(paramString2.length(), 100000))
        {
          zzin.v(paramString2.substring(i, Math.min(paramString2.length(), i + 1000)));
          i += 1000;
        }
      }
      zzin.v("    null");
      zzin.v("  Response Code:\n    " + paramInt + "\n}");
    }
  }
  
  public void zza(final AdRequestInfoParcel paramAdRequestInfoParcel, final zzk paramzzk)
  {
    zzr.zzbF().zzb(this.mContext, paramAdRequestInfoParcel.zzrl);
    zziq.zza(new Runnable()
    {
      public void run()
      {
        try
        {
          AdResponseParcel localAdResponseParcel1 = zzhd.this.zzd(paramAdRequestInfoParcel);
          localAdResponseParcel2 = localAdResponseParcel1;
          if (localAdResponseParcel1 == null) {
            localAdResponseParcel2 = new AdResponseParcel(0);
          }
        }
        catch (Exception localException)
        {
          for (;;)
          {
            try
            {
              AdResponseParcel localAdResponseParcel2;
              paramzzk.zzb(localAdResponseParcel2);
              return;
            }
            catch (RemoteException localRemoteException)
            {
              Object localObject;
              zzin.zzd("Fail to forward ad response.", localRemoteException);
            }
            localException = localException;
            zzr.zzbF().zzb(localException, true);
            zzin.zzd("Could not fetch ad response due to an Exception.", localException);
            localObject = null;
          }
        }
      }
    });
  }
  
  public AdResponseParcel zzd(AdRequestInfoParcel paramAdRequestInfoParcel)
  {
    return zza(this.mContext, this.zzIT, this.zzIS, this.zzIR, paramAdRequestInfoParcel);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\google\android\gms\internal\zzhd.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */