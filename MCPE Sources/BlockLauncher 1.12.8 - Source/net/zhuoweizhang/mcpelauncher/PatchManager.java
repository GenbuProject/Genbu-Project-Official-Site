package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.io.File;
import java.util.Collection;
import java.util.Set;

public class PatchManager
{
  public static final String[] blankArray = new String[0];
  public static PatchManager patchMgr;
  private Context context;
  private Set<String> enabledPatches;
  
  public PatchManager(Context paramContext)
  {
    this.context = paramContext;
    loadEnabledPatches();
  }
  
  public static PatchManager getPatchManager(Context paramContext)
  {
    if (patchMgr == null) {
      patchMgr = new PatchManager(paramContext);
    }
    return patchMgr;
  }
  
  private boolean isEnabled(String paramString)
  {
    return this.enabledPatches.contains(paramString);
  }
  
  public static String join(String[] paramArrayOfString, String paramString)
  {
    StringBuilder localStringBuilder = new StringBuilder();
    int i = 0;
    while (i < paramArrayOfString.length)
    {
      localStringBuilder.append(paramArrayOfString[i]);
      if (i < paramArrayOfString.length - 1) {
        localStringBuilder.append(paramString);
      }
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private void setEnabled(String paramString, boolean paramBoolean)
  {
    if (paramBoolean) {
      this.enabledPatches.add(paramString);
    }
    for (;;)
    {
      saveEnabledPatches();
      return;
      this.enabledPatches.remove(paramString);
    }
  }
  
  public void disableAllPatches()
  {
    this.enabledPatches.clear();
    saveEnabledPatches();
  }
  
  public Set<String> getEnabledPatches()
  {
    return this.enabledPatches;
  }
  
  public boolean isEnabled(File paramFile)
  {
    return isEnabled(paramFile.getAbsolutePath());
  }
  
  protected void loadEnabledPatches()
  {
    this.enabledPatches = Utils.getEnabledPatches();
  }
  
  public void removeDeadEntries(Collection<String> paramCollection)
  {
    this.enabledPatches.retainAll(paramCollection);
    saveEnabledPatches();
  }
  
  protected void saveEnabledPatches()
  {
    SharedPreferences.Editor localEditor = Utils.getPrefs(1).edit();
    localEditor.putString("enabledPatches", join((String[])this.enabledPatches.toArray(blankArray), ";"));
    localEditor.putBoolean("force_prepatch", true);
    localEditor.putInt("patchManagerVersion", 1);
    localEditor.apply();
  }
  
  public void setEnabled(File paramFile, boolean paramBoolean)
  {
    setEnabled(paramFile.getAbsolutePath(), paramBoolean);
  }
  
  public void setEnabled(File[] paramArrayOfFile, boolean paramBoolean)
  {
    int j = paramArrayOfFile.length;
    int i = 0;
    if (i < j)
    {
      String str = paramArrayOfFile[i].getAbsolutePath();
      if ((str == null) || (str.length() <= 0)) {}
      for (;;)
      {
        i += 1;
        break;
        if (paramBoolean) {
          this.enabledPatches.add(str);
        } else {
          this.enabledPatches.remove(str);
        }
      }
    }
    saveEnabledPatches();
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.12.8.jar!\net\zhuoweizhang\mcpelauncher\PatchManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */