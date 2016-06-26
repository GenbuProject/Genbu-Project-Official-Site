package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Build.VERSION;
import java.io.File;
import java.io.PrintStream;
import java.nio.ByteBuffer;
import java.util.Scanner;
import net.zhuoweizhang.pokerface.PokerFace;

public final class MaraudersMap
{
  public static ByteBuffer minecraftTextBuffer = null;
  private static boolean patchingInitialized = false;
  
  public static boolean initPatching(Context paramContext, long paramLong)
    throws Exception
  {
    if (patchingInitialized) {
      return true;
    }
    PokerFace.init();
    int i;
    Object localObject2;
    Object localObject1;
    label85:
    boolean bool1;
    if ((Utils.getPrefs(0).getBoolean("zz_legacy_live_patch", false)) || (ScriptManager.nativeGetArch() == 1) || (new File("/sdcard/blocklauncher_marauders_map_legacy").exists()) || (Build.VERSION.SDK_INT >= 23))
    {
      i = 1;
      localObject2 = System.out;
      StringBuilder localStringBuilder = new StringBuilder().append("Live patching is running in ");
      if (i == 0) {
        break label321;
      }
      localObject1 = "legacy";
      ((PrintStream)localObject2).println((String)localObject1 + " mode");
      bool1 = true;
      patchingInitialized = true;
      localObject1 = new Scanner(new File("/proc/self/maps"));
      paramContext = paramContext.getDir("patched", 0);
    }
    for (;;)
    {
      label138:
      if (!((Scanner)localObject1).hasNextLine()) {
        break label381;
      }
      localObject2 = ((Scanner)localObject1).nextLine().split(" ");
      if (localObject2[(localObject2.length - 1)].indexOf("libminecraftpe.so") >= 0)
      {
        long l3 = Long.parseLong(localObject2[0].substring(0, localObject2[0].indexOf("-")), 16);
        long l4 = Long.parseLong(localObject2[0].substring(localObject2[0].indexOf("-") + 1), 16) - l3;
        if (localObject2[1].indexOf("x") >= 0)
        {
          long l1;
          if (i == 0)
          {
            l1 = remapText(l3, l4, new File(paramContext, "libminecraftpe_text_section").getAbsolutePath());
            label264:
            if ((!bool1) || (l1 < 0L)) {
              break label358;
            }
          }
          label321:
          label358:
          for (boolean bool2 = true;; bool2 = false)
          {
            bool1 = bool2;
            if (l1 <= 0L) {
              break label138;
            }
            com.mojang.minecraftpe.MainActivity.minecraftLibBuffer = PokerFace.createDirectByteBuffer(l3, paramLong);
            minecraftTextBuffer = PokerFace.createDirectByteBuffer(l1, l4);
            bool1 = bool2;
            break label138;
            i = 0;
            break;
            localObject1 = "normal";
            break label85;
            long l2 = l3;
            l1 = l2;
            if (PokerFace.mprotect(l3, l4, 7) >= 0) {
              break label264;
            }
            bool1 = false;
            l1 = l2;
            break label264;
          }
        }
        if (PokerFace.mprotect(l3, l4, 3) < 0) {
          bool1 = false;
        }
      }
    }
    label381:
    ((Scanner)localObject1).close();
    setTranslationFunction(new File(paramContext, "tempXXXXXX").getAbsolutePath());
    return bool1;
  }
  
  private static native long remapText(long paramLong1, long paramLong2, String paramString);
  
  private static native void setTranslationFunction(String paramString);
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\MaraudersMap.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */