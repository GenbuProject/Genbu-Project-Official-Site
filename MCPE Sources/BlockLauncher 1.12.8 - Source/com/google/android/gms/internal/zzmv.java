package com.google.android.gms.internal;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.PowerManager;

public final class zzmv
{
  private static IntentFilter zzaob = new IntentFilter("android.intent.action.BATTERY_CHANGED");
  private static long zzaoc;
  private static float zzaod = NaN.0F;
  
  @TargetApi(20)
  public static int zzax(Context paramContext)
  {
    int k = 1;
    if ((paramContext == null) || (paramContext.getApplicationContext() == null)) {
      return -1;
    }
    Intent localIntent = paramContext.getApplicationContext().registerReceiver(null, zzaob);
    if (localIntent == null)
    {
      i = 0;
      if ((i & 0x7) == 0) {
        break label72;
      }
    }
    label72:
    for (int i = 1;; i = 0)
    {
      paramContext = (PowerManager)paramContext.getSystemService("power");
      if (paramContext != null) {
        break label77;
      }
      return -1;
      i = localIntent.getIntExtra("plugged", 0);
      break;
    }
    label77:
    boolean bool;
    int j;
    if (zzne.zzsl())
    {
      bool = paramContext.isInteractive();
      if (!bool) {
        break label117;
      }
      j = 1;
      label96:
      if (i == 0) {
        break label122;
      }
    }
    label117:
    label122:
    for (i = k;; i = 0)
    {
      return j << 1 | i;
      bool = paramContext.isScreenOn();
      break;
      j = 0;
      break label96;
    }
  }
  
  /* Error */
  public static float zzay(Context paramContext)
  {
    // Byte code:
    //   0: ldc 2
    //   2: monitorenter
    //   3: invokestatic 78	android/os/SystemClock:elapsedRealtime	()J
    //   6: getstatic 80	com/google/android/gms/internal/zzmv:zzaoc	J
    //   9: lsub
    //   10: ldc2_w 81
    //   13: lcmp
    //   14: ifge +21 -> 35
    //   17: getstatic 25	com/google/android/gms/internal/zzmv:zzaod	F
    //   20: ldc 23
    //   22: fcmpl
    //   23: ifeq +12 -> 35
    //   26: getstatic 25	com/google/android/gms/internal/zzmv:zzaod	F
    //   29: fstore_1
    //   30: ldc 2
    //   32: monitorexit
    //   33: fload_1
    //   34: freturn
    //   35: aload_0
    //   36: invokevirtual 37	android/content/Context:getApplicationContext	()Landroid/content/Context;
    //   39: aconst_null
    //   40: getstatic 22	com/google/android/gms/internal/zzmv:zzaob	Landroid/content/IntentFilter;
    //   43: invokevirtual 41	android/content/Context:registerReceiver	(Landroid/content/BroadcastReceiver;Landroid/content/IntentFilter;)Landroid/content/Intent;
    //   46: astore_0
    //   47: aload_0
    //   48: ifnull +27 -> 75
    //   51: aload_0
    //   52: ldc 84
    //   54: iconst_m1
    //   55: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   58: istore_2
    //   59: aload_0
    //   60: ldc 86
    //   62: iconst_m1
    //   63: invokevirtual 57	android/content/Intent:getIntExtra	(Ljava/lang/String;I)I
    //   66: istore_3
    //   67: iload_2
    //   68: i2f
    //   69: iload_3
    //   70: i2f
    //   71: fdiv
    //   72: putstatic 25	com/google/android/gms/internal/zzmv:zzaod	F
    //   75: invokestatic 78	android/os/SystemClock:elapsedRealtime	()J
    //   78: putstatic 80	com/google/android/gms/internal/zzmv:zzaoc	J
    //   81: getstatic 25	com/google/android/gms/internal/zzmv:zzaod	F
    //   84: fstore_1
    //   85: goto -55 -> 30
    //   88: astore_0
    //   89: ldc 2
    //   91: monitorexit
    //   92: aload_0
    //   93: athrow
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	94	0	paramContext	Context
    //   29	56	1	f	float
    //   58	10	2	i	int
    //   66	4	3	j	int
    // Exception table:
    //   from	to	target	type
    //   3	30	88	finally
    //   35	47	88	finally
    //   51	75	88	finally
    //   75	85	88	finally
  }
}


/* Location:              C:\Users\Genbu Hase\�h�L�������g\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\com\google\android\gms\internal\zzmv.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */