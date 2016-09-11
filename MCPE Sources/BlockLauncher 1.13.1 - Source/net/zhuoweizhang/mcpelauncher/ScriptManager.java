package net.zhuoweizhang.mcpelauncher;

import android.app.Instrumentation;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Environment;
import android.util.Log;
import android.view.KeyEvent;
import android.widget.PopupWindow;
import com.mojang.minecraftpe.MainActivity;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.io.Reader;
import java.lang.ref.WeakReference;
import java.lang.reflect.AccessibleObject;
import java.lang.reflect.Field;
import java.lang.reflect.Member;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.net.InetAddress;
import java.net.URL;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import net.zhuoweizhang.mcpelauncher.api.modpe.ArmorType;
import net.zhuoweizhang.mcpelauncher.api.modpe.BlockFace;
import net.zhuoweizhang.mcpelauncher.api.modpe.BlockRenderLayer;
import net.zhuoweizhang.mcpelauncher.api.modpe.CallbackName;
import net.zhuoweizhang.mcpelauncher.api.modpe.DimensionId;
import net.zhuoweizhang.mcpelauncher.api.modpe.EnchantType;
import net.zhuoweizhang.mcpelauncher.api.modpe.Enchantment;
import net.zhuoweizhang.mcpelauncher.api.modpe.EntityRenderType;
import net.zhuoweizhang.mcpelauncher.api.modpe.EntityType;
import net.zhuoweizhang.mcpelauncher.api.modpe.MobEffect;
import net.zhuoweizhang.mcpelauncher.api.modpe.ParticleType;
import net.zhuoweizhang.mcpelauncher.api.modpe.RendererManager;
import net.zhuoweizhang.mcpelauncher.api.modpe.RendererManager.NativeRenderer;
import net.zhuoweizhang.mcpelauncher.api.modpe.RendererManager.NativeRendererApi;
import net.zhuoweizhang.mcpelauncher.api.modpe.UseAnimation;
import net.zhuoweizhang.mcpelauncher.patch.PatchUtils;
import net.zhuoweizhang.mcpelauncher.texture.AtlasProvider;
import net.zhuoweizhang.mcpelauncher.texture.ModPkgTexturePack;
import org.json.JSONException;
import org.json.JSONObject;
import org.mozilla.javascript.ContextFactory;
import org.mozilla.javascript.Function;
import org.mozilla.javascript.ImporterTopLevel;
import org.mozilla.javascript.NativeArray;
import org.mozilla.javascript.NativeJSON;
import org.mozilla.javascript.NativeJavaMethod;
import org.mozilla.javascript.NativeJavaMethod.MethodWatcher;
import org.mozilla.javascript.NativeJavaObject;
import org.mozilla.javascript.NativeObject;
import org.mozilla.javascript.Script;
import org.mozilla.javascript.ScriptRuntime;
import org.mozilla.javascript.Scriptable;
import org.mozilla.javascript.ScriptableObject;
import org.mozilla.javascript.Undefined;
import org.mozilla.javascript.annotations.JSFunction;
import org.mozilla.javascript.annotations.JSStaticFunction;

public class ScriptManager
{
  private static final int AMOUNT = 2;
  public static final int ARCH_ARM = 0;
  public static final int ARCH_I386 = 1;
  private static final int AXIS_X = 0;
  private static final int AXIS_Y = 1;
  private static final int AXIS_Z = 2;
  private static final int DAMAGE = 1;
  private static final String ENTITY_KEY_IMMOBILE = "zhuowei.bl.im";
  private static final String ENTITY_KEY_RENDERTYPE = "zhuowei.bl.rt";
  private static final String ENTITY_KEY_SKIN = "zhuowei.bl.s";
  private static final int ITEMID = 0;
  public static int ITEM_ID_COUNT = 0;
  public static final int MAX_NUM_ERRORS = 5;
  public static final String SCRIPTS_DIR = "modscripts";
  public static List<Long> allentities;
  public static List<Long> allplayers;
  public static android.content.Context androidContext;
  private static Class<?>[] constantsClasses;
  private static String currentScreen;
  private static String currentScript;
  public static Set<String> enabledScripts;
  private static NativeArray entityList;
  private static Map<Long, String> entityUUIDMap;
  public static boolean hasLevel;
  private static Instrumentation instrumentation;
  private static ExecutorService instrumentationExecutor;
  public static boolean isRemote;
  public static AtlasProvider itemsMeta;
  private static float lastDestroyProgress;
  private static int lastDestroySide = -1;
  private static int lastDestroyX;
  private static int lastDestroyY;
  private static int lastDestroyZ;
  public static ModPkgTexturePack modPkgTexturePack;
  private static final ModernWrapFactory modernWrapFactory;
  public static float newPlayerPitch;
  public static float newPlayerYaw;
  private static boolean nextTickCallsSetLevel;
  private static JoinServerRequest requestJoinServer;
  private static boolean requestLeaveGame;
  public static int requestLeaveGameCounter;
  private static boolean requestReloadAllScripts;
  public static boolean requestScreenshot;
  private static SelectLevelRequest requestSelectLevel;
  public static boolean requestSelectLevelHasSetScreen;
  private static boolean requestedGraphicsReset;
  private static List<Runnable> runOnMainThreadList;
  public static String screenshotFileName;
  private static AndroidPrintStream scriptErrorStream;
  private static boolean scriptingEnabled;
  private static boolean scriptingInitialized;
  public static List<ScriptState> scripts = new ArrayList();
  public static boolean sensorEnabled;
  private static String serverAddress;
  private static int serverPort;
  public static AtlasProvider terrainMeta;
  private static WorldData worldData;
  private static int worldDataSaveCounter;
  public static String worldDir;
  public static String worldName;
  
  static
  {
    enabledScripts = new HashSet();
    isRemote = false;
    currentScript = "Unknown";
    requestedGraphicsReset = false;
    sensorEnabled = false;
    newPlayerYaw = 0.0F;
    newPlayerPitch = 0.0F;
    requestSelectLevel = null;
    requestLeaveGame = false;
    requestJoinServer = null;
    scriptingEnabled = true;
    scriptingInitialized = false;
    screenshotFileName = "";
    modernWrapFactory = new ModernWrapFactory();
    requestReloadAllScripts = false;
    runOnMainThreadList = new ArrayList();
    allentities = new ArrayList();
    allplayers = new ArrayList();
    serverAddress = null;
    serverPort = 0;
    entityUUIDMap = new HashMap();
    nextTickCallsSetLevel = false;
    hasLevel = false;
    requestLeaveGameCounter = 0;
    requestScreenshot = false;
    requestSelectLevelHasSetScreen = false;
    ITEM_ID_COUNT = 512;
    modPkgTexturePack = new ModPkgTexturePack("resourcepacks/vanilla/");
    worldData = null;
    worldDataSaveCounter = 1;
    scriptErrorStream = null;
    constantsClasses = new Class[] { ChatColor.class, ItemCategory.class, ParticleType.class, EntityType.class, EntityRenderType.class, ArmorType.class, MobEffect.class, DimensionId.class, BlockFace.class, UseAnimation.class, Enchantment.class, EnchantType.class, BlockRenderLayer.class };
    lastDestroyProgress = -1.0F;
    lastDestroyX = 0;
    lastDestroyY = -1;
    lastDestroyZ = 0;
  }
  
