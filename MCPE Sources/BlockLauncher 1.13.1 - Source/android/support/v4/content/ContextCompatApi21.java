package android.support.v4.content;

import android.content.Context;
import android.graphics.drawable.Drawable;
import java.io.File;

class ContextCompatApi21
{
  public static File getCodeCacheDir(Context paramContext)
  {
    return paramContext.getCodeCacheDir();
  }
  
  public static Drawable getDrawable(Context paramContext, int paramInt)
  {
    return paramContext.getDrawable(paramInt);
  }
  
  public static File getNoBackupFilesDir(Context paramContext)
  {
    return paramContext.getNoBackupFilesDir();
  }
}


/* Location:              C:\Users\Genbu Hase\ドキュメント\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\android\support\v4\content\ContextCompatApi21.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */