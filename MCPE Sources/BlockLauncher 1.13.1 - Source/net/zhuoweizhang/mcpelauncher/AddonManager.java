package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;

public class AddonManager
{
  public static AddonManager addonMgr;
  private Context context;
  private Set<String> disabledAddons;
  
  public AddonManager(Context paramContext)
  {
    this.context = paramContext;
    loadDisabledAddons();
  }
  
  public static AddonManager getAddonManager(Context paramContext)
  {
    if (addonMgr == null) {
      addonMgr = new AddonManager(paramContext.getApplicationContext());
    }
    return addonMgr;
  }
  
  public Set<String> getDisabledAddons()
  {
    return this.disabledAddons;
  }
  
  public boolean isEnabled(String paramString)
  {
    return !this.disabledAddons.contains(paramString);
  }
  
  protected void loadDisabledAddons()
  {
    this.disabledAddons = new HashSet(Arrays.asList(Utils.getPrefs(1).getString("disabledAddons", "").split(";")));
  }
  
  public void removeDeadEntries(Collection<String> paramCollection)
  {
    this.disabledAddons.retainAll(paramCollection);
    saveDisabledAddons();
  }
  
  protected void saveDisabledAddons()
  {
    SharedPreferences.Editor localEditor = Utils.getPrefs(1).edit();
    localEditor.putString("disabledAddons", PatchManager.join((String[])this.disabledAddons.toArray(PatchManager.blankArray), ";"));
    localEditor.putInt("addonManagerVersion", 1);
    localEditor.apply();
  }
  
  public void setEnabled(String paramString, boolean paramBoolean)
  {
    if (!paramBoolean) {
      this.disabledAddons.add(paramString);
    }
    for (;;)
    {
      saveDisabledAddons();
      return;
      this.disabledAddons.remove(paramString);
    }
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\AddonManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */