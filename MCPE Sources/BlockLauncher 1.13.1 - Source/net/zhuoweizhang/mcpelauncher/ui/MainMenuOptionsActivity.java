package net.zhuoweizhang.mcpelauncher.ui;

import android.app.AlertDialog.Builder;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.net.Uri;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Parcelable;
import android.preference.ListPreference;
import android.preference.Preference;
import android.preference.Preference.OnPreferenceClickListener;
import android.preference.PreferenceActivity;
import android.preference.PreferenceScreen;
import android.view.View;
import android.view.Window;
import de.ankri.views.Switch;
import java.io.File;
import java.io.PrintStream;
import java.lang.ref.WeakReference;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Locale;
import java.util.Set;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.R.xml;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.Utils;

public class MainMenuOptionsActivity
  extends PreferenceActivity
  implements Preference.OnPreferenceClickListener, SwitchPreference.OnCheckedChangeListener
{
  public static final String GOOGLE_PLAY_URL = "market://details?id=";
  public static final String PREFERENCES_NAME = "mcpelauncherprefs";
  public static final String PRO_APP_ID = "net.zhuoweizhang.mcpelauncher.pro";
  public static final int REQUEST_MANAGE_ADDONS = 8;
  public static final int REQUEST_MANAGE_PATCHES = 6;
  public static final int REQUEST_MANAGE_SCRIPTS = 10;
  public static final int REQUEST_MANAGE_SKINS = 7;
  public static final int REQUEST_MANAGE_TEXTURES = 5;
  public static final int REQUEST_SERVER_LIST = 9;
  public static boolean isManagingAddons = false;
  private Preference aboutPreference;
  private SwitchPreference addonsPreference;
  private SwitchPreference desktopGuiPreference;
  private SwitchPreference enableKamcordPreference;
  private Preference getProPreference;
  private Preference goToForumsPreference;
  private Set<Switch> hasInflatedSwitches = new HashSet();
  private ListPreference languagePreference;
  private Preference legacyLivePatchPreference;
  private boolean needsRestart = false;
  private Preference paranoidPreference;
  private SwitchPreference patchesPreference;
  private Preference recorderReshareLastPreference;
  private Preference recorderWatchPreference;
  private SwitchPreference reimportScriptsPreference;
  private SwitchPreference safeModePreference;
  private SwitchPreference scriptsPreference;
  private SwitchPreference skinPreference;
  private SwitchPreference texturepackPreference;
  private SwitchPreference themeDarkPreference;
  protected Thread ui = new Thread(new Runnable()
  {
    protected WeakReference<MainMenuOptionsActivity> activity = null;
    
    public void run()
    {
      this.activity = new WeakReference(MainMenuOptionsActivity.this);
      if (this.activity.get() != null)
      {
        updateStates();
        updateTP();
        updateSkin();
        updatePatches();
        updateScripts();
        try
        {
          synchronized (MainMenuOptionsActivity.this.ui)
          {
            MainMenuOptionsActivity.this.ui.wait();
          }
        }
        catch (InterruptedException localInterruptedException)
        {
          for (;;)
          {
            localInterruptedException.printStackTrace();
          }
        }
      }
      System.gc();
    }
    
    protected void updatePatches()
    {
      MainMenuOptionsActivity localMainMenuOptionsActivity = (MainMenuOptionsActivity)this.activity.get();
      final SwitchPreference localSwitchPreference = localMainMenuOptionsActivity.patchesPreference;
      Object localObject2 = null;
      final Object localObject1 = localObject2;
      int i;
      if (localSwitchPreference.content != null)
      {
        localObject1 = localObject2;
        if (localSwitchPreference.content.isChecked())
        {
          int j = MainMenuOptionsActivity.this.getDir("ptpatches", 0).listFiles().length;
          i = j;
          if (!Utils.isPro())
          {
            i = j;
            if (Utils.getMaxPatches() != -1) {
              i = Math.min(Utils.getMaxPatches(), j);
            }
          }
          localObject1 = MainMenuOptionsActivity.this.getString(R.string.plurals_patches_more);
          if (i == 1) {
            localObject1 = MainMenuOptionsActivity.this.getString(R.string.plurals_patches_one);
          }
          if (i != 0) {
            break label146;
          }
        }
      }
      label146:
      for (localObject1 = MainMenuOptionsActivity.this.getString(R.string.plurals_patches_no);; localObject1 = Utils.getEnabledPatches().size() + "/" + i + " " + (String)localObject1)
      {
        localMainMenuOptionsActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            localSwitchPreference.setSummary(localObject1);
          }
        });
        return;
      }
    }
    
    protected void updateScripts()
    {
      MainMenuOptionsActivity localMainMenuOptionsActivity = (MainMenuOptionsActivity)this.activity.get();
      final SwitchPreference localSwitchPreference = localMainMenuOptionsActivity.scriptsPreference;
      Object localObject2 = null;
      final Object localObject1 = localObject2;
      int i;
      if (localSwitchPreference.content != null)
      {
        localObject1 = localObject2;
        if (localSwitchPreference.content.isChecked())
        {
          int j = MainMenuOptionsActivity.this.getDir("modscripts", 0).listFiles().length;
          i = j;
          if (!Utils.isPro())
          {
            i = j;
            if (Utils.getMaxScripts() != -1) {
              i = Math.min(Utils.getMaxScripts(), j);
            }
          }
          localObject1 = MainMenuOptionsActivity.this.getString(R.string.plurals_scripts_more);
          if (i == 1) {
            localObject1 = MainMenuOptionsActivity.this.getString(R.string.plurals_scripts_one);
          }
          if (i != 0) {
            break label146;
          }
        }
      }
      label146:
      for (localObject1 = MainMenuOptionsActivity.this.getString(R.string.plurals_scripts_no);; localObject1 = Utils.getEnabledScripts().size() + "/" + i + " " + (String)localObject1)
      {
        localMainMenuOptionsActivity.runOnUiThread(new Runnable()
        {
          public void run()
          {
            localSwitchPreference.setSummary(localObject1);
          }
        });
        return;
      }
    }
    
    protected void updateSkin() {}
    
    protected void updateStates()
    {
      ((MainMenuOptionsActivity)this.activity.get()).runOnUiThread(new Runnable()
      {
        public void run()
        {
          MainMenuOptionsActivity.this.togglePrefs();
        }
      });
    }
    
    protected void updateTP() {}
  });
  private SwitchPreference useControllerPreference;
  
  private void controllerChange(Switch paramSwitch)
  {
    if (!paramSwitch.isChecked()) {}
    while (Utils.hasExtrasPackage(this)) {
      return;
    }
    paramSwitch.setChecked(false);
    Utils.getPrefs(0).edit().putBoolean("zz_use_controller", false).apply();
    new AlertDialog.Builder(this).setMessage(R.string.purchase_extras_package).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
        paramAnonymousDialogInterface.setData(Uri.parse("https://gist.github.com/zhuowei/4538923d1963524b71fc#file-getextras-md"));
        MainMenuOptionsActivity.this.startActivity(paramAnonymousDialogInterface);
      }
    }).setNegativeButton(17039360, null).show();
  }
  
  private File findLastKamcordVideo()
  {
    Object localObject = new File(new File(Environment.getExternalStorageDirectory(), "Kamcord"), "PP3JLc1YQlxEBNbiuYewGLsn5tBs1J5DGv6BWko2ePi-" + getPackageName());
    if (!((File)localObject).exists()) {
      return null;
    }
    localObject = ((File)localObject).listFiles();
    int j = localObject.length;
    int i = 0;
    label61:
    File localFile;
    if (i < j)
    {
      localFile = localObject[i];
      if (localFile.isDirectory()) {
        break label86;
      }
    }
    label86:
    while ((!new File(localFile, "thumbnail.jpg").exists()) || (!new File(localFile, "video.mp4").exists()))
    {
      i += 1;
      break label61;
      break;
    }
    return new File(localFile, "video.mp4");
  }
  
  private void forceRestart()
  {
    new Thread(new Runnable()
    {
      public void run()
      {
        try
        {
          Thread.sleep(200L);
          System.exit(0);
          return;
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }).start();
  }
  
  private void goToForums()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://www.minecraftforum.net/topic/1675581-blocklauncher-an-android-app-that-patches-minecraft-pe-without-reinstall/"));
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  private void initLanguagePreference()
  {
    String[] arrayOfString = getResources().getString(R.string.languages_supported).split(",");
    ArrayList localArrayList = new ArrayList();
    localArrayList.add(getResources().getString(R.string.pref_zz_language_override_default));
    Locale localLocale = getResources().getConfiguration().locale;
    int j = arrayOfString.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = arrayOfString[i];
      if (((String)localObject).length() == 0)
      {
        i += 1;
      }
      else
      {
        localObject = ((String)localObject).split("_");
        String str = localObject[0];
        if (localObject.length > 1) {}
        for (localObject = localObject[1];; localObject = "")
        {
          localArrayList.add(new Locale(str, (String)localObject).getDisplayName(localLocale));
          break;
        }
      }
    }
    this.languagePreference.setEntries((CharSequence[])localArrayList.toArray(new String[0]));
    this.languagePreference.setEntryValues(arrayOfString);
  }
  
  private void reshareLast()
  {
    Object localObject = findLastKamcordVideo();
    if (localObject == null)
    {
      new AlertDialog.Builder(this).setMessage(R.string.recorder_no_recording).setPositiveButton(17039370, null).show();
      return;
    }
    localObject = Uri.fromFile((File)localObject);
    Intent localIntent = new Intent("android.intent.action.SEND");
    localIntent.setType("video/mp4");
    localIntent.putExtra("android.intent.extra.STREAM", (Parcelable)localObject);
    startActivity(Intent.createChooser(localIntent, "Share video"));
  }
  
  private void startAbout()
  {
    startActivity(new Intent(this, AboutAppActivity.class));
  }
  
  private void startGetPro()
  {
    Intent localIntent = new Intent("android.intent.action.VIEW");
    localIntent.setData(Uri.parse("http://www.minecraftforum.net/topic/1675581-blocklauncher-an-android-app-that-patches-minecraft-pe-without-reinstall/"));
    try
    {
      startActivity(localIntent);
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  protected boolean hasRecorder()
  {
    return getPackageName().equals("net.zhuoweizhang.mcpelauncher.pro");
  }
  
  protected void manageAddons()
  {
    isManagingAddons = true;
    startActivityForResult(new Intent(this, ManageAddonsActivity.class), 8);
  }
  
  protected void managePatches()
  {
    startActivityForResult(new Intent(this, ManagePatchesActivity.class), 6);
  }
  
  protected void manageScripts()
  {
    startActivityForResult(new Intent(this, ManageScriptsActivity.class), 10);
  }
  
  protected void manageSkins()
  {
    startActivityForResult(new Intent(this, ManageSkinsActivity.class), 7);
  }
  
  protected void manageTexturepacks()
  {
    startActivityForResult(new Intent(this, TexturePacksActivity.class), 5);
  }
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent arg3)
  {
    synchronized (this.ui)
    {
      this.ui.notifyAll();
      switch (paramInt1)
      {
      }
    }
    if (paramInt2 == -1)
    {
      this.needsRestart = true;
      return;
      isManagingAddons = false;
      if (paramInt2 == -1)
      {
        this.needsRestart = true;
        return;
        if (paramInt2 == -1)
        {
          onBackPressed();
          return;
          if (paramInt2 == -1)
          {
            this.needsRestart = true;
            return;
          }
        }
      }
    }
  }
  
  public void onBackPressed()
  {
    if (this.needsRestart)
    {
      forceRestart();
      return;
    }
    super.onBackPressed();
  }
  
  public void onCheckedChange(Switch paramSwitch)
  {
    this.needsRestart = true;
    if ((this.useControllerPreference != null) && (paramSwitch == this.useControllerPreference.content)) {
      controllerChange(paramSwitch);
    }
    for (;;)
    {
      return;
      if ((this.reimportScriptsPreference != null) && (paramSwitch == this.reimportScriptsPreference.content))
      {
        if (!paramSwitch.isChecked()) {
          continue;
        }
        new AlertDialog.Builder(this).setMessage(R.string.manage_scripts_reimport_enable_dialog).setPositiveButton(17039370, null).show();
        return;
      }
      synchronized (this.ui)
      {
        this.ui.notifyAll();
        if (paramSwitch != this.texturepackPreference.content) {
          continue;
        }
        ??? = ManageTexturepacksActivity.REQUEST_ENABLE;
        if (!paramSwitch.isChecked()) {
          ??? = ManageTexturepacksActivity.REQUEST_DISABLE;
        }
        ManageTexturepacksActivity.setTexturepack((File)???, null);
        return;
      }
    }
  }
  
  public void onCreate(Bundle paramBundle)
  {
    Utils.setLanguageOverride();
    super.onCreate(paramBundle);
    setUp();
    this.ui.start();
  }
  
  public boolean onPreferenceClick(Preference paramPreference)
  {
    synchronized (this.ui)
    {
      this.ui.notifyAll();
      if (paramPreference == this.patchesPreference)
      {
        managePatches();
        return true;
      }
    }
    if (paramPreference == this.texturepackPreference)
    {
      manageTexturepacks();
      return true;
    }
    if (paramPreference == this.getProPreference)
    {
      startGetPro();
      return true;
    }
    if (paramPreference == this.aboutPreference)
    {
      startAbout();
      return true;
    }
    if (paramPreference == this.addonsPreference)
    {
      manageAddons();
      return true;
    }
    if (paramPreference == this.scriptsPreference)
    {
      manageScripts();
      return true;
    }
    if (paramPreference == this.skinPreference)
    {
      manageSkins();
      return true;
    }
    if (paramPreference == this.goToForumsPreference)
    {
      goToForums();
      return true;
    }
    if ((paramPreference == this.languagePreference) || (paramPreference == this.paranoidPreference) || (paramPreference == this.legacyLivePatchPreference))
    {
      this.needsRestart = true;
      return false;
    }
    if (paramPreference == this.recorderWatchPreference) {
      try
      {
        Class.forName("com.kamcord.android.Kamcord").getDeclaredMethod("showWatchView", (Class[])null).invoke(null, new Object[0]);
        finish();
        return false;
      }
      catch (Exception paramPreference)
      {
        for (;;)
        {
          paramPreference.printStackTrace();
        }
      }
    }
    if (paramPreference == this.recorderReshareLastPreference)
    {
      reshareLast();
      return false;
    }
    return false;
  }
  
  protected void onRestart()
  {
    super.onRestart();
    setPreferenceScreen(null);
    setUp();
    if (!this.ui.isAlive()) {
      this.ui.start();
    }
  }
  
  protected void onStart()
  {
    super.onStart();
    getWindow().getDecorView().post(new Runnable()
    {
      public void run()
      {
        synchronized (MainMenuOptionsActivity.this.ui)
        {
          MainMenuOptionsActivity.this.ui.notifyAll();
          return;
        }
      }
    });
  }
  
  protected void setUp()
  {
    addPreferencesFromResource(R.xml.preferences);
    this.texturepackPreference = ((SwitchPreference)findPreference("zz_texture_pack_enable"));
    if (this.texturepackPreference != null)
    {
      this.texturepackPreference.setListener(this);
      this.texturepackPreference.setOnPreferenceClickListener(this);
    }
    this.patchesPreference = ((SwitchPreference)findPreference("zz_manage_patches"));
    if (this.patchesPreference != null)
    {
      this.patchesPreference.setListener(this);
      this.patchesPreference.setOnPreferenceClickListener(this);
    }
    this.safeModePreference = ((SwitchPreference)findPreference("zz_safe_mode"));
    if (this.safeModePreference != null)
    {
      this.safeModePreference.setListener(this);
      this.safeModePreference.setOnPreferenceClickListener(this);
    }
    this.addonsPreference = ((SwitchPreference)findPreference("zz_load_native_addons"));
    if (this.addonsPreference != null) {
      this.addonsPreference.setOnPreferenceClickListener(this);
    }
    this.skinPreference = ((SwitchPreference)findPreference("zz_skin_enable"));
    if (this.skinPreference != null)
    {
      this.skinPreference.setListener(this);
      this.skinPreference.setOnPreferenceClickListener(this);
    }
    this.scriptsPreference = ((SwitchPreference)findPreference("zz_script_enable"));
    if (this.scriptsPreference != null)
    {
      this.scriptsPreference.setListener(this);
      this.scriptsPreference.setOnPreferenceClickListener(this);
      if (ScriptManager.isRemote) {
        this.scriptsPreference.setEnabled(false);
      }
    }
    try
    {
      if (!getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionName.startsWith("0.15")) {
        this.scriptsPreference.setEnabled(false);
      }
      this.languagePreference = ((ListPreference)findPreference("zz_language_override"));
      if (this.languagePreference != null)
      {
        initLanguagePreference();
        this.languagePreference.setOnPreferenceClickListener(this);
      }
      this.paranoidPreference = findPreference("zz_script_paranoid_mode");
      if (this.paranoidPreference != null) {
        this.paranoidPreference.setOnPreferenceClickListener(this);
      }
      this.legacyLivePatchPreference = findPreference("zz_legacy_live_patch");
      if (this.legacyLivePatchPreference != null) {
        this.legacyLivePatchPreference.setOnPreferenceClickListener(this);
      }
      this.aboutPreference = findPreference("zz_about");
      if (this.aboutPreference != null) {
        this.aboutPreference.setOnPreferenceClickListener(this);
      }
      this.getProPreference = findPreference("zz_get_pro");
      if (this.getProPreference != null) {
        this.getProPreference.setOnPreferenceClickListener(this);
      }
      this.goToForumsPreference = findPreference("zz_go_to_forums");
      if (this.goToForumsPreference != null) {
        this.goToForumsPreference.setOnPreferenceClickListener(this);
      }
      Preference localPreference = findPreference("zz_immersive_mode");
      if ((localPreference != null) && (Build.VERSION.SDK_INT < 19))
      {
        getPreferenceScreen().removePreference(localPreference);
        localPreference.setEnabled(false);
      }
      boolean bool = hasRecorder();
      System.out.println("Has recorder: " + bool);
      this.recorderWatchPreference = findPreference("zz_watch_recording");
      if (this.recorderWatchPreference != null)
      {
        if (!bool) {
          this.recorderWatchPreference.setEnabled(false);
        }
      }
      else
      {
        this.recorderReshareLastPreference = findPreference("zz_reshare_last_recording");
        if (this.recorderReshareLastPreference != null)
        {
          if (bool) {
            break label750;
          }
          this.recorderReshareLastPreference.setEnabled(false);
        }
        this.useControllerPreference = ((SwitchPreference)findPreference("zz_use_controller"));
        if (this.useControllerPreference != null)
        {
          if (Build.VERSION.SDK_INT >= 12) {
            break label761;
          }
          getPreferenceScreen().removePreference(this.useControllerPreference);
        }
        this.enableKamcordPreference = ((SwitchPreference)findPreference("zz_enable_kamcord"));
        if (this.enableKamcordPreference != null)
        {
          if ((Build.VERSION.SDK_INT >= 16) && (Build.VERSION.SDK_INT < 23)) {
            break label772;
          }
          getPreferenceScreen().removePreference(this.enableKamcordPreference);
        }
        this.themeDarkPreference = ((SwitchPreference)findPreference("zz_theme_dark"));
        if (this.themeDarkPreference != null) {
          this.themeDarkPreference.setListener(this);
        }
        this.reimportScriptsPreference = ((SwitchPreference)findPreference("zz_reimport_scripts"));
        if (this.reimportScriptsPreference != null) {
          this.reimportScriptsPreference.setListener(this);
        }
        this.desktopGuiPreference = ((SwitchPreference)findPreference("zz_desktop_gui"));
        if (this.desktopGuiPreference != null) {
          this.desktopGuiPreference.setListener(this);
        }
        return;
      }
    }
    catch (Exception localException)
    {
      for (;;)
      {
        localException.printStackTrace();
        continue;
        this.recorderWatchPreference.setOnPreferenceClickListener(this);
        continue;
        label750:
        this.recorderReshareLastPreference.setOnPreferenceClickListener(this);
        continue;
        label761:
        this.useControllerPreference.setListener(this);
        continue;
        label772:
        this.enableKamcordPreference.setListener(this);
      }
    }
  }
  
  protected void togglePrefs() {}
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ui\MainMenuOptionsActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */