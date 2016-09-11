package net.zhuoweizhang.mcpelauncher;

import android.content.ComponentName;
import android.content.pm.ActivityInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;

public class RedirectPackageManager
  extends WrappedPackageManager
{
  protected String nativeLibraryDir;
  
  public RedirectPackageManager(PackageManager paramPackageManager, String paramString)
  {
    super(paramPackageManager);
    this.nativeLibraryDir = paramString;
  }
  
  public ActivityInfo getActivityInfo(ComponentName paramComponentName, int paramInt)
    throws PackageManager.NameNotFoundException
  {
    paramComponentName = this.wrapped.getActivityInfo(paramComponentName, paramInt);
    paramComponentName.applicationInfo.nativeLibraryDir = this.nativeLibraryDir;
    return paramComponentName;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\RedirectPackageManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */