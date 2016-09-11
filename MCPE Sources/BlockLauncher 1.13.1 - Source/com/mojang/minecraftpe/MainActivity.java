package com.mojang.minecraftpe;

import android.annotation.SuppressLint;
import android.annotation.TargetApi;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.app.Dialog;
import android.app.NativeActivity;
import android.content.ContentResolver;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnCancelListener;
import android.content.DialogInterface.OnClickListener;
import android.content.DialogInterface.OnDismissListener;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.content.res.AssetManager;
import android.content.res.Configuration;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Rect;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.media.MediaScannerConnection;
import android.net.Uri;
import android.net.http.SslError;
import android.os.Build;
import android.os.Build.VERSION;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.os.Message;
import android.os.Process;
import android.os.Vibrator;
import android.provider.MediaStore.Images.Media;
import android.text.ClipboardManager;
import android.text.Editable;
import android.text.Selection;
import android.text.Spannable;
import android.text.TextWatcher;
import android.util.DisplayMetrics;
import android.util.Log;
import android.util.SparseArray;
import android.view.KeyCharacterMap;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.view.Window;
import android.view.inputmethod.InputMethodManager;
import android.webkit.SslErrorHandler;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.PopupWindow.OnDismissListener;
import android.widget.TextView;
import android.widget.TextView.OnEditorActionListener;
import android.widget.Toast;
import com.joshuahuelsman.patchtool.PTPatch;
import dalvik.system.BaseDexClassLoader;
import dalvik.system.DexFile;
import java.io.BufferedInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.io.Writer;
import java.lang.ref.WeakReference;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.ByteBuffer;
import java.nio.charset.Charset;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;
import java.util.UUID;
import net.zhuoweizhang.mcpelauncher.AddonManager;
import net.zhuoweizhang.mcpelauncher.AddonOverrideTexturePack;
import net.zhuoweizhang.mcpelauncher.MaraudersMap;
import net.zhuoweizhang.mcpelauncher.MinecraftVersion;
import net.zhuoweizhang.mcpelauncher.PatchManager;
import net.zhuoweizhang.mcpelauncher.R.id;
import net.zhuoweizhang.mcpelauncher.R.integer;
import net.zhuoweizhang.mcpelauncher.R.layout;
import net.zhuoweizhang.mcpelauncher.R.string;
import net.zhuoweizhang.mcpelauncher.RealmsRedirectInfo;
import net.zhuoweizhang.mcpelauncher.RedirectPackageManager;
import net.zhuoweizhang.mcpelauncher.ScriptManager;
import net.zhuoweizhang.mcpelauncher.ScriptTextureDownloader;
import net.zhuoweizhang.mcpelauncher.TexturePack;
import net.zhuoweizhang.mcpelauncher.Utils;
import net.zhuoweizhang.mcpelauncher.ZipTexturePack;
import net.zhuoweizhang.mcpelauncher.api.modpe.ControllerManager;
import net.zhuoweizhang.mcpelauncher.patch.PatchUtils;
import net.zhuoweizhang.mcpelauncher.texture.AtlasProvider;
import net.zhuoweizhang.mcpelauncher.texture.ResourcePackManifestProvider;
import net.zhuoweizhang.mcpelauncher.texture.TexturePackLoader;
import net.zhuoweizhang.mcpelauncher.ui.GetSubstrateActivity;
import net.zhuoweizhang.mcpelauncher.ui.HoverCar;
import net.zhuoweizhang.mcpelauncher.ui.MainMenuOptionsActivity;
import net.zhuoweizhang.mcpelauncher.ui.ManageScriptsActivity;
import net.zhuoweizhang.mcpelauncher.ui.NerdyStuffActivity;
import net.zhuoweizhang.mcpelauncher.ui.NoMinecraftActivity;
import net.zhuoweizhang.mcpelauncher.ui.TexturePacksActivity;
import org.mozilla.javascript.RhinoException;

@SuppressLint({"SdCardPath"})
public class MainActivity
  extends NativeActivity
{
  public static final int DIALOG_COPY_WORLD = 4;
  public static final int DIALOG_CRASH_SAFE_MODE = 4096;
  public static final int DIALOG_CREATE_WORLD = 1;
  public static final int DIALOG_FIRST_LAUNCH = 4099;
  public static final int DIALOG_INSERT_TEXT = 4103;
  public static final int DIALOG_INVALID_PATCHES = 4098;
  public static final int DIALOG_MULTIPLAYER_DISABLE_SCRIPTS = 4104;
  public static final int DIALOG_NOT_SUPPORTED = 4101;
  public static final int DIALOG_RUNTIME_OPTIONS = 4097;
  public static final int DIALOG_RUNTIME_OPTIONS_WITH_INSERT_TEXT = 4105;
  public static final int DIALOG_SELINUX_BROKE_EVERYTHING = 4106;
  public static final int DIALOG_SETTINGS = 3;
  public static final int DIALOG_UPDATE_TEXTURE_PACK = 4102;
  public static final int DIALOG_VERSION_MISMATCH_SAFE_MODE = 4100;
  public static final String[] GAME_MODES = { "creative", "survival" };
  public static final String HALF_SUPPORT_VERSION = "~~~~";
  public static final String HEY_CAN_YOU_STOP_STEALING_BLOCKLAUNCHER_CODE = "please?";
  public static final int INPUT_STATUS_CANCELLED = 0;
  public static final int INPUT_STATUS_IN_PROGRESS = -1;
  public static final int INPUT_STATUS_OK = 1;
  private static final int MAX_FAILS = 2;
  private static String MC_NATIVE_LIBRARY_DIR = "/data/data/com.mojang.minecraftpe/lib/";
  private static String MC_NATIVE_LIBRARY_LOCATION = "/data/data/com.mojang.minecraftpe/lib/libminecraftpe.so";
  public static final String MOJANG_ACCOUNT_LOGIN_URL = "https://account.mojang.com/m/login?app=mcpe";
  public static final String PT_PATCHES_DIR = "ptpatches";
  private static final int REQUEST_MANAGE_SCRIPTS = 417;
  private static final int REQUEST_MANAGE_TEXTURES = 416;
  private static final int REQUEST_PICK_IMAGE = 415;
  public static final String SCRIPT_SUPPORT_VERSION = "0.15";
  public static final String TAG = "BlockLauncher/Main";
  public static WeakReference<MainActivity> currentMainActivity;
  public static List<String> failedPatches = new ArrayList();
  public static boolean hasPrePatched = false;
  public static boolean libLoaded = false;
  public static Set<String> loadedAddons;
  public static ByteBuffer minecraftLibBuffer;
  public static boolean tempSafeMode = false;
  public AddonOverrideTexturePack addonOverrideTexturePackInstance = null;
  private int commandHistoryIndex = 0;
  private List<String> commandHistoryList = new ArrayList();
  private View commandHistoryView;
  private PopupWindow commandHistoryWindow;
  private boolean controllerInit = false;
  protected DisplayMetrics displayMetrics;
  protected boolean fakePackage = false;
  public boolean forceFallback = false;
  protected boolean hasRecorder = false;
  private boolean hasResetSafeModeCounter = false;
  private boolean hiddenTextDismissAfterOneLine = false;
  private TextView hiddenTextView;
  private PopupWindow hiddenTextWindow;
  private HoverCar hoverCar = null;
  protected int inputStatus = -1;
  protected boolean isRecording = false;
  private Toast lastToast = null;
  private Dialog loginDialog;
  private WebView loginWebView;
  private final Handler mainHandler = new Handler()
  {
    public void dispatchMessage(Message paramAnonymousMessage)
    {
      MainActivity.this.toggleRecording();
    }
  };
  public ApplicationInfo mcAppInfo;
  private PackageInfo mcPkgInfo;
  private int mcpeArch = 0;
  protected Context minecraftApkContext;
  public boolean minecraftApkForwardLocked = false;
  private Typeface minecraftTypeface = null;
  protected MinecraftVersion minecraftVersion;
  private Button nextButton;
  private boolean overlyZealousSELinuxSafeMode = false;
  private long pickImageCallbackAddress = 0L;
  private Intent pickImageResult;
  private Button prevButton;
  public String refreshToken = "";
  private SparseArray<HurlRunner> requestMap = new SparseArray();
  public boolean requiresGuiBlocksPatch = false;
  public String session = "";
  public List<TexturePack> textureOverrides = new ArrayList();
  protected TexturePack texturePack;
  protected String[] userInputStrings = null;
  
  static
  {
    currentMainActivity = null;
    loadedAddons = new HashSet();
  }
  
  private void addLibraryDirToPath(String paramString)
  {
    try
    {
      Object localObject2 = getClassLoader();
      Object localObject1 = Utils.getDeclaredFieldRecursive(localObject2.getClass(), "pathList");
      ((Field)localObject1).setAccessible(true);
      localObject1 = ((Field)localObject1).get(localObject2);
      Object localObject3 = localObject1.getClass();
      Object localObject4 = Utils.getDeclaredFieldRecursive((Class)localObject3, "nativeLibraryDirectories");
      ((Field)localObject4).setAccessible(true);
      Object localObject5 = ((Field)localObject4).get(localObject1);
      if ((localObject5 instanceof File[]))
      {
        localObject5 = (File[])localObject5;
        File[] arrayOfFile = addToFileList((File[])localObject5, new File(paramString));
        if (localObject5 != arrayOfFile) {
          ((Field)localObject4).set(localObject1, arrayOfFile);
        }
      }
      localObject3 = Utils.getDeclaredFieldRecursive((Class)localObject3, "nativeLibraryPathElements");
      if ((localObject3 != null) && ((localObject2 instanceof BaseDexClassLoader)) && (((BaseDexClassLoader)localObject2).findLibrary("minecraftpe") == null))
      {
        ((Field)localObject3).setAccessible(true);
        localObject2 = (Object[])((Field)localObject3).get(localObject1);
        localObject4 = localObject2.getClass().getComponentType().getConstructor(new Class[] { File.class, Boolean.TYPE, File.class, DexFile.class });
        ((Constructor)localObject4).setAccessible(true);
        paramString = ((Constructor)localObject4).newInstance(new Object[] { new File(paramString), Boolean.valueOf(true), null, null });
        localObject2 = Arrays.copyOf((Object[])localObject2, localObject2.length + 1);
        localObject2[(localObject2.length - 1)] = paramString;
        System.out.println(localObject2);
        ((Field)localObject3).set(localObject1, localObject2);
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private File[] addToFileList(File[] paramArrayOfFile, File paramFile)
  {
    int j = paramArrayOfFile.length;
    int i = 0;
    while (i < j)
    {
      if (paramArrayOfFile[i].equals(paramFile)) {
        return paramArrayOfFile;
      }
      i += 1;
    }
    File[] arrayOfFile = new File[paramArrayOfFile.length + 1];
    System.arraycopy(paramArrayOfFile, 0, arrayOfFile, 1, paramArrayOfFile.length);
    arrayOfFile[0] = paramFile;
    return arrayOfFile;
  }
  
  private boolean checkAddonArch(File paramFile)
  {
    try
    {
      int i = Utils.getElfArch(paramFile);
      int j = this.mcpeArch;
      return i == j;
    }
    catch (IOException paramFile) {}
    return true;
  }
  
  private void checkArch()
  {
    try
    {
      this.mcpeArch = Utils.getElfArch(new File(MC_NATIVE_LIBRARY_LOCATION));
      int i = Utils.getElfArch(new File(getApplicationInfo().nativeLibraryDir + "/libmcpelauncher.so"));
      if (this.mcpeArch != i)
      {
        Intent localIntent = new Intent(this, NoMinecraftActivity.class);
        localIntent.putExtra("message", getResources().getString(R.string.minecraft_wrong_arch).toString().replaceAll("ARCH", Utils.getArchName(i)));
        localIntent.putExtra("learnmore_uri", "https://github.com/zhuowei/MCPELauncher/issues/495");
        startActivity(localIntent);
        finish();
        try
        {
          Thread.sleep(1000L);
          System.exit(0);
          return;
        }
        catch (Exception localException)
        {
          for (;;)
          {
            localException.printStackTrace();
          }
        }
      }
      return;
    }
    catch (IOException localIOException) {}
  }
  
  private void checkForSubstrate()
  {
    if (!Build.CPU_ABI.equals("x86")) {
      return;
    }
    Object localObject1 = null;
    try
    {
      localObject2 = getPackageManager().getPackageInfo("com.saurik.substrate", 0);
      localObject1 = localObject2;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      Object localObject2;
      File localFile;
      for (;;) {}
    }
    if (localObject1 == null)
    {
      finish();
      startActivity(new Intent(this, GetSubstrateActivity.class));
      try
      {
        Thread.sleep(100L);
        Process.killProcess(Process.myPid());
        return;
      }
      catch (Throwable localThrowable)
      {
        return;
      }
    }
    localObject2 = getFileStreamPath("libmcpelauncher_tinysubstrate.so");
    if (!((File)localObject2).exists()) {
      localFile = new File(localThrowable.applicationInfo.nativeLibraryDir, "libsubstrate.so");
    }
    try
    {
      PatchUtils.copy(localFile, (File)localObject2);
      System.load(((File)localObject2).getAbsolutePath());
      return;
    }
    catch (IOException localIOException)
    {
      throw new RuntimeException(localIOException);
    }
  }
  
  private void clearRuntimeOptionsDialog()
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.removeDialog(4097);
        MainActivity.this.removeDialog(4105);
      }
    });
  }
  
  private File copyContentStoreToTempFile(Uri paramUri)
  {
    File localFile;
    FileOutputStream localFileOutputStream;
    try
    {
      localFile = new File(getExternalFilesDir(null), "skintemp.png");
      localFile.getParentFile().mkdirs();
      paramUri = getContentResolver().openInputStream(paramUri);
      localFileOutputStream = new FileOutputStream(localFile);
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        int i = paramUri.read(arrayOfByte);
        if (i == -1) {
          break;
        }
        localFileOutputStream.write(arrayOfByte, 0, i);
      }
      paramUri.close();
    }
    catch (IOException paramUri)
    {
      paramUri.printStackTrace();
      return new File("/sdcard/totally/fake");
    }
    localFileOutputStream.close();
    return localFile;
  }
  
  private void disableAllPatches()
  {
    PatchManager.getPatchManager(this).disableAllPatches();
  }
  
  private void disableTransparentSystemBar()
  {
    if (Build.VERSION.SDK_INT < 21) {
      return;
    }
    getWindow().clearFlags(Integer.MIN_VALUE);
  }
  
  private void enableSoftMenuKey()
  {
    if (Build.VERSION.SDK_INT >= 19) {}
    for (int i = 1073741824;; i = 134217728)
    {
      getWindow().addFlags(i);
      return;
    }
  }
  
  public static long findMinecraftLibLength()
    throws Exception
  {
    return new File(MC_NATIVE_LIBRARY_LOCATION).length();
  }
  
  private void fixMyEpicFail()
  {
    SharedPreferences localSharedPreferences = Utils.getPrefs(1);
    int k = localSharedPreferences.getInt("last_bl_version", 0);
    int i = 0;
    try
    {
      int j = getPackageManager().getPackageInfo(getPackageName(), 0).versionCode;
      i = j;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    if (k < 69) {
      Utils.getPrefs(0).edit().putBoolean("zz_load_native_addons", true).apply();
    }
    if (k != i) {
      localSharedPreferences.edit().putInt("last_bl_version", i).apply();
    }
  }
  
  private InputStream getRegularInputStream(String paramString)
  {
    try
    {
      paramString = new BufferedInputStream(new FileInputStream(new File(paramString)));
      return paramString;
    }
    catch (IOException paramString)
    {
      paramString.printStackTrace();
    }
    return null;
  }
  
  private void initAtlasMeta()
  {
    if (Utils.isSafeMode()) {
      return;
    }
    try
    {
      AtlasProvider localAtlasProvider1 = new AtlasProvider("resourcepacks/vanilla/images/terrain_texture.json", "images/terrain-atlas/", "block.bl_modpkg.");
      AtlasProvider localAtlasProvider2 = new AtlasProvider("resourcepacks/vanilla/images/item_texture.json", "images/items-opaque/", "item.bl_modpkg.");
      ResourcePackManifestProvider localResourcePackManifestProvider = new ResourcePackManifestProvider("resourcepacks/vanilla/resources.json");
      localAtlasProvider1.initAtlas(this);
      localAtlasProvider2.initAtlas(this);
      localResourcePackManifestProvider.init(this);
      localResourcePackManifestProvider.addTextures(localAtlasProvider1.addedTextureNames);
      localResourcePackManifestProvider.addTextures(localAtlasProvider2.addedTextureNames);
      this.textureOverrides.add(0, localAtlasProvider1);
      this.textureOverrides.add(1, localAtlasProvider2);
      this.textureOverrides.add(2, localResourcePackManifestProvider);
      ScriptManager.terrainMeta = localAtlasProvider1;
      ScriptManager.itemsMeta = localAtlasProvider2;
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reportError(localException);
    }
  }
  
  private boolean isAddonCompat(String paramString)
  {
    if (paramString == null) {}
    do
    {
      do
      {
        for (;;)
        {
          return false;
          if (paramString.equals(this.mcPkgInfo.versionName)) {
            return true;
          }
          if (this.mcPkgInfo.versionName.startsWith("0.14"))
          {
            String str = this.mcPkgInfo.versionName.substring(this.mcPkgInfo.versionName.lastIndexOf(".") + 1);
            try
            {
              if (Integer.parseInt(str) < 2)
              {
                if (paramString.startsWith("0.14.0")) {
                  return true;
                }
                if (paramString.equals("0.14.1")) {
                  return true;
                }
              }
              else
              {
                if (paramString.equals("0.14.2")) {
                  return true;
                }
                boolean bool = paramString.equals("0.14.3");
                if (bool) {
                  return true;
                }
              }
            }
            catch (Exception paramString)
            {
              paramString.printStackTrace();
              return true;
            }
          }
        }
      } while (!this.mcPkgInfo.versionName.startsWith("0.15"));
      if (paramString.startsWith("0.15.0")) {
        return true;
      }
    } while (!paramString.startsWith("0.15.1"));
    return true;
  }
  
  private boolean isCommandHistoryEnabled()
  {
    return Utils.getPrefs(0).getBoolean("zz_command_history", true);
  }
  
  private boolean isForcingController()
  {
    boolean bool2 = false;
    boolean bool1 = bool2;
    if (Build.VERSION.SDK_INT >= 12)
    {
      bool1 = bool2;
      if (Utils.hasExtrasPackage(this))
      {
        bool1 = bool2;
        if (Utils.getPrefs(0).getBoolean("zz_use_controller", false)) {
          bool1 = true;
        }
      }
    }
    return bool1;
  }
  
  public static native void nativeOnPickImageCanceled(long paramLong);
  
  public static native void nativeOnPickImageSuccess(long paramLong, String paramString);
  
  private void navigateCommandHistory(int paramInt)
  {
    int i = this.commandHistoryIndex + paramInt;
    paramInt = i;
    if (i < 0) {
      paramInt = 0;
    }
    i = paramInt;
    if (paramInt >= this.commandHistoryList.size()) {
      i = this.commandHistoryList.size() - 1;
    }
    setCommandHistoryIndex(i);
    String str = (String)this.commandHistoryList.get(i);
    this.hiddenTextView.setText(str);
    Selection.setSelection((Spannable)this.hiddenTextView.getText(), str.length());
  }
  
  private void prePatch()
    throws Exception
  {
    File localFile1 = getDir("patched", 0);
    Object localObject2 = new File(this.mcAppInfo.nativeLibraryDir + "/libminecraftpe.so");
    File localFile2 = new File(localFile1, "libminecraftpe.so");
    boolean bool = Utils.getPrefs(1).getBoolean("force_prepatch", true);
    if ((!hasPrePatched) && (Utils.getEnabledPatches().size() == 0))
    {
      hasPrePatched = true;
      if (localFile2.exists()) {
        localFile2.delete();
      }
      if (bool) {
        Utils.getPrefs(1).edit().putBoolean("force_prepatch", false).putInt("prepatch_version", this.mcPkgInfo.versionCode).apply();
      }
      return;
    }
    byte[] arrayOfByte;
    Object localObject1;
    int i;
    int j;
    if ((!hasPrePatched) && ((!localFile2.exists()) || (bool)))
    {
      System.out.println("Forcing new prepatch");
      arrayOfByte = new byte[(int)((File)localObject2).length()];
      localObject1 = ByteBuffer.wrap(arrayOfByte);
      localObject2 = new FileInputStream((File)localObject2);
      ((InputStream)localObject2).read(arrayOfByte);
      ((InputStream)localObject2).close();
      i = 0;
      j = getMaxNumPatches();
      localObject2 = Utils.getEnabledPatches().iterator();
    }
    for (;;)
    {
      if (((Iterator)localObject2).hasNext())
      {
        localObject3 = (String)((Iterator)localObject2).next();
        if ((j < 0) || (i < j)) {}
      }
      else
      {
        if (this.requiresGuiBlocksPatch)
        {
          System.out.println("Patching guiblocks");
          localObject2 = new PTPatch();
          if (this.minecraftVersion.guiBlocksPatch != null)
          {
            ((PTPatch)localObject2).loadPatch(this.minecraftVersion.guiBlocksPatch);
            PatchUtils.patch((ByteBuffer)localObject1, (PTPatch)localObject2);
          }
        }
        localObject1 = new FileOutputStream(localFile2);
        ((OutputStream)localObject1).write(arrayOfByte);
        ((OutputStream)localObject1).close();
        hasPrePatched = true;
        Utils.getPrefs(1).edit().putBoolean("force_prepatch", false).putInt("prepatch_version", this.mcPkgInfo.versionCode).apply();
        if (failedPatches.size() > 0) {
          showDialog(4098);
        }
        MC_NATIVE_LIBRARY_DIR = localFile1.getCanonicalPath();
        MC_NATIVE_LIBRARY_LOCATION = localFile2.getCanonicalPath();
        return;
      }
      Object localObject3 = new File((String)localObject3);
      if (((File)localObject3).exists())
      {
        try
        {
          PTPatch localPTPatch = new PTPatch();
          localPTPatch.loadPatch((File)localObject3);
          if (localPTPatch.checkMagic()) {
            break label505;
          }
          failedPatches.add(((File)localObject3).getName());
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
          failedPatches.add(((File)localObject3).getName());
        }
        continue;
        label505:
        PatchUtils.patch((ByteBuffer)localObject1, localException);
        i += 1;
      }
    }
  }
  
  private boolean requiresPatchingInSafeMode()
  {
    return false;
  }
  
  public static void saveScreenshot(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
  
  private void setCommandHistoryIndex(int paramInt)
  {
    boolean bool2 = true;
    this.commandHistoryIndex = paramInt;
    Button localButton = this.prevButton;
    if (paramInt != 0)
    {
      bool1 = true;
      localButton.setEnabled(bool1);
      localButton = this.nextButton;
      if (paramInt == this.commandHistoryList.size() - 1) {
        break label60;
      }
    }
    label60:
    for (boolean bool1 = bool2;; bool1 = false)
    {
      localButton.setEnabled(bool1);
      return;
      bool1 = false;
      break;
    }
  }
  
  private void setFakePackage(boolean paramBoolean)
  {
    this.fakePackage = paramBoolean;
  }
  
  @TargetApi(19)
  private void setImmersiveMode(boolean paramBoolean)
  {
    if (Build.VERSION.SDK_INT < 19) {
      return;
    }
    int i = getWindow().getDecorView().getSystemUiVisibility();
    if (paramBoolean) {
      i |= 0x1002;
    }
    for (;;)
    {
      getWindow().getDecorView().setSystemUiVisibility(i);
      return;
      i &= 0xEFFD;
    }
  }
  
  private static String stringFromInputStream(InputStream paramInputStream, int paramInt)
    throws IOException
  {
    ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream(paramInt);
    try
    {
      byte[] arrayOfByte = new byte['?'];
      for (;;)
      {
        paramInt = paramInputStream.read(arrayOfByte);
        if (paramInt == -1) {
          break;
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, paramInt);
      }
      paramInputStream = localByteArrayOutputStream.toString("UTF-8");
    }
    finally
    {
      localByteArrayOutputStream.close();
    }
    localByteArrayOutputStream.close();
    return paramInputStream;
  }
  
  private void touchImmersiveMode()
  {
    final boolean bool = Utils.getPrefs(0).getBoolean("zz_immersive_mode", false);
    if (!bool) {
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.setImmersiveMode(bool);
      }
    });
  }
  
  private void turnOffSafeMode()
  {
    Utils.getPrefs(0).edit().putBoolean("zz_safe_mode", false).commit();
    Utils.getPrefs(1).edit().putBoolean("force_prepatch", true).commit();
    finish();
    NerdyStuffActivity.forceRestart(this);
  }
  
  private boolean useLegacyKeyboardInput()
  {
    return Utils.getPrefs(0).getBoolean("zz_legacy_keyboard_input", false);
  }
  
  public int abortWebRequest(int paramInt)
  {
    Log.i("BlockLauncher/Main", "Abort web request: " + paramInt);
    HurlRunner localHurlRunner = (HurlRunner)this.requestMap.get(paramInt);
    if (localHurlRunner != null) {
      HurlRunner.access$402(localHurlRunner, false);
    }
    return 0;
  }
  
  protected boolean allowScriptOverrideTextures()
  {
    return true;
  }
  
  protected void applyBuiltinPatches() {}
  
  public void buyGame() {}
  
  public int checkLicense()
  {
    return 0;
  }
  
  public void clearLoginInformation()
  {
    Log.i("BlockLauncher/Main", "Clear login info");
    Utils.getPrefs(0).edit().putString("accessToken", "").putString("clientId", "").putString("profileUuid", "").putString("profileName", "").apply();
  }
  
  public Intent createAndroidLaunchIntent()
  {
    System.out.println("create android launch intent");
    return getIntent();
  }
  
  protected Dialog createBackupsNotSupportedDialog()
  {
    new AlertDialog.Builder(this).setMessage("Backed up versions of BlockLauncher are not supported, as BlockLauncher depends on updates from the application store.  Please reinstall BlockLauncher. If you believe you received this message in error, contact zhuowei_applications@yahoo.com").setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.finish();
      }
    }).setCancelable(false).create();
  }
  
  protected Dialog createCopyWorldDialog()
  {
    View localView = getLayoutInflater().inflate(R.layout.copy_world_dialog, null);
    new AlertDialog.Builder(this).setTitle(R.string.copy_world_title).setView(localView).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = ((TextView)((AlertDialog)paramAnonymousDialogInterface).findViewById(R.id.world_name_entry)).getText().toString();
        MainActivity.this.userInputStrings = new String[] { paramAnonymousDialogInterface };
        MainActivity.this.inputStatus = 1;
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).create();
  }
  
  protected Dialog createCreateWorldDialog()
  {
    View localView = getLayoutInflater().inflate(R.layout.create_world_dialog, null);
    new AlertDialog.Builder(this).setTitle(R.string.world_create_title).setView(localView).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        Object localObject = (AlertDialog)paramAnonymousDialogInterface;
        paramAnonymousDialogInterface = ((TextView)((AlertDialog)localObject).findViewById(R.id.world_name_entry)).getText().toString();
        String str = ((TextView)((AlertDialog)localObject).findViewById(R.id.world_seed_entry)).getText().toString();
        localObject = MainActivity.GAME_MODES[((android.widget.Spinner)localObject.findViewById(R.id.world_gamemode_spinner)).getSelectedItemPosition()];
        MainActivity.this.userInputStrings = new String[] { paramAnonymousDialogInterface, str, localObject };
        MainActivity.this.inputStatus = 1;
      }
    }).setNegativeButton(17039360, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).setOnCancelListener(new DialogInterface.OnCancelListener()
    {
      public void onCancel(DialogInterface paramAnonymousDialogInterface)
      {
        MainActivity.this.inputStatus = 0;
      }
    }).create();
  }
  
  protected Dialog createFirstLaunchDialog()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    localStringBuilder.append(getResources().getString(R.string.firstlaunch_generic_intro)).append("\n\n");
    if (this.minecraftApkForwardLocked) {
      localStringBuilder.append(getResources().getString(R.string.firstlaunch_jelly_bean)).append("\n\n");
    }
    localStringBuilder.append(getResources().getString(R.string.firstlaunch_see_options)).append("\n\n");
    new AlertDialog.Builder(this).setTitle(R.string.firstlaunch_title).setMessage(localStringBuilder.toString()).setPositiveButton(17039370, null).setNeutralButton(R.string.firstlaunch_help, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        paramAnonymousDialogInterface = new Intent("android.intent.action.VIEW");
        paramAnonymousDialogInterface.setData(Uri.parse("http://www.minecraftforum.net/topic/1675581-blocklauncher-an-android-app-that-patches-minecraft-pe-without-reinstall/"));
        MainActivity.this.startActivity(paramAnonymousDialogInterface);
      }
    }).create();
  }
  
  protected Dialog createInsertTextDialog()
  {
    final EditText localEditText = new EditText(this);
    localEditText.setSingleLine(false);
    LinearLayout localLinearLayout = new LinearLayout(this);
    localLinearLayout.setOrientation(0);
    localLinearLayout.addView(localEditText, -2, -2);
    Button localButton = new Button(this);
    localButton.setText(R.string.hovercar_insert_text_backspace);
    localButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        try
        {
          MainActivity.this.nativeTypeCharacter("\b");
          return;
        }
        catch (Exception paramAnonymousView)
        {
          MainActivity.this.showDialog(4101);
        }
      }
    });
    localLinearLayout.addView(localButton, -2, -2);
    new AlertDialog.Builder(this).setTitle(R.string.hovercar_insert_text).setView(localLinearLayout).setPositiveButton(17039370, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        try
        {
          paramAnonymousDialogInterface = localEditText.getText().toString().split("\n");
          paramAnonymousInt = 0;
          while (paramAnonymousInt < paramAnonymousDialogInterface.length)
          {
            if (paramAnonymousInt != 0) {
              MainActivity.this.nativeTypeCharacter("\n");
            }
            MainActivity.this.nativeTypeCharacter(paramAnonymousDialogInterface[paramAnonymousInt]);
            paramAnonymousInt += 1;
          }
          localEditText.setText("");
          return;
        }
        catch (UnsatisfiedLinkError paramAnonymousDialogInterface)
        {
          MainActivity.this.showDialog(4101);
        }
      }
    }).setNegativeButton(17039360, null).create();
  }
  
  protected Dialog createInvalidPatchesDialog()
  {
    return new AlertDialog.Builder(this).setMessage(getResources().getString(R.string.manage_patches_invalid_patches) + "\n" + PatchManager.join((String[])failedPatches.toArray(PatchManager.blankArray), "\n")).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createMultiplayerDisableScriptsDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.script_disabled_in_multiplayer).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createNotSupportedDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.feature_not_supported).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createRuntimeOptionsDialog(boolean paramBoolean)
  {
    Object localObject1 = getResources().getString(R.string.pref_texture_pack);
    final String str3 = getResources().getString(R.string.hovercar_options);
    final String str4 = getResources().getString(R.string.hovercar_insert_text);
    final Object localObject2 = getResources().getString(R.string.pref_zz_manage_scripts);
    String str5 = getResources().getString(R.string.take_screenshot);
    final String str1 = getResources().getString(R.string.hovercar_start_recording);
    final String str2 = getResources().getString(R.string.hovercar_stop_recording);
    localObject2 = new ArrayList(Arrays.asList(new CharSequence[] { localObject1, localObject2, str5 }));
    if (this.hasRecorder)
    {
      this.isRecording = isKamcordRecording();
      if (this.isRecording)
      {
        localObject1 = str2;
        ((List)localObject2).add(localObject1);
      }
    }
    else
    {
      if (paramBoolean) {
        ((List)localObject2).add(str4);
      }
      ((List)localObject2).add(str3);
      localObject1 = new AlertDialog.Builder(this);
      if (!Utils.isSafeMode()) {
        break label263;
      }
    }
    label263:
    for (int i = R.string.pref_zz_safe_mode;; i = R.string.app_name)
    {
      localObject1 = ((AlertDialog.Builder)localObject1).setTitle(i).setItems((CharSequence[])((List)localObject2).toArray(new CharSequence[0]), new DialogInterface.OnClickListener()
      {
        public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
        {
          int i = 1;
          paramAnonymousDialogInterface = (CharSequence)localObject2.get(paramAnonymousInt);
          if (paramAnonymousInt == 0)
          {
            paramAnonymousDialogInterface = new Intent(MainActivity.this, TexturePacksActivity.class);
            MainActivity.this.startActivityForResult(paramAnonymousDialogInterface, 416);
          }
          do
          {
            return;
            if (paramAnonymousInt == 1)
            {
              if (MainActivity.this.hasScriptSupport())
              {
                paramAnonymousDialogInterface = new Intent(MainActivity.this, ManageScriptsActivity.class);
                MainActivity.this.startActivityForResult(paramAnonymousDialogInterface, 417);
                return;
              }
              new AlertDialog.Builder(MainActivity.this).setMessage("Scripts are not supported yet in Minecraft PE " + MainActivity.this.mcPkgInfo.versionName).setPositiveButton(17039370, null).show();
              return;
            }
            if (paramAnonymousInt == 2)
            {
              if ((Utils.getPrefs(0).getBoolean("zz_script_enable", true)) && (!Utils.isSafeMode()) && (MainActivity.this.hasScriptSupport())) {}
              for (paramAnonymousInt = i; paramAnonymousInt != 0; paramAnonymousInt = 0)
              {
                ScriptManager.takeScreenshot("screenshot");
                return;
              }
              new AlertDialog.Builder(MainActivity.this).setMessage(R.string.take_screenshot_requires_modpe_script).setPositiveButton(17039370, null).show();
              return;
            }
            if (paramAnonymousDialogInterface.equals(str3))
            {
              MainActivity.this.startActivity(MainActivity.this.getOptionsActivityIntent());
              return;
            }
            if (paramAnonymousDialogInterface.equals(str4))
            {
              MainActivity.this.showDialog(4103);
              return;
            }
          } while ((!paramAnonymousDialogInterface.equals(str1)) && (!paramAnonymousDialogInterface.equals(str2)));
          MainActivity.this.mainHandler.sendEmptyMessageDelayed(327, 1000L);
        }
      });
      if (Build.VERSION.SDK_INT >= 19) {
        ((AlertDialog.Builder)localObject1).setOnDismissListener(new DialogInterface.OnDismissListener()
        {
          public void onDismiss(DialogInterface paramAnonymousDialogInterface)
          {
            MainActivity.this.touchImmersiveMode();
          }
        });
      }
      return ((AlertDialog.Builder)localObject1).create();
      localObject1 = str1;
      break;
    }
  }
  
  protected Dialog createSELinuxBrokeEverythingDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.selinux_broke_everything).setPositiveButton(17039370, null).create();
  }
  
  protected Dialog createSafeModeDialog(int paramInt)
  {
    new AlertDialog.Builder(this).setMessage(paramInt).setPositiveButton(R.string.safe_mode_exit, new DialogInterface.OnClickListener()
    {
      public void onClick(DialogInterface paramAnonymousDialogInterface, int paramAnonymousInt)
      {
        MainActivity.this.turnOffSafeMode();
      }
    }).setNegativeButton(R.string.safe_mode_continue, null).create();
  }
  
  public String createUUID()
  {
    System.out.println("Create UUID");
    return UUID.randomUUID().toString().replace("-", "");
  }
  
  protected Dialog createUpdateTexturePackDialog()
  {
    return new AlertDialog.Builder(this).setMessage(R.string.extract_textures_need_update).setPositiveButton(17039370, null).create();
  }
  
  public void dismissHiddenTextbox()
  {
    if (this.hiddenTextWindow == null) {
      return;
    }
    this.hiddenTextWindow.dismiss();
    hideKeyboardView();
  }
  
  public boolean dispatchKeyEvent(KeyEvent paramKeyEvent)
  {
    if ((paramKeyEvent.getAction() == 2) && (paramKeyEvent.getKeyCode() == 0)) {
      try
      {
        nativeTypeCharacter(paramKeyEvent.getCharacters());
        return true;
      }
      catch (UnsatisfiedLinkError localUnsatisfiedLinkError) {}
    }
    return super.dispatchKeyEvent(paramKeyEvent);
  }
  
  public void displayDialog(int paramInt)
  {
    System.out.println("displayDialog: " + paramInt);
    this.inputStatus = 0;
    switch (paramInt)
    {
    case 2: 
    default: 
      return;
    case 1: 
      System.out.println("World creation");
      this.inputStatus = -1;
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          MainActivity.this.showDialog(1);
        }
      });
      return;
    case 3: 
      System.out.println("Settings");
      this.inputStatus = -1;
      startActivityForResult(getOptionsActivityIntent(), 1234);
      return;
    }
    System.out.println("Copy world");
    this.inputStatus = -1;
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.showDialog(4);
      }
    });
  }
  
  public boolean doesRequireGuiBlocksPatch()
  {
    return false;
  }
  
  public void fakeTipMessageCallback(String paramString)
  {
    if (this.minecraftTypeface == null) {
      this.minecraftTypeface = Typeface.createFromAsset(getAssets(), "fonts/minecraft.ttf");
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        TextView localTextView = new TextView(MainActivity.this);
        localTextView.setText(this.val$message);
        localTextView.setTypeface(MainActivity.this.minecraftTypeface);
        localTextView.setTextColor(-1);
        localTextView.setShadowLayer(0.1F, 8.0F, 8.0F, -16777216);
        if (MainActivity.this.lastToast != null) {
          MainActivity.this.lastToast.cancel();
        }
        Toast localToast = new Toast(MainActivity.this);
        localToast.setView(localTextView);
        MainActivity.access$1702(MainActivity.this, localToast);
        localToast.show();
      }
    });
  }
  
  protected String filterUrl(String paramString)
  {
    return paramString;
  }
  
  public void forceTextureReload() {}
  
  public String getAccessToken()
  {
    Log.i("BlockLauncher/Main", "Get access token");
    return Utils.getPrefs(0).getString("accessToken", "");
  }
  
  public int getAndroidVersion()
  {
    return Build.VERSION.SDK_INT;
  }
  
  public String[] getBroadcastAddresses()
  {
    Log.i("BlockLauncher/Main", "get broadcast addresses");
    return new String[] { "255.255.255.255" };
  }
  
  public String getClientId()
  {
    Log.i("BlockLauncher/Main", "Get client ID");
    return Utils.getPrefs(0).getString("clientId", "");
  }
  
  public String getDateString(int paramInt)
  {
    System.out.println("getDateString: " + paramInt);
    return DateFormat.getDateInstance(3, Locale.US).format(new Date(paramInt * 1000L));
  }
  
  public String getDeviceId()
  {
    String str2 = Utils.getPrefs(0).getString("snooperId", null);
    String str1 = str2;
    if (str2 == null)
    {
      str1 = createUUID();
      Utils.getPrefs(0).edit().putString("snooperId", str1).apply();
    }
    System.out.println("Get device ID");
    return str1;
  }
  
  public String getDeviceModel()
  {
    return HardwareInformation.getDeviceModelName();
  }
  
  public String getExternalStoragePath()
  {
    return Environment.getExternalStorageDirectory().getAbsolutePath();
  }
  
  public int[] getFakeImageData(String paramString, boolean paramBoolean)
  {
    return new int[] { 1, 1, 0 };
  }
  
  public byte[] getFileDataBytes(String paramString)
  {
    byte[] arrayOfByte2 = getFileDataBytes(paramString, false);
    byte[] arrayOfByte1 = arrayOfByte2;
    if (paramString.endsWith(".meta"))
    {
      String str = new String(arrayOfByte2, Charset.forName("UTF-8"));
      arrayOfByte1 = arrayOfByte2;
      if (!str.contains("portal"))
      {
        arrayOfByte1 = arrayOfByte2;
        if (!str.contains("rabbit_foot")) {
          arrayOfByte1 = getFileDataBytes(paramString, true);
        }
      }
    }
    return arrayOfByte1;
  }
  
  public byte[] getFileDataBytes(String paramString, boolean paramBoolean)
  {
    System.out.println("Get file data: " + paramString);
    label146:
    label148:
    for (;;)
    {
      try
      {
        if (paramString.charAt(0) == '/')
        {
          paramString = getRegularInputStream(paramString);
          if (paramString == null) {
            break label146;
          }
          if ("BlockLauncher/Main".hashCode() != -1771687045) {
            return null;
          }
        }
        else
        {
          if (paramString.endsWith("start_screen.json"))
          {
            paramString = openFallbackAsset(paramString);
            continue;
          }
          if (paramBoolean)
          {
            paramString = getLocalInputStreamForAsset(paramString);
            break label148;
          }
          paramString = getInputStreamForAsset(paramString);
          break label148;
        }
        ByteArrayOutputStream localByteArrayOutputStream = new ByteArrayOutputStream();
        byte[] arrayOfByte = new byte['?'];
        int i = paramString.read(arrayOfByte);
        if (i < 0) {
          return localByteArrayOutputStream.toByteArray();
        }
        localByteArrayOutputStream.write(arrayOfByte, 0, i);
        continue;
        return null;
      }
      catch (Exception paramString) {}
    }
  }
  
  public String[] getIPAddresses()
  {
    System.out.println("get IP addresses?");
    return new String[] { "127.0.0.1" };
  }
  
  public int[] getImageData(String paramString)
  {
    return getImageData(paramString, true);
  }
  
  public int[] getImageData(String paramString, boolean paramBoolean)
  {
    int i = 1;
    System.out.println("Get image data: " + paramString + " from assets? " + paramBoolean);
    if ((paramString.length() > 0) && (paramString.charAt(0) == '/')) {}
    for (;;)
    {
      if (i != 0) {}
      try
      {
        for (InputStream localInputStream = getRegularInputStream(paramString); localInputStream == null; localInputStream = getInputStreamForAsset(paramString)) {
          return getFakeImageData(paramString, paramBoolean);
        }
        paramString = BitmapFactory.decodeStream(localInputStream);
        int[] arrayOfInt = new int[paramString.getWidth() * paramString.getHeight() + 2];
        arrayOfInt[0] = paramString.getWidth();
        arrayOfInt[1] = paramString.getHeight();
        paramString.getPixels(arrayOfInt, 2, paramString.getWidth(), 0, 0, paramString.getWidth(), paramString.getHeight());
        localInputStream.close();
        paramString.recycle();
        return arrayOfInt;
      }
      catch (Exception paramString)
      {
        paramString.printStackTrace();
        return null;
      }
      i = 0;
    }
  }
  
  public InputStream getInputStreamForAsset(String paramString)
  {
    return getInputStreamForAsset(paramString, null);
  }
  
  public InputStream getInputStreamForAsset(String paramString, long[] paramArrayOfLong)
  {
    int i = 0;
    try
    {
      for (;;)
      {
        int j = this.textureOverrides.size();
        if (i >= j) {
          break;
        }
        try
        {
          InputStream localInputStream1 = ((TexturePack)this.textureOverrides.get(i)).getInputStream(paramString);
          if (localInputStream1 != null)
          {
            if (paramArrayOfLong != null) {
              paramArrayOfLong[0] = ((TexturePack)this.textureOverrides.get(i)).getSize(paramString);
            }
            return localInputStream1;
          }
        }
        catch (IOException localIOException)
        {
          i += 1;
        }
      }
      if (this.texturePack == null) {
        return getLocalInputStreamForAsset(paramString, paramArrayOfLong);
      }
      System.out.println("Trying to load  " + paramString + "from tp");
      InputStream localInputStream2 = this.texturePack.getInputStream(paramString);
      if (localInputStream2 == null)
      {
        System.out.println("Can't load " + paramString + " from tp");
        paramString = getLocalInputStreamForAsset(paramString, paramArrayOfLong);
        return paramString;
      }
      return localInputStream2;
    }
    catch (Exception paramString)
    {
      System.err.println(paramString);
    }
    return null;
  }
  
  public int getKeyFromKeyCode(int paramInt1, int paramInt2, int paramInt3)
  {
    return KeyCharacterMap.load(paramInt3).get(paramInt1, paramInt2);
  }
  
  public float getKeyboardHeight()
  {
    Rect localRect = new Rect();
    getWindow().getDecorView().getWindowVisibleDisplayFrame(localRect);
    if (localRect.bottom == 0) {
      return 0.0F;
    }
    return this.displayMetrics.heightPixels - localRect.bottom;
  }
  
  public Intent getLaunchIntent()
  {
    System.out.println("get launch intent");
    return getIntent();
  }
  
  protected InputStream getLocalInputStreamForAsset(String paramString)
  {
    return getLocalInputStreamForAsset(paramString, null);
  }
  
  protected InputStream getLocalInputStreamForAsset(String paramString, long[] paramArrayOfLong)
  {
    try
    {
      if (this.forceFallback)
      {
        paramString = openFallbackAsset(paramString);
        return paramString;
      }
      InputStream localInputStream3;
      try
      {
        InputStream localInputStream1 = this.minecraftApkContext.getAssets().open(paramString);
        localInputStream3 = localInputStream1;
        if (localInputStream1 == null)
        {
          System.out.println("Can't find it in the APK - attempting to load fallback");
          localInputStream3 = openFallbackAsset(paramString);
        }
        if ((localInputStream3 != null) && (paramArrayOfLong != null)) {
          paramArrayOfLong[0] = localInputStream3.available();
        }
      }
      catch (Exception localException)
      {
        for (;;)
        {
          System.out.println("Attempting to load fallback");
          InputStream localInputStream2 = openFallbackAsset(paramString);
        }
      }
      return localInputStream3;
    }
    catch (Exception paramString)
    {
      System.err.println(paramString);
      return null;
    }
  }
  
  public String getLocale()
  {
    Locale localLocale = getResources().getConfiguration().locale;
    return localLocale.getLanguage() + "_" + localLocale.getCountry();
  }
  
  protected String getMCPEVersion()
  {
    return this.mcPkgInfo.versionName;
  }
  
  public int getMaxNumPatches()
  {
    return getResources().getInteger(R.integer.max_num_patches);
  }
  
  public String[] getOptionStrings()
  {
    System.err.println("OptionStrings");
    SharedPreferences localSharedPreferences = Utils.getPrefs(0);
    Object localObject = localSharedPreferences.getAll().entrySet();
    ArrayList localArrayList = new ArrayList();
    localObject = ((Set)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      Map.Entry localEntry = (Map.Entry)((Iterator)localObject).next();
      String str = (String)localEntry.getKey();
      if (str.indexOf("zz_") != 0)
      {
        localArrayList.add(str);
        if (str.equals("ctrl_sensitivity")) {
          localArrayList.add(Double.toString(Integer.parseInt(localEntry.getValue().toString()) / 100.0D));
        } else {
          localArrayList.add(localEntry.getValue().toString());
        }
      }
    }
    localArrayList.add("game_difficulty");
    if (localSharedPreferences.getBoolean("game_difficultypeaceful", false)) {
      localArrayList.add("0");
    }
    for (;;)
    {
      System.out.println(localArrayList.toString());
      return (String[])localArrayList.toArray(new String[0]);
      localArrayList.add("2");
    }
  }
  
  protected Intent getOptionsActivityIntent()
  {
    return new Intent(this, MainMenuOptionsActivity.class);
  }
  
  public PackageManager getPackageManager()
  {
    if (this.fakePackage) {
      return new RedirectPackageManager(super.getPackageManager(), MC_NATIVE_LIBRARY_DIR);
    }
    return super.getPackageManager();
  }
  
  public float getPixelsPerMillimeter()
  {
    System.out.println("Pixels per mm");
    float f2 = this.displayMetrics.densityDpi / 25.4F;
    String str = Utils.getPrefs(0).getString("zz_custom_dpi", null);
    float f1 = f2;
    if (str != null)
    {
      f1 = f2;
      if (str.length() <= 0) {}
    }
    try
    {
      f1 = Float.parseFloat(str);
      f1 /= 25.4F;
      return f1;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return f2;
  }
  
  public String getPlatformStringVar(int paramInt)
  {
    System.out.println("getPlatformStringVar: " + paramInt);
    return "";
  }
  
  public String getProfileId()
  {
    Log.i("BlockLauncher/Main", "Get profile ID");
    return Utils.getPrefs(0).getString("profileUuid", "");
  }
  
  public String getProfileName()
  {
    Log.i("BlockLauncher/Main", "Get profile name");
    return Utils.getPrefs(0).getString("profileName", "");
  }
  
  public RealmsRedirectInfo getRealmsRedirectInfo()
  {
    return (RealmsRedirectInfo)RealmsRedirectInfo.targets.get("NONE");
  }
  
  public String getRefreshToken()
  {
    Log.i("BlockLauncher/Main", "Get Refresh token");
    return Utils.getPrefs(0).getString("refreshToken", "");
  }
  
  public int getScreenHeight()
  {
    System.out.println("height");
    return this.displayMetrics.heightPixels;
  }
  
  public int getScreenWidth()
  {
    System.out.println("width");
    return this.displayMetrics.widthPixels;
  }
  
  public String getSession()
  {
    Log.i("BlockLauncher/Main", "Get Session");
    return Utils.getPrefs(0).getString("sessionId", "");
  }
  
  public long getTotalMemory()
  {
    try
    {
      long l = Utils.parseMemInfo();
      Log.i("BlockLauncher/Main", "Get total memory: " + l);
      return l;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
    return 17179869184L;
  }
  
  public int getUserInputStatus()
  {
    System.out.println("User input status: " + this.inputStatus);
    return this.inputStatus;
  }
  
  public String[] getUserInputString()
  {
    System.out.println("User input string");
    return this.userInputStrings;
  }
  
  public String getWebRequestContent(int paramInt)
  {
    Log.i("BlockLauncher/Main", "Get web request content: " + paramInt);
    return "ThisIsSparta";
  }
  
  public int getWebRequestStatus(int paramInt)
  {
    Log.i("BlockLauncher/Main", "Get web request status: " + paramInt);
    return 0;
  }
  
  public boolean hasBuyButtonWhenInvalidLicense()
  {
    return false;
  }
  
  public boolean hasHardwareChanged()
  {
    return false;
  }
  
  protected boolean hasScriptSupport()
  {
    return this.mcPkgInfo.versionName.startsWith("0.15");
  }
  
  public void hideKeyboard()
  {
    if (useLegacyKeyboardInput())
    {
      hideKeyboardView();
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.dismissHiddenTextbox();
      }
    });
  }
  
  public void hideKeyboardView()
  {
    ((InputMethodManager)getSystemService("input_method")).hideSoftInputFromWindow(getWindow().getDecorView().getWindowToken(), 0);
    touchImmersiveMode();
  }
  
  protected void initKamcord() {}
  
  public void initPatching()
    throws Exception
  {
    System.loadLibrary("mcpelauncher_tinysubstrate");
    System.out.println("MCPE Version is " + getMCPEVersion());
    if (getMCPEVersion().startsWith("~~~~")) {
      System.loadLibrary("mcpelauncher_lite");
    }
    for (;;)
    {
      if (!MaraudersMap.initPatching(this, findMinecraftLibLength()))
      {
        System.out.println("Well, that sucks!");
        tempSafeMode = true;
        this.overlyZealousSELinuxSafeMode = true;
      }
      return;
      System.loadLibrary("mcpelauncher");
    }
  }
  
  public void initiateUserInput(int paramInt)
  {
    System.out.println("initiateUserInput: " + paramInt);
  }
  
  public boolean isDemo()
  {
    Log.i("BlockLauncher/Main", "Is demo");
    return false;
  }
  
  public boolean isFirstSnooperStart()
  {
    boolean bool = false;
    System.out.println("Is first snooper start");
    if (Utils.getPrefs(0).getString("snooperId", null) == null) {
      bool = true;
    }
    return bool;
  }
  
  protected boolean isKamcordRecording()
  {
    return false;
  }
  
  public boolean isNetworkEnabled(boolean paramBoolean)
  {
    return true;
  }
  
  public boolean isRedirectingRealms()
  {
    return false;
  }
  
  public boolean isTablet()
  {
    if (Build.VERSION.SDK_INT < 13) {}
    while (getResources().getConfiguration().smallestScreenWidthDp < 600) {
      return false;
    }
    return true;
  }
  
  public boolean isTouchscreen()
  {
    return Utils.getPrefs(0).getBoolean("ctrl_usetouchscreen", true);
  }
  
  public void launchUri(String paramString)
  {
    paramString = new Intent("android.intent.action.VIEW", Uri.parse(paramString));
    try
    {
      startActivity(paramString);
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  public void leaveGameCallback()
  {
    System.out.println("Leave game");
    if (this.hasRecorder) {
      clearRuntimeOptionsDialog();
    }
  }
  
  protected void loadNativeAddons()
  {
    if (!Utils.getPrefs(0).getBoolean("zz_load_native_addons", true)) {
      return;
    }
    PackageManager localPackageManager = getPackageManager();
    AddonManager localAddonManager = AddonManager.getAddonManager(this);
    Object localObject = localPackageManager.getInstalledApplications(128);
    StringBuilder localStringBuilder = new StringBuilder();
    localObject = ((List)localObject).iterator();
    while (((Iterator)localObject).hasNext())
    {
      ApplicationInfo localApplicationInfo = (ApplicationInfo)((Iterator)localObject).next();
      if (localApplicationInfo.metaData != null)
      {
        String str1 = localApplicationInfo.metaData.getString("net.zhuoweizhang.mcpelauncher.api.nativelibname");
        String str2 = localApplicationInfo.metaData.getString("net.zhuoweizhang.mcpelauncher.api.targetmcpeversion");
        if ((localPackageManager.checkPermission("net.zhuoweizhang.mcpelauncher.ADDON", localApplicationInfo.packageName) == 0) && (localAddonManager.isEnabled(localApplicationInfo.packageName)))
        {
          try
          {
            if (isAddonCompat(str2)) {
              break label241;
            }
            throw new Exception("The addon \"" + localPackageManager.getApplicationLabel(localApplicationInfo).toString() + "\" (" + localApplicationInfo.packageName + ")" + " is not compatible with Minecraft PE " + this.mcPkgInfo.versionName + ".");
          }
          catch (Throwable localThrowable)
          {
            reportError(localThrowable);
            localThrowable.printStackTrace();
          }
          continue;
          label241:
          if (str1 != null)
          {
            if (checkAddonArch(new File(localThrowable.nativeLibraryDir + "/lib" + str1 + ".so")))
            {
              System.load(localThrowable.nativeLibraryDir + "/lib" + str1 + ".so");
              loadedAddons.add(localThrowable.packageName);
            }
            else
            {
              localStringBuilder.append("\"").append(localPackageManager.getApplicationLabel(localThrowable).toString()).append("\" (").append(localThrowable.packageName).append(") ");
            }
          }
          else {
            loadedAddons.add(localThrowable.packageName);
          }
        }
      }
    }
    if (localStringBuilder.length() != 0) {
      reportError(new Exception(getResources().getString(R.string.addons_wrong_arch).toString().replaceAll("ARCH", Utils.getArchName(this.mcpeArch)).replaceAll("ADDONS", localStringBuilder.toString())));
    }
    this.addonOverrideTexturePackInstance = new AddonOverrideTexturePack(this, "resourcepacks/vanilla/");
    this.textureOverrides.add(this.addonOverrideTexturePackInstance);
  }
  
  protected void loadTexturePack()
  {
    try
    {
      boolean bool = Utils.getPrefs(0).getBoolean("zz_texture_pack_enable", false);
      this.texturePack = null;
      if (bool)
      {
        ArrayList localArrayList = new ArrayList();
        List localList = TexturePackLoader.loadTexturePacks(this, localArrayList, getFileDataBytes("images/terrain.meta", true), getFileDataBytes("images/items.meta", true));
        if (localArrayList.size() != 0) {
          new AlertDialog.Builder(this).setMessage("Some of your texture packs are not compatible with Minecraft PE " + getMCPEVersion() + ". Please update " + Utils.join(localArrayList, ", ") + ".").setPositiveButton(17039370, null).show();
        }
        this.textureOverrides.addAll(localList);
      }
      return;
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reportError(localException, R.string.texture_pack_unable_to_load, null);
    }
  }
  
  protected void loadTexturePackOld()
  {
    String str = null;
    Object localObject = str;
    try
    {
      boolean bool = Utils.getPrefs(0).getBoolean("zz_texture_pack_enable", false);
      localObject = str;
      str = Utils.getPrefs(1).getString("texturePack", null);
      if ((bool) && (str != null))
      {
        localObject = str;
        File localFile = new File(str);
        localObject = str;
        if (!localFile.exists())
        {
          localObject = str;
          this.texturePack = null;
          return;
        }
        localObject = str;
        this.texturePack = new ZipTexturePack(localFile);
        return;
      }
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
      reportError(localException, R.string.texture_pack_unable_to_load, (String)localObject + ": size is " + new File((String)localObject).length());
      return;
    }
    localObject = localException;
    this.texturePack = null;
  }
  
  protected void loginLaunchCallback(Uri paramUri)
  {
    this.loginDialog.dismiss();
    if (paramUri.getQueryParameter("sessionId") == null) {
      return;
    }
    String str = paramUri.getQueryParameter("profileName");
    paramUri.getQueryParameter("identity");
    nativeLoginData(paramUri.getQueryParameter("accessToken"), paramUri.getQueryParameter("clientToken"), paramUri.getQueryParameter("profileUuid"), str);
  }
  
  protected void migrateToPatchManager()
  {
    int i = 1;
    for (;;)
    {
      try
      {
        if (Utils.getPrefs(1).getInt("patchManagerVersion", -1) <= 0) {
          break label81;
        }
      }
      catch (Exception localException)
      {
        File localFile;
        localException.printStackTrace();
        return;
      }
      showDialog(4099);
      localFile = getDir("ptpatches", 0);
      PatchManager.getPatchManager(this).setEnabled(localFile.listFiles(), true);
      System.out.println(Utils.getPrefs(1).getString("enabledPatches", "LOL"));
      return;
      while (i != 0)
      {
        return;
        label81:
        i = 0;
      }
    }
  }
  
  public native void nativeBackPressed();
  
  public native void nativeBackSpacePressed();
  
  public native void nativeLoginData(String paramString1, String paramString2, String paramString3, String paramString4);
  
  public native void nativeProcessIntentUriQuery(String paramString1, String paramString2);
  
  public native void nativeRegisterThis();
  
  public native void nativeReturnKeyPressed();
  
  public native void nativeSetTextboxText(String paramString);
  
  public native void nativeStopThis();
  
  public native void nativeSuspend();
  
  public native void nativeTypeCharacter(String paramString);
  
  public native void nativeUnregisterThis();
  
  public native void nativeWebRequestCompleted(int paramInt1, long paramLong, int paramInt2, String paramString);
  
  protected void onActivityResult(int paramInt1, int paramInt2, Intent paramIntent)
  {
    if (paramInt1 == 1234)
    {
      this.inputStatus = 1;
      System.out.println("Settings OK");
      if (!Utils.isSafeMode()) {
        applyBuiltinPatches();
      }
    }
    do
    {
      return;
      if (paramInt1 == 415)
      {
        if (paramInt2 == -1)
        {
          this.pickImageResult = paramIntent;
          paramIntent = copyContentStoreToTempFile(paramIntent.getData());
          nativeOnPickImageSuccess(this.pickImageCallbackAddress, paramIntent.getAbsolutePath());
          return;
        }
        nativeOnPickImageCanceled(this.pickImageCallbackAddress);
        return;
      }
    } while (((paramInt1 != 416) && (paramInt1 != 417)) || (paramInt2 != -1));
    finish();
    NerdyStuffActivity.forceRestart(this);
  }
  
  public void onBackPressed()
  {
    nativeBackPressed();
  }
  
  /* Error */
  public void onCreate(Bundle paramBundle)
  {
    // Byte code:
    //   0: new 2120	java/lang/ref/WeakReference
    //   3: dup
    //   4: aload_0
    //   5: invokespecial 2122	java/lang/ref/WeakReference:<init>	(Ljava/lang/Object;)V
    //   8: putstatic 254	com/mojang/minecraftpe/MainActivity:currentMainActivity	Ljava/lang/ref/WeakReference;
    //   11: iconst_2
    //   12: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   15: ldc_w 2124
    //   18: iconst_0
    //   19: invokeinterface 797 3 0
    //   24: istore_3
    //   25: getstatic 511	java/lang/System:out	Ljava/io/PrintStream;
    //   28: new 537	java/lang/StringBuilder
    //   31: dup
    //   32: invokespecial 538	java/lang/StringBuilder:<init>	()V
    //   35: ldc_w 2126
    //   38: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   41: iload_3
    //   42: invokevirtual 1118	java/lang/StringBuilder:append	(I)Ljava/lang/StringBuilder;
    //   45: invokevirtual 557	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   48: invokevirtual 998	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   51: iload_3
    //   52: istore_2
    //   53: iload_3
    //   54: iconst_2
    //   55: if_icmpne +28 -> 83
    //   58: iconst_0
    //   59: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   62: invokeinterface 807 1 0
    //   67: ldc_w 1099
    //   70: iconst_1
    //   71: invokeinterface 815 3 0
    //   76: invokeinterface 818 1 0
    //   81: iconst_0
    //   82: istore_2
    //   83: iconst_2
    //   84: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   87: invokeinterface 807 1 0
    //   92: ldc_w 2124
    //   95: iload_2
    //   96: iconst_1
    //   97: iadd
    //   98: invokeinterface 822 3 0
    //   103: invokeinterface 1102 1 0
    //   108: pop
    //   109: aload_0
    //   110: invokevirtual 2130	com/mojang/minecraftpe/MainActivity:getApplicationContext	()Landroid/content/Context;
    //   113: putstatic 2133	net/zhuoweizhang/mcpelauncher/MinecraftVersion:context	Landroid/content/Context;
    //   116: iconst_0
    //   117: istore_3
    //   118: aload_0
    //   119: aload_0
    //   120: invokevirtual 634	com/mojang/minecraftpe/MainActivity:getPackageManager	()Landroid/content/pm/PackageManager;
    //   123: ldc_w 2135
    //   126: iconst_0
    //   127: invokevirtual 642	android/content/pm/PackageManager:getPackageInfo	(Ljava/lang/String;I)Landroid/content/pm/PackageInfo;
    //   130: putfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   133: aload_0
    //   134: aload_0
    //   135: getfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   138: getfield 669	android/content/pm/PackageInfo:applicationInfo	Landroid/content/pm/ApplicationInfo;
    //   141: putfield 976	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   144: aload_0
    //   145: getfield 976	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   148: getfield 547	android/content/pm/ApplicationInfo:nativeLibraryDir	Ljava/lang/String;
    //   151: putstatic 244	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_DIR	Ljava/lang/String;
    //   154: new 537	java/lang/StringBuilder
    //   157: dup
    //   158: invokespecial 538	java/lang/StringBuilder:<init>	()V
    //   161: getstatic 244	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_DIR	Ljava/lang/String;
    //   164: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   167: ldc_w 978
    //   170: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   173: invokevirtual 557	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   176: putstatic 248	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_LOCATION	Ljava/lang/String;
    //   179: getstatic 511	java/lang/System:out	Ljava/io/PrintStream;
    //   182: new 537	java/lang/StringBuilder
    //   185: dup
    //   186: invokespecial 538	java/lang/StringBuilder:<init>	()V
    //   189: ldc_w 2137
    //   192: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: getstatic 248	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_LOCATION	Ljava/lang/String;
    //   198: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 557	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: invokevirtual 998	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   207: aload_0
    //   208: invokespecial 2139	com/mojang/minecraftpe/MainActivity:checkArch	()V
    //   211: aload_0
    //   212: getfield 976	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   215: getfield 2142	android/content/pm/ApplicationInfo:sourceDir	Ljava/lang/String;
    //   218: aload_0
    //   219: getfield 976	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   222: getfield 2145	android/content/pm/ApplicationInfo:publicSourceDir	Ljava/lang/String;
    //   225: invokevirtual 630	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   228: ifne +864 -> 1092
    //   231: iconst_1
    //   232: istore 5
    //   234: aload_0
    //   235: iload 5
    //   237: putfield 286	com/mojang/minecraftpe/MainActivity:minecraftApkForwardLocked	Z
    //   240: aload_0
    //   241: getfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   244: getfield 803	android/content/pm/PackageInfo:versionCode	I
    //   247: istore 4
    //   249: aload_0
    //   250: iload 4
    //   252: invokestatic 2149	net/zhuoweizhang/mcpelauncher/MinecraftVersion:getRaw	(I)Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   255: putfield 1027	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   258: aload_0
    //   259: getfield 1027	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   262: ifnonnull +21 -> 283
    //   265: iconst_1
    //   266: putstatic 268	com/mojang/minecraftpe/MainActivity:tempSafeMode	Z
    //   269: aload_0
    //   270: sipush 4100
    //   273: invokevirtual 1046	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   276: aload_0
    //   277: invokestatic 2153	net/zhuoweizhang/mcpelauncher/MinecraftVersion:getDefault	()Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   280: putfield 1027	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   283: aload_0
    //   284: getfield 1027	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   287: getfield 2156	net/zhuoweizhang/mcpelauncher/MinecraftVersion:needsWarning	Z
    //   290: ifeq +12 -> 302
    //   293: ldc -103
    //   295: ldc_w 2158
    //   298: invokestatic 2161	android/util/Log:w	(Ljava/lang/String;Ljava/lang/String;)I
    //   301: pop
    //   302: aload_0
    //   303: getfield 1027	com/mojang/minecraftpe/MainActivity:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   306: putstatic 2162	net/zhuoweizhang/mcpelauncher/patch/PatchUtils:minecraftVersion	Lnet/zhuoweizhang/mcpelauncher/MinecraftVersion;
    //   309: aload_0
    //   310: getfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   313: getfield 894	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   316: ldc_w 915
    //   319: invokevirtual 899	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   322: ifne +938 -> 1260
    //   325: aload_0
    //   326: getfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   329: getfield 894	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   332: ldc_w 917
    //   335: invokevirtual 630	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   338: ifeq +760 -> 1098
    //   341: goto +919 -> 1260
    //   344: aload_0
    //   345: getfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   348: getfield 894	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   351: ldc -106
    //   353: invokevirtual 899	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   356: ifeq +7 -> 363
    //   359: iload_2
    //   360: ifeq +905 -> 1265
    //   363: aload_0
    //   364: getfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   367: getfield 894	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   370: ldc 123
    //   372: invokevirtual 899	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   375: ifeq +728 -> 1103
    //   378: goto +887 -> 1265
    //   381: iload_2
    //   382: ifne +66 -> 448
    //   385: new 559	android/content/Intent
    //   388: dup
    //   389: aload_0
    //   390: ldc_w 2164
    //   393: invokespecial 564	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   396: astore 7
    //   398: aload 7
    //   400: ldc_w 2165
    //   403: aload_0
    //   404: getfield 336	com/mojang/minecraftpe/MainActivity:mcPkgInfo	Landroid/content/pm/PackageInfo;
    //   407: getfield 894	android/content/pm/PackageInfo:versionName	Ljava/lang/String;
    //   410: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   413: pop
    //   414: aload 7
    //   416: ldc_w 2167
    //   419: ldc_w 919
    //   422: invokevirtual 595	android/content/Intent:putExtra	(Ljava/lang/String;Ljava/lang/String;)Landroid/content/Intent;
    //   425: pop
    //   426: aload_0
    //   427: aload 7
    //   429: invokevirtual 603	com/mojang/minecraftpe/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   432: aload_0
    //   433: invokevirtual 606	com/mojang/minecraftpe/MainActivity:finish	()V
    //   436: ldc2_w 607
    //   439: invokestatic 614	java/lang/Thread:sleep	(J)V
    //   442: invokestatic 652	android/os/Process:myPid	()I
    //   445: invokestatic 655	android/os/Process:killProcess	(I)V
    //   448: aload_0
    //   449: invokespecial 2169	com/mojang/minecraftpe/MainActivity:checkForSubstrate	()V
    //   452: aload_0
    //   453: invokespecial 2171	com/mojang/minecraftpe/MainActivity:fixMyEpicFail	()V
    //   456: aload_0
    //   457: invokevirtual 2173	com/mojang/minecraftpe/MainActivity:migrateToPatchManager	()V
    //   460: iconst_1
    //   461: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   464: astore 7
    //   466: iload_3
    //   467: istore_2
    //   468: aload 7
    //   470: ldc_w 994
    //   473: iconst_m1
    //   474: invokeinterface 797 3 0
    //   479: iload 4
    //   481: if_icmpeq +39 -> 520
    //   484: getstatic 511	java/lang/System:out	Ljava/io/PrintStream;
    //   487: ldc_w 2175
    //   490: invokevirtual 998	java/io/PrintStream:println	(Ljava/lang/String;)V
    //   493: aload 7
    //   495: invokeinterface 807 1 0
    //   500: ldc_w 982
    //   503: iconst_1
    //   504: invokeinterface 815 3 0
    //   509: invokeinterface 818 1 0
    //   514: aload_0
    //   515: invokespecial 2176	com/mojang/minecraftpe/MainActivity:disableAllPatches	()V
    //   518: iconst_1
    //   519: istore_2
    //   520: aload 7
    //   522: ldc_w 2178
    //   525: iconst_m1
    //   526: invokeinterface 797 3 0
    //   531: iload 4
    //   533: if_icmpeq +77 -> 610
    //   536: iconst_0
    //   537: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   540: invokeinterface 807 1 0
    //   545: ldc_w 2015
    //   548: iconst_0
    //   549: invokeinterface 815 3 0
    //   554: invokeinterface 818 1 0
    //   559: aload 7
    //   561: invokeinterface 807 1 0
    //   566: ldc_w 2178
    //   569: iload 4
    //   571: invokeinterface 822 3 0
    //   576: invokeinterface 818 1 0
    //   581: aload 7
    //   583: ldc_w 2180
    //   586: ldc_w 288
    //   589: invokeinterface 1475 3 0
    //   594: ldc_w 2182
    //   597: invokevirtual 1748	java/lang/String:indexOf	(Ljava/lang/String;)I
    //   600: iflt +10 -> 610
    //   603: aload_0
    //   604: sipush 4102
    //   607: invokevirtual 1046	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   610: aload_0
    //   611: invokevirtual 800	com/mojang/minecraftpe/MainActivity:getPackageName	()Ljava/lang/String;
    //   614: ldc_w 2135
    //   617: invokevirtual 630	java/lang/String:equals	(Ljava/lang/Object;)Z
    //   620: ifeq +527 -> 1147
    //   623: aload_0
    //   624: aload_0
    //   625: putfield 1684	com/mojang/minecraftpe/MainActivity:minecraftApkContext	Landroid/content/Context;
    //   628: invokestatic 2185	net/zhuoweizhang/mcpelauncher/Utils:setLanguageOverride	()V
    //   631: aload_0
    //   632: new 449	java/io/File
    //   635: dup
    //   636: ldc_w 2187
    //   639: invokespecial 451	java/io/File:<init>	(Ljava/lang/String;)V
    //   642: invokevirtual 664	java/io/File:exists	()Z
    //   645: putfield 278	com/mojang/minecraftpe/MainActivity:forceFallback	Z
    //   648: aload_0
    //   649: getfield 284	com/mojang/minecraftpe/MainActivity:textureOverrides	Ljava/util/List;
    //   652: invokeinterface 2190 1 0
    //   657: aload_0
    //   658: invokevirtual 2192	com/mojang/minecraftpe/MainActivity:loadTexturePack	()V
    //   661: aload_0
    //   662: invokevirtual 2194	com/mojang/minecraftpe/MainActivity:allowScriptOverrideTextures	()Z
    //   665: ifeq +21 -> 686
    //   668: aload_0
    //   669: getfield 284	com/mojang/minecraftpe/MainActivity:textureOverrides	Ljava/util/List;
    //   672: new 2196	net/zhuoweizhang/mcpelauncher/ScriptOverrideTexturePack
    //   675: dup
    //   676: aload_0
    //   677: invokespecial 2197	net/zhuoweizhang/mcpelauncher/ScriptOverrideTexturePack:<init>	(Landroid/content/Context;)V
    //   680: invokeinterface 1059 2 0
    //   685: pop
    //   686: aload_0
    //   687: invokestatic 2202	net/zhuoweizhang/mcpelauncher/ScriptTextureDownloader:attachCache	(Landroid/content/Context;)V
    //   690: aload_0
    //   691: aload_0
    //   692: invokevirtual 2204	com/mojang/minecraftpe/MainActivity:doesRequireGuiBlocksPatch	()Z
    //   695: putfield 280	com/mojang/minecraftpe/MainActivity:requiresGuiBlocksPatch	Z
    //   698: invokestatic 836	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   701: ifne +19 -> 720
    //   704: iconst_0
    //   705: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   708: ldc_w 2206
    //   711: iconst_1
    //   712: invokeinterface 931 3 0
    //   717: ifne +15 -> 732
    //   720: aload_0
    //   721: invokevirtual 1889	com/mojang/minecraftpe/MainActivity:getMCPEVersion	()Ljava/lang/String;
    //   724: ldc 123
    //   726: invokevirtual 899	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   729: ifeq +7 -> 736
    //   732: aload_0
    //   733: invokespecial 2208	com/mojang/minecraftpe/MainActivity:prePatch	()V
    //   736: new 537	java/lang/StringBuilder
    //   739: dup
    //   740: invokespecial 538	java/lang/StringBuilder:<init>	()V
    //   743: aload_0
    //   744: getfield 976	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   747: getfield 547	android/content/pm/ApplicationInfo:nativeLibraryDir	Ljava/lang/String;
    //   750: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   753: ldc_w 2210
    //   756: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   759: invokevirtual 557	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   762: invokestatic 686	java/lang/System:load	(Ljava/lang/String;)V
    //   765: new 537	java/lang/StringBuilder
    //   768: dup
    //   769: invokespecial 538	java/lang/StringBuilder:<init>	()V
    //   772: aload_0
    //   773: getfield 976	com/mojang/minecraftpe/MainActivity:mcAppInfo	Landroid/content/pm/ApplicationInfo;
    //   776: getfield 547	android/content/pm/ApplicationInfo:nativeLibraryDir	Ljava/lang/String;
    //   779: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   782: ldc_w 2212
    //   785: invokevirtual 551	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   788: invokevirtual 557	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   791: invokestatic 686	java/lang/System:load	(Ljava/lang/String;)V
    //   794: getstatic 248	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_LOCATION	Ljava/lang/String;
    //   797: invokestatic 686	java/lang/System:load	(Ljava/lang/String;)V
    //   800: aload_0
    //   801: invokestatic 2216	org/fmod/FMOD:init	(Landroid/content/Context;)V
    //   804: iconst_1
    //   805: putstatic 252	com/mojang/minecraftpe/MainActivity:libLoaded	Z
    //   808: invokestatic 836	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   811: ifeq +10 -> 821
    //   814: aload_0
    //   815: invokespecial 2218	com/mojang/minecraftpe/MainActivity:requiresPatchingInSafeMode	()Z
    //   818: ifeq +83 -> 901
    //   821: aload_0
    //   822: invokevirtual 2220	com/mojang/minecraftpe/MainActivity:initPatching	()V
    //   825: getstatic 2222	com/mojang/minecraftpe/MainActivity:minecraftLibBuffer	Ljava/nio/ByteBuffer;
    //   828: ifnull +73 -> 901
    //   831: iconst_0
    //   832: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   835: ldc_w 2224
    //   838: iconst_0
    //   839: invokeinterface 931 3 0
    //   844: istore 6
    //   846: aload_0
    //   847: invokevirtual 2226	com/mojang/minecraftpe/MainActivity:hasScriptSupport	()Z
    //   850: ifne +357 -> 1207
    //   853: iconst_1
    //   854: istore 5
    //   856: iload 6
    //   858: aload_0
    //   859: iload 5
    //   861: invokestatic 2230	net/zhuoweizhang/mcpelauncher/ScriptManager:nativePrePatch	(ZLcom/mojang/minecraftpe/MainActivity;Z)V
    //   864: aload_0
    //   865: invokevirtual 2226	com/mojang/minecraftpe/MainActivity:hasScriptSupport	()Z
    //   868: ifeq +23 -> 891
    //   871: iconst_0
    //   872: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   875: ldc_w 2232
    //   878: iconst_0
    //   879: invokeinterface 931 3 0
    //   884: ifeq +7 -> 891
    //   887: iconst_1
    //   888: invokestatic 2235	net/zhuoweizhang/mcpelauncher/ScriptManager:nativeModPESetDesktopGui	(Z)V
    //   891: invokestatic 836	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   894: ifne +7 -> 901
    //   897: aload_0
    //   898: invokevirtual 2237	com/mojang/minecraftpe/MainActivity:loadNativeAddons	()V
    //   901: aload_0
    //   902: invokevirtual 2226	com/mojang/minecraftpe/MainActivity:hasScriptSupport	()Z
    //   905: istore 5
    //   907: invokestatic 836	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   910: ifne +51 -> 961
    //   913: getstatic 2222	com/mojang/minecraftpe/MainActivity:minecraftLibBuffer	Ljava/nio/ByteBuffer;
    //   916: ifnull +45 -> 961
    //   919: aload_0
    //   920: invokevirtual 2101	com/mojang/minecraftpe/MainActivity:applyBuiltinPatches	()V
    //   923: iload 5
    //   925: ifeq +36 -> 961
    //   928: iconst_0
    //   929: invokestatic 789	net/zhuoweizhang/mcpelauncher/Utils:getPrefs	(I)Landroid/content/SharedPreferences;
    //   932: ldc_w 2239
    //   935: iconst_1
    //   936: invokeinterface 931 3 0
    //   941: ifeq +20 -> 961
    //   944: aload_0
    //   945: invokestatic 2240	net/zhuoweizhang/mcpelauncher/ScriptManager:init	(Landroid/content/Context;)V
    //   948: aload_0
    //   949: getfield 284	com/mojang/minecraftpe/MainActivity:textureOverrides	Ljava/util/List;
    //   952: getstatic 2244	net/zhuoweizhang/mcpelauncher/ScriptManager:modPkgTexturePack	Lnet/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack;
    //   955: invokeinterface 1059 2 0
    //   960: pop
    //   961: invokestatic 836	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   964: ifne +8 -> 972
    //   967: iload 5
    //   969: ifne +7 -> 976
    //   972: aload_0
    //   973: invokestatic 2247	net/zhuoweizhang/mcpelauncher/ScriptManager:loadEnabledScriptsNames	(Landroid/content/Context;)V
    //   976: iload_2
    //   977: ifeq +6 -> 983
    //   980: invokestatic 2250	net/zhuoweizhang/mcpelauncher/ScriptManager:clearTextureOverrides	()V
    //   983: aload_0
    //   984: invokespecial 2252	com/mojang/minecraftpe/MainActivity:initAtlasMeta	()V
    //   987: aload_0
    //   988: new 1676	android/util/DisplayMetrics
    //   991: dup
    //   992: invokespecial 2253	android/util/DisplayMetrics:<init>	()V
    //   995: putfield 1674	com/mojang/minecraftpe/MainActivity:displayMetrics	Landroid/util/DisplayMetrics;
    //   998: aload_0
    //   999: iconst_3
    //   1000: invokevirtual 2256	com/mojang/minecraftpe/MainActivity:setVolumeControlStream	(I)V
    //   1003: aload_0
    //   1004: invokevirtual 2260	com/mojang/minecraftpe/MainActivity:getWindowManager	()Landroid/view/WindowManager;
    //   1007: invokeinterface 2266 1 0
    //   1012: aload_0
    //   1013: getfield 1674	com/mojang/minecraftpe/MainActivity:displayMetrics	Landroid/util/DisplayMetrics;
    //   1016: invokevirtual 2272	android/view/Display:getMetrics	(Landroid/util/DisplayMetrics;)V
    //   1019: aload_0
    //   1020: getstatic 244	com/mojang/minecraftpe/MainActivity:MC_NATIVE_LIBRARY_DIR	Ljava/lang/String;
    //   1023: invokespecial 2274	com/mojang/minecraftpe/MainActivity:addLibraryDirToPath	(Ljava/lang/String;)V
    //   1026: aload_0
    //   1027: iconst_1
    //   1028: invokespecial 2276	com/mojang/minecraftpe/MainActivity:setFakePackage	(Z)V
    //   1031: aload_0
    //   1032: aload_1
    //   1033: invokespecial 2278	android/app/NativeActivity:onCreate	(Landroid/os/Bundle;)V
    //   1036: aload_0
    //   1037: invokevirtual 2280	com/mojang/minecraftpe/MainActivity:nativeRegisterThis	()V
    //   1040: aload_0
    //   1041: iconst_0
    //   1042: invokespecial 2276	com/mojang/minecraftpe/MainActivity:setFakePackage	(Z)V
    //   1045: aload_0
    //   1046: iconst_1
    //   1047: invokestatic 2284	net/zhuoweizhang/mcpelauncher/Utils:setupTheme	(Landroid/content/Context;Z)V
    //   1050: aload_0
    //   1051: invokespecial 2286	com/mojang/minecraftpe/MainActivity:disableTransparentSystemBar	()V
    //   1054: new 2288	java/net/CookieManager
    //   1057: dup
    //   1058: invokespecial 2289	java/net/CookieManager:<init>	()V
    //   1061: invokestatic 2295	java/net/CookieHandler:setDefault	(Ljava/net/CookieHandler;)V
    //   1064: invokestatic 836	net/zhuoweizhang/mcpelauncher/Utils:isSafeMode	()Z
    //   1067: ifeq +17 -> 1084
    //   1070: aload_0
    //   1071: getfield 299	com/mojang/minecraftpe/MainActivity:overlyZealousSELinuxSafeMode	Z
    //   1074: ifeq +171 -> 1245
    //   1077: aload_0
    //   1078: sipush 4106
    //   1081: invokevirtual 1046	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   1084: aload_0
    //   1085: invokevirtual 2297	com/mojang/minecraftpe/MainActivity:initKamcord	()V
    //   1088: invokestatic 2300	java/lang/System:gc	()V
    //   1091: return
    //   1092: iconst_0
    //   1093: istore 5
    //   1095: goto -861 -> 234
    //   1098: iconst_0
    //   1099: istore_2
    //   1100: goto -756 -> 344
    //   1103: iconst_0
    //   1104: istore_2
    //   1105: goto -724 -> 381
    //   1108: astore_1
    //   1109: aload_1
    //   1110: invokevirtual 2301	android/content/pm/PackageManager$NameNotFoundException:printStackTrace	()V
    //   1113: aload_0
    //   1114: invokevirtual 606	com/mojang/minecraftpe/MainActivity:finish	()V
    //   1117: aload_0
    //   1118: new 559	android/content/Intent
    //   1121: dup
    //   1122: aload_0
    //   1123: ldc_w 561
    //   1126: invokespecial 564	android/content/Intent:<init>	(Landroid/content/Context;Ljava/lang/Class;)V
    //   1129: invokevirtual 603	com/mojang/minecraftpe/MainActivity:startActivity	(Landroid/content/Intent;)V
    //   1132: ldc2_w 645
    //   1135: invokestatic 614	java/lang/Thread:sleep	(J)V
    //   1138: invokestatic 652	android/os/Process:myPid	()I
    //   1141: invokestatic 655	android/os/Process:killProcess	(I)V
    //   1144: return
    //   1145: astore_1
    //   1146: return
    //   1147: aload_0
    //   1148: aload_0
    //   1149: ldc_w 2135
    //   1152: iconst_2
    //   1153: invokevirtual 2305	com/mojang/minecraftpe/MainActivity:createPackageContext	(Ljava/lang/String;I)Landroid/content/Context;
    //   1156: putfield 1684	com/mojang/minecraftpe/MainActivity:minecraftApkContext	Landroid/content/Context;
    //   1159: goto -531 -> 628
    //   1162: astore 7
    //   1164: aload 7
    //   1166: invokevirtual 520	java/lang/Exception:printStackTrace	()V
    //   1169: aload_0
    //   1170: ldc_w 2307
    //   1173: iconst_1
    //   1174: invokestatic 2313	android/widget/Toast:makeText	(Landroid/content/Context;Ljava/lang/CharSequence;I)Landroid/widget/Toast;
    //   1177: invokevirtual 2315	android/widget/Toast:show	()V
    //   1180: aload_0
    //   1181: invokevirtual 606	com/mojang/minecraftpe/MainActivity:finish	()V
    //   1184: goto -556 -> 628
    //   1187: astore 7
    //   1189: aload 7
    //   1191: invokevirtual 520	java/lang/Exception:printStackTrace	()V
    //   1194: goto -458 -> 736
    //   1197: astore_1
    //   1198: new 688	java/lang/RuntimeException
    //   1201: dup
    //   1202: aload_1
    //   1203: invokespecial 691	java/lang/RuntimeException:<init>	(Ljava/lang/Throwable;)V
    //   1206: athrow
    //   1207: iconst_0
    //   1208: istore 5
    //   1210: goto -354 -> 856
    //   1213: astore 7
    //   1215: aload 7
    //   1217: invokevirtual 520	java/lang/Exception:printStackTrace	()V
    //   1220: aload_0
    //   1221: aload 7
    //   1223: invokevirtual 889	com/mojang/minecraftpe/MainActivity:reportError	(Ljava/lang/Throwable;)V
    //   1226: goto -325 -> 901
    //   1229: astore 7
    //   1231: aload 7
    //   1233: invokevirtual 520	java/lang/Exception:printStackTrace	()V
    //   1236: aload_0
    //   1237: aload 7
    //   1239: invokevirtual 889	com/mojang/minecraftpe/MainActivity:reportError	(Ljava/lang/Throwable;)V
    //   1242: goto -266 -> 976
    //   1245: aload_0
    //   1246: sipush 4096
    //   1249: invokevirtual 1046	com/mojang/minecraftpe/MainActivity:showDialog	(I)V
    //   1252: goto -168 -> 1084
    //   1255: astore 7
    //   1257: goto -809 -> 448
    //   1260: iconst_1
    //   1261: istore_2
    //   1262: goto -918 -> 344
    //   1265: iconst_1
    //   1266: istore_2
    //   1267: goto -886 -> 381
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	1270	0	this	MainActivity
    //   0	1270	1	paramBundle	Bundle
    //   52	1215	2	i	int
    //   24	443	3	j	int
    //   247	323	4	k	int
    //   232	977	5	bool1	boolean
    //   844	13	6	bool2	boolean
    //   396	186	7	localObject	Object
    //   1162	3	7	localException1	Exception
    //   1187	3	7	localException2	Exception
    //   1213	9	7	localException3	Exception
    //   1229	9	7	localException4	Exception
    //   1255	1	7	localThrowable	Throwable
    // Exception table:
    //   from	to	target	type
    //   118	231	1108	android/content/pm/PackageManager$NameNotFoundException
    //   234	283	1108	android/content/pm/PackageManager$NameNotFoundException
    //   283	302	1108	android/content/pm/PackageManager$NameNotFoundException
    //   302	341	1108	android/content/pm/PackageManager$NameNotFoundException
    //   344	359	1108	android/content/pm/PackageManager$NameNotFoundException
    //   363	378	1108	android/content/pm/PackageManager$NameNotFoundException
    //   385	436	1108	android/content/pm/PackageManager$NameNotFoundException
    //   436	448	1108	android/content/pm/PackageManager$NameNotFoundException
    //   448	466	1108	android/content/pm/PackageManager$NameNotFoundException
    //   468	518	1108	android/content/pm/PackageManager$NameNotFoundException
    //   520	610	1108	android/content/pm/PackageManager$NameNotFoundException
    //   1132	1144	1145	java/lang/Throwable
    //   610	628	1162	java/lang/Exception
    //   1147	1159	1162	java/lang/Exception
    //   698	720	1187	java/lang/Exception
    //   720	732	1187	java/lang/Exception
    //   732	736	1187	java/lang/Exception
    //   736	800	1197	java/lang/Exception
    //   808	821	1213	java/lang/Exception
    //   821	853	1213	java/lang/Exception
    //   856	891	1213	java/lang/Exception
    //   891	901	1213	java/lang/Exception
    //   901	923	1229	java/lang/Exception
    //   928	961	1229	java/lang/Exception
    //   961	967	1229	java/lang/Exception
    //   972	976	1229	java/lang/Exception
    //   436	448	1255	java/lang/Throwable
  }
  
  public Dialog onCreateDialog(int paramInt)
  {
    switch (paramInt)
    {
    default: 
      return super.onCreateDialog(paramInt);
    case 1: 
      return createCreateWorldDialog();
    case 4: 
      return createCopyWorldDialog();
    case 4096: 
      return createSafeModeDialog(R.string.manage_patches_crash_safe_mode);
    case 4097: 
      return createRuntimeOptionsDialog(false);
    case 4098: 
      return createInvalidPatchesDialog();
    case 4099: 
      return createFirstLaunchDialog();
    case 4100: 
      return createSafeModeDialog(R.string.version_mismatch_message);
    case 4101: 
      return createNotSupportedDialog();
    case 4102: 
      return createUpdateTexturePackDialog();
    case 4103: 
      return createInsertTextDialog();
    case 4104: 
      return createMultiplayerDisableScriptsDialog();
    case 4105: 
      return createRuntimeOptionsDialog(true);
    }
    return createSELinuxBrokeEverythingDialog();
  }
  
  public void onDestroy()
  {
    nativeUnregisterThis();
    super.onDestroy();
    File localFile = new File(getFilesDir(), "running.lock");
    if (localFile.exists()) {
      localFile.delete();
    }
    if (this.hoverCar != null)
    {
      this.hoverCar.dismiss();
      this.hoverCar = null;
    }
    ScriptManager.destroy();
    System.exit(0);
  }
  
  protected void onPause()
  {
    nativeSuspend();
    super.onPause();
    Utils.getPrefs(2).edit().putInt("safe_mode_counter", 0).commit();
    this.hasResetSafeModeCounter = true;
    hideKeyboardView();
  }
  
  protected void onResume()
  {
    super.onResume();
    if (this.hasResetSafeModeCounter)
    {
      int i = Utils.getPrefs(2).getInt("safe_mode_counter", 0);
      Utils.getPrefs(2).edit().putInt("safe_mode_counter", i + 1).commit();
    }
    if (this.hoverCar == null)
    {
      getWindow().getDecorView().post(new Runnable()
      {
        public void run()
        {
          try
          {
            MainActivity.this.setupHoverCar();
            return;
          }
          catch (Exception localException)
          {
            localException.printStackTrace();
          }
        }
      });
      setImmersiveMode(Utils.getPrefs(0).getBoolean("zz_immersive_mode", false));
      return;
    }
    HoverCar localHoverCar = this.hoverCar;
    if (!Utils.getPrefs(0).getBoolean("zz_hovercar_hide", false)) {}
    for (boolean bool = true;; bool = false)
    {
      localHoverCar.setVisible(bool);
      break;
    }
  }
  
  public void onStop()
  {
    nativeStopThis();
    super.onStop();
    ScriptTextureDownloader.flushCache();
    System.gc();
  }
  
  public void onWindowFocusChanged(boolean paramBoolean)
  {
    super.onWindowFocusChanged(paramBoolean);
    if (paramBoolean) {
      touchImmersiveMode();
    }
  }
  
  protected InputStream openFallbackAsset(String paramString)
    throws IOException
  {
    return getAssets().open(paramString);
  }
  
  public void openLoginWindow()
  {
    Log.i("BlockLauncher/Main", "Open login window");
    runOnUiThread(new Runnable()
    {
      @SuppressLint({"SetJavaScriptEnabled"})
      public void run()
      {
        MainActivity.access$502(MainActivity.this, new WebView(MainActivity.this));
        MainActivity.this.loginWebView.setLayoutParams(new ViewGroup.LayoutParams(-1, -1));
        MainActivity.this.loginWebView.setWebViewClient(new MainActivity.LoginWebViewClient(MainActivity.this, null));
        MainActivity.this.loginWebView.getSettings().setJavaScriptEnabled(true);
        MainActivity.access$702(MainActivity.this, new Dialog(MainActivity.this));
        MainActivity.this.loginDialog.setCancelable(true);
        MainActivity.this.loginDialog.requestWindowFeature(1);
        MainActivity.this.loginDialog.setContentView(MainActivity.this.loginWebView);
        MainActivity.this.loginDialog.getWindow().setLayout(-1, -1);
        MainActivity.this.loginDialog.show();
        MainActivity.this.loginWebView.loadUrl(MainActivity.this.getRealmsRedirectInfo().loginUrl);
      }
    });
  }
  
  public void pickImage(long paramLong)
  {
    this.pickImageCallbackAddress = paramLong;
    startActivityForResult(new Intent("android.intent.action.PICK", MediaStore.Images.Media.EXTERNAL_CONTENT_URI), 415);
  }
  
  public void postScreenshotToFacebook(String paramString, int paramInt1, int paramInt2, int[] paramArrayOfInt) {}
  
  public void quit()
  {
    finish();
  }
  
  public void reportError(Throwable paramThrowable)
  {
    reportError(paramThrowable, R.string.report_error_title, null);
  }
  
  public void reportError(final Throwable paramThrowable, final int paramInt, final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        final Object localObject = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter((Writer)localObject);
        paramThrowable.printStackTrace(localPrintWriter);
        if (paramString != null) {}
        for (localObject = paramString + "\n" + ((StringWriter)localObject).toString();; localObject = ((StringWriter)localObject).toString())
        {
          new AlertDialog.Builder(MainActivity.this).setTitle(paramInt).setMessage((CharSequence)localObject).setPositiveButton(17039370, null).setNeutralButton(17039361, new DialogInterface.OnClickListener()
          {
            public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
            {
              ((ClipboardManager)MainActivity.this.getSystemService("clipboard")).setText(localObject);
            }
          }).show();
          return;
        }
      }
    });
  }
  
  public void reportReimported(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.manage_scripts_reimported_toast) + " " + paramString, 0).show();
      }
    });
  }
  
  protected void resetOrientation() {}
  
  public void screenshotCallback(final File paramFile)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(MainActivity.this, MainActivity.this.getResources().getString(R.string.screenshot_saved_as) + " " + paramFile.getAbsolutePath(), 1).show();
        MediaScannerConnection.scanFile(MainActivity.this, new String[] { paramFile.getAbsolutePath() }, new String[] { "image/png" }, null);
      }
    });
  }
  
  public void scriptErrorCallback(final String paramString, final Throwable paramThrowable)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        final StringWriter localStringWriter = new StringWriter();
        PrintWriter localPrintWriter = new PrintWriter(localStringWriter);
        localPrintWriter.println("Error occurred in script: " + paramString);
        if ((paramThrowable instanceof RhinoException))
        {
          String str = ((RhinoException)paramThrowable).lineSource();
          if (str != null) {
            localPrintWriter.println(str);
          }
        }
        paramThrowable.printStackTrace(localPrintWriter);
        new AlertDialog.Builder(MainActivity.this).setTitle(R.string.script_execution_error).setMessage(localStringWriter.toString()).setPositiveButton(17039370, null).setNeutralButton(17039361, new DialogInterface.OnClickListener()
        {
          public void onClick(DialogInterface paramAnonymous2DialogInterface, int paramAnonymous2Int)
          {
            ((ClipboardManager)MainActivity.this.getSystemService("clipboard")).setText(localStringWriter.toString());
          }
        }).show();
      }
    });
  }
  
  public void scriptOverrideTexture(String paramString1, String paramString2)
  {
    forceTextureReload();
  }
  
  public void scriptPrintCallback(final String paramString1, final String paramString2)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        Toast.makeText(MainActivity.this, "Script " + paramString2 + ": " + paramString1, 0).show();
      }
    });
  }
  
  public void scriptResetImages()
  {
    forceTextureReload();
  }
  
  public void scriptTooManyErrorsCallback(final String paramString)
  {
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        new AlertDialog.Builder(MainActivity.this).setTitle(R.string.script_execution_error).setMessage(paramString + " " + MainActivity.this.getResources().getString(R.string.script_too_many_errors)).setPositiveButton(17039370, null).show();
      }
    });
  }
  
  public void setFileDialogCallback(long paramLong) {}
  
  public void setIsPowerVR(boolean paramBoolean)
  {
    System.out.println("PowerVR: " + paramBoolean);
  }
  
  public void setLevelCallback(boolean paramBoolean)
  {
    System.out.println("Set level callback: " + paramBoolean);
    if ((paramBoolean) && (ScriptManager.scripts.size() > 0)) {
      runOnUiThread(new Runnable()
      {
        public void run()
        {
          MainActivity.this.showDialog(4104);
        }
      });
    }
    if (this.hasRecorder) {
      clearRuntimeOptionsDialog();
    }
  }
  
  public void setLoginInformation(String paramString1, String paramString2, String paramString3, String paramString4)
  {
    Utils.getPrefs(0).edit().putString("accessToken", paramString1).putString("clientId", paramString2).putString("profileUuid", paramString3).putString("profileName", paramString4).apply();
  }
  
  public void setRefreshToken(String paramString)
  {
    Utils.getPrefs(0).edit().putString("refreshToken", paramString).apply();
  }
  
  public void setSession(String paramString)
  {
    Utils.getPrefs(0).edit().putString("sessionId", paramString).apply();
  }
  
  protected void setupHoverCar()
  {
    boolean bool = false;
    this.hoverCar = new HoverCar(this, Utils.isSafeMode());
    this.hoverCar.show(getWindow().getDecorView());
    HoverCar localHoverCar = this.hoverCar;
    if (!Utils.getPrefs(0).getBoolean("zz_hovercar_hide", false)) {
      bool = true;
    }
    localHoverCar.setVisible(bool);
    this.hoverCar.mainButton.setOnClickListener(new View.OnClickListener()
    {
      public void onClick(View paramAnonymousView)
      {
        boolean bool = Utils.getPrefs(0).getBoolean("zz_show_insert_text", false);
        paramAnonymousView = MainActivity.this;
        if (bool) {}
        for (int i = 4105;; i = 4097)
        {
          paramAnonymousView.showDialog(i);
          MainActivity.this.resetOrientation();
          return;
        }
      }
    });
  }
  
  public void showHiddenTextbox(String paramString, int paramInt, boolean paramBoolean)
  {
    boolean bool = isCommandHistoryEnabled();
    Object localObject;
    if (this.hiddenTextWindow == null)
    {
      if (!bool) {
        break label329;
      }
      this.commandHistoryView = getLayoutInflater().inflate(R.layout.chat_history_popup, null);
      this.hiddenTextView = ((TextView)this.commandHistoryView.findViewById(R.id.hidden_text_view));
      this.prevButton = ((Button)this.commandHistoryView.findViewById(R.id.command_history_previous));
      this.nextButton = ((Button)this.commandHistoryView.findViewById(R.id.command_history_next));
      localObject = new View.OnClickListener()
      {
        public void onClick(View paramAnonymousView)
        {
          if (paramAnonymousView == MainActivity.this.prevButton) {
            MainActivity.this.navigateCommandHistory(-1);
          }
          while (paramAnonymousView != MainActivity.this.nextButton) {
            return;
          }
          MainActivity.this.navigateCommandHistory(1);
        }
      };
      this.prevButton.setOnClickListener((View.OnClickListener)localObject);
      this.nextButton.setOnClickListener((View.OnClickListener)localObject);
      localObject = new PopupTextWatcher(null);
      this.hiddenTextView.addTextChangedListener((TextWatcher)localObject);
      this.hiddenTextView.setOnEditorActionListener((TextView.OnEditorActionListener)localObject);
      this.hiddenTextView.setSingleLine(true);
      this.hiddenTextView.setImeOptions(301989893);
      this.hiddenTextView.setInputType(1);
      if (!bool) {
        break label344;
      }
    }
    for (this.hiddenTextWindow = new PopupWindow(this.commandHistoryView);; this.hiddenTextWindow = new PopupWindow((View)localObject))
    {
      this.hiddenTextWindow.setWindowLayoutMode(-2, -2);
      this.hiddenTextWindow.setFocusable(true);
      this.hiddenTextWindow.setInputMethodMode(1);
      this.hiddenTextWindow.setBackgroundDrawable(new ColorDrawable());
      this.hiddenTextWindow.setClippingEnabled(false);
      this.hiddenTextWindow.setTouchable(bool);
      this.hiddenTextWindow.setOutsideTouchable(true);
      this.hiddenTextWindow.setOnDismissListener(new PopupWindow.OnDismissListener()
      {
        public void onDismiss()
        {
          MainActivity.this.nativeBackPressed();
        }
      });
      if (!bool) {
        break label405;
      }
      paramInt = this.commandHistoryList.size() - 1;
      while (paramInt >= 0)
      {
        if (((String)this.commandHistoryList.get(paramInt)).equals("")) {
          this.commandHistoryList.remove(paramInt);
        }
        paramInt -= 1;
      }
      label329:
      this.hiddenTextView = new EditText(this);
      break;
      label344:
      localObject = new LinearLayout(this);
      ((LinearLayout)localObject).addView(this.hiddenTextView);
    }
    this.commandHistoryList.add(paramString);
    setCommandHistoryIndex(this.commandHistoryList.size() - 1);
    label405:
    this.hiddenTextView.setText(paramString);
    Selection.setSelection((Spannable)this.hiddenTextView.getText(), paramString.length());
    this.hiddenTextDismissAfterOneLine = paramBoolean;
    if (bool) {}
    for (paramInt = 0;; paramInt = 55536)
    {
      this.hiddenTextWindow.showAtLocation(getWindow().getDecorView(), 51, paramInt, 0);
      this.hiddenTextView.requestFocus();
      showKeyboardView();
      return;
    }
  }
  
  public void showKeyboard(String paramString, int paramInt, boolean paramBoolean)
  {
    showKeyboard(paramString, paramInt, paramBoolean, false);
  }
  
  public void showKeyboard(final String paramString, final int paramInt, final boolean paramBoolean1, boolean paramBoolean2)
  {
    if (useLegacyKeyboardInput())
    {
      showKeyboardView();
      return;
    }
    runOnUiThread(new Runnable()
    {
      public void run()
      {
        MainActivity.this.showHiddenTextbox(paramString, paramInt, paramBoolean1);
      }
    });
  }
  
  public void showKeyboardView()
  {
    Log.i("BlockLauncher/Main", "Show keyboard view");
    ((InputMethodManager)getSystemService("input_method")).showSoftInput(getWindow().getDecorView(), 2);
  }
  
  public void statsTrackEvent(String paramString1, String paramString2)
  {
    Log.i("BlockLauncher/Main", "Stats track: " + paramString1 + ":" + paramString2);
  }
  
  public void statsUpdateUserData(String paramString1, String paramString2)
  {
    Log.i("BlockLauncher/Main", "Stats update user data: " + paramString1 + ":" + paramString2);
  }
  
  public boolean supportsNonTouchscreen()
  {
    if (isForcingController()) {
      if ((!this.controllerInit) && (!Utils.isSafeMode()))
      {
        ControllerManager.init();
        this.controllerInit = true;
      }
    }
    int k;
    int j;
    do
    {
      return true;
      k = 0;
      j = 0;
      String[] arrayOfString = new String[3];
      arrayOfString[0] = Build.MODEL.toLowerCase(Locale.ENGLISH);
      arrayOfString[1] = Build.DEVICE.toLowerCase(Locale.ENGLISH);
      arrayOfString[2] = Build.PRODUCT.toLowerCase(Locale.ENGLISH);
      int m = arrayOfString.length;
      int i = 0;
      while (i < m)
      {
        String str = arrayOfString[i];
        if (str.indexOf("xperia") >= 0) {
          k = 1;
        }
        if (str.indexOf("play") >= 0) {
          j = 1;
        }
        i += 1;
      }
    } while ((k != 0) && (j != 0));
    return false;
  }
  
  public void tick() {}
  
  protected void toggleRecording() {}
  
  public void updateLocalization(String paramString1, String paramString2)
  {
    System.out.println("Update localization: " + paramString1 + ":" + paramString2);
  }
  
  public void updateTextboxText(final String paramString)
  {
    if (this.hiddenTextView == null) {
      return;
    }
    this.hiddenTextView.post(new Runnable()
    {
      public void run()
      {
        if (MainActivity.this.isCommandHistoryEnabled())
        {
          if ((MainActivity.this.commandHistoryList.size() < 1) || (((String)MainActivity.this.commandHistoryList.get(MainActivity.this.commandHistoryList.size() - 1)).length() > 0)) {
            break label128;
          }
          MainActivity.this.commandHistoryList.set(MainActivity.this.commandHistoryList.size() - 1, paramString);
        }
        for (;;)
        {
          MainActivity.this.setCommandHistoryIndex(MainActivity.this.commandHistoryList.size() - 1);
          MainActivity.this.hiddenTextView.setText(paramString);
          return;
          label128:
          MainActivity.this.commandHistoryList.add(paramString);
        }
      }
    });
  }
  
  public void vibrate(int paramInt)
  {
    int i = paramInt;
    if (Utils.getPrefs(0).getBoolean("zz_longvibration", false)) {
      i = paramInt * 5;
    }
    ((Vibrator)getSystemService("vibrator")).vibrate(i);
  }
  
  public void webRequest(int paramInt, long paramLong, String paramString1, String paramString2, String paramString3)
  {
    webRequest(paramInt, paramLong, paramString1, paramString2, paramString3, "");
  }
  
  public void webRequest(int paramInt, long paramLong, String paramString1, String paramString2, String paramString3, String paramString4)
  {
    new Thread(new HurlRunner(paramInt, paramLong, filterUrl(paramString1), paramString2, paramString3)).start();
  }
  
  private class HurlRunner
    implements Runnable
  {
    private HttpURLConnection conn;
    private String cookies;
    private boolean isValid = true;
    private String method;
    private int requestId;
    private String strurl;
    private long timestamp;
    private URL url;
    
    public HurlRunner(int paramInt, long paramLong, String arg5, String paramString2, String paramString3)
    {
      this.requestId = paramInt;
      this.timestamp = paramLong;
      this.strurl = ???;
      this.method = paramString2;
      this.cookies = paramString3;
      synchronized (MainActivity.this.requestMap)
      {
        MainActivity.this.requestMap.put(paramInt, this);
        return;
      }
    }
    
    /* Error */
    public void run()
    {
      // Byte code:
      //   0: aconst_null
      //   1: astore 9
      //   3: aconst_null
      //   4: astore 10
      //   6: aconst_null
      //   7: astore 4
      //   9: aconst_null
      //   10: astore 8
      //   12: aconst_null
      //   13: astore 7
      //   15: iconst_0
      //   16: istore_3
      //   17: iconst_0
      //   18: istore_2
      //   19: iload_3
      //   20: istore_1
      //   21: aload 9
      //   23: astore 6
      //   25: aload 10
      //   27: astore 5
      //   29: aload_0
      //   30: new 62	java/net/URL
      //   33: dup
      //   34: aload_0
      //   35: getfield 40	com/mojang/minecraftpe/MainActivity$HurlRunner:strurl	Ljava/lang/String;
      //   38: invokespecial 65	java/net/URL:<init>	(Ljava/lang/String;)V
      //   41: putfield 67	com/mojang/minecraftpe/MainActivity$HurlRunner:url	Ljava/net/URL;
      //   44: iload_3
      //   45: istore_1
      //   46: aload 9
      //   48: astore 6
      //   50: aload 10
      //   52: astore 5
      //   54: aload_0
      //   55: aload_0
      //   56: getfield 67	com/mojang/minecraftpe/MainActivity$HurlRunner:url	Ljava/net/URL;
      //   59: invokevirtual 71	java/net/URL:openConnection	()Ljava/net/URLConnection;
      //   62: checkcast 73	java/net/HttpURLConnection
      //   65: putfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   68: iload_3
      //   69: istore_1
      //   70: aload 9
      //   72: astore 6
      //   74: aload 10
      //   76: astore 5
      //   78: aload_0
      //   79: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   82: aload_0
      //   83: getfield 42	com/mojang/minecraftpe/MainActivity$HurlRunner:method	Ljava/lang/String;
      //   86: invokevirtual 78	java/net/HttpURLConnection:setRequestMethod	(Ljava/lang/String;)V
      //   89: iload_3
      //   90: istore_1
      //   91: aload 9
      //   93: astore 6
      //   95: aload 10
      //   97: astore 5
      //   99: aload_0
      //   100: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   103: ldc 80
      //   105: aload_0
      //   106: getfield 44	com/mojang/minecraftpe/MainActivity$HurlRunner:cookies	Ljava/lang/String;
      //   109: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   112: iload_3
      //   113: istore_1
      //   114: aload 9
      //   116: astore 6
      //   118: aload 10
      //   120: astore 5
      //   122: aload_0
      //   123: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   126: ldc 86
      //   128: ldc 88
      //   130: invokevirtual 84	java/net/HttpURLConnection:setRequestProperty	(Ljava/lang/String;Ljava/lang/String;)V
      //   133: iload_3
      //   134: istore_1
      //   135: aload 9
      //   137: astore 6
      //   139: aload 10
      //   141: astore 5
      //   143: aload_0
      //   144: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   147: iconst_0
      //   148: invokevirtual 92	java/net/HttpURLConnection:setUseCaches	(Z)V
      //   151: iload_3
      //   152: istore_1
      //   153: aload 9
      //   155: astore 6
      //   157: aload 10
      //   159: astore 5
      //   161: aload_0
      //   162: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   165: iconst_1
      //   166: invokevirtual 95	java/net/HttpURLConnection:setDoInput	(Z)V
      //   169: iload_3
      //   170: istore_1
      //   171: aload 9
      //   173: astore 6
      //   175: aload 10
      //   177: astore 5
      //   179: aload_0
      //   180: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   183: invokevirtual 98	java/net/HttpURLConnection:connect	()V
      //   186: aload 10
      //   188: astore 5
      //   190: aload_0
      //   191: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   194: invokevirtual 102	java/net/HttpURLConnection:getResponseCode	()I
      //   197: istore_1
      //   198: iload_1
      //   199: istore_2
      //   200: aload 10
      //   202: astore 5
      //   204: aload_0
      //   205: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   208: invokevirtual 106	java/net/HttpURLConnection:getInputStream	()Ljava/io/InputStream;
      //   211: astore 6
      //   213: aload 6
      //   215: astore 4
      //   217: iload_1
      //   218: istore_2
      //   219: aload 7
      //   221: astore 5
      //   223: aload 4
      //   225: ifnull +49 -> 274
      //   228: iload_2
      //   229: istore_1
      //   230: aload 4
      //   232: astore 6
      //   234: aload 4
      //   236: astore 5
      //   238: aload_0
      //   239: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   242: invokevirtual 109	java/net/HttpURLConnection:getContentLength	()I
      //   245: ifge +207 -> 452
      //   248: sipush 1024
      //   251: istore_3
      //   252: iload_2
      //   253: istore_1
      //   254: aload 4
      //   256: astore 6
      //   258: aload 4
      //   260: astore 5
      //   262: aload 4
      //   264: iload_3
      //   265: invokestatic 113	com/mojang/minecraftpe/MainActivity:access$2300	(Ljava/io/InputStream;I)Ljava/lang/String;
      //   268: astore 7
      //   270: aload 7
      //   272: astore 5
      //   274: iload_2
      //   275: istore_3
      //   276: aload 5
      //   278: astore 7
      //   280: aload 4
      //   282: ifnull +14 -> 296
      //   285: aload 4
      //   287: invokevirtual 118	java/io/InputStream:close	()V
      //   290: aload 5
      //   292: astore 7
      //   294: iload_2
      //   295: istore_3
      //   296: aload 7
      //   298: ifnull +3 -> 301
      //   301: aload_0
      //   302: getfield 34	com/mojang/minecraftpe/MainActivity$HurlRunner:isValid	Z
      //   305: ifeq +21 -> 326
      //   308: aload_0
      //   309: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   312: aload_0
      //   313: getfield 36	com/mojang/minecraftpe/MainActivity$HurlRunner:requestId	I
      //   316: aload_0
      //   317: getfield 38	com/mojang/minecraftpe/MainActivity$HurlRunner:timestamp	J
      //   320: iload_3
      //   321: aload 7
      //   323: invokevirtual 122	com/mojang/minecraftpe/MainActivity:nativeWebRequestCompleted	(IJILjava/lang/String;)V
      //   326: aload_0
      //   327: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   330: invokestatic 48	com/mojang/minecraftpe/MainActivity:access$2200	(Lcom/mojang/minecraftpe/MainActivity;)Landroid/util/SparseArray;
      //   333: astore 4
      //   335: aload 4
      //   337: monitorenter
      //   338: aload_0
      //   339: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   342: invokestatic 48	com/mojang/minecraftpe/MainActivity:access$2200	(Lcom/mojang/minecraftpe/MainActivity;)Landroid/util/SparseArray;
      //   345: aload_0
      //   346: getfield 29	com/mojang/minecraftpe/MainActivity$HurlRunner:this$0	Lcom/mojang/minecraftpe/MainActivity;
      //   349: invokestatic 48	com/mojang/minecraftpe/MainActivity:access$2200	(Lcom/mojang/minecraftpe/MainActivity;)Landroid/util/SparseArray;
      //   352: aload_0
      //   353: invokevirtual 126	android/util/SparseArray:indexOfValue	(Ljava/lang/Object;)I
      //   356: invokevirtual 130	android/util/SparseArray:remove	(I)V
      //   359: aload 4
      //   361: monitorexit
      //   362: return
      //   363: astore 5
      //   365: aload 10
      //   367: astore 5
      //   369: aload_0
      //   370: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   373: invokevirtual 133	java/net/HttpURLConnection:getErrorStream	()Ljava/io/InputStream;
      //   376: astore 6
      //   378: aload 6
      //   380: astore 4
      //   382: goto -163 -> 219
      //   385: astore 11
      //   387: iload_2
      //   388: istore_1
      //   389: aload 9
      //   391: astore 6
      //   393: aload 10
      //   395: astore 5
      //   397: aload 11
      //   399: invokevirtual 136	java/lang/Exception:printStackTrace	()V
      //   402: goto -183 -> 219
      //   405: astore 4
      //   407: aload 6
      //   409: astore 5
      //   411: aload 4
      //   413: invokevirtual 136	java/lang/Exception:printStackTrace	()V
      //   416: iload_1
      //   417: istore_3
      //   418: aload 8
      //   420: astore 7
      //   422: aload 6
      //   424: ifnull -128 -> 296
      //   427: aload 6
      //   429: invokevirtual 118	java/io/InputStream:close	()V
      //   432: iload_1
      //   433: istore_3
      //   434: aload 8
      //   436: astore 7
      //   438: goto -142 -> 296
      //   441: astore 4
      //   443: iload_1
      //   444: istore_3
      //   445: aload 8
      //   447: astore 7
      //   449: goto -153 -> 296
      //   452: iload_2
      //   453: istore_1
      //   454: aload 4
      //   456: astore 6
      //   458: aload 4
      //   460: astore 5
      //   462: aload_0
      //   463: getfield 75	com/mojang/minecraftpe/MainActivity$HurlRunner:conn	Ljava/net/HttpURLConnection;
      //   466: invokevirtual 109	java/net/HttpURLConnection:getContentLength	()I
      //   469: istore_3
      //   470: goto -218 -> 252
      //   473: astore 4
      //   475: aload 5
      //   477: ifnull +8 -> 485
      //   480: aload 5
      //   482: invokevirtual 118	java/io/InputStream:close	()V
      //   485: aload 4
      //   487: athrow
      //   488: astore 5
      //   490: aload 4
      //   492: monitorexit
      //   493: aload 5
      //   495: athrow
      //   496: astore 4
      //   498: iload_2
      //   499: istore_3
      //   500: aload 5
      //   502: astore 7
      //   504: goto -208 -> 296
      //   507: astore 5
      //   509: goto -24 -> 485
      // Local variable table:
      //   start	length	slot	name	signature
      //   0	512	0	this	HurlRunner
      //   20	434	1	i	int
      //   18	481	2	j	int
      //   16	484	3	k	int
      //   405	7	4	localException1	Exception
      //   441	18	4	localException2	Exception
      //   473	18	4	localObject2	Object
      //   496	1	4	localException3	Exception
      //   27	264	5	localObject3	Object
      //   363	1	5	localException4	Exception
      //   367	114	5	localObject4	Object
      //   488	13	5	localObject5	Object
      //   507	1	5	localException5	Exception
      //   23	434	6	localObject6	Object
      //   13	490	7	localObject7	Object
      //   10	436	8	localObject8	Object
      //   1	389	9	localObject9	Object
      //   4	390	10	localObject10	Object
      //   385	13	11	localException6	Exception
      // Exception table:
      //   from	to	target	type
      //   190	198	363	java/lang/Exception
      //   204	213	363	java/lang/Exception
      //   369	378	385	java/lang/Exception
      //   29	44	405	java/lang/Exception
      //   54	68	405	java/lang/Exception
      //   78	89	405	java/lang/Exception
      //   99	112	405	java/lang/Exception
      //   122	133	405	java/lang/Exception
      //   143	151	405	java/lang/Exception
      //   161	169	405	java/lang/Exception
      //   179	186	405	java/lang/Exception
      //   238	248	405	java/lang/Exception
      //   262	270	405	java/lang/Exception
      //   397	402	405	java/lang/Exception
      //   462	470	405	java/lang/Exception
      //   427	432	441	java/lang/Exception
      //   29	44	473	finally
      //   54	68	473	finally
      //   78	89	473	finally
      //   99	112	473	finally
      //   122	133	473	finally
      //   143	151	473	finally
      //   161	169	473	finally
      //   179	186	473	finally
      //   190	198	473	finally
      //   204	213	473	finally
      //   238	248	473	finally
      //   262	270	473	finally
      //   369	378	473	finally
      //   397	402	473	finally
      //   411	416	473	finally
      //   462	470	473	finally
      //   338	362	488	finally
      //   490	493	488	finally
      //   285	290	496	java/lang/Exception
      //   480	485	507	java/lang/Exception
    }
  }
  
  private class LoginWebViewClient
    extends WebViewClient
  {
    boolean hasFiredLaunchEvent = false;
    
    private LoginWebViewClient() {}
    
    public void onPageStarted(WebView paramWebView, String paramString, Bitmap paramBitmap)
    {
      paramBitmap = Uri.parse(paramString);
      paramString = MainActivity.this.getRealmsRedirectInfo().accountUrl;
      paramWebView = paramString;
      if (paramString == null) {
        paramWebView = "account.mojang.com";
      }
      if ((paramBitmap.getHost().equals(paramWebView)) && (paramBitmap.getPath().equals("/m/launch")) && (!this.hasFiredLaunchEvent))
      {
        MainActivity.this.loginLaunchCallback(paramBitmap);
        this.hasFiredLaunchEvent = true;
      }
    }
    
    public void onReceivedSslError(WebView paramWebView, SslErrorHandler paramSslErrorHandler, SslError paramSslError)
    {
      if (MainActivity.this.isRedirectingRealms())
      {
        paramSslErrorHandler.proceed();
        return;
      }
      super.onReceivedSslError(paramWebView, paramSslErrorHandler, paramSslError);
    }
    
    public boolean shouldOverrideUrlLoading(WebView paramWebView, String paramString)
    {
      Uri localUri = Uri.parse(paramString);
      String str2 = MainActivity.this.getRealmsRedirectInfo().accountUrl;
      String str1 = str2;
      if (str2 == null) {
        str1 = "account.mojang.com";
      }
      if (localUri.getHost().equals(str1))
      {
        if (localUri.getPath().equals("/m/launch"))
        {
          MainActivity.this.loginLaunchCallback(localUri);
          this.hasFiredLaunchEvent = true;
          return true;
        }
        paramWebView.loadUrl(paramString);
        return true;
      }
      return false;
    }
  }
  
  private class PopupTextWatcher
    implements TextWatcher, TextView.OnEditorActionListener
  {
    private PopupTextWatcher() {}
    
    public void afterTextChanged(Editable paramEditable)
    {
      MainActivity.this.nativeSetTextboxText(paramEditable.toString());
      if ((MainActivity.this.isCommandHistoryEnabled()) && (MainActivity.this.commandHistoryIndex >= 0) && (MainActivity.this.commandHistoryIndex < MainActivity.this.commandHistoryList.size())) {
        MainActivity.this.commandHistoryList.set(MainActivity.this.commandHistoryIndex, paramEditable.toString());
      }
    }
    
    public void beforeTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
    
    public boolean onEditorAction(TextView paramTextView, int paramInt, KeyEvent paramKeyEvent)
    {
      if (MainActivity.this.hiddenTextDismissAfterOneLine) {
        MainActivity.this.hiddenTextWindow.dismiss();
      }
      for (;;)
      {
        return true;
        MainActivity.this.nativeReturnKeyPressed();
      }
    }
    
    public void onTextChanged(CharSequence paramCharSequence, int paramInt1, int paramInt2, int paramInt3) {}
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\com\mojang\minecraftpe\MainActivity.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */