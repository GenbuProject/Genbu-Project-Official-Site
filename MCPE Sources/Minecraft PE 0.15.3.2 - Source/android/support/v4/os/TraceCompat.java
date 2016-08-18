package android.support.v4.os;

import android.os.Build.VERSION;

public final class TraceCompat
{
  public static void beginSection(String paramString)
  {
    if (Build.VERSION.SDK_INT >= 18) {
      TraceJellybeanMR2.beginSection(paramString);
    }
  }
  
  public static void endSection()
  {
    if (Build.VERSION.SDK_INT >= 18) {
      TraceJellybeanMR2.endSection();
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\Minecraft PE 0.15.3.2.jar!\android\support\v4\os\TraceCompat.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */