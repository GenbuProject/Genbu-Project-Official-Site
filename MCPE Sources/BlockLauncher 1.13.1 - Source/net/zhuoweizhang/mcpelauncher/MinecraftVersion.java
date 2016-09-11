package net.zhuoweizhang.mcpelauncher;

import android.content.Context;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import java.util.HashMap;
import java.util.Map;

public final class MinecraftVersion
{
  public static final boolean FUZZY_VERSION = true;
  public static MinecraftVersion amazonVer;
  public static Context context;
  public static Map<Integer, MinecraftVersion> versions = new HashMap();
  public byte[] guiBlocksPatch;
  public byte[] guiBlocksUnpatch;
  public int ipAddressOffset;
  public int libLoadOffset;
  public int libLoadOffsetBegin;
  public boolean needsWarning;
  public byte[] noAnimationPatch;
  public byte[] noAnimationUnpatch;
  public int portOffset;
  public PatchTranslator translator;
  public int versionCode;
  
  static
  {
    add(new MinecraftVersion(500801011, false, 2673072, 4096, null, -1, null, null, null, null, -1));
    add(new MinecraftVersion(300801011, false, 2673072, 4096, null, -1, null, null, null, null, -1));
    add(new MinecraftVersion(400801011, false, 2673072, 4096, new AmazonTranslator080(), -1, null, null, null, null, -1));
  }
  
  public MinecraftVersion(int paramInt1, boolean paramBoolean, int paramInt2, int paramInt3, PatchTranslator paramPatchTranslator, int paramInt4, byte[] paramArrayOfByte1, byte[] paramArrayOfByte2, byte[] paramArrayOfByte3, byte[] paramArrayOfByte4, int paramInt5)
  {
    this.versionCode = paramInt1;
    this.needsWarning = paramBoolean;
    this.libLoadOffsetBegin = paramInt2;
    this.libLoadOffset = paramInt3;
    this.ipAddressOffset = paramInt4;
    this.guiBlocksPatch = paramArrayOfByte1;
    this.guiBlocksUnpatch = paramArrayOfByte2;
    this.noAnimationPatch = paramArrayOfByte3;
    this.noAnimationUnpatch = paramArrayOfByte4;
    this.portOffset = paramInt5;
    this.translator = paramPatchTranslator;
  }
  
  public static void add(MinecraftVersion paramMinecraftVersion)
  {
    versions.put(Integer.valueOf(paramMinecraftVersion.versionCode), paramMinecraftVersion);
  }
  
  public static MinecraftVersion get(int paramInt)
  {
    Object localObject2 = (MinecraftVersion)versions.get(Integer.valueOf(paramInt));
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = getDefault();
    }
    localObject2 = localObject1;
    if (((MinecraftVersion)localObject1).versionCode == 500801011)
    {
      localObject2 = localObject1;
      if (isAmazon()) {
        localObject2 = amazonVer;
      }
    }
    return (MinecraftVersion)localObject2;
  }
  
  public static MinecraftVersion get(Context paramContext)
  {
    try
    {
      paramContext = get(paramContext.getPackageManager().getPackageInfo("com.mojang.minecraftpe", 0).versionCode);
      return paramContext;
    }
    catch (Exception paramContext) {}
    return getDefault();
  }
  
  public static MinecraftVersion getDefault()
  {
    MinecraftVersion localMinecraftVersion = (MinecraftVersion)versions.get(Integer.valueOf(500801011));
    if (isAmazon()) {
      localMinecraftVersion = amazonVer;
    }
    return localMinecraftVersion;
  }
  
  public static MinecraftVersion getRaw(int paramInt)
  {
    Object localObject2 = (MinecraftVersion)versions.get(Integer.valueOf(paramInt));
    Object localObject1 = localObject2;
    if (localObject2 == null) {
      localObject1 = getDefault();
    }
    localObject2 = localObject1;
    if (localObject1 != null)
    {
      localObject2 = localObject1;
      if (((MinecraftVersion)localObject1).versionCode == 500801011)
      {
        localObject2 = localObject1;
        if (isAmazon()) {
          localObject2 = amazonVer;
        }
      }
    }
    return (MinecraftVersion)localObject2;
  }
  
  public static boolean isAmazon()
  {
    return false;
  }
  
  public static class AmazonTranslator
    extends MinecraftVersion.PatchTranslator
  {
    public int get(int paramInt)
    {
      if (paramInt < 896608) {
        return paramInt + 64;
      }
      return paramInt + 24;
    }
  }
  
  public static class AmazonTranslator080
    extends MinecraftVersion.PatchTranslator
  {
    public int get(int paramInt)
    {
      return paramInt - 40;
    }
  }
  
  public static abstract class PatchTranslator
  {
    public abstract int get(int paramInt);
  }
}


/* Location:              C:\Users\Genbu Hase\ƒhƒLƒ…ƒƒ“ƒg\Genbu\Tool\Programing\Jad\BlockLauncher 1.13.1.jar!\net\zhuoweizhang\mcpelauncher\MinecraftVersion.class
 * Java compiler version: 6 (50.0)
 * JD-Core Version:       0.7.1-SNAPSHOT-20140817
 */