  private static void appendApiClassConstants(StringBuilder paramStringBuilder, Class<?> paramClass)
  {
    String str = paramClass.getSimpleName();
    paramClass = paramClass.getFields();
    int j = paramClass.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramClass[i];
      int k = ((Field)localObject).getModifiers();
      if ((!Modifier.isStatic(k)) || (!Modifier.isPublic(k))) {}
      for (;;)
      {
        i += 1;
        break;
        paramStringBuilder.append(str).append(".").append(((Field)localObject).getName()).append(";\n");
      }
    }
    paramStringBuilder.append("\n");
  }
  
  private static void appendApiMethodDescription(StringBuilder paramStringBuilder, Method paramMethod, String paramString)
  {
    if (paramString != null)
    {
      paramStringBuilder.append(paramString);
      paramStringBuilder.append('.');
    }
    paramStringBuilder.append(paramMethod.getName());
    paramStringBuilder.append('(');
    paramMethod = paramMethod.getParameterTypes();
    int i = 0;
    while (i < paramMethod.length)
    {
      paramStringBuilder.append("par");
      paramStringBuilder.append(i + 1);
      paramStringBuilder.append(paramMethod[i].getSimpleName().replaceAll("Native", ""));
      if (i < paramMethod.length - 1) {
        paramStringBuilder.append(", ");
      }
      i += 1;
    }
    paramStringBuilder.append(");\n");
  }
  
  private static void appendApiMethods(StringBuilder paramStringBuilder, Class<?> paramClass, String paramString)
  {
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Method localMethod = paramClass[i];
      if ((localMethod.getAnnotation(JSFunction.class) != null) || (localMethod.getAnnotation(JSStaticFunction.class) != null)) {
        appendApiMethodDescription(paramStringBuilder, localMethod, paramString);
      }
      i += 1;
    }
    paramStringBuilder.append("\n");
  }
  
  private static void appendCallbacks(StringBuilder paramStringBuilder)
  {
    Method[] arrayOfMethod = ScriptManager.class.getMethods();
    int j = arrayOfMethod.length;
    int i = 0;
    if (i < j)
    {
      CallbackName localCallbackName = (CallbackName)arrayOfMethod[i].getAnnotation(CallbackName.class);
      if (localCallbackName == null) {}
      for (;;)
      {
        i += 1;
        break;
        if (localCallbackName.prevent()) {
          paramStringBuilder.append("// can use preventDefault()\n");
        }
        paramStringBuilder.append("function ").append(localCallbackName.name()).append("(").append(Utils.joinArray(localCallbackName.args(), ", ")).append(")\n");
      }
    }
    paramStringBuilder.append("\n");
  }
  
  @CallbackName(args={"attacker", "victim"}, name="attackHook", prevent=true)
  public static void attackCallback(long paramLong1, long paramLong2)
  {
    callScriptMethod("attackHook", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2) });
  }
  
  public static void blockEventCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
  {
    callScriptMethod("blockEventHook", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Integer.valueOf(paramInt5) });
  }
  
  public static void callScriptMethod(String paramString, Object... paramVarArgs)
  {
    if (!scriptingEnabled) {}
    for (;;)
    {
      return;
      org.mozilla.javascript.Context localContext = org.mozilla.javascript.Context.enter();
      setupContext(localContext);
      Iterator localIterator = scripts.iterator();
      while (localIterator.hasNext())
      {
        ScriptState localScriptState = (ScriptState)localIterator.next();
        if (localScriptState.errors < 5)
        {
          currentScript = localScriptState.name;
          Scriptable localScriptable = localScriptState.scope;
          Object localObject = localScriptable.get(paramString, localScriptable);
          if ((localObject != null) && ((localObject instanceof Function))) {
            try
            {
              ((Function)localObject).call(localContext, localScriptable, localScriptable, paramVarArgs);
            }
            catch (Exception localException)
            {
              dumpScriptError(localException);
              reportScriptError(localScriptState, localException);
            }
          }
        }
      }
    }
  }
  
  @CallbackName(args={"str"}, name="chatHook", prevent=true)
  public static void chatCallback(String paramString)
  {
    int i = 1;
    if (isRemote) {
      nameAndShame(paramString);
    }
    if ((paramString == null) || (paramString.length() < 1)) {}
    label136:
    for (;;)
    {
      return;
      callScriptMethod("chatHook", new Object[] { paramString });
      if (paramString.charAt(0) == '/')
      {
        callScriptMethod("procCmd", new Object[] { paramString.substring(1) });
        paramString = paramString.substring(1).split(" ");
        if ((paramString.length > 0) && (nativeIsValidCommand(paramString[0]))) {}
        for (;;)
        {
          if ((isRemote) || (i != 0)) {
            break label136;
          }
          nativePreventDefault();
          if (MainActivity.currentMainActivity == null) {
            break;
          }
          paramString = (MainActivity)MainActivity.currentMainActivity.get();
          if (paramString == null) {
            break;
          }
          paramString.updateTextboxText("");
          return;
          i = 0;
        }
      }
    }
  }
  
  public static ScriptableObject classConstantsToJSObject(Class<?> paramClass)
  {
    NativeObject localNativeObject = new NativeObject();
    paramClass = paramClass.getFields();
    int j = paramClass.length;
    int i = 0;
    if (i < j)
    {
      Object localObject = paramClass[i];
      int k = ((Field)localObject).getModifiers();
      if ((!Modifier.isStatic(k)) || (!Modifier.isPublic(k))) {}
      for (;;)
      {
        i += 1;
        break;
        try
        {
          localNativeObject.putConst(((Field)localObject).getName(), localNativeObject, ((Field)localObject).get(null));
        }
        catch (Exception localException)
        {
          localException.printStackTrace();
        }
      }
    }
    return localNativeObject;
  }
  
  private static void clearTextureOverride(String paramString)
  {
    paramString = getTextureOverrideFile(paramString);
    if ((paramString != null) && (paramString.exists())) {
      paramString.delete();
    }
    requestedGraphicsReset = true;
  }
  
  public static void clearTextureOverrides()
  {
    if (androidContext == null) {
      return;
    }
    Utils.clearDirectory(new File(androidContext.getExternalFilesDir(null), "textures"));
    requestedGraphicsReset = true;
  }
  
  @CallbackName(args={"x", "y", "z", "side", "progress"}, name="continueDestroyBlock", prevent=true)
  public static void continueDestroyBlockCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4, float paramFloat)
  {
    if ((paramInt1 == lastDestroyX) && (paramInt2 == lastDestroyY) && (paramInt3 == lastDestroyZ) && (paramInt4 == lastDestroySide)) {}
    for (int i = 1;; i = 0)
    {
      if ((paramFloat == 0.0F) && ((paramFloat != lastDestroyProgress) || (i == 0))) {
        callScriptMethod("startDestroyBlock", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4) });
      }
      lastDestroyProgress = paramFloat;
      lastDestroyX = paramInt1;
      lastDestroyY = paramInt2;
      lastDestroyZ = paramInt3;
      lastDestroySide = paramInt4;
      callScriptMethod("continueDestroyBlock", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Float.valueOf(paramFloat) });
      return;
    }
  }
  
  private static void debugDumpItems()
    throws IOException
  {
    PrintWriter localPrintWriter = new PrintWriter(new File(Environment.getExternalStorageDirectory(), "/items.csv"));
    float[] arrayOfFloat = new float[6];
    int[][] arrayOfInt = new int[9][];
    arrayOfInt[0] = { 1, 1, 6 };
    arrayOfInt[1] = { 12, 1, 1 };
    arrayOfInt[2] = { 38, 0, 8 };
    arrayOfInt[3] = { 159, 0, 15 };
    arrayOfInt[4] = { 171, 0, 15 };
    arrayOfInt[5] = { 175, 0, 5 };
    arrayOfInt[6] = { 349, 1, 3 };
    arrayOfInt[7] = { 350, 1, 1 };
    arrayOfInt[8] = { 383, 10, 63 };
    int i = 0;
    Object localObject;
    String str1;
    if (i < ITEM_ID_COUNT)
    {
      localObject = nativeGetItemName(i, 0, true);
      if (localObject == null) {}
      for (;;)
      {
        i += 1;
        break;
        nativeGetTextureCoordinatesForItem(i, 0, arrayOfFloat);
        str1 = Arrays.toString(arrayOfFloat).replace("[", "").replace("]", "").replace(",", "|");
        localPrintWriter.println(i + "," + (String)localObject + "," + str1);
      }
    }
    int k = arrayOfInt.length;
    i = 0;
    while (i < k)
    {
      localObject = arrayOfInt[i];
      int m = localObject[0];
      int j = localObject[1];
      if (j <= localObject[2])
      {
        str1 = nativeGetItemName(m, j, true);
        if (str1 == null) {}
        for (;;)
        {
          j += 1;
          break;
          nativeGetTextureCoordinatesForItem(m, j, arrayOfFloat);
          String str2 = Arrays.toString(arrayOfFloat).replace("[", "").replace("]", "").replace(",", "|");
          localPrintWriter.println(m + ":" + j + "," + str1 + "," + str2);
        }
      }
      i += 1;
    }
    localPrintWriter.close();
  }
  
  public static void destroy()
  {
    scriptingInitialized = false;
    androidContext = null;
    scripts.clear();
    runOnMainThreadList.clear();
  }
  
  @CallbackName(args={"x", "y", "z", "side"}, name="destroyBlock", prevent=true)
  public static void destroyBlockCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    callScriptMethod("destroyBlock", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4) });
  }
  
  @CallbackName(args={"projectile", "targetEntity"}, name="projectileHitEntityHook")
  public static void dummyThrowableHitEntityCallback() {}
  
  private static void dumpScriptError(Throwable paramThrowable)
  {
    if (scriptErrorStream == null) {
      scriptErrorStream = new AndroidPrintStream(6, "ScriptError");
    }
    paramThrowable.printStackTrace(scriptErrorStream);
  }
  
  @CallbackName(args={"hearts", "saturationRatio"}, name="eatHook")
  public static void eatCallback(int paramInt, float paramFloat)
  {
    callScriptMethod("eatHook", new Object[] { Integer.valueOf(paramInt), Float.valueOf(paramFloat) });
  }
  
  @CallbackName(args={"entity"}, name="entityAddedHook")
  public static void entityAddedCallback(long paramLong)
  {
    System.out.println("Entity added: " + paramLong + " entity type: " + NativeEntityApi.getEntityTypeId(Long.valueOf(paramLong)));
    Object localObject = NativeEntityApi.getExtraData(Long.valueOf(paramLong), "zhuowei.bl.rt");
    if ((localObject != null) && (((String)localObject).length() != 0))
    {
      localObject = RendererManager.NativeRendererApi.getByName((String)localObject);
      if (localObject != null) {
        NativeEntityApi.setRenderTypeImpl(Long.valueOf(paramLong), ((RendererManager.NativeRenderer)localObject).getRenderType());
      }
    }
    localObject = NativeEntityApi.getExtraData(Long.valueOf(paramLong), "zhuowei.bl.s");
    if ((localObject != null) && (((String)localObject).length() != 0))
    {
      System.out.println("Custom skin: " + (String)localObject);
      NativeEntityApi.setMobSkinImpl(Long.valueOf(paramLong), (String)localObject, false);
    }
    String str = NativeEntityApi.getExtraData(Long.valueOf(paramLong), "zhuowei.bl.im");
    if ((str != null) && (str.length() != 0))
    {
      System.out.println("Immobile: " + (String)localObject);
      NativeEntityApi.setImmobileImpl(Long.valueOf(paramLong), str.equals("1"));
    }
    if (NativePlayerApi.isPlayer(Long.valueOf(paramLong))) {
      playerAddedHandler(paramLong);
    }
    allentities.add(Long.valueOf(paramLong));
    callScriptMethod("entityAddedHook", new Object[] { Long.valueOf(paramLong) });
  }
  
  @CallbackName(args={"attacker", "victim", "halfhearts"}, name="entityHurtHook", prevent=true)
  public static void entityHurtCallback(long paramLong1, long paramLong2, int paramInt)
  {
    callScriptMethod("entityHurtHook", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2), Integer.valueOf(paramInt) });
  }
  
  @CallbackName(args={"entity"}, name="entityRemovedHook")
  public static void entityRemovedCallback(long paramLong)
  {
    if (NativePlayerApi.isPlayer(Long.valueOf(paramLong))) {
      playerRemovedHandler(paramLong);
    }
    int i = allentities.indexOf(Long.valueOf(paramLong));
    if (i >= 0) {
      allentities.remove(i);
    }
    callScriptMethod("entityRemovedHook", new Object[] { Long.valueOf(paramLong) });
  }
  
  public static int[] expandColorsArray(Scriptable paramScriptable)
  {
    int j = ((Number)ScriptableObject.getProperty(paramScriptable, "length")).intValue();
    int[] arrayOfInt = new int[16];
    int i = 0;
    if (i < arrayOfInt.length)
    {
      if (i < j) {
        arrayOfInt[i] = ((int)((Number)ScriptableObject.getProperty(paramScriptable, i)).longValue());
      }
      for (;;)
      {
        i += 1;
        break;
        arrayOfInt[i] = ((int)((Number)ScriptableObject.getProperty(paramScriptable, 0)).longValue());
      }
    }
    Log.i("BlockLauncher", Arrays.toString(arrayOfInt));
    return arrayOfInt;
  }
  
  private static int[] expandShapelessRecipe(Scriptable paramScriptable)
  {
    int i = ((Number)ScriptableObject.getProperty(paramScriptable, "length")).intValue();
    int[] arrayOfInt;
    if ((ScriptableObject.getProperty(paramScriptable, 0) instanceof Number))
    {
      if (i % 3 != 0) {
        throw new IllegalArgumentException("Array length must be multiple of 3 (this was changed in 1.6.8): [itemid, itemCount, itemdamage, ...]");
      }
      arrayOfInt = new int[i];
      i = 0;
      while (i < arrayOfInt.length)
      {
        arrayOfInt[i] = ((Number)ScriptableObject.getProperty(paramScriptable, i)).intValue();
        i += 1;
      }
    }
    throw new IllegalArgumentException("Method takes in an array of [itemid, itemCount, itemdamage, ...]");
    return arrayOfInt;
  }
  
  public static TextureRequests expandTexturesArray(Object paramObject)
  {
    int[] arrayOfInt = new int[96];
    String[] arrayOfString = new String[96];
    TextureRequests localTextureRequests = new TextureRequests(null);
    localTextureRequests.coords = arrayOfInt;
    localTextureRequests.names = arrayOfString;
    if ((paramObject instanceof String))
    {
      Arrays.fill(arrayOfString, (String)paramObject);
      return localTextureRequests;
    }
    Scriptable localScriptable = (Scriptable)paramObject;
    int m = ((Number)ScriptableObject.getProperty(localScriptable, "length")).intValue();
    if (m % 6 == 0) {}
    for (int i = 6;; i = 1)
    {
      paramObject = ScriptableObject.getProperty(localScriptable, 0);
      if (((m != 1) && (m != 2)) || (!(paramObject instanceof String))) {
        break label155;
      }
      Arrays.fill(arrayOfString, (String)paramObject);
      if (m != 2) {
        break;
      }
      Arrays.fill(arrayOfInt, ((Number)ScriptableObject.getProperty(localScriptable, 1)).intValue());
      return localTextureRequests;
    }
    label155:
    int j = 0;
    label157:
    if (j < arrayOfInt.length) {
      if (j >= m) {
        break label241;
      }
    }
    label241:
    for (paramObject = ScriptableObject.getProperty(localScriptable, j);; paramObject = ScriptableObject.getProperty(localScriptable, j % i))
    {
      paramObject = (Scriptable)paramObject;
      String str = (String)ScriptableObject.getProperty((Scriptable)paramObject, 0);
      int k = 0;
      if (((Number)ScriptableObject.getProperty((Scriptable)paramObject, "length")).intValue() > 1) {
        k = ((Number)ScriptableObject.getProperty((Scriptable)paramObject, 1)).intValue();
      }
      arrayOfInt[j] = k;
      arrayOfString[j] = str;
      j += 1;
      break label157;
      break;
    }
  }
  
  @CallbackName(args={"entity", "x", "y", "z", "power", "onFire"}, name="explodeHook", prevent=true)
  public static void explodeCallback(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean)
  {
    callScriptMethod("explodeHook", new Object[] { Long.valueOf(paramLong), Float.valueOf(paramFloat1), Float.valueOf(paramFloat2), Float.valueOf(paramFloat3), Float.valueOf(paramFloat4), Boolean.valueOf(paramBoolean) });
  }
  
  public static void frameCallback()
  {
    if (requestReloadAllScripts) {
      if (!nativeIsValidItem(256)) {
        nativeRequestFrameCallback();
      }
    }
    do
    {
      return;
      requestReloadAllScripts = false;
      try
      {
        if (!new File("/sdcard/mcpelauncher_do_not_create_placeholder_blocks").exists()) {
          nativeDefinePlaceholderBlocks();
        }
        MobEffect.initIds();
        loadEnabledScripts();
      }
      catch (Exception localException)
      {
        for (;;)
        {
          dumpScriptError(localException);
          reportScriptError(null, localException);
        }
        nativeSelectLevel(requestSelectLevel.dir, requestSelectLevel.name);
        requestSelectLevel = null;
        requestSelectLevelHasSetScreen = false;
        continue;
      }
      if ((requestSelectLevel != null) && (!requestLeaveGame))
      {
        if (requestSelectLevelHasSetScreen) {
          break;
        }
        nativeShowProgressScreen();
        requestSelectLevelHasSetScreen = true;
        nativeRequestFrameCallback();
      }
    } while (!requestScreenshot);
    ScreenshotHelper.takeScreenshot(screenshotFileName);
    requestScreenshot = false;
  }
  
  public static String getAllApiMethodsDescriptions()
  {
    StringBuilder localStringBuilder = new StringBuilder();
    appendApiMethods(localStringBuilder, BlockHostObject.class, null);
    appendApiMethods(localStringBuilder, NativeModPEApi.class, "ModPE");
    appendApiMethods(localStringBuilder, NativeLevelApi.class, "Level");
    appendApiMethods(localStringBuilder, NativePlayerApi.class, "Player");
    appendApiMethods(localStringBuilder, NativeEntityApi.class, "Entity");
    appendApiMethods(localStringBuilder, NativeItemApi.class, "Item");
    appendApiMethods(localStringBuilder, NativeBlockApi.class, "Block");
    appendApiMethods(localStringBuilder, NativeServerApi.class, "Server");
    appendCallbacks(localStringBuilder);
    Class[] arrayOfClass = constantsClasses;
    int j = arrayOfClass.length;
    int i = 0;
    while (i < j)
    {
      appendApiClassConstants(localStringBuilder, arrayOfClass[i]);
      i += 1;
    }
    return localStringBuilder.toString();
  }
  
  private static String[] getAllJsFunctions(Class<? extends ScriptableObject> paramClass)
  {
    ArrayList localArrayList = new ArrayList();
    paramClass = paramClass.getMethods();
    int j = paramClass.length;
    int i = 0;
    while (i < j)
    {
      Object localObject = paramClass[i];
      if (((Method)localObject).getAnnotation(JSFunction.class) != null) {
        localArrayList.add(((Method)localObject).getName());
      }
      i += 1;
    }
    return (String[])localArrayList.toArray(PatchManager.blankArray);
  }
  
  private static String getCapeURL(String paramString)
  {
    return "http://blskins.ablecuboid.com/blskins/capes/" + paramString + ".png";
  }
  
  public static Set<String> getEnabledScripts()
  {
    return enabledScripts;
  }
  
  private static long getEntityId(Object paramObject)
  {
    if (paramObject == null) {
      return -1L;
    }
    if ((paramObject instanceof NativeJavaObject)) {
      return ((Long)((NativeJavaObject)paramObject).unwrap()).longValue();
    }
    if ((paramObject instanceof Number)) {
      return ((Number)paramObject).longValue();
    }
    if ((paramObject instanceof Undefined)) {
      return 0L;
    }
    throw new RuntimeException("Not an entity: " + paramObject + " (" + paramObject.getClass().toString() + ")");
  }
  
  private static String getEntityUUID(long paramLong)
  {
    return Long.toString(paramLong);
  }
  
  public static File getOriginalFile(File paramFile)
  {
    paramFile = getOriginalLocations().optString(paramFile.getName(), null);
    if (paramFile == null) {
      paramFile = null;
    }
    File localFile;
    do
    {
      return paramFile;
      localFile = new File(paramFile);
      paramFile = localFile;
    } while (localFile.exists());
    return null;
  }
  
  public static JSONObject getOriginalLocations()
  {
    try
    {
      JSONObject localJSONObject = new JSONObject(Utils.getPrefs(1).getString("scriptOriginalLocations", "{}"));
      return localJSONObject;
    }
    catch (JSONException localJSONException) {}
    return new JSONObject();
  }
  
  private static String getPlayerNameFromConfs()
  {
    try
    {
      Object localObject2 = new File(Environment.getExternalStorageDirectory(), "games/com.mojang/minecraftpe/options.txt");
      if (!((File)localObject2).exists()) {
        return "Steve";
      }
      Object localObject1 = new byte[(int)((File)localObject2).length()];
      localObject2 = new FileInputStream((File)localObject2);
      ((FileInputStream)localObject2).read((byte[])localObject1);
      ((FileInputStream)localObject2).close();
      localObject1 = new String((byte[])localObject1, "UTF-8").split("\n");
      int j = localObject1.length;
      int i = 0;
      while (i < j)
      {
        localObject2 = localObject1[i];
        if (((String)localObject2).startsWith("mp_username:"))
        {
          localObject1 = ((String)localObject2).substring("mp_username:".length());
          return (String)localObject1;
        }
        i += 1;
      }
      return "Steve";
    }
    catch (Exception localException)
    {
      localException.printStackTrace();
    }
  }
  
  public static File getScriptFile(String paramString)
  {
    return new File(androidContext.getDir("modscripts", 0), paramString);
  }
  
  private static String getSkinURL(String paramString)
  {
    return "http://blskins.ablecuboid.com/blskins/" + paramString + ".png";
  }
  
  public static byte[] getSoundBytes(String paramString)
  {
    if (MainActivity.currentMainActivity != null)
    {
      MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
      if (localMainActivity != null) {
        return localMainActivity.getFileDataBytes(paramString.substring("file:///android_asset/".length()));
      }
    }
    return null;
  }
  
  public static InputStream getSoundInputStream(String paramString, long[] paramArrayOfLong)
  {
    System.out.println("Get sound input stream");
    if (MainActivity.currentMainActivity != null)
    {
      MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
      if (localMainActivity != null) {
        return localMainActivity.getInputStreamForAsset(paramString.substring("file:///android_asset/".length()), paramArrayOfLong);
      }
    }
    return null;
  }
  
  public static File getTextureOverrideFile(String paramString)
  {
    if (androidContext == null) {
      return null;
    }
    return new File(new File(androidContext.getExternalFilesDir(null), "textures"), paramString.replace("..", ""));
  }
  
  protected static ModernWrapFactory getWrapFactory()
  {
    return modernWrapFactory;
  }
  
  @CallbackName(args={"str"}, name="serverMessageReceiveHook", prevent=true)
  public static void handleChatPacketCallback(String paramString)
  {
    if ((paramString == null) || (paramString.length() < 1)) {
      return;
    }
    callScriptMethod("serverMessageReceiveHook", new Object[] { paramString });
  }
  
  @CallbackName(args={"str", "sender"}, name="chatReceiveHook", prevent=true)
  private static void handleMessagePacketCallback(String paramString1, String paramString2)
  {
    if ((paramString2 == null) || (paramString2.length() < 1)) {
      return;
    }
    if ((paramString1.length() == 0) && (paramString2.equals("Åò0BlockLauncher, enable scripts")))
    {
      scriptingEnabled = true;
      nativePreventDefault();
      if (MainActivity.currentMainActivity != null)
      {
        MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
        if (localMainActivity != null) {
          localMainActivity.scriptPrintCallback("Scripts have been re-enabled", "");
        }
      }
    }
    callScriptMethod("chatReceiveHook", new Object[] { paramString2, paramString1 });
  }
  
  public static void init(android.content.Context paramContext)
    throws IOException
  {
    scriptingInitialized = true;
    int i = 0;
    try
    {
      int j = paramContext.getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionCode;
      i = j;
    }
    catch (PackageManager.NameNotFoundException localNameNotFoundException)
    {
      for (;;) {}
    }
    if (MinecraftVersion.isAmazon()) {
      i = 43690;
    }
    nativeSetupHooks(i);
    ITEM_ID_COUNT = nativeGetItemIdCount();
    scripts.clear();
    entityList = new NativeArray(0L);
    androidContext = paramContext.getApplicationContext();
    ContextFactory.initGlobal(new BlockContextFactory());
    NativeJavaMethod.setMethodWatcher(new MyMethodWatcher(null));
    requestReloadAllScripts = true;
    nativeRequestFrameCallback();
    prepareEnabledScripts();
  }
  
  public static void initJustLoadedScript(org.mozilla.javascript.Context paramContext, Script paramScript, String paramString)
  {
    ScriptableObject localScriptableObject = paramContext.initStandardObjects(new BlockHostObject(null), false);
    paramString = new ScriptState(paramScript, localScriptableObject, paramString);
    Object localObject = getAllJsFunctions(BlockHostObject.class);
    ((ScriptableObject)localScriptableObject).defineFunctionProperties((String[])localObject, BlockHostObject.class, 2);
    try
    {
      ScriptableObject.defineClass(localScriptableObject, NativePlayerApi.class);
      ScriptableObject.defineClass(localScriptableObject, NativeLevelApi.class);
      ScriptableObject.defineClass(localScriptableObject, NativeEntityApi.class);
      ScriptableObject.defineClass(localScriptableObject, NativeModPEApi.class);
      ScriptableObject.defineClass(localScriptableObject, NativeItemApi.class);
      ScriptableObject.defineClass(localScriptableObject, NativeBlockApi.class);
      ScriptableObject.defineClass(localScriptableObject, NativeServerApi.class);
      RendererManager.defineClasses(localScriptableObject);
      localObject = constantsClasses;
      int j = localObject.length;
      int i = 0;
      while (i < j)
      {
        Class localClass = localObject[i];
        ScriptableObject.putProperty(localScriptableObject, localClass.getSimpleName(), classConstantsToJSObject(localClass));
        i += 1;
      }
      return;
    }
    catch (Exception localException)
    {
      dumpScriptError(localException);
      reportScriptError(paramString, localException);
      paramScript.exec(paramContext, localScriptableObject);
      scripts.add(paramString);
    }
  }
  
  private static void injectKeyEvent(final int paramInt1, int paramInt2)
  {
    if (instrumentation == null)
    {
      instrumentation = new Instrumentation();
      instrumentationExecutor = Executors.newSingleThreadExecutor();
    }
    instrumentationExecutor.execute(new Runnable()
    {
      public void run()
      {
        Instrumentation localInstrumentation = ScriptManager.instrumentation;
        if (this.val$pressed != 0) {}
        for (int i = 0;; i = 1)
        {
          localInstrumentation.sendKeySync(new KeyEvent(i, paramInt1));
          return;
        }
      }
    });
  }
  
  private static boolean invalidTexName(String paramString)
  {
    return (paramString == null) || (paramString.equals("undefined")) || (paramString.equals("null"));
  }
  
  private static boolean isClassGenMode()
  {
    return false;
  }
  
  public static boolean isEnabled(File paramFile)
  {
    return isEnabled(paramFile.getName());
  }
  
  private static boolean isEnabled(String paramString)
  {
    return enabledScripts.contains(paramString);
  }
  
  private static boolean isLocalAddress(String paramString)
  {
    boolean bool1 = false;
    try
    {
      InetAddress localInetAddress = InetAddress.getByName(paramString);
      Log.i("BlockLauncher", paramString);
      if ((!localInetAddress.isLoopbackAddress()) && (!localInetAddress.isLinkLocalAddress()))
      {
        boolean bool2 = localInetAddress.isSiteLocalAddress();
        if (!bool2) {}
      }
      else
      {
        bool1 = true;
      }
      return bool1;
    }
    catch (UnknownHostException paramString)
    {
      paramString.printStackTrace();
    }
    return false;
  }
  
  private static boolean isPackagedScript(File paramFile)
  {
    return isPackagedScript(paramFile.getName());
  }
  
  private static boolean isPackagedScript(String paramString)
  {
    return paramString.toLowerCase().endsWith(".modpkg");
  }
  
  protected static boolean isScriptingEnabled()
  {
    return scriptingEnabled;
  }
  
  private static boolean isSkinNameNormalized()
  {
    return true;
  }
  
  private static boolean isValidStringParameter(String paramString)
  {
    return !invalidTexName(paramString);
  }
  
  @CallbackName(name="leaveGame")
  private static void leaveGameCallback(boolean paramBoolean)
  {
    isRemote = false;
    scriptingEnabled = true;
    hasLevel = false;
    if (scriptingInitialized) {
      callScriptMethod("leaveGame", new Object[0]);
    }
    if (MainActivity.currentMainActivity != null)
    {
      MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
      if (localMainActivity != null) {
        localMainActivity.leaveGameCallback();
      }
    }
    if (worldData != null) {}
    try
    {
      worldData.save();
      worldData = null;
      serverAddress = null;
      serverPort = 0;
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  public static void levelEventCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6)
  {
    callScriptMethod("levelEventHook", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Integer.valueOf(paramInt5), Integer.valueOf(paramInt6) });
  }
  
  /* Error */
  protected static void loadAddonScripts()
  {
    // Byte code:
    //   0: getstatic 613	com/mojang/minecraftpe/MainActivity:currentMainActivity	Ljava/lang/ref/WeakReference;
    //   3: invokevirtual 617	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   6: checkcast 609	com/mojang/minecraftpe/MainActivity
    //   9: astore_3
    //   10: aload_3
    //   11: ifnull +10 -> 21
    //   14: aload_3
    //   15: getfield 1309	com/mojang/minecraftpe/MainActivity:addonOverrideTexturePackInstance	Lnet/zhuoweizhang/mcpelauncher/AddonOverrideTexturePack;
    //   18: ifnonnull +4 -> 22
    //   21: return
    //   22: aload_3
    //   23: getfield 1309	com/mojang/minecraftpe/MainActivity:addonOverrideTexturePackInstance	Lnet/zhuoweizhang/mcpelauncher/AddonOverrideTexturePack;
    //   26: invokevirtual 1315	net/zhuoweizhang/mcpelauncher/AddonOverrideTexturePack:getZipsByPackage	()Ljava/util/Map;
    //   29: invokeinterface 1320 1 0
    //   34: invokeinterface 1321 1 0
    //   39: astore 4
    //   41: aload 4
    //   43: invokeinterface 542 1 0
    //   48: ifeq -27 -> 21
    //   51: aload 4
    //   53: invokeinterface 546 1 0
    //   58: checkcast 1323	java/util/Map$Entry
    //   61: astore 5
    //   63: aconst_null
    //   64: astore_1
    //   65: aconst_null
    //   66: astore_2
    //   67: aload_1
    //   68: astore_0
    //   69: aload 5
    //   71: invokeinterface 1326 1 0
    //   76: checkcast 1328	java/util/zip/ZipFile
    //   79: astore 7
    //   81: aload_1
    //   82: astore_0
    //   83: aload 7
    //   85: ldc_w 1330
    //   88: invokevirtual 1334	java/util/zip/ZipFile:getEntry	(Ljava/lang/String;)Ljava/util/zip/ZipEntry;
    //   91: astore 6
    //   93: aload 6
    //   95: ifnonnull +23 -> 118
    //   98: iconst_0
    //   99: ifeq -58 -> 41
    //   102: new 1336	java/lang/NullPointerException
    //   105: dup
    //   106: invokespecial 1337	java/lang/NullPointerException:<init>	()V
    //   109: athrow
    //   110: astore_0
    //   111: aload_0
    //   112: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   115: goto -74 -> 41
    //   118: aload_1
    //   119: astore_0
    //   120: new 1339	java/io/InputStreamReader
    //   123: dup
    //   124: aload 7
    //   126: aload 6
    //   128: invokevirtual 1343	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   131: invokespecial 1346	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   134: astore_1
    //   135: aload_1
    //   136: new 403	java/lang/StringBuilder
    //   139: dup
    //   140: invokespecial 728	java/lang/StringBuilder:<init>	()V
    //   143: ldc_w 1348
    //   146: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   149: aload 5
    //   151: invokeinterface 1351 1 0
    //   156: checkcast 439	java/lang/String
    //   159: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   162: ldc_w 1353
    //   165: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   168: invokevirtual 730	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   171: invokestatic 1357	net/zhuoweizhang/mcpelauncher/ScriptManager:loadScript	(Ljava/io/Reader;Ljava/lang/String;)V
    //   174: aload_1
    //   175: ifnull +86 -> 261
    //   178: aload_1
    //   179: invokevirtual 1360	java/io/Reader:close	()V
    //   182: goto -141 -> 41
    //   185: astore_0
    //   186: aload_0
    //   187: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   190: goto -149 -> 41
    //   193: astore_0
    //   194: aload_2
    //   195: astore_1
    //   196: aload_0
    //   197: astore_2
    //   198: aload_1
    //   199: astore_0
    //   200: aload_2
    //   201: invokestatic 571	net/zhuoweizhang/mcpelauncher/ScriptManager:dumpScriptError	(Ljava/lang/Throwable;)V
    //   204: aload_1
    //   205: astore_0
    //   206: aload_3
    //   207: aload_2
    //   208: invokevirtual 1363	com/mojang/minecraftpe/MainActivity:reportError	(Ljava/lang/Throwable;)V
    //   211: aload_1
    //   212: ifnull -171 -> 41
    //   215: aload_1
    //   216: invokevirtual 1360	java/io/Reader:close	()V
    //   219: goto -178 -> 41
    //   222: astore_0
    //   223: aload_0
    //   224: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   227: goto -186 -> 41
    //   230: astore_1
    //   231: aload_0
    //   232: ifnull +7 -> 239
    //   235: aload_0
    //   236: invokevirtual 1360	java/io/Reader:close	()V
    //   239: aload_1
    //   240: athrow
    //   241: astore_0
    //   242: aload_0
    //   243: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   246: goto -7 -> 239
    //   249: astore_2
    //   250: aload_1
    //   251: astore_0
    //   252: aload_2
    //   253: astore_1
    //   254: goto -23 -> 231
    //   257: astore_2
    //   258: goto -60 -> 198
    //   261: goto -220 -> 41
    // Local variable table:
    //   start	length	slot	name	signature
    //   68	15	0	localObject1	Object
    //   110	2	0	localIOException1	IOException
    //   119	1	0	localObject2	Object
    //   185	2	0	localIOException2	IOException
    //   193	4	0	localException1	Exception
    //   199	7	0	localObject3	Object
    //   222	14	0	localIOException3	IOException
    //   241	2	0	localIOException4	IOException
    //   251	1	0	localObject4	Object
    //   64	152	1	localObject5	Object
    //   230	21	1	localObject6	Object
    //   253	1	1	localObject7	Object
    //   66	142	2	localObject8	Object
    //   249	4	2	localObject9	Object
    //   257	1	2	localException2	Exception
    //   9	198	3	localMainActivity	MainActivity
    //   39	13	4	localIterator	Iterator
    //   61	89	5	localEntry	java.util.Map.Entry
    //   91	36	6	localZipEntry	java.util.zip.ZipEntry
    //   79	46	7	localZipFile	java.util.zip.ZipFile
    // Exception table:
    //   from	to	target	type
    //   102	110	110	java/io/IOException
    //   178	182	185	java/io/IOException
    //   69	81	193	java/lang/Exception
    //   83	93	193	java/lang/Exception
    //   120	135	193	java/lang/Exception
    //   215	219	222	java/io/IOException
    //   69	81	230	finally
    //   83	93	230	finally
    //   120	135	230	finally
    //   200	204	230	finally
    //   206	211	230	finally
    //   235	239	241	java/io/IOException
    //   135	174	249	finally
    //   135	174	257	java/lang/Exception
  }
  
  protected static void loadEnabledScripts()
    throws IOException
  {
    Iterator localIterator = enabledScripts.iterator();
    while (localIterator.hasNext())
    {
      File localFile = getScriptFile((String)localIterator.next());
      if ((!localFile.exists()) || (!localFile.isFile())) {
        Log.i("BlockLauncher", "ModPE script " + localFile.toString() + " doesn't exist");
      } else {
        try
        {
          loadScript(localFile, true);
        }
        catch (Exception localException)
        {
          dumpScriptError(localException);
          ((MainActivity)MainActivity.currentMainActivity.get()).reportError(localException);
        }
      }
    }
    loadResourcePackScripts();
  }
  
  public static void loadEnabledScriptsNames(android.content.Context paramContext)
  {
    enabledScripts = Utils.getEnabledScripts();
  }
  
  /* Error */
  private static void loadPackagedScript(File paramFile, boolean paramBoolean)
    throws IOException
  {
    // Byte code:
    //   0: iload_1
    //   1: ifne +10 -> 11
    //   4: getstatic 252	net/zhuoweizhang/mcpelauncher/ScriptManager:modPkgTexturePack	Lnet/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack;
    //   7: aload_0
    //   8: invokevirtual 1386	net/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack:addPackage	(Ljava/io/File;)V
    //   11: aconst_null
    //   12: astore 5
    //   14: new 1328	java/util/zip/ZipFile
    //   17: dup
    //   18: aload_0
    //   19: invokespecial 1387	java/util/zip/ZipFile:<init>	(Ljava/io/File;)V
    //   22: astore 7
    //   24: aconst_null
    //   25: astore 5
    //   27: iconst_0
    //   28: istore_2
    //   29: aload 7
    //   31: invokestatic 1393	net/zhuoweizhang/mcpelauncher/MpepInfo:fromZip	(Ljava/util/zip/ZipFile;)Lnet/zhuoweizhang/mcpelauncher/MpepInfo;
    //   34: astore 6
    //   36: aload 6
    //   38: ifnull +211 -> 249
    //   41: aload 6
    //   43: astore 5
    //   45: aload 6
    //   47: getfield 1396	net/zhuoweizhang/mcpelauncher/MpepInfo:scrambleCode	Ljava/lang/String;
    //   50: invokevirtual 584	java/lang/String:length	()I
    //   53: istore_3
    //   54: iload_3
    //   55: ifle +194 -> 249
    //   58: iconst_1
    //   59: istore_2
    //   60: aload 6
    //   62: astore 5
    //   64: aload 7
    //   66: invokevirtual 1400	java/util/zip/ZipFile:entries	()Ljava/util/Enumeration;
    //   69: astore 6
    //   71: aload 6
    //   73: invokeinterface 1405 1 0
    //   78: ifeq +142 -> 220
    //   81: aload 6
    //   83: invokeinterface 1408 1 0
    //   88: checkcast 1410	java/util/zip/ZipEntry
    //   91: astore 9
    //   93: aconst_null
    //   94: astore 8
    //   96: aload 9
    //   98: invokevirtual 1411	java/util/zip/ZipEntry:getName	()Ljava/lang/String;
    //   101: astore 10
    //   103: aload 10
    //   105: ldc_w 1413
    //   108: invokevirtual 1082	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   111: ifeq -40 -> 71
    //   114: aload 10
    //   116: invokevirtual 1281	java/lang/String:toLowerCase	()Ljava/lang/String;
    //   119: ldc_w 1415
    //   122: invokevirtual 1286	java/lang/String:endsWith	(Ljava/lang/String;)Z
    //   125: istore 4
    //   127: iload 4
    //   129: ifeq -58 -> 71
    //   132: iload_2
    //   133: ifeq +125 -> 258
    //   136: aload 8
    //   138: astore 6
    //   140: aload 7
    //   142: aload 9
    //   144: invokevirtual 1343	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   147: astore 10
    //   149: aload 8
    //   151: astore 6
    //   153: aload 9
    //   155: invokevirtual 1418	java/util/zip/ZipEntry:getSize	()J
    //   158: l2i
    //   159: newarray <illegal type>
    //   161: astore 9
    //   163: aload 8
    //   165: astore 6
    //   167: aload 10
    //   169: aload 9
    //   171: invokevirtual 1421	java/io/InputStream:read	([B)I
    //   174: pop
    //   175: aload 8
    //   177: astore 6
    //   179: aload 10
    //   181: invokevirtual 1422	java/io/InputStream:close	()V
    //   184: aload 8
    //   186: astore 6
    //   188: aload 9
    //   190: aload 5
    //   192: invokestatic 1428	net/zhuoweizhang/mcpelauncher/Scrambler:scramble	([BLnet/zhuoweizhang/mcpelauncher/MpepInfo;)Ljava/io/Reader;
    //   195: astore 5
    //   197: aload 5
    //   199: astore 6
    //   201: aload 5
    //   203: aload_0
    //   204: invokevirtual 1036	java/io/File:getName	()Ljava/lang/String;
    //   207: invokestatic 1357	net/zhuoweizhang/mcpelauncher/ScriptManager:loadScript	(Ljava/io/Reader;Ljava/lang/String;)V
    //   210: aload 5
    //   212: ifnull +8 -> 220
    //   215: aload 5
    //   217: invokevirtual 1360	java/io/Reader:close	()V
    //   220: aload 7
    //   222: ifnull +8 -> 230
    //   225: aload 7
    //   227: invokevirtual 1429	java/util/zip/ZipFile:close	()V
    //   230: iload_1
    //   231: ifne +17 -> 248
    //   234: iconst_1
    //   235: ifne +13 -> 248
    //   238: getstatic 252	net/zhuoweizhang/mcpelauncher/ScriptManager:modPkgTexturePack	Lnet/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack;
    //   241: aload_0
    //   242: invokevirtual 1036	java/io/File:getName	()Ljava/lang/String;
    //   245: invokevirtual 1432	net/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack:removePackage	(Ljava/lang/String;)V
    //   248: return
    //   249: iconst_0
    //   250: istore_2
    //   251: aload 6
    //   253: astore 5
    //   255: goto -191 -> 64
    //   258: aload 8
    //   260: astore 6
    //   262: new 1339	java/io/InputStreamReader
    //   265: dup
    //   266: aload 7
    //   268: aload 9
    //   270: invokevirtual 1343	java/util/zip/ZipFile:getInputStream	(Ljava/util/zip/ZipEntry;)Ljava/io/InputStream;
    //   273: invokespecial 1346	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   276: astore 5
    //   278: goto -81 -> 197
    //   281: astore 5
    //   283: aload 6
    //   285: ifnull +8 -> 293
    //   288: aload 6
    //   290: invokevirtual 1360	java/io/Reader:close	()V
    //   293: aload 5
    //   295: athrow
    //   296: astore 6
    //   298: aload 7
    //   300: astore 5
    //   302: aload 5
    //   304: ifnull +8 -> 312
    //   307: aload 5
    //   309: invokevirtual 1429	java/util/zip/ZipFile:close	()V
    //   312: iload_1
    //   313: ifne +17 -> 330
    //   316: iconst_0
    //   317: ifne +13 -> 330
    //   320: getstatic 252	net/zhuoweizhang/mcpelauncher/ScriptManager:modPkgTexturePack	Lnet/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack;
    //   323: aload_0
    //   324: invokevirtual 1036	java/io/File:getName	()Ljava/lang/String;
    //   327: invokevirtual 1432	net/zhuoweizhang/mcpelauncher/texture/ModPkgTexturePack:removePackage	(Ljava/lang/String;)V
    //   330: aload 6
    //   332: athrow
    //   333: astore 6
    //   335: goto -33 -> 302
    //   338: astore 6
    //   340: goto -276 -> 64
    // Local variable table:
    //   start	length	slot	name	signature
    //   0	343	0	paramFile	File
    //   0	343	1	paramBoolean	boolean
    //   28	223	2	i	int
    //   53	2	3	j	int
    //   125	3	4	bool	boolean
    //   12	265	5	localObject1	Object
    //   281	13	5	localObject2	Object
    //   300	8	5	localObject3	Object
    //   34	255	6	localObject4	Object
    //   296	35	6	localObject5	Object
    //   333	1	6	localObject6	Object
    //   338	1	6	localJSONException	JSONException
    //   22	277	7	localZipFile	java.util.zip.ZipFile
    //   94	165	8	localObject7	Object
    //   91	178	9	localObject8	Object
    //   101	79	10	localObject9	Object
    // Exception table:
    //   from	to	target	type
    //   140	149	281	finally
    //   153	163	281	finally
    //   167	175	281	finally
    //   179	184	281	finally
    //   188	197	281	finally
    //   201	210	281	finally
    //   262	278	281	finally
    //   29	36	296	finally
    //   45	54	296	finally
    //   64	71	296	finally
    //   71	93	296	finally
    //   96	127	296	finally
    //   215	220	296	finally
    //   288	293	296	finally
    //   293	296	296	finally
    //   14	24	333	finally
    //   29	36	338	org/json/JSONException
    //   45	54	338	org/json/JSONException
  }
  
  /* Error */
  protected static void loadResourcePackScripts()
  {
    // Byte code:
    //   0: getstatic 613	com/mojang/minecraftpe/MainActivity:currentMainActivity	Ljava/lang/ref/WeakReference;
    //   3: invokevirtual 617	java/lang/ref/WeakReference:get	()Ljava/lang/Object;
    //   6: checkcast 609	com/mojang/minecraftpe/MainActivity
    //   9: astore 4
    //   11: aload 4
    //   13: ifnonnull +4 -> 17
    //   16: return
    //   17: invokestatic 1438	net/zhuoweizhang/mcpelauncher/ResourcePack:getAllResourcePacks	()Ljava/util/List;
    //   20: astore_1
    //   21: getstatic 779	java/lang/System:out	Ljava/io/PrintStream;
    //   24: aload_1
    //   25: invokevirtual 1441	java/io/PrintStream:println	(Ljava/lang/Object;)V
    //   28: getstatic 176	net/zhuoweizhang/mcpelauncher/ScriptManager:scripts	Ljava/util/List;
    //   31: invokeinterface 1444 1 0
    //   36: iconst_1
    //   37: isub
    //   38: istore_0
    //   39: iload_0
    //   40: iflt +37 -> 77
    //   43: getstatic 176	net/zhuoweizhang/mcpelauncher/ScriptManager:scripts	Ljava/util/List;
    //   46: iload_0
    //   47: invokeinterface 1446 2 0
    //   52: checkcast 60	net/zhuoweizhang/mcpelauncher/ScriptManager$ScriptState
    //   55: getfield 551	net/zhuoweizhang/mcpelauncher/ScriptManager$ScriptState:name	Ljava/lang/String;
    //   58: ldc_w 1448
    //   61: invokevirtual 1082	java/lang/String:startsWith	(Ljava/lang/String;)Z
    //   64: ifeq +90 -> 154
    //   67: getstatic 176	net/zhuoweizhang/mcpelauncher/ScriptManager:scripts	Ljava/util/List;
    //   70: iload_0
    //   71: invokeinterface 857 2 0
    //   76: pop
    //   77: aload_1
    //   78: invokeinterface 537 1 0
    //   83: astore 5
    //   85: aload 5
    //   87: invokeinterface 542 1 0
    //   92: ifeq -76 -> 16
    //   95: aload 5
    //   97: invokeinterface 546 1 0
    //   102: checkcast 1434	net/zhuoweizhang/mcpelauncher/ResourcePack
    //   105: astore 6
    //   107: aconst_null
    //   108: astore_2
    //   109: aconst_null
    //   110: astore_3
    //   111: aload_2
    //   112: astore_1
    //   113: aload 6
    //   115: ldc_w 1450
    //   118: invokevirtual 1453	net/zhuoweizhang/mcpelauncher/ResourcePack:getInputStream	(Ljava/lang/String;)Ljava/io/InputStream;
    //   121: astore 7
    //   123: aload 7
    //   125: ifnonnull +36 -> 161
    //   128: iconst_0
    //   129: ifeq -44 -> 85
    //   132: new 1336	java/lang/NullPointerException
    //   135: dup
    //   136: invokespecial 1337	java/lang/NullPointerException:<init>	()V
    //   139: athrow
    //   140: astore_1
    //   141: aload_1
    //   142: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   145: goto -60 -> 85
    //   148: astore_1
    //   149: aload_1
    //   150: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   153: return
    //   154: iload_0
    //   155: iconst_1
    //   156: isub
    //   157: istore_0
    //   158: goto -119 -> 39
    //   161: aload_2
    //   162: astore_1
    //   163: new 1339	java/io/InputStreamReader
    //   166: dup
    //   167: aload 7
    //   169: invokespecial 1346	java/io/InputStreamReader:<init>	(Ljava/io/InputStream;)V
    //   172: astore_2
    //   173: aload_2
    //   174: new 403	java/lang/StringBuilder
    //   177: dup
    //   178: invokespecial 728	java/lang/StringBuilder:<init>	()V
    //   181: ldc_w 1448
    //   184: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   187: aload 6
    //   189: invokevirtual 1454	net/zhuoweizhang/mcpelauncher/ResourcePack:getName	()Ljava/lang/String;
    //   192: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   195: ldc_w 1456
    //   198: invokevirtual 407	java/lang/StringBuilder:append	(Ljava/lang/String;)Ljava/lang/StringBuilder;
    //   201: invokevirtual 730	java/lang/StringBuilder:toString	()Ljava/lang/String;
    //   204: invokestatic 1357	net/zhuoweizhang/mcpelauncher/ScriptManager:loadScript	(Ljava/io/Reader;Ljava/lang/String;)V
    //   207: aload_2
    //   208: ifnull +87 -> 295
    //   211: aload_2
    //   212: invokevirtual 1360	java/io/Reader:close	()V
    //   215: goto -130 -> 85
    //   218: astore_1
    //   219: aload_1
    //   220: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   223: goto -138 -> 85
    //   226: astore_1
    //   227: aload_3
    //   228: astore_2
    //   229: aload_1
    //   230: astore_3
    //   231: aload_2
    //   232: astore_1
    //   233: aload_3
    //   234: invokestatic 571	net/zhuoweizhang/mcpelauncher/ScriptManager:dumpScriptError	(Ljava/lang/Throwable;)V
    //   237: aload_2
    //   238: astore_1
    //   239: aload 4
    //   241: aload_3
    //   242: invokevirtual 1363	com/mojang/minecraftpe/MainActivity:reportError	(Ljava/lang/Throwable;)V
    //   245: aload_2
    //   246: ifnull -161 -> 85
    //   249: aload_2
    //   250: invokevirtual 1360	java/io/Reader:close	()V
    //   253: goto -168 -> 85
    //   256: astore_1
    //   257: aload_1
    //   258: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   261: goto -176 -> 85
    //   264: astore_2
    //   265: aload_1
    //   266: ifnull +7 -> 273
    //   269: aload_1
    //   270: invokevirtual 1360	java/io/Reader:close	()V
    //   273: aload_2
    //   274: athrow
    //   275: astore_1
    //   276: aload_1
    //   277: invokevirtual 1300	java/io/IOException:printStackTrace	()V
    //   280: goto -7 -> 273
    //   283: astore_3
    //   284: aload_2
    //   285: astore_1
    //   286: aload_3
    //   287: astore_2
    //   288: goto -23 -> 265
    //   291: astore_3
    //   292: goto -61 -> 231
    //   295: goto -210 -> 85
    // Local variable table:
    //   start	length	slot	name	signature
    //   38	120	0	i	int
    //   20	93	1	localObject1	Object
    //   140	2	1	localIOException1	IOException
    //   148	2	1	localIOException2	IOException
    //   162	1	1	localObject2	Object
    //   218	2	1	localIOException3	IOException
    //   226	4	1	localException1	Exception
    //   232	7	1	localObject3	Object
    //   256	14	1	localIOException4	IOException
    //   275	2	1	localIOException5	IOException
    //   285	1	1	localObject4	Object
    //   108	142	2	localObject5	Object
    //   264	21	2	localObject6	Object
    //   287	1	2	localObject7	Object
    //   110	132	3	localException2	Exception
    //   283	4	3	localObject8	Object
    //   291	1	3	localException3	Exception
    //   9	231	4	localMainActivity	MainActivity
    //   83	13	5	localIterator	Iterator
    //   105	83	6	localResourcePack	ResourcePack
    //   121	47	7	localInputStream	InputStream
    // Exception table:
    //   from	to	target	type
    //   132	140	140	java/io/IOException
    //   17	39	148	java/io/IOException
    //   43	77	148	java/io/IOException
    //   77	85	148	java/io/IOException
    //   85	107	148	java/io/IOException
    //   141	145	148	java/io/IOException
    //   219	223	148	java/io/IOException
    //   257	261	148	java/io/IOException
    //   273	275	148	java/io/IOException
    //   276	280	148	java/io/IOException
    //   211	215	218	java/io/IOException
    //   113	123	226	java/lang/Exception
    //   163	173	226	java/lang/Exception
    //   249	253	256	java/io/IOException
    //   113	123	264	finally
    //   163	173	264	finally
    //   233	237	264	finally
    //   239	245	264	finally
    //   269	273	275	java/io/IOException
    //   173	207	283	finally
    //   173	207	291	java/lang/Exception
  }
  
  public static void loadScript(File paramFile, boolean paramBoolean)
    throws IOException
  {
    if (isClassGenMode())
    {
      if (!scriptingInitialized) {
        label12:
        return;
      }
      if (!scriptingEnabled) {
        throw new RuntimeException("Not available in multiplayer");
      }
      loadScriptFromInstance(ScriptTranslationCache.get(androidContext, paramFile), paramFile.getName());
      return;
    }
    if (isPackagedScript(paramFile))
    {
      loadPackagedScript(paramFile, paramBoolean);
      return;
    }
    Object localObject3 = null;
    try
    {
      localFileReader = new FileReader(paramFile);
    }
    finally
    {
      try
      {
        FileReader localFileReader;
        loadScript(localFileReader, paramFile.getName());
        if (localFileReader == null) {
          break label12;
        }
        localFileReader.close();
        return;
      }
      finally
      {
        paramFile = (File)localObject1;
        Object localObject2 = localObject4;
      }
      localObject1 = finally;
      paramFile = (File)localObject3;
    }
    if (paramFile != null) {
      paramFile.close();
    }
    throw ((Throwable)localObject1);
  }
  
  public static void loadScript(Reader paramReader, String paramString)
    throws IOException
  {
    if (!scriptingInitialized) {}
    for (;;)
    {
      return;
      if (!scriptingEnabled) {
        throw new RuntimeException("Not available in multiplayer");
      }
      paramReader = new ParseThread(paramReader, paramString);
      paramString = new Thread(Thread.currentThread().getThreadGroup(), paramReader, "BlockLauncher parse thread", 262144L);
      paramString.start();
      try
      {
        paramString.join();
        if (paramReader.error == null) {
          continue;
        }
        if ((paramReader.error instanceof RuntimeException)) {}
        for (paramReader = (RuntimeException)paramReader.error;; paramReader = new RuntimeException(paramReader.error)) {
          throw paramReader;
        }
      }
      catch (InterruptedException paramString)
      {
        for (;;) {}
      }
    }
  }
  
  public static void loadScriptFromInstance(Script paramScript, String paramString)
  {
    org.mozilla.javascript.Context localContext = org.mozilla.javascript.Context.enter();
    setupContext(localContext);
    initJustLoadedScript(localContext, paramScript, paramString);
    org.mozilla.javascript.Context.exit();
  }
  
  public static TextureRequests mapTextureNames(TextureRequests paramTextureRequests)
  {
    int i = 0;
    if (i < paramTextureRequests.coords.length)
    {
      Object localObject = paramTextureRequests.names[i];
      if (((String)localObject).equals("stonecutter"))
      {
        localObject = paramTextureRequests.names;
        int j = paramTextureRequests.coords[i];
        localObject[i] = new String[] { "stonecutter_side", "stonecutter_other_side", "stonecutter_top", "stonecutter_bottom" }[(j % 4)];
        paramTextureRequests.coords[i] = 0;
      }
      for (;;)
      {
        i += 1;
        break;
        if (((String)localObject).equals("piston_inner")) {
          paramTextureRequests.names[i] = "piston_top";
        }
      }
    }
    return paramTextureRequests;
  }
  
  @CallbackName(args={"attacker", "victim"}, name="deathHook", prevent=true)
  public static void mobDieCallback(long paramLong1, long paramLong2)
  {
    long l = paramLong1;
    if (paramLong1 == -1L) {
      l = -1L;
    }
    callScriptMethod("deathHook", new Object[] { Long.valueOf(l), Long.valueOf(paramLong2) });
    if (worldData != null) {
      worldData.clearEntityData(paramLong2);
    }
  }
  
  private static void nameAndShame(String paramString) {}
  
  public static native void nativeAddFurnaceRecipe(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeAddItemChest(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
  
  public static native void nativeAddItemCreativeInv(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeAddItemFurnace(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7);
  
  public static native void nativeAddItemInventory(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeAddShapedRecipe(int paramInt1, int paramInt2, int paramInt3, String[] paramArrayOfString, int[] paramArrayOfInt);
  
  public static native void nativeArmorAddQueuedTextures();
  
  public static native String nativeBiomeIdToName(int paramInt);
  
  public static native float nativeBlockGetDestroyTime(int paramInt1, int paramInt2);
  
  public static native float nativeBlockGetFriction(int paramInt);
  
  public static native int nativeBlockGetSecondPart(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native void nativeBlockSetCollisionEnabled(int paramInt, boolean paramBoolean);
  
  public static native void nativeBlockSetColor(int paramInt, int[] paramArrayOfInt);
  
  public static native void nativeBlockSetDestroyTime(int paramInt, float paramFloat);
  
  public static native void nativeBlockSetExplosionResistance(int paramInt, float paramFloat);
  
  public static native void nativeBlockSetFriction(int paramInt, float paramFloat);
  
  public static native void nativeBlockSetLightLevel(int paramInt1, int paramInt2);
  
  public static native void nativeBlockSetLightOpacity(int paramInt1, int paramInt2);
  
  public static native void nativeBlockSetRedstoneConsumer(int paramInt, boolean paramBoolean);
  
  public static native void nativeBlockSetRenderLayer(int paramInt1, int paramInt2);
  
  public static native void nativeBlockSetShape(int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, int paramInt2);
  
  public static native void nativeBlockSetStepSound(int paramInt1, int paramInt2);
  
  public static native void nativeClearCapes();
  
  public static native void nativeClearSlotInventory(int paramInt);
  
  public static native void nativeClientMessage(String paramString);
  
  public static native void nativeCloseScreen();
  
  public static native void nativeDefineArmor(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3, int paramInt3, int paramInt4, int paramInt5);
  
  public static native void nativeDefineBlock(int paramInt1, String paramString, String[] paramArrayOfString, int[] paramArrayOfInt, int paramInt2, boolean paramBoolean, int paramInt3, int paramInt4);
  
  public static native void nativeDefineItem(int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3);
  
  public static native void nativeDefinePlaceholderBlocks();
  
  public static native void nativeDefineSnowballItem(int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3);
  
  public static native void nativeDestroyBlock(int paramInt1, int paramInt2, int paramInt3);
  
  public static native long nativeDropItem(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeDumpVtable(String paramString, int paramInt);
  
  public static native int nativeEntityGetCarriedItem(long paramLong, int paramInt);
  
  public static native String nativeEntityGetMobSkin(long paramLong);
  
  public static native String nativeEntityGetNameTag(long paramLong);
  
  public static native int nativeEntityGetRenderType(long paramLong);
  
  public static native int nativeEntityGetRider(long paramLong);
  
  public static native int nativeEntityGetRiding(long paramLong);
  
  public static native long nativeEntityGetTarget(long paramLong);
  
  public static native long[] nativeEntityGetUUID(long paramLong);
  
  public static native boolean nativeEntityHasCustomSkin(long paramLong);
  
  public static native void nativeEntitySetImmobile(long paramLong, boolean paramBoolean);
  
  public static native void nativeEntitySetNameTag(long paramLong, String paramString);
  
  public static native void nativeEntitySetSize(long paramLong, float paramFloat1, float paramFloat2);
  
  public static native void nativeEntitySetTarget(long paramLong1, long paramLong2);
  
  public static native void nativeExplode(float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, boolean paramBoolean);
  
  public static native void nativeExtinguishFire(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native void nativeForceCrash();
  
  public static native void nativeGetAllEntities();
  
  public static native int nativeGetAnimalAge(long paramLong);
  
  public static native int nativeGetArch();
  
  public static native int nativeGetBlockRenderShape(int paramInt);
  
  public static native int nativeGetBrightness(int paramInt1, int paramInt2, int paramInt3);
  
  public static native int nativeGetCarriedItem(int paramInt);
  
  public static native int nativeGetData(int paramInt1, int paramInt2, int paramInt3);
  
  public static native float nativeGetEntityLoc(long paramLong, int paramInt);
  
  public static native int nativeGetEntityTypeId(long paramLong);
  
  public static native float nativeGetEntityVel(long paramLong, int paramInt);
  
  public static native int nativeGetGameType();
  
  public static native String nativeGetI18NString(String paramString);
  
  public static native int nativeGetItemChest(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native int nativeGetItemCountChest(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native int nativeGetItemCountFurnace(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native int nativeGetItemDataChest(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native int nativeGetItemDataFurnace(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native int nativeGetItemEntityItem(long paramLong, int paramInt);
  
  public static native int nativeGetItemFurnace(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native int nativeGetItemIdCount();
  
  public static native int nativeGetItemMaxDamage(int paramInt);
  
  public static native String nativeGetItemName(int paramInt1, int paramInt2, boolean paramBoolean);
  
  public static native String nativeGetItemNameChest(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native String nativeGetLanguageName();
  
  public static native long nativeGetLevel();
  
  public static native int nativeGetMobHealth(long paramLong);
  
  public static native int nativeGetMobMaxHealth(long paramLong);
  
  public static native float nativeGetPitch(long paramLong);
  
  public static native long nativeGetPlayerEnt();
  
  public static native float nativeGetPlayerLoc(int paramInt);
  
  public static native String nativeGetPlayerName(long paramLong);
  
  public static native int nativeGetSelectedSlotId();
  
  public static native String nativeGetSignText(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native int nativeGetSlotInventory(int paramInt1, int paramInt2);
  
  public static native boolean nativeGetTextureCoordinatesForBlock(int paramInt1, int paramInt2, int paramInt3, float[] paramArrayOfFloat);
  
  public static native boolean nativeGetTextureCoordinatesForItem(int paramInt1, int paramInt2, float[] paramArrayOfFloat);
  
  public static native int nativeGetTile(int paramInt1, int paramInt2, int paramInt3);
  
  public static native long nativeGetTime();
  
  public static native float nativeGetYaw(long paramLong);
  
  public static native void nativeHurtTo(int paramInt);
  
  public static native boolean nativeIsSneaking(long paramLong);
  
  public static native boolean nativeIsValidCommand(String paramString);
  
  public static native boolean nativeIsValidItem(int paramInt);
  
  public static native int nativeItemGetMaxStackSize(int paramInt);
  
  public static native int nativeItemGetUseAnimation(int paramInt);
  
  public static native boolean nativeItemSetProperties(int paramInt, String paramString);
  
  public static native void nativeItemSetStackedByData(int paramInt, boolean paramBoolean);
  
  public static native void nativeItemSetUseAnimation(int paramInt1, int paramInt2);
  
  public static native void nativeJoinServer(String paramString, int paramInt);
  
  public static native void nativeLeaveGame(boolean paramBoolean);
  
  public static native void nativeLevelAddParticle(int paramInt1, float paramFloat1, float paramFloat2, float paramFloat3, float paramFloat4, float paramFloat5, float paramFloat6, int paramInt2);
  
  public static native boolean nativeLevelCanSeeSky(int paramInt1, int paramInt2, int paramInt3);
  
  public static native int nativeLevelGetBiome(int paramInt1, int paramInt2);
  
  public static native String nativeLevelGetBiomeName(int paramInt1, int paramInt2);
  
  public static native int nativeLevelGetDifficulty();
  
  public static native int nativeLevelGetGrassColor(int paramInt1, int paramInt2);
  
  public static native float nativeLevelGetLightningLevel();
  
  public static native float nativeLevelGetRainLevel();
  
  public static native boolean nativeLevelIsRemote();
  
  public static native void nativeLevelSetBiome(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeLevelSetDifficulty(int paramInt);
  
  public static native void nativeLevelSetGrassColor(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeLevelSetLightningLevel(float paramFloat);
  
  public static native void nativeLevelSetRainLevel(float paramFloat);
  
  public static native void nativeMobAddEffect(long paramLong, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2);
  
  public static native int nativeMobGetArmor(long paramLong, int paramInt1, int paramInt2);
  
  public static native String nativeMobGetArmorCustomName(long paramLong, int paramInt);
  
  public static native void nativeMobRemoveAllEffects(long paramLong);
  
  public static native void nativeMobRemoveEffect(long paramLong, int paramInt);
  
  public static native void nativeMobSetArmor(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeMobSetArmorCustomName(long paramLong, int paramInt, String paramString);
  
  public static native void nativeModPESetDesktopGui(boolean paramBoolean);
  
  public static native void nativeModPESetRenderDebug(boolean paramBoolean);
  
  public static native void nativeOnGraphicsReset();
  
  public static native void nativePlaySound(float paramFloat1, float paramFloat2, float paramFloat3, String paramString, float paramFloat4, float paramFloat5);
  
  public static native void nativePlayerAddExperience(int paramInt);
  
  public static native boolean nativePlayerCanFly();
  
  public static native boolean nativePlayerEnchant(int paramInt1, int paramInt2, int paramInt3);
  
  public static native int nativePlayerGetDimension();
  
  public static native int[] nativePlayerGetEnchantments(int paramInt);
  
  public static native float nativePlayerGetExhaustion();
  
  public static native float nativePlayerGetExperience();
  
  public static native float nativePlayerGetHunger(long paramLong);
  
  public static native String nativePlayerGetItemCustomName(int paramInt);
  
  public static native int nativePlayerGetLevel();
  
  public static native int nativePlayerGetPointedBlock(int paramInt);
  
  public static native long nativePlayerGetPointedEntity();
  
  public static native float nativePlayerGetPointedVec(int paramInt);
  
  public static native float nativePlayerGetSaturation();
  
  public static native int nativePlayerGetScore();
  
  public static native boolean nativePlayerIsFlying();
  
  public static native void nativePlayerSetCanFly(boolean paramBoolean);
  
  public static native void nativePlayerSetExhaustion(float paramFloat);
  
  public static native void nativePlayerSetExperience(float paramFloat);
  
  public static native void nativePlayerSetFlying(boolean paramBoolean);
  
  public static native void nativePlayerSetHunger(long paramLong, float paramFloat);
  
  public static native void nativePlayerSetItemCustomName(int paramInt, String paramString);
  
  public static native void nativePlayerSetLevel(int paramInt);
  
  public static native void nativePlayerSetSaturation(float paramFloat);
  
  public static native void nativePrePatch(boolean paramBoolean1, MainActivity paramMainActivity, boolean paramBoolean2);
  
  public static native void nativePreventDefault();
  
  public static native void nativeRecipeSetAnyAuxValue(int paramInt, boolean paramBoolean);
  
  public static native void nativeRemoveEntity(long paramLong);
  
  public static native void nativeRemoveItemBackground();
  
  public static native void nativeRequestFrameCallback();
  
  public static native void nativeRideAnimal(long paramLong1, long paramLong2);
  
  public static native void nativeScreenChooserSetScreen(int paramInt);
  
  public static native void nativeSelectLevel(String paramString1, String paramString2);
  
  public static native void nativeSendChat(String paramString);
  
  public static native void nativeSetAllowEnchantments(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeSetAnimalAge(long paramLong, int paramInt);
  
  public static native void nativeSetBlockRenderShape(int paramInt1, int paramInt2);
  
  public static native void nativeSetCameraEntity(long paramLong);
  
  public static native void nativeSetCape(long paramLong, String paramString);
  
  public static native void nativeSetCarriedItem(long paramLong, int paramInt1, int paramInt2, int paramInt3);
  
  public static native boolean nativeSetEntityRenderType(long paramLong, int paramInt);
  
  public static native void nativeSetExitEnabled(boolean paramBoolean);
  
  public static native void nativeSetFov(float paramFloat, boolean paramBoolean);
  
  public static native void nativeSetGameSpeed(float paramFloat);
  
  public static native void nativeSetGameType(int paramInt);
  
  public static native void nativeSetHandEquipped(int paramInt, boolean paramBoolean);
  
  public static native void nativeSetI18NString(String paramString1, String paramString2);
  
  public static native void nativeSetInventorySlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native void nativeSetIsRecording(boolean paramBoolean);
  
  public static native void nativeSetItemCategory(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeSetItemMaxDamage(int paramInt1, int paramInt2);
  
  public static native void nativeSetItemNameChest(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString);
  
  public static native void nativeSetMobHealth(long paramLong, int paramInt);
  
  public static native void nativeSetMobMaxHealth(long paramLong, int paramInt);
  
  public static native void nativeSetMobSkin(long paramLong, String paramString);
  
  public static native void nativeSetNightMode(boolean paramBoolean);
  
  public static native void nativeSetOnFire(long paramLong, int paramInt);
  
  public static native void nativeSetPosition(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
  
  public static native void nativeSetPositionRelative(long paramLong, float paramFloat1, float paramFloat2, float paramFloat3);
  
  public static native void nativeSetRot(long paramLong, float paramFloat1, float paramFloat2);
  
  public static native void nativeSetSelectedSlotId(int paramInt);
  
  public static native void nativeSetSignText(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString);
  
  public static native void nativeSetSneaking(long paramLong, boolean paramBoolean);
  
  public static native void nativeSetSpawn(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeSetStonecutterItem(int paramInt1, int paramInt2);
  
  public static native void nativeSetTextParseColorCodes(boolean paramBoolean);
  
  public static native void nativeSetTile(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5);
  
  public static native void nativeSetTime(long paramLong);
  
  public static native void nativeSetUseController(boolean paramBoolean);
  
  public static native void nativeSetVel(long paramLong, float paramFloat, int paramInt);
  
  public static native void nativeSetupHooks(int paramInt);
  
  public static native void nativeShowProgressScreen();
  
  public static native void nativeShowTipMessage(String paramString);
  
  public static native long nativeSpawnEntity(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, String paramString);
  
  public static native int nativeSpawnerGetEntityType(int paramInt1, int paramInt2, int paramInt3);
  
  public static native void nativeSpawnerSetEntityType(int paramInt1, int paramInt2, int paramInt3, int paramInt4);
  
  public static native boolean nativeZombieIsBaby(long paramLong);
  
  public static native void nativeZombieSetBaby(long paramLong, boolean paramBoolean);
  
  private static void overrideTexture(String paramString1, String paramString2)
  {
    if (androidContext == null) {
      return;
    }
    if ((paramString2.contains("terrain-atlas.tga")) || (paramString2.contains("items-opaque.png")))
    {
      scriptPrint("cannot override " + paramString2);
      return;
    }
    if (paramString1 == "")
    {
      clearTextureOverride(paramString2);
      return;
    }
    try
    {
      new Thread(new ScriptTextureDownloader(new URL(paramString1), getTextureOverrideFile(paramString2))).start();
      return;
    }
    catch (Exception paramString1)
    {
      throw new RuntimeException(paramString1);
    }
  }
  
  @CallbackName(args={"player", "experienceAdded"}, name="playerAddExpHook", prevent=true)
  public static void playerAddExperienceCallback(long paramLong, int paramInt)
  {
    callScriptMethod("playerAddExpHook", new Object[] { Long.valueOf(paramLong), Integer.valueOf(paramInt) });
  }
  
  @CallbackName(args={"player", "levelsAdded"}, name="playerExpLevelChangeHook", prevent=true)
  public static void playerAddLevelsCallback(long paramLong, int paramInt)
  {
    callScriptMethod("playerExpLevelChangeHook", new Object[] { Long.valueOf(paramLong), Integer.valueOf(paramInt) });
  }
  
  private static void playerAddedHandler(long paramLong)
  {
    allplayers.add(Long.valueOf(paramLong));
    if (!shouldLoadSkin()) {
      return;
    }
    runOnMainThread(new SkinLoader(paramLong));
  }
  
  private static void playerRemovedHandler(long paramLong)
  {
    int i = allplayers.indexOf(Long.valueOf(paramLong));
    if (i >= 0) {
      allplayers.remove(i);
    }
  }
  
  protected static void prepareEnabledScripts()
    throws IOException
  {
    loadEnabledScriptsNames(androidContext);
    boolean bool = Utils.getPrefs(0).getBoolean("zz_reimport_scripts", false);
    StringBuilder localStringBuilder = new StringBuilder();
    Iterator localIterator = enabledScripts.iterator();
    while (localIterator.hasNext())
    {
      File localFile = getScriptFile((String)localIterator.next());
      if ((!localFile.exists()) || (!localFile.isFile()))
      {
        Log.i("BlockLauncher", "ModPE script " + localFile.toString() + " doesn't exist");
      }
      else
      {
        if (bool) {}
        try
        {
          if (reimportIfPossible(localFile)) {
            localStringBuilder.append(localFile.getName()).append(' ');
          }
          prepareScript(localFile);
        }
        catch (Exception localException)
        {
          dumpScriptError(localException);
          ((MainActivity)MainActivity.currentMainActivity.get()).reportError(localException);
        }
      }
    }
    if (localStringBuilder.length() != 0) {
      ((MainActivity)MainActivity.currentMainActivity.get()).reportReimported(localStringBuilder.toString());
    }
  }
  
  private static void prepareScript(File paramFile)
    throws Exception
  {
    if (!isPackagedScript(paramFile)) {
      return;
    }
    modPkgTexturePack.addPackage(paramFile);
  }
  
  public static void processDebugCommand(String paramString)
  {
    try
    {
      if (paramString.equals("dumpitems")) {
        debugDumpItems();
      }
      return;
    }
    catch (Exception paramString)
    {
      paramString.printStackTrace();
    }
  }
  
  private static void rakNetConnectCallback(String paramString, int paramInt)
  {
    Log.i("BlockLauncher", "Connecting to " + paramString + ":" + paramInt);
    scriptingEnabled = isLocalAddress(paramString);
    StringBuilder localStringBuilder = new StringBuilder().append("Scripting is now ");
    String str;
    if (scriptingEnabled)
    {
      str = "enabled";
      Log.i("BlockLauncher", str);
      serverAddress = paramString;
      serverPort = paramInt;
      isRemote = true;
      if (MainActivity.currentMainActivity != null)
      {
        paramString = (MainActivity)MainActivity.currentMainActivity.get();
        if (paramString != null)
        {
          if (!scriptingEnabled) {
            modernWrapFactory.closePopups(paramString);
          }
          if (scriptingEnabled) {
            break label151;
          }
        }
      }
    }
    label151:
    for (boolean bool = true;; bool = false)
    {
      paramString.setLevelCallback(bool);
      return;
      str = "disabled";
      break;
    }
  }
  
  @CallbackName(args={"x", "y", "z", "newCurrent", "someBooleanIDontKnow", "blockId", "blockData"}, name="redstoneUpdateHook")
  public static void redstoneUpdateCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4, boolean paramBoolean, int paramInt5, int paramInt6)
  {
    callScriptMethod("redstoneUpdateHook", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Boolean.valueOf(paramBoolean), Integer.valueOf(paramInt5), Integer.valueOf(paramInt6) });
  }
  
  public static boolean reimportIfPossible(File paramFile)
    throws IOException
  {
    File localFile = getOriginalFile(paramFile);
    if (localFile == null) {}
    while (localFile.lastModified() <= paramFile.lastModified()) {
      return false;
    }
    PatchUtils.copy(localFile, paramFile);
    return true;
  }
  
  public static void reloadScript(File paramFile)
    throws IOException
  {
    removeScript(paramFile.getName());
    loadScript(paramFile, false);
  }
  
  public static void removeDeadEntries(Collection<String> paramCollection)
  {
    enabledScripts.retainAll(paramCollection);
    saveEnabledScripts();
  }
  
  public static void removeScript(String paramString)
  {
    int i = scripts.size() - 1;
    for (;;)
    {
      if (i >= 0)
      {
        if (((ScriptState)scripts.get(i)).name.equals(paramString)) {
          scripts.remove(i);
        }
      }
      else if (!isPackagedScript(paramString)) {}
      try
      {
        modPkgTexturePack.removePackage(paramString);
        return;
      }
      catch (IOException paramString)
      {
        paramString.printStackTrace();
      }
      i -= 1;
    }
  }
  
  public static void reportScriptError(ScriptState paramScriptState, Throwable paramThrowable)
  {
    if (paramScriptState != null) {
      paramScriptState.errors += 1;
    }
    MainActivity localMainActivity;
    if (MainActivity.currentMainActivity != null)
    {
      localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
      if (localMainActivity != null) {
        if (paramScriptState != null) {
          break label69;
        }
      }
    }
    label69:
    for (String str = "Unknown script";; str = paramScriptState.name)
    {
      localMainActivity.scriptErrorCallback(str, paramThrowable);
      if ((paramScriptState != null) && (paramScriptState.errors >= 5)) {
        localMainActivity.scriptTooManyErrorsCallback(paramScriptState.name);
      }
      return;
    }
  }
  
  public static void requestGraphicsReset()
  {
    requestedGraphicsReset = true;
  }
  
  public static void runOnMainThread(Runnable paramRunnable)
  {
    synchronized (runOnMainThreadList)
    {
      runOnMainThreadList.add(paramRunnable);
      return;
    }
  }
  
  protected static void saveEnabledScripts()
  {
    SharedPreferences.Editor localEditor = Utils.getPrefs(1).edit();
    localEditor.putString("enabledScripts", PatchManager.join((String[])enabledScripts.toArray(PatchManager.blankArray), ";"));
    localEditor.putInt("scriptManagerVersion", 1);
    localEditor.apply();
  }
  
  @CallbackName(args={"screenName"}, name="screenChangeHook")
  public static void screenChangeCallback(String paramString1, String paramString2, String paramString3)
  {
    if (("options_screen".equals(paramString1)) && ("resource_packs_screen".equals(currentScreen))) {
      loadResourcePackScripts();
    }
    currentScreen = paramString1;
    callScriptMethod("screenChangeHook", new Object[] { paramString1 });
  }
  
  private static void scriptFakeTipMessage(String paramString)
  {
    if (MainActivity.currentMainActivity != null)
    {
      MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
      if (localMainActivity != null) {
        localMainActivity.fakeTipMessageCallback(paramString);
      }
    }
  }
  
  private static void scriptPrint(String paramString)
  {
    System.out.println(paramString);
    if (MainActivity.currentMainActivity != null)
    {
      MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
      if (localMainActivity != null) {
        localMainActivity.scriptPrintCallback(paramString, currentScript);
      }
    }
  }
  
  @CallbackName(name="selectLevelHook")
  private static void selectLevelCallback(String paramString1, String paramString2)
  {
    System.out.println("World name: " + paramString1);
    System.out.println("World dir: " + paramString2);
    worldName = paramString1;
    worldDir = paramString2;
    scriptingEnabled = true;
    isRemote = false;
    if (worldData != null) {}
    try
    {
      worldData.save();
      worldData = null;
    }
    catch (IOException paramString1)
    {
      try
      {
        worldData = new WorldData(new File(new File("/sdcard/games/com.mojang/minecraftWorlds"), worldDir));
        callScriptMethod("selectLevelHook", new Object[0]);
        nativeArmorAddQueuedTextures();
        nextTickCallsSetLevel = true;
        return;
        paramString1 = paramString1;
        paramString1.printStackTrace();
      }
      catch (IOException paramString1)
      {
        for (;;)
        {
          paramString1.printStackTrace();
        }
      }
    }
  }
  
  public static void setEnabled(File paramFile, boolean paramBoolean)
    throws IOException
  {
    setEnabled(paramFile.getName(), paramBoolean);
  }
  
  private static void setEnabled(String paramString, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean)
    {
      reloadScript(getScriptFile(paramString));
      enabledScripts.add(paramString);
    }
    for (;;)
    {
      saveEnabledScripts();
      return;
      enabledScripts.remove(paramString);
      removeScript(paramString);
    }
  }
  
  public static void setEnabled(File[] paramArrayOfFile, boolean paramBoolean)
    throws IOException
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
        if (paramBoolean)
        {
          reloadScript(getScriptFile(str));
          enabledScripts.add(str);
        }
        else
        {
          enabledScripts.remove(str);
          removeScript(str);
        }
      }
    }
    saveEnabledScripts();
  }
  
  public static void setEnabledWithoutLoad(File paramFile, boolean paramBoolean)
    throws IOException
  {
    setEnabledWithoutLoad(paramFile.getName(), paramBoolean);
  }
  
  private static void setEnabledWithoutLoad(String paramString, boolean paramBoolean)
    throws IOException
  {
    if (paramBoolean) {
      enabledScripts.add(paramString);
    }
    for (;;)
    {
      saveEnabledScripts();
      return;
      enabledScripts.remove(paramString);
    }
  }
  
  public static void setLevelCallback(boolean paramBoolean1, boolean paramBoolean2) {}
  
  @CallbackName(name="newLevel")
  public static void setLevelFakeCallback(boolean paramBoolean1, boolean paramBoolean2)
  {
    paramBoolean2 = true;
    boolean bool = nativeLevelIsRemote();
    nextTickCallsSetLevel = false;
    System.out.println("Level: " + paramBoolean1);
    if (!bool) {
      scriptingEnabled = true;
    }
    nativeSetGameSpeed(20.0F);
    allentities.clear();
    allplayers.clear();
    entityUUIDMap.clear();
    nativeClearCapes();
    hasLevel = true;
    entityAddedCallback(nativeGetPlayerEnt());
    if (!bool) {
      runOnMainThreadList.add(new Runnable()
      {
        public void run() {}
      });
    }
    callScriptMethod("newLevel", new Object[] { Boolean.valueOf(paramBoolean1) });
    MainActivity localMainActivity;
    if (MainActivity.currentMainActivity != null)
    {
      localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
      if (localMainActivity != null)
      {
        if (!scriptingEnabled) {
          modernWrapFactory.closePopups(localMainActivity);
        }
        if (scriptingEnabled) {
          break label171;
        }
      }
    }
    label171:
    for (paramBoolean1 = paramBoolean2;; paramBoolean1 = false)
    {
      localMainActivity.setLevelCallback(paramBoolean1);
      return;
    }
  }
  
  public static void setOriginalLocation(File paramFile1, File paramFile2)
    throws IOException
  {
    SharedPreferences.Editor localEditor = Utils.getPrefs(1).edit();
    JSONObject localJSONObject = getOriginalLocations();
    try
    {
      localJSONObject.put(paramFile2.getName(), paramFile1.getAbsolutePath());
      localEditor.putString("scriptOriginalLocations", localJSONObject.toString());
      localEditor.apply();
      return;
    }
    catch (JSONException paramFile1)
    {
      throw new RuntimeException("Setting original location failed", paramFile1);
    }
  }
  
  private static void setRequestLeaveGame()
  {
    nativeCloseScreen();
    requestLeaveGame = true;
    requestLeaveGameCounter = 10;
  }
  
  public static void setupContext(org.mozilla.javascript.Context paramContext)
  {
    paramContext.setOptimizationLevel(-1);
    paramContext.setLanguageVersion(200);
  }
  
  private static boolean shouldLoadSkin()
  {
    return false;
  }
  
  private static long spawnEntityImpl(float paramFloat1, float paramFloat2, float paramFloat3, int paramInt, String paramString)
  {
    if (paramInt <= 0) {
      throw new RuntimeException("Invalid entity type: " + paramInt);
    }
    long l = nativeSpawnEntity(paramFloat1, paramFloat2, paramFloat3, paramInt, paramString);
    if (nativeEntityHasCustomSkin(l)) {
      NativeEntityApi.setExtraData(Long.valueOf(l), "zhuowei.bl.s", paramString);
    }
    return l;
  }
  
  @CallbackName(args={"x", "y", "z", "side"}, name="startDestroyBlock", prevent=true)
  public static void startDestroyBlockCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
  {
    callScriptMethod("startDestroyBlock", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4) });
  }
  
  public static void takeScreenshot(String paramString)
  {
    screenshotFileName = paramString.replace("/", "").replace("\\", "");
    requestScreenshot = true;
    nativeRequestFrameCallback();
  }
  
  @CallbackName(args={"projectile", "blockX", "blockY", "blockZ", "side"}, name="projectileHitBlockHook")
  public static void throwableHitCallback(long paramLong1, int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, float paramFloat1, float paramFloat2, float paramFloat3, long paramLong2)
  {
    Integer localInteger;
    if (nativeGetEntityTypeId(paramLong1) == 81)
    {
      localInteger = (Integer)NativeItemApi.itemIdToRendererId.get(Integer.valueOf(nativeEntityGetRenderType(paramLong1)));
      if (paramInt1 != 0) {
        break label153;
      }
      if (localInteger != null) {
        callScriptMethod("customThrowableHitBlockHook", new Object[] { Long.valueOf(paramLong1), Integer.valueOf(localInteger.intValue()), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Integer.valueOf(paramInt5), Integer.valueOf(paramInt2) });
      }
      callScriptMethod("projectileHitBlockHook", new Object[] { Long.valueOf(paramLong1), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Integer.valueOf(paramInt5), Integer.valueOf(paramInt2) });
    }
    label153:
    while (paramInt1 != 1)
    {
      return;
      localInteger = null;
      break;
    }
    if (localInteger != null) {
      callScriptMethod("customThrowableHitEntityHook", new Object[] { Long.valueOf(paramLong1), Integer.valueOf(localInteger.intValue()), Long.valueOf(paramLong2) });
    }
    callScriptMethod("projectileHitEntityHook", new Object[] { Long.valueOf(paramLong1), Long.valueOf(paramLong2) });
  }
  
  @CallbackName(name="modTick")
  public static void tickCallback()
  {
    if (nextTickCallsSetLevel) {
      setLevelFakeCallback(true, nativeLevelIsRemote());
    }
    callScriptMethod("modTick", new Object[0]);
    if (requestedGraphicsReset)
    {
      nativeOnGraphicsReset();
      requestedGraphicsReset = false;
    }
    if (sensorEnabled) {
      updatePlayerOrientation();
    }
    int i;
    if (requestLeaveGame)
    {
      i = requestLeaveGameCounter;
      requestLeaveGameCounter = i - 1;
      if (i <= 0)
      {
        nativeScreenChooserSetScreen(1);
        nativeLeaveGame(false);
        requestLeaveGame = false;
        if (MainActivity.currentMainActivity != null)
        {
          ??? = (MainActivity)MainActivity.currentMainActivity.get();
          if (??? != null) {
            ((MainActivity)???).runOnUiThread(new Runnable()
            {
              public void run()
              {
                this.val$main.dismissHiddenTextbox();
                this.val$main.hideKeyboardView();
                System.out.println("Closed keyboard, I hope");
              }
            });
          }
        }
        nativeRequestFrameCallback();
      }
    }
    if ((requestJoinServer != null) && (!requestLeaveGame))
    {
      nativeJoinServer(requestJoinServer.serverAddress, requestJoinServer.serverPort);
      requestJoinServer = null;
    }
    if (runOnMainThreadList.size() > 0)
    {
      synchronized (runOnMainThreadList)
      {
        Iterator localIterator = runOnMainThreadList.iterator();
        if (localIterator.hasNext()) {
          ((Runnable)localIterator.next()).run();
        }
      }
      runOnMainThreadList.clear();
    }
    if (worldData != null)
    {
      i = worldDataSaveCounter - 1;
      worldDataSaveCounter = i;
      if (i > 0) {}
    }
    try
    {
      worldData.save();
      worldDataSaveCounter = 200;
      return;
    }
    catch (IOException localIOException)
    {
      for (;;)
      {
        localIOException.printStackTrace();
      }
    }
  }
  
  private static void updatePlayerOrientation()
  {
    nativeSetRot(nativeGetPlayerEnt(), newPlayerYaw, newPlayerPitch);
  }
  
  @CallbackName(args={"x", "y", "z", "itemid", "blockid", "side", "itemDamage", "blockDamage"}, name="useItem", prevent=true)
  public static void useItemOnCallback(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7, int paramInt8)
  {
    callScriptMethod("useItem", new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), Integer.valueOf(paramInt4), Integer.valueOf(paramInt5), Integer.valueOf(paramInt6), Integer.valueOf(paramInt7), Integer.valueOf(paramInt8) });
  }
  
  private static void verifyBlockTextures(TextureRequests paramTextureRequests)
  {
    if (terrainMeta == null) {}
    for (;;)
    {
      return;
      int i = 0;
      while (i < paramTextureRequests.names.length)
      {
        if (!terrainMeta.hasIcon(paramTextureRequests.names[i], paramTextureRequests.coords[i])) {
          throw new MissingTextureException("The requested block texture " + paramTextureRequests.names[i] + ":" + paramTextureRequests.coords[i] + " does not exist");
        }
        i += 1;
      }
    }
  }
  
  private static void wordWrapClientMessage(String paramString)
  {
    String[] arrayOfString = paramString.split("\n");
    int i = 0;
    if (i < arrayOfString.length)
    {
      String str2 = arrayOfString[i];
      String str1 = str2;
      if (paramString.indexOf("Åò") >= 0) {
        nativeClientMessage(str2);
      }
      for (;;)
      {
        i += 1;
        break;
        while (str1.length() > 40)
        {
          str2 = str1.substring(0, 40);
          nativeClientMessage(str2);
          str1 = str1.substring(str2.length());
        }
        if (str1.length() > 0) {
          nativeClientMessage(str1);
        }
      }
    }
  }
  
  private static class AfterCapeDownloadAction
    implements Runnable
  {
    private long entityId;
    private String skinPath;
    
    public AfterCapeDownloadAction(long paramLong, String paramString)
    {
      this.entityId = paramLong;
      this.skinPath = paramString;
    }
    
    public void run()
    {
      try
      {
        File localFile = ScriptManager.getTextureOverrideFile("images/" + this.skinPath);
        if (localFile != null)
        {
          if (!localFile.exists()) {
            return;
          }
          ScriptManager.NativeEntityApi.setCape(Long.valueOf(this.entityId), this.skinPath);
          return;
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private static class AfterSkinDownloadAction
    implements Runnable
  {
    private long entityId;
    private String skinPath;
    
    public AfterSkinDownloadAction(long paramLong, String paramString)
    {
      this.entityId = paramLong;
      this.skinPath = paramString;
    }
    
    public void run()
    {
      File localFile = ScriptManager.getTextureOverrideFile("images/" + this.skinPath);
      if ((localFile == null) || (!localFile.exists())) {
        return;
      }
      ScriptManager.NativeEntityApi.setMobSkin(Long.valueOf(this.entityId), this.skinPath);
    }
  }
  
  private static class BlockHostObject
    extends ImporterTopLevel
  {
    private long playerEnt = 0L;
    
    @JSFunction
    public void addItemInventory(int paramInt1, int paramInt2, int paramInt3)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("invalid item id " + paramInt1);
      }
      ScriptManager.nativeAddItemInventory(paramInt1, paramInt2, paramInt3);
    }
    
    @JSFunction
    public void bl_setMobSkin(Object paramObject, String paramString)
    {
      ScriptManager.NativeEntityApi.setMobSkin(Long.valueOf(ScriptManager.getEntityId(paramObject)), paramString);
    }
    
    @JSFunction
    public long bl_spawnMob(double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = null;
      }
      return ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, paramInt, str);
    }
    
    @JSFunction
    public void clientMessage(String paramString)
    {
      ScriptManager.wordWrapClientMessage(paramString);
    }
    
    @JSFunction
    public void explode(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, boolean paramBoolean)
    {
      ScriptManager.nativeExplode((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, (float)paramDouble4, paramBoolean);
    }
    
    @JSFunction
    public int getCarriedItem()
    {
      return ScriptManager.nativeGetCarriedItem(0);
    }
    
    public String getClassName()
    {
      return "BlockHostObject";
    }
    
    @JSFunction
    public ScriptManager.NativePointer getLevel()
    {
      return new ScriptManager.NativePointer(ScriptManager.nativeGetLevel());
    }
    
    @JSFunction
    public double getPitch(Object paramObject)
    {
      if ((paramObject == null) || (!(paramObject instanceof Number))) {}
      for (long l = getPlayerEnt();; l = ((Number)paramObject).longValue()) {
        return ScriptManager.nativeGetPitch(l);
      }
    }
    
    @JSFunction
    public long getPlayerEnt()
    {
      this.playerEnt = ScriptManager.nativeGetPlayerEnt();
      return this.playerEnt;
    }
    
    @JSFunction
    public double getPlayerX()
    {
      return ScriptManager.nativeGetPlayerLoc(0);
    }
    
    @JSFunction
    public double getPlayerY()
    {
      return ScriptManager.nativeGetPlayerLoc(1);
    }
    
    @JSFunction
    public double getPlayerZ()
    {
      return ScriptManager.nativeGetPlayerLoc(2);
    }
    
    @JSFunction
    public int getTile(int paramInt1, int paramInt2, int paramInt3)
    {
      return ScriptManager.nativeGetTile(paramInt1, paramInt2, paramInt3);
    }
    
    @JSFunction
    public double getYaw(Object paramObject)
    {
      if ((paramObject == null) || (!(paramObject instanceof Number))) {}
      for (long l = getPlayerEnt();; l = ((Number)paramObject).longValue()) {
        return ScriptManager.nativeGetYaw(l);
      }
    }
    
    @JSFunction
    public void preventDefault() {}
    
    @JSFunction
    public void print(String paramString)
    {
      ScriptManager.scriptPrint(paramString);
    }
    
    @JSFunction
    public void rideAnimal(Object paramObject1, Object paramObject2)
    {
      ScriptManager.nativeRideAnimal(ScriptManager.getEntityId(paramObject1), ScriptManager.getEntityId(paramObject2));
    }
    
    @JSFunction
    public void setNightMode(boolean paramBoolean)
    {
      ScriptManager.nativeSetNightMode(paramBoolean);
    }
    
    @JSFunction
    public void setPosition(Object paramObject, double paramDouble1, double paramDouble2, double paramDouble3)
    {
      ScriptManager.nativeSetPosition(ScriptManager.getEntityId(paramObject), (float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
    }
    
    @JSFunction
    public void setPositionRelative(Object paramObject, double paramDouble1, double paramDouble2, double paramDouble3)
    {
      ScriptManager.nativeSetPositionRelative(ScriptManager.getEntityId(paramObject), (float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
    }
    
    @JSFunction
    public void setRot(Object paramObject, double paramDouble1, double paramDouble2)
    {
      ScriptManager.nativeSetRot(ScriptManager.getEntityId(paramObject), (float)paramDouble1, (float)paramDouble2);
    }
    
    @JSFunction
    public void setTile(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      ScriptManager.nativeSetTile(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    }
    
    @JSFunction
    public void setVelX(Object paramObject, double paramDouble)
    {
      ScriptManager.nativeSetVel(ScriptManager.getEntityId(paramObject), (float)paramDouble, 0);
    }
    
    @JSFunction
    public void setVelY(Object paramObject, double paramDouble)
    {
      ScriptManager.nativeSetVel(ScriptManager.getEntityId(paramObject), (float)paramDouble, 1);
    }
    
    @JSFunction
    public void setVelZ(Object paramObject, double paramDouble)
    {
      ScriptManager.nativeSetVel(ScriptManager.getEntityId(paramObject), (float)paramDouble, 2);
    }
    
    @JSFunction
    public long spawnChicken(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = "mob/chicken.png";
      }
      return ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, 10, str);
    }
    
    @JSFunction
    public long spawnCow(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = "mob/cow.png";
      }
      return ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, 11, str);
    }
    
    @JSFunction
    public long spawnPigZombie(double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = "mob/pigzombie.png";
      }
      long l = ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, 36, str);
      int i;
      if (paramInt != 0)
      {
        i = paramInt;
        if (ScriptManager.nativeIsValidItem(paramInt)) {}
      }
      else
      {
        i = 283;
      }
      ScriptManager.nativeSetCarriedItem(l, i, 1, 0);
      return l;
    }
  }
  
  private static class EnchantmentInstance
  {
    public final int level;
    public final int type;
    
    public EnchantmentInstance(int paramInt1, int paramInt2)
    {
      this.type = paramInt1;
      this.level = paramInt2;
    }
    
    public String toString()
    {
      return "EnchantmentInstance[type=" + this.type + ",level=" + this.level + "]";
    }
  }
  
  private static class JoinServerRequest
  {
    public String serverAddress;
    public int serverPort;
  }
  
  private static final class MyMethodWatcher
    implements NativeJavaMethod.MethodWatcher
  {
    private boolean testName(String paramString)
    {
      return (paramString.equals("showAsDropDown")) || (paramString.equals("showAtLocation"));
    }
    
    public boolean canCall(Method paramMethod, Object paramObject)
    {
      if (((paramObject instanceof AccessibleObject)) && (paramMethod.getName().equals("setAccessible")))
      {
        Class localClass = null;
        if ((paramObject instanceof Member)) {
          localClass = ((Member)paramObject).getDeclaringClass();
        }
        if ((localClass == ScriptManager.class) || (localClass == NativeJavaMethod.class) || (localClass == ContextFactory.class)) {
          return false;
        }
      }
      if (ScriptManager.scriptingEnabled) {
        return true;
      }
      if ((!(paramObject instanceof PopupWindow)) || (!testName(paramMethod.getName()))) {}
      for (boolean bool = true;; bool = false) {
        return bool;
      }
    }
  }
  
  private static class NativeBlockApi
    extends ScriptableObject
  {
    @JSStaticFunction
    public static void defineBlock(int paramInt, String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4)
    {
      defineBlockImpl(paramInt, paramString, paramObject1, paramObject2, paramObject3, paramObject4, 0);
    }
    
    private static void defineBlockImpl(int paramInt1, String paramString, Object paramObject1, Object paramObject2, Object paramObject3, Object paramObject4, int paramInt2)
    {
      if ((paramInt1 < 0) || (paramInt1 >= 256)) {
        throw new IllegalArgumentException("Block IDs must be >= 0 and < 256");
      }
      int j = 17;
      boolean bool2 = true;
      int k = 0;
      int i = j;
      if (paramObject2 != null)
      {
        i = j;
        if ((paramObject2 instanceof Number))
        {
          i = ((Number)paramObject2).intValue();
          Log.i("BlockLauncher", "setting material source to " + i);
        }
      }
      boolean bool1 = bool2;
      if (paramObject3 != null)
      {
        bool1 = bool2;
        if ((paramObject3 instanceof Boolean))
        {
          bool1 = ((Boolean)paramObject3).booleanValue();
          Log.i("BlockLauncher", "setting opaque to " + bool1);
        }
      }
      j = k;
      if (paramObject4 != null)
      {
        j = k;
        if ((paramObject4 instanceof Number))
        {
          j = ((Number)paramObject4).intValue();
          Log.i("BlockLauncher", "setting renderType to " + j);
        }
      }
      paramObject1 = ScriptManager.mapTextureNames(ScriptManager.expandTexturesArray(paramObject1));
      ScriptManager.verifyBlockTextures((ScriptManager.TextureRequests)paramObject1);
      ScriptManager.nativeDefineBlock(paramInt1, paramString, ((ScriptManager.TextureRequests)paramObject1).names, ((ScriptManager.TextureRequests)paramObject1).coords, i, bool1, j, paramInt2);
    }
    
    @JSStaticFunction
    public static int defineLiquidBlock(int paramInt, String paramString, Object paramObject1, Object paramObject2)
    {
      defineBlockImpl(paramInt, paramString, paramObject1, paramObject2, Integer.valueOf(8), Integer.valueOf(8), 1);
      defineBlockImpl(paramInt + 1, "Still " + paramString, paramObject1, paramObject2, Integer.valueOf(8), Integer.valueOf(8), 2);
      return paramInt + 1;
    }
    
    @JSStaticFunction
    public static int[] getAllBlockIds()
    {
      boolean[] arrayOfBoolean = new boolean['?'];
      int j = 0;
      int i = 0;
      int k;
      while (i < 256)
      {
        k = j;
        if (ScriptManager.nativeIsValidItem(i))
        {
          arrayOfBoolean[i] = true;
          k = j + 1;
        }
        i += 1;
        j = k;
      }
      int[] arrayOfInt = new int[j];
      j = 0;
      i = 0;
      if (j < 256)
      {
        if (arrayOfBoolean[j] == 0) {
          break label86;
        }
        k = i + 1;
        arrayOfInt[i] = j;
        i = k;
      }
      label86:
      for (;;)
      {
        j += 1;
        break;
        return arrayOfInt;
      }
    }
    
    @JSStaticFunction
    public static double getDestroyTime(int paramInt1, int paramInt2)
    {
      return ScriptManager.nativeBlockGetDestroyTime(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static double getFriction(int paramInt1, int paramInt2)
    {
      return ScriptManager.nativeBlockGetFriction(paramInt1);
    }
    
    @JSStaticFunction
    public static int getRenderType(int paramInt)
    {
      return ScriptManager.nativeGetBlockRenderShape(paramInt);
    }
    
    @JSStaticFunction
    public static int[] getTextureCoords(int paramInt1, int paramInt2, int paramInt3)
    {
      float[] arrayOfFloat = new float[6];
      if (!ScriptManager.nativeGetTextureCoordinatesForBlock(paramInt1, paramInt2, paramInt3, arrayOfFloat)) {
        throw new RuntimeException("Can't get texture for block " + paramInt1 + ":" + paramInt2);
      }
      return new int[] { (int)(arrayOfFloat[0] * arrayOfFloat[4] + 0.5D), (int)(arrayOfFloat[1] * arrayOfFloat[5] + 0.5D), (int)(arrayOfFloat[2] * arrayOfFloat[4] + 0.5D), (int)(arrayOfFloat[3] * arrayOfFloat[5] + 0.5D), (int)(arrayOfFloat[4] + 0.5D), (int)(arrayOfFloat[5] + 0.5D) };
    }
    
    @JSStaticFunction
    public static void setColor(int paramInt, Scriptable paramScriptable)
    {
      ScriptManager.nativeBlockSetColor(paramInt, ScriptManager.expandColorsArray(paramScriptable));
    }
    
    @JSStaticFunction
    public static void setDestroyTime(int paramInt, double paramDouble)
    {
      if (!ScriptManager.scriptingEnabled) {
        return;
      }
      ScriptManager.nativeBlockSetDestroyTime(paramInt, (float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setExplosionResistance(int paramInt, double paramDouble)
    {
      ScriptManager.nativeBlockSetExplosionResistance(paramInt, (float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setFriction(int paramInt, double paramDouble)
    {
      ScriptManager.nativeBlockSetFriction(paramInt, (float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setLightLevel(int paramInt1, int paramInt2)
    {
      ScriptManager.nativeBlockSetLightLevel(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static void setLightOpacity(int paramInt1, int paramInt2)
    {
      ScriptManager.nativeBlockSetLightOpacity(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static void setRedstoneConsumer(int paramInt, boolean paramBoolean)
    {
      ScriptManager.nativeBlockSetRedstoneConsumer(paramInt, paramBoolean);
    }
    
    @JSStaticFunction
    public static void setRenderLayer(int paramInt1, int paramInt2)
    {
      int i;
      if (paramInt2 == 3) {
        i = 4;
      }
      for (;;)
      {
        ScriptManager.nativeBlockSetRenderLayer(paramInt1, i);
        return;
        i = paramInt2;
        if (paramInt2 == 4099) {
          i = 3;
        }
      }
    }
    
    @JSStaticFunction
    public static void setRenderType(int paramInt1, int paramInt2)
    {
      ScriptManager.nativeSetBlockRenderShape(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static void setShape(int paramInt1, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int paramInt2)
    {
      ScriptManager.nativeBlockSetShape(paramInt1, (float)paramDouble1, (float)paramDouble2, (float)paramDouble3, (float)paramDouble4, (float)paramDouble5, (float)paramDouble6, paramInt2);
    }
    
    public String getClassName()
    {
      return "Block";
    }
  }
  
  private static class NativeEntityApi
    extends ScriptableObject
  {
    @JSStaticFunction
    public static void addEffect(Object paramObject, int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean1, boolean paramBoolean2)
    {
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("addEffect only works for mobs");
      }
      if (MobEffect.effectIds.get(Integer.valueOf(paramInt1)) == null) {
        throw new RuntimeException("Invalid MobEffect id: " + paramInt1);
      }
      ScriptManager.nativeMobAddEffect(l, paramInt1, paramInt2, paramInt3, paramBoolean1, paramBoolean2);
    }
    
    @JSStaticFunction
    public static long[] getAll()
    {
      long[] arrayOfLong = new long[ScriptManager.allentities.size()];
      int i = 0;
      while (arrayOfLong.length > i)
      {
        arrayOfLong[i] = ((Long)ScriptManager.allentities.get(i)).longValue();
        i += 1;
      }
      return arrayOfLong;
    }
    
    @JSStaticFunction
    public static int getAnimalAge(Object paramObject)
    {
      int i = getEntityTypeId(paramObject);
      if ((i == 32) || (i == 34) || (i == 36))
      {
        if (ScriptManager.nativeZombieIsBaby(ScriptManager.getEntityId(paramObject))) {
          return 41536;
        }
        return 0;
      }
      if ((i < 10) || (i >= 32)) {
        throw new RuntimeException("Age can only be get for animals and zombies/skeletons/pigmen");
      }
      return ScriptManager.nativeGetAnimalAge(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static int getArmor(Object paramObject, int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= 4)) {
        throw new RuntimeException("slot " + paramInt + " is not a valid armor slot");
      }
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("getArmor only works for mobs");
      }
      return ScriptManager.nativeMobGetArmor(l, paramInt, 0);
    }
    
    @JSStaticFunction
    public static String getArmorCustomName(Object paramObject, int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= 4)) {
        throw new RuntimeException("slot " + paramInt + " is not a valid armor slot");
      }
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("setArmor only works for mobs");
      }
      return ScriptManager.nativeMobGetArmorCustomName(l, paramInt);
    }
    
    @JSStaticFunction
    public static int getArmorDamage(Object paramObject, int paramInt)
    {
      if ((paramInt < 0) || (paramInt >= 4)) {
        throw new RuntimeException("slot " + paramInt + " is not a valid armor slot");
      }
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("getArmorDamage only works for mobs");
      }
      return ScriptManager.nativeMobGetArmor(l, paramInt, 1);
    }
    
    @JSStaticFunction
    public static int getEntityTypeId(Object paramObject)
    {
      return ScriptManager.nativeGetEntityTypeId(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static String getExtraData(Object paramObject, String paramString)
    {
      if (ScriptManager.worldData == null) {
        return null;
      }
      return ScriptManager.worldData.getEntityData(ScriptManager.getEntityId(paramObject), paramString);
    }
    
    @JSStaticFunction
    public static int getHealth(Object paramObject)
    {
      int i = getEntityTypeId(paramObject);
      if ((i < 10) || (i >= 64)) {
        return 0;
      }
      return ScriptManager.nativeGetMobHealth(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static int getItemEntityCount(Object paramObject)
    {
      long l = ScriptManager.getEntityId(paramObject);
      if (ScriptManager.nativeGetEntityTypeId(l) != 64) {
        throw new RuntimeException("getItemEntity only works on item entities");
      }
      return ScriptManager.nativeGetItemEntityItem(l, 2);
    }
    
    @JSStaticFunction
    public static int getItemEntityData(Object paramObject)
    {
      long l = ScriptManager.getEntityId(paramObject);
      if (ScriptManager.nativeGetEntityTypeId(l) != 64) {
        throw new RuntimeException("getItemEntity only works on item entities");
      }
      return ScriptManager.nativeGetItemEntityItem(l, 1);
    }
    
    @JSStaticFunction
    public static int getItemEntityId(Object paramObject)
    {
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if (i != 64) {
        throw new RuntimeException("getItemEntity only works on item entities: got " + i);
      }
      return ScriptManager.nativeGetItemEntityItem(l, 0);
    }
    
    @JSStaticFunction
    public static int getMaxHealth(Object paramObject)
    {
      return ScriptManager.nativeGetMobMaxHealth(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static String getMobSkin(Object paramObject)
    {
      long l = ScriptManager.getEntityId(paramObject);
      int i = getEntityTypeId(Long.valueOf(l));
      if ((i <= 0) || (i >= 64)) {
        return "";
      }
      return ScriptManager.nativeEntityGetMobSkin(l);
    }
    
    @JSStaticFunction
    public static String getNameTag(Object paramObject)
    {
      return ScriptManager.nativeEntityGetNameTag(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static double getPitch(Object paramObject)
    {
      return ScriptManager.nativeGetPitch(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static int getRenderType(Object paramObject)
    {
      return ScriptManager.nativeEntityGetRenderType(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static int getRider(Object paramObject)
    {
      return ScriptManager.nativeEntityGetRider(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static int getRiding(Object paramObject)
    {
      return ScriptManager.nativeEntityGetRiding(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static long getTarget(Object paramObject)
    {
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("getTarget only works on mobs");
      }
      return ScriptManager.nativeEntityGetTarget(l);
    }
    
    @JSStaticFunction
    public static String getUniqueId(Object paramObject)
    {
      return ScriptManager.getEntityUUID(ScriptManager.access$900(paramObject));
    }
    
    @JSStaticFunction
    public static double getVelX(Object paramObject)
    {
      return ScriptManager.nativeGetEntityVel(ScriptManager.getEntityId(paramObject), 0);
    }
    
    @JSStaticFunction
    public static double getVelY(Object paramObject)
    {
      return ScriptManager.nativeGetEntityVel(ScriptManager.getEntityId(paramObject), 1);
    }
    
    @JSStaticFunction
    public static double getVelZ(Object paramObject)
    {
      return ScriptManager.nativeGetEntityVel(ScriptManager.getEntityId(paramObject), 2);
    }
    
    @JSStaticFunction
    public static double getX(Object paramObject)
    {
      return ScriptManager.nativeGetEntityLoc(ScriptManager.getEntityId(paramObject), 0);
    }
    
    @JSStaticFunction
    public static double getY(Object paramObject)
    {
      return ScriptManager.nativeGetEntityLoc(ScriptManager.getEntityId(paramObject), 1);
    }
    
    @JSStaticFunction
    public static double getYaw(Object paramObject)
    {
      return ScriptManager.nativeGetYaw(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static double getZ(Object paramObject)
    {
      return ScriptManager.nativeGetEntityLoc(ScriptManager.getEntityId(paramObject), 2);
    }
    
    @JSStaticFunction
    public static boolean isSneaking(Object paramObject)
    {
      return ScriptManager.nativeIsSneaking(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static void remove(Object paramObject)
    {
      ScriptManager.nativeRemoveEntity(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static void removeAllEffects(Object paramObject)
    {
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("removeAllEffects only works for mobs");
      }
      ScriptManager.nativeMobRemoveAllEffects(l);
    }
    
    @JSStaticFunction
    public static void removeEffect(Object paramObject, int paramInt)
    {
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("removeEffect only works for mobs");
      }
      if (MobEffect.effectIds.get(Integer.valueOf(paramInt)) == null) {
        throw new RuntimeException("Invalid MobEffect id: " + paramInt);
      }
      ScriptManager.nativeMobRemoveEffect(l, paramInt);
    }
    
    @JSStaticFunction
    public static void rideAnimal(Object paramObject1, Object paramObject2)
    {
      ScriptManager.nativeRideAnimal(ScriptManager.getEntityId(paramObject1), ScriptManager.getEntityId(paramObject2));
    }
    
    @JSStaticFunction
    public static void setAnimalAge(Object paramObject, int paramInt)
    {
      int i = getEntityTypeId(paramObject);
      if ((i == 32) || (i == 34) || (i == 36))
      {
        long l = ScriptManager.getEntityId(paramObject);
        if (paramInt < 0) {}
        for (boolean bool = true;; bool = false)
        {
          ScriptManager.nativeZombieSetBaby(l, bool);
          return;
        }
      }
      if ((i < 10) || (i >= 32)) {
        throw new RuntimeException("Age can only be set for animals and zombies/skeletons/pigmen");
      }
      ScriptManager.nativeSetAnimalAge(ScriptManager.getEntityId(paramObject), paramInt);
    }
    
    @JSStaticFunction
    public static void setArmor(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
    {
      if ((paramInt1 < 0) || (paramInt1 >= 4)) {
        throw new RuntimeException("slot " + paramInt1 + " is not a valid armor slot");
      }
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("setArmor only works for mobs");
      }
      ScriptManager.nativeMobSetArmor(l, paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void setArmorCustomName(Object paramObject, int paramInt, String paramString)
    {
      if ((paramInt < 0) || (paramInt >= 4)) {
        throw new RuntimeException("slot " + paramInt + " is not a valid armor slot");
      }
      long l = ScriptManager.getEntityId(paramObject);
      int i = ScriptManager.nativeGetEntityTypeId(l);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("setArmor only works for mobs");
      }
      ScriptManager.nativeMobSetArmorCustomName(l, paramInt, paramString);
    }
    
    @JSStaticFunction
    public static void setCape(Object paramObject, String paramString)
    {
      int i = ScriptManager.nativeGetEntityTypeId(ScriptManager.getEntityId(paramObject));
      if ((i < 32) || (i >= 64)) {
        throw new RuntimeException("Set cape only works for humanoid mobs");
      }
      ScriptManager.nativeSetCape(ScriptManager.getEntityId(paramObject), paramString);
    }
    
    @JSStaticFunction
    public static void setCarriedItem(Object paramObject, int paramInt1, int paramInt2, int paramInt3)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("The item ID " + paramInt1 + " is invalid.");
      }
      ScriptManager.nativeSetCarriedItem(ScriptManager.getEntityId(paramObject), paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void setCollisionSize(Object paramObject, double paramDouble1, double paramDouble2)
    {
      ScriptManager.nativeEntitySetSize(ScriptManager.getEntityId(paramObject), (float)paramDouble1, (float)paramDouble2);
    }
    
    @JSStaticFunction
    public static boolean setExtraData(Object paramObject, String paramString1, String paramString2)
    {
      if (ScriptManager.worldData == null) {
        return false;
      }
      ScriptManager.worldData.setEntityData(ScriptManager.getEntityId(paramObject), paramString1, paramString2);
      return true;
    }
    
    @JSStaticFunction
    public static void setFireTicks(Object paramObject, int paramInt)
    {
      ScriptManager.nativeSetOnFire(ScriptManager.getEntityId(paramObject), paramInt);
    }
    
    @JSStaticFunction
    public static void setHealth(Object paramObject, int paramInt)
    {
      int i = getEntityTypeId(paramObject);
      if ((i < 10) || (i >= 64)) {
        return;
      }
      ScriptManager.nativeSetMobHealth(ScriptManager.getEntityId(paramObject), paramInt);
    }
    
    @JSStaticFunction
    public static void setImmobile(Object paramObject, boolean paramBoolean)
    {
      setImmobileImpl(paramObject, paramBoolean);
      if (paramBoolean) {}
      for (String str = "1";; str = "0")
      {
        setExtraData(paramObject, "zhuowei.bl.im", str);
        return;
      }
    }
    
    public static void setImmobileImpl(Object paramObject, boolean paramBoolean)
    {
      ScriptManager.nativeEntitySetImmobile(ScriptManager.getEntityId(paramObject), paramBoolean);
    }
    
    @JSStaticFunction
    public static void setMaxHealth(Object paramObject, int paramInt)
    {
      int i = getEntityTypeId(paramObject);
      if ((i < 10) || (i >= 64)) {
        throw new RuntimeException("setMaxHealth called on non-mob: entityType=" + i);
      }
      ScriptManager.nativeSetMobMaxHealth(ScriptManager.getEntityId(paramObject), paramInt);
    }
    
    @JSStaticFunction
    public static void setMobSkin(Object paramObject, String paramString)
    {
      setMobSkinImpl(paramObject, paramString, true);
    }
    
    public static void setMobSkinImpl(Object paramObject, String paramString, boolean paramBoolean)
    {
      ScriptManager.nativeSetMobSkin(ScriptManager.getEntityId(paramObject), paramString);
      if (paramBoolean) {
        setExtraData(paramObject, "zhuowei.bl.s", paramString);
      }
    }
    
    @JSStaticFunction
    public static void setNameTag(Object paramObject, String paramString)
    {
      if (ScriptManager.nativeGetEntityTypeId(ScriptManager.getEntityId(paramObject)) >= 64) {
        throw new IllegalArgumentException("setNameTag only works on mobs");
      }
      ScriptManager.nativeEntitySetNameTag(ScriptManager.getEntityId(paramObject), paramString);
    }
    
    @JSStaticFunction
    public static void setPosition(Object paramObject, double paramDouble1, double paramDouble2, double paramDouble3)
    {
      ScriptManager.nativeSetPosition(ScriptManager.getEntityId(paramObject), (float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
    }
    
    @JSStaticFunction
    public static void setPositionRelative(Object paramObject, double paramDouble1, double paramDouble2, double paramDouble3)
    {
      ScriptManager.nativeSetPositionRelative(ScriptManager.getEntityId(paramObject), (float)paramDouble1, (float)paramDouble2, (float)paramDouble3);
    }
    
    @JSStaticFunction
    public static void setRenderType(Object paramObject1, Object paramObject2)
    {
      Object localObject = paramObject2;
      if ((paramObject2 instanceof NativeJavaObject)) {
        localObject = ((NativeJavaObject)paramObject2).unwrap();
      }
      int i = 0;
      if ((localObject instanceof Number))
      {
        int j = ((Number)localObject).intValue();
        setRenderTypeImpl(paramObject1, j);
        i = 1;
        localObject = RendererManager.NativeRendererApi.getById(j);
        paramObject2 = localObject;
        if (localObject != null) {}
      }
      else
      {
        if (!(localObject instanceof RendererManager.NativeRenderer)) {
          break label99;
        }
      }
      label99:
      for (paramObject2 = (RendererManager.NativeRenderer)localObject;; paramObject2 = RendererManager.NativeRendererApi.getByName(localObject.toString()))
      {
        if (i == 0) {
          setRenderTypeImpl(paramObject1, ((RendererManager.NativeRenderer)paramObject2).getRenderType());
        }
        setExtraData(paramObject1, "zhuowei.bl.rt", ((RendererManager.NativeRenderer)paramObject2).getName());
        return;
      }
    }
    
    public static void setRenderTypeImpl(Object paramObject, int paramInt)
    {
      if ((paramInt < 4096) && (!EntityRenderType.isValidRenderType(paramInt))) {
        throw new RuntimeException("Render type " + paramInt + " does not exist");
      }
      if ((paramInt == 12) && (getEntityTypeId(paramObject) != 15)) {
        throw new RuntimeException("Villager render type can only be used on villagers");
      }
      if (!ScriptManager.nativeSetEntityRenderType(ScriptManager.getEntityId(paramObject), paramInt)) {
        throw new RuntimeException("Custom render type " + paramInt + " does not exist");
      }
    }
    
    @JSStaticFunction
    public static void setRot(Object paramObject, double paramDouble1, double paramDouble2)
    {
      ScriptManager.nativeSetRot(ScriptManager.getEntityId(paramObject), (float)paramDouble1, (float)paramDouble2);
    }
    
    @JSStaticFunction
    public static void setSneaking(Object paramObject, boolean paramBoolean)
    {
      ScriptManager.nativeSetSneaking(ScriptManager.getEntityId(paramObject), paramBoolean);
    }
    
    @JSStaticFunction
    public static void setTarget(Object paramObject1, Object paramObject2)
    {
      long l2 = ScriptManager.getEntityId(paramObject1);
      int i = ScriptManager.nativeGetEntityTypeId(l2);
      if ((i <= 0) || (i >= 64)) {
        throw new RuntimeException("setTarget only works on mob entities");
      }
      if (paramObject2 == null) {}
      for (long l1 = -1L; l1 != -1L; l1 = ScriptManager.getEntityId(paramObject2))
      {
        i = ScriptManager.nativeGetEntityTypeId(l1);
        if ((i > 0) && (i < 64)) {
          break;
        }
        throw new RuntimeException("setTarget only works on mob targets");
      }
      ScriptManager.nativeEntitySetTarget(l2, l1);
    }
    
    @JSStaticFunction
    public static void setVelX(Object paramObject, double paramDouble)
    {
      ScriptManager.nativeSetVel(ScriptManager.getEntityId(paramObject), (float)paramDouble, 0);
    }
    
    @JSStaticFunction
    public static void setVelY(Object paramObject, double paramDouble)
    {
      ScriptManager.nativeSetVel(ScriptManager.getEntityId(paramObject), (float)paramDouble, 1);
    }
    
    @JSStaticFunction
    public static void setVelZ(Object paramObject, double paramDouble)
    {
      ScriptManager.nativeSetVel(ScriptManager.getEntityId(paramObject), (float)paramDouble, 2);
    }
    
    @JSStaticFunction
    public static long spawnMob(double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = null;
      }
      return ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, paramInt, str);
    }
    
    public String getClassName()
    {
      return "Entity";
    }
  }
  
  private static class NativeGuiApi
    extends ScriptableObject
  {
    @JSStaticFunction
    public static int getScreenHeight()
    {
      return 0;
    }
    
    @JSStaticFunction
    public static int getScreenWidth()
    {
      return 0;
    }
    
    public String getClassName()
    {
      return "Gui";
    }
  }
  
  private static class NativeItemApi
    extends ScriptableObject
  {
    private static List<Object[]> activeRecipes = new ArrayList();
    private static Map<Integer, Integer> itemIdToRendererId = new HashMap();
    private static Map<Integer, Integer> rendererToItemId = new HashMap();
    
    @JSStaticFunction
    public static void addCraftRecipe(int paramInt1, int paramInt2, int paramInt3, Scriptable paramScriptable)
    {
      Object localObject = ScriptManager.expandShapelessRecipe(paramScriptable);
      paramScriptable = new StringBuilder();
      char c1 = 'a';
      int[] arrayOfInt = new int[localObject.length];
      int i = 0;
      int m;
      int n;
      while (i < localObject.length)
      {
        k = localObject[i];
        m = localObject[(i + 1)];
        n = localObject[(i + 2)];
        char c2 = (char)(c1 + '\001');
        j = 0;
        while (j < m)
        {
          paramScriptable.append(c1);
          j += 1;
        }
        arrayOfInt[i] = c1;
        arrayOfInt[(i + 1)] = k;
        arrayOfInt[(i + 2)] = n;
        i += 3;
        c1 = c2;
      }
      int j = paramScriptable.length();
      i = j;
      if (j > 9)
      {
        ScriptManager.scriptPrint("Too many ingredients in shapeless recipe: max of 9 slots, the extra items have been ignored");
        paramScriptable.delete(9, paramScriptable.length());
        i = paramScriptable.length();
      }
      if (i <= 4)
      {
        j = 2;
        m = i / j;
        if (i % j == 0) {
          break label276;
        }
      }
      label276:
      for (int k = 1;; k = 0)
      {
        localObject = new String[k + m];
        k = 0;
        while (k < localObject.length)
        {
          int i1 = k * j;
          n = i1 + j;
          m = n;
          if (n > i) {
            m = i;
          }
          localObject[k] = paramScriptable.substring(i1, m);
          k += 1;
        }
        j = 3;
        break;
      }
      verifyAndAddShapedRecipe(paramInt1, paramInt2, paramInt3, (String[])localObject, arrayOfInt);
    }
    
    @JSStaticFunction
    public static void addFurnaceRecipe(int paramInt1, int paramInt2, int paramInt3)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("Invalid input in furnace recipe: " + paramInt1 + " is not a valid item. " + "You must create the item before you can add it to a recipe.");
      }
      if (!ScriptManager.nativeIsValidItem(paramInt2)) {
        throw new RuntimeException("Invalid output in furnace recipe: " + paramInt2 + " is not a valid item. " + "You must create the item before you can add it to a recipe.");
      }
      ScriptManager.nativeAddFurnaceRecipe(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void addShapedRecipe(int paramInt1, int paramInt2, int paramInt3, Scriptable paramScriptable1, Scriptable paramScriptable2)
    {
      int j = ((Number)ScriptableObject.getProperty(paramScriptable1, "length")).intValue();
      String[] arrayOfString = new String[j];
      int i = 0;
      while (i < j)
      {
        arrayOfString[i] = ScriptableObject.getProperty(paramScriptable1, i).toString();
        i += 1;
      }
      j = ((Number)ScriptableObject.getProperty(paramScriptable2, "length")).intValue();
      if (j % 3 != 0) {
        throw new RuntimeException("Ingredients array must be [\"?\", id, damage, ...]");
      }
      paramScriptable1 = new int[j];
      i = 0;
      if (i < j)
      {
        Object localObject = ScriptableObject.getProperty(paramScriptable2, i);
        if (i % 3 == 0) {
          paramScriptable1[i] = localObject.toString().charAt(0);
        }
        for (;;)
        {
          i += 1;
          break;
          paramScriptable1[i] = ((Number)localObject).intValue();
        }
      }
      verifyAndAddShapedRecipe(paramInt1, paramInt2, paramInt3, arrayOfString, paramScriptable1);
    }
    
    @JSStaticFunction
    public static void defineArmor(int paramInt1, String paramString1, int paramInt2, String paramString2, String paramString3, int paramInt3, int paramInt4, int paramInt5)
    {
      if ((paramInt5 < 0) || (paramInt5 > 3)) {
        throw new RuntimeException("Invalid armor type: use ArmorType.helmet, ArmorType.chestplate,ArmorType.leggings, or ArmorType.boots");
      }
      if ((paramInt1 < 0) || (paramInt1 >= ScriptManager.ITEM_ID_COUNT)) {
        throw new IllegalArgumentException("Item IDs must be >= 0 and < " + ScriptManager.ITEM_ID_COUNT);
      }
      if ((ScriptManager.itemsMeta != null) && (!ScriptManager.itemsMeta.hasIcon(paramString1, paramInt2))) {
        throw new MissingTextureException("The item icon " + paramString1 + ":" + paramInt2 + " does not exist");
      }
      ScriptManager.nativeDefineArmor(paramInt1, paramString1, paramInt2, paramString2, paramString3, paramInt3, paramInt4, paramInt5);
    }
    
    @JSStaticFunction
    public static void defineThrowable(int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3)
    {
      if ((paramInt1 < 0) || (paramInt1 >= ScriptManager.ITEM_ID_COUNT)) {
        throw new IllegalArgumentException("Item IDs must be >= 0 and < ITEM_ID_COUNT");
      }
      if ((ScriptManager.itemsMeta != null) && (!ScriptManager.itemsMeta.hasIcon(paramString1, paramInt2))) {
        throw new MissingTextureException("The item icon " + paramString1 + ":" + paramInt2 + " does not exist");
      }
      ScriptManager.nativeDefineSnowballItem(paramInt1, paramString1, paramInt2, paramString2, paramInt3);
      paramInt2 = RendererManager.nativeCreateItemSpriteRenderer(paramInt1);
      itemIdToRendererId.put(Integer.valueOf(paramInt2), Integer.valueOf(paramInt1));
      rendererToItemId.put(Integer.valueOf(paramInt1), Integer.valueOf(paramInt2));
    }
    
    @JSStaticFunction
    public static int getCustomThrowableRenderType(int paramInt)
    {
      Integer localInteger = (Integer)rendererToItemId.get(Integer.valueOf(paramInt));
      if (localInteger == null) {
        throw new RuntimeException("Not a custom throwable item ID: " + paramInt);
      }
      return localInteger.intValue();
    }
    
    @JSStaticFunction
    public static int getMaxDamage(int paramInt)
    {
      return ScriptManager.nativeGetItemMaxDamage(paramInt);
    }
    
    @JSStaticFunction
    public static int getMaxStackSize(int paramInt)
    {
      return ScriptManager.nativeItemGetMaxStackSize(paramInt);
    }
    
    @JSStaticFunction
    public static String getName(int paramInt1, int paramInt2, boolean paramBoolean)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("getName called with invalid item ID: " + paramInt1);
      }
      return ScriptManager.nativeGetItemName(paramInt1, paramInt2, paramBoolean);
    }
    
    @JSStaticFunction
    public static int[] getTextureCoords(int paramInt1, int paramInt2)
    {
      float[] arrayOfFloat = new float[6];
      if (!ScriptManager.nativeGetTextureCoordinatesForItem(paramInt1, paramInt2, arrayOfFloat)) {
        throw new RuntimeException("Can't get texture for item " + paramInt1 + ":" + paramInt2);
      }
      return new int[] { (int)(arrayOfFloat[0] * arrayOfFloat[4] + 0.5D), (int)(arrayOfFloat[1] * arrayOfFloat[5] + 0.5D), (int)(arrayOfFloat[2] * arrayOfFloat[4] + 0.5D), (int)(arrayOfFloat[3] * arrayOfFloat[5] + 0.5D), (int)(arrayOfFloat[4] + 0.5D), (int)(arrayOfFloat[5] + 0.5D) };
    }
    
    @JSStaticFunction
    public static int getUseAnimation(int paramInt)
    {
      return ScriptManager.nativeItemGetUseAnimation(paramInt);
    }
    
    private static boolean idHasName(String paramString, int paramInt, boolean paramBoolean)
    {
      int i = 0;
      String str2 = ScriptManager.nativeGetItemName(paramInt, 0, paramBoolean);
      if (str2 == null) {
        return false;
      }
      String str1 = str2;
      if (paramBoolean) {
        if (!str2.endsWith(".name")) {
          break label91;
        }
      }
      label91:
      for (paramInt = str2.length() - 5;; paramInt = str2.length())
      {
        if ((str2.startsWith("tile.")) || (str2.startsWith("item."))) {
          i = 5;
        }
        str1 = str2.substring(i, paramInt);
        return paramString.equals(str1.replace(" ", "_").toLowerCase());
      }
    }
    
    @JSStaticFunction
    public static int internalNameToId(String paramString)
    {
      return nameToIdImpl(paramString, true);
    }
    
    @JSStaticFunction
    public static boolean isValidItem(int paramInt)
    {
      return ScriptManager.nativeIsValidItem(paramInt);
    }
    
    private static int nameToIdImpl(String paramString, boolean paramBoolean)
    {
      int i;
      if (paramString == null)
      {
        i = -1;
        return i;
      }
      paramString = paramString.replace(" ", "_").toLowerCase();
      int j = 256;
      for (;;)
      {
        if (j >= 4096) {
          break label49;
        }
        i = j;
        if (idHasName(paramString, j, paramBoolean)) {
          break;
        }
        j += 1;
      }
      label49:
      j = 1;
      for (;;)
      {
        if (j >= 256) {
          break label76;
        }
        i = j;
        if (idHasName(paramString, j, paramBoolean)) {
          break;
        }
        j += 1;
      }
      try
      {
        label76:
        i = Integer.parseInt(paramString);
        return i;
      }
      catch (Exception paramString) {}
      return -1;
    }
    
    public static void reregisterRecipes()
    {
      Iterator localIterator = activeRecipes.iterator();
      while (localIterator.hasNext())
      {
        Object[] arrayOfObject = (Object[])localIterator.next();
        ScriptManager.nativeAddShapedRecipe(((Integer)arrayOfObject[0]).intValue(), ((Integer)arrayOfObject[1]).intValue(), ((Integer)arrayOfObject[2]).intValue(), (String[])arrayOfObject[3], (int[])arrayOfObject[4]);
      }
    }
    
    @JSStaticFunction
    public static void setCategory(int paramInt1, int paramInt2)
    {
      if ((paramInt2 < 0) || (paramInt2 > 4)) {
        throw new RuntimeException("Invalid category " + paramInt2 + ": should be one of ItemCategory.MATERIAL, ItemCategory.DECORATION, " + "ItemCategory.TOOL, or ItemCategory.FOOD");
      }
      ScriptManager.nativeSetItemCategory(paramInt1, paramInt2, 0);
    }
    
    @JSStaticFunction
    public static void setEnchantType(int paramInt1, int paramInt2, int paramInt3)
    {
      ScriptManager.nativeSetAllowEnchantments(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void setHandEquipped(int paramInt, boolean paramBoolean)
    {
      ScriptManager.nativeSetHandEquipped(paramInt, paramBoolean);
    }
    
    @JSStaticFunction
    public static void setMaxDamage(int paramInt1, int paramInt2)
    {
      ScriptManager.nativeSetItemMaxDamage(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static void setProperties(int paramInt, Object paramObject)
    {
      if (!isValidItem(paramInt)) {
        throw new RuntimeException(paramInt + " is not a valid item");
      }
      if (((paramObject instanceof CharSequence)) || (ScriptRuntime.typeof(paramObject).equals("string"))) {
        paramObject = paramObject.toString();
      }
      while (!ScriptManager.nativeItemSetProperties(paramInt, (String)paramObject))
      {
        throw new RuntimeException("Failed to set properties for item " + paramInt);
        if ((paramObject instanceof Scriptable))
        {
          paramObject = (Scriptable)paramObject;
          paramObject = NativeJSON.stringify(org.mozilla.javascript.Context.getCurrentContext(), ((Scriptable)paramObject).getParentScope(), paramObject, null, "").toString();
        }
        else
        {
          throw new RuntimeException("Invalid input to setProperties: " + paramObject + " cannot be converted to JSON");
        }
      }
    }
    
    @JSStaticFunction
    public static void setStackedByData(int paramInt, boolean paramBoolean)
    {
      ScriptManager.nativeItemSetStackedByData(paramInt, paramBoolean);
    }
    
    @JSStaticFunction
    public static void setUseAnimation(int paramInt1, int paramInt2)
    {
      ScriptManager.nativeItemSetUseAnimation(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static int translatedNameToId(String paramString)
    {
      return nameToIdImpl(paramString, false);
    }
    
    private static void verifyAndAddShapedRecipe(int paramInt1, int paramInt2, int paramInt3, String[] paramArrayOfString, int[] paramArrayOfInt)
    {
      if ((paramInt1 < 0) || (paramInt1 >= ScriptManager.ITEM_ID_COUNT)) {
        throw new RuntimeException("Invalid result in recipe: " + paramInt1 + ": must be between 0 and " + ScriptManager.ITEM_ID_COUNT);
      }
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("Invalid result in recipe: " + paramInt1 + " is not a valid item. " + "You must create the item before you can add it to a recipe.");
      }
      int i = 0;
      while (i < paramArrayOfInt.length)
      {
        if (!ScriptManager.nativeIsValidItem(paramArrayOfInt[(i + 1)])) {
          throw new RuntimeException("Invalid input in recipe: " + paramInt1 + " is not a valid item. " + "You must create the item before you can add it to a recipe.");
        }
        i += 3;
      }
      Iterator localIterator = activeRecipes.iterator();
      while (localIterator.hasNext())
      {
        Object[] arrayOfObject = (Object[])localIterator.next();
        if ((((Integer)arrayOfObject[0]).intValue() == paramInt1) && (((Integer)arrayOfObject[1]).intValue() == paramInt2) && (((Integer)arrayOfObject[2]).intValue() == paramInt3) && (Arrays.equals((String[])arrayOfObject[3], paramArrayOfString)) && (Arrays.equals((int[])arrayOfObject[4], paramArrayOfInt)))
        {
          System.out.println("Recipe already exists.");
          return;
        }
      }
      activeRecipes.add(new Object[] { Integer.valueOf(paramInt1), Integer.valueOf(paramInt2), Integer.valueOf(paramInt3), paramArrayOfString, paramArrayOfInt });
      ScriptManager.nativeAddShapedRecipe(paramInt1, paramInt2, paramInt3, paramArrayOfString, paramArrayOfInt);
    }
    
    public String getClassName()
    {
      return "Item";
    }
  }
  
  private static class NativeLevelApi
    extends ScriptableObject
  {
    @JSStaticFunction
    public static void addParticle(int paramInt1, double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, double paramDouble5, double paramDouble6, int paramInt2)
    {
      if (!ParticleType.checkValid(paramInt1, paramInt2)) {
        return;
      }
      ScriptManager.nativeLevelAddParticle(paramInt1, (float)paramDouble1, (float)paramDouble2, (float)paramDouble3, (float)paramDouble4, (float)paramDouble5, (float)paramDouble6, paramInt2);
    }
    
    @JSStaticFunction
    public static String biomeIdToName(int paramInt)
    {
      return ScriptManager.nativeBiomeIdToName(paramInt);
    }
    
    @JSStaticFunction
    public static boolean canSeeSky(int paramInt1, int paramInt2, int paramInt3)
    {
      return ScriptManager.nativeLevelCanSeeSky(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void destroyBlock(int paramInt1, int paramInt2, int paramInt3, boolean paramBoolean)
    {
      if (!ScriptManager.scriptingEnabled) {}
      int i;
      int j;
      do
      {
        return;
        i = getTile(paramInt1, paramInt2, paramInt3);
        j = getData(paramInt1, paramInt2, paramInt3);
        ScriptManager.nativeDestroyBlock(paramInt1, paramInt2, paramInt3);
      } while (!paramBoolean);
      dropItem(paramInt1 + 0.5D, paramInt2, paramInt3 + 0.5D, 1.0D, i, 1, j);
    }
    
    @JSStaticFunction
    public static long dropItem(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, int paramInt1, int paramInt2, int paramInt3)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("invalid item id " + paramInt1);
      }
      return ScriptManager.nativeDropItem((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, (float)paramDouble4, paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void explode(double paramDouble1, double paramDouble2, double paramDouble3, double paramDouble4, boolean paramBoolean)
    {
      ScriptManager.nativeExplode((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, (float)paramDouble4, paramBoolean);
    }
    
    @JSStaticFunction
    public static ScriptManager.NativePointer getAddress()
    {
      return new ScriptManager.NativePointer(ScriptManager.nativeGetLevel());
    }
    
    @JSStaticFunction
    public static int getBiome(int paramInt1, int paramInt2)
    {
      return ScriptManager.nativeLevelGetBiome(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static String getBiomeName(int paramInt1, int paramInt2)
    {
      return ScriptManager.nativeLevelGetBiomeName(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static int getBrightness(int paramInt1, int paramInt2, int paramInt3)
    {
      return ScriptManager.nativeGetBrightness(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static int getChestSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return ScriptManager.nativeGetItemChest(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static int getChestSlotCount(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return ScriptManager.nativeGetItemCountChest(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static String getChestSlotCustomName(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return ScriptManager.nativeGetItemNameChest(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static int getChestSlotData(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return ScriptManager.nativeGetItemDataChest(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static int getData(int paramInt1, int paramInt2, int paramInt3)
    {
      return ScriptManager.nativeGetData(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static int getDifficulty()
    {
      return ScriptManager.nativeLevelGetDifficulty();
    }
    
    @JSStaticFunction
    public static int getFurnaceSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return ScriptManager.nativeGetItemFurnace(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static int getFurnaceSlotCount(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return ScriptManager.nativeGetItemCountFurnace(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static int getFurnaceSlotData(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      return ScriptManager.nativeGetItemDataFurnace(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static int getGameMode()
    {
      return ScriptManager.nativeGetGameType();
    }
    
    @JSStaticFunction
    public static int getGrassColor(int paramInt1, int paramInt2)
    {
      return ScriptManager.nativeLevelGetGrassColor(paramInt1, paramInt2);
    }
    
    @JSStaticFunction
    public static double getLightningLevel()
    {
      return ScriptManager.nativeLevelGetLightningLevel();
    }
    
    @JSStaticFunction
    public static double getRainLevel()
    {
      return ScriptManager.nativeLevelGetRainLevel();
    }
    
    @JSStaticFunction
    public static String getSignText(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if ((paramInt4 < 0) || (paramInt4 >= 4)) {
        throw new RuntimeException("Invalid line for sign: must be in the range of 0 to 3");
      }
      return ScriptManager.nativeGetSignText(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static int getSpawnerEntityType(int paramInt1, int paramInt2, int paramInt3)
    {
      if (getTile(paramInt1, paramInt2, paramInt3) != 52) {
        throw new RuntimeException("Block at " + paramInt1 + ":" + paramInt2 + ":" + paramInt3 + " is not a mob spawner!");
      }
      return ScriptManager.nativeSpawnerGetEntityType(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static int getTile(int paramInt1, int paramInt2, int paramInt3)
    {
      return ScriptManager.nativeGetTile(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static int getTime()
    {
      return (int)ScriptManager.nativeGetTime();
    }
    
    @JSStaticFunction
    public static String getWorldDir()
    {
      return ScriptManager.worldDir;
    }
    
    @JSStaticFunction
    public static String getWorldName()
    {
      return ScriptManager.worldName;
    }
    
    @JSStaticFunction
    public static void playSound(double paramDouble1, double paramDouble2, double paramDouble3, String paramString, double paramDouble4, double paramDouble5)
    {
      float f3 = (float)paramDouble1;
      float f4 = (float)paramDouble2;
      float f5 = (float)paramDouble3;
      float f1;
      if (paramDouble4 <= 0.0D)
      {
        f1 = 1.0F;
        if (paramDouble5 > 0.0D) {
          break label57;
        }
      }
      label57:
      for (float f2 = 1.0F;; f2 = (float)paramDouble5)
      {
        ScriptManager.nativePlaySound(f3, f4, f5, paramString, f1, f2);
        return;
        f1 = (float)paramDouble4;
        break;
      }
    }
    
    @JSStaticFunction
    public static void playSoundEnt(Object paramObject, String paramString, double paramDouble1, double paramDouble2)
    {
      float f2 = 1.0F;
      float f3 = ScriptManager.nativeGetEntityLoc(ScriptManager.getEntityId(paramObject), 0);
      float f4 = ScriptManager.nativeGetEntityLoc(ScriptManager.getEntityId(paramObject), 1);
      float f5 = ScriptManager.nativeGetEntityLoc(ScriptManager.getEntityId(paramObject), 2);
      float f1;
      if (paramDouble1 <= 0.0D)
      {
        f1 = 1.0F;
        if (paramDouble2 > 0.0D) {
          break label71;
        }
      }
      for (;;)
      {
        ScriptManager.nativePlaySound(f3, f4, f5, paramString, f1, f2);
        return;
        f1 = (float)paramDouble1;
        break;
        label71:
        f2 = (float)paramDouble2;
      }
    }
    
    @JSStaticFunction
    public static void setChestSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt5)) {
        throw new RuntimeException("invalid item id " + paramInt5);
      }
      ScriptManager.nativeAddItemChest(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
    }
    
    @JSStaticFunction
    public static void setChestSlotCustomName(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
    {
      ScriptManager.nativeSetItemNameChest(paramInt1, paramInt2, paramInt3, paramInt4, paramString);
    }
    
    @JSStaticFunction
    public static void setDifficulty(int paramInt)
    {
      ScriptManager.nativeLevelSetDifficulty(paramInt);
    }
    
    @JSStaticFunction
    public static void setFurnaceSlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5, int paramInt6, int paramInt7)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt5)) {
        throw new RuntimeException("invalid item id " + paramInt5);
      }
      ScriptManager.nativeAddItemFurnace(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5, paramInt6, paramInt7);
    }
    
    @JSStaticFunction
    public static void setGameMode(int paramInt)
    {
      if (!ScriptManager.scriptingEnabled) {
        return;
      }
      ScriptManager.nativeSetGameType(paramInt);
    }
    
    @JSStaticFunction
    public static void setGrassColor(int paramInt1, int paramInt2, int paramInt3)
    {
      ScriptManager.nativeLevelSetGrassColor(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void setLightningLevel(double paramDouble)
    {
      ScriptManager.nativeLevelSetLightningLevel((float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setNightMode(boolean paramBoolean)
    {
      ScriptManager.nativeSetNightMode(paramBoolean);
    }
    
    @JSStaticFunction
    public static void setRainLevel(double paramDouble)
    {
      ScriptManager.nativeLevelSetRainLevel((float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setSignText(int paramInt1, int paramInt2, int paramInt3, int paramInt4, String paramString)
    {
      if ((paramInt4 < 0) || (paramInt4 >= 4)) {
        throw new RuntimeException("Invalid line for sign: must be in the range of 0 to 3");
      }
      ScriptManager.nativeSetSignText(paramInt1, paramInt2, paramInt3, paramInt4, paramString);
    }
    
    @JSStaticFunction
    public static void setSpawn(int paramInt1, int paramInt2, int paramInt3)
    {
      ScriptManager.nativeSetSpawn(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void setSpawnerEntityType(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      if (getTile(paramInt1, paramInt2, paramInt3) != 52) {
        throw new RuntimeException("Block at " + paramInt1 + ":" + paramInt2 + ":" + paramInt3 + " is not a mob spawner!");
      }
      ScriptManager.nativeSpawnerSetEntityType(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static void setTile(int paramInt1, int paramInt2, int paramInt3, int paramInt4, int paramInt5)
    {
      ScriptManager.nativeSetTile(paramInt1, paramInt2, paramInt3, paramInt4, paramInt5);
    }
    
    @JSStaticFunction
    public static void setTime(int paramInt)
    {
      ScriptManager.nativeSetTime(paramInt);
    }
    
    @JSStaticFunction
    public static long spawnChicken(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = "mob/chicken.png";
      }
      return ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, 10, str);
    }
    
    @JSStaticFunction
    public static long spawnCow(double paramDouble1, double paramDouble2, double paramDouble3, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = "mob/cow.png";
      }
      return ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, 11, str);
    }
    
    @JSStaticFunction
    public static long spawnMob(double paramDouble1, double paramDouble2, double paramDouble3, int paramInt, String paramString)
    {
      String str = paramString;
      if (ScriptManager.invalidTexName(paramString)) {
        str = null;
      }
      return ScriptManager.spawnEntityImpl((float)paramDouble1, (float)paramDouble2, (float)paramDouble3, paramInt, str);
    }
    
    public String getClassName()
    {
      return "Level";
    }
  }
  
  private static class NativeModPEApi
    extends ScriptableObject
  {
    @JSStaticFunction
    public static void dumpVtable(String paramString, int paramInt)
    {
      ScriptManager.nativeDumpVtable("_ZTV" + paramString.length() + paramString, paramInt);
    }
    
    @JSStaticFunction
    public static byte[] getBytesFromTexturePack(String paramString)
    {
      if (MainActivity.currentMainActivity != null)
      {
        MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
        if (localMainActivity != null) {
          return localMainActivity.getFileDataBytes(paramString);
        }
      }
      return null;
    }
    
    @JSStaticFunction
    public static String getI18n(String paramString)
    {
      return ScriptManager.nativeGetI18NString(paramString);
    }
    
    @JSStaticFunction
    public static String getLanguage()
    {
      return ScriptManager.nativeGetLanguageName();
    }
    
    private static String getLevelName(File paramFile)
      throws IOException
    {
      Object localObject = new File(paramFile, "levelname.txt");
      if (!((File)localObject).exists()) {
        return null;
      }
      paramFile = new FileInputStream((File)localObject);
      localObject = new byte[(int)((File)localObject).length()];
      paramFile.read((byte[])localObject);
      paramFile.close();
      return new String((byte[])localObject, "UTF-8");
    }
    
    @JSStaticFunction
    public static String getMinecraftVersion()
    {
      try
      {
        String str = ScriptManager.androidContext.getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionName;
        return str;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
      return "Unknown";
    }
    
    @JSStaticFunction
    public static void langEdit(String paramString1, String paramString2)
    {
      ScriptManager.nativeSetI18NString(paramString1, paramString2);
    }
    
    @JSStaticFunction
    public static void leaveGame() {}
    
    @JSStaticFunction
    public static void log(String paramString)
    {
      Log.i("MCPELauncherLog", paramString);
    }
    
    @JSStaticFunction
    public static InputStream openInputStreamFromTexturePack(String paramString)
    {
      if (MainActivity.currentMainActivity != null)
      {
        MainActivity localMainActivity = (MainActivity)MainActivity.currentMainActivity.get();
        if (localMainActivity != null) {
          return localMainActivity.getInputStreamForAsset(paramString);
        }
      }
      return null;
    }
    
    @JSStaticFunction
    public static void overrideTexture(String paramString1, String paramString2)
    {
      ScriptManager.overrideTexture(paramString2, paramString1);
    }
    
    @JSStaticFunction
    public static String readData(String paramString)
    {
      return ScriptManager.androidContext.getSharedPreferences("BlockLauncherModPEScript" + ScriptManager.currentScript, 0).getString(paramString, "");
    }
    
    @JSStaticFunction
    public static void removeData(String paramString)
    {
      SharedPreferences.Editor localEditor = ScriptManager.androidContext.getSharedPreferences("BlockLauncherModPEScript" + ScriptManager.currentScript, 0).edit();
      localEditor.remove(paramString);
      localEditor.commit();
    }
    
    @JSStaticFunction
    public static void resetFov()
    {
      ScriptManager.nativeSetFov(0.0F, false);
    }
    
    @JSStaticFunction
    public static void resetImages() {}
    
    @JSStaticFunction
    public static void saveData(String paramString1, String paramString2)
    {
      SharedPreferences.Editor localEditor = ScriptManager.androidContext.getSharedPreferences("BlockLauncherModPEScript" + ScriptManager.currentScript, 0).edit();
      localEditor.putString(paramString1, paramString2);
      localEditor.commit();
    }
    
    @JSStaticFunction
    public static void selectLevel(String paramString)
    {
      Object localObject2 = paramString;
      Object localObject3 = new File("/sdcard/games/com.mojang/minecraftWorlds");
      File localFile2 = new File((File)localObject3, (String)localObject2);
      Object localObject1 = localObject2;
      File localFile1 = localFile2;
      int j;
      int i;
      if (!localFile2.exists())
      {
        localObject3 = ((File)localObject3).listFiles();
        j = localObject3.length;
        i = 0;
      }
      for (;;)
      {
        localObject1 = localObject2;
        localFile1 = localFile2;
        if (i < j) {
          localFile1 = localObject3[i];
        }
        try
        {
          localObject1 = getLevelName(localFile1);
          if ((localObject1 != null) && (((String)localObject1).equals(paramString)))
          {
            localObject1 = localFile1.getName();
            if (localFile1.exists()) {
              break;
            }
            throw new RuntimeException("The selected world " + paramString + " does not exist.");
          }
        }
        catch (IOException localIOException1)
        {
          localIOException1.printStackTrace();
          i += 1;
        }
      }
      if (localIOException1.equals(ScriptManager.worldDir))
      {
        System.err.println("Attempted to load level that is already loaded - ignore");
        return;
      }
      paramString = null;
      try
      {
        localObject2 = getLevelName(localFile1);
        paramString = (String)localObject2;
        ScriptManager.access$1700();
      }
      catch (IOException localIOException2)
      {
        for (;;)
        {
          localIOException2.printStackTrace();
          continue;
          String str = paramString;
        }
      }
      ScriptManager.access$1802(new ScriptManager.SelectLevelRequest(null));
      ScriptManager.requestSelectLevel.dir = localIOException1;
      localObject2 = ScriptManager.requestSelectLevel;
      if (paramString == null)
      {
        ((ScriptManager.SelectLevelRequest)localObject2).name = localIOException1;
        return;
      }
    }
    
    @JSStaticFunction
    public static void setCamera(Object paramObject)
    {
      ScriptManager.nativeSetCameraEntity(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static void setFoodItem(int paramInt1, String paramString1, int paramInt2, int paramInt3, String paramString2, int paramInt4)
    {
      setItem(paramInt1, paramString1, paramInt2, paramString2, paramInt4);
      ScriptManager.NativeItemApi.setProperties(paramInt1, "{\"use_animation\":\"eat\",\"use_duration\": 32,\"food\":{\"nutrition\":" + paramInt3 + ",\"saturation_modifier\": \"normal\"," + "\"is_meat\": false}}");
    }
    
    @JSStaticFunction
    public static void setFov(double paramDouble)
    {
      ScriptManager.nativeSetFov((float)paramDouble, true);
    }
    
    @JSStaticFunction
    public static void setGameSpeed(double paramDouble)
    {
      ScriptManager.nativeSetGameSpeed((float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setGuiBlocks(String paramString)
    {
      overrideTexture("gui/gui_blocks.png", paramString);
    }
    
    @JSStaticFunction
    public static void setItem(int paramInt1, String paramString1, int paramInt2, String paramString2, int paramInt3)
    {
      try
      {
        Integer.parseInt(paramString1);
        throw new IllegalArgumentException("The item icon for " + paramString2.trim() + " is not updated for 0.8.0. Please ask the script author to update");
      }
      catch (NumberFormatException localNumberFormatException)
      {
        if ((paramInt1 < 0) || (paramInt1 >= ScriptManager.ITEM_ID_COUNT)) {
          throw new IllegalArgumentException("Item IDs must be >= 0 and < ITEM_ID_COUNT");
        }
        if ((ScriptManager.itemsMeta != null) && (!ScriptManager.itemsMeta.hasIcon(paramString1, paramInt2))) {
          throw new MissingTextureException("The item icon " + paramString1 + ":" + paramInt2 + " does not exist");
        }
        ScriptManager.nativeDefineItem(paramInt1, paramString1, paramInt2, paramString2, paramInt3);
      }
    }
    
    @JSStaticFunction
    public static void setItems(String paramString)
    {
      overrideTexture("images/items-opaque.png", paramString);
    }
    
    @JSStaticFunction
    public static void setTerrain(String paramString)
    {
      overrideTexture("images/terrain-atlas.tga", paramString);
    }
    
    @JSStaticFunction
    public static void setUiRenderDebug(boolean paramBoolean)
    {
      ScriptManager.nativeModPESetRenderDebug(paramBoolean);
    }
    
    @JSStaticFunction
    public static void showTipMessage(String paramString)
    {
      ScriptManager.nativeShowTipMessage(paramString);
    }
    
    @JSStaticFunction
    public static void takeScreenshot(String paramString)
    {
      ScriptManager.screenshotFileName = paramString.replace("/", "").replace("\\", "");
      ScriptManager.nativeRequestFrameCallback();
    }
    
    public String getClassName()
    {
      return "ModPE";
    }
  }
  
  private static class NativePlayerApi
    extends ScriptableObject
  {
    private static long playerEnt = 0L;
    
    @JSStaticFunction
    public static void addExp(int paramInt)
    {
      ScriptManager.nativePlayerAddExperience(paramInt);
    }
    
    @JSStaticFunction
    public static void addItemCreativeInv(int paramInt1, int paramInt2, int paramInt3)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("You must make an item with id " + paramInt1 + " before you can add it to the creative inventory.");
      }
      ScriptManager.nativeAddItemCreativeInv(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void addItemInventory(int paramInt1, int paramInt2, int paramInt3)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt1)) {
        throw new RuntimeException("invalid item id " + paramInt1);
      }
      ScriptManager.nativeAddItemInventory(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static boolean canFly()
    {
      return ScriptManager.nativePlayerCanFly();
    }
    
    @JSStaticFunction
    public static void clearInventorySlot(int paramInt)
    {
      ScriptManager.nativeClearSlotInventory(paramInt);
    }
    
    @JSStaticFunction
    public static boolean enchant(int paramInt1, int paramInt2, int paramInt3)
    {
      if ((paramInt2 < 0) || (paramInt2 > 24)) {
        throw new RuntimeException("Invalid enchantment: " + paramInt2);
      }
      return ScriptManager.nativePlayerEnchant(paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static int getArmorSlot(int paramInt)
    {
      return ScriptManager.NativeEntityApi.getArmor(Long.valueOf(getEntity()), paramInt);
    }
    
    @JSStaticFunction
    public static int getArmorSlotDamage(int paramInt)
    {
      return ScriptManager.NativeEntityApi.getArmorDamage(Long.valueOf(getEntity()), paramInt);
    }
    
    @JSStaticFunction
    public static int getCarriedItem()
    {
      return ScriptManager.nativeGetCarriedItem(0);
    }
    
    @JSStaticFunction
    public static int getCarriedItemCount()
    {
      return ScriptManager.nativeGetCarriedItem(2);
    }
    
    @JSStaticFunction
    public static int getCarriedItemData()
    {
      return ScriptManager.nativeGetCarriedItem(1);
    }
    
    @JSStaticFunction
    public static int getDimension()
    {
      return ScriptManager.nativePlayerGetDimension();
    }
    
    @JSStaticFunction
    public static ScriptManager.EnchantmentInstance[] getEnchantments(int paramInt)
    {
      int[] arrayOfInt = ScriptManager.nativePlayerGetEnchantments(paramInt);
      Object localObject;
      if (arrayOfInt == null)
      {
        localObject = null;
        return (ScriptManager.EnchantmentInstance[])localObject;
      }
      ScriptManager.EnchantmentInstance[] arrayOfEnchantmentInstance = new ScriptManager.EnchantmentInstance[arrayOfInt.length / 2];
      paramInt = 0;
      for (;;)
      {
        localObject = arrayOfEnchantmentInstance;
        if (paramInt >= arrayOfEnchantmentInstance.length) {
          break;
        }
        arrayOfEnchantmentInstance[paramInt] = new ScriptManager.EnchantmentInstance(arrayOfInt[(paramInt * 2)], arrayOfInt[(paramInt * 2 + 1)]);
        paramInt += 1;
      }
    }
    
    @JSStaticFunction
    public static long getEntity()
    {
      playerEnt = ScriptManager.nativeGetPlayerEnt();
      return playerEnt;
    }
    
    @JSStaticFunction
    public static double getExhaustion()
    {
      return ScriptManager.nativePlayerGetExhaustion();
    }
    
    @JSStaticFunction
    public static double getExp()
    {
      return ScriptManager.nativePlayerGetExperience();
    }
    
    @JSStaticFunction
    public static double getHunger()
    {
      return ScriptManager.nativePlayerGetHunger(getEntity());
    }
    
    @JSStaticFunction
    public static int getInventorySlot(int paramInt)
    {
      return ScriptManager.nativeGetSlotInventory(paramInt, 0);
    }
    
    @JSStaticFunction
    public static int getInventorySlotCount(int paramInt)
    {
      return ScriptManager.nativeGetSlotInventory(paramInt, 2);
    }
    
    @JSStaticFunction
    public static int getInventorySlotData(int paramInt)
    {
      return ScriptManager.nativeGetSlotInventory(paramInt, 1);
    }
    
    @JSStaticFunction
    public static String getItemCustomName(int paramInt)
    {
      return ScriptManager.nativePlayerGetItemCustomName(paramInt);
    }
    
    @JSStaticFunction
    public static int getLevel()
    {
      return ScriptManager.nativePlayerGetLevel();
    }
    
    @JSStaticFunction
    public static String getName(Object paramObject)
    {
      if (!isPlayer(paramObject))
      {
        if ((paramObject == null) || (ScriptManager.getEntityId(paramObject) == getEntity())) {
          return ScriptManager.access$1300();
        }
        return "Not a player";
      }
      return ScriptManager.nativeGetPlayerName(ScriptManager.getEntityId(paramObject));
    }
    
    @JSStaticFunction
    public static int getPointedBlockData()
    {
      return ScriptManager.nativePlayerGetPointedBlock(17);
    }
    
    @JSStaticFunction
    public static int getPointedBlockId()
    {
      return ScriptManager.nativePlayerGetPointedBlock(16);
    }
    
    @JSStaticFunction
    public static int getPointedBlockSide()
    {
      return ScriptManager.nativePlayerGetPointedBlock(18);
    }
    
    @JSStaticFunction
    public static int getPointedBlockX()
    {
      return ScriptManager.nativePlayerGetPointedBlock(0);
    }
    
    @JSStaticFunction
    public static int getPointedBlockY()
    {
      return ScriptManager.nativePlayerGetPointedBlock(1);
    }
    
    @JSStaticFunction
    public static int getPointedBlockZ()
    {
      return ScriptManager.nativePlayerGetPointedBlock(2);
    }
    
    @JSStaticFunction
    public static long getPointedEntity()
    {
      return ScriptManager.nativePlayerGetPointedEntity();
    }
    
    @JSStaticFunction
    public static double getPointedVecX()
    {
      return ScriptManager.nativePlayerGetPointedVec(0);
    }
    
    @JSStaticFunction
    public static double getPointedVecY()
    {
      return ScriptManager.nativePlayerGetPointedVec(1);
    }
    
    @JSStaticFunction
    public static double getPointedVecZ()
    {
      return ScriptManager.nativePlayerGetPointedVec(2);
    }
    
    @JSStaticFunction
    public static double getSaturation()
    {
      return ScriptManager.nativePlayerGetSaturation();
    }
    
    @JSStaticFunction
    public static int getScore()
    {
      return ScriptManager.nativePlayerGetScore();
    }
    
    @JSStaticFunction
    public static int getSelectedSlotId()
    {
      return ScriptManager.nativeGetSelectedSlotId();
    }
    
    @JSStaticFunction
    public static double getX()
    {
      return ScriptManager.nativeGetPlayerLoc(0);
    }
    
    @JSStaticFunction
    public static double getY()
    {
      return ScriptManager.nativeGetPlayerLoc(1);
    }
    
    @JSStaticFunction
    public static double getZ()
    {
      return ScriptManager.nativeGetPlayerLoc(2);
    }
    
    @JSStaticFunction
    public static boolean isFlying()
    {
      return ScriptManager.nativePlayerIsFlying();
    }
    
    @JSStaticFunction
    public static boolean isPlayer(Object paramObject)
    {
      return ScriptManager.NativeEntityApi.getEntityTypeId(Long.valueOf(ScriptManager.getEntityId(paramObject))) == 63;
    }
    
    @JSStaticFunction
    public static void setArmorSlot(int paramInt1, int paramInt2, int paramInt3)
    {
      if (!ScriptManager.nativeIsValidItem(paramInt2)) {
        throw new RuntimeException("invalid item id " + paramInt2);
      }
      ScriptManager.NativeEntityApi.setArmor(Long.valueOf(getEntity()), paramInt1, paramInt2, paramInt3);
    }
    
    @JSStaticFunction
    public static void setCanFly(boolean paramBoolean)
    {
      ScriptManager.nativePlayerSetCanFly(paramBoolean);
    }
    
    @JSStaticFunction
    public static void setExhaustion(double paramDouble)
    {
      ScriptManager.nativePlayerSetExhaustion((float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setExp(double paramDouble)
    {
      ScriptManager.nativePlayerSetExperience((float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setFlying(boolean paramBoolean)
    {
      ScriptManager.nativePlayerSetFlying(paramBoolean);
    }
    
    @JSStaticFunction
    public static void setHealth(int paramInt)
    {
      ScriptManager.nativeSetMobHealth(ScriptManager.nativeGetPlayerEnt(), paramInt);
    }
    
    @JSStaticFunction
    public static void setHunger(double paramDouble)
    {
      ScriptManager.nativePlayerSetHunger(getEntity(), (float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setInventorySlot(int paramInt1, int paramInt2, int paramInt3, int paramInt4)
    {
      ScriptManager.nativeSetInventorySlot(paramInt1, paramInt2, paramInt3, paramInt4);
    }
    
    @JSStaticFunction
    public static void setItemCustomName(int paramInt, String paramString)
    {
      ScriptManager.nativePlayerSetItemCustomName(paramInt, paramString);
    }
    
    @JSStaticFunction
    public static void setLevel(int paramInt)
    {
      ScriptManager.nativePlayerSetLevel(paramInt);
    }
    
    @JSStaticFunction
    public static void setSaturation(double paramDouble)
    {
      ScriptManager.nativePlayerSetSaturation((float)paramDouble);
    }
    
    @JSStaticFunction
    public static void setSelectedSlotId(int paramInt)
    {
      ScriptManager.nativeSetSelectedSlotId(paramInt);
    }
    
    public String getClassName()
    {
      return "Player";
    }
  }
  
  private static class NativePointer
    extends ScriptableObject
  {
    public long value;
    
    public NativePointer(long paramLong)
    {
      this.value = paramLong;
    }
    
    public String getClassName()
    {
      return "NativePointer";
    }
  }
  
  private static class NativeServerApi
    extends ScriptableObject
  {
    @JSStaticFunction
    public static String getAddress()
    {
      return ScriptManager.serverAddress;
    }
    
    @JSStaticFunction
    public static String[] getAllPlayerNames()
    {
      String[] arrayOfString = new String[ScriptManager.allplayers.size()];
      int i = 0;
      while (arrayOfString.length > i)
      {
        arrayOfString[i] = ScriptManager.nativeGetPlayerName(((Long)ScriptManager.allplayers.get(i)).longValue());
        i += 1;
      }
      return arrayOfString;
    }
    
    @JSStaticFunction
    public static long[] getAllPlayers()
    {
      long[] arrayOfLong = new long[ScriptManager.allplayers.size()];
      int i = 0;
      while (arrayOfLong.length > i)
      {
        arrayOfLong[i] = ((Long)ScriptManager.allplayers.get(i)).longValue();
        i += 1;
      }
      return arrayOfLong;
    }
    
    @JSStaticFunction
    public static int getPort()
    {
      return ScriptManager.serverPort;
    }
    
    @JSStaticFunction
    public static void joinServer(String paramString, int paramInt)
    {
      throw new RuntimeException("FIXME 0.11");
    }
    
    @JSStaticFunction
    public static void sendChat(String paramString)
    {
      ScriptManager.nativeSendChat(paramString);
    }
    
    public String getClassName()
    {
      return "Server";
    }
  }
  
  private static class ParseThread
    implements Runnable
  {
    public Exception error = null;
    private Reader in;
    private String sourceName;
    
    public ParseThread(Reader paramReader, String paramString)
    {
      this.in = paramReader;
      this.sourceName = paramString;
    }
    
    public void run()
    {
      try
      {
        org.mozilla.javascript.Context localContext = org.mozilla.javascript.Context.enter();
        ScriptManager.setupContext(localContext);
        ScriptManager.initJustLoadedScript(localContext, localContext.compileReader(this.in, this.sourceName, 0, null), this.sourceName);
        org.mozilla.javascript.Context.exit();
        return;
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
        this.error = localException;
      }
    }
  }
  
  public static class ScriptState
  {
    public int errors = 0;
    public String name;
    public Scriptable scope;
    public Script script;
    
    protected ScriptState(Script paramScript, Scriptable paramScriptable, String paramString)
    {
      this.script = paramScript;
      this.scope = paramScriptable;
      this.name = paramString;
    }
  }
  
  private static class SelectLevelRequest
  {
    public String dir;
    public int gameMode = 0;
    public String name;
    public String seed;
  }
  
  private static class SkinLoader
    implements Runnable
  {
    private long entityId;
    
    public SkinLoader(long paramLong)
    {
      this.entityId = paramLong;
    }
    
    public void run()
    {
      try
      {
        String str2 = ScriptManager.nativeGetPlayerName(this.entityId);
        System.out.println("Player name: " + str2 + " entity ID: " + this.entityId);
        if (str2 == null) {
          return;
        }
        String str1 = str2;
        if (ScriptManager.access$400()) {
          str1 = str2.toLowerCase();
        }
        if (str1.length() > 0)
        {
          str2 = "mob/" + str1 + ".png";
          File localFile = ScriptManager.getTextureOverrideFile("images/" + str2);
          if (localFile != null)
          {
            new Thread(new ScriptTextureDownloader(new URL(ScriptManager.getSkinURL(str1)), localFile, new ScriptManager.AfterSkinDownloadAction(this.entityId, str2), false)).start();
            return;
          }
        }
      }
      catch (Exception localException)
      {
        localException.printStackTrace();
      }
    }
  }
  
  private static class TextureRequests
  {
    public int[] coords;
    public String[] names;
  }
}


/* Location:              C:\Users\Genbu Hase\ÉhÉLÉÖÉÅÉìÉg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\ScriptManager.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */